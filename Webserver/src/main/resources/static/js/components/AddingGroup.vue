<template>
    <v-container>
        <form class="form" action="/login" method="post">
            <v-content>
                <v-layout column wrap>
                    <v-text-field
                            name="groupName"
                            autofocus solo type="text"
                            placeholder="Group Name"
                            @keyup.enter="submit"/>
                    <v-text-field
                            name="groupTag"
                            solo type="password"
                            placeholder="Group Tag"
                            @keyup.enter="submit"/>
                </v-layout>
            </v-content>
            <v-content>
                <v-layout justify-center row wrap>
                    <v-btn type="submit">Add</v-btn>
                    <v-spacer></v-spacer>
                    <v-btn >Cancel</v-btn>
                </v-layout>
            </v-content>
        </form>
    </v-container>
</template>

<script>
    export default {
        name: "AddingGroup"
    }
</script>

<style scoped>

</style>