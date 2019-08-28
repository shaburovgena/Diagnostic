<template>
    <div>
        <div>
            <sensors-list/>
        </div>
        <v-spacer></v-spacer>
        <!--<div>-->
            <!--<Tmp/>-->
        <!--</div>-->
    </div>

</template>

<script>

    import Tmp from '../components/Tmp.vue'
    import SensorsList from '../components/SensorsList.vue'

    export default {
        components: {Tmp, SensorsList},

    }
</script>

<style>
</style>