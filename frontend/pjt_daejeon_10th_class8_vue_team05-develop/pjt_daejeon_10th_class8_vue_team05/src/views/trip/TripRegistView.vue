<script setup>
import TripRegistBasic from "../../components/trip/TripRegistBasic2.vue";
import TripRegistLocation from "../../components/trip/TripRegistLocation.vue"
import { ref } from "vue";

const basicInfo = ref(false);

</script>

<template>
    <div id="main">
        <h1>여행 계획 등록하기</h1>
        <TripRegistBasic v-if="basicInfo == false" @next-regist="basicInfo = !basicInfo"/>
        <TripRegistLocation v-else/>
    </div>
</template>


<style scoped>
#main{
    text-align: center;
}
</style>