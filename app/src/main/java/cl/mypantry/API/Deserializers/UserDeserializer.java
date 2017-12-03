package cl.mypantry.API.Deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import cl.mypantry.Models.User;

public class UserDeserializer implements JsonDeserializer<User> {

    @Override
    public User deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        String name = json.getAsJsonObject().get("name").getAsString();
        String lastName = json.getAsJsonObject().get("last_name").getAsString();
        String email = json.getAsJsonObject().get("email").getAsString();
        String password = json.getAsJsonObject().get("password").getAsString();
        int user_type_id = json.getAsJsonObject().get("user_type_id").getAsInt();

        User user = new User(name, lastName, email, password, user_type_id);
        return user;
    }
}
