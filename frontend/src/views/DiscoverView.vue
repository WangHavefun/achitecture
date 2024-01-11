<script setup lang="ts">
import FooterSection from '@/components/FooterSection.vue';
import {onBeforeMount, onBeforeUnmount, onMounted, ref} from "vue";
import router from "@/router";
import axios from 'axios';
interface BusinessItem {
  businessid: number; // 商家编号
  businessname: string; // 商家名称
  businessaddress: string; // 商家地址
  businessexplain: string; // 商家介绍
  businessimg: string; // 商家图片（base64）
  ordertypeid: number; // 点餐分类
  starprice: number; // 起送费
  deliveryprice: number; // 配送费
  remarks: string; // 备注

  quantity: number; // 当前用户在指定店家的购物车项数
}

const businessList = ref<BusinessItem[] | null>(null); // 商家列表

onBeforeMount(()=>{
  axios.get('/business/list').then((res)=>{
    let r = res.data;
    if(r.code == 0){
      businessList.value = r.data.records;
    }
  })
});

onMounted(() => {
  // 将搜索块固定到视口顶部
  document.onscroll = () => {
    //获取滚动条位置
    let s1 = document.documentElement.scrollTop;
    let s2 = document.body.scrollTop;
    let scroll = s1 == 0 ? s2 : s1;
    //获取视口宽度
    let width = document.documentElement.clientWidth;
    //获取顶部固定块
    let search = document.getElementById('fixedBox') as HTMLElement;

    //判断滚动条超过视口宽度的12%时，搜索块变成固定定位
    if (scroll > width * 0.12) {
      search.style.position = 'fixed';
      search.style.left = '0';
      search.style.top = '0';
    } else {
      search.style.position = 'static';
    }
  };


});

onBeforeUnmount(() => {
  // 组件卸载后去掉document滚动条事件
  document.onscroll = null;
});

// 根据商家类别跳转到商家列表页
function toBusinessList(type: number) {
  // 路由跳转
  router.push({
    name: 'businessList',
    params: {
      type: type
    }
  });
}

function nonsupport() {
  ElMessage({
    message: '暂不支持该功能',
    type: 'warning'
  });
}

function toBusinessInfo(id: number) {
  // 路由跳转
  router.push({
    name: 'businessInfo',
    params: {
      id
    }
  });
}

function changeServer() {
  ElMessageBox.prompt('Please input your server address', 'Tip', {
    confirmButtonText: 'OK',
    cancelButtonText: 'Cancel',
    inputErrorMessage: 'Invalid Email'
  })
      .then((value: any) => {
        if (!value.value.startsWith('http://')) {
          value.value = 'http://' + value.value;
        }
        axios.defaults.baseURL = value.value;
        ElMessage({
          type: 'success',
          message: `Server address changed to ${value.value}`
        });
      })
      .catch(() => {
        ElMessage({
          type: 'info',
          message: 'Input canceled'
        });
      });
}
</script>

<template>

  <ul class="business">
    <li
        v-for="item in businessList"
        :key="item.businessid"
        @click="toBusinessInfo(item.businessid)"
    >
      <div class="business-img">
        <img :src="item.businessimg" />
        <div class="business-img-quantity" v-show="item.quantity > 0">{{ item.quantity }}</div>
      </div>
      <div class="business-info">
        <h3>{{ item.businessname }}</h3>
        <p>&#165;{{ item.starprice }}起送 | &#165;{{ item.deliveryprice }}配送</p>
        <p>{{ item.businessexplain }}</p>
      </div>
    </li>
  </ul>

  <div class="wrapper">
    <!-- 底部菜单部分 -->
    <FooterSection />
  </div>
</template>

<style scoped>
/****总容器部分***/
.wrapper {
  width: 100%;
  height: 100%;
  text-align: center;
  display: flex;
  justify-content: center;
}

/****推荐商家列表部分***/
.wrapper .business {
  width: 100%;
  padding-bottom: 14vw;
}

.wrapper .business li {
  width: 100%;
  box-sizing: border-box;
  padding: 2.5vw;
  user-select: none;
  border-bottom: solid 1px #ddd;
  display: flex;
}

.wrapper .business li img {
  width: 18vw;
  height: 18vw;
}

.wrapper .business li .business-info {
  width: 100%;
  box-sizing: border-box;
  padding-left: 3vw;
}

.wrapper .business li .business-info .business-info-h {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 2vw;
}

.wrapper .business li .business-info .business-info-h h3 {
  font-size: 4vw;
  color: #333;
}

.wrapper .business li .business-info .business-info-h .business-info-like {
  width: 1.6vw;
  height: 3.4vw;
  background-color: #666;
  color: #fff;
  font-size: 4vw;
  margin-right: 4vw;

  display: flex;
  justify-content: center;
  align-items: center;
}

.wrapper .business li .business-info .business-info-star {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 2vw;
  font-size: 3.1vw;
}

.wrapper .business li .business-info .business-info-star .business-info-star-left {
  display: flex;
  align-items: center;
}

.wrapper .business li .business-info .business-info-star .business-info-star-left .fa-star {
  color: #fec80e;
  margin-right: 0.5vw;
}

.wrapper .business li .business-info .business-info-star .business-info-star-left p {
  color: #666;
  margin-left: 1vw;
}

.wrapper .business li .business-info .business-info-star .business-info-star-right {
  background-color: #0097ff;
  color: #fff;
  font-size: 2.4vw;
  font-weight: 600;
  border-radius: 2px;
  padding: 0 0.6vw;
}

.wrapper .business li .business-info .business-info-delivery {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 2vw;

  color: #666;
  font-size: 3.1vw;
}

.wrapper .business li .business-info .business-info-explain {
  display: flex;
  align-items: center;
  margin-bottom: 3vw;
}

.wrapper .business li .business-info .business-info-explain div {
  border: solid 1px #ddd;
  font-size: 2.8vw;
  color: #666;
  border-radius: 3px;
  padding: 0 0.1vw;
}

.wrapper .business li .business-info .business-info-promotion {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1.8vw;
}

.wrapper .business li .business-info .business-info-promotion .business-info-promotion-left {
  display: flex;
  align-items: center;
}

.wrapper
.business
li
.business-info
.business-info-promotion
.business-info-promotion-left
.business-info-promotion-left-icon {
  width: 4vw;
  height: 4vw;
  background-color: #70bc46;
  border-radius: 3px;
  font-size: 3vw;
  font-weight: 500;
  color: #fff;

  display: flex;
  justify-content: center;
  align-items: center;
}

.wrapper .business li .business-info .business-info-promotion .business-info-promotion-left p {
  color: #666;
  font-size: 3vw;
  font-weight: 500;
  margin-left: 2vw;
}

.wrapper .business li .business-info .business-info-promotion .business-info-promotion-right {
  display: flex;
  align-items: center;
  font-size: 2.5vw;
  color: #999;
}

.wrapper .business li .business-info .business-info-promotion .business-info-promotion-right p {
  margin-right: 2vw;
}

.wrapper {
  width: 100%;
  height: 100%;
}

/****header部分***/
.wrapper header {
  width: 100%;
  height: 12vw;
  background-color: #0097ff;
  color: #fff;
  font-size: 4.8vw;

  position: fixed;
  left: 0;
  top: 0;
  z-index: 1000;

  display: flex;
  justify-content: center;
  align-items: center;
}

/******商家列表部分*****/
.wrapper .business {
  width: 100%;
  margin-top: 12vw;
  padding-bottom: 14vw;
}

.wrapper .business li {
  width: 100%;
  box-sizing: border-box;
  padding: 2.5vw;
  border-bottom: solid 1px #ddd;
  user-select: none;
  cursor: pointer;

  display: flex;
  align-items: center;
}

.wrapper .business li .business-img {
  position: relative;
}

.wrapper .business li .business-img img {
  width: 20vw;
  height: 20vw;
}

.wrapper .business li .business-img .business-img-quantity {
  width: 5vw;
  height: 5vw;
  background-color: red;
  color: #fff;
  font-size: 3.6vw;
  border-radius: 2.5vw;

  display: flex;
  justify-content: center;
  align-items: center;

  position: absolute;
  right: -1.5vw;
  top: -1.5vw;
}

.wrapper .business li .business-info {
  margin-left: 3vw;
}

.wrapper .business li .business-info h3 {
  font-size: 3.8vw;
  color: #555;
  margin-bottom: 3.4vw;
}

.wrapper .business li .business-info p {
  font-size: 3vw;
  color: #888;
  margin-top: 2vw;
}
</style>
