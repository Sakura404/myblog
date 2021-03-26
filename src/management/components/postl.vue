<template>
	<navm><post></post></navm>
</template>

<script>
	import navm from './navm.vue';
	import post from './post.vue'
	export default {
		name: 'App',

		components: {
			navm,
			post,
		},

		data: () => ({
			//
		}),
	};
</script>
