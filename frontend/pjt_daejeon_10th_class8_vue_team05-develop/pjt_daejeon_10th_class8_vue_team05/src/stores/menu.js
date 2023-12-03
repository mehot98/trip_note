import { ref } from "vue";
import { defineStore } from "pinia";

export const useMenuStore = defineStore("menuStore", () => {
  const menuList = ref([
    { name: "회원가입", show: true, routeName: "user-regist" },
    { name: "로그인", show: true, routeName: "user-login" },
    // { name: "오늘할일", show: false, routeName: "todos" },
    { name: "여행 등록하기", show: false, routeName: "trip-regist" },
    { name: "내정보", show: false, routeName: "user-profile" },
    { name: "로그아웃", show: false, routeName: "user-logout" },
  ]);

  const isCheck = ref(false);
  const changeMenuState = () => {
    console.log("changeMenu");
    // menuList.value = menuList.value.map((item) => ({
    //   ...item,
    //   show: !item.show,
    // }));
  };
  return {
    menuList,
    changeMenuState,
  };
});
