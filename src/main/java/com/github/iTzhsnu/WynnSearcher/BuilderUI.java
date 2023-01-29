package com.github.iTzhsnu.WynnSearcher;

import com.github.iTzhsnu.WynnSearcher.builder.*;
import com.github.iTzhsnu.WynnSearcher.builder.skilltree.*;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private final List<JsonObject> armourTomeJson = new ArrayList<>();
    private final List<JsonObject> weaponTomeJson = new ArrayList<>();
    private final List<JsonObject> guildTomeJson = new ArrayList<>();

    //UIs
    private final List<JLabel> texts = new ArrayList<>();
    private final List<JComboBox<String>> itemBox = new ArrayList<>();
    private final List<JTextField> powderField = new ArrayList<>();
    private final JButton create = new JButton("Create");
    private final JButton update = new JButton("Update");
    private final JButton save = new JButton("Save");
    private final JButton load = new JButton("Load");
    private final JTextField output = new JTextField();

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
        getTomes(armourTomeJson, weaponTomeJson, guildTomeJson);

        pane.setPreferredSize(new Dimension(1064, 1795));
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

        save.setBounds(860, 280, 80, 40);
        save.addActionListener(this);

        load.setBounds(950, 280, 80, 40);
        load.addActionListener(this);

        output.setBounds(800, 360, 250, 20);

        skillPoint = new SkillPoint(pane);
        damage_ids = new Damage_IDs(pane);
        id_display = new ID_Display(pane);
        damage_display = new Damage_Display(pane);
        item_display = new Item_Display(pane);

        classes.setBounds(10, 185, 80, 20);
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
        pane.add(save);
        pane.add(load);
        pane.add(output);
        pane.add(classes);
        for (JLabel l : texts) {
            pane.add(l);
        }
        p.add(scrollPane);
        p.add(itemConnect);
        p.add(ingConnect);
        p.add(recipeConnect);
    }

    private void getTomes(List<JsonObject> armourTomes, List<JsonObject> weaponTomes, List<JsonObject> guildTomes) {
        try {
            JsonObject armourJ = JsonParser.parseReader(new FileReader(new File(Objects.requireNonNull(getClass().getResource("/other/armour_tomes.json")).toURI()))).getAsJsonObject();
            JsonObject weaponJ = JsonParser.parseReader(new FileReader(new File(Objects.requireNonNull(getClass().getResource("/other/weapon_tomes.json")).toURI()))).getAsJsonObject();
            JsonObject guildJ = JsonParser.parseReader(new FileReader(new File(Objects.requireNonNull(getClass().getResource("/other/guild_tomes.json")).toURI()))).getAsJsonObject();

            for (JsonElement j : armourJ.get("items").getAsJsonArray()) {
                armourTomes.add(j.getAsJsonObject());
            }
            for (JsonElement j : weaponJ.get("items").getAsJsonArray()) {
                weaponTomes.add(j.getAsJsonObject());
            }
            for (JsonElement j : guildJ.get("items").getAsJsonArray()) {
                guildTomes.add(j.getAsJsonObject());
            }
        } catch (FileNotFoundException | URISyntaxException e) {
            e.printStackTrace();
        }
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
            case 1:
                return assassin;
            case 2:
                return mage;
            case 3:
                return archer;
            case 4:
                return shaman;
            default:
                return warrior;
        }
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

        JComboBox<String> armourTome1Box = new JComboBox<>();
        JComboBox<String> armourTome2Box = new JComboBox<>();
        JComboBox<String> armourTome3Box = new JComboBox<>();
        JComboBox<String> armourTome4Box = new JComboBox<>();
        JComboBox<String> weaponTome1Box = new JComboBox<>();
        JComboBox<String> weaponTome2Box = new JComboBox<>();
        JComboBox<String> guildTomeBox = new JComboBox<>();

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

        for (JsonObject j : armourTomeJson) {
            armourTome1Box.addItem(j.get("name").getAsString());
            armourTome2Box.addItem(j.get("name").getAsString());
            armourTome3Box.addItem(j.get("name").getAsString());
            armourTome4Box.addItem(j.get("name").getAsString());
        }
        for (JsonObject j : weaponTomeJson) {
            weaponTome1Box.addItem(j.get("name").getAsString());
            weaponTome2Box.addItem(j.get("name").getAsString());
        }
        for (JsonObject j : guildTomeJson) {
            guildTomeBox.addItem(j.get("name").getAsString());
        }

        //Helmet
        helmetBox.setEditable(true);
        helmetBox.setSelectedIndex(-1);
        helmetBox.getEditor().getEditorComponent().addKeyListener(new CrafterUI.Adapter(helmetBox, helmetJson));
        helmetBox.setBounds(10, 10, 200, 20);
        itemBox.add(helmetBox);

        JTextField helmetPowder = new JTextField();
        helmetPowder.setBounds(130, 35, 80, 20);
        powderField.add(helmetPowder);

        //Chestplate
        chestplateBox.setEditable(true);
        chestplateBox.setSelectedIndex(-1);
        chestplateBox.getEditor().getEditorComponent().addKeyListener(new CrafterUI.Adapter(chestplateBox, chestplateJson));
        chestplateBox.setBounds(220, 10, 200, 20);
        itemBox.add(chestplateBox);

        JTextField chestplatePowder = new JTextField();
        chestplatePowder.setBounds(340, 35, 80, 20);
        powderField.add(chestplatePowder);

        //Leggings
        leggingsBox.setEditable(true);
        leggingsBox.setSelectedIndex(-1);
        leggingsBox.getEditor().getEditorComponent().addKeyListener(new CrafterUI.Adapter(leggingsBox, leggingsJson));
        leggingsBox.setBounds(430, 10, 200, 20);
        itemBox.add(leggingsBox);

        JTextField leggingsPowder = new JTextField();
        leggingsPowder.setBounds(550, 35, 80, 20);
        powderField.add(leggingsPowder);

        //Boots
        bootsBox.setEditable(true);
        bootsBox.setSelectedIndex(-1);
        bootsBox.getEditor().getEditorComponent().addKeyListener(new CrafterUI.Adapter(bootsBox, bootsJson));
        bootsBox.setBounds(640, 10, 200, 20);
        itemBox.add(bootsBox);

        JTextField bootsPowder = new JTextField();
        bootsPowder.setBounds(760, 35, 80, 20);
        powderField.add(bootsPowder);

        //Rings
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

        //Bracelet
        braceletBox.setEditable(true);
        braceletBox.setSelectedIndex(-1);
        braceletBox.getEditor().getEditorComponent().addKeyListener(new CrafterUI.Adapter(braceletBox, braceletJson));
        braceletBox.setBounds(430, 65, 200, 20);
        itemBox.add(braceletBox);

        //Necklace
        necklaceBox.setEditable(true);
        necklaceBox.setSelectedIndex(-1);
        necklaceBox.getEditor().getEditorComponent().addKeyListener(new CrafterUI.Adapter(necklaceBox, necklaceJson));
        necklaceBox.setBounds(640, 65, 200, 20);
        itemBox.add(necklaceBox);

        //Weapon
        weaponBox.setEditable(true);
        weaponBox.setSelectedIndex(-1);
        weaponBox.getEditor().getEditorComponent().addKeyListener(new CrafterUI.Adapter(weaponBox, weaponJson));
        weaponBox.setBounds(850, 65, 200, 20);
        itemBox.add(weaponBox);

        JTextField weaponPowder = new JTextField();
        weaponPowder.setBounds(970, 90, 80, 20);
        powderField.add(weaponPowder);

        //Armour Tomes
        armourTome1Box.setEditable(true);
        armourTome1Box.setSelectedIndex(-1);
        armourTome1Box.getEditor().getEditorComponent().addKeyListener(new CrafterUI.Adapter(armourTome1Box, armourTomeJson));
        armourTome1Box.setBounds(10, 120, 200, 20);
        itemBox.add(armourTome1Box);

        armourTome2Box.setEditable(true);
        armourTome2Box.setSelectedIndex(-1);
        armourTome2Box.getEditor().getEditorComponent().addKeyListener(new CrafterUI.Adapter(armourTome2Box, armourTomeJson));
        armourTome2Box.setBounds(220, 120, 200, 20);
        itemBox.add(armourTome2Box);

        armourTome3Box.setEditable(true);
        armourTome3Box.setSelectedIndex(-1);
        armourTome3Box.getEditor().getEditorComponent().addKeyListener(new CrafterUI.Adapter(armourTome3Box, armourTomeJson));
        armourTome3Box.setBounds(430, 120, 200, 20);
        itemBox.add(armourTome3Box);

        armourTome4Box.setEditable(true);
        armourTome4Box.setSelectedIndex(-1);
        armourTome4Box.getEditor().getEditorComponent().addKeyListener(new CrafterUI.Adapter(armourTome4Box, armourTomeJson));
        armourTome4Box.setBounds(640, 120, 200, 20);
        itemBox.add(armourTome4Box);

        //Guild Tome
        guildTomeBox.setEditable(true);
        guildTomeBox.setSelectedIndex(-1);
        guildTomeBox.getEditor().getEditorComponent().addKeyListener(new CrafterUI.Adapter(guildTomeBox, guildTomeJson));
        guildTomeBox.setBounds(850, 120, 200, 20);
        itemBox.add(guildTomeBox);

        //Weapon Tomes
        weaponTome1Box.setEditable(true);
        weaponTome1Box.setSelectedIndex(-1);
        weaponTome1Box.getEditor().getEditorComponent().addKeyListener(new CrafterUI.Adapter(weaponTome1Box, weaponTomeJson));
        weaponTome1Box.setBounds(850, 175, 200, 20);
        itemBox.add(weaponTome1Box);

        weaponTome2Box.setEditable(true);
        weaponTome2Box.setSelectedIndex(-1);
        weaponTome2Box.getEditor().getEditorComponent().addKeyListener(new CrafterUI.Adapter(weaponTome2Box, weaponTomeJson));
        weaponTome2Box.setBounds(850, 230, 200, 20);
        itemBox.add(weaponTome2Box);

        JLabel helmetText = new JLabel("Helmet");
        JLabel chestplateText = new JLabel("Chestplate");
        JLabel leggingsText = new JLabel("Leggings");
        JLabel bootsText = new JLabel("Boots");
        JLabel ring1Text = new JLabel("Ring");
        JLabel ring2Text = new JLabel("Ring");
        JLabel braceletText = new JLabel("Bracelet");
        JLabel necklaceText = new JLabel("Necklace");
        JLabel weaponText = new JLabel("Weapon");

        JLabel armourTome1Text = new JLabel("Armour Tome");
        JLabel armourTome2Text = new JLabel("Armour Tome");
        JLabel armourTome3Text = new JLabel("Armour Tome");
        JLabel armourTome4Text = new JLabel("Armour Tome");
        JLabel guildTomeText = new JLabel("Guild Tome");
        JLabel weaponTome1Text = new JLabel("Weapon Tome");
        JLabel weaponTome2Text = new JLabel("Weapon Tome");

        helmetText.setBounds(20, 35, 100, 20);
        chestplateText.setBounds(230, 35, 100, 20);
        leggingsText.setBounds(440, 35, 100, 20);
        bootsText.setBounds(650, 35, 100, 20);
        ring1Text.setBounds(20, 90, 100, 20);
        ring2Text.setBounds(230, 90, 100, 20);
        braceletText.setBounds(440, 90, 100, 20);
        necklaceText.setBounds(650, 90, 100, 20);
        weaponText.setBounds(860, 90, 100, 20);

        armourTome1Text.setBounds(20, 145, 100, 20);
        armourTome2Text.setBounds(230, 145, 100, 20);
        armourTome3Text.setBounds(440, 145, 100, 20);
        armourTome4Text.setBounds(650, 145, 100, 20);
        guildTomeText.setBounds(860, 145, 100, 20);
        weaponTome1Text.setBounds(860, 200, 100, 20);
        weaponTome2Text.setBounds(860, 255, 100, 20);

        texts.add(helmetText);
        texts.add(chestplateText);
        texts.add(leggingsText);
        texts.add(bootsText);
        texts.add(ring1Text);
        texts.add(ring2Text);
        texts.add(braceletText);
        texts.add(necklaceText);
        texts.add(weaponText);

        texts.add(armourTome1Text);
        texts.add(armourTome2Text);
        texts.add(armourTome3Text);
        texts.add(armourTome4Text);
        texts.add(guildTomeText);
        texts.add(weaponTome1Text);
        texts.add(weaponTome2Text);

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
        } else if (e.getSource() == save) {
            saveBuild();
        } else if (e.getSource() == load) {
            loadBuild();
        }
    }

    public void getItemID_And_Display() {
        item_display.setItem_Display(itemBox, ingAPI, recipeAPI, helmetJson, chestplateJson, leggingsJson, bootsJson, ringJson, braceletJson, necklaceJson, weaponJson, armourTomeJson, weaponTomeJson, guildTomeJson);
        skillPoint.setSkillPoint(item_display.getItemJsons());
        id_display.setIDs(item_display.getItemJsons(), damage_ids, skillPoint, getTree(), damage_boost, powderField, classes.getSelectedIndex(), false);
        damage_display.setDamage_Display(item_display.getItemJsons().getWeapon(), skillPoint, damage_boost, getTree(), id_display.getId_Numbers(), powderField);
    }

    public void updateIDs() {
        skillPoint.updateSkillPoint();
        id_display.setIDs(item_display.getItemJsons(), damage_ids, skillPoint, getTree(), damage_boost, powderField, classes.getSelectedIndex(), true);
        damage_display.setDamage_Display(item_display.getItemJsons().getWeapon(), skillPoint, damage_boost, getTree(), id_display.getId_Numbers(), powderField);
    }

    public void saveBuild() {
        StringBuilder sb = new StringBuilder();

        //Class
        String base = "{\"class\":" + classes.getSelectedIndex() + ",\"items\":[";
        sb.append(base);

        //Armors, Accessories, Weapon and Tomes
        for (int i = 0; 15 >= i; ++i) {
            String s = "\"" + getItemName(i) + "\",";
            sb.append(s);
        }
        sb.deleteCharAt(sb.length() - 1);

        //Powders
        sb.append("],\"powders\":[");
        for (int i = 0; 4 >= i; ++i) {
            String s = "\"" + powderField.get(i).getText() + "\",";
            sb.append(s);
        }
        sb.deleteCharAt(sb.length() - 1);

        //Skill Points
        sb.append("],\"sps\":[");
        for (int i = 0; 4 >= i; ++i) {
            String s = skillPoint.getSkillPointInt(i) + ",";
            sb.append(s);
        }
        sb.deleteCharAt(sb.length() - 1);

        //Ability Tree
        boolean haveTree = false;
        sb.append("],\"tree\":[");
        for (int i = 0; getTree().getTcb().size() > i; ++i) {
            if (getTree().getTcb().get(i).isSelected()) {
                haveTree = true;
                String s = i + ",";
                sb.append(s);
            }
        }
        if (haveTree) sb.deleteCharAt(sb.length() - 1);

        //IDs
        sb.append("],\"ids\":[");
        for (int i = 0; 48 >= i; ++i) {
            String s = damage_ids.getID(i).getValue() + ",";
            sb.append(s);
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]}");

        output.setText(sb.toString());
    }

    public void loadBuild() {
        JsonObject j = JsonParser.parseString(output.getText()).getAsJsonObject();

        //Set Class
        classes.setSelectedIndex(j.get("class").getAsInt());

        //Set Armor, Accessory, Weapon and Tome
        for (int i = 0; 15 >= i; ++i) {
            setItemName(i, j.get("items").getAsJsonArray().get(i).getAsString());
        }

        //Set Powders
        for (int i = 0; 4 >= i; ++i) {
            powderField.get(i).setText(j.get("powders").getAsJsonArray().get(i).getAsString());
        }

        //Set Tree
        for (JsonElement je : j.get("tree").getAsJsonArray()) {
            getTree().getTcb().get(je.getAsInt()).setSelected(true);
        }

        //Create Build
        getItemID_And_Display();

        //Set Skill Point
        for (int i = 0; 4 >= i; ++i) {
            skillPoint.getSkillPoint(i).setTextValue(j.get("sps").getAsJsonArray().get(i).getAsInt());
        }

        //Set IDs
        for (int i = 0; 48 >= i; ++i) {
            damage_ids.getID(i).setTextValue(j.get("ids").getAsJsonArray().get(i).getAsInt());
        }

        //Update Build
        updateIDs();
    }

    private String getItemName(int i) {
        return ((JTextField) itemBox.get(i).getEditor().getEditorComponent()).getText();
    }

    private void setItemName(int i, String text) {
        ((JTextField) itemBox.get(i).getEditor().getEditorComponent()).setText(text);
    }
}
