package springmvc;

public class RegInfo
{
    private int  id;
    private String name ;
    private String password ;
    private String sex ;
    private String tel ;
    private String address ;

    public int getId(){
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getAddress()
    {
        return address;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public String getSex()
    {
        return sex;
    }
    public void setSex(String sex)
    {
        this.sex = sex;
    }
    public String getTel()
    {
        return tel;
    }
    public void setTel(String tel)
    {
        this.tel = tel;
    }

    @Override
    public String toString(){
        return "RegInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
