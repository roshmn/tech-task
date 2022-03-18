package com.book.trendyol.config.properties;

import com.book.trendyol.config.PropsLoader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import static com.book.trendyol.config.constants.PropertyNames.*;

public class ApiConfigs {

    private Properties apiProps;

    public Properties getProps() {
        if (apiProps == null) {
            apiProps = PropsLoader.apiProperties.get();
        }
        return apiProps;
    }

    public int port() {
        return Integer.parseInt(getProps().getProperty(PORT));
    }

    public String protocol() {
        return getProps().getProperty(PROTOCOL);
    }

    public String host() {
        return getProps().getProperty(HOST);
    }

    public String rootPath() {
        return getProps().getProperty(ROOT_PATH);
    }

    public int connectionTimeout() {
        return Integer.parseInt(getProps().getProperty(CONNECTION_TIMEOUT));
    }
}
