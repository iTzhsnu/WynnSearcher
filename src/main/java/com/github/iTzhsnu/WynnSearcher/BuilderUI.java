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
import java.util.ArrayList;
import java.util.List;

public class BuilderUI implements ActionListener {
    //Main Panel
    private final JPanel pane = new JPanel();
    private final JScrollPane scrollPane;

    //Json
    private final List<JsonObject> itemAPI;
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
    private final List<JsonObject> otherItemAPI;
    private final List<JsonObject> armourTomeJson = new ArrayList<>();
    private final List<JsonObject> weaponTomeJson = new ArrayList<>();
    private final List<JsonObject> guildTomeJson = new ArrayList<>();
    private final List<JsonObject> lootrunTomeJson = new ArrayList<>();
    private final List<JsonObject> marathonTomeJson = new ArrayList<>();
    private final List<JsonObject> expertiseTomeJson = new ArrayList<>();
    private final List<JsonObject> mysticismTomeJson = new ArrayList<>();
    private final List<JsonObject> aspects = new ArrayList<>();

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
    private final JLabel recipeConnect;
    private final JLabel treeConnect = new JLabel("Tree API Connected");

    //Skill Point, Damage IDs and ID Display
    private final SkillPoint skillPoint;
    private final Damage_IDs damage_ids;
    private final ID_Display id_display;
    private final Damage_Display damage_display;
    private final Item_Display item_display;
    private final Powder_Effects powder_effects;
    private final Comprehensive_Display comprehensive_Display;

    //Ability Tree
    private final JComboBox<String> classes = new JComboBox<>();
    private final Ability_Buffs abilityBuffs;
    private final TreeBase warrior;
    private final TreeBase assassin;
    private final TreeBase archer;
    private final TreeBase mage;
    private final TreeBase shaman;

    public BuilderUI(Container p, List<JsonObject> itemAPI, List<JsonObject> ingAPI, List<JsonObject> otherItemAPI, List<JsonObject> recipeAPI, JLabel itemAPIConnect, String recipeAPIConnect) {
        this.itemAPI = itemAPI;
        this.ingAPI = ingAPI;
        this.recipeAPI = recipeAPI;
        this.otherItemAPI = otherItemAPI;
        setJson(itemAPI);
        setTomeJson();

        pane.setPreferredSize(new Dimension(1064, 2185));
        pane.setLayout(null);

        scrollPane = new JScrollPane(pane);
        scrollPane.setBounds(2, 30, 1082, 730);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);

        itemConnect = new JLabel(itemAPIConnect.getText());
        recipeConnect = new JLabel(recipeAPIConnect);
        itemConnect.setForeground(itemAPIConnect.getForeground());
        if (recipeAPIConnect.equals("Recipe API Connected")) {
            recipeConnect.setForeground(new Color(0, 169, 104));
        } else if (recipeAPIConnect.equals("Archive Loaded")) {
            recipeConnect.setForeground(new Color(0, 169, 104));
        } else {
            recipeConnect.setForeground(new Color(255, 0, 0));
        }
        itemConnect.setBounds(400, 5, 150, 20);
        recipeConnect.setBounds(600, 5, 150, 20);

        setComboBox();

        create.setBounds(860, 10, 80, 40);
        create.addActionListener(this);

        update.setBounds(950, 10, 80, 40);
        update.addActionListener(this);

        save.setBounds(860, 555, 80, 40);
        save.addActionListener(this);

        load.setBounds(950, 555, 80, 40);
        load.addActionListener(this);

        JPanel outputP = new JPanel();
        JScrollBar outputB = new JScrollBar(JScrollBar.HORIZONTAL);
        outputP.setBounds(800, 605, 250, 40);
        outputP.setLayout(new BoxLayout(outputP, BoxLayout.Y_AXIS));
        outputB.setUnitIncrement(20);
        outputB.setModel(output.getHorizontalVisibility());
        outputP.add(output);
        outputP.add(outputB);

        skillPoint = new SkillPoint(pane);
        damage_ids = new Damage_IDs(pane);
        id_display = new ID_Display(pane);
        damage_display = new Damage_Display(pane);
        item_display = new Item_Display(pane);
        powder_effects = new Powder_Effects(pane);
        comprehensive_Display = new Comprehensive_Display(pane);

        classes.setBounds(10, 350, 80, 20);
        classes.addItem("Warrior");
        classes.addItem("Assassin");
        classes.addItem("Mage");
        classes.addItem("Archer");
        classes.addItem("Shaman");
        classes.addActionListener(this);

        abilityBuffs = new Ability_Buffs(pane);

        treeConnect.setForeground(new Color(0, 169, 104));
        treeConnect.setBounds(800, 5, 150, 20);
        warrior = new TreeBase(pane, Archetype.FALLEN, Archetype.BATTLE_MONK, Archetype.PALADIN, "warrior", treeConnect);
        assassin = new TreeBase(pane, Archetype.SHADESTEPPER, Archetype.TRICKSTER, Archetype.ACROBAT, "assassin", treeConnect);
        archer = new TreeBase(pane, Archetype.BOLTSLINGER, Archetype.SHARPSHOOTER, Archetype.TRAPPER, "archer", treeConnect);
        mage = new TreeBase(pane, Archetype.RIFTWALKER, Archetype.LIGHT_BENDER, Archetype.ARCANIST, "mage", treeConnect);
        shaman = new TreeBase(pane, Archetype.SUMMONER, Archetype.RITUALIST, Archetype.ACOLYTE, "shaman", treeConnect);

        setClassOnlyDisplayVisible();

        pane.add(create);
        pane.add(update);
        pane.add(save);
        pane.add(load);
        pane.add(outputP);
        pane.add(classes);
        for (JLabel l : texts) {
            pane.add(l);
        }
        p.add(scrollPane);
        p.add(itemConnect);
        p.add(recipeConnect);
        p.add(treeConnect);
    }

    public void setTomeJson() {
        for (JsonObject j : otherItemAPI) {
            if (j.get("tomeType") != null) {
                switch (j.get("tomeType").getAsString()) {
                    case "guild_tome": //x1 Slots
                        guildTomeJson.add(j);
                        break;
                    case "marathon_tome": //x2 Slots
                        marathonTomeJson.add(j);
                        break;
                    case "expertise_tome": //x2 Slots
                        expertiseTomeJson.add(j);
                        break;
                    case "lootrun_tome": //x1 Slots
                        lootrunTomeJson.add(j);
                        break;
                    case "mysticism_tome": //x2 Slots
                        mysticismTomeJson.add(j);
                        break;
                    case "weapon_tome": //x2 Slots
                        weaponTomeJson.add(j);
                        break;
                    case "armour_tome": //x4 Slots
                        armourTomeJson.add(j);
                        break;
                }
            }
        }
    }

    public void setJson(List<JsonObject> jsonList) {
        for (JsonObject item : jsonList) {
            if (item.get("weaponType") != null) {
                weaponJson.add(item);
            } else if (item.get("armourType") != null) {
                switch (item.get("armourType").getAsString()) {
                    case "helmet":
                        helmetJson.add(item);
                        break;
                    case "chestplate":
                        chestplateJson.add(item);
                        break;
                    case "leggings":
                        leggingsJson.add(item);
                        break;
                    case "boots":
                        bootsJson.add(item);
                        break;
                }
            } else if (item.get("accessoryType") != null) {
                switch (item.get("accessoryType").getAsString()) {
                    case "ring":
                        ringJson.add(item);
                        break;
                    case "bracelet":
                        braceletJson.add(item);
                        break;
                    case "necklace":
                        necklaceJson.add(item);
                        break;
                }
            }
        }
    }

    public void setBuilderVisible(boolean visible) {
        scrollPane.setVisible(visible);
        itemConnect.setVisible(visible);
        recipeConnect.setVisible(visible);
        treeConnect.setVisible(visible);
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
        abilityBuffs.getSlider().get(Ability_Buffs_Enum.CORRUPTED.getPos()).setSlider_Visible(warriorB); //Corrupted
        abilityBuffs.getSlider().get(Ability_Buffs_Enum.DISCOMBOBULATE.getPos()).setSlider_Visible(warriorB); //Discombobulate
        abilityBuffs.getBox().get(Ability_Buffs_Enum.MANTLE.getPos()).setVisible(warriorB); //Mantle
        abilityBuffs.getBox().get(Ability_Buffs_Enum.BRINK_OF_MADNESS.getPos()).setVisible(warriorB); //Brink of Madness
        abilityBuffs.getBox().get(Ability_Buffs_Enum.SACRED_SURGE.getPos()).setVisible(warriorB); //Sacred Surge

        assassin.setTreeVisible(assassinB);
        abilityBuffs.getSlider().get(Ability_Buffs_Enum.CLONES.getPos()).setSlider_Visible(assassinB); //Clones
        abilityBuffs.getSlider().get(Ability_Buffs_Enum.NIGHTCLOAK_KNIFE.getPos()).setSlider_Visible(assassinB); //Nightcloak Knife
        abilityBuffs.getBox().get(Ability_Buffs_Enum.BACKSTAB.getPos()).setVisible(assassinB); //Backstab
        abilityBuffs.getBox().get(Ability_Buffs_Enum.SURPRISE_STRIKE.getPos()).setVisible(assassinB); //Surprise Strike
        abilityBuffs.getBox().get(Ability_Buffs_Enum.DELIRIOUS_GAS.getPos()).setVisible(assassinB); //Derilious Gas
        abilityBuffs.getBox().get(Ability_Buffs_Enum.MIRROR_IMAGE.getPos()).setVisible(assassinB); //Mirror Image
        abilityBuffs.getBox().get(Ability_Buffs_Enum.SATSUJIN.getPos()).setVisible(assassinB); //Satsujin
        abilityBuffs.getBox().get(Ability_Buffs_Enum.FLOW_STATE.getPos()).setVisible(assassinB); //Flow State
        abilityBuffs.getBox().get(Ability_Buffs_Enum.PARRY.getPos()).setVisible(assassinB); //Parry
        abilityBuffs.getBox().get(Ability_Buffs_Enum.DISSOLUTION.getPos()).setVisible(assassinB); //Dissolution

        archer.setTreeVisible(archerB);
        abilityBuffs.getSlider().get(Ability_Buffs_Enum.FOCUS.getPos()).setSlider_Visible(archerB); //Focus
        abilityBuffs.getSlider().get(Ability_Buffs_Enum.PATIENT_HUNTER.getPos()).setSlider_Visible(archerB); //Patient Hunter
        abilityBuffs.getSlider().get(Ability_Buffs_Enum.DECIMATOR.getPos()).setSlider_Visible(archerB); //Decimator
        abilityBuffs.getBox().get(Ability_Buffs_Enum.INITIATOR.getPos()).setVisible(archerB); //Initiator

        mage.setTreeVisible(mageB);
        abilityBuffs.getSlider().get(Ability_Buffs_Enum.WINDED.getPos()).setSlider_Visible(mageB); //Winded

        shaman.setTreeVisible(shamanB);
        abilityBuffs.getSlider().get(Ability_Buffs_Enum.SHEPHERD.getPos()).setSlider_Visible(shamanB); //Shepherd
        abilityBuffs.getBox().get(Ability_Buffs_Enum.MASK_OF_THE_LUNATIC.getPos()).setVisible(shamanB); //Mask of the Lunatic
        abilityBuffs.getBox().get(Ability_Buffs_Enum.MASK_OF_THE_FANATIC.getPos()).setVisible(shamanB); //Mask of the Fanatic
        abilityBuffs.getBox().get(Ability_Buffs_Enum.MASK_OF_THE_COWARD.getPos()).setVisible(shamanB); //Mask of the Coward
        abilityBuffs.getBox().get(Ability_Buffs_Enum.CHANT_OF_THE_FANATIC.getPos()).setVisible(shamanB); //Chant of the Fanatic
        abilityBuffs.getBox().get(Ability_Buffs_Enum.BULLWHIP.getPos()).setVisible(shamanB); //Bullwhip
        abilityBuffs.getBox().get(Ability_Buffs_Enum.INVIGORATING_WAVE.getPos()).setVisible(shamanB); //Invigorating Wave
        abilityBuffs.getBox().get(Ability_Buffs_Enum.SACRIFICIAL_SHRINE.getPos()).setVisible(shamanB); //Sacrificial Shrine
        abilityBuffs.getBox().get(Ability_Buffs_Enum.AWAKENED.getPos()).setVisible(shamanB); //Awakened
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
        JComboBox<String> guildTomeBox = new JComboBox<>();

        JComboBox<String> weaponTome1Box = new JComboBox<>();
        JComboBox<String> weaponTome2Box = new JComboBox<>();
        JComboBox<String> marathonTome1Box = new JComboBox<>();
        JComboBox<String> marathonTome2Box = new JComboBox<>();
        JComboBox<String> lootrunTomeBox = new JComboBox<>();

        JComboBox<String> expertiseTome1Box = new JComboBox<>();
        JComboBox<String> expertiseTome2Box = new JComboBox<>();
        JComboBox<String> mysticismTome1Box = new JComboBox<>();
        JComboBox<String> mysticismTome2Box = new JComboBox<>();

        JComboBox<String> aspect1Box = new JComboBox<>();
        JComboBox<String> aspect2Box = new JComboBox<>();
        JComboBox<String> aspect3Box = new JComboBox<>();
        JComboBox<String> aspect4Box = new JComboBox<>();
        JComboBox<String> aspect5Box = new JComboBox<>();

        for (JsonObject j : helmetJson) {
            if (j.get("name") != null) {
                helmetBox.addItem(j.get("name").getAsString());
            }
        }
        for (JsonObject j : chestplateJson) {
            if (j.get("name") != null) {
                chestplateBox.addItem(j.get("name").getAsString());
            }
        }
        for (JsonObject j : leggingsJson) {
            if (j.get("name") != null) {
                leggingsBox.addItem(j.get("name").getAsString());
            }
        }
        for (JsonObject j : bootsJson) {
            if (j.get("name") != null) {
                bootsBox.addItem(j.get("name").getAsString());
            }
        }
        for (JsonObject j : ringJson) {
            if (j.get("name") != null) {
                ring1Box.addItem(j.get("name").getAsString());
                ring2Box.addItem(j.get("name").getAsString());
            }
        }
        for (JsonObject j : braceletJson) {
            if (j.get("name") != null) {
                braceletBox.addItem(j.get("name").getAsString());
            }
        }
        for (JsonObject j : necklaceJson) {
            if (j.get("name") != null) {
                necklaceBox.addItem(j.get("name").getAsString());
            }
        }
        for (JsonObject j : weaponJson) {
            if (j.get("name") != null) {
                weaponBox.addItem(j.get("name").getAsString());
            }
        }

        for (JsonObject j : armourTomeJson) {
            armourTome1Box.addItem(j.get("name").getAsString());
            armourTome2Box.addItem(j.get("name").getAsString());
            armourTome3Box.addItem(j.get("name").getAsString());
            armourTome4Box.addItem(j.get("name").getAsString());
        }
        for (JsonObject j : guildTomeJson) {
            guildTomeBox.addItem(j.get("name").getAsString());
        }
        for (JsonObject j : weaponTomeJson) {
            weaponTome1Box.addItem(j.get("name").getAsString());
            weaponTome2Box.addItem(j.get("name").getAsString());
        }
        for (JsonObject j : marathonTomeJson) {
            marathonTome1Box.addItem(j.get("name").getAsString());
            marathonTome2Box.addItem(j.get("name").getAsString());
        }
        for (JsonObject j : lootrunTomeJson) {
            lootrunTomeBox.addItem(j.get("name").getAsString());
        }
        for (JsonObject j : expertiseTomeJson) {
            expertiseTome1Box.addItem(j.get("name").getAsString());
            expertiseTome2Box.addItem(j.get("name").getAsString());
        }
        for (JsonObject j : mysticismTomeJson) {
            mysticismTome1Box.addItem(j.get("name").getAsString());
            mysticismTome2Box.addItem(j.get("name").getAsString());
        }
        for (JsonObject j : aspects) {
            aspect1Box.addItem(j.get("name").getAsString());
            aspect2Box.addItem(j.get("name").getAsString());
            aspect3Box.addItem(j.get("name").getAsString());
            aspect4Box.addItem(j.get("name").getAsString());
            aspect5Box.addItem(j.get("name").getAsString());
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
        weaponTome1Box.setBounds(10, 175, 200, 20);
        itemBox.add(weaponTome1Box);

        weaponTome2Box.setEditable(true);
        weaponTome2Box.setSelectedIndex(-1);
        weaponTome2Box.getEditor().getEditorComponent().addKeyListener(new CrafterUI.Adapter(weaponTome2Box, weaponTomeJson));
        weaponTome2Box.setBounds(220, 175, 200, 20);
        itemBox.add(weaponTome2Box);

        //Marathon Tomes
        marathonTome1Box.setEditable(true);
        marathonTome1Box.setSelectedIndex(-1);
        marathonTome1Box.getEditor().getEditorComponent().addKeyListener(new CrafterUI.Adapter(marathonTome1Box, marathonTomeJson));
        marathonTome1Box.setBounds(430, 175, 200, 20);
        itemBox.add(marathonTome1Box);

        marathonTome2Box.setEditable(true);
        marathonTome2Box.setSelectedIndex(-1);
        marathonTome2Box.getEditor().getEditorComponent().addKeyListener(new CrafterUI.Adapter(marathonTome2Box, marathonTomeJson));
        marathonTome2Box.setBounds(640, 175, 200, 20);
        itemBox.add(marathonTome2Box);

        //Lootrun Tome
        lootrunTomeBox.setEditable(true);
        lootrunTomeBox.setSelectedIndex(-1);
        lootrunTomeBox.getEditor().getEditorComponent().addKeyListener(new CrafterUI.Adapter(lootrunTomeBox, lootrunTomeJson));
        lootrunTomeBox.setBounds(850, 175, 200, 20);
        itemBox.add(lootrunTomeBox);

        //Expertise Tomes
        expertiseTome1Box.setEditable(true);
        expertiseTome1Box.setSelectedIndex(-1);
        expertiseTome1Box.getEditor().getEditorComponent().addKeyListener(new CrafterUI.Adapter(expertiseTome1Box, expertiseTomeJson));
        expertiseTome1Box.setBounds(10, 230, 200, 20);
        itemBox.add(expertiseTome1Box);

        expertiseTome2Box.setEditable(true);
        expertiseTome2Box.setSelectedIndex(-1);
        expertiseTome2Box.getEditor().getEditorComponent().addKeyListener(new CrafterUI.Adapter(expertiseTome2Box, expertiseTomeJson));
        expertiseTome2Box.setBounds(220, 230, 200, 20);
        itemBox.add(expertiseTome2Box);

        //Mysticism Tomes
        mysticismTome1Box.setEditable(true);
        mysticismTome1Box.setSelectedIndex(-1);
        mysticismTome1Box.getEditor().getEditorComponent().addKeyListener(new CrafterUI.Adapter(mysticismTome1Box, mysticismTomeJson));
        mysticismTome1Box.setBounds(430, 230, 200, 20);
        itemBox.add(mysticismTome1Box);

        mysticismTome2Box.setEditable(true);
        mysticismTome2Box.setSelectedIndex(-1);
        mysticismTome2Box.getEditor().getEditorComponent().addKeyListener(new CrafterUI.Adapter(mysticismTome2Box, mysticismTomeJson));
        mysticismTome2Box.setBounds(640, 230, 200, 20);
        itemBox.add(mysticismTome2Box);

        //Aspects
        aspect1Box.setEditable(true);
        aspect1Box.setSelectedIndex(-1);
        aspect1Box.getEditor().getEditorComponent().addKeyListener(new CrafterUI.Adapter(aspect1Box, aspects));
        aspect1Box.setBounds(10, 285, 200, 20);
        itemBox.add(aspect1Box);

        aspect2Box.setEditable(true);
        aspect2Box.setSelectedIndex(-1);
        aspect2Box.getEditor().getEditorComponent().addKeyListener(new CrafterUI.Adapter(aspect2Box, aspects));
        aspect2Box.setBounds(220, 285, 200, 20);
        itemBox.add(aspect2Box);

        aspect3Box.setEditable(true);
        aspect3Box.setSelectedIndex(-1);
        aspect3Box.getEditor().getEditorComponent().addKeyListener(new CrafterUI.Adapter(aspect3Box, aspects));
        aspect3Box.setBounds(430, 285, 200, 20);
        itemBox.add(aspect3Box);

        aspect4Box.setEditable(true);
        aspect4Box.setSelectedIndex(-1);
        aspect4Box.getEditor().getEditorComponent().addKeyListener(new CrafterUI.Adapter(aspect4Box, aspects));
        aspect4Box.setBounds(640, 285, 200, 20);
        itemBox.add(aspect4Box);

        aspect5Box.setEditable(true);
        aspect5Box.setSelectedIndex(-1);
        aspect5Box.getEditor().getEditorComponent().addKeyListener(new CrafterUI.Adapter(aspect5Box, aspects));
        aspect5Box.setBounds(850, 285, 200, 20);
        itemBox.add(aspect5Box);


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
        JLabel marathonTome1Text = new JLabel("Marathon Tome");
        JLabel marathonTome2Text = new JLabel("Marathon Tome");
        JLabel lootrunTomeText = new JLabel("Lootrun Tome");

        JLabel expertiseTome1Text = new JLabel("Expertise Tome");
        JLabel expertiseTome2Text = new JLabel("Expertise Tome");
        JLabel mysticismTome1Text = new JLabel("Mysticism Tome");
        JLabel mysticismTome2Text = new JLabel("Mysticism Tome");

        JLabel aspect1Text = new JLabel("Aspect");
        JLabel aspect2Text = new JLabel("Aspect");
        JLabel aspect3Text = new JLabel("Aspect");
        JLabel aspect4Text = new JLabel("Aspect");
        JLabel aspect5Text = new JLabel("Aspect");

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

        weaponTome1Text.setBounds(20, 200, 100, 20);
        weaponTome2Text.setBounds(230, 200, 100, 20);
        marathonTome1Text.setBounds(440, 200, 100, 20);
        marathonTome2Text.setBounds(650, 200, 100, 20);
        lootrunTomeText.setBounds(860, 200, 100, 20);

        expertiseTome1Text.setBounds(20, 255, 100, 20);
        expertiseTome2Text.setBounds(230, 255, 100, 20);
        mysticismTome1Text.setBounds(440, 255, 100, 20);
        mysticismTome2Text.setBounds(650, 255, 100, 20);

        aspect1Text.setBounds(20, 310, 100, 20);
        aspect2Text.setBounds(230, 310, 100, 20);
        aspect3Text.setBounds(440, 310, 100, 20);
        aspect4Text.setBounds(650, 310, 100, 20);
        aspect5Text.setBounds(860, 310, 100, 20);

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
        texts.add(marathonTome1Text);
        texts.add(marathonTome2Text);
        texts.add(lootrunTomeText);

        texts.add(expertiseTome1Text);
        texts.add(expertiseTome2Text);
        texts.add(mysticismTome1Text);
        texts.add(mysticismTome2Text);

        texts.add(aspect1Text);
        texts.add(aspect2Text);
        texts.add(aspect3Text);
        texts.add(aspect4Text);
        texts.add(aspect5Text);


        for (JComboBox<String> c : itemBox) {
            pane.add(c);
        }
        for (JTextField t : powderField) {
            pane.add(t);
        }
    }

    public void updateAPIs() {
        if (weaponJson.size() > 0) weaponJson.clear();
        if (helmetJson.size() > 0) helmetJson.clear();
        if (chestplateJson.size() > 0) chestplateJson.clear();
        if (leggingsJson.size() > 0) leggingsJson.clear();
        if (bootsJson.size() > 0) bootsJson.clear();
        if (ringJson.size() > 0) ringJson.clear();
        if (braceletJson.size() > 0) braceletJson.clear();
        if (necklaceJson.size() > 0) necklaceJson.clear();
        setJson(itemAPI);
        if (weaponTomeJson.size() > 0) weaponTomeJson.clear();
        if (guildTomeJson.size() > 0) guildTomeJson.clear();
        if (armourTomeJson.size() > 0) armourTomeJson.clear();
        if (lootrunTomeJson.size() > 0) lootrunTomeJson.clear();
        if (marathonTomeJson.size() > 0) marathonTomeJson.clear();
        if (expertiseTomeJson.size() > 0) expertiseTomeJson.clear();
        if (mysticismTomeJson.size() > 0) mysticismTomeJson.clear();
        setTomeJson();
        for (JComboBox<String> b : itemBox) b.removeAllItems();

        JComboBox<String> helmetBox = itemBox.get(0);
        JComboBox<String> chestplateBox = itemBox.get(1);
        JComboBox<String> leggingsBox = itemBox.get(2);
        JComboBox<String> bootsBox = itemBox.get(3);
        JComboBox<String> ring1Box = itemBox.get(4);
        JComboBox<String> ring2Box = itemBox.get(5);
        JComboBox<String> braceletBox = itemBox.get(6);
        JComboBox<String> necklaceBox = itemBox.get(7);
        JComboBox<String> weaponBox = itemBox.get(8);

        JComboBox<String> armourTome1Box = itemBox.get(9);
        JComboBox<String> armourTome2Box = itemBox.get(10);
        JComboBox<String> armourTome3Box = itemBox.get(11);
        JComboBox<String> armourTome4Box = itemBox.get(12);
        JComboBox<String> guildTomeBox = itemBox.get(13);

        JComboBox<String> weaponTome1Box = itemBox.get(14);
        JComboBox<String> weaponTome2Box = itemBox.get(15);
        JComboBox<String> marathonTome1Box = itemBox.get(16);
        JComboBox<String> marathonTome2Box = itemBox.get(17);
        JComboBox<String> lootrunTomeBox = itemBox.get(18);

        JComboBox<String> expertiseTome1Box = itemBox.get(19);
        JComboBox<String> expertiseTome2Box = itemBox.get(20);
        JComboBox<String> mysticismTome1Box = itemBox.get(21);
        JComboBox<String> mysticismTome2Box = itemBox.get(22);

        for (JsonObject j : helmetJson) {
            if (j.get("name") != null) {
                helmetBox.addItem(j.get("name").getAsString());
            }
        }
        for (JsonObject j : chestplateJson) {
            if (j.get("name") != null) {
                chestplateBox.addItem(j.get("name").getAsString());
            }
        }
        for (JsonObject j : leggingsJson) {
            if (j.get("name") != null) {
                leggingsBox.addItem(j.get("name").getAsString());
            }
        }
        for (JsonObject j : bootsJson) {
            if (j.get("name") != null) {
                bootsBox.addItem(j.get("name").getAsString());
            }
        }
        for (JsonObject j : ringJson) {
            if (j.get("name") != null) {
                ring1Box.addItem(j.get("name").getAsString());
                ring2Box.addItem(j.get("name").getAsString());
            }
        }
        for (JsonObject j : braceletJson) {
            if (j.get("name") != null) {
                braceletBox.addItem(j.get("name").getAsString());
            }
        }
        for (JsonObject j : necklaceJson) {
            if (j.get("name") != null) {
                necklaceBox.addItem(j.get("name").getAsString());
            }
        }
        for (JsonObject j : weaponJson) {
            if (j.get("name") != null) {
                weaponBox.addItem(j.get("name").getAsString());
            }
        }

        for (JsonObject j : armourTomeJson) {
            armourTome1Box.addItem(j.get("name").getAsString());
            armourTome2Box.addItem(j.get("name").getAsString());
            armourTome3Box.addItem(j.get("name").getAsString());
            armourTome4Box.addItem(j.get("name").getAsString());
        }
        for (JsonObject j : guildTomeJson) {
            guildTomeBox.addItem(j.get("name").getAsString());
        }

        for (JsonObject j : weaponTomeJson) {
            weaponTome1Box.addItem(j.get("name").getAsString());
            weaponTome2Box.addItem(j.get("name").getAsString());
        }
        for (JsonObject j : marathonTomeJson) {
            marathonTome1Box.addItem(j.get("name").getAsString());
            marathonTome2Box.addItem(j.get("name").getAsString());
        }
        for (JsonObject j : lootrunTomeJson) {
            lootrunTomeBox.addItem(j.get("name").getAsString());
        }

        for (JsonObject j : expertiseTomeJson) {
            expertiseTome1Box.addItem(j.get("name").getAsString());
            expertiseTome2Box.addItem(j.get("name").getAsString());
        }
        for (JsonObject j : mysticismTomeJson) {
            mysticismTome1Box.addItem(j.get("name").getAsString());
            mysticismTome2Box.addItem(j.get("name").getAsString());
        }


        for (JComboBox<String> b : itemBox) b.setSelectedIndex(-1);
        itemConnect.setText("Item API Latest");
        itemConnect.setForeground(new Color(0, 169, 104));
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
        comprehensive_Display.reset();
        item_display.setItem_Display(itemBox, ingAPI, recipeAPI, helmetJson, chestplateJson, leggingsJson, bootsJson, ringJson, braceletJson, necklaceJson, weaponJson, armourTomeJson, guildTomeJson, weaponTomeJson, marathonTomeJson, lootrunTomeJson, expertiseTomeJson, mysticismTomeJson, classes.getSelectedIndex());
        id_display.setIDs(item_display.getItemJsons(), damage_ids, skillPoint, getTree(), abilityBuffs ,powder_effects, powderField, classes.getSelectedIndex(), false);
        skillPoint.setSkillPoint(item_display.getItemJsons(), id_display.getSetBonuses());
        damage_display.setDamage_Display(item_display.getItemJsons(), skillPoint, abilityBuffs, getTree(), powder_effects, id_display.getId_Numbers(), powderField);
        comprehensive_Display.setDisplay(item_display.getItemJsons(), id_display.getSetBonuses(), skillPoint.getEquipOrder());
    }

    public void updateIDs() {
        skillPoint.updateSkillPoint();
        id_display.setIDs(item_display.getItemJsons(), damage_ids, skillPoint, getTree(), abilityBuffs, powder_effects, powderField, classes.getSelectedIndex(), true);
        damage_display.setDamage_Display(item_display.getItemJsons(), skillPoint, abilityBuffs, getTree(), powder_effects, id_display.getId_Numbers(), powderField);
    }

    public void saveBuild() {
        StringBuilder sb = new StringBuilder();

        //Class
        String base = "{\"class\":" + classes.getSelectedIndex() + ",\"items\":[";
        sb.append(base);

        //Armors, Accessories, Weapon and Tomes
        for (int i = 0; itemBox.size() > i; ++i) {
            String s = "\"" + getItemName(i).replaceAll("\"", "\\\\\"") + "\",";
            sb.append(s);
        }
        sb.deleteCharAt(sb.length() - 1);

        //Powders
        sb.append("],\"powders\":[");
        for (JTextField t : powderField) {
            String s = "\"" + t.getText() + "\",";
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
        for (int i = 0; damage_ids.size() > i; ++i) {
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
        for (int i = 0; j.get("items").getAsJsonArray().size() > i; ++i) {
            setItemName(i, j.get("items").getAsJsonArray().get(i).getAsString());
        }

        //Set Powders
        for (int i = 0; powderField.size() > i; ++i) {
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
        for (int i = 0; j.get("ids").getAsJsonArray().size() > i; ++i) {
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
