package com.github.iTzhsnu.WynnSearcher;

import com.github.iTzhsnu.WynnSearcher.builder.*;
import com.github.iTzhsnu.WynnSearcher.builder.skilltree.*;
import com.google.gson.JsonObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BuilderUI implements ActionListener {
    //Main Panel
    private final JPanel pane = new JPanel();
    private final JScrollPane scrollPane;

    //Json
    private final List<JsonObject> weaponJson = new ArrayList<>();
    private final List<JsonObject> helmetJson = new ArrayList<>();
    private final List<JsonObject> chestplateJson = new ArrayList<>();
    private final List<JsonObject> leggingsJson = new ArrayList<>();
    private final List<JsonObject> bootsJson = new ArrayList<>();
    private final List<JsonObject> ringJson = new ArrayList<>();
    private final List<JsonObject> braceletJson = new ArrayList<>();
    private final List<JsonObject> necklaceJson = new ArrayList<>();
    private final List<JsonObject> ingAPI;
    private final List<JsonObject> recipeAPI;

    //UIs
    private final List<JLabel> texts = new ArrayList<>();
    private final List<JComboBox<String>> itemBox = new ArrayList<>();
    private final List<JTextField> powderField = new ArrayList<>();
    private final JButton create = new JButton("Create");
    private final JButton update = new JButton("Update");

    //API Connect
    private final JLabel itemConnect;
    private final JLabel ingConnect;
    private final JLabel recipeConnect;

    //Skill Point, Damage IDs and ID Display
    private final SkillPoint skillPoint;
    private final Damage_IDs damage_ids;
    private final ID_Display id_display;
    private final Damage_Display damage_display;
    private final Item_Display item_display;

    //Ability Tree
    private final JComboBox<String> classes = new JComboBox<>();
    private final Damage_Boosts damage_boost;
    private final Warrior warrior;
    private final Assassin assassin;
    private final Archer archer;
    private final Mage mage;
    private final Shaman shaman;

    public BuilderUI(Container p, List<JsonObject> itemAPI, List<JsonObject> ingAPI, List<JsonObject> recipeAPI, JLabel itemAPIConnect, JLabel ingAPIConnect, String recipeAPIConnect) {
        this.ingAPI = ingAPI;
        this.recipeAPI = recipeAPI;
        setJson(itemAPI);

        pane.setPreferredSize(new Dimension(1064, 1710));
        pane.setLayout(null);

        scrollPane = new JScrollPane(pane);
        scrollPane.setBounds(2, 30, 1082, 730);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);

        itemConnect = new JLabel(itemAPIConnect.getText());
        ingConnect = new JLabel(ingAPIConnect.getText());
        recipeConnect = new JLabel(recipeAPIConnect);
        itemConnect.setForeground(itemAPIConnect.getForeground());
        ingConnect.setForeground(ingAPIConnect.getForeground());
        if (recipeAPIConnect.equals("Recipe API Connected")) {
            recipeConnect.setForeground(new Color(0, 169, 104));
        } else {
            recipeConnect.setForeground(new Color(255, 0, 0));
        }
        itemConnect.setBounds(520, 5, 150, 20);
        ingConnect.setBounds(700, 5, 150, 20);
        recipeConnect.setBounds(900, 5, 150, 20);

        setComboBox();

        create.setBounds(860, 10, 80, 40);
        create.addActionListener(this);
        update.setBounds(950, 10, 80, 40);
        update.addActionListener(this);

        skillPoint = new SkillPoint(pane);
        damage_ids = new Damage_IDs(pane);
        id_display = new ID_Display(pane);
        damage_display = new Damage_Display(pane);
        item_display = new Item_Display(pane);

        classes.setBounds(10, 130, 80, 20);
        classes.addItem("Warrior");
        classes.addItem("Assassin");
        classes.addItem("Mage");
        classes.addItem("Archer");
        classes.addItem("Shaman");
        classes.addActionListener(this);

        damage_boost = new Damage_Boosts(pane);
        warrior = new Warrior(pane);
        assassin = new Assassin(pane);
        archer = new Archer(pane);
        mage = new Mage(pane);
        shaman = new Shaman(pane);

        setClassOnlyDisplayVisible();

        pane.add(create);
        pane.add(update);
        pane.add(classes);
        for (JLabel l : texts) {
            pane.add(l);
        }
        p.add(scrollPane);
        p.add(itemConnect);
        p.add(ingConnect);
        p.add(recipeConnect);
    }

    public void setJson(List<JsonObject> jsonList) {
        for (JsonObject j : jsonList) {
            if (j.get("type") != null) {
                switch (j.get("type").getAsString()) {
                    case "Bow":
                    case "Spear":
                    case "Wand":
                    case "Dagger":
                    case "Relik":
                        weaponJson.add(j);
                        break;
                    case "Helmet":
                        helmetJson.add(j);
                        break;
                    case "Chestplate":
                        chestplateJson.add(j);
                        break;
                    case "Leggings":
                        leggingsJson.add(j);
                        break;
                    case "Boots":
                        bootsJson.add(j);
                        break;
                }
            } else if (j.get("accessoryType") != null) {
                switch (j.get("accessoryType").getAsString()) {
                    case "Ring":
                        ringJson.add(j);
                        break;
                    case "Bracelet":
                        braceletJson.add(j);
                        break;
                    case "Necklace":
                        necklaceJson.add(j);
                        break;
                }
            }
        }
    }

    public void setBuilderVisible(boolean visible) {
        scrollPane.setVisible(visible);
        itemConnect.setVisible(visible);
        ingConnect.setVisible(visible);
        recipeConnect.setVisible(visible);
    }

    public void setClassOnlyDisplayVisible() {
        boolean archerB = false;
        boolean mageB = false;
        boolean shamanB = false;
        boolean warriorB = false;
        boolean assassinB = false;
        switch (classes.getItemAt(classes.getSelectedIndex())) {
            case "Archer":
                archerB = true;
                break;
            case "Mage":
                mageB = true;
                break;
            case "Shaman":
                shamanB = true;
                break;
            case "Warrior":
                warriorB = true;
                break;
            case "Assassin":
                assassinB = true;
                break;
        }
        warrior.setTreeVisible(warriorB);
        damage_boost.getSlider().get(0).setDamage_Boost_Slider_Visible(warriorB);
        damage_boost.getSlider().get(1).setDamage_Boost_Slider_Visible(warriorB);
        damage_boost.getBox().get(5).setVisible(warriorB);
        damage_boost.getBox().get(6).setVisible(warriorB);
        damage_boost.getBox().get(7).setVisible(warriorB);

        assassin.setTreeVisible(assassinB);
        damage_boost.getSlider().get(2).setDamage_Boost_Slider_Visible(assassinB);
        damage_boost.getBox().get(8).setVisible(assassinB);
        damage_boost.getBox().get(9).setVisible(assassinB);
        damage_boost.getBox().get(10).setVisible(assassinB);
        damage_boost.getBox().get(11).setVisible(assassinB);
        damage_boost.getBox().get(12).setVisible(assassinB);

        archer.setTreeVisible(archerB);
        damage_boost.getSlider().get(3).setDamage_Boost_Slider_Visible(archerB);
        damage_boost.getSlider().get(4).setDamage_Boost_Slider_Visible(archerB);
        damage_boost.getSlider().get(5).setDamage_Boost_Slider_Visible(archerB);

        mage.setTreeVisible(mageB);
        damage_boost.getSlider().get(6).setDamage_Boost_Slider_Visible(mageB);

        shaman.setTreeVisible(shamanB);
        damage_boost.getSlider().get(7).setDamage_Boost_Slider_Visible(shamanB);
        damage_boost.getBox().get(13).setVisible(shamanB);
        damage_boost.getBox().get(14).setVisible(shamanB);
        damage_boost.getBox().get(15).setVisible(shamanB);
        damage_boost.getBox().get(16).setVisible(shamanB);
        damage_boost.getBox().get(17).setVisible(shamanB);
        damage_boost.getBox().get(18).setVisible(shamanB);
        damage_boost.getBox().get(19).setVisible(shamanB);
        damage_boost.getBox().get(20).setVisible(shamanB);
    }

    private TreeBase getTree() {
        switch (classes.getSelectedIndex()) {
            case 0:
                return warrior;
            case 1:
                return assassin;
            case 2:
                return mage;
            case 3:
                return archer;
            case 4:
                return shaman;
        }
        return null;
    }

    public void setComboBox() {
        JComboBox<String> helmetBox = new JComboBox<>();
        JComboBox<String> chestplateBox = new JComboBox<>();
        JComboBox<String> leggingsBox = new JComboBox<>();
        JComboBox<String> bootsBox = new JComboBox<>();
        JComboBox<String> ring1Box = new JComboBox<>();
        JComboBox<String> ring2Box = new JComboBox<>();
        JComboBox<String> braceletBox = new JComboBox<>();
        JComboBox<String> necklaceBox = new JComboBox<>();
        JComboBox<String> weaponBox = new JComboBox<>();

        for (JsonObject j : helmetJson) {
            if (j.get("displayName") != null) {
                helmetBox.addItem(j.get("displayName").getAsString());
            } else if (j.get("name") != null) {
                helmetBox.addItem(j.get("name").getAsString());
            }
        }
        for (JsonObject j : chestplateJson) {
            if (j.get("displayName") != null) {
                chestplateBox.addItem(j.get("displayName").getAsString());
            } else if (j.get("name") != null) {
                chestplateBox.addItem(j.get("name").getAsString());
            }
        }
        for (JsonObject j : leggingsJson) {
            if (j.get("displayName") != null) {
                leggingsBox.addItem(j.get("displayName").getAsString());
            } else if (j.get("name") != null) {
                leggingsBox.addItem(j.get("name").getAsString());
            }
        }
        for (JsonObject j : bootsJson) {
            if (j.get("displayName") != null) {
                bootsBox.addItem(j.get("displayName").getAsString());
            } else if (j.get("name") != null) {
                bootsBox.addItem(j.get("name").getAsString());
            }
        }
        for (JsonObject j : ringJson) {
            if (j.get("displayName") != null) {
                ring1Box.addItem(j.get("displayName").getAsString());
                ring2Box.addItem(j.get("displayName").getAsString());
            } else if (j.get("name") != null) {
                ring1Box.addItem(j.get("name").getAsString());
                ring2Box.addItem(j.get("name").getAsString());
            }
        }
        for (JsonObject j : braceletJson) {
            if (j.get("displayName") != null) {
                braceletBox.addItem(j.get("displayName").getAsString());
            } else if (j.get("name") != null) {
                braceletBox.addItem(j.get("name").getAsString());
            }
        }
        for (JsonObject j : necklaceJson) {
            if (j.get("displayName") != null) {
                necklaceBox.addItem(j.get("displayName").getAsString());
            } else if (j.get("name") != null) {
                necklaceBox.addItem(j.get("name").getAsString());
            }
        }
        for (JsonObject j : weaponJson) {
            if (j.get("displayName") != null) {
                weaponBox.addItem(j.get("displayName").getAsString());
            } else if (j.get("name") != null) {
                weaponBox.addItem(j.get("name").getAsString());
            }
        }

        helmetBox.setEditable(true);
        helmetBox.setSelectedIndex(-1);
        helmetBox.getEditor().getEditorComponent().addKeyListener(new CrafterUI.Adapter(helmetBox, helmetJson));
        helmetBox.setBounds(10, 10, 200, 20);
        itemBox.add(helmetBox);

        JTextField helmetPowder = new JTextField();
        helmetPowder.setBounds(130, 35, 80, 20);
        powderField.add(helmetPowder);

        chestplateBox.setEditable(true);
        chestplateBox.setSelectedIndex(-1);
        chestplateBox.getEditor().getEditorComponent().addKeyListener(new CrafterUI.Adapter(chestplateBox, chestplateJson));
        chestplateBox.setBounds(220, 10, 200, 20);
        itemBox.add(chestplateBox);

        JTextField chestplatePowder = new JTextField();
        chestplatePowder.setBounds(340, 35, 80, 20);
        powderField.add(chestplatePowder);

        leggingsBox.setEditable(true);
        leggingsBox.setSelectedIndex(-1);
        leggingsBox.getEditor().getEditorComponent().addKeyListener(new CrafterUI.Adapter(leggingsBox, leggingsJson));
        leggingsBox.setBounds(430, 10, 200, 20);
        itemBox.add(leggingsBox);

        JTextField leggingsPowder = new JTextField();
        leggingsPowder.setBounds(550, 35, 80, 20);
        powderField.add(leggingsPowder);

        bootsBox.setEditable(true);
        bootsBox.setSelectedIndex(-1);
        bootsBox.getEditor().getEditorComponent().addKeyListener(new CrafterUI.Adapter(bootsBox, bootsJson));
        bootsBox.setBounds(640, 10, 200, 20);
        itemBox.add(bootsBox);

        JTextField bootsPowder = new JTextField();
        bootsPowder.setBounds(760, 35, 80, 20);
        powderField.add(bootsPowder);

        ring1Box.setEditable(true);
        ring1Box.setSelectedIndex(-1);
        ring1Box.getEditor().getEditorComponent().addKeyListener(new CrafterUI.Adapter(ring1Box, ringJson));
        ring1Box.setBounds(10, 65, 200, 20);
        itemBox.add(ring1Box);

        ring2Box.setEditable(true);
        ring2Box.setSelectedIndex(-1);
        ring2Box.getEditor().getEditorComponent().addKeyListener(new CrafterUI.Adapter(ring2Box, ringJson));
        ring2Box.setBounds(220, 65, 200, 20);
        itemBox.add(ring2Box);

        braceletBox.setEditable(true);
        braceletBox.setSelectedIndex(-1);
        braceletBox.getEditor().getEditorComponent().addKeyListener(new CrafterUI.Adapter(braceletBox, braceletJson));
        braceletBox.setBounds(430, 65, 200, 20);
        itemBox.add(braceletBox);

        necklaceBox.setEditable(true);
        necklaceBox.setSelectedIndex(-1);
        necklaceBox.getEditor().getEditorComponent().addKeyListener(new CrafterUI.Adapter(necklaceBox, necklaceJson));
        necklaceBox.setBounds(640, 65, 200, 20);
        itemBox.add(necklaceBox);

        weaponBox.setEditable(true);
        weaponBox.setSelectedIndex(-1);
        weaponBox.getEditor().getEditorComponent().addKeyListener(new CrafterUI.Adapter(weaponBox, weaponJson));
        weaponBox.setBounds(850, 65, 200, 20);
        itemBox.add(weaponBox);

        JTextField weaponPowder = new JTextField();
        weaponPowder.setBounds(970, 90, 80, 20);
        powderField.add(weaponPowder);

        JLabel helmetText = new JLabel("Helmet");
        JLabel chestplateText = new JLabel("Chestplate");
        JLabel leggingsText = new JLabel("Leggings");
        JLabel bootsText = new JLabel("Boots");
        JLabel ring1Text = new JLabel("Ring");
        JLabel ring2Text = new JLabel("Ring");
        JLabel braceletText = new JLabel("Bracelet");
        JLabel necklaceText = new JLabel("Necklace");
        JLabel weaponText = new JLabel("Weapon");

        helmetText.setBounds(85, 35, 100, 20);
        chestplateText.setBounds(275, 35, 100, 20);
        leggingsText.setBounds(490, 35, 100, 20);
        bootsText.setBounds(720, 35, 100, 20);
        ring1Text.setBounds(20, 90, 100, 20);
        ring2Text.setBounds(230, 90, 100, 20);
        braceletText.setBounds(440, 90, 100, 20);
        necklaceText.setBounds(650, 90, 100, 20);
        weaponText.setBounds(915, 90, 100, 20);

        texts.add(helmetText);
        texts.add(chestplateText);
        texts.add(leggingsText);
        texts.add(bootsText);
        texts.add(ring1Text);
        texts.add(ring2Text);
        texts.add(braceletText);
        texts.add(necklaceText);
        texts.add(weaponText);

        for (JComboBox<String> c : itemBox) {
            pane.add(c);
        }
        for (JTextField t : powderField) {
            pane.add(t);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == create) {
            getItemID_And_Display();
        } else if (e.getSource() == update) {
            updateIDs();
        } else if (e.getSource() == classes) {
            setClassOnlyDisplayVisible();
        }
    }

    public void getItemID_And_Display() {
        item_display.setItem_Display(itemBox, ingAPI, recipeAPI, helmetJson, chestplateJson, leggingsJson, bootsJson, ringJson, braceletJson, necklaceJson, weaponJson);
        id_display.setIDs(item_display.getItemJsons(), damage_ids, skillPoint, classes.getSelectedIndex(), getTree(), damage_boost, false);
    }

    public void updateIDs() {
        skillPoint.updateSkillPoint();
        id_display.setIDs(item_display.getItemJsons(), damage_ids, skillPoint, classes.getSelectedIndex() , getTree(), damage_boost, true);
    }
}
