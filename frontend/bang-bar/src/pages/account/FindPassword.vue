<template>
  <div>
    <p>비밀번호 찾기</p>
    <div class="container" v-show="!auth">
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
            <base-button mode="outline" @click="authCode_Check()">확인</base-button>
          </div>
    </div>
    <div class="container" v-show="auth">
      <div class="joindiv">
            <label for="password">새로운 비밀번호</label>
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
          <base-button mode="outline" @click="password_Change()">비밀번호 변경하기</base-button>
    </div>
  </div>
</template>

<script>
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  data() {
    return {
      emailCheck:false, 
      finalCheck:false,
      auth:false, 
      authCode_Spring: "", 
      message:"",
      passmessage:"",
    }
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
        
      this.axios.post(`${SERVER_URL}/user/help/${this.email}`,
        JSON.stringify(params),
         { headers }
      )
      .then((result)=>{
        if(result.data=="FAIL"){
          alert("존재하지 않는 이메일 입니다.")
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
        this.auth=!this.auth
        this.emailCheck=true;
        alert("인증에 성공했습니다.")
      } else {
        alert("인증에 실패했습니다.")
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
    password_Change() {
      if(!this.finalCheck || this.ispassCheck(this.password)) {
        // console.log(this.finalCheck);
        // console.log(this.password);
        // console.log(this.ispassCheck(this.password));
        alert("비밀번호를 체크해주세요.");
      } else {
        let params={
          email:this.email,
          password:this.password
        }
        const headers = {
        'Content-type': 'application/json; charset=UTF-8',
        'Accept': '*/*',
        'Access-Control-Allow-Origin': '*',
          "Access-Control-Allow-Headers": "*",
        }
          
        this.axios.put(`${SERVER_URL}/user/mypage/options/pw`,
          JSON.stringify(params),
          { headers }
        )
        .then(()=>{
            alert("비밀번호가 변경되었습니다. 다시 로그인을 시도해주세요.");
            this.$router.push({
            path:'/home'
          });
        })
        .catch(e=>{
            console.log('error:',e)
        })
        
      }
    },
    ispassCheck(password){  
      var check= new RegExp("^(?=.*[0-9])(?=.*[a-zA-Z]).{8,20}$");
        if(check.test(password)){
          return false;
        }
      return true;
    },
  }
}
</script>

<style scoped>
p {
  margin: 0;
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: bold;
  font-size: 64px;
  line-height: 93px;
  display: flex;
  align-items: center;

  color: #23232f;
}
</style>
