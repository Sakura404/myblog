<template>
  <div id="inspire" class="serif">
    <v-main>
      <v-system-bar app>
        <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
        <v-spacer></v-spacer>
        <v-btn icon to="/"> <v-icon>mdi-square</v-icon></v-btn>
        <v-btn icon to="/"> <v-icon>mdi-circle</v-icon></v-btn>
        <v-btn icon to="/"> <v-icon>mdi-triangle</v-icon></v-btn>
      </v-system-bar>

      <v-navigation-drawer color="grey lighten-4" v-model="drawer" app>
        <v-sheet color="grey lighten-4" class="pa-4">
          <v-avatar class="mb-4" color="grey darken-1" size="64"
            ><img src="../../assets/bg/1.jpg"
          /></v-avatar>

          <div>博客管理</div>
        </v-sheet>

        <v-divider></v-divider>

        <v-list>
          <v-list-item
            v-for="[icon, text, link] in links"
            :to="link"
            :key="icon"
            link
            color="primary"
          >
            <v-list-item-icon>
              <v-icon>{{ icon }}</v-icon>
            </v-list-item-icon>

            <v-list-item-content>
              <v-list-item-title>{{ text }}</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-navigation-drawer>

      <v-container>
        <router-view></router-view>
      </v-container>
    </v-main>
  </div>
</template>

<script>
export default {
  name: "navm",
  data: () => ({
    cards: ["Today", "Yesterday"],
    drawer: null,
    links: [
      ["mdi-home", "首页", "/admin/post"],
      // ["mdi-post-outline", "文章管理", "/admin/post"],
      ["mdi-comment", "评论管理", "/admin/comment"],
      ["mdi-pencil", "文章编辑器", "/admin/editor"],
      ["mdi-note-text", "短句管理", "/admin/phrase"],
      ["mdi-image", "图片", "/admin/image"],
      //  ["mdi-delete", "用户管理"],
      //["mdi-account-details", "媒体管理"],
      ["mdi-alert-octagon", "关于"],
    ],
  }),
};
</script>
