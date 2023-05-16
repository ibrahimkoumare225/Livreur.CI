package com.koumare.livraisonci;

public class User {
    public String username;
    public String password;
    public String email;
    public String telephone;



    public  User (){

    }
    public  User(String username,String password,String email,String telephone){
        this.username = username;
        this.password = password;
        this.email = email;
        this.telephone = telephone;
    }
}
