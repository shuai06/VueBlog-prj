
// import Demo from '@/views/Demo' 
import Login from '@/views/Login' 
import Blogs from '@/views/Blogs' 
import BlogEdit from '@/views/BlogEdit' 
import BlogDetail from '@/views/BlogDetail' 

//创建具体的路由
const routes = [
    {
        path: '/',
        name: 'Index',
        redirect:{name : "Blogs"}
      },
      {
        path: '/blogs',
        name: 'Blogs',
        component: Blogs
      },{
      path: '/Login',
      name: 'Login',
      component: Login
      },{
        path: '/blog/add',   // 注意放在 path: '/blog/:blogId'之前
        name: 'BlogAdd',
        component: BlogEdit,
        meta: {
            requireAuth: true
          }
      }, 
    //   {
    //     path: '/Demo',
    //     name: 'Demo',
    //     component: Demo
    //   },
      {
        path: '/blog/:blogid',
        name: 'BlogDetail',
        component: BlogDetail
      } ,{
        // path: '/blog/:blogid/edit', vue2,而vue3中应该edit在前面，id在后面
        path: '/blog/edit/:blogid',
        name: 'BlogEdit', 
        component: BlogEdit,
        meta: {
            requireAuth: true
          }
      }
  
]

export default routes