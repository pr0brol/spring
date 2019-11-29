package com.geekbrains.launcher;

import com.geekbrains.crud.CrudApp;

public class MainApp {
    public static void main(String[] args) {
        CrudApp crud = new CrudApp();
        crud.init();

        crud.addNewProductInDataBase("juice", 4);
//        crud.readProudctByIdFromDataBase(2L);
//        crud.updateProductInDataBaseById(9L, "orange", 1);

        crud.addProductIntoBuying("Bob", "milk");
        crud.addProductIntoBuying("Bob", "bread");

        crud.finalMethod();

    }
}
