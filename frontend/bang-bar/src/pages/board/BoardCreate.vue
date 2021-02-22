<template>
  <div
    class="font-color-black-400 flex flex-col justify-items-center font-S-CoreDream-light"
  >
    <span
      v-if="!updateMode"
      class="text-center my-10 text-4xl font-S-CoreDream-medium font-bold"
      >새 글 작성</span
    >
    <span
      v-else
      class="text-center my-10 text-4xl font-S-CoreDream-medium font-bold"
      >글수정하기</span
    >
    <section class="mx-auto">
      <select
        class="appearance-none w-auto m-3 text-base bg-white hover:bg-gray-100 px-10 py-2 rounded-full leading-tight border-4 border-transparent focus:outline-none focus:shadow-outline"
        v-model="category"
      >
        <!-- 나중에 수정할 것  -->
        <!-- <option>공지사항</option> -->
        <option>후기</option>
        <option>질문</option>
      </select>
    </section>
    <section class="mx-auto w-1/2">
      <textarea
        class="card-flat w-full text-center text-lg font-S-CoreDream-medium"
        rows="1"
        placeholder="제목을 입력해주세요."
        v-model="title"
      >
      </textarea>
    </section>
    <section class="mx-auto w-1/2">
      <textarea
        class="card-flat w-full min-h"
        v-model="content"
        placeholder="내용을 입력해주세요."
      >
      </textarea>
    </section>
    <!-- <button @click="test()">클릭</button> -->
    <section class="mx-auto">
      <base-button class="px-6 py-2 text-sm" @click="updateMode ? updateContent() : uploadContent()"
        >저장</base-button
      >
      <base-button mode="outline" class="px-6 py-2 text-sm" @click="cancle">취소</base-button>
    </section>
  </div>
</template>

<script>
// import data from '@/data'

// var date=new Date();
// var forum2=this.forum;
const user_pk = localStorage.getItem("pk_user");
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: "BoardCreate",
  data() {
    return {
      forum: [],
      category: "후기",
      title: "",
      context: "",
      user_id: user_pk,
      created_at: this.time_cal(),
      updated_at: "",
      updateObject: null,
      updateMode: this.$route.params.contentId > 0 ? true : false,
      pk_forum: this.$route.params.contentId,
    };
  },
  created() {
    if (this.$route.params.contentId > 0) {
      this.take_data();
      this.category = this.forum.category;
      this.title = this.forum.title;
      this.content = this.forum.content;
    }
  },

  methods: {
    test() {
      console.log(this.user_id);
    },

    time_cal() {
      let today = new Date();

      let year = today.getFullYear(); // 년도
      let month = today.getMonth() + 1; // 월
      let date = today.getDate(); // 날짜

      let hour = today.getHours();
      let min = today.getMinutes();
      let sec = today.getSeconds();

      if (date < "10") {
        date = "0" + date;
      }

      if (month < "10") {
        month = "0" + month;
      }

      if (hour < "10") {
        hour = "0" + hour;
      }

      if (min < "10") {
        min = "0" + min;
      }
      if (sec < "10") {
        sec = "0" + sec;
      }

      this.created_at =
        year + "" + month + "" + date + "" + hour + "" + min + "" + sec;
    },

    take_data() {
      this.axios
        .get(`${SERVER_URL}/forum/` + this.$route.params.contentId, {
          headers: {
            "Access-Control-Allow-Origin": "*",
            "Content-Type": "application/json; charset = utf-8",
            "Access-Control-Allow-Headers": "*",
          },
        })
        .then((result) => {
          // console.log(result)

          this.category = result.data.category;
          this.title = result.data.title;
          this.content = result.data.content;
          console.log(this.forum);
        })
        .catch((e) => {
          console.log("error:", e);
        });
    },

    uploadContent() {
      // 저장

      // let contentItems = data.Content.sort((a, b) => { return b.content_id - a.content_id });
      // const content_id = contentItems[0].content_id + 1;  // 마지막 데이터의 id + 1
      // alert(this.created_at);
      this.time_cal();

      let params = {
        title: this.title,
        category: this.category,
        content: this.content,
        created_at: this.created_at,
        updated_at: this.created_at,
        user_no: this.user_id,
      };
      const headers = {
        "Content-type": "application/json; charset=UTF-8",
        Accept: "*/*",
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Headers": "*",
      };

      this.axios
        .post(`${SERVER_URL}/forum/create-forum`, JSON.stringify(params), {
          headers,
        })
        .then((result) => {
          console.log(result);
          this.$router.push({
            path: "/board/list",
          });
        })
        .catch((e) => {
          console.log("error:", e);
        });

      // this.$router.push({
      //   path: '/board/list'
      // })
    },

    updateContent() {
      // 수정
      this.time_cal();

      let params = {
        title: this.title,
        category: this.category,
        content: this.content,
        updated_at: this.created_at,
        pk_forum: this.pk_forum,
      };
      const headers = {
        "Content-type": "application/json; charset=UTF-8",
        Accept: "*/*",
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Headers": "*",
      };

      this.axios
        .put(`${SERVER_URL}/forum/update-forum`, JSON.stringify(params), {
          headers,
        })
        .then((result) => {
          console.log(result);
          this.$router.push({
            path: "/board/list",
          });
        })
        .catch((e) => {
          console.log("error:", e);
        });
    },
    cancle() {
      this.$router.push({
        path: "/board/list",
      });
    },
  },
};
</script>

<style scoped>
.card-flat {
  padding: 1rem 1.25rem;
  margin: 0.6rem 0;
}

.min-h {
  min-height: 50vh;
}

</style>
