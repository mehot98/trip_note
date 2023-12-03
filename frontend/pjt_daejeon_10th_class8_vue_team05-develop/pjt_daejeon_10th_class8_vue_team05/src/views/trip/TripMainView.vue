<script setup>
import TripPlan from "../../components/trip/TripList.vue";
import TripSideFilter from "../../components/trip/TripSideFilter.vue";

import { selectListTrip } from "../../api/trip.js";
import { selectListCategory } from "../../api/category";

import { useTripListStore } from "../../stores/tripList";

import { storeToRefs } from "pinia";
const { trips } = storeToRefs(useTripListStore());

trips.value = [];
let page = 1;

async function load(event) {
  console.log("loading...");
  selectListTrip(
    {
      page: page++,
      // userId: "ssafy",
    },
    async ({ data }) => {
      for (let trip of data) {
        await selectListCategory(
          trip.tripId,
          ({ data }) => {
            trip.category = [...data];
          },
          () => {
            console.log("fail");
          }
        );
      }
      if (data.length < 5) {
        trips.value.push(...data);
        
        page = 1;
        // event.complete();
        event.loaded();
      } else {
        trips.value.push(...data);
        event.loaded();
      }
    },
    (error) => {
      console.log(error);
      event.error();
    }
  );
}
</script>

<template>
  <div class="row">
    <div class="col-2">
      <TripSideFilter @change-result="changeResult" />
    </div>
    <div class="col">
      <!-- {{ trips }} -->
      <TripPlan @load="load" />
    </div>
  </div>
</template>

<style scoped></style>
