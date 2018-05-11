package io.mykit.annotation.spring.log.service.impl;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import io.mykit.annotation.spring.log.entity.SystemLog;
import io.mykit.annotation.spring.log.service.SystemLogService;

@Service("systemLogService")
public class SystemLogServiceImpl implements SystemLogService {
	
	private final Logger logger = LoggerFactory.getLogger(SystemLogServiceImpl.class);
	
	@Override
	public int deleteSystemLog(String id) {
		logger.info("deleteSystemLog===>>>" + id);
		return 1;
	}

	@Override
	public int insert(SystemLog record) {
		logger.info("insert===>>>" + record.toString());
		return 1;
	}

	@Override
	public int insertTest(SystemLog record) {
		logger.info("insertTest===>>>" + record.toString());
		return 1;
	}

	@Override
	public SystemLog selectSystemLog(String id) {
		logger.info("selectSystemLog===>>>" + id);
	    SystemLog log = new SystemLog();  
        log.setId(UUID.randomUUID().toString());
        log.setDescription("查询日志");  
        log.setMethod("selectSystemLog");  
        log.setLogType((long)0);  
        log.setRequestIp("127.0.0.1");  
        log.setExceptioncode( null);  
        log.setExceptionDetail( null);  
        log.setParams( null);  
        log.setCreateBy("刘亚壮");  
        log.setCreateDate(new Date()); 
		return log;
	}

	@Override
	public int updateSystemLog(SystemLog record) {
		logger.info("updateSystemLog===>>>" + record.toString());
		return 1;
	}
}
