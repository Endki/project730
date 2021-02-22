const SERVER_URL = process.env.VUE_APP_SERVER_URL;
export default {
  
    //2가지 기능이 있다.

  async checkEmail(context, payload) {
    console.log(context);
    console.log(payload);
        
    const emailData = {
      email: payload.email,
    };

    // const token = context.rootGetters.token;
    const url = `${SERVER_URL}/user/join/mail/`+this.email;
    const response = await fetch(url, {
      headers: {
        "Content-Type": "application/json; charset=utf-8",
        Accept: "application/json;",
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Headers": "*",
      },
      method: "POST",
      body: JSON.stringify(emailData),
    });
    const responseData = await response.text();
    console.log("responseData");
    console.log(responseData);

    if (!response.ok) {
      // ...
    }
    context.commit("checkEmail", emailData);
    },
    
    async checkName(context, payload) {
        console.log(context);
        console.log(payload); 

        const nameData = {
            nickname: payload.nickname,
        };
        
        const url = `${SERVER_URL}/user/join/mail/`+this.email;
        const response = await fetch(url, {
          headers: {
            "Content-Type": "application/json; charset=utf-8",
            Accept: "application/json;",
            "Access-Control-Allow-Origin": "*",
            "Access-Control-Allow-Headers": "*",
          },
          method: "POST",
          body: JSON.stringify(emailData),
        });
        const responseData = await response.text();
        console.log("responseData");
        console.log(responseData);
    
        if (!response.ok) {
          // ...
        }
        context.commit("checkEmail", emailData);

    },
};
