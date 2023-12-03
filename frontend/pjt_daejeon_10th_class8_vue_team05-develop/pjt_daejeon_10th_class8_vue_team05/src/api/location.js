import { locationInstance } from "./index.js";

const api = locationInstance();

function selectListLocation(tripId, success, fail) {
  api
    .get("/" + tripId)
    .then(success)
    .catch(fail);
}

export { selectListLocation };
