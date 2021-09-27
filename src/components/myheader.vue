<template>
  <el-container>
    <el-aside style="width: auto;">
      <el-image
        style="width: 200px; height: 100px"
        :src="require('../assets/ziti_a.png')"
        fit="contain"
      />
    </el-aside>
    <el-main>
      <el-menu
        background-color="#ffffff"
        active-text-color="#00557f"
        mode="horizontal"
        unique-opened="true"
        default-active="1"
        @select="handleselect"
      >
        <el-menu-item
          v-for="(m,index) in menus"
          :key="index++"
          :index="index+1"
        >
          {{ m.itemname }}
        </el-menu-item>
      </el-menu>
    </el-main>
  </el-container>
</template>

<script>
	export default {
		name: "Myheader",
		data() {
			return {
				menus: [{
					itemname: "首页",
					url: "#"
				}, {
					itemname: "图片",
					url: "#"
				}],
			}
		},
		methods: {
			goBack() {
				console.log('go back');
			},
			handleselect(key, keyPath) {
				console.log(key, keyPath);
			}
		},
	}
</script>

<style>
</style>
