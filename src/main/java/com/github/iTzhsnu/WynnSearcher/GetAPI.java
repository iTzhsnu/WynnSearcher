package com.github.iTzhsnu.WynnSearcher;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import org.scijava.util.FileUtils;
import org.scijava.util.Types;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.List;
import java.util.*;

public class GetAPI {
    public static final String WYNN_RECIPE_API = "https://api.wynncraft.com/v2/recipe/search/skill/";
    public static final String WYNN_ITEM_V3_API = "https://web-api.wynncraft.com/api/v3/item/search";

    public GetAPI() {}

    public File getFilePath(String path) {
        String s = FileUtils.urlToFile(Types.location(GetAPI.class)).getParentFile().getPath().replaceAll("\\\\", "/") + path;
        try {
            return FileUtils.urlToFile(s);
        } catch (IllegalArgumentException e) {
         return new File(s);
        }
    }

    public void getWynnAPIV3(List<JsonObject> equipAndWeapon, List<JsonObject> ingredients, List<JsonObject> otherItems, JLabel label) {
        boolean connect = true;
        JsonObject saveEquipAndWeaponJ = JsonParser.parseString("{\"items\":[]}").getAsJsonObject();
        JsonObject saveIngredientJ = JsonParser.parseString("{\"items\":[]}").getAsJsonObject();
        JsonObject saveOtherItemsJ = JsonParser.parseString("{\"items\":[]}").getAsJsonObject();
        try {
            //Load Items (1m20s Slow)
            for (int i = 0; 8 > i; ++i) {
                String s = "\"weapons\"";
                switch (i) {
                    case 1:
                        s = "\"armour\"";
                        break;
                    case 2:
                        s = "\"accessories\"";
                        break;
                    case 3:
                        s = "\"tomes\"";
                        break;
                    case 4:
                        s = "\"charms\"";
                        break;
                    case 5:
                        s = "\"tools\"";
                        break;
                    case 6:
                        s = "\"ingredients\"";
                        break;
                    case 7:
                        s = "\"materials\"";
                        break;
                }

                String post = "{\"query\":null,\"type\":[" + s + "],\"tier\":[],\"attackSpeed\":[],\"levelRange\":[0,110],\"professions\":[],\"identifications\":[]}";
                int endPos = 1;
                for (int n = 1; endPos >= n; ++n) {
                    URLConnection urlConn = new URL(WYNN_ITEM_V3_API + "?page=" + n).openConnection();
                    urlConn.setDoOutput(true);
                    urlConn.setRequestProperty("Content-Type", "application/json");

                    DataOutputStream dos = new DataOutputStream(urlConn.getOutputStream());
                    dos.writeBytes(post);

                    BufferedReader buffer = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), StandardCharsets.UTF_8));
                    String line;
                    StringBuilder builder = new StringBuilder();
                    while ((line = buffer.readLine()) != null) {
                        builder.append(line);
                    }

                    JsonObject json = JsonParser.parseString(builder.toString()).getAsJsonObject();
                    if (json.get("results") != null) {
                        if (n == 1) endPos = json.get("controller").getAsJsonObject().get("pages").getAsInt();
                        Set<Map.Entry<String, JsonElement>> entries = json.get("results").getAsJsonObject().entrySet();
                        for (Map.Entry<String, JsonElement> entry : entries) {
                            JsonObject j = json.get("results").getAsJsonObject().get(entry.getKey()).getAsJsonObject();
                            j.addProperty("name", entry.getKey());
                            switch (i) {
                                case 0: //Weapons
                                case 1: //Armours
                                case 2: //Accessories
                                    equipAndWeapon.add(j);
                                    saveEquipAndWeaponJ.get("items").getAsJsonArray().add(j);
                                    break;
                                case 6: //Ingredients
                                    ingredients.add(j);
                                    saveIngredientJ.get("items").getAsJsonArray().add(j);
                                    break;
                                case 3: //Tomes
                                    j.addProperty("type", "tome");
                                    otherItems.add(j);
                                    saveOtherItemsJ.get("items").getAsJsonArray().add(j);
                                    break;
                                case 4: //Charms
                                    j.addProperty("type", "charm");
                                    otherItems.add(j);
                                    saveOtherItemsJ.get("items").getAsJsonArray().add(j);
                                    break;
                                case 5: //Tools
                                    j.addProperty("type", "tool");
                                    otherItems.add(j);
                                    saveOtherItemsJ.get("items").getAsJsonArray().add(j);
                                    break;
                                case 7: //Materials
                                    j.addProperty("type", "material");
                                    otherItems.add(j);
                                    saveOtherItemsJ.get("items").getAsJsonArray().add(j);
                                    break;
                            }
                            System.out.println(entry.getKey());
                        }
                    } else {
                        connect = false;
                    }

                    buffer.close();
                }
            }

            //Write File
            if (connect) {
                FileWriter eawW = new FileWriter(getFilePath("/items_data/equip_and_weapons.json"));
                FileWriter ingW = new FileWriter(getFilePath("/items_data/ingredients.json"));
                FileWriter oItemsW = new FileWriter(getFilePath("/items_data/other_items.json"));
                eawW.write(saveEquipAndWeaponJ.toString());
                ingW.write(saveIngredientJ.toString());
                oItemsW.write(saveOtherItemsJ.toString());
                eawW.close();
                ingW.close();
                oItemsW.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (connect) {
            label.setText("Item API Latest");
            label.setForeground(new Color(0, 169, 104));
        } else {
            label.setText("Update Failed");
            label.setForeground(new Color(255, 255, 0));
        }
    }

    public void loadArchiveV3API(List<JsonObject> equipAndWeapon, List<JsonObject> ingredients, List<JsonObject> otherItems, JLabel label) {
        try {
            JsonObject eawJ = JsonParser.parseReader(new JsonReader(new InputStreamReader(new FileInputStream(getFilePath("/items_data/equip_and_weapons.json")), StandardCharsets.UTF_8))).getAsJsonObject();
            JsonObject ingJ = JsonParser.parseReader(new JsonReader(new InputStreamReader(new FileInputStream(getFilePath("/items_data/ingredients.json")), StandardCharsets.UTF_8))).getAsJsonObject();
            JsonObject oItemsJ = JsonParser.parseReader(new JsonReader(new InputStreamReader(new FileInputStream(getFilePath("/items_data/other_items.json")), StandardCharsets.UTF_8))).getAsJsonObject();

            //Equip And Weapons
            for (JsonElement je : eawJ.get("items").getAsJsonArray()) {
                equipAndWeapon.add(je.getAsJsonObject());
            }

            //Ingredients
            for (JsonElement je : ingJ.get("items").getAsJsonArray()) {
                ingredients.add(je.getAsJsonObject());
            }

            //Other Items
            for (JsonElement je : oItemsJ.get("items").getAsJsonArray()) {
                otherItems.add(je.getAsJsonObject());
            }

            label.setText("Archive Loaded");
            label.setForeground(new Color(0, 169, 104));
        } catch (IOException e) {
            e.printStackTrace();
            label.setText("Load Failed");
            label.setForeground(new Color(255, 0, 0));
        }
    }

    public String setRecipeData(List<JsonObject> list) {
        boolean connect = true;
        String[] ss = new String[] {"armouring", "tailoring", "weaponsmithing", "woodworking", "jeweling", "scribing", "cooking", "alchemism"};

        for (String s : ss) {
            try {
                BufferedReader buffer = new BufferedReader(new InputStreamReader(new URL(WYNN_RECIPE_API + s).openStream(), StandardCharsets.UTF_8));
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
