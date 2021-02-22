<template>
  <div class="flex flex-row font-S-CoreDream-light font-color-black-400 w-full">
    <section class="flex justify-items-center mx-auto">
      <img
        id="profileImage"
        alt="profile image"
        :src="userInfo[0].img_path"
        class="h-32 w-32 m-4 mr-8 cursor-pointer rounded-full object-cover text-center"
        @click="viewProfileImage"
      />
      <base-modal
        @close="hideDialog"
        :open="imgDialogIsVisible"
        custom="width-wide"
        class="flex flex-col justify-items-center z-40"
      >
        <img
          id="profileImage"
          alt="profile image"
          :src="userInfo[0].img_path"
          class="h-80 w-80 m-4 rounded-2xl object-cover text-center"
          @click="viewProfileImage"
        />
      </base-modal>
      <section class="flex flex-col justify-items-center my-auto">
        <article class="flex items-center my-4">
          <span
            class="font-S-CoreDream-medium text-3xl mr-auto font-semibold"
            >{{ userInfo[0].user_name }}</span
          >
          <article
            v-if="userInfo[0].user_name !== owner_check"
            class="flex flex-row justify-self-end ml-auto"
          >
            <base-button
              v-if="isFollow"
              class="text-sm font-S-CoreDream-medium px-5 py-1.5"
              mode="colored"
              @click="follow"
              >팔로잉</base-button
            >
            <base-button
              v-else
              class="text-sm font-S-CoreDream-medium px-5 py-1.5"
              mode="outline-colored"
              @click="follow"
              >팔로우</base-button
            >
          </article>
        </article>

        <article class="flex font-S-CoreDream-medium text-lg mx-4">
          <span class="mr-6 px-6 py-1.5"
            >작성글
            <span class="font-bold">{{ recipeCnt + postCnt }}</span></span
          >
          <span
            class="btn mr-6 px-6 py-1.5 cursor-pointer"
            @click="viewFollowerList()"
            >팔로워 <span class="font-bold">{{ followCnt }}</span></span
          >
          <span
            class="btn px-6 py-1.5 cursor-pointer"
            @click="viewFollowingList()"
            >팔로우 <span class="font-bold">{{ followingCnt }}</span></span
          >

          <base-modal
            @close="hideDialog"
            :open="followerDialogIsVisible"
            custom="width-wide"
            class="flex flex-col justify-items-center"
          >
            <section class="modal-header">
              <span>팔로워</span>
            </section>
            <section class="w-full flex flex-col">
              <section
                v-if="followList.length > 0"
                class="mx-1 my-2 py-2 bg-white"
              >
                <article
                  v-for="user in followList"
                  :key="user.pk_user"
                  class="flex items-center mx-6 my-4"
                >
                  <img
                    alt="profile image"
                    :src="user.img_path"
                    class="w-12 h-12 mr-4 object-cover rounded-full"
                  />
                  <span class="mr-2 font-S-CoreDream-medium">{{
                    user.user_name
                  }}</span>
                  <!-- <base-button
                    v-if="user.user_name !== myself"
                    mode="outline"
                    class="text-xs px-4 py-1.5 ml-auto justify-self-end"
                    >보러가기</base-button> -->
                </article>
              </section>
              <span
                v-else
                class="text-xl flex mx-auto text-center my-28 font-S-CoreDream-medium font-color-black-200"
                >팔로잉하는 유저가 없습니다.</span
              >
            </section>
          </base-modal>
          <base-modal
            @close="hideDialog"
            :open="followingDialogIsVisible"
            custom="width-wide"
            class="flex flex-col justify-items-center"
          >
            <section class="modal-header">
              <span>팔로우</span>
            </section>
            <section class="w-full flex flex-col">
              <section
                v-if="followingList.length > 0"
                class="mx-1 my-2 py-2 bg-white"
              >
                <article
                  v-for="user in followingList"
                  :key="user.pk_user"
                  class="flex items-center mx-6 my-4"
                >
                  <img
                    alt="profile image"
                    :src="user.img_path"
                    class="w-12 h-12 mr-4 object-cover rounded-full"
                  />
                  <span class="mr-2 font-S-CoreDream-medium">{{
                    user.user_name
                  }}</span>
                  <!-- <base-button
                    v-if="user.user_name !== myself"
                    mode="outline"
                    class="text-xs px-4 py-1.5 ml-auto justify-self-end"
                    >팔로우</base-button
                  > -->
                </article>
              </section>
              <span
                v-else
                class="text-xl flex mx-auto text-center my-28 font-S-CoreDream-medium font-color-black-200"
                >팔로우하는 유저가 없습니다.</span
              >
            </section>
          </base-modal>
        </article>
      </section>
    </section>
  </div>
</template>

<script>
export default {
  props: ["username"],
  data() {
    return {
      owner_check: localStorage.getItem("user_name"),
      isFollow: false,
      imgDialogIsVisible: false,
      followingDialogIsVisible: false,
      followerDialogIsVisible: false,
      isFollower: false,
      myself: "",
    };
  },
  computed: {
    myProfileImage() {
      return this.$store.getters.profileImage;
    },
    userInfo() {
      return this.$store.getters["users/otherMyPage"];
    },
    set_isFollow() {
      return this.$store.getters["follows/isFollow"];
    },
    followList() {
      return this.$store.getters["follows/followList"];
    },
    followingList() {
      return this.$store.getters["follows/followingList"];
    },
    followCnt() {
      return this.$store.getters["follows/followList"].length;
    },
    followingCnt() {
      return this.$store.getters["follows/followingList"].length;
    },
    postCnt() {
      return this.$store.getters["users/myForum"].length;
    },
    recipeCnt() {
      return this.$store.getters["users/myRecipe"].length;
    },
  },
  created() {
    this.loadUserInfo();
    this.LoadFollowingList();
    this.LoadFollowList();
    // console.log(this.followingList);
    // console.log(this.followList);
    this.myself = this.$store.getters.userName;
    console.log("userboard", this.username);
  },
  updated() {
    this.is_Follow();
    this.isFollow = this.$store.getters["follows/isFollow"];
  },
  methods: {
    async loadUserInfo() {
      const userInfo = {
        mode: "myPage",
      };
      await this.$store.dispatch("users/LoadOtherMyPage", userInfo);
    },
    async is_Follow() {
      const userInfo = {
        target_no: localStorage.getItem("pkOther"),
      };
      await this.$store.dispatch("follows/isFollow", userInfo);
    },
    async follow() {
      if (localStorage.getItem("user_name") === null) {
        alert("로그인이 필요한 기능입니다.");
        return;
      }
      const mode = this.isFollow ? "following" : "x";
      const flag = 1;
      const userInfo = {
        target_no: localStorage.getItem("pkOther"),
        mode: mode,
        flag: flag,
      };
      if (this.isFollow) {
        await this.$store.dispatch("follows/unfollow", userInfo);
      } else {
        await this.$store.dispatch("follows/follow", userInfo);
      }
    },
    async LoadFollowList() {
      const userInfo = {
        target_no: localStorage.getItem("pkOther"),
        mode: "otherUser",
      };
      await this.$store.dispatch("follows/followList", userInfo);
    },
    async LoadFollowingList() {
      const userInfo = {
        target_no: localStorage.getItem("pkOther"),
        mode: "otherUser",
      };
      await this.$store.dispatch("follows/followingList", userInfo);
    },

    viewProfileImage() {
      this.imgDialogIsVisible = true;
    },

    viewFollowerList() {
      this.followerDialogIsVisible = true;
    },
    viewFollowingList() {
      this.followingDialogIsVisible = true;
    },
    hideDialog() {
      this.imgDialogIsVisible = false;
      this.followerDialogIsVisible = false;
      this.followingDialogIsVisible = false;
    }
  }, 
  watch: {
    set_isFollow(newVal) {
      this.isFollow = newVal;
    },
  },
};
</script>

<style scoped>
#modal-width {
  width: 25rem;
}

.modal-header {
  margin: 1rem auto;
  display: flex;
  align-items: center;
  font-weight: 700;
  font-size: 18pt;
}

.btn {
  border-radius: 30px;
  transition: 0.3s ease-out;
}

.btn:hover {
  color: white;
  background-color: #00002f;
}
</style>
