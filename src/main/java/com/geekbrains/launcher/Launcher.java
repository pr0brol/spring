package com.geekbrains.launcher;

import java.net.URL;
import java.security.ProtectionDomain;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class Launcher {
    // Домашнее задание:
    // 1. Заменить студентов на продукты   +
    // 2. Добавить возможность:
    // - Добавления нового продукта через форму   + проверка по Id и по имени
    // - Отображение списка всех продуктов    +
    // - Отображение информации о продукте по его id    - не получается передать "product" в FormController, кидает исключение,
    //   не хочет запихивать в качестве атрибута Product, полученный через поиск по id
    // - * Переход со списка товаров на страницу конкретного продукта   не дошёл
    // - с возможностью его изменения

    public static void main(String[] args) throws Exception {
        Server server = new Server(8189);

        ProtectionDomain domain = Launcher.class.getProtectionDomain();
        URL location = domain.getCodeSource().getLocation();

        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setContextPath("/app");
        webAppContext.setWar(location.toExternalForm());

        server.setHandler(webAppContext);
        server.start();
        server.join();
    }
}
