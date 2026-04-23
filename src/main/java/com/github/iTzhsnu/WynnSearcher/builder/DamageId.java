package com.github.iTzhsnu.WynnSearcher.builder;

import com.github.iTzhsnu.WynnSearcher.Identifications;
import com.github.iTzhsnu.WynnSearcher.ui.UiUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DamageId {
    private final List<DamageIdPanel> damageIdPanels = new ArrayList<>();

    public int size() {
        return GET_DAMAGE_ID_NUM_FROM_ID.size();
    }

    public static final Map<Identifications, Integer> GET_DAMAGE_ID_NUM_FROM_ID = new HashMap<>(66, 2);

    public DamageId(JPanel p) {
        damageIdPanels.add(new DamageIdPanel("Damage %", getX(0), getY(0), p, Identifications.DAMAGE_PERCENT));
        damageIdPanels.add(new DamageIdPanel("Neutral Dam %", getX(1), getY(0), p, Identifications.NEUTRAL_DAMAGE_PERCENT));
        damageIdPanels.add(new DamageIdPanel("Earth Dam %", getX(2), getY(0), p, Identifications.EARTH_DAMAGE_PERCENT));
        damageIdPanels.add(new DamageIdPanel("Thunder Dam %", getX(3), getY(0), p, Identifications.THUNDER_DAMAGE_PERCENT));
        damageIdPanels.add(new DamageIdPanel("Water Dam %", getX(4), getY(0), p, Identifications.WATER_DAMAGE_PERCENT));
        damageIdPanels.add(new DamageIdPanel("Fire Dam %", getX(5), getY(0), p, Identifications.FIRE_DAMAGE_PERCENT));
        damageIdPanels.add(new DamageIdPanel("Air Dam %", getX(6), getY(0), p, Identifications.AIR_DAMAGE_PERCENT));
        damageIdPanels.add(new DamageIdPanel("Elem. Dam %", getX(7), getY(0), p, Identifications.ELEMENTAL_DAMAGE_PERCENT));

        damageIdPanels.add(new DamageIdPanel("Raw Damage", getX(0), getY(1), p, Identifications.RAW_DAMAGE));
        damageIdPanels.add(new DamageIdPanel("Raw Neutral Dam", getX(1), getY(1), p, Identifications.RAW_NEUTRAL_DAMAGE));
        damageIdPanels.add(new DamageIdPanel("Raw Earth Dam", getX(2), getY(1), p, Identifications.RAW_EARTH_DAMAGE));
        damageIdPanels.add(new DamageIdPanel("Raw Thunder Dam", getX(3), getY(1), p, Identifications.RAW_THUNDER_DAMAGE));
        damageIdPanels.add(new DamageIdPanel("Raw Water Dam", getX(4), getY(1), p, Identifications.RAW_WATER_DAMAGE));
        damageIdPanels.add(new DamageIdPanel("Raw Fire Dam", getX(5), getY(1), p, Identifications.RAW_FIRE_DAMAGE));
        damageIdPanels.add(new DamageIdPanel("Raw Air Dam", getX(6), getY(1), p, Identifications.RAW_AIR_DAMAGE));
        damageIdPanels.add(new DamageIdPanel("Raw Elem. Dam", getX(7), getY(1), p, Identifications.RAW_ELEMENTAL_DAMAGE));

        damageIdPanels.add(new DamageIdPanel("Poison", getX(0), getY(2), p, Identifications.POISON));
        damageIdPanels.add(new DamageIdPanel("Attack Speed", getX(1), getY(2), p, Identifications.ATTACK_SPEED_BONUS));
        damageIdPanels.add(new DamageIdPanel("Health Regen %", getX(2), getY(2), p, Identifications.HEALTH_REGEN_PERCENT));
        damageIdPanels.add(new DamageIdPanel("Raw Health Regen", getX(3), getY(2), p, Identifications.RAW_HEALTH_REGEN));
        damageIdPanels.add(new DamageIdPanel("Healing Efficiency", getX(4), getY(2), p, Identifications.HEALING_EFFICIENCY));

        damageIdPanels.add(new DamageIdPanel("Spell Dam %", getX(0), getY(3), p, Identifications.SPELL_DAMAGE_PERCENT));
        damageIdPanels.add(new DamageIdPanel("Neutral Spell %", getX(1), getY(3), p, Identifications.NEUTRAL_SPELL_DAMAGE_PERCENT));
        damageIdPanels.add(new DamageIdPanel("Earth Spell %", getX(2), getY(3), p, Identifications.EARTH_SPELL_DAMAGE_PERCENT));
        damageIdPanels.add(new DamageIdPanel("Thunder Spell %", getX(3), getY(3), p, Identifications.THUNDER_SPELL_DAMAGE_PERCENT));
        damageIdPanels.add(new DamageIdPanel("Water Spell %", getX(4), getY(3), p, Identifications.WATER_SPELL_DAMAGE_PERCENT));
        damageIdPanels.add(new DamageIdPanel("Fire Spell %", getX(5), getY(3), p, Identifications.FIRE_SPELL_DAMAGE_PERCENT));
        damageIdPanels.add(new DamageIdPanel("Air Spell %", getX(6), getY(3), p, Identifications.AIR_SPELL_DAMAGE_PERCENT));
        damageIdPanels.add(new DamageIdPanel("Elem. Spell %", getX(7), getY(3), p, Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT));

        damageIdPanels.add(new DamageIdPanel("Raw Spell Dam", getX(0), getY(4), p, Identifications.RAW_SPELL_DAMAGE));
        damageIdPanels.add(new DamageIdPanel("Raw Neutral Spell", getX(1), getY(4), p, Identifications.RAW_NEUTRAL_SPELL_DAMAGE));
        damageIdPanels.add(new DamageIdPanel("Raw Earth Spell", getX(2), getY(4), p, Identifications.RAW_EARTH_SPELL_DAMAGE));
        damageIdPanels.add(new DamageIdPanel("Raw Thunder Spell", getX(3), getY(4), p, Identifications.RAW_THUNDER_SPELL_DAMAGE));
        damageIdPanels.add(new DamageIdPanel("Raw Water Spell", getX(4), getY(4), p, Identifications.RAW_WATER_SPELL_DAMAGE));
        damageIdPanels.add(new DamageIdPanel("Raw Fire Spell", getX(5), getY(4), p, Identifications.RAW_FIRE_SPELL_DAMAGE));
        damageIdPanels.add(new DamageIdPanel("Raw Air Spell", getX(6), getY(4), p, Identifications.RAW_AIR_SPELL_DAMAGE));
        damageIdPanels.add(new DamageIdPanel("Raw Elem. Spell", getX(7), getY(4), p, Identifications.RAW_ELEMENTAL_SPELL_DAMAGE));

        damageIdPanels.add(new DamageIdPanel("Melee Dam %", getX(0), getY(5), p, Identifications.MELEE_DAMAGE_PERCENT));
        damageIdPanels.add(new DamageIdPanel("Neutral Melee %", getX(1), getY(5), p, Identifications.NEUTRAL_MELEE_DAMAGE_PERCENT));
        damageIdPanels.add(new DamageIdPanel("Earth Melee %", getX(2), getY(5), p, Identifications.EARTH_MELEE_DAMAGE_PERCENT));
        damageIdPanels.add(new DamageIdPanel("Thunder Melee %", getX(3), getY(5), p, Identifications.THUNDER_MELEE_DAMAGE_PERCENT));
        damageIdPanels.add(new DamageIdPanel("Water Melee %", getX(4), getY(5), p, Identifications.WATER_MELEE_DAMAGE_PERCENT));
        damageIdPanels.add(new DamageIdPanel("Fire Melee %", getX(5), getY(5), p, Identifications.FIRE_MELEE_DAMAGE_PERCENT));
        damageIdPanels.add(new DamageIdPanel("Air Melee %", getX(6), getY(5), p, Identifications.AIR_MELEE_DAMAGE_PERCENT));
        damageIdPanels.add(new DamageIdPanel("Elem. Melee %", getX(7), getY(5), p, Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT));

        damageIdPanels.add(new DamageIdPanel("Raw Melee Dam", getX(0), getY(6), p, Identifications.RAW_MELEE_DAMAGE));
        damageIdPanels.add(new DamageIdPanel("Raw Neutral Melee", getX(1), getY(6), p, Identifications.RAW_NEUTRAL_MELEE_DAMAGE));
        damageIdPanels.add(new DamageIdPanel("Raw Earth Melee", getX(2), getY(6), p, Identifications.RAW_EARTH_MELEE_DAMAGE));
        damageIdPanels.add(new DamageIdPanel("Raw Thunder Melee", getX(3), getY(6), p, Identifications.RAW_THUNDER_MELEE_DAMAGE));
        damageIdPanels.add(new DamageIdPanel("Raw Water Melee", getX(4), getY(6), p, Identifications.RAW_WATER_MELEE_DAMAGE));
        damageIdPanels.add(new DamageIdPanel("Raw Fire Melee", getX(5), getY(6), p, Identifications.RAW_FIRE_MELEE_DAMAGE));
        damageIdPanels.add(new DamageIdPanel("Raw Air Melee", getX(6), getY(6), p, Identifications.RAW_AIR_MELEE_DAMAGE));
        damageIdPanels.add(new DamageIdPanel("Raw Elem. Melee", getX(7), getY(6), p, Identifications.RAW_ELEMENTAL_MELEE_DAMAGE));

        damageIdPanels.add(new DamageIdPanel("1st Spell Cost %", getX(0), getY(7), p, Identifications.PERCENT_1ST_SPELL_COST));
        damageIdPanels.add(new DamageIdPanel("2nd Spell Cost %", getX(1), getY(7), p, Identifications.PERCENT_2ND_SPELL_COST));
        damageIdPanels.add(new DamageIdPanel("3rd Spell Cost %", getX(2), getY(7), p, Identifications.PERCENT_3RD_SPELL_COST));
        damageIdPanels.add(new DamageIdPanel("4th Spell Cost %", getX(3), getY(7), p, Identifications.PERCENT_4TH_SPELL_COST));
        damageIdPanels.add(new DamageIdPanel("Raw 1st Spell Cost", getX(4), getY(7), p, Identifications.RAW_1ST_SPELL_COST));
        damageIdPanels.add(new DamageIdPanel("Raw 2nd Spell Cost", getX(5), getY(7), p, Identifications.RAW_2ND_SPELL_COST));
        damageIdPanels.add(new DamageIdPanel("Raw 3rd Spell Cost", getX(6), getY(7), p, Identifications.RAW_3RD_SPELL_COST));
        damageIdPanels.add(new DamageIdPanel("Raw 4th Spell Cost", getX(7), getY(7), p, Identifications.RAW_4TH_SPELL_COST));

        damageIdPanels.add(new DamageIdPanel("Reflection", getX(0), getY(8), p, Identifications.REFLECTION));
        damageIdPanels.add(new DamageIdPanel("Stealing", getX(1), getY(8), p, Identifications.STEALING));
        damageIdPanels.add(new DamageIdPanel("Life Steal", getX(2), getY(8), p, Identifications.LIFE_STEAL));
        damageIdPanels.add(new DamageIdPanel("Walk Speed", getX(3), getY(8), p, Identifications.WALK_SPEED));
        damageIdPanels.add(new DamageIdPanel("Health Bonus", getX(4), getY(8), p, Identifications.HEALTH_BONUS));
    }

    public DamageIdPanel getID(int num) {
        return damageIdPanels.get(num);
    }

    public static int getX(int i) {
        return 10 + i * 130;
    }

    //OLD Y: 1130
    public static int getY(int i) {
        return 1630 + i * 60;
    }

    public static class DamageIdPanel {
        private final JTextField textField = UiUtils.createNoBeepTextField("0");
        private final JLabel original = new JLabel("Original: 0");

        public DamageIdPanel(String name, int x, int y, JPanel p, Identifications id) {
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
