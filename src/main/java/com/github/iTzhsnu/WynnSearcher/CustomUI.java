package com.github.iTzhsnu.WynnSearcher;

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

        uiTemplate("Name", 20, 30, false, 95);
        uiTemplate("Strength Req", 20, 55, false, 95);
        uiTemplate("Dexterity Req", 20, 80, false, 95);
        uiTemplate("Intelligence Req", 20, 105, false, 95);
        uiTemplate("Defense Req", 20, 130, false, 95);
        uiTemplate("Agility Req", 20, 155, false, 95);
        uiTemplate("Powder Slots", 20, 180, false, 95);

        uiTemplate("Health", 20, 225, false, 95);
        uiTemplate("Earth Def", 20, 250, false, 95);
        uiTemplate("Thunder Def", 20, 275, false, 95);
        uiTemplate("Water Def", 20, 300, false, 95);
        uiTemplate("Fire Def", 20, 325, false, 95);
        uiTemplate("Air Def", 20, 350, false, 95);

        uiTemplate("Neutral Dam", 20, 395, true, 75);
        uiTemplate("Earth Dam", 20, 420, true, 75);
        uiTemplate("Thunder Dam", 20, 445, true, 75);
        uiTemplate("Water Dam", 20, 470, true, 75);
        uiTemplate("Fire Dam", 20, 495, true, 75);
        uiTemplate("Air Dam", 20, 520, true, 75);
        atkSpdBoxUITemplate("Atk Spd", 20, 545);

        uiTemplate("Strength", 20, 590, false, 95);
        uiTemplate("Dexterity", 20, 615, false, 95);
        uiTemplate("Intelligence", 20, 640, false, 95);
        uiTemplate("Defense", 20, 665, false, 95);
        uiTemplate("Agility", 20, 690, false, 95);

        uiTemplate("Health Bonus", 180, 30, false, 105);
        uiTemplate("Raw Health Regen", 180, 55, false, 105);
        uiTemplate("Health Regen %", 180, 80, false, 105);
        uiTemplate("Life Steal", 180, 105, false, 105);

        uiTemplate("Earth Def %", 180, 130, false, 105);
        uiTemplate("Thunder Def %", 180, 155, false, 105);
        uiTemplate("Water Def %", 180, 180, false, 105);
        uiTemplate("Fire Def %", 180, 205, false, 105);
        uiTemplate("Air Def %", 180, 230, false, 105);

        uiTemplate("Earth Dam %", 180, 255, false, 105);
        uiTemplate("Thunder Dam %", 180, 280, false, 105);
        uiTemplate("Water Dam %", 180, 305, false, 105);
        uiTemplate("Fire Dam %", 180, 330, false, 105);
        uiTemplate("Air Dam %", 180, 355, false, 105);

        uiTemplate("Raw Spell Dam", 200, 400, false, 95);
        uiTemplate("Raw Melee Dam", 200, 425, false, 95);
        uiTemplate("Spell Dam %", 200, 450, false, 95);
        uiTemplate("Melee Dam %", 200, 475, false, 95);
        uiTemplate("Poison", 200, 500, false, 95);
        uiTemplate("Atk Spd Bonus", 200, 525, false, 95);

        uiTemplate("Mana Regen", 200, 550, false, 95);
        uiTemplate("Mana Steal", 200, 575, false, 95);

        uiTemplate("Walk Speed", 200, 620, false, 95);
        uiTemplate("Sprint Bonus", 200, 645, false, 95);
        uiTemplate("Sprint Regen", 200, 670, false, 95);
        uiTemplate("Jump Height", 200, 695, false, 95);

        uiTemplate("Thorns", 370, 30, false, 80);
        uiTemplate("Reflection", 370, 55, false, 80);
        uiTemplate("Exploding", 370, 80, false, 80);
        uiTemplate("Stealing", 370, 105, false, 80);

        uiTemplate("Combat XP Bonus", 345, 150, false, 105);
        uiTemplate("Gathering XP", 345, 175, false, 105);
        uiTemplate("Gathering Speed", 345, 200, false, 105);
        uiTemplate("Loot Bonus", 345, 225, false, 105);
        uiTemplate("Loot Quality", 345, 250, false, 105);
        uiTemplate("Soul Point Regen", 345, 275, false, 105);

        uiTemplate("Raw 1st Cost", 365, 320, false, 85);
        uiTemplate("Raw 2nd Cost", 365, 345, false, 85);
        uiTemplate("Raw 3rd Cost", 365, 370, false, 85);
        uiTemplate("Raw 4th Cost", 365, 395, false, 85);

        uiTemplate("1st Spell Cost %", 355, 420, false, 95);
        uiTemplate("2nd Spell Cost %", 355, 445, false, 95);
        uiTemplate("3rd Spell Cost %", 355, 470, false, 95);
        uiTemplate("4th Spell Cost %", 355, 495, false, 95);

        uiTemplate("Neutral Melee %", 350, 540, false, 100);
        uiTemplate("Earth Melee %", 350, 565, false, 100);
        uiTemplate("Thunder Melee %", 350, 590, false, 100);
        uiTemplate("Water Melee %", 350, 615, false, 100);
        uiTemplate("Fire Melee %", 350, 640, false, 100);
        uiTemplate("Air Melee %", 350, 665, false, 100);
        uiTemplate("Elem. Melee %", 350, 690, false, 100);

        uiTemplate("Raw Neutral Melee", 510, 30, false, 110);
        uiTemplate("Raw Earth Melee", 510, 55, false, 110);
        uiTemplate("Raw Thunder Melee", 510, 80, false, 110);
        uiTemplate("Raw Water Melee", 510, 105, false, 110);
        uiTemplate("Raw Fire Melee", 510, 130, false, 110);
        uiTemplate("Raw Air Melee", 510, 155, false, 110);
        uiTemplate("Raw Elem. Melee", 510, 180, false, 110);

        uiTemplate("Neutral Spell %", 520, 225, false, 100);
        uiTemplate("Earth Spell %", 520, 250, false, 100);
        uiTemplate("Thunder Spell %", 520, 275, false, 100);
        uiTemplate("Water Spell %", 520, 300, false, 100);
        uiTemplate("Fire Spell %", 520, 325, false, 100);
        uiTemplate("Air Spell %", 520, 350, false, 100);
        uiTemplate("Elem. Spell %", 520, 375, false, 100);

        uiTemplate("Raw Neutral Spell", 510, 420, false, 110);
        uiTemplate("Raw Earth Spell", 510, 445, false, 110);
        uiTemplate("Raw Thunder Spell", 510, 470, false, 110);
        uiTemplate("Raw Water Spell", 510, 495, false, 110);
        uiTemplate("Raw Fire Spell", 510, 520, false, 110);
        uiTemplate("Raw Air Spell", 510, 545, false, 110);
        uiTemplate("Raw Elem. Spell", 510, 570, false, 110);

        variable.setBounds(510, 605, 100, 20);

        create.setBounds(520, 635, 80, 40);
        create.addActionListener(this);

        load.setBounds(610, 635, 80, 40);
        load.addActionListener(this);

        itemText.setBounds(520, 695, 200, 20);

        display.setBorder(new LineBorder(Color.BLACK));
        display.setPreferredSize(new Dimension(270, 747));
        display.setLayout(null);

        scroll = new JScrollPane(display);
        scroll.setBounds(750, 5, 288, 750);
        scroll.getVerticalScrollBar().setUnitIncrement(20);

        pane.add(create);
        pane.add(load);
        pane.add(variable);
        pane.add(itemText);
        pane.add(scroll);
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
        itemText.setVisible(visible);
        scroll.setVisible(visible);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == create) {
            createCustomItem();
        } else if (e.getSource() == load) {
            loadCustomItem();
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

        atkSpdBox.addItem("SUPER_FAST");
        atkSpdBox.addItem("VERY_FAST");
        atkSpdBox.addItem("FAST");
        atkSpdBox.addItem("NORMAL");
        atkSpdBox.addItem("SLOW");
        atkSpdBox.addItem("VERY_SLOW");
        atkSpdBox.addItem("SUPER_SLOW");

        this.name.add(name);

        pane.add(this.name.get(this.name.size() - 1));
        pane.add(atkSpdBox);
    }

    public void createCustomItem() {
        display.removeAll();

        StringBuilder itemData = new StringBuilder();
        itemData.append("{");
        if (notVariable.get(0).getText().isEmpty()) {
            itemData.append("\"name\":\"Custom Item\",");
        } else {
            String s = "\"name\":\"" + notVariable.get(0).getText() + "\",";
            itemData.append(s);
        }

        for (int i = 1; notVariable.size() > i; ++i) {
            if (!notVariable.get(i).getText().isEmpty()) {
                Identifications id = ID_FROM_NOT_VARIABLE_INT.get(i);
                String s = id.getItemName() + ":" + notVariable.get(i).getText() + ",";
                if (notVariable.get(i).getText().matches("[+-]?\\d*(\\.\\d+)?")) itemData.append(s);
            }
        }

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

                    String s = id.getItemName() + ":\"" + minNum + "-" + maxNum + "\",";
                    itemData.append(s);
                }
            }
        }

        String atkSpdS = Identifications.ATTACK_SPEED.getItemName() + ":\"" + atkSpdBox.getItemAt(atkSpdBox.getSelectedIndex()) + "\",";
        itemData.append(atkSpdS);

        if (variable.isSelected()) {
            itemData.append("\"tier\":\"Custom\",\"identified\":false}");
        } else {
            itemData.append("\"tier\":\"Custom\",\"identified\":true}");
        }

        ItemUITemplate itemUI = new ItemUITemplate(JsonParser.parseString(itemData.toString()).getAsJsonObject(), false, null, null, 270, 0, true);
        display.add(itemUI);
        if (itemUI.getBounds().y + itemUI.getBounds().height > 747) {
            display.setPreferredSize(new Dimension(270, itemUI.getBounds().y + itemUI.getBounds().height));
        } else {
            display.setPreferredSize(new Dimension(270, 747));
        }
        itemText.setText("CI-" + itemData);
        SwingUtilities.updateComponentTreeUI(display);
    }

    public void loadCustomItem() {
        display.removeAll();
        if (itemText.getText().contains("CI-")) {
            JsonObject j = JsonParser.parseString(itemText.getText().replace("CI-", "")).getAsJsonObject();
            ItemUITemplate itemUI = new ItemUITemplate(j, false, null, null, 270, 0, true);
            display.add(itemUI);
            if (itemUI.getBounds().y + itemUI.getBounds().height > 747) {
                display.setPreferredSize(new Dimension(270, itemUI.getBounds().y + itemUI.getBounds().height));
            } else {
                display.setPreferredSize(new Dimension(270, 747));
            }

            if (j.get("name") != null) notVariable.get(0).setText(j.get("name").getAsString());
            for (int i = 1; notVariable.size() > i; ++i) {
                Identifications id = ID_FROM_NOT_VARIABLE_INT.get(i);
                if (j.get(id.getItemName()) != null) {
                    if (j.get(id.getItemName()).getAsInt() != 0) {
                        notVariable.get(i).setText(String.valueOf(j.get(id.getItemName()).getAsInt()));
                    } else {
                        notVariable.get(i).setText("");
                    }
                }
            }
            for (int i = 0; min.size() > i; ++i) {
                Identifications id = ID_FROM_VARIABLE_INT.get(i);
                if (j.get(id.getItemName()) != null) {
                    if (!j.get(id.getItemName()).getAsString().equals("0-0")) {
                        if (j.get(id.getItemName()).getAsString().contains("-")) {
                            String[] ss = j.get(id.getItemName()).getAsString().split("-");
                            min.get(i).setText(ss[0]);
                            max.get(i).setText(ss[ss.length - 1]);
                        }
                    } else {
                        min.get(i).setText("");
                        max.get(i).setText("");
                    }
                }
            }
            if (j.get("attackSpeed") != null) {
                switch (j.get("attackSpeed").getAsString()) {
                    case "SUPER_FAST":
                        atkSpdBox.setSelectedIndex(0);
                        break;
                    case "VERY_FAST":
                        atkSpdBox.setSelectedIndex(1);
                        break;
                    case "FAST":
                        atkSpdBox.setSelectedIndex(2);
                        break;
                    case "NORMAL":
                        atkSpdBox.setSelectedIndex(3);
                        break;
                    case "SLOW":
                        atkSpdBox.setSelectedIndex(4);
                        break;
                    case "VERY_SLOW":
                        atkSpdBox.setSelectedIndex(5);
                        break;
                    case "SUPER_SLOW":
                        atkSpdBox.setSelectedIndex(6);
                        break;
                }
            }
            if (j.get("identified") != null) variable.setSelected(!j.get("identified").getAsBoolean());
        }
        SwingUtilities.updateComponentTreeUI(display);
    }
}
