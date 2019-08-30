<template>
    <div>
        <div v-for="sensor in sensors">
            <div>
                <a v-bind:href="url + sensor.groupSensor.id">{{sensor.title}}:{{sensor.port}} in Group
                    {{sensor.groupSensor.id}}
                    Value {{sensor.value}}</a>
            </div>
        </div>

        <div>
            <h1><a href="/group">Main page</a></h1>
        </div>
    </div>
</template>

<script>
    export default {
        name: "SensorsList",
        data() {
            return {
                sensors: [],
                url: '/group/'
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

<style scoped>

</style>