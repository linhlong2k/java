package handle;

import general.Objects;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CallableStatementFunction {
    /*
    * Được sử dụng để tương tác với procedure
    * */
    private Connection con;

    public CallableStatementFunction() {this.con = base.SQLServerConnection.getConnection();}

    public int createRowTable1NotReturnValue (Objects obj) {
        int res = -1;
        try {
            CallableStatement CStmt = con.prepareCall("{call [dbo].[SP_insert_into_table1_not_return_identity](?,?)}");
            CStmt.setNString(1, obj.getName());
            CStmt.setInt(2, obj.getAge());
            CStmt.execute();
            res = 1;
        } catch (Exception e) {
            System.err.println("CallableStatementFunction createRowTable1NotReturnValue " + e);
        }
        return res;
    }

    public int createRowTable1ReturnValue (Objects obj) {
        int res = -1;
        try {
            CallableStatement CStmt = con.prepareCall("{? = call [dbo].[SP_insert_into_table1](?,?)}");
            CStmt.registerOutParameter(1, java.sql.Types.INTEGER);
            CStmt.setNString(2, obj.getName());
            CStmt.setInt(3, obj.getAge());
            CStmt.execute();
            res = CStmt.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }


    public List<Objects> createRowTable1ReturnResultset () {
        ResultSet rs = null;
        List<Objects> res = new ArrayList<>();
        try {
            CallableStatement CStmt = con.prepareCall("{call SP_FIND_ALL_TABLE1()}");

            rs = CStmt.executeQuery();
            while (rs.next()){
                res.add(new Objects(rs.getInt(1), rs.getNString(2), rs.getInt(3)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public List<Objects> createRowTable1ReturnResultset2 () {
        List<Objects> res = new ArrayList<>();
        try {
            CallableStatement CStmt = con.prepareCall("{call SP_FIND_ALL_TABLE1()}",
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);

            boolean results = CStmt.execute();
            int rowsAffected = 0;
            ResultSet rs = null;

            while (results || rowsAffected != -1) {
                if (results) {
                    rs = CStmt.getResultSet();
                    break;
                } else {
                    rowsAffected = CStmt.getUpdateCount();
                }
                results = CStmt.getMoreResults();
            }
            while (rs.next()){
                res.add(new Objects(rs.getInt(1), rs.getNString(2), rs.getInt(3)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
