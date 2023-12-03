import { tripInstance } from "./index.js";

const api = tripInstance();

function selectListTrip(params, success, fail) {
  api
    .get("/", {
      params: params,
    })
    .then(success)
    .catch(fail);
}

function selectTrip(tripId, success, fail) {
  api.get(`/${tripId}`).then(success).catch(fail);
}

function selectTripDetail(tripId, success, fail) {
  api.get(`/detail/${tripId}`).then(success).catch(fail);
}

function registTrip(trip, success, fail) {
  const config = {
    headers: {
      "Content-Type": "multipart/form-data", // 컨텐츠 타입 지정해줘야함
    },
  };

  api.post(``, trip, config).then(success).catch(fail);
}

export { selectListTrip, selectTrip, selectTripDetail, registTrip };
