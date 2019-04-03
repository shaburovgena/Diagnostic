import Vue from 'vue';
import App from 'pages/App.vue';
import { connect } from "./util/ws";

connect();
new Vue({
    el: '#app',
    render: a => a(App)
});

