
import java.sql.*;

/**
 * Created by User on 29.07.2016.
 */
public class JDBC {
    public static void main(String[] args) {
        Connection con=null;//объект для соед с БД
        Statement st=null; //объект, который умеет выполнять запросы к БД
        ResultSet rs=null;//для запросов к БД
        try{
            Class.forName("org.gjt.mm.mysql.Driver");//грузим класс драйвер в пам
            con= DriverManager.getConnection("jdbc:mysql://127.0.0.1/library","root","030588");//устанавливаем соед
            st=con.createStatement();
            rs=st.executeQuery("select * from users");//в ответ получим таблицу
            while (rs.next()){
                System.out.println(rs.getInt("id")+"   "+rs.getString("login"));
            }
        }catch (ClassNotFoundException e){e.printStackTrace();
        }catch (SQLException e){e.printStackTrace();
        }finally {
            try{
                if (st!=null)
                    st.close();
            }catch (SQLException e){}
            try{
                if (con!=null){
                    con.close();
                }
            }catch (SQLException e){}
        }
    }
}
