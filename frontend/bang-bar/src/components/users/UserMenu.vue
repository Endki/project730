<template>
  <div class="card flex flex-col font-color-black-400 font-S-CoreDream-light">
    <ul class="menubar m-auto tracking-widest">
      <li class="mx-12 my-6 flex items-center">
        <router-link to="/header/editprofile"
          ><img
            src="../../assets/icon/profile@2x.png"
            alt="icon"
            class="mr-1"
          />
          프로필/개인정보 수정</router-link
        >
      </li>
      <li class="mx-12 my-6 flex items-center">
        <router-link to="/header/bookmarkedrecipe"
          ><img
            src="../../assets/icon/bookmark@2x.png"
            alt="icon"
            class="mr-1"
          />북마크한 레시피</router-link
        >
      </li>
      <li class="mx-12 my-6 flex items-center">
        <router-link to="/header/myposts"
          ><img
            src="../../assets/icon/mypost@2x.png"
            alt="icon"
            class="mr-1"
          />내가 쓴 글</router-link
        >
      </li>
      <!-- <li class="mx-12 my-6 flex items-center">
        <router-link to="/header/activitylog"
          ><img
            src="../../assets/icon/activity@2x.png"
            alt="icon"
            class="mr-1"
          />활동 기록</router-link
        >
      </li> -->
      <li class="mx-12 my-6 flex items-center">
        <router-link to="/header/followingfollowers"
          ><img
            src="../../assets/icon/follow@2x.png"
            alt="icon"
            class="mr-1"
          />팔로잉/팔로워</router-link
        >
      </li>
    </ul>
    <ul>
      <li class="mb-8 text-center text-sm action-leave font-color-black-300">
        <router-link to="/header/leave">탈퇴하기</router-link>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  methods: {},
};
</script>

<style scoped>
.card {
  /* position: fixed;
  width: 20%;
  z-index: 1;
  top: 8rem;
  left: 3rem; */
  height: 38rem;
  background-color: #56e6a5;
  border-radius: 30px;
}

.menubar {
  list-style: none;
}

.menubar li a {
  color: #747475;
  text-decoration: none;
}

.menubar li a img {
  width: 2rem;
  height: 2rem;
  opacity: 0.5;
}

.menubar li a:hover,
.menubar li a:hover img,
.menubar li a.router-link-active,
.menubar li a.router-link-active img {
  opacity: 1;
  font-weight: 700;
  color: #23232f;
}

.action-leave a:hover,
.action-leave a.router-link-active {
  font-weight: 700;
  color: #23232f;
}
</style>
