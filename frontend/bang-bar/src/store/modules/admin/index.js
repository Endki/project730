// 랭킹 관리
import mutations from "./mutations.js";
import actions from "./actions.js";
import getters from "./getters.js";

export default {
    namespaced: true,
    state() {
        return {
            Ranking:[],
            ArticleRanking:[],
            NewArticle:[],
            NewForum:[],
        };
    },
    mutations,
    actions,
    getters,
}