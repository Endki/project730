export default {
    registerRecipe(state, payload) {
        console.log("mutations");
        console.log(payload);
        state.recipes.push(payload);
    },
    setRecipes(state, payload) {
        state.recipes = payload;
    }, 
    setFetchTimestamp(state) {
        state.lastFetch = new Date().getTime();
    },setRanking(state, payload){
        state.Ranking = payload;
    },setArticleRanking(state, payload){
        state.ArticleRanking = payload;
    },
    setRecommendRecipes(state, payload) {
        state.recommendRecipes = payload;
    },
    setRecommendationData(state, payload) {
        state.recommendationData = payload;
    },
    setKeyword(state, payload) {
        state.keyword = payload;
    },
};