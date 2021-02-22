export default {
    setIsFollow(state, payload) {
        state.isFollow = payload;
    },
    setFollowList(state, payload) {
        state.followList = payload;
    },
    setFollowingList(state, payload) {
        state.followingList = payload;
    },
    deleteFollowing(state, payload) {
        state.followingList = state.followingList.filter(user => user.pk_user !== payload);
    },
    deleteFollower(state, payload) {
        state.followList = state.followList.filter(user => user.pk_user !== payload);
    },
};