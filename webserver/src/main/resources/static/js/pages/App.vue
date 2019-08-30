<template>
    <div>
        <div>
            <h1><a href="/group">Main page</a></h1>
        </div>
        <!--<div>-->
            <!--<group-list/>-->
        <!--</div>-->
        <div v-for="group in groups">
            <div>
                <h1>{{group.groupName}}</h1>
            </div>
        </div>

        <!--<div>-->
            <!--<Tmp/>-->
        <!--</div>-->
    </div>
</template>

<script>

    import GroupList from '../components/GroupList.vue'
    import { addHandler } from 'util/ws'
    import { getIndex } from 'util/collections'
    export default {
        components: {
            GroupList
        },
        data() {
            return {
                groups: groups,
            }
        },
        created() {
            addHandler(data => {
                let index = getIndex(this.groups, data.id)
                if (index > -1) {
                    this.groups.splice(index, 1, data)
                } else {
                    this.groups.push(data)
                }
            })
        }
    }
</script>

<style>
</style>