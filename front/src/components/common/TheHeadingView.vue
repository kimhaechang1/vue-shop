<script setup>
import { ref } from "vue";
const toggle = ref(false);

const toggleEvent = () => {
  toggle.value = !toggle.value;
};
</script>

<template>
  <header>
    <div class="layout">
      <div class="logo"><router-link :to="{name : 'index'}">SHOP</router-link></div>
      <nav>
        <div><router-link :to="{name : 'shop'}">Shoes</router-link></div>
        <div>Notice</div>
        <div>SignIn</div>
        <div>SignUp</div>
      </nav>
      <div @click="toggleEvent" class="dropdown">
        menu
        <div v-show="toggle" class="menu">
          <div><router-link :to="{name : 'shop'}">Shoes</router-link></div>
          <div>Notice</div>
          <div>SignIn</div>
          <div>SignUp</div>
        </div>
      </div>
      <div class="sup"></div>
    </div>
  </header>
</template>

<style scoped>
header {
  display: flex;
  flex-direction: row;
  justify-content: center;
  height: 80px;
  width: 100%;
}
.layout {
  width: 92%;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-around;
}
.logo {
  display: flex;
  flex-direction: row;
  align-items: center;
  width: fit-content;
}
nav {
  width: 80%;
  gap: 15px;
  display: flex;
  flex-direction: row;
  justify-content: center;
}
.dropdown {
  display: none;
  position: relative;
}
.menu {
  border-radius: 8px;
  box-shadow: rgba(0, 0, 0, 0.05) 0px 6px 24px 0px,
    rgba(0, 0, 0, 0.08) 0px 0px 0px 1px;
  position: absolute;
  padding-top: 12px;
  padding-bottom: 12px;
  top: 2.5rem;
  width: 85px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 12px;
}
.logo, .dropdown, .menu > div, nav > div{
    cursor: pointer;
}
@media (max-width: 896px) {
  .layout {
    justify-content: space-between;
    padding-left: 70px;
    padding-right: 70px;
  }
  nav {
    display: none;
  }
  .dropdown {
    display: block;
  }
  .sup {
    display: none;
  }
}
</style>
