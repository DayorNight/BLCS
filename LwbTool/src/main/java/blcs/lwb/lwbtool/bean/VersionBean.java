package blcs.lwb.lwbtool.bean;

public class VersionBean {

    /**
     * Id : 1
     * VersionName : 1.0.3
     * PublicTime : 2019-04-23T00:00:00
     * VersionIntroduce : 1、优化体验
     * VersionNo : 4
     * ApkUrl : 123132132
     */

    private int Id;
    private String VersionName;
    private String PublicTime;
    private String VersionIntroduce;
    private String VersionNo;
    private String ApkUrl;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getVersionName() {
        return VersionName;
    }

    public void setVersionName(String VersionName) {
        this.VersionName = VersionName;
    }

    public String getPublicTime() {
        return PublicTime;
    }

    public void setPublicTime(String PublicTime) {
        this.PublicTime = PublicTime;
    }

    public String getVersionIntroduce() {
        return VersionIntroduce;
    }

    public void setVersionIntroduce(String VersionIntroduce) {
        this.VersionIntroduce = VersionIntroduce;
    }

    public String getVersionNo() {
        return VersionNo;
    }

    public void setVersionNo(String VersionNo) {
        this.VersionNo = VersionNo;
    }

    public String getApkUrl() {
        return ApkUrl;
    }

    public void setApkUrl(String ApkUrl) {
        this.ApkUrl = ApkUrl;
    }
}
