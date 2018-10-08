package com.example.wsq.library.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/7 0007.
 */

public class CityInfoBean {

    public static final String TABLE_NAME           = "tp_region";

    public static final String ID = "id";

    public static final String AREA_CODE = "areaCode";

    public static final String NAME = "name";

    public static final String LEVEL = "level";

    public static final String CITY_CODE = "cityCode";

    public static final String CENTER = "center";

    public static final String PARENT_ID = "parent_id";


    public  int id;

    public String area_code;

    public String name;

    public int level;

    public String city_code;

    public String center;

    public int parent_id;

    private List<?> list;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArea_code() {
        return area_code;
    }

    public void setArea_code(String area_code) {
        this.area_code = area_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public CityInfoBean() {
    }

    @Override
    public String toString() {
        return "[name="+name+"]";
    }
}
