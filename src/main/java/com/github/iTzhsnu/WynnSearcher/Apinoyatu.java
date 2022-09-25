package com.github.iTzhsnu.WynnSearcher;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Apinoyatu {
    public static final String wynnCraftIngredientApi = "https://api.wynncraft.com/v2/ingredient/search/tier/";

    public void getAPI() {
        List<JsonObject> jso = new ArrayList<>();
        for(int i=0; i<=3; i++) {
            try {
                URL wynnCraftIngredientApi = new URL(Apinoyatu.wynnCraftIngredientApi + i);
                InputStream strm = wynnCraftIngredientApi.openStream();
                InputStreamReader in = new InputStreamReader(strm);
                BufferedReader inb = new BufferedReader(in);
                String line;
                StringBuilder sb = new StringBuilder();
                while ((line = inb.readLine()) != null) {
                    sb.append(line);
                }
                inb.close();
                in.close();
                strm.close();
                JsonElement je = new JsonParser().parse(sb.toString());
                JsonObject jo = je.getAsJsonObject();
                for (JsonElement jr : jo.get("data").getAsJsonArray()) {
                    jso.add(jr.getAsJsonObject());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
