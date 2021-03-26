<template>
	<div>
		<navm>
			<keep-alive>
				<router-view></router-view>
				</keep-alive >
		</navm>
	</div>
</template>

<script>
	import navm from './components/navm.vue';
	export default {
		name: 'App',

		components: {
			navm,

		},

		data: () => ({
			//
		}),
	};
</script>
