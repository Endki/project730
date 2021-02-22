<template>
  <div>
    <section class="tabs">
      <base-tab
        v-for="item in list"
        :key="item.id"
        :id="item.id"
        :label="item.label"
        :value="currentId"
        v-model="currentId"
        v-on:input="currentTab"
      ></base-tab>
    </section>
    <section class="contents">
      <transition name="route" mode="out-in">
        <section class="content" :key="currentId">
          {{ current.content }}
        </section>
      </transition>
    </section>
  </div>
</template>

<script>
export default {
  data() {
    return {
      currentId: 1,
      list: [
        { id: 1, label: "레시피", content: "콘텐츠1" },
        { id: 2, label: "게시글", content: "콘텐츠2" },
      ],
    };
  },
  computed: {
    current() {
      return this.list.find(el => el.id === this.currentId) || {}
    }
  },
  methods: {
    currentTab(currentId) {
      this.currentId = currentId;
    }
  },
};
</script>

<style scoped>
.contents {
  position: relative;
  overflow: hidden;
  width: 280px;
  
  background-color: white;
  border: 2px solid #000;
}
.content {
  /* box-sizing: border-box;
  padding: 10px;
  width: 100%;
  transition: all 0.8s ease; */
   padding: 30px;
    border: 1px solid #ccc;
    border-radius: 10px;
    box-shadow: 3px 3px 6px #e1e1e1
}
/* 트랜지션 전용 스타일 */
.v-leave-active {
  position: absolute;
  transition: all 0.3s ease-in-out;
}
.v-enter {
  opacity: 0;
  transform: translateX(-100%);
}
.v-leave-to {
  transform: translateX(100%);
}
</style>