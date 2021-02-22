export default {
    setLikeBtn(state, payload) {
        state.likeBtn = payload;
    },
    setBmarkBtn(state, payload) {
        state.bmarkBtn = payload;
    },
    setLikeCnt(state, payload) {
        state.like_cnt = payload;
    },
    setBmarkCnt(state, payload) {
        state.bookmark_cnt = payload;
    }
}