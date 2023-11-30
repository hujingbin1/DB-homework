package books.serverlet.utils;

import javax.faces.event.PostPutFlashValueEvent;
import java.security.PrivilegedExceptionAction;

/**
 * ClassName:CommonResult
 * Package:books.serverlet.utils
 * Description:
 *
 * @Author 赵天礁
 * @Creat 2023-11-1120:53
 * Version 1.0
 */
public class CommonResult {
    private Boolean flag;//服务端处理客户端请求的标志
    private Object resultData;//存储返回给客户端的对象
    private String message;//服务端返回给客户端的字符串
    public static CommonResult requestOK()
    {
        return new CommonResult().setFlag(true);
    }
    public static CommonResult requestError()
    {
        return new CommonResult().setFlag(false);
    }

    public Boolean getFlag() {
        return flag;
    }

    public CommonResult setFlag(Boolean flag) {
        this.flag = flag;
        return this;
    }

    public Object getResultData() {
        return resultData;
    }

    public void setResultData(Object resultData) {
        this.resultData = resultData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "CommonResult{" +
                "flag=" + flag +
                ", resultData=" + resultData +
                ", message='" + message + '\'' +
                '}';
    }


}
