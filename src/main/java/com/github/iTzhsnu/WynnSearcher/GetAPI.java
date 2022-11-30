package com.github.iTzhsnu.WynnSearcher;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
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
            try {
                JsonObject j = JsonParser.parseReader(new FileReader(new File(Objects.requireNonNull(getClass().getResource("/items.json")).toURI()))).getAsJsonObject();
                for (JsonElement je : j.get("items").getAsJsonArray()) {
                    list.add(je.getAsJsonObject());
                }
                if (j.get("request") != null) {
                    label.setText(new Timestamp(j.get("request").getAsJsonObject().get("timestamp").getAsLong() * 1000).toLocalDateTime().toLocalDate() + " Archive");
                }
            } catch (FileNotFoundException | URISyntaxException e) {
                e.printStackTrace();
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
            try {
                for (int i = 0; 3 >= i; ++i) {
                    JsonObject j = JsonParser.parseReader(new FileReader(new File(Objects.requireNonNull(getClass().getResource("/ings_" + i + ".json")).toURI()))).getAsJsonObject();
                    for (JsonElement je : j.get("data").getAsJsonArray()) {
                        list.add(je.getAsJsonObject());
                    }
                    if (i == 0 && j.get("request") != null) {
                        label.setText(new Timestamp(j.get("request").getAsJsonObject().get("timestamp").getAsLong() * 1000).toLocalDateTime().toLocalDate() + " Archive");
                    }
                }
            } catch (FileNotFoundException | URISyntaxException e) {
                e.printStackTrace();
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
            try {
                for (String s : ss) {
                    JsonObject j = JsonParser.parseReader(new FileReader(new File(Objects.requireNonNull(getClass().getResource("/recipes_" + s + ".json")).toURI()))).getAsJsonObject();
                    for (JsonElement je : j.get("data").getAsJsonArray()) {
                        list.add(je.getAsJsonObject());
                    }
                    if (j.get("request") != null) {
                        return new Timestamp(j.get("request").getAsJsonObject().get("timestamp").getAsLong() * 1000).toLocalDateTime().toLocalDate() + " Archive";
                    }
                }
            } catch (FileNotFoundException | URISyntaxException e) {
                e.printStackTrace();
            }
            return "Using Archive";
        }
    }
}
