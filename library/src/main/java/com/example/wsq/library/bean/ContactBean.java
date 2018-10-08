package com.example.wsq.library.bean;

import android.util.Log;

import com.example.wsq.library.utils.PinyinUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ContactBean implements Serializable {


    private String id;
    private String name;

    /**
     * 姓
     */
    private String surname;

    private String duty;  //职务

    private String companyName;  //公司名称

    private String avatar;  //头像

    private String logo; //公司logo

    private String original_photo; //名片

    private String mobile;

    private String email;

    private String companyAddress;

    private int type;  //'类型，0普通，1 自己， 2 特别关注',

    private String pinyin;  //全拼

    private String spell;  //简拼

    private String letters;//显示拼音的首字母;

    private String match_start;  //匹配字符串之前的

    private String match_center;  //相匹配的字符串

    private String match_end;  //匹配字符串之后的

    private boolean isSearch; //是否搜索

    private boolean isFocus; //是否关注  默认是不关注的
    private boolean isShowChildTitle; //是否显示item里面的title
    private String childTitle; //item里面的title
    private int drawableId; //item里面title的图片

    private int plate_type;  //数值对应 CardType， 名片类型
    private String custom_detail_url; //自定义名片路径
    private String qrcode_url; //  分享二维码图片
    private String website; //公司官网
    private String phone2; //
    private int vip_level; //名片vip等级
    private int privilege; //是否vip  0 不是  1是
    private int card_template_id; //名片背景的id
    private String template_background_top; //背景图片
    private String template_background_foot; //背景图片
    private String longitude = "";  //纬度
    private String latitude = "";   //经度
    private String firm_add_area = "";

    public void setLon_lat(String lon_lat) {
        this.lon_lat = lon_lat;
    }

    public String getLon_lat() {
        return lon_lat;
    }

    private String lon_lat;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getCustom_detail_url() {
        return custom_detail_url;
    }

    public void setCustom_detail_url(String custom_detail_url) {
        this.custom_detail_url = custom_detail_url;
    }

    public String getfirm_add_area() {
        return firm_add_area;
    }

    public void setfirm_add_area(String firm_add_area) {
        this.firm_add_area = firm_add_area;
    }

    public int getCard_template_id() {
        return card_template_id;
    }

    public void setCard_template_id(int card_template_id) {
        this.card_template_id = card_template_id;
    }

    public String getTemplate_background_top() {
        return template_background_top;
    }

    public void setTemplate_background_top(String template_background_top) {
        this.template_background_top = template_background_top;
    }

    public String getTemplate_background_foot() {
        return template_background_foot;
    }

    public void setTemplate_background_foot(String template_background_foot) {
        this.template_background_foot = template_background_foot;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    private List<Map<String, Object>> name_url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getLetters() {
        return letters;
    }

    public void setLetters(String letters) {
        this.letters = letters;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }

    public String getMatch_start() {
        return match_start;
    }

    public void setMatch_start(String match_start) {
        this.match_start = match_start;
    }

    public String getMatch_center() {
        return match_center;
    }

    public void setMatch_center(String match_center) {
        this.match_center = match_center;
    }

    public String getMatch_end() {
        return match_end;
    }

    public void setMatch_end(String match_end) {
        this.match_end = match_end;
    }

    public boolean isSearch() {
        return isSearch;
    }

    public void setSearch(boolean search) {
        isSearch = search;
    }

    public boolean isFocus() {
        return isFocus;
    }

    public void setFocus(boolean focus) {
        isFocus = focus;
    }

    public boolean isShowChildTitle() {
        return isShowChildTitle;
    }

    public void setShowChildTitle(boolean showChildTitle) {
        isShowChildTitle = showChildTitle;
    }

    public String getChildTitle() {
        return childTitle;
    }

    public void setChildTitle(String childTitle) {
        this.childTitle = childTitle;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public List<Map<String, Object>> getName_url() {
        return name_url;
    }

    public void setName_url(List<Map<String, Object>> name_url) {
        this.name_url = name_url;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getOriginal_photo() {
        return original_photo;
    }

    public void setOriginal_photo(String original_photo) {
        this.original_photo = original_photo;
    }

    public int getPlate_type() {
        return plate_type;
    }

    public void setPlate_type(int plate_type) {
        this.plate_type = plate_type;
    }

    public String getQrcode_url() {
        return qrcode_url;
    }

    public void setQrcode_url(String qrcode_url) {
        this.qrcode_url = qrcode_url;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getVip_level() {
        return vip_level;
    }

    public void setVip_level(int vip_level) {
        this.vip_level = vip_level;
    }

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }

    public ContactBean(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public ContactBean() {
    }

    @Override
    public String toString() {
        return "{name=" + name + "," +
                "id = " + id +
                "surname = " + surname + "," +
                "duty = " + duty + "," +
                " companyName= " + companyName + "," +
                "mobile = " + mobile + "," +
                "email = " + email + "," +
                "companyAddress = " + companyAddress + "," +
                "pinyin = " + pinyin + "," +
                "spell = " + spell + "," +
                "letters = " + letters + "," +
                "isSearch = " + isSearch + "," +
                "isShowChildTitle = " + isShowChildTitle + "," +
                "childTitle = " + childTitle + "," +
                "plate_type = " + plate_type + "," +
                "isFocus = " + isFocus +
                "vip_level = " + vip_level +
                "firm_add_area=" + firm_add_area +
                "privilege = " + privilege +
                "}";
    }

    public String toJSON() {
        JSONObject json = new JSONObject();
        try {
            json.put("name", name);
            json.put("surname", surname);
            json.put("duty", duty);
            json.put("companyName", companyName);
            json.put("mobile", mobile);
            json.put("email", email);
            json.put("companyAddress", companyAddress);
            json.put("pinyin", pinyin);
            json.put("spell", spell);
            json.put("letters", letters);
            json.put("match_start", match_start);
            json.put("match_center", match_center);
            json.put("match_end", match_end);
            json.put("isSearch", isSearch);
            json.put("isFocus", isFocus);
            json.put("isShowChildTitle", isShowChildTitle);
            json.put("childTitle", childTitle);
            json.put("drawableId", drawableId);
            json.put("plate_type", plate_type);
            json.put("firm_add_area", firm_add_area);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json.toString();
    }

    /**
     * 将map对象转换成联系人
     *
     * @param data
     * @return
     * @throws Exception
     */
    public ContactBean onContact(Map<String, Object> data) {

        this.name = data.get("name") + "";

        this.id = data.get("id") + "";
        String surname = name.substring(0, 1);
        this.surname = surname;
        String pinyin = PinyinUtils.getPingYin(name);
        this.pinyin = pinyin;
        String arrays[] = pinyin.split(",");
        StringBuffer spell = new StringBuffer();
        for (int j = 0; j < arrays.length; j++) {
            spell.append(arrays[j].substring(0, 1));
        }
        this.spell = spell.toString();

        if (data.containsKey("type")) {
            this.type = (int) data.get("type");
        }
        this.duty = data.get("position") + "";

        if (data.containsKey("phone")) {
            this.mobile = data.get("phone") + "";
        } else {
            this.mobile = data.get("phone1") + "";
        }
        if (data.containsKey("firm_name")) {
            this.companyName = data.get("firm_name") + "";
        } else {
            this.companyName = data.get("company") + "";
        }
        this.email = data.get("email") + "";
        if (data.containsKey("address")) {
            this.companyAddress = data.get("address") + "";
        } else {
            this.companyAddress = data.get("firm_add") + "";
        }
        this.firm_add_area = data.get("firm_add_area") + "";

        this.lon_lat = (String) data.get("lon_lat");
        if (lon_lat != null && !"".equals(lon_lat)) {
            String[] latArr = lon_lat.split("#");
            if (latArr.length == 2) {
                Log.e("第一个" + latArr[0], "第二个" + latArr[1]);
                this.longitude = latArr[0];
                this.latitude = latArr[1];
            }
        }

        this.logo = data.get("logo") + "";
        this.avatar = data.get("avatar") + "";
        this.website = data.get("website") + "";
        this.original_photo = data.get("original_photo") + "";
        if (data.containsKey("vip_level")) {
            this.vip_level = (int) data.get("vip_level");
        }
        this.original_photo = data.get("original_photo") + "";
        this.qrcode_url = data.get("qrcode_url") + "";

        List<Map<String, Object>> list = (List<Map<String, Object>>) data.get("name_url");
        this.name_url = list;
        String latter = pinyin.substring(0, 1);
        if (type == 2) {
            this.isFocus = true;
            this.letters = "#";
        } else {
            this.letters = latter;
        }

        return this;
    }

}
