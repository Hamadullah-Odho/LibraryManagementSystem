package dao;
import connection.DBConnection;
import model.Book;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
final public class BookDao {

    private BookDao() {}
    static{
        getAllBooks();
    }

    public static List<Book> getAllBooks() {
        String query = "SELECT * FROM book;";
        List<Book> books = new ArrayList<>();
        try(Connection con = DBConnection.getConnection()){
            try(PreparedStatement ps = con.prepareStatement(query)){
                try(ResultSet rs = ps.executeQuery()){
                    while(rs.next()) {
                        Book book = new Book();
                        book.setId(rs.getInt("id"));
                        book.setName(rs.getString("title"));
                        book.setPublish_year(rs.getInt("pub_year"));
                        book.setCategory(rs.getString("category"));
                        book.setAvailable_copies(rs.getInt("available_copies"));
                        book.setTotal_copies(rs.getInt("total_copies"));
                        books.add(book);
                    }
                }
            }
        }
        catch (SQLException e){
            System.out.println("Error in getAllBooks()" + e.getMessage());
        }
        return books;
    }



    public static boolean addBook(Book book) {
        String query = "INSERT INTO book (title,pub_year,category,total_copies,available_copies) VALUES (?, ?, ?, ?, ?)";
        int rowsAffected = 0;

        try(Connection con = DBConnection.getConnection()){
            try(PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, book.getName());
                ps.setInt(2, book.getPublish_year());
                ps.setString(3, book.getCategory());
                ps.setInt(4, book.getTotal_copies());
                ps.setInt(5, book.getAvailable_copies());
                rowsAffected = ps.executeUpdate();
            }
        }
        catch (SQLException e){
            System.out.println("Error in addBook()" + e.getMessage());
        }
        return rowsAffected > 0;
    }



    public static Book searchBook(int id) {
        String query = "SELECT * FROM book WHERE id = ?;";
        Book book = null;
        try(Connection c = DBConnection.getConnection()) {
            try (PreparedStatement ps = c.prepareStatement(query)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        book = new Book();
                        book.setId(rs.getInt("id"));
                        book.setName(rs.getString("title"));
                        book.setPublish_year(rs.getInt("pub_year"));
                        book.setCategory(rs.getString("category"));
                        book.setTotal_copies(rs.getInt("total_copies"));
                        book.setAvailable_copies(rs.getInt("available_copies"));
                    }
                }
            }
        }
        catch (SQLException e){
            System.out.println("Error in searchBook()" + e);
        }
        return book;
    }



    public static boolean deleteBook(int id){
        String query = "DELETE FROM book WHERE id = ?;";
        int rowsAffected = 0;

        try(Connection con = DBConnection.getConnection()){
            try(PreparedStatement st = con.prepareStatement(query)){
                st.setInt(1, id);
                rowsAffected = st.executeUpdate();
            }
        }
        catch (SQLException e){
            System.out.println("Error in deleteBook()" + e.getMessage());
        }
        return rowsAffected > 0;
    }



    public static void bookQuantity(Connection c,int book_id,boolean decrease) throws SQLException{
        String decrement = "UPDATE book SET available_copies = available_copies - 1 WHERE id = ?";
        String increment = "UPDATE book SET available_copies = available_copies + 1 WHERE id = ?";

        if(decrease) {
            try(PreparedStatement ps = c.prepareStatement(decrement)){
                ps.setInt(1, book_id);
                ps.executeUpdate();
            }
        }
        else{
            try(PreparedStatement ps = c.prepareStatement(increment)){
                ps.setInt(1, book_id);
                ps.executeUpdate();
            }
        }

    }

}
