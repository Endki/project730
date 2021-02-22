<template>
  <div
    @click="viewRecipeDetail"
    class="font-S-CoreDream-light flex flex-col justify-center card-corner max-w-sm rounded overflow-hidden bg-white m-4 shadow-lg pb-2 transition duration-200 ease-in-out transform hover:scale-105"
  >
    <img
      class="w-full h-44 object-cover mr-0"
      :src="imgsrc"
      alt="cocktail image"
    />

    <span
      class="font-S-CoreDream-medium font-semibold text-lg mt-2 mb-2 text-center"
      >{{ cocktailname }}</span
    >

    <section class="mx-2">
      <span
        v-for="tag in tags.slice(0, 2)"
        :key="tag"
        class="w-max inline-block bg-gray-200 rounded-full px-2 py-0.5 font-2xs font-semibold ml-2"
        >#{{ tag }}</span
      >
    </section>
    <section class="flex items-center mt-4">
      <img
        :src="userProfileImage"
        class="w-6 h-6 rounded-full ml-6 mr-2 object-cover"
        alt="profile image"
      />
      <span class="ml-2 text-xs font-semibold">{{ user_name }}</span>
      <section class="flex justify-center justify-self-end ml-auto mr-4">
        <article class="flex items-center">
          <img
            src="../../assets/icon/like@0.75x.png"
            class="object-contain ml-2 mr-0"
            alt="like icon"
          />
          <span class="font-2xs font-semibold font-color-black-300">
            {{ like_cnt }}
          </span>
        </article>
        <article class="flex items-center">
          <img
            src="../../assets/icon/bookmark@0.75x.png"
            class="object-contain ml-1 mr-0"
            alt="bookmark icon"
          />
          <span class="mr-2 font-2xs font-semibold font-color-black-300">
            {{ bookmark_cnt }}
          </span>
        </article>
      </section>
    </section>
  </div>
</template>

<script>
export default {
  props: [
    "pk_article",
    "img_path",
    "cocktailname",
    "user_name",
    "user_profileImage",
    "tag",
    "like_cnt",
    "bookmark_cnt",
  ],
  data() {
    return {
      tags: [],
      imgsrc: this.img_path,
    };
  },
  created() {
    this.tags = this.tag.split("<br>");
  },
  computed: {
    userProfileImage() {
      if (this.user_profileImage) return this.user_profileImage;
      else
        return "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSaaNaTC8W_ygKLZxLFWpHOerfIYQiVlsuyrw&usqp=CAU";
    },
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

<style scoped>
.card-corner {
  border-radius: 30px;
}
</style>
