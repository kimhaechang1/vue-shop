<script setup>
import { ref, onMounted, watch } from "vue";
import { useRouter, useRoute, onBeforeRouteUpdate } from "vue-router";
import ThePageNavigation from "../common/ThePageNavigation.vue";
import ShopItem from "./ShopItem.vue";

const router = useRouter();
const route = useRoute();

const shopItems = ref([]);
const params = ref({
  pgno: 1,
  spp: 20,
  brand: "",
  word: "",
});
const totalPageCount = ref(1);
const totalItemCount = ref(0);

const getProducts = async () => {
  try {
    const { pgno, spp, brand, word } = params.value;
    const res = await fetch(
      `http://localhost/api/product?pgno=${pgno}&spp=${spp}&brand=${brand}&word=${word}`,
      {
        method: "GET",
        headers: {
          "Access-Control-Allow-Origin": "*",
        },
      }
    );
    const json = await res.json();
    console.log(json);
    const { data, msg, status } = json;
    if (status == 200 || status == 210) {
      shopItems.value = data.productList;
      totalItemCount.value = parseInt(data.totalItemCount);
      totalPageCount.value = parseInt(data.totalPageCount);
      console.log(totalPageCount.value, totalItemCount.value);
    } else if (status == 500) {
      alert(msg);
    }
  } catch (exception) {
    console.log(exception);
  }
};

onMounted(async () => {
  const { pgno, spp, brand, word } = route.query;
  const newParams = {
    pgno: parseInt(pgno),
    spp: parseInt(spp),
    brand,
    word,
  };
  params.value = newParams;
  await getProducts();
});

watch(
  () => route.query,
  async () => {
    const { pgno, spp, brand, word } = route.query;
    const newParams = {
      pgno: parseInt(pgno),
      spp: parseInt(spp),
      brand,
      word,
    };
    params.value = newParams;
    await getProducts();
  }
);

const pgnoChangeEvent = (pgno) => {
  console.log("넘어온 pgno : " + pgno);
  router.push({
    name: "shop-list",
    query: {
      pgno,
      spp: params.value.spp,
      brand: params.value.brand,
      word: params.value.word,
    },
  });
};

const searchEvent = () => {};
</script>

<template>
  <div class="shop-items-layout">
    <div class="search-layout">
      <div></div>
      <select>
        <option>선택 해 주세요</option>
        <option value="title">제품명</option>
      </select>
    </div>
    <div class="items-layout">
      <ul class="shop-items">
        <template v-for="item in shopItems">
          <li>
            <ShopItem :item="item"></ShopItem>
          </li>
        </template>
      </ul>
    </div>
    <ThePageNavigation
      :presentPage="parseInt(params.pgno)"
      :totalPage="totalPageCount"
      @pgnoChange="pgnoChangeEvent"
    ></ThePageNavigation>
  </div>
</template>

<style scoped>
.layout {
  display: flex;
  flex-direction: row;
  justify-content: center;
  width: 100%;
}
.shop-items-layout {
  display: flex;
  flex-direction: column;
}
.search-layout {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
}
.items-layout {
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: center;
}
.shop-items {
  width: 80%;
  margin: 0 auto;
  display: flex;
  flex-direction: row;
  padding-inline-start: 0px;
  flex-wrap: wrap;
}
.shop-items > li {
  display: flex;
  flex: none;
  list-style: none;
  flex-basis: 33.33333333333333%;
  border: 1px solid #dfdfdf;
  box-sizing: border-box;
}
@media (max-width: 896px) {
  .shop-items-layout {
    padding-left: 40px;
    padding-right: 40px;
  }
  .shop-items {
    width: 90%;
  }
  .shop-items > li {
    display: flex;
    flex: none;
    list-style: none;
    flex-basis: 50%;
    border: 1px solid #dfdfdf;
    box-sizing: border-box;
  }
}
@media (max-width: 625px) {
  .shop-items > li {
    display: flex;
    flex: none;
    list-style: none;
    flex-basis: 100%;
    border: 1px solid #dfdfdf;
    box-sizing: border-box;
  }
}
</style>
