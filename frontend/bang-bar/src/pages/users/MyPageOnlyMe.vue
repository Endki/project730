<template>
  <div style="flex flex-row font-color-black-400 font-S-CoreDream-light">
    <div class="flex grid grid-cols-4 grid-flow-row gap-4 justify-items-center">
      <section class="mt-10 mx-auto">
        <user-menu></user-menu>
      </section>

      <section class="mt-10 mb-24 mx-auto col-span-3 w-full">
        <router-view v-slot="slotProps">
          <transition name="route" mode="out-in">
      <component :is="slotProps.Component"></component>
    </transition>
        </router-view>
      </section>
    </div>
  </div>
</template>

<script>
import UserMenu from '../../components/users/UserMenu.vue';

export default {
  components: {
    UserMenu,
  },
};
</script>

<style scoped>
</style>
