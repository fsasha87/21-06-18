package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {//класс читает параметры из внешних источников

    public static String getUrl() {
        return getProperty("url");
    }

    public static Browser getBrowser() {
        return Browser.valueOf(getProperty("browser"));//преобразовываем к формату Browser с помощью valueOf
    }

    private static String getProperty(String propertyName) {//логика считывания properties
        if (System.getProperty(propertyName) == null) {//проверяем заданы ли System.properties (в консоле)
            return getPropertyFromFile(propertyName);//берет из проперти-файла
        } else {
            return System.getProperty(propertyName);//берет
        }
    }

    private static String getPropertyFromFile(String propertyName) {//метод реализует считывание пропертиз из файла
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream("src/main/resources/framework.properties");//путь к property-файлу
            prop.load(input);
        } catch (IOException ex) {
            System.out.println("Cannot read property value for " +
                    propertyName);
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return prop.getProperty(propertyName);
    }
}

//метод прочитывает пропертис из файла
//запуск с консоля: C:\Users\38093\IdeaProjects\21-06-18>mvn clean test -Durl=https://www.google.ru -Dbrowser=IE