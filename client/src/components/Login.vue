<template>
  <div>
    <input type="text" v-model="username" placeholder="用户名">
    <input type="password" v-model="password" placeholder="密码">
    <button v-on:click="doLogin">登录</button>
  </div>
</template>

<script>
import router from '../router'
import request from '../utils/request'
const TokenKey = 'JWT-Token'

export default {
  name: 'Login',
  data () {
    return {
      username: null,
      password: null
    }
  },
  methods: {
    doLogin: function () {
      if (!this.username || !this.password) {
        alert('请输入用户名和密码')
      } else {
        request({
          url: '/api/login',
          method: 'POST',
          data: JSON.stringify({ username: this.username, password: this.password })
        }).then(json => {
          if (json && typeof json !== 'undefined') {
            localStorage.setItem(TokenKey, JSON.stringify(json))
            router.push({
              name: 'Welcome'
            })
          } else {
            alert('登录失败')
          }
        }).catch(error => {
          console.log(error.message)
        })
      }
    }
  }

}
</script>
