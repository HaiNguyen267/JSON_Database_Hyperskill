package client;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class Request {
    @Parameter(
            names = "-t",
            description = "Type of the request"
    )
    private String type;

    @Parameter(
            names = "-k",
            description = "The key of the object"
    )
    private String key;
    @Parameter(
            names = "-v",
            description = "The value of the object"
    )
    private String value;

    @Parameter(
            names = "-in",
            description = "The name of the file"
    )
    private String fileName;

    public String toJsonString() {
        JsonObject jo = new JsonObject();

        jo.addProperty("type", this.type);

        if (!this.type.equals("exit")) {
            jo.addProperty("key", this.key);

            if (this.type.equals("set")) {
                jo.addProperty("value", this.value);
            }
        }
        return jo.toString();
    }

    public String getType() {
        return type;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public String getFileName() {
        return fileName;
    }
}

