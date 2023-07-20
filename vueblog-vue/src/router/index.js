import { createRouter,createWebHashHistory} from "vue-router";


//为保证代码整洁，可以将routes=[{…}]部分提取到另一js文件；或通过api动态加载路由
import routes from './routes'

//创建一个路由器
const router = createRouter({
    routes:routes,
    history:createWebHashHistory(process.env.BASE_URL)
})

export default router