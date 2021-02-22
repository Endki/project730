import mutations from './mutations.js';
import actions from './actions.js';
import getters from './getters.js';

export default {
  state() {
    return {
      user_name: null,
      pk_user: null,
      email: null,
      profileImage: null,
    };
  },
  mutations,
  actions,
  getters,
};