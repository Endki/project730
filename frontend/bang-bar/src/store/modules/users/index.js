// 전체 회원 관리
import mutations from "./mutations.js";
import actions from "./actions.js";
import getters from "./getters.js";

export default {
    namespaced: true,
    state() {
        return {
            myRecipe:[],
            myForum:[],
            myBookmark: [],
            otherMyPage: [],
        };
    },
    mutations,
    actions,
    getters,
}