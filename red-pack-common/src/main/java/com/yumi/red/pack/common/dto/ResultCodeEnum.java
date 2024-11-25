package com.yumi.red.pack.common.dto;

import com.yumi.red.pack.common.exception.BusinessException;

import static com.yumi.red.pack.common.exception.BusinessExceptionCodeConstant.enumCodeNotFoundCode;

public enum ResultCodeEnum {
    SUCCESS(0, "成功"),
    FAIL(1, "失败")
    ;

    private Integer code;
    private String desc;

    ResultCodeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static ResultCodeEnum fromCode(int code) {
        for (ResultCodeEnum value : ResultCodeEnum.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new BusinessException("未找到对应的code", enumCodeNotFoundCode);
    }
}
