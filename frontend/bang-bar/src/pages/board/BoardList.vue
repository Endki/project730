<template>
  <div class="flex flex-col justify-items-center px-16 font-color-black-400">
    <span class="text-center my-10 text-4xl font-S-CoreDream-medium font-bold ">게시판</span>

    <section id="search-bar" class="flex flex-row mb-6 mx-28 font-S-CoreDream-light">
      <article class="flex justify-center justify-self-start">
        <base-button mode="important" class="px-8 py-2" @click="writeContent">글쓰기</base-button>
      </article>

      <article class="flex flex-1"></article>
      <article class="flex justify-center justify-self-end">
        <!-- 말머리 선택 드롭다운 -->
        <div class="inline-block relative w-max">
          <select
            v-model="searchType"
            class="block appearance-none w-full text-base bg-white hover:bg-gray-100 px-10 py-2 rounded-full shadow-lg leading-tight border-4 border-transparent focus:outline-none focus:shadow-outline"
          >
            <option>제목</option>
            <option>내용</option>
          </select>
          <div
            class="pointer-events-none absolute inset-y-0 right-0 flex items-center px-2 text-gray-700"
          >
            <svg
              class="fill-current h-4 w-4"
              xmlns="http://www.w3.org/2000/svg"
              viewBox="0 0 20 20"
            >
              <path
                d="M9.293 12.95l.707.707L15.657 8l-1.414-1.414L10 10.828 5.757 6.586 4.343 8z"
              />
            </svg>
          </div>
        </div>
        <!-- 검색창 제목과 내용으로-->
        <div class="mx-4 flex-auto inline-block">
          <input
            class="text-base text-left shadow-lg appearance-none rounded-full w-full px-10 py-2 leading-tight border-4 border-transparent hover:bg-gray-100 focus:outline-none focus:shadow-outline focus:border-gray-200"
            id="search"
            type="text"
            placeholder="키워드 입력"
            v-model="keyword"
            @keydown.enter="getList()"
          />
        </div>
        <base-button class="px-6 py-2 text-base" @click="getList()">검색</base-button>
      </article>
    </section>

    <!-- 본문 내용 -->
    <section class="font-S-CoreDream-light flex flex-col justify-items-center">
      <!-- 게시판 테이블 -->
      <section class="mt-4 mx-32">
        <table
          class="corner w-full flex flex-row flex-no-wrap sm:bg-white overflow-hidden sm:shadow-lg my-5 font-color-black-400 table-fixed"
        >
          <thead role="rowgroup" class="border-none py-4">
            <tr role="row" class="font-S-CoreDream-light font-bold text-base">
              <th class="pt-4 pb-2 w-1/12">말머리</th>
              <th class="pt-4 pb-2 w-6/12">제목</th>
              <th class="pt-4 pb-2 w-2/12">작성자</th>
              <th class="pt-4 pb-2 w-1/12">작성일</th>
              <th class="pt-4 pb-2 w-1/12">조회수</th>
            </tr>
          </thead>
          <tbody class="text-center">
            <tr
              class="flex flex-col flex-no wrap sm:table-row mb-2 sm:mb-0 hover:bg-gray-100 hover:font-medium cursor-pointer text-base"
              @click="rowClick(item)"
              v-for="item in items"
              :key="item.no"
            >
              <td class="py-3 text-sm">{{ item.category }}</td>
              <td class="text-sm text-left truncate">
                {{ item.title }} <span class="text-xs">[ {{ item.comment_cnt }} ]</span>
              </td>
              <td class="text-sm truncate">{{ item.user_name }}</td>
              <td class="font-color-black-200 text-sm">
                {{ item.created_at }}
              </td>
              <td class="text-sm">{{ item.hits }}</td>
            </tr>
          </tbody>
        </table>
      </section>
      <!-- 페이지네이션바 -->
      <section>
        <div class="flex flex-col items-center my-12">
          <div class="flex text-gray-700 items-center">
            <!-- << -->
            <button
              class="h-5 w-5 mr-1 flex justify-center text-gray-500"
              @click="goStarat()"
              :disabled="!checkPrev"
              v-if="checkPrev"
            >
              <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor">
                <path
                  fill-rule="evenodd"
                  d="M15.707 15.707a1 1 0 01-1.414 0l-5-5a1 1 0 010-1.414l5-5a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 010 1.414zm-6 0a1 1 0 01-1.414 0l-5-5a1 1 0 010-1.414l5-5a1 1 0 011.414 1.414L5.414 10l4.293 4.293a1 1 0 010 1.414z"
                  clip-rule="evenodd"
                />
              </svg>
            </button>
            <!-- < -->
            <button
              class="h-5 w-5 mr-1 flex justify-center items-center text-gray-500 "
              @click="prevPage()"
              :disabled="!checkPrev"
            >
              <svg
                xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 20 20"
                fill="currentColor"
                class=""
              >
                <path
                  fill-rule="evenodd"
                  d="M12.707 5.293a1 1 0 010 1.414L9.414 10l3.293 3.293a1 1 0 01-1.414 1.414l-4-4a1 1 0 010-1.414l4-4a1 1 0 011.414 0z"
                  clip-rule="evenodd"
                />
              </svg>
            </button>
            <!-- 숫자 -->
            <ul class="flex h-8 font-medium ">
              <li
                class="w-9 md:flex justify-center items-center hidden cursor-pointer leading-5 transition duration-150 ease-in border-t-2 border-transparent"
                v-for="page in pageRangePart"
                :key="page"
                @click="goPageNum(page - 1)"
              >
                {{ page }}
              </li>
            </ul>
            <!-- > -->
            <button
              type="button"
              class="h-5 w-5 mr-1 flex justify-center items-center text-gray-500"
              @click="nextPage()"
              :disabled="!checkNext"
            >
              <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor">
                <path
                  fill-rule="evenodd"
                  d="M7.293 14.707a1 1 0 010-1.414L10.586 10 7.293 6.707a1 1 0 011.414-1.414l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414 0z"
                  clip-rule="evenodd"
                />
              </svg>
            </button>
            <!-- >> -->
            <button
              type="button"
              class="h-5 w-5 mr-1 flex justify-center text-gray-500"
              :disabled="!checkNext"
              v-if="checkNext"
              @click="goLast()"
            >
              <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor">
                <path
                  fill-rule="evenodd"
                  d="M10.293 15.707a1 1 0 010-1.414L14.586 10l-4.293-4.293a1 1 0 111.414-1.414l5 5a1 1 0 010 1.414l-5 5a1 1 0 01-1.414 0z"
                  clip-rule="evenodd"
                />
                <path
                  fill-rule="evenodd"
                  d="M4.293 15.707a1 1 0 010-1.414L8.586 10 4.293 5.707a1 1 0 011.414-1.414l5 5a1 1 0 010 1.414l-5 5a1 1 0 01-1.414 0z"
                  clip-rule="evenodd"
                />
              </svg>
            </button>
          </div>
        </div>
      </section>
    </section>
  </div>
</template>

<script>
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  components: {},

  name: 'BoardList',
  data() {
    return {
      writemode: null,
      pk_forum: '',
      pageNum: 0,
      keyword: '',
      searchType: '제목',
      pageTotalCnt: '', // 전체 페이지 수
      items: [],
      pageArray: this.items,
      time_conver: '',
      forumCntPerPage: 15, // 한 페이지 당 보여질 게시글 수
      rangeCnt: 5, // 한 페이지에 보여지는 범위의 수
      pageRange: 1,
      endNumPerPage: '', // 해당 페이지에서 보여지는 마지막 페이지 수
      checkNext: '', // 다음페이지 버튼 활성화
      checkPrev: '', // 이번페이지 버튼 활성화
      pageRangePart: [],
    };
  },

  methods: {
    writeContent() {
      if (localStorage.getItem('pk_user') != null) {
        this.$router.push({
          path: `/board/create`,
        });
      } else {
        alert('로그인을 해주세요.');
      }
    },

    fnView() {
      // alert("이동!");
      <router-link to="/board/view"></router-link>;
    },
    rowClick(item) {
      this.$router.push({
        path: `/board/detail/${item.pk_forum}`,
        params: { user_no: item.user_no },
      });
    },

    getList() {
      let params = {
        page_num: this.pageNum + 1,
        page_range: this.pageRange,
        search_type: this.searchType,
        keyword: this.keyword,
        forum_cnt_per_page: this.forumCntPerPage,
      };

      this.axios
        .get(`${SERVER_URL}/forum/search-forum-list`, {
          headers: {
            'Access-Control-Allow-Origin': '*',
            'Content-Type': 'application/json; charset = utf-8',
            'Access-Control-Allow-Headers': '*',
          },
          params,
        })
        .then((result) => {
          this.items = result.data;
          this.convert_time();
          this.get_length();
          this.get_end_page_num();
        })
        .catch((e) => {
          console.log('error:', e);
        });
    },

    get_length() {
      let params = {
        search_type: this.searchType,
        keyword: this.keyword,
      };
      this.axios
        .get(`${SERVER_URL}/forum/forum_cnt`, {
          headers: {
            'Access-Control-Allow-Origin': '*',
            'Content-Type': 'application/json; charset = utf-8',
            'Access-Control-Allow-Headers': '*',
          },
          params,
        })
        .then((result) => {
          this.pageTotalCnt = Math.ceil(result.data / this.forumCntPerPage);
          console.log('pageTotalCnt : ' + this.pageTotalCnt);
        })
        .catch((e) => {
          console.log('error:', e);
        });
    },

    convert_time() {
      for (var i = 0; i < this.items.length; i++) {
        var Y = String(this.items[i].created_at).substring(0, 4);
        var M = String(this.items[i].created_at).substring(4, 6);
        var D = String(this.items[i].created_at).substring(6, 8);

        var H = String(this.items[i].created_at).substring(8, 10);
        var Min = String(this.items[i].created_at).substring(10, 12);
        var S = String(this.items[i].created_at).substring(12, 14);

        //현재 월
        let month = new Date().getMonth() + 1; // 월
        let date = new Date().getDate(); // 날짜

        if (month < '10') {
          month = '0' + month;
        }
        if (date < '10') {
          date = '0' + date;
        }
        var answer = '';
         if (String(month) === M && D === String(date)) {
          answer = H + ':' + Min + ':' + S;
        } else {
          answer = Y + '.' + M + '.' + D;
        }
        this.items[i].created_at = answer;
      }
    },

    //페이지네이션 함수
    nextPage() {
      this.pageNum = this.pageRange * this.rangeCnt;
      this.pageRange = this.pageRange + 1;
      this.getList();
    },
    prevPage() {
      this.pageRange = this.pageRange - 1;
      this.pageNum = this.rangeCnt * this.pageRange - 1;
      this.getList();
    },

    goPageNum(idx) {
      this.pageNum = idx;
      this.getList();
    },

    get_end_page_num() {
      if (this.pageTotalCnt > this.rangeCnt * this.pageRange) {
        this.endNumPerPage = this.rangeCnt * this.pageRange;
      } else {
        this.endNumPerPage = this.pageTotalCnt;
      }

      this.pageRangePart = [];
      for (let i = (this.pageRange - 1) * this.rangeCnt; i < this.endNumPerPage; i++)
        this.pageRangePart.push(i + 1);

      console.log('pageRangePage' + this.pageRangePart);
      this.checkPrev = this.pageRange === 1 ? false : true;
      this.checkNext = this.pageTotalCnt < this.rangeCnt * this.pageRange ? false : true;
    },
  },

  computed: {
    rows() {
      return this.items.length;
    },
  },
  created() {
    this.pageArray = this.items;
    this.writemode = localStorage.getItem('pk_user');
    this.getList();
    this.get_length();
    this.get_end_page_num();
  },
  mounted() {
    this.getList();
    this.get_length();
  },
  update() {
    console.log('!');
  },
};
</script>
<style scoped>
.board {
  /* width:50%; */

  background-color: #ffffff;
  text-decoration: none;
  padding: 0.75rem 1.5rem;
  font: inherit;
  /* border: 0.1px solid #FFFFFF; */

  border-radius: 30px;
  margin-right: 0.5rem;
  /* display: inline-block; */
  transition: 0.3s ease-out;
  margin-bottom: 15px;
}
@media (min-width: 640px) {
  table {
    display: inline-table !important;
  }
  thead tr:not(:first-child) {
    display: none;
  }
}
/* td:not(:last-child) {
  border-bottom: 0;
}
th:not(:last-child) {
  border-bottom: 2px solid rgba(0, 0, 0, 0.1);
} */
th {
  border-bottom: 2px solid rgba(0, 0, 0, 0.1);
}
.corner {
  border-radius: 45px;
}
</style>
