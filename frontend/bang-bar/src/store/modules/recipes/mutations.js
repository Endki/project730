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
    }
};