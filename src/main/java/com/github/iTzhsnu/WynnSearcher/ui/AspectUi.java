package com.github.iTzhsnu.WynnSearcher.ui;

import com.github.iTzhsnu.WynnSearcher.Identifications;
import com.github.iTzhsnu.WynnSearcher.data.ItemBase;
import com.github.iTzhsnu.WynnSearcher.general.ItemType;
import com.github.iTzhsnu.WynnSearcher.general.JsonValues;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.swing.*;

public class AspectUi extends ItemUi {
    private int tier = 0;

    public AspectUi(ItemBase item, ItemType type, JPanel previous, JPanel above, int uiWidth, float totalValue, boolean isCustom) {
        super(item, type, previous, above, uiWidth, totalValue, isCustom);
    }

    @Override
    public void setDisplay() {
        JsonObject json = item.getJson();

        if (tier == 0) tier = 4;

        int maxTier = 4;
        if (tier > 3) {
            tier = 4;
            if (!item.getIdString(Identifications.RARITY).equals(JsonValues.LEGENDARY)) {
                tier = 3;
                maxTier = 3;
            }
        }

        String itemName = "";
        if (json.get("name") != null) {
            itemName = json.get("name").getAsString();
            label.add(new JLabel("<html>" + itemName + "</html>"));
        }

        label.add(new JLabel("Tier: "+ tier + "/" + maxTier));

        label.add(new JLabel(" "));

        if (json.get("tiers") != null && json.get("tiers").getAsJsonObject().get(String.valueOf(tier)) != null && json.get("tiers").getAsJsonObject().get(String.valueOf(tier)).getAsJsonObject().get("description") != null) {
            for (JsonElement je : json.get("tiers").getAsJsonObject().get(String.valueOf(tier)).getAsJsonObject().get("description").getAsJsonArray()) {
                label.add(new JLabel("<html>" + je.getAsString().replaceAll("#AAAAAA", "#333333").replaceAll("#555555", "#333333").replaceAll("#FFFFFF", "#333333").replaceAll("#FF5555", "#333333").replaceAll(" style='text-decoration: underline'", "") + "</html>"));
            }
        }

        label.add(new JLabel(" "));

        //TODO TIER CHANGE BUTTON

        for (JLabel l : label) {
            add(l);
        }

        fixesSize(itemName);
    }

    public void setTier(int value) {}
}
