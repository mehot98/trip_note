<script setup>
import { ref } from "vue";
import { storeToRefs } from "pinia";
import { useRouter } from "vue-router";
import { useMemberStore } from "@/stores/member";
import { useMenuStore } from "@/stores/menu";

const router = useRouter();

const memberStore = useMemberStore();

const { isLogin } = storeToRefs(memberStore);
const { userLogin, getUserInfo } = memberStore;
const { changeMenuState } = useMenuStore();

const loginUser = ref({
  userId: "ssafy",
  userPassword: "1234",
});

const login = async () => {
  await userLogin(loginUser.value);
  let token = sessionStorage.getItem("accessToken");
  if (isLogin) {
    getUserInfo(token);
    changeMenuState();
  }
  router.push("/");
};
</script>

<template>
  <section class="vh-100">
    <div class="container py-5 h-100">
      <div class="row d-flex align-items-center justify-content-center h-100">
        <div class="col-md-7 col-lg-5 col-xl-5 offset-xl-1">
          <form>
            <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">로그인</p>

            <div class="form-outline mb-4">
              <label class="form-label" for="userId">아이디</label>
              <input
                type="text"
                id="userId"
                class="form-control"
                v-model="loginUser.userId"
                placeholder="아이디를 입력해주세요"
              />
            </div>

            <div class="form-outline mb-4">
              <label class="form-label" for="userPassword">비밀번호</label>
              <input
                type="password"
                id="userPassword"
                class="form-control"
                v-model="loginUser.userPassword"
                @keyup.enter="login"
                placeholder="비밀번호를 입력해주세요"
              />
            </div>

            <div
              class="d-flex justify-content-around align-items-center mb-4"
            ></div>

            <button
              type="submit"
              class="btn btn-primary btn-lg btn-block"
              @click.prevent="login"
            >
              로그인
            </button>
          </form>
        </div>
      </div>
    </div>
  </section>
</template>

<style scoped>
.divider:after,
.divider:before {
  content: "";
  flex: 1;
  height: 1px;
  background: #eee;
}
</style>
