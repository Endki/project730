export default {
   setMyRecipe(state, payload){
       state.myRecipe = payload;
   },
   setMyForum(state, payload) {
      state.myForum = payload;
   },
   getMyBookmark(state, payload){
       state.myBookmark = payload;
   },
   getOtherMyPage(state, payload){
       state.otherMyPage = payload;
   } 
};