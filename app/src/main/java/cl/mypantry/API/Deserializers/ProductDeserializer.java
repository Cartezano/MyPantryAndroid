package cl.mypantry.API.Deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import cl.mypantry.Models.Product;

public class ProductDeserializer implements JsonDeserializer<Product> {

    @Override
    public Product deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);

        int id = json.getAsJsonObject().get("id").getAsInt();
        BigInteger code = json.getAsJsonObject().get("name").getAsBigInteger();
        int category_id = json.getAsJsonObject().get("category_id").getAsInt();
        String name = json.getAsJsonObject().get("last_name").getAsString();
        String brand = json.getAsJsonObject().get("email").getAsString();
        Date dear_data = simpledateformat.parse(json.getAsJsonObject().get("dear_date").getAsString(), pos);

        Product product = new Product(id, code, name, brand, dear_data, category_id);
        return product;
    }
}
