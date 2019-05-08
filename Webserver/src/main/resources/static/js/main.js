import Vue from 'vue'
import App from 'pages/App.vue'
import VueResource from 'vue-resource'

Vue.use(VueResource)
new Vue({
    el: '#app',
    //Необходимо в компонент #app поместить отрендеренный App
    render: a => a(App),
});