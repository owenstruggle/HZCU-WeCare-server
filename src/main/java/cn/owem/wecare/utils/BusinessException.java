package cn.owem.wecare.utils;

/**
 * @author Owem
 * @date 2022/12/2 10:50
 * @description TODO
 **/
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final String defaultMessage;

    public BusinessException(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}
