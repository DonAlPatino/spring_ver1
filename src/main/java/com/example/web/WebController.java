package com.example.web;

import com.example.dao.UserInfoDao;
import com.example.model.UserInfo;
import com.example.web.dto.CreateUserDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import com.example.service.UserInfoService;

/**
 * Обработчик web-запросов
 */
@RestController
public class WebController {

    /**
     * Средство для вывода сообщений на экран
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(WebController.class);

    /**
     * Объект для операциями с БД
     * TODO: Позже надо перейти на использование сервисного слоя
     */
    //private final UserInfoDao userInfoDao;
    private final UserInfoService userInfoService;
    /**
     * Инъекция одних объектов в другие происходит через конструктор
     * и обеспечивается библиотеками Spring
     */
    /*
    public WebController(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }*/

    /**
     * Иньекция одних объектов в другие происходит через конструктор
     * и обеспечивается библиотеками Spring
     */
    public WebController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }
    /**
     * Обработчик запросов на создание пользователя
     * @param createUserDto запрос на создание пользователя
     */
    @PostMapping("/users")

    //@Valid  - валидация
    public void createUser(@Valid @RequestBody CreateUserDto createUserDto) {

        /**
         * Получили запрос на создание пользователя,
         * пока можем только залогировать этот факт
         */
        LOGGER.info("Create user request received: {}", createUserDto);

        /**
         * Сохраняем пользователя, преобразуя DTO в модель
         */
        userInfoService.createUser(
                UserInfo.builder().setName(createUserDto.getName()).build()
        );


    }

    /**
     * Обработчик запросов на получение информации о пользователе
     * @param userName имя пользователя
     * @return информация о пользователе
     */
    @GetMapping("/users/{userName}")
    public UserInfo getUserInfo(@PathVariable String userName) {
        return userInfoService.getUserInfoByName(userName);
    }

    /**
     * Обработчик запросов на удаление пользователя
     * @param userName имя пользователя
     */
    @DeleteMapping("/users/{userName}")
    public void deleteUser(@PathVariable String userName) {
        userInfoService.deleteUser(userName);
    }
}

