package com.demo.web.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.LongAdder;

/**
 * listener是servlet规范定义的一种特殊类，用于监听 servletContext,HttpSession和ServletRequest等域对象的创建和销毁事件。
 * 监听域对象的属性发生修改的事件，用于在事件发生前、发生后做一些必要的处理。
 * 监听器一般 可用于以下方面：
 * 1、统计在线人数和在线用户
 * 2、系统启动时加载初始化信息
 * 3、统计网站访问量
 * 4、记录用户访问路径
 */
public class MyHttpSessionListener implements HttpSessionListener {

   public static LongAdder sessionCnt = new LongAdder();

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("session 被创建");
        sessionCnt.add(1L);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("销毁session");
    }

}
