// 引入axios依赖
import axios from 'axios'
// 引入element-ui依赖
import {ElementPlus,ElMessage} from "element-plus"
import router from "./router/index"
import store from './store/index'



//配置默认前缀：也可以在config中配置
axios.defaults.baseURL= "http://localhost:8081/";  //获取连接前缀相当于 http://localhost:8081/。axios发送请求可以直接写后面部分了

//配置前置拦截
// 前置拦截，其实可以统一为所有需要权限的请求装配上header的token信息，这样不需要在使用是再配置，我的小项目比较小，所以，还是免了吧~
axios.interceptors.request.use(config => { 
  return config
})

//配置后置拦截
// res => res.data  取出data数据，将来调用接口的时候直接拿到的就是后台的数据
axios.interceptors.response.use(response=>{
    let res = response.data; 
    if(res.code == 200){
      return response
    }else{ 
        console.log(res)
        ElMessage.error(res.msg)
        //返回一个异常提示就不会继续往下走axios的then了 不+reject的话 res=>的里面 还是会继续走的
        return Promise.reject(response.data.msg)
    }
     // 捕获并处理后台异常信息
  },error=>{
     // 使得异常信息更加友好
    console.log(error) 
    if (error.response.data) { //data不为空时
      error.message = error.response.data.msg
      console.log("-------------------------")
      console.log(error.message)
      console.log("-------------------------")
    }
    if(error.response.status == 401){
      store.commit('REMOVE_INFO')//清空token userinfo
      router.push("/login")  //跳转登录页面
    } 
    ElMessage.error(error.message)
    
    return Promise.reject(error)
  }

)
