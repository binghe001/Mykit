package io.mykit.db.sync.provider.entity;

public class DbInfo {
    String url;
    String username;
    String password;
    String dbtype;
    String driver;
    
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getDbtype() {
        return dbtype;
    }
    public void setDbtype(String dbtype) {
        this.dbtype = dbtype;
    }
    public String getDriver() {
        return driver;
    }
    public void setDriver(String driver) {
        this.driver = driver;
    }
}
