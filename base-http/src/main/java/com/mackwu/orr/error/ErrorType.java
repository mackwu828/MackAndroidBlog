package com.mackwu.orr.error;

public enum ErrorType {

    /**
     * 未知。
     */
    UNKNOWN(0x00),

    /**
     * 服务端返回错误代码。
     */
    CODE_ERROR(0x01),

    /**
     * 数据为空。
     */
    DATA_NULL_ERROR(0x02),

    /**
     * 数据列表为空。
     */
    DATA_LIST_NULL_ERROR(0x03),

    /**
     * 数据某些字段为空。
     */
    FIELD_NULL_ERROR(0x04),

    /**
     * EXCEPTION
     */
    HTTP_ERROR(0x10),

    SOCKET_TIME_OUT(0x11),

    RUNTIME_ERROR(0x12),

    UNKNOWN_HOST_ERROR(0x13),

    UNKNOWN_SERVICE_ERROR(0x14),
    ;

    private int value;

    ErrorType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
