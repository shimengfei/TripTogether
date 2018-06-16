package com.hebut.triptogether.Model;

/**
 * Created by Zhang_Rui on 2018/6/11.
 */
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by LICHENGLONG on 2017-10-12.
 */

public class JD {

    private Integer id;//景点ID
    private String headPortrait;//头像
    private String jdName;//名字
    private String price;//价格
    private String intro;//简介



    public JD(
            Integer id,
            String headPortrait,
            String jdName,
            String price,
            String intro) {
        this.id = id;
        this.headPortrait = headPortrait;
        this.jdName = jdName;
        this.intro = intro;
        this.price = price;
    }

    public static JD sectionInfoData(JSONObject json){
        try {
            return new JD(
                    json.getInt("id"),
                    json.getString("headPortrait"),
                    json.getString("jdName"),
                    json.getString("price"),
                    json.getString("intro"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer getId() {
        return id;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public String getJdName() {
        return jdName;
    }

    public String getPrice() {
        return price;
    }

    public String getIntro() {
        return intro;
    }
}