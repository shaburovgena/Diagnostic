<template>
    <v-app>
        <v-app-bar app>
            <v-btn v-if="profile" icon href="/group">
                <v-icon>{{homeBtn}}</v-icon>
            </v-btn>
            <v-spacer></v-spacer>
            <v-toolbar-title>Graphic panel</v-toolbar-title>
            <v-btn v-if="profile" icon href="/">
                <v-icon>{{refreshBtn}}</v-icon>
            </v-btn>
            <v-spacer></v-spacer>
            <div v-if="profile">
                <v-text>{{profile.username}}&nbsp</v-text>
                <v-btn icon href="/logout">
                    <v-icon>{{logoutBtn}}</v-icon>
                </v-btn>
            </div>
            <div v-else>
                <v-btn icon @click="loginPage">
                    <v-icon>{{loginBtn}}</v-icon>
                </v-btn>
                <v-btn icon @click="registerPage">
                    <v-icon>{{registerBtn}}</v-icon>
                </v-btn>
            </div>
        </v-app-bar>


        <v-container grid-list-lg mt-12>
            <v-layout align-start justify-end row wrap>
                <v-flex lg3 d-flex v-if="isLogin && !profile">
                    <login-page/>
                </v-flex>
                <v-flex lg3 d-flex v-if="isRegister">
                    <register-page/>
                </v-flex>
                <v-flex lg12 v-if="profile">
                    <sensors-list :sensors="sensors"/>
                </v-flex>
            </v-layout>
        </v-container>
        <v-container grid-list-lg mt-12>
            <v-layout align-start justify-end row wrap mb-12>
                <v-flex lg3 d-flex v-if="isAddingGroup">
                <adding-group/>
                </v-flex>
            </v-layout>
        </v-container>
        <v-bottom-navigation absolute>
            <div></div>
            <v-spacer></v-spacer>
            <v-btn v-if="profile" icon @click="groupAdd">
                <v-icon v-if="!isAddingGroup">{{groupPlusBtn}}</v-icon>
                <v-icon v-else>{{groupRemoveBtn}}</v-icon>
            </v-btn>
        </v-bottom-navigation>
    </v-app>
</template>

<script>
    import {mdiLogout} from '@mdi/js'
    import {mdiLogin} from '@mdi/js'
    import {mdiHome} from '@mdi/js'
    import {mdiRefresh} from '@mdi/js'
    import {mdiFolderPlusOutline} from '@mdi/js'
    import {mdiFolderRemoveOutline} from '@mdi/js'
    import {mdiAccountPlusOutline} from '@mdi/js'
    import SensorsList from '../components/SensorsList.vue'
    import LoginPage from '../components/LoginPage.vue'
    import RegisterPage from '../components/RegisterPage.vue'
    import AddingGroup from '../components/AddingGroup.vue'
    import Tmp from '../components/Tmp.vue'

    export default {
        components: {SensorsList, LoginPage, RegisterPage, Tmp, AddingGroup},
        data() {
            return {
                sensors: frontendData.sensors,
                profile: frontendData.profile,
                logoutBtn: mdiLogout,
                loginBtn: mdiLogin,
                registerBtn: mdiAccountPlusOutline,
                homeBtn: mdiHome,
                refreshBtn: mdiRefresh,
                groupPlusBtn: mdiFolderPlusOutline,
                groupRemoveBtn: mdiFolderRemoveOutline,
                isLogin: true,
                isRegister: false,
                isAddingGroup: false
            }
        },
        methods: {
            loginPage: function () {
                if (!this.isLogin) {
                    this.isLogin = true
                    this.isRegister = false
                } else {
                    this.isLogin = false
                }
            },
            registerPage: function () {
                if (!this.isRegister) {
                    this.isRegister = true
                    this.isLogin = false
                } else {
                    this.isRegister = false
                }
            },
            groupAdd: function () {
                if (!this.isAddingGroup) {
                    this.isAddingGroup = true
                } else {
                    this.isAddingGroup = false
                }
            }

        }
    }
</script>

<style>

</style>