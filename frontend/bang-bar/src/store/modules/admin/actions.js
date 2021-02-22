const SERVER_URL = process.env.VUE_APP_SERVER_URL;
export default {
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

        for (const key in responseData) {
            const articleranking = {
                pk_article: responseData[key].pk_article,
                user_name: responseData[key].user_name,
                like_weekly: responseData[key].like_weekly,
                img_path: responseData[key].img_path,
                title_kor: responseData[key].title_kor,
                like_cnt: responseData[key].like_cnt,
                bookmark_cnt: responseData[key].bookmark_cnt,
                tag: responseData[key].tag,
            };
            articlerankings.push(articleranking);
            console.log(articleranking.pk_article);
        }
        context.commit("setArticleRanking", articlerankings);
    },
    // 새로운 레시피 
    async LoadNewArticle(context) {
        const response = await fetch(`${SERVER_URL}/article/new`, {
            headers: {
                "Content-Type": "application/json; charset=utf-8",
                'Accept': '*/*',
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Headers': '*',
            },
            method: "GET",
        });
        const responseData = await response.json();
        const newarticles = [];

        for (const key in responseData) {
            const newarticle = {
                pk_article: responseData[key].pk_article,
                user_name: responseData[key].user_name,
                like_weekly: responseData[key].like_weekly,
                img_path: responseData[key].img_path,
                title_kor: responseData[key].title_kor,
                like_cnt: responseData[key].like_cnt,
                bookmark_cnt: responseData[key].bookmark_cnt,
                tag: responseData[key].tag,
            };
            newarticles.push(newarticle);
        }
        context.commit("setNewArticle", newarticles);
    },// 새로운 게시글 
    async LoadNewForum(context) {
        const response = await fetch(`${SERVER_URL}/forum/new`, {
            headers: {
                "Content-Type": "application/json; charset=utf-8",
                'Accept': '*/*',
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Headers': '*',
            },
            method: "GET",
        });
        const responseData = await response.json();
        const newforums = [];

        for (const key in responseData) {
            const newforum = {
                pk_forum: responseData[key].pk_forum,
                category: responseData[key].category,
                title: responseData[key].title,
                img_path: responseData[key].img_path,
                user_name: responseData[key].user_name,
                created_at: responseData[key].created_at,
                content: responseData[key].content,
               
            };
            newforums.push(newforum);
        }
        context.commit("setNewForum", newforums);
    },
};