const SERVER_URL = process.env.VUE_APP_SERVER_URL;
export default {
  
  async registerRecipe(context, payload) {
    console.log(context);
    console.log(payload);
    // const userId = context.rootGetters.userId;
    const recipeData = {
      user_no: context.rootGetters.pkUser,
      created_at: new Date().toLocaleTimeString(), // 변경 가능
      category: payload.category,
      img_path: payload.img_path,
      title_kor: payload.title_kor,
      title_eng: payload.title_eng,
      abv: payload.abv,
      content: payload.content,
      cup_no: payload.cupinfo,
      tag: payload.tags,
      alcohol: payload.alcohols,
      ingredient: payload.ingredients,
      recipe: payload.recipes,
    };

    // const token = context.rootGetters.token;
    const url = "http://localhost:8081/article/create";
    //axios의 then이 response.ok 다
    const response = await fetch(url, {
      headers: {
        "Content-Type": "application/json; charset=utf-8",
        Accept: "application/json;",
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Headers": "*",
      },
      method: "POST",
      body: JSON.stringify(recipeData),
    });
    const responseData = await response.text(); //"
    console.log("responseData");
    console.log(responseData);

    //실패했을 때
    if (!response.ok) {
      // ...
    }
    context.commit("registerRecipe", recipeData);
  },

  //글을 불러오는 곳
  async loadBoard(context, payload) {
    
    if (!payload.forceRefresh && !context.getters.shouldUpdate) {
      console.log("if Check")
      return;
    }

    const pageData = {
      page_num: payload.page_num,
      page_range: payload.page_range,
      search_type: payload.search_type,
      keyword: payload.keyword,
    }
    
    const url = `${SERVER_URL}/forum/search-forum-list` ;
    // console.log(pageData.page_num+" pageNum");
    const response = await fetch(url, {
      headers: {
        "Content-Type": "application/json; charset=utf-8",
        'Accept': '*/*',
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Headers': '*',
      },
      method: "POST",
      body: JSON.stringify(pageData),
    });
    const responseData = await response.json();
    console.log(responseData);
    if (!response.ok) {
      console.log("error")
    }

    const boards = [];
    // const paginated = [];
    for (const key in responseData) {
      const board = {
        pk_forum: responseData[key].pk_forum,
        user_no: responseData[key].user_no,
        title: responseData[key].title,
        category: responseData[key].category,
        like_cnt: responseData[key].like_cnt,
        hits: responseData[key].hits,
        created_at: responseData[key].created_at,
        updated_at: responseData[key].updated_at,
        content:responseData[key].content,

        range_total_cnt: responseData[key].range_total_cnt,
        page_start: responseData[key].page_start,
        page_end: responseData[key].page_end,
        forum_start: responseData[key].forum_start,
        check_prev: responseData[key].check_prev,
        check_next: responseData[key].check_next,

      };
      
      boards.push(board);
    }
    console.log(boards);
    context.commit("setBoards", boards);
  },



};
