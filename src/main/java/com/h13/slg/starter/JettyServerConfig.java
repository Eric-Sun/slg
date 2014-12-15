package com.h13.slg.starter;


public class JettyServerConfig {
    public int port;
    public int minThread = 128;
    public int maxThread = 512;
    public int maxIdleTime = 60000;
    public boolean log = false;
    public String webXmlPath = "WEB-INF/web.xml";
    public String serverName;
    public String tmpBaseDir;
    public String contextPath = "/";


    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public String getTmpBaseDir() {
        return tmpBaseDir;
    }

    public void setTmpBaseDir(String tmpBaseDir) {
        this.tmpBaseDir = tmpBaseDir;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public boolean isLog() {
        return log;
    }

    public void setLog(boolean log) {
        this.log = log;
    }

    public int getMaxIdleTime() {
        return maxIdleTime;
    }

    public void setMaxIdleTime(int maxIdleTime) {
        this.maxIdleTime = maxIdleTime;
    }

    public int getMaxThread() {
        return maxThread;
    }

    public void setMaxThread(int maxThread) {
        this.maxThread = maxThread;
    }

    public int getMinThread() {
        return minThread;
    }

    public void setMinThread(int minThread) {
        this.minThread = minThread;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getWebXmlPath() {
        return webXmlPath;
    }

    public void setWebXmlPath(String webXmlPath) {
        this.webXmlPath = webXmlPath;
    }
}
