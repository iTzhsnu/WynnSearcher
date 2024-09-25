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
import java.util.*;
import java.util.List;

public class ItemUITemplate extends JPanel {
    private final JsonObject json;
    private final List<JLabel> label = new ArrayList<>();
    private final float totalValue;
    private final boolean isCustom;
    private int urlSize = 0;

    public static final Map<Integer, Identifications> ITEM_IDS = new HashMap<Integer, Identifications>(82, 2);

    public static final Map<Integer, Identifications> REVERSED_ITEM_IDS = new HashMap<Integer, Identifications>(8, 2) {{
       put(0, Identifications.PERCENT_1ST_SPELL_COST);
       put(1, Identifications.PERCENT_2ND_SPELL_COST);
       put(2, Identifications.PERCENT_3RD_SPELL_COST);
       put(3, Identifications.PERCENT_4TH_SPELL_COST);

       put(4, Identifications.RAW_1ST_SPELL_COST);
       put(5, Identifications.RAW_2ND_SPELL_COST);
       put(6, Identifications.RAW_3RD_SPELL_COST);
       put(7, Identifications.RAW_4TH_SPELL_COST);
    }};

    public static final Map<Integer, Identifications> DAMAGE_IDS = new HashMap<Integer, Identifications>(6, 2) {{
        put(0, Identifications.NEUTRAL_DAMAGE);
        put(1, Identifications.EARTH_DAMAGE);
        put(2, Identifications.THUNDER_DAMAGE);
        put(3, Identifications.WATER_DAMAGE);
        put(4, Identifications.FIRE_DAMAGE);
        put(5, Identifications.AIR_DAMAGE);
    }};

    public static final Map<Integer, Identifications> DEFENSE_IDS = new HashMap<Integer, Identifications>(6, 2) {{
        put(0, Identifications.HEALTH);
        put(1, Identifications.EARTH_DEFENSE);
        put(2, Identifications.THUNDER_DEFENSE);
        put(3, Identifications.WATER_DEFENSE);
        put(4, Identifications.FIRE_DEFENSE);
        put(5, Identifications.AIR_DEFENSE);
    }};

    public static final Map<Integer, Identifications> SP_REQUESTS = new HashMap<Integer, Identifications>(5, 2) {{
        put(0, Identifications.STRENGTH_REQ);
        put(1, Identifications.DEXTERITY_REQ);
        put(2, Identifications.INTELLIGENCE_REQ);
        put(3, Identifications.DEFENSE_REQ);
        put(4, Identifications.AGILITY_REQ);
    }};

    public static final Map<Integer, Identifications> INGREDIENT_EFFECTIVENESS = new HashMap<Integer, Identifications>(6, 2) {{
        put(0, Identifications.INGREDIENT_EFFECTIVENESS_ABOVE);
        put(1, Identifications.INGREDIENT_EFFECTIVENESS_UNDER);
        put(2, Identifications.INGREDIENT_EFFECTIVENESS_LEFT);
        put(3, Identifications.INGREDIENT_EFFECTIVENESS_RIGHT);
        put(4, Identifications.INGREDIENT_EFFECTIVENESS_NOT_TOUCHING);
        put(5, Identifications.INGREDIENT_EFFECTIVENESS_TOUCHING);
    }};

    public ItemUITemplate(JsonObject json, ItemType type, JPanel previous, JPanel above, int uiWidth, float totalValue, boolean isCustom) {
        this.json = json;
        this.totalValue = totalValue;
        this.isCustom = isCustom;

        switch (type) {
            case ITEM:
                if (!isCustom) urlSize = 56;
                setItemDisplay();
                break;
            case INGREDIENT:
                if (!isCustom) urlSize = 32;
                setIngDisplay();
                break;
            case OTHER:
                if (!isCustom && !json.get("type").getAsString().equals("charm") && !json.get("type").getAsString().equals("tome")) urlSize = 32;
                setOtherDisplay();
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
        if (json.get(Identifications.RARITY.getItemName()) != null) {
            switch (json.get(Identifications.RARITY.getItemName()).getAsString()) {
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
        } else if (json.get(Identifications.RARITY.getIngName()) != null) {
            try {
                switch (json.get(Identifications.RARITY.getIngName()).getAsInt()) {
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
                System.out.println("old item found.");
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

        if (json.get("weaponType") != null) {
            label.add(new JLabel("Type: " + json.get("weaponType").getAsString().substring(0, 1).toUpperCase() + json.get("weaponType").getAsString().substring(1)));
        } else if (json.get("armourType") != null) {
            label.add(new JLabel("Type: " + json.get("armourType").getAsString().substring(0, 1).toUpperCase() + json.get("armourType").getAsString().substring(1)));
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
                if (json.get(Identifications.RARITY.getItemName()) != null && json.get(Identifications.RARITY.getItemName()).getAsString().equals("crafted")) {
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
                    if (json.get(Identifications.RARITY.getItemName()) != null && !json.get(Identifications.RARITY.getItemName()).getAsString().equals("crafted")) {
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
                if (json.get(Identifications.RARITY.getItemName()) != null && json.get(Identifications.RARITY.getItemName()).getAsString().equals("crafted")) {
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
            if (json.get(id.getItemFieldPos()) != null && json.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()) != null) {
                JsonElement j = json.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName());
                if (!j.isJsonObject()) { //Constant Value
                    label.add(new JLabel(id.getDisplayName() + " "+ setPlus(j.getAsInt()) + id.getDisplaySp()));
                } else if (json.get("identified") != null && json.get("identified").getAsBoolean()) { //Identified Items
                    String minOrMax = "max";
                    if (j.getAsJsonObject().get("max").getAsInt() < 0) minOrMax = "min";
                    label.add(new JLabel(id.getDisplayName() + " " + setPlus(SearchUI.getBaseID(j.getAsJsonObject().get(minOrMax).getAsInt())) + id.getDisplaySp()));
                } else if (id.isItemVariable() || json.get(Identifications.RARITY.getItemName()).getAsString().equals("crafted")) { //Crafted Items or Variable ID
                    label.add(new JLabel(setPlus(j.getAsJsonObject().get("min").getAsInt()) + id.getDisplaySp() + " " + id.getDisplayName() + " " + setPlus(j.getAsJsonObject().get("max").getAsInt()) + id.getDisplaySp()));
                }
            }
        }

        for (int i = 0; REVERSED_ITEM_IDS.size() > i; ++i) {
            Identifications id = REVERSED_ITEM_IDS.get(i);
            if (json.get(id.getItemFieldPos()) != null && json.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()) != null) {
                JsonElement j = json.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName());
                if (!j.isJsonObject()) { //Constant Value
                    label.add(new JLabel(id.getDisplayName() + " "+ setPlus(j.getAsInt()) + id.getDisplaySp()));
                } else if (json.get("identified") != null && json.get("identified").getAsBoolean()) { //Identified Items
                    if (j.getAsJsonObject().get("raw") == null) {
                        label.add(new JLabel(id.getDisplayName() + " " + setPlus(SearchUI.getBaseID(j.getAsJsonObject().get("max").getAsInt())) + id.getDisplaySp()));
                    } else {
                        label.add(new JLabel(id.getDisplayName() + " " + setPlus(j.getAsJsonObject().get("raw").getAsInt()) + id.getDisplaySp()));
                    }
                } else {
                    if (json.get(Identifications.RARITY.getItemName()) != null && json.get(Identifications.RARITY.getItemName()).getAsString().equals("crafted")) { //Crafted Items
                        label.add(new JLabel(setPlus(j.getAsJsonObject().get("min").getAsInt()) + id.getDisplaySp() + " " + id.getDisplayName() + " " + setPlus(j.getAsJsonObject().get("max").getAsInt()) + id.getDisplaySp()));
                    } else { //Normal Items
                        if (j.getAsJsonObject().get("raw") == null) {
                            int base = SearchUI.getBaseID(j.getAsJsonObject().get("max").getAsInt());
                            label.add(new JLabel(getReversedMax(base) + id.getDisplaySp() + " " + id.getDisplayName() + " " + getReversedMin(base) + id.getDisplaySp()));
                        } else {
                            label.add(new JLabel(setPlus(j.getAsJsonObject().get("min").getAsInt()) + id.getDisplaySp() + " " + id.getDisplayName() + " " + setPlus(j.getAsJsonObject().get("max").getAsInt()) + id.getDisplaySp()));
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
                if (p > 0 && (p != 14 || json.get("dropMeta") == null)) {
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
                        setPosOnlyType(manual, sb, itemName, "world_event", "World Event: "); //World Event
                        setPosOnlyType(manual, sb, itemName, "lootrun", ""); //Lootrun
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
                        boolean skipLocate = false;
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
                                    case "merchant":
                                        sb.append(" Merchant");
                                        break;
                                    case "lootrun":
                                        skipLocate = true;
                                        break;
                                    default:
                                        String s = " " + j.get("type").getAsString();
                                        sb.append(s);
                                        System.out.println(itemName + " has unknown drop type: " + s);
                                        break;
                                }
                            }
                        }
                        if (j.get("coordinates") != null && !skipLocate) {
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
                                System.out.println(itemName + " has unknown drop type:" + json.get("dropRestriction").getAsString());
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
        if (json.get("requirements") != null && json.get("requirements").getAsJsonObject().get(Identifications.LEVEL.getIngName()) != null) {
            lv = json.get("requirements").getAsJsonObject().get(Identifications.LEVEL.getIngName()).getAsInt();
            label.add(new JLabel("Lv. Min: " + lv));
        }

        label.add(new JLabel(" "));

        if (json.get("itemOnlyIDs") != null) {
            JsonObject j = json.getAsJsonObject("itemOnlyIDs").getAsJsonObject();
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

        if (json.get("consumableOnlyIDs") != null) {
            JsonObject j = json.get("consumableOnlyIDs").getAsJsonObject();
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

        if (json.get("ingredientPositionModifiers") != null) {
            JsonObject j = json.get("ingredientPositionModifiers").getAsJsonObject();
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

        if (json.get("identifications") != null) {
            JsonObject j = json.get("identifications").getAsJsonObject();
            boolean run = false;

            for (int i = 0; ITEM_IDS.size() > i; ++i) {
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

            for (int i = 0; REVERSED_ITEM_IDS.size() > i; ++i) {
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
                setPosOnlyType(manual, sb, itemName, "raid", "Raid Rewards: "); //Raid Rewards
                setPosOnlyType(manual, sb, itemName, "world_event", "World Event: "); //World Event
                setPosOnlyType(manual, sb, itemName, "lootrun", "Lootrun End Rewards: "); //Lootrun
            }

            if (json.get("droppedBy") != null) {
                for (JsonElement je : json.get("droppedBy").getAsJsonArray()) {
                    JsonObject j = je.getAsJsonObject();
                    if (j.get("name") != null) {
                        if (j.get("name").getAsString().equals("Ingredient Dummy")) continue;
                        String s = "Mob Name: " + j.get("name").getAsString() + "<br>";
                        sb.append(s);
                        if (j.get("coords") != null && j.get("coords").isJsonArray()) {
                            if (j.get("coords").getAsJsonArray().get(0).isJsonArray()) {
                                for (int i = 0; j.get("coords").getAsJsonArray().size() > i; ++i) {
                                    JsonArray ja = j.get("coords").getAsJsonArray().get(i).getAsJsonArray();
                                    String s1 = "Locate " + (i + 1) + ": " + ja.get(0).getAsInt() + ", " + ja.get(1).getAsInt() + ", " + ja.get(2).getAsInt() + " | Radius: " + (ja.get(3).getAsInt() / 2F) + "<br>";
                                    sb.append(s1);
                                    if (i == j.get("coords").getAsJsonArray().size() - 1) sb.append("<br>");
                                }
                            } else {
                                JsonArray ja = j.get("coords").getAsJsonArray();
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
            if (type.equals("material")) {
                s = "Refined%20" + json.get("name").getAsString().replaceAll(" ", "%20");
            }
            dataButton.setToolTipText("https://www.wynndata.tk/i/" + s);
        }

        label.add(new JLabel("Type: " + type.substring(0, 1).toUpperCase() + type.substring(1)));

        if (json.get("tomeType") != null) {
            String s;
            switch (json.get("tomeType").getAsString()) {
                case "guild_tome": //x1 Slots
                    s = "Guild";
                    break;
                case "marathon_tome": //x2 Slots
                    s = "Marathon";
                    break;
                case "expertise_tome": //x2 Slots
                    s = "Expertise";
                    break;
                case "lootrun_tome": //x1 Slots
                    s = "Lootrun";
                    break;
                case "mysticism_tome": //x2 Slots
                    s = "Mysticism";
                    break;
                case "weapon_tome": //x2 Slots
                    s = "Weapon";
                    break;
                case "armour_tome": //x4 Slots
                    s = "Armour";
                    break;
                default:
                    s = json.get("tomeType").getAsString();
                    System.out.println(itemName + " has unknown tome type: " + s);
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

        if (type.equals("charm") && json.get(Identifications.LEVELED_XP_BONUS.getItemFieldPos()) != null && json.get("requirements") != null) {
            JsonObject j = json.get(Identifications.LEVELED_XP_BONUS.getItemFieldPos()).getAsJsonObject();
            int min = json.get("requirements").getAsJsonObject().get("levelRange").getAsJsonObject().get("min").getAsInt();
            int max = json.get("requirements").getAsJsonObject().get("levelRange").getAsJsonObject().get("max").getAsInt();

            if (j.get(Identifications.LEVELED_XP_BONUS.getItemName()) != null) label.add(new JLabel(setPlus(j.get(Identifications.LEVELED_XP_BONUS.getItemName()).getAsJsonObject().get("min").getAsInt()) + "% XP from Lv." + min + "-" + max + " contents " + setPlus(j.get(Identifications.LEVELED_XP_BONUS.getItemName()).getAsJsonObject().get("max").getAsInt()) + "%"));
            if (j.get(Identifications.DAMAGE_FROM_MOBS.getItemName()) != null) label.add(new JLabel(setPlus(j.get(Identifications.DAMAGE_FROM_MOBS.getItemName()).getAsJsonObject().get("min").getAsInt()) + "% Damage taken from mobs " + setPlus(j.get(Identifications.DAMAGE_FROM_MOBS.getItemName()).getAsJsonObject().get("max").getAsInt()) + "%"));
            if (j.get(Identifications.LEVELED_LOOT_BONUS.getItemName()) != null) label.add(new JLabel(setPlus(j.get(Identifications.LEVELED_LOOT_BONUS.getItemName()).getAsJsonObject().get("min").getAsInt()) + "% Loot from Lv." + min + "-" + max + " contents " + setPlus(j.get(Identifications.LEVELED_LOOT_BONUS.getItemName()).getAsJsonObject().get("max").getAsInt()) + "%"));
        }

        if (type.equals("tome") || type.equals("charm")) { //IDs
            for (int i = 0; ITEM_IDS.size() > i; ++i) {
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

            for (int i = 0; REVERSED_ITEM_IDS.size() > i; ++i) {
                Identifications id = REVERSED_ITEM_IDS.get(i);
                if (json.get(id.getItemFieldPos()) != null && json.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()) != null) {
                    JsonElement j = json.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName());
                    if (!j.isJsonObject()) {
                        label.add(new JLabel(id.getDisplayName() + " "+ setPlus(j.getAsInt()) + id.getDisplaySp()));
                    } else if (json.get("identified") != null && json.get("identified").getAsBoolean()) {
                        if (j.getAsJsonObject().get("raw") == null) {
                            label.add(new JLabel(id.getDisplayName() + " " + setPlus(SearchUI.getBaseID(j.getAsJsonObject().get("max").getAsInt())) + id.getDisplaySp()));
                        } else {
                            label.add(new JLabel(id.getDisplayName() + " " + setPlus(j.getAsJsonObject().get("raw").getAsInt()) + id.getDisplaySp()));
                        }
                    } else {
                        if (j.getAsJsonObject().get("raw") == null) {
                            int base = SearchUI.getBaseID(j.getAsJsonObject().get("max").getAsInt());
                            label.add(new JLabel(getReversedMax(base) + id.getDisplaySp() + " " + id.getDisplayName() + " " + getReversedMin(base) + id.getDisplaySp()));
                        } else {
                            label.add(new JLabel(setPlus(j.getAsJsonObject().get("min").getAsInt()) + id.getDisplaySp() + " " + id.getDisplayName() + " " + setPlus(j.getAsJsonObject().get("max").getAsInt()) + id.getDisplaySp()));
                        }
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
                if (p > 0 && p != 14) {
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
                        setPosOnlyType(manual, sb, itemName, "lootrun", ""); //Lootrun
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
                        boolean skipLocate = false;
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
                                    case "merchant":
                                        sb.append(" Merchant");
                                        break;
                                    case "lootrun":
                                        skipLocate = true;
                                        break;
                                    default:
                                        String s = " " + j.get("type").getAsString();
                                        sb.append(s);
                                        System.out.println(itemName + "has unknown drop type: " + s);
                                        break;
                                }
                            }
                        }
                        if (j.get("coordinates") != null && !skipLocate) {
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
                                System.out.println(itemName + " has unknown drop type:" + json.get("dropRestriction").getAsString());
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
        if (j.get("merchant") != null && j.get("merchant").getAsJsonObject().get(itemName) != null) {
            if (!j.get("merchant").getAsJsonObject().get(itemName).getAsJsonObject().entrySet().isEmpty()) {
                return 4;
            } else {
                return 14;
            }
        }

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

       //World Event
       if (j.get("world_event") != null && j.get("world_event").getAsJsonObject().get(itemName) != null) return 15;

       //Lootrun
       if (j.get("lootrun") != null && j.get("lootrun").getAsJsonObject().get(itemName) != null) return 16;

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
