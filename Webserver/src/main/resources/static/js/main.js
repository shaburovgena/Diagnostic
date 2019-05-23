import Vue from 'vue'
import App from 'pages/App.vue'
import VueResource from 'vue-resource'
import VueRouter from 'vue-router'

Vue.use(VueRouter)
Vue.use(VueResource)
new Vue({
    el: '#app',
    VueRouter,
    //Необходимо в компонент #app поместить отрендеренный App
    render: a => a(App),
});