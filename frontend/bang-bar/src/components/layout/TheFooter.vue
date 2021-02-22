<template>
  <footer class="mt-12 mb-10">
    <div class="flex flex-col justify-items-center">
      <section>
      <router-link to="/home">
        <article class="logo flex flex-col justify-items-center w-max">
          <img src="../../assets/img/logo.png" alt="logo-image" />
          <span
            class="text-lg text-center font-color-black-400 font-Mapo-DPPA mt-2"
            >방구석 바텐더</span
          >
        </article>
      </router-link>
      </section>
      <section class="">
        <nav class="font-color-black-400 text-base">
          <ul>
            <li><router-link to="/">방바 가이드</router-link></li>
            <li><router-link to="/footer/people">방바 사람들</router-link></li>
            <li><router-link to="/footer/ask">문의</router-link></li>
          </ul>
        </nav>
      </section>
      <section class="my-6">
        <span class="font-S-CoreDream-light text-sm"
          >2021 ⓒ 방구석 바텐더 All rights reserved</span
        >
      </section>
    </div>
  </footer>
</template>

<script>
export default {};
</script>

<style scoped>
* {
  text-align: center;
}

nav {
  font-family: "S-CoreDream-5Medium";
}

section {
  align-items: center;
}

img {
  margin: auto;
}

.logo {
  font-size: 12pt;
  border-radius: 50%;
  margin: 1rem;
  padding: 0.5rem;
  transition: 0.3s ease-out;
}

.logo p {
  margin: 0;
}

.logo:hover {
  /* border-bottom: 3px solid #FF5E46; */
  background: #ff5e46;
}

nav {
  width: 100%;
}

footer {
  width: 100%;
  height: auto;
}

footer a {
  text-decoration: none;
  color: #23232f;
  display: inline-block;
  padding: 0.25rem 1rem;
  border: 1px solid transparent;
  transition: 0.3s ease-out;
}

a:active,
a:hover,
a.router-link-active {
}

li a {
  border-radius: 50px;
}

li a:hover,
li a:active,
li a.router-link-active {
  color: white;
  background-color: #23232f;
  border-radius: 25px;
}

footer nav {
  width: 90%;
  margin: auto;
}

footer ul {
  list-style: none;
  margin: 0;
  padding: 0;
  display: flex;
  justify-content: center;
  align-items: center;
}

li {
  margin: 0 0.5rem;
  /* width: 140px; */
}
</style>
