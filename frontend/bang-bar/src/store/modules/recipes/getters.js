export default {
  recipes(state) {
    return state.recipes;
  },
  hasRecipes(state) {
    return state.recipes && state.recipes.length > 0;
  },
  shouldUpdate(state) {
    const lastFetch = state.lastFetch;
    if (!lastFetch) return true;

    const currentTimeStamp = new Date().getTime();
    return (currentTimeStamp - lastFetch) / 1000 > 60;
  },
  Ranking(state) {
    return state.Ranking;
  },
  ArticleRanking(state) {
    return state.ArticleRanking;
  },
  recommendRecipes(state) {
    return state.recommendRecipes;
  },
  recommendationData(state) {
    return state.recommendationData;
  },
  keyword(state) {
    return state.keyword;
  },
};
