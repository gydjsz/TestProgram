package DataLink;

import java.sql.*;

public class Link {
    private String strCon;
    private String strUsr;
    private String strPwd;
    private Statement sql;
    private Connection con;


    public Link(){
        strCon = "jdbc:mysql://localhost:3306/Test";
        strUsr = "root";
        strPwd = "123456";
        startLink();
    }

    public void startLink(){
        System.out.println("正在连接数据库...");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(strCon, strUsr, strPwd);
            System.out.println("连接数据库成功");
            sql = con.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getSqlInsert(String strSql) throws SQLException {
            return sql.executeQuery(strSql);
    }

    public void addData(String workName, int workAge){
        String sqlStr = "INSERT INTO hero(workNum, workName, workAge) VALUES (?, ?, ?)";
        try {
            PreparedStatement pre = con.prepareStatement(sqlStr);
            pre.setInt(1, getRow());
            pre.setString(2, workName);
            pre.setInt(3, workAge);
            pre.execute();
            getRow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getRow(){
        int num = 0;
        try {
            ResultSet rs = sql.executeQuery("SELECT * FROM hero");
            while(rs.next()){
                num++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num + 1;
    }
}
