package com.github.iTzhsnu.WynnSearcher;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

public class GetAPI {
    public static final String WYNN_ITEM_API = "https://api.wynncraft.com/public_api.php?action=itemDB&category=all";
    public static final String WYNN_INGREDIENT_API = "https://api.wynncraft.com/v2/ingredient/search/tier/";
    public static final String WYNN_RECIPE_API = "https://api.wynncraft.com/v2/recipe/search/skill/";

    public GetAPI() {}

    public void setItemData(List<JsonObject> list, JLabel label) {
        boolean connect = true;
        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(new URL(WYNN_ITEM_API).openStream()));
            String line;
            StringBuilder builder = new StringBuilder();

            while ((line = buffer.readLine()) != null) {
                builder.append(line);
            }

            JsonObject object = JsonParser.parseString(builder.toString()).getAsJsonObject();
            if (object.get("items") != null) {
                for (JsonElement je : object.get("items").getAsJsonArray()) {
                    list.add(je.getAsJsonObject());
                }
            } else {
                connect = false;
            }

            buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
            connect = false;
        }

        if (connect) {
            label.setText("Item API Connected");
            label.setForeground(new Color(0, 169, 104));
        } else {
            label.setText("Using Archive");
            label.setForeground(new Color(255, 0, 0));
            JsonObject j = JsonParser.parseReader(new JsonReader(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/archive_jsons/items.json")), StandardCharsets.UTF_8))).getAsJsonObject();
            for (JsonElement je : j.get("items").getAsJsonArray()) {
                list.add(je.getAsJsonObject());
            }
            if (j.get("request") != null) {
                label.setText(new Timestamp(j.get("request").getAsJsonObject().get("timestamp").getAsLong() * 1000).toLocalDateTime().toLocalDate() + " Archive");
            }
        }
    }

    public void setIngredientData(List<JsonObject> list, JLabel label) {
        boolean connect = true;
        for (int i = 0; 3 >= i; ++i) {
            try {
                BufferedReader buffer = new BufferedReader(new InputStreamReader(new URL(WYNN_INGREDIENT_API + i).openStream()));
                String line;
                StringBuilder builder = new StringBuilder();

                while ((line = buffer.readLine()) != null) {
                    builder.append(line);
                }

                JsonObject object = JsonParser.parseString(builder.toString()).getAsJsonObject();
                if (object.get("data") != null) {
                    for (JsonElement je : object.get("data").getAsJsonArray()) {
                        list.add(je.getAsJsonObject());
                    }
                } else {
                    connect = false;
                }

                buffer.close();
            } catch (IOException e) {
                e.printStackTrace();
                connect = false;
            }
        }

        if (connect) {
            label.setText("Ingredient API Connected");
            label.setForeground(new Color(0, 169, 104));
        } else {
            label.setText("Using Archive");
            label.setForeground(new Color(255, 0, 0));
            for (int i = 0; 3 >= i; ++i) {
                JsonObject j = JsonParser.parseReader(new JsonReader(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/archive_jsons/ings_" + i + ".json")), StandardCharsets.UTF_8))).getAsJsonObject();
                for (JsonElement je : j.get("data").getAsJsonArray()) {
                    list.add(je.getAsJsonObject());
                }
                if (i == 0 && j.get("request") != null) {
                    label.setText(new Timestamp(j.get("request").getAsJsonObject().get("timestamp").getAsLong() * 1000).toLocalDateTime().toLocalDate() + " Archive");
                }
            }
        }
    }

    public String setRecipeData(List<JsonObject> list) {
        boolean connect = true;
        String[] ss = new String[] {"armouring", "tailoring", "weaponsmithing", "woodworking", "jeweling", "scribing", "cooking", "alchemism"};

        for (String s : ss) {
            try {
                BufferedReader buffer = new BufferedReader(new InputStreamReader(new URL(WYNN_RECIPE_API + s).openStream()));
                String line;
                StringBuilder builder = new StringBuilder();

                while ((line = buffer.readLine()) != null) {
                    builder.append(line);
                }

                JsonObject object = JsonParser.parseString(builder.toString()).getAsJsonObject();
                if (object.get("data") != null) {
                    for (JsonElement je : object.get("data").getAsJsonArray()) {
                        list.add(je.getAsJsonObject());
                    }
                } else {
                    connect = false;
                }

                buffer.close();
            } catch (IOException e) {
                e.printStackTrace();
                connect = false;
            }
        }

        if (connect) {
            return "Recipe API Connected";
        } else {
            for (String s : ss) {
                JsonObject j = JsonParser.parseReader(new JsonReader(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/archive_jsons/recipes_" + s + ".json")), StandardCharsets.UTF_8))).getAsJsonObject();
                for (JsonElement je : j.get("data").getAsJsonArray()) {
                    list.add(je.getAsJsonObject());
                }
                if (s.equals("armouring") && j.get("request") != null) {
                    return new Timestamp(j.get("request").getAsJsonObject().get("timestamp").getAsLong() * 1000).toLocalDateTime().toLocalDate() + " Archive";
                }
            }
            return "Using Archive";
        }
    }

    public static boolean getUpdate() {
        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(new URL("https://raw.githubusercontent.com/iTzhsnu/WynnSearcher/master/version.txt").openStream()));
            String line;
            StringBuilder builder = new StringBuilder();

            while ((line = buffer.readLine()) != null) {
                builder.append(line);
            }
            buffer.close();
            return !builder.toString().equals(SearchUI.VERSION);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
