export default {
  boards(state) {
    return state.boards;
  },
  hasBoards(state) {
    return state.boards && state.boards.length > 0;
  },
  getPageNum(state) {
    return state.page_num;
  },

  shouldUpdate(state) {
    const lastFetch = state.lastFetch;
    if (!lastFetch) return true;

    const currentTimeStamp = new Date().getTime();
    return (currentTimeStamp - lastFetch) / 1000 > 60;
  },
};
