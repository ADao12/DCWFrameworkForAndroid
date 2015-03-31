package com.dcw.framework.dcwframework;

/**
 * Created by adao1_000 on 2015/4/1.
 */
public class RichTextEvent {

    private String mMsg;

    public RichTextEvent(String msg) {
        mMsg = msg;
    }

    public void setMsg(String msg) {
        this.mMsg = msg;
    }

    public String getMsg() {
        return mMsg;
    }
}
