package com.github.iTzhsnu.WynnSearcher;

import com.github.iTzhsnu.WynnSearcher.builder.*;
import com.github.iTzhsnu.WynnSearcher.builder.skilltree.*;
import com.github.iTzhsnu.WynnSearcher.data.ItemBase;
import com.github.iTzhsnu.WynnSearcher.general.JsonValues;
import com.github.iTzhsnu.WynnSearcher.ui.ItemBoxAdapter;
import com.github.iTzhsnu.WynnSearcher.ui.UiUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BuilderUi implements ActionListener {
    //Main Panel
    private final JPanel pane = new JPanel();
    private final JScrollPane scrollPane;

    //UIs
    private final List<JLabel> texts = new ArrayList<>();
    private final List<JComboBox<String>> itemBox = new ArrayList<>();
    private final List<JTextField> powderField = new ArrayList<>();
    private final JButton create = new JButton("Create");
    private final JButton update = new JButton("Update");
    private final JButton save = new JButton("Save");
    private final JButton load = new JButton("Load");
    private final JTextField output = UiUtils.createNoBeepTextField();

    //API Connect
    private final JLabel itemConnect = new JLabel();
    private final JLabel recipeConnect = new JLabel();
    private final JLabel treeConnect = new JLabel("Tree API Connected");

    //Skill Point, Damage IDs and ID Display
    private final SkillPoint skillPoint;
    private final DamageId damage_ids;
    private final IdDisplay id_display;
    private final DamageDisplay damage_display;
    private final ItemDisplay item_display;
    private final PowderEffect powder_effects;
    private final ComprehensiveDisplay comprehensive_Display;

    //Ability Tree
    private final JComboBox<String> classes = new JComboBox<>();
    private final AbilityBuffs abilityBuffs;
    private final TreeBase warrior;
    private final TreeBase assassin;
    private final TreeBase archer;
    private final TreeBase mage;
    private final TreeBase shaman;

    public BuilderUi(Container p) {
        pane.setPreferredSize(new Dimension(1064, 2185));
        pane.setLayout(null);

        scrollPane = new JScrollPane(pane);
        scrollPane.setBounds(2, 30, 1082, 730);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);

        ApiDataManager api = ApiDataManager.getManager();
        ApiDataManager.setApiConnectText(itemConnect, "Item", api.itemApiConnect);
        ApiDataManager.setApiConnectText(recipeConnect, "Recipe", api.recipeApiConnect);
        itemConnect.setBounds(400, 5, 150, 20);
        recipeConnect.setBounds(600, 5, 150, 20);

        setComboBoxes(api);

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
        damage_ids = new DamageId(pane);
        id_display = new IdDisplay(pane);
        damage_display = new DamageDisplay(pane);
        item_display = new ItemDisplay(pane);
        powder_effects = new PowderEffect(pane);
        comprehensive_Display = new ComprehensiveDisplay(pane);

        classes.setBounds(10, 350, 80, 20);
        classes.addItem("Warrior");
        classes.addItem("Assassin");
        classes.addItem("Mage");
        classes.addItem("Archer");
        classes.addItem("Shaman");
        classes.addActionListener(this);

        abilityBuffs = new AbilityBuffs(pane);

        treeConnect.setForeground(new Color(0, 169, 104));
        treeConnect.setBounds(800, 5, 150, 20);
        warrior = new TreeBase(pane, Archetype.FALLEN, Archetype.BATTLE_MONK, Archetype.PALADIN, JsonValues.WARRIOR, treeConnect);
        assassin = new TreeBase(pane, Archetype.SHADESTEPPER, Archetype.TRICKSTER, Archetype.ACROBAT, JsonValues.ASSASSIN, treeConnect);
        archer = new TreeBase(pane, Archetype.BOLTSLINGER, Archetype.SHARPSHOOTER, Archetype.TRAPPER, JsonValues.ARCHER, treeConnect);
        mage = new TreeBase(pane, Archetype.RIFTWALKER, Archetype.LIGHT_BENDER, Archetype.ARCANIST, JsonValues.MAGE, treeConnect);
        shaman = new TreeBase(pane, Archetype.SUMMONER, Archetype.RITUALIST, Archetype.ACOLYTE, JsonValues.SHAMAN, treeConnect);

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

    public void setBuilderVisible(boolean visible) {
        scrollPane.setVisible(visible);
        itemConnect.setVisible(visible);
        recipeConnect.setVisible(visible);
        treeConnect.setVisible(visible);
    }

    // TODO Update
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
        abilityBuffs.getSlider().get(AbilityBuffsEnum.CORRUPTED.getPos()).setSliderVisible(warriorB); //Corrupted
        abilityBuffs.getSlider().get(AbilityBuffsEnum.DISCOMBOBULATE.getPos()).setSliderVisible(warriorB); //Discombobulate
        abilityBuffs.getBox().get(AbilityBuffsEnum.MANTLE.getPos()).setVisible(warriorB); //Mantle
        abilityBuffs.getBox().get(AbilityBuffsEnum.BRINK_OF_MADNESS.getPos()).setVisible(warriorB); //Brink of Madness
        abilityBuffs.getBox().get(AbilityBuffsEnum.SACRED_SURGE.getPos()).setVisible(warriorB); //Sacred Surge

        assassin.setTreeVisible(assassinB);
        abilityBuffs.getSlider().get(AbilityBuffsEnum.CLONES.getPos()).setSliderVisible(assassinB); //Clones
        abilityBuffs.getSlider().get(AbilityBuffsEnum.NIGHTCLOAK_KNIFE.getPos()).setSliderVisible(assassinB); //Nightcloak Knife
        abilityBuffs.getBox().get(AbilityBuffsEnum.BACKSTAB.getPos()).setVisible(assassinB); //Backstab
        abilityBuffs.getBox().get(AbilityBuffsEnum.SURPRISE_STRIKE.getPos()).setVisible(assassinB); //Surprise Strike
        abilityBuffs.getBox().get(AbilityBuffsEnum.DELIRIOUS_GAS.getPos()).setVisible(assassinB); //Derilious Gas
        abilityBuffs.getBox().get(AbilityBuffsEnum.MIRROR_IMAGE.getPos()).setVisible(assassinB); //Mirror Image
        abilityBuffs.getBox().get(AbilityBuffsEnum.SATSUJIN.getPos()).setVisible(assassinB); //Satsujin
        abilityBuffs.getBox().get(AbilityBuffsEnum.FLOW_STATE.getPos()).setVisible(assassinB); //Flow State
        abilityBuffs.getBox().get(AbilityBuffsEnum.PARRY.getPos()).setVisible(assassinB); //Parry
        abilityBuffs.getBox().get(AbilityBuffsEnum.DISSOLUTION.getPos()).setVisible(assassinB); //Dissolution

        archer.setTreeVisible(archerB);
        abilityBuffs.getSlider().get(AbilityBuffsEnum.FOCUS.getPos()).setSliderVisible(archerB); //Focus
        abilityBuffs.getSlider().get(AbilityBuffsEnum.PATIENT_HUNTER.getPos()).setSliderVisible(archerB); //Patient Hunter
        abilityBuffs.getSlider().get(AbilityBuffsEnum.DECIMATOR.getPos()).setSliderVisible(archerB); //Decimator
        abilityBuffs.getBox().get(AbilityBuffsEnum.INITIATOR.getPos()).setVisible(archerB); //Initiator

        mage.setTreeVisible(mageB);
        abilityBuffs.getSlider().get(AbilityBuffsEnum.WINDED.getPos()).setSliderVisible(mageB); //Winded

        shaman.setTreeVisible(shamanB);
        abilityBuffs.getSlider().get(AbilityBuffsEnum.SHEPHERD.getPos()).setSliderVisible(shamanB); //Shepherd
        abilityBuffs.getBox().get(AbilityBuffsEnum.MASK_OF_THE_LUNATIC.getPos()).setVisible(shamanB); //Mask of the Lunatic
        abilityBuffs.getBox().get(AbilityBuffsEnum.MASK_OF_THE_FANATIC.getPos()).setVisible(shamanB); //Mask of the Fanatic
        abilityBuffs.getBox().get(AbilityBuffsEnum.MASK_OF_THE_COWARD.getPos()).setVisible(shamanB); //Mask of the Coward
        abilityBuffs.getBox().get(AbilityBuffsEnum.CHANT_OF_THE_FANATIC.getPos()).setVisible(shamanB); //Chant of the Fanatic
        abilityBuffs.getBox().get(AbilityBuffsEnum.BULLWHIP.getPos()).setVisible(shamanB); //Bullwhip
        abilityBuffs.getBox().get(AbilityBuffsEnum.INVIGORATING_WAVE.getPos()).setVisible(shamanB); //Invigorating Wave
        abilityBuffs.getBox().get(AbilityBuffsEnum.SACRIFICIAL_SHRINE.getPos()).setVisible(shamanB); //Sacrificial Shrine
        abilityBuffs.getBox().get(AbilityBuffsEnum.AWAKENED.getPos()).setVisible(shamanB); //Awakened
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

    public void setComboBoxes(ApiDataManager api) {
        JComboBox<String> helmetBox = setComboBox(api.helmetData);
        JComboBox<String> chestplateBox = setComboBox(api.chestplateData);
        JComboBox<String> leggingsBox = setComboBox(api.leggingsData);
        JComboBox<String> bootsBox = setComboBox(api.bootsData);

        JComboBox<String> ring1Box = setComboBox(api.ringData);
        JComboBox<String> ring2Box = setComboBox(api.ringData);
        JComboBox<String> braceletBox = setComboBox(api.braceletData);
        JComboBox<String> necklaceBox = setComboBox(api.necklaceData);
        JComboBox<String> weaponBox = setComboBox(api.weaponData);

        JComboBox<String> armourTome1Box = setComboBox(api.armourTomeData);
        JComboBox<String> armourTome2Box = setComboBox(api.armourTomeData);
        JComboBox<String> armourTome3Box = setComboBox(api.armourTomeData);
        JComboBox<String> armourTome4Box = setComboBox(api.armourTomeData);
        JComboBox<String> guildTomeBox = setComboBox(api.guildTomeData);

        JComboBox<String> weaponTome1Box = setComboBox(api.weaponTomeData);
        JComboBox<String> weaponTome2Box = setComboBox(api.weaponTomeData);
        JComboBox<String> marathonTome1Box = setComboBox(api.marathonTomeData);
        JComboBox<String> marathonTome2Box = setComboBox(api.marathonTomeData);
        JComboBox<String> lootrunTomeBox = setComboBox(api.lootrunTomeData);

        JComboBox<String> expertiseTome1Box = setComboBox(api.expertiseTomeData);
        JComboBox<String> expertiseTome2Box = setComboBox(api.expertiseTomeData);
        JComboBox<String> mysticismTome1Box = setComboBox(api.mysticismTomeData);
        JComboBox<String> mysticismTome2Box = setComboBox(api.mysticismTomeData);

        JComboBox<String> aspect1Box = setComboBox(api.aspects);
        JComboBox<String> aspect2Box = setComboBox(api.aspects);
        JComboBox<String> aspect3Box = setComboBox(api.aspects);
        JComboBox<String> aspect4Box = setComboBox(api.aspects);
        JComboBox<String> aspect5Box = setComboBox(api.aspects);

        // Helmet
        helmetBox.setBounds(10, 10, 200, 20);
        itemBox.add(helmetBox);

        JTextField helmetPowder = UiUtils.createNoBeepTextField();
        helmetPowder.setBounds(130, 35, 80, 20);
        powderField.add(helmetPowder);

        //Chestplate
        chestplateBox.setBounds(220, 10, 200, 20);
        itemBox.add(chestplateBox);

        JTextField chestplatePowder = UiUtils.createNoBeepTextField();
        chestplatePowder.setBounds(340, 35, 80, 20);
        powderField.add(chestplatePowder);

        //Leggings
        leggingsBox.setBounds(430, 10, 200, 20);
        itemBox.add(leggingsBox);

        JTextField leggingsPowder = UiUtils.createNoBeepTextField();
        leggingsPowder.setBounds(550, 35, 80, 20);
        powderField.add(leggingsPowder);

        //Boots
        bootsBox.setBounds(640, 10, 200, 20);
        itemBox.add(bootsBox);

        JTextField bootsPowder = UiUtils.createNoBeepTextField();
        bootsPowder.setBounds(760, 35, 80, 20);
        powderField.add(bootsPowder);

        //Rings
        ring1Box.setBounds(10, 65, 200, 20);
        itemBox.add(ring1Box);

        ring2Box.setBounds(220, 65, 200, 20);
        itemBox.add(ring2Box);

        //Bracelet
        braceletBox.setBounds(430, 65, 200, 20);
        itemBox.add(braceletBox);

        //Necklace
        necklaceBox.setBounds(640, 65, 200, 20);
        itemBox.add(necklaceBox);

        //Weapon
        weaponBox.setBounds(850, 65, 200, 20);
        itemBox.add(weaponBox);

        JTextField weaponPowder = new JTextField();
        weaponPowder.setBounds(970, 90, 80, 20);
        powderField.add(weaponPowder);

        //Armour Tomes
        armourTome1Box.setBounds(10, 120, 200, 20);
        itemBox.add(armourTome1Box);

        armourTome2Box.setBounds(220, 120, 200, 20);
        itemBox.add(armourTome2Box);

        armourTome3Box.setBounds(430, 120, 200, 20);
        itemBox.add(armourTome3Box);

        armourTome4Box.setBounds(640, 120, 200, 20);
        itemBox.add(armourTome4Box);

        //Guild Tome
        guildTomeBox.setBounds(850, 120, 200, 20);
        itemBox.add(guildTomeBox);

        //Weapon Tomes
        weaponTome1Box.setBounds(10, 175, 200, 20);
        itemBox.add(weaponTome1Box);

        weaponTome2Box.setBounds(220, 175, 200, 20);
        itemBox.add(weaponTome2Box);

        //Marathon Tomes
        marathonTome1Box.setBounds(430, 175, 200, 20);
        itemBox.add(marathonTome1Box);

        marathonTome2Box.setBounds(640, 175, 200, 20);
        itemBox.add(marathonTome2Box);

        //Lootrun Tome
        lootrunTomeBox.setBounds(850, 175, 200, 20);
        itemBox.add(lootrunTomeBox);

        //Expertise Tomes
        expertiseTome1Box.setBounds(10, 230, 200, 20);
        itemBox.add(expertiseTome1Box);

        expertiseTome2Box.setBounds(220, 230, 200, 20);
        itemBox.add(expertiseTome2Box);

        //Mysticism Tomes
        mysticismTome1Box.setBounds(430, 230, 200, 20);
        itemBox.add(mysticismTome1Box);

        mysticismTome2Box.setBounds(640, 230, 200, 20);
        itemBox.add(mysticismTome2Box);

        //Aspects
        aspect1Box.setBounds(10, 285, 200, 20);
        itemBox.add(aspect1Box);

        aspect2Box.setBounds(220, 285, 200, 20);
        itemBox.add(aspect2Box);

        aspect3Box.setBounds(430, 285, 200, 20);
        itemBox.add(aspect3Box);

        aspect4Box.setBounds(640, 285, 200, 20);
        itemBox.add(aspect4Box);

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

    public <T extends ItemBase> JComboBox<String> setComboBox(List<T> items) {
        JComboBox<String> box = UiUtils.createNoBeepComboBox();
        setBoxItem(box, items);

        box.setEditable(true);
        box.setSelectedIndex(-1);
        box.getEditor().getEditorComponent().addKeyListener(new ItemBoxAdapter<>(box, items));
        return box;
    }

    public <T extends ItemBase> void setBoxItem(JComboBox<String> box, List<T> items) {
        for (T i : items) {
            if (!i.getName().isEmpty()) box.addItem(i.getName());
        }
    }

    public void updateApi() {
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

        ApiDataManager api = ApiDataManager.getManager();
        setBoxItem(helmetBox, api.helmetData);
        setBoxItem(chestplateBox, api.chestplateData);
        setBoxItem(leggingsBox, api.leggingsData);
        setBoxItem(bootsBox, api.bootsData);

        setBoxItem(ring1Box, api.ringData);
        setBoxItem(ring2Box, api.ringData);
        setBoxItem(braceletBox, api.braceletData);
        setBoxItem(necklaceBox, api.necklaceData);
        setBoxItem(weaponBox, api.weaponData);

        setBoxItem(armourTome1Box, api.armourTomeData);
        setBoxItem(armourTome2Box, api.armourTomeData);
        setBoxItem(armourTome3Box, api.armourTomeData);
        setBoxItem(armourTome4Box, api.armourTomeData);
        setBoxItem(guildTomeBox, api.guildTomeData);

        setBoxItem(weaponTome1Box, api.weaponTomeData);
        setBoxItem(weaponTome2Box, api.weaponTomeData);
        setBoxItem(marathonTome1Box, api.marathonTomeData);
        setBoxItem(marathonTome2Box, api.marathonTomeData);
        setBoxItem(lootrunTomeBox, api.lootrunTomeData);

        setBoxItem(expertiseTome1Box, api.expertiseTomeData);
        setBoxItem(expertiseTome2Box, api.expertiseTomeData);
        setBoxItem(mysticismTome1Box, api.mysticismTomeData);
        setBoxItem(mysticismTome2Box, api.mysticismTomeData);

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
        item_display.setItem_Display(itemBox, classes.getSelectedIndex());
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
