import { ref, computed } from "vue";
import { defineStore } from "pinia";

export const useTripListStore = defineStore("tripList", () => {
  const trips = ref([]);

  const category = ref([]);
  const _with = ref("");

  const filterTrips = computed(() => {
    return trips.value.filter(
      (trip) =>
        (_with.value == "" || trip.with == _with.value) &&
        checkContain(category.value, trip.category)
    );
  });

  function checkContain(arr1, arr2) {
    for (let i = 0; i < arr1.length; i++) {
      if (arr2.indexOf(arr1[i]) < 0) return false;
    }
    return true;
  }

  return { trips, filterTrips, category, _with };
});
