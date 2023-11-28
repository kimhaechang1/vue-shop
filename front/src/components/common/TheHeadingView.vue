<script setup>
</script>

<template>
    <header>
        <div class="logo">
            SHOP
        </div>
        <div class="layout">
            <nav>
                <div>Shoes</div>
                <div>Notice</div>
                <div>SignIn</div>
                <div>SignUp</div>
            </nav>
            <div></div>
        </div>
    </header>
</template>

<style scoped>
header{
    display: flex;
    flex-direction: row;
    justify-content: center;
    height: 80px;
    width: 100%;
}
.layout{
    width: 92%;
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
}
.logo{
    display: flex;
    flex-direction: row;
    align-items: center;
    width: fit-content;
}
nav{
    width: 80%;
    gap: 15px;
    display: flex;
    flex-direction: row;
    justify-content: center;
}
</style>