<template>
  <v-app class="serif">
    <v-main
      style="
        background-position: top center;
        background-repeat: no-repeat;
        background-attachment: fixed;
        background-size: cover;
      "
      :style="loginBackgroundSrc"
      app
    >
      <v-row no-gutters style="height: 100%" align="center" justify="center">
        <v-col align-self="center" cols="3"
          ><v-card color="rgb(255,255,255,.7)">
            <v-form class="mx-5">
              <h1 id="loginTitle" class="text-center blue-grey--text">
                后台管理
              </h1>
              <v-text-field
                v-model="account"
                color="orange"
                type="text"
                label="用户名"
              >
              </v-text-field>
              <v-text-field
                v-model="password"
                color="orange"
                type="password"
                label="密码"
              >
              </v-text-field>
              <v-btn text block @click="userLogin()">登录</v-btn>
            </v-form>
          </v-card></v-col
        ></v-row
      >
    </v-main>
  </v-app>
</template>
<script>
const loginbg = require(`../assets/bg/${
  Math.floor(Math.random() * 16) + 1
}.jpg`);
export default {
  data: () => ({
    account: "",
    password: "",
    loginBackgroundSrc: `background-image:url('${loginbg}')`,
  }),
  methods: {
    userLogin() {
      const data = {
        account: this.account,
        password: this.password,
      };
      this.$http
        .post("api/users/login", data)
        .then((res) => {
          console.log(res);
          if (res.data.code == "10000") {
            sessionStorage.setItem("token", res.data.data.LOGIN_TOKEN);
            if (this.$route.query.redirect) {
              this.$router.push(this.$route.query.redirect);
            } else {
              this.$router.push("/");
            }
          }
        })
        .catch((err) => {
          console.error(err);
        });
    },
  },
};
</script>
<style>
#loginTitle {
  position: relative;
}
#loginTitle::before {
  content: "";
  transform: scaleX(0.5);
  height: 2px;
  background-image: linear-gradient(
    to left,
    rgb(78, 109, 134),
    rgba(255, 255, 255, 0.007)
  );
  width: 100%;
  right: 50%;
  height: 2px;
  bottom: 50%;
  position: absolute;
}
</style>