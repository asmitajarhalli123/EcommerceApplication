package org.example.service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.example.app.hibernateUtil.*;
import jakarta.persistence.EntityManagerFactory;
import org.example.app.hibernateUtil;
import org.example.entity.product;

import javax.swing.text.html.parser.Entity;
import java.util.List;

public class productService {

    private final EntityManagerFactory emf ;

    public productService(){
        this.emf = hibernateUtil.getEntityManagerFactory();
    }

    //create
    public  void createProduct( String productName , int price){
        EntityTransaction transaction = null;
        EntityManager entityManager = emf.createEntityManager();

        try{
            transaction = entityManager.getTransaction();
            transaction.begin();
            product p = new product();
            p.setproductName(productName);
            p.setPrice(price);
            entityManager.persist(p);
            transaction.commit();
            System.out.println("Created product with ID :" + p.getproductId());
        } catch( Exception e){
            transaction.rollback();
            e.printStackTrace();
        }  finally {
            entityManager.close();
        }
    }

    //Read by Id
    public  product viewProduct(int productId){
        EntityManager entityManager = emf.createEntityManager();
        try{
            return entityManager.find(product.class, productId);
        }finally {
            entityManager.close();
        }
    }

    //Read all
    public List<product> viewAllProducts(){
        EntityManager entityManager = emf.createEntityManager();
        try{
            TypedQuery<product> query = (TypedQuery<product>) entityManager.createQuery("FROM product" , product.class);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }


    //update
    public void updateProduct(int productId , String newProductName , int newPrice){
        EntityTransaction transaction = null ;
        EntityManager entityManager = emf.createEntityManager();

        try{
            transaction = entityManager.getTransaction();
            transaction.begin();
            product p = entityManager.find(product.class , productId);
            if (p != null) {
                p.setproductName(newProductName);
                p.setPrice(newPrice);
                System.out.println("Updated product with id :" + productId);
            }
            else{
                System.out.println("Product with id :" + productId + "not found ");
            }
        }catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }


    //Delete
    public void deleteProduct(int productId){
        EntityTransaction transaction = null ;
        EntityManager entityManager = emf.createEntityManager();

        try{
            transaction = entityManager.getTransaction();
            transaction.begin();
            product p = entityManager.find(product.class, productId);
            if (p != null) {
                entityManager.remove(p);
                transaction.commit();
                System.out.println("Deleted product with ID: " + productId);
            } else {
                System.out.println("Product with ID " + productId + " not found");
            }
        }catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    // Shutdown
    public void shutdown() {
        hibernateUtil.shutDown();
    }

}//productService
