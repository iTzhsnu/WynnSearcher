package com.github.iTzhsnu.WynnSearcher;

import com.github.iTzhsnu.WynnSearcher.general.*;
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
    public static final String VERSION = "4.0.0";

    //API
    private final List<JsonObject> wynnItems = new ArrayList<>();
    private final List<JsonObject> wynnIngredients = new ArrayList<>();
    private final List<JsonObject> wynnOtherItems = new ArrayList<>();
    private final JLabel itemAPIConnect = new JLabel("Item API Connecting...");

    //Item Type Json
    private final List<JsonObject> bowJson = new ArrayList<>();
    private final List<JsonObject> spearJson = new ArrayList<>();
    private final List<JsonObject> wandJson = new ArrayList<>();
    private final List<JsonObject> daggerJson = new ArrayList<>();
    private final List<JsonObject> relikJson = new ArrayList<>();
    private final List<JsonObject> helmetJson = new ArrayList<>();
    private final List<JsonObject> chestplateJson = new ArrayList<>();
    private final List<JsonObject> leggingsJson = new ArrayList<>();
    private final List<JsonObject> bootsJson = new ArrayList<>();
    private final List<JsonObject> ringJson = new ArrayList<>();
    private final List<JsonObject> braceletJson = new ArrayList<>();
    private final List<JsonObject> necklaceJson = new ArrayList<>();
    private static JsonObject setsJson = null;

    //Ingredient Type Json
    private final List<JsonObject> armouringJson = new ArrayList<>();
    private final List<JsonObject> tailoringJson = new ArrayList<>();
    private final List<JsonObject> weaponsmithingJson = new ArrayList<>();
    private final List<JsonObject> woodworkingJson = new ArrayList<>();
    private final List<JsonObject> jewelingJson = new ArrayList<>();
    private final List<JsonObject> scribingJson = new ArrayList<>();
    private final List<JsonObject> cookingJson = new ArrayList<>();
    private final List<JsonObject> alchemismJson = new ArrayList<>();

    //Other Type Json
    private final List<JsonObject> tomeJson = new ArrayList<>();
    private final List<JsonObject> charmJson = new ArrayList<>();
    private final List<JsonObject> toolJson = new ArrayList<>();
    private final List<JsonObject> materialJson = new ArrayList<>();
    private final List<JsonObject> armourTomeJson = new ArrayList<>();
    private final List<JsonObject> guildTomeJson = new ArrayList<>();
    private final List<JsonObject> weaponTomeJson = new ArrayList<>();
    private final List<JsonObject> marathonTomeJson = new ArrayList<>();
    private final List<JsonObject> lootrunTomeJson = new ArrayList<>();
    private final List<JsonObject> expertiseTomeJson = new ArrayList<>();
    private final List<JsonObject> mysticismTomeJson = new ArrayList<>();

    //Aspect Json
    private final List<JsonObject> aspects = new ArrayList<>();
    private final List<JsonObject> warriorAspectJson = new ArrayList<>();
    private final List<JsonObject> assassinAspectJson = new ArrayList<>();
    private final List<JsonObject> mageAspectJson = new ArrayList<>();
    private final List<JsonObject> archerAspectJson = new ArrayList<>();
    private final List<JsonObject> shamanAspectJson = new ArrayList<>();

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
    private final JTextField searchF = new JTextField();
    private final Container contentPane;
    private final JPanel searched = new JPanel();
    private final List<JPanel> itemDisplays = new ArrayList<>();
    private final List<JsonObject> searchedItems = new ArrayList<>();
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
    private final JTextField idMin_1 = new JTextField();
    private final JTextField idMin_2 = new JTextField();
    private final JTextField idMin_3 = new JTextField();
    private final JTextField idMin_4 = new JTextField();

    //ID Max Text
    private final JTextField idMax_1 = new JTextField();
    private final JTextField idMax_2 = new JTextField();
    private final JTextField idMax_3 = new JTextField();
    private final JTextField idMax_4 = new JTextField();

    //ID Text
    private final List<JLabel> idTexts = new ArrayList<>();

    //Other UIs
    private final CrafterUI crafterUI;
    private final BuilderUI builderUI;
    private final CustomUI customUI;
    private final ChangesUI changesUI;

    //How to Obtain
    private static JsonObject how_to_obtain_item = null;
    private static JsonObject how_to_obtain_ing = null;
    private static JsonObject how_to_obtain_other = null;


    public static boolean isReversedID(Identifications id) {
        return id == Identifications.RAW_1ST_SPELL_COST || id == Identifications.RAW_2ND_SPELL_COST || id == Identifications.RAW_3RD_SPELL_COST || id == Identifications.RAW_4TH_SPELL_COST || id == Identifications.PERCENT_1ST_SPELL_COST || id == Identifications.PERCENT_2ND_SPELL_COST || id == Identifications.PERCENT_3RD_SPELL_COST || id == Identifications.PERCENT_4TH_SPELL_COST;
    }

    public SearchUI() {

        GetAPI getAPI = new GetAPI();
        getAPI.loadArchiveV3API(wynnItems, wynnIngredients, wynnOtherItems, itemAPIConnect);
        List<JsonObject> wynnRecipes = new ArrayList<>();
        String recipeAPIConnect = getAPI.loadRecipeData(wynnRecipes);
        setsJson = getAPI.loadWynnSetsAPI();

        getAPI.loadWynnAspectAPI(JsonValues.WARRIOR, warriorAspectJson);
        getAPI.loadWynnAspectAPI(JsonValues.ASSASSIN, assassinAspectJson);
        getAPI.loadWynnAspectAPI(JsonValues.MAGE, mageAspectJson);
        getAPI.loadWynnAspectAPI(JsonValues.ARCHER, archerAspectJson);
        getAPI.loadWynnAspectAPI(JsonValues.SHAMAN, shamanAspectJson);
        aspects.addAll(warriorAspectJson);
        aspects.addAll(assassinAspectJson);
        aspects.addAll(mageAspectJson);
        aspects.addAll(archerAspectJson);
        aspects.addAll(shamanAspectJson);

        how_to_obtain_item = getAPI.getHowToObtainItem();
        how_to_obtain_ing = getAPI.getHowToObtainIng();
        how_to_obtain_other = getAPI.getHowToObtainOther();

        setItemJson();
        setIngredientJson();
        setOtherItemsJson();

        if (GetAPI.getUpdate()) {
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
        sortType.addItem("Sort: Max");
        sortType.addItem("Sort: Min");

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

        this.crafterUI = new CrafterUI(contentPane, wynnIngredients, wynnRecipes, recipeAPIConnect, itemAPIConnect);
        this.builderUI = new BuilderUI(contentPane, wynnItems, wynnIngredients, wynnOtherItems, aspects, wynnRecipes, itemAPIConnect, recipeAPIConnect);
        this.customUI = new CustomUI(contentPane);
        this.changesUI = new ChangesUI(this, wynnItems, wynnIngredients, wynnOtherItems);

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
            updateAPI();
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

    public void updateAPI() {
        if (!wynnItems.isEmpty()) wynnItems.clear();
        if (!wynnIngredients.isEmpty()) wynnIngredients.clear();
        if (!wynnOtherItems.isEmpty()) wynnOtherItems.clear();
        if (!bowJson.isEmpty()) bowJson.clear();
        if (!spearJson.isEmpty()) spearJson.clear();
        if (!wandJson.isEmpty()) wandJson.clear();
        if (!daggerJson.isEmpty()) daggerJson.clear();
        if (!relikJson.isEmpty()) relikJson.clear();
        if (!helmetJson.isEmpty()) helmetJson.clear();
        if (!chestplateJson.isEmpty()) chestplateJson.clear();
        if (!leggingsJson.isEmpty()) leggingsJson.clear();
        if (!bootsJson.isEmpty()) bootsJson.clear();
        if (!ringJson.isEmpty()) ringJson.clear();
        if (!braceletJson.isEmpty()) braceletJson.clear();
        if (!necklaceJson.isEmpty()) necklaceJson.clear();
        if (!armouringJson.isEmpty()) armouringJson.clear();
        if (!tailoringJson.isEmpty()) tailoringJson.clear();
        if (!weaponsmithingJson.isEmpty()) weaponsmithingJson.clear();
        if (!woodworkingJson.isEmpty()) woodworkingJson.clear();
        if (!jewelingJson.isEmpty()) jewelingJson.clear();
        if (!scribingJson.isEmpty()) scribingJson.clear();
        if (!cookingJson.isEmpty()) cookingJson.clear();
        if (!alchemismJson.isEmpty()) alchemismJson.clear();
        if (!tomeJson.isEmpty()) tomeJson.clear();
        if (!charmJson.isEmpty()) charmJson.clear();
        if (!toolJson.isEmpty()) toolJson.clear();
        if (!materialJson.isEmpty()) materialJson.clear();

        GetAPI api = new GetAPI();

        api.getWynnAPIV3_3(wynnItems, wynnIngredients, wynnOtherItems, itemAPIConnect);
        api.getWynnSetsAPI();

        api.setWynnAbilityTreeAPI(JsonValues.WARRIOR);
        api.setWynnAbilityTreeAPI(JsonValues.ASSASSIN);
        api.setWynnAbilityTreeAPI(JsonValues.MAGE);
        api.setWynnAbilityTreeAPI(JsonValues.ARCHER);
        api.setWynnAbilityTreeAPI(JsonValues.SHAMAN);

        api.getWynnAspectAPI(JsonValues.WARRIOR);
        api.getWynnAspectAPI(JsonValues.ASSASSIN);
        api.getWynnAspectAPI(JsonValues.MAGE);
        api.getWynnAspectAPI(JsonValues.ARCHER);
        api.getWynnAspectAPI(JsonValues.SHAMAN);

        setItemJson();
        setIngredientJson();
        setOtherItemsJson();
        setsJson = api.loadWynnSetsAPI();
        crafterUI.updateIngAPI();
        builderUI.updateAPIs();

        updateAPI.setVisible(true);
    }

    public void setIDBoxAndIDField(List<JComboBox<String>> boxes, JTextField min, JTextField max, int baseX, int baseY, int length, boolean need) {
        for (int i = 0; length > i; ++i) {
            //ID Box
            boxes.add(new JComboBox<>());
            boxes.get(i).setBounds(baseX + (224 * i), baseY, 200, 20);
            for (String s : IDBoxAdapter.DISPLAY_ID_LIST) {
                boxes.get(i).addItem(s);
            }
            boxes.get(i).setEditable(true);

            if (need && i > 0) {
                boxes.get(i).setSelectedIndex(-1);
            } else if (!need) {
                boxes.get(i).setSelectedIndex(-1);
            }

            boxes.get(i).getEditor().getEditorComponent().addKeyListener(new IDBoxAdapter(boxes.get(i)));

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
        tier.addItem("All");
        tier.addItem("0 Star");
        tier.addItem("1 Star");
        tier.addItem("2 Star");
        tier.addItem("3 Star");
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

    public void searchItems(List<JsonObject> modify) {
        long startTime = System.currentTimeMillis();

        filterItems(modify);

        long midTime = System.currentTimeMillis();

        for (int sil = searchedItems.size() - 1; sil >= 0; --sil) {
            sort(null, ItemType.ITEM, how_to_obtain_item);
        }

        setDisplaySize();

        displayTime.setText((midTime - startTime) + "ms, " + (System.currentTimeMillis() - midTime) + "ms");
    }

    public void filterItems(List<JsonObject> modify) {
        searchedItems.clear();
        if (modify != null && !modify.isEmpty()) {
            searchedItems.addAll(modify);
        } else {
            //Search Item Type
            if (bow.isSelected() && spear.isSelected() && wand.isSelected() && dagger.isSelected() && relik.isSelected() && helmet.isSelected() && chestplate.isSelected() && leggings.isSelected() && boots.isSelected() && ring.isSelected() && bracelet.isSelected() && necklace.isSelected()) {
                searchedItems.addAll(wynnItems);
            } else {
                if (bow.isSelected()) searchedItems.addAll(bowJson);
                if (spear.isSelected()) searchedItems.addAll(spearJson);
                if (wand.isSelected()) searchedItems.addAll(wandJson);
                if (dagger.isSelected()) searchedItems.addAll(daggerJson);
                if (relik.isSelected()) searchedItems.addAll(relikJson);
                if (helmet.isSelected()) searchedItems.addAll(helmetJson);
                if (chestplate.isSelected()) searchedItems.addAll(chestplateJson);
                if (leggings.isSelected()) searchedItems.addAll(leggingsJson);
                if (boots.isSelected()) searchedItems.addAll(bootsJson);
                if (ring.isSelected()) searchedItems.addAll(ringJson);
                if (bracelet.isSelected()) searchedItems.addAll(braceletJson);
                if (necklace.isSelected()) searchedItems.addAll(necklaceJson);
            }
        }

        searchIDs(idBoxes_1, idMin_1, idMax_1, how_to_obtain_item, ItemType.ITEM);
        searchIDs(idBoxes_2, idMin_2, idMax_2, how_to_obtain_item, ItemType.ITEM);
        searchIDs(idBoxes_3, idMin_3, idMax_3, how_to_obtain_item, ItemType.ITEM);
        searchIDs(idBoxes_4, idMin_4, idMax_4, how_to_obtain_item, ItemType.ITEM);

        Identifications id_0 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(idBoxes_1, 0), Identifications.EMPTY);
        Identifications id_1 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(idBoxes_1, 1), Identifications.EMPTY);
        Identifications id_2 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(idBoxes_1, 2), Identifications.EMPTY);
        Identifications id_3 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(idBoxes_1, 3), Identifications.EMPTY);
        if (id_0.getItemName() == null && id_1.getItemName() == null && id_2.getItemName() == null && id_3.getItemName() == null) {
            removeAllSearchedItems();
        }

        searchFromTier();

        searchFromName();

        filterRange(idBoxes_1, idMin_1, idMax_1, how_to_obtain_item, ItemType.ITEM);
        filterRange(idBoxes_2, idMin_2, idMax_2, how_to_obtain_item, ItemType.ITEM);
        filterRange(idBoxes_3, idMin_3, idMax_3, how_to_obtain_item, ItemType.ITEM);
        filterRange(idBoxes_4, idMin_4, idMax_4, how_to_obtain_item, ItemType.ITEM);

        searchedItemCount.setText("Searched Item: " + searchedItems.size());
    }

    public void searchIngredient(List<JsonObject> modify) {
        long startTime = System.currentTimeMillis();

        filterIng(modify);

        long midTime = System.currentTimeMillis();

        for (int sil = searchedItems.size() - 1; sil >= 0; --sil) {
            sort(null, ItemType.INGREDIENT, how_to_obtain_ing);
        }

        setDisplaySize();

        displayTime.setText((midTime - startTime) + "ms, " + (System.currentTimeMillis() - midTime) + "ms");
    }

    public void filterIng(List<JsonObject> modify) {
        searchedItems.clear();

        if (modify != null && !modify.isEmpty()) {
            searchedItems.addAll(modify);
        } else {
            //Search Can Craft Type
            if (armouring.isSelected() && tailoring.isSelected() && weaponsmithing.isSelected() && woodworking.isSelected() && jeweling.isSelected() && scribing.isSelected() && cooking.isSelected() && alchemism.isSelected()) {
                searchedItems.addAll(wynnIngredients);
            } else {
                //Add Armouring
                if (armouring.isSelected()) searchedItems.addAll(armouringJson);

                //Add Tailoring
                if (tailoring.isSelected()) {
                    if (!armouring.isSelected()) {
                        searchedItems.addAll(tailoringJson);
                    } else {
                        for (JsonObject json : tailoringJson) {
                            boolean add = true;
                            JsonArray array = json.get(JsonKeys.REQUIREMENTS.getKey()).getAsJsonObject().get(JsonKeys.SKILLS.getKey()).getAsJsonArray();
                            for (int i = 0; array.size() > i; ++i) {
                                String s = array.get(i).getAsString();
                                if (s.contains(JsonValues.ARMOURING)) add = false;
                            }
                            if (add) searchedItems.add(json);
                        }
                    }
                }

                //Add Weaponsmithing
                if (weaponsmithing.isSelected()) {
                    if (!armouring.isSelected() && !tailoring.isSelected()) {
                        searchedItems.addAll(weaponsmithingJson);
                    } else {
                        for (JsonObject json : weaponsmithingJson) {
                            boolean add = true;
                            JsonArray array = json.get(JsonKeys.REQUIREMENTS.getKey()).getAsJsonObject().get(JsonKeys.SKILLS.getKey()).getAsJsonArray();
                            for (int i = 0; array.size() > i; ++i) {
                                String s = array.get(i).getAsString();
                                if (hasIngType(s, JsonValues.ARMOURING) || hasIngType(s, JsonValues.TAILORING)) add = false;
                            }
                            if (add) searchedItems.add(json);
                        }
                    }
                }

                //Add Woodworking
                if (woodworking.isSelected()) {
                    if (!armouring.isSelected() && !tailoring.isSelected() && !weaponsmithing.isSelected()) {
                        searchedItems.addAll(woodworkingJson);
                    } else {
                        for (JsonObject json : woodworkingJson) {
                            boolean add = true;
                            JsonArray array = json.get(JsonKeys.REQUIREMENTS.getKey()).getAsJsonObject().get(JsonKeys.SKILLS.getKey()).getAsJsonArray();
                            for (int i = 0; array.size() > i; ++i) {
                                String s = array.get(i).getAsString();
                                if (hasIngType(s, JsonValues.ARMOURING) || hasIngType(s, JsonValues.TAILORING) || hasIngType(s, JsonValues.WEAPONSMITHING))
                                    add = false;
                            }
                            if (add) searchedItems.add(json);
                        }
                    }
                }

                //Add Jeweling
                if (jeweling.isSelected()) {
                    if (!armouring.isSelected() && !tailoring.isSelected() && !weaponsmithing.isSelected() && !woodworking.isSelected()) {
                        searchedItems.addAll(jewelingJson);
                    } else {
                        for (JsonObject json : jewelingJson) {
                            boolean add = true;
                            JsonArray array = json.get(JsonKeys.REQUIREMENTS.getKey()).getAsJsonObject().get(JsonKeys.SKILLS.getKey()).getAsJsonArray();
                            for (int i = 0; array.size() > i; ++i) {
                                String s = array.get(i).getAsString();
                                if (hasIngType(s, JsonValues.ARMOURING) || hasIngType(s, JsonValues.TAILORING) || hasIngType(s, JsonValues.WEAPONSMITHING) || hasIngType(s, JsonValues.WOODWORKING))
                                    add = false;
                            }
                            if (add) searchedItems.add(json);
                        }
                    }
                }

                //Add Scribing
                if (scribing.isSelected()) {
                    if (!armouring.isSelected() && !tailoring.isSelected() && !weaponsmithing.isSelected() && !woodworking.isSelected() && !jeweling.isSelected()) {
                        searchedItems.addAll(scribingJson);
                    } else {
                        for (JsonObject json : scribingJson) {
                            boolean add = true;
                            JsonArray array = json.get(JsonKeys.REQUIREMENTS.getKey()).getAsJsonObject().get(JsonKeys.SKILLS.getKey()).getAsJsonArray();
                            for (int i = 0; array.size() > i; ++i) {
                                String s = array.get(i).getAsString();
                                if (hasIngType(s, JsonValues.ARMOURING) || hasIngType(s, JsonValues.TAILORING) || hasIngType(s, JsonValues.WEAPONSMITHING) || hasIngType(s, JsonValues.WOODWORKING) || hasIngType(s, JsonValues.JEWELING))
                                    add = false;
                            }
                            if (add) searchedItems.add(json);
                        }
                    }
                }

                //Add Cooking
                if (cooking.isSelected()) {
                    if (!armouring.isSelected() && !tailoring.isSelected() && !weaponsmithing.isSelected() && !woodworking.isSelected() && !jeweling.isSelected() && !scribing.isSelected()) {
                        searchedItems.addAll(cookingJson);
                    } else {
                        for (JsonObject json : cookingJson) {
                            boolean add = true;
                            JsonArray array = json.get(JsonKeys.REQUIREMENTS.getKey()).getAsJsonObject().get(JsonKeys.SKILLS.getKey()).getAsJsonArray();
                            for (int i = 0; array.size() > i; ++i) {
                                String s = array.get(i).getAsString();
                                if (hasIngType(s, JsonValues.ARMOURING) || hasIngType(s, JsonValues.TAILORING) || hasIngType(s, JsonValues.WEAPONSMITHING) || hasIngType(s, JsonValues.WOODWORKING) || hasIngType(s, JsonValues.JEWELING) || hasIngType(s, JsonValues.SCRIBING))
                                    add = false;
                            }
                            if (add) searchedItems.add(json);
                        }
                    }
                }

                //Add Alchemism
                if (alchemism.isSelected()) {
                    if (!armouring.isSelected() && !tailoring.isSelected() && !weaponsmithing.isSelected() && !woodworking.isSelected() && !jeweling.isSelected() && !scribing.isSelected() && !cooking.isSelected()) {
                        searchedItems.addAll(alchemismJson);
                    } else {
                        for (JsonObject json : alchemismJson) {
                            boolean add = true;
                            JsonArray array = json.get(JsonKeys.REQUIREMENTS.getKey()).getAsJsonObject().get(JsonKeys.SKILLS.getKey()).getAsJsonArray();
                            for (int i = 0; array.size() > i; ++i) {
                                String s = array.get(i).getAsString();
                                if (hasIngType(s, JsonValues.ARMOURING) || hasIngType(s, JsonValues.TAILORING) || hasIngType(s, JsonValues.WEAPONSMITHING) || hasIngType(s, JsonValues.WOODWORKING) || hasIngType(s, JsonValues.JEWELING) || hasIngType(s, JsonValues.SCRIBING) || hasIngType(s, JsonValues.COOKING))
                                    add = false;
                            }
                            if (add) searchedItems.add(json);
                        }
                    }
                }
            }
        }

        searchIDs(idBoxes_1, idMin_1, idMax_1, how_to_obtain_ing, ItemType.INGREDIENT);
        searchIDs(idBoxes_2, idMin_2, idMax_2, how_to_obtain_ing, ItemType.INGREDIENT);
        searchIDs(idBoxes_3, idMin_3, idMax_3, how_to_obtain_ing, ItemType.INGREDIENT);
        searchIDs(idBoxes_4, idMin_4, idMax_4, how_to_obtain_ing, ItemType.INGREDIENT);

        searchFromTier();

        searchFromName();

        filterRange(idBoxes_1, idMin_1, idMax_1, how_to_obtain_ing, ItemType.INGREDIENT);
        filterRange(idBoxes_2, idMin_2, idMax_2, how_to_obtain_ing, ItemType.INGREDIENT);
        filterRange(idBoxes_3, idMin_3, idMax_3, how_to_obtain_ing, ItemType.INGREDIENT);
        filterRange(idBoxes_4, idMin_4, idMax_4, how_to_obtain_ing, ItemType.INGREDIENT);

        searchedItemCount.setText("Searched Item: " + searchedItems.size());
    }

    public void searchOtherItems(List<JsonObject> modify) {
        long startTime = System.currentTimeMillis();

        filterOther(modify);

        long midTime = System.currentTimeMillis();

        for (int sil = searchedItems.size() - 1; sil >= 0; --sil) {
            sort(null, ItemType.OTHER, how_to_obtain_other);
        }

        setDisplaySize();

        displayTime.setText((midTime - startTime) + "ms, " + (System.currentTimeMillis() - midTime) + "ms");
    }

    public void filterOther(List<JsonObject> modify) {
        searchedItems.clear();

        //Search Item Type
        if (modify != null && !modify.isEmpty()) {
            searchedItems.addAll(modify);
        } else {
            if (tome.isSelected() && charm.isSelected() && tool.isSelected() && material.isSelected()) {
                searchedItems.addAll(wynnOtherItems);
            } else {
                if (tome.isSelected()) {
                    if (armourTome.isSelected() && guildTome.isSelected() && weaponTome.isSelected() && marathonTome.isSelected() && lootrunTome.isSelected() && expertiseTome.isSelected() && mysticismTome.isSelected()) {
                        searchedItems.addAll(tomeJson);
                    } else {
                        if (armourTome.isSelected()) searchedItems.addAll(armourTomeJson);
                        if (guildTome.isSelected()) searchedItems.addAll(guildTomeJson);
                        if (weaponTome.isSelected()) searchedItems.addAll(weaponTomeJson);
                        if (marathonTome.isSelected()) searchedItems.addAll(marathonTomeJson);
                        if (lootrunTome.isSelected()) searchedItems.addAll(lootrunTomeJson);
                        if (expertiseTome.isSelected()) searchedItems.addAll(expertiseTomeJson);
                        if (mysticismTome.isSelected()) searchedItems.addAll(mysticismTomeJson);
                    }
                }
                if (charm.isSelected()) searchedItems.addAll(charmJson);
                if (tool.isSelected()) searchedItems.addAll(toolJson);
                if (material.isSelected()) searchedItems.addAll(materialJson);
            }
        }

        searchIDs(idBoxes_1, idMin_1, idMax_1, how_to_obtain_other, ItemType.OTHER);
        searchIDs(idBoxes_2, idMin_2, idMax_2, how_to_obtain_other, ItemType.OTHER);
        searchIDs(idBoxes_3, idMin_3, idMax_3, how_to_obtain_other, ItemType.OTHER);
        searchIDs(idBoxes_4, idMin_4, idMax_4, how_to_obtain_other, ItemType.OTHER);

        Identifications id_0 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(idBoxes_1, 0), Identifications.EMPTY);
        Identifications id_1 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(idBoxes_1, 1), Identifications.EMPTY);
        Identifications id_2 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(idBoxes_1, 2), Identifications.EMPTY);
        Identifications id_3 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(idBoxes_1, 3), Identifications.EMPTY);
        if (id_0.getItemName() == null && id_1.getItemName() == null && id_2.getItemName() == null && id_3.getItemName() == null) {
            removeAllSearchedItems();
        }

        searchFromTier();

        searchFromName();

        filterRange(idBoxes_1, idMin_1, idMax_1, how_to_obtain_other, ItemType.OTHER);
        filterRange(idBoxes_2, idMin_2, idMax_2, how_to_obtain_other, ItemType.OTHER);
        filterRange(idBoxes_3, idMin_3, idMax_3, how_to_obtain_other, ItemType.OTHER);
        filterRange(idBoxes_4, idMin_4, idMax_4, how_to_obtain_other, ItemType.OTHER);

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
        searchedItems.clear();

        switch (aspectType.getSelectedIndex()) {
            case 0:
                searchedItems.addAll(warriorAspectJson);
                break;
            case 1:
                searchedItems.addAll(assassinAspectJson);
                break;
            case 2:
                searchedItems.addAll(mageAspectJson);
                break;
            case 3:
                searchedItems.addAll(archerAspectJson);
                break;
            case 4:
                searchedItems.addAll(shamanAspectJson);
                break;
        }

        searchFromTier();

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

    public void setItemJson() {
        for (JsonObject item : wynnItems) {
            if (item.get(JsonKeys.SUBTYPE.getKey()) != null) {
                switch (item.get(JsonKeys.SUBTYPE.getKey()).getAsString()) {
                    case JsonValues.BOW:
                        bowJson.add(item);
                        break;
                    case JsonValues.SPEAR:
                        spearJson.add(item);
                        break;
                    case JsonValues.WAND:
                        wandJson.add(item);
                        break;
                    case JsonValues.DAGGER:
                        daggerJson.add(item);
                        break;
                    case JsonValues.RELIK:
                        relikJson.add(item);
                        break;
                    case JsonValues.HELMET:
                        helmetJson.add(item);
                        break;
                    case JsonValues.CHESTPLATE:
                        chestplateJson.add(item);
                        break;
                    case JsonValues.LEGGINGS:
                        leggingsJson.add(item);
                        break;
                    case JsonValues.BOOTS:
                        bootsJson.add(item);
                        break;
                    case JsonValues.RING:
                        ringJson.add(item);
                        break;
                    case JsonValues.BRACELET:
                        braceletJson.add(item);
                        break;
                    case JsonValues.NECKLACE:
                        necklaceJson.add(item);
                        break;
                }
            }

            //if (item.get("weaponType") != null) {
            //    switch (item.get("weaponType").getAsString()) {
            //        case "bow":
            //            bowJson.add(item);
            //            break;
            //        case "spear":
            //            spearJson.add(item);
            //            break;
            //        case "wand":
            //            wandJson.add(item);
            //            break;
            //        case "dagger":
            //            daggerJson.add(item);
            //            break;
            //        case "relik":
            //            relikJson.add(item);
            //            break;
            //    }
            //} else if (item.get("armourType") != null) {
            //    switch (item.get("armourType").getAsString()) {
            //        case "helmet":
            //            helmetJson.add(item);
            //            break;
            //        case "chestplate":
            //            chestplateJson.add(item);
            //            break;
            //        case "leggings":
            //            leggingsJson.add(item);
            //            break;
            //        case "boots":
            //            bootsJson.add(item);
            //            break;
            //    }
            //} else if (item.get("accessoryType") != null) {
            //    switch (item.get("accessoryType").getAsString()) {
            //        case "ring":
            //            ringJson.add(item);
            //            break;
            //        case "bracelet":
            //            braceletJson.add(item);
            //            break;
            //        case "necklace":
            //            necklaceJson.add(item);
            //            break;
            //    }
            //}
        }
    }

    public void setIngredientJson() {
        for (JsonObject ing : wynnIngredients) {
            for (int i = 0; ing.get(JsonKeys.REQUIREMENTS.getKey()).getAsJsonObject().get(JsonKeys.SKILLS.getKey()).getAsJsonArray().size() > i; ++i) {
                switch (ing.get(JsonKeys.REQUIREMENTS.getKey()).getAsJsonObject().get(JsonKeys.SKILLS.getKey()).getAsJsonArray().get(i).getAsString()) {
                    case JsonValues.ARMOURING:
                        armouringJson.add(ing);
                        break;
                    case JsonValues.TAILORING:
                        tailoringJson.add(ing);
                        break;
                    case JsonValues.WEAPONSMITHING:
                        weaponsmithingJson.add(ing);
                        break;
                    case JsonValues.WOODWORKING:
                        woodworkingJson.add(ing);
                        break;
                    case JsonValues.JEWELING:
                        jewelingJson.add(ing);
                        break;
                    case JsonValues.SCRIBING:
                        scribingJson.add(ing);
                        break;
                    case JsonValues.COOKING:
                        cookingJson.add(ing);
                        break;
                    case JsonValues.ALCHEMISM:
                        alchemismJson.add(ing);
                        break;
                }
            }
        }
    }

    public void setOtherItemsJson() {
        for (JsonObject j : wynnOtherItems) {
            switch (j.get(JsonKeys.TYPE.getKey()).getAsString()) {
                case JsonValues.TOME:
                    tomeJson.add(j);
                    switch (j.get(JsonKeys.SUBTYPE.getKey()).getAsString()) {
                        case JsonValues.GUILD_TOME: //x1 Slots
                            guildTomeJson.add(j);
                            break;
                        case JsonValues.MARATHON_TOME: //x2 Slots
                            marathonTomeJson.add(j);
                            break;
                        case JsonValues.EXPERTISE_TOME: //x2 Slots
                            expertiseTomeJson.add(j);
                            break;
                        case JsonValues.LOOTRUN_TOME: //x1 Slots
                            lootrunTomeJson.add(j);
                            break;
                        case JsonValues.MYSTICISM_TOME: //x2 Slots
                            mysticismTomeJson.add(j);
                            break;
                        case JsonValues.WEAPON_TOME: //x2 Slots
                            weaponTomeJson.add(j);
                            break;
                        case JsonValues.ARMOUR_TOME: //x4 Slots
                            armourTomeJson.add(j);
                            break;
                    }
                    break;
                case JsonValues.CHARM:
                    charmJson.add(j);
                    break;
                case JsonValues.TOOL:
                    toolJson.add(j);
                    break;
                case JsonValues.MATERIAL:
                    materialJson.add(j);
                    break;
            }
        }
    }

    public void searchIDs(List<JComboBox<String>> box, JTextField min, JTextField max, JsonObject how_to_obtain, ItemType type) {
        Identifications id_0 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, 0), Identifications.EMPTY);
        Identifications id_1 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, 1), Identifications.EMPTY);
        Identifications id_2 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, 2), Identifications.EMPTY);
        Identifications id_3 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, 3), Identifications.EMPTY);

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
                    JsonObject j = searchedItems.get(i);
                    if (!min.getText().isEmpty() || !max.getText().isEmpty()) { //ID Range Filter 0 ~ 0
                        int min_Int = Integer.MIN_VALUE;
                        int max_Int = Integer.MAX_VALUE;
                        if (!min.getText().isEmpty() && min.getText().matches("[+-]?\\d*(\\.\\d+)?")) min_Int = Integer.parseInt(min.getText());
                        if (!max.getText().isEmpty() && max.getText().matches("[+-]?\\d*(\\.\\d+)?")) max_Int = Integer.parseInt(max.getText());
                        if (min_Int == 0 || max_Int == 0) {
                            if (!hasID(id_0, j, how_to_obtain, min, max, type) && !hasID(id_1, j, how_to_obtain, min, max, type) && !hasID(id_2, j, how_to_obtain, min, max, type) && !hasID(id_3, j, how_to_obtain, min, max, type)) {
                                continue;
                            }
                        }
                    }
                    boolean remove = true;
                    for (int num = 0; 4 > num; ++num) {
                        if (!getComboBoxText(box, num).isEmpty()) {
                            Identifications id = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, num), Identifications.EMPTY);
                            if (hasID(id, j, how_to_obtain, min, max, type)) {
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

    public boolean hasID(Identifications id, JsonObject j, JsonObject how_to_obtain, JTextField min, JTextField max, ItemType type) {
        if (id.getIDType() != DataType.SUM) {
            return hasIDValue(j, id, how_to_obtain, min, max, type);
        } else {
            boolean need = false;
            boolean needAll = true;
            for (int n = 0; id.getSum().getIds().size() > n; ++n) {
                Identifications id2 = id.getSum().getIds().get(n);
                boolean has = hasIDValue(j, id2, how_to_obtain, min, max, type);
                if (has) {
                    need = true;
                } else {
                    needAll = false;
                }
            }

            if (id.getSum().isNeedAll() && needAll) {
                return true;
            } else {
                return (!id.getSum().isNeedAll() && need);
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

    public void searchFromTier() {
        if (!itemTier.getItemAt(itemTier.getSelectedIndex()).equals(DataKeys.RARITY_ALL) || !tier.getItemAt(tier.getSelectedIndex()).equals(DataKeys.RARITY_ALL)) {
            for (int i = searchedItems.size() - 1; i >= 0; --i) {
                JsonObject j = searchedItems.get(i);

                if (itemTier.getItemAt(itemTier.getSelectedIndex()).equals(DataKeys.RARITY_SET)) { // rarity set was discontinued but set bonus is available.
                    if (j.get(JsonKeys.SETS.getKey()) == null) {
                        searchedItems.remove(i);
                    }
                    continue;
                }

                if (j.get(Identifications.RARITY.getItemName()) != null) {
                    String s = j.get(Identifications.RARITY.getItemName()).getAsString();
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
                        //case "Set": // Set Rarity was discontinued.
                        //    if (!s.equals("set")) {
                        //        searchedItems.remove(i);
                        //    }
                        //    break;
                        case DataKeys.RARITY_NORMAL:
                            if (!s.equals(JsonValues.R_NORMAL)) {
                                searchedItems.remove(i);
                            }
                            break;
                    }
                } else if (j.get(Identifications.RARITY.getIngName()) != null) {
                    String t = j.get(Identifications.RARITY.getIngName()).getAsString();
                    switch (tier.getItemAt(tier.getSelectedIndex())) {
                        case DataKeys.RARITY_0STAR:
                            if (!t.equals(JsonValues.STAR_0)) {
                                searchedItems.remove(i);
                            }
                            break;
                        case DataKeys.RARITY_1STAR:
                            if (!t.equals(JsonValues.STAR_1)) {
                                searchedItems.remove(i);
                            }
                            break;
                        case DataKeys.RARITY_2STAR:
                            if (!t.equals(JsonValues.STAR_2)) {
                                searchedItems.remove(i);
                            }
                            break;
                        case DataKeys.RARITY_3STAR:
                            if (!t.equals(JsonValues.STAR_3)) {
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
            JsonObject json = searchedItems.get(i);
            if (json.get(JsonKeys.NAME.getKey()) != null) {
                if (!json.get(JsonKeys.NAME.getKey()).getAsString().toLowerCase().contains(searchF.getText().toLowerCase())) {
                    searchedItems.remove(i);
                }
            } else {
                searchedItems.remove(i);
            }
        }
    }

    public void filterRange(List<JComboBox<String>> box, JTextField min, JTextField max, JsonObject how_to_obtain, ItemType type) {
        if (!min.getText().isEmpty() || !max.getText().isEmpty()) {
            int min_Int = Integer.MIN_VALUE;
            int max_Int = Integer.MAX_VALUE;
            if (!min.getText().isEmpty() && min.getText().matches("[+-]?\\d*(\\.\\d+)?")) min_Int = Integer.parseInt(min.getText());
            if (!max.getText().isEmpty() && max.getText().matches("[+-]?\\d*(\\.\\d+)?")) max_Int = Integer.parseInt(max.getText());
            if (min_Int != Integer.MIN_VALUE || max_Int != Integer.MAX_VALUE) {
                Identifications id_1 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, 0), Identifications.EMPTY);
                Identifications id_2 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, 1), Identifications.EMPTY);
                Identifications id_3 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, 2), Identifications.EMPTY);
                Identifications id_4 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, 3), Identifications.EMPTY);

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
                            JsonObject j = searchedItems.get(i);
                            float total_min = 0;
                            float total_max = 0;
                            for (int s = 0; 4 > s; ++s) {
                                Identifications id = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, s), Identifications.EMPTY);
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
                                                float atkSpd = getAttackSpeed(j);
                                                total_min += atkSpd;
                                                total_min += atkSpd;
                                            } else if (hasIDValue(j, id, how_to_obtain, min, max, type)) {
                                                total_min += 1;
                                                total_max += 1;
                                            }
                                        } else if (id.getIDType() == DataType.INT) {
                                            total_min += getIDValue(j, id, JsonKeys.MIN, type);
                                            total_max += getIDValue(j, id, JsonKeys.MAX, type);
                                        } else {
                                            System.out.println("Warning: Can't filtering this id. ID Name: " + id.getDisplayName());
                                        }
                                    } else {
                                        if (id.getSum().getSumIDs() != null) {
                                            for (int n = 0; id.getSum().getSumIDs().size() > n; n++) {
                                                total_min += getTotalSumFloat(j, id.getSum().getSumIDs().get(n), JsonKeys.MIN, type);
                                                total_max += getTotalSumFloat(j, id.getSum().getSumIDs().get(n), JsonKeys.MAX, type);
                                            }
                                        } else {
                                            total_min += getTotalSumFloat(j, id.getSum(), JsonKeys.MIN, type);
                                            total_max += getTotalSumFloat(j, id.getSum(), JsonKeys.MAX, type);
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

    public void sort(Set<String> sortList, ItemType type, JsonObject how_to_obtain) {
        int si = searchedItems.size() - 1;
        int iu = 0;
        float max = Integer.MIN_VALUE;
        JsonKeys bSortType = JsonKeys.MAX;

        if (sortType.getItemAt(sortType.getSelectedIndex()).equals(DataKeys.SORT_MIN)) {
            max = Integer.MAX_VALUE;
            bSortType = JsonKeys.MIN;
        }

        for (int i = 0; si >= i; ++i) {
            JsonObject j = searchedItems.get(si - i);
            float total = 0;

            for (int num = 0; 4 > num; ++num) {
                Identifications id = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(idBoxes_1, num), Identifications.EMPTY);
                String idName = id.getItemName();
                JsonKeys fieldPos = id.getItemFieldPos();
                if (type == ItemType.INGREDIENT) {
                    idName = id.getIngName();
                    fieldPos = id.getIngFieldPos();
                }

                if (idName != null && fieldPos != null) {
                    if (id.getIDType() != DataType.SUM) {
                        if (id.getIDType() == DataType.INT) {
                            total += getIDValue(j, id, bSortType, type);
                        } else if (id == Identifications.ATTACK_SPEED) {
                            //Attack Speed
                            total += getAttackSpeed(j);
                        }
                    } else { // is SUM
                        if (id.getSum().getSumIDs() != null) {
                            //SUM in SUM
                            for (int n = 0; id.getSum().getSumIDs().size() > n; ++n) {
                                total += getTotalSumFloat(j, id.getSum().getSumIDs().get(n), bSortType, type);
                            }
                        } else {
                            //Normal SUM
                            total += getTotalSumFloat(j, id.getSum(), bSortType, type);
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
            sortList.add(searchedItems.get(iu).get("name").getAsString());
        } else {
            JPanel previous = null;
            JPanel above = null;
            if (!itemDisplays.isEmpty()) {
                previous = itemDisplays.get(itemDisplays.size() - 1);
            }
            if (itemDisplays.size() >= (int) Math.floor((scrollPane.getWidth() - 5) / 260d)) {
                above = itemDisplays.get(itemDisplays.size() - (int) Math.floor((scrollPane.getWidth() - 5) / 260d));
            }
            itemDisplays.add(new ItemUITemplate(searchedItems.get(iu), type, previous, above, scrollPane.getWidth(), max, false, how_to_obtain));
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
            itemDisplays.add(new ItemUITemplate(searchedItems.get(i), ItemType.ASPECT, previous, above, scrollPane.getWidth(), 0, false, null));
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

    public List<JsonObject> getSearchedItems() {
        return this.searchedItems;
    }

    public void filterItemType(List<JsonObject> json) {
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
                if (json.get(i).get("weaponType") != null) {
                    if (!l.contains(json.get(i).get("weaponType").getAsString())) {
                        json.remove(i);
                    }
                } else if (json.get(i).get("armourType") != null) {
                    if (!l.contains(json.get(i).get("armourType").getAsString())) {
                        json.remove(i);
                    }
                } else if (json.get(i).get("accessoryType") != null) {
                    if (!l.contains(json.get(i).get("accessoryType").getAsString())) {
                        json.remove(i);
                    }
                }
            }
        }
    }

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

    public void filterIngType(List<JsonObject> json) {
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
                JsonObject j = json.get(i);
                boolean isSelected = false;
                if (j.get("requirements") != null && j.get("requirements").getAsJsonObject().get("skills") != null) {
                    for (JsonElement je : j.get("requirements").getAsJsonObject().get("skills").getAsJsonArray()) {
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

    public void filterOtherType(List<JsonObject> json) {
        List<String> l = new ArrayList<>();
        if (tome.isSelected()) l.add("tome");
        if (charm.isSelected()) l.add("charm");
        if (tool.isSelected()) l.add("tool");
        if (material.isSelected()) l.add("material");
        if (l.size() != 4) {
            for (int i = json.size() - 1; i >= 0; --i) {
                if (json.get(i).get("type") != null) {
                    if (!l.contains(json.get(i).get("type").getAsString())) {
                        json.remove(i);
                    }
                } else {
                    json.remove(i);
                    System.out.println("Error: Item Type Not Found | Item Name: " + json.get(i).get("name").getAsString());
                }
            }
        }
    }

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

    public static int getIDValue(JsonObject j, Identifications id, JsonKeys sortType, ItemType type) {
        String idName = id.getItemName();
        JsonKeys fieldPos = id.getItemFieldPos();
        if (type == ItemType.INGREDIENT) {
            idName = id.getIngName();
            fieldPos = id.getIngFieldPos();
        }

        if (idName != null && fieldPos != null && id.getIDType() == DataType.INT) {
            if (fieldPos == JsonKeys.NOTHING) {
                return j.get(idName).getAsInt();
            } else {
                if (j.get(fieldPos.getKey()) != null && j.get(fieldPos.getKey()).getAsJsonObject().get(idName) != null) {
                    if (!j.get(fieldPos.getKey()).getAsJsonObject().get(idName).isJsonObject()) {
                        return j.get(fieldPos.getKey()).getAsJsonObject().get(idName).getAsInt(); // Not a Json Object
                    } else if (j.get(JsonKeys.IDENTIFIED.getKey()) != null && j.get(JsonKeys.IDENTIFIED.getKey()).getAsBoolean() && id.isItemVariable()) {
                        if (j.get(fieldPos.getKey()).getAsJsonObject().get(idName).getAsJsonObject().get(JsonKeys.RAW.getKey()) != null) {
                            return j.get(fieldPos.getKey()).getAsJsonObject().get(idName).getAsJsonObject().get(JsonKeys.RAW.getKey()).getAsInt();
                            //return getBaseID(j.get(fieldPos.getKey()).getAsJsonObject().get(idName).getAsJsonObject().get(JsonKeys.RAW.getKey()).getAsInt()); // this old code why used?
                        } else {
                            String minOrMax = JsonKeys.MAX.getKey();
                            if (isReversedID(id)) {
                                if (j.get(fieldPos.getKey()).getAsJsonObject().get(idName).getAsJsonObject().get(JsonKeys.MAX.getKey()).getAsInt() > 0) minOrMax = JsonKeys.MIN.getKey();
                            } else {
                                if (j.get(fieldPos.getKey()).getAsJsonObject().get(idName).getAsJsonObject().get(JsonKeys.MAX.getKey()).getAsInt() < 0) minOrMax = JsonKeys.MIN.getKey();
                            }
                            return getBaseID(j.get(fieldPos.getKey()).getAsJsonObject().get(idName).getAsJsonObject().get(minOrMax).getAsInt());
                        }
                    } else if (id.isItemVariable()) { //Item ID Variable
                        if (j.get(fieldPos.getKey()).getAsJsonObject().get(idName).getAsJsonObject().get(sortType.getKey()) != null) {
                            return j.get(fieldPos.getKey()).getAsJsonObject().get(idName).getAsJsonObject().get(sortType.getKey()).getAsInt();
                        } else {
                            System.out.println("Warning: Variable ID Value get failed.");
                        }
                    }
                }
            }
        }
        return 0;
    }

    public static boolean hasIDValue(JsonObject j, Identifications id, JsonObject how_to_obtain, JTextField min, JTextField max, ItemType type) {
        if (type == ItemType.INGREDIENT) {
            return hasIngIDValue(j, id, id.getIngName(), id.getIngFieldPos(), how_to_obtain);
        } else {
            return hasItemIDValue(j, id, id.getItemName(), id.getItemFieldPos(), how_to_obtain, min, max);
        }
    }

    public static boolean hasIntIDValue(JsonObject j, Identifications id, String idName, JsonKeys fieldPos) {
        if (id.getIDType() == DataType.INT) {
            if (fieldPos == JsonKeys.NOTHING) {
                return j.get(idName) != null;
            } else {
                if (j.get(fieldPos.getKey()) != null) return j.get(fieldPos.getKey()).getAsJsonObject().get(idName) != null;
            }
        }
        return false;
    }

    public static boolean hasItemIDValue(JsonObject j, Identifications id, String idName, JsonKeys fieldPos, JsonObject how_to_obtain, JTextField min, JTextField max) {
        if (idName != null) {
            if (id.getIDType() == DataType.INT) {
                return hasIntIDValue(j, id, idName, fieldPos);
            } else if (id.getIDType() == DataType.STRING) {
                // Check has ID
                if (fieldPos != null) {
                    if (fieldPos == JsonKeys.NOTHING) {
                        if (j.get(idName) == null) return false;
                    } else if (fieldPos == JsonKeys.OTHERS) {
                        return false;
                    } else {
                        if (j.get(fieldPos.getKey()) != null) {
                            if (j.get(fieldPos.getKey()).getAsJsonObject().get(idName) == null) return false;
                        } else {
                            return false;
                        }
                    }
                } else {
                    return false;
                }

                // Check match ID or Filtering
                if (id.getItemName().equals(Identifications.DROP_TYPE.getItemName())) { // If Drop Type
                    if (j.get(JsonKeys.NAME.getKey()) != null) {
                        String itemName = j.get(JsonKeys.NAME.getKey()).getAsString();
                        if (ItemUITemplate.haveManualDrop(how_to_obtain, itemName) > 0 && how_to_obtain.get(id.getDisplayName()) != null) { // Manual Drop Type Check
                            if (how_to_obtain.get(id.getDisplayName()).isJsonArray()) {
                                for (JsonElement je : how_to_obtain.get(id.getDisplayName()).getAsJsonArray()) {
                                    if (je.getAsString().equals(itemName)) {
                                        return true;
                                    }
                                }
                            } else if (how_to_obtain.get(id.getDisplayName()).isJsonObject()) {
                                if (how_to_obtain.get(id.getDisplayName()).getAsJsonObject().get(itemName) != null) {
                                    return true;
                                }
                            }
                        }

                        if (j.get(JsonKeys.DROP_META.getKey()) != null) {
                            if (j.get(JsonKeys.DROP_META.getKey()).getAsJsonObject().get(JsonKeys.TYPE.getKey()).isJsonArray()) {
                                for (JsonElement je : j.get(JsonKeys.DROP_META.getKey()).getAsJsonObject().get(JsonKeys.TYPE.getKey()).getAsJsonArray()) {
                                    return id.getDisplayName().equals(je.getAsString());
                                }
                            } else {
                                return id.getDisplayName().equals(j.get(JsonKeys.DROP_META.getKey()).getAsJsonObject().get(JsonKeys.TYPE.getKey()).getAsString());
                            }
                        }

                        if (j.get(JsonKeys.DROP_RESTRICTION.getKey()) != null) {
                            if (id == Identifications.DROP_TYPE_UNKNOWN) {
                                if (ItemUITemplate.haveManualDrop(how_to_obtain, itemName) > 0) return false;
                            }
                            return id.getDisplayName().equals(j.get(JsonKeys.DROP_RESTRICTION.getKey()).getAsString());
                        }
                    }
                } else if (id == Identifications.MAJOR_IDS) {
                    if (!min.getText().isEmpty() || !max.getText().isEmpty()) {
                        String s = max.getText();
                        if (!min.getText().isEmpty()) s = min.getText();
                        //if (j.get(Identifications.MAJOR_IDS.getItemName()).getAsJsonObject().get("name").getAsString().toLowerCase().contains(s.toLowerCase())) remove = false;
                        for (Map.Entry<String, JsonElement> entry : j.get(Identifications.MAJOR_IDS.getItemName()).getAsJsonObject().entrySet()) {
                            if (entry.getKey().toLowerCase().contains(s.toLowerCase())) {
                                return true;
                            }
                        }
                    } else { //has No Text (Major ID)
                        return true;
                    }
                } else if (id == Identifications.ATTACK_SPEED) {
                    if (!min.getText().isEmpty() || !max.getText().isEmpty()) {
                        String s = max.getText();
                        if (!min.getText().isEmpty()) s = min.getText();

                        return j.get(idName).getAsString().toLowerCase().contains(s.toLowerCase());
                    } else { // has No Text (Attack Speed)
                        return true;
                    }
                } else if (id == Identifications.SET) {
                    if (!min.getText().isEmpty() || !max.getText().isEmpty()) {
                        String s = max.getText();
                        if (!min.getText().isEmpty()) s = min.getText();

                        return j.get(idName).getAsString().toLowerCase().contains(s.toLowerCase()); // Warning: this is string array
                    } else { // has No Text (SET)
                        return true;
                    }
                } else {
                    return true;
                }
            } else {
                System.out.println("Warning: Can't check this ID");
            }
        }
        return false;
    }

    public static boolean hasIngIDValue(JsonObject j, Identifications id, String idName, JsonKeys fieldPos, JsonObject how_to_obtain) {
        if (idName != null) {
            if (id.getIDType() == DataType.INT) {
                return hasIntIDValue(j, id, idName, fieldPos);
            } else if (id.getIDType() == DataType.STRING) {
                // Check has ID
                if (fieldPos != null) {
                    if (fieldPos == JsonKeys.NOTHING) {
                        if (j.get(idName) == null) return false;
                    } else if (fieldPos == JsonKeys.OTHERS) {
                        return false;
                    } else {
                        if (j.get(fieldPos.getKey()) != null) {
                            if (j.get(fieldPos.getKey()).getAsJsonObject().get(idName) == null) return false;
                        } else {
                            return false;
                        }
                    }
                } else {
                    return false;
                }

                // Check match ID or filtering
                if (id.getIngName().equals(Identifications.DROP_TYPE.getIngName())) {
                    switch (id) {
                        case DROP_TYPE_UNKNOWN: {
                            boolean isObtainable = false;
                            for (JsonElement je : j.get(JsonKeys.DROPPED_BY.getKey()).getAsJsonArray()) {
                                if (je.getAsJsonObject().get(JsonKeys.NAME.getKey()) != null && !je.getAsJsonObject().get(JsonKeys.NAME.getKey()).getAsString().equals(JsonValues.DUMMY)) {
                                    isObtainable = true;
                                    break;
                                }
                            }
                            int haveItem = ItemUITemplate.haveManualDrop(how_to_obtain, j.get(JsonKeys.NAME.getKey()).getAsString());
                            if (haveItem > 0 && haveItem != 1) isObtainable = true;

                            return !isObtainable;
                        }
                        case DROP_TYPE_SPECIFIC_DROP: {
                            for (JsonElement je : j.get(JsonKeys.DROPPED_BY.getKey()).getAsJsonArray()) {
                                if (je.getAsJsonObject().get(JsonKeys.NAME.getKey()) != null && !je.getAsJsonObject().get(JsonKeys.NAME.getKey()).getAsString().equals(JsonValues.DUMMY)) {
                                    return true;
                                }
                            }
                            return (how_to_obtain.get(JsonValues.SPECIFIC) != null && how_to_obtain.get(JsonValues.SPECIFIC).getAsJsonObject().get(j.get(JsonKeys.NAME.getKey()).getAsString()) != null);
                        }
                        case DROP_TYPE_NORMAL:
                        case DROP_TYPE_UNOBTAINABLE:
                            if (how_to_obtain.get(id.getDisplayName()) != null) {
                                for (JsonElement je : how_to_obtain.get(id.getDisplayName()).getAsJsonArray()) {
                                    if (je.getAsString().equals(j.get(JsonKeys.NAME.getKey()).getAsString())) {
                                        return true;
                                    }
                                }
                            }
                            break;
                        case DROP_TYPE_MERCHANT:
                        case DROP_TYPE_QUEST:
                        case DROP_TYPE_RAID_REWARDS:
                        case DROP_TYPE_WORLD_EVENT:
                        case DROP_TYPE_LOOTRUN:
                            return (how_to_obtain.get(id.getDisplayName()) != null && how_to_obtain.get(id.getDisplayName()).getAsJsonObject().get(j.get(JsonKeys.NAME.getKey()).getAsString()) != null);
                    }
                } else {
                    return true;
                }
            } else {
                System.out.println("Warning: Can't check this ID");
            }
        }
        return false;
    }

    public float getTotalSumFloat(JsonObject j, SumEnum sum, JsonKeys sortType, ItemType type) {
        float total = 0;
        float sum_total = 0;
        int sum_total_sub = 0;
        //Base IDs
        if (sum.getIds() != null) {
            for (int n = 0; sum.getIds().size() > n; ++n) {
                Identifications ids = sum.getIds().get(n);
                sum_total += getIDValue(j, ids, sortType, type);
            }
        }
        //Sub IDs
        if (sum.getMultiIDs() != null) {
            for (int n = 0; sum.getMultiIDs().size() > n; ++n) {
                Identifications ids = sum.getMultiIDs().get(n);
                sum_total_sub += getIDValue(j, ids, sortType, type);
            }
            if (sum_total < 0 && sum_total_sub < 0) {
                sum_total_sub *= -1;
            } else if (sum_total < 0 && sum_total_sub > 0) {
                sum_total_sub *= -1;
                if (sum_total_sub < -100) {
                    sum_total_sub = -100;
                }
            }
            sum_total = sum_total * (1F + sum_total_sub / 100F);
        }
        if (sum.getAddIDs() != null) {
            for (int n = 0; sum.getAddIDs().size() > n; ++n) {
                Identifications ids = sum.getAddIDs().get(n);
                int t = getIDValue(j, ids, sortType, type);

                if (sum.isMeleeDPS()) {
                    sum_total += t;
                } else {
                    total += t;
                }
            }
        }
        //DPS (Attack Speed)
        if (sum.isDPS()) {
            if (j.get(Identifications.ATTACK_SPEED.getItemName()) != null) {
                sum_total *= getAttackSpeed(j.get(Identifications.ATTACK_SPEED.getItemName()).getAsString());
            }
        }
        return total + sum_total;
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

    public static int getBaseID(int i) {
        return Math.round(i / 1.3F);
    }

    public static float getAttackSpeed(JsonObject j) {
        if (j.get(Identifications.ATTACK_SPEED.getItemName()) != null) {
            return getAttackSpeed(j.get(Identifications.ATTACK_SPEED.getItemName()).getAsString());
        }
        return 0;
    }

    public static float getAttackSpeed(String s) {
        return switch (s) {
            case "super_fast", JsonValues.SUPER_FAST -> 4.3F;
            case "very_fast", JsonValues.VERY_FAST -> 3.1F;
            case JsonValues.FAST -> 2.5F;
            case JsonValues.A_NORMAL -> 2.05F;
            case JsonValues.SLOW -> 1.5F;
            case "very_slow", JsonValues.VERY_SLOW -> 0.83F;
            case "super_slow", JsonValues.SUPER_SLOW -> 0.51F;
            default -> 0;
        };
    }

    public static JsonObject getSetsJson() {
        return setsJson;
    }

    public static JsonObject getHow_to_obtain_item() {
        return how_to_obtain_item;
    }

    public static JsonObject getHow_to_obtain_ing() {
        return how_to_obtain_ing;
    }

    public static JsonObject getHow_to_obtain_other() {
        return how_to_obtain_other;
    }
}
