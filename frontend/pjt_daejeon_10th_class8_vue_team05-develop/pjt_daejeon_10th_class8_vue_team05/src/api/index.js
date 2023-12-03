import axios from "axios";

const { VITE_VUE_API_URL } = import.meta.env;

function localAxios() {
  const instance = axios.create({
    baseURL: VITE_VUE_API_URL, // baseUrl(도메인) 까지만 , get 호출할 때 /board를 보내줄 수 있음
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
  });
  return instance;
}

function tripInstance() {
  const instance = axios.create({
    baseURL: VITE_VUE_API_URL + "/trip",
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
  });
  return instance;
}

function tripScheduleInstance() {
  const instance = axios.create({
    baseURL: VITE_VUE_API_URL + "/trip-schedule",
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
  });
  return instance;
}

function locationInstance() {
  const instance = axios.create({
    baseURL: VITE_VUE_API_URL + "/location",
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
  });

  return instance;
}

function categoryInstance() {
  const instance = axios.create({
    baseURL: VITE_VUE_API_URL + "/category",
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
  });

  return instance;
}

function fileInstance() {
  const instance = axios.create({
    baseURL: VITE_VUE_API_URL,
    headers: {
      "Content-Type": "multipart/form-data",
    },
  });
  return instance;
}

export {
  localAxios,
  fileInstance,
  tripInstance,
  locationInstance,
  categoryInstance,
  tripScheduleInstance,
};
