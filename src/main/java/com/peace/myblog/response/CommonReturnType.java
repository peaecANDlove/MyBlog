package com.peace.myblog.response;

/**
 * @author YR#
 * @create 2020-08-13 16:51
 */
public class CommonReturnType {

//    请求返回的处理结果 "success" 或 "fail"
    private String status;

    /**
     * 请求返回的数据
     * 若 status=success,则返回对应的数据
     * 若 status=fail,则返回通用错误格式码
     */
    private Object data;

    //定义一个通用的创建方法
    public static CommonReturnType create(Object result){
        return CommonReturnType.create(result,"success");
    }


//    自定义返回数据
    public static CommonReturnType create(Object result,String status){
        CommonReturnType type = new CommonReturnType();
        type.setStatus(status);
        type.setData(result);
        return type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
