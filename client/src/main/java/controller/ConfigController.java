package controller;

import model.ConfigModel;
import view.ConfigView;


public class ConfigController {

    private static final String DEFAULT_HOST = "127.0.0.1";
    private static final int DEFAULT_PORT = 9000;

    public static ConfigModel configModel = new ConfigModel(DEFAULT_HOST,DEFAULT_PORT); // access it outside
    private ConfigView configView;

    public ConfigController() {
    }

    public String getServerHost() {
        return configModel.getHost();
    }

    public int getServerPort() {
        return configModel.getPort();
    }

    public void setServerHost(String serverHost) {
        configModel.setHost(serverHost);
    }


    public boolean setDefaultHostAndPort() {
        configModel = new ConfigModel(DEFAULT_HOST,DEFAULT_PORT);
        return true;
    }

    public boolean saveHostAndPort(String hostValue, String portValue) {
        int port = 0;
        if (hostValue.isBlank() || portValue.isBlank())
            return false;

        try {
           port = Integer.parseInt(portValue);
        }catch (Exception e){
            return false;
        }

        if (port <= 0)
            return false;

        configModel.setHost(hostValue);
        configModel.setPort(port);

        return true;
    }
}
