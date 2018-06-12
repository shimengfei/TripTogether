package com.hebut.triptogether.Jsonutil;

import android.util.Log;

import com.hebut.triptogether.Model.*;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import java.lang.Throwable;
public class jsontool {

	public jsontool() {
		// TODO Auto-generated constructor stub
	}
	public static User getUser( String value)
	{
		User user=new User();
		try {
			if(value!=null&&!"".equals(value)) {
				//TODO
				JSONObject userjson = net.sf.json.JSONObject.fromObject(value);
				user.setUserName(userjson.getString("username"));
				user.setPassword(userjson.getString("password"));

			}else{
				Log.v("null","json对象为空值");
			}

		} catch (Exception q) {
			// TODO Auto-generated catch block
			q.printStackTrace();
		}
		return user;

	}
	public static JSONObject setUser(User user){
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("username",user.getUserName());
		jsonObject.put("password",user.getPassword());
		jsonObject.put("email",user.getEmailAddress());
		return jsonObject;
	}
}
