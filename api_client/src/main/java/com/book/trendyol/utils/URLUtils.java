package com.book.trendyol.utils;

import com.book.trendyol.config.properties.ApiConfigs;

import java.net.MalformedURLException;
import java.net.URL;

public class URLUtils {
    private final ApiConfigs configs;

    private String urlCreated;

    public URLUtils() {
        this.configs = new ApiConfigs();
    }

    public String getUrl() {
        if (urlCreated == null) {
            try {
                urlCreated = new URL(configs.protocol(), configs.host(), configs.port(), configs.rootPath())
                        .toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
                throw new IllegalArgumentException("URL is not valid!");
            }
        }
        return urlCreated;
    }
}
