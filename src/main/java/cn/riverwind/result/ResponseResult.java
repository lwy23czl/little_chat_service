package cn.riverwind.result;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@ApiModel(value = "api返回结果")
@AllArgsConstructor
public class ResponseResult<T> {



    private boolean success;

    private String msg;

    private T data;

    //不附带消息直接返回成功
    public ResponseResult() {
        this.success = true;
        this.msg = null;
        this.data = null;
    }

    //不附带msg返回数据
    public ResponseResult(T data) {
        this.success = true;
        this.msg = null;
        this.data = data;
    }

    //默认附带msg返回数据
    public ResponseResult(String msg, T data) {
        this.success = true;
        this.msg = msg;
        this.data = data;
    }

    //不附带数据
    public ResponseResult(Boolean success,String msg) {
        this.success = success;
        this.msg = msg;
        this.data = null;
    }

}
