<script setup>
import { ref } from "vue";
import { useMenuStore } from "@/stores/menu";
import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";
import { useRouter } from "vue-router";

const router = useRouter();
const menuStore = useMenuStore();
const { menuList } = storeToRefs(menuStore);
const { changeMenuState } = useMenuStore();
const { userLogout } = useMemberStore();
const memberStore = useMemberStore();
const { getUserInfo } = memberStore;
const { userInfo } = storeToRefs(memberStore);
getUserInfo(sessionStorage.getItem("accessToken"));

const { isLogin } = storeToRefs(memberStore);

const loginUser = ref({
  userId: "",
  userPassword: "",
});

const logout = async () => {
  await userLogout(loginUser.value.userId);
  changeMenuState();
  router.push("/");
};
</script>

<template>
  <div id="main" class="">
    <header
      class="d-flex align-items-center justify-content-center justify-content-md-between mb-4 border-bottom"
    >
      <!-- <ul class="nav col-12 col-md-auto mb-2 mb-md-0 "> -->
      <router-link to="/board/list" class="nav-link px-2 ms-5"> 게시판 </router-link>
      <!-- <li><router-link to="/user/profile" class="nav-link px-2"> 마이페이지 </router-link></li> -->
      <!-- </ul> -->

      <router-link
        to="/"
        class="d-flex align-items-center mx-auto mb-3 mb-md-0 me-md-auto link-body-emphasis text-decoration-none"
      >
        <span class="fs-4">몰루코</span>
      </router-link>
      <!-- <router-link to="/trip/regist"><button type="button" class="btn btn-primary btn-md me-5">여행
          등록하기</button></router-link> -->
      <!--여행 등록하기랑 마이페이지도 아레에다가 넣고 싶음 -->

      <!-- <div class="col-md-4 text-end">
          <button type="button" class="btn btn-outline-primary me-2"><router-link to="/user/login" class="nav-link px-2"> 로그인 </router-link></button>
          <button type="button" class="btn btn-primary"><router-link to="/user/join" class="nav-link px-2"> 회원가입 </router-link></button>
        </div> -->
      <!-- {{ userInfo == null }} -->
      <ul
        class="navbar-nav my-2 my-lg-0 navbar-nav-scroll d-flex flex-row me-5"
        style="--bs-scroll-height: 80px"
      >
        <template v-for="menu in menuList" :key="menu.routeName">
          <!-- {{ menu }} -->
          <template v-if="(userInfo == null) ^ !menu.show">
            <!-- {{ menu.show }} -->
            <template v-if="menu.routeName === 'user-logout'">
              <li class="nav-item">
                <button type="button" class="nav-link btn btn-outline-primary me-2">
                  <router-link to="/" @click.prevent="logout" class="nav-link">{{
                    menu.name
                  }}</router-link>
                </button>
              </li>
            </template>
            <template v-else>
              <li class="nav-item">
                <button type="button" class="nav-item btn btn-outline-primary me-2">
                  <router-link :to="{ name: menu.routeName }" class="nav-link">{{
                    menu.name
                  }}</router-link>
                </button>
              </li>
            </template>
          </template>
        </template>
      </ul>
    </header>
  </div>
</template>

<style scoped>
#main {
  background-color: white;
  width: 100%;
}

header {
  height: 100px;
}
</style>
