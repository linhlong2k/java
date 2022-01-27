package handle;

import java.sql.Connection;
import java.sql.SQLException;

public class Transaction {
    /*
    * Transaction là tập hợp các statement được thực hiện (hoặc không thực hiện) dưới dạng đơn lẻ...
    *
    *
    * */

    public void transaction() throws SQLException {
        //Kết nối cơ sỏ dữ liệu
        Connection con = base.SQLServerConnection.getConnection();
        try {
            //bắt đầu một transaction
            con.setAutoCommit(false);

            // câu lệnh tương tác với database đặt ở đây

            //Commit các lệnh phía trên lên database
            con.commit();
        } catch(Exception e) {
            //Nếu có bất kì vấn đề gì xảy ra trong quá trình tương tác với database thì những giá trị của các đối tượng sẽ được phục hồi như trước khi tương tác với database.
            con.rollback();
        } finally {
            if(con != null) {
                //đóng kết nối
                con.close();
            }
        }
    }
}
