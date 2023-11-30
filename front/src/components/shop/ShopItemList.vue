<script setup>
import {ref, onMounted} from 'vue';
import ShopItem from './ShopItem.vue';
const shopItems = ref([]);

onMounted(async()=>{
    const res = await fetch("https://fakestoreapi.com/products?limit=12");
    const data = await res.json();
    shopItems.value = data;
})

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
        </div>
        
</template>

<style scoped>
.layout{
    display: flex;
    flex-direction: row;
    justify-content: center;
    width: 100%;
}
.shop-items-layout{
    display: flex;
    flex-direction: column;
}
.search-layout{
    display: flex;
    flex-direction: row;
    justify-content: space-around;
}
.items-layout{
    width: 100%;
    display: flex;
    flex-direction: row;
    justify-content: center;
}
.shop-items{
    width: 80%;
    margin: 0 auto;
    display: flex;
    flex-direction: row;
    padding-inline-start: 0px;
    flex-wrap: wrap;
}
.shop-items > li{
    display: flex;
    flex: none;
    list-style: none;
    flex-basis: 33.33333333333333%;
    border: 1px solid #dfdfdf;
    box-sizing: border-box;
}
@media (max-width:896px){
    .shop-items-layout{
        padding-left: 40px;
        padding-right: 40px;
    }
    .shop-items{
        width: 90%;
    }
    .shop-items > li{
        display: flex;
    flex: none;
    list-style: none;
    flex-basis: 50%;
    border: 1px solid #dfdfdf;
    box-sizing: border-box;
    }
}
@media (max-width:625px){
    .shop-items > li{
        display: flex;
    flex: none;
    list-style: none;
    flex-basis: 100%;
    border: 1px solid #dfdfdf;
    box-sizing: border-box;
    }
}
</style>