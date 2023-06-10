package com.github.iTzhsnu.WynnSearcher;

import com.github.iTzhsnu.WynnSearcher.builder.skilltree.TreeCheckBox;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.swing.*;
import java.awt.*;
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
        int urlSize = 0;

        switch (type) {
            case "item":
                setItemDisplay();
                if (!isCustom) urlSize = 56;
                break;
            case "ingredient":
                setIngDisplay();
                if (!isCustom) urlSize = 32;
                break;
            case "other":
                setOtherDisplay();
                if (!isCustom && !json.get("type").getAsString().equals("charm") && !json.get("type").getAsString().equals("tome")) urlSize = 32;
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

        if (json.get("name") != null) {
            label.add(new JLabel(json.get("name").getAsString()));
            String name = json.get("name").getAsString().replaceAll(" ", "%20");
            dataButton.setToolTipText("https://www.wynndata.tk/i/" + name);
            builderButton.setToolTipText("https://hppeng-wynn.github.io/item/#" + name);
        }

        if (json.get("type") != null) {
            label.add(new JLabel("Type: " + json.get("type").getAsString()));
        } else if (json.get("accessoryType") != null) {
            label.add(new JLabel("Type: " + json.get("accessoryType").getAsString()));
        }

        if (json.get("attackSpeed") != null) {
            label.add(new JLabel("Attack Speed: " + json.get("attackSpeed").getAsString()));
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

        for (int i = 0; 76 >= i; ++i) {
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
            label.add(new JLabel("Rarity: " + json.get("tier").getAsString()));
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

        if (json.get("dropRestriction") != null || json.get("dropMeta") != null) {
            label.add(new JLabel(" "));
            JLabel_Custom l = new JLabel_Custom("How to Obtain (Not Perfect)");
            l.setForeground(Color.BLUE);
            if (json.get("dropMeta") != null) {
                if (json.get("dropMeta").isJsonObject()) {
                    JsonObject j = json.get("dropMeta").getAsJsonObject();
                    StringBuilder sb = new StringBuilder();
                    sb.append("&fThis item can be obtained by<br>");
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
                                    sb.append(" Raid");
                                    break;
                                case "altar":
                                    sb.append(" Altar");
                                    break;
                                case "dungeon":
                                    sb.append(" Dungeon");
                                    break;
                                default:
                                    String s = " " + j.get("type").getAsString();
                                    sb.append(s);
                                    System.out.println(j.get("name").getAsString() + " this item drop type unknown:" + s);
                                    break;
                            }
                        }
                    }
                    if (j.get("coordinates") != null) {
                        String s = "<br>Locate: " + j.get("coordinates").getAsJsonArray().get(0).getAsInt() + ", " + j.get("coordinates").getAsJsonArray().get(1).getAsInt() + ", " + j.get("coordinates").getAsJsonArray().get(2).getAsInt();
                        sb.append(s);
                    }

                    l.setToolTipText("<html>" + TreeCheckBox.fixesText(sb.toString()));
                }
            } else if (json.get("dropRestriction") != null) {
                switch (json.get("dropRestriction").getAsString()) {
                    case "normal":
                        l.setToolTipText("<html>" + TreeCheckBox.fixesText("&fHostile Mob and Any Loot Chests<br>Level " + (lv - 4) + " to " + (lv + 4)));
                        break;
                    case "lootchest":
                        l.setToolTipText("<html>" + TreeCheckBox.fixesText("&fTier 3 and 4 Loot Chests<br>Level " + (lv - 4) + " to " + (lv + 4)));
                        break;
                    case "never":
                        l.setToolTipText("<html>" + TreeCheckBox.fixesText("&fUnknown"));
                        break;
                    case "dungeon":
                        l.setToolTipText("<html>" + TreeCheckBox.fixesText("&fDungeon Drop"));
                        break;
                    default:
                        l.setToolTipText("<html>" + TreeCheckBox.fixesText("&f" + json.get("dropRestriction").getAsString()));
                        System.out.println(json.get("name").getAsString() + " this drop type unknown:" + json.get("dropRestriction").getAsString());
                        break;
                }
            }

            label.add(l);
        }

        if (!isCustom) {
            label.add(new JLabel(" "));
            label.add(new JLabel("External Links"));
        }

        for (JLabel jLabel : label) {
            add(jLabel);
        }

        if (!isCustom) {
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

        if (json.get("name") != null) {
            label.add(new JLabel(json.get("name").getAsString()));
            dataButton.setToolTipText("https://www.wynndata.tk/i/" + json.get("name").getAsString().replaceAll(" ", "%20"));
        }

        if (json.get("tier") != null) {
            label.add(new JLabel("Star: " + json.get("tier").getAsInt()));
        }

        if (json.get("requirements") != null && json.get("requirements").getAsJsonObject().get("level") != null) {
            label.add(new JLabel("Lv. Min: " + json.get("requirements").getAsJsonObject().get("level").getAsInt()));
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

            for (int i = 0; 76 >= i; ++i) {
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
                label.add(new JLabel(j.get(i).getAsString()));
            }
            label.add(new JLabel(" "));
        }

        if (json.get("droppedBy") != null) {
            JLabel_Custom l = new JLabel_Custom("How to Obtain (Not Perfect)");
            l.setForeground(Color.BLUE);
            JsonObject j = json.get("droppedBy").getAsJsonObject();
            StringBuilder sb = new StringBuilder();
            sb.append("&fThis ingredient can be dropped by:<br>");
            for (Map.Entry<String, JsonElement> entry : json.get("droppedBy").getAsJsonObject().entrySet()) {
                if (j.get(entry.getKey()).isJsonArray()) {
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
                    String s = "Mob Name: " + entry.getKey() + "<br><br>";
                    sb.append(s);
                }
            }
            l.setToolTipText("<html>" + TreeCheckBox.fixesText(sb.substring(0, sb.toString().length() - 4)));
            label.add(l);
            label.add(new JLabel(" "));
        }

        label.add(new JLabel("External Links"));

        for (JLabel jLabel : label) {
            add(jLabel);
        }

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

        if (json.get("name") != null) {
            label.add(new JLabel(json.get("name").getAsString()));
            String s = json.get("name").getAsString().replaceAll(" ", "%20");
            if (type.equals("tool")) {
                s = s.substring(4);
            } else if (type.equals("material")) {
                s = "Refined%20" + json.get("name").getAsString().replaceAll(" ", "%20");
            }
            dataButton.setToolTipText("https://www.wynndata.tk/i/" + s);
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
            for (int i = 0; 76 >= i; ++i) {
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
            label.add(new JLabel("Rarity: " + json.get("tier").getAsString()));
            label.add(new JLabel(" "));
        }

        if (json.get("dropRestriction") != null || json.get("dropMeta") != null) {
            JLabel_Custom l = new JLabel_Custom("How to Obtain (Not Perfect)");
            l.setForeground(Color.BLUE);
            if (json.get("dropMeta") != null) {
                if (json.get("dropMeta").isJsonObject()) {
                    JsonObject j = json.get("dropMeta").getAsJsonObject();
                    StringBuilder sb = new StringBuilder();
                    sb.append("&fThis item can be obtained by<br>");
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
                                    sb.append(" Raid");
                                    break;
                                case "altar":
                                    sb.append(" Altar");
                                    break;
                                case "dungeon":
                                    sb.append(" Dungeon");
                                    break;
                                case "guild":
                                    break;
                                default:
                                    String s = " " + j.get("type").getAsString();
                                    sb.append(s);
                                    System.out.println(j.get("name").getAsString() + " this item drop type unknown:" + s);
                                    break;
                            }
                        }
                    }
                    if (j.get("coordinates") != null) {
                        String s = "<br>Locate: " + j.get("coordinates").getAsJsonArray().get(0).getAsInt() + ", " + j.get("coordinates").getAsJsonArray().get(1).getAsInt() + ", " + j.get("coordinates").getAsJsonArray().get(2).getAsInt();
                        sb.append(s);
                    }

                    l.setToolTipText("<html>" + TreeCheckBox.fixesText(sb.toString()));
                }
            } else if (json.get("dropRestriction") != null) {
                switch (json.get("dropRestriction").getAsString()) {
                    case "normal":
                        l.setToolTipText("<html>" + TreeCheckBox.fixesText("&fHostile Mob and Any Loot Chests<br>Level " + (lv - 4) + " to " + (lv + 4)));
                        break;
                    case "lootchest":
                        l.setToolTipText("<html>" + TreeCheckBox.fixesText("&fTier 3 and 4 Loot Chests<br>Level " + (lv - 4) + " to " + (lv + 4)));
                        break;
                    case "never":
                        l.setToolTipText("<html>" + TreeCheckBox.fixesText("&fUnknown"));
                        break;
                    case "dungeon":
                        l.setToolTipText("<html>" + TreeCheckBox.fixesText("&fDungeon Drop"));
                        break;
                    default:
                        l.setToolTipText("<html>" + TreeCheckBox.fixesText("&f" + json.get("dropRestriction").getAsString()));
                        System.out.println(json.get("name").getAsString() + " this drop type unknown:" + json.get("dropRestriction").getAsString());
                        break;
                }
            }

            label.add(l);
            label.add(new JLabel(" "));
        }

        if (!type.equals("tome") && !type.equals("charm")) label.add(new JLabel("External Links"));

        for (JLabel jLabel : label) {
            add(jLabel);
        }

        if (!type.equals("tome") && !type.equals("charm")) add(dataButton);

        JLabel sortValue = new JLabel("Sort Value: " + totalValue);
        sortValue.setForeground(Color.DARK_GRAY);
        add(sortValue);
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
}
