<template>
  <div class="flex justify-center font-S-CoreDream-light mt-20 font-color-black-400">
    <section class="w-1/3 flex flex-col justify-items-center">
      <base-card class="flex flex-col justify-items-center">
        <img
          class="object-cover w-auto img-height rounded-3xl mx-6 mt-6"
          :src="imgsrc"
          alt="cocktail image"
        />
        <span class="text-2xl mt-6 mb-8 text-center font-S-CoreDream-medium font-bold"
          >{{ title_kor }}
        </span>
      </base-card>
      <article class="flex text-center">
        <base-card
          class="flex-1 flex flex-col justify-items-center items-center transition duration-200 ease-in-out transform hover:scale-105"
          @click="clickLikeBtn"
        >
          <img
            src="../../assets/icon/liked@2x.png"
            class="mr-0 w-12 h-12 object-contain"
            alt="like icon"
            v-if="likeBtn"
          />
          <img
            src="../../assets/icon/like@2x.png"
            class="mr-0 w-12 h-12 object-contain"
            alt="like icon"
            v-else
          />
          <span class="mt-2 font-S-CoreDream-medium">{{ like_cnt }}</span>
        </base-card>
        <base-card
          class="flex-1 flex flex-col justify-items-center items-center transition duration-200 ease-in-out transform hover:scale-105"
          @click="clickBmarkBtn"
        >
          <img
            src="../../assets/icon/bookmarked@2x.png"
            class="mr-0 w-12 h-12 object-contain"
            alt="bookmark icon"
            v-if="bmarkBtn"
          />
          <img
            src="../../assets/icon/bookmark@2x.png"
            class="mr-0 w-12 h-12 object-contain"
            alt="bookmark icon"
            v-else
          />
          <span class="mt-2 font-S-CoreDream-medium">{{ bookmark_cnt }}</span>
        </base-card>
      </article>

      <article class="card-flat mx-auto flex w-11/12">
        <img
          class="object-cover w-14 h-12 mr-2 rounded-full cursor-pointer"
          :src="userInfo[0].img_path"
          alt="profile image"
          @click="saveUser(userInfo[0].pk_user, userInfo[0].user_name)"
        />
        <section class="flex flex-col w-full">
          <article class="tracking-wider flex items-center">
            <span
              ><span
                class="font-S-CoreDream-medium cursor-pointer"
                @click="saveUser(userInfo[0].pk_user, userInfo[0].user_name)"
                >{{ userInfo[0].user_name }}</span
              >님의 레시피입니다.</span
            >
          </article>
          <article class="flex items-center font-color-black-200 text-xs">
            <span class="mr-2">{{ convert_time(created_at) }}</span>
            <section class="">
              <span v-if="selectedRecipe.user_name === owner_check">
                <base-button class="text-xs px-2 py-1 ml-2" mode="nude" @click="updateRecipe"
                  >수정</base-button
                >
                <base-button class="text-xs px-2 py-1" mode="nude" @click="deleteRecipe"
                  >삭제</base-button
                >
              </span>
            </section>
            <div class="justify-self-end ml-auto" v-if="selectedRecipe.user_name !== owner_check">
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
            </div>
          </article>
        </section>
      </article>
      <base-button @click="gotoPreviousPage" id="btn" class="w-max px-10 py-1.5 text-sm font-bold">목록으로 돌아가기</base-button>
    </section>
    <section class="flex w-1/2 flex-col justify-items-center ">
      <base-card class="flex flex-col">
        <span class="mx-4 my-2 leading-relaxed">{{ content }}</span>
      </base-card>
      <article class="ml-4">
        <span
          v-for="tag in tags"
          :key="tag"
          class="w-max inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold ml-2 mb-2"
          >#{{ tag }}</span
        >
      </article>
      <article class="flex">
        <base-card class="w-1/2 flex flex-col justify-items-center">
          <span class="text-xl font-S-CoreDream-medium font-bold text-center mt-4 mb-6">재료</span>
          <ul class="mx-4">
            <li class="mb-2" v-for="alcohol in alcohols" :key="alcohol">🍸 {{ alcohol }}</li>
            <li class="mb-2" v-for="ingredient in ingredients" :key="ingredient">
              🥄 {{ ingredient }}
            </li>
          </ul>
        </base-card>
        <base-card class="w-1/2 flex flex-col justify-items-center">
          <span class="text-xl font-S-CoreDream-medium font-bold text-center mt-4 mb-6"
            >레시피</span
          >
          <ol class="mx-4">
            <li class="mb-2" v-for="(recipe, index) in recipes" :key="recipe">
              {{ index + 1 }}. {{ recipe }}
            </li>
          </ol>
        </base-card>
      </article>
    </section>
    <section class="">
      <!-- <span v-if="selectedRecipe.user_name === owner_check">
        <base-button class="text-xs px-2 py-1 ml-2" mode="nude" @click="updateRecipe"
          >수정</base-button
        >
        <base-button class="text-xs px-2 py-1" mode="nude" @click="deleteRecipe">삭제</base-button>
      </span> -->
    </section>
  </div>
</template>

<script>
export default {
  props: ['pk_article'],
  data() {
    return {
      owner_check: localStorage.getItem('user_name'),
      selectedRecipe: null,
      likeBtn: false,
      bmarkBtn: false,
      like_cnt: 0,
      bookmark_cnt: 0,
      isFollow: false,
    };
  },
  watch: {
    setLikeBtn: function(newVal) {
      this.likeBtn = newVal;
    },
    setBmarkBtn: function(newVal) {
      this.bmarkBtn = newVal;
    },
    set_like_cnt: function(newVal) {
      this.like_cnt = newVal;
    },
    set_bookmark_cnt: function(newVal) {
      this.bookmark_cnt = newVal;
    },
    set_isFollow(newVal) {
      this.isFollow = newVal;
    },
  },
  computed: {
    title_kor() {
      return this.selectedRecipe.title_kor;
    },
    imgsrc() {
      return this.selectedRecipe.img_path;
    },
    set_like_cnt() {
      return this.$store.getters['likes/likeCnt'];
    },
    set_bookmark_cnt() {
      return this.$store.getters['likes/bmarkCnt'];
    },
    created_at() {
      return this.selectedRecipe.created_at;
    },
    hits() {
      return this.selectedRecipe.hits;
    },
    content() {
      return this.selectedRecipe.content;
    },
    tags() {
      return this.selectedRecipe.tag.split('<br>');
    },
    alcohols() {
      const alcoholItem = this.selectedRecipe.alcohol.split('<br>');
      const modified = [];
      for (const item of alcoholItem) {
        const alcoholInfo = item.split('/');
        const modifiedItem = `${alcoholInfo[0]} ${alcoholInfo[1]}${alcoholInfo[2]}`;
        modified.push(modifiedItem);
      }
      return modified;
    },
    ingredients() {
      const ingredientItem = this.selectedRecipe.ingredient.split('<br>');
      const modified = [];
      for (const item of ingredientItem) {
        const ingredientInfo = item.split('/');
        const modifiedItem = `${ingredientInfo[1]} ${ingredientInfo[2]}${ingredientInfo[3]}`;
        modified.push(modifiedItem);
      }
      return modified;
    },
    recipes() {
      return this.selectedRecipe.recipe.split('<br>');
    },
    setLikeBtn() {
      return this.$store.getters['likes/likeBtn'];
    },
    setBmarkBtn() {
      return this.$store.getters['likes/bmarkBtn'];
    },
    set_isFollow() {
      return this.$store.getters['follows/isFollow'];
    },
    userInfo() {
      return this.$store.getters['users/otherMyPage'];
    },
  },
  created() {
    this.selectedRecipe = this.$store.getters['recipes/recipes'].find(
      (recipe) => recipe.pk_article.toString() === this.pk_article
    );
    this.test();
    this.loadUserInfo();
  },
  methods: {
    test() {
      this.isLike();
      this.isBmark();
      this.likeBtn = this.$store.getters['likes/likeBtn'];
      this.bmarkBtn = this.$store.getters['likes/bmarkBtn'];
      this.like_cnt = this.$store.getters['likes/likeCnt'];
      this.bookmark_cnt = this.$store.getters['likes/bmarkCnt'];
    },
    gotoPreviousPage() {
      this.$router.go(-1);
    },

    convert_time(time) {
      var Y = String(time).substring(0, 4);
      var M = String(time).substring(4, 6);
      var D = String(time).substring(6, 8);

      var H = String(time).substring(8, 10);
      var Min = String(time).substring(10, 12);
      var S = String(time).substring(12, 14);

      //현재 월
      let month = new Date().getMonth() + 1; // 월
      let date = new Date().getDate(); // 날짜

      if (month < '10') {
        month = '0' + month;
      }
      if (date < '10') {
        date = '0' + date;
      }
      var answer = '';
      answer = Y + '.' + M + '.' + D + '  ' + H + ':' + Min + ':' + S;

      return answer;
    },
    async isLike() {
      const btnInfo = {
        isLike: true,
        article_no: this.pk_article,
        like_cnt: this.selectedRecipe.like_cnt,
      };
      await this.$store.dispatch('likes/isClick', btnInfo);
    },
    async isBmark() {
      const btnInfo = {
        isLike: false,
        article_no: this.pk_article,
        bookmark_cnt: this.selectedRecipe.bookmark_cnt,
      };
      await this.$store.dispatch('likes/isClick', btnInfo);
    },
    async clickLikeBtn() {
      if (localStorage.getItem('user_name') === null) {
        alert('로그인이 필요한 기능입니다.');
        return;
      }
      const btnInfo = {
        isLike: true,
        article_no: this.pk_article,
        isclick: this.likeBtn == false ? 'off' : 'on',
        like_cnt: this.like_cnt,
      };
      await this.$store.dispatch('likes/clickBtn', btnInfo);
    },
    async clickBmarkBtn() {
      if (localStorage.getItem('user_name') === null) {
        alert('로그인이 필요한 기능입니다.');
        return;
      }
      const btnInfo = {
        isLike: false,
        article_no: this.pk_article,
        isclick: this.bmarkBtn == false ? 'off' : 'on',
        bookmark_cnt: this.bookmark_cnt,
      };
      await this.$store.dispatch('likes/clickBtn', btnInfo);
    },
    //수정
    updateRecipe() {
      this.$router.push({
        path: !this.selectedRecipe.category ? `/recipe/register/custom/${this.pk_article}` : `/recipe/register/official/${this.pk_article}`,
      });
    },
    //삭제 버튼에 들어갈 함수
    async deleteRecipe() {
      const articleInfo = {
        pk_article: this.pk_article,
      };

      const result = await this.$store.dispatch('recipes/deleteRecipe', articleInfo);
      if (result) {
        this.$router.replace('/recipe/custom');
      }
    },
    saveUser(pkOther, nickname) {
      localStorage.setItem('pkOther', pkOther);
      this.$router.push({
        path: `/mypageother/${nickname}`,
      });
    },
    // 팔로우 여부 확인하기
    async is_Follow() {
      const userInfo = {
        target_no: this.selectedRecipe.user_no,
      };
      await this.$store.dispatch('follows/isFollow', userInfo);
    },
    //follow 하기
    async follow() {
      if (localStorage.getItem('user_name') === null) {
        alert('로그인이 필요한 기능입니다.');
        return;
      }
      const mode = this.isFollow ? 'following' : 'x';
      const userInfo = {
        target_no: this.selectedRecipe.user_no,
        mode: mode,
      };
      if (this.isFollow) {
        await this.$store.dispatch('follows/unfollow', userInfo);
      } else {
        await this.$store.dispatch('follows/follow', userInfo);
      }
    },
    async loadUserInfo() {
      const userInfo = {
        target_no: this.selectedRecipe.user_no,
        mode: 'recipe',
      };
      await this.$store.dispatch('users/LoadOtherMyPage', userInfo);
    },
  },
  updated() {
    this.is_Follow();
    this.isFollow = this.$store.getters['follows/isFollow'];
  },
};
</script>

<style scoped>
#btn {
  margin: 0.25rem auto;
}

.img-height {
  height: 30rem;
}
</style>
