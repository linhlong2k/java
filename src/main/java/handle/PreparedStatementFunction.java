package handle;

import general.Objects;

import java.sql.*;

public class PreparedStatementFunction {
    private Connection con;

    public PreparedStatementFunction() {
        this.con = base.SQLServerConnection.getConnection();
    }

    public int createRowTable1UsingProcedure(Objects obj) {
        int res = -1;
        PreparedStatement PStatement = null;

        try {
            String query = "USE jdbc; EXEC SP_insert_into_table1_not_return_identity ?, ?;";

            if(con != null) {
                PStatement = con.prepareStatement(query);

                PStatement.setNString(1, obj.getName());
                PStatement.setInt(2, obj.getAge());

                res = PStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("statementFunction createRowTable1UsingProcedure " + e);
        }
        return res;
    }

    public int createRowTable1(Objects obj) {
        int res = -1;
        PreparedStatement PStatement;
        try {
            String query = "USE [jdbc]; INSERT INTO [dbo].[Table1] ([name], [age]) VALUES (?, ?);";

            if(con != null){
                PStatement = con.prepareStatement(query);

                PStatement.setNString(1, obj.getName());
                PStatement.setInt(2, obj.getAge());

                res = PStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("statementFunction createRowTable1 " + e);
        }
        return res;
    }
}
