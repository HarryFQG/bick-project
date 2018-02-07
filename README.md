# 简单的实现mobike单车功能
## 一、主要技术
1. security的应用，主要是对权限的控制，以及无状态的处理。
1. mongodb geo 的使用，单车位置的查询
2. redis 的使用，主要是对setnx 的特性使用，以及redis事物 的操作。
3. Nginx的使用，挂在多个tomcat 使用轮询实现简单的负载均衡
