<template>
  <div style="font-color-black-400 font-S-CoreDream-light">
    <section class="w-1/2 my-20 mx-auto">
      <user-board :username="username"></user-board>
    </section>
    <section class="w-4/5 mx-auto mt-10">
      <other-posts :username="username"></other-posts>
    </section>
  </div>
</template>

<script>
import UserBoard from "../../components/users/UserBoard.vue";
import OtherPosts from "../../components/users/OtherPosts.vue";

export default {
  props: ["username"],
  components: {
    UserBoard,
    OtherPosts,
  },
};
</script>

<style scoped></style>
