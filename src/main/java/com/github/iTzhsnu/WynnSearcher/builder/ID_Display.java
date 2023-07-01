package com.github.iTzhsnu.WynnSearcher.builder;

import com.github.iTzhsnu.WynnSearcher.Identifications;
import com.github.iTzhsnu.WynnSearcher.ItemUITemplate;
import com.github.iTzhsnu.WynnSearcher.SearchUI;
import com.github.iTzhsnu.WynnSearcher.builder.skilltree.TreeBase;
import com.github.iTzhsnu.WynnSearcher.builder.skilltree.TreeCheckBox;
import com.google.gson.JsonElement;
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

        put(70, Identifications.ELEMENTAL_DAMAGE_PERCENT);
        put(71, Identifications.RAW_ELEMENTAL_DAMAGE);
        put(72, Identifications.RAW_NEUTRAL_DAMAGE);
        put(73, Identifications.RAW_EARTH_DAMAGE);
        put(74, Identifications.RAW_THUNDER_DAMAGE);
        put(75, Identifications.RAW_WATER_DAMAGE);
        put(76, Identifications.RAW_FIRE_DAMAGE);
        put(77, Identifications.RAW_AIR_DAMAGE);
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

        put(Identifications.ELEMENTAL_DAMAGE_PERCENT, 70);
        put(Identifications.RAW_ELEMENTAL_DAMAGE, 71);
        put(Identifications.RAW_NEUTRAL_DAMAGE, 72);
        put(Identifications.RAW_EARTH_DAMAGE, 73);
        put(Identifications.RAW_THUNDER_DAMAGE, 74);
        put(Identifications.RAW_WATER_DAMAGE, 75);
        put(Identifications.RAW_FIRE_DAMAGE, 76);
        put(Identifications.RAW_AIR_DAMAGE, 77);

        put(Identifications.RAW_1ST_SPELL_COST, 78);
        put(Identifications.RAW_2ND_SPELL_COST, 79);
        put(Identifications.RAW_3RD_SPELL_COST, 80);
        put(Identifications.RAW_4TH_SPELL_COST, 81);
        put(Identifications.PERCENT_1ST_SPELL_COST, 82);
        put(Identifications.PERCENT_2ND_SPELL_COST, 83);
        put(Identifications.PERCENT_3RD_SPELL_COST, 84);
        put(Identifications.PERCENT_4TH_SPELL_COST, 85);
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
        scrollPane.setBounds(422, 435, 268, 400);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);

        p.add(scrollPane);
    }



    public void setIDs(ItemJsons itemJsons, Damage_IDs damage_ids, SkillPoint sp, TreeBase tree, Damage_Boosts damage_boosts, Powder_Effects powder_effects, List<JTextField> powders, int classID, boolean updateOnly) {
        pane.removeAll();
        if (ids.size() > 0) {
            ids.subList(0, ids.size()).clear();
        }
        int[] numbers = new int[] { //Using 0 ~ 79 and 89
                535, 0, 0, 0, 0, 0, 0, 0, 0, 0, //0 ~ 9 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //10 ~ 19 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //20 ~ 29 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //30 ~ 39 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //40 ~ 49 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //50 ~ 59 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //60 ~ 69 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //70 ~ 79 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0 //80 ~ 85 (ID), 89 (Weapon Tome Damage Bonus)
        };
        int[] numbers_Sub = new int[] { //Using 0 ~ 79
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //0 ~ 9 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //10 ~ 19 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //20 ~ 29 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //30 ~ 39 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //40 ~ 49 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //50 ~ 59 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //60 ~ 69 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //70 ~ 79 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0 //80 ~ 85 (ID), 89 (Weapon Tome Damage Bonus)
        };
        float def = 2F - CLASS_DEF.get(classID); //Base Defense

        float tomeResist = 1; //Tome Resistance
        //Tomes
        if (itemJsons.getArmourTomes().size() > 0) { //Armour Tomes
            for (JsonObject j : itemJsons.getArmourTomes()) {
                tomeResist -= j.get("base").getAsJsonObject().get("defenceToMobs").getAsInt() / 100F; //Armour Tome Resistance
                setNumbers(j, numbers);
                setNumbers(j, numbers_Sub);
            }
        }
        if (itemJsons.getWeaponTomes().size() > 0) { //Weapon Tomes
            for (JsonObject j : itemJsons.getWeaponTomes()) {
                numbers[89] += j.get("base").getAsJsonObject().get("damageToMobs").getAsInt(); //Weapon Tome Damage Bonus
                setNumbers(j, numbers);
                setNumbers(j, numbers_Sub);
            }
        }
        if (itemJsons.getGuildTome() != null) { //Guild Tome
            setNumbers(itemJsons.getGuildTome(), numbers);
        }

        //Weapon, Armor, Accessory
        if (itemJsons.getJsonObjectList().size() > 0) {
            for (JsonObject j : itemJsons.getJsonObjectList()) {
                setNumbers(j, numbers);
            }
        }

        if (itemJsons.getWeapon() != null) {
            setNumbers(itemJsons.getWeapon(), numbers);
        }

        JsonObject powderJ = JsonParser.parseReader(new JsonReader(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/other/powders.json")), StandardCharsets.UTF_8))).getAsJsonObject();

        //Powder
        if (itemJsons.getHelmet() != null) {
            setArmorPowder(itemJsons.getHelmet(), powderJ, powders.get(0), numbers);
        }

        //Chestplate Powder
        if (itemJsons.getChestplate() != null) {
            setArmorPowder(itemJsons.getChestplate(), powderJ, powders.get(1), numbers);
        }

        //Leggings Powder
        if (itemJsons.getLeggings() != null) {
            setArmorPowder(itemJsons.getLeggings(), powderJ, powders.get(2), numbers);
        }

        //Boots Powder
        if (itemJsons.getBoots() != null) {
            setArmorPowder(itemJsons.getBoots(), powderJ, powders.get(3), numbers);
        }

        if (updateOnly) { //Update Edited ID
            for (int i = 29; 77 >= i; ++i) { //Damages and Health Regen
                numbers[i] = damage_ids.getID(Damage_IDs.GET_DAMAGE_ID_NUM_FROM_ID.get(IDS.get(i))).getValue();
            }
            for (int i = 0; 7 >= i; ++i) { //Spell Costs
                numbers[i + 78] = damage_ids.getID(Damage_IDs.GET_DAMAGE_ID_NUM_FROM_ID.get(REVERSED_ID.get(i))).getValue();
            }
        } else { //Set Editable ID Original Num
            for (int i = 29; 77 >= i; ++i) { //Damages and Health Regen
                damage_ids.getID(Damage_IDs.GET_DAMAGE_ID_NUM_FROM_ID.get(IDS.get(i))).setValue(numbers[i]);
            }
            for (int i = 0; 7 >= i; ++i) { //Spell Costs
                damage_ids.getID(Damage_IDs.GET_DAMAGE_ID_NUM_FROM_ID.get(REVERSED_ID.get(i))).setValue(numbers[i + 78]);
            }
        }

        //Radiance
        if (damage_boosts.getBox().get(2).isSelected()) {
            for (int i = 1; 77 >= i; ++i) {
                if (i >= 3 && i <= 7) continue;
                if (i == ID_INT.get(Identifications.COMBAT_XP_BONUS) || i == ID_INT.get(Identifications.LOOT_BONUS) || i == ID_INT.get(Identifications.LOOT_QUALITY) || i == ID_INT.get(Identifications.GATHERING_XP_BONUS) || i == ID_INT.get(Identifications.GATHERING_SPEED_BONUS)) continue;
                if (numbers[i] > 0) {
                    numbers[i] -= numbers_Sub[i];
                    numbers[i] = Math.round(numbers[i] * 1.2F);
                    numbers[i] += numbers_Sub[i];
                }
            }
            for (int i = 0; 7 >= i; ++i) {
                if (0 > numbers[i + 78]) numbers[i + 78] = Math.round(numbers[i + 78] * 1.2F);
            }
        }

        for (int i = 0; 85 >= i; ++i) { //Reset Numbers Sub
            numbers_Sub[i] = 0;
        }

        //Ability Tree
        for (TreeCheckBox tcb : tree.getTcb()) {
            if (tcb.isSelected()) {
                switch (tcb.getFixedTreeName()) {
                    case "Earth Mastery":
                        numbers_Sub[ID_INT.get(Identifications.EARTH_DAMAGE_PERCENT)] += 20;
                        break;
                    case "Thunder Mastery":
                        numbers_Sub[ID_INT.get(Identifications.THUNDER_DAMAGE_PERCENT)] += 10;
                        break;
                    case "Water Mastery":
                        numbers_Sub[ID_INT.get(Identifications.WATER_DAMAGE_PERCENT)] += 15;
                        break;
                    case "Fire Mastery":
                        numbers_Sub[ID_INT.get(Identifications.FIRE_DAMAGE_PERCENT)] += 15;
                        break;
                    case "Air Mastery":
                        numbers_Sub[ID_INT.get(Identifications.AIR_DAMAGE_PERCENT)] += 15;
                        break;
                }
                switch (tree.getClasses()) {
                    case "warrior": {
                        switch (tcb.getFixedTreeName()) {
                            case "Tougher Skin":
                                numbers[ID_INT.get(Identifications.HEALTH_BONUS)] += Math.max(Math.min((numbers[ID_INT.get(Identifications.HEALTH_REGEN_PERCENT)] + numbers[ID_INT.get(Identifications.RAW_HEALTH_REGEN)]) * 10, 100), 0);
                            case "Mythril Skin":
                                def -= 0.05F;
                                break;
                            case "Vehement": numbers[ID_INT.get(Identifications.WALK_SPEED)] += Math.max(Math.min((numbers[ID_INT.get(Identifications.RAW_MELEE_DAMAGE)] + numbers[ID_INT.get(Identifications.MELEE_DAMAGE_PERCENT)]) * 2, 20), 0);
                                break;
                            case "Burning Heart": numbers[ID_INT.get(Identifications.FIRE_DAMAGE_PERCENT)] += Math.max(Math.min(Math.floor(numbers[ID_INT.get(Identifications.HEALTH_BONUS)] / 100F) * 2, 100), 0);
                                break;
                            case "Radiant Devotee": numbers[ID_INT.get(Identifications.MANA_REGEN)] += Math.max(Math.min(Math.floor(numbers[ID_INT.get(Identifications.REFLECTION)] / 4F), 10), 0);
                                break;
                        }
                        break;
                    }
                    case "assassin": {
                        switch (tcb.getFixedTreeName()) {
                            case "Poisoned Blade":
                                numbers[ID_INT.get(Identifications.POISON)] += Math.max(Math.min(Math.floor((numbers[ID_INT.get(Identifications.MELEE_DAMAGE_PERCENT)] + numbers[ID_INT.get(Identifications.RAW_MELEE_DAMAGE)]) / 2F), 50), 0);
                                break;
                            case "Shenanigans":
                                numbers[ID_INT.get(Identifications.MANA_STEAL)] += Math.max(Math.min(Math.floor(numbers[ID_INT.get(Identifications.STEALING)] / 2F), 8), 0);
                                break;
                            case "Psithurism":
                                numbers[ID_INT.get(Identifications.WALK_SPEED)] += 20;
                                numbers[ID_INT.get(Identifications.JUMP_HEIGHT)] += 1;
                                break;
                        }
                        break;
                    }
                    case "archer": {
                        if (tcb.getFixedTreeName().equals("Traveler")) numbers[ID_INT.get(Identifications.RAW_SPELL_DAMAGE)] += Math.max(Math.min(numbers[ID_INT.get(Identifications.WALK_SPEED)], 100), 0);
                        break;
                    }
                    case "mage": {
                        switch (tcb.getFixedTreeName()) {
                            case "Wisdom":
                                numbers[ID_INT.get(Identifications.MANA_REGEN)] += Math.max(Math.min(Math.floor((numbers[ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)] + numbers[ID_INT.get(Identifications.RAW_SPELL_DAMAGE)]) / 2F), 5), 0);
                                break;
                            case "Seance":
                                numbers[ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)] += Math.max(Math.min(Math.floor(numbers[ID_INT.get(Identifications.LIFE_STEAL)] / 5F), 50), 0);
                                break;
                            case "Dynamic Faith":
                                numbers[ID_INT.get(Identifications.THUNDER_DAMAGE_PERCENT)] += Math.max(Math.min(Math.floor(numbers[ID_INT.get(Identifications.SPRINT_BONUS)] / 2F), 40), 0);
                                break;
                        }
                        break;
                    }
                    case "shaman": {
                        switch (tcb.getFixedTreeName()) {
                            case "Blood Moon":
                                numbers[ID_INT.get(Identifications.LIFE_STEAL)] += Math.max(Math.min(numbers[ID_INT.get(Identifications.SOUL_POINT_REGEN)] * 5, 1000), 0);
                                break;
                            case "Mengjdu":
                                numbers[ID_INT.get(Identifications.WATER_DAMAGE_PERCENT)] += Math.max(Math.min(numbers[ID_INT.get(Identifications.THORNS)], 40), 0);
                                break;
                        }
                        break;
                    }
                }
            }
        }

        for (int i = 0; 85 >= i; ++i) { //ID_Main + ID_Sub = ID_Main
            numbers[i] += numbers_Sub[i];
        }

        //Powder Effects
        if (powder_effects.getPowderPanel("Earth").getValue() > 0) numbers[ID_INT.get(Identifications.EARTH_DAMAGE_PERCENT)] += powder_effects.getPowderPanel("Earth").getValue();
        if (powder_effects.getPowderPanel("Thunder").getValue() > 0) numbers[ID_INT.get(Identifications.THUNDER_DAMAGE_PERCENT)] += powder_effects.getPowderPanel("Thunder").getValue();
        if (powder_effects.getPowderPanel("Water").getValue() > 0) numbers[ID_INT.get(Identifications.WATER_DAMAGE_PERCENT)] += powder_effects.getPowderPanel("Water").getValue();
        if (powder_effects.getPowderPanel("Fire").getValue() > 0) numbers[ID_INT.get(Identifications.FIRE_DAMAGE_PERCENT)] += powder_effects.getPowderPanel("Fire").getValue();
        if (powder_effects.getPowderPanel("Air").getValue() > 0) numbers[ID_INT.get(Identifications.AIR_DAMAGE_PERCENT)] += powder_effects.getPowderPanel("Air").getValue();

        //War Scream
        if (damage_boosts.getBox().get(0).isSelected()) def *= 0.8F;

        //Check Boxes and Slider
        switch (tree.getClasses()) {
            case "warrior": {
                if (damage_boosts.getBox().get(7).isSelected()) def *= 0.3F; //Mantle
                if (damage_boosts.getBox().get(8).isSelected()) def *= 0.6F; //Brink of Madness
                break;
            }
            case "assassin": {
                if (damage_boosts.getBox().get(12).isSelected()) def *= 0.4F; //Mirror Image
                if (damage_boosts.getBox().get(16).isSelected()) def *= 0.6F; //Dissolution
                break;
            }
            case "shaman": {
                if (damage_boosts.getBox().get(18).isSelected()) numbers[ID_INT.get(Identifications.WALK_SPEED)] -= 35; //Mask of the Lunatic
                if (damage_boosts.getBox().get(19).isSelected()) def *= 0.65F; //Mask of the Fanatic
                if (damage_boosts.getBox().get(20).isSelected()) { //Mask of the Coward
                    numbers[ID_INT.get(Identifications.WALK_SPEED)] += 80;
                    def *= 1.2F;
                }
                if (damage_boosts.getBox().get(21).isSelected()) def *= 0.3F; //Chant of the Fanatic
                if (damage_boosts.getBox().get(25).isSelected()) { //Mask of the Awakened
                    def *= 0.65F;
                    numbers[ID_INT.get(Identifications.WALK_SPEED)] += 80;
                }
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
        if (numbers[ID_Display.ID_INT.get(Identifications.RAW_HEALTH_REGEN)] < 0) {
            ids.add(new JLabel("HPR: " + Math.min(Math.round(numbers[29] * ((100F + (numbers[30] * -1)) / 100F)), 0))); //Total Health Regen
        } else {
            ids.add(new JLabel("HPR: " + (Math.round(numbers[29] * ((100F + numbers[30]) / 100F))))); //Total Health Regen
        }
        ids.add(new JLabel("Life Steal: " + numbers[2] + "/3s")); //Life Steal
        ids.add(new JLabel(" "));
        for (int i = 3; 7 >= i; ++i) { //Defenses
            ids.add(new JLabel(IDS.get(i).getDisplayName() + ": " + (int) Math.floor(numbers[i] * ((100F + numbers[i + 5]) / 100F))));
        }
        ids.add(new JLabel(" "));
        if (numbers[1] != 0) ids.add(new JLabel(IDS.get(1).getDisplayName() + ": " + numbers[1] + IDS.get(1).getDisplaySp())); //Health Bonus
        for (int i = 8; 77 >= i; ++i) { //Normal IDS
            if (numbers[i] != 0) ids.add(new JLabel(IDS.get(i).getDisplayName() + ": " + numbers[i] + IDS.get(i).getDisplaySp()));
        }
        for (int i = 0; 7 >= i; ++i) { //Reversed IDS
            if (numbers[i + 78] != 0) ids.add(new JLabel(REVERSED_ID.get(i).getDisplayName() + ": " + numbers[i + 78] + REVERSED_ID.get(i).getDisplaySp()));
        }

        for (JLabel l : ids) {
            pane.add(l);
        }
        SwingUtilities.updateComponentTreeUI(pane);
    }

    public int[] getId_Numbers() {
        return id_Numbers;
    }

    private static void setNumbers(JsonObject json, int[] numbers) {
        if (json.get("base") != null) {
            JsonObject j = json.get("base").getAsJsonObject();
            if (j.get(Identifications.HEALTH.getItemName()) != null) {
                JsonElement j2 = j.get(Identifications.HEALTH.getItemName());
                if (json.get("tier") != null && json.get("tier").getAsString().equals("crafted")) {
                    numbers[ID_INT.get(Identifications.HEALTH)] += j2.getAsJsonObject().get("max").getAsInt();
                } else if (j.get(Identifications.HEALTH.getItemName()).getAsInt() != 0) {
                    numbers[ID_INT.get(Identifications.HEALTH)] += j2.getAsInt();
                }
            }

            if (j.get(Identifications.EARTH_DEFENSE.getItemName()) != null && j.get(Identifications.EARTH_DEFENSE.getItemName()).getAsInt() != 0) {
                numbers[ID_INT.get(Identifications.EARTH_DEFENSE)] += j.get(Identifications.EARTH_DEFENSE.getItemName()).getAsInt();
            }

            if (j.get(Identifications.THUNDER_DEFENSE.getItemName()) != null && j.get(Identifications.THUNDER_DEFENSE.getItemName()).getAsInt() != 0) {
                numbers[ID_INT.get(Identifications.THUNDER_DEFENSE)] += j.get(Identifications.THUNDER_DEFENSE.getItemName()).getAsInt();
            }

            if (j.get(Identifications.WATER_DEFENSE.getItemName()) != null && j.get(Identifications.WATER_DEFENSE.getItemName()).getAsInt() != 0) {
                numbers[ID_INT.get(Identifications.WATER_DEFENSE)] += j.get(Identifications.WATER_DEFENSE.getItemName()).getAsInt();
            }

            if (j.get(Identifications.FIRE_DEFENSE.getItemName()) != null && j.get(Identifications.FIRE_DEFENSE.getItemName()).getAsInt() != 0) {
                numbers[ID_INT.get(Identifications.FIRE_DEFENSE)] += j.get(Identifications.FIRE_DEFENSE.getItemName()).getAsInt();
            }

            if (j.get(Identifications.AIR_DEFENSE.getItemName()) != null && j.get(Identifications.AIR_DEFENSE.getItemName()).getAsInt() != 0) {
                numbers[ID_INT.get(Identifications.AIR_DEFENSE)] += j.get(Identifications.AIR_DEFENSE.getItemName()).getAsInt();
            }
        }

        for (int i = 5; 76 >= i; ++i) {
            Identifications id = ItemUITemplate.ITEM_IDS.get(i);
            if (json.get(id.getItemFieldPos()) != null && json.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()) != null) {
                JsonElement j = json.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName());
                if (!j.isJsonObject()) {
                    numbers[ID_INT.get(id)] += j.getAsInt();
                } else if (json.get("identified") != null && json.get("identified").getAsBoolean()) {
                    String minOrMax = "max";
                    if (j.getAsJsonObject().get("max").getAsInt() < 0) minOrMax = "min";
                    numbers[ID_INT.get(id)] += SearchUI.getBaseID(j.getAsJsonObject().get(minOrMax).getAsInt());
                } else if (id.isItemVariable() || json.get("tier").getAsString().equals("crafted")) {
                    numbers[ID_INT.get(id)] += j.getAsJsonObject().get("max").getAsInt();
                }
            }
        }

        for (int i = 0; 7 >= i; ++i) {
            Identifications id = ItemUITemplate.REVERSED_ITEM_IDS.get(i);
            if (json.get(id.getItemFieldPos()) != null && json.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()) != null) {
                JsonElement j = json.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName());
                if (!j.isJsonObject()) {
                    numbers[ID_INT.get(id)] += j.getAsInt();
                } else if (json.get("identified") != null && json.get("identified").getAsBoolean()) {
                    numbers[ID_INT.get(id)] += SearchUI.getBaseID(j.getAsJsonObject().get("max").getAsInt());
                } else {
                    int base = SearchUI.getBaseID(j.getAsJsonObject().get("max").getAsInt());
                    numbers[ID_INT.get(id)] += ItemUITemplate.getReversedMinInt(base);
                }
            }
        }
    }

    private static void setArmorPowder(JsonObject json, JsonObject powderJ, JTextField powder, int[] numbers) {
        if (json.get(Identifications.POWDER_SLOTS.getItemName()) != null && json.get(Identifications.POWDER_SLOTS.getItemName()).getAsInt() > 0 && powder.getText().length() > 1) {
            for (int i = 0; (int) Math.floor(powder.getText().length() / 2F) * 2 > i; i += 2) {
                if (json.get(Identifications.POWDER_SLOTS.getItemName()).getAsInt() >= i / 2) {
                    String name = String.valueOf(powder.getText().charAt(i)) + powder.getText().charAt(i + 1);
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
    }
}
