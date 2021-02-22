<template>
<div>
  <the-header></the-header>
    <transition name="route" mode="out-in">
    <router-view v-slot="slotProps">
      <component :is="slotProps.Component"></component>
  </router-view>
    </transition>
  <the-footer></the-footer>
</div>
</template>

<script>
import TheHeader from "./components/layout/TheHeader.vue";
import TheFooter from "./components/layout/TheFooter.vue";

export default {
  components: {
    TheHeader,
    TheFooter,

  },
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap");
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Abril+Fatface&display=swap');
/* font-family: 'Abril Fatface', cursive; */
.font-Abril-Fatface {
  font-family: 'Abril Fatface', cursive;
}

@font-face {
     font-family: 'NIXGONL-Vb';
     src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/NIXGONL-Vb.woff') format('woff');
     font-weight: normal;
     font-style: normal;
}

.font-Nixgon-light {
  font-family: 'NIXGONL-Vb';
}

@font-face {
     font-family: 'NIXGONM-Vb';
     src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/NIXGONM-Vb.woff') format('woff');
     font-weight: normal;
     font-style: normal;
}

.font-Nixgon-medium {
  font-family: 'NIXGONM-Vb';
}

@font-face {
     font-family: 'NIXGONB-Vb';
     src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/NIXGONB-Vb.woff') format('woff');
     font-weight: normal;
     font-style: normal;
}

.font-Nixgon-bold {
  font-family: 'NIXGONB-Vb';
}

@font-face {
    font-family: 'MapoDPPA';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/MapoDPPA.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

.font-Mapo-DPPA {
  font-family: 'MapoDPPA';
}

@font-face {
     font-family: 'S-CoreDream-3Light';
     src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-3Light.woff') format('woff');
     font-weight: normal;
     font-style: normal;
}

.font-S-CoreDream-light {
  font-family: 'S-CoreDream-3Light';
}

@font-face {
     font-family: 'S-CoreDream-5Medium';
     src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-5Medium.woff') format('woff');
     font-weight: normal;
     font-style: normal;
}

.font-S-CoreDream-medium {
  font-family: 'S-CoreDream-5Medium';
}

.font-color-black-400 {
  color: #23232F;
}

.font-color-black-300 {
  color: #747475;
}

.font-color-black-200 {
  color: #9A9A9A;
}

.font-color-black-100 {
  color: #C6C6C6;
}

.line-color-300 {
  color: #25253B;
}

.line-color-200 {
  color: #9FA2AB;
}

.line-color-100 {
  color: #DDDDDD;
}

.font-2xs {
  font-size: 0.7rem;
  line-height: 1.2rem;
}

.font-3xs {
  font-size: 0.5rem;
  line-height: 1rem;
}

.font-red {
  color: #FF0000;
}

.card-flat {
  border-radius: 25px;
  padding: 0.75rem 1rem;
  margin: 0.6rem 1rem;
  background-color: white;
}

* {
  box-sizing: border-box;
}

html {
  /* font-family: 'Noto Sans KR', sans-serif; */
  /* font-family: 'NIXGONM-Vb'; */
  font-family: 'S-CoreDream-3Light';
  color: #23232F;
  /* font-family: "Roboto", sans-serif; */
  background-color: #FFF5EB;
}

body {
  margin: 0;
}

input {
  outline: none;
}

.route-enter-from {
  opacity: 0;
  transform: translateY(-30px);
}

.route-leave-to {
  opacity: 0;
  transform: translateY(30px);
}

.route-enter-active {
  transition: all 0.3s ease-out;
}

.route-leave-active {
  transition: all 0.3s ease-in;
}

.route-enter-to,
.route-leave-from {
  opacity: 1;
  transform: translateY(0);
}


</style>
