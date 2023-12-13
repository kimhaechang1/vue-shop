import { createRouter, createWebHistory } from 'vue-router'
import ShopView from '../views/ShopView.vue';
import MainView from '../views/MainView.vue';
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path:"/",
      name:"index",
      component: MainView
    },
    {
      path : "/shop",
      name : "shop",
      component : ShopView,
      redirect : { name : "shop-list", query : { pgno : 1, spp : 10, brand : "", word : ""} },
      children : [
        { path : "list", name : "shop-list", component : () => import('../components/shop/ShopItemList.vue') }
      ]
    }
  ]
})

export default router


