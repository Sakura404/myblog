<template>
  <div class="mdui-card">
    <div class="mdui-card-media">
      <img :src="bgsrc">
      <div class="mdui-card-media-covered mdui-card-media-covered-transparent">
        <div class="mdui-card-primary">
          <div class="mdui-card-primary-title">
            {{ name }}
          </div>
          <div class="mdui-card-primary-subtitle">
            {{ emali }}
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
	export default {
		name: 'Drawercard',
		props: ['bgsrc'],
		data() {
			return {
				name: 'nameless',
				emali:'380487608@qq.com'
			}
		},
	}
</script>

<style>
</style>
