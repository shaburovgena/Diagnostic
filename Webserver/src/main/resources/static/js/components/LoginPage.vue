<template>
    <v-container>
        <form class="form" action="/login" method="post">
            <v-content>
                <v-layout column wrap>
                    <v-text-field
                            name="username"
                            autofocus solo type="text"
                            placeholder="Username"
                            v-model="username"
                            :rules="[usernameEmpty,  userFound]"
                            @keyup.enter="submit"
                    />
                    <v-text-field
                            name="password"
                            solo type="password"
                            placeholder="Password"
                            @keyup.enter="submit"
                            @focus="checkUsername"/>
                </v-layout>
            </v-content>
            <v-content>
                <v-layout justify-center row wrap>
                    <v-btn type="submit">Sign In</v-btn>
                    <v-spacer></v-spacer>

                </v-layout>
            </v-content>
        </form>
    </v-container>
</template>

<script>

    export default {
        name: "LoginPage",
        data() {
            return {
                url: 'http://localhost:8080/checkUser',
                validUsername: false,
                username: '',

            }
        },
        computed: {
            usernameEmpty() {
                return v => !!v || 'Enter a username'
            },
            userFound() {
                return () => this.validUsername || 'User not found'
            },
        },
        methods: {
            checkUsername() {
                axios.post(this.url, this.username)
                    .then((response) => {
                        if (response.data === true) {
                            console.log(`Rule  found is ${this.userFound()} ${this.username}`)
                            this.validUsername = response.data;
                        } else {
                            console.log(`Rule  found is ${this.userFound()} ${this.username}`)
                            this.validUsername = null;
                        }

                    })
                    .catch((error) => {
                        console.log(error);
                    });
            }
        },
        watch: {//Отслеживание изменений через v-model
            // username: function () {
            //     if (this.username != 0)
            //         this.username = username
            // },
            //     checkUsername() {
            //         axios.post(this.url, this.checkUsername)
            //             .then((response) => {
            //                 if (response.data === true) {
            //                     console.log(`Rule  found is ${this.userFound()}`)
            //                     this.validUsername = response.data;
            //                 } else {
            //                     console.log(`Rule  found is ${this.userFound()}`)
            //                     this.validUsername = null
            //                 }
            //
            //             })
            //             .catch((error) => {
            //                 console.log(error);
            //             });
            //     }
        }

    }
</script>

<style scoped>

</style>