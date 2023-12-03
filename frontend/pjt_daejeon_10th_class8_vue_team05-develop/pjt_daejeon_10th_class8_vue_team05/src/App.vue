<script setup>
import { RouterLink, RouterView } from "vue-router";
import TheMainHeader from "./components/layout/TheMainHeader.vue";
</script>

<template>
  <div>
    <TheMainHeader class="position-fixed z-3" />
    <div id="header-block"></div>

    <RouterView />
  </div>
</template>

<style scoped>
.title {
  margin-top: 50px;
  margin-bottom: 50px;
}

#header-block {
  height: 150px;
}
</style>
