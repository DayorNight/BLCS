package blcs.lwb.lwbtool.bean;

public class CountryBean {

    @Override
    public String toString() {
        return "CountryBean{" +
                "en='" + en + '\'' +
                ", cn='" + cn + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    /**
     * en : Angola
     * cn : 安哥拉
     * code : +0244
     */

    private String en;
    private String cn;
    private String code;

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
