<template>
  <div
    class="flex flex-col font-S-CoreDream-light justify-items-center mx-16 font-color-black-400"
  >
    <span
      class="text-4xl text-center my-10 font-S-CoreDream-medium font-bold font-color-black-400"
      >검색</span
    >
    <section id="search-bar" class="flex items-center px-64 mb-12">
      <div class="inline-block relative w-max">
        <select
          v-model="category"
          class="block appearance-none w-full text-base bg-white hover:bg-gray-100 px-8 py-3 rounded-full shadow-lg leading-tight border-3 border-transparent focus:outline-none focus:shadow-outline"
        >
          <option value="all">통합</option>
          <option value="official">오피셜</option>
          <option value="custom">커스텀</option>
        </select>
        <div
          class="pointer-events-none absolute inset-y-0 right-0 flex items-center px-2 text-gray-700"
        >
          <svg
            class="fill-current h-4 w-4"
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 20 20"
          >
            <path
              d="M9.293 12.95l.707.707L15.657 8l-1.414-1.414L10 10.828 5.757 6.586 4.343 8z"
            />
          </svg>
        </div>
      </div>
      <div class="ml-4 flex-auto inline-block">
        <input
          class="text-base text-left shadow-lg appearance-none rounded-full w-full px-8 py-3 leading-tight border-3 border-transparent hover:bg-gray-100 focus:outline-none focus:shadow-outline focus:border-gray-200"
          id="search"
          type="text"
          placeholder="레시피명, 태그명으로 검색하세요😉"
          v-model="searchKeyword"
        />
      </div>
    </section>
    <section class="flex flex-col">
      <div v-if="isLoading" class="my-32">
        <base-spinner></base-spinner>
      </div>
      <div
        v-else-if="searchCondition"
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
        >검색된 레시피가 없습니다.</span
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
      searchKeyword: "",
      category: "",
    };
  },
  watch: {
    category(newVal) {
      this.category = newVal;
    }
  },
  computed: {
    searchCondition() {
      return (
        this.filteredRecipes.length > 0 && this.searchKeyword.trim().length > 0
      );
    },
    filteredRecipes() {
      let recipes = this.$store.getters["recipes/recipes"];
      let category = true;
      if (this.category === "custom") category = false;
      // console.log(this.searchKeyword);
      // console.log(this.category);
      if (this.category !== "all") {
        recipes = recipes.filter((recipe) => {
          if (recipe.category === category) return true;
          return false;
        });
      }
      return recipes.filter((recipe) => {
        // console.log(recipe.title_kor.includes(this.searchKeyword));
        if (recipe.title_kor.includes(this.searchKeyword)) return true;
        // if (
        //   recipe.title_eng
        //     .toLowerCase()
        //     .includes(this.searchKeyword.toLowerCase())
        // )
        //   return true;
        if (recipe.tag.includes(this.searchKeyword)) return true;
        return false;
      });
    },
  },
  created() {
    this.loadRecipes();
    this.loadKeyword();
  },
  methods: {
    loadKeyword() {
      this.searchKeyword = this.$store.getters["recipes/keyword"];
      this.$store.dispatch("recipes/setSearchKeyword", null);
    },
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
.card-corner {
  border-radius: 30px;
}
</style>
