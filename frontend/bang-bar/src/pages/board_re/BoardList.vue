<template>
  <div class="flex flex-col justify-items-center px-16">
    <span
      class="text-center my-10 text-6xl font-S-CoreDream-medium font-bold font-color-black-400"
      >게시판</span
    >
    <section id="search-bar" class="flex flex-row mb-6 px-16 font-S-CoreDream-light">
      <article class="flex justify-center justify-self-start">
        <base-button mode="important" @click="writeContent" >글쓰기</base-button>
      </article>
      <article class="flex flex-1"></article>
      <article class="flex justify-center justify-self-end">

<!-- 제목검색으로 변경 -->

        <div class="inline-block relative w-max">
          <select
            class="block appearance-none w-full text-lg bg-white hover:bg-gray-100 px-10 py-2 rounded-full shadow-lg leading-tight border-4 border-transparent focus:outline-none focus:shadow-outline"
          >
            <option>전체</option>
            <option>제목</option>
            <option>내용</option>
            <option>작성자</option>
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
        <div class="mx-4 flex-auto inline-block">
          <input
            class="text-lg text-left shadow-lg appearance-none rounded-full w-full px-10 py-2 leading-tight border-4 border-transparent hover:bg-gray-100 focus:outline-none focus:shadow-outline focus:border-gray-200"
            id="search"
            type="text"
            placeholder="검색"
            v-model="keyword"
            @keydown.enter="search_board()"
          />
        </div>
        <base-button @click="search_board()">검색</base-button>
      </article>
    </section>
    <paginated-list :list-array="boardList" />
  </div>
</template>


<script>
import PaginatedList from './PaginatedList.vue';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  components: { 
    PaginatedList,
  },
  
  name: "BoardList",
  data() {    
    // User 와 Content 의 user_id 의 같은 번호를 찾아 Content 에 기존자료 + 'user_name' 으로 추가한다.
    return {
      writemode:null,
      keyword:"",
      fields: [
        {
          key: "category",
          label: "카테고리"
        },
        {
          key: "title",
          label: "제목"
        },
        {
          key: "user_name",
          label: "글쓴이"
        },
        {
          key: "created_at",
          label: "작성일"
        },
        {
          key: "hits",
          label : "조회수"
        }
      ],
      boards: [],
      pageArray:this.boards
    };
  },
  methods: {
    //글 상세보기로 이동
    rowClick(item) {
      // alert(item.content_id)
      
      this.$router.push({
        path: `/board/detail/${item.content_id}`
      });
    },
    //로그인 체크
    writeContent() {
      if(localStorage.getItem('pk_user')!=null){
      this.$router.push({
        path: `/board/create`
      });
    }else{
      alert("로그인을 해주세요.")
    }
    },

    //글 목록 불러오기 - 목록을 boards에 저장
    async loadBoard(refresh = true) {
      this.isLoading = true;
      try {
        await this.$store.dispatch("boards/loadBoard", {
          forceRefresh: refresh,
        });
      } catch (error) {
        this.error =
          error.message || "게시판을 불러오는데 문제가 발생했습니다.";
      }
      this.isLoading = false;
      console.log(this.$store)
    },
    handleError() {
      this.error = null;
    },


    //검색 
    search_board(){
      this.axios.get(`${SERVER_URL}/forum/search-forum-list/`+this.keyword, {
        headers: {
        'Access-Control-Allow-Origin': '*',
        'Content-Type': 'application/json; charset = utf-8',
        "Access-Control-Allow-Headers": "*",
        }
      })
      .then((result)=>{
        // this.items=result;
        console.log(result)
        this.boards = result.data
      })
      .catch(e=>{
        console.log('error:',e)
      })
    }
  },
  
  computed: {

    //boards에 데이터를 넣는 과정
    boardList() {
      const boards = this.$store.getters["boards/boards"]; //모듈/getters
      console.log("boardList() 함수 실행 ")
      return boards;
    },
    //데이터가 있는지 확인
    hasRecipes() {
      return !this.isLoading && this.$store.getters["boards/hasBoards"];
    },
    rows() {
      return this.boards.length;
    }
  },
  created() {
    this.pageArray=this.boards;
    this.writemode=localStorage.getItem("pk_user");
    this.loadBoard();
  },
  mounted() {
      // this.loadBoard()
      // console.log(this.items);
  },

};

</script>
<style scoped>
    .board{
        /* width:50%; */

        background-color: #FFFFFF;
        text-decoration: none;
        padding: 0.75rem 1.5rem;
        font: inherit;
        /* border: 0.1px solid #FFFFFF; */
        
        border-radius: 30px;
        margin-right: 0.5rem;
        /* display: inline-block; */
        transition: .3s ease-out;
        margin-bottom: 15px;
    }
</style>