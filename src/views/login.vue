<template>
  <v-app class="serif">
    <v-main style="
        background-position: top center;
        background-repeat: no-repeat;
        background-attachment: fixed;
        background-size: 100% 100%;
      "
      :style="loginBackgroundSrc"
      app>
      <v-row no-gutters
        style="height: 100%"
        align="center"
        justify="center">
        <v-col align-self="center"
          md="3"
          sm="6"
          cols="12">
          <v-snackbar style="transition-duration:0.5"
            :style="`translateY:60px`"
            transition="scroll-x-reverse-transition"
            timeout="2000"
            multi-line
            v-model="submitAlert.show"
            :color="submitAlert.type=='error'?'red':'green'">
            <v-icon class="mr-2">{{submitAlert.type=='error' ?"mdi-alert" :"mdi-check-circle"}}</v-icon> {{ submitAlert.meg }}
          </v-snackbar>

          <v-card color="rgb(255,255,255,.7)">
            <v-form ref="login"
              @keyup.enter.native="userLogin()"
              class="mx-5">
              <v-row justify="center"
                no-gutters>
                <v-col>
                  <div id="loginTitle1"></div>
                </v-col>
                <v-col>
                  <h1 id="loginTitle"
                    class="text-center">登录</h1>
                </v-col>
                <v-col>
                  <div id="loginTitle2"></div>
                </v-col>
              </v-row>
              <v-text-field :rules="namerules"
                v-model="account"
                color="orange"
                type="text"
                label="用户名">
              </v-text-field>
              <v-text-field :rules="pswrules"
                v-model="password"
                color="orange"
                type="password"
                label="密码">
              </v-text-field>
              <v-btn text
                block
                @keyup.enter.native="userLogin()"
                @click="userLogin()"
                class="text-button">登录</v-btn>
            </v-form>
          </v-card>
        </v-col>
      </v-row>
    </v-main>
  </v-app>
</template>
<script>
export default {
  data: () => ({
    account: "",
    password: "",
    namerules: [(v) => !!v || "名字不能为空"],
    submitAlert: {},
    pswrules: [
      (v) => !!v || "密码不能为空",
      (v) => v.length >= 6 || "密码不小于6位",
    ],
  }),
  computed: {
    loginBackgroundSrc() {
      return `background-image:url('${this.$randomImg.cdnRandomImg()}')`;
    },
  },
  methods: {
    userLogin() {
      if (this.$refs.login.validate()) {
        const data = {
          account: this.account,
          password: this.password,
        };
        this.$http
          .post("api/users/login", data)
          .then((res) => {
            if (res.data.code == "10000") {
              this.submitAlert = {
                meg: "登录成功 ",
                type: "success",
                show: true,
              };
              //   document.cookie = `LOGIN_TOKEN=${res.data.data.LOGIN_TOKEN}`;
              sessionStorage.setItem("token", res.data.data.LOGIN_TOKEN);
              if (this.$route.query.redirect) {
                this.$router.push(this.$route.query.redirect);
              } else {
                this.$router.push("/");
              }
            } else {
              this.submitAlert = {
                meg: res.data.message,
                type: "error",
                show: true,
              };
            }
          })
          .catch((err) => {
            console.error(err);
          });
      }
    },
  },
};
</script>
<style>
#loginTitle {
  position: relative;
}
#loginTitle1 {
  content: "";
  transform: scaleX(1);
  height: 2px;
  background-image: linear-gradient(
    to left,
    rgb(78, 109, 134),
    rgba(255, 255, 255, 0.007)
  );
  width: 100%;
  height: 2px;
  top: 50%;
  position: relative;
}
#loginTitle2 {
  content: "";
  transform: scaleX(1);
  height: 2px;
  background-image: linear-gradient(
    to right,
    rgb(78, 109, 134),
    rgba(255, 255, 255, 0.007)
  );
  width: 100%;
  top: 50%;
  height: 2px;

  position: relative;
}
</style>