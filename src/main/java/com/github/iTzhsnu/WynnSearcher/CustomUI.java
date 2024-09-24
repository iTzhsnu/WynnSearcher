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
    private final JComboBox<String> majorIDBox = new JComboBox<>();
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
    private final JComboBox<String> rarity = new JComboBox<>();
    private final JLabel majorIDText = new JLabel("Major ID");
    private final JsonObject majorIDJson;

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
        uiTemplate("Mana Steal", 180, getY(17), false, 105);
        uiTemplate("Mana Regen", 180, getY(16), false, 105);

        uiTemplate("Earth Def %", 180, getY(4), false, 105);
        uiTemplate("Thunder Def %", 180, getY(5), false, 105);
        uiTemplate("Water Def %", 180, getY(6), false, 105);
        uiTemplate("Fire Def %", 180, getY(7), false, 105);
        uiTemplate("Air Def %", 180, getY(8), false, 105);
        uiTemplate("Elem. Defense %", 685, getY(25), false, 110);

        uiTemplate("Spell Dam %", 180, getY(12), false, 105);
        uiTemplate("Melee Dam %", 180, getY(13), false, 105);
        uiTemplate("Earth Dam %", 20, getY(21), false, 95);
        uiTemplate("Thunder Dam %", 20, getY(22), false, 95);
        uiTemplate("Water Dam %", 20, getY(23), false, 95);
        uiTemplate("Fire Dam %", 20, getY(24), false, 95);
        uiTemplate("Air Dam %", 20, getY(25), false, 95);
        uiTemplate("Elem. Dam %", 20, getY(26), false, 95);

        uiTemplate("Neutral Spell %", 510, getY(8), false, 115);
        uiTemplate("Earth Spell %", 510, getY(9), false, 115);
        uiTemplate("Thunder Spell %", 510, getY(10), false, 115);
        uiTemplate("Water Spell %", 510, getY(11), false, 115);
        uiTemplate("Fire Spell %", 510, getY(12), false, 115);
        uiTemplate("Air Spell %", 510, getY(13), false, 115);
        uiTemplate("Elem. Spell %", 510, getY(14), false, 115);

        uiTemplate("Neutral Melee %", 345, getY(21), false, 105);
        uiTemplate("Earth Melee %", 345, getY(22), false, 105);
        uiTemplate("Thunder Melee %", 345, getY(23), false, 105);
        uiTemplate("Water Melee %", 345, getY(24), false, 105);
        uiTemplate("Fire Melee %", 345, getY(25), false, 105);
        uiTemplate("Air Melee %", 345, getY(26), false, 105);
        uiTemplate("Elem. Melee %", 345, getY(27), false, 105);

        uiTemplate("Raw Spell Dam", 180, getY(10), false, 105);
        uiTemplate("Raw Melee Dam", 180, getY(11), false, 105);

        uiTemplate("Raw Neutral Dam", 180, getY(19), false, 105);
        uiTemplate("Raw Earth Dam", 180, getY(20), false, 105);
        uiTemplate("Raw Thunder Dam", 180, getY(21), false, 105);
        uiTemplate("Raw Water Dam", 180, getY(22), false, 105);
        uiTemplate("Raw Fire Dam", 180, getY(23), false, 105);
        uiTemplate("Raw Air Dam", 180, getY(24), false, 105);
        uiTemplate("Raw Elem. Dam", 180, getY(25), false, 105);

        uiTemplate("Raw Neutral Spell", 510, getY(16), false, 115);
        uiTemplate("Raw Earth Spell", 510, getY(17), false, 115);
        uiTemplate("Raw Thunder Spell", 510, getY(18), false, 115);
        uiTemplate("Raw Water Spell", 510, getY(19), false, 115);
        uiTemplate("Raw Fire Spell", 510, getY(20), false, 115);
        uiTemplate("Raw Air Spell", 510, getY(21), false, 115);
        uiTemplate("Raw Elem. Spell", 510, getY(22), false, 115);

        uiTemplate("Raw Neutral Melee", 510, getY(0), false, 115);
        uiTemplate("Raw Earth Melee", 510, getY(1), false, 115);
        uiTemplate("Raw Thunder Melee", 510, getY(2), false, 115);
        uiTemplate("Raw Water Melee", 510, getY(3), false, 115);
        uiTemplate("Raw Fire Melee", 510, getY(4), false, 115);
        uiTemplate("Raw Air Melee", 510, getY(5), false, 115);
        uiTemplate("Raw Elem. Melee", 510, getY(6), false, 115);

        uiTemplate("Poison", 180, getY(14), false, 105);
        uiTemplate("Atk Spd Bonus", 180, getY(15), false, 105);

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

        uiTemplate("Knockback", 685, getY(21), false, 110);
        uiTemplate("Healing Efficiency", 685, getY(22), false, 110);
        uiTemplate("Weaken Enemy", 685, getY(23), false, 110);
        uiTemplate("Slow Enemy", 685, getY(24), false, 110);

        uiTemplate("1st Spell Cost %", 345, getY(11), false, 105);
        uiTemplate("2nd Spell Cost %", 345, getY(12), false, 105);
        uiTemplate("3rd Spell Cost %", 345, getY(13), false, 105);
        uiTemplate("4th Spell Cost %", 345, getY(14), false, 105);

        uiTemplate("Raw 1st Cost", 345, getY(7), false, 105);
        uiTemplate("Raw 2nd Cost", 345, getY(8), false, 105);
        uiTemplate("Raw 3rd Cost", 345, getY(9), false, 105);
        uiTemplate("Raw 4th Cost", 345, getY(10), false, 105);

        majorIDJson = new GetAPI().getMajorIDJson();

        setMajorIDBox(685, getY(20));

        variable.setBounds(185, 685, 90, 20);

        create.setBounds(685, 20, 80, 40);
        create.addActionListener(this);

        load.setBounds(685, 70, 80, 40);
        load.addActionListener(this);

        textScroll.setBounds(20, 715, 285, 40);
        textScroll.setLayout(new BoxLayout(textScroll, BoxLayout.Y_AXIS));
        JScrollBar textScrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
        textScrollBar.setUnitIncrement(20);
        textScrollBar.setModel(itemText.getHorizontalVisibility());
        textScroll.add(itemText);
        textScroll.add(textScrollBar);

        display.setBorder(new LineBorder(Color.BLACK));
        display.setPreferredSize(new Dimension(270, 497));
        display.setLayout(null);

        scroll = new JScrollPane(display);
        scroll.setBounds(790, 10, 288, 500);
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

        rarity.setBounds(410, 5, 100, 20);
        rarity.addItem("normal");
        rarity.addItem("unique");
        rarity.addItem("rare");
        rarity.addItem("legendary");
        rarity.addItem("fabled");
        rarity.addItem("mythic");
        rarity.addItem("set");
        rarity.addItem("crafted");

        pane.add(create);
        pane.add(load);
        pane.add(variable);
        pane.add(textScroll);
        pane.add(scroll);
        pane.add(itemType);
        pane.add(rarity);
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
        rarity.setVisible(visible);
        majorIDBox.setVisible(visible);
        majorIDText.setVisible(visible);

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

    public void setMajorIDBox(int posX, int posY) {
        majorIDBox.setBounds(posX + 60, posY, 200, 20);
        majorIDBox.addItem("None");
        if (majorIDJson.get("major_ids") != null) {
            for (Map.Entry<String, JsonElement> entry : majorIDJson.get("major_ids").getAsJsonObject().entrySet()) {
                majorIDBox.addItem(entry.getKey());
            }
        }

        majorIDText.setBounds(posX, posY, 50, 20);

        pane.add(majorIDBox);
        pane.add(majorIDText);
    }

    public void createCustomItem() {
        display.removeAll();

        JsonObject j = JsonParser.parseString("{\"rarity\":\"" + rarity.getItemAt(rarity.getSelectedIndex()) + "\"}").getAsJsonObject();
        if (notVariable.get(0).getText().isEmpty()) {
            j.addProperty("name", "Custom Item");
        } else {
            j.addProperty("name", notVariable.get(0).getText());
        }

        //Powder Slots
        if (!notVariable.get(6).getText().isEmpty() && notVariable.get(6).getText().matches("[+-]?\\d*(\\.\\d+)?")) j.addProperty(Identifications.POWDER_SLOTS.getItemName(), Integer.parseInt(notVariable.get(6).getText()));

        //IDs
        for (int i = 1; notVariable.size() > i; ++i) {
            if (i >= 6 && i <= 12) continue;
            if (!notVariable.get(i).getText().isEmpty()) {
                Identifications id;
                if (i <= 5) { //SP Requests
                    id = ID_FROM_NOT_VARIABLE_INT.get(i);
                } else if (i - ID_FROM_NOT_VARIABLE_INT.size() > ItemUITemplate.ITEM_IDS.size()) { //Reversed IDs
                    id = ItemUITemplate.REVERSED_ITEM_IDS.get(i - ID_FROM_NOT_VARIABLE_INT.size() - ItemUITemplate.ITEM_IDS.size() - 1);
                } else { //Normal IDs
                    id = ItemUITemplate.ITEM_IDS.get(i - ID_FROM_NOT_VARIABLE_INT.size() - 1);
                }
                if (notVariable.get(i).getText().matches("[+-]?\\d*(\\.\\d+)?") && !notVariable.get(i).getText().equals("0")) {
                    if (j.get(id.getItemFieldPos()) == null) j.add(id.getItemFieldPos(), JsonParser.parseString("{}"));
                    if (!variable.isSelected() || !id.isItemVariable()) { //Id Not Variable
                        j.get(id.getItemFieldPos()).getAsJsonObject().addProperty(id.getItemName(), Integer.parseInt(notVariable.get(i).getText()));
                    } else { //ID Variable
                        int min = Integer.parseInt(notVariable.get(i).getText());
                        int max = Integer.parseInt(notVariable.get(i).getText());
                        if (SearchUI.isReversedID(id)) {
                            if (min > 0) {
                                min = ItemUITemplate.getReversedMinInt(min);
                                max = ItemUITemplate.getReversedMaxInt(max);
                            } else {
                                min = ItemUITemplate.getReversedMaxInt(min);
                                max = ItemUITemplate.getReversedMinInt(max);
                            }
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
                        j.get(id.getItemFieldPos()).getAsJsonObject().add(id.getItemName(), JsonParser.parseString("{\"min\":" + minNum + ",\"max\":" + maxNum + "}"));
                    }
                }
            }

            //Attack Speed
            j.addProperty(Identifications.ATTACK_SPEED.getItemName(), atkSpdBox.getItemAt(atkSpdBox.getSelectedIndex()));
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

        if (majorIDBox.getSelectedIndex() != 0) {
            String majorIDName = majorIDBox.getItemAt(majorIDBox.getSelectedIndex());
            j.add("majorIds", JsonParser.parseString("{\"name\":\"" + majorIDName + "\",\"description\":\"" + majorIDJson.get("major_ids").getAsJsonObject().get(majorIDName).getAsString() + "\"}"));
        }

        j.addProperty("type", selectedType);
        j.addProperty("identified", !variable.isSelected());

        ItemUITemplate itemUI = new ItemUITemplate(j, ItemType.ITEM, null, null, 270, 0, true);
        display.add(itemUI);
        if (itemUI.getBounds().y + itemUI.getBounds().height > 497) {
            display.setPreferredSize(new Dimension(270, itemUI.getBounds().y + itemUI.getBounds().height));
        } else {
            display.setPreferredSize(new Dimension(270, 497));
        }
        itemText.setText("CI-" + j);
        SwingUtilities.updateComponentTreeUI(display);
    }

    public void loadCustomItem() {
        display.removeAll();
        if (itemText.getText().contains("CI-")) {
            JsonObject j = JsonParser.parseString(itemText.getText().replace("CI-", "")).getAsJsonObject();
            ItemUITemplate itemUI = new ItemUITemplate(j, ItemType.ITEM, null, null, 270, 0, true);
            display.add(itemUI);
            if (itemUI.getBounds().y + itemUI.getBounds().height > 497) {
                display.setPreferredSize(new Dimension(270, itemUI.getBounds().y + itemUI.getBounds().height));
            } else {
                display.setPreferredSize(new Dimension(270, 497));
            }

            if (j.get("type") != null) itemType.setSelectedItem(j.get("type").getAsString());
            if (j.get("name") != null) notVariable.get(0).setText(j.get("name").getAsString());
            if (j.get(Identifications.POWDER_SLOTS.getItemName()) != null) notVariable.get(6).setText(String.valueOf(j.get(Identifications.POWDER_SLOTS.getItemName()).getAsInt()));
            for (int i = 1; notVariable.size() > i; ++i) {
                if (i == 6) continue;
                Identifications id;
                if (i <= 12) {
                    id = ID_FROM_NOT_VARIABLE_INT.get(i);
                } else if (i - ID_FROM_NOT_VARIABLE_INT.size() > ItemUITemplate.ITEM_IDS.size()) { //Reversed IDs
                    id = ItemUITemplate.REVERSED_ITEM_IDS.get(i - ID_FROM_NOT_VARIABLE_INT.size() - ItemUITemplate.ITEM_IDS.size() - 1);
                } else { //Normal IDs
                    id = ItemUITemplate.ITEM_IDS.get(i - ID_FROM_NOT_VARIABLE_INT.size() - 1);
                }
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
            if (j.get(Identifications.MAJOR_IDS.getItemName()) != null) {
                majorIDBox.setSelectedItem(j.get(Identifications.MAJOR_IDS.getItemName()).getAsJsonObject().get("name").getAsString());
            } else {
                majorIDBox.setSelectedIndex(0);
            }
            if (j.get(Identifications.ATTACK_SPEED.getItemName()) != null) atkSpdBox.setSelectedItem(j.get(Identifications.ATTACK_SPEED.getItemName()).getAsString());
            if (j.get("identified") != null) variable.setSelected(!j.get("identified").getAsBoolean());
            if (j.get("tier") != null) rarity.setSelectedItem(j.get("tier").getAsString());
        }
        SwingUtilities.updateComponentTreeUI(display);
    }
}
