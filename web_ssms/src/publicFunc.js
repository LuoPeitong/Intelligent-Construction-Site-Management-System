exports.install = function (Vue, options) {
  Vue.prototype.PrintMessage = function (code, message) {
    const h = this.$createElement
    this.$notify({
      title: code === 200 ? '成功' : '错误',
      message: h('i',
        {style: 'color: teal'},
        message)
    })
  }
}
