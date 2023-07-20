<template>
  <div>
    <Header></Header>
    <div class="blog">
      <h2>{{blog.title}}</h2>

      <el-link icon="el-icon-edit" v-if="ownBlog">
        <!-- 按钮设置权限，不是自己的id不显示编辑 -->
        <router-link :to="{name:'BlogEdit',params:{blogid:blog.id}}">
          编辑
        </router-link>
      </el-link>

      <el-link icon="el-icon-delete" v-if="ownBlog" class="linklist">
        <el-button type="danger" round @click="delblog">删除</el-button>
      </el-link>

      <el-divider></el-divider>
      <div class="markdown-body" v-html="blog.content"></div>
    </div>
  </div>
</template>

<script>
// import MarkdownIT from 'vue3-markdown-it';
import 'highlight.js/styles/a11y-dark.css';
import Header from "../components/Header";
export default {
  name: "BlogDetail",
  components: { Header },
  data () {
    return {
      blog: {
        id: '',
        title: '',
        content: '',
        description: ''

      },
      ownBlog: false
    }
  },
   methods: {
    delblog () {
      const blogId = this.$route.params.blogid
      const _this = this
      if (blogId) {
        this.$confirm('此操作将永久删除该文章, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          _this.$http.post(`/blogdel/${blogId}`, null, {
            headers: {
              "Authorization": localStorage.getItem("token")
            }
          }).then(res => {
            this.$message({
              type: 'success',
              message: res.data.data
            });
            _this.$router.push("/blogs")
          })

        }).catch(() => {

          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });


      }
    }
  },

  created () {
    //获取动态路由的 blogid
    const blogId = this.$route.params.blogid
    // const _this = this
    if (blogId) {
      this.$http.get("/blog/" + blogId).then(res => {
        const blog = res.data.data
        this.blog.id = blog.id
        this.blog.title = blog.title
        this.blog.description = blog.description

        //MardownIt 渲染
        var MardownIt = require("markdown-it")
        var md = new MardownIt();
        var result = md.render(blog.content)
        this.blog.content = result
        //查看是否是登录人 是则可以编辑
        this.ownBlog = (blog.userId === this.$store.getters.getUser.id)
        console.log(this.ownBlog)
      })
    }

  }
}
</script>

<style scoped>
.blog {
  margin-top: 10px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  width: 100%;
  min-height: 700px;
  padding: 10px;
}
</style>
