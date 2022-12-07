<script setup>
    //import { lastIndexOf } from 'lodash';
    import {ref} from 'vue'
    import axios from 'axios'
    import {useRouter} from 'vue-router'
    import storage from '../utils/LocalStorage';

    const router=useRouter()
    const username=window.location.href.substring(window.location.href.lastIndexOf('/')+1,window.location.href.length)
    let userInfo=({
        posts:[],
        headImage:'',
    })
    function isValid(){
        return username===storage.get("userID")  
    }

    

</script>

<template>
    <div class="accountBox">     
        <div style="display: flex;flex-direction: row;">
            <image style="width: 100%;"></image>
        </div>
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