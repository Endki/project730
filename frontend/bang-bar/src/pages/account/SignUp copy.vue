<template>
  <div>
    <section class="">
      <section>
        <img src="@/assets/img/BangBaJoin.png" />
        <span style="text-align:left text-2xl font-bold">회원가입<br />Sign up to Bang-Ba</span>
      </section>
      <div class="container">
        <form @submit.prevent="submitForm">
          <div class="joindiv">
            <label for="email">이메일</label>
            <input
              type="text"
              id="email"
              class="textarea"
              v-model="email"
              placeholder="cocktail@bangba.com"
              required
              
            />
            <base-button class="redbutton" @click="authMail_Send()">인증메일 전송</base-button>
          </div>
          <div class="joindiv">
            <label for="code">인증코드 입력</label>
            <input
              type="text"
              id="code"
              v-model="code"
              class="textarea"
              placeholder="이메일로 전송된 코드 입력"
              required
            />
            <base-button mode="outline" v-show="auth" @click="authCode_Check()">확인</base-button>
          </div>
          <div class="joindiv">
            <label for="phone_numver">전화번호</label>
            <input
              type="text"
              id="phone_numver"
              v-model="phone_number"
              class="textarea"
              placeholder="01012345678"
              required
              maxlength="11"
              oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
            />
            
          </div>
          <div class="joindiv">
            <label for="birthday">생년월일</label>
            <input type="date" class="date" v-model="date"/>
          </div>
          <div class="joindiv">
            <label for="nickname">닉네임</label>
            <input
              type="nickname"
              id="nickname"
              v-model="nickname"
              class="textarea"
              required
              pattern="{2,10}+"
              minlength="2"
              maxlength="10"
            />
            <base-button class="redbutton" @click="nickName_Check()">중복 확인</base-button>
            <div class="alertText">
              *닉네임은 최소 2자, 최대 10자까지 입력이 가능해요.<br />* 회원가입
              후에도 자유롭게 변경할 수 있어요.
            </div>
          </div>
          <div class="joindiv">
            <label for="password">비밀번호</label>
            <input
              type="password"
              id="password"
              v-model="password"
              class="textarea"
              minlength="8"
              maxlength="20"
              required
              pattern="^(?=.*[A-Za-z])(?=.[0-9])[A-Za-z0-9]+{8,20}$"
              @keyup="pass_Check()"
              
            />
            <span>{{ message }}</span>
            <div class="alertText">
              * 비밀번호는 영문과 숫자를 반드시 포함해 최소 8자, 최대 20자까지
              입력이 가능해요.
            </div>
          </div>
          <div class="joindiv">
            <label for="passwordConfirm">비밀번호 확인</label>
            <input
              type="password"
              id="passwordConfirm"
              v-model="passwordConfirm"
              class="textarea"
              minlength="8"
              maxlength="20"
              required
              pattern="^(?=.*[A-Za-z])(?=.[0-9])[A-Za-z0-9]+{8,20}$"
              @keyup="passDupl_Check()"
            />
            <!-- <a>{{ message }}</a> -->
            <span>{{ passmessage }}</span>
          </div>
          <!-- <div>{{ passmessage }}</div> -->

          <div class="joindiv">
            <label><input type="checkbox" name="check" value="check"  v-model="checked"/> </label>
            모든

            <base-modal @close="hideDialog" :open="dialogIsVisible_terms">
              <section class="modal-header">
                <img src="../../assets/img/logo.png" />
              </section>

              이용약관

              <section class="button-modal">
                <base-button @click="hideDialog_terms" mode="outline"
                  >취소</base-button
                >
              </section>
            </base-modal>
            <span
              @click="showDialog_terms"
              style="text-decoration:underline; font-weight:bold"
            >
              이용약관
            </span>
            과
            <base-modal @close="hideDialog" :open="dialogIsVisible_personal">
              <section class="modal-header">
                <img src="../../assets/img/logo.png" />
              </section>
              개인정보이용정책
              <section class="button-modal">
                <base-button @click="hideDialog_personal" mode="outline"
                  >취소</base-button
                >
              </section>
            </base-modal>
            <span
              @click="showDialog_personal"
              style="text-decoration:underline; font-weight:bold"
            >
              개인정보이용정책
            </span>
            에 동의합니다.
          </div>
          <div style="text-align:left">
            <base-button @click="join_Check()">회원 가입</base-button>
            <base-button mode="outline"  @click="goback()" >취소</base-button>
          </div>
        </form>
      </div>
    </section>
  </div>
</template>

<script>
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
export default {
  data() {  
    return {


      formIsValid: true,
      isLoading: false,
      error: null,

      tag: "",
      type: "",
      ingredient: "",
      quantity: null,
      unit: "",
      recipe: "",
      alcoholTemp: [],
      ingredientTemp: [],


      email: {
        val: "",
        isValid: true,
      },
      Vcode: {
        val: "",
        isValid: true,
      },
      phoneNumber: {
        val: null,
        isValid: true,
      },
      birth: {
        val: "",
        isValid: true,
      },
      nickName: {
        val: "",
        isValid: true,
      },
      password: {
        val: [],
        isValid: true,
      },
      passwordCheck: {
        val: [],
        isValid: true,
      },
      Terms: {
        val: [],
        isValid: true,
      },
      
    };
  },

  methods: {

    //메일 전송
      async authMail_Send(){
        if(!email.isValid){
          console.log("이메일 확인요망")
          return;
        }

        let emailData={
          email:this.email
        };
        console.log(emailData);
        await this.$store.dispatch("account/checkEmail",emailData);
        
      },
    //닉네임 중복확인 && 닉네임 공백확인
    async nickName_Check(){
      //닉네임 공백확인
      if(!nickName.val.length===0){
        alert("닉네임을 입력해주세요.")
        return;
      }

      let nameData={
        nickname: this.nickName
      };
      console.log(nameData);

      await this.$store.dispatch("account/checkName",nameData);
    },
    // 회원 가입
    
    //폼 last check
    validateForm() {
      if (this.email.val === "") {
        this.email.isValid = false;
        this.formIsValid = false;
      }
      if (!this.Vcode.val || this.Vcode.val < 0) {
        this.Vcode.isValid = false;
        this.formIsValid = false;
      }
      if (this.Vcode.val === "") {
        this.Vcode.isValid = false;
        this.formIsValid = false;
      }
      if (this.phoneNumber.val === "") {
        this.phoneNumber.isValid = false;
        this.formIsValid = false;
      }
      if (this.birth.val.length === 0) {
        this.birth.isValid = false;
        this.formIsValid = false;
      }
      if (this.nickName.val.length === 0) {
        this.nickName.isValid = false;
        this.formIsValid = false;
      }
      if (this.password.val.length === 0) {
        this.password.isValid = false;
        this.formIsValid = false;
      }
      if (this.passwordCheck.val.length === 0) {
        this.passwordCheck.isValid = false;
        this.formIsValid = false;
      }
      if (this.Terms.val.length === 0) {
        this.Terms.isValid = false;
        this.formIsValid = false;
      }
    },
  },


};
</script>

<style scope>
input {
  text-align: center;
  vertical-align: auto;
  margin: auto;
}
h1 {
  text-align: left;
}

img {
  /* text-align: left; */
  /* display: ; */
  float: left;
  /* align-content:inherit; */
  margin-right: 40px;
}
label {
  padding: 0.75rem 1.5rem;
  font-weight: 500;
}

.date,
.textarea {
  text-decoration: none;
  padding: 0.75rem 1.5rem;
  font: inherit;
  border: 0.1px solid #ffffff;
  border-radius: 30px;
  margin-right: 0.5rem;
  display: inline-block;
  transition: 0.3s ease-out;
}
.joindiv {
  margin: 20px;
}

.alertText {
  color: #c6c6c6;
  margin-left: 600px;
}

::placeholder {
  color: #c6c6c6;
}


input[type="number"]::-webkit-outer-spin-button,
input[type="number"]::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
}
</style>
