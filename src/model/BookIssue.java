package model;

public class BookIssue {

    private int id;
    private String bookName;
    private String issueDate;
    private String issuedTo;
    private String returnDate;
    private String issuedToContact;

    public void setId(int id){this.id=id;}
    public void setIssuedToContact(String issuedToContact) {this.issuedToContact = issuedToContact;}
    public void setBookName(String bookName){this.bookName = bookName;}
    public void setIssueDate(String issueDate){this.issueDate = issueDate;}
    public void setIssuedTo(String issuedTo){this.issuedTo = issuedTo;}
    public void setReturnDate(String returnDate){this.returnDate = returnDate;}

    public int getId(){return id;}
    public String getIssuedToContact(){return issuedToContact;}
    public String getBookName(){return this.bookName;}
    public String getIssueDate(){return this.issueDate;}
    public String getIssuedTo(){return this.issuedTo;}
    public String getReturnDate(){return this.returnDate;}

    public String toString(){
        return String.format("%-10s %-25s %-20s %-40s %-15s %-15s ",id,issuedTo,issuedToContact,bookName,issueDate,returnDate);
    }

}
