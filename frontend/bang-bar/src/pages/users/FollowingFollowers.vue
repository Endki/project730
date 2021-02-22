<template>
  <div class="flex font-S-CoreDream-light font-color-black-400 w-full">
    <section class="flex flex-col w-full justify-items-center mr-4">
      <span
        class="tracking-wider font-semibold text-2xl border-gray-800 border-b-4 font-S-CoreDream-medium w-max px-1 mt-10 mx-auto"
        >팔로잉 ({{ followingCnt }})</span
      >
      <section v-if="followingList.length > 0" class="mx-10 my-8 py-4 bg-white card">
        <article
          v-for="user in followingList"
          :key="user.pk_user"
          class="flex items-center mx-12 my-2 py-1"
        >
          <img
            alt="profile image"
            :src="user.img_path"
            class="w-12 h-12 ml-4 mr-2 object-cover rounded-full"
          />
          <span class="font-S-CoreDream-medium">{{ user.user_name }}</span>
          <base-button
            mode="outline"
            class="text-xs px-4 py-1 ml-auto justify-self-end"
            @click="unfollow(user.pk_user, 'following')"
            >삭제</base-button
          >
        </article>
      </section>
      <span v-else class="text-xl text-center my-32 font-S-CoreDream-medium font-color-black-200"
        >팔로잉하는 유저가 없습니다.</span
      >
    </section>
    <section class="flex flex-col w-full justify-items-center">
      <span
        class="tracking-wider font-semibold text-2xl border-gray-800 border-b-4 font-S-CoreDream-medium w-max px-1 mt-10 mx-auto"
        >팔로워 ({{ followCnt }})</span
      >
      <section v-if="followList.length > 0" class="mx-10 my-8 py-4 bg-white card">
        <article
          v-for="user in followList"
          :key="user.pk_user"
          class="flex items-center mx-12 my-2 py-1"
        >
          <img
            alt="profile image"
            :src="user.img_path"
            class="w-12 h-12 ml-4 mr-2 object-cover rounded-full"
          />
          <span class="font-S-CoreDream-medium">{{ user.user_name }}</span>
          <base-button
            mode="outline"
            class="text-xs px-4 py-1 ml-auto justify-self-end"
            @click="unfollow(user.pk_user, 'follower')"
            >삭제</base-button
          >
        </article>
      </section>
      <span v-else class="text-xl text-center my-32 font-S-CoreDream-medium font-color-black-200"
        >나를 팔로우하는 유저가 없습니다.</span
      >
    </section>
  </div>
</template>

<script>
// const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  data() {
    return {};
  },
  created() {
    this.LoadFollowingList();
    this.LoadFollowList();
  },
  computed: {
    tempImage() {
      return localStorage.getItem('profileImage');
    },
    followList() {
      return this.$store.getters['follows/followList'];
    },
    followingList() {
      return this.$store.getters['follows/followingList'];
    },
    followCnt() {
      return this.followList.length;
    },
    followingCnt() {
      return this.followingList.length;
    },
  },
  watch: {},
  methods: {
    async LoadFollowList() {
      const userInfo = 'myPage';
      await this.$store.dispatch('follows/followList', userInfo);
    },
    async LoadFollowingList() {
      const userInfo = 'myPage';
      await this.$store.dispatch('follows/followingList', userInfo);
    },
    async unfollow(pk_user, mode) {
      const unfollowData = {
        target_no: pk_user,
        mode: mode,
      };
      console.log(unfollowData);
      await this.$store.dispatch('follows/unfollow', unfollowData);
    },
  },
};
</script>

<style scoped>
.card {
  border-radius: 30px;
}
</style>
