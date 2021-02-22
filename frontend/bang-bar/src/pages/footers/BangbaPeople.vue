<template>
  <div class="py-12 font-S-CoreDream-light font-color-black-400 flex flex-col">
    <div class="text-center">
      <h2 class="text-base tracking-wide uppercase font-extrabold">
        Meet our team
      </h2>
      <p
        class="font-Mapo-DPPA mt-2 text-3xl leading-8 text-gray-900 sm:text-4xl leading-relaxed"
      >
        방구석 사람들
      </p>
      <p
        class="mt-4 max-w-2xl text-lg font-color-black-200 lg:mx-auto tracking-wide leading-relaxed"
      >
        싸피 공통프로젝트로 모여 이루어진 방구석 바텐더 개발팀을 소개합니다!
      </p>
    </div>
    <div class="mt-20 mx-16 flex grid grid-cols-3">
      <section
        v-for="person in people"
        :key="person.name"
        class="flex flex-col justify-items-center mt-4 mb-10"
      >
        <img
          class="mx-auto rounded-full object-cover bg-white w-36 h-36 p-4"
          :src="getImgsrc(person.profileImage)"
          alt="profile image"
        />
        <span
          class="text-2xl leading-6 font-S-CoreDream-medium tracking-widest font-bold mx-auto mt-4"
        >
          {{ person.name }}
        </span>
        <span
          class="mt-2 text-base font-S-CoreDream-medium tracking-wide mx-auto"
          >{{ person.role }}</span
        >
        <article class="mx-auto">
          <base-card class="w-96">
            <span
              class="flex m-2 tracking-wide leading-7 whitespace-pre-line text-base text-color-black-300"
            >
              {{ person.description }}
            </span>
          </base-card>
        </article>
      </section>
    </div>
  </div>
</template>

<script>
import bangbaInfo from "../../data/people.js";
export default {
  data() {
    return {
      people: bangbaInfo.bangbaPeople,
    };
  },
  methods: {
    getImgsrc(imageName) {
      return require("../../assets/" + imageName);
    },
  },
};
</script>

<style scoped>
/* p {
  margin: 0;
  font-style: normal;
  font-weight: bold;
  font-size: 64px;
  line-height: 93px;
  display: flex;
  align-items: center;

  color: #23232f;
} */
</style>
