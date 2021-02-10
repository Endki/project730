<template>
  <div
    @click="viewRecipeDetail"
    class="font-S-CoreDream-light flex flex-col justify-center card-corner max-w-sm rounded overflow-hidden bg-white m-4 shadow-lg pb-2 transition duration-200 ease-in-out transform hover:scale-105"
  >
    <img class="w-full h-64 object-cover mr-0" :src="imgsrc" alt="Sunset in the mountains" />

    <span class="font-S-CoreDream-medium font-semibold text-xl mt-4 mb-6 text-center">{{
      cocktailname
    }}</span>

    <section class="">
      <span
        v-for="tag in tags.slice(0, 3)"
        :key="tag"
        class="w-max inline-block bg-gray-200 rounded-full px-3 py-1 text-xs font-semibold ml-2 mb-2"
        >#{{ tag }}</span
      >
    </section>
  </div>
</template>

<script>
export default {
  props: ['pk_article', 'img_path', 'cocktailname', 'user_name', 'tag'],
  data() {
    return {
      tags: [],
      imgsrc: this.img_path,
    };
  },
  created() {
    this.tags = this.tag.split('<br>');
  },
  methods: {
    viewRecipeDetail() {
      console.log(this.img_path);
      this.$router.push({
        path: `/recipe/detail/${this.pk_article}`,
      });
    },
  },
};
</script>
