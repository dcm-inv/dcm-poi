package com.dcm.easypoi.handler.inter;

import java.util.Collection;

/**
 * 大数据写出服务接口
 *
 * @Author hourz
 * @since 2018-01-06
 */
public interface IWriter<T> {
    /**
     * 获取输出对象
     *
     * @return
     */
    default public T get() {
        return null;
    }

    /**
     * 写入数据
     *
     * @param data
     * @return
     */
    public IWriter<T> write(Collection data);

    /**
     * 关闭流,完成业务
     *
     * @return
     */
    public T close();
}
