import { localAxios } from "@/util/http-commons";

const local = localAxios();

// function listUser(userId, success, fail) {
//   local.get(`/user/profile/${userId}`).then(success).catch(fail);
// }

function modifyUser(formData, success, fail) {
  const config = {
    headers: {
      "Content-Type": "multipart/form-data", // 컨텐츠 타입 지정해줘야함
    },
  };

  local.put(`/user/modify`, formData, config).then(success).catch(fail);
}

// function infoUser(userId, success, fail) {
//   local.get(`/user/info/${userId}`).then(success).catch(fail);
// }

function deleteUser(userId, success, fail) {
  local.delete(`/user/delete/${userId}`).then(success).catch(fail);
}

function joinUser(user, success, fail) {
  local.post(`/user/join`, JSON.stringify(user)).then(success).catch(fail); // 여기 경로랑 controller에서의 /user(requestMapping)/PostMapping()이랑 같아야 함.
}

async function userConfirm(param, success, fail) {
  await local.post(`/user/login`, param).then(success).catch(fail);
}

async function findById(userId, success, fail) {
  local.defaults.headers["Authorization"] = sessionStorage.getItem("accessToken");
  await local.get(`/user/info/${userId}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  local.defaults.headers["refreshToken"] = sessionStorage.getItem("refreshToken"); //axios header에 refresh-token 셋팅
  await local.post(`/user/refresh`, user).then(success).catch(fail);
}

async function logout(userId, success, fail) {
  await local.get(`/user/logout/${userId}`).then(success).catch(fail);
}

export { deleteUser, joinUser, modifyUser, userConfirm, findById, tokenRegeneration, logout };
