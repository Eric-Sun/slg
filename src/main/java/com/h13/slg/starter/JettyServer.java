package com.h13.slg.starter;


import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.RequestLogHandler;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.util.thread.ThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JettyServer implements GameServer {

    private JettyServerConfig jettyServerConfig;
    private Server server;

    public JettyServer(JettyServerConfig jettyServerConfig) {
        this.jettyServerConfig = jettyServerConfig;
    }

    @Override
    public void init() throws Exception {
        server = new Server();
        server.setThreadPool(createThreadPool());
        server.addConnector(createConnector());
        server.setHandler(createHandlers());
        server.setStopAtShutdown(true);


    }

    @Override
    public void start() throws Exception {
        server.start();
        server.join();
    }


    public ThreadPool createThreadPool() {
        // TODO: You should configure these appropriately
        // for your environment - this is an example only
        QueuedThreadPool _threadPool = new QueuedThreadPool();
        _threadPool.setMinThreads(jettyServerConfig.getMinThread());
        _threadPool.setMaxThreads(jettyServerConfig.getMaxThread());
        return _threadPool;
    }

    private SelectChannelConnector createConnector() {
        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(jettyServerConfig.getPort());
        connector.setMaxIdleTime(jettyServerConfig.getMaxIdleTime());
        return connector;
    }

    private HandlerCollection createHandlers() {

        WebAppContext _ctx = new WebAppContext();
        String serverName = jettyServerConfig.getServerName();
        File tempDir = new File(jettyServerConfig.getTmpBaseDir() + File.separator + serverName);
        if (!tempDir.exists()) {
            tempDir.mkdir();
        }
        _ctx.setTempDirectory(tempDir);
        _ctx.setContextPath("/"+serverName);
        _ctx.setInitParameter("org.eclipse.jetty.servlet.Default.dirAllowed",
                "false");

        _ctx.setWar(getShadedWarUrl());

        List<Handler> _handlers = new ArrayList<Handler>();

        _handlers.add(_ctx);

        HandlerList _contexts = new HandlerList();
        _contexts.setHandlers(_handlers.toArray(new Handler[1]));

        HandlerCollection _result = new HandlerCollection();
        _result.setHandlers(new Handler[]{_contexts});
        return _result;
    }

    private String getShadedWarUrl() {
        String _urlStr = Thread.currentThread().getContextClassLoader()
                .getResource(jettyServerConfig.getWebXmlPath()).toString();
        return _urlStr.substring(0, _urlStr.length() - 15);
    }
}
