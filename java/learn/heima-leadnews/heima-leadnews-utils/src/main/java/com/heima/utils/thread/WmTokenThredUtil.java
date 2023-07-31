package com.heima.utils.thread;

import com.heima.model.wemedia.pojos.WmUser;

public class WmTokenThredUtil {
    public static ThreadLocal<WmUser> threadLocal = new ThreadLocal<>();

    public static void setUser(WmUser wmUser) {
        threadLocal.set(wmUser);
    }

    public static WmUser getUser() {
        return threadLocal.get();
    }

    public static void clear() {
        threadLocal.remove();
    }
}
