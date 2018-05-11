package io.mykit.annotation.spring.log.service;

import io.mykit.annotation.spring.log.entity.SystemLog;

/**
 * 日志的Service接口
 * @author liuyazhuang
 *
 */
public interface SystemLogService {

    int deleteSystemLog(String id);

    int insert(SystemLog record);
    
    int insertTest(SystemLog record);

    SystemLog selectSystemLog(String id);
    
    int updateSystemLog(SystemLog record);
}
