package com.example.model;

/*
  Информация о пользователе
*/

import com.example.web.WebController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserInfo {


    private static final Logger LOGGER = LoggerFactory.getLogger(WebController.class);
    /**
     * Имя пользователя
     */
    private final String name;

    public static Builder builder() { return new Builder(); }

    /**
     * Конструктор сделан закрытым, потому что объекты этого класса
     * надо порождать таким образом:
     * dto = User.builder().setName("John Doe").build()
     */
    private UserInfo(Builder builder) {
        this.name = builder.name;
    }

    public String getName() { return name; }

    /**
     * Используется при выводе сообщений на экран
     */
    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                '}';
    }

    public static class Builder {
        private String name;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public UserInfo build() {
            LOGGER.info("Create user request received by model UserInfo");
            return new UserInfo(this); }
    }
}
