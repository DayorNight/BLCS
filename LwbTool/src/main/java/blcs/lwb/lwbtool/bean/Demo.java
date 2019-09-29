package blcs.lwb.lwbtool.bean;

/**
 * http://pndatsn5v.bkt.clouddn.com/rxjava_retrofit.txt
 */
public class Demo {
    @Override
    public String toString() {
        return "Demo{" + "id='" + id + '\'' +
                ", appid='" + appid + '\'' +
                ", name='" + name + '\'' +
                ", showtype='" + showtype + '\'' +
                '}';
    }
    private String id;
    private String appid;
    private String name;
    private String showtype;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getAppid() {
        return appid;
    }
    public void setAppid(String appid) {
        this.appid = appid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getShowtype() {
        return showtype;
    }
    public void setShowtype(String showtype) {
        this.showtype = showtype;
    }
}
