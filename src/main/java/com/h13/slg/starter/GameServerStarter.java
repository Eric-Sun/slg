package com.h13.slg.starter;

/**
 *
 */
public class GameServerStarter {

    private GameServer instance = null;


    public GameServerStarter() {

    }


    public void run() throws Exception {
        // init server
        JettyServerConfig jettyServerConfig = new JettyServerConfig();
        jettyServerConfig.setPort(8080);
        jettyServerConfig.setServerName("slg");
        jettyServerConfig.setTmpBaseDir("/Users/sunbo/project/tmp");
        instance = new JettyServer(jettyServerConfig);
        instance.init();
        instance.start();
    }


    public static void main(String[] args) throws Exception {
        GameServerStarter starter = new GameServerStarter();
        starter.run();
    }
}
