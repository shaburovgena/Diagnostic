<template>
    <v-container>
        <form class="form" action="/login" method="post">
            <v-content>
                <v-layout column wrap>
                    <v-text-field
                            name="username"
                            autofocus solo type="text"
                            placeholder="Username"
                            v-model="checkUsername"
                            :rules="[usernameEmpty,  userFound]"
                            @keyup.enter="submit"
                    />
                    <v-text-field
                            name="password"
                            solo type="password"
                            placeholder="Password"
                            @keyup.enter="submit"
                            />
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
                checkUsername: '',

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

        watch: {//Отслеживание изменений через v-model
              checkUsername() {
                   axios.post(this.url, this.checkUsername)
                        .then((response) => {
                            console.log(response.data)
                            this.validUsername = response.data
                            return response.data
                        })
                        .catch((error) => {
                            console.log(error);
                        });

                }
        }

    }
</script>

<style scoped>

</style>