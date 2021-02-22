export default {
    registerBoard(state, payload) {
        console.log("mutations");
        console.log(payload);
        state.boards.push(payload);
    },  

    setBoards(state, payload) {
        console.log("setBoards")
        state.boards = payload;
    },
    
    setPageInfo(state, payload) {
        state.page_num = payload.page_num;
        state.page_range = payload.page_range;
        state.search_type = payload.search_type;
        state.keyword = payload.keyword;
        state.forum_cnt_per_page = payload.forum_cnt_per_page;
    },

    setFetchTimestamp(state) {
        state.lastFetch = new Date().getTime();
    }
};