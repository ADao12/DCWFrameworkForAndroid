package com.dcw.framework.uiframework.basic;

/**
 * <p>Title: ucweb</p>
 *
 * <p>Description: </p>
 *  ......
 * <p>Copyright: Copyright (c) 2015</p>
 *
 * <p>Company: ucweb.com</p>
 *
 * @author JiaYing.Cheng
 * @email adao12.vip@gmail.com
 * @create 2015/3/19
 * @version 1.0
 */public interface FrameworkMessage {
    //框架层预留消息，外面业务层不能覆盖。
    public static final String LAUNCHER_CONTROLLER_INVOKE = "fr_msg_launcher_invoke";
}