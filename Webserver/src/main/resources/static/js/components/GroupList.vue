<template>
    <div>
        <div v-for="group in groups">
            <div>
                <h1>{{group.groupName}}</h1>
            </div>
        </div>


    </div>
</template>

<script>
    export default {
        name: "GroupList",
        data() {
            return {
                groups: [],
            }
        },

        data() {
            return {
                groups: groups
            }
        },

        // created: function () {//Действия выполняемые при создании app
        //     const groupsApi = Vue.resource('/panel')
        //     groupsApi.get().then(result =>
        //         result.json().then(data =>
        //             data.forEach(group => this.groups.push(group)))
        //     )
        // },
    }

</script>

<style scoped>

</style>