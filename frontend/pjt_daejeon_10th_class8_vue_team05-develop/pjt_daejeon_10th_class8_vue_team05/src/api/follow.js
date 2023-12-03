import { localAxios } from "@/util/http-commons";

const local = localAxios();

function countFollowUser(userId, success, fail) {
    local.get(`/follow/${userId}`).then(success).catch(fail);
  }

  export{
    countFollowUser};