<template>
  <div class="font-color-black-400 font-S-CoreDream-light flex flex-col justify-items-center">
    <section class="mt-32 mb-24 flex flex-col tracking-widest leading-relaxed text-xl mx-auto">
      <span>당신의 칵테일을 완성하세요</span>
      <span>Build Your Cocktail Taste</span>
    </section>
    <form @submit.prevent="submitForm">
      <section class="flex flex-col justify-items-center tracking-wider mx-auto mb-20">
        <span class="font-S-CoreDream-medium font-semibold text-xl mx-auto mb-8">베이스</span>
        <article class="flex mx-auto">
          <label
            v-for="(baseItem, index) in bases"
            :key="baseItem.name"
            class="flex flex-col justify-items-center"
          >
            <input
              type="radio"
              name="base"
              :value="index"
              v-model="base.val"
              @change="baseInfo"
            />
            <div
              class="w-24 h-24 rounded-full flex justify-items-center items-center"
            >
              <img
                :src="baseItem.imgsrc"
                alt="icon"
                class="mx-auto w-16 h-16"
              />
            </div>
            <span class="mt-2 mx-auto">{{ baseItem.name }}</span>
          </label>
        </article>
        <article v-if="base.idx >= 0" class="flex flex-col justify-items-center items-center mt-4">
          <base-card class="w-1/2 flex">
            <span class="mx-4 my-2">{{ baseAlcoholes[base.idx].content }}</span>
          </base-card>
        </article>
      </section>
      <section class="flex flex-col justify-items-center tracking-wider mx-auto mb-20">
        <span class="font-S-CoreDream-medium font-semibold text-xl mx-auto mb-8">재료</span>
        <article class="flex mx-auto">
          <label
            v-for="(ingredientItem, index) in ingredients.slice(0, 4)"
            :key="ingredientItem.name"
            class="flex flex-col justify-items-center"
          >
            <input
              type="radio"
              name="ingredient"
              :value="index"
              v-model="ingredient.val"
              @change="ingredientInfo"
            />
            <div class="w-24 h-24 rounded-full flex justify-items-center items-center">
              <img :src="ingredientItem.imgsrc" alt="icon" class="mx-auto w-16 h-16" />
            </div>
            <span class="mt-2 mx-auto">{{ ingredientItem.name }}</span>
          </label>
        </article>
        <article class="flex mx-auto">
          <label
            v-for="(ingredientItem, index) in ingredients.slice(4, ingredient.length)"
            :key="ingredientItem.name"
            class="flex flex-col justify-items-center"
          >
            <input
              type="radio"
              name="ingredient"
              :value="index + 4"
              v-model="ingredient.val"
              @change="ingredientInfo"
            />
            <div class="w-24 h-24 rounded-full flex justify-items-center items-center">
              <img :src="ingredientItem.imgsrc" alt="icon" class="mx-auto w-16 h-16" />
            </div>
            <span class="mt-2 mx-auto">{{ ingredientItem.name }}</span>
          </label>
        </article>
      </section>
      <section class="flex flex-col justify-items-center tracking-wider mx-auto mb-20">
        <span class="font-S-CoreDream-medium font-semibold text-xl mx-auto mb-8">도수</span>
        <article class="flex mx-auto">
          <label
            v-for="(abvItem, index) in abvs"
            :key="abvItem.name"
            class="flex flex-col justify-items-center"
          >
            <input
              type="radio"
              name="abv"
              :value="index"
              v-model="abv.val"
              @change="abvInfo"
            />
            <div class="w-24 h-24 rounded-full flex justify-items-center items-center">
              <img :src="abvItem.imgsrc" alt="icon" class="mx-auto w-16 h-16" />
            </div>
            <span class="mt-2 mx-auto">{{ abvItem.name }}</span>
          </label>
        </article>
        <article v-if="abv.idx >= 0" class="flex flex-col justify-items-center items-center mt-4">
          <base-card class="w-1/2 card-margin flex">
            <span class="mx-4 my-2">{{ abvs[abv.idx].content }}</span>
          </base-card>
        </article>
      </section>
      <section class="flex flex-col text-center justify-items-center items-center">
        <base-button mode="important" class="w-max px-4 py-2">결과 확인하기</base-button>
      </section>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      bases: [
         { name:"데킬라", imgsrc: require("../../assets/icon/tequila.png") },
         { name:"진", imgsrc: require("../../assets/icon/gin.png") },
         { name:"럼", imgsrc: require("../../assets/icon/rum.png") },
         { name:"보드카", imgsrc: require("../../assets/icon/vodka.png") },
         { name:"브랜디", imgsrc: require("../../assets/icon/brandy.png") },
         { name:"리큐르", imgsrc: require("../../assets/icon/liquor.png") },
          ],
      ingredients: [
        { name: '레몬', imgsrc: require('../../assets/icon/lemon.png') },
        { name: '라임', imgsrc: require('../../assets/icon/lime.png') },
        { name: '오렌지', imgsrc: require('../../assets/icon/orange.png') },
        { name: '자몽', imgsrc: require('../../assets/icon/grapefruit.png') },
        { name: '체리', imgsrc: require('../../assets/icon/cherry.png') },
        { name: '딸기', imgsrc: require('../../assets/icon/strawberry.png') },
        { name: '사과', imgsrc: require('../../assets/icon/apple.png') },
        {
          name: '파인애플',
          imgsrc: require('../../assets/icon/pineapple.png'),
        },
      ],
      abvs: [
        {
          name: '약해요',
          val: '약한',
          imgsrc: require('../../assets/icon/abv_weak.png'),
          content: '남들이 알아주는 알쓰/술찌라면, 이 도수가 적당할 거에요.',
        },
        {
          name: '적당해요',
          val: '적당한',
          imgsrc: require('../../assets/icon/abv_moderate.png'),
          content: '와인에서 소주 사이의 도수에요. 약간의 취기를 느낄 수 있어요.',
        },
        {
          name: '강해요',
          val: '강한',
          imgsrc: require('../../assets/icon/abv_strong.png'),
          content:
            '마시고 죽자! 싶은 날, 내일을 걱정하지 않아도 되는 날! 과감하게 이 도수를 골라보아요.',
        },
      ],
      base: {
        idx: -1,
        val: "",
      },
      ingredient: {
        idx: -1,
        val: "",
      },
      abv: {
        idx: -1,
        val: "",
      },
    };
  },
  computed: {
    baseAlcoholes() {
      return this.$store.getters['recommendationes/baseType'];
    },
  },
  created() {
    this.loadBaseAlcoholes();
  },
  methods: {
    async loadBaseAlcoholes() {
      await this.$store.dispatch('recommendationes/loadBaseAlcohol');
    },
    baseInfo(input) {
      this.base.idx = input.target.value;
      // console.log(input.target.value);
    },
    ingredientInfo(input) {
      this.ingredient.idx = input.target.value;
      // console.log(input.target.value);
    },
    abvInfo(input) {
      this.abv.idx = input.target.value;
      // console.log(input.target.value);
    },
    async submitForm() {
      const formData = {
        base: this.bases[this.base.val].name,
        ingredient: this.ingredients[this.ingredient.val].name,
        abv: this.abvs[this.abv.idx].val,
      };
      console.log(formData);
      await this.$store.dispatch('recipes/recommendRecipe', formData);
      this.$router.replace('/recommendation/result');
    },
  },
};
</script>

<style scoped>
.card-margin {
  margin: 2rem auto;
}
/* HIDE RADIO */
[type='radio'] {
  position: absolute;
  opacity: 0;
  width: 0;
  height: 0;
}

/* default STYLES */
[type='radio'] + div {
  cursor: pointer;
  background: #c4c4c4;
}
[type='radio'] + div > img {
  cursor: pointer;
  opacity: 0.4;
}

/* CHECKED STYLES */
[type='radio']:checked + div {
  background: #56e6a5;
}

[type='radio']:checked + div > img {
  opacity: 1;
}
</style>
