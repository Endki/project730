const SERVER_URL = process.env.VUE_APP_SERVER_URL;
export default {
  //베이스 술 정보 불러오기
  async loadBaseAlcohol(context) {
    const response = await fetch(`${SERVER_URL}/article/base-alcohol`, {
      headers: {
        "Content-Type": "application/json; charset=utf-8",
        'Accept': "application/json;",
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Headers": "*",
      },
      method: "GET",
    });
    const responseData = await response.json();

    const baseAlcoholes = [];

    for (const key in responseData) {
      const baseAlcohol = {
        pk_alcohol: responseData[key].pk_alcohol,
        type_kor: responseData[key].type_kor,
        type_eng: responseData[key].type_eng,
        name_kor: responseData[key].name_kor,
        name_eng: responseData[key].name_eng,
        abv: responseData[key].abv,
        img_path: responseData[key].img_path,
        content: responseData[key].content,
      };
      baseAlcoholes.push(baseAlcohol);
    }
    context.commit("baseAlcohol", baseAlcoholes);
  },
};
