<script setup>
</script>

<template>
   <div class="container text-center mt-3">
    <div class="alert alert-info fs-1" role="alert" >NOTICE</div>
    <!-- <router-link to="/qna/list" class="nav-link px-2"> <h3><b-icon icon="journals"></b-icon> Q&A</h3> </router-link> -->

    <router-view></router-view>
  </div>
</template>

<style scoped>
mark.sky {
  background: linear-gradient(to top, #54fff9 20%, transparent 30%);
}
</style>
