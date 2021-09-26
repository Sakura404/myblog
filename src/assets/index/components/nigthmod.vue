<template>
  <li class="mdui-list-item mdui-ripple">
    <i class="mdui-list-item-icon mdui-icon material-icons">brightness_2</i>
    <div class="mdui-list-item-content">
      夜间模式
    </div>
    <label class="mdui-switch">
      <input
        v-model="check"
        type="checkbox"
        true-value="nigth"
        false-value="day"
        checked
        @change="modchange()"
      >
      <i class="mdui-switch-icon" />
    </label>
  </li>
</template>

<script>
	export default {
		name: 'Nightmod',
		data() {
			return {
				check: 'day'
			}
		},
		methods: {
			modchange() {
				var bodyclass = document.getElementsByTagName("body")[0].classList;
				var drawerclass = document.getElementById("app-menu").classList;
				if (this.check != 'day') {
					bodyclass.add("mdui-theme-layout-dark")
					bodyclass.remove("mdui-theme-layout-light")
					drawerclass.add("mdui-theme-layout-dark")
					drawerclass.remove("mdui-color-white")

				} else {
					bodyclass.add("mdui-theme-layout-light")
					bodyclass.remove("mdui-theme-layout-dark")
					drawerclass.add("mdui-color-white")
					drawerclass.remove("mdui-theme-layout-dark")

				}
			}
		}
	}
</script>

<style>
</style>
