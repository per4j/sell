package com.dapan.sell.vo;

import lombok.Data;

/**
 * http请求返回的最外层对象
 */
@Data
public class ResultVO<T> {

    /** 错误码 */
    private Integer code;

    /** 提示语 */
    private String msg;

    private T data;

}
