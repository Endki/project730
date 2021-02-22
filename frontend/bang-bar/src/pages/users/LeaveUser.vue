<template>
  <div class="flex flex-col font-S-CoreDream-light font-color-black-400">
    <span
      class="tracking-wider font-semibold text-2xl border-gray-800 border-b-4 font-S-CoreDream-medium w-max px-1 mt-10"
      >탈퇴하기</span
    >
    <section class="mt-36 mb-28">
      <span class="font-S-CoreDream-medium text-right mr-10 mb-10"
        >비밀번호 입력</span
      >
      <input
        type="password"
        class="text-sm text-left rounded-full px-8 py-3 mb-4 border-3 border-transparent hover:bg-gray-100 focus:outline-none"
        id="password"
        v-model="currentPassword"
      />
    </section>
    <section class="page__toggle">
      <label class="toggle">
        <input class="toggle__input" type="checkbox" v-model="leaveChecked" />
        <span class="toggle__label">
          <span
            class="toggle__text text-lg font-S-CoreDream-medium text-right mr-10 mb-10"
            >정말 탈퇴하시겠습니까?</span
          >
        </span>
      </label>
    </section>
    <section class="mt-4">
      <base-button mode="outline" class="btn px-4 py-2" @click="checkPW">탈퇴하기</base-button>
      <base-button class="px-4 py-2">취소</base-button>
    </section>
  </div>
</template>

<script>
export default {
  data() {
    return {
      leaveChecked: false,
    };
  },
  watch: {
    leaveChecked: function(value) {
      console.log(value);
    },
    $route(to) {
      if (to.path === `/` || to.path === `/home`) { 
        location.reload();
      } 
    } 
  },
  methods: {
    async checkPW() {
      const result = await this.$store.dispatch("users/confirmPW", this.currentPassword);
      if(result === "FAIL") {
        alert("현재 비밀번호가 아닙니다.");
        return;
      } else {
        //탈퇴 로직처리 
        this.leaveUser();
      }
    },
    async leaveUser() {
      if(!this.leaveChecked) {
        alert("체크박스를 확인해주세요.");
        return;
      }
      const result = await this.$store.dispatch("users/leave");
      if(result === "success") {
        this.$router.replace("/");
      }
    }
  }
}
</script>

<style scoped>
.btn {
  margin: 0 1rem 0 0;
}

/*
https://codepen.io/melnik909/pen/YjGZqQ
=====
CORE STYLES
=====
*/

.toggle {
  --uiToggleSize: var(--toggleSize, 1.25rem);
  --uiToggleBorderWidth: var(--toggleBorderWidth, 2px);
  --uiToggleColor: var(--toggleColor, #222);

  display: var(--toggleDisplay, inline-flex);
  position: relative;
}

.toggle__input {
  /*
  The pattern by Sara Soueidan https://www.sarasoueidan.com/blog/inclusively-hiding-and-styling-checkboxes-and-radio-buttons/
  */
  width: var(--uiToggleSize);
  height: var(--uiToggleSize);
  opacity: 0;

  position: absolute;
  top: 0;
  left: 0;
  margin: 0;
}

/*
1. Calculation of the gap for the custom checkbox
*/

.toggle__label {
  display: inline-flex;
  /* min-height: var(--uiToggleSize);
  padding-left: calc(var(--uiToggleSize) + var(--toggleIndent, 0.4em)); */
}

.toggle__input:not(:disabled) ~ .toggle__label {
  cursor: pointer;
}

/*
1. Ems helps to calculate size of the checkbox
*/

.toggle__label::after {
  content: "";
  box-sizing: border-box;
  width: 1em;
  height: 1em;
  font-size: var(--uiToggleSize); /* 1 */

  background-color: transparent;
  border: var(--uiToggleBorderWidth) solid var(--uiToggleColor);

  position: absolute;
  left: 0;
  top: 1rem;
  z-index: 2;
}

.toggle__input:checked ~ .toggle__label::after {
  background-color: var(--uiToggleColor);
}

.toggle__text {
  margin-top: auto;
  margin-bottom: auto;
}

/*
The arrow size and position depends from sizes of square because I needed an arrow correct positioning from the top left corner of the element toggle

1. Ems helps to calculate size and position of the arrow
*/

.toggle__label::before {
  content: "";
  width: 0;
  height: 0;
  font-size: var(--uiToggleSize); /* 1 */

  border-left-width: 0;
  border-bottom-width: 0;
  border-left-style: solid;
  border-bottom-style: solid;
  border-color: var(--toggleArrowColor, #fff);

  position: absolute;
  top: 1.3428em;
  left: 0.25em;
  z-index: 3;

  transform-origin: left top;
  transform: rotate(-40deg) skew(10deg);
}

.toggle__input:checked ~ .toggle__label::before {
  --uiToggleArrowWidth: var(--toggleArrowWidth, 2px);

  width: 0.4em;
  height: 0.2em;
  border-left-width: var(--uiToggleArrowWidth);
  border-bottom-width: var(--uiToggleArrowWidth);
}

/*
=====
PRESENTATION STYLES
=====
*/

/* 
The demo skin
*/

.toggle__label::after {
  border-radius: var(--toggleBorderRadius, 2px);
}

/* 
The animation of switching states
*/

.toggle__input:not(:disabled) ~ .toggle__label::before {
  will-change: width, height;
  opacity: 0;
}

.toggle__input:not(:disabled):checked ~ .toggle__label::before {
  opacity: 1;
  transition: opacity 0.1s ease-out 0.25s, width 0.1s ease-out 0.5s,
    height 0.2s ease-out 0.3s;
}

.toggle__input:not(:disabled) ~ .toggle__label::after {
  will-change: background-color;
  transition: background-color 0.2s ease-out;
}

/*
=====
SETTINGS
=====
*/

.page__custom-settings {
  --toggleColor: #690e90;
  --toggleOutlineColorFocus: #690e90;
  --toggleSize: 2rem;
}

/*
=====
DEMO
=====
*/

.page__toggle {
  /* margin-top: 1.25rem;
  margin-right: 1.5rem; */
  font-size: var(--pageToggleTextFontSize);
}
</style>
