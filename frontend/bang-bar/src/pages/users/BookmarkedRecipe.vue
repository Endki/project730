<template>
  <div class="my-12 mx-auto px-4 md:px-12">
    <span class="border-gray-800 border-b-2">북마크한 레시피</span>

    <!-- 본문 -->
    <div class="flex flex-wrap -mx-1 lg:-mx-4">
      <!-- col 3로 구역 나눔 -->
      <div class="my-1 px-1 w-full md:w-1/2 lg:my-4 lg:px-4 lg:w-1/3">
        <!-- 레시피 카드 -->
        <my-page-card
          v-for="cocktail in filteredRecipes"
          :key="cocktail.pk_article"
          :pk_article="cocktail.pk_article"
          :img_path="cocktail.img_path"
          :cocktailname="cocktail.title_kor"
          :user_name="cocktail.user_name"
          :tag="cocktail.tag"
        >
        </my-page-card>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  components: {},
};
</script>

<style scoped>
.title {
  font-size: 42px;
  line-height: 93px;
}

.card-corner {
  border-radius: 30px;
}
</style>
