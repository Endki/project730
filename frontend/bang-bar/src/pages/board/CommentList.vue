<template>
  <div>
    <div :key="item.comment_id" v-for="item in comments" class="flex">
      <section
        class="card-flat flex flex-col flex-initial h-full w-full"
        v-if="item.pk_fcomment !== this.return_num || modify_flag"
      >
        <article class="flex items-center mb-1">
          <img
            :src="item.img_path"
            class="w-8 h-8 rounded-full mr-2 object-cover"
            alt="profile image"
          />
          <span class="font-S-CoreDream-medium">{{ item.user_name }} </span>
        </article>
        <span class="flex items-center font-color-black-200 text-xs mb-2">{{
          convert_time(item.created_at)
        }}</span>
        <section class="">
          <div class="tracking-wide whitespace-pre-line mb-2">
            {{ item.content }}
          </div>
          <div
            class="w-max px-3 py-1 text-sm justify-self-end ml-auto"
            v-if="item.user_name === this.loginName"
          >
            <base-button
              class="px-2.5 py-1 text-xs"
              mode="nude"
              variant="info"
              @click="check_modifyComment(item.pk_fcomment, item.content)"
              >수정</base-button
            >
            <base-button
              class="px-2.5 py-1 text-xs"
              mode="nude"
              variant="info"
              @click="deleteComment(item.pk_fcomment)"
              >삭제</base-button
            >
          </div>
        </section>
      </section>

      <section
        class="card-flat flex flex-col flex-initial h-full w-full"
        v-else
      >
        <span class="font-S-CoreDream-medium mb-2">{{ item.user_name }}</span>
        <textarea
          ref="commentTA"
          class="w-full mb-2"
          v-model="content"
          @keypress.enter="modifyComment()"
        >
        </textarea>
        <div class="w-max px-3 py-1 text-sm justify-self-end ml-auto">
          <button
            variant="info"
            @click="modifyComment(item.pk_fcomment, this.content)"
          >
            수정
          </button>
          &nbsp;&nbsp;&nbsp;
          <button
            variant="info"
            @click="(this.return_num = -1), (modify_flag = true)"
          >
            취소
          </button>
        </div>
      </section>
    </div>

    <comment-create :forum_id="forum_id" v-on:addComment="addComment" />
  </div>
</template>

<script>
import CommentCreate from "./CommentCreate";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: "CommentList",
  components: {
    CommentCreate,
  },
  props: ["pk_forum"],
  data() {
    return {
      comments: [],
      pk_num: "",
      loginName: localStorage.getItem("user_name"),
      myComment: false,
      modify_flag: true,
      return_num: "",
      content: "",
      forum_id: "",
      img_path: "",
    };
  },
  methods: {
    getList_Comment() {
      //주소입력을 위함
      var link = document.location.href.split("/");
      var answer = link[5]; // = 74
      this.axios
        .get(`${SERVER_URL}/forum/comment/keyword/` + answer, {
          headers: {
            "Content-Type": "application/json; charset=utf-8",
            Accept: "*/*",
            "Access-Control-Allow-Origin": "*",
            "Access-Control-Allow-Headers": "*",
          },
        })
        .then((result) => {
          this.comments = result.data;
          console.log(this.comments);
          //시간순 정렬
          this.comments.sort(function(a, b) {
            return a.created_at - b.created_at;
          });
        })
        .catch((e) => {
          console.log("error:", e);
        });
    },

    addComment() {
      this.getList_Comment();
    },

    //시간 포멧 변경
    convert_time(create_at) {
      var Y = String(create_at).substring(0, 4);
      var M = String(create_at).substring(4, 6);
      var D = String(create_at).substring(6, 8);

      var H = String(create_at).substring(8, 10);
      var Min = String(create_at).substring(10, 12);
      var S = String(create_at).substring(12, 14);

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
      return answer;
    },
    deleteComment(pk_fcomment) {
      var pk = parseInt(pk_fcomment);

      const headers = {
        "Content-type": "application/json; charset=UTF-8",
        Accept: "*/*",
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Headers": "*",
      };

      this.axios
        .delete(`${SERVER_URL}/forum/comment/${pk}`, { headers })
        .then((result) => {
          console.log(result);
          this.getList_Comment();
        })
        .catch((e) => {
          console.log("error:", e);
        });
    },
    check_modifyComment(pk_fcomment, content) {
      this.modify_flag = false;
      this.return_num = pk_fcomment;
      this.pk_num = pk_fcomment;
      this.content = content;
      // this.content=this.
      this.$nextTick(() => {
        this.$refs.commentTA.focus();
      });
    },
    modifyComment(pk_fcomment, content) {
      // 수정
      let params = {
        pk_fcomment: pk_fcomment,
        content: content,
      };
      const headers = {
        "Content-type": "application/json; charset=UTF-8",
        Accept: "*/*",
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Headers": "*",
      };

      this.axios
        .put(
          `${SERVER_URL}/forum/comment/update-comment`,
          JSON.stringify(params),
          {
            headers,
          }
        )
        .then((result) => {
          console.log(result);
          this.return_num = this.pk_num;
          this.modify_flag = true;
          this.getList_Comment();
        })
        .catch((e) => {
          console.log("error:", e);
        });
    },
  },

  created() {
    this.getList_Comment();
  },
};
</script>

<style scoped></style>
