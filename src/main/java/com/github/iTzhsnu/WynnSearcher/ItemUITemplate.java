package com.github.iTzhsnu.WynnSearcher;

import com.github.iTzhsnu.WynnSearcher.builder.skilltree.TreeCheckBox;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemUITemplate extends JPanel {
    private final JsonObject json;
    private final List<JLabel> label = new ArrayList<>();
    private final float totalValue;
    private final boolean isCustom;
    private int urlSize = 0;

    public static final Map<Integer, Identifications> ITEM_IDS = new HashMap<Integer, Identifications>() {{
        put(0, Identifications.STRENGTH);
        put(1, Identifications.DEXTERITY);
        put(2, Identifications.INTELLIGENCE);
        put(3, Identifications.DEFENSE);
        put(4, Identifications.AGILITY);

        put(5, Identifications.HEALTH_BONUS);
        put(6, Identifications.RAW_HEALTH_REGEN);
        put(7, Identifications.HEALTH_REGEN_PERCENT);
        put(8, Identifications.LIFE_STEAL);
        put(9, Identifications.MANA_STEAL);
        put(10, Identifications.MANA_REGEN);

        put(11, Identifications.EARTH_DEFENSE_PERCENT);
        put(12, Identifications.THUNDER_DEFENSE_PERCENT);
        put(13, Identifications.WATER_DEFENSE_PERCENT);
        put(14, Identifications.FIRE_DEFENSE_PERCENT);
        put(15, Identifications.AIR_DEFENSE_PERCENT);

        put(16, Identifications.SPELL_DAMAGE_PERCENT);
        put(17, Identifications.MELEE_DAMAGE_PERCENT);
        put(18, Identifications.EARTH_DAMAGE_PERCENT);
        put(19, Identifications.THUNDER_DAMAGE_PERCENT);
        put(20, Identifications.WATER_DAMAGE_PERCENT);
        put(21, Identifications.FIRE_DAMAGE_PERCENT);
        put(22, Identifications.AIR_DAMAGE_PERCENT);
        put(23, Identifications.ELEMENTAL_DAMAGE_PERCENT);

        put(24, Identifications.NEUTRAL_SPELL_DAMAGE_PERCENT);
        put(25, Identifications.EARTH_SPELL_DAMAGE_PERCENT);
        put(26, Identifications.THUNDER_SPELL_DAMAGE_PERCENT);
        put(27, Identifications.WATER_SPELL_DAMAGE_PERCENT);
        put(28, Identifications.FIRE_SPELL_DAMAGE_PERCENT);
        put(29, Identifications.AIR_SPELL_DAMAGE_PERCENT);
        put(30, Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT);

        put(31, Identifications.NEUTRAL_MELEE_DAMAGE_PERCENT);
        put(32, Identifications.EARTH_MELEE_DAMAGE_PERCENT);
        put(33, Identifications.THUNDER_MELEE_DAMAGE_PERCENT);
        put(34, Identifications.WATER_MELEE_DAMAGE_PERCENT);
        put(35, Identifications.FIRE_MELEE_DAMAGE_PERCENT);
        put(36, Identifications.AIR_MELEE_DAMAGE_PERCENT);
        put(37, Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT);

        put(38, Identifications.RAW_SPELL_DAMAGE);
        put(39, Identifications.RAW_MELEE_DAMAGE);

        put(40, Identifications.RAW_NEUTRAL_DAMAGE);
        put(41, Identifications.RAW_EARTH_DAMAGE);
        put(42, Identifications.RAW_THUNDER_DAMAGE);
        put(43, Identifications.RAW_WATER_DAMAGE);
        put(44, Identifications.RAW_FIRE_DAMAGE);
        put(45, Identifications.RAW_AIR_DAMAGE);
        put(46, Identifications.RAW_ELEMENTAL_DAMAGE);

        put(47, Identifications.RAW_NEUTRAL_SPELL_DAMAGE);
        put(48, Identifications.RAW_EARTH_SPELL_DAMAGE);
        put(49, Identifications.RAW_THUNDER_SPELL_DAMAGE);
        put(50, Identifications.RAW_WATER_SPELL_DAMAGE);
        put(51, Identifications.RAW_FIRE_SPELL_DAMAGE);
        put(52, Identifications.RAW_AIR_SPELL_DAMAGE);
        put(53, Identifications.RAW_ELEMENTAL_SPELL_DAMAGE);

        put(54, Identifications.RAW_NEUTRAL_MELEE_DAMAGE);
        put(55, Identifications.RAW_EARTH_MELEE_DAMAGE);
        put(56, Identifications.RAW_THUNDER_MELEE_DAMAGE);
        put(57, Identifications.RAW_WATER_MELEE_DAMAGE);
        put(58, Identifications.RAW_FIRE_MELEE_DAMAGE);
        put(59, Identifications.RAW_AIR_MELEE_DAMAGE);
        put(60, Identifications.RAW_ELEMENTAL_MELEE_DAMAGE);

        put(61, Identifications.POISON);
        put(62, Identifications.ATTACK_SPEED_BONUS);

        put(63, Identifications.WALK_SPEED);
        put(64, Identifications.SPRINT_BONUS);
        put(65, Identifications.SPRINT_REGEN);
        put(66, Identifications.JUMP_HEIGHT);

        put(67, Identifications.THORNS);
        put(68, Identifications.REFLECTION);
        put(69, Identifications.EXPLODING);
        put(70, Identifications.STEALING);
        put(71, Identifications.COMBAT_XP_BONUS);
        put(72, Identifications.GATHERING_XP_BONUS);
        put(73, Identifications.GATHERING_SPEED_BONUS);
        put(74, Identifications.LOOT_BONUS);
        put(75, Identifications.LOOT_QUALITY);
        put(76, Identifications.SOUL_POINT_REGEN);

        put(77, Identifications.KNOCKBACK);
        put(78, Identifications.HEALING_EFFICIENCY);
        put(79, Identifications.WEAKEN_ENEMY);
        put(80, Identifications.SLOW_ENEMY);
        put(81, Identifications.ELEMENTAL_DEFENSE);
    }};

    public static final Map<Integer, Identifications> REVERSED_ITEM_IDS = new HashMap<Integer, Identifications>() {{
       put(0, Identifications.PERCENT_1ST_SPELL_COST);
       put(1, Identifications.PERCENT_2ND_SPELL_COST);
       put(2, Identifications.PERCENT_3RD_SPELL_COST);
       put(3, Identifications.PERCENT_4TH_SPELL_COST);

       put(4, Identifications.RAW_1ST_SPELL_COST);
       put(5, Identifications.RAW_2ND_SPELL_COST);
       put(6, Identifications.RAW_3RD_SPELL_COST);
       put(7, Identifications.RAW_4TH_SPELL_COST);
    }};

    public static final Map<Integer, Identifications> DAMAGE_IDS = new HashMap<Integer, Identifications>() {{
        put(0, Identifications.NEUTRAL_DAMAGE);
        put(1, Identifications.EARTH_DAMAGE);
        put(2, Identifications.THUNDER_DAMAGE);
        put(3, Identifications.WATER_DAMAGE);
        put(4, Identifications.FIRE_DAMAGE);
        put(5, Identifications.AIR_DAMAGE);
    }};

    public ItemUITemplate(JsonObject json, String type, JPanel previous, JPanel above, int uiWidth, float totalValue, boolean isCustom) {
        this.json = json;
        this.totalValue = totalValue;
        this.isCustom = isCustom;

        switch (type) {
            case "item":
                if (!isCustom) urlSize = 56;
                setItemDisplay();
                break;
            case "ingredient":
                if (!isCustom) urlSize = 32;
                setIngDisplay();
                break;
            case "other":
                if (!isCustom && !json.get("type").getAsString().equals("charm") && !json.get("type").getAsString().equals("tome")) urlSize = 32;
                setOtherDisplay();
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
        if (json.get("tier") != null) {
            try {
                switch (json.get("tier").getAsInt()) {
                    case 1:
                        setBackground(new Color(252, 242, 99));
                        break;
                    case 2:
                        setBackground(new Color(255, 168, 211)); //OLD COLOR 220, 107, 154
                        break;
                    case 3:
                        setBackground(new Color(135, 206, 250));
                        break;
                    default:
                        setBackground(new Color(230, 230, 230));
                        break;
                }
            } catch (NumberFormatException e) {
                switch (json.get("tier").getAsString()) {
                    case "unique":
                        setBackground(new Color(252, 242, 99));
                        break;
                    case "rare":
                        setBackground(new Color(255, 168, 211)); //OLD COLOR 220, 107, 154
                        break;
                    case "legendary":
                        setBackground(new Color(135, 206, 250));
                        break;
                    case "fabled":
                        setBackground(new Color(220, 107, 154)); //OLD COLOR 255 81 81
                        break;
                    case "mythic":
                        setBackground(new Color(145, 93, 163));
                        break;
                    case "set":
                        setBackground(new Color(121, 192, 110)); //OLD COLOR 85 255 85
                        break;
                    case "crafted":
                        setBackground(new Color(0, 175, 204));
                        break;
                    default:
                        setBackground(new Color(230, 230, 230));
                        break;
                }
            }
        }
        setVisible(true);
    }

    public void setItemDisplay() {
        JButton dataButton = new JButton("Open Wynndata");
        dataButton.setBorderPainted(false);
        dataButton.setOpaque(false);
        dataButton.setBackground(Color.WHITE);
        dataButton.setForeground(Color.BLUE);
        dataButton.addActionListener(new OpenURLAction());

        JButton builderButton = new JButton("Open Wynnbuilder");
        builderButton.setBorderPainted(false);
        builderButton.setOpaque(false);
        builderButton.setBackground(Color.WHITE);
        builderButton.setForeground(Color.BLUE);
        builderButton.addActionListener(new OpenURLAction());

        String itemName = "";

        if (json.get("name") != null) {
            label.add(new JLabel(json.get("name").getAsString()));
            itemName = json.get("name").getAsString();
            String name = itemName.replaceAll(" ", "%20");
            dataButton.setToolTipText("https://www.wynndata.tk/i/" + name);
            builderButton.setToolTipText("https://hppeng-wynn.github.io/item/#" + name);
        }

        if (json.get("type") != null) {
            label.add(new JLabel("Type: " + json.get("type").getAsString().substring(0, 1).toUpperCase() + json.get("type").getAsString().substring(1)));
        } else if (json.get("accessoryType") != null) {
            label.add(new JLabel("Type: " + json.get("accessoryType").getAsString().substring(0, 1).toUpperCase() + json.get("accessoryType").getAsString().substring(1)));
        }

        if (json.get("attackSpeed") != null) {
            StringBuilder sb = new StringBuilder();
            String s = json.get("attackSpeed").getAsString();
            if (json.get("attackSpeed").getAsString().contains("_")) {
                String[] ss = s.split("_");
                for (String s1 : ss) {
                    String s2 = " " + s1.substring(0, 1).toUpperCase() + s1.substring(1);
                    sb.append(s2);
                }
            } else {
                String s1 = " " + s.substring(0, 1).toUpperCase() + s.substring(1);
                sb.append(s1);
            }
            label.add(new JLabel("Attack Speed:" + sb));
        }

        label.add(new JLabel(" "));

        if (json.get("base") != null) {
            JsonObject j = json.get("base").getAsJsonObject();
            if (j.get(Identifications.HEALTH.getItemName()) != null) {
                JsonElement j2 = j.get(Identifications.HEALTH.getItemName());
                if (json.get("tier") != null && json.get("tier").getAsString().equals("crafted")) {
                    label.add(new JLabel("Health: " + j2.getAsJsonObject().get("min").getAsInt() + "-" + j2.getAsJsonObject().get("max").getAsInt()));
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
                    if (json.get("tier") != null && !json.get("tier").getAsString().equals("crafted")) {
                        label.add(new JLabel(id.getDisplayName() + ": " + j2.get("min").getAsInt() + "-" + j2.getAsJsonObject().get("max").getAsInt()));
                    } else {
                        label.add(new JLabel(id.getDisplayName() + ": " + j2.get("min").getAsString() + " to " + j2.getAsJsonObject().get("max").getAsString()));
                    }
                }
            }
        }

        label.add(new JLabel(" "));

        int lv = 0;
        if (json.get("requirements") != null) {
            JsonObject j = json.get("requirements").getAsJsonObject();
            if (j.get(Identifications.LEVEL.getItemName()) != null) {
                JsonElement j2 = j.get(Identifications.LEVEL.getItemName());
                if (json.get("tier") != null && json.get("tier").getAsString().equals("crafted")) {
                    label.add(new JLabel("Combat Lv. Min: " + j2.getAsJsonObject().get("min").getAsInt() + "-" + j2.getAsJsonObject().get("max").getAsInt()));
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

            if (j.get(Identifications.AGILITY_REQ.getItemName()) != null && j.get(Identifications.AGILITY_REQ.getItemName()).getAsInt() != 0) {
                label.add(new JLabel("Agility Req: " + j.get(Identifications.AGILITY_REQ.getItemName()).getAsInt()));
            }

            if (j.get(Identifications.QUEST_REQ.getItemName()) != null) {
                label.add(new JLabel("Quest Req: " + j.get(Identifications.QUEST_REQ.getItemName()).getAsString()));
            }
        }

        label.add(new JLabel(" "));

        for (int i = 0; 81 >= i; ++i) {
            Identifications id = ITEM_IDS.get(i);
            if (json.get(id.getItemFieldPos()) != null && json.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()) != null) {
                JsonElement j = json.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName());
                if (!j.isJsonObject()) {
                    label.add(new JLabel(id.getDisplayName() + " "+ setPlus(j.getAsInt()) + id.getDisplaySp()));
                } else if (json.get("identified") != null && json.get("identified").getAsBoolean()) {
                    String minOrMax = "max";
                    if (j.getAsJsonObject().get("max").getAsInt() < 0) minOrMax = "min";
                    label.add(new JLabel(id.getDisplayName() + " " + setPlus(SearchUI.getBaseID(j.getAsJsonObject().get(minOrMax).getAsInt())) + id.getDisplaySp()));
                } else if (id.isItemVariable() || json.get("tier").getAsString().equals("crafted")) {
                    label.add(new JLabel(setPlus(j.getAsJsonObject().get("min").getAsInt()) + id.getDisplaySp() + " " + id.getDisplayName() + " " + setPlus(j.getAsJsonObject().get("max").getAsInt()) + id.getDisplaySp()));
                }
            }
        }

        for (int i = 0; 7 >= i; ++i) {
            Identifications id = REVERSED_ITEM_IDS.get(i);
            if (json.get(id.getItemFieldPos()) != null && json.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()) != null) {
                JsonElement j = json.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName());
                if (!j.isJsonObject()) {
                    label.add(new JLabel(id.getDisplayName() + " "+ setPlus(j.getAsInt()) + id.getDisplaySp()));
                } else if (json.get("identified") != null && json.get("identified").getAsBoolean()) {
                    label.add(new JLabel(id.getDisplayName() + " " + setPlus(SearchUI.getBaseID(j.getAsJsonObject().get("max").getAsInt())) + id.getDisplaySp()));
                } else {
                    int base = SearchUI.getBaseID(j.getAsJsonObject().get("max").getAsInt());
                    label.add(new JLabel(getReversedMax(base) + id.getDisplaySp() + " " + id.getDisplayName() + " " + getReversedMin(base) + id.getDisplaySp()));
                }
            }
        }

        label.add(new JLabel(" "));
        if (json.get(Identifications.POWDER_SLOTS.getItemName()) != null && json.get(Identifications.POWDER_SLOTS.getItemName()).getAsInt() != 0) {
            label.add(new JLabel("Powder Slots: " + json.get(Identifications.POWDER_SLOTS.getItemName()).getAsInt()));
        }

        if (json.get(Identifications.MAJOR_IDS.getItemName()) != null) {
            JLabel_Custom l = new JLabel_Custom("Major ID: " + json.get(Identifications.MAJOR_IDS.getItemName()).getAsJsonObject().get("name").getAsString());
            l.setToolTipText("<html>" + TreeCheckBox.fixesText(json.get(Identifications.MAJOR_IDS.getItemName()).getAsJsonObject().get("description").getAsString()));
            label.add(l);
        }

        if (json.get("tier") != null) {
            label.add(new JLabel("Rarity: " + json.get("tier").getAsString().substring(0, 1).toUpperCase() + json.get("tier").getAsString().substring(1)));
        }

        if (json.get("durability") != null) {
            label.add(new JLabel("Durability: " + json.get("durability").getAsString()));
        }

        if (json.get("duration") != null) {
            label.add(new JLabel("Duration: " + json.get("duration").getAsString()));
        }

        if (json.get("charges") != null) {
            label.add(new JLabel("Charge: " + json.get("charges").getAsInt()));
        }

        if (json.get("restrictions") != null && !json.get("restrictions").isJsonNull()) {
            label.add(new JLabel(json.get("restrictions").getAsString()));
        }

        JButton_Custom l = null;
        if (!isCustom) {
            if (json.get("dropRestriction") != null || json.get("dropMeta") != null) {
                label.add(new JLabel(" "));
                l = new JButton_Custom("How to Obtain (Not Perfect)");
                l.setBorderPainted(false);
                l.setOpaque(false);
                l.setBackground(Color.WHITE);
                l.setForeground(Color.BLUE);

                StringBuilder sb = new StringBuilder();
                sb.append("&fThis item can be obtained by<br>");

                JsonObject manual = new GetAPI().getHowToObtainItem();
                int p = haveManualDrop(manual, itemName);
                if (p > 0) {
                    if (p == 1) {
                        //Unobtainable
                        l.setToolTipText("<html>" + TreeCheckBox.fixesText("&fThis item can't be obtained."));
                    } else if (p == 13) {
                        //Discontinued
                        l.setToolTipText("<html>" + TreeCheckBox.fixesText("&fThis item is Discontinued.<br>" + manual.get("discontinued").getAsJsonObject().get(itemName).getAsJsonObject().get("pos").getAsString()));
                    } else {
                        if (manual.get("normal") != null) { //Normal
                            for (JsonElement je : manual.get("normal").getAsJsonArray()) {
                                if (je.getAsString().equals(itemName)) {
                                    String s = "Hostile Mob and Any Loot Chests<br>Level " + Math.max((lv - 4), 1) + " to " + (lv + 4) + "<br><br>";
                                    sb.append(s);
                                    break;
                                }
                            }
                        }
                        if (manual.get("legendary_island") != null) { //Legendary Island
                            for (JsonElement je : manual.get("legendary_island").getAsJsonArray()) {
                                if (je.getAsString().equals(itemName)) {
                                    sb.append("Legendary Island<br><br>");
                                    break;
                                }
                            }
                        }
                        setPosOnlyType(manual, sb, itemName, "dungeon", ""); //Dungeon Drop (and Forgery Chest)
                        setMerchant(manual, sb, itemName, "merchant"); //Merchant
                        setMerchant(manual, sb, itemName, "dungeonMerchant"); //Dungeon Merchant
                        setPosOnlyType(manual, sb, itemName, "the_qira_hive", "The Qira Hive: "); //The Qira Hive
                        setPosOnlyType(manual, sb, itemName, "secret_discovery", ""); //Secret Discovery
                        setPosOnlyType(manual, sb, itemName, "quest", "Quest: "); //Quest
                        setPosOnlyType(manual, sb, itemName, "raid", "Raid Rewards: "); //Raid Rewards
                        setPosOnlyType(manual, sb, itemName, "other", ""); //Other
                        if (manual.get("specific") != null && manual.get("specific").getAsJsonObject().get(itemName) != null) { //Specific
                            for (JsonElement je : manual.get("specific").getAsJsonObject().get(itemName).getAsJsonArray()) {
                                JsonObject jsp = je.getAsJsonObject();
                                if (jsp.get("ismobname") == null || jsp.get("ismobname").getAsBoolean())
                                    sb.append("Mob Name: ");
                                sb.append(jsp.get("name").getAsString());
                                if (jsp.get("pos") != null) {
                                    for (JsonElement je1 : jsp.get("pos").getAsJsonArray()) {
                                        String s = "<br>Locate: " + je1.getAsString();
                                        sb.append(s);
                                    }
                                }
                                sb.append("<br><br>");
                            }
                        }
                        l.setToolTipText("<html>" + TreeCheckBox.fixesText(sb.substring(0, sb.toString().length() - 4)));
                    }
                } else {
                    if (json.get("dropMeta") != null) {
                        JsonObject j = json.get("dropMeta").getAsJsonObject();
                        if (j.get("name") != null) sb.append(j.get("name").getAsString());
                        if (j.get("type") != null) {
                            if (j.get("type").isJsonArray() && j.get("event") != null) {
                                String s = " Merchant and " + j.get("event").getAsString() + " Event";
                                sb.append(s);
                            } else {
                                switch (j.get("type").getAsString()) {
                                    case "dungeonMerchant":
                                        sb.append(" Dungeon Merchant");
                                        break;
                                    case "raid":
                                        sb.append(" Raid Rewards");
                                        break;
                                    case "altar":
                                        sb.append(" Altar");
                                        break;
                                    case "dungeon":
                                        sb.append(" Dungeon Drop");
                                        break;
                                    default:
                                        String s = " " + j.get("type").getAsString();
                                        sb.append(s);
                                        System.out.println(itemName + " this item drop type unknown: " + s);
                                        break;
                                }
                            }
                        }
                        if (j.get("coordinates") != null) {
                            String s = "<br>Locate: " + j.get("coordinates").getAsJsonArray().get(0).getAsInt() + ", " + j.get("coordinates").getAsJsonArray().get(1).getAsInt() + ", " + j.get("coordinates").getAsJsonArray().get(2).getAsInt();
                            sb.append(s);
                        }
                        l.setToolTipText("<html>" + TreeCheckBox.fixesText(sb.toString()));
                    } else if (json.get("dropRestriction") != null) {
                        switch (json.get("dropRestriction").getAsString()) {
                            case "normal": {
                                l.setToolTipText("<html>" + TreeCheckBox.fixesText(sb + "Hostile Mob and Any Loot Chests<br>Level " + Math.max((lv - 4), 1) + " to " + (lv + 4)));
                                break;
                            }
                            case "lootchest": {
                                l.setToolTipText("<html>" + TreeCheckBox.fixesText(sb + "Tier 3 and 4 Loot Chests<br>Level " + Math.max((lv - 4), 1) + " to " + (lv + 4)));
                                break;
                            }
                            case "never": {
                                l.setToolTipText("<html>" + TreeCheckBox.fixesText("&fUnknown"));
                                break;
                            }
                            case "dungeon": {
                                l.setToolTipText("<html>" + TreeCheckBox.fixesText(sb + "Dungeon Drop"));
                                break;
                            }
                            default: {
                                l.setToolTipText("<html>" + TreeCheckBox.fixesText(sb + json.get("dropRestriction").getAsString()));
                                System.out.println(itemName + " this drop type unknown:" + json.get("dropRestriction").getAsString());
                                break;
                            }
                        }
                    }
                }
                l.addActionListener(new FullMessageAction());
            }
        }

        for (JLabel jLabel : label) {
            add(jLabel);
        }

        if (l != null) {
            add(l);
            urlSize += 24;
        }

        if (!isCustom) {
            label.add(new JLabel(" "));
            label.add(new JLabel("External Links"));
            add(label.get(label.size() - 2));
            add(label.get(label.size() - 1));

            add(dataButton);
            add(builderButton);

            JLabel sortValue = new JLabel("Sort Value: " + totalValue);
            sortValue.setForeground(Color.DARK_GRAY);
            add(sortValue);
        }
    }

    public void setIngDisplay() {
        JButton dataButton = new JButton("Wynndata Link");
        dataButton.setBorderPainted(false);
        dataButton.setOpaque(false);
        dataButton.setBackground(Color.WHITE);
        dataButton.setForeground(Color.BLUE);
        dataButton.addActionListener(new OpenURLAction());

        String itemName = "";
        if (json.get("name") != null) {
            itemName = json.get("name").getAsString();
            if (json.get("displayName") != null) {
                label.add(new JLabel(json.get("displayName").getAsString()));
            } else {
                label.add(new JLabel(itemName));
            }
            dataButton.setToolTipText("https://www.wynndata.tk/i/" + itemName.replaceAll(" ", "%20"));
        }

        if (json.get("tier") != null) {
            label.add(new JLabel("Star: " + json.get("tier").getAsInt()));
        }

        int lv = 0;
        if (json.get("requirements") != null && json.get("requirements").getAsJsonObject().get("level") != null) {
            lv = json.get("requirements").getAsJsonObject().get("level").getAsInt();
            label.add(new JLabel("Lv. Min: " + lv));
        }

        label.add(new JLabel(" "));

        if (json.get("itemOnlyIDs") != null) {
            JsonObject j = json.getAsJsonObject("itemOnlyIDs").getAsJsonObject();
            boolean run = false;
            if (j.get("durabilityModifier") != null && j.get("durabilityModifier").getAsInt() != 0) {
                label.add(new JLabel("Durability: " + setPlus(j.get("durabilityModifier").getAsInt() / 1000)));
                run = true;
            }
            if (j.get("strengthRequirement") != null && j.get("strengthRequirement").getAsInt() != 0) {
                label.add(new JLabel("Strength Req: " + setPlus(j.get("strengthRequirement").getAsInt())));
                run = true;
            }
            if (j.get("dexterityRequirement") != null && j.get("dexterityRequirement").getAsInt() != 0) {
                label.add(new JLabel("Dexterity Req: " + setPlus(j.get("dexterityRequirement").getAsInt())));
                run = true;
            }
            if (j.get("intelligenceRequirement") != null && j.get("intelligenceRequirement").getAsInt() != 0) {
                label.add(new JLabel("Intelligence Req: " + setPlus(j.get("intelligenceRequirement").getAsInt())));
                run = true;
            }
            if (j.get("defenceRequirement") != null && j.get("defenceRequirement").getAsInt() != 0) {
                label.add(new JLabel("Defense Req: " + setPlus(j.get("defenceRequirement").getAsInt())));
                run = true;
            }
            if (j.get("agilityRequirement") != null && j.get("agilityRequirement").getAsInt() != 0) {
                label.add(new JLabel("Agility Req: " + setPlus(j.get("agilityRequirement").getAsInt())));
                run = true;
            }
            if (run) label.add(new JLabel(" "));
        }

        if (json.get("consumableOnlyIDs") != null) {
            JsonObject j = json.get("consumableOnlyIDs").getAsJsonObject();
            boolean run = false;
            if (j.get("duration") != null && j.get("duration").getAsInt() != 0) {
                label.add(new JLabel("Duration: " + setPlus(j.get("duration").getAsInt())));
                run = true;
            }
            if (j.get("charges") != null && j.get("charges").getAsInt() != 0) {
                label.add(new JLabel("Charges: " + setPlus(j.get("charges").getAsInt())));
                run = true;
            }
            if (run) label.add(new JLabel(" "));
        }

        if (json.get("ingredientPositionModifiers") != null) {
            JsonObject j = json.get("ingredientPositionModifiers").getAsJsonObject();
            boolean run = false;
            if (j.get("above") != null && j.get("above").getAsInt() != 0) {
                label.add(new JLabel("Ingredient Effectiveness (Above): " + setPlus(j.get("above").getAsInt()) + "%"));
                run = true;
            }
            if (j.get("under") != null && j.get("under").getAsInt() != 0) {
                label.add(new JLabel("Ingredient Effectiveness (Under): " + setPlus(j.get("under").getAsInt()) + "%"));
                run = true;
            }
            if (j.get("right") != null && j.get("right").getAsInt() != 0) {
                label.add(new JLabel("Ingredient Effectiveness (Right): " + setPlus(j.get("right").getAsInt()) + "%"));
                run = true;
            }
            if (j.get("left") != null && j.get("left").getAsInt() != 0) {
                label.add(new JLabel("Ingredient Effectiveness (Left): " + setPlus(j.get("left").getAsInt()) + "%"));
                run = true;
            }
            if (j.get("touching") != null && j.get("touching").getAsInt() != 0) {
                label.add(new JLabel("Ingredient Effectiveness (Touch): " + setPlus(j.get("touching").getAsInt()) + "%"));
                run = true;
            }
            if (j.get("notTouching") != null && j.get("notTouching").getAsInt() != 0) {
                label.add(new JLabel("Ingredient Effectiveness (Not Touch): " + setPlus(j.get("notTouching").getAsInt()) + "%"));
                run = true;
            }
            if (run) label.add(new JLabel(" "));
        }

        if (json.get("identifications") != null) {
            JsonObject j = json.get("identifications").getAsJsonObject();
            boolean run = false;

            for (int i = 0; 81 >= i; ++i) {
                Identifications id = ITEM_IDS.get(i);
                if (id.getIngName() != null && id.getIngFieldPos().equals("identifications") && j.get(id.getIngName()) != null && j.get(id.getIngName()).getAsJsonObject() != null) {
                    JsonElement je = j.get(id.getIngName());
                    if (!je.isJsonObject()) {
                        label.add(new JLabel(id.getDisplayName() + " " + setPlus(je.getAsInt()) + id.getDisplaySp()));
                    } else {
                        label.add(new JLabel(setPlus(je.getAsJsonObject().get("min").getAsInt()) + id.getDisplaySp() + " " + id.getDisplayName() + " " + setPlus(je.getAsJsonObject().get("max").getAsInt()) + id.getDisplaySp()));
                    }
                    run = true;
                }
            }

            for (int i = 0; 7 >= i; ++i) {
                Identifications id = REVERSED_ITEM_IDS.get(i);
                if (id.getIngName() != null && id.getIngFieldPos().equals("identifications") && j.get(id.getIngName()) != null && j.get(id.getIngName()).getAsJsonObject() != null) {
                    JsonElement je = j.get(id.getIngName());
                    if (!je.isJsonObject()) {
                        label.add(new JLabel(id.getDisplayName() + " " + setPlus(je.getAsInt()) + id.getDisplaySp()));
                    } else {
                        label.add(new JLabel(setPlus(je.getAsJsonObject().get("min").getAsInt()) + id.getDisplaySp() + " " + id.getDisplayName() + " " + setPlus(je.getAsJsonObject().get("max").getAsInt()) + id.getDisplaySp()));
                    }
                    run = true;
                }
            }

            if (run) label.add(new JLabel(" "));
        }

        if (json.get("requirements") != null && json.get("requirements").getAsJsonObject().get("skills") != null) {
            JsonArray j = json.get("requirements").getAsJsonObject().get("skills").getAsJsonArray();
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
            JsonObject manual = new GetAPI().getHowToObtainIng();
            int haveManualDrop = haveManualDrop(manual, itemName);
            if (haveManualDrop > 0) {
                if (haveManualDrop == 11) {
                    String s = "Any Loot Chests<br>" + "Level " + Math.max((lv - 4), 1) + " to " + (lv + 4) + "<br><br>";
                    sb.append(s);
                }
                if (manual.get("merchant") != null && manual.get("merchant").getAsJsonObject().get(itemName) != null) {
                    JsonObject jo = manual.get("merchant").getAsJsonObject().get(itemName).getAsJsonObject();
                    if (jo.get("name") != null) {
                        String s = "Merchant: " + jo.get("name").getAsString() + "<br>";
                        sb.append(s);
                    }
                    if (jo.get("pos") != null) {
                        String s = "Location: " + jo.get("pos").getAsString() + "<br>";
                        sb.append(s);
                    }
                    if (jo.get("price") != null) {
                        String s = "Price: " + jo.get("price").getAsString() + "<br>";
                        sb.append(s);
                    }
                    sb.append("<br>");
                }
                setPosOnlyType(manual, sb, itemName, "quest", "Quest: "); //Quest
            }

            if (json.get("droppedBy") != null) {
                JsonObject j = json.get("droppedBy").getAsJsonObject();
                for (Map.Entry<String, JsonElement> entry : json.get("droppedBy").getAsJsonObject().entrySet()) {
                    if (j.get(entry.getKey()).isJsonArray()) {
                        if (entry.getKey().equals("Ingredient Dummy")) continue;
                        String s = "Mob Name: " + entry.getKey() + "<br>";
                        sb.append(s);
                        if (j.get(entry.getKey()).getAsJsonArray().get(0).isJsonArray()) {
                            for (int i = 0; j.get(entry.getKey()).getAsJsonArray().size() > i; ++i) {
                                JsonArray ja = j.get(entry.getKey()).getAsJsonArray().get(i).getAsJsonArray();
                                String s1 = "Locate " + (i + 1) + ": " + ja.get(0).getAsInt() + ", " + ja.get(1).getAsInt() + ", " + ja.get(2).getAsInt() + " | Radius: " + (ja.get(3).getAsInt() / 2F) + "<br>";
                                sb.append(s1);
                                if (i == j.get(entry.getKey()).getAsJsonArray().size() - 1) sb.append("<br>");
                            }
                        } else {
                            JsonArray ja = j.get(entry.getKey()).getAsJsonArray();
                            String s1 = "Locate: " + ja.get(0).getAsInt() + ", " + ja.get(1).getAsInt() + ", " + ja.get(2).getAsInt() + " | Radius: " + (ja.get(3).getAsInt() / 2F) + "<br><br>";
                            sb.append(s1);
                        }
                    } else {
                        if (entry.getKey().equals("Ingredient Dummy")) continue;
                        String s = "Mob Name: " + entry.getKey() + "<br><br>";
                        sb.append(s);
                    }
                }
            }

            if (manual.get("specific") != null && manual.get("specific").getAsJsonObject().get(itemName) != null) {
                for (JsonElement je : manual.get("specific").getAsJsonObject().get(itemName).getAsJsonArray()) {
                    JsonObject jsp = je.getAsJsonObject();
                    if (jsp.get("ismobname") == null || jsp.get("ismobname").getAsBoolean()) sb.append("Mob Name: ");
                    sb.append(jsp.get("name").getAsString());
                    sb.append("<br>");
                    if (jsp.get("pos") != null) {
                        for (JsonElement je1 : jsp.get("pos").getAsJsonArray()) {
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

        label.add(new JLabel("External Links"));
        add(label.get(label.size() - 1));
        add(dataButton);

        JLabel sortValue = new JLabel("Sort Value: " + totalValue);
        sortValue.setForeground(Color.DARK_GRAY);
        add(sortValue);
    }

    public void setOtherDisplay() {
        String type = json.get("type").getAsString();
        JButton dataButton = new JButton("Open Wynndata");
        dataButton.setBorderPainted(false);
        dataButton.setOpaque(false);
        dataButton.setBackground(Color.WHITE);
        dataButton.setForeground(Color.BLUE);
        dataButton.addActionListener(new OpenURLAction());

        String itemName = "";

        if (json.get("name") != null) {
            itemName = json.get("name").getAsString();
            label.add(new JLabel(json.get("name").getAsString()));
            String s = json.get("name").getAsString().replaceAll(" ", "%20");
            if (type.equals("tool")) {
                s = s.substring(4);
            } else if (type.equals("material")) {
                s = "Refined%20" + json.get("name").getAsString().replaceAll(" ", "%20");
            }
            dataButton.setToolTipText("https://www.wynndata.tk/i/" + s);
        }

        label.add(new JLabel("Type: " + type.substring(0, 1).toUpperCase() + type.substring(1)));

        if (json.get("tomeType") != null) {
            String s = "Armour";
            switch (json.get("tomeType").getAsString()) {
                case "mobdamage":
                    s = "Weapon";
                    break;
                case "guildtome":
                    s = "Guild";
                    break;
                case "slayingxp":
                    s = "Slaying";
                    break;
                case "dungeonxp":
                    s = "Dungeon";
                    break;
                case "gatheringxp":
                    s = "Gathering";
                    break;
                case "lootrun":
                    s = "Lootrun";
                    break;
            }
            label.add(new JLabel("Tome Type: " + s));
        }

        if (json.get("toolType") != null) {
            label.add(new JLabel("Tool Type: " + json.get("toolType").getAsString().substring(0, 1).toUpperCase() + json.get("toolType").getAsString().substring(1)));
        }

        if (type.equals("material") && json.get("tier") != null) {
            label.add(new JLabel("Star: " + json.get("tier").getAsInt()));
        }

        int lv = 0;
        if (json.get("requirements") != null && json.get("requirements").getAsJsonObject().get("level") != null) {
            int i = json.get("requirements").getAsJsonObject().get("level").getAsInt();
            lv = i;
            switch (type) {
                case "tome":
                case "charm":
                    label.add(new JLabel("Combat Lv. Min: " + i));
                    break;
                case "tool":
                    switch (json.get("toolType").getAsString()) {
                        case "pickaxe":
                            label.add(new JLabel("Mining Lv. Min: " + i));
                            break;
                        case "axe":
                            label.add(new JLabel("Woodcutting Lv. Min: " + i));
                            break;
                        case "scythe":
                            label.add(new JLabel("Farming Lv. Min: " + i));
                            break;
                        case "rod":
                            label.add(new JLabel("Fishing Lv. Min: " + i));
                            break;
                    }
                    break;
                case "material":
                    label.add(new JLabel("Lv. Min: " + i));
                    break;
            }
        }

        label.add(new JLabel(" "));

        if (json.get("gatheringSpeed") != null) { //Tools
            String s = "Slow";
            int i = json.get("gatheringSpeed").getAsInt();
            if (i >= 120) {
                s = "Very Fast";
            } else if (i >= 65) {
                s = "Fast";
            } else if (i == 45) {
                s = "Normal";
            }
            label.add(new JLabel("Gathering Speed: " + i + " (" + s + ")"));
        }

        if (json.get("craftable") != null) { //Material
            label.add(new JLabel("Use this material to craft"));
            for (JsonElement j : json.get("craftable").getAsJsonArray()) {
                label.add(new JLabel(j.getAsString()));
            }
        }

        if (type.equals("tome") && json.get("base") != null) { //Tome
            JsonObject j = json.get("base").getAsJsonObject();
            if (j.get("damageToMobs") != null) label.add(new JLabel("+" + j.get("damageToMobs").getAsInt() + "% Damage to Mobs"));
            if (j.get("defenceToMobs") != null) label.add(new JLabel("+" + j.get("defenceToMobs").getAsInt() + "% Mob Damage Resistance"));
            if (j.get("dungeonXP") != null) label.add(new JLabel("+" + j.get("dungeonXP").getAsInt() + "% Dungeon XP"));
            if (j.get("gatheringXP") != null) label.add(new JLabel("+" + j.get("gatheringXP").getAsInt() + "% Gathering XP"));
            if (j.get("slayingXP") != null) label.add(new JLabel("+" + j.get("slayingXP").getAsInt() + "% Slaying XP"));
            label.add(new JLabel(" "));
        }

        if (type.equals("charm") && json.get("base") != null && json.get("requirements") != null) {
            JsonObject j = json.get("base").getAsJsonObject();
            int min = json.get("requirements").getAsJsonObject().get("levelRange").getAsJsonObject().get("min").getAsInt();
            int max = json.get("requirements").getAsJsonObject().get("levelRange").getAsJsonObject().get("max").getAsInt();

            if (j.get("leveledXpBonus") != null) label.add(new JLabel(getMin(j.get("leveledXpBonus").getAsInt()) + "% XP from Lv." + min + "-" + max + " contents " + getMax(j.get("leveledXpBonus").getAsInt()) + "%"));
            if (j.get("damageFromMobs") != null) label.add(new JLabel(getMin(j.get("damageFromMobs").getAsInt()) + "% Damage taken from mobs " + getMax(j.get("damageFromMobs").getAsInt()) + "%"));
            if (j.get("leveledLootBonus") != null) label.add(new JLabel(getMin(j.get("leveledLootBonus").getAsInt()) + "% Loot from Lv." + min + "-" + max + " contents " + getMax(j.get("leveledLootBonus").getAsInt()) + "%"));
        }

        if (type.equals("tome") || type.equals("charm")) { //IDs
            for (int i = 0; 81 >= i; ++i) {
                Identifications id = ITEM_IDS.get(i);
                if (json.get(id.getItemFieldPos()) != null && json.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()) != null) {
                    JsonElement j = json.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName());
                    if (!j.isJsonObject()) {
                        label.add(new JLabel(id.getDisplayName() + " "+ setPlus(j.getAsInt()) + id.getDisplaySp()));
                    } else if (json.get("identified") != null && json.get("identified").getAsBoolean()) {
                        String minOrMax = "max";
                        if (j.getAsJsonObject().get("max").getAsInt() < 0) minOrMax = "min";
                        label.add(new JLabel(id.getDisplayName() + " " + setPlus(SearchUI.getBaseID(j.getAsJsonObject().get(minOrMax).getAsInt())) + id.getDisplaySp()));
                    } else if (id.isItemVariable()) {
                        label.add(new JLabel(setPlus(j.getAsJsonObject().get("min").getAsInt()) + id.getDisplaySp() + " " + id.getDisplayName() + " " + setPlus(j.getAsJsonObject().get("max").getAsInt()) + id.getDisplaySp()));
                    }
                }
            }

            for (int i = 0; 7 >= i; ++i) {
                Identifications id = REVERSED_ITEM_IDS.get(i);
                if (json.get(id.getItemFieldPos()) != null && json.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()) != null) {
                    JsonElement j = json.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName());
                    if (!j.isJsonObject()) {
                        label.add(new JLabel(id.getDisplayName() + " "+ setPlus(j.getAsInt()) + id.getDisplaySp()));
                    } else if (json.get("identified") != null && json.get("identified").getAsBoolean()) {
                        label.add(new JLabel(id.getDisplayName() + " " + setPlus(SearchUI.getBaseID(j.getAsJsonObject().get("max").getAsInt())) + id.getDisplaySp()));
                    } else {
                        int base = SearchUI.getBaseID(j.getAsJsonObject().get("max").getAsInt());
                        label.add(new JLabel(getReversedMax(base) + id.getDisplaySp() + " " + id.getDisplayName() + " " + getReversedMin(base) + id.getDisplaySp()));
                    }
                }
            }
        }

        label.add(new JLabel(" "));

        if (!type.equals("material") && !type.equals("tool") && json.get("tier") != null) {
            label.add(new JLabel("Rarity: " + json.get("tier").getAsString().substring(0, 1).toUpperCase() + json.get("tier").getAsString().substring(1)));
        }

        if (json.get("restrictions") != null) {
            label.add(new JLabel(json.get("restrictions").getAsString()));
        }

        JButton_Custom l = null;
        if (!isCustom) {
            if (json.get("dropRestriction") != null || json.get("dropMeta") != null) {
                label.add(new JLabel(" "));
                l = new JButton_Custom("How to Obtain (Not Perfect)");
                l.setBorderPainted(false);
                l.setOpaque(false);
                l.setBackground(Color.WHITE);
                l.setForeground(Color.BLUE);

                StringBuilder sb = new StringBuilder();
                sb.append("&fThis item can be obtained by<br>");

                JsonObject manual = new GetAPI().getHowToObtainOther();
                int p = haveManualDrop(manual, itemName);
                if (p > 0) {
                    if (p == 1) {
                        //Unobtainable
                        l.setToolTipText("<html>" + TreeCheckBox.fixesText("&fThis item can't be obtained."));
                    } else if (p == 13) {
                        //Discontinued
                        l.setToolTipText("<html>" + TreeCheckBox.fixesText("&fThis item is Discontinued.<br>" + manual.get("discontinued").getAsJsonObject().get(itemName).getAsJsonObject().get("pos").getAsString()));
                    } else {
                        if (manual.get("normal") != null) { //Normal
                            for (JsonElement je : manual.get("normal").getAsJsonArray()) {
                                if (je.getAsString().equals(itemName)) {
                                    String s = "Hostile Mob and Any Loot Chests<br>Level " + Math.max((lv - 4), 1) + " to " + (lv + 4) + "<br><br>";
                                    sb.append(s);
                                    break;
                                }
                            }
                        }
                        if (manual.get("legendary_island") != null) { //Legendary Island
                            for (JsonElement je : manual.get("legendary_island").getAsJsonArray()) {
                                if (je.getAsString().equals(itemName)) {
                                    sb.append("Legendary Island<br><br>");
                                    break;
                                }
                            }
                        }
                        setPosOnlyType(manual, sb, itemName, "dungeon", ""); //Dungeon Drop (and Forgery Chest)
                        setMerchant(manual, sb, itemName, "merchant"); //Merchant
                        setMerchant(manual, sb, itemName, "dungeonMerchant"); //Dungeon Merchant
                        setPosOnlyType(manual, sb, itemName, "the_qira_hive", "The Qira Hive: "); //The Qira Hive
                        setPosOnlyType(manual, sb, itemName, "secret_discovery", ""); //Secret Discovery
                        setPosOnlyType(manual, sb, itemName, "quest", "Quest: "); //Quest
                        setPosOnlyType(manual, sb, itemName, "raid", "Raid Rewards: "); //Raid Rewards
                        setPosOnlyType(manual, sb, itemName, "other", ""); //Other
                        if (manual.get("specific") != null && manual.get("specific").getAsJsonObject().get(itemName) != null) { //Specific
                            for (JsonElement je : manual.get("specific").getAsJsonObject().get(itemName).getAsJsonArray()) {
                                JsonObject jsp = je.getAsJsonObject();
                                if (jsp.get("ismobname") == null || jsp.get("ismobname").getAsBoolean())
                                    sb.append("Mob Name: ");
                                sb.append(jsp.get("name").getAsString());
                                if (jsp.get("pos") != null) {
                                    for (JsonElement je1 : jsp.get("pos").getAsJsonArray()) {
                                        String s = "<br>Locate: " + je1.getAsString();
                                        sb.append(s);
                                    }
                                }
                                sb.append("<br><br>");
                            }
                        }
                        l.setToolTipText("<html>" + TreeCheckBox.fixesText(sb.substring(0, sb.toString().length() - 4)));
                    }
                } else {
                    if (json.get("dropMeta") != null) {
                        JsonObject j = json.get("dropMeta").getAsJsonObject();
                        if (j.get("name") != null) sb.append(j.get("name").getAsString());
                        if (j.get("type") != null) {
                            if (j.get("type").isJsonArray() && j.get("event") != null) {
                                String s = " Merchant and " + j.get("event").getAsString() + " Event";
                                sb.append(s);
                            } else {
                                switch (j.get("type").getAsString()) {
                                    case "dungeonMerchant":
                                        sb.append(" Dungeon Merchant");
                                        break;
                                    case "raid":
                                        sb.append(" Raid Rewards");
                                        break;
                                    case "altar":
                                        sb.append(" Altar");
                                        break;
                                    case "dungeon":
                                        sb.append(" Dungeon Drop");
                                        break;
                                    default:
                                        String s = " " + j.get("type").getAsString();
                                        sb.append(s);
                                        System.out.println(itemName + " this item drop type unknown: " + s);
                                        break;
                                }
                            }
                        }
                        if (j.get("coordinates") != null) {
                            String s = "<br>Locate: " + j.get("coordinates").getAsJsonArray().get(0).getAsInt() + ", " + j.get("coordinates").getAsJsonArray().get(1).getAsInt() + ", " + j.get("coordinates").getAsJsonArray().get(2).getAsInt();
                            sb.append(s);
                        }
                        l.setToolTipText("<html>" + TreeCheckBox.fixesText(sb.toString()));
                    } else if (json.get("dropRestriction") != null) {
                        switch (json.get("dropRestriction").getAsString()) {
                            case "normal": {
                                l.setToolTipText("<html>" + TreeCheckBox.fixesText(sb + "Hostile Mob and Any Loot Chests<br>Level " + Math.max((lv - 4), 1) + " to " + (lv + 4)));
                                break;
                            }
                            case "lootchest": {
                                l.setToolTipText("<html>" + TreeCheckBox.fixesText(sb + "Tier 3 and 4 Loot Chests<br>Level " + Math.max((lv - 4), 1) + " to " + (lv + 4)));
                                break;
                            }
                            case "never": {
                                l.setToolTipText("<html>" + TreeCheckBox.fixesText("&fUnknown"));
                                break;
                            }
                            case "dungeon": {
                                l.setToolTipText("<html>" + TreeCheckBox.fixesText(sb + "Dungeon Drop"));
                                break;
                            }
                            default: {
                                l.setToolTipText("<html>" + TreeCheckBox.fixesText(sb + json.get("dropRestriction").getAsString()));
                                System.out.println(itemName + " this drop type unknown:" + json.get("dropRestriction").getAsString());
                                break;
                            }
                        }
                    }
                }
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

        if (!type.equals("tome") && !type.equals("charm")) {
            label.add(new JLabel("External Links"));
            add(label.get(label.size() - 1));
            add(dataButton);
        }

        JLabel sortValue = new JLabel("Sort Value: " + totalValue);
        sortValue.setForeground(Color.DARK_GRAY);
        add(sortValue);
    }

    public void setMerchant(JsonObject manual, StringBuilder sb, String itemName, String path) {
        if (manual.get(path) != null && manual.get(path).getAsJsonObject().get(itemName) != null) { //Merchant
            JsonObject merchant = manual.get(path).getAsJsonObject().get(itemName).getAsJsonObject();
            if (merchant.get("name") != null) {
                String s = "Merchant: " + merchant.get("name").getAsString() + "<br>";
                sb.append(s);
            }
            if (merchant.get("pos") != null) {
                String s = "Locate: " + merchant.get("pos").getAsString() + "<br>";
                sb.append(s);
            }
            if (merchant.get("price") != null) {
                String s = "Price: " + merchant.get("price").getAsString() + "<br>";
                sb.append(s);
            }
            sb.append("<br>");
        }
    }

    public void setPosOnlyType(JsonObject manual, StringBuilder sb, String itemName, String path, String description) {
        if (manual.get(path) != null && manual.get(path).getAsJsonObject().get(itemName) != null) { //Other
            String s = description + manual.get(path).getAsJsonObject().get(itemName).getAsJsonObject().get("pos").getAsString() + "<br><br>";
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

   public static int haveManualDrop(JsonObject j, String itemName) {
        if (j.get("normal") != null) { //Normal
            for (JsonElement je : j.get("normal").getAsJsonArray()) {
                if (je.getAsString().equals(itemName)) return 11;
            }
        }
        if (j.get("unobtainable") != null) { //Unobtainable
            for (JsonElement je : j.get("unobtainable").getAsJsonArray()) {
                if (je.getAsString().equals(itemName)) return 1;
            }
        }
        //Dugenon Drop and Forgery Chest
        if (j.get("dungeon") != null && j.get("dungeon").getAsJsonObject().get(itemName) != null) return 2;

        if (j.get("legendary_island") != null) { //Legendary Island
            for (JsonElement je : j.get("legendary_island").getAsJsonArray()) {
                if (je.getAsString().equals(itemName)) return 3;
            }
        }
        //Merchant
        if (j.get("merchant") != null && j.get("merchant").getAsJsonObject().get(itemName) != null) return 4;

        //The Qira Hive
       if (j.get("the_qira_hive") != null && j.get("the_qira_hive").getAsJsonObject().get(itemName) != null) return 5;

       //Secret Discovery
       if (j.get("secret_discovery") != null && j.get("secret_discovery").getAsJsonObject().get(itemName) != null) return 6;

       //Quest Rewards
       if (j.get("quest") != null && j.get("quest").getAsJsonObject().get(itemName) != null) return 7;

       //Specific
       if (j.get("specific") != null && j.get("specific").getAsJsonObject().get(itemName) != null) return 8;

       //Raid Rewards
       if (j.get("raid") != null && j.get("raid").getAsJsonObject().get(itemName) != null) return 9;

       //Other
       if (j.get("other") != null && j.get("other").getAsJsonObject().get(itemName) != null) return 10;

       //Dungeon Merchant
       if (j.get("dungeonMerchant") != null && j.get("dungeonMerchant").getAsJsonObject().get(itemName) != null) return 12;

       //Discontinued
       if (j.get("discontinued") != null && j.get("discontinued").getAsJsonObject().get(itemName) != null) return 13;

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
