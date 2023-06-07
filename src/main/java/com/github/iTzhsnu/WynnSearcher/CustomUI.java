package com.github.iTzhsnu.WynnSearcher;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomUI implements ActionListener {
    private final Container pane;
    //Variable Int Field
    private final List<JTextField> min = new ArrayList<>();
    private final List<JTextField> max = new ArrayList<>();

    //Not Variable Field
    private final JComboBox<String> atkSpdBox = new JComboBox<>();
    private final List<JTextField> notVariable = new ArrayList<>();

    //Other
    private final JCheckBox variable = new JCheckBox("ID Variable");
    private final List<JLabel> name = new ArrayList<>();
    private final JButton create = new JButton("Create");
    private final JButton load = new JButton("Load");
    private final JTextField itemText = new JTextField();
    private final JPanel display = new JPanel();
    private final JScrollPane scroll;
    private final JComboBox<String> itemType = new JComboBox<>();
    private final JPanel textScroll = new JPanel();

    private static final Map<Integer, Identifications> ID_FROM_NOT_VARIABLE_INT = new HashMap<Integer, Identifications>() {{
        put(1, Identifications.STRENGTH_REQ);
        put(2, Identifications.DEXTERITY_REQ);
        put(3, Identifications.INTELLIGENCE_REQ);
        put(4, Identifications.DEFENSE_REQ);
        put(5, Identifications.AGILITY_REQ);
        put(6, Identifications.POWDER_SLOTS);
        put(7, Identifications.HEALTH);
        put(8, Identifications.EARTH_DEFENSE);
        put(9, Identifications.THUNDER_DEFENSE);
        put(10, Identifications.WATER_DEFENSE);
        put(11, Identifications.FIRE_DEFENSE);
        put(12, Identifications.AIR_DEFENSE);
        put(13, Identifications.STRENGTH);
        put(14, Identifications.DEXTERITY);
        put(15, Identifications.INTELLIGENCE);
        put(16, Identifications.DEFENSE);
        put(17, Identifications.AGILITY);
        put(18, Identifications.HEALTH_BONUS);
        put(19, Identifications.RAW_HEALTH_REGEN);
        put(20, Identifications.HEALTH_REGEN_PERCENT);
        put(21, Identifications.LIFE_STEAL);
        put(22, Identifications.EARTH_DEFENSE_PERCENT);
        put(23, Identifications.THUNDER_DEFENSE_PERCENT);
        put(24, Identifications.WATER_DEFENSE_PERCENT);
        put(25, Identifications.FIRE_DEFENSE_PERCENT);
        put(26, Identifications.AIR_DEFENSE_PERCENT);
        put(27, Identifications.EARTH_DAMAGE_PERCENT);
        put(28, Identifications.THUNDER_DAMAGE_PERCENT);
        put(29, Identifications.WATER_DAMAGE_PERCENT);
        put(30, Identifications.FIRE_DAMAGE_PERCENT);
        put(31, Identifications.AIR_DAMAGE_PERCENT);
        put(32, Identifications.RAW_SPELL_DAMAGE);
        put(33, Identifications.RAW_MELEE_DAMAGE);
        put(34, Identifications.SPELL_DAMAGE_PERCENT);
        put(35, Identifications.MELEE_DAMAGE_PERCENT);
        put(36, Identifications.POISON);
        put(37, Identifications.ATTACK_SPEED_BONUS);
        put(38, Identifications.MANA_REGEN);
        put(39, Identifications.MANA_STEAL);
        put(40, Identifications.WALK_SPEED);
        put(41, Identifications.SPRINT_BONUS);
        put(42, Identifications.SPRINT_REGEN);
        put(43, Identifications.JUMP_HEIGHT);
        put(44, Identifications.THORNS);
        put(45, Identifications.REFLECTION);
        put(46, Identifications.EXPLODING);
        put(47, Identifications.STEALING);
        put(48, Identifications.COMBAT_XP_BONUS);
        put(49, Identifications.GATHERING_XP_BONUS);
        put(50, Identifications.GATHERING_SPEED_BONUS);
        put(51, Identifications.LOOT_BONUS);
        put(52, Identifications.LOOT_QUALITY);
        put(53, Identifications.SOUL_POINT_REGEN);
        put(54, Identifications.RAW_1ST_SPELL_COST);
        put(55, Identifications.RAW_2ND_SPELL_COST);
        put(56, Identifications.RAW_3RD_SPELL_COST);
        put(57, Identifications.RAW_4TH_SPELL_COST);
        put(58, Identifications.PERCENT_1ST_SPELL_COST);
        put(59, Identifications.PERCENT_2ND_SPELL_COST);
        put(60, Identifications.PERCENT_3RD_SPELL_COST);
        put(61, Identifications.PERCENT_4TH_SPELL_COST);
        put(62, Identifications.NEUTRAL_MELEE_DAMAGE_PERCENT);
        put(63, Identifications.EARTH_MELEE_DAMAGE_PERCENT);
        put(64, Identifications.THUNDER_MELEE_DAMAGE_PERCENT);
        put(65, Identifications.WATER_MELEE_DAMAGE_PERCENT);
        put(66, Identifications.FIRE_MELEE_DAMAGE_PERCENT);
        put(67, Identifications.AIR_MELEE_DAMAGE_PERCENT);
        put(68, Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT);
        put(69, Identifications.RAW_NEUTRAL_MELEE_DAMAGE);
        put(70, Identifications.RAW_EARTH_MELEE_DAMAGE);
        put(71, Identifications.RAW_THUNDER_MELEE_DAMAGE);
        put(72, Identifications.RAW_WATER_MELEE_DAMAGE);
        put(73, Identifications.RAW_FIRE_MELEE_DAMAGE);
        put(74, Identifications.RAW_AIR_MELEE_DAMAGE);
        put(75, Identifications.RAW_ELEMENTAL_MELEE_DAMAGE);
        put(76, Identifications.NEUTRAL_SPELL_DAMAGE_PERCENT);
        put(77, Identifications.EARTH_SPELL_DAMAGE_PERCENT);
        put(78, Identifications.THUNDER_SPELL_DAMAGE_PERCENT);
        put(79, Identifications.WATER_SPELL_DAMAGE_PERCENT);
        put(80, Identifications.FIRE_SPELL_DAMAGE_PERCENT);
        put(81, Identifications.AIR_SPELL_DAMAGE_PERCENT);
        put(82, Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT);
        put(83, Identifications.RAW_NEUTRAL_SPELL_DAMAGE);
        put(84, Identifications.RAW_EARTH_SPELL_DAMAGE);
        put(85, Identifications.RAW_THUNDER_SPELL_DAMAGE);
        put(86, Identifications.RAW_WATER_SPELL_DAMAGE);
        put(87, Identifications.RAW_FIRE_SPELL_DAMAGE);
        put(88, Identifications.RAW_AIR_SPELL_DAMAGE);
        put(89, Identifications.RAW_ELEMENTAL_SPELL_DAMAGE);
        put(90, Identifications.ELEMENTAL_DAMAGE_PERCENT);
        put(91, Identifications.RAW_ELEMENTAL_DAMAGE);
        put(92, Identifications.RAW_NEUTRAL_DAMAGE);
        put(93, Identifications.RAW_EARTH_DAMAGE);
        put(94, Identifications.RAW_THUNDER_DAMAGE);
        put(95, Identifications.RAW_WATER_DAMAGE);
        put(96, Identifications.RAW_FIRE_DAMAGE);
        put(97, Identifications.RAW_AIR_DAMAGE);
    }};

    private static final Map<Integer, Identifications> ID_FROM_VARIABLE_INT = new HashMap<Integer, Identifications>() {{
        put(0, Identifications.NEUTRAL_DAMAGE);
        put(1, Identifications.EARTH_DAMAGE);
        put(2, Identifications.THUNDER_DAMAGE);
        put(3, Identifications.WATER_DAMAGE);
        put(4, Identifications.FIRE_DAMAGE);
        put(5, Identifications.AIR_DAMAGE);
    }};

    public CustomUI(Container pane) {
        this.pane = pane;

        uiTemplate("Name", 20, getY(0), false, 95);
        uiTemplate("Strength Req", 20, getY(1), false, 95);
        uiTemplate("Dexterity Req", 20, getY(2), false, 95);
        uiTemplate("Intelligence Req", 20, getY(3), false, 95);
        uiTemplate("Defense Req", 20, getY(4), false, 95);
        uiTemplate("Agility Req", 20, getY(5), false, 95);
        uiTemplate("Powder Slots", 20, getY(6), false, 95);

        uiTemplate("Health", 20, getY(8), false, 95);
        uiTemplate("Earth Def", 20, getY(9), false, 95);
        uiTemplate("Thunder Def", 20, getY(10), false, 95);
        uiTemplate("Water Def", 20, getY(11), false, 95);
        uiTemplate("Fire Def", 20, getY(12), false, 95);
        uiTemplate("Air Def", 20, getY(13), false, 95);

        uiTemplate("Neutral Dam", 10, getY(8), true, 75);
        uiTemplate("Earth Dam", 10, getY(9), true, 75);
        uiTemplate("Thunder Dam", 10, getY(10), true, 75);
        uiTemplate("Water Dam", 10, getY(11), true, 75);
        uiTemplate("Fire Dam", 10, getY(12), true, 75);
        uiTemplate("Air Dam", 10, getY(13), true, 75);
        atkSpdBoxUITemplate("Atk Spd", 10, getY(14));

        uiTemplate("Strength", 20, getY(15), false, 95);
        uiTemplate("Dexterity", 20, getY(16), false, 95);
        uiTemplate("Intelligence", 20, getY(17), false, 95);
        uiTemplate("Defense", 20, getY(18), false, 95);
        uiTemplate("Agility", 20, getY(19), false, 95);

        uiTemplate("Health Bonus", 180, getY(0), false, 105);
        uiTemplate("Raw Health Regen", 180, getY(1), false, 105);
        uiTemplate("Health Regen %", 180, getY(2), false, 105);
        uiTemplate("Life Steal", 180, getY(3), false, 105);

        uiTemplate("Earth Def %", 180, getY(4), false, 105);
        uiTemplate("Thunder Def %", 180, getY(5), false, 105);
        uiTemplate("Water Def %", 180, getY(6), false, 105);
        uiTemplate("Fire Def %", 180, getY(7), false, 105);
        uiTemplate("Air Def %", 180, getY(8), false, 105);

        uiTemplate("Earth Dam %", 20, getY(21), false, 95);
        uiTemplate("Thunder Dam %", 20, getY(22), false, 95);
        uiTemplate("Water Dam %", 20, getY(23), false, 95);
        uiTemplate("Fire Dam %", 20, getY(24), false, 95);
        uiTemplate("Air Dam %", 20, getY(25), false, 95);

        uiTemplate("Raw Spell Dam", 180, getY(10), false, 105);
        uiTemplate("Raw Melee Dam", 180, getY(11), false, 105);
        uiTemplate("Spell Dam %", 180, getY(12), false, 105);
        uiTemplate("Melee Dam %", 180, getY(13), false, 105);
        uiTemplate("Poison", 180, getY(14), false, 105);
        uiTemplate("Atk Spd Bonus", 180, getY(15), false, 105);

        uiTemplate("Mana Regen", 180, getY(16), false, 105);
        uiTemplate("Mana Steal", 180, getY(17), false, 105);

        uiTemplate("Walk Speed", 345, getY(16), false, 105);
        uiTemplate("Sprint Bonus", 345, getY(17), false, 105);
        uiTemplate("Sprint Regen", 345, getY(18), false, 105);
        uiTemplate("Jump Height", 345, getY(19), false, 105);

        uiTemplate("Thorns", 510, getY(24), false, 115);
        uiTemplate("Reflection", 510, getY(25), false, 115);
        uiTemplate("Exploding", 510, getY(26), false, 115);
        uiTemplate("Stealing", 510, getY(27), false, 115);

        uiTemplate("Combat XP Bonus", 345, getY(0), false, 105);
        uiTemplate("Gathering XP", 345, getY(1), false, 105);
        uiTemplate("Gathering Speed", 345, getY(2), false, 105);
        uiTemplate("Loot Bonus", 345, getY(3), false, 105);
        uiTemplate("Loot Quality", 345, getY(4), false, 105);
        uiTemplate("Soul Point Regen", 345, getY(5), false, 105);

        uiTemplate("Raw 1st Cost", 345, getY(7), false, 105);
        uiTemplate("Raw 2nd Cost", 345, getY(8), false, 105);
        uiTemplate("Raw 3rd Cost", 345, getY(9), false, 105);
        uiTemplate("Raw 4th Cost", 345, getY(10), false, 105);

        uiTemplate("1st Spell Cost %", 345, getY(11), false, 105);
        uiTemplate("2nd Spell Cost %", 345, getY(12), false, 105);
        uiTemplate("3rd Spell Cost %", 345, getY(13), false, 105);
        uiTemplate("4th Spell Cost %", 345, getY(14), false, 105);

        uiTemplate("Neutral Melee %", 345, getY(21), false, 105);
        uiTemplate("Earth Melee %", 345, getY(22), false, 105);
        uiTemplate("Thunder Melee %", 345, getY(23), false, 105);
        uiTemplate("Water Melee %", 345, getY(24), false, 105);
        uiTemplate("Fire Melee %", 345, getY(25), false, 105);
        uiTemplate("Air Melee %", 345, getY(26), false, 105);
        uiTemplate("Elem. Melee %", 345, getY(27), false, 105);

        uiTemplate("Raw Neutral Melee", 510, getY(0), false, 115);
        uiTemplate("Raw Earth Melee", 510, getY(1), false, 115);
        uiTemplate("Raw Thunder Melee", 510, getY(2), false, 115);
        uiTemplate("Raw Water Melee", 510, getY(3), false, 115);
        uiTemplate("Raw Fire Melee", 510, getY(4), false, 115);
        uiTemplate("Raw Air Melee", 510, getY(5), false, 115);
        uiTemplate("Raw Elem. Melee", 510, getY(6), false, 115);

        uiTemplate("Neutral Spell %", 510, getY(8), false, 115);
        uiTemplate("Earth Spell %", 510, getY(9), false, 115);
        uiTemplate("Thunder Spell %", 510, getY(10), false, 115);
        uiTemplate("Water Spell %", 510, getY(11), false, 115);
        uiTemplate("Fire Spell %", 510, getY(12), false, 115);
        uiTemplate("Air Spell %", 510, getY(13), false, 115);
        uiTemplate("Elem. Spell %", 510, getY(14), false, 115);

        uiTemplate("Raw Neutral Spell", 510, getY(16), false, 115);
        uiTemplate("Raw Earth Spell", 510, getY(17), false, 115);
        uiTemplate("Raw Thunder Spell", 510, getY(18), false, 115);
        uiTemplate("Raw Water Spell", 510, getY(19), false, 115);
        uiTemplate("Raw Fire Spell", 510, getY(20), false, 115);
        uiTemplate("Raw Air Spell", 510, getY(21), false, 115);
        uiTemplate("Raw Elem. Spell", 510, getY(22), false, 115);

        uiTemplate("Elem. Dam %", 20, getY(26), false, 95);
        uiTemplate("Raw Elem. Dam", 180, getY(25), false, 105);

        uiTemplate("Raw Neutral Dam", 180, getY(19), false, 105);
        uiTemplate("Raw Earth Dam", 180, getY(20), false, 105);
        uiTemplate("Raw Thunder Dam", 180, getY(21), false, 105);
        uiTemplate("Raw Water Dam", 180, getY(22), false, 105);
        uiTemplate("Raw Fire Dam", 180, getY(23), false, 105);
        uiTemplate("Raw Air Dam", 180, getY(24), false, 105);

        variable.setBounds(185, 685, 90, 20);

        create.setBounds(675, 660, 80, 40);
        create.addActionListener(this);

        load.setBounds(675, 710, 80, 40);
        load.addActionListener(this);

        textScroll.setBounds(20, 715, 285, 40);
        textScroll.setLayout(new BoxLayout(textScroll, BoxLayout.Y_AXIS));
        JScrollBar textScrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
        textScrollBar.setUnitIncrement(20);
        textScrollBar.setModel(itemText.getHorizontalVisibility());
        textScroll.add(itemText);
        textScroll.add(textScrollBar);

        display.setBorder(new LineBorder(Color.BLACK));
        display.setPreferredSize(new Dimension(270, 747));
        display.setLayout(null);

        scroll = new JScrollPane(display);
        scroll.setBounds(770, 5, 288, 750);
        scroll.getVerticalScrollBar().setUnitIncrement(20);

        itemType.setBounds(300, 5, 100, 20);
        itemType.addItem("helmet");
        itemType.addItem("chestplate");
        itemType.addItem("leggings");
        itemType.addItem("boots");
        itemType.addItem("ring");
        itemType.addItem("bracelet");
        itemType.addItem("necklace");
        itemType.addItem("spear");
        itemType.addItem("dagger");
        itemType.addItem("bow");
        itemType.addItem("wand");
        itemType.addItem("relik");
        itemType.addActionListener(this);

        pane.add(create);
        pane.add(load);
        pane.add(variable);
        pane.add(textScroll);
        pane.add(scroll);
        pane.add(itemType);
    }

    public static int getY(int i) {
        return 30 + i * 25;
    }

    public void setCustomVisible(boolean visible) {
        for (JLabel l : name) {
            l.setVisible(visible);
        }
        for (int i = 0; min.size() > i; ++i) {
            min.get(i).setVisible(visible);
            max.get(i).setVisible(visible);
        }
        for (JTextField t : notVariable) {
            t.setVisible(visible);
        }
        atkSpdBox.setVisible(visible);
        variable.setVisible(visible);
        create.setVisible(visible);
        load.setVisible(visible);
        textScroll.setVisible(visible);
        scroll.setVisible(visible);
        itemType.setVisible(visible);

        if (visible) {
            String selected = itemType.getItemAt(itemType.getSelectedIndex());
            if (selected.equals("spear") || selected.equals("dagger") || selected.equals("bow") || selected.equals("wand") || selected.equals("relik")) {
                for (int i = 0; min.size() > i; ++i) {
                    min.get(i).setVisible(true);
                    max.get(i).setVisible(true);
                    name.get(i + 13).setVisible(true);
                }
                for (int i = 7; 12 >= i; ++i) {
                    notVariable.get(i).setVisible(false);
                    name.get(i).setVisible(false);
                }
                name.get(19).setVisible(true);
                atkSpdBox.setVisible(true);
            } else { //Armor and Accessories
                for (int i = 0; min.size() > i; ++i) {
                    min.get(i).setVisible(false);
                    max.get(i).setVisible(false);
                    name.get(i + 13).setVisible(false);
                }
                for (int i = 7; 12 >= i; ++i) {
                    notVariable.get(i).setVisible(true);
                    name.get(i).setVisible(true);
                }
                name.get(19).setVisible(false);
                atkSpdBox.setVisible(false);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == create) {
            createCustomItem();
        } else if (e.getSource() == load) {
            loadCustomItem();
        } else if (e.getSource() == itemType) {
            String selected = itemType.getItemAt(itemType.getSelectedIndex());
            if (selected.equals("spear") || selected.equals("dagger") || selected.equals("bow") || selected.equals("wand") || selected.equals("relik")) {
                for (int i = 0; min.size() > i; ++i) {
                    min.get(i).setVisible(true);
                    max.get(i).setVisible(true);
                    name.get(i + 13).setVisible(true);
                }
                for (int i = 7; 12 >= i; ++i) {
                    notVariable.get(i).setVisible(false);
                    name.get(i).setVisible(false);
                }
                name.get(19).setVisible(true);
                atkSpdBox.setVisible(true);
            } else { //Armor and Accessories
                for (int i = 0; min.size() > i; ++i) {
                    min.get(i).setVisible(false);
                    max.get(i).setVisible(false);
                    name.get(i + 13).setVisible(false);
                }
                for (int i = 7; 12 >= i; ++i) {
                    notVariable.get(i).setVisible(true);
                    name.get(i).setVisible(true);
                }
                name.get(19).setVisible(false);
                atkSpdBox.setVisible(false);
            }
        }
    }

    public void uiTemplate(String labelName, int posX, int posY, boolean isVariable, int width) {
        JLabel name = new JLabel(labelName);

        if (isVariable) {
            JTextField min = new JTextField();
            JTextField max = new JTextField();

            name.setBounds(posX + 45, posY, width + 20, 20);
            min.setBounds(posX, posY, 40, 20);
            max.setBounds(posX + width + 50, posY, 40, 20);

            this.min.add(min);
            this.max.add(max);

            pane.add(this.min.get(this.min.size() - 1));
            pane.add(this.max.get(this.max.size() - 1));
        } else {
            JTextField notVariable = new JTextField();
            name.setBounds(posX, posY, width + 20, 20);
            notVariable.setBounds(posX + width, posY, 40, 20);
            this.notVariable.add(notVariable);
            pane.add(this.notVariable.get(this.notVariable.size() - 1));
        }
        this.name.add(name);
        pane.add(this.name.get(this.name.size() - 1));
    }

    public void atkSpdBoxUITemplate(String labelName, int width, int height) {
        JLabel name = new JLabel(labelName);

        name.setBounds(width, height, 45, 20);
        atkSpdBox.setBounds(width + 50, height, 120, 20);

        atkSpdBox.addItem("super_fast");
        atkSpdBox.addItem("very_fast");
        atkSpdBox.addItem("fast");
        atkSpdBox.addItem("normal");
        atkSpdBox.addItem("slow");
        atkSpdBox.addItem("very_slow");
        atkSpdBox.addItem("super_slow");

        this.name.add(name);

        pane.add(this.name.get(this.name.size() - 1));
        pane.add(atkSpdBox);
    }

    public void createCustomItem() {
        display.removeAll();

        JsonObject j = JsonParser.parseString("{\"tier\":\"custom\"}").getAsJsonObject();
        if (notVariable.get(0).getText().isEmpty()) {
            j.addProperty("name", "Custom Item");
        } else {
            j.addProperty("name", notVariable.get(0).getText());
        }

        for (int i = 1; notVariable.size() > i; ++i) {
            if (i >= 7 && i <= 12) continue;
            if (!notVariable.get(i).getText().isEmpty()) {
                Identifications id = ID_FROM_NOT_VARIABLE_INT.get(i);
                if (notVariable.get(i).getText().matches("[+-]?\\d*(\\.\\d+)?") && !notVariable.get(i).getText().equals("0")) {
                    if (j.get(id.getItemFieldPos()) == null) j.add(id.getItemFieldPos(), JsonParser.parseString("{}"));
                    if (!variable.isSelected() || !id.isItemVariable()) { //Id Not Variable
                        j.get(id.getItemFieldPos()).getAsJsonObject().addProperty(id.getItemName(), Integer.parseInt(notVariable.get(i).getText()));
                    } else { //ID Variable
                        int min = Integer.parseInt(notVariable.get(i).getText());
                        int max = Integer.parseInt(notVariable.get(i).getText());
                        if (SearchUI.isReversedID(id)) {
                            min = ItemUITemplate.getReversedMinInt(min);
                            max = ItemUITemplate.getReversedMaxInt(max);
                        } else {
                            min = ItemUITemplate.getMinInt(min);
                            max = ItemUITemplate.getMaxInt(max);
                        }

                        j.get(id.getItemFieldPos()).getAsJsonObject().add(id.getItemName(), JsonParser.parseString("{\"min\":" + min + ",\"max\":" + max + "}"));
                    }
                }
            }
        }

        String selectedType = itemType.getItemAt(itemType.getSelectedIndex());
        if (selectedType.equals("spear") || selectedType.equals("dagger") || selectedType.equals("bow") || selectedType.equals("wand") || selectedType.equals("relik")) {
            //Neutral, Earth, Thunder, Water, Fire and Air Damage
            for (int i = 0; min.size() > i; ++i) {
                if (!min.get(i).getText().isEmpty() || !max.get(i).getText().isEmpty()) {
                    if (min.get(i).getText().matches("[+-]?\\d*(\\.\\d+)?") || max.get(i).getText().matches("[+-]?\\d*(\\.\\d+)?")) {
                        Identifications id = ID_FROM_VARIABLE_INT.get(i);
                        String minNum = min.get(i).getText();
                        String maxNum = max.get(i).getText();

                        if (!maxNum.matches("[+-]?\\d*(\\.\\d+)?") || maxNum.isEmpty()) {
                            maxNum = minNum;
                        } else if (!minNum.matches("[+-]?\\d*(\\.\\d+)?") || minNum.isEmpty()) {
                            minNum = maxNum;
                        }

                        if (j.get(id.getItemFieldPos()) == null) j.add(id.getItemFieldPos(), JsonParser.parseString("{}"));
                        j.get(id.getItemFieldPos()).getAsJsonObject().add(id.getItemName(), JsonParser.parseString("{\"min\":" + minNum + "\"max\":" + maxNum + "}"));
                    }
                }
            }

            //Attack Speed
            j.addProperty(Identifications.ATTACK_SPEED_BONUS.getItemName(), atkSpdBox.getItemAt(atkSpdBox.getSelectedIndex()));
        } else {
            //Health, Earth Defense, Thunder Defense, Water Defense, Fire Defense and Air Defense
            for (int i = 7; 12 >= i; ++i) {
                if (!notVariable.get(i).getText().isEmpty()) {
                    Identifications id = ID_FROM_NOT_VARIABLE_INT.get(i);
                    if (notVariable.get(i).getText().matches("[+-]?\\d*(\\.\\d+)?") && !notVariable.get(i).getText().equals("0")) {
                        if (j.get(id.getItemFieldPos()) == null) j.add(id.getItemFieldPos(), JsonParser.parseString("{}"));
                        j.get(id.getItemFieldPos()).getAsJsonObject().addProperty(id.getItemName(), Integer.parseInt(notVariable.get(i).getText()));
                    }
                }
            }
        }

        j.addProperty("type", selectedType);
        j.addProperty("identified", !variable.isSelected());

        ItemUITemplate itemUI = new ItemUITemplate(j, "item", null, null, 270, 0, true);
        display.add(itemUI);
        if (itemUI.getBounds().y + itemUI.getBounds().height > 747) {
            display.setPreferredSize(new Dimension(270, itemUI.getBounds().y + itemUI.getBounds().height));
        } else {
            display.setPreferredSize(new Dimension(270, 747));
        }
        itemText.setText("CI-" + j);
        SwingUtilities.updateComponentTreeUI(display);
    }

    public void loadCustomItem() {
        display.removeAll();
        if (itemText.getText().contains("CI-")) {
            JsonObject j = JsonParser.parseString(itemText.getText().replace("CI-", "")).getAsJsonObject();
            ItemUITemplate itemUI = new ItemUITemplate(j, "item", null, null, 270, 0, true);
            display.add(itemUI);
            if (itemUI.getBounds().y + itemUI.getBounds().height > 747) {
                display.setPreferredSize(new Dimension(270, itemUI.getBounds().y + itemUI.getBounds().height));
            } else {
                display.setPreferredSize(new Dimension(270, 747));
            }

            if (j.get("name") != null) notVariable.get(0).setText(j.get("name").getAsString());
            for (int i = 1; notVariable.size() > i; ++i) {
                Identifications id = ID_FROM_NOT_VARIABLE_INT.get(i);
                if (j.get(id.getItemFieldPos()) != null && j.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()) != null) {
                    JsonElement je = j.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName());
                    if (!je.isJsonObject()) {
                        notVariable.get(i).setText(String.valueOf(je.getAsInt()));
                    } else {
                        String getMinOrMax = "max";
                        if (je.getAsJsonObject().get("max").getAsInt() < 0 && !SearchUI.isReversedID(id)) getMinOrMax = "min";
                        notVariable.get(i).setText(String.valueOf(SearchUI.getBaseID(je.getAsJsonObject().get(getMinOrMax).getAsInt())));
                    }
                } else {
                    notVariable.get(i).setText("");
                }
            }
            for (int i = 0; min.size() > i; ++i) {
                Identifications id = ID_FROM_VARIABLE_INT.get(i);
                if (j.get(id.getItemFieldPos()) != null && j.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()) != null) {
                    JsonObject jo = j.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()).getAsJsonObject();
                    min.get(i).setText(String.valueOf(jo.get("min").getAsInt()));
                    max.get(i).setText(String.valueOf(jo.get("max").getAsInt()));
                } else {
                    min.get(i).setText("");
                    max.get(i).setText("");
                }
            }
            if (j.get("attackSpeed") != null) {
                switch (j.get("attackSpeed").getAsString()) {
                    case "super_fast":
                        atkSpdBox.setSelectedIndex(0);
                        break;
                    case "very_fast":
                        atkSpdBox.setSelectedIndex(1);
                        break;
                    case "fast":
                        atkSpdBox.setSelectedIndex(2);
                        break;
                    case "normal":
                        atkSpdBox.setSelectedIndex(3);
                        break;
                    case "slow":
                        atkSpdBox.setSelectedIndex(4);
                        break;
                    case "very_slow":
                        atkSpdBox.setSelectedIndex(5);
                        break;
                    case "super_slow":
                        atkSpdBox.setSelectedIndex(6);
                        break;
                }
            }
            if (j.get("identified") != null) variable.setSelected(!j.get("identified").getAsBoolean());
        }
        SwingUtilities.updateComponentTreeUI(display);
    }
}
