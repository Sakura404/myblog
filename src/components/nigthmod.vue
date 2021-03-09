<template>
	<li class="mdui-list-item mdui-ripple">
		<i class="mdui-list-item-icon mdui-icon material-icons">brightness_2</i>
		<div class="mdui-list-item-content">夜间模式</div>
		<label class="mdui-switch">
			<input type="checkbox" @change="modchange()" v-model="check" true-value="nigth" false-value="day" checked />
			<i class="mdui-switch-icon"></i>
		</label>
	</li>
</template>

<script>
	export default {
		name: 'nightmod',
		data() {
			return {
				check: 'day'
			}
		},
		methods: {
			modchange() {
				if (this.check != 'day') {
					document.getElementsByTagName("body")[0].classList.add("mdui-theme-layout-dark");
					document.getElementsByTagName("body")[0].classList.remove("mdui-theme-layout-light")
				} else {
					document.getElementsByTagName("body")[0].classList.add("mdui-theme-layout-light");
					document.getElementsByTagName("body")[0].classList.remove("mdui-theme-layout-dark")

				}
			}
		}
	}
</script>

<style>
</style>
