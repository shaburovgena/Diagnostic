<template>
    <v-container>
        <form class="form" action="/login" method="post">
            <v-content>
                <v-layout column wrap>
                    <v-text-field
                            name="username"
                            autofocus solo type="text"
                            placeholder="Username"
                            v-model="usernameText"
                            @keyup.enter="submit"/>
                    <v-text-field
                            name="password"
                            solo type="password"
                            placeholder="Password"
                            v-model="passwordText"
                            @keyup.enter="submit"/>
                </v-layout>
            </v-content>
            <v-content>
                <v-layout justify-center row wrap>
                    <v-btn type="submit">Login</v-btn>
                    <v-spacer></v-spacer>
                    <v-btn @click="login">Registration</v-btn>
                </v-layout>
            </v-content>
        </form>
    </v-container>
</template>

<script>

    export default {
        name: "LoginPage",
        data: function () {
            return {
                usernameText: '',
                passwordText: '',
                user: [{
                    username: '',
                    password: ''
                }],
                str: ''
            }
        },
        watch: {
            usernameText: function () {
                if (this.usernameText != 0) {
                    this.usernameText = usernameText

                }
            },
            passwordText: function () {
                if (this.passwordText != 0) {
                    this.passwordText = passwordText

                }
            },
        },
        methods: {
            login: function () {
                this.user = [{
                    username: this.usernameText,
                    password: this.passwordText
                }],

                    // this.user.username = this.usernameText,
                    //     this.user.password = this.passwordText,
                    axios({
                        url: '/signin',
                        // data:[{username:this.user.username},{password:this.user.password}],
                        data: this.user,
                        method: 'POST'
                    })
                        .then(response => {
                            console.log(response.data);
                        })
                        .catch(e => {
                            console.log(e);
                        }),
                    this.usernameText = '',
                    this.passwordText = ''

            },


        },

    }
</script>

<style scoped>

</style>