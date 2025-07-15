package org.example.app;
import java.util.List;
import java.util.Scanner;
import org.example.app.hibernateUtil.*;
import org.example.entity.product;
import org.example.entity.user;
import org.example.service.productService;
import org.example.service.userService;
import org.example.entity.product.*;
import org.example.entity.user.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.example.entity.product.*;
import java.sql.SQLOutput;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello there ! Welcome to the our world of ShopSpark Shopping .. STAY HOME AND SHOP ONLINE !!");
        while(true){
            System.out.println("\nShopSpark Shop wish list :");
            System.out.println("\n 1. Add New Product");
            System.out.println("\n 2. Update Product");
            System.out.println("\n 3. Delete Product");
            System.out.println("\n 4. View Product by ID");
            System.out.println("\n 5. View All Products ");
            System.out.println("\n 6. Add New User ");
            System.out.println("\n 7. Update User");
            System.out.println("\n 8. Delete USer ");
            System.out.println("\n 9. View Users by Id");
            System.out.println("\n 10.View All Users");
            System.out.println("\n 11. Exit");
            System.out.println("\n Enter Your Choice(1-11):");

        int choice ;

        choice = sc.nextInt();

        productService ProductService = new productService();
        userService UserService = new userService();

        switch (choice){
            case 1 -> { // add new product

                System.out.println("\nEnter Product Name:");
                String productName = sc.next();
                System.out.println("\n Enter Product Price:");
                int price = sc.nextInt();
                ProductService.createProduct(productName, price);
                break;
            }

            case 2 -> { //Update Product

                System.out.println("\nEnter Product Id to Update :");
                int productId = sc.nextInt();
                System.out.println("\nEnter new Product Name :");
                String newProductName = sc.next();
                System.out.println("\nEnter new Product Price :");
                int newPrice = sc.nextInt();
                ProductService.updateProduct(productId, newProductName, newPrice);
                break;
            }

            case 3 -> { //delete Product

                System.out.println("\nEnter Product Id to Delete :");
                int productId = sc.nextInt();
                ProductService.deleteProduct(productId);
                break;
            }

            case 4 -> { // View Product by ID

                System.out.println("\nEnter Product Id to View :");
                int productId = sc.nextInt();
                product p = ProductService.viewProduct(productId);
                if (p != null) {
                    System.out.println("ID :" + p.getproductId() + "Product Name : " + p.getproductName() + "Price :" + p.getprice());
                } else {
                    System.out.print("Product with Id " + productId + "not found.");
                }

                break;
            }

            case 5 -> {//view all products
                List<product> products = ProductService.viewAllProducts();
                if (products.isEmpty()) {
                    System.out.println("No Products found.");
                } else {
                    System.out.println("All Products :");
                    for (product p : products) {
                        System.out.println("ID :" + p.getproductId() + "Name :" + p.getproductName() + "Price :" + p.getprice());

                    }
                }
                break;
            }

            case 6 -> {   // Add New User

                System.out.println("Enter User Name :");
                String userName = sc.next();
                System.out.println("Enter Email :");
                String email = sc.next();
               UserService.createUser(userName, email);
                break;
            }

            case 7 -> {  //update user
                    System.out.println("Enter USwr ID to update :");
                    int userId = sc.nextInt();
                    System.out.println("Enter new User Email :");
                    String newEmail = sc.next();
                    System.out.println("Enter new User Name :");
                    String newUserName = sc.next();

                    UserService.updateUser(userId , newUserName , newEmail);
                    break;
            }

             case 8 -> { // delete USer
                    System.out.println("Enter User Id  to delete :");
                     int userId = sc.nextInt();
                     UserService.deleteUser(userId);
             }

             case 9 -> { //view user by id

                    System.out.println("Enter user id to view :");
                    int userId = sc.nextInt();
                    user u = UserService.viewUser(userId);
                    if (u != null){
                        System.out.println("ID :" + u.getuserId() + "Name :" + u.getuserName() + "Email ;" + u.getemail());

                    }
                    else{
                        System.out.println("No user Found!");
                    }
                    break;
             }

             case 10 ->{ //view all user

                    List<user> users = UserService.viewAllUser();
                    if(users.isEmpty()){
                        System.out.println("No users Found!");
                    }
                    else {
                        System.out.println("All Users :");
                        for( user u : users){
                            System.out.println("ID :" + u.getuserId() + "Name :" + u.getuserName() + "Email :" + u.getemail());
                        }
                    }
                    break;
             }

             case 11 -> { //exit

                 hibernateUtil.shutDown();
                    System.out.println("Exiting application. Thank you for choosing [ShopSpark Shopping]!");
                    break;
             }

            default ->{
                    System.out.println("Invalid Choice.Please enter a number between (1 to 11)");
                    break;

            }
        }


        }


    }
}