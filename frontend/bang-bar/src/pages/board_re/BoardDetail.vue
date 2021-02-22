<template>
  <div style="text-align: center;">
     
      <div class="whiteboard">
        <div class="content-detail-content-info-left-number" >[{{forum.category}}]</div>
        <div class="content-detail-content-info-left-subject" >{{forum.title}}</div>
      </div>
      <div class="whiteboard">
        <div class="content-detail-content-info-right-user">{{forum.user_name}}
          <base-button @click="follow" v-if="isFollow">팔로잉</base-button>
          <base-button @click="follow" v-else>팔로우</base-button>
        </div>
        <div class="content-detail-content-info-right-created">
          <!-- 날짜 가공할 것 2021.01.24. 23:23 순으로 -->
          {{forum.created_at}}
          -----
          <span>조회수 : {{forum.hits}} </span>
          
          -----
          <span>코멘트수</span>
          ---------
          <span>추천수  {{forum.like_cnt}} </span>

          </div>
      </div>
      <div class="whiteboard" style="min-height:500px" >{{forum.content}}</div>
     
      <div class ="flex flex-col">
        <base-button>추천수 {{forum.like_cnt}}</base-button>
      </div>
      <div class="content-detail-button">
        
        <span v-if="forum.user_name===owner_check">
          <base-button @click="updateData">수정</base-button>
          <base-button @click="deleteData">삭제</base-button>
        </span>
        <base-button @click="golist">목록</base-button>
        
      </div>
      <!-- <div class="content-detail-comment">
        <CommentList :contentId="contentId"></CommentList>
      </div> -->
    
  </div>
</template>

<script>
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
export default {
  name: "BoardDetail",
  data() {
    return {
      owner_check:localStorage.getItem('user_name'),
      forum:[],
      forumId:this.$route.params.contentId,
      isFollow : false,
    };
  },
  created(){
    this.forum_Detail();
    this.isFollow();
    this.isFollow = this.$store.getters["follows/isFollow"];
  },
  methods: {
    //삭제
    forum_Detail(){
        this.axios.get(`${SERVER_URL}/forum/${this.forumId}`, {
        headers: {
        'Access-Control-Allow-Origin': '*',
        'Content-Type': 'application/json; charset = utf-8',
        "Access-Control-Allow-Headers": "*",
        }
      })
      .then((result)=>{
        // this.items=result;
        // console.log(result)
        this.forum = result.data
      })
      .catch(e=>{
        console.log('error:',e)
      })


    },


    deleteData() {
      // const content_index = data.Content.findIndex(
        // contentItem => contentItem.content_id === this.contentId
      // );
      // data.Content.splice(content_index, 1); // 데이터 삭제
        // var repl=this.date.replace("-","");
        // let params={
        //   forum: this.forumId,
          
        // }
        const headers = {
          'Content-type': 'application/json; charset=UTF-8',
          'Accept': '*/*',
          'Access-Control-Allow-Origin': '*',
        "Access-Control-Allow-Headers": "*",
      }

        this.axios.delete(`${SERVER_URL}/forum/delete-forum/${this.forumId}`,
        // JSON.stringify(params),
        { headers }
      )
      .then((result)=>{
          console.log(result)
      this.$router.push({
        path: "/board/list"
      });
      })
      .catch(e=>{
          console.log('error:',e)
      })
    },

    //수정
    updateData() {
      this.$router.push({
        // path: `/board/modify/${this.forumId}`
        path: `/board/create/${this.forumId}`
      });
    },

    //목록으로가기
    golist(){
      this.$router.go(-1)
    },
    // 팔로우 여부 확인하기
    async isFollow() {
      const userInfo = {
        target_no : this.forum.user_no
      };
      await this.$store.dispatch("follows/isFollow", userInfo);
    },
    //follow 하기
    async follow() {
      console.log(this.isFollow);
      console.log(this.isFollow);
      console.log(this.isFollow);
      console.log(this.isFollow);
      console.log(this.isFollow);
      if(localStorage.getItem("user_name") === null) {
        alert("로그인이 필요한 기능입니다.")
        return;
      }
      const userInfo = {
        target_no : this.forum.user_no
      };
      if(this.isFollow) {
        await this.$store.dispatch("follows/unfollow", userInfo);
      } else {
        await this.$store.dispatch("follows/follow", userInfo);
      }
    }
  },
  watch : {
    set_isFollow(newVal) {
      this.isFollow = newVal;
    }
  },
  computed : {
    set_isFollow() {
      return this.$store.getters["follows/isFollow"];
    }
  },
  components: {
    // CommentList,
    
  }
};
</script>
<style scoped>
.content-detail-content-info {
  border: 1px solid black;
  display: flex;
  justify-content: space-between;
}

.content-detail-content-info-left {
  width: 720px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  padding: 1rem;
}

.content-detail-content-info-right {
  width: 300px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 1rem;
}

.content-detail-content {
  border: 1px solid black;
  margin-top: 1rem;
  padding-top: 1rem;
  min-height: 720px;
}

.content-detail-button {
  border: 1px solid black;
  margin-top: 1rem;
  padding: 2rem;
}

.content-detail-comment {
  border: 1px solid black;
  margin-top: 1rem;
  padding: 2rem;
}

    .whiteboard{
        width:50%;
        background-color: #FFFFFF;
        text-decoration: none;
        padding: 0.75rem 1.5rem;
        font: inherit;
        border: 0.1px solid #FFFFFF;
        border-radius: 30px;
        margin-right: 0.5rem;
        display: inline-block;
        transition: .3s ease-out;
        margin-bottom: 15px;
    }
</style>