package com.github.iTzhsnu.WynnSearcher.builder;

import com.github.iTzhsnu.WynnSearcher.GetAPI;
import com.github.iTzhsnu.WynnSearcher.Identifications;
import com.github.iTzhsnu.WynnSearcher.ItemUITemplate;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class SetBonus {
    private final String setName;
    private final int size;
    private final List<String> equippedItems = new ArrayList<>();
    private int equippedSize = 0;
    private boolean hasWeapon = false;

    public SetBonus(String setName, String itemName, boolean isWeapon) {
        this.setName = setName;
        JsonObject j = new GetAPI().getSetBonus();
        if (j.get(setName) != null) {
            size = j.get(setName).getAsJsonObject().get("size").getAsInt();
        } else {
            size = 0;
        }
        add(itemName, isWeapon);
    }

    public String getName() {
        return setName + " Set (" + equippedItems.size() + "/" + size + ")";
    }

    public String getSetName() {
        return setName;
    }

    public int[] getId_Numbers(boolean checkWeapon) {
        if (checkWeapon && hasWeapon) {
            return getId_Numbers(equippedItems.size() - 1);
        } else {
            return getId_Numbers(equippedItems.size());
        }
    }

    public int[] getId_Numbers(int size) {
        int[] id_Numbers = new int[] {
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //0 ~ 9 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //10 ~ 19 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //20 ~ 29 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //30 ~ 39 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //40 ~ 49 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //50 ~ 59 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //60 ~ 69 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //70 ~ 79 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //80 ~ 89 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //90 ~ 99 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0 //100 ~ 108 (ID), 109 (Weapon Tome Damage Bonus)
        };

        if (size > 0) {
            JsonElement jo = new GetAPI().getSetBonus().get(setName);
            if (jo != null && jo.getAsJsonObject().get(String.valueOf(size)) != null) {
                JsonObject j = jo.getAsJsonObject().get(String.valueOf(size)).getAsJsonObject();
                for (int i = 0; ItemUITemplate.ITEM_IDS.size() > i; ++i) {
                    Identifications id = ItemUITemplate.ITEM_IDS.get(i);
                    if (j.get(id.getItemName()) != null) id_Numbers[ID_Display.ID_INT.get(id)] = j.get(id.getItemName()).getAsInt();
                }
                for (int i = 0; ItemUITemplate.REVERSED_ITEM_IDS.size() > i; ++i) {
                    Identifications id = ItemUITemplate.REVERSED_ITEM_IDS.get(i);
                    if (j.get(id.getItemName()) != null) id_Numbers[ID_Display.ID_INT.get(id)] = j.get(id.getItemName()).getAsInt();
                }
            }
        }

        return id_Numbers;
    }

    public void add_to_ID_Numbers(int[] id_Numbers) {
        int[] id_Numbers_Set = getId_Numbers(false);
        for (int i = 0; id_Numbers.length > i; ++i) {
            id_Numbers[i] += id_Numbers_Set[i];
        }
    }

    public List<String> getEquippedItems() {
        return equippedItems;
    }

    public void addEquipped() {
        ++equippedSize;
    }

    public int getEquippedSize() {
        return equippedSize;
    }

    public void add(String itemName, boolean isWeapon) {
        equippedItems.add(itemName);
        if (isWeapon) hasWeapon = true;
    }
}
