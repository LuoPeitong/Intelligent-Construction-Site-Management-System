<template>
	<view class="content">
		<view class="inputWrapper">
			<input class="input" type="text" placeholder="请输入工号" v-model="username" />
		</view>
		<view class="inputWrapper">
			<input class="input" type="password" placeholder="请输入密码" v-model="password" />
		</view>
		<view class="load-button">
			<button class="button" type="primary" @tap="loginSubmit">登录</button>
		</view>
		<view class="other">
			<text class="textBox" @click="resetPassword">忘记密码</text>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				username: '',
				password: ''
			}
		},
		methods: {
			resetPassword() {
				uni.navigateTo({
					url: '../resetPassword/resetPassword'
				});
			},
			loginSubmit() {
				uni.request({
					url: this.$baseUrl + "login",
					data:{
						jobNo:this.username,
						pwd:this.password,
						permissions: 0
					},
					method: 'post',
					header: {
						'content-type': 'application/json'
					},
					success: res => {
						if (res.data.code == 200) {
							uni.setStorageSync('staff', res.data.object);
							setTimeout(() => {
								this.dialogToggle(res.data.message);
								setTimeout(() => {
									uni.hideToast();
									//关闭提示后跳转
									uni.navigateTo({
										url: '../main/main'
									});
								}, 1000)
							}, 0);
						}
					}
				})
			}
		}
	}
</script>

<style>
	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
		height: 100vh;
		background: linear-gradient(to top, #9dfa91, #5a9ebd);
	}

	.inputWrapper {
		width: 70%;
		height: 40px;
		background: white;
		border-radius: 10px;
		box-sizing: border-box;
		padding: 0 20px;
		margin-top: 25px;
	}

	.inputWrapper .input {
		width: 100%;
		height: 100%;
		text-align: left;
		font-size: 15px;
	}

	.load-button {
		width: 70%;
		height: 80px;
		box-sizing: border-box;
		margin-top: 15px;
	}

	.button {
		width: 100%;
		height: 40px;
		border-radius: 20px;
		box-sizing: border-box;
		display: flex;
		justify-content: center;
		align-items: center;
		margin-top: 25px;
	}
	
	.other{
		margin-top: 10px;
	}
	
	.textBox{
		margin-left: 100px;
		color: #666;
	}
</style>
