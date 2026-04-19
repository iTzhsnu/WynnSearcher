package com.github.iTzhsnu.WynnSearcher;

import com.github.iTzhsnu.WynnSearcher.data.Ingredient;
import com.github.iTzhsnu.WynnSearcher.data.ItemBase;
import com.github.iTzhsnu.WynnSearcher.general.*;
import com.github.iTzhsnu.WynnSearcher.ui.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class SearchUI extends JFrame implements ActionListener {
    public static final String VERSION = "4.4.6";

    //API
    private final JLabel itemAPIConnect = new JLabel("Item API Connecting...");

    //Item or Ingredient
    private final JComboBox<String> itemType = new JComboBox<>();

    //Sort Min or Max
    private final JComboBox<String> sortType = new JComboBox<>();

    //Item Check Boxes
    private final JComboBox<String> itemTier = new JComboBox<>();
    private final JCheckBox bow = new JCheckBox("Bow");
    private final JCheckBox spear = new JCheckBox("Spear");
    private final JCheckBox wand = new JCheckBox("Wand");
    private final JCheckBox dagger = new JCheckBox("Dagger");
    private final JCheckBox relik = new JCheckBox("Relik");
    private final JCheckBox helmet = new JCheckBox("Helmet");
    private final JCheckBox chestplate = new JCheckBox("Chestplate");
    private final JCheckBox leggings = new JCheckBox("Leggings");
    private final JCheckBox boots = new JCheckBox("Boots");
    private final JCheckBox ring = new JCheckBox("Ring");
    private final JCheckBox bracelet = new JCheckBox("Bracelet");
    private final JCheckBox necklace = new JCheckBox("Necklace");

    //Ingredient Check Boxes
    private final JComboBox<String> tier = new JComboBox<>();
    private final JCheckBox armouring = new JCheckBox("Armouring");
    private final JCheckBox tailoring = new JCheckBox("Tailoring");
    private final JCheckBox weaponsmithing = new JCheckBox("Weaponsmithing");
    private final JCheckBox woodworking = new JCheckBox("Woodworking");
    private final JCheckBox jeweling = new JCheckBox("Jeweling");
    private final JCheckBox scribing = new JCheckBox("Scribing");
    private final JCheckBox cooking = new JCheckBox("Cooking");
    private final JCheckBox alchemism = new JCheckBox("Alchemism");

    //Other Check Boxes
    private final JCheckBox tome = new JCheckBox("Tome");
    private final JCheckBox charm = new JCheckBox("Charm");
    private final JCheckBox tool = new JCheckBox("Tool");
    private final JCheckBox material = new JCheckBox("Material");
    private final JCheckBox armourTome = new JCheckBox("Armour");
    private final JCheckBox guildTome = new JCheckBox("Guild");
    private final JCheckBox weaponTome = new JCheckBox("Weapon");
    private final JCheckBox marathonTome = new JCheckBox("Marathon");
    private final JCheckBox lootrunTome = new JCheckBox("Lootrun");
    private final JCheckBox expertiseTome = new JCheckBox("Expertise");
    private final JCheckBox mysticismTome = new JCheckBox("Mysticism");

    //Aspect Check Boxes
    private final JComboBox<String> aspectType = new JComboBox<>();
    private final JCheckBox archetype1Aspect = new JCheckBox();
    private final JCheckBox archetype2Aspect = new JCheckBox();
    private final JCheckBox archetype3Aspect = new JCheckBox();
    private final JCheckBox otherTypeAspect = new JCheckBox("Other");

    //Search Button and Text
    private final JLabel name = new JLabel("Name:");
    private final JButton searchB = new JButton("Search");
    private final JTextField searchF = UiUtils.createNoBeepTextField();
    private final Container contentPane;
    private final JPanel searched = new JPanel();
    private final List<JPanel> itemDisplays = new ArrayList<>();
    private final List<ItemBase> searchedItems = new ArrayList<>();
    private final JLabel searchedItemCount = new JLabel();
    private final JScrollPane scrollPane;
    private final JButton updateSize = new JButton("Update Size");
    private final JLabel displayTime = new JLabel();
    private final JComboBox<String> type = new JComboBox<>();
    private final JButton updateAPI = new JButton("Update API");

    //ID Combo Box
    private final List<JComboBox<String>> idBoxes_1 = new ArrayList<>();
    private final List<JComboBox<String>> idBoxes_2 = new ArrayList<>();
    private final List<JComboBox<String>> idBoxes_3 = new ArrayList<>();
    private final List<JComboBox<String>> idBoxes_4 = new ArrayList<>();

    //ID Min Text
    private final JTextField idMin_1 = UiUtils.createNoBeepTextField();
    private final JTextField idMin_2 = UiUtils.createNoBeepTextField();
    private final JTextField idMin_3 = UiUtils.createNoBeepTextField();
    private final JTextField idMin_4 = UiUtils.createNoBeepTextField();

    //ID Max Text
    private final JTextField idMax_1 = UiUtils.createNoBeepTextField();
    private final JTextField idMax_2 = UiUtils.createNoBeepTextField();
    private final JTextField idMax_3 = UiUtils.createNoBeepTextField();
    private final JTextField idMax_4 = UiUtils.createNoBeepTextField();

    //ID Text
    private final List<JLabel> idTexts = new ArrayList<>();

    //Other UIs
    private final CrafterUI crafterUI;
    private final BuilderUI builderUI;
    private final CustomUI customUI;
    private final ChangesUI changesUI;

    public SearchUI() {
        ApiDataManager.Init();
        ApiDataManager apiMan = ApiDataManager.getManager();
        ApiDataManager.setApiConnectText(itemAPIConnect, "Item", apiMan.itemApiConnect);

        if (ApiDataManager.getVersion()) {
            setTitle("Wynncraft Searcher (" + VERSION + ") Update Available");
        } else {
            setTitle("Wynncraft Searcher (" + VERSION + ")");
        }
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/wynn_searcher_icon.png"))).getImage());
        setBounds(100, 100, 1100, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = getContentPane();
        setLayout(null);

        //Search Text Field
        searchF.setBounds(10, 30, 200, 30);
        name.setBounds(10, 0, 60, 30);

        //Click to Search
        searchB.setBounds(230, 20, 80, 40);
        searchB.addActionListener(this);

        //Item or Ingredient
        itemType.setBounds(320, 15, 170, 20);
        itemType.addItem(DataKeys.TYPE_EQUIPMENT);
        itemType.addItem(DataKeys.TYPE_INGREDIENT);
        itemType.addItem(DataKeys.TYPE_OTHER_ITEM);
        itemType.addItem(DataKeys.TYPE_ASPECT);
        itemType.addActionListener(this);

        typeItemDataSet();
        typeIngredientDataSet();
        typeOtherItemsDataSet();
        typeAspectDataSet();
        setVisibleIngredient(false);
        setVisibleOther(false);
        setVisibleAspect(false);
        setVisibleItem(true);

        //ID Search Setting Bar
        setIDBoxAndIDField(idBoxes_1, idMin_1, idMax_1, 10, 65, 4, true);
        setIDBoxAndIDField(idBoxes_2, idMin_2, idMax_2, 10, 115, 4, false);
        setIDBoxAndIDField(idBoxes_3, idMin_3, idMax_3, 10, 165, 4, false);
        setIDBoxAndIDField(idBoxes_4, idMin_4, idMax_4, 10, 215, 4, false);

        //Searched Items (or Ingredients) List Panel
        searched.setBorder(new LineBorder(Color.BLACK));
        searched.setPreferredSize(new Dimension(1057, 482));
        searched.setLayout(null);

        scrollPane = new JScrollPane(searched);
        scrollPane.setBounds(5, 270, 1075, 485);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);

        //Sort Min or Max
        sortType.setBounds(10, 90, 90, 20);
        sortType.addItem(DataKeys.SORT_MAX);
        sortType.addItem(DataKeys.SORT_MIN);

        //Searched Item Count
        searchedItemCount.setBounds(450, 235, 200, 40);

        //Update API
        updateAPI.setBounds(850, 237, 100, 30);
        updateAPI.addActionListener(this);

        //Update Size
        updateSize.setBounds(960, 237, 105, 30);
        updateSize.addActionListener(this);

        //Sort Filter Display Time
        displayTime.setBounds(960, 180, 110, 40);

        //Item and Ing Search, Craft, Build or Powder
        type.setBounds(80, 5, 130, 20);
        type.addItem("Searcher (Stable)");
        type.addItem("Crafter (Beta)");
        type.addItem("Builder (Beta)");
        type.addItem("Custom");
        type.addItem("Changes (Beta)");
        type.addActionListener(this);

        //Add Contents
        contentPane.add(name);
        contentPane.add(searchB);
        contentPane.add(searchF);
        contentPane.add(itemType);
        contentPane.add(scrollPane);
        contentPane.add(sortType);
        contentPane.add(searchedItemCount);
        contentPane.add(updateSize);
        contentPane.add(type);
        contentPane.add(displayTime);
        contentPane.add(updateAPI);

        this.crafterUI = new CrafterUI(contentPane);
        this.builderUI = new BuilderUI(contentPane);
        this.customUI = new CustomUI(contentPane);
        this.changesUI = new ChangesUI(this);

        crafterUI.setCrafterVisible(false);
        builderUI.setBuilderVisible(false);
        customUI.setCustomVisible(false);
        changesUI.setChangesVisible(false);

        ToolTipManager tipManager = ToolTipManager.sharedInstance();
        tipManager.setInitialDelay(100);
        tipManager.setDismissDelay(Integer.MAX_VALUE);
        tipManager.setReshowDelay(100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchB) {
            search();
        } else if (e.getSource() == itemType) {
            changeItemType();
        } else if (e.getSource() == updateSize) {
            scrollPane.setSize(getWidth() - 25, getHeight() - 315);
            SwingUtilities.updateComponentTreeUI(searched);
        } else if (e.getSource() == type) {
            changeUIType();
        } else if (e.getSource() == updateAPI) {
            updateAPI.setVisible(false);
            itemAPIConnect.setText("Updating");
            itemAPIConnect.setForeground(new Color(255, 255, 0));
            updateApi();
        } else if (e.getSource() == tome) {
            armourTome.setVisible(tome.isSelected());
            guildTome.setVisible(tome.isSelected());
            weaponTome.setVisible(tome.isSelected());
            marathonTome.setVisible(tome.isSelected());
            lootrunTome.setVisible(tome.isSelected());
            expertiseTome.setVisible(tome.isSelected());
            mysticismTome.setVisible(tome.isSelected());
        } else if (e.getSource() == aspectType) {
            changeAspectType();
        }
    }

    public void search() {
        for (int i = itemDisplays.size() - 1; i >= 0; --i) {
            searched.remove(itemDisplays.get(i));
            itemDisplays.remove(i);
        }
        if (canSearchItem()) {
            if (type.getSelectedIndex() == 4) {
                changesUI.searchItems();
            } else {
                searchItems(null);
            }
        } else if (canSearchIngredient()) {
            if (type.getSelectedIndex() == 4) {
                changesUI.searchIng();
            } else {
                searchIngredient(null);
            }
        } else if (canSearchOtherItems()) {
            if (type.getSelectedIndex() == 4) {
                changesUI.searchOther();
            } else {
                searchOtherItems(null);
            }
        } else if (canSearchAspects()) {
            if (type.getSelectedIndex() == 4) {
                searchedItemCount.setText("Changes can't search Aspect");
            } else {
                searchAspects();
            }
        } else {
            searchedItemCount.setText("Search Failed");
        }
    }

    public void changeItemType() {
        if (type.getSelectedIndex() == 4) {
            changesUI.setFileList(itemType.getItemAt(itemType.getSelectedIndex()));
        }
        if (Objects.equals(itemType.getItemAt(itemType.getSelectedIndex()), DataKeys.TYPE_EQUIPMENT)) {
            setVisibleIngredient(false);
            setVisibleOther(false);
            setVisibleAspect(false);
            setVisibleItem(true);
        } else if (Objects.equals(itemType.getItemAt(itemType.getSelectedIndex()), DataKeys.TYPE_INGREDIENT)) {
            setVisibleItem(false);
            setVisibleOther(false);
            setVisibleAspect(false);
            setVisibleIngredient(true);
        } else if (itemType.getItemAt(itemType.getSelectedIndex()).equals(DataKeys.TYPE_OTHER_ITEM)) {
            setVisibleItem(false);
            setVisibleIngredient(false);
            setVisibleAspect(false);
            setVisibleOther(true);
        } else if (itemType.getItemAt(itemType.getSelectedIndex()).equals(DataKeys.TYPE_ASPECT)) {
            setVisibleItem(false);
            setVisibleIngredient(false);
            setVisibleOther(false);
            setVisibleAspect(true);
        }
    }

    public void changeUIType() {
        switch (type.getSelectedIndex()) {
            case 0:
                setSearcherVisible(true);
                break;
            case 1:
                setCrafterVisible(true);
                break;
            case 2:
                setBuilderVisible(true);
                break;
            case 3:
                setCustomVisible(true);
                break;
            case 4:
                setChangesVisible(true);
                break;
        }
    }

    public void updateApi() {
        ApiDataManager.getManager().updateApi();
        crafterUI.updateIngAPI();
        builderUI.updateApi();

        updateAPI.setVisible(true);
    }

    public void setIDBoxAndIDField(List<JComboBox<String>> boxes, JTextField min, JTextField max, int baseX, int baseY, int length, boolean need) {
        for (int i = 0; length > i; ++i) {
            //ID Box
            boxes.add(UiUtils.createNoBeepComboBox());
            boxes.get(i).setBounds(baseX + (224 * i), baseY, 200, 20);
            for (String s : IdBoxAdapter.DISPLAY_ID_LIST) {
                boxes.get(i).addItem(s);
            }
            boxes.get(i).setEditable(true);

            if (need && i > 0) {
                boxes.get(i).setSelectedIndex(-1);
            } else if (!need) {
                boxes.get(i).setSelectedIndex(-1);
            }

            boxes.get(i).getEditor().getEditorComponent().addKeyListener(new IdBoxAdapter(boxes.get(i)));

            //And Or
            if (i < 3) {
                JLabel label;
                if (need) {
                    label = new JLabel("and");
                } else {
                    label = new JLabel(" or");
                }
                label.setBounds(baseX + 202 + (224 * i), baseY, 30, 20);
                idTexts.add(label);
                contentPane.add(idTexts.get(idTexts.size() - 1));
            } else if (i == 3) {
                JLabel label;
                if (need) {
                    label = new JLabel("(Total Value Sort)");
                } else {
                    label = new JLabel("(Need One of These)");
                }
                label.setBounds(baseX + 202 + (224 * i), baseY, 115, 20);
                idTexts.add(label);
                contentPane.add(idTexts.get(idTexts.size() - 1));
            }

            contentPane.add(boxes.get(i));
        }

        //Min Max
        min.setBounds(baseX + 120, baseY + 21, 30, 20);
        max.setBounds(baseX + 170, baseY + 21, 30, 20);

        JLabel toName = new JLabel("to");
        toName.setBounds(baseX + 155, baseY + 20, 20, 20);
        idTexts.add(toName);

        contentPane.add(min);
        contentPane.add(idTexts.get(idTexts.size() - 1));
        contentPane.add(max);
    }

    public void typeItemDataSet() {
        itemTier.addItem(DataKeys.RARITY_ALL);
        itemTier.addItem(DataKeys.RARITY_NO_NORMAL);
        itemTier.addItem(DataKeys.RARITY_MYTHIC);
        itemTier.addItem(DataKeys.RARITY_FABLED);
        itemTier.addItem(DataKeys.RARITY_LEGENDARY);
        itemTier.addItem(DataKeys.RARITY_RARE);
        itemTier.addItem(DataKeys.RARITY_UNIQUE);
        itemTier.addItem(DataKeys.RARITY_SET);
        itemTier.addItem(DataKeys.RARITY_NORMAL);
        itemTier.setBounds(320, 35, 170, 20);

        bow.setBounds(500, 10, 50, 20);
        spear.setBounds(560, 10, 60, 20);
        wand.setBounds(630, 10, 60, 20);
        dagger.setBounds(700, 10, 70, 20);
        relik.setBounds(780, 10, 60, 20);

        helmet.setBounds(500, 35, 65, 20);
        chestplate.setBounds(575, 35, 90, 20);
        leggings.setBounds(675, 35, 80, 20);
        boots.setBounds(765, 35, 60, 20);

        ring.setBounds(850, 10, 50, 20);
        bracelet.setBounds(910, 10, 80, 20);
        necklace.setBounds(835, 35, 80, 20);

        itemAPIConnect.setBounds(925, 35, 120, 20);

        contentPane.add(itemTier);
        contentPane.add(bow);
        contentPane.add(spear);
        contentPane.add(wand);
        contentPane.add(dagger);
        contentPane.add(relik);
        contentPane.add(helmet);
        contentPane.add(chestplate);
        contentPane.add(leggings);
        contentPane.add(boots);
        contentPane.add(ring);
        contentPane.add(bracelet);
        contentPane.add(necklace);
        contentPane.add(itemAPIConnect);
    }

    public void typeIngredientDataSet() {
        tier.addItem(DataKeys.RARITY_ALL);
        tier.addItem(DataKeys.RARITY_0STAR);
        tier.addItem(DataKeys.RARITY_1STAR);
        tier.addItem(DataKeys.RARITY_2STAR);
        tier.addItem(DataKeys.RARITY_3STAR);
        tier.setBounds(320, 35, 170, 20);

        armouring.setBounds(500, 10, 85, 20);
        tailoring.setBounds(595, 10, 75, 20);
        weaponsmithing.setBounds(680, 10, 125, 20);
        woodworking.setBounds(815, 10, 105, 20);

        jeweling.setBounds(500, 35, 80, 20);
        scribing.setBounds(590, 35, 75, 20);
        cooking.setBounds(675, 35, 75, 20);
        alchemism.setBounds(760, 35, 90, 20);

        contentPane.add(tier);
        contentPane.add(armouring);
        contentPane.add(tailoring);
        contentPane.add(weaponsmithing);
        contentPane.add(woodworking);
        contentPane.add(jeweling);
        contentPane.add(scribing);
        contentPane.add(cooking);
        contentPane.add(alchemism);
    }

    public void typeOtherItemsDataSet() {
        tome.setBounds(500, 10, 60, 20);
        charm.setBounds(570, 10, 65, 20);
        tool.setBounds(645, 10, 50, 20);
        material.setBounds(705, 10, 75, 20);

        armourTome.setBounds(500, 35, 70, 20);
        guildTome.setBounds(575, 35, 55, 20);
        weaponTome.setBounds(635, 35, 75, 20);
        marathonTome.setBounds(715, 35, 80, 20);
        lootrunTome.setBounds(800, 35, 70, 20);
        expertiseTome.setBounds(785, 10, 80, 20);
        mysticismTome.setBounds(870, 10, 85, 20);

        contentPane.add(tome);
        contentPane.add(charm);
        contentPane.add(tool);
        contentPane.add(material);

        contentPane.add(armourTome);
        contentPane.add(guildTome);
        contentPane.add(weaponTome);
        contentPane.add(marathonTome);
        contentPane.add(lootrunTome);
        contentPane.add(expertiseTome);
        contentPane.add(mysticismTome);

        tome.addActionListener(this);

        armourTome.setSelected(true);
        guildTome.setSelected(true);
        weaponTome.setSelected(true);
        marathonTome.setSelected(true);
        lootrunTome.setSelected(true);
        expertiseTome.setSelected(true);
        mysticismTome.setSelected(true);
    }

    public void typeAspectDataSet() {
        aspectType.setBounds(490, 15, 100, 20);
        aspectType.addItem("Warrior");
        aspectType.addItem("Assassin");
        aspectType.addItem("Mage");
        aspectType.addItem("Archer");
        aspectType.addItem("Shaman");
        aspectType.addActionListener(this);

        warriorAspectUI();

        contentPane.add(aspectType);
        contentPane.add(archetype1Aspect);
        contentPane.add(archetype2Aspect);
        contentPane.add(archetype3Aspect);
        contentPane.add(otherTypeAspect);
    }

    public void changeAspectType() {
        switch (aspectType.getSelectedIndex()) {
            case 0:
                warriorAspectUI();
                break;
            case 1:
                assassinAspectUI();
                break;
            case 2:
                mageAspectUI();
                break;
            case 3:
                archerAspectUI();
                break;
            case 4:
                shamanAspectUI();
                break;
        }
    }

    public void warriorAspectUI() {
        archetype1Aspect.setText("Fallen");
        archetype2Aspect.setText("Battle Monk");
        archetype3Aspect.setText("Paladin");

        archetype1Aspect.setBounds(600, 10, 60, 20);
        archetype2Aspect.setBounds(670, 10, 95, 20);
        archetype3Aspect.setBounds(775, 10, 70, 20);
        otherTypeAspect.setBounds(855, 10, 60, 20);
    }

    public void assassinAspectUI() {
        archetype1Aspect.setText("Shadestepper");
        archetype2Aspect.setText("Trickster");
        archetype3Aspect.setText("Acrobat");

        archetype1Aspect.setBounds(600, 10, 110, 20);
        archetype2Aspect.setBounds(720, 10, 80, 20);
        archetype3Aspect.setBounds(810, 10, 70, 20);
        otherTypeAspect.setBounds(890, 10, 60, 20);
    }

    public void mageAspectUI() {
        archetype1Aspect.setText("Riftwalker");
        archetype2Aspect.setText("Light Bender");
        archetype3Aspect.setText("Arcanist");

        archetype1Aspect.setBounds(600, 10, 85, 20);
        archetype2Aspect.setBounds(695, 10, 100, 20);
        archetype3Aspect.setBounds(805, 10, 75, 20);
        otherTypeAspect.setBounds(890, 10, 60, 20);
    }

    public void archerAspectUI() {
        archetype1Aspect.setText("Boltslinger");
        archetype2Aspect.setText("Trapper");
        archetype3Aspect.setText("Sharpshooter");

        archetype1Aspect.setBounds(600, 10, 90, 20);
        archetype2Aspect.setBounds(700, 10, 70, 20);
        archetype3Aspect.setBounds(780, 10, 105, 20);
        otherTypeAspect.setBounds(895, 10, 60, 20);
    }

    public void shamanAspectUI() {
        archetype1Aspect.setText("Summoner");
        archetype2Aspect.setText("Ritualist");
        archetype3Aspect.setText("Acolyte");

        archetype1Aspect.setBounds(600, 10, 90, 20);
        archetype2Aspect.setBounds(700, 10, 75, 20);
        archetype3Aspect.setBounds(785, 10, 70, 20);
        otherTypeAspect.setBounds(865, 10, 60, 20);
    }

    public void setVisibleItem(boolean visible) {
        itemTier.setBounds(320, 35, 170, 20);
        itemTier.setVisible(visible);
        bow.setVisible(visible);
        spear.setVisible(visible);
        wand.setVisible(visible);
        dagger.setVisible(visible);
        relik.setVisible(visible);
        helmet.setVisible(visible);
        chestplate.setVisible(visible);
        leggings.setVisible(visible);
        boots.setVisible(visible);
        ring.setVisible(visible);
        bracelet.setVisible(visible);
        necklace.setVisible(visible);
    }

    public void setVisibleIngredient(boolean visible) {
        tier.setBounds(320, 35, 170, 20);
        tier.setVisible(visible);
        armouring.setVisible(visible);
        tailoring.setVisible(visible);
        weaponsmithing.setVisible(visible);
        woodworking.setVisible(visible);
        jeweling.setVisible(visible);
        scribing.setVisible(visible);
        cooking.setVisible(visible);
        alchemism.setVisible(visible);
    }

    public void setVisibleOther(boolean visible) {
        itemTier.setBounds(320, 35, 100, 20);
        tier.setBounds(420, 35, 70, 20);
        itemTier.setVisible(visible);
        tier.setVisible(visible);
        tome.setVisible(visible);
        charm.setVisible(visible);
        tool.setVisible(visible);
        material.setVisible(visible);

        if (visible && tome.isSelected()) {
            armourTome.setVisible(true);
            guildTome.setVisible(true);
            weaponTome.setVisible(true);
            marathonTome.setVisible(true);
            lootrunTome.setVisible(true);
            expertiseTome.setVisible(true);
            mysticismTome.setVisible(true);
        }

        if (!visible) {
            armourTome.setVisible(false);
            guildTome.setVisible(false);
            weaponTome.setVisible(false);
            marathonTome.setVisible(false);
            lootrunTome.setVisible(false);
            expertiseTome.setVisible(false);
            mysticismTome.setVisible(false);
        }
    }

    public void setVisibleAspect(boolean visible) {
        itemTier.setBounds(320, 35, 170, 20);
        itemTier.setVisible(visible);
        aspectType.setVisible(visible);
        archetype1Aspect.setVisible(visible);
        archetype2Aspect.setVisible(visible);
        archetype3Aspect.setVisible(visible);
        otherTypeAspect.setVisible(visible);
    }

    public boolean canSearchItem() {
        if (Objects.equals(itemType.getItemAt(itemType.getSelectedIndex()), DataKeys.TYPE_EQUIPMENT)) {
            if (bow.isSelected() || spear.isSelected() || wand.isSelected() || dagger.isSelected() || relik.isSelected() || helmet.isSelected() || chestplate.isSelected() || leggings.isSelected() || boots.isSelected() || ring.isSelected() || bracelet.isSelected() || necklace.isSelected()) {
                boolean hasText = false;
                for (JComboBox<String> box : idBoxes_1) {
                    if (notEmpty(box)) hasText = true;
                }
                return hasText;
            }
        }
        return false;
    }

    public boolean canSearchIngredient() {
        if (Objects.equals(itemType.getItemAt(itemType.getSelectedIndex()), DataKeys.TYPE_INGREDIENT)) {
            if (armouring.isSelected() || tailoring.isSelected() || weaponsmithing.isSelected() || woodworking.isSelected() || jeweling.isSelected() || scribing.isSelected() || cooking.isSelected() || alchemism.isSelected()) {
                boolean hasText = false;
                for (JComboBox<String> box : idBoxes_1) {
                    if (notEmpty(box)) hasText = true;
                }
                return hasText;
            }
        }
        return false;
    }

    public boolean canSearchOtherItems() {
        if (Objects.equals(itemType.getItemAt(itemType.getSelectedIndex()), DataKeys.TYPE_OTHER_ITEM)) {
            if (tome.isSelected() || charm.isSelected() || tool.isSelected() || material.isSelected()) {
                boolean hasText = false;
                for (JComboBox<String> box : idBoxes_1) {
                    if (notEmpty(box)) hasText = true;
                }
                return hasText;
            }
        }
        return false;
    }

    public boolean canSearchAspects() {
        if (Objects.equals(itemType.getItemAt(itemType.getSelectedIndex()), DataKeys.TYPE_ASPECT)) {
            if (archetype1Aspect.isSelected() || archetype2Aspect.isSelected() || archetype3Aspect.isSelected() || otherTypeAspect.isSelected()) {
                boolean hasText = false;
                for (JComboBox<String> box : idBoxes_1) {
                    if (notEmpty(box)) hasText = true;
                }
                return hasText;
            }
        }
        return false;
    }

    public boolean notEmpty(JComboBox<String> box) {
        return !((JTextField) box.getEditor().getEditorComponent()).getText().isEmpty();
    }

    public void searchItems(List<ItemBase> modify) {
        long startTime = System.currentTimeMillis();

        filterItems(modify);

        long midTime = System.currentTimeMillis();

        for (int sil = searchedItems.size() - 1; sil >= 0; --sil) {
            sort(null, ItemType.ITEM, ApiDataManager.getManager().howToObtainItem);
        }

        setDisplaySize();

        displayTime.setText((midTime - startTime) + "ms, " + (System.currentTimeMillis() - midTime) + "ms");
    }

    public void filterItems(List<ItemBase> modify) {
        ApiDataManager api = ApiDataManager.getManager();
        searchedItems.clear();
        if (modify != null && !modify.isEmpty()) {
            searchedItems.addAll(modify);
        } else {
            //Search Item Type
            if (bow.isSelected() && spear.isSelected() && wand.isSelected() && dagger.isSelected() && relik.isSelected() && helmet.isSelected() && chestplate.isSelected() && leggings.isSelected() && boots.isSelected() && ring.isSelected() && bracelet.isSelected() && necklace.isSelected()) {
                searchedItems.addAll(api.wynnItems);
            } else {
                if (bow.isSelected()) searchedItems.addAll(api.bowData);
                if (spear.isSelected()) searchedItems.addAll(api.spearData);
                if (wand.isSelected()) searchedItems.addAll(api.wandData);
                if (dagger.isSelected()) searchedItems.addAll(api.daggerData);
                if (relik.isSelected()) searchedItems.addAll(api.relikData);
                if (helmet.isSelected()) searchedItems.addAll(api.helmetData);
                if (chestplate.isSelected()) searchedItems.addAll(api.chestplateData);
                if (leggings.isSelected()) searchedItems.addAll(api.leggingsData);
                if (boots.isSelected()) searchedItems.addAll(api.bootsData);
                if (ring.isSelected()) searchedItems.addAll(api.ringData);
                if (bracelet.isSelected()) searchedItems.addAll(api.braceletData);
                if (necklace.isSelected()) searchedItems.addAll(api.necklaceData);
            }
        }

        searchIDs(idBoxes_1, idMin_1, idMax_1, api.howToObtainItem, ItemType.ITEM);
        searchIDs(idBoxes_2, idMin_2, idMax_2, api.howToObtainItem, ItemType.ITEM);
        searchIDs(idBoxes_3, idMin_3, idMax_3, api.howToObtainItem, ItemType.ITEM);
        searchIDs(idBoxes_4, idMin_4, idMax_4, api.howToObtainItem, ItemType.ITEM);

        Identifications id_0 = IdBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(idBoxes_1, 0), Identifications.EMPTY);
        Identifications id_1 = IdBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(idBoxes_1, 1), Identifications.EMPTY);
        Identifications id_2 = IdBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(idBoxes_1, 2), Identifications.EMPTY);
        Identifications id_3 = IdBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(idBoxes_1, 3), Identifications.EMPTY);
        if (id_0.getItemName() == null && id_1.getItemName() == null && id_2.getItemName() == null && id_3.getItemName() == null) {
            removeAllSearchedItems();
        }

        searchFromTier(ItemType.ITEM);

        searchFromName();

        filterRange(idBoxes_1, idMin_1, idMax_1, api.howToObtainItem, ItemType.ITEM);
        filterRange(idBoxes_2, idMin_2, idMax_2, api.howToObtainItem, ItemType.ITEM);
        filterRange(idBoxes_3, idMin_3, idMax_3, api.howToObtainItem, ItemType.ITEM);
        filterRange(idBoxes_4, idMin_4, idMax_4, api.howToObtainItem, ItemType.ITEM);

        searchedItemCount.setText("Searched Item: " + searchedItems.size());
    }

    public void searchIngredient(List<Ingredient> modify) {
        long startTime = System.currentTimeMillis();

        filterIng(modify);

        long midTime = System.currentTimeMillis();

        for (int sil = searchedItems.size() - 1; sil >= 0; --sil) {
            sort(null, ItemType.INGREDIENT, ApiDataManager.getManager().howToObtainIng);
        }

        setDisplaySize();

        displayTime.setText((midTime - startTime) + "ms, " + (System.currentTimeMillis() - midTime) + "ms");
    }

    public void filterIng(List<Ingredient> modify) {
        ApiDataManager api = ApiDataManager.getManager();
        searchedItems.clear();

        if (modify != null && !modify.isEmpty()) {
            searchedItems.addAll(modify);
        } else {
            //Search Can Craft Type
            if (armouring.isSelected() && tailoring.isSelected() && weaponsmithing.isSelected() && woodworking.isSelected() && jeweling.isSelected() && scribing.isSelected() && cooking.isSelected() && alchemism.isSelected()) {
                searchedItems.addAll(api.wynnIngredients);
            } else {
                //Add Armouring
                if (armouring.isSelected()) searchedItems.addAll(api.armouringData);

                //Add Tailoring
                if (tailoring.isSelected()) {
                    if (!armouring.isSelected()) {
                        searchedItems.addAll(api.tailoringData);
                    } else {
                        for (Ingredient item : api.tailoringData) {
                            boolean add = true;
                            JsonArray array = item.getSkills();
                            for (int i = 0; array.size() > i; ++i) {
                                String s = array.get(i).getAsString();
                                if (s.contains(JsonValues.ARMOURING)) add = false;
                            }
                            if (add) searchedItems.add(item);
                        }
                    }
                }

                //Add Weaponsmithing
                if (weaponsmithing.isSelected()) {
                    if (!armouring.isSelected() && !tailoring.isSelected()) {
                        searchedItems.addAll(api.weaponsmithingData);
                    } else {
                        for (Ingredient item : api.weaponsmithingData) {
                            boolean add = true;
                            JsonArray array = item.getSkills();
                            for (int i = 0; array.size() > i; ++i) {
                                String s = array.get(i).getAsString();
                                if (hasIngType(s, JsonValues.ARMOURING) || hasIngType(s, JsonValues.TAILORING)) add = false;
                            }
                            if (add) searchedItems.add(item);
                        }
                    }
                }

                //Add Woodworking
                if (woodworking.isSelected()) {
                    if (!armouring.isSelected() && !tailoring.isSelected() && !weaponsmithing.isSelected()) {
                        searchedItems.addAll(api.woodworkingData);
                    } else {
                        for (Ingredient item : api.woodworkingData) {
                            boolean add = true;
                            JsonArray array = item.getSkills();
                            for (int i = 0; array.size() > i; ++i) {
                                String s = array.get(i).getAsString();
                                if (hasIngType(s, JsonValues.ARMOURING) || hasIngType(s, JsonValues.TAILORING) || hasIngType(s, JsonValues.WEAPONSMITHING))
                                    add = false;
                            }
                            if (add) searchedItems.add(item);
                        }
                    }
                }

                //Add Jeweling
                if (jeweling.isSelected()) {
                    if (!armouring.isSelected() && !tailoring.isSelected() && !weaponsmithing.isSelected() && !woodworking.isSelected()) {
                        searchedItems.addAll(api.jewelingData);
                    } else {
                        for (Ingredient item : api.jewelingData) {
                            boolean add = true;
                            JsonArray array = item.getSkills();
                            for (int i = 0; array.size() > i; ++i) {
                                String s = array.get(i).getAsString();
                                if (hasIngType(s, JsonValues.ARMOURING) || hasIngType(s, JsonValues.TAILORING) || hasIngType(s, JsonValues.WEAPONSMITHING) || hasIngType(s, JsonValues.WOODWORKING))
                                    add = false;
                            }
                            if (add) searchedItems.add(item);
                        }
                    }
                }

                //Add Scribing
                if (scribing.isSelected()) {
                    if (!armouring.isSelected() && !tailoring.isSelected() && !weaponsmithing.isSelected() && !woodworking.isSelected() && !jeweling.isSelected()) {
                        searchedItems.addAll(api.scribingData);
                    } else {
                        for (Ingredient item : api.scribingData) {
                            boolean add = true;
                            JsonArray array = item.getSkills();
                            for (int i = 0; array.size() > i; ++i) {
                                String s = array.get(i).getAsString();
                                if (hasIngType(s, JsonValues.ARMOURING) || hasIngType(s, JsonValues.TAILORING) || hasIngType(s, JsonValues.WEAPONSMITHING) || hasIngType(s, JsonValues.WOODWORKING) || hasIngType(s, JsonValues.JEWELING))
                                    add = false;
                            }
                            if (add) searchedItems.add(item);
                        }
                    }
                }

                //Add Cooking
                if (cooking.isSelected()) {
                    if (!armouring.isSelected() && !tailoring.isSelected() && !weaponsmithing.isSelected() && !woodworking.isSelected() && !jeweling.isSelected() && !scribing.isSelected()) {
                        searchedItems.addAll(api.cookingData);
                    } else {
                        for (Ingredient item : api.cookingData) {
                            boolean add = true;
                            JsonArray array = item.getSkills();
                            for (int i = 0; array.size() > i; ++i) {
                                String s = array.get(i).getAsString();
                                if (hasIngType(s, JsonValues.ARMOURING) || hasIngType(s, JsonValues.TAILORING) || hasIngType(s, JsonValues.WEAPONSMITHING) || hasIngType(s, JsonValues.WOODWORKING) || hasIngType(s, JsonValues.JEWELING) || hasIngType(s, JsonValues.SCRIBING))
                                    add = false;
                            }
                            if (add) searchedItems.add(item);
                        }
                    }
                }

                //Add Alchemism
                if (alchemism.isSelected()) {
                    if (!armouring.isSelected() && !tailoring.isSelected() && !weaponsmithing.isSelected() && !woodworking.isSelected() && !jeweling.isSelected() && !scribing.isSelected() && !cooking.isSelected()) {
                        searchedItems.addAll(api.alchemismData);
                    } else {
                        for (Ingredient item: api.alchemismData) {
                            boolean add = true;
                            JsonArray array = item.getSkills();
                            for (int i = 0; array.size() > i; ++i) {
                                String s = array.get(i).getAsString();
                                if (hasIngType(s, JsonValues.ARMOURING) || hasIngType(s, JsonValues.TAILORING) || hasIngType(s, JsonValues.WEAPONSMITHING) || hasIngType(s, JsonValues.WOODWORKING) || hasIngType(s, JsonValues.JEWELING) || hasIngType(s, JsonValues.SCRIBING) || hasIngType(s, JsonValues.COOKING))
                                    add = false;
                            }
                            if (add) searchedItems.add(item);
                        }
                    }
                }
            }
        }

        searchIDs(idBoxes_1, idMin_1, idMax_1, api.howToObtainIng, ItemType.INGREDIENT);
        searchIDs(idBoxes_2, idMin_2, idMax_2, api.howToObtainIng, ItemType.INGREDIENT);
        searchIDs(idBoxes_3, idMin_3, idMax_3, api.howToObtainIng, ItemType.INGREDIENT);
        searchIDs(idBoxes_4, idMin_4, idMax_4, api.howToObtainIng, ItemType.INGREDIENT);

        searchFromTier(ItemType.INGREDIENT);

        searchFromName();

        filterRange(idBoxes_1, idMin_1, idMax_1, api.howToObtainIng, ItemType.INGREDIENT);
        filterRange(idBoxes_2, idMin_2, idMax_2, api.howToObtainIng, ItemType.INGREDIENT);
        filterRange(idBoxes_3, idMin_3, idMax_3, api.howToObtainIng, ItemType.INGREDIENT);
        filterRange(idBoxes_4, idMin_4, idMax_4, api.howToObtainIng, ItemType.INGREDIENT);

        searchedItemCount.setText("Searched Item: " + searchedItems.size());
    }

    public void searchOtherItems(List<ItemBase> modify) {
        long startTime = System.currentTimeMillis();

        filterOther(modify);

        long midTime = System.currentTimeMillis();

        for (int sil = searchedItems.size() - 1; sil >= 0; --sil) {
            sort(null, ItemType.OTHER, ApiDataManager.getManager().howToObtainOthers);
        }

        setDisplaySize();

        displayTime.setText((midTime - startTime) + "ms, " + (System.currentTimeMillis() - midTime) + "ms");
    }

    public void filterOther(List<ItemBase> modify) {
        ApiDataManager api = ApiDataManager.getManager();
        searchedItems.clear();

        //Search Item Type
        if (modify != null && !modify.isEmpty()) {
            searchedItems.addAll(modify);
        } else {
            if (tome.isSelected() && charm.isSelected() && tool.isSelected() && material.isSelected()) {
                searchedItems.addAll(api.wynnOtherItems);
            } else {
                if (tome.isSelected()) {
                    if (armourTome.isSelected() && guildTome.isSelected() && weaponTome.isSelected() && marathonTome.isSelected() && lootrunTome.isSelected() && expertiseTome.isSelected() && mysticismTome.isSelected()) {
                        searchedItems.addAll(api.tomeData);
                    } else {
                        if (armourTome.isSelected()) searchedItems.addAll(api.armourTomeData);
                        if (guildTome.isSelected()) searchedItems.addAll(api.guildTomeData);
                        if (weaponTome.isSelected()) searchedItems.addAll(api.weaponTomeData);
                        if (marathonTome.isSelected()) searchedItems.addAll(api.marathonTomeData);
                        if (lootrunTome.isSelected()) searchedItems.addAll(api.lootrunTomeData);
                        if (expertiseTome.isSelected()) searchedItems.addAll(api.expertiseTomeData);
                        if (mysticismTome.isSelected()) searchedItems.addAll(api.mysticismTomeData);
                    }
                }
                if (charm.isSelected()) searchedItems.addAll(api.charmData);
                if (tool.isSelected()) searchedItems.addAll(api.toolData);
                if (material.isSelected()) searchedItems.addAll(api.materialData);
            }
        }

        searchIDs(idBoxes_1, idMin_1, idMax_1, api.howToObtainOthers, ItemType.OTHER);
        searchIDs(idBoxes_2, idMin_2, idMax_2, api.howToObtainOthers, ItemType.OTHER);
        searchIDs(idBoxes_3, idMin_3, idMax_3, api.howToObtainOthers, ItemType.OTHER);
        searchIDs(idBoxes_4, idMin_4, idMax_4, api.howToObtainOthers, ItemType.OTHER);

        Identifications id_0 = IdBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(idBoxes_1, 0), Identifications.EMPTY);
        Identifications id_1 = IdBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(idBoxes_1, 1), Identifications.EMPTY);
        Identifications id_2 = IdBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(idBoxes_1, 2), Identifications.EMPTY);
        Identifications id_3 = IdBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(idBoxes_1, 3), Identifications.EMPTY);
        if (id_0.getItemName() == null && id_1.getItemName() == null && id_2.getItemName() == null && id_3.getItemName() == null) {
            removeAllSearchedItems();
        }

        searchFromTier(ItemType.OTHER);

        searchFromName();

        filterRange(idBoxes_1, idMin_1, idMax_1, api.howToObtainOthers, ItemType.OTHER);
        filterRange(idBoxes_2, idMin_2, idMax_2, api.howToObtainOthers, ItemType.OTHER);
        filterRange(idBoxes_3, idMin_3, idMax_3, api.howToObtainOthers, ItemType.OTHER);
        filterRange(idBoxes_4, idMin_4, idMax_4, api.howToObtainOthers, ItemType.OTHER);

        searchedItemCount.setText("Searched Item: " + searchedItems.size());
    }

    // TODO check
    public void searchAspects() {
        long startTime = System.currentTimeMillis();

        filterAspects();

        sortAspects();

        setDisplaySize();

        displayTime.setText((System.currentTimeMillis() - startTime) + "ms");
    }

    // TODO check
    public void filterAspects() {
        ApiDataManager api = ApiDataManager.getManager();
        searchedItems.clear();

        switch (aspectType.getSelectedIndex()) {
            case 0:
                searchedItems.addAll(api.warriorAspectData);
                break;
            case 1:
                searchedItems.addAll(api.assassinAspectData);
                break;
            case 2:
                searchedItems.addAll(api.mageAspectData);
                break;
            case 3:
                searchedItems.addAll(api.archerAspectData);
                break;
            case 4:
                searchedItems.addAll(api.shamanAspectData);
                break;
        }

        searchFromTier(ItemType.ASPECT);

        searchFromName();

        searchedItemCount.setText("Searched Item: " + searchedItems.size());
    }

    public boolean hasIngType(String thisName, String needName) {
        JCheckBox needCheckBox = armouring;

        switch (needName) {
            case JsonValues.TAILORING:
                needCheckBox = tailoring;
                break;
            case JsonValues.WEAPONSMITHING:
                needCheckBox = weaponsmithing;
                break;
            case JsonValues.WOODWORKING:
                needCheckBox = woodworking;
                break;
            case JsonValues.JEWELING:
                needCheckBox = jeweling;
                break;
            case JsonValues.SCRIBING:
                needCheckBox = scribing;
                break;
            case JsonValues.COOKING:
                needCheckBox = cooking;
                break;
            case JsonValues.ALCHEMISM:
                needCheckBox = alchemism;
                break;
        }

        return thisName.contains(needName) && needCheckBox.isSelected();
    }

    public void searchIDs(List<JComboBox<String>> box, JTextField min, JTextField max, JsonObject howToObtain, ItemType type) {
        Identifications id_0 = IdBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, 0), Identifications.EMPTY);
        Identifications id_1 = IdBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, 1), Identifications.EMPTY);
        Identifications id_2 = IdBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, 2), Identifications.EMPTY);
        Identifications id_3 = IdBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, 3), Identifications.EMPTY);

        String idName_0 = id_0.getItemName();
        String idName_1 = id_1.getItemName();
        String idName_2 = id_2.getItemName();
        String idName_3 = id_3.getItemName();
        if (type == ItemType.INGREDIENT) {
            idName_0 = id_0.getIngName();
            idName_1 = id_1.getIngName();
            idName_2 = id_2.getIngName();
            idName_3 = id_3.getIngName();
        }

        if (!getComboBoxText(box, 0).isEmpty() || !getComboBoxText(box, 1).isEmpty() || !getComboBoxText(box, 2).isEmpty() || !getComboBoxText(box, 3).isEmpty()) {
            if (idName_0 != null || idName_1 != null || idName_2 != null || idName_3 != null) {
                for (int i = searchedItems.size() - 1; i >= 0; --i) {
                    ItemBase item = searchedItems.get(i);
                    if (!min.getText().isEmpty() || !max.getText().isEmpty()) { //ID Range Filter 0 ~ 0
                        int min_Int = Integer.MIN_VALUE;
                        int max_Int = Integer.MAX_VALUE;
                        if (!min.getText().isEmpty() && min.getText().matches("[+-]?\\d*(\\.\\d+)?")) min_Int = Integer.parseInt(min.getText());
                        if (!max.getText().isEmpty() && max.getText().matches("[+-]?\\d*(\\.\\d+)?")) max_Int = Integer.parseInt(max.getText());
                        if (min_Int == 0 || max_Int == 0) {
                            if (!item.haveId(id_0, howToObtain, min.getText(), max.getText()) && !item.haveId(id_1, howToObtain, min.getText(), max.getText()) && !item.haveId(id_2, howToObtain, min.getText(), max.getText()) && !item.haveId(id_3, howToObtain, min.getText(), max.getText())) {
                                continue;
                            }
                        }
                    }
                    boolean remove = true;
                    for (int num = 0; 4 > num; ++num) {
                        if (!getComboBoxText(box, num).isEmpty()) {
                            Identifications id = IdBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, num), Identifications.EMPTY);
                            if (item.haveId(id, howToObtain, min.getText(), max.getText())) {
                                remove = false;
                                break;
                            }
                        }
                    }
                    if (remove) {
                        searchedItems.remove(i);
                    }
                }
            }
        }
    }

    public String getComboBoxText(List<JComboBox<String>> boxes, int i) {
        return ((JTextField) boxes.get(i).getEditor().getEditorComponent()).getText();
    }

    public void setDisplaySize() {
        if (!itemDisplays.isEmpty()) {
            int si = itemDisplays.size() - 1;
            int iu = -1;
            int max = 0;
            for (int i = 0; si >= i && 4 > i; ++i) {
                int y = itemDisplays.get(si - i).getBounds().y + itemDisplays.get(si - i).getBounds().height;
                if (y > max) {
                    max = y;
                    iu = i;
                }
            }
            JPanel p = itemDisplays.get(si);

            if (iu > 0) p = itemDisplays.get(si - iu);

            if (10 + p.getBounds().y + p.getBounds().height > scrollPane.getHeight() - 3) {
                searched.setPreferredSize(new Dimension(scrollPane.getWidth() - 18, 10 + p.getBounds().y + p.getBounds().height));
            } else {
                searched.setPreferredSize(new Dimension(scrollPane.getWidth() - 18, scrollPane.getHeight() - 3));
            }
        }

        SwingUtilities.updateComponentTreeUI(searched);
    }

    public void searchFromTier(ItemType type) {
        if (!itemTier.getItemAt(itemTier.getSelectedIndex()).equals(DataKeys.RARITY_ALL) || !tier.getItemAt(tier.getSelectedIndex()).equals(DataKeys.RARITY_ALL)) {
            for (int i = searchedItems.size() - 1; i >= 0; --i) {
                ItemBase item = searchedItems.get(i);

                if (itemTier.getItemAt(itemTier.getSelectedIndex()).equals(DataKeys.RARITY_SET)) { // rarity set was discontinued but set bonus is available.
                    if (item.getJson().get(JsonKeys.SETS.getKey()) == null) {
                        searchedItems.remove(i);
                    }
                    continue;
                }

                String s = item.getIdString(Identifications.RARITY);
                if (type != ItemType.INGREDIENT) {
                    switch (itemTier.getItemAt(itemTier.getSelectedIndex())) {
                        case DataKeys.RARITY_NO_NORMAL:
                            if (s.equals(JsonValues.R_NORMAL)) {
                                searchedItems.remove(i);
                            }
                            break;
                        case DataKeys.RARITY_MYTHIC:
                            if (!s.equals(JsonValues.MYTHIC)) {
                                searchedItems.remove(i);
                            }
                            break;
                        case DataKeys.RARITY_FABLED:
                            if (!s.equals(JsonValues.FABLED)) {
                                searchedItems.remove(i);
                            }
                            break;
                        case DataKeys.RARITY_LEGENDARY:
                            if (!s.equals(JsonValues.LEGENDARY)) {
                                searchedItems.remove(i);
                            }
                            break;
                        case DataKeys.RARITY_RARE:
                            if (!s.equals(JsonValues.RARE)) {
                                searchedItems.remove(i);
                            }
                            break;
                        case DataKeys.RARITY_UNIQUE:
                            if (!s.equals(JsonValues.UNIQUE)) {
                                searchedItems.remove(i);
                            }
                            break;
                        case DataKeys.RARITY_NORMAL:
                            if (!s.equals(JsonValues.R_NORMAL)) {
                                searchedItems.remove(i);
                            }
                            break;
                    }
                } else {
                    switch (tier.getItemAt(tier.getSelectedIndex())) {
                        case DataKeys.RARITY_0STAR:
                            if (!s.equals(JsonValues.STAR_0)) {
                                searchedItems.remove(i);
                            }
                            break;
                        case DataKeys.RARITY_1STAR:
                            if (!s.equals(JsonValues.STAR_1)) {
                                searchedItems.remove(i);
                            }
                            break;
                        case DataKeys.RARITY_2STAR:
                            if (!s.equals(JsonValues.STAR_2)) {
                                searchedItems.remove(i);
                            }
                            break;
                        case DataKeys.RARITY_3STAR:
                            if (!s.equals(JsonValues.STAR_3)) {
                                searchedItems.remove(i);
                            }
                            break;
                    }
                }
            }
        }
    }

    public void searchFromName() {
        for (int i = searchedItems.size() - 1; i >= 0  ; --i) {
            ItemBase item = searchedItems.get(i);
            if (!item.getName().isEmpty()) {
                if (!item.getName().toLowerCase().contains(searchF.getText().toLowerCase())) {
                    searchedItems.remove(i);
                }
            } else {
                searchedItems.remove(i);
            }
        }
    }

    public void filterRange(List<JComboBox<String>> box, JTextField min, JTextField max, JsonObject howToObtain, ItemType type) {
        if (!min.getText().isEmpty() || !max.getText().isEmpty()) {
            int min_Int = Integer.MIN_VALUE;
            int max_Int = Integer.MAX_VALUE;
            if (!min.getText().isEmpty() && min.getText().matches("[+-]?\\d*(\\.\\d+)?")) min_Int = Integer.parseInt(min.getText());
            if (!max.getText().isEmpty() && max.getText().matches("[+-]?\\d*(\\.\\d+)?")) max_Int = Integer.parseInt(max.getText());
            if (min_Int != Integer.MIN_VALUE || max_Int != Integer.MAX_VALUE) {
                Identifications id_1 = IdBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, 0), Identifications.EMPTY);
                Identifications id_2 = IdBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, 1), Identifications.EMPTY);
                Identifications id_3 = IdBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, 2), Identifications.EMPTY);
                Identifications id_4 = IdBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, 3), Identifications.EMPTY);

                String idName_1 = id_1.getItemName();
                String idName_2 = id_2.getItemName();
                String idName_3 = id_3.getItemName();
                String idName_4 = id_4.getItemName();
                if (type == ItemType.INGREDIENT) {
                    idName_1 = id_1.getIngName();
                    idName_2 = id_2.getIngName();
                    idName_3 = id_3.getIngName();
                    idName_4 = id_4.getIngName();
                }

                if (idName_1 != null || idName_2 != null || idName_3 != null || idName_4 != null) {
                    if (id_1.getIDType() != DataType.STRING && id_2.getIDType() != DataType.STRING && id_3.getIDType() != DataType.STRING && id_4.getIDType() != DataType.STRING) {
                        for (int i = searchedItems.size() - 1; i >= 0; --i) {
                            ItemBase item = searchedItems.get(i);
                            float total_min = 0;
                            float total_max = 0;
                            for (int s = 0; 4 > s; ++s) {
                                Identifications id = IdBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, s), Identifications.EMPTY);
                                String idName = id.getItemName();
                                JsonKeys fieldPos = id.getItemFieldPos();
                                if (type == ItemType.INGREDIENT) {
                                    idName = id.getIngName();
                                    fieldPos = id.getIngFieldPos();
                                }

                                if (idName != null && fieldPos != null) {
                                    if (id.getIDType() != DataType.SUM) {
                                        if (id.getIDType() == DataType.STRING) {
                                            if (id == Identifications.ATTACK_SPEED) {
                                                float atkSpd = item.getAttackSpeed();
                                                total_min += atkSpd;
                                                total_min += atkSpd;
                                            } else if (item.haveIdValue(id, howToObtain, min.getText(), max.getText())) {
                                                total_min += 1;
                                                total_max += 1;
                                            }
                                        } else if (id.getIDType() == DataType.INT) {
                                            total_min += item.getIdValue(id, JsonKeys.MIN);
                                            total_max += item.getIdValue(id, JsonKeys.MAX);
                                        } else {
                                            System.out.println("Warning: Can't filtering this id. ID Name: " + id.getDisplayName());
                                        }
                                    } else {
                                        if (id.getSum().getSumIDs() != null) {
                                            for (int n = 0; id.getSum().getSumIDs().size() > n; n++) {
                                                total_min += item.getTotalSumFloat(id.getSum().getSumIDs().get(n), JsonKeys.MIN, min.getText(), max.getText());
                                                total_max += item.getTotalSumFloat(id.getSum().getSumIDs().get(n), JsonKeys.MAX, min.getText(), max.getText());
                                            }
                                        } else {
                                            total_min += item.getTotalSumFloat(id.getSum(), JsonKeys.MIN, min.getText(), max.getText());
                                            total_max += item.getTotalSumFloat(id.getSum(), JsonKeys.MAX, min.getText(), max.getText());
                                        }
                                    }
                                }
                            }
                            if (min_Int > total_min || max_Int < total_max) {
                                if (min_Int > total_max || max_Int < total_min) searchedItems.remove(i);
                            }
                        }
                    }
                }
            }
        }
    }

    public void sort(Set<String> sortList, ItemType type, JsonObject howToObtain) {
        int si = searchedItems.size() - 1;
        int iu = 0;
        float max = Integer.MIN_VALUE;
        JsonKeys bSortType = JsonKeys.MAX;

        if (sortType.getItemAt(sortType.getSelectedIndex()).equals(DataKeys.SORT_MIN)) {
            max = Integer.MAX_VALUE;
            bSortType = JsonKeys.MIN;
        }

        for (int i = 0; si >= i; ++i) {
            ItemBase item = searchedItems.get(si - i);
            float total = 0;

            for (int num = 0; 4 > num; ++num) {
                Identifications id = IdBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(idBoxes_1, num), Identifications.EMPTY);
                String idName = id.getItemName();
                JsonKeys fieldPos = id.getItemFieldPos();
                if (type == ItemType.INGREDIENT) {
                    idName = id.getIngName();
                    fieldPos = id.getIngFieldPos();
                }

                if (idName != null && fieldPos != null) {
                    if (id.getIDType() != DataType.SUM) {
                        if (id.getIDType() == DataType.INT) {
                            total += item.getIdValue(id, bSortType);
                        } else if (id == Identifications.ATTACK_SPEED) {
                            //Attack Speed
                            total += item.getAttackSpeed();
                        }
                    } else { // is SUM
                        if (id == Identifications.SUM_MELEE_APPROPRIATE || id == Identifications.SUM_SPELL_APPROPRIATE) {
                            JTextField minField = idMin_1;
                            JTextField maxField = idMax_1;
                            switch (num) {
                                case 1:
                                    minField = idMin_2;
                                    maxField = idMax_2;
                                    break;
                                case 2:
                                    minField = idMin_3;
                                    maxField = idMax_3;
                                    break;
                                case 3:
                                    minField = idMin_4;
                                    maxField = idMax_4;
                                    break;
                            }

                            total += item.getDamAppropriateSumFloat(id.getSum(), bSortType, minField.getText(), maxField.getText());
                        } else if (id.getSum().getSumIDs() != null) {
                            //SUM in SUM
                            for (int n = 0; id.getSum().getSumIDs().size() > n; ++n) {
                                total += item.getTotalSumFloat(id.getSum().getSumIDs().get(n), bSortType, null, null);
                            }
                        } else {
                            //Normal SUM
                            total += item.getTotalSumFloat(id.getSum(), bSortType, null, null);
                        }
                    }
                }
            }

            if (bSortType == JsonKeys.MIN) {
                if (total < max) {
                    max = total;
                    iu = si - i;
                }
            } else {
                if (total > max) {
                    max = total;
                    iu = si - i;
                }
            }
        }

        if (sortList != null) {
            sortList.add(searchedItems.get(iu).getName());
        } else {
            JPanel previous = null;
            JPanel above = null;
            if (!itemDisplays.isEmpty()) {
                previous = itemDisplays.get(itemDisplays.size() - 1);
            }
            if (itemDisplays.size() >= (int) Math.floor((scrollPane.getWidth() - 5) / 260d)) {
                above = itemDisplays.get(itemDisplays.size() - (int) Math.floor((scrollPane.getWidth() - 5) / 260d));
            }

            switch (type) {
                case ITEM -> itemDisplays.add(new EquipmentUi(searchedItems.get(iu), type, previous, above, scrollPane.getWidth(), max, false));
                case INGREDIENT -> itemDisplays.add(new IngredientUi(searchedItems.get(iu), type, previous, above, scrollPane.getWidth(), max, false));
                case OTHER -> itemDisplays.add(new OthersUi(searchedItems.get(iu), type, previous, above, scrollPane.getWidth(), max, false));
            }
            searched.add(itemDisplays.get(itemDisplays.size() - 1));
        }
        searchedItems.remove(iu);
    }

    public void sortAspects() {
        for (int i = searchedItems.size() - 1; i >= 0; --i) {
            JPanel previous = null;
            JPanel above = null;
            if (!itemDisplays.isEmpty()) {
                previous = itemDisplays.get(itemDisplays.size() - 1);
            }
            if (itemDisplays.size() >= (int) Math.floor((scrollPane.getWidth() - 5) / 260d)) {
                above = itemDisplays.get(itemDisplays.size() - (int) Math.floor((scrollPane.getWidth() - 5) / 260d));
            }
            itemDisplays.add(new AspectUi(searchedItems.get(i), ItemType.ASPECT, previous, above, scrollPane.getWidth(), 0, false));
            searched.add(itemDisplays.get(itemDisplays.size() - 1));

            searchedItems.remove(i);
        }
    }

    public void removeAllSearchedItems() {
        if (!searchedItems.isEmpty()) {
            searchedItems.clear();
        }
    }

    public List<JPanel> getItemDisplays() {
        return this.itemDisplays;
    }

    public JPanel getSearched() {
        return this.searched;
    }

    public JScrollPane getScrollPane() {
        return this.scrollPane;
    }

    public List<ItemBase> getSearchedItems() {
        return this.searchedItems;
    }

    // OLD CODE: Changes, TODO Update
    public void filterItemType(List<ItemBase> json) {
        List<String> l = new ArrayList<>();
        if (bow.isSelected()) l.add("bow");
        if (spear.isSelected()) l.add("spear");
        if (wand.isSelected()) l.add("wand");
        if (dagger.isSelected()) l.add("dagger");
        if (relik.isSelected()) l.add("relik");
        if (helmet.isSelected()) l.add("helmet");
        if (chestplate.isSelected()) l.add("chestplate");
        if (leggings.isSelected()) l.add("leggings");
        if (boots.isSelected()) l.add("boots");
        if (ring.isSelected()) l.add("ring");
        if (bracelet.isSelected()) l.add("bracelet");
        if (necklace.isSelected()) l.add("necklace");

        if (l.size() != 12) {
            for (int i = json.size() - 1; i >= 0; --i) {
                if (json.get(i).getJson().get("weaponType") != null) {
                    if (!l.contains(json.get(i).getJson().get("weaponType").getAsString())) {
                        json.remove(i);
                    }
                } else if (json.get(i).getJson().get("armourType") != null) {
                    if (!l.contains(json.get(i).getJson().get("armourType").getAsString())) {
                        json.remove(i);
                    }
                } else if (json.get(i).getJson().get("accessoryType") != null) {
                    if (!l.contains(json.get(i).getJson().get("accessoryType").getAsString())) {
                        json.remove(i);
                    }
                }
            }
        }
    }

    // OLD CODE: Changes, TODO Update
    public boolean filterItemType(JsonObject j) {
        List<String> l = new ArrayList<>();
        if (bow.isSelected()) l.add("bow");
        if (spear.isSelected()) l.add("spear");
        if (wand.isSelected()) l.add("wand");
        if (dagger.isSelected()) l.add("dagger");
        if (relik.isSelected()) l.add("relik");
        if (helmet.isSelected()) l.add("helmet");
        if (chestplate.isSelected()) l.add("chestplate");
        if (leggings.isSelected()) l.add("leggings");
        if (boots.isSelected()) l.add("boots");
        if (ring.isSelected()) l.add("ring");
        if (bracelet.isSelected()) l.add("bracelet");
        if (necklace.isSelected()) l.add("necklace");

        if (l.size() != 12) {
            if (j.get("weaponType") != null) {
                return l.contains(j.get("weaponType").getAsString());
            } else if (j.get("armourType") != null) {
                return l.contains(j.get("armourType").getAsString());
            } else if (j.get("accessoryType") != null) {
                return l.contains(j.get("accessoryType").getAsString());
            }
        }
        return true;
    }

    // OLD CODE: Changes, TODO Update
    public void filterIngType(List<ItemBase> json) {
        List<String> l = new ArrayList<>();
        if (armouring.isSelected()) l.add("armouring");
        if (tailoring.isSelected()) l.add("tailoring");
        if (weaponsmithing.isSelected()) l.add("weaponsmithing");
        if (woodworking.isSelected()) l.add("woodworking");
        if (jeweling.isSelected()) l.add("jeweling");
        if (scribing.isSelected()) l.add("scribing");
        if (cooking.isSelected()) l.add("cooking");
        if (alchemism.isSelected()) l.add("alchemism");

        if (l.size() != 8) {
            for (int i = json.size() - 1; i >= 0; --i) {
                Ingredient j = (Ingredient) json.get(i);
                boolean isSelected = false;
                if (!j.getSkills().isEmpty()) {
                    for (JsonElement je : j.getSkills()) {
                        if (l.contains(je.getAsString())) {
                            isSelected = true;
                            break;
                        }
                    }
                }
                if (!isSelected) {
                    json.remove(i);
                }
            }
        }
    }

    // OLD CODE: Changes, TODO Update
    public boolean filterIngType(JsonObject j) {
        List<String> l = new ArrayList<>();
        if (armouring.isSelected()) l.add("armouring");
        if (tailoring.isSelected()) l.add("tailoring");
        if (weaponsmithing.isSelected()) l.add("weaponsmithing");
        if (woodworking.isSelected()) l.add("woodworking");
        if (jeweling.isSelected()) l.add("jeweling");
        if (scribing.isSelected()) l.add("scribing");
        if (cooking.isSelected()) l.add("cooking");
        if (alchemism.isSelected()) l.add("alchemism");
        if (l.size() != 8) {
            if (j.get("requirements") != null && j.get("requirements").getAsJsonObject().get("skills") != null) {
                for (JsonElement je : j.get("requirements").getAsJsonObject().get("skills").getAsJsonArray()) {
                    if (l.contains(je.getAsString())) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    // OLD CODE: Changes, TODO Update
    public void filterOtherType(List<ItemBase> json) {
        List<String> l = new ArrayList<>();
        if (tome.isSelected()) l.add("tome");
        if (charm.isSelected()) l.add("charm");
        if (tool.isSelected()) l.add("tool");
        if (material.isSelected()) l.add("material");
        if (l.size() != 4) {
            for (int i = json.size() - 1; i >= 0; --i) {
                if (!json.get(i).getType().isEmpty()) {
                    if (!l.contains(json.get(i).getType())) {
                        json.remove(i);
                    }
                } else {
                    json.remove(i);
                    System.out.println("Error: Item Type Not Found | Item Name: " + json.get(i).getName());
                }
            }
        }
    }

    // OLD CODE: Changes, TODO Update
    public boolean filterOtherType(JsonObject j) {
        List<String> l = new ArrayList<>();
        if (tome.isSelected()) l.add("tome");
        if (charm.isSelected()) l.add("charm");
        if (tool.isSelected()) l.add("tool");
        if (material.isSelected()) l.add("material");
        if (l.size() != 4) {
            if (j.get("type") != null) {
                return l.contains(j.get("type").getAsString());
            } else {
                System.out.println("Error: Item Type Not Found | Item Name: " + j.get("name").getAsString());
                return false;
            }
        }
        return true;
    }

    public void setSearcherVisible(boolean visible) {
        itemType.setVisible(visible);
        switch (itemType.getItemAt(itemType.getSelectedIndex())) {
            case DataKeys.TYPE_EQUIPMENT:
                setVisibleItem(visible);
                break;
            case DataKeys.TYPE_INGREDIENT:
                setVisibleIngredient(visible);
                break;
            case DataKeys.TYPE_OTHER_ITEM:
                setVisibleOther(visible);
                break;
            case DataKeys.TYPE_ASPECT:
                setVisibleAspect(visible);
                break;
        }
        itemAPIConnect.setVisible(visible);
        name.setVisible(visible);
        searchB.setVisible(visible);
        searchF.setVisible(visible);
        scrollPane.setVisible(visible);
        searchedItemCount.setVisible(visible);
        updateSize.setVisible(visible);
        displayTime.setVisible(visible);
        updateAPI.setVisible(visible);
        for (int i = 0; 3 >= i; ++i) {
            idBoxes_1.get(i).setVisible(visible);
            idBoxes_2.get(i).setVisible(visible);
            idBoxes_3.get(i).setVisible(visible);
            idBoxes_4.get(i).setVisible(visible);
        }
        for (JLabel idText : idTexts) {
            idText.setVisible(visible);
        }
        idMin_1.setVisible(visible);
        idMin_2.setVisible(visible);
        idMin_3.setVisible(visible);
        idMin_4.setVisible(visible);
        idMax_1.setVisible(visible);
        idMax_2.setVisible(visible);
        idMax_3.setVisible(visible);
        idMax_4.setVisible(visible);
        sortType.setVisible(visible);

        if (visible) {
            setCrafterVisible(false);
            setBuilderVisible(false);
            setCustomVisible(false);
            setChangesVisible(false);
        }
    }

    public void setCrafterVisible(boolean visible) {
        crafterUI.setCrafterVisible(visible);

        if (visible) {
            setSearcherVisible(false);
            setBuilderVisible(false);
            setCustomVisible(false);
            setChangesVisible(false);
        }
    }

    public void setBuilderVisible(boolean visible) {
        builderUI.setBuilderVisible(visible);
        if (visible) {
            setSearcherVisible(false);
            setCrafterVisible(false);
            setCustomVisible(false);
            setChangesVisible(false);
        }
    }

    public void setCustomVisible(boolean visible) {
        customUI.setCustomVisible(visible);

        if (visible) {
            setSearcherVisible(false);
            setCrafterVisible(false);
            setBuilderVisible(false);
            setChangesVisible(false);
        }
    }

    public void setChangesVisible(boolean visible) {
        if (visible) {
            setSearcherVisible(true);
            setCrafterVisible(false);
            setBuilderVisible(false);
            setCustomVisible(false);
        }
        changesUI.setChangesVisible(visible);
    }

    public void setProcessTime(long startTime, long midTime) {
        displayTime.setText((midTime - startTime) + "ms, " + (System.currentTimeMillis() - midTime) + "ms");
    }
}
