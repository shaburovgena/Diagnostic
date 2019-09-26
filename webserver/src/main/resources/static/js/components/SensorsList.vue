<template>
    <v-content>
        <v-container>
            <v-hover v-for="sensor in sensors">
            <template v-slot="{ hover }">
                <v-card :elevation="hover ? 24 : 6"
                        class="ma-3 mx-auto pa-6" >{{sensor.title}}:{{sensor.port}} in Group
                    {{sensor.groupSensor.id}}
                    <!--<a v-bind:href="url + sensor.groupSensor.id">-->
                        <!--</a>-->
                </v-card>
            </template>
        </v-hover>
        </v-container>

    </v-content>
</template>

<script>
    export default {
        name: "SensorsList",
        props: ['sensors'],
        data() {
            return {
                url: '/group/'
            }
        },
        // created: function () {//Действия выполняемые при создании app
        //     const sensorsApi = Vue.resource('/sensor{/id}')
        //     sensorsApi.get().then(result =>
        //         result.json().then(data =>
        //             data.forEach(sensor => this.sensors.push(sensor)))
        //     )
        // },
        methods: {}
    }
</script>

<style scoped>
</style>