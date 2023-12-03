<script setup>
import { useTripStore } from "../../stores/trip";
import { useMemberStore } from "../../stores/member";

const memberStore = useMemberStore();
const { getUserInfo, userInfo } = memberStore;

getUserInfo(sessionStorage.getItem("accessToken"));

const emit = defineEmits(["nextRegist"]);

const { result, img } = useTripStore();

result["tripName"] = "";
result.dayCount = 1;
result.isPublic = "공개";
result.with = "혼자";
result.category = [];
result.userId = userInfo.userId;
img.value = null;

import { ref, watch } from "vue";

const preview = ref("");

watch(
  () => img.value,
  () => {
    preview.value = URL.createObjectURL(img.value);
  }
);

function selectFile(event) {
  img.value = event.target.files[0];
}

const nextRegist = function () {
  emit("nextRegist");
};
</script>

<template>
  <div id="main">
    <div class="option">
      <h3>여행 이름</h3>

      <input type="text" class="form-control mb-2" v-model="result['tripName']" />
    </div>

    <h3>여행 사진</h3>
    <div id="imgBox" class="option">
      <input id="imgInput" type="file" class="form-control mb-2 me-5" @change="selectFile" />
      <img v-if="preview" :src="preview" alt="파일 형식이 잘못됐습니다." class="mb-2" />
      <img v-else="preview" src="../../assets/defaultTrip.png" alt="" class="mb-2" />
    </div>

    <div class="option">
      <h3>며칠 떠나시나요?</h3>
      <button type="button" class="btn btn-outline-primary btn-sm m-2" @click="result.dayCount--">
        -
      </button>
      {{ result.dayCount }}일
      <button type="button" class="btn btn-outline-primary btn-sm m-2" @click="result.dayCount++">
        +
      </button>
    </div>

    <div class="option">
      <h3>공개여부</h3>
      <input
        type="radio"
        class="btn-check"
        v-model="result.isPublic"
        name="options-outlined2"
        id="success-outlined2"
        autocomplete="off"
        value="공개"
        checked
      />
      <label class="btn btn-outline-primary m-2" for="success-outlined2">공개</label>

      <input
        type="radio"
        class="btn-check"
        v-model="result.isPublic"
        name="options-outlined2"
        id="danger-outlined2"
        autocomplete="off"
        value="비공개"
      />
      <label class="btn btn-outline-primary m-2" for="danger-outlined2">비공개</label>
    </div>

    <div class="option">
      <h3>누구와</h3>

      <input
        type="radio"
        v-model="result.with"
        class="btn-check"
        name="options-base"
        id="option1"
        autocomplete="off"
        value="혼자"
        checked
      />
      <label class="btn btn-outline-primary m-2" for="option1">혼자</label>

      <input
        type="radio"
        v-model="result.with"
        class="btn-check"
        name="options-base"
        id="option2"
        autocomplete="off"
        value="친구와"
      />
      <label class="btn btn-outline-primary m-2" for="option2">친구와</label>

      <input
        type="radio"
        v-model="result.with"
        class="btn-check"
        name="options-base"
        id="option3"
        autocomplete="off"
        value="연인과"
      />
      <label class="btn btn-outline-primary m-2" for="option3">연인과</label>

      <input
        type="radio"
        v-model="result.with"
        class="btn-check"
        name="options-base"
        id="option4"
        autocomplete="off"
        value="배우자와"
      />
      <label class="btn btn-outline-primary m-2" for="option4">배우자와</label>

      <input
        type="radio"
        v-model="result.with"
        class="btn-check"
        name="options-base"
        id="option5"
        autocomplete="off"
        value="아이와"
      />
      <label class="btn btn-outline-primary m-2" for="option5">아이와</label>

      <input
        type="radio"
        v-model="result.with"
        class="btn-check"
        name="options-base"
        id="option6"
        autocomplete="off"
        value="부모님과"
      />
      <label class="btn btn-outline-primary m-2" for="option6">부모님과</label>

      <input
        type="radio"
        v-model="result.with"
        class="btn-check"
        name="options-base"
        id="option7"
        autocomplete="off"
        value="반려동물과"
      />
      <label class="btn btn-outline-primary m-2" for="option7">반려동물과</label>

      <input
        type="radio"
        v-model="result.with"
        class="btn-check"
        name="options-base"
        id="option8"
        autocomplete="off"
        value="기타"
      />
      <label class="btn btn-outline-primary m-2" for="option8">기타</label>
    </div>

    <div class="option">
      <h3>여행 스타일</h3>
      <input
        type="checkbox"
        class="btn-check"
        id="btn-check-outlined"
        autocomplete="off"
        v-model="result.category"
        value="체험-액티비티"
      />
      <label class="btn btn-outline-primary m-2" for="btn-check-outlined">체험, 액티비티</label>

      <input
        type="checkbox"
        class="btn-check"
        id="btn-check-outlined2"
        autocomplete="off"
        v-model="result.category"
        value="SNS-핫플레이스"
      />
      <label class="btn btn-outline-primary m-2" for="btn-check-outlined2">SNS 핫플레이스</label>

      <input
        type="checkbox"
        class="btn-check"
        id="btn-check-outlined3"
        autocomplete="off"
        v-model="result.category"
        value="자연"
      />
      <label class="btn btn-outline-primary m-2" for="btn-check-outlined3">자연</label>

      <input
        type="checkbox"
        class="btn-check"
        id="btn-check-outlined4"
        autocomplete="off"
        v-model="result.category"
        value="유명-관광지"
      />
      <label class="btn btn-outline-primary m-2" for="btn-check-outlined4">유명 관광지</label>

      <input
        type="checkbox"
        class="btn-check"
        id="btn-check-outlined5"
        autocomplete="off"
        v-model="result.category"
        value="힐링"
      />
      <label class="btn btn-outline-primary m-2" for="btn-check-outlined5">힐링</label>

      <input
        type="checkbox"
        class="btn-check"
        id="btn-check-outlined6"
        autocomplete="off"
        v-model="result.category"
        value="문화-예술-역사"
      />
      <label class="btn btn-outline-primary m-2" for="btn-check-outlined6">문화, 예술, 역사</label>

      <input
        type="checkbox"
        class="btn-check"
        id="btn-check-outlined7"
        autocomplete="off"
        v-model="result.category"
        value="체험"
      />
      <label class="btn btn-outline-primary m-2" for="btn-check-outlined7">체험</label>

      <input
        type="checkbox"
        class="btn-check"
        id="btn-check-outlined8"
        autocomplete="off"
        v-model="result.category"
        value="쇼핑"
      />
      <label class="btn btn-outline-primary m-2" for="btn-check-outlined8">쇼핑</label>

      <input
        type="checkbox"
        class="btn-check"
        id="btn-check-outlined9"
        autocomplete="off"
        v-model="result.category"
        value="음식"
      />
      <label class="btn btn-outline-primary m-2" for="btn-check-outlined9">음식</label>
    </div>

    <button type="button" class="btn btn-primary btn-lg mb-5" @click="nextRegist">
      다음 단계로
    </button>
  </div>
</template>

<style scoped>
#main {
  text-align: center;
}

.option {
  border-bottom: solid 1px;
  width: 30%;
  margin: 50px auto;
}

img {
  width: 100px;
  flex: 1;
  margin: auto;
  border: solid 1px;
}

#imgInput {
  width: 100px;
  flex: 1;
  margin: auto;
}

#imgBox {
  display: flex;
  width: 70%;
}
</style>
