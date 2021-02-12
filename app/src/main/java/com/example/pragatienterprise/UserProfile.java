package com.example.pragatienterprise;




public class UserProfile {
   public String a_mob;
    public String   b_fullname;
    public String  city;
    public String  email;
    UserProfile() {
    }

    public String getA_mob() {
        return a_mob;
    }

    public void setA_mob(String a_mob) {
        this.a_mob = a_mob;
    }

    public String getB_fullname() {
        return b_fullname;
    }

    public void setB_fullname(String b_fullname) {
        this.b_fullname = b_fullname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public UserProfile(String a_mob, String b_fullname, String city, String email, String uname) {
        this.a_mob = a_mob;
        this.b_fullname = b_fullname;
        this.city = city;
        this.email = email;
        this.uname = uname;
    }


    public String  uname;





}


