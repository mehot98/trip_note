<script setup>
import { ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import {joinUser} from '@/api/user';

const router = useRouter();
const route = useRoute();

const user = ref({
  userId: '',
  userName: '',
  userPassword: '',
  emailId: '',
  emailDomain: '',
});

function JoinUser() {
  console.log("회원가입중")
  joinUser(
    user.value,
    (response) => {
      let msg = "회원가입 중 문제가 발생하였습니다.";
      if (response.status == 200) msg = "회원가입이 완료되었습니다.";
      alert(msg);
      router.push({ name: "index" }); // 수정이 완료되면 메인화면으로 감       
    },
    (error) => {
      console.error(error);
    }
  );
  }
</script>

<template>
  <section class="vh-100" style="background-color: #eee;">
    <div class="container h-100">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-lg-12 col-xl-11">
          <div class="card text-black" style="border-radius: 25px;">
            <div class="card-body p-md-5">
              <div class="row justify-content-center">
                <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">

                  <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">회원가입</p>

                  <form class="mx-1 mx-md-4">

                    <div class="d-flex flex-row align-items-center mb-4">
                      <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                      <div class="form-outline flex-fill mb-0">
                        <label class="form-label" for="form3Exple1c">아이디</label>
                        <input type="text" id="form1c" class="form-control" v-model="user.userId" />
                      </div>
                    </div>
                    <div class="d-flex flex-row align-items-center mb-4">
                      <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                      <div class="form-outline flex-fill mb-0">
                        <label class="form-label" for="form3Example1c">이름</label>
                        <input type="text" id="f3Example1" class="form-control" v-model="user.userName" />
                      </div>
                    </div>
                    <div class="d-flex flex-row align-items-center mb-4">
                      <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                      <div class="form-outline flex-fill mb-0">
                        <label class="form-label" for="forExample4c">비밀번호</label>
                        <input type="password" id="formle4c" class="form-control" v-model="user.userPassword" />
                      </div>
                    </div>

                    <div class="d-flex flex-row align-items-center mb-4">
                      <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                      <div class="input-group flex-fill">
                        <label class="form-label" for="form3Example1c">이메일 주소</label>
                        <input type="text" class="form-control" id="form3Exame1c" v-model="user.emailId" placeholder="이메일 아이디">
                        <span class="input-group-text">@</span>
                        <input type="text" class="form-control" id="form3Eple1c" v-model="user.emailDomain"
                          placeholder="도메인">
                      </div>
                    </div>

                    <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                      <button type="button" class="btn btn-primary btn-lg" @click="JoinUser()">가입하기</button>
                    </div>
                  </form>

                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<style scoped></style>