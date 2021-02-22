<template>
  <div
    class="flex flex-col justify-center font-S-CoreDream-light mt-20 font-color-black-400 w-2/3 mx-auto"
  >
    <span
      class="font-S-CoreDream-medium card-flat px-title text-center text-xl tracking-widest"
      ><span class="font-semibold ">{{ name }}</span
      >님에게 어울리는 칵테일은</span
    >
    <section class="flex mx-auto">
      <section class="w-1/3 flex flex-col justify-items-center">
        <base-card class="flex flex-col justify-items-center transition duration-200 ease-in-out transform hover:scale-105">
          <img
            class="object-cover w-auto h-72 rounded-3xl mx-6 mt-6"
            :src="imgsrc"
            alt="Sunset in the mountains"
            @click="moveRecipe"
          />
          <span
            class="text-xl mt-6 mb-4 text-center tracking-wider font-S-CoreDream-medium font-bold"
            >{{ cocktailName }}
          </span>
        </base-card>
      </section>
      <section class="flex flex-col w-2/3">
        <article class="card-flat leading-7 tracking-wide whitespace-pre-line">
          <p class="mb-4">
            <span class="font-S-CoreDream-medium">{{ base }}</span
            >와/과 <span class="font-S-CoreDream-medium">{{ ingredient }}</span
            >을/를 좋아하고 {{ abv }} 칵테일을 원하는 {{ name }}님 입맛에 가장
            어울리는 칵테일은
            <span class="font-S-CoreDream-medium">‘{{ cocktailName }}’</span
            >입니다!
          </p>
          <p class="mb-4">{{ content }}</p>
          <p>아래는 어울리는 다른 칵테일입니다.</p>
        </article>
        <div class="flex">
          <article
            v-for="recipe in recipes.slice(1, 3)"
            :key="recipe.pk_article"
            class="card-flat w-1/2 flex flex-col justify-items-center transition duration-200 ease-in-out transform hover:scale-105"
          >
            <img
              class="object-cover w-52 h-52 rounded-2xl mx-auto mt-2"
              :src="recipe.img_path"
              alt="Sunset in the mountains"
              @click="moveSubRecipe(recipe.pk_article)"
            />
            <span
              class="text-xl mt-6 text-center tracking-wider font-S-CoreDream-medium font-bold"
              >{{ recipe.title_kor }}</span
            >
          </article>
        </div>
      </section>
    </section>
    <section
      class="flex flex-col text-center justify-items-center items-center mt-10"
    >
      <base-button class="px-10 py-2 text-center" link to="/recommendation/form"
        >다시 추천받기</base-button
      >
    </section>
  </div>
</template>

<script>
import randomName from "../../data/randomName.js";
function getRandomInt(max) {
  return Math.floor(Math.random() * Math.floor(max));
}
export default {
  data() {
    return {
      randomName: randomName.name,
      // base: "데킬라",
      // ingredient: "오렌지",
      // abv: "알딸딸한",
      // content:
      //   "칵테일의 이름은 데킬라를 마시고 난 숙취를 의미합니다. 오렌지 주스와 그레나딘 시럽이 들어가 잘 넘어가기 때문에 주의해야 합니다. 본 칵테일과는 반대로 석양을 컨셉으로 한 데킬라 선셋이라는 칵테일도 존재합니다. 또한 데킬라를 제외시키면 선라이즈라는 이름의 논 알콜 칵테일이 됩니다.",
      // recipes: [
      //   {
      //     pk_article: 3,
      //     title_kor: "데킬라 선셋",
      //     imgsrc:
      //       "https://dailyshotassets.s3-accelerate.amazonaws.com/media/cocktails/%25Y/%25m/%25d/6231068f-07e0-4441-bd13-f4598b03fd5f.jpg",
      //   },
      //   {
      //     pk_article: 4,
      //     title_kor: "데킬라 스톤 사워",
      //     imgsrc: "http://www.haveacocktail.com/images/da/7081.jpg",
      //   },
      // ],
    };
  },
  computed: {
    base() {
      return this.$store.getters["recipes/recommendationData"].base;
    },
    ingredient() {
      return this.$store.getters["recipes/recommendationData"].ingredient;
    },
    abv() {
      return this.$store.getters["recipes/recommendationData"].abv;
    },
    name() {
      return localStorage.getItem("user_name") === null
        ? `익명의 ${this.randomName[getRandomInt(this.randomName.length)]}`
        : localStorage.getItem("user_name");
    },
    imgsrc() {
      return this.recipes[0].img_path;
    },
    content() {
      return this.recipes[0].content;
    },
    cocktailName() {
      return this.recipes[0].title_kor;
    },
    recipes() {
      return this.$store.getters["recipes/recommendRecipes"];
    },
  },
  created() {
    console.log(this.recipes);
  },
  methods: {
    async moveRecipe() {
      const Info = {
        searchtxt: this.cocktailName,
      };
      var pk = await this.$store.dispatch("recipes/searchRecipe", Info);

      this.$router.push({
        path: `/recipe/detail/${pk}`,
      });
    },

    async moveSubRecipe(pk_article) {
      // const Info = {
      //   searchtxt:this.cocktailName
      // };
      // var pk=await this.$store.dispatch('recipes/searchRecipe', Info);
      // console.log(pk_article);
      this.$router.push({
        path: `/recipe/detail/${pk_article}`,
      });
    },
  },
};
</script>

<style scoped>
.card-flat {
  padding: 1.5rem;
  margin: 1rem;
}

.px-title {
  padding: 2rem 1.5rem;
}
</style>
