<template>
  <div class="flex flex-col justify-items-center mx-16 font-color-black-400">
    <span
      class="text-4xl text-center my-10 font-S-CoreDream-medium font-bold font-color-black-400"
      >오피셜 레시피</span
    >
    <section class="flex justify-end mx-12">
      <!-- <base-button
        class="w-max px-8 py-2"
        mode="important"
        link
        to="register/official"
        >레시피 등록</base-button
      > -->
      <article class="py-6"></article>
    </section>
    <section
      id="search-bar"
      class="flex justify-items-center items-center mx-64 mb-12"
    >
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
        class="grid grid-cols-4 grid-flow-row gap-4 mx-auto"
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
export default {
  components: {
    RecipeCard,
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
    filteredRecipes() {
      let recipes = this.$store.getters["recipes/recipes"]; //모듈/getters
      if (this.keyword !== "") {
        recipes = recipes.filter((recipeItem) => {
          if (recipeItem.title_kor.includes(this.keyword)) return true;
          if (recipeItem.tag.includes(this.keyword)) return true;
          return false;
        });
      }

      console.log(recipes);
      return recipes.filter((recipeItem) => {
        if (recipeItem.category === true) return true;
      });
    },
    hasRecipes() {
      return !this.isLoading && this.$store.getters["recipes/hasRecipes"];
    },
  },
  created() {
    this.loadRecipes();
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
    handleError() {
      this.error = null;
    },
  },
};
</script>

<style scoped>
.title {
  font-size: 42px;
  line-height: 93px;
}

.search-icon {
  position: relative;
  left: -7.5rem;
  border-radius: 100%;
  transition: 0.3s ease-out;
}

.search-icon:hover {
  background-color: #c6c6c6;
  border-radius: 100%;
}
</style>
