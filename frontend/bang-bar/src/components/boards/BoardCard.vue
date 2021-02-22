<template>
    <div
    @click="viewBoardDetail"
    class="font-S-CoreDream-light flex flex-col px-4 py-6 h-28 justify-start card-corner rounded overflow-hidden bg-white mx-1 my-2 shadow-lg transition duration-200 ease-in-out transform hover:scale-105"
  >
    <span class="text-xs font-color-black-200">{{ created_at }}</span>
    <span class="font-S-CoreDream-medium tracking-wider">[{{category}}] {{ title }}</span>
    <span class="text-sm truncate font-color-black-300 mt-1 h-auto">{{ content }}</span>
  </div>
</template>

<script>
export default {
  props: [
    "pk_forum",
    "created_at",
    "category",
    "title",
    "content",
  ],
  methods: {
    viewBoardDetail() {
      this.$router.push({
        path: `/board/detail/${this.pk_forum}`
      });
    },
  },
};
</script>

<style scoped>
.card-corner {
  border-radius: 30px;
}
</style>