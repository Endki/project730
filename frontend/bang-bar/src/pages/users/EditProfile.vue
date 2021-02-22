<template>
  <div class="flex flex-col font-S-CoreDream-light font-color-black-400 w-max">
    <span
      class="tracking-wider font-semibold text-2xl border-gray-800 border-b-4 font-S-CoreDream-medium w-max px-1 mt-10"
      >프로필 변경</span
    >
    <section class="flex">
      <article
        class="flex flex-col mt-6 ml-4 mr-10 justify-items-center content-center"
      >
        <img
          id="profileImage"
          alt="profile image"
          :src="imgsrc"
          class="h-24 w-24 mx-auto mb-3 rounded-full object-cover text-center"
        />
        <!-- <base-button class="btn px-3 py-1 mr-0 text-sm"  @click="changeProfileImage"
          >이미지 변경</base-button
        > -->

        <label class="btn px-3 py-1 text-sm" @click="showDialog"
          >이미지 변경
          <!-- <input
            type="file"
            class="hidden"
            id="image_uploads"
            name="image_uploads"
            accept=".jpg, .jpeg, .png .gif"
            @change="changeProfileImage"
          /> -->
        </label>
        <base-modal
          @close="hideDialog"
          :open="dialogIsVisible"
          class="flex flex-col justify-items-center z-40"
        >
        <span class="tracking-wider font-semibold text-2xl font-S-CoreDream-medium w-max px-1 mt-6 mb-10">프로필 이미지 변경</span>
        <input
              class="text-sm text-left rounded-full w-full px-8 py-3 mb-4 border-3 border-transparent hover:bg-gray-100 focus:outline-none"
              type="text"
              placeholder="이미지 주소를 입력하세요"
              v-model.trim="newImgsrc"
            />
            <span v-if="!formIsValid" class="text-xs text-red-500">
              올바른 이미지 주소를 입력하세요.
            </span>
            <article class="flex mt-8">
        <base-button class="w-24 py-2 text-sm" @click="changeProfileImageByURL">변경하기</base-button>
        <base-button class="w-24 py-2 text-sm" @click="hideDialog" mode="outline">취소</base-button>
        </article>
        </base-modal>
      </article>
      <article class="my-auto flex flex-col">
        <div class="items-center">
          <input
            class="text-base text-left rounded-full px-8 py-3 mr-4 hover:bg-gray-100 focus:outline-none"
            id="username"
            type="text"
            placeholder="닉네임"
            v-model="user_name"
          />
          <base-button
            mode="important"
            class="px-6 py-3 text-sm"
            @click="checkDuplication"
            >중복 확인</base-button
          >
        </div>
        <div class="text-xs font-color-black-200 mt-2">
          <p>* 이름은 최소 2자, 최대 10자까지 입력이 가능해요</p>
          <p>* 수정한 정보는 방구석 바텐더의 서비스에 바로 적용돼요</p>
        </div>
      </article>
    </section>
      <article class="flex justify-self-center mx-auto mt-10">
      <base-button class="px-4 py-2 text-sm" @click="saveNameImg">변경사항 저장하기</base-button>
    </article>
    <span
      class="tracking-wider font-semibold text-2xl border-gray-800 border-b-4 font-S-CoreDream-medium w-max px-1 mt-12"
      >개인정보 변경</span
    >
    <section class="flex">
      <article class="m-4 mt-6 flex flex-col">
        <span class="font-S-CoreDream-medium text-right mr-10 mb-10"
          >이메일</span
        >
        <span class="font-S-CoreDream-medium text-right mr-10">비밀번호</span>
      </article>
      <article class="m-4 mt-6 flex flex-col">
        <span class="font-S-CoreDream-medium mb-10">{{ user_id }}</span>
        <input
          class="text-sm text-left rounded-full px-8 py-3 mb-4 border-3 border-transparent hover:bg-gray-100 focus:outline-none"
          id="currentPassword"
          v-model="currentPassword"
          type="password"
          placeholder="현재 비밀번호"
          minlength="8"
          maxlength="20"
        />
        <input
          class="text-sm text-left rounded-full px-8 py-3 mb-4 border-3 border-transparent hover:bg-gray-100 focus:outline-none"
          id="newPassword"
          v-model="newPassword"
          type="password"
          placeholder="새 비밀번호"
          minlength="8"
          maxlength="20"
        />
        <!-- <div class="alertText">
              * 비밀번호는 영문과 숫자를 반드시 포함해 최소 8자, 최대 20자까지
              입력이 가능해요.
        </div> -->
        <input
          class="text-sm text-left rounded-full px-8 py-3 mb-4 border-3 border-transparent hover:bg-gray-100 focus:outline-none"
          id="confirmNewPassword"
          v-model="confirmNewPassword"
          type="password"
          placeholder="새 비밀번호 확인"
          minlength="8"
          maxlength="20"
      
          @keyup="passDupl_Check()"
        />
        <span>{{ passmessage }}</span>
      </article>
    </section>
    <article class="flex justify-self-center mx-auto mt-10">
      <base-button class="px-4 py-2 text-sm" @click="checkPW">변경사항 저장하기</base-button>
    </article>
  </div>
</template>

<script>
export default {
  data() {
    return {
      user_id: localStorage.getItem("email"),
      user_name: localStorage.getItem("user_name"),
      imgsrc: localStorage.getItem("profileImage") === null ? "https://www.lifewire.com/thmb/wTQhx22YA7ljA0-dTNKiHp2bReI=/1142x642/smart/filters:no_upscale()/iphonex_animoji_fox-59dd137c03f4020010a60b54.gif" : localStorage.getItem("profileImage"),
      newImgsrc: "",
      passmessage:"",
      checkFlag : false,
      dialogIsVisible: false,
      formIsValid: true,
    };
  },
  watch: { 
    $route(to) {
      if (to.path === `/` || to.path === `/home`) { 
        location.reload();
      } 
    } 
  },
  methods: {
    changeProfileImage(input) {
      console.log(input.target.files[0].name);
      const objectURL = URL.createObjectURL(input.target.files[0]);
      this.imgsrc = objectURL;
    },
    changeProfileImageByURL() {
      this.formIsValid = true;

      if (this.newImgsrc === "") {
        this.formIsValid = false;
        return;
      }
      
      this.imgsrc = this.newImgsrc;
      this.newImgsrc = "";
      this.dialogIsVisible = false;
    },
    showDialog() {
      this.dialogIsVisible = true;

    },
    hideDialog() {
      this.dialogIsVisible = false;
    },
    async checkDuplication() {
      const userinfo = {
        newValue: this.user_name,
        oldValue: this.$store.getters.userName
      };
      const result = await this.$store.dispatch("users/checkName", userinfo);
      if(result === "SUCCESS") {
        this.checkFlag = true;
      } 
    },
    
    async saveNameImg() {
      if(!this.checkFlag) {
        alert("닉네임 중복확인을 해주세요.");
        return;
      }
      if(this.user_name === localStorage.getItem("user_name") && this.imgsrc === localStorage.getItem("profileImage")) {
        alert("변경된 사항이 없습니다.");
        return;
      }
      const userInfo = {
        user_name : this.user_name,
        img_path : this.imgsrc
      }
      await this.$store.dispatch("users/changeNameImg", userInfo);
      this.$store.dispatch("changeProfileImage", this.imgsrc);
      this.$router.replace("/header/editprofile");
    },
    async checkPW() {
      const result = await this.$store.dispatch("users/confirmPW", this.currentPassword);
      if(result === "FAIL") {
        alert("현재 비밀번호가 아닙니다.");
        return;
      } else {
        this.savePW();
      }
    },
     passDupl_Check(){
      if(this.newPassword===this.confirmNewPassword && this.newPassword.length >= 8){
        this.passmessage="비밀번호가 동일합니다."
      }else{
        this.passmessage="비밀번호가 다릅니다."
      }
    },
    ispassCheck(password){  

      var check= new RegExp("^(?=.*[0-9])(?=.*[a-zA-Z]).{8,20}$");
        if(check.test(password)){
          return false;
        }
      return true;
    },
    async savePW() {
      if(this.ispassCheck(this.newPassword) || this.newPassword.length < 8) {
        alert("새로 입력한 비밀번호가 입력형식에 맞지 않습니다.");
        alert("* 비밀번호는 영문과 숫자를 반드시 포함해 최소 8자, 최대 20자까지 입력이 가능해요.");
        return;
      }
      if(this.password === this.newPassword) {
        alert("현재 비밀번호와 동일한 비밀번호입니다.");
        return;
      }
      if(this.newPassword !== this.confirmNewPassword) {
        alert("새로 입력한 비밀번호가 일치하지 않습니다.");
        return;
      }

      const userInfo = {
        email : localStorage.getItem("email"),
        password : this.newPassword
      }
      const result = await this.$store.dispatch("users/changePassword", userInfo);
      if(result === "success") {
        this.$router.replace("/");
      }
    }
  },
};
</script>

<style scoped>
.btn {
  background-color: #00002f;
  border: 3px solid #00002f;
  color: white;
  cursor: pointer;
  border-radius: 30px;
  display: inline-block;
  transition: 0.3s ease-out;
}

.btn:hover {
  color: #FF5E46;
}
</style>
