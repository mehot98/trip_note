import { categoryInstance } from "./index.js";

const api = categoryInstance();

async function selectListCategory(tripId, success, fail) {
  await api
    .get("/" + tripId)
    .then(success)
    .catch(fail);
}

export { selectListCategory };
