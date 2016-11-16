##目录结构

- com.netease.course.model
- com.netease.course.dao
- com.netease.course.dao.impl
- com.netease.course.service
- com.netease.course.service.impl
- com.netease.course.utils
- com.netease.course.web.controller
- com.netease.course.web.filter
- com.netease.course.web.listener
- com.netease.course.UI

## 问题

* 价格处理的时候乘以100，取整数部分，显示的时候除以100

从页面读入double类型的价格，然后在service层把double转成int（乘以100舍去小数部分），把int放到数据库里，取出来的时候再除以100，所以类里面的price需要是double类型。

* 异常页面捕获

直接访问/publicSubmit页面获取不到参数而报错，通过`@ControllerAdvice`定义切面，捕获MissingServletRequestParameterException异常。

HttpRequestMethodNotSupportedException捕获请求方法非POST异常。

对于不存在的页面使用web.xml里定义error-page实现

* ftl匹配的controller地址加上.html或者.htm后缀后能访问，并且获取不到session

对于需要访问权限的页面拦截所有后缀名的访问请求

* 整个网站有3种权限，买家权限、卖家权限以及普通权限，前两者通过HandlerInterceptor拦截