import java.util.*;

public class main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Database dao = new Database();
        while (true) {
            System.out.println("\n📚 Library Management");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Delete Book");
            System.out.println("6. Exit");
            System.out.print("Choose: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Author: ");
                    String author = sc.nextLine();
                    System.out.print("Quantity: ");
                    int qty = sc.nextInt();
                    dao.addBook(title, author, qty);
                    break;
                case 2:
                    dao.viewBooks();
                    break;
                case 3:
                    System.out.print("Book ID: ");
                    int bId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("User name: ");
                    String user = sc.nextLine();
                    dao.borrowBook(bId, user);
                    break;
                case 4:
                    System.out.print("Book ID: ");
                    int rId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("User name: ");
                    String rUser = sc.nextLine();
                    dao.returnBook(rId, rUser);
                    break;
                case 5:
                    System.out.print("Book ID to delete: ");
                    int dId = sc.nextInt();
                    dao.deleteBook(dId);
                    break;
                case 6:
                    System.out.println("👋 Exiting...");
                    System.exit(0);
            }
        }
    }
}
