<template>
  <div class="flex flex-col justify-items-center mx-16 font-color-black-400">
    <span
      class="text-4xl text-center my-10 font-S-CoreDream-medium font-bold font-color-black-400"
      >커스텀 레시피</span
    >
    <section
      class="flex justify-center grid grid-cols-4 mb-6 font-S-CoreDream-light px-16"
    >
      <base-card id="card-margin" class="flex flex-col justify-items-center">
        <span
          class="font-S-CoreDream-medium text-2xl font-bold text-center py-4"
          >금주의 랭킹</span
        >
        <section
          class="transition duration-200 ease-in-out transform hover:scale-105 hover:shadow-lg py-1 my-1"
          v-for="(ranking, index) in filteredRanking.slice(0, 5)"
          :key="ranking.user_name"
          @click="saveUser(ranking.pk_user, ranking.user_name)"
        >
          <div class="flex items-center mt-3">
            <span class="text-2xl font-extrabold mx-4">{{ index + 1 }}</span>
            <img
              :src="
                ranking.img_path === ''
                  ? 'https://www.lifewire.com/thmb/wTQhx22YA7ljA0-dTNKiHp2bReI=/1142x642/smart/filters:no_upscale()/iphonex_animoji_fox-59dd137c03f4020010a60b54.gif'
                  : ranking.img_path
              "
              class="w-10 h-10 object-cover rounded-full ml-4 mr-2"
              alt="profile image"
            />
            <span class="text-base ml-2 ">{{ ranking.user_name }}</span>
            <!-- <span class="text-base font-medium ml-2">{{
              ranking.like_weekly
            }}</span> -->
          </div>
        </section>
      </base-card>
      <base-card
        id="card-margin"
        class="flex-auto inline-block flex flex-col justify-items-center col-span-3 "
      >
        <span
          class="font-S-CoreDream-medium text-2xl font-bold text-center mt-4"
          >주간 베스트</span
        >
        <section class="font-S-CoreDream-light p-3">
          <div v-if="isLoading" class="my-32">
            <base-spinner></base-spinner>
          </div>
          <div
            v-else-if="filteredArticleRanking.length > 0"
            class=" grid grid-cols-3 grid-flow-row gap-2 mx-auto"
          >
            <recipe-card-small
              v-for="articleranking in filteredArticleRanking"
              :key="articleranking.pk_article"
              :pk_article="articleranking.pk_article"
              :img_path="articleranking.img_path"
              :user_name="articleranking.user_name"
              :user_profileImage="articleranking.user_profileImage"
              :cocktailname="articleranking.title_kor"
              :tag="articleranking.tag"
              :like_cnt="articleranking.like_cnt"
              :bookmark_cnt="articleranking.bookmark_cnt"
            >
            </recipe-card-small>
          </div>
          <span
            v-else
            class="text-2xl text-center my-32 font-S-CoreDream-medium font-bold font-color-black-200"
            >등록된 레시피가 없습니다.</span
          >
        </section>
      </base-card>
    </section>
    <section class="flex justify-end mx-12">
      <base-button
        class="w-max  px-8 py-2"
        mode="important"
        link
        to="register/custom"
        >레시피 등록</base-button
      >
    </section>
    <section class="flex items-center mx-64 mb-12 font-S-CoreDream-light">
      <article class="flex w-full items-center">
        <input
          class="mx-auto text-base text-left shadow-lg appearance-none rounded-full w-5/6 pl-10 pr-20 py-4 leading-tight border-3 border-transparent hover:bg-gray-100 focus:outline-none focus:shadow-outline focus:border-gray-200"
          id="search"
          type="text"
          placeholder="레시피명, 태그명으로 검색하세요😉"
          v-model.trim="keyword"
        />
      </article>
    </section>
    <section class="flex flex-col">
      <div v-if="isLoading" class="my-32">
        <base-spinner></base-spinner>
      </div>
      <div
        v-else-if="filteredRecipes.length > 0"
        class=" grid grid-cols-4 grid-flow-row gap-4 mx-auto"
      >
        <recipe-card
          v-for="cocktail in filteredRecipes"
          :key="cocktail.pk_article"
          :pk_article="cocktail.pk_article"
          :img_path="cocktail.img_path"
          :user_name="cocktail.user_name"
          :user_profileImage="cocktail.user_profileImage"
          :cocktailname="cocktail.title_kor"
          :tag="cocktail.tag"
          :like_cnt="cocktail.like_cnt"
          :bookmark_cnt="cocktail.bookmark_cnt"
        >
        </recipe-card>
      </div>
      <span
        v-else
        class="text-2xl text-center my-32 font-S-CoreDream-medium font-bold font-color-black-200"
        >등록된 레시피가 없습니다.</span
      >
    </section>
  </div>
</template>

<script>
import RecipeCard from "../../components/recipes/RecipeCard.vue";
import RecipeCardSmall from "../../components/recipes/RecipeCardSmall.vue";
export default {
  components: {
    RecipeCard,
    RecipeCardSmall,
  },
  data() {
    return {
      isLoading: false,
      error: null,
      keyword: "",
    };
  },
  watch: {
    category(newVal) {
      this.keyword = newVal;
    },
  },
  computed: {
    filteredRecipes() {let recipes = this.$store.getters["recipes/recipes"]; //모듈/getters
      if (this.keyword !== "") {
        recipes = recipes.filter((recipeItem) => {
          if (recipeItem.title_kor.includes(this.keyword)) return true;
          if (recipeItem.tag.includes(this.keyword)) return true;
          return false;
        });
      }

      console.log(recipes);
      return recipes.filter((recipeItem) => {
        if (recipeItem.category === false) return true;
      });
    },
    hasRecipes() {
      return !this.isLoading && this.$store.getters["recipes/hasRecipes"];
    },
    filteredRanking() {
      const ranking = this.$store.getters["recipes/Ranking"]; //모듈/getters
      console.log(ranking);
      return ranking;
    },
    hasRanking() {
      return !this.isLoading && this.$store.getters["recipes/Ranking"];
    },
    filteredArticleRanking() {
      const articleranking = this.$store.getters["recipes/ArticleRanking"]; //모듈/getters
      return articleranking;
    },
    hasArticleRanking() {
      return !this.isLoading && this.$store.getters["recipes/ArticleRanking"];
    },
  },
  created() {
    this.loadRecipes();
    this.LoadRanking();
    this.LoadArticleRanking();
  },
  methods: {
    async loadRecipes(refresh = true) {
      this.isLoading = true;
      try {
        await this.$store.dispatch("recipes/loadRecipes", {
          forceRefresh: refresh,
        });
      } catch (error) {
        this.error =
          error.message || "레시피를 불러오는데 문제가 발생했습니다.";
      }
      this.isLoading = false;
    },
    async LoadRanking(refresh = true) {
      this.isLoading = true;
      try {
        await this.$store.dispatch("recipes/LoadRanking", {
          forceRefresh: refresh,
        });
      } catch (error) {
        this.error = error.message || "랭킹을 불러오는데 문제가 발생했습니다.";
      }
      this.isLoading = false;
    },
    async LoadArticleRanking(refresh = true) {
      this.isLoading = true;
      try {
        await this.$store.dispatch("recipes/LoadArticleRanking", {
          forceRefresh: refresh,
        });
      } catch (error) {
        this.error =
          error.message || "레시피 랭킹을 불러오는데 문제가 발생했습니다.";
      }
      this.isLoading = false;
    },
    handleError() {
      this.error = null;
    },
    saveUser(pkOther, nickname) {
      localStorage.setItem("pkOther", pkOther);
      this.$router.push({
        path: `/mypageother/${nickname}`,
      });
    },
  },
};
</script>

<style scoped>
#card-margin {
  margin: 0.75rem;
}
p {
  margin: 0;
  font-style: normal;
  font-weight: bold;
}

.title {
  color: #23232f;
}
</style>
