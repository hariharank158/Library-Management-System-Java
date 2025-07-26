import java.sql.*;

public class Database {

    public void addBook(String title, String author, int qty) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "INSERT INTO books(title, author, quantity) VALUES (?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, author);
        ps.setInt(3, qty);
        ps.executeUpdate();
        System.out.println("✅ Book added successfully.");
    }

    public void viewBooks() throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "SELECT * FROM books";
        ResultSet rs = con.createStatement().executeQuery(sql);
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id") +
                    ", Title: " + rs.getString("title") +
                    ", Author: " + rs.getString("author") +
                    ", Qty: " + rs.getInt("quantity"));
        }
    }

    public void borrowBook(int bookId, String user) throws Exception {
        Connection con = DBConnection.getConnection();

        // Check if the book is available
        String check = "SELECT quantity FROM books WHERE id=?";
        PreparedStatement ps = con.prepareStatement(check);
        ps.setInt(1, bookId);
        ResultSet rs = ps.executeQuery();

        if (rs.next() && rs.getInt("quantity") > 0) {
            // Insert into issued_books
            String issue = "INSERT INTO issued_books(book_id, user_name) VALUES (?, ?)";
            PreparedStatement issueStmt = con.prepareStatement(issue);
            issueStmt.setInt(1, bookId);
            issueStmt.setString(2, user);
            issueStmt.executeUpdate();

            // Decrease quantity
            String update = "UPDATE books SET quantity = quantity - 1 WHERE id = ?";
            PreparedStatement updateStmt = con.prepareStatement(update);
            updateStmt.setInt(1, bookId);
            updateStmt.executeUpdate();

            System.out.println("✅ Book issued to " + user);
        } else {
            System.out.println("❌ Book not available.");
        }
    }

    public void returnBook(int bookId, String user) throws Exception {
        Connection con = DBConnection.getConnection();

        // Delete from issued_books
        String deleteIssued = "DELETE FROM issued_books WHERE book_id=? AND user_name=? LIMIT 1";
        PreparedStatement ps = con.prepareStatement(deleteIssued);
        ps.setInt(1, bookId);
        ps.setString(2, user);
        int affected = ps.executeUpdate();

        if (affected > 0) {
            // Increase quantity
            String update = "UPDATE books SET quantity = quantity + 1 WHERE id = ?";
            PreparedStatement updateStmt = con.prepareStatement(update);
            updateStmt.setInt(1, bookId);
            updateStmt.executeUpdate();

            System.out.println("✅ Book returned by " + user);
        } else {
            System.out.println("❌ No such issued record.");
        }
    }

    public void deleteBook(int bookId) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "DELETE FROM books WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, bookId);
        ps.executeUpdate();
        System.out.println("✅ Book deleted.");
    }
}
