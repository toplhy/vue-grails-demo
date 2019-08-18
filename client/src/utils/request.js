import axios from 'axios'
import router from '../router'

const TokenKey = 'JWT-Token'
// 创建axios实例
const service = axios.create({
  baseURL: process.env.SERVER_URL, // api 的 base_url   process.env.BASE_API
  timeout: 5000 // 请求超时时间
})

// request拦截器
service.interceptors.request.use(config => {
  if (!config.headers['Content-Type']) {
    config.headers['Content-Type'] = 'application/json'
  }
  if (localStorage.getItem(TokenKey) && typeof localStorage.getItem(TokenKey) !== 'undefined' && JSON.parse(localStorage.getItem(TokenKey)).access_token) {
    config.headers['Authorization'] = `Bearer ${JSON.parse(localStorage.getItem(TokenKey)).access_token}` // 让每个请求携带自定义token 请根据实际情况自行修改
  }
  return config
},
error => {
  Promise.reject(error)
})

// response 拦截器
service.interceptors.response.use(response => {
  if (response.status >= 200 && response.status < 300) {
    return response.data
  } else {
    let error = new Error(response.statusText)
    error.response = response
    return Promise.reject(error)
  }
},
error => {
  if (error.response && error.response.status === 401) {
    localStorage.removeItem(TokenKey)
    router.replace({
      path: '/login'
    })
  } else {
    return Promise.reject(error)
  }
})

export default service
