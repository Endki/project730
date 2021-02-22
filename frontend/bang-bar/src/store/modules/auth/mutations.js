export default {
    setUser(state, payload) {
      state.user_name = payload.user_name;
      state.user_email = payload.email;
      state.pk_user = payload.pk_user;
      state.profileImage = payload.profileImage;
    },
    setProfileImage(state, payload) {
      state.profileImage = payload;
    },
    setUserName(state, payload) {
      state.userName = payload;
    }
  };