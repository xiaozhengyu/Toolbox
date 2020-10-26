package com.zhengyuxiao.toolbox.msg;

/**
 * @param <T>
 * @author xzy
 * @date 2020-10-26 10:23
 * 说明：{ "status":xxx,"message":"xxx","ok":xxx,"data":xxx}
 */

public class MessageBox<T> extends Message {
    private T data;

    public MessageBox() {
        super();
    }

    public MessageBox(int status, String message, boolean ok, T data) {
        super(status, message, ok);
        this.data = data;
    }

    public MessageBox(boolean ok, T data) {
        super(ok);
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * @return - { "status":1,"message":"成功","ok":true,"data":xxx}
     */
    public static <E> MessageBox<E> ok(E data) {
        return new MessageBox<>(Message.BOOLEAN_OK, data);
    }

    /**
     * @return - { "status":0,"message":"失败","ok":false,"data":xxx}
     */
    public static <E> MessageBox<E> fail(E data) {
        return new MessageBox<>(Message.BOOLEAN_FAIL, data);
    }
}
