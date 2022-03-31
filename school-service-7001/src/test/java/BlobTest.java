/**
 * @Author: Zero
 * @Date: 2022/3/29 10:45
 * @Description:
 */

import java.io.*;
import java.sql.*;

public class BlobTest {

    //url
    private static String url = "jdbc:mysql://localhost:3306/springcloud?serverTimezone=GMT%2B8";
    //user
    private static String user = "root";
    //password
    private static String password = "123456";
    //驱动程序类
    private static String driverClass = "com.mysql.cj.jdbc.Driver";


    public static void main(String[] args) {
        write();
        //read();
    }

    //从数据库读取一条记录并抽取第四列的BLOB对象
    private static void read() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = BlobTest.getConnection();
            String sql = "select * from TAB_COMPANY_PROMISE where id=?";
            stmt = conn.prepareStatement(sql);
            //加入sql语句的参数
            stmt.setString(1,"7556AF3FD2F8499EBCFE1B78549C8530");
            rs = stmt.executeQuery();
            //获取查询结果
            if(rs.next()){
                //我的查询结果的第四列是BLOB对象
                Blob blob = rs.getBlob(4);
                //根据自定义的文件名在本地创建空文件,本地创建的文件后缀要与事先
                //保存的文件吻合
                File f = new File("d:"+File.separator+"testblob.pdf");
                OutputStream out = null;
                //初始化字节流
                out = new FileOutputStream(f);
                //以字节流的形式将BLOB对象写入创建的文件  "f"
                out.write(blob.getBytes(1, (int)blob.length()));
                out.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(rs != null){
                    rs.close();
                }
                if(stmt != null){
                    stmt.close();
                }
                if(conn != null)  {
                    conn.close();
                }
            }    catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    private static void write() {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = BlobTest.getConnection();
            String sql = "UPDATE TAB_COMPANY_PROMISE SET PROMISEFILE=? WHERE ID = '7556AF3FD2F8499EBCFE1B78549C8530'";
            stmt = conn.prepareStatement(sql);
            //根据需要上传的文件路径初始化File类对象
            File f = new File("d:"+File.separator+"test.pdf");
            InputStream in = null;
            //获取文件流对象
            in = new FileInputStream(f);
            //将文件读为二进制流，并写到第一个参数中
            stmt.setBinaryStream(1,in,(int)f.length());
            // 执行插入/更新数据库操作
            stmt.executeUpdate();

            System.out.println("添加成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                if(stmt != null){
                    stmt.close();
                }
                if(conn != null)  {
                    conn.close();
                }
            }    catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    /**
     * 获取数据库连接
     */
    public static Connection getConnection(){
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
//        return null;
    }


}
