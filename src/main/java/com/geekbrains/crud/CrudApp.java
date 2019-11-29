package com.geekbrains.crud;

import com.geekbrains.PrepareDataApp;
import com.geekbrains.entities.Product;
import com.geekbrains.users.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CrudApp {

    private static SessionFactory factory;
    private static Session session;

    public void init(){
        PrepareDataApp.forcePrepareData();
        factory = new Configuration()
                .configure("configs/crud/crud.cfg.xml")
                .buildSessionFactory();
        session = null;
    }

    public void finalMethod(){
        factory.close();
        if (session != null) {
            session.close();
        }
    }

    public void addNewProductInDataBase(String title, int price){
        System.out.println("============\n== CREATE ==\n============");
        session = factory.getCurrentSession();
        session.beginTransaction();
        Product newProduct = new Product(title, price);
        System.out.println("Before save: " + newProduct);
        session.save(newProduct);
        System.out.println("After save: " + newProduct);
        session.getTransaction().commit();
        System.out.println("After save and commit: " + newProduct);
    }

    public void readProudctByIdFromDataBase(Long id){
        System.out.println("============\n=== READ ===\n============");
        session = factory.getCurrentSession();
        session.beginTransaction();
        Product productFromDb = session.get(Product.class, id);
        System.out.println(productFromDb);
        session.getTransaction().commit();
    }
    public void updateProductInDataBaseById(Long id, String title, int price){
        System.out.println("============\n== UPDATE ==\n============");
        session = factory.getCurrentSession();
        session.beginTransaction();
        Product productForUpdate = session.get(Product.class, id);
        System.out.printf("Loaded item with id = %d: \n" + productForUpdate, id);
        productForUpdate.setPrice(price);
        productForUpdate.setTitle(title);
        System.out.println("Modified item: " + productForUpdate);
        session.getTransaction().commit();
    }

    public void deleteProductFromDataBaseById(Long id){
        System.out.println("============\n== DELETE ==\n============");
        session = factory.getCurrentSession();
        session.beginTransaction();
        session.delete(session.get(Product.class, id));
        System.out.printf("Product with id = %d deleted \n", id);
        session.getTransaction().commit();
    }

    public void addProductIntoBuying(String name, String title){
        System.out.println("============\n== ORDER ==\n============");
        session = factory.getCurrentSession();
        session.beginTransaction();
//        List<User> users = session.createQuery("SELECT user FROM User user WHERE user.name = :name", User.class).setParameter("name", name).getResultList();
        User user = session.createQuery("SELECT user FROM User user WHERE user.name = :name", User.class).setParameter("name", name).getSingleResult();
//        List<Product> products = session.createQuery("SELECT p FROM Product p WHERE p.title = :title", Product.class).setParameter("title", title).getResultList();
        Product product = session.createQuery("SELECT p FROM Product p WHERE p.title = :title", Product.class).setParameter("title", title).getSingleResult();
        user.getProducts().add(product);
        session.getTransaction().commit();
    }

    public void readProductsByName(String name){
        System.out.println("============\n=== READ ===\n============");
        session = factory.getCurrentSession();
        session.beginTransaction();
        User user = session.createQuery("SELECT user FROM User user WHERE user.name = :name", User.class).setParameter("name", name).getSingleResult();
        System.out.println("User " + user.getName() + " buy next products: ");
        for (Product p: user.getProducts()) {
            System.out.println(p.getTitle());
        }
        session.getTransaction().commit();
    }
}

