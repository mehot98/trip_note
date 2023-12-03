<script setup>
import { ref, onMounted, defineProps } from "vue";
import { useRouter } from "vue-router";
import {
  writeTripComment,
  deleteTripComment,
  getTripComment,
} from "@/api/board";
import { useMemberStore } from "@/stores/member";
import { storeToRefs } from "pinia";

const memberStore = useMemberStore();
const showModal = ref(false);
const { getUserInfo } = memberStore;
const { userInfo } = storeToRefs(memberStore);
const router = useRouter();
const props = defineProps({ boardTripId: Number }); // props 선언
// console.log("trip comment receive props", props.boardTripId);
const comments = ref([]);

const comment = ref({
  commetId: 0,
  userId: "",
  comment: "",
  regtime: "",
  board_Trip: "",
  boardTripId: 0,
});

onMounted(() => {
  comment.value.boardTripId = props.boardTripId;
  onGetTripComment();
});
getUserInfo(sessionStorage.getItem("accessToken"));
comment.value.userId = userInfo.value.userId;
const onGetTripComment = () => {
  // console.log("comment 정보 얻어올게요!!");
  getTripComment(
    props.boardTripId,
    ({ data }) => {
      // console.log(data);
      comments.value = data;
    },
    (error) => {
      console.error(error);
    }
  );
};
onGetTripComment();

function onDeleteComment(commentId) {
  console.log("comment 정보 삭제할게요!!");
  deleteTripComment(
    props.boardTripId,
    commentId,
    (response) => {
      let msg = "댓글 삭제 중 문제가 발생하였습니다.";
      if (response.status == 200) msg = "댓글이 삭제되었습니다.";
      alert(msg);
      onGetTripComment();
      router.push({
        name: "trip-detail",
        params: { tripId: props.boardTripId },
      });
    },
    (error) => {
      console.error(error);
    }
  );
}

function writeComment() {
  console.log("comment 정보 쓸게요!!");
  // console.log(comment);
  writeTripComment(
    comment.value,
    (response) => {
      let msg = "댓글 작성 중 문제가 발생하였습니다.";
      if (response.status == 201) msg = "댓글이 작성되었습니다.";
      alert(msg);
      onGetTripComment();
      router.push({
        name: "trip-detail",
        params: { tripId: props.boardTripId },
      });
    },
    (error) => {
      console.error(error);
    }
  );
}

const closeModal = () => {
  showModal.value = false;
};

const submitComment = () => {
  writeComment();
  closeModal();
};
</script>

<template>
  <div>
    <!-- 댓글 목록 -->
    <div v-if="comments.length > 0">
      <div
        v-for="comment in comments"
        :key="comment.commentId"
        class="comment-container border border-3"
      >
        <div class="comment-header col">
          <div class="col-2 fs-5">{{ comment.userId }}</div>
          <div class="comment-content col-4">{{ comment.comment }}</div>
          <div class="col-3">{{ comment.regtime }}</div>
          <div>
            <button
              v-if="userInfo.userId == comment.userId"
              class="btn btn-link"
              @click="onDeleteComment(comment.commentId)"
            >
              리뷰 삭제
            </button>
          </div>
        </div>
      </div>
    </div>
    <div v-else>
      <p>리뷰가 없습니다.</p>
    </div>
    <button
      v-if="userInfo !== null"
      type="button"
      class="btn btn-primary"
      data-bs-toggle="modal"
      data-bs-target="#exampleModal"
    >
      리뷰 작성
    </button>

    <div
      class="modal fade"
      id="exampleModal"
      tabindex="-1"
      aria-labelledby="exampleModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-lg modal-dialog-centered">
        <div class="modal-content">
          <div class="modal-header">
            <h1 class="modal-title fs-8" id="exampleModalLabel">리뷰</h1>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          <div class="modal-body">
            <textarea
              class="input-group mb-3"
              v-model="comment.comment"
              placeholder="리뷰를 작성하세요"
            ></textarea>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-bs-dismiss="modal"
            >
              취소
            </button>
            <button
              type="button"
              class="btn btn-primary"
              @click="submitComment"
              data-bs-dismiss="modal"
            >
              저장
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.comment-container {
  border: 1px solid #ddd;
  margin: 10px;
  padding: 10px;
}

.comment-header {
  display: flex;
  justify-content: space-between;
}

.comment-modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
}

.comment-modal div {
  background: #fff;
  padding: 20px;
  border-radius: 10px;
}

.comment-modal textarea {
  width: 100%;
  height: 100px;
  margin-bottom: 10px;
}

.comment-modal button {
  background: #4caf50;
  color: #fff;
  border: none;
  padding: 10px;
  cursor: pointer;
  border-radius: 5px;
}
</style>
