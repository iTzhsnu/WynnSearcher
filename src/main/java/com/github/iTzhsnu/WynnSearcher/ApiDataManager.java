package com.github.iTzhsnu.WynnSearcher;

import com.github.iTzhsnu.WynnSearcher.data.Aspect;
import com.github.iTzhsnu.WynnSearcher.data.Ingredient;
import com.github.iTzhsnu.WynnSearcher.data.Item;
import com.github.iTzhsnu.WynnSearcher.general.ErrorType;
import com.github.iTzhsnu.WynnSearcher.general.JsonKeys;
import com.github.iTzhsnu.WynnSearcher.general.JsonValues;
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

public class ApiDataManager {
    public static final String WYNN_RECIPE_API = "https://api.wynncraft.com/v2/recipe/search/skill/";
    public static final String WYNN_ITEM_V3_API = "https://api.wynncraft.com/v3/item/search";
    public static final String WYNN_ABILITY_TREE_API = "https://api.wynncraft.com/v3/ability/"; // map/[class], tree/[class]
    public static final String WYNN_ASPECT_API = "https://api.wynncraft.com/v3/aspects/"; //[class]
    public static final String WYNN_SETS_API = "https://api.wynncraft.com/v3/item/sets";

    private static ApiDataManager apiDataManager = null;

    //API
    public final List<Item> wynnItems = new ArrayList<>();
    public final List<Ingredient> wynnIngredients = new ArrayList<>();
    public final List<Item> wynnOtherItems = new ArrayList<>();
    public ErrorType itemApiConnect;

    public final List<JsonObject> wynnRecipes = new ArrayList<>();
    public final ErrorType recipeApiConnect;

    //Item Type Json
    public final List<Item> weaponData = new ArrayList<>();
    public final List<Item> bowData = new ArrayList<>();
    public final List<Item> spearData = new ArrayList<>();
    public final List<Item> wandData = new ArrayList<>();
    public final List<Item> daggerData = new ArrayList<>();
    public final List<Item> relikData = new ArrayList<>();
    public final List<Item> helmetData = new ArrayList<>();
    public final List<Item> chestplateData = new ArrayList<>();
    public final List<Item> leggingsData = new ArrayList<>();
    public final List<Item> bootsData = new ArrayList<>();
    public final List<Item> ringData = new ArrayList<>();
    public final List<Item> braceletData = new ArrayList<>();
    public final List<Item> necklaceData = new ArrayList<>();
    public JsonObject setsJson = null;

    //Ingredient Type Json
    public final List<Ingredient> armouringData = new ArrayList<>();
    public final List<Ingredient> tailoringData = new ArrayList<>();
    public final List<Ingredient> weaponsmithingData = new ArrayList<>();
    public final List<Ingredient> woodworkingData = new ArrayList<>();
    public final List<Ingredient> jewelingData = new ArrayList<>();
    public final List<Ingredient> scribingData = new ArrayList<>();
    public final List<Ingredient> cookingData = new ArrayList<>();
    public final List<Ingredient> alchemismData = new ArrayList<>();

    //Other Type Json
    public final List<Item> tomeData = new ArrayList<>();
    public final List<Item> charmData = new ArrayList<>();
    public final List<Item> toolData = new ArrayList<>();
    public final List<Item> materialData = new ArrayList<>();
    public final List<Item> armourTomeData = new ArrayList<>();
    public final List<Item> guildTomeData = new ArrayList<>();
    public final List<Item> weaponTomeData = new ArrayList<>();
    public final List<Item> marathonTomeData = new ArrayList<>();
    public final List<Item> lootrunTomeData = new ArrayList<>();
    public final List<Item> expertiseTomeData = new ArrayList<>();
    public final List<Item> mysticismTomeData = new ArrayList<>();

    //Aspect Json
    public final List<Aspect> aspects = new ArrayList<>();
    public final List<Aspect> warriorAspectData = new ArrayList<>();
    public final List<Aspect> assassinAspectData = new ArrayList<>();
    public final List<Aspect> mageAspectData = new ArrayList<>();
    public final List<Aspect> archerAspectData = new ArrayList<>();
    public final List<Aspect> shamanAspectData = new ArrayList<>();

    //How to Obtain
    public final JsonObject howToObtainItem;
    public final JsonObject howToObtainIng;
    public final JsonObject howToObtainOthers;

    // Powder
    public final JsonObject powderData;



    private ApiDataManager() {
        itemApiConnect = loadArchiveV3API(wynnItems, wynnIngredients, wynnOtherItems);
        recipeApiConnect = loadRecipeData(wynnRecipes);
        setsJson = loadWynnSetsAPI();

        loadWynnAspectAPI(JsonValues.WARRIOR, warriorAspectData);
        loadWynnAspectAPI(JsonValues.ASSASSIN, assassinAspectData);
        loadWynnAspectAPI(JsonValues.MAGE, mageAspectData);
        loadWynnAspectAPI(JsonValues.ARCHER, archerAspectData);
        loadWynnAspectAPI(JsonValues.SHAMAN, shamanAspectData);
        aspects.addAll(warriorAspectData);
        aspects.addAll(assassinAspectData);
        aspects.addAll(mageAspectData);
        aspects.addAll(archerAspectData);
        aspects.addAll(shamanAspectData);

        howToObtainItem = getHowToObtainItem();
        howToObtainIng = getHowToObtainIng();
        howToObtainOthers = getHowToObtainOthers();

        powderData = JsonParser.parseReader(new JsonReader(new InputStreamReader(getClass().getResourceAsStream("/other/powders.json"), StandardCharsets.UTF_8))).getAsJsonObject();

        setItemData();
        setIngredientData();
        setOtherItemsData();
    }

    public static void Init() {
        if (apiDataManager == null) apiDataManager = new ApiDataManager();
    }

    public static ApiDataManager getManager() {
        return apiDataManager;
    }

    public File getFilePath(String path) {
        String s = FileUtils.urlToFile(Types.location(ApiDataManager.class)).getParentFile().getPath().replaceAll("\\\\", "/") + path;
        try {
            return FileUtils.urlToFile(s);
        } catch (IllegalArgumentException e) {
            return new File(s);
        }
    }

    private void getWynnAPIV3(List<JsonObject> equipAndWeapon, List<JsonObject> ingredients, List<JsonObject> otherItems, JLabel label) {
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
                        s = "\"accessory\"";
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
                //if (i == 8) post = "{\"query\":\"Tome Of Lootrun Mastery\",\"type\":[],\"tier\":[],\"attackSpeed\":[],\"levelRange\":[0,110],\"professions\":[],\"identifications\":[]}"; //Lootrun Tomes
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
                        for (Map.Entry<String, JsonElement> entry : json.get("results").getAsJsonObject().entrySet()) {
                            JsonObject j = json.get("results").getAsJsonObject().get(entry.getKey()).getAsJsonObject();
                            j.addProperty("name", entry.getKey());
                            if (j.get(Identifications.RARITY.getItemName()) != null && j.get(Identifications.RARITY.getItemName()).getAsString().equals("common")) j.addProperty(Identifications.RARITY.getItemName(), "normal"); //Fixes Rarity Common => Normal
                            switch (i) {
                                case 0: //Weapons
                                case 1: //Armours
                                case 2: //Accessories
                                    equipAndWeapon.add(j);
                                    saveEquipAndWeaponJ.get("items").getAsJsonArray().add(j);
                                    break;
                                case 6: //Ingredients
                                    if (j.get("droppedBy") == null) j.add("droppedBy", JsonParser.parseString("{\"Ingredient Dummy\":null}"));
                                    ingredients.add(j);
                                    saveIngredientJ.get("items").getAsJsonArray().add(j);
                                    break;
                                case 3: //Tomes
                                //case 8: //Lootrun Tomes
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
                            System.out.println("[Load] " + entry.getKey());
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

            if (connect) {
                label.setText("Item API Latest");
                label.setForeground(new Color(0, 169, 104));
            } else {
                label.setText("Update Failed");
                label.setForeground(new Color(255, 255, 0));
            }
        } catch (IOException e) {
            e.printStackTrace();
            label.setText(e.getMessage());
            label.setForeground(new Color(255, 255, 0));
        }
    }

    private ErrorType getWynnAPIV3_3(List<Item> equipAndWeapon, List<Ingredient> ingredients, List<Item> otherItems) {
        JsonObject saveEquipAndWeaponJ = JsonParser.parseString("{\"items\":[]}").getAsJsonObject();
        JsonObject saveIngredientJ = JsonParser.parseString("{\"items\":[]}").getAsJsonObject();
        JsonObject saveOtherItemsJ = JsonParser.parseString("{\"items\":[]}").getAsJsonObject();

        // Get Token
        StringBuilder token = new StringBuilder();
        try {
            BufferedReader tokenBuffer = new BufferedReader(new FileReader(getFilePath("/items_data/token.txt")));
            String s = tokenBuffer.readLine();
            while (s != null) {
                token.append(s);
                s = tokenBuffer.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            for (int i = 0; 8 > i; ++i) {
                String s = "\"weapons\"";
                switch (i) {
                    case 1:
                        s = "\"armour\"";
                        break;
                    case 2:
                        s = "\"accessory\"";
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

                String post = "{\"query\":null,\"type\":[" + s + "],\"tier\":[],\"attackSpeed\":[],\"levelRange\":[0,120],\"professions\":[],\"identifications\":[]}";
                StringBuilder builder = new StringBuilder();

                URLConnection urlConn = new URL(WYNN_ITEM_V3_API + "?fullResult").openConnection();
                urlConn.setDoOutput(true);
                urlConn.setRequestProperty("Content-Type", "application/json");
                if (!token.isEmpty()) urlConn.setRequestProperty("Authorization", "Bearer " + token);

                DataOutputStream dos = new DataOutputStream(urlConn.getOutputStream());
                dos.writeBytes(post);

                BufferedReader buffer = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), StandardCharsets.UTF_8));
                String line;
                while ((line = buffer.readLine()) != null) {
                    builder.append(line);
                }

                JsonObject json = JsonParser.parseString(builder.toString()).getAsJsonObject();
                for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
                    JsonObject j = json.get(entry.getKey()).getAsJsonObject();
                    j.addProperty(JsonKeys.NAME.getKey(), entry.getKey());

                    if (j.get(Identifications.RARITY.getItemName()) != null && j.get(Identifications.RARITY.getItemName()).getAsString().equals("common")) j.addProperty(Identifications.RARITY.getItemName(), JsonValues.R_NORMAL); //Fixes Rarity Common => Normal
                    switch (i) {
                        case 0: //Weapons
                        case 1: //Armours
                        case 2: //Accessories
                            equipAndWeapon.add(new Item(j));
                            saveEquipAndWeaponJ.get("items").getAsJsonArray().add(j);
                            break;
                        case 6: //Ingredients
                            if (j.get(JsonKeys.DROPPED_BY.getKey()) == null) j.add(JsonKeys.DROPPED_BY.getKey(), JsonParser.parseString("[{\"name\":\"Ingredient Dummy\"}]"));
                            ingredients.add(new Ingredient(j));
                            saveIngredientJ.get("items").getAsJsonArray().add(j);
                            break;
                        case 3: //Tomes
                        case 4: //Charms
                        case 5: //Tools
                        case 7: //Materials
                            //case 8: //Lootrun Tomes
                            //j.addProperty("type", "tome");
                            otherItems.add(new Item(j));
                            saveOtherItemsJ.get("items").getAsJsonArray().add(j);
                            break;
                        //case 4: //Charms
                            //j.addProperty("type", "charm");
                            //otherItems.add(j);
                            //saveOtherItemsJ.get("items").getAsJsonArray().add(j);
                            //break;
                        //case 5: //Tools
                            //j.addProperty("type", "tool");
                            //otherItems.add(j);
                            //saveOtherItemsJ.get("items").getAsJsonArray().add(j);
                            //break;
                        //case 7: //Materials
                            //j.addProperty("type", "material");
                            //otherItems.add(j);
                            //saveOtherItemsJ.get("items").getAsJsonArray().add(j);
                            //break;
                    }
                    System.out.println("[Load] " + entry.getKey());
                }

                //Write File
                FileWriter eawW = new FileWriter(getFilePath("/items_data/equip_and_weapons.json"));
                FileWriter ingW = new FileWriter(getFilePath("/items_data/ingredients.json"));
                FileWriter oItemsW = new FileWriter(getFilePath("/items_data/other_items.json"));
                eawW.write(saveEquipAndWeaponJ.toString());
                ingW.write(saveIngredientJ.toString());
                oItemsW.write(saveOtherItemsJ.toString());
                eawW.close();
                ingW.close();
                oItemsW.close();

                //label.setText("Item API Latest");
                //label.setForeground(new Color(0, 169, 104));
            }
            return ErrorType.LATEST;
        } catch (IOException e) {
            //label.setText(e.getMessage());
            //label.setForeground(new Color(255, 255, 0));
            System.out.println(e.getMessage());
            return ErrorType.FAILED;
        }
    }

    private ErrorType loadArchiveV3API(List<Item> equipAndWeapon, List<Ingredient> ingredients, List<Item> otherItems) {
        try {
            JsonObject eawJ = JsonParser.parseReader(new JsonReader(new InputStreamReader(new FileInputStream(getFilePath("/items_data/equip_and_weapons.json")), StandardCharsets.UTF_8))).getAsJsonObject();
            JsonObject ingJ = JsonParser.parseReader(new JsonReader(new InputStreamReader(new FileInputStream(getFilePath("/items_data/ingredients.json")), StandardCharsets.UTF_8))).getAsJsonObject();
            JsonObject oItemsJ = JsonParser.parseReader(new JsonReader(new InputStreamReader(new FileInputStream(getFilePath("/items_data/other_items.json")), StandardCharsets.UTF_8))).getAsJsonObject();

            //Equip And Weapons
            for (JsonElement je : eawJ.get("items").getAsJsonArray()) {
                equipAndWeapon.add(new Item(je.getAsJsonObject()));
            }

            //Ingredients
            for (JsonElement je : ingJ.get("items").getAsJsonArray()) {
                ingredients.add(new Ingredient(je.getAsJsonObject()));
            }

            //Other Items
            for (JsonElement je : oItemsJ.get("items").getAsJsonArray()) {
                otherItems.add(new Item(je.getAsJsonObject()));
            }

            return ErrorType.SUCCEED;
            //label.setText("Archive Loaded");
            //label.setForeground(new Color(0, 169, 104));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return ErrorType.FAILED;
            //label.setText("Load Failed");
            //label.setForeground(new Color(255, 0, 0));
        }
    }

    //BROKEN
    private String setRecipeData(List<JsonObject> list) {
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

    private ErrorType loadRecipeData(List<JsonObject> list) {
        String[] ss = new String[] {"armouring", "tailoring", "weaponsmithing", "woodworking", "jeweling", "scribing", "cooking", "alchemism"};

        for (String s : ss) {
            JsonObject j = JsonParser.parseReader(new JsonReader(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/archive_jsons/recipes_" + s + ".json")), StandardCharsets.UTF_8))).getAsJsonObject();
            for (JsonElement je : j.get("data").getAsJsonArray()) {
                list.add(je.getAsJsonObject());
            }
        }

        return ErrorType.SUCCEED;
    }

    //public JsonObject setWynnAbilityTreeAPI(String s, List<JsonObject> treeMap, JLabel connect)
    private void setWynnAbilityTreeAPI(String s) {
        String treePath = WYNN_ABILITY_TREE_API + "tree/" + s;
        String mapPath = WYNN_ABILITY_TREE_API + "map/" + s;

        try {
            //Tree
            BufferedReader treeBuffer = new BufferedReader(new InputStreamReader(new URL(treePath).openStream(), StandardCharsets.UTF_8));
            String treeLine;
            StringBuilder treeBuilder = new StringBuilder();

            while ((treeLine = treeBuffer.readLine()) != null) {
                treeBuilder.append(treeLine);
            }

            //Tree Map
            BufferedReader mapBuffer = new BufferedReader(new InputStreamReader(new URL(mapPath).openStream(), StandardCharsets.UTF_8));
            String mapLine;
            StringBuilder mapBuilder = new StringBuilder();

            while ((mapLine = mapBuffer.readLine()) != null) {
                mapBuilder.append(mapLine);
            }

            //JsonObject json = JsonParser.parseString(mapBuilder.toString()).getAsJsonObject();
            //for (int i = 1; 7 >= i; ++i) {
            //    if (json.get(String.valueOf(i)) != null) {
            //        for (JsonElement je : json.get(String.valueOf(i)).getAsJsonArray()) {
            //            if (je.getAsJsonObject().get("type") != null && je.getAsJsonObject().get("type").getAsString().equals("connector")) {
            //                treeMap.add(je.getAsJsonObject());
            //            }
            //        }
            //    }
            //}

            FileWriter treeFW = new FileWriter(getFilePath("/items_data/" + s + "_tree.json"));
            FileWriter mapFW = new FileWriter(getFilePath("/items_data/" + s + "_map.json"));
            treeFW.write(treeBuilder.toString());
            mapFW.write(mapBuilder.toString());
            treeFW.close();
            mapFW.close();

            System.out.println(s + " Ability Tree Loaded.");
            //return JsonParser.parseString(treeBuilder.toString()).getAsJsonObject();
        } catch (IOException e) {
            System.out.println(s + " Ability Tree Load Failed.");
            System.out.println(e.getMessage());
        }

        //return null;
    }

    public JsonObject loadWynnAbilityTreeAPI(String s, List<JsonObject> treeMap, JLabel connect) {
        connect.setText("Using Archive");
        connect.setForeground(new Color(255, 255, 0));

        try {
            JsonObject treeJ = JsonParser.parseReader(new JsonReader(new InputStreamReader(new FileInputStream(getFilePath("/items_data/" + s + "_tree.json")), StandardCharsets.UTF_8))).getAsJsonObject();
            JsonObject mapJ = JsonParser.parseReader(new JsonReader(new InputStreamReader(new FileInputStream(getFilePath("/items_data/" + s + "_map.json")), StandardCharsets.UTF_8))).getAsJsonObject();

            //Tree Map
            for (int i = 1; 7 >= i; ++i) {
                if (mapJ.get(String.valueOf(i)) != null) {
                    for (JsonElement je : mapJ.get(String.valueOf(i)).getAsJsonArray()) {
                        if (je.getAsJsonObject().get("type") != null && je.getAsJsonObject().get("type").getAsString().equals("connector")) {
                            treeMap.add(je.getAsJsonObject());
                        }
                    }
                }
            }

            return treeJ;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        connect.setText("Load Failed");
        connect.setForeground(new Color(255, 0, 0));
        return null;
    }

    private void getWynnAspectAPI(String s) {
        //JsonObject json = JsonParser.parseString("{\"aspects\":[]}").getAsJsonObject();

        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(new URL(WYNN_ASPECT_API + s).openStream(), StandardCharsets.UTF_8));
            String line;
            StringBuilder builder = new StringBuilder();

            while ((line = buffer.readLine()) != null) {
                builder.append(line);
            }

            JsonObject json = JsonParser.parseString(builder.toString()).getAsJsonObject();
            for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
                JsonObject j = json.get(entry.getKey()).getAsJsonObject();
                if (j.get("rarity") != null) {
                    j.addProperty(Identifications.RARITY.getItemName(), j.get("rarity").getAsString());
                    j.remove("rarity");
                }
            }

            FileWriter writer = new FileWriter(getFilePath("/items_data/" + s + "_aspect.json"));
            writer.write(json.toString());
            writer.close();

            System.out.println(s + " Aspects loaded.");

        } catch (IOException e) {
            System.out.println(s + " Aspects load failed.");
            System.out.println(e.getMessage());
        }
    }

    private void loadWynnAspectAPI(String s, List<Aspect> list) {
        try {
            JsonObject json = JsonParser.parseReader(new JsonReader(new InputStreamReader(new FileInputStream(getFilePath("/items_data/" + s + "_aspect.json")), StandardCharsets.UTF_8))).getAsJsonObject();

            for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
                list.add(new Aspect(entry.getValue().getAsJsonObject()));
            }
        } catch (IOException e) {
            System.out.println(s + " Aspects json read failed.");
            System.out.println(e.getMessage());
        }
    }

    private void getWynnSetsAPI() {
        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(new URL(WYNN_SETS_API).openStream(), StandardCharsets.UTF_8));
            String line;
            StringBuilder builder = new StringBuilder();

            while ((line = buffer.readLine()) != null) {
                builder.append(line);
            }

            JsonObject json = JsonParser.parseString(builder.toString()).getAsJsonObject();
            for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
                JsonObject j = json.get(entry.getKey()).getAsJsonObject();
                j.addProperty(JsonKeys.NAME.getKey(), entry.getKey());
            }

            FileWriter writer = new FileWriter(getFilePath("/items_data/sets.json"));
            writer.write(json.toString());
            writer.close();

            System.out.println("Sets loaded.");
        } catch (IOException e) {
            System.out.println("Sets load failed.");
            System.out.println(e.getMessage());
        }
    }

    private JsonObject loadWynnSetsAPI() {
        try {
            return JsonParser.parseReader(new JsonReader(new InputStreamReader(new FileInputStream(getFilePath("/items_data/sets.json")), StandardCharsets.UTF_8))).getAsJsonObject();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public File getOldItemFile() {
        return getFilePath("/items_data/old_data/item");
    }

    public File getOldIngFile() {
        return getFilePath("/items_data/old_data/ing");
    }

    public File getOldOtherFile() {
        return getFilePath("/items_data/old_data/other");
    }

    public JsonObject getMajorIDJson() {
        try {
            return JsonParser.parseReader(new JsonReader(new InputStreamReader(new FileInputStream(getFilePath("/items_data/major_ids.json")), StandardCharsets.UTF_8))).getAsJsonObject();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static boolean getVersion() {
        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(new URL("https://raw.githubusercontent.com/iTzhsnu/WynnSearcher/master/version.txt").openStream()));
            String line;
            StringBuilder builder = new StringBuilder();

            while ((line = buffer.readLine()) != null) {
                builder.append(line);
            }
            buffer.close();

            String[] latest = builder.toString().split("\\.");
            String[] now = SearchUi.VERSION.split("\\.");
            if (Integer.parseInt(latest[0]) > Integer.parseInt(now[0])) {
                return true;
            } else if (Integer.parseInt(latest[0]) == Integer.parseInt(now[0])) {
                if (Integer.parseInt(latest[1]) > Integer.parseInt(now[1])) {
                    return true;
                } else if (Integer.parseInt(latest[1]) == Integer.parseInt(now[1]) && Integer.parseInt(latest[2]) > Integer.parseInt(now[2])) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("This url not found.");
        }
        return false;
    }

    private JsonObject getHowToObtainItem() {
        try {
            return JsonParser.parseReader(new JsonReader(new InputStreamReader(new FileInputStream(getFilePath("/items_data/manual_item_drop.json")), StandardCharsets.UTF_8))).getAsJsonObject();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("How to obtain item json file not found.");
        }
        return null;
    }

    private JsonObject getHowToObtainIng() {
        try {
            return JsonParser.parseReader(new JsonReader(new InputStreamReader(new FileInputStream(getFilePath("/items_data/manual_ingredient_drop.json")), StandardCharsets.UTF_8))).getAsJsonObject();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("How to obtain ingredient json file not found.");
        }
        return null;
    }

    private JsonObject getHowToObtainOthers() {
        try {
            return JsonParser.parseReader(new JsonReader(new InputStreamReader(new FileInputStream(getFilePath("/items_data/manual_other_drop.json")), StandardCharsets.UTF_8))).getAsJsonObject();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("How to obtain other item file not found.");
        }
        return null;
    }

    @Deprecated
    public JsonObject getSetBonus() {
        try {
            return JsonParser.parseReader(new JsonReader(new InputStreamReader(new FileInputStream(getFilePath("/items_data/set_bonus.json")), StandardCharsets.UTF_8))).getAsJsonObject().get("set_bonus").getAsJsonObject();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Set Bonus file not found.");
        }
        return null;
    }

    @Deprecated
    public JsonObject getSetItems() {
        try {
            return JsonParser.parseReader(new JsonReader(new InputStreamReader(new FileInputStream(getFilePath("/items_data/set_items.json")), StandardCharsets.UTF_8))).getAsJsonObject().get("items").getAsJsonObject();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Set Items file not found.");
        }
        return null;
    }

    private void setItemData() {
        for (Item item : wynnItems) {
            switch (item.getSubType()) {
                case JsonValues.BOW:
                    bowData.add(item);
                    weaponData.add(item);
                    break;
                case JsonValues.SPEAR:
                    spearData.add(item);
                    weaponData.add(item);
                    break;
                case JsonValues.WAND:
                    wandData.add(item);
                    weaponData.add(item);
                    break;
                case JsonValues.DAGGER:
                    daggerData.add(item);
                    weaponData.add(item);
                    break;
                case JsonValues.RELIK:
                    relikData.add(item);
                    weaponData.add(item);
                    break;
                case JsonValues.HELMET:
                    helmetData.add(item);
                    break;
                case JsonValues.CHESTPLATE:
                    chestplateData.add(item);
                    break;
                case JsonValues.LEGGINGS:
                    leggingsData.add(item);
                    break;
                case JsonValues.BOOTS:
                    bootsData.add(item);
                    break;
                case JsonValues.RING:
                    ringData.add(item);
                    break;
                case JsonValues.BRACELET:
                    braceletData.add(item);
                    break;
                case JsonValues.NECKLACE:
                    necklaceData.add(item);
                    break;
            }
        }
    }

    private void setIngredientData() {
        for (Ingredient ing : wynnIngredients) {
            for (JsonElement skill : ing.getSkills()) {
                switch (skill.getAsString()) {
                    case JsonValues.ARMOURING:
                        armouringData.add(ing);
                        break;
                    case JsonValues.TAILORING:
                        tailoringData.add(ing);
                        break;
                    case JsonValues.WEAPONSMITHING:
                        weaponsmithingData.add(ing);
                        break;
                    case JsonValues.WOODWORKING:
                        woodworkingData.add(ing);
                        break;
                    case JsonValues.JEWELING:
                        jewelingData.add(ing);
                        break;
                    case JsonValues.SCRIBING:
                        scribingData.add(ing);
                        break;
                    case JsonValues.COOKING:
                        cookingData.add(ing);
                        break;
                    case JsonValues.ALCHEMISM:
                        alchemismData.add(ing);
                        break;
                }
            }
        }
    }

    private void setOtherItemsData() {
        for (Item item : wynnOtherItems) {
            switch (item.getType()) {
                case JsonValues.TOME:
                    tomeData.add(item);
                    switch (item.getSubType()) {
                        case JsonValues.GUILD_TOME: //x1 Slots
                            guildTomeData.add(item);
                            break;
                        case JsonValues.MARATHON_TOME: //x2 Slots
                            marathonTomeData.add(item);
                            break;
                        case JsonValues.EXPERTISE_TOME: //x2 Slots
                            expertiseTomeData.add(item);
                            break;
                        case JsonValues.LOOTRUN_TOME: //x1 Slots
                            lootrunTomeData.add(item);
                            break;
                        case JsonValues.MYSTICISM_TOME: //x2 Slots
                            mysticismTomeData.add(item);
                            break;
                        case JsonValues.WEAPON_TOME: //x2 Slots
                            weaponTomeData.add(item);
                            break;
                        case JsonValues.ARMOUR_TOME: //x4 Slots
                            armourTomeData.add(item);
                            break;
                    }
                    break;
                case JsonValues.CHARM:
                    charmData.add(item);
                    break;
                case JsonValues.TOOL:
                    toolData.add(item);
                    break;
                case JsonValues.MATERIAL:
                    materialData.add(item);
                    break;
            }
        }
    }

    public void updateApi() {
        if (!wynnItems.isEmpty()) wynnItems.clear();
        if (!wynnIngredients.isEmpty()) wynnIngredients.clear();
        if (!wynnOtherItems.isEmpty()) wynnOtherItems.clear();
        if (!bowData.isEmpty()) bowData.clear();
        if (!spearData.isEmpty()) spearData.clear();
        if (!wandData.isEmpty()) wandData.clear();
        if (!daggerData.isEmpty()) daggerData.clear();
        if (!relikData.isEmpty()) relikData.clear();
        if (!helmetData.isEmpty()) helmetData.clear();
        if (!chestplateData.isEmpty()) chestplateData.clear();
        if (!leggingsData.isEmpty()) leggingsData.clear();
        if (!bootsData.isEmpty()) bootsData.clear();
        if (!ringData.isEmpty()) ringData.clear();
        if (!braceletData.isEmpty()) braceletData.clear();
        if (!necklaceData.isEmpty()) necklaceData.clear();
        if (!armouringData.isEmpty()) armouringData.clear();
        if (!tailoringData.isEmpty()) tailoringData.clear();
        if (!weaponsmithingData.isEmpty()) weaponsmithingData.clear();
        if (!woodworkingData.isEmpty()) woodworkingData.clear();
        if (!jewelingData.isEmpty()) jewelingData.clear();
        if (!scribingData.isEmpty()) scribingData.clear();
        if (!cookingData.isEmpty()) cookingData.clear();
        if (!alchemismData.isEmpty()) alchemismData.clear();
        if (!tomeData.isEmpty()) tomeData.clear();
        if (!charmData.isEmpty()) charmData.clear();
        if (!toolData.isEmpty()) toolData.clear();
        if (!materialData.isEmpty()) materialData.clear();

        ApiDataManager api = ApiDataManager.getManager();

        itemApiConnect = api.getWynnAPIV3_3(wynnItems, wynnIngredients, wynnOtherItems);
        api.getWynnSetsAPI();

        api.setWynnAbilityTreeAPI(JsonValues.WARRIOR);
        api.setWynnAbilityTreeAPI(JsonValues.ASSASSIN);
        api.setWynnAbilityTreeAPI(JsonValues.MAGE);
        api.setWynnAbilityTreeAPI(JsonValues.ARCHER);
        api.setWynnAbilityTreeAPI(JsonValues.SHAMAN);

        api.getWynnAspectAPI(JsonValues.WARRIOR);
        api.getWynnAspectAPI(JsonValues.ASSASSIN);
        api.getWynnAspectAPI(JsonValues.MAGE);
        api.getWynnAspectAPI(JsonValues.ARCHER);
        api.getWynnAspectAPI(JsonValues.SHAMAN);

        setItemData();
        setIngredientData();
        setOtherItemsData();
        setsJson = api.loadWynnSetsAPI();
    }

    public static void setApiConnectText(JLabel label, String name, ErrorType error) {
        label.setText(name + " " + error.getValue());
        switch (error) {
            case LATEST:
            case SUCCEED:
                label.setForeground(new Color(0, 169, 104));
                break;
            case FAILED:
                label.setForeground(new Color(255, 0, 0));
            break;
        }
    }

}
