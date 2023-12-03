import { localAxios } from "@/util/http-commons";

const local = localAxios();

function listArticle(param, success, fail) {
  local.get(`/board`, { params: param }).then(success).catch(fail);
}

function detailArticle(articleno, success, fail) {
  local.get(`/board/${articleno}`).then(success).catch(fail);
}

function registArticle(article, success, fail) {
  console.log("boardjs article", article);
  local.post(`/board`, JSON.stringify(article)).then(success).catch(fail);
}

function getModifyArticle(articleno, success, fail) {
  local.get(`/board/modify/${articleno}`).then(success).catch(fail);
}

function modifyArticle(article, success, fail) {
  local.put(`/board`, JSON.stringify(article)).then(success).catch(fail);
}

function deleteArticle(articleno, success, fail) {
  local.delete(`/board/${articleno}`).then(success).catch(fail);
}

function deleteBoardComment(boardTripId, commentId, success, fail) {
  local
    .delete(`/comment/board/${boardTripId}/${commentId}`)
    .then(success)
    .catch(fail);
}

function writeBoardComment(comment, success, fail) {
  local
    .post(`/comment/board`, JSON.stringify(comment))
    .then(success)
    .catch(fail);
}

function getBoardComment(boardTripId, success, fail) {
  local.get(`/comment/board/${boardTripId}`).then(success).catch(fail);
}

function deleteTripComment(boardTripId, commentId, success, fail) {
  local
    .delete(`/comment/trip/${boardTripId}/${commentId}`)
    .then(success)
    .catch(fail);
}

function writeTripComment(comment, success, fail) {
  local
    .post(`/comment/trip`, JSON.stringify(comment))
    .then(success)
    .catch(fail);
}

function getTripComment(boardTripId, success, fail) {
  local.get(`/comment/trip/${boardTripId}`).then(success).catch(fail);
}

export {
  listArticle,
  detailArticle,
  registArticle,
  getModifyArticle,
  modifyArticle,
  deleteArticle,
  deleteBoardComment,
  writeBoardComment,
  getBoardComment,
  deleteTripComment,
  writeTripComment,
  getTripComment,
};
