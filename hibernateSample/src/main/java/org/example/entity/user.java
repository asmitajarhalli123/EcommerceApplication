package org.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int userId ;


    String userName ;
    String email;

    public int getuserId() {
        return userId;
    }

    public void setuserId(int productId) {
        this.userId = userId;
    }

    public String getuserName(){
        return userName ;
    }

    public void setuserName (String userName){
        this.userName = userName;
    }

    public String getemail(){
        return email;
    }

    public String setemail(String email){
        return this.email  = email;
    }

}
