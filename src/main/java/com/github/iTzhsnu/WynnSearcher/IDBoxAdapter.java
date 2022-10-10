package com.github.iTzhsnu.WynnSearcher;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class IDBoxAdapter extends KeyAdapter {
    private final JComboBox<String> box;

    public static final String[] DISPLAY_ID_LIST = new String[] {
            "Level", "Strength Req", "Dexterity Req", "Intelligence Req", "Defense Req", "Agility Req",
            "Health", "Health Bonus", "Raw Health Regen", "Health Regen %", "Life Steal",
            "Strength", "Dexterity", "Intelligence", "Defense", "Agility",
            "Earth Defense", "Thunder Defense", "Water Defense", "Fire Defense", "Air Defense",
            "Earth Defense %", "Thunder Defense %", "Water Defense %", "Fire Defense %", "Air Defense %",
            "Neutral Damage", "Earth Damage", "Thunder Damage", "Water Damage", "Fire Damage", "Air Damage",
            "Earth Damage %", "Thunder Damage %", "Water Damage %", "Fire Damage %", "Air Damage %",
            "Raw Spell Damage", "Raw Melee Damage", "Spell Damage %", "Melee Damage %", "Poison",
            "Attack Speed", "Attack Speed Bonus",
            "Mana Regen", "Mana Steal",
            "Walk Speed", "Sprint Bonus", "Sprint Regen", "Jump Height",
            "Thorns", "Reflection", "Exploding", "Stealing",
            "Combat XP Bonus", "Gathering XP Bonus", "Gathering Speed Bonus", "Loot Bonus", "Loot Quality",
            "Soul Point Regen", "Powder Slots", "Major ID",
            "1st Spell Cost Raw", "2nd Spell Cost Raw", "3rd Spell Cost Raw", "4th Spell Cost Raw",
            "1st Spell Cost %", "2nd Spell Cost %", "3rd Spell Cost %", "4th Spell Cost %",
            "Neutral Melee Damage %", "Earth Melee Damage %", "Thunder Melee Damage %", "Water Melee Damage %", "Fire Melee Damage %", "Air Melee Damage %", "Elemental Melee Damage %",
            "Raw Neutral Melee Damage", "Raw Earth Melee Damage", "Raw Thunder Melee Damage", "Raw Water Melee Damage",  "Raw Fire Melee Damage", "Raw Air Melee Damage",  "Raw Elemental Melee Damage",
            "Neutral Spell Damage %", "Earth Spell Damage %", "Thunder Spell Damage %", "Water Spell Damage %", "Fire Spell Damage %", "Air Spell Damage %", "Elemental Spell Damage %",
            "Raw Neutral Spell Damage", "Raw Earth Spell Damage", "Raw Thunder Spell Damage", "Raw Water Spell Damage", "Raw Fire Spell Damage", "Raw Air Spell Damage", "Raw Elemental Spell Damage",
            "Durability", "Duration", "Charges", "Ingredient Effectiveness", "Ingredient Effectiveness (Above)", "Ingredient Effectiveness (Under)",
            "Ingredient Effectiveness (Right)", "Ingredient Effectiveness (Left)", "Ingredient Effectiveness (Touching)", "Ingredient Effectiveness (Not Touching)",
            "Sum (Total Health)", "Sum (Total Health Regen)", "Sum (Raw Defenses)", "Sum (Raw Rainbow Defenses)", "Sum (Defenses %)", "Sum (Rainbow Defenses %)",
            "Sum (Skill Point Bonus)", "Sum (Rainbow Skill Point Bonus)",
            "Sum (Base DPS)", "Sum (Neutral DPS)", "Sum (Earth DPS)", "Sum (Thunder DPS)", "Sum (Water DPS)", "Sum (Fire DPS)", "Sum (Air DPS)",
            "Sum (Damages Raw)", "Sum (Rainbow Damages Raw)", "Sum (Damages %)", "Sum (Rainbow Damages %)",
            "Sum (Total Melee Damage)", "Sum (Total Neutral Melee Damage)",
            "Sum (Total Earth Damage)", "Sum (Total Thunder Damage)", "Sum (Total Water Damage)", "Sum (Total Fire Damage)", "Sum (Total Air Damage)",
            "Sum (Total DPS)", "Sum (Total Melee DPS)", "Sum (Total Spell DPS)", "Sum (Total Neutral Melee DPS)", "Sum (Total Neutral Spell DPS)",
            "Sum (Total Earth DPS)", "Sum (Total Thunder DPS)", "Sum (Total Water DPS)", "Sum (Total Fire DPS)", "Sum (Total Air DPS)",
            "Sum (Raw Spell Costs)", "Sum (Spell Costs %)"
    };

    public static final Map<String, Identifications> ID_LIST = new HashMap<String, Identifications>(){{
        put("Level", Identifications.LEVEL);
        put("Strength Req", Identifications.STRENGTH_REQ);
        put("Dexterity Req", Identifications.DEXTERITY_REQ);
        put("Intelligence Req", Identifications.INTELLIGENCE_REQ);
        put("Defense Req", Identifications.DEFENSE_REQ);
        put("Agility Req", Identifications.AGILITY_REQ);

        put("Health", Identifications.HEALTH);
        put("Health Bonus", Identifications.HEALTH_BONUS);
        put("Raw Health Regen", Identifications.RAW_HEALTH_REGEN);
        put("Health Regen %", Identifications.HEALTH_REGEN_PERCENT);
        put("Life Steal", Identifications.LIFE_STEAL);

        put("Strength", Identifications.STRENGTH);
        put("Dexterity", Identifications.DEXTERITY);
        put("Intelligence", Identifications.INTELLIGENCE);
        put("Defense", Identifications.DEFENSE);
        put("Agility", Identifications.AGILITY);

        put("Earth Defense", Identifications.EARTH_DEFENSE);
        put("Thunder Defense", Identifications.THUNDER_DEFENSE);
        put("Water Defense", Identifications.WATER_DEFENSE);
        put("Fire Defense", Identifications.FIRE_DEFENSE);
        put("Air Defense", Identifications.AIR_DEFENSE);

        put("Earth Defense %", Identifications.EARTH_DEFENSE_PERCENT);
        put("Thunder Defense %", Identifications.THUNDER_DEFENSE_PERCENT);
        put("Water Defense %", Identifications.WATER_DEFENSE_PERCENT);
        put("Fire Defense %", Identifications.FIRE_DEFENSE_PERCENT);
        put("Air Defense %", Identifications.AIR_DEFENSE_PERCENT);

        put("Neutral Damage", Identifications.NEUTRAL_DAMAGE);
        put("Earth Damage", Identifications.EARTH_DAMAGE);
        put("Thunder Damage", Identifications.THUNDER_DAMAGE);
        put("Water Damage", Identifications.WATER_DAMAGE);
        put("Fire Damage", Identifications.FIRE_DAMAGE);
        put("Air Damage", Identifications.AIR_DAMAGE);

        put("Earth Damage %", Identifications.EARTH_DAMAGE_PERCENT);
        put("Thunder Damage %", Identifications.THUNDER_DAMAGE_PERCENT);
        put("Water Damage %", Identifications.WATER_DAMAGE_PERCENT);
        put("Fire Damage %", Identifications.FIRE_DAMAGE_PERCENT);
        put("Air Damage %", Identifications.AIR_DAMAGE_PERCENT);

        put("Raw Spell Damage", Identifications.RAW_SPELL_DAMAGE);
        put("Raw Melee Damage", Identifications.RAW_MELEE_DAMAGE);
        put("Spell Damage %", Identifications.SPELL_DAMAGE_PERCENT);
        put("Melee Damage %", Identifications.MELEE_DAMAGE_PERCENT);
        put("Poison", Identifications.POISON);

        put("Attack Speed", Identifications.ATTACK_SPEED);
        put("Attack Speed Bonus", Identifications.ATTACK_SPEED_BONUS);

        put("Mana Regen", Identifications.MANA_REGEN);
        put("Mana Steal", Identifications.MANA_STEAL);

        put("Walk Speed", Identifications.WALK_SPEED);
        put("Sprint Bonus", Identifications.SPRINT_BONUS);
        put("Sprint Regen", Identifications.SPRINT_REGEN);
        put("Jump Height", Identifications.JUMP_HEIGHT);

        put("Thorns", Identifications.THORNS);
        put("Reflection", Identifications.REFLECTION);
        put("Exploding", Identifications.EXPLODING);
        put("Stealing", Identifications.STEALING);
        put("Combat XP Bonus", Identifications.COMBAT_XP_BONUS);
        put("Gathering XP Bonus", Identifications.GATHERING_XP_BONUS);
        put("Gathering Speed Bonus", Identifications.GATHERING_SPEED_BONUS);
        put("Loot Bonus", Identifications.LOOT_BONUS);
        put("Loot Quality", Identifications.LOOT_QUALITY);
        put("Soul Point Regen", Identifications.SOUL_POINT_REGEN);
        put("Powder Slots", Identifications.POWDER_SLOTS);
        put("Major ID", Identifications.MAJOR_IDS);

        put("1st Spell Cost Raw", Identifications.RAW_1ST_SPELL_COST);
        put("2nd Spell Cost Raw", Identifications.RAW_2ND_SPELL_COST);
        put("3rd Spell Cost Raw", Identifications.RAW_3RD_SPELL_COST);
        put("4th Spell Cost Raw", Identifications.RAW_4TH_SPELL_COST);

        put("1st Spell Cost %", Identifications.PERCENT_1ST_SPELL_COST);
        put("2nd Spell Cost %", Identifications.PERCENT_2ND_SPELL_COST);
        put("3rd Spell Cost %", Identifications.PERCENT_3RD_SPELL_COST);
        put("4th Spell Cost %", Identifications.PERCENT_4TH_SPELL_COST);

        put("Neutral Melee Damage %", Identifications.NEUTRAL_MELEE_DAMAGE_PERCENT);
        put("Earth Melee Damage %", Identifications.EARTH_MELEE_DAMAGE_PERCENT);
        put("Thunder Melee Damage %", Identifications.THUNDER_MELEE_DAMAGE_PERCENT);
        put("Water Melee Damage %", Identifications.WATER_MELEE_DAMAGE_PERCENT);
        put("Fire Melee Damage %", Identifications.FIRE_MELEE_DAMAGE_PERCENT);
        put("Air Melee Damage %", Identifications.AIR_MELEE_DAMAGE_PERCENT);
        put("Elemental Melee Damage %", Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT);

        put("Raw Neutral Melee Damage", Identifications.RAW_NEUTRAL_MELEE_DAMAGE);
        put("Raw Earth Melee Damage", Identifications.RAW_EARTH_MELEE_DAMAGE);
        put("Raw Thunder Melee Damage", Identifications.RAW_THUNDER_MELEE_DAMAGE);
        put("Raw Water Melee Damage", Identifications.RAW_WATER_MELEE_DAMAGE);
        put("Raw Fire Melee Damage", Identifications.RAW_FIRE_MELEE_DAMAGE);
        put("Raw Air Melee Damage", Identifications.RAW_AIR_MELEE_DAMAGE);
        put("Raw Elemental Melee Damage", Identifications.RAW_ELEMENTAL_MELEE_DAMAGE);

        put("Neutral Spell Damage %", Identifications.NEUTRAL_SPELL_DAMAGE_PERCENT);
        put("Earth Spell Damage %", Identifications.EARTH_SPELL_DAMAGE_PERCENT);
        put("Thunder Spell Damage %", Identifications.THUNDER_SPELL_DAMAGE_PERCENT);
        put("Water Spell Damage %", Identifications.WATER_SPELL_DAMAGE_PERCENT);
        put("Fire Spell Damage %", Identifications.FIRE_SPELL_DAMAGE_PERCENT);
        put("Air Spell Damage %", Identifications.AIR_SPELL_DAMAGE_PERCENT);
        put("Elemental Spell Damage %", Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT);


        put("Raw Neutral Spell Damage", Identifications.RAW_NEUTRAL_SPELL_DAMAGE);
        put("Raw Earth Spell Damage", Identifications.RAW_EARTH_SPELL_DAMAGE);
        put("Raw Thunder Spell Damage", Identifications.RAW_THUNDER_SPELL_DAMAGE);
        put("Raw Water Spell Damage", Identifications.RAW_WATER_SPELL_DAMAGE);
        put("Raw Fire Spell Damage", Identifications.RAW_FIRE_SPELL_DAMAGE);
        put("Raw Air Spell Damage", Identifications.RAW_AIR_SPELL_DAMAGE);
        put("Raw Elemental Spell Damage", Identifications.RAW_ELEMENTAL_SPELL_DAMAGE);

        put("Durability", Identifications.DURABILITY);
        put("Duration", Identifications.DURATION);
        put("Charges", Identifications.CHARGES);
        put("Ingredient Effectiveness", Identifications.INGREDIENT_EFFECTIVENESS);
        put("Ingredient Effectiveness (Above)", Identifications.INGREDIENT_EFFECTIVENESS_ABOVE);
        put("Ingredient Effectiveness (Under)", Identifications.INGREDIENT_EFFECTIVENESS_UNDER);
        put("Ingredient Effectiveness (Right)", Identifications.INGREDIENT_EFFECTIVENESS_RIGHT);
        put("Ingredient Effectiveness (Left)", Identifications.INGREDIENT_EFFECTIVENESS_LEFT);
        put("Ingredient Effectiveness (Touching)", Identifications.INGREDIENT_EFFECTIVENESS_TOUCHING);
        put("Ingredient Effectiveness (Not Touching)", Identifications.INGREDIENT_EFFECTIVENESS_NOT_TOUCHING);

        put("Sum (Total Health)", Identifications.SUM_TOTAL_HEALTH);
        put("Sum (Total Health Regen)", Identifications.SUM_TOTAL_HP_REGEN);
        put("Sum (Raw Defenses)", Identifications.SUM_RAW_DEFENSES);
        put("Sum (Raw Rainbow Defenses)", Identifications.SUM_RAW_RAINBOW_DEFENSES);
        put("Sum (Defenses %)", Identifications.SUM_DEFENSES_PERCENT);
        put("Sum (Rainbow Defenses %)", Identifications.SUM_RAINBOW_DEFENSES_PERCENT);
        put("Sum (Skill Point Bonus)", Identifications.SUM_SKILL_POINT_BONUS);
        put("Sum (Rainbow Skill Point Bonus)", Identifications.SUM_RAINBOW_SKILL_POINT_BONUS);

        put("Sum (Base DPS)", Identifications.SUM_BASE_DPS);
        put("Sum (Neutral DPS)", Identifications.SUM_NEUTRAL_DPS);
        put("Sum (Earth DPS)", Identifications.SUM_EARTH_DPS);
        put("Sum (Thunder DPS)", Identifications.SUM_THUNDER_DPS);
        put("Sum (Water DPS)", Identifications.SUM_WATER_DPS);
        put("Sum (Fire DPS)", Identifications.SUM_FIRE_DPS);
        put("Sum (Air DPS)", Identifications.SUM_AIR_DPS);

        put("Sum (Damages Raw)", Identifications.SUM_RAW_DAMAGES);
        put("Sum (Rainbow Damages Raw)", Identifications.SUM_RAW_RAINBOW_DAMAGES);
        put("Sum (Damages %)", Identifications.SUM_DAMAGES_PERCENT);
        put("Sum (Rainbow Damages %)", Identifications.SUM_RAINBOW_DAMAGES_PERCENT);

        put("Sum (Total Melee Damage)", Identifications.SUM_TOTAL_MELEE_DAMAGE);
        put("Sum (Total Neutral Melee Damage)", Identifications.SUM_TOTAL_NEUTRAL_MELEE_DAMAGE);
        put("Sum (Total Earth Damage)", Identifications.SUM_TOTAL_EARTH_DAMAGE);
        put("Sum (Total Thunder Damage)", Identifications.SUM_TOTAL_THUNDER_DAMAGE);
        put("Sum (Total Water Damage)", Identifications.SUM_TOTAL_WATER_DAMAGE);
        put("Sum (Total Fire Damage)", Identifications.SUM_TOTAL_FIRE_DAMAGE);
        put("Sum (Total Air Damage)", Identifications.SUM_TOTAL_AIR_DAMAGE);

        put("Sum (Total DPS)", Identifications.SUM_TOTAL_DPS);
        put("Sum (Total Melee DPS)", Identifications.SUM_TOTAL_MELEE_DPS);
        put("Sum (Total Spell DPS)", Identifications.SUM_TOTAL_SPELL_DPS);
        put("Sum (Total Neutral Melee DPS)", Identifications.SUM_TOTAL_NEUTRAL_MELEE_DPS);
        put("Sum (Total Neutral Spell DPS)", Identifications.SUM_TOTAL_NEUTRAL_SPELL_DPS);
        put("Sum (Total Earth DPS)", Identifications.SUM_TOTAL_EARTH_DPS);
        put("Sum (Total Thunder DPS)", Identifications.SUM_TOTAL_THUNDER_DPS);
        put("Sum (Total Water DPS)", Identifications.SUM_TOTAL_WATER_DPS);
        put("Sum (Total Fire DPS)", Identifications.SUM_TOTAL_FIRE_DPS);
        put("Sum (Total Air DPS)", Identifications.SUM_TOTAL_AIR_DPS);

        put("Sum (Raw Spell Costs)", Identifications.SUM_RAW_SPELL_COSTS);
        put("Sum (Spell Costs %)", Identifications.SUM_SPELL_COSTS_PERCENT);
    }};

    public IDBoxAdapter(JComboBox<String> box) {
        super();
        this.box = box;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        JTextField text = (JTextField) e.getComponent();

        setList(box, text.getText(), text.getCaretPosition(), text.getSelectionStart(), text.getSelectionEnd());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
    }

    private static void setList(JComboBox<String> box, String text, int caretPos, int selectionStart, int selectionEnd) {
        box.removeAllItems();

        for (String s : DISPLAY_ID_LIST) {
            if (text.isEmpty() || s.toLowerCase().contains(text.toLowerCase())) {
                box.addItem(s);
            }
        }

        ((JTextField) box.getEditor().getEditorComponent()).setText(text);
        ((JTextField) box.getEditor().getEditorComponent()).setCaretPosition(caretPos);
        if (selectionStart < selectionEnd) {
            ((JTextField) box.getEditor().getEditorComponent()).setSelectionStart(selectionStart);
            ((JTextField) box.getEditor().getEditorComponent()).setSelectionEnd(selectionEnd);
        }
    }
}
