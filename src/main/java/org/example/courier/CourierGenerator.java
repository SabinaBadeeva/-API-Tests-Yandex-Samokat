package org.example.courier;
import org.apache.commons.lang3.RandomStringUtils;

public class CourierGenerator {

    // курьер
    public static Courier getDefault(){
        return new Courier("kotiKootik","1234","courier");
    }
    // курьер без логина
    public static Courier getWithoutLogin() {
        return new Courier( "","1234","courier");
    }
    // курьер без пароля
    public static Courier getWithoutPassword() {
        return new Courier("kot","","courier");
    }

    //незарегистрированнный курьер
    public static Courier getNoRegisteredCourier (){
        return new Courier("nonExisted","4321");
    }
    //зарегистрированный курьер
    public static Courier getRegisteredCourier (){
        return new Courier("dostavochka","12345","dostaevsky");}
    // данные для авторизации
    public static Courier getDataRegisteredCourier (){
        return new Courier("dostavkin","12345");}

    // курьер без логина
    public static Courier getLoggedWithoutLogin (){
        return new Courier("12345");
    }
    public static Courier getRandomCourier() {
        final String courierLogin = RandomStringUtils.random(10, true, true);
        final String courierPassword = RandomStringUtils.random(10, true, true);
        final String courierFirstName = RandomStringUtils.randomAlphabetic(10);
        return new Courier(courierLogin, courierPassword, courierFirstName);
    }
}

