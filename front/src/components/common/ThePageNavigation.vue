<script setup>
import { ref, onMounted, watch } from "vue";

const PAGE_PER_PAGENUMBER = 5;

const props = defineProps({
  totalPage: Number,
  presentPage: Number,
});

const isFirstPage = ref(false);
const isLastPage = ref(false);

const ArrayFor = ref([]);
const totalPage = ref(props.totalPage);
const curPage = ref(props.presentPage);

const getPageArray = () => {
  let start = 1;
  let currentPageNumber = curPage.value;
  let totalPages = totalPage.value;

  if (currentPageNumber > PAGE_PER_PAGENUMBER) {
    start =
      Math.floor(currentPageNumber / PAGE_PER_PAGENUMBER) *
        PAGE_PER_PAGENUMBER +
      1;
  }
  let end = start + (PAGE_PER_PAGENUMBER - 1);
  if (end > totalPages) {
    end = totalPages;
  }

  let pageArray = [];
  console.log("start : " + start + " end : " + end);
  for (let i = start; i <= end; i++) {
    pageArray.push(i);
  }
  if (currentPageNumber == 1) {
    isFirstPage.value = true;
  } else {
    isFirstPage.value = false;
  }

  if (currentPageNumber == totalPages) {
    isLastPage.value = true;
  } else {
    isLastPage.value = false;
  }

  ArrayFor.value = pageArray;
};

watch(
  () => props.totalPage,
  (newValue) => {
    totalPage.value = newValue;
    getPageArray();
  }
);

watch(
  () => props.presentPage,
  (newValue) => {
    curPage.value = newValue;
    getPageArray();
  }
);

const emits = defineEmits(["pgnoChange"]);

const sendPgno = (pgno) => {
  emits("pgnoChange", pgno);
};

onMounted(() => {
  getPageArray();
});
</script>

<template>
  <div class="pageNavigation-layout">
    <div class="pageNavigation-elements">
      <div
        class="pageNavigation-first"
        v-if="!isFirstPage"
        @click="sendPgno(1)"
      >
        <div>F</div>
      </div>
      <div v-if="!isFirstPage"></div>
      <div class="pageNavigation-first" v-if="!isFirstPage">
        <div @click="sendPgno(curPage - 1)">prev</div>
      </div>
      <div v-if="isFirstPage"></div>
      <div class="pageNavigation-numberBtns">
        <template v-for="item in ArrayFor">
          <div
            class="pageNavigation-numberBtn"
            @click="sendPgno(item)"
            :class="{ curPage: curPage == item }"
          >
            {{ item }}
          </div>
        </template>
      </div>
      <div v-if="!isLastPage"></div>
      <div class="pageNavigation-first" v-if="!isLastPage">
        <div @click="sendPgno(curPage + 1)">next</div>
      </div>
      <div v-if="isLastPage"></div>
      <div
        class="pageNavigation-last"
        v-if="!isLastPage"
        @click="sendPgno(props.totalPage)"
      >
        L
      </div>
      <div v-if="isLastPage"></div>
    </div>
  </div>
</template>

<style scoped>
.pageNavigation-layout {
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: center;
}
.pageNavigation-elements {
  display: inherit;
  flex-direction: inherit;
  justify-content: space-evenly;
  flex-basis: 33%;
}
.pageNavigation-numberBtns {
  display: inherit;
  flex-direction: inherit;
  justify-content: space-evenly;
}
.pageNavigation-numberBtn {
  padding: 10px;
  cursor: pointer;
}
.pageNavigation-numberBtn:hover {
  background-color: black;
  color: white;
}

.curPage {
  background-color: black;
  color: white;
}
.pageNavigation-first {
  display: flex;
  flex-direction: column;
  justify-content: center;
  cursor: pointer;
}
.pageNavigation-last {
  display: flex;
  flex-direction: column;
  justify-content: center;
  cursor: pointer;
}
</style>
