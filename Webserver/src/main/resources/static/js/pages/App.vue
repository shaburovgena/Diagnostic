<template>
    <div >
        <div v-for="sensor in sensors"><a href="/group">{{sensor.title}}</a></div>
    </div>
</template>

<script>


    export default {
        data(){
            return{
                sensors:[]
            }
        },

        created: function () {//Действия выполняемые при создании app
            const sensorsApi = Vue.resource('/sensor{/id}')
           sensorsApi.get().then(result =>
           result.json().then(data =>
           data.forEach(sensor => this.sensors.push(sensor)))
           )
        },
    }
</script>

<style>
</style>