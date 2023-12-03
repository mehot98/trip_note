<script setup>
import { ref, onMounted, watch } from "vue";
import { useRoute, useRouter } from "vue-router";
import { findById, modifyUser, deleteUser } from "@/api/user";
import { useMemberStore } from "../../stores/member";

const router = useRouter();
const route = useRoute();
const memberStore = useMemberStore();
const { getUserInfo, userInfo } = memberStore;

onMounted(() => {
  getUserInfo(sessionStorage.getItem("accessToken"));
});

const user = ref({
  userId: "",
  userName: "",
  userPassword: "",
  emailId: "",
  emailDomain: "",
});

const profileImg = ref("");

const getUser = () => {
  console.log("user 정보 얻어올게요");
  findById(
    userInfo.userId,
    ({ data }) => {
      user.value.userId = data.userInfo.userId;
      user.value.userName = data.userInfo.userName;
      user.value.userPassword = data.userInfo.userPassword;
      user.value.emailId = data.userInfo.emailId;
      user.value.emailDomain = data.userInfo.emailDomain;
      profileImg.value = data.userInfo.profileImg;
    },
    (error) => {
      console.error(error);
    }
  );
};

getUser();

function UpdateUser() {
  let formData = new FormData();

  const userDto = new Blob([JSON.stringify(user.value)], { type: "application/json" });

  console.log(user);
  console.log(profileImg);

  formData.append("userDto", userDto);
  formData.append("profileImg", profileImg.value);

  console.log(user.value.userId + "의 사용자를 put 방식으로 수정할거임", user.value);
  modifyUser(
    formData,
    (response) => {
      let msg = "회원정보 수정 중 문제가 발생하였습니다.";
      if (response.status == 200) msg = "회원정보가 수정되었습니다.";
      alert(msg);
      getUserInfo(sessionStorage.getItem("accessToken"));
      router.push({ name: "user-profile" }); // 수정이 완료되면 프로필화면으로 감
    },
    (error) => {
      console.error(error);
    }
  );
}

function DeleteUser() {
  console.log(user.value.userId + "의 사용자를 삭제할거임");
  deleteUser(
    userInfo.userId,
    (response) => {
      let msg = "회원 탈퇴 중 문제가 발생하였습니다";
      if (response.status == 200) {
        msg = "회원 탈퇴가 완료되었습니다.";
        alert(msg);
        router.push({ name: "index" });
      }
    },
    (error) => {
      console.error(error);
    }
  );
}

const preview = ref("");

function changeImg(event) {
  profileImg.value = event.target.files[0];
  console.log(profileImg);
}

watch(
  () => profileImg.value,
  () => {
    preview.value = URL.createObjectURL(profileImg.value);
  }
);
</script>

<template>
  <div class="container mt-5">
    <h2>회원정보 수정</h2>
    <div id="profileImg" class="col-lg-4">
      <img
        v-if="preview"
        :src="preview"
        class="bd-placeholder-img rounded-circle mb-3"
        width="140"
        height="140"
        xmlns="http://www.w3.org/2000/svg"
        role="img"
        aria-label="Placeholder"
        preserveAspectRatio="xMidYMid slice"
        focusable="false"
        alt="파일 형식이 잘못됐습니다."
      />
      <img
        v-else-if="userInfo.profileImg"
        :src="`data:image/png;base64,${userInfo.profileImg}`"
        class="bd-placeholder-img rounded-circle mb-3"
        width="140"
        height="140"
        xmlns="http://www.w3.org/2000/svg"
        role="img"
        aria-label="Placeholder"
        preserveAspectRatio="xMidYMid slice"
        focusable="false"
        alt="파일 형식이 잘못됐습니다."
      />
      <img
        v-else
        src="../../assets/defaultProfile.png"
        class="bd-placeholder-img rounded-circle mb-3"
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
      <!-- <h2 class="fw-normal">Heading</h2> -->
      <!-- <p>
              Some representative placeholder content for the three columns of text below the
              carousel. This is the first column.
            </p> -->
      <input id="profile-img" type="file" style="display: none" @change="changeImg" />
      <p><label class="btn btn-secondary" for="profile-img">프로필 사진 넣기</label></p>
    </div>
    <form id="userInfoForm">
      <div class="mb-3">
        <h4>아이디 : {{ user.userId }}</h4>
      </div>
      <div class="mb-3">
        <h4>이름 : {{ user.userName }}</h4>
      </div>
      <h4>비밀번호 변경</h4>
      <div class="mb-3">
        <label for="userPassword" class="form-label">New Password:</label>
        <input
          type="password"
          class="form-control"
          id="userPassword"
          v-model="user.userPassword"
        />
      </div>
      <div class="mb-3">
        <h5>변경할 이메일 주소</h5>
        <label for="email" class="form-label">Email:</label>
        <div class="input-group">
          <input
            type="text"
            class="form-control"
            id="emailId"
            v-model="user.emailId"
            placeholder="이메일 아이디"
          />
          <span class="input-group-text">@</span>
          <input
            type="text"
            class="form-control"
            id="emailDomain"
            v-model="user.emailDomain"
            placeholder="도메인"
          />
        </div>
      </div>
      <button type="button" class="btn btn-primary" @click="UpdateUser()">
        수정하기
      </button>
      <button type="button" class="btn btn-primary" @click="DeleteUser()">
        탈퇴하기
      </button>
    </form>
  </div>
</template>

<style scoped></style>
