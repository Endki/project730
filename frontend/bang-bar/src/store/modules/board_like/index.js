import mutations from "./mutations.js";
import actions from "./actions.js";
import getters from "./getters.js";

export default {
    namespaced: true,
    state() {
        return {
            isLikeClick: "off",
            isBmarkClick: "off", 
            article_no:"",
            likeBtn: false,
            like_cnt: 0,
        }
    },
    mutations,
    actions,
    getters,
}