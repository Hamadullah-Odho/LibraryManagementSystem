package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import connection.DBConnection;
import model.Member;
public class MemberDao {

    private MemberDao() {}

    public static List<Member> getAllMembers() {

        String query = "SELECT * FROM member;";
        List<Member> members = new ArrayList<>();

        try(Connection con = DBConnection.getConnection()){
            try(PreparedStatement ps = con.prepareStatement(query)){
                try(ResultSet rs = ps.executeQuery()){
                    while (rs.next()) {
                        Member member = new Member();
                        member.setId(rs.getInt("id"));
                        member.setName(rs.getString("name"));
                        member.setEmail(rs.getString("email"));
                        member.setPhone(rs.getString("phone"));
                        member.setJoin_date(rs.getString("join_date"));
                        members.add(member);
                    }
                }
            }
        }
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
        return members;
    }



    public static boolean addMember(Member member) {

        String query = "INSERT INTO member(name, email, phone, join_date) VALUES (?, ?, ?, ?);";
        int rowsAffected = 0;

        try(Connection con = DBConnection.getConnection()){
            try(PreparedStatement ps = con.prepareStatement(query)){
                ps.setString(1, member.getName());
                ps.setString(2, member.getEmail());
                ps.setString(3, member.getPhone());
                ps.setString(4, member.getJoin_date());
                rowsAffected = ps.executeUpdate();
            }
        }
        catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }
        return rowsAffected > 0;
    }


    public static Member searchMember(int id) {
        String query = "SELECT * FROM member WHERE id = ?;";
        Member member = new Member();

        try (Connection con = DBConnection.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(query)) {
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        member.setId(id);
                        member.setName(rs.getString("name"));
                        member.setEmail(rs.getString("email"));
                        member.setPhone(rs.getString("phone"));
                        member.setJoin_date(rs.getString("join_date"));
                    } else {
                        System.out.println("No member with id: " + id);
                    }
                }
            }
        }
        catch (SQLException e) {
            System.out.println("Error searching member: " + e.getMessage());
        }
        return member;
    }
}
