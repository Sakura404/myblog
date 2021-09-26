<template>
  <div id="app">
    <blmenu />
    <headinfo />
    <login/>
    <bangumi />
  </div>
</template>

<script>
	import blmenu from './components/blmenu.vue'
	import headinfo from'../components/headinfo.vue'
	import bangumi from './components/bangumi.vue'
    import login from './components/login.vue'
	import 'mdui';
	export default {
		name: 'App',
		components: {
			blmenu,
			headinfo,
			bangumi,
            login
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
