package services;
import dao.BookDao;
import model.Book;
import java.util.List;
import java.util.Scanner;

final public class BookService {


    public void showAllBooks() throws Exception{
        List<Book> books = BookDao.getAllBooks();

        String heading = String.format("%-10s %-45s %-15s %-15s %-15s %-15s","id","name", "category", "publish_year", "total_copies", "available_copies");
        System.out.println(heading);

        for(Book book : books) {
            System.out.println(book);
        }
    }

    public void addBook(Scanner s) throws Exception {

        System.out.print("Enter Book Title :");
        String title = s.nextLine();
        System.out.print("Enter Book Category :");
        String category = s.nextLine();
        System.out.print("Enter Book Publish Year :");
        int publish_year = Integer.parseInt(s.nextLine());
        System.out.print("Enter Book Total Copies :");
        int total_copies = Integer.parseInt(s.nextLine());
        addBookHelper(title, category, publish_year, total_copies);

    }

    private void addBookHelper(String title,  String category, int publish_year, int total_copies) throws NumberFormatException {
        Book book = new Book();

        book.setName(title);
        book.setCategory(category);
        book.setPublish_year(publish_year);
        book.setTotal_copies(total_copies);
        book.setAvailable_copies(total_copies);

        boolean success = BookDao.addBook(book);

        if (success) {
            System.out.println("Book added successfully");
        }
        else  {
            System.out.println("Error adding Book");
        }
    }

    public void searchBook(Scanner s) throws NumberFormatException{
        while(true) {
            System.out.print("Enter Book Id (press 0 for exit): ");
            int id = Integer.parseInt(s.nextLine());

            if(id > 0) {
                Book book = BookDao.searchBook(id);
                if (book != null) {
                    searchBookHelper(book);
                } else {
                    System.out.println("Book not found");
                }
            }
            else{
                return;
            }
        }
    }

    private void searchBookHelper(Book book) {

        String heading = String.format("%-10s %-45s %-15s %-15s %-15s %-15s","id","name", "category", "publish_year", "total_copies", "available_copies");

        System.out.println("\nBook Found");
        System.out.println(heading);
        System.out.println(book.toString());
    }

    public void deleteBook(Scanner s) throws Exception {

        while (true) {
            showAllBooks();
            System.out.print("Enter Book Id (press 0 for exit): ");
            int id = Integer.parseInt(s.nextLine());

            if(id > 0) {
                boolean success = BookDao.deleteBook(id);
                if (success) {
                    System.out.println("Book deleted successfully");
                } else {
                    System.out.println("Error Deleting Book");
                }
            }
            else {
                return;
            }
        }
    }

}
