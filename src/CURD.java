import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by el1ven on 14-5-9.
 */
public class CURD extends ActionSupport   {


    private HttpServletRequest request;
    private Connection conn;

    private String userName;
    private String userJob;
    private int userId;

    private ArrayList<News> list = new ArrayList<News>();
    private News news;

    public CURD() throws ClassNotFoundException, SQLException {
        this.request = ServletActionContext.getRequest();
        Class.forName("org.sqlite.JDBC");
        this.conn= DriverManager.getConnection("jdbc:sqlite:test.db");
        this.request.setAttribute("list",this.list);

    }

    public String create () throws ClassNotFoundException, SQLException {



        Statement stat = this.conn.createStatement();
        //stat.executeUpdate("drop table if exists people;");
        //stat.executeUpdate("create table people ( id INTEGER PRIMARY KEY AUTOINCREMENT, name, job);");


        ResultSet rs = stat.executeQuery("select * from people;");
        while (rs.next()) {
            this.news =  new News();
            this.news.setId(rs.getInt(1));
            this.news.setName(rs.getString("name"));
            this.news.setJob(rs.getString("job"));
            this.list.add(this.news);
        }
        rs.close();
        this.conn.close();
        return SUCCESS;

        }

    public String add () throws ClassNotFoundException, SQLException{


        Statement stat = this.conn.createStatement();
        //stat.executeUpdate("drop table if exists people;");
        //stat.executeUpdate("create table people (name, job);");

        PreparedStatement prep = this.conn.prepareStatement("insert into people values (?, ? ,?);");
        prep.setString(2,this.getUserName());
        prep.setString(3,this.getUserJob());
        prep.addBatch();



        this.conn.setAutoCommit(false);
        prep.executeBatch();
        this.conn.setAutoCommit(true);

        ResultSet rs = stat.executeQuery("select * from people;");
        while (rs.next()) {
            this.news =  new News();
            this.news.setId(rs.getInt(1));
            this.news.setName(rs.getString("name"));
            this.news.setJob(rs.getString("job"));
            this.list.add(this.news);


        }
        //request.setAttribute("list",this.list);
        rs.close();
        this.conn.close();
        return SUCCESS;
    }

    public String change() throws SQLException {
        Statement stat = this.conn.createStatement();

        PreparedStatement prep = this.conn.prepareStatement("update people set name=?,job=? where id=?");
        prep.setString(1, this.getUserName());
        prep.setString(2, this.getUserJob());
        prep.setInt(3, this.getUserId());
        prep.addBatch();

        this.conn.setAutoCommit(false);
        prep.executeBatch();
        this.conn.setAutoCommit(true);

        ResultSet rs = stat.executeQuery("select * from people;");
        while (rs.next()) {
            this.news =  new News();
            this.news.setId(rs.getInt(1));
            this.news.setName(rs.getString("name"));
            this.news.setJob(rs.getString("job"));
            this.list.add(this.news);


        }

        rs.close();
        this.conn.close();

        return SUCCESS;
    }

    public String delete() throws SQLException{


        Statement stat = this.conn.createStatement();
        String id1 = request.getParameter("id");
        int id2 = Integer.parseInt(id1);

        PreparedStatement prep = this.conn.prepareStatement("delete from people where id=?");
        prep.setInt(1, id2);
        prep.addBatch();

        this.conn.setAutoCommit(false);
        prep.executeBatch();
        this.conn.setAutoCommit(true);

        ResultSet rs = stat.executeQuery("select * from people;");
        while (rs.next()) {
            this.news =  new News();
            this.news.setId(rs.getInt(1));
            this.news.setName(rs.getString("name"));
            this.news.setJob(rs.getString("job"));
            this.list.add(this.news);


        }

        rs.close();
        this.conn.close();

        return SUCCESS;
    }



    public String getUserName(){
        return userName;
    }
    public String getUserJob(){
        return userJob;
    }
    public int getUserId(){
        return userId;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public void setUserJob(String userJob){
        this.userJob = userJob;
    }
    public void setUserId(int userId){ this.userId = userId; }



}
