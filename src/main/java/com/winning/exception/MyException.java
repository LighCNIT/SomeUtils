package com.winning.exception;

/**
 * 
 * @ClassName: MyException
 * @Description: TODO (自定义异常)
 * @author  Ligh
 * @date 2017年7月5日下午4:53:05
 */
public class MyException extends Exception
{

    private String errcode;
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -3766085216795605493L;

    public MyException()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    public MyException(String message)
    {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public MyException(Throwable cause, String errcode)
    {
        super(errcode, cause);
        this.errcode = errcode;
        // TODO Auto-generated constructor stub
    }

    public MyException(String message, String errcode)
    {
        super(message);
        // TODO Auto-generated constructor stub
        this.errcode = errcode;
    }

    public String getErrcode()
    {
        return errcode;
    }

    public MyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

    public MyException(String message, Throwable cause)
    {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public MyException(Throwable cause)
    {
        super(cause);
        // TODO Auto-generated constructor stub
    }
    
    
}
