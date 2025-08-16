package dao;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import connection.DBConnection;
import model.BookIssue;
import model.Transactions;
import util.DateAndTime;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

final public class TransactionDao {

    private TransactionDao() {}

    static{
        getTransactions();
        fineValidate();
    }

    public static boolean issueTransaction(Transactions t){
        String query = "INSERT INTO transactions(book_id,member_id,return_date,issue_date,fine) VALUES (?, ?, ?, ?, ?)";
        int rowAffected = 0;
        try(Connection con = DBConnection.getConnection()){
            try(PreparedStatement ps = con.prepareStatement(query)){
                ps.setInt(1, t.getBook_id());
                ps.setInt(2, t.getMember_id());
                ps.setString(3, t.getReturn_date());
                ps.setString(4, t.getIssue_date());
                ps.setDouble(5, t.getFine());
                rowAffected = ps.executeUpdate();

                BookDao.bookQuantity(con,t.getBook_id(),true);

            }
        }
        catch (SQLException e){
            System.out.println("Error in issueBook :" + e.getMessage());
        }
        return rowAffected > 0;
    }

    public static List<Transactions> getTransactions(){
        List<Transactions> transactions = new ArrayList<>();
        String query = "SELECT * FROM transactions";

        try(Connection con = DBConnection.getConnection()){
            try(PreparedStatement ps = con.prepareStatement(query)){
                try(ResultSet rs = ps.executeQuery()){
                    while(rs.next()){
                        Transactions t = new Transactions();
                        t.setId(rs.getInt("id"));
                        t.setBook_id(rs.getInt("book_id"));
                        t.setMember_id(rs.getInt("member_id"));
                        t.setReturn_date(rs.getString("return_date"));
                        t.setIssue_date(rs.getString("issue_date"));
                        t.setFine(rs.getDouble("fine"));
                        transactions.add(t);
                    }
                }
            }
        }
        catch (SQLException e){
            System.out.println("Error in getBookRecord :" + e.getMessage());
        }
        return transactions;
    }

    public static BookIssue getBookRecord(int id){
        BookIssue bookIssue = new BookIssue();
        String query = "SELECT t.id, m.name, m.phone, b.title,t.issue_date, t.return_date " +
                "FROM transactions t " +
                "JOIN book b ON t.book_id = b.id " +
                "JOIN member m ON t.member_id = m.id " +
                "WHERE t.id = ?;";


        try(Connection con = DBConnection.getConnection()){
            try(PreparedStatement ps = con.prepareStatement(query)){
                ps.setInt(1, id);
                try(ResultSet rs = ps.executeQuery()){
                    while(rs.next()){
                        bookIssue.setId(rs.getInt("id"));
                        bookIssue.setIssuedTo(rs.getString("name"));
                        bookIssue.setIssuedToContact(rs.getString("phone"));
                        bookIssue.setBookName(rs.getString("title"));
                        bookIssue.setIssueDate(rs.getString("issue_date"));
                        bookIssue.setReturnDate(rs.getString("return_date"));
                    }
                }
            }
        }
        catch (SQLException e){
            System.out.println("Error in getBookRecord :" + e.getMessage());
        }
        return bookIssue;
    }

    private static void fineValidate(){

        String query = "UPDATE transactions SET fine = ?  WHERE id = ?;";
        List<Transactions> transactions = TransactionDao.getTransactions();
        Iterator<Transactions> iterator = transactions.iterator();

        try(Connection con = DBConnection.getConnection()){
            try(PreparedStatement ps = con.prepareStatement(query)){

                int fine = 200;
                while(iterator.hasNext()){
                    Transactions t = iterator.next();
                    if(DateAndTime.validateDate(t.getReturn_date())){

                        ps.setInt(1,fine);
                        ps.setInt(2,t.getId());
                        ps.executeUpdate();
                    }
                }
            }
        }
        catch(SQLException e){
            System.out.println("Error in Fine Validate : " + e.getMessage());
        }

    }

    public static boolean returnBook(int id){
        String query = "DELETE FROM transactions WHERE Id = ?";
        int rowsAffected = 0;

        try(Connection con = DBConnection.getConnection()){
            try (PreparedStatement ps = con.prepareStatement(query)){
                ps.setInt(1, id);
                rowsAffected = ps.executeUpdate();
                BookDao.bookQuantity(con,id,false);
            }
        }
        catch(SQLException e){
            System.out.println("Error Deleting Record" + e.getMessage());
        }
        return  rowsAffected > 0;
    }
}
