package blcs.lwb.utils.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class GreenDao {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String address;
    @Generated(hash = 534050545)
    public GreenDao(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
    @Generated(hash = 766040118)
    public GreenDao() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
