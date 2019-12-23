package com.ningyv.smallcat.resultEntity;

/**
 * @author LCX
 * @create 2019-12-22 15:10
 */
public class ResultEntity<T> {


    public static final String SUCCESS = "SUCCESS";
    public static final String FAILED = "FAILED";
    public static final String NO_MESSAGE = "NO_MESSAGE";  ///没有消息
    public static final String NO_DATA = "NO_DATA";    //没有数据

    private String result;
    private String message;
    private T data;

    /**
     * 操作成功，但是没有数据返回的情况
     * @return
     */
    public static ResultEntity<String> successWithoutData() {
        return new ResultEntity<>(SUCCESS, NO_MESSAGE, NO_DATA);
    }

    /**
     * 操作成功，需要返回数据的情况
     * @param data
     * @return
     */
    public static <T> ResultEntity<T> successWithData(T data) {
        return new ResultEntity<>(SUCCESS, NO_MESSAGE, data);
    }

    /**
     * 操作失败的情况
     * @param message
     * @return
     */
    public static <T> ResultEntity<T> failed(String message) {
        return new ResultEntity<T>(FAILED, message, null);
    }

    public ResultEntity() {
        // TODO Auto-generated constructor stub
    }

    public ResultEntity(String result, String message, T data) {
        super();
        this.result = result;
        this.message = message;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultEntity [result=" + result + ", message=" + message + ", data=" + data + "]";
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
