<template>
    <v-app>
        <v-app-bar app>
            <v-btn v-if = "profile" icon href="/group" flat>
                <v-icon>{{homeBtn}}</v-icon>
            </v-btn>
            <v-spacer></v-spacer>
            <v-toolbar-title>Graph panel</v-toolbar-title>
            <v-btn v-if = "profile" icon href="/" flat>
                <v-icon>{{refreshBtn}}</v-icon>
            </v-btn>
            <v-spacer></v-spacer>

            <v-btn v-if = "profile" icon href="/logout" flat>
                <v-icon>{{logout}}</v-icon>
            </v-btn>

        </v-app-bar>

        <v-content>
            <v-container  v-if="!profile">
                <a href="/login">Необходимо авторизоваться</a>
            </v-container >
            <v-container v-if = "profile">
                <sensors-list :sensors="sensors"/>
            </v-container >
        </v-content>

    </v-app>

</template>

<script>
    import { mdiExitToApp } from '@mdi/js'
    import { mdiHome } from '@mdi/js'
    import { mdiRefresh } from '@mdi/js'
    import SensorsList from '../components/SensorsList.vue'
    export default {
        components: { SensorsList},
        data() {
            return {
                sensors: frontendData.sensors,
                profile: frontendData.profile,
                logout: mdiExitToApp,
                homeBtn: mdiHome,
                refreshBtn :mdiRefresh
            }
        },
    }
</script>

<style>
    .main-app {
        color: red;
    }
</style>