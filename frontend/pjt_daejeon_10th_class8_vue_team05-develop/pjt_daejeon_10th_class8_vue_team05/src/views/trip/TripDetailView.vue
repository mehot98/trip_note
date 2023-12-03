<script setup>
import KakaoMapResult from "../../components/map/KakaoMapResult.vue";
import TripComment from "../../components/trip/TripComment.vue";

import { useRoute } from "vue-router";
import { selectTripDetail } from "../../api/trip";
import { selectListLocation } from "../../api/location";
import { ref } from "vue";

const route = useRoute();
const tripId = ref();
tripId.value = route.params.tripId;
const trip = ref({});
const locations = ref();

selectTripDetail(
  tripId.value,
  ({ data }) => {
    trip.value = data;
  },
  () => {
    console.log("trip detail get trip fail");
  }
);

selectListLocation(
  tripId.value,
  ({ data }) => {
    locations.value = [];
    for (let i = 0; i < data.length; i++) {
      locations.value[data[i].day] == undefined
        ? (locations.value[data[i].day] = [data[i]])
        : locations.value[data[i].day].push(data[i]);
    }
    // console.log(locations.value)
  },
  () => {
    console.log(tripId.value);
    console.log("tripDetailVie fail get location list");
  }
);
</script>

<template>
  <hr />
  <div id="container" v-if="locations">
    <hr />
    <h1>{{ trip.tripName }}</h1>
    <hr />

    <img
      v-if="trip.img"
      :src="`data:image/png;base64,${trip.img}`"
      alt="파일 형식이 잘못됐습니다."
      class="mb-2"
    />

    <div
      class="my-3 p-3 bg-body rounded shadow-sm"
      v-for="day in trip.locations.length - 1"
      :key="day"
    >
      <h3 class="border-bottom pb-2 mb-0" v-if="trip.locations[day]">Day{{ day }}</h3>
      <div
        class="d-flex text-body-secondary pt-3"
        v-for="location in trip.locations[day]"
        :key="location.id"
      >
        <svg
          class="bd-placeholder-img flex-shrink-0 me-2 rounded"
          width="32"
          height="32"
          xmlns="http://www.w3.org/2000/svg"
          role="img"
          aria-label="Placeholder: 32x32"
          preserveAspectRatio="xMidYMid slice"
          focusable="false"
        >
          <title>Placeholder</title>
          <rect width="100%" height="100%" fill="#007bff"></rect>
          <text x="50%" y="50%" fill="#007bff" dy=".3em">32x32</text>
        </svg>
        <div class="pb-3 mb-0 small lh-sm border-bottom w-100">
          <div class="d-flex justify-content-between">
            <strong class="text-gray-dark">{{ location.placeName }}</strong>
            <a :href="location.placeUrl">상세보기</a>
          </div>
          <span class="d-block">{{ location.addressName }}</span>
        </div>
      </div>
    </div>
    <KakaoMapResult :locations="trip.locations" />
  </div>
  <!-- {{ trip }} -->
  <TripComment :boardTripId="tripId"></TripComment>
</template>

<style scoped>
h1 {
  text-align: center;
}

img {
  width: 70%;
  margin: auto;
  display: block;
}
</style>
