package io.mykit.db.sync.provider.entity;

public class JobInfo {
    String name;
    String cron;
    String srcSql;
    String destTable;
    String destTableFields;
    String destTableKey;
    String destTableUpdate;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }    
    public String getCron() {
        return cron;
    }
    public void setCron(String cron) {
        this.cron = cron;
    }
    public String getSrcSql() {
        return srcSql;
    }
    public void setSrcSql(String srcSql) {
        this.srcSql = srcSql;
    }
    public String getDestTable() {
        return destTable;
    }
    public void setDestTable(String destTable) {
        this.destTable = destTable;
    }
    public String getDestTableFields() {
        return destTableFields;
    }
    public void setDestTableFields(String destTableFields) {
        this.destTableFields = destTableFields;
    }
    public String getDestTableKey() {
        return destTableKey;
    }
    public void setDestTableKey(String destTableKey) {
        this.destTableKey = destTableKey;
    }
    public String getDestTableUpdate() {
        return destTableUpdate;
    }
    public void setDestTableUpdate(String destTableUpdate) {
        this.destTableUpdate = destTableUpdate;
    }
}
