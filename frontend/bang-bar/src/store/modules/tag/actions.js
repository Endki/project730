const SERVER_URL = process.env.VUE_APP_SERVER_URL;
export default {
  //태그 등록
  async submitTag(context, payload) {

    var tag = payload.content_kor;
    // console.log(tagData);
    const url = `${SERVER_URL}/article/submit_tag`;
    const response = await fetch(url, {
      headers: {
        "Content-Type": "application/json; charset=utf-8",
        'Accept': '*/*',
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Headers': '*',
      },
      method: "POST",
      body: tag,
    });
    const responseData = await response.text();
    // console.log("responseData");
    // console.log(responseData);

    if (responseData === "error") {
      console.log("태그 등록 에러")
    }
  },
  //태그 검색
  async checkTag(context, payload) {
    // console.log("context",context);
    // console.log("payload",payload);

    const tagData = {
        searchtxt:payload.content_kor,
    };
    // console.log(tagData);
    // console.log(tagData.searchtxt);
    const url = `${SERVER_URL}/article/tag/${tagData.searchtxt}`;
    const response = await fetch(url, {
      headers: {
        "Content-Type": "application/json; charset=utf-8",
        'Accept': '*/*',
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Headers': '*',
      },
      method: "GET",
      // body: JSON.stringify(tagData),
    });
    const responseData = await response.json();
    // console.log("responseData");
    // console.log(responseData);
    
    if (responseData === "error") {
      console.log("태그체크 에러")
    }
    //없으면 false return
    if (responseData.length===0) {
      return false;  
    }
    //있으면 true 리턴
    else {
      return true;
    }
    
  },
  //재료 등록
  async submitIngredient(context, payload) {
    // console.log(context);
    console.log(payload);

    // const tagData = {
      // tag:payload.content_kor,
    // };

    var ingredient = payload.content_kor;
    // console.log(tagData);
    console.log(ingredient)
    const url = `${SERVER_URL}/article/ingredient`;
    const response = await fetch(url, {
      headers: {
        "Content-Type": "application/json; charset=utf-8",
        'Accept': '*/*',
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Headers': '*',
      },
      method: "POST",
      body: ingredient,
    });
    const responseData = await response.text();
    // console.log("responseData");
    // console.log(responseData);

    if (responseData === "error") {
      console.log("재료 등록 에러")
    }
  },

  //재료 검색 
  async checkIngredient(context, payload) {
    // console.log("context",context);
    // console.log("payload",payload);

    const Data = {
        searchtxt:payload.content_kor,
    };
    // console.log(tagData);
    // console.log(tagData.searchtxt);
    const url = `${SERVER_URL}/article/ingredient/${Data.searchtxt}`;
    const response = await fetch(url, {
      headers: {
        "Content-Type": "application/json; charset=utf-8",
        'Accept': '*/*',
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Headers': '*',
      },
      method: "GET",
      // body: JSON.stringify(tagData),
    });
    const responseData = await response.json();
    // console.log("responseData");
    // console.log(responseData);
    
    if (responseData === "error") {
      console.log("재료 체크 에러")
    }
    //없으면 false return
    if (responseData.length===0) {
      return false;  
    }
    //있으면 true 리턴
    else {
      return true;
    }
    
  },
};
