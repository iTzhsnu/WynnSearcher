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
    }};

    public Damage_IDs(JPanel p) {
        damage_ID_Panels.add(new Damage_IDs_Panel("Earth Dam %", 10, 720, p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Thunder Dam %", 140, 720, p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Water Dam %", 270, 720, p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Fire Dam %", 400, 720, p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Air Dam %", 530, 720, p));

        damage_ID_Panels.add(new Damage_IDs_Panel("Spell Dam %", 10, 787, p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Spell Dam", 140, 787, p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Melee Dam %", 400, 787, p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Melee Dam", 530, 787, p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Poison", 660, 787, p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Attack Speed", 790, 787, p));

        damage_ID_Panels.add(new Damage_IDs_Panel("Neutral Spell %", 10, 837, p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Earth Spell %", 140, 837, p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Thunder Spell %", 270, 837, p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Water Spell %", 400, 837, p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Fire Spell %", 530, 837, p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Air Spell %", 660, 837, p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Elem. Spell %", 790, 837, p));

        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Neutral Spell", 10, 894, p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Earth Spell", 140, 894, p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Thunder Spell", 270, 894, p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Water Spell", 400, 894, p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Fire Spell", 530, 894, p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Air Spell", 660, 894, p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Elem. Spell", 790, 894, p));

        damage_ID_Panels.add(new Damage_IDs_Panel("Neutral Melee %", 10, 956, p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Earth Melee %", 140, 956, p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Thunder Melee %", 270, 956, p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Water Melee %", 400, 956, p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Fire Melee %", 530, 956, p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Air Melee %", 660, 956, p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Elem. Melee %", 790, 956, p));

        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Neutral Melee", 10, 1013, p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Earth Melee", 140, 1013, p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Thunder Melee", 270, 1013, p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Water Melee", 400, 1013, p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Fire Melee", 530, 1013, p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Air Melee", 660, 1013, p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Elem. Melee", 790, 1013, p));

        damage_ID_Panels.add(new Damage_IDs_Panel("1st Spell Cost %", 10, 1075, p));
        damage_ID_Panels.add(new Damage_IDs_Panel("2nd Spell Cost %", 140, 1075, p));
        damage_ID_Panels.add(new Damage_IDs_Panel("3rd Spell Cost %", 270, 1075, p));
        damage_ID_Panels.add(new Damage_IDs_Panel("4th Spell Cost %", 400, 1075, p));

        damage_ID_Panels.add(new Damage_IDs_Panel("Raw 1st Spell Cost", 10, 1132, p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw 2nd Spell Cost", 140, 1132, p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw 3rd Spell Cost", 270, 1132, p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw 4th Spell Cost", 400, 1132, p));

        damage_ID_Panels.add(new Damage_IDs_Panel("Health Regen %", 790, 1075, p));
        damage_ID_Panels.add(new Damage_IDs_Panel("Raw Health Regen", 790, 1132, p));
    }

    public Damage_IDs_Panel getID(int num) {
        return damage_ID_Panels.get(num);
    }


    public static class Damage_IDs_Panel {
        private final JTextField textField = new JTextField("0");
        private final JLabel name = new JLabel();
        private final JLabel original = new JLabel("Original: 0");

        public Damage_IDs_Panel(String name, int x, int y, JPanel p) {
            JPanel pane = new JPanel();
            pane.setBounds(x, y, 120, 52);
            pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
            this.name.setText(name);

            this.name.setAlignmentX(Component.CENTER_ALIGNMENT);
            textField.setAlignmentX(Component.CENTER_ALIGNMENT);
            original.setAlignmentX(Component.CENTER_ALIGNMENT);

            pane.add(this.name);
            pane.add(textField);
            pane.add(original);

            p.add(pane);
        }

        public void setValue(int value) {
            textField.setText(String.valueOf(value));
            original.setText("Original: " + value);
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
