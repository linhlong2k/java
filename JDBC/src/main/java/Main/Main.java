package Main;

import general.Objects;
import handle.PreparedStatementFunction;
import handle.StatementFunction;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Chương trình database " +
                "\nI. Using Statement" +
                "\n\t1. findAll" +
                "\n\t2. Add a row to table1" +
                "\nII. Using PreparedStatement" +
                "\n\t3. Add a row to table1 using procedure" +
                "\n\t4. Add a row to table1 not using procedure" +
                "\nIII. Using callableStatement" +
                "\n\t5. Add a row to table1 not return identity" +
                "\n\t6. Add a row to table1 return identity" +
                "\n\t7. Find all row in table1 (solution 1)" +
                "\n\t8. Find all row in table1 (solution 2)" +
                "\nOther. exit" +
                "\nNhập lựa chọn của bạn: ");
        int n = scan.nextInt();
        switch (n) {
            case 1:
                System.out.println("Danh sách đối tượng trong table 1");
                new StatementFunction().findAllTable1().forEach(System.out::println);
                break;
            case 2:
                System.out.println("Thêm đối tượng mới vào table 1");
                System.out.print("Nhập tên: ");
                scan.nextLine();
                String name2 = scan.nextLine();
                System.out.print("Nhập tuổi: ");
                int age2 = scan.nextInt();

                System.out.println(new StatementFunction().createRowTable1(new Objects(0, name2, age2)));
                break;
            case 3:
                System.out.println("Thêm đối tượng mới vào table 1");
                System.out.print("Nhập tên: ");
                scan.nextLine();
                String name3 = scan.nextLine();
                System.out.print("Nhập tuổi: ");
                int age3 = scan.nextInt();

                System.out.println(new PreparedStatementFunction().createRowTable1UsingProcedure(new Objects(0, name3, age3)));
                break;
            case 4:
                System.out.println("Thêm đối tượng mới vào table 1");
                System.out.print("Nhập tên: ");
                scan.nextLine();
                String name4 = scan.nextLine();
                System.out.print("Nhập tuổi: ");
                int age4 = scan.nextInt();

                System.out.println(new PreparedStatementFunction().createRowTable1(new Objects(0, name4, age4)));
                break;
            case 5:
                System.out.println("Thêm đối tượng mới vào table 1");
                System.out.print("Nhập tên: ");
                scan.nextLine();
                String name5 = scan.nextLine();
                System.out.print("Nhập tuổi: ");
                int age5 = scan.nextInt();

                System.out.println(new handle.CallableStatementFunction().createRowTable1NotReturnValue(new Objects(0, name5, age5)));
                break;
            case 6:
                System.out.println("Thêm đối tượng mới vào table 1");
                System.out.print("Nhập tên: ");
                scan.nextLine();
                String name6 = scan.nextLine();
                System.out.print("Nhập tuổi: ");
                int age6 = scan.nextInt();

                System.out.println(new handle.CallableStatementFunction().createRowTable1ReturnValue(new Objects(0, name6, age6)));
                break;
            case 7:
                System.out.println("Danh sách đối tượng trong table 1");
                new handle.CallableStatementFunction().createRowTable1ReturnResultset().forEach(System.out::println);
                break;
            case 8:
                System.out.println("Danh sách đối tượng trong table 1");
                new handle.CallableStatementFunction().createRowTable1ReturnResultset2().forEach(System.out::println);
                break;
            default:
                break;
        }
    }
}
