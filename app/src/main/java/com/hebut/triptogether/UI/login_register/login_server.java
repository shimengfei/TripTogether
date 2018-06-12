package com.hebut.triptogether.UI.login_register;
import android.util.Log;

import com.hebut.triptogether.Jsonutil.jsontool;
import com.hebut.triptogether.Jsonutil.jsonutil;
import com.hebut.triptogether.Model.User;
import com.hebut.triptogether.Model.person;
import net.sf.json.JSONObject;
public class login_server {
    static String path = "http://192.168.130.2:8080/TripTogetherServer/";

    public static boolean login(final String username, final String password,final String emailAddress) {
        new Thread() {
            @Override
            public void run() {
                try {
                    JSONObject user=jsontool.setUser(new User(username,password,emailAddress));
                    String url = path + "/login";
                    String jsonstring = jsonutil.getjsoncontent(url);

                    Log.i("success", "成功注册");
                } catch (Exception e) {

                }
            }
        }.start();
        return true;
    }

}
