package blcs.lwb.utils.bean;
import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class SqliteDemo extends LitePalSupport {
    @Column(nullable = true)
    private int id;
    @Column(unique = true, defaultValue = "unknown")
    private String name;
    private String address;
    public SqliteDemo(String name, String address) {
        this.name = name;
        this.address = address;
    }
    public SqliteDemo(int id,String name, String address) {
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
