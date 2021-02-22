<template>
  <div class="font-S-CoreDream-light flex flex-col justify-items-center">
    <section class="mt-4 mx-32">
      <table
        class="corner w-full flex flex-row flex-no-wrap sm:bg-white overflow-hidden sm:shadow-lg my-5 font-color-black-400"
      >
        <thead role="rowgroup" class="border-none py-4">
          <tr role="row" class="font-S-CoreDream-light font-bold text-lg">
            <th class="py-4 px-3">말머리</th>
            <th class="w-3/7">제목</th>
            <th class="w-1/7">작성자</th>
            <th class="w-1/7">작성일</th>
            <th class="w-1/7">조회수</th>
          </tr>
        </thead>
        <tbody class="text-center">
          <tr
            class="flex flex-col flex-no wrap sm:table-row mb-2 sm:mb-0 hover:bg-gray-100 hover:font-medium cursor-pointer text-base"
            @click="rowClick(item)"
            v-for="item in paginatedData"
            :key="item.no"
          >
            <td class="py-4 text-sm">{{ item.category }}</td>
            <td class="text-left font-semibold">{{ item.title }}</td>
            <td class="">{{ item.user_name }}</td>
            <td class="font-color-black-200 text-sm">{{ item.created_at }}</td>
            <td class="text-sm">{{ item.hits }}</td>
          </tr>
          </tbody>
      </table>
    </section>
    <section class="font-sm flex justify-center items-center mt-2 mb-6">
      <base-button
        mode="outline"
        :disabled="pageNum === 0"
        @click="prevPage"
      >
        이전
      </base-button>

      
      <span class="mx-4 font-semibold">{{ pageNum + 1 }} / {{ (Math.ceil(pageSize/10)) }}</span>

      <base-button
        mode="outline"
        :disabled="pageNum >= (Math.ceil(pageSize/10))-1"
        @click="nextPage"
        
      >
        다음
      </base-button>
    </section>
  </div>
</template>

<script>
export default {
  name: 'paginated-list',
  
  data () {
    
    return {
      pk_forum:'',
      keyword:"포럼",
      pageNum: 0
    }
  },
  props: {
    listArray: {
      type: Array,
      required: true
    },
    pageSize: {
      type: Number,
      required: false,
      
    }
  },

  methods: {
    nextPage () {
      this.pageNum += 1;
      this.$emit("pageNum",this.pageNum);
    },
    prevPage () {
      this.pageNum -= 1;
      this.$emit("pageNum",this.pageNum);
    },
    fnView(){
      // alert("이동!");
      <router-link to="/board/view"></router-link>
    },
    rowClick(item) {

      this.$router.push({
        path: `/board/detail/${item.pk_forum}`
      });
    },
    writeContent() {
      this.$router.push({
        path: `/board/create`
      });
    },

  },

  computed: {
    pageCount () {
      let listLeng = this.listArray.length,
          listSize = this.pageSize,
          page = Math.floor(listLeng / listSize);
      if (listLeng % listSize > 0) page += 1;
      return page;
    },
    paginatedData () {

      return this.listArray;
    }


  }
}
</script>

<style>
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