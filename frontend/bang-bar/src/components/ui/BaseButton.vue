<template>
  <button v-if="!link" :class="mode">
    <slot></slot>
  </button>
  <router-link v-else :to="to" :class="mode">
    <slot></slot>
  </router-link>
</template>

<script>
export default {
  props: {
    mode: {
      type: String,
      required: false,
      default: null,
    },
    link: {
      type: Boolean,
      required: false,
      default: false,
    },
    to: {
      type: String,
      required: false,
      default: "/",
    },
  },
};
</script>

<style scoped>
button,
a {
  text-decoration: none;
  /* padding: 0.75rem 1.5rem; */
  /* font: inherit; */
  background-color: #00002f;
  border: 3px solid #00002f;
  color: white;
  cursor: pointer;
  border-radius: 30px;
  margin-right: 0.5rem;
  display: inline-block;
  transition: 0.3s ease-out;
}

a:hover,
a:active,
button:hover,
button:active {
  color: #ff5e46;
}

a:focus,
button:focus {
  outline: none;
}

.flat {
  background-color: transparent;
  color: #270041;
  border: none;
}

.outline {
  background-color: transparent;
  border: 3px solid #00002f;
  color: #00002f;
}

.redbutton:hover,
.flat:hover,
.flat:active,
.outline:hover,
.outline:active {
  color: white;
  background-color: #00002f;
}

/* SJ의 추가 css */
.redbutton {
  background-color: #ff5e46;
  border: 1px solid #ffffff;
}

.important {
  background-color: #ff5e46;
  border: 1px solid #ffffff;
}

.important:hover {
  color: #00002f;
  background-color: #ff5e46;
}

.nude {
  color: #00002f;
  background-color: transparent;
  border: 1px solid transparent;
}

.nude:hover,
.nude:active {
  color: white;
  background-color: #00002f;
}

.outline-colored:hover,
.outline-colored:active {
  color: #00002f;
  background-color: #56e6a5;
}
.colored {
  color: #00002f;
  background-color: #56e6a5;
}

.colored:hover,
.colored:active {
  color: white;
  background-color: #00002f;
}
</style>
