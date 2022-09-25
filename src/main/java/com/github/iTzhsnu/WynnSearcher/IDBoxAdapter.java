package com.github.iTzhsnu.WynnSearcher;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class IDBoxAdapter extends KeyAdapter {
    private final JComboBox<String> box;

    public static final String[] displayIDList = new String[] {
            "Combat Level", "Strength Req", "Dexterity Req", "Inteligence Req", "Defense Req", "Agility Req", "Quest Req",
            "Health", "Health Bonus", "Raw Health Regen", "Health Regen %", "Life Steal",
            "Strength", "Dexterity", "Inteligence", "Defense", "Agility",
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
            "Soul Point Regen", "Powder Slots",
            "1st Spell Cost Raw", "2nd Spell Cost Raw", "3rd Spell Cost Raw", "4th Spell Cost Raw",
            "1st Spell Cost %", "2nd Spell Cost %", "3rd Spell Cost %", "4th Spell Cost %",
            "Raw Earth Spell Damage", "Raw Thunder Spell Damage", "Raw Water Spell Damage", "Raw Fire Spell Damage", "Raw Air Spell Damage", "Raw Elemental Spell Damage",
            "Durability", "Duration", "Ingredient Effectiveness", "Ingredient Effectiveness (Above)", "Ingredient Effectiveness (Under)",
            "Ingredient Effectiveness (Right)", "Ingredient Effectiveness (Left)", "Ingredient Effectiveness (Touching)", "Ingredient Effectiveness (Not Touching)",
            "Sum (Total Health)", "Sum (Raw Defenses)", "Sum (Raw Rainbow Defenses)", "Sum (Defenses %)", "Sum (Rainbow Defenses %)",
            "Sum (Skill Point Bonus)", "Sum (Rainbow Skill Point Bonus)",
            "Sum (Base DPS)", "Sum (Total Melee Damage)", "Sum (Total Spell Damage)", "Sum (Damages Raw)", "Sum (Rainbow Damages Raw)",
            "Sum (Damages %)", "Sum (Rainbow Damages %)",
            "Sum (Total Melee Damage x Damages %)", "Sum (Total Spell Damage x Damages %)", "Sum (Neutral Damages x Melee %)", "Sum (Neutral Damages x Spell %)",
            "Sum (Earth Damages x Damages %)", "Sum (Thunder Damages x Damages %)", "Sum (Water Damages x Damages %)", "Sum (Fire Damages x Damages %)", "Sum (Air Damages x Damages %)",
            "Sum (Base DPS x Damages %)", "Sum (DPS x Melee Damage %)", "Sum (DPS x Spell Damage %)", "Sum (Neutral DPS x Melee %)", "Sum (Neutral DPS x Spell %)",
            "Sum (Earth DPS x Damages %)", "Sum (Thunder DPS x Damages %)", "Sum (Water DPS x Damages %)", "Sum (Fire DPS x Damages %)", "Sum (Air DPS x Damages %)",
            "Sum (Raw Spell Costs)", "Sum (Spell Costs %)"
    };

    public static final Map<String, Integer> idList = new HashMap<String, Integer>(){{
        put("Combat Level", 1);
        put("Strength Req", 2);
        put("Dexterity Req", 3);
        put("Inteligence Req", 4);
        put("Defense Req", 5);
        put("Agility Req", 6);
        put("Quest Req", 7);

        put("Health", 10);
        put("Health Bonus", 11);
        put("Raw Health Regen", 12);
        put("Health Regen %", 13);
        put("Life Steal", 14);

        put("Strength", 15);
        put("Dexterity", 16);
        put("Inteligence", 17);
        put("Defense", 18);
        put("Agility", 19);

        put("Earth Defense", 20);
        put("Thunder Defense", 21);
        put("Water Defense", 22);
        put("Fire Defense", 23);
        put("Air Defense", 24);

        put("Earth Defense %", 25);
        put("Thunder Defense %", 26);
        put("Water Defense %", 27);
        put("Fire Defense %", 28);
        put("Air Defense %", 29);

        put("Neutral Damage", 30);
        put("Earth Damage", 31);
        put("Thunder Damage", 32);
        put("Water Damage", 33);
        put("Fire Damage", 34);
        put("Air Damage", 35);

        put("Earth Damage %", 36);
        put("Thunder Damage %", 37);
        put("Water Damage %", 38);
        put("Fire Damage %", 39);
        put("Air Damage %", 40);

        put("Raw Spell Damage", 41);
        put("Raw Melee Damage", 42);
        put("Spell Damage %", 43);
        put("Melee Damage %", 44);
        put("Poison", 45);

        put("Attack Speed", 46);
        put("Attack Speed Bonus", 47);

        put("Mana Regen", 48);
        put("Mana Steal", 49);

        put("Walk Speed", 50);
        put("Sprint Bonus", 51);
        put("Sprint Regen", 52);
        put("Jump Height", 53);

        put("Thorns", 54);
        put("Reflection", 55);
        put("Exploding", 56);
        put("Stealing", 57);
        put("Combat XP Bonus", 58);
        put("Gathering XP Bonus", 59);
        put("Gathering Speed Bonus", 60);
        put("Loot Bonus", 61);
        put("Loot Quality", 62);
        put("Soul Point Regen", 63);
        put("Powder Slots", 64);

        put("1st Spell Cost Raw", 65);
        put("2nd Spell Cost Raw", 66);
        put("3rd Spell Cost Raw", 67);
        put("4th Spell Cost Raw", 68);

        put("1st Spell Cost %", 69);
        put("2nd Spell Cost %", 70);
        put("3rd Spell Cost %", 71);
        put("4th Spell Cost %", 72);

        put("Raw Earth Spell Damage", 73);
        put("Raw Thunder Spell Damage", 74);
        put("Raw Water Spell Damage", 75);
        put("Raw Fire Spell Damage", 76);
        put("Raw Air Spell Damage", 77);
        put("Raw Elemental Spell Damage", 78);

        put("Durability", 80);
        put("Duration", 81);
        put("Ingredient Effectiveness", 82);
        put("Ingredient Effectiveness (Above)", 83);
        put("Ingredient Effectiveness (Under)", 84);
        put("Ingredient Effectiveness (Right)", 85);
        put("Ingredient Effectiveness (Left)", 86);
        put("Ingredient Effectiveness (Touching)", 87);
        put("Ingredient Effectiveness (Not Touching)", 88);

        put("Sum (Total Health)", 90);
        put("Sum (Raw Defenses)", 91);
        put("Sum (Raw Rainbow Defenses)", 92);
        put("Sum (Defenses %)", 93);
        put("Sum (Rainbow Defenses %)", 94);
        put("Sum (Skill Point Bonus)", 95);
        put("Sum (Rainbow Skill Point Bonus)", 96);

        put("Sum (Base DPS)", 97);
        put("Sum (Total Melee Damage)", 98);
        put("Sum (Total Spell Damage)", 99);
        put("Sum (Damages Raw)", 100);
        put("Sum (Rainbow Damages Raw)", 101);
        put("Sum (Damages %)", 102);
        put("Sum (Rainbow Damages %)", 103);

        put("Sum (Total Melee Damage x Damages %)", 104);
        put("Sum (Total Spell Damage x Damages %)", 105);
        put("Sum (Neutral Damages x Melee %)", 106);
        put("Sum (Neutral Damages x Spell %)", 107);
        put("Sum (Earth Damages x Damages %)", 108);
        put("Sum (Thunder Damages x Damages %)", 109);
        put("Sum (Water Damages x Damages %)", 110);
        put("Sum (Fire Damages x Damages %)", 111);
        put("Sum (Air Damages x Damages %)", 112);

        put("Sum (Base DPS x Damages %)", 113);
        put("Sum (DPS x Melee Damage %)", 114);
        put("Sum (DPS x Spell Damage %)", 115);
        put("Sum (Neutral DPS x Melee %)", 116);
        put("Sum (Neutral DPS x Spell %)", 117);
        put("Sum (Earth DPS x Damages %)", 118);
        put("Sum (Thunder DPS x Damages %)", 119);
        put("Sum (Water DPS x Damages %)", 120);
        put("Sum (Fire DPS x Damages %)", 121);
        put("Sum (Air DPS x Damages %)", 122);

        put("Sum (Raw Spell Costs)", 123);
        put("Sum (Spell Costs %)", 124);
    }};

    public IDBoxAdapter(JComboBox<String> box) {
        super();
        this.box = box;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        JTextField text = (JTextField) e.getComponent();

        setList(box, text.getText());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
    }

    private static void setList(JComboBox<String> box, String text) {
        box.removeAllItems();

        for (String s : displayIDList) {
            if (text.isEmpty() || s.toLowerCase().contains(text.toLowerCase())) {
                box.addItem(s);
            }
        }

        ((JTextField) box.getEditor().getEditorComponent()).setText(text);
    }
}
