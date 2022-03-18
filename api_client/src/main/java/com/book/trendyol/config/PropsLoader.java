package com.book.trendyol.config;

import com.book.trendyol.config.constants.FilePaths;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.function.Supplier;

public class PropsLoader {

    public static Supplier<Properties> apiProperties = () -> readPropsFile(FilePaths.BASE_PROPERTIES);

    private static Properties readPropsFile(String filePath) {
        var props = new Properties();
        try (InputStream inStream = new FileInputStream(filePath)) {
            props.load(inStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }
}
