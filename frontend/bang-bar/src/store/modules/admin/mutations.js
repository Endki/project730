export default {
    setRanking(state, payload){
       state.Ranking = payload;
   },setArticleRanking(state, payload){
    state.ArticleRanking = payload;
    },setNewArticle(state, payload){
        state.NewArticle = payload;
    },setNewForum(state, payload){
        state.NewForum = payload;
    }
};