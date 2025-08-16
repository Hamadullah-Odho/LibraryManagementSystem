package model;

public class Member {

    private int id;
    private String name;
    private String email;
    private String phone;
    private String join_date;

    public void setId(int id){this.id=id;}
    public int getId(){return id;}

    public void setName(String name){this.name=name;}
    public String getName(){return name;}

    public void setEmail(String email){this.email=email;}
    public String getEmail(){return email;}

    public void setPhone(String phone){this.phone=phone;}
    public String getPhone(){return phone;}

    public void setJoin_date(String join_date){this.join_date=join_date;}
    public String getJoin_date(){return join_date;}

    public String toString(){
        String toString = String.format("%-10d %-25s %-35s %-15s %-15s",id,name,email,phone,join_date);
        return toString;
    }

}
