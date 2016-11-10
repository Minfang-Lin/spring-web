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

1. 价格处理的时候乘以100，取整数部分，显示的时候除以100

从页面读入double类型的价格，然后在service层把double转成int（乘以100舍去小数部分），把int放到数据库里，取出来的时候再除以100，所以类里面的price需要是double类型。

2. 直接访问/publicSubmit页面获取不到参数而报错