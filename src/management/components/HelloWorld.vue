<template>
	<v-app id="inspire">
		<v-main>
			<v-system-bar app>
				<v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
				<v-spacer></v-spacer>
				<v-icon>mdi-square</v-icon>

				<v-icon>mdi-circle</v-icon>

				<v-icon>mdi-triangle</v-icon>
			</v-system-bar>

			<v-navigation-drawer v-model="drawer" app>
				<v-sheet color="grey lighten-4" class="pa-4">
					<v-avatar class="mb-4" color="grey darken-1" size="64"><img src="../assets/mk.jpg" /></v-avatar>

					<div>博客管理</div>
				</v-sheet>

				<v-divider></v-divider>

				<v-list>
					<v-list-item v-for="[icon, text,link] in links" :href="link" :key="icon" link>
						<v-list-item-icon>
							<v-icon>{{ icon }}</v-icon>
						</v-list-item-icon>

						<v-list-item-content>
							<v-list-item-title>{{ text }}</v-list-item-title>
						</v-list-item-content>
					</v-list-item>
				</v-list>
			</v-navigation-drawer>

			<post></post>
		</v-main>
	</v-app>
</template>

<script>
	import post from './post.vue'
	export default {
		components: {
			post,
		},
		data: () => ({
			cards: ['Today', 'Yesterday'],
			drawer: null,
			links: [
				['mdi-home', '首页', './index.html'],
				['mdi-post-outline', '文章管理'],
				['mdi-comment', '评论管理'],
				['mdi-delete', '用户管理'],
				['mdi-account-details', '媒体管理'],
				['mdi-alert-octagon', '关于'],
			],
		}),
	}
</script>
