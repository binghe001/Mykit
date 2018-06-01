package io.mykit.lock.integrate.sequence;

/**
 * 分布式序列生成器接口
 * @author liuyazhuang
 */
public interface DistributedSequence {

    /**
     * 生成分布式序列
     * @param sequenceName
     * @return
     */
    Long sequence(String sequenceName);
}
