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
		onLoad() {

		},
		methods: {
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
			},
			dialogToggle(type) {
				uni.showToast({
					title: type,
					icon: 'none', //如果要纯文本，不要icon，将值设为'none'
					duration: 2000 //持续时间为 2秒
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
		width: 100vw;
		height: 100vh;
		background-color: #E3EDCD;
	}

	.inputWrapper {
		width: 70%;
		height: 80upx;
		background: white;
		border-radius: 20px;
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
		height: 80upx;
		box-sizing: border-box;
		padding: 0 20px;
		margin-top: 15px;
	}

	.button {
		width: 100%;
		height: 80upx;
		border-radius: 20px;
		box-sizing: border-box;
		display: flex;
		justify-content: center;
		align-items: center;
		margin-top: 25px;
	}

	.avatarWrapper {
		height: 30vh; //屏幕的三分之一
		width: 100vw; //屏幕宽不变
		display: flex;
		justify-content: center; //居中
	}

	.avatar {
		height: 200upx;
		width: 200upx;
		border-radius: 50%;
	}

	.avatar .img {
		width: 100%;
	}
</style>
