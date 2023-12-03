<script setup>
import { ref } from 'vue';
import LocationBookmark from '../../components/trip/LocationBookmark.vue';
import TripPlanBookmark from '../../components/trip/TripPlanBookmark.vue';

const currentTab = ref('location'); // 기본값은 'location'

const changeTab = (tab) => {
  currentTab.value = tab;
};
</script>

<template>
  <div>
    <button
      type="button"
      class="btn btn-outline-primary me-1 flex-grow-1"
      :class="{ active: currentTab === 'location' }"
      @click="changeTab('location')"
    >
      Location
    </button>
    <button
      type="button"
      class="btn btn-outline-primary me-1 flex-grow-1"
      :class="{ active: currentTab === 'tripplan' }"
      @click="changeTab('tripplan')"
    >
      TripPlan
    </button>

    <div>
      <LocationBookmark v-if="currentTab === 'location'" />
      <TripPlanBookmark v-if="currentTab === 'tripplan'" />
    </div>
  </div>
</template>

<style scoped>
.btn.active {
  background-color: #007bff;
  color: #fff;
}
</style>
