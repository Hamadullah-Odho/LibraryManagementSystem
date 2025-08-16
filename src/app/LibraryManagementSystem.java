package app;
import java.util.Scanner;
import services.*;
public class LibraryManagementSystem {

    public static void main(String[] args) throws InterruptedException {

        Scanner input = new Scanner(System.in);
        boolean flag = true;

        while(flag){

            try{
                System.out.println("\n-- Library Management System --\n");
                System.out.println("1. Add Book");
                System.out.println("2. View Books");
                System.out.println("3. Issue Book");
                System.out.println("4. Return Book");
                System.out.println("5. Delete Book");
                System.out.println("6. Search Book");
                System.out.println("7. Book Issue Records");
                System.out.println("\n8. Add Member");
                System.out.println("9. View Members");
                System.out.println("\n10. Exit");
                System.out.print("Enter your choice: ");
                int userInput = Integer.parseInt(input.nextLine());

                switch (userInput){
                    case 1:
                        new BookService().addBook(input);
                        break;
                    case 2:
                        new BookService().showAllBooks();
                        break;
                    case 3:
                        new TransactionService().issueBook(input);
                        break;
                    case 4:
                        new TransactionService().returnBook(input);
                        break;
                    case 5:
                        new BookService().deleteBook(input);
                        break;
                    case 6:
                        new BookService().searchBook(input);
                        break;
                    case 7:
                        new TransactionService().getBookRecord(input);
                        break;
                    case 8:
                        new MemberService().createMember(input);
                        break;
                    case 9:
                        new MemberService().showMembers();
                        break;
                    case 10:
                        flag = false;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }

            }
            catch(NumberFormatException e){
                System.out.println("Input Error :"+ e.getMessage());
            }
            catch (Exception e){
                System.out.println("Error: "  + e.getMessage());
            }
        }
        input.close();

        String outro = "LMS has been terminated";
        for(char c : outro.toCharArray()){
            System.out.print(c);
            Thread.sleep(70);
        }
    }
}
