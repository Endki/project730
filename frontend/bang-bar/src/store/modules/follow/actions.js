const SERVER_URL = process.env.VUE_APP_SERVER_URL;
export default {
    async isFollow(context, payload) {
        const userInfo = {
            user_no : context.rootGetters.pkUser,
            target_no : payload.target_no
        };
        const response = await fetch(`${SERVER_URL}/user/isfollow`, {
            headers: {
                "Content-Type": "application/json; charset=utf-8",
                'Accept': '*/*',
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Headers': '*',
            },
            method: "POST",
            body: JSON.stringify(userInfo)
        });
        const responseData = await response.text();
        console.log(responseData);
        if(parseInt(responseData) == 1) {
            context.commit("setIsFollow", true);
        } else if(parseInt(responseData) == 0) {
            context.commit("setIsFollow", false);
        } else {
            alert("팔로우 호출이 잘못되었습니다.");
        }
    },
    async follow(context, payload) {
        const userInfo = {
            user_no : context.rootGetters.pkUser,
            target_no : payload.target_no
        };
        const response =await fetch(`${SERVER_URL}/user/follow`,{
            headers: {
                "Content-Type": "application/json; charset=utf-8",
                'Accept': '*/*',
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Headers': '*',
            },
            method: "POST",
            body: JSON.stringify(userInfo)
        });
        const responseData = await response.json();
        console.log(responseData);
        if(responseData !== null) {
            context.commit("setIsFollow", true);
            const followList = [];
            for (const key in responseData) {
              const follow = {
                pk_user: responseData[key].pk_user,
                user_name: responseData[key].user_name,
                img_path: responseData[key].img_path,
                follow_cnt: responseData[key].follow_cnt,
              };
              followList.push(follow);
            }
            context.commit("setFollowList", followList);
        } else {
            alert("팔로우 실패");
        }
    },
  async unfollow(context, payload) {
    let userInfo;
    console.log("mode : " + payload.mode);
    if (payload.mode === "following") { // 내가 팔로우 하는 사람
      userInfo = {
        user_no: context.rootGetters.pkUser, //지금 로그인한 유저
        target_no: payload.target_no, // 지울 사람 
      };
    } else if (payload.mode === "follower") { // 나를 팔로우 하는 사람 
      userInfo = {
        user_no: payload.target_no,
        target_no: context.rootGetters.pkUser,
      };
    }
    console.log(userInfo);
    const response = await fetch(`${SERVER_URL}/user/unfollow`, {
      headers: {
        "Content-Type": "application/json; charset=utf-8",
        Accept: "*/*",
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Headers": "*",
      },
      method: "POST",
      body: JSON.stringify(userInfo),
    });
    const responseData = await response.text();
    console.log(responseData);
    if (responseData === "SUCCESS") {
      context.commit("setIsFollow", false);
    } else {
      alert("팔로우 실패");
    }
    if(payload.flag ===1) {
      context.commit('deleteFollower', context.rootGetters.pkUser);
    } else {
      if (payload.mode === "following") context.commit('deleteFollowing', payload.target_no);
      else if (payload.mode === "follower") context.commit('deleteFollower', payload.target_no);
    }
  },

  async followList(context, payload) {
    let url = '';

    if (payload.mode === "otherUser") {
      url = `${SERVER_URL}/user/follow/` + payload.target_no + `/ec`
    } else {
      url = `${SERVER_URL}/user/follow/` + context.rootGetters.pkUser + `/ec`
    }

    const response = await fetch(url, {
        headers: {
          "Content-Type": "application/json; charset=utf-8",
          Accept: "*/*",
          "Access-Control-Allow-Origin": "*",
          "Access-Control-Allow-Headers": "*",
        },
        method: "GET",
      }
    );

    const responseData = await response.json();
    const followList = [];
    console.log(responseData);
    for (const key in responseData) {
      const follow = {
        pk_user: responseData[key].pk_user,
        user_name: responseData[key].user_name,
        img_path: responseData[key].img_path,
        follow_cnt: responseData[key].follow_cnt,
      };
      followList.push(follow);
    }
    context.commit("setFollowList", followList);
  },
  async followingList(context, payload) {
    let url = '';

    if (payload.mode === "otherUser") {
      url = `${SERVER_URL}/user/follow/` + payload.target_no + `/ic`
    } else {
      url = `${SERVER_URL}/user/follow/` + context.rootGetters.pkUser + `/ic`
    }

    const response = await fetch(url, {
        headers: {
          "Content-Type": "application/json; charset=utf-8",
          Accept: "*/*",
          "Access-Control-Allow-Origin": "*",
          "Access-Control-Allow-Headers": "*",
        },
        method: "GET",
      }
    );
    const responseData = await response.json();
    const followingList = [];
    console.log(responseData);
    for (const key in responseData) {
      const following = {
        pk_user: responseData[key].pk_user,
        user_name: responseData[key].user_name,
        img_path: responseData[key].img_path,
        follow_cnt: responseData[key].follow_cnt,
      };
      followingList.push(following);
    }
    context.commit("setFollowingList", followingList);
  },
};
