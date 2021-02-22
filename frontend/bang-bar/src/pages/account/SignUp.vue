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
            <base-button class="redbutton" @click="phone_Check()">중복 확인</base-button>
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
      password:'',
      date: new Date(),
      checked:false,
      auth:true,
      authCode_Spring: "",
      passmessage:"",
      message:"",
      dialogIsVisible_terms: false,
      dialogIsVisible_personal: false,
      errors:[],
      finalCheck:false,
      phoneCheck:false,
      nameCheck:false,
      emailCheck:false
    };
  },

  methods: {

    authMail_Send(){
      if(this.email==null){
        alert("이메일을 입력해주세요.");
      }else{
        let params={
          email:this.email
        }
      const headers = {
      'Content-type': 'application/json; charset=UTF-8',
      'Accept': '*/*',
      'Access-Control-Allow-Origin': '*',
        "Access-Control-Allow-Headers": "*",
      }
        
      this.axios.post(`${SERVER_URL}/user/join/mail/`+this.email,
        JSON.stringify(params),
         { headers }
      )
      .then((result)=>{
        if(result.data=="FAIL"){
          alert("이미 가입한 이메일 입니다.")
        }else{
          this.authCode_Spring=result.data;
          alert("인증코드가 발송되었습니다.")
        }
          console.log(result)
      })
      .catch(e=>{
          console.log('error:',e)
      })
      }
    },

    authCode_Check(){
      if(this.authCode_Spring == this.code) {
        // this.auth=!this.auth
        this.emailCheck=true;
        alert("인증에 성공했습니다.")
      } else {
        alert("인증에 실패했습니다.")
      }
    },


    nickName_Check(){
      if(this.nickname==null || this.nickname.length<2){
        alert("닉네임을 확인해주세요.");
      }else{
        let params={
          user_name:this.nickname
        }

      const headers = {
      'Content-type': 'application/json; charset=UTF-8',
      'Accept': '*/*',
      'Access-Control-Allow-Origin': '*',
        "Access-Control-Allow-Headers": "*",
      }

        this.axios.post(`${SERVER_URL}/user/join/`+this.nickname,
        JSON.stringify(params),
        { headers }
      )
      .then((result)=>{

        if(result.data=="FAIL"){
          alert("중복된 닉네임 입니다.")
        }else{
          this.nameCheck=true
          alert("사용하실 수 있는 닉네임 입니다.")
        }
      })
      .catch(e=>{
          console.log('error:',e)
          
      })
      }
    },

    phone_Check(){
      if(this.phone_number == null || this.phone_number.length<10){
        alert("전화번호를 모두 입력해주세요.");
      }else{
        let params={
          phone_number:this.phone_number
        }

      const headers = {
      'Content-type': 'application/json; charset=UTF-8',
      'Accept': '*/*',
      'Access-Control-Allow-Origin': '*',
        "Access-Control-Allow-Headers": "*",
      }

        this.axios.post(`${SERVER_URL}/user/join/phone/`+this.phone_number,
        JSON.stringify(params),
        { headers }
      )
      .then((result)=>{

        if(result.data === 1){
          alert("중복된 전화번호 입니다.")
        }else{
          console.log(result.data)
          this.phoneCheck=true
          alert("사용하실 수 있는 전화번호 입니다.")
        }
      })
      .catch(e=>{
          console.log('error:',e)
          
      })
      }
    },

    join_Check(){
      
      //약관
      if(this.checked==false){
        alert("약관에 동의해주세요.");
      }
       // 이메일 인증 체크
      else if(this.emailCheck == false) {
        alert("이메일 인증을 진행해 주세요.")
      }
      //전화번호 길이 
      else if(this.phone_number.length<10 || this.phone_number.length>11 || this.phone_number.substr(0,3) != "010"){
        alert("잘못된 전화번호입니다.")
      } 
      else if(this.phoneCheck == false) {
        alert("이미 존재하는 핸드폰 번호입니다.")
      }
      //가입 생년월일
      else if(this.date.replace("-","").replace("-","")>20030000){
        alert("가입할 수 없는 생년월일입니다.")
      }
      else if(this.nameCheck == false) {
        alert("닉네임 중복확인을 해주세요.")
      }
      else if(this.password.length<8 || this.ispassCheck(this.password)){
        alert("비밀번호를 확인해 주세요.")
      }
      //비밀번호 체크
      else if(this.finalCheck==false){
        alert("비밀번호를 확인해주세요.")
      } 
      else{
        // var repl=this.date.replace("-","");
        let params={
          email: this.email,
          password: this.password,
          user_name: this.nickname,
          birth: this.date.replace("-","").replace("-",""),
          phone_number: this.phone_number,
          user_type:0,
          banned:false,
          img_path: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSaaNaTC8W_ygKLZxLFWpHOerfIYQiVlsuyrw&usqp=CAU",
        }
        const headers = {
          'Content-type': 'application/json; charset=UTF-8',
          'Accept': '*/*',
          'Access-Control-Allow-Origin': '*',
        "Access-Control-Allow-Headers": "*",
      }

        this.axios.post(`${SERVER_URL}/user/join`,
        JSON.stringify(params),
        { headers }
      )
      .then(()=>{
          // console.log(result)
          alert("회원가입이 완료되었습니다.");
          this.$router.push({
            path:'/home'
          });
      })
      .catch(e=>{
          console.log('error:',e)
      })
      }
    },
    passDupl_Check(){
      if(this.password===this.passwordConfirm ){
        this.passmessage="비밀번호가 동일합니다."
        this.finalCheck=true;
      }else{
        this.passmessage="비밀번호가 다릅니다."
        this.finalCheck=false;
      }
    },


    ispassCheck(password){  

      var check= new RegExp("^(?=.*[0-9])(?=.*[a-zA-Z]).{8,20}$");
        if(check.test(password)){
          return false;
        }
      return true;
    },


    goback(){
      this.$router.go(-1)
    },

    showDialog_terms() {
      this.dialogIsVisible_terms = true;
    },
    hideDialog_terms() {
      this.dialogIsVisible_terms = false;
    },
    showDialog_personal() {
      this.dialogIsVisible_personal = true;
    },
    hideDialog_personal() {
      this.dialogIsVisible_personal = false;
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
