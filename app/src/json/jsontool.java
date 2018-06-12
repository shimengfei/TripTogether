package json;

import org.json.JSONException;
import org.json.JSONObject;

import domain.person;

public class jsontool {

	public jsontool() {
		// TODO Auto-generated constructor stub
	}
public static person getperson(String key,String value)
{
	person person=new person();
	try {
		JSONObject object=new JSONObject(value);
		JSONObject personobjiect=object.getJSONObject("person");
		person.setId(personobjiect.getInt("id"));
		person.setName(personobjiect.getString("name"));
		person.setAdress(personobjiect.getString("adress"));
		
		
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return person;
	
}
}
