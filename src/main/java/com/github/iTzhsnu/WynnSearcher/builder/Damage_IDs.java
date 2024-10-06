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

    public int size() {
        return GET_DAMAGE_ID_NUM_FROM_ID.size();
    }

    public static final Map<Identifications, Integer> GET_DAMAGE_ID_NUM_FROM_ID = new HashMap<Identifications, Integer>(65, 2);

    public Damage_IDs(JPanel p) {
        damage_ID_Panels.add(new Damage_IDs_Panel("Damage %", getX(0), getY(0), p, Identifications.DAMAGE_PERCENT));
        damage_ID_Panels.add(new Damage_IDs_Panel("Neutral Dam %", getX(1), getY(0), p, Identifications.NEUTRAL_DAMAGE_PERCENT));
        damage_ID_Panels.add(new Damage_IDs_Panel("Earth Dam %", getX(2), getY(0), p, Identifications.EARTH_DAMAGE_PERCENT));
        damage_ID_Panels.add(new Damage_IDs_Panel("Thunder Dam %", getX(3), getY(0), p, Identifications.THUNDER_DAMAGE_PERCENT));
        damage_ID_Panels.add(new Damage_IDs_Panel("Water Dam %", getX(4), getY(0), p, Identifications.WATER_DAMAGE_PERCENT));
        damage_ID_Panels.add(new Damage_IDs_Panel("Fire Dam %", getX(5), getY(0), p, Identifications.FIRE_DAMAGE_PERCENT));
        damage_ID_Panels.add(new Damage_IDs_Panel("Air Dam %", getX(6), getY(0), p, Identifications.AIR_DAMAGE_PERCENT));
        damage_ID_Panels.add(new Damage_IDs_Panel("Elem. Dam %", getX(7), getY(0), p, Identifications.ELEMENTAL_DAMAGE_PERCENT));

        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Damage", getX(0), getY(1), p, Identifications.RAW_DAMAGE));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Neutral Dam", getX(1), getY(1), p, Identifications.RAW_NEUTRAL_DAMAGE));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Earth Dam", getX(2), getY(1), p, Identifications.RAW_EARTH_DAMAGE));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Thunder Dam", getX(3), getY(1), p, Identifications.RAW_THUNDER_DAMAGE));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Water Dam", getX(4), getY(1), p, Identifications.RAW_WATER_DAMAGE));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Fire Dam", getX(5), getY(1), p, Identifications.RAW_FIRE_DAMAGE));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Air Dam", getX(6), getY(1), p, Identifications.RAW_AIR_DAMAGE));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Elem. Dam", getX(7), getY(1), p, Identifications.RAW_ELEMENTAL_DAMAGE));

        damage_ID_Panels.add(new Damage_IDs_Panel("Poison", getX(0), getY(2), p, Identifications.POISON));
        damage_ID_Panels.add(new Damage_IDs_Panel("Attack Speed", getX(1), getY(2), p, Identifications.ATTACK_SPEED_BONUS));
        damage_ID_Panels.add(new Damage_IDs_Panel("Health Regen %", getX(2), getY(2), p, Identifications.HEALTH_REGEN_PERCENT));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Health Regen", getX(3), getY(2), p, Identifications.RAW_HEALTH_REGEN));
        damage_ID_Panels.add(new Damage_IDs_Panel("Healing Efficiency", getX(4), getY(2), p, Identifications.HEALING_EFFICIENCY));

        damage_ID_Panels.add(new Damage_IDs_Panel("Spell Dam %", getX(0), getY(3), p, Identifications.SPELL_DAMAGE_PERCENT));
        damage_ID_Panels.add(new Damage_IDs_Panel("Neutral Spell %", getX(1), getY(3), p, Identifications.NEUTRAL_SPELL_DAMAGE_PERCENT));
        damage_ID_Panels.add(new Damage_IDs_Panel("Earth Spell %", getX(2), getY(3), p, Identifications.EARTH_SPELL_DAMAGE_PERCENT));
        damage_ID_Panels.add(new Damage_IDs_Panel("Thunder Spell %", getX(3), getY(3), p, Identifications.THUNDER_SPELL_DAMAGE_PERCENT));
        damage_ID_Panels.add(new Damage_IDs_Panel("Water Spell %", getX(4), getY(3), p, Identifications.WATER_SPELL_DAMAGE_PERCENT));
        damage_ID_Panels.add(new Damage_IDs_Panel("Fire Spell %", getX(5), getY(3), p, Identifications.FIRE_SPELL_DAMAGE_PERCENT));
        damage_ID_Panels.add(new Damage_IDs_Panel("Air Spell %", getX(6), getY(3), p, Identifications.AIR_SPELL_DAMAGE_PERCENT));
        damage_ID_Panels.add(new Damage_IDs_Panel("Elem. Spell %", getX(7), getY(3), p, Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT));

        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Spell Dam", getX(0), getY(4), p, Identifications.RAW_SPELL_DAMAGE));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Neutral Spell", getX(1), getY(4), p, Identifications.RAW_NEUTRAL_SPELL_DAMAGE));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Earth Spell", getX(2), getY(4), p, Identifications.RAW_EARTH_SPELL_DAMAGE));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Thunder Spell", getX(3), getY(4), p, Identifications.RAW_THUNDER_SPELL_DAMAGE));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Water Spell", getX(4), getY(4), p, Identifications.RAW_WATER_SPELL_DAMAGE));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Fire Spell", getX(5), getY(4), p, Identifications.RAW_FIRE_SPELL_DAMAGE));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Air Spell", getX(6), getY(4), p, Identifications.RAW_AIR_SPELL_DAMAGE));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Elem. Spell", getX(7), getY(4), p, Identifications.RAW_ELEMENTAL_SPELL_DAMAGE));

        damage_ID_Panels.add(new Damage_IDs_Panel("Melee Dam %", getX(0), getY(5), p, Identifications.MELEE_DAMAGE_PERCENT));
        damage_ID_Panels.add(new Damage_IDs_Panel("Neutral Melee %", getX(1), getY(5), p, Identifications.NEUTRAL_MELEE_DAMAGE_PERCENT));
        damage_ID_Panels.add(new Damage_IDs_Panel("Earth Melee %", getX(2), getY(5), p, Identifications.EARTH_MELEE_DAMAGE_PERCENT));
        damage_ID_Panels.add(new Damage_IDs_Panel("Thunder Melee %", getX(3), getY(5), p, Identifications.THUNDER_MELEE_DAMAGE_PERCENT));
        damage_ID_Panels.add(new Damage_IDs_Panel("Water Melee %", getX(4), getY(5), p, Identifications.WATER_MELEE_DAMAGE_PERCENT));
        damage_ID_Panels.add(new Damage_IDs_Panel("Fire Melee %", getX(5), getY(5), p, Identifications.FIRE_MELEE_DAMAGE_PERCENT));
        damage_ID_Panels.add(new Damage_IDs_Panel("Air Melee %", getX(6), getY(5), p, Identifications.AIR_MELEE_DAMAGE_PERCENT));
        damage_ID_Panels.add(new Damage_IDs_Panel("Elem. Melee %", getX(7), getY(5), p, Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT));

        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Melee Dam", getX(0), getY(6), p, Identifications.RAW_MELEE_DAMAGE));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Neutral Melee", getX(1), getY(6), p, Identifications.RAW_NEUTRAL_MELEE_DAMAGE));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Earth Melee", getX(2), getY(6), p, Identifications.RAW_EARTH_MELEE_DAMAGE));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Thunder Melee", getX(3), getY(6), p, Identifications.RAW_THUNDER_MELEE_DAMAGE));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Water Melee", getX(4), getY(6), p, Identifications.RAW_WATER_MELEE_DAMAGE));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Fire Melee", getX(5), getY(6), p, Identifications.RAW_FIRE_MELEE_DAMAGE));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Air Melee", getX(6), getY(6), p, Identifications.RAW_AIR_MELEE_DAMAGE));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Elem. Melee", getX(7), getY(6), p, Identifications.RAW_ELEMENTAL_MELEE_DAMAGE));

        damage_ID_Panels.add(new Damage_IDs_Panel("1st Spell Cost %", getX(0), getY(7), p, Identifications.PERCENT_1ST_SPELL_COST));
        damage_ID_Panels.add(new Damage_IDs_Panel("2nd Spell Cost %", getX(1), getY(7), p, Identifications.PERCENT_2ND_SPELL_COST));
        damage_ID_Panels.add(new Damage_IDs_Panel("3rd Spell Cost %", getX(2), getY(7), p, Identifications.PERCENT_3RD_SPELL_COST));
        damage_ID_Panels.add(new Damage_IDs_Panel("4th Spell Cost %", getX(3), getY(7), p, Identifications.PERCENT_4TH_SPELL_COST));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw 1st Spell Cost", getX(4), getY(7), p, Identifications.RAW_1ST_SPELL_COST));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw 2nd Spell Cost", getX(5), getY(7), p, Identifications.RAW_2ND_SPELL_COST));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw 3rd Spell Cost", getX(6), getY(7), p, Identifications.RAW_3RD_SPELL_COST));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw 4th Spell Cost", getX(7), getY(7), p, Identifications.RAW_4TH_SPELL_COST));

        damage_ID_Panels.add(new Damage_IDs_Panel("Reflection", getX(0), getY(8), p, Identifications.REFLECTION));
        damage_ID_Panels.add(new Damage_IDs_Panel("Stealing", getX(1), getY(8), p, Identifications.STEALING));
        damage_ID_Panels.add(new Damage_IDs_Panel("Life Steal", getX(2), getY(8), p, Identifications.LIFE_STEAL));
        damage_ID_Panels.add(new Damage_IDs_Panel("Walk Speed", getX(3), getY(8), p, Identifications.WALK_SPEED));
    }

    public Damage_IDs_Panel getID(int num) {
        return damage_ID_Panels.get(num);
    }

    public static int getX(int i) {
        return 10 + i * 130;
    }

    //OLD Y: 1130
    public static int getY(int i) {
        return 1630 + i * 60;
    }

    public static class Damage_IDs_Panel {
        private final JTextField textField = new JTextField("0");
        private final JLabel original = new JLabel("Original: 0");

        public Damage_IDs_Panel(String name, int x, int y, JPanel p, Identifications id) {
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

            GET_DAMAGE_ID_NUM_FROM_ID.put(id, GET_DAMAGE_ID_NUM_FROM_ID.size());
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
