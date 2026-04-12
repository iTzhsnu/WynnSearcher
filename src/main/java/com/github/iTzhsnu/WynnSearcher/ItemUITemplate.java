package com.github.iTzhsnu.WynnSearcher;

import com.github.iTzhsnu.WynnSearcher.builder.skilltree.TreeCheckBox;
import com.github.iTzhsnu.WynnSearcher.general.ItemType;
import com.github.iTzhsnu.WynnSearcher.general.JsonKeys;
import com.github.iTzhsnu.WynnSearcher.general.JsonValues;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.List;

public class ItemUITemplate extends JPanel {
    private final JsonObject json;
    private final List<JLabel> label = new ArrayList<>();
    private final float totalValue;
    private final boolean isCustom;
    private int urlSize = 0;

    public static final Map<Integer, Identifications> ITEM_IDS = new HashMap<>(82, 2);

    public static final Map<Integer, Identifications> REVERSED_ITEM_IDS = new HashMap<>(8, 2) {{
        put(0, Identifications.PERCENT_1ST_SPELL_COST);
        put(1, Identifications.PERCENT_2ND_SPELL_COST);
        put(2, Identifications.PERCENT_3RD_SPELL_COST);
        put(3, Identifications.PERCENT_4TH_SPELL_COST);

        put(4, Identifications.RAW_1ST_SPELL_COST);
        put(5, Identifications.RAW_2ND_SPELL_COST);
        put(6, Identifications.RAW_3RD_SPELL_COST);
        put(7, Identifications.RAW_4TH_SPELL_COST);
    }};

    public static final Map<Integer, Identifications> DAMAGE_IDS = new HashMap<>(6, 2) {{
        put(0, Identifications.NEUTRAL_DAMAGE);
        put(1, Identifications.EARTH_DAMAGE);
        put(2, Identifications.THUNDER_DAMAGE);
        put(3, Identifications.WATER_DAMAGE);
        put(4, Identifications.FIRE_DAMAGE);
        put(5, Identifications.AIR_DAMAGE);
    }};

    public static final Map<Integer, Identifications> DEFENSE_IDS = new HashMap<>(6, 2) {{
        put(0, Identifications.HEALTH);
        put(1, Identifications.EARTH_DEFENSE);
        put(2, Identifications.THUNDER_DEFENSE);
        put(3, Identifications.WATER_DEFENSE);
        put(4, Identifications.FIRE_DEFENSE);
        put(5, Identifications.AIR_DEFENSE);
    }};

    public static final Map<Integer, Identifications> SP_REQUESTS = new HashMap<>(5, 2) {{
        put(0, Identifications.STRENGTH_REQ);
        put(1, Identifications.DEXTERITY_REQ);
        put(2, Identifications.INTELLIGENCE_REQ);
        put(3, Identifications.DEFENSE_REQ);
        put(4, Identifications.AGILITY_REQ);
    }};

    public static final Map<Integer, Identifications> INGREDIENT_EFFECTIVENESS = new HashMap<>(6, 2) {{
        put(0, Identifications.INGREDIENT_EFFECTIVENESS_ABOVE);
        put(1, Identifications.INGREDIENT_EFFECTIVENESS_UNDER);
        put(2, Identifications.INGREDIENT_EFFECTIVENESS_LEFT);
        put(3, Identifications.INGREDIENT_EFFECTIVENESS_RIGHT);
        put(4, Identifications.INGREDIENT_EFFECTIVENESS_NOT_TOUCHING);
        put(5, Identifications.INGREDIENT_EFFECTIVENESS_TOUCHING);
    }};

    public ItemUITemplate(JsonObject json, ItemType type, JPanel previous, JPanel above, int uiWidth, float totalValue, boolean isCustom, JsonObject how_to_obtain) {
        this.json = json;
        this.totalValue = totalValue;
        this.isCustom = isCustom;

        switch (type) {
            case ITEM:
                //if (!isCustom) urlSize = 56;
                setItemDisplay(how_to_obtain);
                break;
            case INGREDIENT:
                //if (!isCustom) urlSize = 32;
                setIngDisplay(how_to_obtain);
                break;
            case OTHER:
                //if (!isCustom && !json.get("type").getAsString().equals("charm") && !json.get("type").getAsString().equals("tome")) urlSize = 32;
                setOtherDisplay(how_to_obtain);
                break;
            case ASPECT:
                setAspectDisplay(4);
                break;
        }

        if (previous != null) {
            Rectangle x = previous.getBounds();
            boolean down = x.x + x.width + 10 > uiWidth - 250;

            if (down) {
                Rectangle y = above.getBounds();
                setBounds(10, y.y + y.height + 10, 250, (label.size() + 1) * 16 + urlSize);
            } else {
                if (above != null) {
                    Rectangle y = above.getBounds();
                    setBounds(10 + x.x + x.width, y.y + y.height + 10, 250, (label.size() + 1) * 16 + urlSize);
                } else {
                    setBounds(10 + x.x + x.width, 10, 250, (label.size() + 1) * 16 + urlSize);
                }
            }
        } else {
            setBounds(10, 10, 250, (label.size() + 1) * 16 + urlSize);
        }

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        if (type == ItemType.INGREDIENT && json.get(Identifications.RARITY.getIngName()) != null) {
            try {
                switch (json.get(Identifications.RARITY.getIngName()).getAsString()) {
                    case JsonValues.STAR_1:
                        setBackground(new Color(252, 242, 99));
                        break;
                    case JsonValues.STAR_2:
                        setBackground(new Color(255, 168, 211)); //OLD COLOR 220, 107, 154
                        break;
                    case JsonValues.STAR_3:
                        setBackground(new Color(135, 206, 250));
                        break;
                    default:
                        setBackground(new Color(230, 230, 230));
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("old item found.");
            }
        } else if (json.get(Identifications.RARITY.getItemName()) != null) {
            switch (json.get(Identifications.RARITY.getItemName()).getAsString()) {
                case JsonValues.UNIQUE:
                    setBackground(new Color(252, 242, 99));
                    break;
                case JsonValues.RARE:
                    setBackground(new Color(255, 168, 211)); //OLD COLOR 220, 107, 154
                    break;
                case JsonValues.LEGENDARY:
                    setBackground(new Color(135, 206, 250));
                    break;
                case JsonValues.FABLED:
                    setBackground(new Color(220, 107, 154)); //OLD COLOR 255 81 81
                    break;
                case JsonValues.MYTHIC:
                    setBackground(new Color(145, 93, 163));
                    break;
                case "set": // This rarity was discontinued.
                    setBackground(new Color(121, 192, 110)); //OLD COLOR 85 255 85
                    break;
                case JsonValues.CRAFTED:
                    setBackground(new Color(0, 175, 204));
                    break;
                default:
                    setBackground(new Color(230, 230, 230));
                    break;
            }
        }
        setVisible(true);
    }

    public void setItemDisplay(JsonObject how_to_obtain) {
        //JButton dataButton = new JButton("Open Wynndata");
        //dataButton.setBorderPainted(false);
        //dataButton.setOpaque(false);
        //dataButton.setBackground(Color.WHITE);
        //dataButton.setForeground(Color.BLUE);
        //dataButton.addActionListener(new OpenURLAction());

        //JButton builderButton = new JButton("Open Wynnbuilder");
        //builderButton.setBorderPainted(false);
        //builderButton.setOpaque(false);
        //builderButton.setBackground(Color.WHITE);
        //builderButton.setForeground(Color.BLUE);
        //builderButton.addActionListener(new OpenURLAction());

        String itemName = "";

        if (json.get(JsonKeys.NAME.getKey()) != null) {
            label.add(new JLabel(json.get(JsonKeys.NAME.getKey()).getAsString()));
            itemName = json.get(JsonKeys.NAME.getKey()).getAsString();
        //    String name = itemName.replaceAll(" ", "%20");
        //    dataButton.setToolTipText("https://www.wynndata.tk/i/" + name);
        //    builderButton.setToolTipText("https://hppeng-wynn.github.io/item/#" + name);
        }

        if (json.get(JsonKeys.SUBTYPE.getKey()) != null) {
            label.add(new JLabel("Type: " + json.get(JsonKeys.SUBTYPE.getKey()).getAsString().substring(0, 1).toUpperCase() + json.get(JsonKeys.SUBTYPE.getKey()).getAsString().substring(1)));
        }
        //if (json.get("weaponType") != null) {
        //    label.add(new JLabel("Type: " + json.get("weaponType").getAsString().substring(0, 1).toUpperCase() + json.get("weaponType").getAsString().substring(1)));
        //} else if (json.get("armourType") != null) {
        //    label.add(new JLabel("Type: " + json.get("armourType").getAsString().substring(0, 1).toUpperCase() + json.get("armourType").getAsString().substring(1)));
        //} else if (json.get("accessoryType") != null) {
        //    label.add(new JLabel("Type: " + json.get("accessoryType").getAsString().substring(0, 1).toUpperCase() + json.get("accessoryType").getAsString().substring(1)));
        //}

        if (json.get(Identifications.ATTACK_SPEED.getItemName()) != null) {
            String s = json.get(Identifications.ATTACK_SPEED.getItemName()).getAsString();
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

        if (json.get(JsonKeys.BASE.getKey()) != null) {
            JsonObject j = json.get(JsonKeys.BASE.getKey()).getAsJsonObject();
            if (j.get(Identifications.HEALTH.getItemName()) != null) {
                JsonElement j2 = j.get(Identifications.HEALTH.getItemName());
                if (json.get(Identifications.RARITY.getItemName()) != null && json.get(Identifications.RARITY.getItemName()).getAsString().equals(JsonValues.CRAFTED)) {
                    label.add(new JLabel("Health: " + j2.getAsJsonObject().get(JsonKeys.MIN.getKey()).getAsInt() + "-" + j2.getAsJsonObject().get(JsonKeys.MAX.getKey()).getAsInt()));
                } else if (j.get(Identifications.HEALTH.getItemName()).getAsInt() != 0) {
                    label.add(new JLabel("Health: " + setPlus(j2.getAsInt())));
                }
            }

            if (j.get(Identifications.EARTH_DEFENSE.getItemName()) != null && j.get(Identifications.EARTH_DEFENSE.getItemName()).getAsInt() != 0) {
                label.add(new JLabel("Earth Defense: " + setPlus(j.get(Identifications.EARTH_DEFENSE.getItemName()).getAsInt())));
            }

            if (j.get(Identifications.THUNDER_DEFENSE.getItemName()) != null && j.get(Identifications.THUNDER_DEFENSE.getItemName()).getAsInt() != 0) {
                label.add(new JLabel("Thunder Defense: " + setPlus(j.get(Identifications.THUNDER_DEFENSE.getItemName()).getAsInt())));
            }

            if (j.get(Identifications.WATER_DEFENSE.getItemName()) != null && j.get(Identifications.WATER_DEFENSE.getItemName()).getAsInt() != 0) {
                label.add(new JLabel("Water Defense: " + setPlus(j.get(Identifications.WATER_DEFENSE.getItemName()).getAsInt())));
            }

            if (j.get(Identifications.FIRE_DEFENSE.getItemName()) != null && j.get(Identifications.FIRE_DEFENSE.getItemName()).getAsInt() != 0) {
                label.add(new JLabel("Fire Defense: " + setPlus(j.get(Identifications.FIRE_DEFENSE.getItemName()).getAsInt())));
            }

            if (j.get(Identifications.AIR_DEFENSE.getItemName()) != null && j.get(Identifications.AIR_DEFENSE.getItemName()).getAsInt() != 0) {
                label.add(new JLabel("Air Defense: " + setPlus(j.get(Identifications.AIR_DEFENSE.getItemName()).getAsInt())));
            }

            for (int i = 0; 5 >= i; ++i) { //Damages
                Identifications id = DAMAGE_IDS.get(i);
                if (j.get(id.getItemName()) != null) {
                    JsonObject j2 = j.get(id.getItemName()).getAsJsonObject();
                    if (json.get(Identifications.RARITY.getItemName()) != null && json.get(Identifications.RARITY.getItemName()).getAsString().equals(JsonValues.CRAFTED)) {
                        label.add(new JLabel(id.getDisplayName() + ": " + j2.get(JsonKeys.MIN.getKey()).getAsString() + " to " + j2.getAsJsonObject().get(JsonKeys.MAX.getKey()).getAsString()));
                    } else {
                        label.add(new JLabel(id.getDisplayName() + ": " + j2.get(JsonKeys.MIN.getKey()).getAsInt() + "-" + j2.getAsJsonObject().get(JsonKeys.MAX.getKey()).getAsInt()));
                    }
                }
            }
        }

        label.add(new JLabel(" "));

        int lv = 0;
        if (json.get(JsonKeys.REQUIREMENTS.getKey()) != null) {
            JsonObject j = json.get(JsonKeys.REQUIREMENTS.getKey()).getAsJsonObject();
            if (j.get(Identifications.LEVEL.getItemName()) != null) {
                JsonElement j2 = j.get(Identifications.LEVEL.getItemName());
                if (json.get(Identifications.RARITY.getItemName()) != null && json.get(Identifications.RARITY.getItemName()).getAsString().equals(JsonValues.CRAFTED)) {
                    label.add(new JLabel("Combat Lv. Min: " + j2.getAsJsonObject().get(JsonKeys.MIN.getKey()).getAsInt() + "-" + j2.getAsJsonObject().get(JsonKeys.MAX.getKey()).getAsInt()));
                } else {
                    label.add(new JLabel("Combat Lv. Min: " + j2.getAsInt()));
                    lv = j2.getAsInt();
                }
            }

            if (j.get(Identifications.STRENGTH_REQ.getItemName()) != null && j.get(Identifications.STRENGTH_REQ.getItemName()).getAsInt() != 0) {
                label.add(new JLabel("Strength Req: " + j.get(Identifications.STRENGTH_REQ.getItemName()).getAsInt()));
            }

            if (j.get(Identifications.DEXTERITY_REQ.getItemName()) != null && j.get(Identifications.DEXTERITY_REQ.getItemName()).getAsInt() != 0) {
                label.add(new JLabel("Dexterity Req: " + j.get(Identifications.DEXTERITY_REQ.getItemName()).getAsInt()));
            }

            if (j.get(Identifications.INTELLIGENCE_REQ.getItemName()) != null && j.get(Identifications.INTELLIGENCE_REQ.getItemName()).getAsInt() != 0) {
                label.add(new JLabel("Intelligence Req: " + j.get(Identifications.INTELLIGENCE_REQ.getItemName()).getAsInt()));
            }

            if (j.get(Identifications.DEFENSE_REQ.getItemName()) != null && j.get(Identifications.DEFENSE_REQ.getItemName()).getAsInt() != 0) {
                label.add(new JLabel("Defense Req: " + j.get(Identifications.DEFENSE_REQ.getItemName()).getAsInt()));
            }

            //OLD DEFENSE REQ
            if (j.get("defense") != null && j.get("defense").getAsInt() != 0) {
                label.add(new JLabel("Defense Req: " + j.get("defense").getAsInt()));
            }

            if (j.get(Identifications.AGILITY_REQ.getItemName()) != null && j.get(Identifications.AGILITY_REQ.getItemName()).getAsInt() != 0) {
                label.add(new JLabel("Agility Req: " + j.get(Identifications.AGILITY_REQ.getItemName()).getAsInt()));
            }

            if (j.get(Identifications.QUEST_REQ.getItemName()) != null) {
                label.add(new JLabel("Quest Req: " + j.get(Identifications.QUEST_REQ.getItemName()).getAsString()));
            }
        }

        label.add(new JLabel(" "));

        for (int i = 0; ITEM_IDS.size() > i; ++i) {
            Identifications id = ITEM_IDS.get(i);
            if (json.get(id.getItemFieldPos().getKey()) != null && json.get(id.getItemFieldPos().getKey()).getAsJsonObject().get(id.getItemName()) != null) {
                JsonElement j = json.get(id.getItemFieldPos().getKey()).getAsJsonObject().get(id.getItemName());
                if (!j.isJsonObject()) { //Constant Value
                    label.add(new JLabel(id.getDisplayName() + " "+ setPlus(j.getAsInt()) + id.getDisplaySp()));
                } else if (json.get(JsonKeys.IDENTIFIED.getKey()) != null && json.get(JsonKeys.IDENTIFIED.getKey()).getAsBoolean()) { //Identified Items
                    String minOrMax = JsonKeys.MAX.getKey();
                    if (j.getAsJsonObject().get(JsonKeys.MAX.getKey()).getAsInt() < 0) minOrMax = JsonKeys.MIN.getKey();
                    label.add(new JLabel(id.getDisplayName() + " " + setPlus(SearchUI.getBaseID(j.getAsJsonObject().get(minOrMax).getAsInt())) + id.getDisplaySp()));
                } else if (id.isItemVariable() || json.get(Identifications.RARITY.getItemName()).getAsString().equals(JsonValues.CRAFTED)) { //Crafted Items or Variable ID
                    label.add(new JLabel(setPlus(j.getAsJsonObject().get(JsonKeys.MIN.getKey()).getAsInt()) + id.getDisplaySp() + " " + id.getDisplayName() + " " + setPlus(j.getAsJsonObject().get(JsonKeys.MAX.getKey()).getAsInt()) + id.getDisplaySp()));
                }
            }
        }

        for (int i = 0; REVERSED_ITEM_IDS.size() > i; ++i) {
            Identifications id = REVERSED_ITEM_IDS.get(i);
            if (json.get(id.getItemFieldPos().getKey()) != null && json.get(id.getItemFieldPos().getKey()).getAsJsonObject().get(id.getItemName()) != null) {
                JsonElement j = json.get(id.getItemFieldPos().getKey()).getAsJsonObject().get(id.getItemName());
                if (!j.isJsonObject()) { //Constant Value
                    label.add(new JLabel(id.getDisplayName() + " "+ setPlus(j.getAsInt()) + id.getDisplaySp()));
                } else if (json.get(JsonKeys.IDENTIFIED.getKey()) != null && json.get(JsonKeys.IDENTIFIED.getKey()).getAsBoolean()) { //Identified Items
                    if (j.getAsJsonObject().get(JsonKeys.RAW.getKey()) == null) {
                        label.add(new JLabel(id.getDisplayName() + " " + setPlus(SearchUI.getBaseID(j.getAsJsonObject().get(JsonKeys.MAX.getKey()).getAsInt())) + id.getDisplaySp()));
                    } else {
                        label.add(new JLabel(id.getDisplayName() + " " + setPlus(j.getAsJsonObject().get(JsonKeys.RAW.getKey()).getAsInt()) + id.getDisplaySp()));
                    }
                } else {
                    if (json.get(Identifications.RARITY.getItemName()) != null && json.get(Identifications.RARITY.getItemName()).getAsString().equals(JsonValues.CRAFTED)) { //Crafted Items
                        label.add(new JLabel(setPlus(j.getAsJsonObject().get(JsonKeys.MIN.getKey()).getAsInt()) + id.getDisplaySp() + " " + id.getDisplayName() + " " + setPlus(j.getAsJsonObject().get(JsonKeys.MAX.getKey()).getAsInt()) + id.getDisplaySp()));
                    } else { //Normal Items
                        if (j.getAsJsonObject().get(JsonKeys.RAW.getKey()) == null) {
                            int base = SearchUI.getBaseID(j.getAsJsonObject().get(JsonKeys.MAX.getKey()).getAsInt());
                            label.add(new JLabel(getReversedMax(base) + id.getDisplaySp() + " " + id.getDisplayName() + " " + getReversedMin(base) + id.getDisplaySp()));
                        } else {
                            label.add(new JLabel(setPlus(j.getAsJsonObject().get(JsonKeys.MIN.getKey()).getAsInt()) + id.getDisplaySp() + " " + id.getDisplayName() + " " + setPlus(j.getAsJsonObject().get(JsonKeys.MAX.getKey()).getAsInt()) + id.getDisplaySp()));
                        }
                    }
                }
            }
        }

        label.add(new JLabel(" "));
        if (json.get(Identifications.POWDER_SLOTS.getItemName()) != null && json.get(Identifications.POWDER_SLOTS.getItemName()).getAsInt() != 0) {
            label.add(new JLabel("Powder Slots: " + json.get(Identifications.POWDER_SLOTS.getItemName()).getAsInt()));
        }

        if (json.get(Identifications.MAJOR_IDS.getItemName()) != null) {
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

        if (json.get(Identifications.RARITY.getItemName()) != null) {
            label.add(new JLabel("Rarity: " + json.get(Identifications.RARITY.getItemName()).getAsString().substring(0, 1).toUpperCase() + json.get(Identifications.RARITY.getItemName()).getAsString().substring(1)));
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
            if (SearchUI.getSetsJson().get(setName) != null) {
                //label.add(new JLabel(" "));
                sets_label = new JButton_Custom("Set Bonuses");
                sets_label.setBorderPainted(false);
                sets_label.setOpaque(false);
                sets_label.setBackground(Color.WHITE);
                sets_label.setForeground(Color.BLUE);

                JsonObject setJ = SearchUI.getSetsJson().get(setName).getAsJsonObject();
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

                l.setToolTipText(getHowToObtainText(itemName, json, lv, how_to_obtain));

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
        //    label.add(new JLabel("External Links"));
        //    add(label.get(label.size() - 2));
            add(label.get(label.size() - 1));

        //    add(dataButton);
        //    add(builderButton);

            JLabel sortValue = new JLabel("Sort Value: " + totalValue);
            sortValue.setForeground(Color.DARK_GRAY);
            add(sortValue);
        }
    }

    public void setIngDisplay(JsonObject manual) {
        //JButton dataButton = new JButton("Wynndata Link");
        //dataButton.setBorderPainted(false);
        //dataButton.setOpaque(false);
        //dataButton.setBackground(Color.WHITE);
        //dataButton.setForeground(Color.BLUE);
        //dataButton.addActionListener(new OpenURLAction());

        String itemName = "";
        if (json.get(JsonKeys.NAME.getKey()) != null) {
            itemName = json.get(JsonKeys.NAME.getKey()).getAsString();
            if (json.get(JsonKeys.DISPLAY_NAME.getKey()) != null) {
                label.add(new JLabel(json.get(JsonKeys.DISPLAY_NAME.getKey()).getAsString()));
            } else {
                label.add(new JLabel(itemName));
            }
            //dataButton.setToolTipText("https://www.wynndata.tk/i/" + itemName.replaceAll(" ", "%20"));
        }

        if (json.get(Identifications.RARITY.getIngName()) != null) {
            int star = 0;
            switch (json.get(Identifications.RARITY.getIngName()).getAsString()) {
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

        int lv = 0;
        if (json.get(Identifications.LEVEL.getIngFieldPos().getKey()) != null && json.get(Identifications.LEVEL.getIngFieldPos().getKey()).getAsJsonObject().get(Identifications.LEVEL.getIngName()) != null) {
            lv = json.get(Identifications.LEVEL.getIngFieldPos().getKey()).getAsJsonObject().get(Identifications.LEVEL.getIngName()).getAsInt();
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

                //JsonObject j = json.get("droppedBy").getAsJsonObject();
                //for (Map.Entry<String, JsonElement> entry : json.get("droppedBy").getAsJsonObject().entrySet()) {
                //    if (j.get(entry.getKey()).isJsonArray()) {
                //        if (entry.getKey().equals("Ingredient Dummy")) continue;
                //        String s = "Mob Name: " + entry.getKey() + "<br>";
                //        sb.append(s);
                //        if (j.get(entry.getKey()).getAsJsonArray().get(0).isJsonArray()) {
                //            for (int i = 0; j.get(entry.getKey()).getAsJsonArray().size() > i; ++i) {
                //                JsonArray ja = j.get(entry.getKey()).getAsJsonArray().get(i).getAsJsonArray();
                //                String s1 = "Locate " + (i + 1) + ": " + ja.get(0).getAsInt() + ", " + ja.get(1).getAsInt() + ", " + ja.get(2).getAsInt() + " | Radius: " + (ja.get(3).getAsInt() / 2F) + "<br>";
                //                sb.append(s1);
                //                if (i == j.get(entry.getKey()).getAsJsonArray().size() - 1) sb.append("<br>");
                //            }
                //        } else {
                //            JsonArray ja = j.get(entry.getKey()).getAsJsonArray();
                //            String s1 = "Locate: " + ja.get(0).getAsInt() + ", " + ja.get(1).getAsInt() + ", " + ja.get(2).getAsInt() + " | Radius: " + (ja.get(3).getAsInt() / 2F) + "<br><br>";
                //            sb.append(s1);
                //        }
                //    } else {
                //        if (entry.getKey().equals("Ingredient Dummy")) continue;
                //        String s = "Mob Name: " + entry.getKey() + "<br><br>";
                //         sb.append(s);
                //    }
                //}
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

        //label.add(new JLabel("External Links"));
        //add(label.get(label.size() - 1));
        //add(dataButton);

        JLabel sortValue = new JLabel("Sort Value: " + totalValue);
        sortValue.setForeground(Color.DARK_GRAY);
        add(sortValue);
    }

    public void setOtherDisplay(JsonObject how_to_obtain) {
        String type = json.get(JsonKeys.TYPE.getKey()).getAsString();
        //JButton dataButton = new JButton("Open Wynndata");
        //dataButton.setBorderPainted(false);
        //dataButton.setOpaque(false);
        //dataButton.setBackground(Color.WHITE);
        //dataButton.setForeground(Color.BLUE);
        //dataButton.addActionListener(new OpenURLAction());

        String itemName = "";

        if (json.get(JsonKeys.NAME.getKey()) != null) {
            itemName = json.get(JsonKeys.NAME.getKey()).getAsString();
            label.add(new JLabel(json.get(JsonKeys.NAME.getKey()).getAsString()));
            //String s = json.get("name").getAsString().replaceAll(" ", "%20");
            //if (type.equals("material")) {
            //    s = "Refined%20" + json.get("name").getAsString().replaceAll(" ", "%20");
            //}
            //dataButton.setToolTipText("https://www.wynndata.tk/i/" + s);
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

        //if (json.get("toolType") != null) {
        //    label.add(new JLabel("Tool Type: " + json.get("toolType").getAsString().substring(0, 1).toUpperCase() + json.get("toolType").getAsString().substring(1)));
        //}

        //if (type.equals(JsonValues.MATERIAL) && json.get("tier") != null) {
        //    label.add(new JLabel("Star: " + json.get("tier").getAsInt()));
        //}

        int lv = 0;
        if (json.get(Identifications.LEVEL.getItemFieldPos().getKey()) != null && json.get(Identifications.LEVEL.getItemFieldPos().getKey()).getAsJsonObject().get(Identifications.LEVEL.getItemName()) != null) {
            int i = json.get(JsonKeys.REQUIREMENTS.getKey()).getAsJsonObject().get(Identifications.LEVEL.getItemName()).getAsInt();
            lv = i;
            switch (type) {
                case JsonValues.TOME:
                case JsonValues.CHARM:
                    label.add(new JLabel("Combat Lv. Min: " + i));
                    break;
                case JsonValues.TOOL:
                    switch (json.get(JsonKeys.SUBTYPE.getKey()).getAsString()) {
                        case JsonValues.PICKAXE:
                            label.add(new JLabel("Mining Lv. Min: " + i));
                            break;
                        case JsonValues.AXE:
                            label.add(new JLabel("Woodcutting Lv. Min: " + i));
                            break;
                        case JsonValues.SCYTHE:
                            label.add(new JLabel("Farming Lv. Min: " + i));
                            break;
                        case JsonValues.ROD:
                            label.add(new JLabel("Fishing Lv. Min: " + i));
                            break;
                    }
                    break;
                case JsonValues.MATERIAL:
                    label.add(new JLabel("Lv. Min: " + i));
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

        //if (json.get("craftable") != null) { //Material
        //    label.add(new JLabel("Use this material to craft"));
        //    for (JsonElement j : json.get("craftable").getAsJsonArray()) {
        //        label.add(new JLabel(j.getAsString()));
        //    }
        //}

        //DELETED
        //if (type.equals("tome") && json.get("base") != null) { //Tome
        //    JsonObject j = json.get("base").getAsJsonObject();
        //    if (j.get("damageToMobs") != null) label.add(new JLabel("+" + j.get("damageToMobs").getAsInt() + "% Damage to Mobs"));
        //    if (j.get("defenceToMobs") != null) label.add(new JLabel("+" + j.get("defenceToMobs").getAsInt() + "% Mob Damage Resistance"));
        //    if (j.get("dungeonXP") != null) label.add(new JLabel("+" + j.get("dungeonXP").getAsInt() + "% Dungeon XP"));
        //    if (j.get("gatheringXP") != null) label.add(new JLabel("+" + j.get("gatheringXP").getAsInt() + "% Gathering XP"));
        //    if (j.get("slayingXP") != null) label.add(new JLabel("+" + j.get("slayingXP").getAsInt() + "% Slaying XP"));
        //    label.add(new JLabel(" "));
        //}

        // level range removed from api?
        if (type.equals(JsonValues.CHARM) && json.get(Identifications.LEVELED_XP_BONUS.getItemFieldPos().getKey()) != null && json.get(JsonKeys.REQUIREMENTS.getKey()) != null) {
            JsonObject j = json.get(Identifications.LEVELED_XP_BONUS.getItemFieldPos().getKey()).getAsJsonObject();
            //int min = json.get(JsonKeys.REQUIREMENTS.getKey()).getAsJsonObject().get("levelRange").getAsJsonObject().get("min").getAsInt();
            //int max = json.get(JsonKeys.REQUIREMENTS.getKey()).getAsJsonObject().get("levelRange").getAsJsonObject().get("max").getAsInt();
            int max = lv + 20;

            if (j.get(Identifications.LEVELED_XP_BONUS.getItemName()) != null) label.add(new JLabel(setPlus(j.get(Identifications.LEVELED_XP_BONUS.getItemName()).getAsJsonObject().get("min").getAsInt()) + "% XP from Lv." + lv + "-" + max + " contents " + setPlus(j.get(Identifications.LEVELED_XP_BONUS.getItemName()).getAsJsonObject().get("max").getAsInt()) + "%"));
            if (j.get(Identifications.DAMAGE_FROM_MOBS.getItemName()) != null) label.add(new JLabel(setPlus(j.get(Identifications.DAMAGE_FROM_MOBS.getItemName()).getAsJsonObject().get("min").getAsInt()) + "% Damage taken from mobs " + setPlus(j.get(Identifications.DAMAGE_FROM_MOBS.getItemName()).getAsJsonObject().get("max").getAsInt()) + "%"));
            if (j.get(Identifications.LEVELED_LOOT_BONUS.getItemName()) != null) label.add(new JLabel(setPlus(j.get(Identifications.LEVELED_LOOT_BONUS.getItemName()).getAsJsonObject().get("min").getAsInt()) + "% Loot from Lv." + lv + "-" + max + " contents " + setPlus(j.get(Identifications.LEVELED_LOOT_BONUS.getItemName()).getAsJsonObject().get("max").getAsInt()) + "%"));
        }

        if (type.equals(JsonValues.TOME) || type.equals(JsonValues.CHARM)) { //IDs
            for (int i = 0; ITEM_IDS.size() > i; ++i) {
                Identifications id = ITEM_IDS.get(i);
                if (json.get(id.getItemFieldPos().getKey()) != null && json.get(id.getItemFieldPos().getKey()).getAsJsonObject().get(id.getItemName()) != null) {
                    JsonElement j = json.get(id.getItemFieldPos().getKey()).getAsJsonObject().get(id.getItemName());
                    if (!j.isJsonObject()) {
                        label.add(new JLabel(id.getDisplayName() + " "+ setPlus(j.getAsInt()) + id.getDisplaySp()));
                    } else if (json.get(JsonKeys.IDENTIFIED.getKey()) != null && json.get(JsonKeys.IDENTIFIED.getKey()).getAsBoolean()) {
                        String minOrMax = JsonKeys.MAX.getKey();
                        if (j.getAsJsonObject().get(JsonKeys.MAX.getKey()).getAsInt() < 0) minOrMax = JsonKeys.MIN.getKey();
                        label.add(new JLabel(id.getDisplayName() + " " + setPlus(SearchUI.getBaseID(j.getAsJsonObject().get(minOrMax).getAsInt())) + id.getDisplaySp()));
                    } else if (id.isItemVariable()) {
                        label.add(new JLabel(setPlus(j.getAsJsonObject().get(JsonKeys.MIN.getKey()).getAsInt()) + id.getDisplaySp() + " " + id.getDisplayName() + " " + setPlus(j.getAsJsonObject().get(JsonKeys.MAX.getKey()).getAsInt()) + id.getDisplaySp()));
                    }
                }
            }

            for (int i = 0; REVERSED_ITEM_IDS.size() > i; ++i) {
                Identifications id = REVERSED_ITEM_IDS.get(i);
                if (json.get(id.getItemFieldPos().getKey()) != null && json.get(id.getItemFieldPos().getKey()).getAsJsonObject().get(id.getItemName()) != null) {
                    JsonElement j = json.get(id.getItemFieldPos().getKey()).getAsJsonObject().get(id.getItemName());
                    if (!j.isJsonObject()) {
                        label.add(new JLabel(id.getDisplayName() + " "+ setPlus(j.getAsInt()) + id.getDisplaySp()));
                    } else if (json.get(JsonKeys.IDENTIFIED.getKey()) != null && json.get(JsonKeys.IDENTIFIED.getKey()).getAsBoolean()) {
                        if (j.getAsJsonObject().get(JsonKeys.RAW.getKey()) == null) {
                            label.add(new JLabel(id.getDisplayName() + " " + setPlus(SearchUI.getBaseID(j.getAsJsonObject().get(JsonKeys.MAX.getKey()).getAsInt())) + id.getDisplaySp()));
                        } else {
                            label.add(new JLabel(id.getDisplayName() + " " + setPlus(j.getAsJsonObject().get(JsonKeys.RAW.getKey()).getAsInt()) + id.getDisplaySp()));
                        }
                    } else {
                        if (j.getAsJsonObject().get(JsonKeys.RAW.getKey()) == null) {
                            int base = SearchUI.getBaseID(j.getAsJsonObject().get(JsonKeys.MAX.getKey()).getAsInt());
                            label.add(new JLabel(getReversedMax(base) + id.getDisplaySp() + " " + id.getDisplayName() + " " + getReversedMin(base) + id.getDisplaySp()));
                        } else {
                            label.add(new JLabel(setPlus(j.getAsJsonObject().get(JsonKeys.MIN.getKey()).getAsInt()) + id.getDisplaySp() + " " + id.getDisplayName() + " " + setPlus(j.getAsJsonObject().get(JsonKeys.MAX.getKey()).getAsInt()) + id.getDisplaySp()));
                        }
                    }
                }
            }
        }

        label.add(new JLabel(" "));

        if (!type.equals(JsonValues.MATERIAL) && !type.equals(JsonValues.TOOL) && json.get(Identifications.RARITY.getItemName()) != null) {
            label.add(new JLabel("Rarity: " + json.get(Identifications.RARITY.getItemName()).getAsString().substring(0, 1).toUpperCase() + json.get(Identifications.RARITY.getItemName()).getAsString().substring(1)));
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

                l.setToolTipText(getHowToObtainText(itemName, json, lv, how_to_obtain));

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

        //if (!type.equals("tome") && !type.equals("charm")) {
        //    label.add(new JLabel("External Links"));
        //    add(label.get(label.size() - 1));
        //    add(dataButton);
        //}

        JLabel sortValue = new JLabel("Sort Value: " + totalValue);
        sortValue.setForeground(Color.DARK_GRAY);
        add(sortValue);
    }

    public void setAspectDisplay(int tier) {
        int maxTier = 4;
        if (tier > 3) {
            tier = 4;
            if (json.get(Identifications.RARITY.getItemName()) != null && !json.get(Identifications.RARITY.getItemName()).getAsString().equals("legendary")) {
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

    public void fixesSize(String name) {
        switch (name) {
            case "Paladin's Embodiment of Undying Determination":
            case "Aspect of Overflowing Hope":
            case "Aspect of Unquenching Flames":
            case "Shadestepper's Embodiment of Unseen Execution":
            case "Aspect of Sleight-Of-Hand":
            case "Light Bender's Embodiment of Celestial Brilliance":
            case "Aspect of Undercrank":
            case "Ritualist's Embodiment of the Ancestral Avatar":
            case "Acolyte's Embodiment of Unwavering Adherence":
            case "Aspect of Motivation":
            case "Aspect of the Channeler":
            case "Aspect of Exsanguination":
                urlSize += 16;
                break;
            case "Fallen's Embodiment of Blind Fury":
            case "Battle Monk's Embodiment of Complete Synchrony":
            case "Aspect of Deafening Echoes":
            case "Trickster's Embodiment of the Ultimate Show":
            case "Arcanist's Embodiment of Total Obliteration":
            case "Aspect of Fatal Fulguration":
            case "Trapper's Embodiment of Persistence Predation":
            case "Sharpshooter's Embodiment of Laser Precision":
            case "Summoner's Embodiment of the Omnipotent Overseer":
            case "Aspect of Lashing Fire":
            case "Aspect of Stances":
                urlSize += 32;
                break;
        }
    }

    public static void setMerchant(JsonObject manual, StringBuilder sb, String itemName, String path) {
        if (manual.get(path) != null && manual.get(path).getAsJsonObject().get(itemName) != null) { //Merchant
            JsonObject merchant = manual.get(path).getAsJsonObject().get(itemName).getAsJsonObject();
            boolean isEmpty = true;
            if (merchant.get(JsonKeys.NAME.getKey()) != null) {
                String s = "Merchant: " + merchant.get(JsonKeys.NAME.getKey()).getAsString() + "<br>";
                sb.append(s);
                isEmpty = false;
            }
            if (merchant.get(JsonKeys.POS.getKey()) != null) {
                String s = "Locate: " + merchant.get(JsonKeys.POS.getKey()).getAsString() + "<br>";
                sb.append(s);
                isEmpty = false;
            }
            if (merchant.get(JsonKeys.PRICE.getKey()) != null) {
                String s = "Price: " + merchant.get(JsonKeys.PRICE.getKey()).getAsString() + "<br>";
                sb.append(s);
                isEmpty = false;
            }

            if (isEmpty) sb.append("Merchant");

            sb.append("<br>");
        }
    }

    public static void setPosOnlyType(JsonObject manual, StringBuilder sb, String itemName, String path, String description) {
        if (manual.get(path) != null && manual.get(path).getAsJsonObject().get(itemName) != null) { //Other
            String s = description + manual.get(path).getAsJsonObject().get(itemName).getAsJsonObject().get(JsonKeys.POS.getKey()).getAsString() + "<br><br>";
            sb.append(s);
        }
    }

   public static String getMin(int base) {
       if (base < 0) {
           return String.valueOf(getMinInt(base));
       } else {
           return "+" + getMinInt(base);
       }
   }

   public static String getMax(int base) {
       if (base < 0) {
           return String.valueOf(getMaxInt(base));
       } else {
           return "+" + getMaxInt(base);
       }
   }

   public String getReversedMin(int base) {
       if (base < 0) {
           return String.valueOf(getReversedMinInt(base));
       } else {
           return "+" + getReversedMinInt(base);
       }
   }

   public String getReversedMax(int base) {
       if (base < 0) {
           return String.valueOf(getReversedMaxInt(base));
       } else {
           return "+" + getReversedMaxInt(base);
       }
   }

   public static int getMinInt(int base) {
       if (base < 0) {
           return (int) Math.min(Math.round(base * 1.3d), -1);
       } else {
           return (int) Math.max(Math.round(base * 0.3d), 1);
       }
   }

   public static int getMaxInt(int base) {
        if (base < 0) {
            return (int) Math.min(Math.round(base * 0.7d), -1);
        } else {
            return (int) Math.max(Math.round(base * 1.3d), 1);
        }
    }

    public static int getReversedMinInt(int base) {
        if (base < 0) {
            return (int) Math.min(Math.round(base * 1.3d), -1);
        } else {
            return (int) Math.max(Math.round(base * 0.7d), 1);
        }
    }

    public static int getReversedMaxInt(int base) {
        if (base < 0) {
            return (int) Math.min(Math.round(base * 0.3d), -1);
        } else {
            return (int) Math.max(Math.round(base * 1.3d), 1);
        }
    }

   public static String setPlus(int i) {
        if (i < 0) {
            return "" + i;
        } else {
            return "+" + i;
        }
   }

   public static String getHowToObtainText(String itemName, JsonObject json, int lv, JsonObject manual) {
       StringBuilder sb = new StringBuilder();
       sb.append("&fThis item can be obtained by<br>");

       int p = haveManualDrop(manual, itemName);
       if (p > 0 && json.get(JsonKeys.DROP_META.getKey()) == null) { // dropMeta == null is need?
           if (p == 1) {
               //Unobtainable
               return "<html>" + TreeCheckBox.fixesText("&fThis item can't be obtained.");
           } else if (p == 13) {
               //Discontinued
               return "<html>" + TreeCheckBox.fixesText("&fThis item is Discontinued.<br>" + manual.get(JsonValues.DISCONTINUED).getAsJsonObject().get(itemName).getAsJsonObject().get(JsonKeys.POS.getKey()).getAsString());
           } else {
               if (manual.get(JsonValues.D_NORMAL) != null) { //Normal
                   for (JsonElement je : manual.get(JsonValues.D_NORMAL).getAsJsonArray()) {
                       if (je.getAsString().equals(itemName)) {
                           String s = "Hostile Mob and Any Loot Chests<br>Level " + Math.max((lv - 4), 1) + " to " + (lv + 4) + "<br><br>";
                           sb.append(s);
                           break;
                       }
                   }
               }
               if (manual.get(JsonValues.LEGENDARY_ISLAND) != null) { //Legendary Island
                   for (JsonElement je : manual.get(JsonValues.LEGENDARY_ISLAND).getAsJsonArray()) {
                       if (je.getAsString().equals(itemName)) {
                           sb.append("Legendary Island<br><br>");
                           break;
                       }
                   }
               }
               setPosOnlyType(manual, sb, itemName, JsonValues.DUNGEON, ""); //Dungeon Drop (and Forgery Chest)
               setMerchant(manual, sb, itemName, JsonValues.MERCHANT); //Merchant
               setMerchant(manual, sb, itemName, JsonValues.DUNGEON_MERCHANT); //Dungeon Merchant
               setPosOnlyType(manual, sb, itemName, JsonValues.THE_QIRA_HIVE, "The Qira Hive: "); //The Qira Hive
               setPosOnlyType(manual, sb, itemName, JsonValues.SECRET_DISCOVERY, ""); //Secret Discovery
               setPosOnlyType(manual, sb, itemName, JsonValues.QUEST, "Quest: "); //Quest
               setPosOnlyType(manual, sb, itemName, JsonValues.RAID, "Raid Rewards: "); //Raid Rewards
               setPosOnlyType(manual, sb, itemName, JsonValues.OTHER, ""); //Other
               setPosOnlyType(manual, sb, itemName, JsonValues.WORLD_EVENT, "World Event: "); //World Event
               setPosOnlyType(manual, sb, itemName, JsonValues.LOOTRUN, ""); //Lootrun
               if (manual.get(JsonValues.SPECIFIC) != null && manual.get(JsonValues.SPECIFIC).getAsJsonObject().get(itemName) != null) { //Specific
                   for (JsonElement je : manual.get(JsonValues.SPECIFIC).getAsJsonObject().get(itemName).getAsJsonArray()) {
                       JsonObject jsp = je.getAsJsonObject();
                       if (jsp.get(JsonKeys.IS_MOBNAME.getKey()) == null || jsp.get(JsonKeys.IS_MOBNAME.getKey()).getAsBoolean())
                           sb.append("Mob Name: ");
                       sb.append(jsp.get(JsonKeys.NAME.getKey()).getAsString());
                       if (jsp.get(JsonKeys.POS.getKey()) != null) {
                           for (JsonElement je1 : jsp.get(JsonKeys.POS.getKey()).getAsJsonArray()) {
                               String s = "<br>Locate: " + je1.getAsString();
                               sb.append(s);
                           }
                       }
                       sb.append("<br><br>");
                   }
               }
               return "<html>" + TreeCheckBox.fixesText(sb.substring(0, sb.toString().length() - 4));
           }
       } else {
           if (json.get(JsonKeys.DROP_META.getKey()) != null) {
               JsonObject j = json.get(JsonKeys.DROP_META.getKey()).getAsJsonObject();
               boolean skipLocate = false;
               if (j.get(JsonKeys.NAME.getKey()) != null) sb.append(j.get(JsonKeys.NAME.getKey()).getAsString());
               if (j.get(JsonKeys.TYPE.getKey()) != null) {
                   if (j.get(JsonKeys.TYPE.getKey()).isJsonArray() && j.get(JsonKeys.EVENT.getKey()) != null) {
                       String s = " Merchant and " + j.get(JsonKeys.EVENT.getKey()).getAsString() + " Event";
                       sb.append(s);
                   } else {
                       switch (j.get(JsonKeys.TYPE.getKey()).getAsString()) {
                           case JsonValues.DUNGEON_MERCHANT:
                               sb.append(" Dungeon Merchant");
                               break;
                           case JsonValues.RAID:
                               sb.append(" Raid Rewards");
                               break;
                           case JsonValues.ALTAR:
                               sb.append(" Altar");
                               break;
                           case JsonValues.DUNGEON:
                               sb.append(" Dungeon Drop");
                               break;
                           case JsonValues.MERCHANT:
                               sb.append(" Merchant");
                               break;
                           case JsonValues.LOOTRUN:
                           case JsonValues.QUEST:
                           case JsonValues.CHALLENGE:
                           case JsonValues.MINIBOSS:
                               break;
                           default:
                               String s = " " + j.get(JsonKeys.TYPE.getKey()).getAsString();
                               sb.append(s);
                               System.out.println(itemName + " has unknown drop type: " + s);
                               break;
                       }
                   }
               }
               if (j.get(JsonKeys.COORDINATES.getKey()) != null && !skipLocate) {
                   String s = "<br>Locate: " + j.get(JsonKeys.COORDINATES.getKey()).getAsJsonArray().get(0).getAsInt() + ", " + j.get(JsonKeys.COORDINATES.getKey()).getAsJsonArray().get(1).getAsInt() + ", " + j.get(JsonKeys.COORDINATES.getKey()).getAsJsonArray().get(2).getAsInt();
                   sb.append(s);
               }
               return "<html>" + TreeCheckBox.fixesText(sb.toString());
           } else if (json.get(JsonKeys.DROP_RESTRICTION.getKey()) != null) {
               switch (json.get(JsonKeys.DROP_RESTRICTION.getKey()).getAsString()) {
                   case JsonValues.D_NORMAL: {
                       return "<html>" + TreeCheckBox.fixesText(sb + "Hostile Mob and Any Loot Chests<br>Level " + Math.max((lv - 4), 1) + " to " + (lv + 4));
                   }
                   case JsonValues.LOOTCHEST: {
                       return "<html>" + TreeCheckBox.fixesText(sb + "Tier 3 and 4 Loot Chests<br>Level " + Math.max((lv - 4), 1) + " to " + (lv + 4));
                   }
                   case JsonValues.NEVER: {
                       return "<html>" + TreeCheckBox.fixesText("&fUnknown");
                   }
                   case JsonValues.DUNGEON: {
                       return "<html>" + TreeCheckBox.fixesText(sb + "Dungeon Drop");
                   }
                   default: {
                       System.out.println(itemName + " has unknown drop type:" + json.get(JsonKeys.DROP_RESTRICTION.getKey()).getAsString());
                       return "<html>" + TreeCheckBox.fixesText(sb + json.get(JsonKeys.DROP_RESTRICTION.getKey()).getAsString());
                   }
               }
           }
       }
       return "";
   }

   public static int haveManualDrop(JsonObject j, String itemName) {
        if (j.get(JsonValues.D_NORMAL) != null) { //Normal
            for (JsonElement je : j.get(JsonValues.D_NORMAL).getAsJsonArray()) {
                if (je.getAsString().equals(itemName)) return 11;
            }
        }
        if (j.get(JsonValues.UNOBTAINABLE) != null) { //Unobtainable
            for (JsonElement je : j.get(JsonValues.UNOBTAINABLE).getAsJsonArray()) {
                if (je.getAsString().equals(itemName)) return 1;
            }
        }
        //Dugenon Drop and Forgery Chest
        if (j.get(JsonValues.DUNGEON) != null && j.get(JsonValues.DUNGEON).getAsJsonObject().get(itemName) != null) return 2;

        if (j.get(JsonValues.LEGENDARY_ISLAND) != null) { //Legendary Island
            for (JsonElement je : j.get(JsonValues.LEGENDARY_ISLAND).getAsJsonArray()) {
                if (je.getAsString().equals(itemName)) return 3;
            }
        }
        //Merchant
        if (j.get(JsonValues.MERCHANT) != null && j.get(JsonValues.MERCHANT).getAsJsonObject().get(itemName) != null) {
            //if (!j.get(JsonValues.MERCHANT).getAsJsonObject().get(itemName).getAsJsonObject().entrySet().isEmpty()) {
            //    return 4;
            //} else {
            //    return 14;
            //}
            return 4;
        }

        //The Qira Hive
       if (j.get(JsonValues.THE_QIRA_HIVE) != null && j.get(JsonValues.THE_QIRA_HIVE).getAsJsonObject().get(itemName) != null) return 5;

       //Secret Discovery
       if (j.get(JsonValues.SECRET_DISCOVERY) != null && j.get(JsonValues.SECRET_DISCOVERY).getAsJsonObject().get(itemName) != null) return 6;

       //Quest Rewards
       if (j.get(JsonValues.QUEST) != null && j.get(JsonValues.QUEST).getAsJsonObject().get(itemName) != null) return 7;

       //Specific
       if (j.get(JsonValues.SPECIFIC) != null && j.get(JsonValues.SPECIFIC).getAsJsonObject().get(itemName) != null) return 8;

       //Raid Rewards
       if (j.get(JsonValues.RAID) != null && j.get(JsonValues.RAID).getAsJsonObject().get(itemName) != null) return 9;

       //Other
       if (j.get(JsonValues.OTHER) != null && j.get(JsonValues.OTHER).getAsJsonObject().get(itemName) != null) return 10;

       //Dungeon Merchant
       if (j.get(JsonValues.DUNGEON_MERCHANT) != null && j.get(JsonValues.DUNGEON_MERCHANT).getAsJsonObject().get(itemName) != null) return 12;

       //Discontinued
       if (j.get(JsonValues.DISCONTINUED) != null && j.get(JsonValues.DISCONTINUED).getAsJsonObject().get(itemName) != null) return 13;

       //World Event
       if (j.get(JsonValues.WORLD_EVENT) != null && j.get(JsonValues.WORLD_EVENT).getAsJsonObject().get(itemName) != null) return 15;

       //Lootrun
       if (j.get(JsonValues.LOOTRUN) != null && j.get(JsonValues.LOOTRUN).getAsJsonObject().get(itemName) != null) return 16;

        return 0;
   }

   public static class FullMessageAction implements ActionListener {

       @Override
       public void actionPerformed(ActionEvent e) {
           if (e.getSource() instanceof JButton) {
               String s = ((JButton) e.getSource()).getToolTipText()
                       .replaceAll("<html>", "").replaceAll("<br>", "\n").replaceAll("</font>", "")
                       .replaceAll("<font color=\"#000000\">", "").replaceAll("<font color=\"#0000AA\">", "").replaceAll("<font color=\"#00AA00\">", "")
                       .replaceAll("<font color=\"#00AAAA\">", "").replaceAll("<font color=\"#AA0000\">", "").replaceAll("<font color=\"#AA00AA\">", "")
                       .replaceAll("<font color=\"#FFAA00\">", "").replaceAll("<font color=\"#AAAAAA\">", "").replaceAll("<font color=\"#555555\">", "")
                       .replaceAll("<font color=\"#5555FF\">", "").replaceAll("<font color=\"#55FF55\">", "").replaceAll("<font color=\"#55FFFF\">", "")
                       .replaceAll("<font color=\"#FF5555\">", "").replaceAll("<font color=\"#FF55FF\">", "").replaceAll("<font color=\"#FFFF55\">", "")
                       .replaceAll("<font color=\"#FFFFFF\">", "").replaceAll("<b>", "").replaceAll("</b>", "").replaceAll("<u>", "").replaceAll("</u>", "");
               System.out.println("Clipped: " + s);

               StringSelection selection = new StringSelection(s);
               Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, selection);
           }
       }
   }

   public static class OpenURLAction implements ActionListener {

       @Override
       public void actionPerformed(ActionEvent e) {
           if (Desktop.isDesktopSupported() && e.getSource() instanceof JButton) {
               try {
                   Desktop.getDesktop().browse(URI.create(((JButton) e.getSource()).getToolTipText()));
               } catch (IOException ex) {
                   ex.printStackTrace();
               }
           }
       }
   }

   public static class JLabel_Custom extends JLabel {
        public JLabel_Custom(String text) {
            super(text);
        }

       @Override
       public JToolTip createToolTip() {
           JToolTip toolTip = new JToolTip().createToolTip();
           toolTip.setBackground(new Color(15, 1, 15));
           return toolTip;
       }
   }

   public static class JButton_Custom extends JButton {
        public JButton_Custom(String s) {
            super(s);
        }

       @Override
       public JToolTip createToolTip() {
           JToolTip toolTip = new JToolTip().createToolTip();
           toolTip.setBackground(new Color(15, 1, 15));
           return toolTip;
       }
   }
}
