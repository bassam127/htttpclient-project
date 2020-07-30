package util;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import testhttpclient.request.Requestmessage;

public class AllAsStringSerializer implements JsonSerializer<Requestmessage> {

	public JsonElement serialize(Requestmessage src, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject jsonObject = new JsonObject();

		addPropertyIfSet(jsonObject, "Name", src.Name);
		addPropertyIfSet(jsonObject, "Notes", src.Notes);

		return jsonObject;
	}

	private void addPropertyIfSet(JsonObject jsonObject, String property, String value) {
		if (value != null && !"".equals(value)) {
			jsonObject.addProperty(property, value);
		}
	}

}
