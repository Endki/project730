const SERVER_URL = process.env.VUE_APP_SERVER_URL;
export default {
    // 버튼이 눌러져있는지 판단 
    async isClick(context, payload) {
        console.log(payload);
        let url = "";
        // 만약 누른 버튼이 like라면
        url = `${SERVER_URL}/forum/is-like`;
        console.log("isClick");
        const userInfo = {
            user_no : context.rootGetters.pkUser,
            forum_no : payload.forum_no,
        }
        const response = await fetch(url, {
            headers: {
                "Content-Type": "application/json; charset=utf-8",
                'Accept': '*/*',
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Headers': '*',
            },
            method: "POST",
            body: JSON.stringify(userInfo)
        });
        // 만약 쿼리가 실행됐으면 -1 이상의 값이 반환
        // 눌렀으면 1, 안눌렀으면 0
        const responseData = await response.text();
        console.log(responseData);
        if(parseInt(responseData) == 0) { // 누른적없는 게시글이라면
                context.commit("setLikeBtn", false);
        } else if(parseInt(responseData) == 1) { // 누른적 있는 게시글이라면
                context.commit("setLikeBtn", true);
        } else {
            console.log(parseInt(responseData));
            // alert("페이지 로드에 문제가 생겼습니다.");
        }

        if(payload.isLike == true) {
            context.commit("setLikeCnt", payload.like_cnt);
        }
    }, 

    // 좋아요 혹은 북마크를 누르면 내 계정에 추가 
    async clickBtn(context, payload) {
        let url = "";

        url = `${SERVER_URL}/forum/like`;

        console.log(payload);
        const userInfo= {
            isclick : payload.isclick,
            user_no : context.rootGetters.pkUser,
            forum_no : payload.forum_no,
        }
        const response = await fetch(url, {
            headers: {
                "Content-Type": "application/json; charset=utf-8",
                'Accept': '*/*',
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Headers': '*',
              },
              method: "PUT",
              body: JSON.stringify(userInfo),
        });
        const responseData = await response.text();

        if (responseData === "error") {
            alert("북마크 혹은 좋아요 누르기 실패");
        } else {

                if(payload.isclick == "off") { // 원래 상태가 누르지 않았다면
                    context.commit("setLikeBtn", true);
                    context.commit("setLikeCnt", payload.like_cnt + 1);
                    console.log("좋아요업");
                } else { // 원래 상태가 눌렀다면
                    context.commit("setLikeBtn", false);
                    context.commit("setLikeCnt", payload.like_cnt - 1);
                    console.log("좋아요다운");
                }
        
        }
    }
}