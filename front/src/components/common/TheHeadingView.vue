<script setup>
import { ref } from "vue";
const toggle = ref({ hide: true });

const toggleEvent = () => {
  toggle.value.hide = !toggle.value.hide;
};
</script>

<template>
  <header>
    <div class="layout">
      <div class="logo">SHOP</div>
      <nav>
        <div>Shoes</div>
        <div>Notice</div>
        <div>SignIn</div>
        <div>SignUp</div>
      </nav>
      <div @click="toggleEvent" class="dropdown">
        menu
        <div :class="toggle" class="menu">
          <div>Shoes</div>
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
.hide {
  display: none;
}
.menu {
  border-radius: 15px;
  box-shadow: rgba(0, 0, 0, 0.05) 0px 6px 24px 0px,
    rgba(0, 0, 0, 0.08) 0px 0px 0px 1px;
  position: absolute;
  top: 2.5rem;
}
@media (max-width: 896px) {
  .layout {
    justify-content: space-between;
    padding-left: 25px;
    padding-right: 25px;
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
