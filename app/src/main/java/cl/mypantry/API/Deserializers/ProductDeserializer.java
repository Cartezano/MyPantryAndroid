package cl.mypantry.API.Deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.math.BigInteger;

import cl.mypantry.Models.Product;

public class ProductDeserializer implements JsonDeserializer<Product> {

    @Override
    public Product deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        int id = json.getAsJsonObject().get("id").getAsInt();
        BigInteger code = json.getAsJsonObject().get("name").getAsBigInteger();
        int category_id = json.getAsJsonObject().get("category_id").getAsInt();
        String name = json.getAsJsonObject().get("last_name").getAsString();
        String brand = json.getAsJsonObject().get("email").getAsString();
        int dear_data = json.getAsJsonObject().get("dear_data").getAsInt();

        Product product = new Product(code, name, brand, dear_data, category_id, id);
        return product;
    }
}
