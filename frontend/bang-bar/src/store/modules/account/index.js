import mutations from "./mutations.js";
import actions from "./actions.js";
import getters from "./getters.js";

// 던져줘야 하는 데이터들

export default {
  namespaced: true,
  state() {
    return {
      lastFetch: null,
      email:"",
    };
  },
  mutations,
  actions,
  getters,
};
