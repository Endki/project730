import mutations from "./mutations.js";
import actions from "./actions.js";
import getters from "./getters.js";

export default {
  namespaced: true,
  state() {
    return {
      lastFetch: null,
      // dummy data 
      keyword: null,
      Ranking:[],
      ArticleRanking:[],
      recommendationData: [],
      recommendRecipes: [],
      recipes: [
        {
          pk_article: 1,
          user_name: "관리자",
          category: true,
          img_path: "https://images.unsplash.com/photo-1544145945-f90425340c7e?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80",
          title_kor: "롱 아일랜드 아이스 티",
          title_eng: "long island iced tea",
          content:
            "롱아일랜드 아이스 티는 일반적으로 보드카, 데킬라, 가벼운 럼주, 트리플 섹, 진, 그리고 콜라 스플래시로 만든 알코올 혼합 음료의 한 종류이다. 아이스 티와 같은 황색을 띠고 있다.",
          tag:
            "진<br>보드카<br>화이트 럼<br>테킬라<br>트리플 섹<br>레몬 주스<br>설탕 시럽<br>콜라<br>단 맛<br>도수 높음",
          alcohol:
            "보드카/15/ml<br>진/15/ml<br>화이트 럼/15/ml<br>테킬라/15/ml<br>트리플 섹/15/ml",
          ingredient:
            "재료/레몬 주스/25/ml<br>재료/설탕 시럽/30/ml<br>재료/콜라/1/캔<br>가니쉬/레몬 슬라이스/1/개",
          recipe:
            "모든 재료를 얼음으로 채워진 하이볼 글래스에 넣으세요.<br>부드럽게 저으세요.<br>레몬 슬라이스로 장식하세요.",
          like_cnt: 5,
          bookmark_cnt: 7,
          hits: 23,
        },
        {
          pk_article: 2,
          user_name: "방구석 바텐더",
          category: false,
          img_path: "https://images.unsplash.com/photo-1497534446932-c925b458314e?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=707&q=80",
          title_kor: "테킬라 스트로베리 샤워",
          title_eng: "tequila strawberry shower",
          content:
            "딸기덕후인 제가 딸기를 칵테일로 접목시켜 봤습니다~~! 마가리타 느낌으로 생각해주시면 되는데요, 확실히 딸기가 들어가서 그런지 상큼하고 맛있습니다ㅎㅎ",
          tag: "테킬라<br>레몬 주스<br>딸기<br>토닉 워터<br>알딸딸",
          alcohol: "테킬라/20/ml",
          ingredient:
            "재료/레몬 주스/10/ml<br>재료/토닉 워터/40/ml<br>재료/딸기/3/알<br>가니쉬/레몬 슬라이스/1/개",
          recipe:
            "깨끗이 씻고 꼭지를 딴 딸기를 컵에 넣고 숟가락으로 마구 부셔주세요.<br>테킬라, 레몬주스를 넣고 1차로 섞어줍니다.<br>토닉 워터를 붓고 살살 저어줍니다. (기호에 따라 더 넣어주세요.)<br>레몬 슬라이스로 장식하세요.",
          like_cnt: 12,
          bookmark_cnt: 29,
          hits: 430,
        },
      ],
    };
  },
  mutations,
  actions,
  getters,
};
