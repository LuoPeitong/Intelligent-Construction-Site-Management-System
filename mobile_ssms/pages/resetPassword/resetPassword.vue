<template>
	<view class="content">
		<view class="inputWrapper">
			<input class="input" type="text" placeholder="请输入邮箱" v-model="email" />
		</view>
		<view class="EmailBox">
			<input class="inputEmail" type="text" placeholder="请输入工号" v-model="jobNo" />
			<button class="getCodeButton" type="primary" @click="getCode">获取验证码</button>
		</view>
		<view class="inputWrapper">
			<input class="input" type="text" placeholder="请输入验证码" v-model="code" />
		</view>
		<view class="inputWrapper">
			<input class="input" type="password" placeholder="请输入密码" v-model="pwd" />
		</view>
		<view class="load-button">
			<button class="button" type="primary" @tap="resetPassword">登录</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				jobNo: '',
				email: '',
				code:'',
				pwd:'',
				realCode:''
			}
		},
		methods: {
			resetPassword() {
				uni.request({
					url: this.$baseUrl + "Login/resetPwd",
					data: {
						jobNo:this.jobNo,
						realCode:this.realCode,
						inputCode:this.code,
						pwd:this.pwd
					},
					method: 'post',
					header: {
						'content-type': 'application/json'
					},
					success: res => {
						if(res.data.code==200){
							this.PrintMessage(res.data.message)
							uni.navigateTo({
								url: '../index/index'
							});
						}
						else{
							this.PrintMessage(res.data.message)
						}
					}
				})
			},
			getCode() {
				uni.request({
					url: this.$baseUrl + "Login/getCode",
					data: {
						jobNo:this.jobNo,
						email:this.email
					},
					method: 'post',
					header: {
						'content-type': 'application/json'
					},
					success: res => {
						if(res.data.code==200){
							this.realCode=res.data.object
							this.PrintMessage(res.data.message)
						}
						else{
							this.PrintMessage(res.data.message)
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
		float: left;
	}

	.getCodeButton{
		width: 120px;
		height: 40px;
		border-radius: 10px;
		box-sizing: border-box;
		display: flex;
		justify-content: center;
		align-items: center;
		float: right;
		margin-top: 25px;
	}
	
	.inputEmail{
		width: 50%;
		height: 40px;
		background: white;
		border-radius: 10px;
		box-sizing: border-box;
		padding: 0 20px;
		margin-top: 25px;
	}
	
	.EmailBox{
		width: 70%;
		height: 40px;
		display: flex;
		margin-bottom: 25px;
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
