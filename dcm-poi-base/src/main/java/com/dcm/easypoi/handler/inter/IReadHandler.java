package com.dcm.easypoi.handler.inter;

/**
 * 接口自定义处理类
 *
 * @Author hourz
 * @since 2018-01-06
 */
public interface IReadHandler<T> {
    /**
     * 处理解析对象
     * @param t
     */
    public void handler(T t);


    /**
     * 处理完成之后的业务
     */
    public void doAfterAll();

}
