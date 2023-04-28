<template>
	<view>
		<text>{{staff.name}}</text>
		<text>{{staff.jobNo}}</text>
		<text>{{staff.departmentName}}</text>
		<text>{{staff.projectName}}</text>
		<!-- <button @tap="getLocation">定位</button> -->
		<button @click="inputDialogToggle">定位</button>
		<button @tap="signIn">签到</button>
		<button @tap="signOut">签退</button>
		<button @tap="logOut">退出登录</button>

		<view>
			<uni-popup ref="inputDialog" type="dialog">
				<uni-popup-dialog ref="inputClose" mode="input" title="输入当前位置"  placeholder="请输入坐标"
					@confirm="dialogInputConfirm">
				</uni-popup-dialog>
			</uni-popup>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				// 位置
				location:'',
				staff: {}
			}
		},

		onLoad() {
			this.staff = uni.getStorageSync('staff');
		},
		methods: {
			inputDialogToggle() {
				this.$refs.inputDialog.open()
			},
			// 后续修改 签到、签退功能时，将此函数的确认和提交请求分离，然后由定位、签到、签退再去发送请求。
			dialogInputConfirm(val) {
				console.log("location: "+ val)
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
						if (res.data.code == 200) {
							setTimeout(() => {
								this.dialogToggle(res.data.message);
								setTimeout(() => {
									uni.hideToast();
								}, 1000)
							}, 0);
						}
					}
				})
			},
			getLocation() {
				uni.getLocation({
					type: 'gcj02',
					geocode: true,
					success: (res) => {
						console.log('当前位置的经度：' + res.longitude);
						console.log('当前位置的纬度：' + res.latitude);
						this.location = res.longitude + "," + res.latitude
						console.log('当前位置的纬度：' + this.location);
						this.setLocation();
					}
				});
				
			},
			setLocation(){
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
						if (res.data.code == 200) {
							setTimeout(() => {
								this.dialogToggle(res.data.message);
								setTimeout(() => {
									uni.hideToast();
								}, 1000)
							}, 0);
						}
					}
				})
			},
			// 签到 考勤表(attendance)
			signIn() {
				uni.request({
					url: this.$baseUrl + "attendance/signIn",
					data: {
						jobNo: this.staff.jobNo
					},
					method: 'post',
					header: {
						'content-type': 'application/json'
					},
					success: res => {
						setTimeout(() => {
							this.dialogToggle(res.data.message);
							setTimeout(() => {
								uni.hideToast();
							}, 1000)
						}, 0);
					}
				})
			},
			signOut() {
				uni.request({
					url: this.$baseUrl + "attendance/signOut",
					data: {
						jobNo: this.staff.jobNo
					},
					method: 'post',
					header: {
						'content-type': 'application/json'
					},
					success: res => {
						setTimeout(() => {
							this.dialogToggle(res.data.message);
							setTimeout(() => {
								uni.hideToast();
							}, 1000)
						}, 0);
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

</style>
