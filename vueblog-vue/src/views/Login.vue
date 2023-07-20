<template>
  <div>
    <el-container>
      <el-header>
        <img class="mlogo" src="https://geoer.cn/images/avatar.png" alt="logo图片" />
      </el-header>
      <el-main>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="用户姓名" prop="username">
            <el-input v-model="ruleForm.username"></el-input>
          </el-form-item>
          <el-form-item label="用户密码" prop="password">
            <el-input v-model="ruleForm.password"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">立即登录</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
  </div>
</template>
<script>
export default {
  name: "Login",
  data () {
    return {
      ruleForm: {
        username: '',
        password: ''
      },
      rules: {
        username: [
          { required: true, message: '请输入用户名称', trigger: 'blur' },
          { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }

        ],
        password: [
          { required: true, message: '请选择密码', trigger: 'blur' }
        ]
      }
    };
  },
  methods: {
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
            const _this = this;//获取整个vue的this
            //   alert('submit!');
            //   _this.$http.post("http://localhost:8081/login", _this.ruleForm).then(res => {
            _this.$http.post("/login", _this.ruleForm).then(res => {

            // console.log(res)
            const jwt = res.headers['authorization'];
            const userInfo = res.data.data
            //存储(共享)全局变量jwt和userInfo
            _this.$store.commit("SET_TOKEN", jwt);
            _this.$store.commit("SET_USERINFO", userInfo);
            //登录成功后自动跳转到博客列表
            _this.$router.push("/blogs")
            //获取token和getUser
            // console.log(_this.$store.getters.getToken)
            // console.log(_this.$store.getters.getUser) 

          })
          .catch((err)=>{
            console.log(err);
         })

        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm (formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>

<style scoped>
.el-header,
.el-footer {
  background-color: #b3c0d1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-main {
  /* background-color: #e9eef3; */
  color: #333;
  text-align: center;
  line-height: 160px;
}
.mlogo {
  height: 80%;
}

.demo-ruleForm {
  max-width: 500px;
  margin: 0 auto;
}
</style>
