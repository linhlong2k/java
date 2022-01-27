package handle;

import general.Objects;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StatementFunction {

    private Connection con;
    private Statement stmt;

    public StatementFunction() {
        this.con = base.SQLServerConnection.getConnection();
    }

    public int update(String input) {
        int res = -1;
        try {
            Statement stmt;

            if(con != null){
                stmt = con.createStatement();
                res = stmt.executeUpdate(input);
            }
        } catch (SQLException e) {
            System.err.println("statementFunction update " + e);
        }
        return res;
    }

    public ResultSet query(String input) {
        ResultSet res = null;
        try {
            Statement stmt;

            if(con != null) {
                stmt = con.createStatement();
                res = stmt.executeQuery(input);
            }
        } catch (SQLException e) {
            System.err.println("statementFunction query " + e);
        }
        return res;
    }


    //=======================================================================example====================================

    public List<Objects> findAllTable1() {
        List<Objects> res = new ArrayList<>();
        ResultSet rs = null;
        Statement stmt;

        try {
            String query = "select * from Table1";

            if(con != null) {
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while(rs.next()) {
                    res.add(new Objects(rs.getInt("id"), rs.getNString("name"), rs.getInt("age")));
                    //Có thể dùng res.add(new Objects(rs.getInt(1), rs.getNString(2)));
                }
                stmt.close();
            }
        } catch (SQLException e) {
            System.err.println("statementFunction findAllTable1 " + e);
        }
        return res;
    }

    public int createRowTable1(Objects obj) {
        int res = -1;
        Statement stmt;
        try {
            String query = "USE [jdbc]; INSERT INTO [dbo].[Table1] ([name], [age]) VALUES ('" + obj.getName() + "', " + obj.getAge() + ");";

            if(con != null){
                stmt = con.createStatement();
                res = stmt.executeUpdate(query);

                stmt.close();
            }
        } catch (SQLException e) {
            System.err.println("statementFunction createRowTable1 " + e);
        }
        return res;
    }

}
