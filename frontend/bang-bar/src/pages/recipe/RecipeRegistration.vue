<template>
  <div class="font-S-CoreDream-light flex flex-col justify-items-center mx-16">
    <span
      v-if="!updateMode"
      class="text-3xl text-center my-10 font-S-CoreDream-medium font-bold border-gray-800 border-b-4 w-max mx-auto pb-1 px-1"
      >{{ getCategoryTitle }} 레시피 등록</span
    >
    <span
      v-else
      class="text-3xl text-center my-10 font-S-CoreDream-medium font-bold border-gray-800 border-b-4 w-max mx-auto pb-1 px-1"
      >{{ getCategoryTitle }} 레시피 수정</span
    >
    <base-card class="w-2/3" id="card-margin">
      <form
        class="text-lg flex flex-col justify-items-start mx-4"
        id="form-recipe"
        @submit.prevent=""
      >
        <div
          class="form-control flex flex-col items-center"
          :class="{ invalid: !img_path.isValid }"
        >
          <img
            class="w-64 h-80 mr-0 rounded-3xl object-cover"
            :src="img_path.val"
            alt="cocktail image"
          />
          <input
            class="w-2/3 text-sm mt-5"
            type="text"
            id="imgsrc"
            placeholder="이미지 주소 입력"
            v-model.trim="imgsrc"
          />
          <base-button
            class="mt-4 px-4 py-1.5 text-base"
            @click="showImgRegDialog"
            >사진 등록</base-button
          >
          <p v-if="!img_path.isValid">등록된 사진이 없습니다.</p>
        </div>
        <div class="form-control" :class="{ invalid: !title_kor.isValid }">
          <section class="flex">
          <label for="title_kor">칵테일 이름</label>
          <input
            class="w-1/3"
            type="text"
            id="title_kor"
            placeholder="칵테일 이름을 정해주세요"
            v-model.trim="title_kor.val"
            @blur="clearValidity('title_kor')"
          />
          </section>
          <p v-if="!title_kor.isValid">칵테일 이름을 적어주세요.</p>
        </div>
        <div class="form-control" :class="{ invalid: !title_eng.isValid }">
          <section class="flex">
          <label class="font-color-black-200" for="title_eng.val">칵테일 이름 (eng)</label>
          <input
            class="w-1/3"
            type="text"
            id="title_eng"
            placeholder="칵테일 영문명을 적어주세요"
            v-model.trim="title_eng.val"
            @blur="clearValidity('title_eng')"
          />
          </section>
          <p v-if="!title_eng.isValid">칵테일 영문명을 적어주세요.</p>
        </div>
        <div class="form-control" :class="{ invalid: !abv.isValid }">
          <section class="flex">
          <label for="abv.val">칵테일 도수 (ABV)</label>
          <input
            class="w-1/6"
            type="number"
            min="0"
            max="100"
            id="abv"
            placeholder="22"
            v-model.number="abv.val"
            @blur="clearValidity('abv')"
          />
          </section>
          <p v-if="!abv.isValid">칵테일 도수를 적어주세요.</p>
        </div>
        <div
          class="form-control items-center"
          :class="{ invalid: !content.isValid }"
        >
        <section class="flex">
          <label for="content">칵테일 소개</label>
          <textarea
            class="w-2/3"
            id="content"
            rows="3"
            placeholder="어떤 칵테일인지 소개 해주세요"
            v-model.trim="content.val"
            @blur="clearValidity('content')"
          ></textarea>
        </section>
          <p v-if="!content.isValid">칵테일 소개는 비어있으면 안됩니다.</p>
        </div>
        <div class="form-control" :class="{ invalid: !cupinfo.isValid }">
          <section class="flex">
          <label for="cupinfo">컵 정보 추가하기</label>
          <select
            class="w-4/12"
            name="cupinfo"
            id="cupinfo"
            v-model="cupinfo.val"
            @blur="clearValidity('cupinfo')"
          >
            <option v-for="cup in cups" :key="cup.index" :value="cup.index">{{
              cup.val
            }}</option>
          </select>
          </section>
          <p v-if="!cupinfo.isValid">컵을 선택해주세요.</p>
        </div>
        <div class="form-control" :class="{ invalid: !ingredients.isValid }">
          <section class="flex">
          <label for="ingredients">재료 추가하기</label>
          <select class="w-28 mr-2" name="type" id="type" v-model="type">
            <option value="">분류</option>
            <option value="주류">주류</option>
            <option value="재료">재료</option>
            <option value="가니쉬">가니쉬</option>
          </select>
          <input
            class="w-3/12 mr-2"
            type="text"
            id="ingredient"
            placeholder="보드카"
            v-model.trim="ingredient"
          />
          <input
            class="w-28 mr-2"
            type="number"
            min="0"
            max="500"
            id="quantity"
            placeholder="30"
            v-model.number="quantity"
          />
          <input
            class="w-28"
            type="text"
            id="unit"
            placeholder="ml"
            v-model.trim="unit"
            @keyup.enter="addIngredient"
          />
          <base-button class="px-4 py-1.5 text-base ml-4" @click="addIngredient"
            >추가하기</base-button
          >
          </section>
          <ul class="ml-48 mt-2">
            <li v-for="(alcoholItem, index) in alcoholTemp" :key="alcoholItem">
              <span class="w-max inline-block bg-gray-200 rounded-full px-3 py-1 text-base font-semibold mr-2 mb-2">🍸 {{ alcoholItem }}
              <span
                class="ml-2 text-grey-400 cursor-pointer hover:text-red-400"
                @click="removeAlcohol(index)"
                >x</span
              ></span>
            </li>
          </ul>
          <ul class="ml-48">
            <li
              v-for="(ingredientItem, index) in ingredientTemp"
              :key="ingredientItem"
            >
              <span class="w-max inline-block bg-gray-200 rounded-full px-3 py-1 text-base font-semibold mr-2 mb-2">🥄 {{ ingredientItem }}
              <span
                class="ml-2 text-grey-400 cursor-pointer hover:text-red-400"
                @click="removeIngredient(index)"
                >x</span
              ></span>
            </li>
          </ul>
          <p v-if="!ingredients.isValid || !alcohols.isValid">
            최소 한 가지의 주류, 재료를 추가해주세요.
          </p>
        </div>
        <div class="form-control" :class="{ invalid: !recipes.isValid }">
          <section class="flex">
          <label for="recipes">레시피 추가하기</label>
          <input
            class="w-1/3 text-left"
            type="text"
            id="recipe"
            placeholder="레시피를 입력하세요"
            v-model.trim="recipe"
            @blur="clearValidity('recipes')"
            @keyup.enter="addRecipe"
          />
          <base-button class="px-4 py-1.5 text-base ml-4" @click="addRecipe"
            >추가하기</base-button
          >
          </section>
          <ul class="ml-48 mt-2 text-base">
            <li v-for="(recipeItem, index) in recipes.val" :key="recipeItem">
              <span class="w-max inline-block bg-gray-200 rounded-full px-3 py-1 text-base font-semibold mr-2 mb-2">{{ index + 1 }}. {{ recipeItem }}
              <span
                class="ml-2 text-grey-400 cursor-pointer hover:text-red-400"
                @click="removeRecipe(index)"
                >x</span
              ></span>
            </li>
          </ul>
          <p v-if="!recipes.isValid">최소 한 가지 순서를 추가해주세요.</p>
        </div>
        <div class="form-control" :class="{ invalid: !tags.isValid }">
          <section class="flex">
          <label for="tag">태그</label>
          <input
            class="w-3/12"
            type="text"
            id="tag"
            v-model.trim="tag"
            @blur="clearValidity('tags')"
            @keyup.enter="addTag"
          />
          <base-button class="px-4 py-1.5 text-base ml-4" @click="addTag"
            >추가하기</base-button
          >
          </section>
          <section class="mt-2 ml-48">
            <span class="w-max inline-block bg-gray-200 rounded-full px-3 py-1 text-base font-semibold mr-2 mb-2" v-for="(tag, index) in tags.val" :key="tag">
              {{ tag }}
              <span
                class="ml-2 text-grey-400 cursor-pointer hover:text-red-400"
                @click="removeTag(index)"
                >x</span
              >
            </span>
          </section>
          <p v-if="!tags.isValid">최소 하나의 태그를 추가해주세요.</p>
        </div>
        <section
          class="flex flex-col text-center justify-items-center items-center mt-8 mb-4"
        >
          <span class="font-red mb-4 font-S-CoreDream-medium" v-if="!formIsValid">
            비어있는 칸이 있습니다. 채운 후 다시 시도해주세요.
          </span>
          <base-button
            mode="important"
            class="w-max px-8 py-2.5 text-base"
            @click="updateMode ? updateForm() : submitForm()"
            >레시피 등록하기</base-button
          >
        </section>
      </form>
    </base-card>
  </div>
</template>

<script>
export default {
  props: {
    category: {
      type: String,
      required: true,
      default: "official",
    },
  },
  data() {
    return {
      updateMode: this.$route.params.pk_article > 0 ? true : false,
      formIsValid: true,
      isLoading: false,
      error: null,
      cups: [
        { index: 24, name: "none", val: "상관 없음" },
        { index: 1, name: "shot", val: "샷 글라스" },
        { index: 2, name: "highball", val: "하이볼 글라스" },
        { index: 3, name: "sling", val: "슬링 글라스" },
        { index: 4, name: "oldfashioned", val: "올드패션드/락 글라스" },
        { index: 5, name: "rock", val: "락 글라스" },
        { index: 6, name: "collins", val: "콜린스 글라스" },
        { index: 7, name: "martini", val: "마티니/칵테일 글라스" },
        { index: 8, name: "margarita", val: "마가리타 글라스" },
        { index: 9, name: "pilsner", val: "필스너 글라스" },
        {
          index: 10,
          name: "irish-coffee-fizz",
          val: "아이리쉬 커피/피즈 글라스",
        },
        { index: 11, name: "pousse", val: "푸세카페 글라스" },
        { index: 12, name: "brandy-snifter", val: "브랜디 스나이퍼" },
        { index: 13, name: "punch-cup", val: "펀치 컵" },
        { index: 14, name: "cordial", val: "코디얼 글라스" },
        { index: 15, name: "white-wine", val: "화이트 와인 글라스" },
        { index: 16, name: "red-wine", val: "레드 와인 글라스" },
        { index: 17, name: "sherry", val: "쉐리 글라스" },
        { index: 18, name: "champagne-flute", val: "샴페인 플룻" },
        { index: 19, name: "parfait", val: "파르페 글라스" },
        { index: 20, name: "sour", val: "위스키사워 글래스" },
        { index: 21, name: "beer", val: "맥주 잔" },
        { index: 22, name: "soju", val: "소주 잔" },
        { index: 23, name: "mug", val: "머그 잔" },
      ],
      units: [{}],
      tag: "",
      type: "",
      ingredient: "",
      quantity: null,
      unit: "",
      recipe: "",
      alcoholTemp: [],
      ingredientTemp: [],
      imgsrc: "",
      img_path: {
        val: require("../../assets/img/defaultCocktailImage.png"),
        src: "img/defaultCocktailImage.png",
        isValid: true,
      },
      title_kor: {
        val: "",
        isValid: true,
      },
      title_eng: {
        val: "",
        isValid: true,
      },
      abv: {
        val: null,
        isValid: true,
      },
      content: {
        val: "",
        isValid: true,
      },
      cupinfo: {
        val: "",
        isValid: true,
      },
      tags: {
        val: [],
        isValid: true,
      },
      alcohols: {
        val: [],
        isValid: true,
      },
      ingredients: {
        val: [],
        isValid: true,
      },
      recipes: {
        val: [],
        isValid: true,
      },
    };
  },
  created() {
    if (this.$route.params.pk_article > 0) {
      const selectedRecipe = this.$store.getters["recipes/recipes"].find(
        (recipe) =>
          recipe.pk_article.toString() === this.$route.params.pk_article
      );
      console.log(selectedRecipe);
      this.imgsrc = selectedRecipe.img_path;
      this.img_path.val = selectedRecipe.img_path;
      this.title_kor.val = selectedRecipe.title_kor;
      this.title_eng.val = selectedRecipe.title_eng;
      this.abv.val = selectedRecipe.abv;
      this.content.val = selectedRecipe.content;
      this.cupinfo.val = selectedRecipe.cup_no;
      // 재료 어떻게..?
      this.tags.val = selectedRecipe.tag.split("<br>");
      this.recipes.val = selectedRecipe.recipe.split("<br>");
      //알코올
      const alcoholItem = selectedRecipe.alcohol.split("<br>");
      const modifiedAlcohol = [];
      for (const item of alcoholItem) {
        const alcoholInfo = item.split("/");
        const modifiedAlcoholItem = `${alcoholInfo[0]} ${alcoholInfo[1]}${alcoholInfo[2]}`;
        modifiedAlcohol.push(modifiedAlcoholItem);
      }
      this.alcohols.val = selectedRecipe.alcohol.split("<br>");
      this.alcoholTemp = modifiedAlcohol;

      const ingredientItem = selectedRecipe.ingredient.split("<br>");
      const modifiedIngredient = [];
      for (const item of ingredientItem) {
        const ingredientInfo = item.split("/");
        const modifiedIngredientItem = `${ingredientInfo[1]} ${ingredientInfo[2]}${ingredientInfo[3]}`;
        modifiedIngredient.push(modifiedIngredientItem);
      }
      this.ingredients.val = selectedRecipe.ingredient.split("<br>");
      this.ingredientTemp = modifiedIngredient;
    }
  },
  computed: {
    getCategoryTitle() {
      if (this.category === 'custom') return "커스텀";
      return "오피셜";
    },
  },
  methods: {
    showImgRegDialog() {
      // console.log("사진 등록 중");
      // console.log(this.img_path.val);
      this.img_path.val = this.imgsrc;
      this.imgsrc = "";
      // alert("사진 등록 기능 준비중");
    },
    async addTag() {
      if (this.tag === "") alert("태그 내용을 입력해주세요.");
      else if (this.tags.val.includes(this.tag))
        alert("이미 등록된 태그입니다.");
      else {
        this.tags.val.push(this.tag);
        //실제로 tag 테이블네 넣는 조건

        //태그 이름을 넣어줌
        const tagData = {
          content_kor: this.tag,
        };

        //중복된 값이 있는지 체크 있으면 continue
        const flag = await this.$store.dispatch("tags/checkTag", tagData);
        console.log(flag);
        //없으면 태그 데이터에 넣어줌
        if (!flag) {
          await this.$store.dispatch("tags/submitTag", tagData);
        }

        this.tag = "";
      }
    },
    removeTag(index) {
      var tagN = this.tags.val.splice(index, 1);
      console.log(tagN);
      //실제로 삭제되는 로직 구현
    },
    async addIngredient() {
      if (this.type === "") {
        alert("분류를 선택해주세요.");
        return;
      }
      if (this.ingredient === "") {
        alert("재료명을 입력해주세요.");
        return;
      }
      if (!this.quantity || this.quantity <= 0) {
        alert("용량을 입력해주세요. (용량은 0보다 커야 합니다.");
        return;
      }
      if (this.unit === "") {
        alert("단위를 입력해주세요.");
        return;
      }

      const isAlcoholDuplicated = this.alcoholTemp.filter((alcohol) => {
        if (alcohol.includes(this.ingredient)) return true;
        return false;
      });
      const isIngredientDuplicated = this.ingredientTemp.filter(
        (ingredient) => {
          if (ingredient.includes(this.ingredient)) return true;
          return false;
        }
      );

      if (!isAlcoholDuplicated || !isIngredientDuplicated) {
        alert("이미 등록된 재료입니다.");
        return;
      }

      const tempItem = `${this.ingredient} ${this.quantity}${this.unit}`;

      if (this.type === "주류") {
        const alcoholItem = `${this.ingredient}/${this.quantity}/${this.unit}`;
        this.alcoholTemp.push(tempItem);
        this.alcohols.val.push(alcoholItem);
        this.alcohols.isValid = true;

        //태그 로직 아래 추가
        const tagData = {
          content_kor: this.ingredient,
        };
        //중복된 값이 있는지 체크 있으면 continue
        const flag = await this.$store.dispatch("tags/checkTag", tagData);
        // console.log(flag)
        //없으면 태그 데이터에 넣어줌
        if (!flag) {
          await this.$store.dispatch("tags/submitTag", tagData);
        }
      } else {
        const ingredientItem = `${this.type}/${this.ingredient}/${this.quantity}/${this.unit}`;
        this.ingredientTemp.push(tempItem);
        this.ingredients.val.push(ingredientItem);
        this.ingredients.isValid = true;

        //재료, 태그 로직 아래 추가
        //태그 로직
        const tagData = {
          content_kor: this.ingredient,
        };
        // 중복된 값이 있는지 체크 있으면 continue
        const flag = await this.$store.dispatch("tags/checkTag", tagData);
        // console.log(flag)
        //없으면 태그 데이터에 넣어줌
        if (!flag) {
          await this.$store.dispatch("tags/checkTag", tagData);
        }

        //재료 로직
        const iflag = await this.$store.dispatch(
          "tags/checkIngredient",
          tagData
        );
        // console.log(iflag);
        if (!iflag) {
          await this.$store.dispatch("tags/submitIngredient", tagData);
        }
      }
      alert(this.type + " (" + this.ingredient + ")가 추가되었습니다.");

      this.type = "";
      this.ingredient = "";
      this.quantity = null;
      this.unit = "";
    },
    removeAlcohol(index) {
      this.alcoholTemp.splice(index, 1);
      this.alcohols.val.splice(index, 1);
    },
    removeIngredient(index) {
      this.ingredientTemp.splice(index, 1);
      this.ingredients.val.splice(index, 1);
    },
    addRecipe() {
      if (this.recipe !== "") {
        this.recipes.val.push(this.recipe);
        this.recipe = "";
      } else alert("레시피 내용을 입력해주세요.");
    },
    removeRecipe(index) {
      this.recipes.val.splice(index, 1);
    },
    clearValidity(input) {
      this[input].isValid = true;
    },
    validateForm() {
      this.formIsValid = true;

      if (this.title_kor.val === "") {
        this.title_kor.isValid = false;
        this.formIsValid = false;
        console.log("1");
      }
      // if (this.title_eng.val === "") {
      //   this.title_eng.isValid = false;
      //   this.formIsValid = false;
      // }
      if (!this.abv.val || this.abv.val < 0) {
        this.abv.isValid = false;
        this.formIsValid = false;
      }
      if (this.content.val === "") {
        this.content.isValid = false;
        this.formIsValid = false;
      }
      if (this.cupinfo.val === "") {
        this.cupinfo.isValid = false;
        this.formIsValid = false;
      }
      if (this.tags.val.length === 0) {
        this.tags.isValid = false;
        this.formIsValid = false;
      }
      // if (this.alcohols.val.length === 0) {
      //   this.alcohols.isValid = false;
      //   this.formIsValid = false;
      // }
      // if (this.ingredients.val.length === 0) {
      //   this.ingredients.isValid = false;
      //   this.formIsValid = false;
      // }
      if (this.recipes.val.length === 0) {
        this.recipes.isValid = false;
        this.formIsValid = false;
      }
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

      var convertTime =
        year + "" + month + "" + date + "" + hour + "" + min + "" + sec;

      return convertTime;
    },

    async submitForm() {
      this.validateForm();

      if (!this.formIsValid) return;

      const formData = {
        category: this.category,
        img_path: this.img_path.val,
        title_kor: this.title_kor.val,
        title_eng: this.title_eng.val,
        abv: this.abv.val,
        content: this.content.val,
        cupinfo: this.cupinfo.val,
        tags: this.tags.val.join("<br>"), // default separator: ','
        alcohols: this.alcohols.val.join("<br>"),
        ingredients: this.ingredients.val.join("<br>"),
        recipes: this.recipes.val.join("<br>"),
        created_at: this.time_cal(),
      };

      console.log(formData);

      await this.$store.dispatch("recipes/registerRecipe", formData);
      this.$router.replace("/recipe/" + this.category);
    },
    async updateForm() {
      this.validateForm();
      if (!this.formIsValid) return;

      const formData = {
        pk_article: this.$route.params.pk_article,
        category: this.category,
        img_path: this.img_path.val,
        title_kor: this.title_kor.val,
        title_eng: this.title_eng.val,
        abv: this.abv.val,
        content: this.content.val,
        cupinfo: this.cupinfo.val,
        tags: this.tags.val.join("<br>"), // default separator: ','
        alcohols: this.alcohols.val.join("<br>"),
        ingredients: this.ingredients.val.join("<br>"),
        recipes: this.recipes.val.join("<br>"),
        created_at: this.time_cal(),
      };

      console.log(formData);

      const result = await this.$store.dispatch(
        "recipes/updateRecipe",
        formData
      );
      if (result) {
        alert("변경이 완료되었습니다.");
      }
      this.$router.replace("/recipe/" + this.category);
    },
  },
};
</script>

<style scoped>
div {
  margin: 1rem 0;
}
.title {
  font-size: 48px;
  line-height: 93px;
}

#form-recipe select {
  font-size: 1rem;
  line-height: 1.5rem;
  padding: 0.5rem 1rem;
  border: 1px solid #ccc;
  border-radius: 5rem;
}

#form-recipe label {
  font-family: "S-CoreDream-5Medium";
  font-size: 1.1rem;
  line-height: 1.6rem;
  width: 12rem;
  display: flex;
}

#form-recipe input,
#form-recipe textarea {
  font-size: 1rem;
  line-height: 1.5rem;
  padding: 0.5rem 1rem;
  border: 1.5px solid #ccc;
  border-radius: 1rem;
  outline: none;
}

#form-recipe input {
  border-radius: 5rem;
}

#form-recipe select:focus,
#form-recipe input:focus,
#form-recipe textarea:focus {
  outline: none;
  border-color: #1b995e;
  background-color: #d7fdeb;
}

.invalid p {
  margin-top: 0.5rem;
  margin-left: 12.25rem;
  font-size: 1rem;
  line-height: 1.5rem;
  color: #ff0000;
}

.invalid input,
.invalid select,
.invalid textarea {
  border: 1px solid #ff0000;
}

#card-margin {
  margin: 2rem auto;
}

</style>
