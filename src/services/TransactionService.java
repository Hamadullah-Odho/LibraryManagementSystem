package services;
import dao.TransactionDao;
import model.BookIssue;
import model.Transactions;
import util.DateAndTime;
import java.util.List;
import java.util.Scanner;

final public class TransactionService {



    private void transactionRecords(){
        List<Transactions> transactions = TransactionDao.getTransactions();
        String format = String.format("%-20s %-15s %-15s %-15s %-15s %-10s ","Transaction_Id","Book_Id","Member_Id","Issue Date","Return Date","Fine Amount");
        System.out.println("\n" + format);

        for(Transactions t: transactions){
            System.out.println(t.toString());
        }
    }
    public void issueBook(Scanner s) throws Exception{

        System.out.println("\n--- Issuing Book ---");

        new MemberService().showMembers();
        System.out.print("Enter Member Id: ");
        int member_id = Integer.parseInt(s.nextLine());

        System.out.println();

        new BookService().showAllBooks();
        System.out.print("Enter Book Id: ");
        int book_id = Integer.parseInt(s.nextLine());

        System.out.println("Current Date: " + DateAndTime.getDate());
        System.out.print("Enter Return Date: ");
        String return_date = s.nextLine();

        String current_date = DateAndTime.getDate();
        boolean validDate = DateAndTime.compareDate(current_date, return_date);

        if(validDate){
            issueBookHelper(member_id, book_id,current_date, return_date);
        }
        else{
            System.out.println("Invalid Return Date Entered");
        }
    }

    private void issueBookHelper(int member_id, int book_id,String current_date, String return_date){

        Transactions t = new Transactions();

        t.setBook_id(book_id);
        t.setMember_id(member_id);
        t.setIssue_date(current_date);
        t.setReturn_date(return_date);
        t.setFine(0.0);

        boolean issued = TransactionDao.issueTransaction(t);

        if(issued){
            System.out.println("Book issued successfully");
        }
        else{
            System.out.println("Error Issuing Book");
        }
    }

    public void getBookRecord(Scanner s) throws NumberFormatException{
        while (true) {
            transactionRecords();
            System.out.print("\nEnter id for more details (Press 0 for exit):");
            int id = Integer.parseInt(s.nextLine());
            if (id > 0) {
                IssueRecordDetails(id);
            }
            else{
                return;
            }
        }
    }

    private void IssueRecordDetails(int id){
        BookIssue b = TransactionDao.getBookRecord(id);

        String format = String.format("%-10s %-25s %-20s %-40s %-15s %-15s ","T_Id","Issued_To","Contact","Book_Title","Issued_Date","Return_Date");
        System.out.println("\n" + format);
        System.out.println(b);
    }

    public void returnBook(Scanner s) throws NumberFormatException{
        while(true) {
            transactionRecords();
            System.out.print("\nEnter id for return (Press 0 for exit):");
            int id = Integer.parseInt(s.nextLine());

            if (id > 0) {
                boolean success = TransactionDao.returnBook(id);
                if (success) {
                    System.out.println("Book returned successfully");
                } else {
                    System.out.println("Error returning Book");
                }
            }
            else{
                return;
            }
        }

    }

}
