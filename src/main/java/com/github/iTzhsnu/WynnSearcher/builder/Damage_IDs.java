package com.github.iTzhsnu.WynnSearcher.builder;

import com.github.iTzhsnu.WynnSearcher.Identifications;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Damage_IDs {
    private final List<Damage_IDs_Panel> damage_ID_Panels = new ArrayList<>();

    public static final Map<Identifications, Integer> GET_DAMAGE_ID_NUM_FROM_ID = new HashMap<Identifications, Integer>() {{
        put(Identifications.EARTH_DAMAGE_PERCENT, 0);
        put(Identifications.THUNDER_DAMAGE_PERCENT, 1);
        put(Identifications.WATER_DAMAGE_PERCENT, 2);
        put(Identifications.FIRE_DAMAGE_PERCENT, 3);
        put(Identifications.AIR_DAMAGE_PERCENT, 4);

        put(Identifications.SPELL_DAMAGE_PERCENT, 5);
        put(Identifications.RAW_SPELL_DAMAGE, 6);
        put(Identifications.MELEE_DAMAGE_PERCENT, 7);
        put(Identifications.RAW_MELEE_DAMAGE, 8);
        put(Identifications.POISON, 9);
        put(Identifications.ATTACK_SPEED_BONUS, 10);

        put(Identifications.NEUTRAL_SPELL_DAMAGE_PERCENT, 11);
        put(Identifications.EARTH_SPELL_DAMAGE_PERCENT, 12);
        put(Identifications.THUNDER_SPELL_DAMAGE_PERCENT, 13);
        put(Identifications.WATER_SPELL_DAMAGE_PERCENT, 14);
        put(Identifications.FIRE_SPELL_DAMAGE_PERCENT, 15);
        put(Identifications.AIR_SPELL_DAMAGE_PERCENT, 16);
        put(Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT, 17);

        put(Identifications.RAW_NEUTRAL_SPELL_DAMAGE, 18);
        put(Identifications.RAW_EARTH_SPELL_DAMAGE, 19);
        put(Identifications.RAW_THUNDER_SPELL_DAMAGE, 20);
        put(Identifications.RAW_WATER_SPELL_DAMAGE, 21);
        put(Identifications.RAW_FIRE_SPELL_DAMAGE, 22);
        put(Identifications.RAW_AIR_SPELL_DAMAGE, 23);
        put(Identifications.RAW_ELEMENTAL_SPELL_DAMAGE, 24);

        put(Identifications.NEUTRAL_MELEE_DAMAGE_PERCENT, 25);
        put(Identifications.EARTH_MELEE_DAMAGE_PERCENT, 26);
        put(Identifications.THUNDER_MELEE_DAMAGE_PERCENT, 27);
        put(Identifications.WATER_MELEE_DAMAGE_PERCENT, 28);
        put(Identifications.FIRE_MELEE_DAMAGE_PERCENT, 29);
        put(Identifications.AIR_MELEE_DAMAGE_PERCENT, 30);
        put(Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT, 31);

        put(Identifications.RAW_NEUTRAL_MELEE_DAMAGE, 32);
        put(Identifications.RAW_EARTH_MELEE_DAMAGE, 33);
        put(Identifications.RAW_THUNDER_MELEE_DAMAGE, 34);
        put(Identifications.RAW_WATER_MELEE_DAMAGE, 35);
        put(Identifications.RAW_FIRE_MELEE_DAMAGE, 36);
        put(Identifications.RAW_AIR_MELEE_DAMAGE, 37);
        put(Identifications.RAW_ELEMENTAL_MELEE_DAMAGE, 38);

        put(Identifications.PERCENT_1ST_SPELL_COST, 39);
        put(Identifications.PERCENT_2ND_SPELL_COST, 40);
        put(Identifications.PERCENT_3RD_SPELL_COST, 41);
        put(Identifications.PERCENT_4TH_SPELL_COST, 42);

        put(Identifications.RAW_1ST_SPELL_COST, 43);
        put(Identifications.RAW_2ND_SPELL_COST, 44);
        put(Identifications.RAW_3RD_SPELL_COST, 45);
        put(Identifications.RAW_4TH_SPELL_COST, 46);

        put(Identifications.HEALTH_REGEN_PERCENT, 47);
        put(Identifications.RAW_HEALTH_REGEN, 48);

        put(Identifications.ELEMENTAL_DAMAGE_PERCENT, 49);
        put(Identifications.RAW_ELEMENTAL_DAMAGE, 50);

        put(Identifications.RAW_NEUTRAL_DAMAGE, 51);
        put(Identifications.RAW_EARTH_DAMAGE, 52);
        put(Identifications.RAW_THUNDER_DAMAGE, 53);
        put(Identifications.RAW_WATER_DAMAGE, 54);
        put(Identifications.RAW_FIRE_DAMAGE, 55);
        put(Identifications.RAW_AIR_DAMAGE, 56);
    }};

    public Damage_IDs(JPanel p) {
        damage_ID_Panels.add(new Damage_IDs_Panel("Earth Dam %", getX(0), getY(0), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Thunder Dam %", getX(1), getY(0), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Water Dam %", getX(2), getY(0), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Fire Dam %", getX(3), getY(0), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Air Dam %", getX(4), getY(0), p));

        damage_ID_Panels.add(new Damage_IDs_Panel("Spell Dam %", getX(0), getY(2), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Spell Dam", getX(1), getY(2), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Melee Dam %", getX(2), getY(2), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Melee Dam", getX(3), getY(2), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Poison", getX(4), getY(2), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Attack Speed", getX(5), getY(2), p));

        damage_ID_Panels.add(new Damage_IDs_Panel("Neutral Spell %", getX(0), getY(3), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Earth Spell %", getX(1), getY(3), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Thunder Spell %", getX(2), getY(3), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Water Spell %", getX(3), getY(3), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Fire Spell %", getX(4), getY(3), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Air Spell %", getX(5), getY(3), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Elem. Spell %", getX(6), getY(3), p));

        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Neutral Spell", getX(0), getY(4), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Earth Spell", getX(1), getY(4), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Thunder Spell", getX(2), getY(4), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Water Spell", getX(3), getY(4), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Fire Spell", getX(4), getY(4), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Air Spell", getX(5), getY(4), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Elem. Spell", getX(6), getY(4), p));

        damage_ID_Panels.add(new Damage_IDs_Panel("Neutral Melee %", getX(0), getY(5), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Earth Melee %", getX(1), getY(5), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Thunder Melee %", getX(2), getY(5), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Water Melee %", getX(3), getY(5), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Fire Melee %", getX(4), getY(5), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Air Melee %", getX(5), getY(5), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Elem. Melee %", getX(6), getY(5), p));

        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Neutral Melee", getX(0), getY(6), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Earth Melee", getX(1), getY(6), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Thunder Melee", getX(2), getY(6), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Water Melee", getX(3), getY(6), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Fire Melee", getX(4), getY(6), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Air Melee", getX(5), getY(6), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Elem. Melee", getX(6), getY(6), p));

        damage_ID_Panels.add(new Damage_IDs_Panel("1st Spell Cost %", getX(0), getY(7), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("2nd Spell Cost %", getX(1), getY(7), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("3rd Spell Cost %", getX(2), getY(7), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("4th Spell Cost %", getX(3), getY(7), p));

        damage_ID_Panels.add(new Damage_IDs_Panel("Raw 1st Spell Cost", getX(0), getY(8), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw 2nd Spell Cost", getX(1), getY(8), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw 3rd Spell Cost", getX(2), getY(8), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw 4th Spell Cost", getX(3), getY(8), p));

        damage_ID_Panels.add(new Damage_IDs_Panel("Health Regen %", getX(6), getY(7), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Health Regen", getX(6), getY(8), p));

        damage_ID_Panels.add(new Damage_IDs_Panel("Elem. Dam %", getX(5), getY(0), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Elem. Dam", getX(6), getY(1), p));

        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Neutral Dam", getX(0), getY(1), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Earth Dam", getX(1), getY(1), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Thunder Dam", getX(2), getY(1), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Water Dam", getX(3), getY(1), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Fire Dam", getX(4), getY(1), p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Air Dam", getX(5), getY(1), p));
    }

    public Damage_IDs_Panel getID(int num) {
        return damage_ID_Panels.get(num);
    }

    public static int getX(int i) {
        return 10 + i * 130;
    }

    public static int getY(int i) {
        return 855 + i * 60;
    }

    public static class Damage_IDs_Panel {
        private final JTextField textField = new JTextField("0");
        private final JLabel original = new JLabel("Original: 0");

        public Damage_IDs_Panel(String name, int x, int y, JPanel p) {
            JPanel pane = new JPanel();
            pane.setBounds(x, y, 120, 52);
            pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
            JLabel nameP = new JLabel();
            nameP.setText(name);

            nameP.setAlignmentX(Component.CENTER_ALIGNMENT);
            textField.setAlignmentX(Component.CENTER_ALIGNMENT);
            original.setAlignmentX(Component.CENTER_ALIGNMENT);

            pane.add(nameP);
            pane.add(textField);
            pane.add(original);

            p.add(pane);
        }

        public void setValue(int value) {
            textField.setText(String.valueOf(value));
            original.setText("Original: " + value);
        }

        public void setTextValue(int value) {
            textField.setText(String.valueOf(value));
        }

        public int getValue() {
            int value = 0;
            if (textField.getText().matches("[+-]?\\d*(\\.\\d+)?")) {
                value = Integer.parseInt(textField.getText());
            }
            return value;
        }
    }
}
