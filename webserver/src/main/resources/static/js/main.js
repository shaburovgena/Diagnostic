import Vue from 'vue'
import App from 'pages/App.vue'
import VueResource from 'vue-resource'
import VueRouter from 'vue-router'
import 'vuetify/dist/vuetify.min.css'
import {connect} from "./util/ws";

connect()

Vue.use(VueRouter)
Vue.use(VueResource)
new Vue({
    el: '#app',
    VueRouter,
    //Необходимо в компонент #app поместить отрендеренный App
    render: a => a(App),
});