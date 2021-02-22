<template>
  <div
    class="font-color-black-400 flex flex-col justify-items-center font-S-CoreDream-light"
  >
    <section
      class="card-flat mt-12 w-1/2 flex flex-col justify-items-center mx-auto text-center"
    >
      <span class="font-extrabold text-base">[{{ forum.category }}]</span>
      <span class="font-extrabold text-xl tracking-wider mt-1">{{
        forum.title
      }}</span>
    </section>
    <section class="card-flat w-1/2 mx-auto flex">
      <img
        class="w-12 h-12 mr-2 cursor-pointer object-cover rounded-full"
        :src="imgsrc"
        alt="profile image"
        @click="saveUser(forum.user_no, forum.user_name)"
      />
      <section class="flex flex-col w-full">
        <article
          class="font-S-CoreDream-medium tracking-wider flex items-center"
        >
          <span
            class="cursor-pointer"
            @click="saveUser(forum.user_no, forum.user_name)"
            >{{ forum.user_name }}</span
          >
          <base-button
            v-if="isFollow"
            class="text-xs px-2 py-1 ml-2"
            mode="colored"
            @click="follow"
            >팔로잉</base-button
          >
          <base-button
            v-else
            class="text-xs px-2 py-1 ml-2"
            mode="outline-colored"
            @click="follow"
            >팔로우</base-button
          >
        </article>
        <article class="flex items-center font-color-black-200 text-sm">
          <span class="mr-2">{{ forum.created_at }}</span>
          <span class="mr-2">조회 {{ forum.hits }}</span>
          <section class="">
            <span v-if="forum.user_name === owner_check">
              <base-button
                class="text-xs px-2 py-1 ml-2"
                mode="nude"
                @click="updateData"
                >수정</base-button
              >
              <base-button
                class="text-xs px-2 py-1"
                mode="nude"
                @click="deleteData"
                >삭제</base-button
              >
            </span>
          </section>
          <section
            class="flex justify-self-end items-center ml-auto mr-4"
            @click="isLike"
          >
            <article class="flex items-center">
              <img
                src="../../assets/icon/like@0.75x.png"
                class="object-contain ml-2 mr-0"
                alt="like icon"
                v-if="!likeBtn"
              />
              <img
                src="../../assets/icon/liked@0.75x.png"
                class="object-contain ml-2 mr-0"
                alt="like icon"
                v-else
              />
              <span class="font-2xs font-semibold font-color-black-300">
                {{ forum.like_cnt }}
              </span>
            </article>
            <article class="flex items-center">
              <img
                src="../../assets/icon/comment@0.75x.png"
                class="object-contain ml-1 mr-0"
                alt="bookmark icon"
              />
              <span class="mr-2 font-2xs font-semibold font-color-black-300">
                {{ forum.comment_cnt }}
              </span>
            </article>
          </section>
        </article>
      </section>
    </section>

    <section class="card-flat flex flex-col w-1/2 mx-auto h-full ">
      <div
        class="mx-4 my-6 mb-10 min-h leading-7 tracking-wide whitespace-pre-line"
      >
        {{ forum.content }}
      </div>
    </section>
    <section
      class="card-flat flex flex-col justify-items-center items-center mx-auto transition duration-200 ease-in-out transform hover:scale-105 hover:shadow-lg"
      @click="clickLikeBtn"
    >
      <img
        src="../../assets/icon/like@1.5x.png"
        class="object-contain mx-2"
        alt="like icon"
        v-if="!likeBtn"
      />
      <img
        src="../../assets/icon/liked@1.5x.png"
        class="object-contain mx-2"
        alt="like icon"
        v-else
      />
      <span class="w-max font-S-CoreDream-medium font-color-black-300">{{
        forum.like_cnt
      }}</span>
    </section>
    <section class="flex flex-col w-1/2 mx-auto mt-10">
      <span
        class="text-center mb-4 font-S-CoreDream-medium font-semibold text-xl border-gray-800 border-b-4 tracking-wider w-max mx-auto"
        >댓글</span
      >
      <comment-list :contentId="forumId.val"></comment-list>
      <section class="flex justify-self-end ml-auto">
        <base-button mode="outline" class="px-6 py-2 text-sm" @click="golist"
          >목록</base-button
        >
        <base-button mode="outline" class="px-6 py-2 text-sm" @click="backToTop"
          >TOP</base-button
        >
      </section>
    </section>
  </div>
</template>

<script>
import CommentList from "./CommentList.vue";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
export default {
  name: "BoardDetail",
  components: {
    CommentList,
  },
  data() {
    return {
      owner_check: localStorage.getItem("user_name"),
      forum: [
        {
          category: "",
          comment_cnt: 0,
          content: "",
          created_at: "",
          hits: 0,
          like_cnt: 0,
          pk_forum: 0,
          title: "",
          updated_at: "",
          user_name: "",
          user_no: 0,
          img_path: "",
        },
      ],
      forumId: {
        type: Number,
        val: this.$route.params.contentId,
      },
      isFollow: false,
      likeBtn: false,
    };
  },
  created() {
    this.forum_Detail();

    //좋아요

    this.likeBtn = this.$store.getters["boardlikes/likeBtn"];
    this.like_cnt = this.$store.getters["boardlikes/likeCnt"];
  },
  mounted() {
    console.log("mounted", this.forum[0].user_no);
  },
  updated() {
    console.log("update", this.forum.user_no);
    this.is_Follow();
    this.isLike();
    this.isFollow = this.$store.getters["follows/isFollow"];
  },

  methods: {
    backToTop() {
      window.scroll({
        top: 0,
        left: 0,
        behavior: "smooth",
      });
    },
    //상세페이지 이동
    forum_Detail() {
      this.axios
        .get(`${SERVER_URL}/forum/${this.forumId.val}`, {
          headers: {
            "Access-Control-Allow-Origin": "*",
            "Content-Type": "application/json; charset = utf-8",
            "Access-Control-Allow-Headers": "*",
          },
        })
        .then((result) => {
          // this.items=result;
          this.forum = result.data;

          // console.log(result.data);
          // console.log(this.forum.user_no);
          this.convert_time();
        })
        .catch((e) => {
          console.log("error:", e);
        });
    },

    //삭제
    deleteData() {
      const headers = {
        "Content-type": "application/json; charset=UTF-8",
        Accept: "*/*",
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Headers": "*",
      };

      this.axios
        .delete(
          `${SERVER_URL}/forum/delete-forum/${this.forumId.val}`,
          // JSON.stringify(params),
          { headers }
        )
        .then(() => {
          // console.log(result);
          this.$router.push({
            path: "/board/list",
          });
        })
        .catch((e) => {
          console.log("error:", e);
        });
    },

    //수정
    updateData() {
      this.$router.push({
        // path: `/board/modify/${this.forumId}`
        path: `/board/create/${this.forumId.val}`,
      });
    },

    //목록으로가기
    golist() {
      this.$router.go(-1);
    },

    //시간 포멧 수정
    convert_time() {
      var Y = String(this.forum.created_at).substring(0, 4);
      var M = String(this.forum.created_at).substring(4, 6);
      var D = String(this.forum.created_at).substring(6, 8);

      var H = String(this.forum.created_at).substring(8, 10);
      var Min = String(this.forum.created_at).substring(10, 12);
      var S = String(this.forum.created_at).substring(12, 14);

      //현재 월
      let month = new Date().getMonth() + 1; // 월
      let date = new Date().getDate(); // 날짜

      if (month < "10") {
        month = "0" + month;
      }
      if (date < "10") {
        date = "0" + date;
      }
      var answer = "";
      answer = Y + "." + M + "." + D + "  " + H + ":" + Min + ":" + S;
      this.forum.created_at = answer;
    },
    // 팔로우 여부 확인하기
    async is_Follow() {
      const userInfo = {
        target_no: this.forum.user_no,
      };
      await this.$store.dispatch("follows/isFollow", userInfo);
    },
    //follow 하기
    async follow() {
      if (localStorage.getItem("user_name") === null) {
        alert("로그인이 필요한 기능입니다.");
        return;
      }
      const mode = this.isFollow ? "following" : "x";
      const userInfo = {
        target_no: this.forum.user_no,
        mode: mode,
      };
      if (this.isFollow) {
        await this.$store.dispatch("follows/unfollow", userInfo);
      } else {
        await this.$store.dispatch("follows/follow", userInfo);
      }
    },

    //좋아용
    async isLike() {
      console.log("isLike", this.forum.pk_forum);

      const btnInfo = {
        forum_no: this.forum.pk_forum,
        like_cnt: this.forum.like_cnt,
      };
      await this.$store.dispatch("boardlikes/isClick", btnInfo);
    },
    async clickLikeBtn() {
      if (localStorage.getItem("user_name") === null) {
        alert("로그인이 필요한 기능입니다.");
        return;
      }

      // console.log("isLike",this.forum.pk_forum) // 포럼pk 잘뜸
      const btnInfo = {
        forum_no: this.forum.pk_forum,
        isclick: this.likeBtn == false ? "off" : "on",
        like_cnt: this.like_cnt,
      };
      await this.$store.dispatch("boardlikes/clickBtn", btnInfo);
    },

    //마이페이지 이동
    saveUser(pkOther, nickname) {
      localStorage.setItem("pkOther", pkOther);
      this.$router.push({
        path: `/mypageother/${nickname}`,
      });
    },
  },
  watch: {
    set_isFollow(newVal) {
      this.isFollow = newVal;
    },
    set_forum_no(newVal) {
      this.forum.user_no = newVal;
    },
    //좋아용
    setLikeBtn: function(newVal) {
      this.likeBtn = newVal;
    },
    set_like_cnt: function(newVal) {
      this.forum.like_cnt = newVal;
    },
  },
  computed: {
    imgsrc() {
      if (this.forum.img_path) return this.forum.img_path;
      else
        return "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSaaNaTC8W_ygKLZxLFWpHOerfIYQiVlsuyrw&usqp=CAU";
    },
    set_isFollow() {
      return this.$store.getters["follows/isFollow"];
    },
    set_forum_no() {
      return this.forum.user_no;
    },

    //좋아용
    set_like_cnt() {
      return this.$store.getters["boardlikes/likeCnt"];
    },
    setLikeBtn() {
      return this.$store.getters["boardlikes/likeBtn"];
    },
    like_cnt() {
      return this.forum.like_cnt;
    },
    isMe() {
      return localStorage.getItem('user_name') === this.forum.user_name ? false : true;
    },
  },
};
</script>
<style scoped>
.min-h {
  min-height: 40vh;
}
</style>
