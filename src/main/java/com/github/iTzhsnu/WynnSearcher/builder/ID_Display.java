package com.github.iTzhsnu.WynnSearcher.builder;

import com.github.iTzhsnu.WynnSearcher.Identifications;
import com.github.iTzhsnu.WynnSearcher.ItemUITemplate;
import com.github.iTzhsnu.WynnSearcher.builder.skilltree.TreeBase;
import com.google.gson.JsonObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ID_Display {
    private final JPanel pane = new JPanel();
    private final List<JLabel> ids = new ArrayList<>();

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
        scrollPane.setBounds(450, 300, 268, 400);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);

        p.add(scrollPane);
    }



    public void setIDs(ItemJsons itemJsons, Damage_IDs damage_ids, SkillPoint sp, int classID, TreeBase tree, Damage_Boosts damage_boosts, boolean updateOnly) {
        pane.removeAll();
        if (ids.size() > 0) {
            ids.subList(0, ids.size()).clear();
        }
        int[] numbers = new int[] {
                535, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        };
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
                if (!updateOnly) {
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
                                numbers[i + 70] += ItemUITemplate.getReversedMaxInt(j.get(REVERSED_ID.get(i).getItemName()).getAsInt());
                            } else numbers[i + 70] += j.get(REVERSED_ID.get(i).getItemName()).getAsInt();
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
            if (!updateOnly) {
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
                            numbers[i + 70] += ItemUITemplate.getReversedMaxInt(j.get(REVERSED_ID.get(i).getItemName()).getAsInt());
                        } else numbers[i + 70] += j.get(REVERSED_ID.get(i).getItemName()).getAsInt();
                    }
                }
            }
        }

        if (updateOnly) {
            for (int i = 29; 69 >= i; ++i) {
                numbers[i] = damage_ids.getID(Damage_IDs.GET_DAMAGE_ID_NUM_FROM_ID.get(IDS.get(i))).getValue();
            }
            for (int i = 0; 7 >= i; ++i) {
                numbers[i + 70] = damage_ids.getID(Damage_IDs.GET_DAMAGE_ID_NUM_FROM_ID.get(REVERSED_ID.get(i))).getValue();
            }
        }

        numbers[0] += numbers[1]; //Health + Health Bonus
        if (numbers[0] < 5) numbers[0] = 5;
        float def = 2F - CLASS_DEF.get(classID); //Base Defense (TODO ADD ABILITY TREE TAKE DAMAGE REDUCES)
        float defSP = sp.getSkillPoint(SkillPoint.SkillPointType.DEFENSE).getSPBoost() - 1F; //Defense Skill Point
        float agiSP = sp.getSkillPoint(SkillPoint.SkillPointType.AGILITY).getSPBoost() - 1F; //Agility Skill Point
        float ehp = numbers[0] / (0.1F * agiSP + (1F - agiSP) * (1F - defSP)) / def; //Effective Health Calc
        ids.add(new JLabel("Health: " + numbers[0])); //Total Health
        ids.add(new JLabel("EHP: " + ehp)); //Effective Health
        ids.add(new JLabel("EHP (No Agi): " + (numbers[0] / ((1F - defSP) * def)))); //Effective Health (No Agility)
        ids.add(new JLabel("HPR: " + (numbers[37] * ((100F + numbers[38]) / 100F)))); //Total Health Regen
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

        //Set Editable ID Original Num
        if (!updateOnly) { //Damage, Spell Cost and Health Regen
            for (int i = 29; 69 >= i; ++i) {
                damage_ids.getID(Damage_IDs.GET_DAMAGE_ID_NUM_FROM_ID.get(IDS.get(i))).setValue(numbers[i]);
            }
            for (int i = 0; 7 >= i; ++i) { //Spell Cost
                damage_ids.getID(Damage_IDs.GET_DAMAGE_ID_NUM_FROM_ID.get(REVERSED_ID.get(i))).setValue(numbers[i + 70]);
            }
        }

        for (JLabel l : ids) {
            pane.add(l);
        }
        SwingUtilities.updateComponentTreeUI(pane);
    }
}
