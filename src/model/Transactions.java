package model;

public class Transactions {

    private int id;
    private int book_id;
    private int member_id;
    private String issue_date;
    private String return_date;
    private Double fine;

    public void setId(int id){this.id=id;}
    public int getId(){return id;}
    public void setBook_id(int book_id){this.book_id=book_id;}
    public int getBook_id(){return book_id;}
    public void setMember_id(int member_id){this.member_id=member_id;}
    public int getMember_id(){return member_id;}
    public void setIssue_date(String issue_date){this.issue_date=issue_date;}
    public String getIssue_date(){return issue_date;}
    public void setReturn_date(String return_date){this.return_date=return_date;}
    public String getReturn_date(){return return_date;}
    public void setFine(Double fine){this.fine=fine;}
    public Double getFine(){return fine;}

    public String toString(){

        return String.format("%-20s %-15s %-15s %-15s %-15s %-10s ",id,book_id,member_id,issue_date,return_date,fine);
    }
}
