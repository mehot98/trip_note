<script setup>
import InfiniteLoading from "v3-infinite-loading";
import "v3-infinite-loading/lib/style.css";

import { useTripListStore } from "../../stores/tripList";
import { storeToRefs } from "pinia";
const { trips, filterTrips } = storeToRefs(useTripListStore());

const emit = defineEmits(["load"]);

const load = (event) => {
  emit("load", event);
};
</script>

<template>
  <div id="tripList" class="row row-cols-1 row-cols-md-1 g-4 container">
    <div v-if="trips == '여행 정보를 받아오지 못했습니다.'">{{ trips }}</div>

    <div id="trip" class="col" v-for="trip in filterTrips" :key="trip.tiprId" v-else>
      <div class="card" v-if="(trip.isPublic = '공개')">
        <router-link :to="`/trip/${trip.tripId}`" class="text-decoration-none">
          <img
            v-if="trip.img"
            :src="`data:image/png;base64,${trip.img}`"
            class="card-img-top"
            alt=""
          />
          <img v-else src="../../assets/defaultTrip.png" class="card-img-top" alt="" />
          <div class="card-body">
            <h5 class="card-title">{{ trip.tripName }}</h5>
            <p class="card-text text-end">{{ trip.userId }}</p>
            <span class="card-text" v-if="trip.with">#{{ trip.with }}</span>
            <!-- <span  v-if="trip.category"> -->
            <span
              class="card-text"
              v-for="category in trip.category"
              :key="category"
            >
              #{{ category }}</span
            >
            <!-- </span> -->
          </div>
        </router-link>
      </div>
    </div>
    <InfiniteLoading @infinite="load" />
  </div>
</template>

<style scoped>
#tripList {
  margin: auto;
  width: 70%;
}

#trip {
  margin-top: 20%;
  margin-bottom: 20%;
}
</style>
