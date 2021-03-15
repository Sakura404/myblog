<template>
  <div id="app">
    <blmenu />
    <headinfo />
    <bangumi />
  </div>
</template>

<script>
	import blmenu from './components/blmenu.vue'
	import headinfo from'./components/headinfo.vue'
	import bangumi from './components/bangumi.vue'
	import 'mdui';
	export default {
		name: 'App',
		components: {
			blmenu,
			headinfo,
			bangumi,
		},
		data() {
			return {}
		},
		methods: {
			nameless() {
				console.log("hello world");
			},
		},
	}
</script>

<style>
	#app {
		font-family: Avenir, Helvetica, Arial, sans-serif;
		-webkit-font-smoothing: antialiased;
		-moz-osx-font-smoothing: grayscale;
	}
</style>
