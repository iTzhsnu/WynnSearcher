//Original Code Created By: soramame_256
package com.github.iTzhsnu.WynnSearcher;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

public class GetAPI {
    public static final String WYNN_ITEM_API = "https://api.wynncraft.com/public_api.php?action=itemDB&category=all";
    public static final String WYNN_INGREDIENT_API = "https://api.wynncraft.com/v2/ingredient/search/tier/";

    //private static final String VIOLET_SHIFT = "{\"name\":\"Violet-Shift\", \"tier\":\"Fabled\", \"type\":\"Boots\", \"sockets\":2, \"fireDefense\":-50, \"level\":77, \"manaRegen\":11, \"lifeSteal\":233, \"exploding\":17, \"soulPoints\":-25, \"spellElementalDamageBonus\":29, \"spellNeutralDamageBonusRaw\":178}";

    public static void setItemData(List<JsonObject> list, JLabel label) {
        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(new URL(WYNN_ITEM_API).openStream()));
            String line;
            StringBuilder builder = new StringBuilder();

            while ((line = buffer.readLine()) != null) {
                builder.append(line);
            }

            JsonElement element = JsonParser.parseString(builder.toString());
            JsonObject object = element.getAsJsonObject();
            for (JsonElement je : object.get("items").getAsJsonArray()) {
                list.add(je.getAsJsonObject());
            }

            label.setText("Item API Connected");

        } catch (IOException e) {
            e.printStackTrace();
            label.setText("Connect Failed");
        }
    }

    public static void setExistOnAPIItemData(List<JsonObject> list) {
        JsonElement violet_shift = JsonParser.parseString("{\"name\":\"Violet-Shift\", \"tier\":\"Fabled\", \"type\":\"Boots\", \"sockets\":2, \"fireDefense\":-50, \"level\":77, \"manaRegen\":11, \"lifeSteal\":233, \"exploding\":17, \"soulPoints\":-25, \"spellElementalDamageBonus\":29, \"spellNeutralDamageBonusRaw\":178, \"majorIds\":[\"ENTROPY\"]}");
        list.add(violet_shift.getAsJsonObject());
    }

    public static void setIngredientData(List<JsonObject> list, JLabel label) {
        for (int i = 0; i <= 3; ++i) {
            try {
                BufferedReader buffer = new BufferedReader(new InputStreamReader(new URL(WYNN_INGREDIENT_API + i).openStream()));
                String line;
                StringBuilder builder = new StringBuilder();

                while ((line = buffer.readLine()) != null) {
                    builder.append(line);
                }

                JsonElement element = JsonParser.parseString(builder.toString());
                JsonObject object = element.getAsJsonObject();
                for (JsonElement je : object.get("data").getAsJsonArray()) {
                    list.add(je.getAsJsonObject());
                }

                label.setText("Ingredient API Connected");

            } catch (IOException e) {
                e.printStackTrace();
                label.setText("Connect Failed");
            }
        }
    }

}
