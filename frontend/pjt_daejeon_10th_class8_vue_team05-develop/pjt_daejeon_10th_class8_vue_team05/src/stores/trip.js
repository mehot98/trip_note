import { ref } from "vue";
import { defineStore } from "pinia";

export const useTripStore = defineStore("trip", () => {
  const result = ref({});
  const img = ref({});

  function filterLocation(locationId) {
    for (let i = 1; i <= result.value.dayCount; i++) {
      result.value.data[i] = result.value.data[i].filter((location) => location.id != locationId);
    }
  }

  return { result, img, filterLocation };
});
