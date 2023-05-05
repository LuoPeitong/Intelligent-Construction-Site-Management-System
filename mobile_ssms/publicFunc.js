exports.install = function (Vue, options) {
  Vue.prototype.PrintMessage = function (message) {
    setTimeout(() => {
    	this.dialogToggle(message);
    	setTimeout(() => {
    		uni.hideToast();
    	}, 1000)
    }, 0);
  },
  Vue.prototype.dialogToggle = function (type) {
  	uni.showToast({
  		title: type,
  		icon: 'none', //如果要纯文本，不要icon，将值设为'none'
  		duration: 2000 //持续时间为 2秒
  	})
  }
}
