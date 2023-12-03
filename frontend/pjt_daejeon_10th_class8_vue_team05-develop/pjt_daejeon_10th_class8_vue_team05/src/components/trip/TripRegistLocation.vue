<script setup>
import Draggable from "vue3-draggable";
import draggable from "vuedraggable";
import KakaoMapRegist from "../map/KakaoMapRegist.vue";
import KakaoMapResult from "../map/KakaoMapResult.vue";

import { useTripStore } from "../../stores/trip";
import { registTrip } from "../../api/trip";
import { useRouter } from "vue-router";

const router = useRouter();

function clickRegistTrip() {
  console.log("click regist");

  const info = new Blob([JSON.stringify(result)], {
    type: "application/json",
  });

  var formData = new FormData();

  formData.append("info", info);
  formData.append("img", img.value);

  registTrip(
    formData,
    () => {
      console.log("good");
    },
    () => {
      console.log("no");
    }
  );
  router.push({ name: "trip-main" });
}

const { result, img, filterLocation } = useTripStore();

result.data = [];

for (let i = 0; i <= result.dayCount; i++) {
  result.data.push([]);
}

function deleteLocation(locationId) {
  filterLocation(locationId);
}
</script>

<template>
  <KakaoMapResult :locations="result.data" />

  <div class="my-3 p-3 bg-body rounded shadow-sm" v-for="day in result.dayCount" :key="day">
    <h3 class="border-bottom pb-2 mb-3" v-if="result.data[day]">Day{{ day }}</h3>
    <draggable class="list-group" :list="result.data[day]" group="people" itemKey="name">
      <template #item="{ element, index }">
        <div class="d-flex text-body-secondary pt-3">
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
            <div id="location-id" class="d-flex justify-content-between">
              <strong class="text-gray-dark">{{ element["place_name"] }}</strong>
              <a :href="element['place_url']">상세보기</a>
            </div>
            <div id="location-id" class="d-flex justify-content-between">
              <span class="d-block text-start">{{ element["address_name"] }}</span>
              <button
                type="button"
                class="btn btn-primary btn-sm mt-2"
                @click="deleteLocation(element.id)"
              >
                제거
              </button>
            </div>
          </div>
        </div>
      </template>
    </draggable>

    <button
      @click="result.cur = day"
      class="btn btn-primary mt-3"
      data-bs-toggle="modal"
      data-bs-target="#exampleModal"
    >
      추가하기
    </button>
  </div>

  <!-- Modal -->
  <div
    class="modal fade"
    id="exampleModal"
    tabindex="-1"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-xl">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="exampleModalLabel">여행지 찾기</h1>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body">
          <KakaoMapRegist />
        </div>
      </div>
    </div>
  </div>
  <button @click="clickRegistTrip" class="btn btn-primary mb-5">등록하기</button>
</template>

<style scoped>
#exampleModal {
  width: 1500px;
  height: 800px;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
</style>
