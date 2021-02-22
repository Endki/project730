const SERVER_URL = process.env.VUE_APP_SERVER_URL;
export default {
  async registerRecipe(context, payload) {
    console.log(context);
    console.log(payload);

    // let isOfficial = true;
    // if (payload.category === "custom") {
    //   isOfficial = false;
    // }
    const recipeData = {
      user_no: context.rootGetters.pkUser,
      // created_at: new Date().toLocaleTimeString(), // 변경 가능
      created_at:payload.created_at,
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
    console.log(recipeData);
    const url = `${SERVER_URL}/article/create`;
    const response = await fetch(url, {
      headers: {
        "Content-Type": "application/json; charset=utf-8",
        'Accept': '*/*',
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Headers': '*',
      },
      method: "POST",
      body: JSON.stringify(recipeData),
    });
    const responseData = await response.text();
    console.log("responseData");
    console.log(responseData);

    if (responseData === "error") {
      alert("등록에 실패하였습니다.");
      return;
    }
    alert("등록이 완료되었습니다.");
  },

  //글을 불러오는 것
  async loadRecipes(context, payload) {
    if (!payload.forceRefresh && !context.getters.shouldUpdate) {
      return;
    }

    const keyword = {
      searchtxt: "",
      tag: ""
    };

    const response = await fetch(`${SERVER_URL}/article/all`, {
      headers: {
        "Content-Type": "application/json; charset=utf-8",
        Accept: "application/json;",
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Headers": "*",
      },
      method: "POST",
      body: JSON.stringify(keyword),
    });
    const responseData = await response.json();
    if (!response.ok) {
      // error..
    }

    const recipes = [];

    for (const key in responseData) {
      const recipe = {
        pk_article: responseData[key].pk_article,
        user_name: responseData[key].user_name,
        user_no: responseData[key].user_no,
        user_profileImage: responseData[key].user_img,
        title_kor: responseData[key].title_kor,
        title_eng: responseData[key].title_eng,
        like_cnt: responseData[key].like_cnt,
        bookmark_cnt: responseData[key].bookmark_cnt,
        hits: responseData[key].hits,
        created_at: responseData[key].created_at,
        updated_at: responseData[key].updated_at,
        like_weekly: responseData[key].like_weekly,
        content: responseData[key].content,
        img_path: responseData[key].img_path,
        category: responseData[key].category,
        abv: responseData[key].abv,
        cup_no: responseData[key].cup_no,
        tag: responseData[key].tag,
        alcohol: responseData[key].alcohol,
        ingredient: responseData[key].ingredient,
        recipe: responseData[key].recipe,

      };
      // console.log(key, responseData[key].img_path);
      recipes.push(recipe);
    }

    context.commit("setRecipes", recipes);
    context.commit("setFetchTimestamp");
  },
  // 주간 유저 랭킹
  async LoadRanking(context) {
      const response = await fetch(`${SERVER_URL}/admin/member/userrank`, {
          headers: {
              "Content-Type": "application/json; charset=utf-8",
              'Accept': '*/*',
              'Access-Control-Allow-Origin': '*',
              'Access-Control-Allow-Headers': '*',
          },
          method: "GET",
      });
      const responseData = await response.json();
      const rankings = [];

      for (const key in responseData) {
        const ranking = {        
              pk_user: responseData[key].pk_user,
              user_name: responseData[key].user_name,
              like_weekly: responseData[key].like_weekly,
              img_path: responseData[key].img_path,
          };
          rankings.push(ranking);
      }
      context.commit("setRanking", rankings);
  },
  // 주간 레시피 랭킹
  async LoadArticleRanking(context) {
    const response = await fetch(`${SERVER_URL}/admin/member/articlerank`, {
        headers: {
            "Content-Type": "application/json; charset=utf-8",
            'Accept': '*/*',
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Headers': '*',
        },
        method: "GET",
    });
    const responseData = await response.json();
    const articlerankings = [];
    console.log(responseData);
    for (const key in responseData) {
      const articleranking = {
            pk_article: responseData[key].pk_article,
            user_name: responseData[key].user_name,
            user_profileImage: responseData[key].user_img,
            like_weekly: responseData[key].like_weekly,
            img_path: responseData[key].img_path,
            title_kor: responseData[key].title_kor,
            like_cnt: responseData[key].like_cnt,
            bookmark_cnt: responseData[key].bookmark_cnt,
            tag: responseData[key].tag,
        };
        articlerankings.push(articleranking);
    }
    context.commit("setArticleRanking", articlerankings);
},

  async updateRecipe(context, payload) {
    const recipeData = {
      pk_article : payload.pk_article,
      updated_at: payload.created_at, // 변경 가능
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
    console.log(recipeData);
    const url = `${SERVER_URL}/article/recipe/` + payload.pk_article;
    const response = await fetch(url, {
      headers: {
        "Content-Type": "application/json; charset=utf-8",
        Accept: "application/json;",
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Headers": "*",
      },
      method: "PUT",
      body: JSON.stringify(recipeData)
    });
    const responseData = await response.text();
    console.log(responseData);
    if(responseData === "success") {
      // alert("글 수정이 완료되었습니다.");
      return true;
    }
  },
  async deleteRecipe(_, payload) {
    const response = await fetch(`${SERVER_URL}/article/recipe/` + payload.pk_article, {
      headers: {
        "Content-Type": "application/json; charset=utf-8",
        Accept: "application/json;",
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Headers": "*",
      },
      method: "DELETE"
    });
    const responseData = response.text();
    if(responseData === "success") {
      alert("글 삭제가 완료되었습니다.");
      return true;
    }
  },
  //레시피 추천용 레시피 찾기 이름입력시 -> pk값을 리턴해준다!
  async searchRecipe(context, payload) {
    // console.log(payload);
    console.log(payload.searchtxt);
    const Data = {
      searchtxt: payload.searchtxt,
      // searchtxt:"",
      tag: ""
    };


    const response = await fetch(`${SERVER_URL}/article/keyword`, {
      headers: {
        "Content-Type": "application/json; charset=utf-8",
        Accept: "application/json;",
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Headers": "*",
      },
      method: "POST",
      body: JSON.stringify(Data),
    });
    // Promise.all().then(result=>console.log(result));

    const responseData =await response.json();
    
    // console.log(responseData[0].pk_article);
    return responseData[0].pk_article;
  },
  async recommendRecipe(context, payload) {
    const response = await fetch(`${SERVER_URL}/admin/recommend-favor`, {
      headers: {
        "Content-Type": "application/json; charset=utf-8",
        Accept: "application/json;",
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Headers": "*",
      },
      method: "POST",
      body: JSON.stringify(payload),
    }); 

    const responseData = await response.json();
    console.log(responseData);
    context.commit("setRecommendRecipes", responseData);
    context.commit("setRecommendationData", payload);
  },
  setSearchKeyword(context, payload) {
    context.commit("setKeyword", payload);
  } 
};
