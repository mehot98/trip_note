<script setup>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import {
  writeBoardComment,
  deleteBoardComment,
  getBoardComment,
} from "../../../api/board";
import { useMemberStore } from "@/stores/member";

const router = useRouter();
const route = useRoute();
const comments = ref([]);
const memberStore = useMemberStore();
const { getUserInfo, userInfo } = memberStore;

onMounted(() => {
  comment.value.boardTripId = route.params;
  console.log(comment.value.boardTripId);
});
getUserInfo(sessionStorage.getItem("accessToken"));
comment.value.userId = userInfo.userId;

const comment = ref({
  commentId: 0,
  userId: "",
  comment: "",
  regtime: "",
  board_trip: "",
  boardTripId: 0,
});

// let { boardTripId } = route.params; // 이게 되나

const getComment = () => {
  console.log("comment 정보 얻어올게요");
  getBoardComment(
    boardTripId, // 파라미터 이렇게 받는 거인듯 : 3
    ({ data }) => {
      comments.value = data;
    },
    (error) => {
      console.error(error);
    }
  );
};

onMounted(() => {
  getComment();
}); // 처음에 댓글 목록 가져와서 뿌려야 함.

function writeComment() {
  console.log("comment 정보 쓸게요");
  writeBoardComment(
    comment.value,
    ({ response }) => {
      let msg = "댓글 작성 중 문제가 발생하였습니다.";
      if (response.status == 201) msg = "댓글이 작성되었습니다.";
      alert(msg);
      router.push({ name: "board-detail" }); // 수정이 완료되면 프로필화면으로 감
    },
    (error) => {
      console.error(error);
    }
  );
}

function updateComment() {
  console.log("comment 정보 수정할게요");
  modifyBoardComment(
    comment.value,
    ({ response }) => {
      let msg = "댓글 수정 중 문제가 발생하였습니다.";
      if (response.status == 200) msg = "댓글이 수정되었습니다.";
      alert(msg);
      router.push({ name: "board-detail" }); // 수정이 완료되면 프로필화면으로 감
    },
    (error) => {
      console.error(error);
    }
  );
}

function onDeleteComment() {
  deleteBoardComment(
    boardTripId,
    (response) => {
      let msg = "댓글 삭제 중 문제가 발생하였습니다.";
      if (response.status == 200) msg = "댓글이 삭제되었습니다.";
      alert(msg);
      router.push({ name: "board-detail" }); // 수정이 완료되면 프로필화면으로 감
    },
    (error) => {
      console.error(error);
    }
  );
}
</script>

<template>
  <div>
    <table class="table table-hover">
      <thead>
        <tr class="text-center">
          <th scope="col">작성자</th>
          <th scope="col">글내용</th>
          <th scope="col">작성일</th>
        </tr>
      </thead>
      <tbody>
        <div
          v-for="comment in comments"
          :key="comment.commentId"
          :comment="comment"
        >
          <tr class="text-center">
            <th scope="col">{{ comment.userId }}</th>
            <th scope="col">
              {{ comment.comment }}
            </th>
            <th scope="col">{{ comment.regtime }}</th>
          </tr>
        </div>
        <div class="mb-3">
          <label for="userid" class="form-label">작성자 ID : </label>
          {{ userInfo.userId }}
        </div>
        <div class="mb-3">
          <label for="content" class="form-label">내용 : </label>
          <textarea
            class="form-control"
            v-model="comment.content"
            rows="10"
          ></textarea>
        </div>
        <div class="d-flex justify-content-end">
          <button
            type="submit"
            class="btn btn-outline-success mb-3 ms-1"
            @click="writeComment"
          >
            댓글작성
          </button>
          <button
            type="submit"
            class="btn btn-outline-success mb-3 ms-1"
            @click="updateComment"
          >
            댓글수정
          </button>
          <button
            type="button"
            class="btn btn-outline-danger mb-3 ms-1"
            @click="onDeleteComment"
          >
            댓글삭제
          </button>
        </div>
      </tbody>
    </table>
  </div>
</template>

<style scoped></style>
