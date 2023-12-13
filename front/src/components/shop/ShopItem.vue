<script setup>
const props = defineProps({
  item: Object,
});
</script>
<template>
  <div class="item-layout">
    <div class="item-header">
      <div>
        <template v-if="item.productImg == null">
          <img src="https://placehold.co/150x120" />
        </template>
        <template v-if="item.productImg != null">
          <img :src="item.productImg" />
        </template>
      </div>
    </div>
    <div class="item-body">
      <div class="item-title">{{ item.productName }}</div>
      <div class="item-detail">
        <div>
          <div>가격 : {{ item.productCost }}</div>
        </div>
        <div>
          <button>찜 하기</button>
          <button>장바구니 담기</button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.item-layout {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 15px;
  height: 350px;
}
img {
  width: 150px;
  height: 120px;
}
.item-title {
  white-space: nowrap;
  overflow: hidden;
  width: 200px;
  text-overflow: ellipsis;
  text-align: center;
}
.item-body {
  display: flex;
  flex-direction: column;
  gap: 10px;
}
@media (max-width: 896px) {
  img {
    width: 120px;
    height: 80px;
  }
  .item-layout {
    height: 240px;
  }
  .item-title {
    width: 180px;
  }
}
</style>
