import java.sql.*;

/**
 * Created by User on 02.08.2016.
 */
public class JDBC {
    public static void main(String[] args) {
        Connection con=null;//������ ��� ���� � ��
        PreparedStatement st=null; //������, ������� ����� ��������� ������� � ��
        ResultSet rs=null;//��� �������� � ��
        try{
            Class.forName("org.gjt.mm.mysql.Driver");//������ ����� ������� � ���

            con= DriverManager.getConnection("jdbc:mysql://127.0.0.1/library", "root", "030588");//������������� ����

            String sql = "INSERT INTO users(id, login, password, role) VALUES(?,?,?,?)";
            st=con.prepareStatement(sql);

            st.setInt(1,6);
            st.setString(2,"Ryzhaya");
            st.setString(3,"nastik");
            st.setString(4,"user");


            st.executeUpdate();

            rs=st.executeQuery("select * from users");//� ����� ������� �������
            while (rs.next()){
                System.out.println(rs.getInt("id")+"   "+rs.getString("login"));
            }
        }catch (ClassNotFoundException e){e.printStackTrace();
        }catch (SQLException e){e.printStackTrace();
        }finally {
            try{
                if (con!=null){
                    con.close();
                }
            }catch (SQLException e){}
        }
    }
}
