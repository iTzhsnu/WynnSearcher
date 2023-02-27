package com.github.iTzhsnu.WynnSearcher.builder;

import com.github.iTzhsnu.WynnSearcher.Identifications;
import com.github.iTzhsnu.WynnSearcher.ItemUITemplate;
import com.github.iTzhsnu.WynnSearcher.builder.skilltree.TreeBase;
import com.github.iTzhsnu.WynnSearcher.builder.skilltree.TreeCheckBox;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.*;

public class ID_Display {
    private final JPanel pane = new JPanel();
    private final List<JLabel> ids = new ArrayList<>();
    private int[] id_Numbers = new int[] {};

    private static final Map<Integer, Identifications> IDS = new HashMap<Integer, Identifications>() {{
        put(0, Identifications.HEALTH);
        put(1, Identifications.HEALTH_BONUS);
        put(2, Identifications.LIFE_STEAL);

        put(3, Identifications.EARTH_DEFENSE);
        put(4, Identifications.THUNDER_DEFENSE);
        put(5, Identifications.WATER_DEFENSE);
        put(6, Identifications.FIRE_DEFENSE);
        put(7, Identifications.AIR_DEFENSE);
        put(8, Identifications.EARTH_DEFENSE_PERCENT);
        put(9, Identifications.THUNDER_DEFENSE_PERCENT);
        put(10, Identifications.WATER_DEFENSE_PERCENT);
        put(11, Identifications.FIRE_DEFENSE_PERCENT);
        put(12, Identifications.AIR_DEFENSE_PERCENT);

        put(13, Identifications.MANA_REGEN);
        put(14, Identifications.MANA_STEAL);
        put(15, Identifications.WALK_SPEED);
        put(16, Identifications.SPRINT_BONUS);
        put(17, Identifications.SPRINT_REGEN);
        put(18, Identifications.JUMP_HEIGHT);
        put(19, Identifications.THORNS);
        put(20, Identifications.REFLECTION);
        put(21, Identifications.EXPLODING);
        put(22, Identifications.STEALING);
        put(23, Identifications.COMBAT_XP_BONUS);
        put(24, Identifications.GATHERING_XP_BONUS);
        put(25, Identifications.GATHERING_SPEED_BONUS);
        put(26, Identifications.LOOT_BONUS);
        put(27, Identifications.LOOT_QUALITY);
        put(28, Identifications.SOUL_POINT_REGEN);

        put(29, Identifications.RAW_HEALTH_REGEN);
        put(30, Identifications.HEALTH_REGEN_PERCENT);
        put(31, Identifications.EARTH_DAMAGE_PERCENT);
        put(32, Identifications.THUNDER_DAMAGE_PERCENT);
        put(33, Identifications.WATER_DAMAGE_PERCENT);
        put(34, Identifications.FIRE_DAMAGE_PERCENT);
        put(35, Identifications.AIR_DAMAGE_PERCENT);

        put(36, Identifications.SPELL_DAMAGE_PERCENT);
        put(37, Identifications.RAW_SPELL_DAMAGE);
        put(38, Identifications.MELEE_DAMAGE_PERCENT);
        put(39, Identifications.RAW_MELEE_DAMAGE);
        put(40, Identifications.POISON);
        put(41, Identifications.ATTACK_SPEED_BONUS);

        put(42, Identifications.NEUTRAL_SPELL_DAMAGE_PERCENT);
        put(43, Identifications.EARTH_SPELL_DAMAGE_PERCENT);
        put(44, Identifications.THUNDER_SPELL_DAMAGE_PERCENT);
        put(45, Identifications.WATER_SPELL_DAMAGE_PERCENT);
        put(46, Identifications.FIRE_SPELL_DAMAGE_PERCENT);
        put(47, Identifications.AIR_SPELL_DAMAGE_PERCENT);
        put(48, Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT);

        put(49, Identifications.RAW_NEUTRAL_SPELL_DAMAGE);
        put(50, Identifications.RAW_EARTH_SPELL_DAMAGE);
        put(51, Identifications.RAW_THUNDER_SPELL_DAMAGE);
        put(52, Identifications.RAW_WATER_SPELL_DAMAGE);
        put(53, Identifications.RAW_FIRE_SPELL_DAMAGE);
        put(54, Identifications.RAW_AIR_SPELL_DAMAGE);
        put(55, Identifications.RAW_ELEMENTAL_SPELL_DAMAGE);

        put(56, Identifications.NEUTRAL_MELEE_DAMAGE_PERCENT);
        put(57, Identifications.EARTH_MELEE_DAMAGE_PERCENT);
        put(58, Identifications.THUNDER_MELEE_DAMAGE_PERCENT);
        put(59, Identifications.WATER_MELEE_DAMAGE_PERCENT);
        put(60, Identifications.FIRE_MELEE_DAMAGE_PERCENT);
        put(61, Identifications.AIR_MELEE_DAMAGE_PERCENT);
        put(62, Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT);

        put(63, Identifications.RAW_NEUTRAL_MELEE_DAMAGE);
        put(64, Identifications.RAW_EARTH_MELEE_DAMAGE);
        put(65, Identifications.RAW_THUNDER_MELEE_DAMAGE);
        put(66, Identifications.RAW_WATER_MELEE_DAMAGE);
        put(67, Identifications.RAW_FIRE_MELEE_DAMAGE);
        put(68, Identifications.RAW_AIR_MELEE_DAMAGE);
        put(69, Identifications.RAW_ELEMENTAL_MELEE_DAMAGE);
    }};

    private static final Map<Integer, Identifications> REVERSED_ID = new HashMap<Integer, Identifications>() {{
        put(0, Identifications.RAW_1ST_SPELL_COST);
        put(1, Identifications.RAW_2ND_SPELL_COST);
        put(2, Identifications.RAW_3RD_SPELL_COST);
        put(3, Identifications.RAW_4TH_SPELL_COST);
        put(4, Identifications.PERCENT_1ST_SPELL_COST);
        put(5, Identifications.PERCENT_2ND_SPELL_COST);
        put(6, Identifications.PERCENT_3RD_SPELL_COST);
        put(7, Identifications.PERCENT_4TH_SPELL_COST);
    }};

    public static final Map<Identifications, Integer> ID_INT = new HashMap<Identifications, Integer>() {{
        put(Identifications.HEALTH, 0);
        put(Identifications.HEALTH_BONUS, 1);
        put(Identifications.LIFE_STEAL, 2);

        put(Identifications.EARTH_DEFENSE, 3);
        put(Identifications.THUNDER_DEFENSE, 4);
        put(Identifications.WATER_DEFENSE, 5);
        put(Identifications.FIRE_DEFENSE, 6);
        put(Identifications.AIR_DEFENSE, 7);
        put(Identifications.EARTH_DEFENSE_PERCENT, 8);
        put(Identifications.THUNDER_DEFENSE_PERCENT, 9);
        put(Identifications.WATER_DEFENSE_PERCENT, 10);
        put(Identifications.FIRE_DEFENSE_PERCENT, 11);
        put(Identifications.AIR_DEFENSE_PERCENT, 12);

        put(Identifications.MANA_REGEN, 13);
        put(Identifications.MANA_STEAL, 14);
        put(Identifications.WALK_SPEED, 15);
        put(Identifications.SPRINT_BONUS, 16);
        put(Identifications.SPRINT_REGEN, 17);
        put(Identifications.JUMP_HEIGHT, 18);
        put(Identifications.THORNS, 19);
        put(Identifications.REFLECTION, 20);
        put(Identifications.EXPLODING, 21);
        put(Identifications.STEALING, 22);
        put(Identifications.COMBAT_XP_BONUS, 23);
        put(Identifications.GATHERING_XP_BONUS, 24);
        put(Identifications.GATHERING_SPEED_BONUS, 25);
        put(Identifications.LOOT_BONUS, 26);
        put(Identifications.LOOT_QUALITY, 27);
        put(Identifications.SOUL_POINT_REGEN, 28);

        put(Identifications.RAW_HEALTH_REGEN, 29);
        put(Identifications.HEALTH_REGEN_PERCENT, 30);

        put(Identifications.EARTH_DAMAGE_PERCENT, 31);
        put(Identifications.THUNDER_DAMAGE_PERCENT, 32);
        put(Identifications.WATER_DAMAGE_PERCENT, 33);
        put(Identifications.FIRE_DAMAGE_PERCENT, 34);
        put(Identifications.AIR_DAMAGE_PERCENT, 35);

        put(Identifications.SPELL_DAMAGE_PERCENT, 36);
        put(Identifications.RAW_SPELL_DAMAGE, 37);
        put(Identifications.MELEE_DAMAGE_PERCENT, 38);
        put(Identifications.RAW_MELEE_DAMAGE, 39);
        put(Identifications.POISON, 40);
        put(Identifications.ATTACK_SPEED_BONUS, 41);

        put(Identifications.NEUTRAL_SPELL_DAMAGE_PERCENT, 42);
        put(Identifications.EARTH_SPELL_DAMAGE_PERCENT, 43);
        put(Identifications.THUNDER_SPELL_DAMAGE_PERCENT, 44);
        put(Identifications.WATER_SPELL_DAMAGE_PERCENT, 45);
        put(Identifications.FIRE_SPELL_DAMAGE_PERCENT, 46);
        put(Identifications.AIR_SPELL_DAMAGE_PERCENT, 47);
        put(Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT, 48);

        put(Identifications.RAW_NEUTRAL_SPELL_DAMAGE, 49);
        put(Identifications.RAW_EARTH_SPELL_DAMAGE, 50);
        put(Identifications.RAW_THUNDER_SPELL_DAMAGE, 51);
        put(Identifications.RAW_WATER_SPELL_DAMAGE, 52);
        put(Identifications.RAW_FIRE_SPELL_DAMAGE, 53);
        put(Identifications.RAW_AIR_SPELL_DAMAGE, 54);
        put(Identifications.RAW_ELEMENTAL_SPELL_DAMAGE, 55);

        put(Identifications.NEUTRAL_MELEE_DAMAGE_PERCENT, 56);
        put(Identifications.EARTH_MELEE_DAMAGE_PERCENT, 57);
        put(Identifications.THUNDER_MELEE_DAMAGE_PERCENT, 58);
        put(Identifications.WATER_MELEE_DAMAGE_PERCENT, 59);
        put(Identifications.FIRE_MELEE_DAMAGE_PERCENT, 60);
        put(Identifications.AIR_MELEE_DAMAGE_PERCENT, 61);
        put(Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT, 62);

        put(Identifications.RAW_NEUTRAL_MELEE_DAMAGE, 63);
        put(Identifications.RAW_EARTH_MELEE_DAMAGE, 64);
        put(Identifications.RAW_THUNDER_MELEE_DAMAGE, 65);
        put(Identifications.RAW_WATER_MELEE_DAMAGE, 66);
        put(Identifications.RAW_FIRE_MELEE_DAMAGE, 67);
        put(Identifications.RAW_AIR_MELEE_DAMAGE, 68);
        put(Identifications.RAW_ELEMENTAL_MELEE_DAMAGE, 69);

        put(Identifications.RAW_1ST_SPELL_COST, 70);
        put(Identifications.RAW_2ND_SPELL_COST, 71);
        put(Identifications.RAW_3RD_SPELL_COST, 72);
        put(Identifications.RAW_4TH_SPELL_COST, 73);
        put(Identifications.PERCENT_1ST_SPELL_COST, 74);
        put(Identifications.PERCENT_2ND_SPELL_COST, 75);
        put(Identifications.PERCENT_3RD_SPELL_COST, 76);
        put(Identifications.PERCENT_4TH_SPELL_COST, 77);
    }};

    private static final Map<Integer, Float> CLASS_DEF = new HashMap<Integer, Float>() {{
        put(0, 1F); //Warrior
        put(1, 1F); //Assassin
        put(2, 0.8F); //Mage
        put(3, 0.7F); //Archer
        put(4, 0.6F); //Shaman
    }};

    public ID_Display(JPanel p) {
        pane.setPreferredSize(new Dimension(250, 2000));
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(pane);
        scrollPane.setBounds(422, 385, 268, 400);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);

        p.add(scrollPane);
    }



    public void setIDs(ItemJsons itemJsons, Damage_IDs damage_ids, SkillPoint sp, TreeBase tree, Damage_Boosts damage_boosts, List<JTextField> powders, int classID, boolean updateOnly) {
        pane.removeAll();
        if (ids.size() > 0) {
            ids.subList(0, ids.size()).clear();
        }
        int[] numbers = new int[] { //Using 0 ~ 77 and 89
                535, 0, 0, 0, 0, 0, 0, 0, 0, 0, //0 ~ 9 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //10 ~ 19 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //20 ~ 29 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //30 ~ 39 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //40 ~ 49 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //50 ~ 59 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //60 ~ 69 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //70 ~ 77 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0 //89 (Weapon Tome Damage Bonus)
        };
        float def = 2F - CLASS_DEF.get(classID); //Base Defense

        float tomeResist = 1; //Tome Resistance
        //Tomes
        if (itemJsons.getArmourTomes().size() > 0) { //Armour Tomes
            for (JsonObject j : itemJsons.getArmourTomes()) {
                tomeResist -= j.get("bonus").getAsInt() / 100F; //Armour Tome Resistance
                for (int i = 0; 27 >= i; ++i) {
                    if (i >= 3 && i <= 7) continue;
                    Identifications id = Item_Display.TOME_IDS.get(i);
                    if (j.get(id.getItemName()) != null) {
                        if (id.isItemVariable()) { //Variable
                            numbers[ID_INT.get(id)] += ItemUITemplate.getMaxInt(j.get(id.getItemName()).getAsInt());
                        } else { //Not Variable
                            numbers[ID_INT.get(id)] += j.get(id.getItemName()).getAsInt();
                        }
                    }
                }
            }
        }
        if (itemJsons.getWeaponTomes().size() > 0) { //Weapon Tomes
            for (JsonObject j : itemJsons.getWeaponTomes()) {
                numbers[89] += j.get("bonus").getAsInt(); //Weapon Tome Damage Bonus
                for (int i = 0; 27 >= i; ++i) {
                    if (i >= 3 && i <= 7) continue;
                    Identifications id = Item_Display.TOME_IDS.get(i);
                    if (j.get(id.getItemName()) != null) {
                        if (id.isItemVariable()) { //Variable
                            numbers[ID_INT.get(id)] += ItemUITemplate.getMaxInt(j.get(id.getItemName()).getAsInt());
                        } else { //Not Variable
                            numbers[ID_INT.get(id)] += j.get(id.getItemName()).getAsInt();
                        }
                    }
                }
            }
        }
        if (itemJsons.getGuildTome() != null) { //Guild Tome
            JsonObject j = itemJsons.getGuildTome();
            for (int i = 0; 27 >= i; ++i) {
                if (i >= 3 && i <= 7) continue;
                Identifications id = Item_Display.TOME_IDS.get(i);
                if (j.get(id.getItemName()) != null) {
                    if (id.isItemVariable()) { //Variable
                        numbers[ID_INT.get(id)] += ItemUITemplate.getMaxInt(j.get(id.getItemName()).getAsInt());
                    } else { //Not Variable
                        numbers[ID_INT.get(id)] += j.get(id.getItemName()).getAsInt();
                    }
                }
            }
        }

        //Weapon, Armor, Accessory
        if (itemJsons.getJsonObjectList().size() > 0) {
            for (JsonObject j : itemJsons.getJsonObjectList()) {
                for (int i = 0; 28 >= i; ++i) {
                    if (j.get(IDS.get(i).getItemName()) != null && j.get(IDS.get(i).getItemName()).getAsInt() != 0) {
                        if (!IDS.get(i).isItemVariable()) {
                            numbers[i] += j.get(IDS.get(i).getItemName()).getAsInt();
                        } else if (j.get("identified") != null && j.get("identified").getAsBoolean()) {
                            numbers[i] += j.get(IDS.get(i).getItemName()).getAsInt();
                        } else {
                            numbers[i] += ItemUITemplate.getMaxInt(j.get(IDS.get(i).getItemName()).getAsInt());
                        }
                    }
                }
                for (int i = 29; 69 >= i; ++i) {
                    if (j.get(IDS.get(i).getItemName()) != null && j.get(IDS.get(i).getItemName()).getAsInt() != 0) {
                        if (!IDS.get(i).isItemVariable()) {
                            numbers[i] += j.get(IDS.get(i).getItemName()).getAsInt();
                        } else if (j.get("identified") != null && j.get("identified").getAsBoolean()) {
                            numbers[i] += j.get(IDS.get(i).getItemName()).getAsInt();
                        } else {
                            numbers[i] += ItemUITemplate.getMaxInt(j.get(IDS.get(i).getItemName()).getAsInt());
                        }
                    }
                }
                for (int i = 0; 7 >= i; ++i) {
                    if (j.get(REVERSED_ID.get(i).getItemName()) != null && j.get(REVERSED_ID.get(i).getItemName()).getAsInt() != 0) {
                        if (j.get("identified") != null && j.get("identified").getAsBoolean()) {
                            numbers[i + 70] += j.get(REVERSED_ID.get(i).getItemName()).getAsInt();
                        } else {
                            numbers[i + 70] += ItemUITemplate.getReversedMinInt(j.get(REVERSED_ID.get(i).getItemName()).getAsInt());
                        }
                    }
                }
            }
        }

        if (itemJsons.getWeapon() != null) {
            JsonObject j = itemJsons.getWeapon();
            for (int i = 1; 28 >= i; ++i) {
                if (i >= 3 && i <= 7) continue;
                if (j.get(IDS.get(i).getItemName()) != null && j.get(IDS.get(i).getItemName()).getAsInt() != 0) {
                    if (!IDS.get(i).isItemVariable()) {
                        numbers[i] += j.get(IDS.get(i).getItemName()).getAsInt();
                    } else if (j.get("identified") != null && j.get("identified").getAsBoolean()) {
                        numbers[i] += j.get(IDS.get(i).getItemName()).getAsInt();
                    } else {
                        numbers[i] += ItemUITemplate.getMaxInt(j.get(IDS.get(i).getItemName()).getAsInt());
                    }
                }
            }
            for (int i = 29; 69 >= i; ++i) {
                if (j.get(IDS.get(i).getItemName()) != null && j.get(IDS.get(i).getItemName()).getAsInt() != 0) {
                    if (!IDS.get(i).isItemVariable()) {
                        numbers[i] += j.get(IDS.get(i).getItemName()).getAsInt();
                    } else if (j.get("identified") != null && j.get("identified").getAsBoolean()) {
                        numbers[i] += j.get(IDS.get(i).getItemName()).getAsInt();
                    } else {
                        numbers[i] += ItemUITemplate.getMaxInt(j.get(IDS.get(i).getItemName()).getAsInt());
                    }
                }
            }
            for (int i = 0; 7 >= i; ++i) {
                if (j.get(REVERSED_ID.get(i).getItemName()) != null && j.get(REVERSED_ID.get(i).getItemName()).getAsInt() != 0) {
                    if (j.get("identified") != null && j.get("identified").getAsBoolean()) {
                        numbers[i + 70] += j.get(REVERSED_ID.get(i).getItemName()).getAsInt();
                    } else {
                        numbers[i + 70] += ItemUITemplate.getReversedMinInt(j.get(REVERSED_ID.get(i).getItemName()).getAsInt());
                    }
                }
            }
        }

        JsonObject powderJ = JsonParser.parseReader(new JsonReader(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/other/powders.json")), StandardCharsets.UTF_8))).getAsJsonObject();

        //Helmet Powder
        if (itemJsons.getHelmet() != null && itemJsons.getHelmet().get("sockets") != null && itemJsons.getHelmet().get("sockets").getAsInt() > 0 && powders.get(0).getText().length() > 1) {
            for (int i = 0; (int) Math.floor(powders.get(0).getText().length() / 2F) * 2 > i; i += 2) {
                if (itemJsons.getHelmet().get("sockets").getAsInt() >= i / 2) {
                    String name = String.valueOf(powders.get(0).getText().charAt(i)) + powders.get(0).getText().charAt(i + 1);
                    if (powderJ.get(name) != null) {
                        JsonObject j = powderJ.get(name).getAsJsonObject();
                        if (j.get("earth_def") != null) numbers[ID_INT.get(Identifications.EARTH_DEFENSE)] += j.get("earth_def").getAsInt();
                        if (j.get("thunder_def") != null) numbers[ID_INT.get(Identifications.THUNDER_DEFENSE)] += j.get("thunder_def").getAsInt();
                        if (j.get("water_def") != null) numbers[ID_INT.get(Identifications.WATER_DEFENSE)] += j.get("water_def").getAsInt();
                        if (j.get("fire_def") != null) numbers[ID_INT.get(Identifications.FIRE_DEFENSE)] += j.get("fire_def").getAsInt();
                        if (j.get("air_def") != null) numbers[ID_INT.get(Identifications.AIR_DEFENSE)] += j.get("air_def").getAsInt();
                    }
                } else {
                    break;
                }
            }
        }

        //Chestplate Powder
        if (itemJsons.getChestplate() != null && itemJsons.getChestplate().get("sockets") != null && itemJsons.getChestplate().get("sockets").getAsInt() > 0 && powders.get(1).getText().length() > 1) {
            for (int i = 0; (int) Math.floor(powders.get(1).getText().length() / 2F) * 2 > i; i += 2) {
                if (itemJsons.getChestplate().get("sockets").getAsInt() >= i / 2) {
                    String name = String.valueOf(powders.get(1).getText().charAt(i)) + powders.get(1).getText().charAt(i + 1);
                    if (powderJ.get(name) != null) {
                        JsonObject j = powderJ.get(name).getAsJsonObject();
                        if (j.get("earth_def") != null) numbers[ID_INT.get(Identifications.EARTH_DEFENSE)] += j.get("earth_def").getAsInt();
                        if (j.get("thunder_def") != null) numbers[ID_INT.get(Identifications.THUNDER_DEFENSE)] += j.get("thunder_def").getAsInt();
                        if (j.get("water_def") != null) numbers[ID_INT.get(Identifications.WATER_DEFENSE)] += j.get("water_def").getAsInt();
                        if (j.get("fire_def") != null) numbers[ID_INT.get(Identifications.FIRE_DEFENSE)] += j.get("fire_def").getAsInt();
                        if (j.get("air_def") != null) numbers[ID_INT.get(Identifications.AIR_DEFENSE)] += j.get("air_def").getAsInt();
                    }
                } else {
                    break;
                }
            }
        }

        //Leggings Powder
        if (itemJsons.getLeggings() != null && itemJsons.getLeggings().get("sockets") != null && itemJsons.getLeggings().get("sockets").getAsInt() > 0 && powders.get(2).getText().length() > 1) {
            for (int i = 0; (int) Math.floor(powders.get(2).getText().length() / 2F) * 2 > i; i += 2) {
                if (itemJsons.getLeggings().get("sockets").getAsInt() >= i / 2) {
                    String name = String.valueOf(powders.get(2).getText().charAt(i)) + powders.get(2).getText().charAt(i + 1);
                    if (powderJ.get(name) != null) {
                        JsonObject j = powderJ.get(name).getAsJsonObject();
                        if (j.get("earth_def") != null) numbers[ID_INT.get(Identifications.EARTH_DEFENSE)] += j.get("earth_def").getAsInt();
                        if (j.get("thunder_def") != null) numbers[ID_INT.get(Identifications.THUNDER_DEFENSE)] += j.get("thunder_def").getAsInt();
                        if (j.get("water_def") != null) numbers[ID_INT.get(Identifications.WATER_DEFENSE)] += j.get("water_def").getAsInt();
                        if (j.get("fire_def") != null) numbers[ID_INT.get(Identifications.FIRE_DEFENSE)] += j.get("fire_def").getAsInt();
                        if (j.get("air_def") != null) numbers[ID_INT.get(Identifications.AIR_DEFENSE)] += j.get("air_def").getAsInt();
                    }
                } else {
                    break;
                }
            }
        }

        //Boots Powder
        if (itemJsons.getBoots() != null && itemJsons.getBoots().get("sockets") != null && itemJsons.getBoots().get("sockets").getAsInt() > 0 && powders.get(3).getText().length() > 1) {
            for (int i = 0; (int) Math.floor(powders.get(3).getText().length() / 2F) * 2 > i; i += 2) {
                if (itemJsons.getBoots().get("sockets").getAsInt() >= i / 2) {
                    String name = String.valueOf(powders.get(3).getText().charAt(i)) + powders.get(3).getText().charAt(i + 1);
                    if (powderJ.get(name) != null) {
                        JsonObject j = powderJ.get(name).getAsJsonObject();
                        if (j.get("earth_def") != null) numbers[ID_INT.get(Identifications.EARTH_DEFENSE)] += j.get("earth_def").getAsInt();
                        if (j.get("thunder_def") != null) numbers[ID_INT.get(Identifications.THUNDER_DEFENSE)] += j.get("thunder_def").getAsInt();
                        if (j.get("water_def") != null) numbers[ID_INT.get(Identifications.WATER_DEFENSE)] += j.get("water_def").getAsInt();
                        if (j.get("fire_def") != null) numbers[ID_INT.get(Identifications.FIRE_DEFENSE)] += j.get("fire_def").getAsInt();
                        if (j.get("air_def") != null) numbers[ID_INT.get(Identifications.AIR_DEFENSE)] += j.get("air_def").getAsInt();
                    }
                } else {
                    break;
                }
            }
        }

        if (updateOnly) { //Update Edited ID
            for (int i = 29; 69 >= i; ++i) { //Damages and Health Regen
                numbers[i] = damage_ids.getID(Damage_IDs.GET_DAMAGE_ID_NUM_FROM_ID.get(IDS.get(i))).getValue();
            }
            for (int i = 0; 7 >= i; ++i) { //Spell Costs
                numbers[i + 70] = damage_ids.getID(Damage_IDs.GET_DAMAGE_ID_NUM_FROM_ID.get(REVERSED_ID.get(i))).getValue();
            }
        } else { //Set Editable ID Original Num
            for (int i = 29; 69 >= i; ++i) { //Damages and Health Regen
                damage_ids.getID(Damage_IDs.GET_DAMAGE_ID_NUM_FROM_ID.get(IDS.get(i))).setValue(numbers[i]);
            }
            for (int i = 0; 7 >= i; ++i) { //Spell Costs
                damage_ids.getID(Damage_IDs.GET_DAMAGE_ID_NUM_FROM_ID.get(REVERSED_ID.get(i))).setValue(numbers[i + 70]);
            }
        }

        //Radiance
        if (damage_boosts.getBox().get(2).isSelected()) {
            for (int i = 1; 69 >= i; ++i) {
                if (i >= 3 && i <= 7) continue;
                if (numbers[i] > 0) numbers[i] = Math.round(numbers[i] * 1.2F);
            }
            for (int i = 0; 7 >= i; ++i) {
                if (0 > numbers[i + 70]) numbers[i + 70] = Math.round(numbers[i + 70] * 1.2F);
            }
        }

        int[] numbers_Sub = new int[] { //Using 0 ~ 77
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //0 ~ 9 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //10 ~ 19 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //20 ~ 29 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //30 ~ 39 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //40 ~ 49 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //50 ~ 59 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //60 ~ 69 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //70 ~ 77 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0 //80 (Weapon Tome Damage Bonus)
        };

        //Ability Tree
        for (TreeCheckBox tcb : tree.getTcb()) {
            if (tcb.isSelected()) {
                switch (tcb.getSkill()) {
                    case EARTH_DAMAGE: numbers_Sub[ID_INT.get(Identifications.EARTH_DAMAGE_PERCENT)] += 20;
                        break;
                    case THUNDER_DAMAGE: numbers_Sub[ID_INT.get(Identifications.THUNDER_DAMAGE_PERCENT)] += 10;
                        break;
                    case WATER_DAMAGE: numbers_Sub[ID_INT.get(Identifications.WATER_DAMAGE_PERCENT)] += 15;
                        break;
                    case FIRE_DAMAGE: numbers_Sub[ID_INT.get(Identifications.FIRE_DAMAGE_PERCENT)] += 15;
                        break;
                    case AIR_DAMAGE: numbers_Sub[ID_INT.get(Identifications.AIR_DAMAGE_PERCENT)] += 15;
                        break;
                }
                switch (tree.getClasses()) {
                    case "Warrior": {
                        switch (tcb.getSkill()) {
                            case TOUGHER_SKIN:
                            case MYTHRIL_SKIN:
                                def -= 0.05F;
                                break;
                            case VEHEMENT: numbers[ID_INT.get(Identifications.WALK_SPEED)] += Math.min((numbers[ID_INT.get(Identifications.RAW_MELEE_DAMAGE)] + numbers[ID_INT.get(Identifications.MELEE_DAMAGE_PERCENT)]) * 2, 20);
                                break;
                            case BURNING_HEART: numbers[ID_INT.get(Identifications.FIRE_DAMAGE_PERCENT)] += Math.min(Math.floor(numbers[ID_INT.get(Identifications.HEALTH_BONUS)] / 100F) * 2, 100);
                                break;
                            case RADIANT_DEVOTEE: numbers[ID_INT.get(Identifications.MANA_REGEN)] += Math.min(Math.floor(numbers[ID_INT.get(Identifications.REFLECTION)] / 4F), 10);
                                break;
                        }
                        break;
                    }
                    case "Assassin": {
                        //TODO Assassin Ability Tree
                    }
                    case "Archer": {
                        //TODO Archer Ability Tree
                    }
                    case "Mage": {
                        //TODO Mage Ability Tree
                    }
                    case "Shaman": {
                        //TODO Shaman Ability Tree
                    }
                }
            }
        }

        for (int i = 0; 77 >= i; ++i) { //ID_Main + ID_Sub = ID_Main
            numbers[i] += numbers_Sub[i];
        }

        //War Scream
        if (damage_boosts.getBox().get(0).isSelected()) def *= 0.8F;

        //Check Boxes and Slider
        switch (tree.getClasses()) {
            case "Warrior": {
                if (damage_boosts.getBox().get(5).isSelected()) def *= 0.3F; //Mantle
                if (damage_boosts.getBox().get(7).isSelected()) def *= 0.6F; //Brink of Madness
                break;
            }
            case "Assassin": {
                if (damage_boosts.getBox().get(11).isSelected()) def *= 0.2F; //Mirror Image
                break;
            }
            case "Shaman": {
                if (damage_boosts.getBox().get(13).isSelected()) numbers[ID_INT.get(Identifications.WALK_SPEED)] -= 35; //Mask of the Lunatic
                if (damage_boosts.getBox().get(14).isSelected()) def *= 0.65F; //Mask of the Fanatic
                if (damage_boosts.getBox().get(15).isSelected()) { //Mask of the Coward
                    numbers[ID_INT.get(Identifications.WALK_SPEED)] += 80;
                    def *= 1.2F;
                }
                if (damage_boosts.getBox().get(16).isSelected()) def *= 0.3F; //Chant of the Fanatic
                break;
            }
        }

        id_Numbers = numbers; //Copy ID Numbers

        def *= tomeResist; //Defense * Tome Resistance

        int health = numbers[0] + numbers[1]; //Health + Health Bonus
        if (health < 5) health = 5;
        float defSP = sp.getSkillPoint(SkillPoint.SkillPointType.DEFENSE).getSPBoost() - 1F; //Defense Skill Point
        float agiSP = sp.getSkillPoint(SkillPoint.SkillPointType.AGILITY).getSPBoost() - 1F; //Agility Skill Point
        float ehp = health / (0.1F * agiSP + (1F - agiSP) * (1F - defSP)) / def; //Effective Health Calc
        ids.add(new JLabel("Health: " + health)); //Total Health
        ids.add(new JLabel("EHP: " + ehp)); //Effective Health
        ids.add(new JLabel("EHP (No Agi): " + (health / ((1F - defSP) * def)))); //Effective Health (No Agility)
        ids.add(new JLabel("HPR: " + (numbers[29] * ((100F + numbers[30]) / 100F)))); //Total Health Regen
        ids.add(new JLabel("Life Steal: " + numbers[2] + "/3s")); //Life Steal
        ids.add(new JLabel(" "));
        for (int i = 3; 7 >= i; ++i) { //Defenses
            ids.add(new JLabel(IDS.get(i).getDisplayName() + ": " + (int) Math.floor(numbers[i] * ((100F + numbers[i + 5]) / 100F))));
        }
        ids.add(new JLabel(" "));
        if (numbers[1] != 0) ids.add(new JLabel(IDS.get(1).getDisplayName() + ": " + numbers[1] + IDS.get(1).getDisplaySp())); //Health Bonus
        for (int i = 13; 69 >= i; ++i) { //Normal IDS
            if (numbers[i] != 0) ids.add(new JLabel(IDS.get(i).getDisplayName() + ": " + numbers[i] + IDS.get(i).getDisplaySp()));
        }
        for (int i = 0; 7 >= i; ++i) { //Reversed IDS
            if (numbers[i + 70] != 0) ids.add(new JLabel(REVERSED_ID.get(i).getDisplayName() + ": " + numbers[i + 70] + REVERSED_ID.get(i).getDisplaySp()));
        }

        for (JLabel l : ids) {
            pane.add(l);
        }
        SwingUtilities.updateComponentTreeUI(pane);
    }

    public int[] getId_Numbers() {
        return id_Numbers;
    }
}
