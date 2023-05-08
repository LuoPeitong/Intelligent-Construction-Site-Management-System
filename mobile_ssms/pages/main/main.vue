<template>
	<view>
		<text>{{staff.name}}</text>
		<text>{{staff.jobNo}}</text>
		<text>{{staff.departmentName}}</text>
		<text>{{staff.projectName}}</text>
		<!-- <button @click="getLocation">定位</button> -->
		<button @click="inputDialogToggle">定位</button>
		<button @click="signIn">签到</button>
		<button @click="signOut">签退</button>
		<button @click="editPassword">修改密码</button>
		<button @click="logOut">退出登录</button>

		<uni-popup ref="inputDialog" type="dialog">
			<uni-popup-dialog ref="inputClose" mode="input" title="输入当前位置" placeholder="请输入坐标"
				@confirm="dialogInputConfirm">
			</uni-popup-dialog>
		</uni-popup>
		
		<uni-popup ref="locationDialog" type="dialog">
			<uni-popup-dialog ref="locationClose" mode="input" title="输入当前位置" placeholder="请输入坐标"
				@confirm="dialoglocationConfirm">
			</uni-popup-dialog>
		</uni-popup>
		
		<uni-popup ref="editPwdDialog" type="dialog">
			<uni-popup-dialog ref="inputClose" mode="input" title="修改密码" placeholder="请输入新密码"
				@confirm="editPwdConfirm">
			</uni-popup-dialog>
		</uni-popup>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				// 位置
				location: '',
				staff: {}
			}
		},
		created() {
			this.staff = uni.getStorageSync('staff');
		},
		methods: {
			// 打开定位按钮的dialog
			inputDialogToggle() {
				this.$refs.inputDialog.open()
			},
			locationDialogToggle() {
				this.$refs.locationDialog.open()
			},
			editPassword() {
				this.$refs.editPwdDialog.open()
			},
			editPwdConfirm(val) {
				uni.request({
					url: this.$baseUrl + "Login/editPwd",
					data: {
						jobNo: this.staff.jobNo,
						pwd:val
					},
					method: 'post',
					header: {
						'content-type': 'application/json'
					},
					success: res => {
						this.PrintMessage(res.data.message)
					}
				})
			},
			dialoglocationConfirm(val) {
				this.location = val
			},
			dialogInputConfirm(val) {
				this.location = val
				uni.request({
					url: this.$baseUrl + "trajectory/setLocation",
					data: {
						jobNo: this.staff.jobNo,
						location: this.location
					},
					method: 'post',
					header: {
						'content-type': 'application/json'
					},
					success: res => {
						this.PrintMessage(res.data.message)
					}
				})
			},
			getLocation() {
				uni.getLocation({
					type: 'gcj02',
					geocode: true,
					success: (res) => {
						this.location = res.longitude + "," + res.latitude
						this.setLocation();
					}
				});
			},
			setLocation() {
				// 将时间和经纬存入 轨迹表(trajectory)
				uni.request({
					url: this.$baseUrl + "trajectory/setLocation",
					data: {
						jobNo: this.staff.jobNo,
						location: this.location
					},
					method: 'post',
					header: {
						'content-type': 'application/json'
					},
					success: res => {
						this.PrintMessage(res.data.message)
					}
				})
			},
			// 签到 考勤表(attendance)
			signIn() {
				uni.request({
					url: this.$baseUrl + "attendance/signIn",
					data: {
						jobNo: this.staff.jobNo,
						location: this.location
					},
					method: 'post',
					header: {
						'content-type': 'application/json'
					},
					success: res => {
						this.PrintMessage(res.data.message)
					}
				})
			},
			// 签退
			signOut() {
				uni.request({
					url: this.$baseUrl + "attendance/signOut",
					data: {
						jobNo: this.staff.jobNo,
						location: this.location
					},
					method: 'post',
					header: {
						'content-type': 'application/json'
					},
					success: res => {
						this.PrintMessage(res.data.message)
					}
				})
			},
			logOut() {
				uni.request({
					url: this.$baseUrl + "logOut",
					data: {
						jobNo: this.staff.jobNo,
						id: this.staff.id
					},
					method: 'post',
					header: {
						'content-type': 'application/json'
					},
					success: res => {
						if (res.data.code == 200) {
							uni.clearStorage();
							setTimeout(() => {
								this.dialogToggle(res.data.message);
								setTimeout(() => {
									uni.hideToast();
									//关闭提示后跳转
									uni.navigateTo({
										url: '../index/index'
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

</style>