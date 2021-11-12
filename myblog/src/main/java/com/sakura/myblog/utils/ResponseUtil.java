package com.sakura.myblog.utils;

import com.sakura.myblog.constant.enums.ResponseEnum;
import com.sakura.myblog.model.dto.ResponseDTO;

/**
 * @author Sakura
 */
public class ResponseUtil {

    /**
     * 成功且带数据
     **/
    public static ResponseDTO success(Object object) {
        ResponseDTO result = new ResponseDTO();
        result.setCode(ResponseEnum.SUCCESS.getCode());
        result.setMessage(ResponseEnum.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }

    /**
     * 成功但不带数据
     **/
    public static ResponseDTO success() {

        return success(null);
    }

    /**
     * 失败
     **/
    public static ResponseDTO error(Integer code, String msg) {
        ResponseDTO result = new ResponseDTO();
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }
}
