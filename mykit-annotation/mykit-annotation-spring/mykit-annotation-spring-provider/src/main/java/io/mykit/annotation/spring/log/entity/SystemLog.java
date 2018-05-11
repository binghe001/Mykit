package io.mykit.annotation.spring.log.entity;

import java.util.Date;

/**
 * 定义日志实体信息
 * @author liuyazhuang
 *
 */
public class SystemLog {

	private String id;

	private String description;

	private String method;

	private Long logType;

	private String requestIp;

	private String exceptioncode;

	private String exceptionDetail;

	private String params;

	private String createBy;

	private Date createDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method == null ? null : method.trim();
	}

	public Long getLogType() {
		return logType;
	}

	public void setLogType(Long logType) {
		this.logType = logType;
	}

	public String getRequestIp() {
		return requestIp;
	}

	public void setRequestIp(String requestIp) {
		this.requestIp = requestIp == null ? null : requestIp.trim();
	}

	public String getExceptioncode() {
		return exceptioncode;
	}

	public void setExceptioncode(String exceptioncode) {
		this.exceptioncode = exceptioncode == null ? null : exceptioncode.trim();
	}

	public String getExceptionDetail() {
		return exceptionDetail;
	}

	public void setExceptionDetail(String exceptionDetail) {
		this.exceptionDetail = exceptionDetail == null ? null : exceptionDetail.trim();
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params == null ? null : params.trim();
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy == null ? null : createBy.trim();
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "SystemLog [id=" + id + ", description=" + description + ", method=" + method + ", logType=" + logType
				+ ", requestIp=" + requestIp + ", exceptioncode=" + exceptioncode + ", exceptionDetail="
				+ exceptionDetail + ", params=" + params + ", createBy=" + createBy + ", createDate=" + createDate
				+ "]";
	}
	
}