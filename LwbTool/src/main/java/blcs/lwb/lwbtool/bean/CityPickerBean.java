package blcs.lwb.lwbtool.bean;

import java.util.ArrayList;
import java.util.List;

public class CityPickerBean {
    List<AllCityBean> province;
    ArrayList<ArrayList<String>>  city;
    ArrayList<ArrayList<ArrayList<String>>> county ;
    public List<AllCityBean> getProvince() {
        return province;
    }

    public void setProvince(List<AllCityBean> province) {
        this.province = province;
    }

    public ArrayList<ArrayList<String>> getCity() {
        return city;
    }

    public void setCity(ArrayList<ArrayList<String>> city) {
        this.city = city;
    }

    public ArrayList<ArrayList<ArrayList<String>>> getCounty() {
        return county;
    }

    public void setCounty(ArrayList<ArrayList<ArrayList<String>>> county) {
        this.county = county;
    }


}
