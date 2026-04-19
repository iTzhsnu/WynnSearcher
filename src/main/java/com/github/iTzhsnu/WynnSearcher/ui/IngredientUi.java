package com.github.iTzhsnu.WynnSearcher.ui;

import com.github.iTzhsnu.WynnSearcher.ApiDataManager;
import com.github.iTzhsnu.WynnSearcher.Identifications;
import com.github.iTzhsnu.WynnSearcher.builder.skilltree.TreeCheckBox;
import com.github.iTzhsnu.WynnSearcher.data.ItemBase;
import com.github.iTzhsnu.WynnSearcher.general.ItemType;
import com.github.iTzhsnu.WynnSearcher.general.JsonKeys;
import com.github.iTzhsnu.WynnSearcher.general.JsonValues;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.swing.*;
import java.awt.*;

public class IngredientUi extends ItemUi {
    public IngredientUi(ItemBase item, ItemType type, JPanel previous, JPanel above, int uiWidth, float totalValue, boolean isCustom) {
        super(item, type, previous, above, uiWidth, totalValue, isCustom);
    }

    // TODO fixes
    @Override
    public void setDisplay() {
        JsonObject json = item.getJson();

        if (!item.getName().isEmpty()) {
            if (json.get(JsonKeys.DISPLAY_NAME.getKey()) != null) {
                label.add(new JLabel(json.get(JsonKeys.DISPLAY_NAME.getKey()).getAsString()));
            } else {
                label.add(new JLabel(item.getName()));
            }
        }

        if (!item.getIdString(Identifications.RARITY).isEmpty()) {
            int star = 0;
            switch (item.getIdString(Identifications.RARITY)) {
                case JsonValues.STAR_1:
                    star = 1;
                    break;
                case JsonValues.STAR_2:
                    star = 2;
                    break;
                case JsonValues.STAR_3:
                    star = 3;
                    break;
            }
            label.add(new JLabel("Star: " + star));
        }

        int lv = item.getIdValue(Identifications.LEVEL, JsonKeys.MAX);
        if (lv != 0) {
            label.add(new JLabel("Lv. Min: " + lv));
        }

        label.add(new JLabel(" "));

        if (json.get(JsonKeys.ITEMONLYIDS.getKey()) != null) {
            JsonObject j = json.getAsJsonObject(JsonKeys.ITEMONLYIDS.getKey()).getAsJsonObject();
            boolean run = false;
            if (j.get(Identifications.DURABILITY.getIngName()) != null && j.get(Identifications.DURABILITY.getIngName()).getAsInt() != 0) {
                label.add(new JLabel("Durability: " + setPlus(j.get(Identifications.DURABILITY.getIngName()).getAsInt() / 1000)));
                run = true;
            }
            if (j.get(Identifications.STRENGTH_REQ.getIngName()) != null && j.get(Identifications.STRENGTH_REQ.getIngName()).getAsInt() != 0) {
                label.add(new JLabel("Strength Req: " + setPlus(j.get(Identifications.STRENGTH_REQ.getIngName()).getAsInt())));
                run = true;
            }
            if (j.get(Identifications.DEXTERITY_REQ.getIngName()) != null && j.get(Identifications.DEXTERITY_REQ.getIngName()).getAsInt() != 0) {
                label.add(new JLabel("Dexterity Req: " + setPlus(j.get(Identifications.DEXTERITY_REQ.getIngName()).getAsInt())));
                run = true;
            }
            if (j.get(Identifications.INTELLIGENCE_REQ.getIngName()) != null && j.get(Identifications.INTELLIGENCE_REQ.getIngName()).getAsInt() != 0) {
                label.add(new JLabel("Intelligence Req: " + setPlus(j.get(Identifications.INTELLIGENCE_REQ.getIngName()).getAsInt())));
                run = true;
            }
            if (j.get(Identifications.DEFENSE_REQ.getIngName()) != null && j.get(Identifications.DEFENSE_REQ.getIngName()).getAsInt() != 0) {
                label.add(new JLabel("Defense Req: " + setPlus(j.get(Identifications.DEFENSE_REQ.getIngName()).getAsInt())));
                run = true;
            }
            if (j.get(Identifications.AGILITY_REQ.getIngName()) != null && j.get(Identifications.AGILITY_REQ.getIngName()).getAsInt() != 0) {
                label.add(new JLabel("Agility Req: " + setPlus(j.get(Identifications.AGILITY_REQ.getIngName()).getAsInt())));
                run = true;
            }
            if (run) label.add(new JLabel(" "));
        }

        if (json.get(JsonKeys.CONSUMABLEONLYIDS.getKey()) != null) {
            JsonObject j = json.get(JsonKeys.CONSUMABLEONLYIDS.getKey()).getAsJsonObject();
            boolean run = false;
            if (j.get(Identifications.DURATION.getIngName()) != null && j.get(Identifications.DURATION.getIngName()).getAsInt() != 0) {
                label.add(new JLabel("Duration: " + setPlus(j.get(Identifications.DURATION.getIngName()).getAsInt())));
                run = true;
            }
            if (j.get(Identifications.CHARGES.getIngName()) != null && j.get(Identifications.CHARGES.getIngName()).getAsInt() != 0) {
                label.add(new JLabel("Charges: " + setPlus(j.get(Identifications.CHARGES.getIngName()).getAsInt())));
                run = true;
            }
            if (run) label.add(new JLabel(" "));
        }

        if (json.get(JsonKeys.INGREDIENTPOSITIONMODIFIERS.getKey()) != null) {
            JsonObject j = json.get(JsonKeys.INGREDIENTPOSITIONMODIFIERS.getKey()).getAsJsonObject();
            boolean run = false;
            if (j.get(Identifications.INGREDIENT_EFFECTIVENESS_ABOVE.getIngName()) != null && j.get(Identifications.INGREDIENT_EFFECTIVENESS_ABOVE.getIngName()).getAsInt() != 0) {
                label.add(new JLabel("Ingredient Effectiveness (Above): " + setPlus(j.get(Identifications.INGREDIENT_EFFECTIVENESS_ABOVE.getIngName()).getAsInt()) + "%"));
                run = true;
            }
            if (j.get(Identifications.INGREDIENT_EFFECTIVENESS_UNDER.getIngName()) != null && j.get(Identifications.INGREDIENT_EFFECTIVENESS_UNDER.getIngName()).getAsInt() != 0) {
                label.add(new JLabel("Ingredient Effectiveness (Under): " + setPlus(j.get(Identifications.INGREDIENT_EFFECTIVENESS_UNDER.getIngName()).getAsInt()) + "%"));
                run = true;
            }
            if (j.get(Identifications.INGREDIENT_EFFECTIVENESS_RIGHT.getIngName()) != null && j.get(Identifications.INGREDIENT_EFFECTIVENESS_RIGHT.getIngName()).getAsInt() != 0) {
                label.add(new JLabel("Ingredient Effectiveness (Right): " + setPlus(j.get(Identifications.INGREDIENT_EFFECTIVENESS_RIGHT.getIngName()).getAsInt()) + "%"));
                run = true;
            }
            if (j.get(Identifications.INGREDIENT_EFFECTIVENESS_LEFT.getIngName()) != null && j.get(Identifications.INGREDIENT_EFFECTIVENESS_LEFT.getIngName()).getAsInt() != 0) {
                label.add(new JLabel("Ingredient Effectiveness (Left): " + setPlus(j.get(Identifications.INGREDIENT_EFFECTIVENESS_LEFT.getIngName()).getAsInt()) + "%"));
                run = true;
            }
            if (j.get(Identifications.INGREDIENT_EFFECTIVENESS_TOUCHING.getIngName()) != null && j.get(Identifications.INGREDIENT_EFFECTIVENESS_TOUCHING.getIngName()).getAsInt() != 0) {
                label.add(new JLabel("Ingredient Effectiveness (Touch): " + setPlus(j.get(Identifications.INGREDIENT_EFFECTIVENESS_TOUCHING.getIngName()).getAsInt()) + "%"));
                run = true;
            }
            if (j.get(Identifications.INGREDIENT_EFFECTIVENESS_NOT_TOUCHING.getIngName()) != null && j.get(Identifications.INGREDIENT_EFFECTIVENESS_NOT_TOUCHING.getIngName()).getAsInt() != 0) {
                label.add(new JLabel("Ingredient Effectiveness (Not Touch): " + setPlus(j.get(Identifications.INGREDIENT_EFFECTIVENESS_NOT_TOUCHING.getIngName()).getAsInt()) + "%"));
                run = true;
            }
            if (run) label.add(new JLabel(" "));
        }

        if (json.get(JsonKeys.IDENTIFICATION.getKey()) != null) {
            JsonObject j = json.get(JsonKeys.IDENTIFICATION.getKey()).getAsJsonObject();
            boolean run = false;

            for (int i = 0; ITEM_IDS.size() > i; ++i) {
                Identifications id = ITEM_IDS.get(i);
                if (id.getIngName() != null && id.getIngFieldPos() == JsonKeys.IDENTIFICATION && j.get(id.getIngName()) != null && j.get(id.getIngName()).getAsJsonObject() != null) {
                    JsonElement je = j.get(id.getIngName());
                    if (!je.isJsonObject()) {
                        label.add(new JLabel(id.getDisplayName() + " " + setPlus(je.getAsInt()) + id.getDisplaySp()));
                    } else {
                        label.add(new JLabel(setPlus(je.getAsJsonObject().get(JsonKeys.MIN.getKey()).getAsInt()) + id.getDisplaySp() + " " + id.getDisplayName() + " " + setPlus(je.getAsJsonObject().get(JsonKeys.MAX.getKey()).getAsInt()) + id.getDisplaySp()));
                    }
                    run = true;
                }
            }

            for (int i = 0; REVERSED_ITEM_IDS.size() > i; ++i) {
                Identifications id = REVERSED_ITEM_IDS.get(i);
                if (id.getIngName() != null && id.getIngFieldPos() == JsonKeys.IDENTIFICATION && j.get(id.getIngName()) != null && j.get(id.getIngName()).getAsJsonObject() != null) {
                    JsonElement je = j.get(id.getIngName());
                    if (!je.isJsonObject()) {
                        label.add(new JLabel(id.getDisplayName() + " " + setPlus(je.getAsInt()) + id.getDisplaySp()));
                    } else {
                        label.add(new JLabel(setPlus(je.getAsJsonObject().get(JsonKeys.MIN.getKey()).getAsInt()) + id.getDisplaySp() + " " + id.getDisplayName() + " " + setPlus(je.getAsJsonObject().get(JsonKeys.MAX.getKey()).getAsInt()) + id.getDisplaySp()));
                    }
                    run = true;
                }
            }

            if (run) label.add(new JLabel(" "));
        }

        if (json.get(JsonKeys.REQUIREMENTS.getKey()) != null && json.get(JsonKeys.REQUIREMENTS.getKey()).getAsJsonObject().get(JsonKeys.SKILLS.getKey()) != null) {
            JsonArray j = json.get(JsonKeys.REQUIREMENTS.getKey()).getAsJsonObject().get(JsonKeys.SKILLS.getKey()).getAsJsonArray();
            label.add(new JLabel("Can Use:"));
            for (int i = 0; j.size() > i; ++i) {
                label.add(new JLabel(j.get(i).getAsString().substring(0, 1).toUpperCase() + j.get(i).getAsString().substring(1)));
            }
            label.add(new JLabel(" "));
        }

        JButton_Custom l = null;
        if (!isCustom) {
            l = new JButton_Custom("How to Obtain (Not Perfect)");
            l.setBorderPainted(false);
            l.setOpaque(false);
            l.setBackground(Color.WHITE);
            l.setForeground(Color.BLUE);
            StringBuilder sb = new StringBuilder();
            sb.append("&fThis ingredient can be dropped by:<br>");

            String itemName = item.getName();
            JsonObject manual = ApiDataManager.getManager().howToObtainIng;
            int haveManualDrop = haveManualDrop(manual, itemName);
            if (haveManualDrop > 0) {
                if (haveManualDrop == 11) {
                    String s = "Any Loot Chests<br>" + "Level " + Math.max((lv - 4), 1) + " to " + (lv + 4) + "<br><br>";
                    sb.append(s);
                }
                if (manual.get(JsonValues.MERCHANT) != null && manual.get(JsonValues.MERCHANT).getAsJsonObject().get(itemName) != null) {
                    JsonObject jo = manual.get(JsonValues.MERCHANT).getAsJsonObject().get(itemName).getAsJsonObject();
                    if (jo.get(JsonKeys.NAME.getKey()) != null) {
                        String s = "Merchant: " + jo.get(JsonKeys.NAME.getKey()).getAsString() + "<br>";
                        sb.append(s);
                    }
                    if (jo.get(JsonKeys.POS.getKey()) != null) {
                        String s = "Location: " + jo.get(JsonKeys.POS.getKey()).getAsString() + "<br>";
                        sb.append(s);
                    }
                    if (jo.get(JsonKeys.PRICE.getKey()) != null) {
                        String s = "Price: " + jo.get(JsonKeys.PRICE.getKey()).getAsString() + "<br>";
                        sb.append(s);
                    }
                    sb.append("<br>");
                }
                setPosOnlyType(manual, sb, itemName, JsonValues.QUEST, "Quest: "); //Quest
                setPosOnlyType(manual, sb, itemName, JsonValues.RAID, "Raid Rewards: "); //Raid Rewards
                setPosOnlyType(manual, sb, itemName, JsonValues.WORLD_EVENT, "World Event: "); //World Event
                setPosOnlyType(manual, sb, itemName, JsonValues.LOOTRUN, "Lootrun End Rewards: "); //Lootrun
            }

            if (json.get(JsonKeys.DROPPED_BY.getKey()) != null && json.get(JsonKeys.DROPPED_BY.getKey()).isJsonArray()) {
                for (JsonElement je : json.get(JsonKeys.DROPPED_BY.getKey()).getAsJsonArray()) {
                    JsonObject j = je.getAsJsonObject();
                    if (j.get(JsonKeys.NAME.getKey()) != null) {
                        if (j.get(JsonKeys.NAME.getKey()).getAsString().equals(JsonValues.DUMMY)) continue;
                        String s = "Mob Name: " + j.get(JsonKeys.NAME.getKey()).getAsString() + "<br>";
                        sb.append(s);
                        if (j.get(JsonKeys.COORDS.getKey()) != null && j.get(JsonKeys.COORDS.getKey()).isJsonArray()) {
                            if (j.get(JsonKeys.COORDS.getKey()).getAsJsonArray().get(0).isJsonArray()) {
                                for (int i = 0; j.get(JsonKeys.COORDS.getKey()).getAsJsonArray().size() > i; ++i) {
                                    JsonArray ja = j.get(JsonKeys.COORDS.getKey()).getAsJsonArray().get(i).getAsJsonArray();
                                    String s1 = "Locate " + (i + 1) + ": " + ja.get(0).getAsInt() + ", " + ja.get(1).getAsInt() + ", " + ja.get(2).getAsInt() + " | Radius: " + (ja.get(3).getAsInt() / 2F) + "<br>";
                                    sb.append(s1);
                                    if (i == j.get(JsonKeys.COORDS.getKey()).getAsJsonArray().size() - 1) sb.append("<br>");
                                }
                            } else {
                                JsonArray ja = j.get(JsonKeys.COORDS.getKey()).getAsJsonArray();
                                String s1 = "Locate: " + ja.get(0).getAsInt() + ", " + ja.get(1).getAsInt() + ", " + ja.get(2).getAsInt() + " | Radius: " + (ja.get(3).getAsInt() / 2F) + "<br><br>";
                                sb.append(s1);
                            }
                        }
                    }
                }
            }

            if (manual.get(JsonValues.SPECIFIC) != null && manual.get(JsonValues.SPECIFIC).getAsJsonObject().get(itemName) != null) {
                for (JsonElement je : manual.get(JsonValues.SPECIFIC).getAsJsonObject().get(itemName).getAsJsonArray()) {
                    JsonObject jsp = je.getAsJsonObject();
                    if (jsp.get(JsonKeys.IS_MOBNAME.getKey()) == null || jsp.get(JsonKeys.IS_MOBNAME.getKey()).getAsBoolean()) sb.append("Mob Name: ");
                    sb.append(jsp.get(JsonKeys.NAME.getKey()).getAsString());
                    sb.append("<br>");
                    if (jsp.get(JsonKeys.POS.getKey()) != null) {
                        for (JsonElement je1 : jsp.get(JsonKeys.POS.getKey()).getAsJsonArray()) {
                            String s = "Locate: " + je1.getAsString() + "<br>";
                            sb.append(s);
                        }
                    }
                    sb.append("<br>");
                }
            }
            l.setToolTipText("<html>" + TreeCheckBox.fixesText(sb.substring(0, sb.toString().length() - 4)));
            l.addActionListener(new FullMessageAction());
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
