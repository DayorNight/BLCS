package blcs.lwb.lwbtool.utils;

import android.content.Context;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

import blcs.lwb.lwbtool.bean.AllCityBean;
import blcs.lwb.lwbtool.bean.CityBean;
import blcs.lwb.lwbtool.bean.CityPickerBean;
import blcs.lwb.lwbtool.bean.CountryBean;

/**
 * 获取国家名称
 * 获取省份
 * 根据省份获取市区
 * 获取县
 * 获取三级联动数据
 */
public class LinCountry {

    /**
     * 获取国家名称
     */
    public static List<CountryBean> getCountry(Context context) {
        String json = MyUtils.getJson(context, "country.json");
        return JSON.parseArray(json, CountryBean.class);
    }

    /**
     * 获取省份
     */
    public static List<AllCityBean> getProvince(Context context) {
        String json = MyUtils.getJson(context, "province.json");
        return JSON.parseArray(json, AllCityBean.class);
    }


    /**
     * 根据省份获取市区
     */
    public static List<AllCityBean.CityBean> getCity(Context context, String province) {
        for (AllCityBean bean : getProvince(context)) {
            if (bean.getName().equals(province)) {
                return bean.getCity();
            }
        }
        return null;
    }

    /**
     * 获取县
     */
    public static List<String> getCounty(Context context, String province, String city) {
        List<AllCityBean.CityBean> cityBeans = getCity(context, province);
        for (AllCityBean.CityBean bean : cityBeans) {
            if (bean.getName().equals(city)) {
                return bean.getArea();
            }
        }
        return null;
    }

    /**
     * 获取三级联动数据（用于城市Picker）
     */
    public static CityPickerBean getLinkCity(Context context) {
        List<AllCityBean> province = getProvince(context);
        ArrayList<ArrayList<String>> optionsCity = new ArrayList<>();
        ArrayList<ArrayList<ArrayList<String>>> optionsCounty = new ArrayList<>();
        CityPickerBean cityPickerBean = new CityPickerBean();
        /**
         * 添加省份数据
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        for (int i = 0; i < province.size(); i++) {//遍历省份
            ArrayList<String> cityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）
            for (int c = 0; c < province.get(i).getCity().size(); c++) {//遍历该省份的所有城市
                String cityName = province.get(i).getCity().get(c).getName();
                cityList.add(cityName);//添加城市
                ArrayList<String> city_AreaList = new ArrayList<>();//该城市的所有地区列表
                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                /*
                if (jsonBean.get(i).getCityList().get(c).getArea() == null || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    city_AreaList.add("");
                } else {
                    city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }
                */
                city_AreaList.addAll(province.get(i).getCity().get(c).getArea());
                province_AreaList.add(city_AreaList);//添加该省所有地区数据
            }
            /**
             * 添加城市数据
             */
            optionsCity.add(cityList);
            /**
             * 添加地区数据
             */
            optionsCounty.add(province_AreaList);
        }
        cityPickerBean.setProvince(province);
        cityPickerBean.setCity(optionsCity);
        cityPickerBean.setCounty(optionsCounty);
        return cityPickerBean;
    }

    /**
     * 获取省份
     */
    public static List<CityBean> getProvince1(Context context) {
        String json = MyUtils.getJson(context, "province1.json");
        return JSON.parseArray(json, CityBean.class);
    }

    /**
     * 获取城市
     */
    public static List<CityBean> getCity(Context context) {
        String json = MyUtils.getJson(context, "city1.json");
        return JSON.parseArray(json, CityBean.class);
    }

    /**
     * 根据i获取城市
     */
    public static List<CityBean> getCityByI(Context context,String i) {
        ArrayList<CityBean> citys = new ArrayList<>();
        List<CityBean> city = getCity(context);
        for (CityBean bean:city){
            if(i.equals(bean.getP())){
                citys.add(bean);
            }
        }
        return citys;
    }

    /**
     * 根据省份名获取城市
     */
    public static List<CityBean> getCityByProvince(Context context,String province) {
        List<CityBean> provinceBean = getProvince1(context);
        for(CityBean proBean:provinceBean){
            if (proBean.getN().equals(province)){
                return getCityByI(context,proBean.getI());
            }
        }
        return null;
    }


}
