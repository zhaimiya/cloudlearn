package cloud.product.common.vo;

import cloud.product.common.Constant;
import lombok.Data;

@Data
public class ResultVo<T> {
    private Integer code;
    private String msg;
    private T data;

    public static <T> ResultVo<T> sendSuccessResponse(T data) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(Constant.SUCCESS_CODE);
        resultVo.setMsg(Constant.SUCCESS_MSG);
        resultVo.setData(data);
        return resultVo;
    }

    public static <T> ResultVo<T> sendResponse(Integer code,String msg,T data) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(code);
        resultVo.setMsg(msg);
        resultVo.setData(data);
        return resultVo;
    }

}
