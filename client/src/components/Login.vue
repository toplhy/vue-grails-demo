<template>
  <el-form :model="loginForm" :rules="rules" ref="loginForm" class="login-form">
    <el-form-item style="text-align: center;">
      <h3>登录</h3>
    </el-form-item>
    <el-form-item label="" prop="username">
      <el-input prefix-icon="el-icon-user" v-model="loginForm.username" placeholder="用户名" clearable></el-input>
    </el-form-item>
    <el-form-item label="" prop="password">
      <el-input prefix-icon="el-icon-key" v-model="loginForm.password" placeholder="密码" show-password></el-input>
    </el-form-item>
    <el-form-item style="text-align: center;">
      <el-button type="primary" @click="doLogin('loginForm')">登录</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import router from '../router'
import request from '../utils/request'

export default {
  name: 'Login',
  data () {
    return {
      loginForm: {
        username: null,
        password: null
      },
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    doLogin: function (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          request({
            url: '/api/login',
            method: 'POST',
            data: JSON.stringify(this.loginForm)
          }).then(json => {
            if (json && typeof json !== 'undefined') {
              localStorage.setItem(this.tokenKey, JSON.stringify(json))
              router.push({
                name: 'Welcome'
              })
            } else {
              this.$message({
                message: '用户名或密码错误',
                type: 'warning'
              })
            }
          }).catch(error => {
            this.$message.error(error);
          })
        }
      })
    }
  }

}
</script>

<style>
  .login-form{
    border: 1px solid #9d9d9d;
    width: 400px;
    margin: 200px auto;
    padding: 35px 35px 15px 35px;
    box-shadow: 0 0 25px #9d9d9d;
    border-radius: 5px;
  }
</style>
