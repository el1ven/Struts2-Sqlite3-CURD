/**
 * Created by el1ven on 14-5-10.
 */
public class News {
    private int id;
    private String name;//对应数据库里面的字段
    private String job;

    //设置get,set方法
    public void setId(int id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setJob(String job){
        this.job = job;
    }
    public int getId(){return this.id; }
    public String getName(){
        return this.name;
    }
    public String getJob(){
        return this.job;
    }
}
