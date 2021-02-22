<template>
  <div class="container font-S-CoreDream-medium">
    <section>
      <span class="font-bold mb-10">당신의 칵테일을 찾아보세요</span>
    </section>
    <section>
      <base-button class="px-6 py-2" mode="outline" link to="/recommendation/form"
        >내 취향대로 칵테일 추천받으러 가기</base-button
      >
    </section>
  </div>
</template>

<style scoped>
div {
  display: flex;
  flex-direction: column;
  margin: 70px auto;
  width: 1060px;
  height: 465px;
  justify-content: center;
  align-items: center;
  background: linear-gradient(
    100.06deg,
    #9943e2 -5.4%,
    #ff4266 30.23%,
    #ff5e46 50.89%,
    #ffbd31 71.1%,
    rgba(255, 189, 49, 0.45) 88.14%
  );
  border-radius: 45px;
}

section {
  align-items: center;
}

span {
  font-size: 36px;
  line-height: 93px;
  display: flex;
  align-items: center;

  color: white;
}
</style>
