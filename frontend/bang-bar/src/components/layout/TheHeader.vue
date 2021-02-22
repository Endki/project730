<template>
  <header class="mt-2">
    <div v-if="isLoading">
      <base-spinner></base-spinner>
    </div>
    <nav>
      <router-link to="/home">
        <section class="logo">
          <img class="mr-2" src="../../assets/img/logo.png" alt="logo-image" />
          <span class="text-lg text-center font-color-black-400 font-Mapo-DPPA"
            >방구석 바텐더</span
          >
        </section>
      </router-link>
      <ul class="ul-menu tracking-wider">
        <li><router-link to="/search">검색</router-link></li>
        <li><router-link to="/recommendation">칵테일 추천</router-link></li>
        <li><router-link to="/recipe/official">오피셜 레시피</router-link></li>
        <li><router-link to="/recipe/custom">커스텀 레시피</router-link></li>
        <li><router-link to="/board/list">게시판</router-link></li>
      </ul>
      <section v-if="username == null">
        <base-button
          id="button-signup"
          class="px-4 py-2"
          link
          mode="outline"
          to="/signup"
          >회원가입</base-button
        >

        <base-modal
          @close="hideDialog"
          :open="dialogIsVisible"
          class="flex flex-col justify-items-center z-40"
        >
          <section class="modal-header">
            <img src="../../assets/img/logo.png" alt="logo" />
            <span>로그인</span>
          </section>
          <form @submit.prevent="submitForm" class="flex flex-col justify-items-center">
            <input class="input-modal" type="text" placeholder="이메일" v-model.trim="email" />
            <input
              class="input-modal"
              type="password"
              placeholder="비밀번호"
              v-model.trim="password"
            />
            <span v-if="!formIsValid" class="font-sm text-red-500">
              이메일과 비밀번호가 올바른지 확인해주세요. (비밀번호는 최소 8자 입니다.)
            </span>
            <section class="button-modal flex justify-center">
              <base-button class="w-24 py-2">로그인</base-button>
              <base-button class="w-24 py-2" @click="hideDialog" mode="outline"
                >취소</base-button
              >
            </section>
          </form>
          <section class="sub-menu flex justify-center">
            <router-link @click="hideDialog" to="/findpassword"><u>비밀번호 찾기</u></router-link>
            <router-link @click="hideDialog" to="/signup"><u>회원가입</u></router-link>
          </section>
        </base-modal>
        <base-button class="ml-4 px-4 py-2.5" @click="showDialog"
          >로그인</base-button
        >
      </section>
      <section v-else>
        <base-dropdown class="z-40">
          <template v-slot="context">
            <img
              @click="context.toggleOpen"
              class="h-12 w-12 cursor-pointer rounded-full object-cover"
              :src="imgsrc"
              alt="profile image"
            />
            <transition
              enter-active-class="transition-all duration-300 ease-out"
              leave-active-class="transition-all duration-200 ease-in"
              enter-class="opacity-0 scale-75"
              enter-to-class="opacity-100 scale-100"
              leave-class="opacity-100 scale-100"
              leave-to-class="opacity-0 scale-75"
            >
              <div
                v-if="context.open"
                class="origin-top-right absolute -right-6 mt-14 w-max bg-white text-center border border-transparent overflow-hidden rounded-3xl shadow-2xl z-40"
              >
                <ul>
                  <li>
                    <div
                      class="rounded-t-lg block px-4 py-3 hover:bg-gray-100 cursor-pointer"
                      @click="goHome(setUserPk)"
                    >
                      <div class="font-semibold">{{ username }}</div>
                      <div class="text-gray-700">{{ email }}</div>
                    </div>
                  </li>
                  <li class="hover:bg-gray-100">
                    <router-link class="font-normal block px-4 py-3" to="/header/editprofile"
                      >프로필 수정</router-link
                    >
                  </li>
                  <li class="hover:bg-gray-100">
                    <router-link class="font-normal block px-4 py-3" to="/header/bookmarkedrecipe"
                      >북마크한 레시피</router-link
                    >
                  </li>
                  <li class="hover:bg-gray-100">
                    <router-link class="font-normal block px-4 py-3" to="/header/myposts"
                      >내가 쓴 글</router-link
                    >
                  </li>
                  <!-- <li class="hover:bg-gray-100">
                    <router-link
                      class="font-normal block px-4 py-3"
                      to="/header/activitylog"
                      >활동기록</router-link
                    >
                  </li> -->
                  <li class="hover:bg-gray-100">
                    <router-link class="font-normal block px-4 py-3" to="/header/followingfollowers"
                      >팔로잉/팔로워</router-link
                    >
                  </li>
                  <hr />
                  <li class="hover:bg-gray-100">
                    <span class="font-font-normal block px-4 py-3" @click="logout">로그아웃</span>
                  </li>
                </ul>
              </div>
            </transition>
          </template>
        </base-dropdown>
      </section>
    </nav>
  </header>
</template>

<script>
export default {
  data() {
    return {
      username: null,
      email: '',
      password: '',
      dialogIsVisible: false,
      isAuth: false,
      formIsValid: true,
      isLoading: false,
      error: null,
      // imgsrc: this.$store.getters.profileImage,
    };
  },
  created() {
    this.test();
  },
  computed: {
    setUsername() {
      return this.$store.getters.userName;
    },
    setUserEmail() {
      return this.$store.getters.email;
    },
    imgsrc() {
      return this.$store.getters.profileImage;
    },
  },
  watch: {
    // imgsrc: function(newVal) {
    //   this.imgsrc = newVal;
    // },
    setUsername: function(newVal) {
      this.username = newVal;
    },
    setUserEmail: function(newVal) {
      this.username = newVal;
    },
  },
  methods: {
    test() {
      this.username = localStorage.getItem('user_name');
      this.email = localStorage.getItem('email');
      this.userpk = localStorage.getItem('pk_user');
      console.log(this.userpk);
      // console.log(this.email);
    },
    showDialog() {
      this.email = "";
      this.password = "";
      this.dialogIsVisible = true;
      this.formIsValid = true;
    },
    hideDialog() {
      this.dialogIsVisible = false;
    },
    login() {
      this.isAuth = true;
      // location.reload();
    },
    logout() {
      this.$store.dispatch("logout");
      this.username = null;
      this.password = "";
      this.email = "";
      this.dialogIsVisible = false;
      this.$router.replace('/');
    },
    async submitForm() {
      this.formIsValid = true;
      if (this.email === '' || !this.email.includes('@') || this.password.length < 8) {
        this.formIsValid = false;
        this.password = "";
        return;
      }

      const actionPayload = {
        email: this.email,
        password: this.password,
      };

      try {
        if (this.username === null) {
          await this.$store.dispatch("login", actionPayload);
        }
      } catch (error) {
        this.error = error.message || '로그인에 실패하였습니다. 다시 시도바랍니다.';
      }
      this.username = localStorage.getItem("user_name");
      if (this.username === null) {
        this.formIsValid = false;
        return;
      }
    },
    handleError() {
      this.error = null;
    },
    goHome() {
      localStorage.setItem('pkOther', localStorage.getItem('pk_user'));
      return this.$router.push('/mypageother/' + this.username);
    },
  },
};
</script>

<style scoped>
section,
li {
  /* font-family: 'NIXGONM-Vb'; */
  font-family: "S-CoreDream-3Light";
}
.img-profile {
  width: 50px;
  height: 50px;
  border-radius: 15px;
}

header {
  width: 100%;
  height: 100%;
  /* background-color: #3d008d; */
  display: flex;
  justify-content: center;
  align-items: center;
}

header a {
  text-decoration: none;
  color: #23232f;
  display: inline-block;
  padding: 0.75rem 1rem 0.25rem 1rem;
  border: 1px solid transparent;
}

a:active,
a:hover,
a.router-link-active {
  /* border: 1px solid #ff5e46; */
}

.ul-menu li {
  margin: 0;
}

.ul-menu li a {
  margin: 0;
  border-bottom: 5px solid transparent;
  transition: 0.3s ease-out;
}

.ul-menu li a:hover,
.ul-menu li a:active,
.ul-menu li a.router-link-active {
  /* border-color: transparent; */
  border-bottom: 4px solid #ff5e46;
  border-radius: 4px;
}

.router-link-active {
  font-weight: 700;
}

header nav {
  width: 90%;
  margin: auto;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

header .ul-menu {
  list-style: none;
  margin: 0;
  padding: 0;
  display: flex;
  justify-content: center;
  align-items: center;
}

.ul-menu li {
  margin: 0 0.5rem;
  font-size: 12pt;
}

.modal-header {
  margin: 2rem auto;
  display: flex;
  align-items: center;
  font-weight: 700;
  font-size: 18pt;
}

.input-modal {
  font-size: 12pt;
  text-align: left;
  padding: 0.75rem 2rem;
  width: 257px;
  border-radius: 25px;
  border: 3px solid white;
  background-color: white;
  margin: 0.5rem 0;
  transition: 0.3s ease-out;
}

.input-modal:hover,
.input-modal:active,
.input-modal:focus {
  border: 3px solid #dddddd;
}

.button-modal {
  margin-top: 1rem;
}

u {
  font-size: 0.8rem;
  color: #747475;
  transition: 0.3s ease-out;
}

u:hover,
u:active {
  color: #23232f;
}

.logo {
  display: flex;
  align-items: center;
}

.input-modal:hover,
.input-modal:active,
.input-modal:focus {
  border: 3px solid #dddddd;
}

.button-modal {
  margin-top: 1rem;
}

u {
  color: #9a9a9a;
  transition: 0.3s ease-out;
}

u:hover,
u:active {
  color: #23232f;
}

.logo {
  display: flex;
  align-items: center;
  border-radius: 100%;
  transition: 0.3s ease-out;
  /* padding: 0 1rem; */
}

.logo:hover {
  background: #ff5e46;
}

#button-signup {
  padding: 0.5rem 1rem;
}
</style>
