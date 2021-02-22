const SERVER_URL = process.env.VUE_APP_SERVER_URL;
export default {
    // 내가 쓴 글 불러오기
    async LoadMyRecipe(context, payload) {
        let url = '';

        if (payload.mode === "otherUser") {
            url = `${SERVER_URL}/user/mypage/article/` + payload.target_no
          } else {
            url = `${SERVER_URL}/user/mypage/article/` + context.rootGetters.pkUser
        }

        const response = await fetch(url, {
            headers: {
                "Content-Type": "application/json; charset=utf-8",
                'Accept': '*/*',
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Headers': '*',
            },
            method: "GET",
        });
        const responseData = await response.json();
        const recipes = [];

        for (const key in responseData) {
            const recipe = {
                pk_article: responseData[key].pk_article,
                user_name: responseData[key].user_name,
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
            recipes.push(recipe);
        }
        context.commit("setMyRecipe", recipes);
    },
    // 닉네임 중복확인 
    async checkName(_, payload) {
        console.log(payload);

        if (payload.newValue === payload.oldValue) {
            alert("닉네임을 유지합니다.");
            return "SUCCESS";
        }

        const response = await fetch(`${SERVER_URL}/user/join/${payload.newValue}`, {
            headers: {
                "Content-Type": "application/json; charset=utf-8",
                'Accept': '*/*',
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Headers': '*',
            },
            method: "POST"
        });
        
        const responseData = await response.text();
        
        if(responseData === "FAIL") {
            alert("중복된 닉네임입니다.");
            return "FAIL";
        } else {
            alert("사용가능한 닉네임입니다.");
            return "SUCCESS";
        }
    },
    // 내가 쓴 게시글 불러오기
    async LoadMyForum(context, payload) {
        let url = '';

        if (payload.mode === "otherUser") {
            url = `${SERVER_URL}/user/mypage/forum/` + payload.target_no
          } else {
            url = `${SERVER_URL}/user/mypage/forum/` + context.rootGetters.pkUser
        }
        
        const response = await fetch(url, {
            headers: {
                "Content-Type": "application/json; charset=utf-8",
                'Accept': '*/*',
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Headers': '*',
            },
            method: "GET",
        });
        const responseData = await response.json();

        const forums = [];

        for (const key in responseData) {
            const forum = {
                pk_forum: responseData[key].pk_forum,
                created_at:responseData[key].created_at,
                updated_at:responseData[key].updated_at,
                category:responseData[key].category,
                title:responseData[key].title,
                content:responseData[key].content
            };
            forums.push(forum);
        }
        context.commit("setMyForum", forums);
    },
    // 이름, 이미지 저장 
    async changeNameImg(context, payload) {
        const userInfo = {
            pk_user : context.rootGetters.pkUser,
            user_name : payload.user_name,
            img_path : payload.img_path
        }

        const response = await fetch(`${SERVER_URL}/user/mypage/name-img`, {
            headers: {
                "Content-Type": "application/json; charset=utf-8",
                'Accept': '*/*',
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Headers': '*',
              },
              method: "PUT",
              body:JSON.stringify(userInfo)
        });
        const responseData = await response.json();

        // 이미지 저장
        if(responseData.user_name === payload.user_name && responseData.img_path === payload.img_path) {
            alert("변경되었습니다.");
            localStorage.setItem('user_name', responseData.user_name);
            localStorage.setItem('profileImage', responseData.img_path);
            context.commit('setProfileImage', payload.img_path, {root: true});
            context.commit('setUserName', payload.user_name, {root: true});
            location.reload();
        }
    },
    async confirmPW(context, payload) {
        const userInfo = {
            pk_user : context.rootGetters.pkUser,
            password : payload
        };

        const response = await fetch(`${SERVER_URL}/user/mypage/confirm-pw`, {
            headers: {
                "Content-Type": "application/json; charset=utf-8",
                'Accept': '*/*',
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Headers': '*',
              },
              method: "POST",
              body:JSON.stringify(userInfo)
        });
        const responseData = response.text();
        return responseData;
    },
    async changePassword(context, payload) {
        const userInfo = {
            pk_user : context.rootGetters.pkUser,
            email : payload.email,
            password : payload.password
        };
        const response = await fetch(`${SERVER_URL}/user/mypage/pw`, {
            headers: {
                "Content-Type": "application/json; charset=utf-8",
                'Accept': '*/*',
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Headers': '*',
              },
              method: "PUT",
              body:JSON.stringify(userInfo)
        });
        const responseData = await response.json();

        console.log(responseData);
        alert("비밀번호가 변경되었습니다.");
        localStorage.removeItem("user_name");
        localStorage.removeItem("pk_user");
        localStorage.removeItem("email");
        return "success";
    },
    
    // 북마크한 게시글 불러오기
    async LoadMyBookmark(context) {
        const paramsPkUser = context.rootGetters.pkUser
        const url = `${SERVER_URL}/user/mypage/bookmark/?pk_user=${paramsPkUser}`;

        const response = await fetch(url, {
            headers: {
                "Content-Type": "application/json; charset=utf-8",
                'Accept': '*/*',
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Headers': '*',
            },
            method: "GET",
        });
        const responseData = await response.json();
        const recipes = [];
        for (const key in responseData) {
            const recipe = {
                pk_article: responseData[key].pk_article,
                user_name: responseData[key].user_name,
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
            recipes.push(recipe);
        }
        context.commit("getMyBookmark", recipes);
    },
    async leave(context) {
        console.log(context.rootGetters.pkUser);
        const response = await fetch(`${SERVER_URL}/user/mypage/`+context.rootGetters.pkUser, {
            headers: {
                "Content-Type": "application/json; charset=utf-8",
                'Accept': '*/*',
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Headers': '*',
              },
              method: "DELETE"
        });
        const responseData = await response.text();
        if(responseData === "SUCCESS") {
            alert("회원탈퇴가 완료되었습니다.");
            localStorage.removeItem("user_name");
            localStorage.removeItem("pk_user");
            localStorage.removeItem("email");
            return "success";
        }
    },

    // 타인의 정보 불러오기
    async LoadOtherMyPage(context, payload) {
        let paramsPkUser;
        if (payload.mode === "recipe") {
            paramsPkUser = payload.target_no
        } else {
            paramsPkUser = localStorage.getItem("pkOther")
        }
        const url = `${SERVER_URL}/user/mypage/?pk_user=${paramsPkUser}`;
        const response = await fetch(url, {
            headers: {
                "Content-Type": "application/json; charset=utf-8",
                'Accept': '*/*',
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Headers': '*',
            },
            method: "GET",
        });
        const responseData = await response.json();
        const userInfos = [];
        for (const key in responseData) {
            const userInfo = {
                pk_user: responseData[key].pk_user,
                user_name: responseData[key].user_name,
                img_path: responseData[key].img_path,
            };
            userInfos.push(userInfo);
        }
        context.commit("getOtherMyPage", userInfos);
    },
};