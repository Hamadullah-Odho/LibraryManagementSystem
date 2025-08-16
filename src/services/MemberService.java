package services;
import dao.MemberDao;
import model.Member;
import util.DateAndTime;
import java.util.List;
import java.util.Scanner;


final public class MemberService {


    public void showMembers(){
        List<Member> members = MemberDao.getAllMembers();
        try {
            String header = String.format("%-10s %-25s %-35s %-15s %-15s","Id","Name","Email","Phone","Join Date");
            System.out.println(header);
            for (Member member : members) {
                System.out.println(member.toString());
            }
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Error : " + e.getMessage());
        }

    }

    public void createMember(Scanner sc){
        try{
            System.out.println("--- Creating member ---");
            System.out.print("Enter Name :");
            String name = sc.nextLine();
            System.out.print("Enter Email :");
            String email = sc.nextLine();
            System.out.print("Enter Phone :");
            String phone = sc.nextLine();
            boolean success = createMemberHelper(name,email,phone);
            if(success){
                System.out.println("Member Created Successfully");
            }
            else{
                System.out.println("Member Creation Failed");
            }
        }
        catch (NumberFormatException e){
            System.out.println("Error : " + e.getMessage());
        }
        catch (Exception e){
            System.out.println("Unexpected Error : " + e.getMessage());
        }
    }

    private boolean createMemberHelper(String name,String email,String phone)throws Exception{
        Member member = new Member();
        member.setName(name);
        member.setEmail(email);
        member.setPhone(phone);
        member.setJoin_date(DateAndTime.getDate());
        return MemberDao.addMember(member);
    }
}
