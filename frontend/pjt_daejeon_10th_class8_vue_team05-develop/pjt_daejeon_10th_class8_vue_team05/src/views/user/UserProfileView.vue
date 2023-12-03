<script setup>
import Profile from "../../components/user/Profile.vue";
import TripList from "../../components/trip/TripList.vue";
import { selectListTrip } from "../../api/trip.js";
import { selectListCategory } from "../../api/category";
import { useTripListStore } from "../../stores/tripList";
import { useMemberStore } from "../../stores/member";

const memberStore = useMemberStore();
const { getUserInfo, userInfo } = memberStore;

getUserInfo(sessionStorage.getItem("accessToken"));

import { storeToRefs } from "pinia";
const { trips } = storeToRefs(useTripListStore());

trips.value = [];
let page = 1;
console.log(userInfo);

async function load(event) {
  console.log("loading...");
  selectListTrip(
    {
      page: page++,
      userId: userInfo.userId,
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
      //   console.log(trip.value);
      if (data.length < 5) {
        trips.value.push(...data);
        event.complete();
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
  <div>
    <Profile />
    <TripList @load="load" />
  </div>
</template>

<style scoped></style>
