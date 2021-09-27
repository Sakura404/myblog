<template>
	<div id="login" class="mdui-container mdui-hoverable">
		<header id='l-header'>
			<h2>login | 登录</h2>
		</header>
		<form @submit.prevent="haslogin()">
			<div class="mdui-textfield mdui-textfield-floating-label">
				<i class="mdui-icon material-icons">account_circle</i>
				<label class="mdui-textfield-label">账号</label>
				<input v-model="uservalue" class="mdui-textfield-input" type="text">

			</div>
			<div class="mdui-textfield mdui-textfield-floating-label">
				<i class="mdui-icon material-icons">lock</i>
				<label class="mdui-textfield-label">密码</label>
				<input v-model="passwordvalue" class="mdui-textfield-input" type="password" minlength="6"
					maxlength="12">
				<div class="mdui-textfield-error">密码至少要大于6位</div>
			</div>
			<input type="submit" class="mdui-btn mdui-btn-block mdui-ripple mdui-color-blue-200" value="登录">
		</form>
	</div>
</template>

<script>
	export default {
		name: 'Login',
		data() {
			return {
				uservalue: null,
				passwordvalue: null
			}
		},
		methods: {
			haslogin() {
				console.log('账号：' + this.uservalue + '\n密码：' + this.passwordvalue);
			}
		}
	}
</script>

<style>
	#login {
		max-width: 400px;
		padding: 40px;
	}

	#l-header {
		text-align: center;
	}
</style>
