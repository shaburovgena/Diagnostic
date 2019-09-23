<template>
    <v-container>
        <form class="form" action="/register" method="post">
            <v-content>
                <v-layout column wrap>
                    <v-text-field
                            name="username"
                            autofocus solo type="text"
                            placeholder="Username"
                            @keyup.enter="submit"/>
                    <v-text-field
                            name="password"
                            solo type="password"
                            placeholder="Password"
                            @keyup.enter="submit"/>
                    <v-text-field
                                  name="passwordConfirm"
                                  solo type="password"
                                  placeholder="Confirm Password"
                                  @keyup.enter="submit"/>
                    <v-text-field
                                  name="email"
                                  autofocus solo type="email"
                                  placeholder="email@example.com"
                                  @keyup.enter="submit"/>
                </v-layout>
            </v-content>
            <v-content>
                <v-layout justify-center row wrap>
                    <v-btn  type="submit">Sign Up</v-btn>
                    <v-spacer></v-spacer>
                </v-layout>
            </v-content>
        </form>
    </v-container>
</template>

<script>
    export default {
        name: "RegisterPage"
    }
</script>

<style scoped>

</style>