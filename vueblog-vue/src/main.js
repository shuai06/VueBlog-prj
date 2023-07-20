import { createApp } from 'vue'
import App from './App.vue'

// 引入element-plus
import ElementPlus from "element-plus"
import "element-plus/dist/index.css"
import router from "./router/index"
import store from './store/index'
import axios from 'axios'
import "./axios.js"

import VueMarkdownEditor from '@kangc/v-md-editor';
import '@kangc/v-md-editor/lib/style/base-editor.css';
import vuepressTheme from '@kangc/v-md-editor/lib/theme/vuepress.js';
import '@kangc/v-md-editor/lib/theme/style/vuepress.css';
import Prism from 'prismjs';
import './permission.js' // 路由拦截



VueMarkdownEditor.use(vuepressTheme, {
    Prism,
  });


// // 使用element-plus
const app = createApp(App)
            .use(ElementPlus)
            .use(router)
            .use(store)
            .use(VueMarkdownEditor)

app.mount('#app')
//将axios挂载为app的全局自定义属性之后
//每个组件可以通过this直接访问到全局挂载的自定义属性：在组件中发起axios请求：this.$http.get('/users')
app.config.globalProperties.$http = axios
