<template>
  <div class="font-S-CoreDream-light font-color-black-400 py-12">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
      <div class="text-center">
        <h2 class="text-base tracking-wide uppercase font-extrabold">
          Contact Bangba
        </h2>
        <p
          class="font-Mapo-DPPA mt-2 text-3xl leading-8 text-gray-900 sm:text-4xl leading-relaxed"
        >
          문의
        </p>
        <p
          class="mt-4 max-w-2xl text-lg font-color-black-200 lg:mx-auto tracking-wide leading-relaxed"
        >
          방구석 바텐더 웹사이트에 대해 궁금하신 모든 사항은 환영입니다!
        </p>
      </div>

      <div class="mt-20">
        <form
          class="w-full mx-auto max-w-3xl bg-white shadow p-8 text-gray-700 card"
        >
          <div class="flex flex-wrap mb-6">
            <div class="relative w-full appearance-none label-floating">
              <input
                class="text-left tracking-wide py-2 px-4 mb-3 leading-relaxed appearance-none block w-full bg-gray-200 border border-gray-200 rounded focus:outline-none focus:bg-white focus:border-gray-500"
                id="name"
                type="text"
                placeholder="성함을 적어 주세요"
                required
              />
              <label
                for="name"
                class="absolute tracking-wide py-2 px-4 mb-4 opacity-0 leading-tight block top-0 left-0 cursor-text"
              >
                성함
              </label>
            </div>
          </div>
          <!-- email field -->
          <div class="flex flex-wrap mb-6">
            <div class="relative w-full appearance-none label-floating">
              <input
                class="text-left tracking-wide py-2 px-4 mb-3 leading-relaxed appearance-none block w-full bg-gray-200 border border-gray-200 rounded focus:outline-none focus:bg-white focus:border-gray-500"
                id="name"
                type="text"
                placeholder="이메일을 적어 주세요"
                required
              />
              <label
                for="name"
                class="absolute tracking-wide py-2 px-4 mb-4 opacity-0 leading-tight block top-0 left-0 cursor-text"
              >
                이메일
              </label>
            </div>
          </div>
          <!-- Message field -->
          <div class="flex flex-wrap mb-6">
            <div class="relative w-full appearance-none label-floating">
              <textarea
                class="autoexpand tracking-wide py-2 px-4 mb-3 leading-relaxed appearance-none block w-full bg-gray-200 border border-gray-200 rounded focus:outline-none focus:bg-white focus:border-gray-500"
                id="message"
                type="text"
                placeholder="문의사항을 적어 주세요"
              ></textarea>
              <label
                for="message"
                class="absolute tracking-wide py-2 px-4 mb-4 opacity-0 leading-tight block top-0 left-0 cursor-text"
                >문의사항
              </label>
            </div>
          </div>

          <div class="">
            <button
              class="w-full shadow bg-gray-700 hover:bg-gray-800 focus:shadow-outline focus:outline-none text-white font-bold py-2 px-4 rounded-full"
              type="submit"
            >
              보내기
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<style scoped>

.card {
  border-radius: 30px;
  box-shadow: 0 10px 25px rgba(0, 0, 0, 0.2);
  background-color: white;
}

.label-floating input:not(:placeholder-shown),
.label-floating textarea:not(:placeholder-shown) {
  padding-top: 1.4rem;
}
.label-floating input:not(:placeholder-shown) ~ label,
.label-floating textarea:not(:placeholder-shown) ~ label {
  font-size: 0.8rem;
  transition: all 0.2s ease-in-out;
  color: #1f9d55;
  opacity: 1;
}
/* p {
  margin: 0;
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: bold;
  font-size: 64px;
  line-height: 93px;
  display: flex;
  align-items: center;

  color: #23232f;
} */
</style>
