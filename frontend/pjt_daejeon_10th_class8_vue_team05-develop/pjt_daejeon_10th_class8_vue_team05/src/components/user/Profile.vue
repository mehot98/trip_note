<script setup>
import { ref } from "vue";
import { useRoute, useRouter } from "vue-router";
import { findById } from "@/api/user.js";
import { useMemberStore } from "../../stores/member";
import { countFollowUser } from "../../api/follow";
import { storeToRefs } from "pinia";

const route = useRoute();
const router = useRouter();
const memberStore = useMemberStore();
const { getUserInfo } = memberStore;
const { userInfo } = storeToRefs(memberStore);

// onMounted(() => {
getUserInfo(sessionStorage.getItem("accessToken"));
// })

const getProfile = () => {
  console.log("프로필 얻어올게요");
  findById(
    userInfo.value.userId, // "userInfo.userId", // 어디다 저장한 거를 userId로 여기서 써줘야 해
    // console.log("userInfo.userId"),

    ({ data }) => {
      console.log(data.userInfo.userId);
      // userInfo.value = response.data.userInfo
      // console.log(userInfo.userId)
      user.value.userId = data.userInfo.userId;
      console.log("hello!!");
      user.value.userName = data.userInfo.userName;
    },
    (error) => {
      console.error(error);
    }
  );
};

const followCount = ref(0);
const getFollowCount = () => {
  console.log("followCount 얻어올게요!!");
  countFollowUser(
    userInfo.value.userId,
    (response) => {
      // console.log(data);
      followCount.value = response.data;
      console.log(followCount.value);
    },
    (error) => {
      console.error(error);
    }
  );
};
getFollowCount();

const user = ref({
  // 초기화
  userId: "",
  userName: "",
});

const followIncrease = () => {
  user.value.followCount++;
  console.log("follow 수 올라가게 해야 함");
};
getProfile();
getFollowCount();
</script>

<template>
  <div class="row d-flex justify-content-center align-items-center h-100">
    <div class="col col-md-9 col-lg-7 col-xl-5">
      <div class="card" style="border-radius: 15px">
        <div class="card-body p-4">
          <div id="profileImg" class="col-lg-4">
            <img
              v-if="userInfo.profileImg"
              :src="`data:image/png;base64,${userInfo.profileImg}`"
              class="bd-placeholder-img rounded-circle mb-2"
              width="140"
              height="140"
              xmlns="http://www.w3.org/2000/svg"
              role="img"
              aria-label="Placeholder"
              preserveAspectRatio="xMidYMid slice"
              focusable="false"
            />

            <img
              v-else
              src="../../assets/defaultProfile.png"
              class="bd-placeholder-img rounded-circle mb-2"
              width="140"
              height="140"
              xmlns="http://www.w3.org/2000/svg"
              role="img"
              aria-label="Placeholder"
              preserveAspectRatio="xMidYMid slice"
              focusable="false"
            />
            <title>Placeholder</title>
            <rect width="100%" height="100%" fill="var(--bs-secondary-color)"></rect>
            <p><label class="btn btn-secondary" for="file">프로필 사진 넣기</label></p>
          </div>

          <div id="profileInfo" class="d-flex text-black">
            <div class="flex-shrink-0"></div>
            <div class="flex-grow-1 ms-3">
              <h5 class="mb-1">{{ user.userName }}</h5>
              <p class="mb-2 pb-1" style="color: #2b2a2a">ID: {{ user.userId }}</p>
              <div id="edit" class="d-flex pt-1">
                <button type="button" class="btn btn-outline-primary mt-auto me-1 flex-grow-1">
                  <router-link to="/user/modify">Edit Profile</router-link>
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
#profileImg {
  width: 30%;
  float: left;
  text-align: center;
}

#profileInfo {
  float: left;
  white-space: nowrap;
  width: 70%;
  margin: auto;
}

#edit {
  height: 100%;
}
</style>
