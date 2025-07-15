package org.example.service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.example.app.hibernateUtil;
import org.example.entity.product;
import org.example.entity.product.*;
import org.example.entity.user;
import org.example.entity.user.*;

import java.util.List;

public class userService {

    private  EntityManagerFactory emf ;

    public userService(){
        this.emf = hibernateUtil.getEntityManagerFactory();
    }

    product p = new product();
    user u = new user();


    //create
    public  void createUser (String userName , String email){
        EntityTransaction transaction = null ;
        EntityManager entityManager = emf.createEntityManager();

        try{
            transaction = entityManager.getTransaction();
            transaction.begin();

            u.setuserName(userName);
            u.setemail(email);
            entityManager.persist(u);
            transaction.commit();
            System.out.println("Created User with ID :" + u.getuserId());
        } catch( Exception e){
            transaction.rollback();
            e.printStackTrace();
        }  finally {
            entityManager.close();
        }
    }



    //Read by Id
    public  user viewUser(int userId){
        EntityManager entityManager = emf.createEntityManager();
        try{
            return entityManager.find(user.class, userId);
        }finally {
            entityManager.close();
        }
    }

    //Read all
    public List<user> viewAllUser(){
        EntityManager entityManager = emf.createEntityManager();
        try{
            TypedQuery<user> query = (TypedQuery<user>) entityManager.createQuery("FROM user" , user.class);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }



    //update
    public void updateUser(int userId , String newUserName , String newEmail){
        EntityTransaction transaction = null ;
        EntityManager entityManager = emf.createEntityManager();

        try{
            transaction = entityManager.getTransaction();
            transaction.begin();
            product p = entityManager.find(product.class , userId);
            if (u != null) {
                u.setuserName(newUserName);
                u.setemail(newEmail);
                System.out.println("Updated user with id :" + userId);
            }
            else{
                System.out.println("User with id :" + userId + "not found. ");
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
    public void deleteUser(int userId){
        EntityTransaction transaction = null ;
        EntityManager entityManager = emf.createEntityManager();

        try{
            transaction = entityManager.getTransaction();
            transaction.begin();
            user u = entityManager.find(user.class, userId);
            if (u != null) {
                entityManager.remove(p);
                transaction.commit();
                System.out.println("Deleted user with ID: " + userId);
            } else {
                System.out.println("user with ID " + userId + " not found");
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



}//userService
