export default {
    email(state) {
      return state.email;
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
  };
  