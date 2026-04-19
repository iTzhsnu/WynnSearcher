package com.github.iTzhsnu.WynnSearcher.ui;

import com.github.iTzhsnu.WynnSearcher.ApiDataManager;
import com.github.iTzhsnu.WynnSearcher.Identifications;
import com.github.iTzhsnu.WynnSearcher.SearchUI;
import com.github.iTzhsnu.WynnSearcher.data.ItemBase;
import com.github.iTzhsnu.WynnSearcher.general.ItemType;
import com.github.iTzhsnu.WynnSearcher.general.JsonKeys;
import com.github.iTzhsnu.WynnSearcher.general.JsonValues;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.swing.*;
import java.awt.*;

public class OthersUi extends ItemUi {
    public OthersUi(ItemBase item, ItemType type, JPanel previous, JPanel above, int uiWidth, float totalValue, boolean isCustom) {
        super(item, type, previous, above, uiWidth, totalValue, isCustom);
    }

    @Override
    public void setDisplay() {
        JsonObject json = item.getJson();

        String type = json.get(JsonKeys.TYPE.getKey()).getAsString();

        String itemName = "";

        if (json.get(JsonKeys.NAME.getKey()) != null) {
            itemName = json.get(JsonKeys.NAME.getKey()).getAsString();
            label.add(new JLabel(json.get(JsonKeys.NAME.getKey()).getAsString()));
        }

        label.add(new JLabel("Type: " + type.substring(0, 1).toUpperCase() + type.substring(1)));

        if (json.get(JsonKeys.SUBTYPE.getKey()) != null) {
            String s;
            switch (json.get(JsonKeys.SUBTYPE.getKey()).getAsString()) {
                case JsonValues.GUILD_TOME: //x1 Slots
                    s = "Guild";
                    break;
                case JsonValues.MARATHON_TOME: //x2 Slots
                    s = "Marathon";
                    break;
                case JsonValues.EXPERTISE_TOME: //x2 Slots
                    s = "Expertise";
                    break;
                case JsonValues.LOOTRUN_TOME: //x1 Slots
                    s = "Lootrun";
                    break;
                case JsonValues.MYSTICISM_TOME: //x2 Slots
                    s = "Mysticism";
                    break;
                case JsonValues.WEAPON_TOME: //x2 Slots
                    s = "Weapon";
                    break;
                case JsonValues.ARMOUR_TOME: //x4 Slots
                    s = "Armour";
                    break;
                default:
                    s = json.get(JsonKeys.SUBTYPE.getKey()).getAsString();
                    //System.out.println(itemName + " has unknown tome type: " + s);
                    break;
            }

            switch (type) {
                case JsonValues.TOME:
                    label.add(new JLabel("Tome Type: " + s));
                    break;
                case JsonValues.TOOL:
                    label.add(new JLabel("Tool Type: " + s.substring(0, 1).toUpperCase() + s.substring(1)));
                    break;
                case JsonValues.MATERIAL:
                    label.add(new JLabel("Gather Type: " + s.substring(0, 1).toUpperCase() + s.substring(1)));
                    break;
            }
        }

        int lv = item.getIdValue(Identifications.LEVEL, JsonKeys.MAX);
        if (lv != 0) {
            switch (type) {
                case JsonValues.TOME:
                case JsonValues.CHARM:
                    label.add(new JLabel("Combat Lv. Min: " + lv));
                    break;
                case JsonValues.TOOL:
                    switch (json.get(JsonKeys.SUBTYPE.getKey()).getAsString()) {
                        case JsonValues.PICKAXE:
                            label.add(new JLabel("Mining Lv. Min: " + lv));
                            break;
                        case JsonValues.AXE:
                            label.add(new JLabel("Woodcutting Lv. Min: " + lv));
                            break;
                        case JsonValues.SCYTHE:
                            label.add(new JLabel("Farming Lv. Min: " + lv));
                            break;
                        case JsonValues.ROD:
                            label.add(new JLabel("Fishing Lv. Min: " + lv));
                            break;
                    }
                    break;
                case JsonValues.MATERIAL:
                    label.add(new JLabel("Lv. Min: " + lv));
                    break;
            }
        }

        label.add(new JLabel(" "));

        if (json.get(JsonKeys.GATHERING_SPEED.getKey()) != null) { //Tools
            String s = "Slow";
            int i = json.get(JsonKeys.GATHERING_SPEED.getKey()).getAsInt();
            if (i >= 120) {
                s = "Very Fast";
            } else if (i >= 65) {
                s = "Fast";
            } else if (i == 45) {
                s = "Normal";
            }
            label.add(new JLabel("Gathering Speed: " + i + " (" + s + ")"));
        }

        // level range removed from api?
        if (type.equals(JsonValues.CHARM) && json.get(Identifications.LEVELED_XP_BONUS.getItemFieldPos().getKey()) != null && json.get(JsonKeys.REQUIREMENTS.getKey()) != null) {
            JsonObject j = json.get(Identifications.LEVELED_XP_BONUS.getItemFieldPos().getKey()).getAsJsonObject();
            int max = lv + 20;

            if (j.get(Identifications.LEVELED_XP_BONUS.getItemName()) != null) label.add(new JLabel(setPlus(j.get(Identifications.LEVELED_XP_BONUS.getItemName()).getAsJsonObject().get("min").getAsInt()) + "% XP from Lv." + lv + "-" + max + " contents " + setPlus(j.get(Identifications.LEVELED_XP_BONUS.getItemName()).getAsJsonObject().get("max").getAsInt()) + "%"));
            if (j.get(Identifications.DAMAGE_FROM_MOBS.getItemName()) != null) label.add(new JLabel(setPlus(j.get(Identifications.DAMAGE_FROM_MOBS.getItemName()).getAsJsonObject().get("min").getAsInt()) + "% Damage taken from mobs " + setPlus(j.get(Identifications.DAMAGE_FROM_MOBS.getItemName()).getAsJsonObject().get("max").getAsInt()) + "%"));
            if (j.get(Identifications.LEVELED_LOOT_BONUS.getItemName()) != null) label.add(new JLabel(setPlus(j.get(Identifications.LEVELED_LOOT_BONUS.getItemName()).getAsJsonObject().get("min").getAsInt()) + "% Loot from Lv." + lv + "-" + max + " contents " + setPlus(j.get(Identifications.LEVELED_LOOT_BONUS.getItemName()).getAsJsonObject().get("max").getAsInt()) + "%"));
        }

        if (type.equals(JsonValues.TOME) || type.equals(JsonValues.CHARM)) { //IDs
            for (int i = 0; ITEM_IDS.size() > i; ++i) {
                Identifications id = ITEM_IDS.get(i);
                int minValue = item.getIdValue(id, JsonKeys.MIN);
                int maxValue = item.getIdValue(id, JsonKeys.MAX);

                if (minValue != 0 || maxValue != 0) {
                    if (minValue == maxValue) { //Constant Value
                        label.add(new JLabel(id.getDisplayName() + " "+ setPlus(maxValue) + id.getDisplaySp()));
                    } else if (id.isItemVariable() || item.getIdString(Identifications.RARITY).equals(JsonValues.CRAFTED)) { //Crafted Items or Variable ID
                        label.add(new JLabel(setPlus(minValue) + id.getDisplaySp() + " " + id.getDisplayName() + " " + setPlus(maxValue) + id.getDisplaySp()));
                    }
                }
            }

            for (int i = 0; REVERSED_ITEM_IDS.size() > i; ++i) {
                Identifications id = REVERSED_ITEM_IDS.get(i);
                int minValue = item.getIdValue(id, JsonKeys.MIN);
                int maxValue = item.getIdValue(id, JsonKeys.MAX);

                if (minValue != 0 || maxValue != 0) {
                    if (minValue == maxValue) { //Constant Value
                        label.add(new JLabel(id.getDisplayName() + " "+ setPlus(maxValue) + id.getDisplaySp()));
                    } else if (id.isItemVariable() || item.getIdString(Identifications.RARITY).equals(JsonValues.CRAFTED)) { //Crafted Items or Variable ID
                        label.add(new JLabel(setPlus(minValue) + id.getDisplaySp() + " " + id.getDisplayName() + " " + setPlus(maxValue) + id.getDisplaySp()));
                    }
                }
            }
        }

        label.add(new JLabel(" "));

        if (!type.equals(JsonValues.MATERIAL) && !type.equals(JsonValues.TOOL) && !item.getIdString(Identifications.RARITY).isEmpty()) {
            label.add(new JLabel("Rarity: " + item.getIdString(Identifications.RARITY).substring(0, 1).toUpperCase() + item.getIdString(Identifications.RARITY).substring(1)));
        }

        if (json.get("restrictions") != null) {
            label.add(new JLabel(json.get("restrictions").getAsString()));
        }

        JButton_Custom l = null;
        if (!isCustom) {
            if (json.get(JsonKeys.DROP_RESTRICTION.getKey()) != null || json.get(JsonKeys.DROP_META.getKey()) != null) {
                label.add(new JLabel(" "));
                l = new JButton_Custom("How to Obtain (Not Perfect)");
                l.setBorderPainted(false);
                l.setOpaque(false);
                l.setBackground(Color.WHITE);
                l.setForeground(Color.BLUE);

                l.setToolTipText(getHowToObtainText(itemName, json, lv, ApiDataManager.getManager().howToObtainOthers));

                l.addActionListener(new FullMessageAction());
            }
        }

        for (JLabel jLabel : label) {
            add(jLabel);
        }

        if (l != null) {
            add(l);
            urlSize += 24;
            label.add(new JLabel(" "));
            add(label.get(label.size() - 1));
        }

        JLabel sortValue = new JLabel("Sort Value: " + totalValue);
        sortValue.setForeground(Color.DARK_GRAY);
        add(sortValue);
    }
}
