import { createRouter, createWebHistory } from "vue-router";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "index",
      redirect: { name: "trip-main" },
    },
    {
      path: "/trip",
      children: [
        {
          path: "main",
          name: "trip-main",
          component: () => import("../views/trip/TripMainView.vue"),
        },
        {
          path: ":tripId",
          name: "trip-detail",
          component: () => import("../views/trip/TripDetailView.vue"),
        },
        {
          path: "regist",
          name: "trip-regist",
          component: () => import("../views/trip/TripRegistView.vue"),
        },
        {
          path: "bookmark",
          name: "trip-bookmark",
          component: () => import("../views/trip/BookmarkView.vue"),
        },
      ],
    },
    {
      path: "/user",
      name: "user",
      children: [
        {
          path: "login",
          name: "user-login",
          component: () => import("../views/user/UserLoginView.vue"),
        },
        {
          path: "join",
          name: "user-regist",
          component: () => import("../views/user/UserRegistView.vue"),
        },
        {
          path: "profile",
          name: "user-profile",
          component: () => import("../views/user/UserProfileView.vue"),
        },
        {
          path: "modify",
          name: "edit-profile",
          component: () => import("../views/user/UserModifyView.vue"),
        },
      ],
    },
    {
      path: "/board",
      name: "board",
      component: () => import("../views/board/BoardListView.vue"),
      redirect: { name: "article-list" },
      children: [
        {
          path: "list",
          name: "article-list",
          component: () => import("@/components/board/BoardList.vue"),
        },
        {
          path: "view/:articleno",
          name: "article-view",
          component: () => import("@/components/board/BoardDetail.vue"),
        },
        {
          path: "write",
          name: "article-write",
          component: () => import("@/components/board/BoardWrite.vue"),
        },
        {
          path: "modify/:articleno",
          name: "article-modify",
          component: () => import("@/components/board/BoardModify.vue"),
        },
      ],
    },
    {
      path: "/plan",
      name: "plan",
      component: () => import(/* webpackChunkName: "plan" */ "@/views/AppPlan"),
      redirect: "/plan/list",
      children: [
        {
          path: "list",
          name: "planlist",
          component: () =>
            import(/* webpackChunkName: "plan" */ "@/components/plan/PlanList"),
        },
        {
          path: "write",
          name: "planwrite",
          component: () =>
            import(
              /* webpackChunkName: "plan" */ "@/components/plan/PlanWrite"
            ),
        },
        {
          path: "view/:planId",
          name: "planview",
          component: () =>
            import(/* webpackChunkName: "plan" */ "@/components/plan/PlanView"),
        },
      ],
    },

    {
      path: "/location",
      children: [
        {
          path: "info",
          name: "location-info",
          component: () => import("../views/trip/LocationMainView.vue"),
        },
      ],
    },
    {
      path: "/follow",
      children: [
        {
          path: "user",
          name: "follow-user",
          component: () => import("@/component/follow/FollowUser.vue"),
        },
      ],
    },
  ],
});

export default router;
