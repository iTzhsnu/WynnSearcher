package com.github.iTzhsnu.WynnSearcher.ui;

import com.github.iTzhsnu.WynnSearcher.ApiDataManager;
import com.github.iTzhsnu.WynnSearcher.Identifications;
import com.github.iTzhsnu.WynnSearcher.builder.skilltree.TreeCheckBox;
import com.github.iTzhsnu.WynnSearcher.data.ItemBase;
import com.github.iTzhsnu.WynnSearcher.general.ItemType;
import com.github.iTzhsnu.WynnSearcher.general.JsonKeys;
import com.github.iTzhsnu.WynnSearcher.general.JsonValues;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class EquipmentUi extends ItemUi {

    public EquipmentUi(ItemBase item, ItemType type, JPanel previous, JPanel above, int uiWidth, float totalValue, boolean isCustom) {
        super(item, type, previous, above, uiWidth, totalValue, isCustom);
    }

    @Override
    public void setDisplay() {
        JsonObject json = item.getJson();

        if (!item.getName().isEmpty()) {
            label.add(new JLabel(item.getName()));
        }

        if (!item.getSubType().isEmpty()) {
            label.add(new JLabel("Type: " + item.getSubType().substring(0, 1).toUpperCase() + item.getSubType().substring(1)));
        }

        if (!item.getIdString(Identifications.ATTACK_SPEED).isEmpty()) {
            String s = item.getIdString(Identifications.ATTACK_SPEED);
            String s1;
            if (s.equals(JsonValues.SUPER_FAST) || s.equals(JsonValues.SUPER_SLOW)) {
                s1 = " " + s.substring(0, 1).toUpperCase() + s.substring(1, 5) + " " + s.substring(5);
            } else if (s.equals(JsonValues.VERY_FAST) || s.equals(JsonValues.VERY_SLOW)) {
                s1 = " " + s.substring(0, 1).toUpperCase() + s.substring(1, 4) + " " + s.substring(4);
            } else {
                s1 = " " + s.substring(0, 1).toUpperCase() + s.substring(1);
            }
            label.add(new JLabel("Attack Speed:" + s1));
        }

        label.add(new JLabel(" "));

        if (item.haveFieldPos(JsonKeys.BASE)) {
            if (item.haveId(Identifications.HEALTH, null, null, null)) {
                int maxValue = item.getIdValue(Identifications.HEALTH, JsonKeys.MAX);

                if (maxValue != 0) {
                    if (item.getIdString(Identifications.RARITY).equals(JsonValues.CRAFTED)) {
                        label.add(new JLabel("Health: " + item.getIdValue(Identifications.HEALTH, JsonKeys.MIN) + "-" + maxValue));
                    } else {
                        label.add(new JLabel("Health: " + setPlus(maxValue)));
                    }
                }
            }

            for (int i = 1; 6 > i; ++ i) { // Defenses
                Identifications id = DEFENSE_IDS.get(i);
                int value = item.getIdValue(id, JsonKeys.MAX);
                if (value != 0) {
                    label.add(new JLabel(id.getDisplayName() + ": " + setPlus(value) + id.getDisplaySp()));
                }
            }

            for (int i = 0; 6 > i; ++i) { //Damages
                Identifications id = DAMAGE_IDS.get(i);
                if (item.getIdString(Identifications.RARITY).equals(JsonValues.CRAFTED)) {
                    if (json.get(id.getItemFieldPos().getKey()) != null && json.get(id.getItemFieldPos().getKey()).getAsJsonObject().get(id.getItemName()) != null) {
                        JsonObject j = json.get(id.getItemFieldPos().getKey()).getAsJsonObject().get(id.getItemName()).getAsJsonObject();
                        label.add(new JLabel(id.getDisplayName() + ": " + j.get(JsonKeys.MIN.getKey()).getAsString() + " to " + j.get(JsonKeys.MAX.getKey()).getAsString()));
                    }
                } else {
                    int maxValue = item.getIdValue(id, JsonKeys.MAX);

                    if (maxValue != 0) {
                        label.add(new JLabel(id.getDisplayName() + ": " + item.getIdValue(id, JsonKeys.MIN) + "-" + maxValue));
                    }
                }
            }
        }

        label.add(new JLabel(" "));

        int lv = item.getIdValue(Identifications.LEVEL, JsonKeys.MAX);
        if (item.haveFieldPos(JsonKeys.REQUIREMENTS)) {
            if (lv != 0) {
                if (item.getIdString(Identifications.RARITY).equals(JsonValues.CRAFTED)) {
                    label.add(new JLabel("Combat Lv. Min: " + item.getIdValue(Identifications.LEVEL, JsonKeys.MIN) + "-" + lv));
                } else {
                    label.add(new JLabel("Combat Lv. Min: " + lv));
                }
            }

            for (int i = 0; 5 > i; ++i) {
                Identifications id = SP_REQUESTS.get(i);
                int value = item.getIdValue(id, JsonKeys.MAX);
                if (value != 0) {
                    label.add(new JLabel(id.getDisplayName() + ": " + value));
                }
            }

            if (!item.getIdString(Identifications.QUEST_REQ).isEmpty()) {
                label.add(new JLabel("Quest Req: " + item.getIdString(Identifications.QUEST_REQ)));
            }
        }

        label.add(new JLabel(" "));

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
                } else {
                    label.add(new JLabel(setPlus(minValue) + id.getDisplaySp() + " " + id.getDisplayName() + " " + setPlus(maxValue) + id.getDisplaySp()));
                }
            }
        }

        label.add(new JLabel(" "));
        if (item.getIdValue(Identifications.POWDER_SLOTS, JsonKeys.MAX) != 0) {
            label.add(new JLabel("Powder Slots: " + item.getIdValue(Identifications.POWDER_SLOTS, JsonKeys.MAX)));
        }

        if (item.haveId(Identifications.MAJOR_IDS, null, "", "")) {
            if (json.get(Identifications.MAJOR_IDS.getItemName()).getAsJsonObject().get("name") != null) { //OLD
                JLabel_Custom l = new JLabel_Custom("Major ID: " + json.get(Identifications.MAJOR_IDS.getItemName()).getAsJsonObject().get("name").getAsString());
                l.setToolTipText("<html>" + TreeCheckBox.fixesText(json.get(Identifications.MAJOR_IDS.getItemName()).getAsJsonObject().get("description").getAsString()));
                label.add(l);
            } else { //NEWER
                for (Map.Entry<String, JsonElement> entry : json.get(Identifications.MAJOR_IDS.getItemName()).getAsJsonObject().entrySet()) {
                    JLabel_Custom l = new JLabel_Custom("Major ID: " + entry.getKey());
                    l.setToolTipText("<html>" + TreeCheckBox.fixesText(json.get(Identifications.MAJOR_IDS.getItemName()).getAsJsonObject().get(entry.getKey()).getAsString()));
                    label.add(l);
                }
            }
        }

        if (!item.getIdString(Identifications.RARITY).isEmpty()) {
            String rarity = item.getIdString(Identifications.RARITY);
            label.add(new JLabel("Rarity: " + rarity.substring(0, 1).toUpperCase() + rarity.substring(1)));
        }

        if (json.get(Identifications.DURABILITY.getIngName()) != null) { // Crafted
            label.add(new JLabel("Durability: " + json.get(Identifications.DURABILITY.getIngName()).getAsString()));
        }

        if (json.get(Identifications.DURATION.getIngName()) != null) { // Crafted
            label.add(new JLabel("Duration: " + json.get(Identifications.DURATION.getIngName()).getAsString()));
        }

        if (json.get(Identifications.CHARGES.getIngName()) != null) { // Crafted
            label.add(new JLabel("Charge: " + json.get(Identifications.CHARGES.getIngName()).getAsInt()));
        }

        if (json.get("restrictions") != null && !json.get("restrictions").isJsonNull()) {
            label.add(new JLabel(json.get("restrictions").getAsString()));
        }

        JButton_Custom sets_label = null;
        if (!isCustom && json.get(JsonKeys.SETS.getKey()) != null) {
            String setName = json.get(JsonKeys.SETS.getKey()).getAsString(); // Warning: this is string array
            ApiDataManager api = ApiDataManager.getManager();
            if (api.setsJson.get(setName) != null) {
                //label.add(new JLabel(" "));
                sets_label = new JButton_Custom("Set Bonuses");
                sets_label.setBorderPainted(false);
                sets_label.setOpaque(false);
                sets_label.setBackground(Color.WHITE);
                sets_label.setForeground(Color.BLUE);

                JsonObject setJ = api.setsJson.get(setName).getAsJsonObject();
                StringBuilder sb = new StringBuilder();
                sb.append(setName);

                if (setJ.get(JsonKeys.BONUSES.getKey()) != null) {
                    JsonObject bonusJ = setJ.get(JsonKeys.BONUSES.getKey()).getAsJsonObject();
                    for (int n = 1; 10 > n; ++n) {
                        if (bonusJ.get(String.valueOf(n)) != null && bonusJ.get(String.valueOf(n)).getAsJsonObject().get(JsonKeys.MINOR.getKey()) != null) {
                            JsonObject j = bonusJ.get(String.valueOf(n)).getAsJsonObject().get(JsonKeys.MINOR.getKey()).getAsJsonObject();

                            sb.append("<br>");
                            sb.append(n);
                            sb.append(":");

                            for (int i = 0; ITEM_IDS.size() > i; ++i) {
                                Identifications id = ITEM_IDS.get(i);
                                if (j.get(id.getItemName()) != null) {
                                    String s = "<br>" + id.getDisplayName() + " " + setPlus(j.get(id.getItemName()).getAsInt()) + id.getDisplaySp();
                                    sb.append(s);
                                }
                            }

                            for (int i = 0; REVERSED_ITEM_IDS.size() > i; ++i) {
                                Identifications id = ITEM_IDS.get(i);
                                if (j.get(id.getItemName()) != null) {
                                    String s = "<br>" + id.getDisplayName() + " " + setPlus(j.get(id.getItemName()).getAsInt()) + id.getDisplaySp();
                                    sb.append(s);
                                }
                            }

                            sb.append("<br>");
                        }
                    }
                }

                sets_label.setToolTipText("<html><font color=\"#FFFFFF\">" + sb.substring(0, sb.toString().length() - 4));
                sets_label.addActionListener(new FullMessageAction());
            }
        }


        JButton_Custom l = null; // How to Obtain Label
        if (!isCustom) {
            if (json.get(JsonKeys.DROP_RESTRICTION.getKey()) != null || json.get(JsonKeys.DROP_META.getKey()) != null) {
                label.add(new JLabel(" "));
                l = new JButton_Custom("How to Obtain (Not Perfect)");
                l.setBorderPainted(false);
                l.setOpaque(false);
                l.setBackground(Color.WHITE);
                l.setForeground(Color.BLUE);

                l.setToolTipText(getHowToObtainText(item.getName(), json, lv, ApiDataManager.getManager().howToObtainItem));

                l.addActionListener(new FullMessageAction());
            }
        }

        for (JLabel jLabel : label) {
            add(jLabel);
        }

        if (sets_label != null) {
            add(sets_label);
            urlSize += 28;
        }

        if (l != null) {
            add(l);
            urlSize += 24;
        }

        if (!isCustom) {
            label.add(new JLabel(" "));
            add(label.get(label.size() - 1));

            JLabel sortValue = new JLabel("Sort Value: " + totalValue);
            sortValue.setForeground(Color.DARK_GRAY);
            add(sortValue);
        }
    }
}
