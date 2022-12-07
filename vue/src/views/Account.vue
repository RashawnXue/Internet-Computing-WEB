<script setup>
    import {ref} from 'vue'
    

</script>

<template>
    <div class="accountBox">
        <image src="../assets/img/me.png" width="100%"></image>
        Account detail Goes Here.
    </div>
</template>
<style>
body{
    margin:0;
    padding:0;
}
.accountBox{
    box-sizing: border-box;
    width:50%;
    margin:70px auto;
    border-radius: 18px;
    box-shadow: 0 0 10px 10px rgba(25, 128, 139, 0.82);
    display:flex;
    flex-direction:row; 
    height: 60rem;
}
</style>