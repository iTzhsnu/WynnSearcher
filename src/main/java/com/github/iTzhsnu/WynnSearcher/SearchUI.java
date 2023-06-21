package com.github.iTzhsnu.WynnSearcher;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SearchUI extends JFrame implements ActionListener {
    public static final String VERSION = "3.1.4";

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

    //Item or Ingredient
    private final JComboBox<String> itemOrIngredient = new JComboBox<>();

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

    //How to Obtain
    private final JsonObject how_to_obtain_item;
    private final JsonObject how_to_obtain_ing;
    private final JsonObject how_to_obtain_other;

    public static boolean isReversedID(Identifications id) {
        return id == Identifications.RAW_1ST_SPELL_COST || id == Identifications.RAW_2ND_SPELL_COST || id == Identifications.RAW_3RD_SPELL_COST || id == Identifications.RAW_4TH_SPELL_COST || id == Identifications.PERCENT_1ST_SPELL_COST || id == Identifications.PERCENT_2ND_SPELL_COST || id == Identifications.PERCENT_3RD_SPELL_COST || id == Identifications.PERCENT_4TH_SPELL_COST;
    }

    public SearchUI() {

        GetAPI getAPI = new GetAPI();
        getAPI.loadArchiveV3API(wynnItems, wynnIngredients, wynnOtherItems, itemAPIConnect);
        List<JsonObject> wynnRecipes = new ArrayList<>();
        String recipeAPIConnect = getAPI.setRecipeData(wynnRecipes);

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
        itemOrIngredient.setBounds(320, 15, 170, 20);
        itemOrIngredient.addItem("Type: Armor and Weapon");
        itemOrIngredient.addItem("Type: Ingredient");
        itemOrIngredient.addItem("Type: Other Items");
        itemOrIngredient.addActionListener(this);

        typeItemDataSet();
        typeIngredientDataSet();
        typeOtherItemsDataSet();
        setVisibleIngredient(false);
        setVisibleOther(false);
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
        displayTime.setBounds(700, 235, 200, 40);

        //Item and Ing Search, Craft, Build or Powder
        type.setBounds(80, 5, 130, 20);
        type.addItem("Searcher (Stable)");
        type.addItem("Crafter (Beta)");
        type.addItem("Builder (Beta)");
        type.addItem("Custom");
        type.addActionListener(this);

        //Add Contents
        contentPane.add(name);
        contentPane.add(searchB);
        contentPane.add(searchF);
        contentPane.add(itemOrIngredient);
        contentPane.add(scrollPane);
        contentPane.add(sortType);
        contentPane.add(searchedItemCount);
        contentPane.add(updateSize);
        contentPane.add(type);
        contentPane.add(displayTime);
        contentPane.add(updateAPI);

        this.crafterUI = new CrafterUI(contentPane, wynnIngredients, wynnRecipes, recipeAPIConnect, itemAPIConnect);
        this.builderUI = new BuilderUI(contentPane, wynnItems, wynnIngredients, wynnOtherItems, wynnRecipes, itemAPIConnect, recipeAPIConnect);
        this.customUI = new CustomUI(contentPane);

        crafterUI.setCrafterVisible(false);
        builderUI.setBuilderVisible(false);
        customUI.setCustomVisible(false);

        ToolTipManager tipManager = ToolTipManager.sharedInstance();
        tipManager.setInitialDelay(100);
        tipManager.setDismissDelay(Integer.MAX_VALUE);
        tipManager.setReshowDelay(100);
    }

    public static void main(String[] args) {
        new SearchUI().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchB) {
            for (int i = itemDisplays.size() - 1; i >= 0; --i) {
                    searched.remove(itemDisplays.get(i));
                    itemDisplays.remove(i);
            }
            if (canSearchItem()) {
                searchItems();
            } else if (canSearchIngredient()) {
                searchIngredient();
            } else if (canSearchOtherItems()) {
                searchOtherItems();
            } else {
                searchedItemCount.setText("Search Failed");
            }
        } else if (e.getSource() == itemOrIngredient) {
            if (Objects.equals(itemOrIngredient.getItemAt(itemOrIngredient.getSelectedIndex()), "Type: Armor and Weapon")) {
                setVisibleIngredient(false);
                setVisibleOther(false);
                setVisibleItem(true);
            } else if (Objects.equals(itemOrIngredient.getItemAt(itemOrIngredient.getSelectedIndex()), "Type: Ingredient")) {
                setVisibleItem(false);
                setVisibleOther(false);
                setVisibleIngredient(true);
            } else if (itemOrIngredient.getItemAt(itemOrIngredient.getSelectedIndex()).equals("Type: Other Items")) {
                setVisibleItem(false);
                setVisibleIngredient(false);
                setVisibleOther(true);
            }
        } else if (e.getSource() == updateSize) {
            scrollPane.setSize(getWidth() - 25, getHeight() - 315);
            SwingUtilities.updateComponentTreeUI(searched);
        } else if (e.getSource() == type) {
            switch (type.getSelectedIndex()) {
                case 0: setSearcherVisible(true);
                    break;
                case 1: setCrafterVisible(true);
                    break;
                case 2: setBuilderVisible(true);
                    break;
                case 3: setCustomVisible(true);
                    break;
            }
        } else if (e.getSource() == updateAPI) {
            updateAPI.setVisible(false);
            itemAPIConnect.setText("Updating");
            itemAPIConnect.setForeground(new Color(255, 255, 0));
            updateAPI();
        }
    }

    public void updateAPI() {
        if (wynnItems.size() > 0) wynnItems.clear();
        if (wynnIngredients.size() > 0) wynnIngredients.clear();
        if (wynnOtherItems.size() > 0) wynnOtherItems.clear();
        if (bowJson.size() > 0) bowJson.clear();
        if (spearJson.size() > 0) spearJson.clear();
        if (wandJson.size() > 0) wandJson.clear();
        if (daggerJson.size() > 0) daggerJson.clear();
        if (relikJson.size() > 0) relikJson.clear();
        if (helmetJson.size() > 0) helmetJson.clear();
        if (chestplateJson.size() > 0) chestplateJson.clear();
        if (leggingsJson.size() > 0) leggingsJson.clear();
        if (bootsJson.size() > 0) bootsJson.clear();
        if (ringJson.size() > 0) ringJson.clear();
        if (braceletJson.size() > 0) braceletJson.clear();
        if (necklaceJson.size() > 0) necklaceJson.clear();
        if (armouringJson.size() > 0) armouringJson.clear();
        if (tailoringJson.size() > 0) tailoringJson.clear();
        if (weaponsmithingJson.size() > 0) weaponsmithingJson.clear();
        if (woodworkingJson.size() > 0) woodworkingJson.clear();
        if (jewelingJson.size() > 0) jewelingJson.clear();
        if (scribingJson.size() > 0) scribingJson.clear();
        if (cookingJson.size() > 0) cookingJson.clear();
        if (alchemismJson.size() > 0) alchemismJson.clear();
        if (tomeJson.size() > 0) tomeJson.clear();
        if (charmJson.size() > 0) charmJson.clear();
        if (toolJson.size() > 0) toolJson.clear();
        if (materialJson.size() > 0) materialJson.clear();

        new GetAPI().getWynnAPIV3(wynnItems, wynnIngredients, wynnOtherItems, itemAPIConnect);

        setItemJson();
        setIngredientJson();
        setOtherItemsJson();
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
        itemTier.addItem("All");
        itemTier.addItem("No Normal");
        itemTier.addItem("Mythic");
        itemTier.addItem("Fabled");
        itemTier.addItem("Legendary");
        itemTier.addItem("Rare");
        itemTier.addItem("Unique");
        itemTier.addItem("Set");
        itemTier.addItem("Normal");
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

        contentPane.add(tome);
        contentPane.add(charm);
        contentPane.add(tool);
        contentPane.add(material);
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
    }

    public boolean canSearchItem() {
        if (Objects.equals(itemOrIngredient.getItemAt(itemOrIngredient.getSelectedIndex()), "Type: Armor and Weapon")) {
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
        if (Objects.equals(itemOrIngredient.getItemAt(itemOrIngredient.getSelectedIndex()), "Type: Ingredient")) {
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
        if (Objects.equals(itemOrIngredient.getItemAt(itemOrIngredient.getSelectedIndex()), "Type: Other Items")) {
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

    public boolean notEmpty(JComboBox<String> box) {
        return !((JTextField) box.getEditor().getEditorComponent()).getText().isEmpty();
    }

    public void searchItems() {
        long startTime = System.currentTimeMillis();

        searchedItems.clear();

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

        searchItemFromIDs(idBoxes_1, idMin_1, idMax_1);
        searchItemFromIDs(idBoxes_2, idMin_2, idMax_2);
        searchItemFromIDs(idBoxes_3, idMin_3, idMax_3);
        searchItemFromIDs(idBoxes_4, idMin_4, idMax_4);

        Identifications id_0 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(idBoxes_1, 0), Identifications.EMPTY);
        Identifications id_1 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(idBoxes_1, 1), Identifications.EMPTY);
        Identifications id_2 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(idBoxes_1, 2), Identifications.EMPTY);
        Identifications id_3 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(idBoxes_1, 3), Identifications.EMPTY);
        if (id_0.getItemName() == null && id_1.getItemName() == null && id_2.getItemName() == null && id_3.getItemName() == null) {
            removeAllSearchedItemOrIngredients();
        }

        searchItemFromTier();

        searchFromName();

        filterItemFromSize(idBoxes_1, idMin_1, idMax_1);
        filterItemFromSize(idBoxes_2, idMin_2, idMax_2);
        filterItemFromSize(idBoxes_3, idMin_3, idMax_3);
        filterItemFromSize(idBoxes_4, idMin_4, idMax_4);

        searchedItemCount.setText("Searched Item: " + searchedItems.size());

        long midTime = System.currentTimeMillis();

        for (int sil = searchedItems.size() - 1; sil >= 0; --sil) {
            sortItems(idBoxes_1);
        }

        setDisplaySize();

        displayTime.setText((midTime - startTime) + "ms, " + (System.currentTimeMillis() - midTime) + "ms");
    }

    public void searchIngredient() {
        long startTime = System.currentTimeMillis();
        searchedItems.clear();

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
                        JsonArray array = json.get("requirements").getAsJsonObject().get("skills").getAsJsonArray();
                        for (int i = 0; array.size() > i; ++i) {
                            String s = array.get(i).getAsString();
                            if (s.contains("armouring")) add = false;
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
                        JsonArray array = json.get("requirements").getAsJsonObject().get("skills").getAsJsonArray();
                        for (int i = 0; array.size() > i; ++i) {
                            String s = array.get(i).getAsString();
                            if (hasIngType(s, "armouring") || hasIngType(s, "tailoring")) add = false;
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
                        JsonArray array = json.get("requirements").getAsJsonObject().get("skills").getAsJsonArray();
                        for (int i = 0; array.size() > i; ++i) {
                            String s = array.get(i).getAsString();
                            if (hasIngType(s, "armouring") || hasIngType(s, "tailoring") || hasIngType(s, "weaponsmithing")) add = false;
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
                        JsonArray array = json.get("requirements").getAsJsonObject().get("skills").getAsJsonArray();
                        for (int i = 0; array.size() > i; ++i) {
                            String s = array.get(i).getAsString();
                            if (hasIngType(s, "armouring") || hasIngType(s, "tailoring") || hasIngType(s, "weaponsmithing") || hasIngType(s, "woodworking")) add = false;
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
                        JsonArray array = json.get("requirements").getAsJsonObject().get("skills").getAsJsonArray();
                        for (int i = 0; array.size() > i; ++i) {
                            String s = array.get(i).getAsString();
                            if (hasIngType(s, "armouring") || hasIngType(s, "tailoring") || hasIngType(s, "weaponsmithing") || hasIngType(s, "woodworking") || hasIngType(s, "jeweling")) add = false;
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
                        JsonArray array = json.get("requirements").getAsJsonObject().get("skills").getAsJsonArray();
                        for (int i = 0; array.size() > i; ++i) {
                            String s = array.get(i).getAsString();
                            if (hasIngType(s, "armouring") || hasIngType(s, "tailoring") || hasIngType(s, "weaponsmithing") || hasIngType(s, "woodworking") || hasIngType(s, "jeweling") || hasIngType(s, "scribing")) add = false;
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
                        JsonArray array = json.get("requirements").getAsJsonObject().get("skills").getAsJsonArray();
                        for (int i = 0; array.size() > i; ++i) {
                            String s = array.get(i).getAsString();
                            if (hasIngType(s, "armouring") || hasIngType(s, "tailoring") || hasIngType(s, "weaponsmithing") || hasIngType(s, "woodworking") || hasIngType(s, "jeweling") || hasIngType(s, "scribing") || hasIngType(s, "cooking")) add = false;
                        }
                        if (add) searchedItems.add(json);
                    }
                }
            }
        }

        searchIngFromIDs(idBoxes_1, idMin_1, idMax_1);
        searchIngFromIDs(idBoxes_2, idMin_2, idMax_2);
        searchIngFromIDs(idBoxes_3, idMin_3, idMax_3);
        searchIngFromIDs(idBoxes_4, idMin_4, idMax_4);

        searchIngFromTier();

        searchFromName();

        filterIngredientFromSize(idBoxes_1, idMin_1, idMax_1);
        filterIngredientFromSize(idBoxes_2, idMin_2, idMax_2);
        filterIngredientFromSize(idBoxes_3, idMin_3, idMax_3);
        filterIngredientFromSize(idBoxes_4, idMin_4, idMax_4);

        searchedItemCount.setText("Searched Item: " + searchedItems.size());

        long midTime = System.currentTimeMillis();

        for (int sil = searchedItems.size() - 1; sil >= 0; --sil) {
            sortIngredients(idBoxes_1);
        }

        setDisplaySize();

        displayTime.setText((midTime - startTime) + "ms, " + (System.currentTimeMillis() - midTime) + "ms");
    }

    public void searchOtherItems() {
        long startTime = System.currentTimeMillis();

        searchedItems.clear();

        //Search Item Type
        if (tome.isSelected() && charm.isSelected() && tool.isSelected() && material.isSelected()) {
            searchedItems.addAll(wynnOtherItems);
        } else {
            if (tome.isSelected()) searchedItems.addAll(tomeJson);
            if (charm.isSelected()) searchedItems.addAll(charmJson);
            if (tool.isSelected()) searchedItems.addAll(toolJson);
            if (material.isSelected()) searchedItems.addAll(materialJson);
        }

        searchItemFromIDs(idBoxes_1, idMin_1, idMax_1);
        searchItemFromIDs(idBoxes_2, idMin_2, idMax_2);
        searchItemFromIDs(idBoxes_3, idMin_3, idMax_3);
        searchItemFromIDs(idBoxes_4, idMin_4, idMax_4);

        Identifications id_0 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(idBoxes_1, 0), Identifications.EMPTY);
        Identifications id_1 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(idBoxes_1, 1), Identifications.EMPTY);
        Identifications id_2 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(idBoxes_1, 2), Identifications.EMPTY);
        Identifications id_3 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(idBoxes_1, 3), Identifications.EMPTY);
        if (id_0.getItemName() == null && id_1.getItemName() == null && id_2.getItemName() == null && id_3.getItemName() == null) {
            removeAllSearchedItemOrIngredients();
        }

        searchOtherFromTier();

        searchFromName();

        filterItemFromSize(idBoxes_1, idMin_1, idMax_1);
        filterItemFromSize(idBoxes_2, idMin_2, idMax_2);
        filterItemFromSize(idBoxes_3, idMin_3, idMax_3);
        filterItemFromSize(idBoxes_4, idMin_4, idMax_4);

        searchedItemCount.setText("Searched Item: " + searchedItems.size());

        long midTime = System.currentTimeMillis();

        for (int sil = searchedItems.size() - 1; sil >= 0; --sil) {
            sortOtherItems(idBoxes_1);
        }

        setDisplaySize();

        displayTime.setText((midTime - startTime) + "ms, " + (System.currentTimeMillis() - midTime) + "ms");
    }

    public boolean hasIngType(String thisName, String needName) {
        JCheckBox needCheckBox = armouring;

        switch (needName) {
            case "TAILORING":
                needCheckBox = tailoring;
                break;
            case "WEAPONSMITHING":
                needCheckBox = weaponsmithing;
                break;
            case "WOODWORKING":
                needCheckBox = woodworking;
                break;
            case "JEWELING":
                needCheckBox = jeweling;
                break;
            case "SCRIBING":
                needCheckBox = scribing;
                break;
            case "COOKING":
                needCheckBox = cooking;
                break;
            case "ALCHEMISM":
                needCheckBox = alchemism;
                break;
        }

        return thisName.contains(needName) && needCheckBox.isSelected();
    }

    public void setItemJson() {
        for (JsonObject item : wynnItems) {
            if (item.get("type") != null) {
                switch (item.get("type").getAsString()) {
                    case "bow":
                        bowJson.add(item);
                        break;
                    case "spear":
                        spearJson.add(item);
                        break;
                    case "wand":
                        wandJson.add(item);
                        break;
                    case "dagger":
                        daggerJson.add(item);
                        break;
                    case "relik":
                        relikJson.add(item);
                        break;
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

    public void setIngredientJson() {
        for (JsonObject ing : wynnIngredients) {
            for (int i = 0; ing.get("requirements").getAsJsonObject().get("skills").getAsJsonArray().size() > i; ++i) {
                switch (ing.get("requirements").getAsJsonObject().get("skills").getAsJsonArray().get(i).getAsString()) {
                    case "armouring":
                        armouringJson.add(ing);
                        break;
                    case "tailoring":
                        tailoringJson.add(ing);
                        break;
                    case "weaponsmithing":
                        weaponsmithingJson.add(ing);
                        break;
                    case "woodworking":
                        woodworkingJson.add(ing);
                        break;
                    case "jeweling":
                        jewelingJson.add(ing);
                        break;
                    case "scribing":
                        scribingJson.add(ing);
                        break;
                    case "cooking":
                        cookingJson.add(ing);
                        break;
                    case "alchemism":
                        alchemismJson.add(ing);
                        break;
                }
            }
        }
    }

    public void setOtherItemsJson() {
        for (JsonObject j : wynnOtherItems) {
            switch (j.get("type").getAsString()) {
                case "tome":
                    tomeJson.add(j);
                    break;
                case "charm":
                    charmJson.add(j);
                    break;
                case "tool":
                    toolJson.add(j);
                    break;
                case "material":
                    materialJson.add(j);
                    break;
            }
        }
    }

    public void searchItemFromIDs(List<JComboBox<String>> box, JTextField min, JTextField max) {
        Identifications id_0 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, 0), Identifications.EMPTY);
        Identifications id_1 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, 1), Identifications.EMPTY);
        Identifications id_2 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, 2), Identifications.EMPTY);
        Identifications id_3 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, 3), Identifications.EMPTY);

        if (!getComboBoxText(box, 0).isEmpty() || !getComboBoxText(box, 1).isEmpty() || !getComboBoxText(box, 2).isEmpty() || !getComboBoxText(box, 3).isEmpty()) {
            if (id_0.getItemName() != null || id_1.getItemName() != null || id_2.getItemName() != null || id_3.getItemName() != null) {
                for (int i = searchedItems.size() - 1; i >= 0; --i) {
                    JsonObject j = searchedItems.get(i);
                    if (!min.getText().isEmpty() || !max.getText().isEmpty()) { //ID Range Filter 0 ~ 0
                        int min_Int = Integer.MIN_VALUE;
                        int max_Int = Integer.MAX_VALUE;
                        if (!min.getText().isEmpty() && min.getText().matches("[+-]?\\d*(\\.\\d+)?")) min_Int = Integer.parseInt(min.getText());
                        if (!max.getText().isEmpty() && max.getText().matches("[+-]?\\d*(\\.\\d+)?")) max_Int = Integer.parseInt(max.getText());
                        if (min_Int == 0 || max_Int == 0) {
                            if (notHaveItemID(id_0, j, 0, 0) && notHaveItemID(id_1, j, 0, 0) && notHaveItemID(id_2, j, 0, 0) && notHaveItemID(id_3, j, 0, 0)) {
                                continue;
                            }
                        }
                    }
                    boolean remove = true;
                    for (int num = 0; 4 > num; ++num) {
                        if (!getComboBoxText(box, num).isEmpty()) {
                            Identifications id = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, num), Identifications.EMPTY);
                            if (id.getItemName() != null && id.getItemFieldPos() != null) {
                                if (notHaveItemID(id_0, j, num, 1) && notHaveItemID(id_1, j, num, 2) && notHaveItemID(id_2, j, num, 3)) {
                                    if (!Objects.equals(id.getIDType(), "sum")) {
                                        if (id.getItemFieldPos().equals("nothing") && j.get(id.getItemName()) != null) {
                                            if (id.getItemName().equals(Identifications.DROP_TYPE.getItemName())) {
                                                String s = j.get(id.getItemName()).getAsString();
                                                if (j.get("dropMeta") != null) {
                                                    if (j.get("dropMeta").getAsJsonObject().get("type").isJsonArray()) {
                                                        s = "merchant";
                                                    } else {
                                                        s = j.get("dropMeta").getAsJsonObject().get("type").getAsString();
                                                    }
                                                }
                                                if (ItemUITemplate.haveManualDrop(how_to_obtain_item, j.get("name").getAsString()) > 0) s = "";
                                                if (how_to_obtain_item.get(id.getDisplayName()) != null) {
                                                    if (how_to_obtain_item.get(id.getDisplayName()).isJsonArray()) {
                                                        for (JsonElement je : how_to_obtain_item.get(id.getDisplayName()).getAsJsonArray()) {
                                                            if (je.getAsString().equals(j.get("name").getAsString())) {
                                                                s = id.getDisplayName();
                                                                break;
                                                            }
                                                        }
                                                    } else if (how_to_obtain_item.get(id.getDisplayName()).isJsonObject()) {
                                                        for (Map.Entry<String, JsonElement> entry : how_to_obtain_item.get(id.getDisplayName()).getAsJsonObject().entrySet()) {
                                                            if (entry.getKey().equals(j.get("name").getAsString())) {
                                                                s = id.getDisplayName();
                                                                break;
                                                            }
                                                        }
                                                    }
                                                }
                                                remove = !s.equals(id.getDisplayName());
                                            } else {
                                                remove = false;
                                            }
                                        } else if (j.get(id.getItemFieldPos()) != null && j.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()) != null) {
                                            remove = false;
                                        }
                                    } else if (Objects.equals(id.getIDType(), "sum")) {
                                        boolean needAll = true;
                                        boolean need = false;
                                        for (int n = 0; id.getSum().getIds().size() > n; ++n) {
                                            Identifications id2 = id.getSum().getIds().get(n);
                                            if (id2.getItemName() != null) {
                                                if (id2.getItemFieldPos().equals("nothing")) {
                                                    if (j.get(id2.getItemName()) != null) {
                                                        need = true;
                                                    } else {
                                                        needAll = false;
                                                    }
                                                } else {
                                                    if (j.get(id2.getItemFieldPos()) != null && j.get(id2.getItemFieldPos()).getAsJsonObject().get(id2.getItemName()) != null) {
                                                        need = true;
                                                    } else {
                                                        needAll = false;
                                                    }
                                                }
                                            } else {
                                                needAll = false;
                                            }
                                        }
                                        if (id.getSum().isNeedAll() && needAll) {
                                            remove = false;
                                        } else if (!id.getSum().isNeedAll() && need) {
                                            remove = false;
                                        }
                                    }
                                }
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

    public boolean notHaveItemID(Identifications id, JsonObject j, int idPos, int needPos) {
        if (idPos >= needPos && id.getItemName() != null && id.getItemFieldPos() != null) {
            if (!Objects.equals(id.getIDType(), "sum")) {
                if (id.getItemFieldPos().equals("nothing")) {
                    return j.get(id.getItemName()) == null;
                } else return j.get(id.getItemFieldPos()) == null || j.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()) == null;
            } else if (id.getIDType().equals("sum")) {
                boolean need = false;
                boolean needAll = true;
                for (int n = 0; id.getSum().getIds().size() > n; ++n) {
                    Identifications id2 = id.getSum().getIds().get(n);
                    if (id2.getItemName() != null) {
                        if (id2.getItemFieldPos().equals("nothing")) {
                            if (j.get(id2.getItemName()) != null) {
                                if (id2.getItemName().equals(Identifications.DROP_TYPE.getItemName())) {
                                    //Drop Type
                                    String s = j.get(id.getItemName()).getAsString();
                                    if (j.get("dropMeta") != null) {
                                        if (j.get("dropMeta").getAsJsonObject().get("type").isJsonArray()) {
                                            s = "merchant";
                                        } else {
                                            s = j.get("dropMeta").getAsJsonObject().get("type").getAsString();
                                        }
                                    }
                                    if (ItemUITemplate.haveManualDrop(how_to_obtain_item, j.get("name").getAsString()) > 0) s = "";
                                    if (how_to_obtain_item.get(id.getDisplayName()) != null) {
                                        if (how_to_obtain_item.get(id.getDisplayName()).isJsonArray()) {
                                            for (JsonElement je : how_to_obtain_item.get(id.getDisplayName()).getAsJsonArray()) {
                                                if (je.getAsString().equals(j.get("name").getAsString())) {
                                                    s = id.getDisplayName();
                                                    break;
                                                }
                                            }
                                        } else if (how_to_obtain_item.get(id.getDisplayName()).isJsonObject()) {
                                            for (Map.Entry<String, JsonElement> entry : how_to_obtain_item.get(id.getDisplayName()).getAsJsonObject().entrySet()) {
                                                if (entry.getKey().equals(j.get("name").getAsString())) {
                                                    s = id.getDisplayName();
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                    if (s.equals(id.getDisplayName())) {
                                        need = true;
                                    } else {
                                        needAll = false;
                                    }
                                } else {
                                    need = true;
                                }
                            } else {
                                needAll = false;
                            }
                        } else {
                            if (j.get(id2.getItemFieldPos()) != null && j.get(id2.getItemFieldPos()).getAsJsonObject().get(id2.getItemName()) != null) {
                                need = true;
                            } else {
                                needAll = false;
                            }
                        }
                    } else {
                        needAll = false;
                    }
                }

                if (id.getSum().isNeedAll() && needAll) {
                    return false;
                } else {
                    return id.getSum().isNeedAll() || !need;
                }
            }
        }
        return true;
    }

    public void searchIngFromIDs(List<JComboBox<String>> box, JTextField min, JTextField max) {
        Identifications id_0 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, 0), Identifications.EMPTY);
        Identifications id_1 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, 1), Identifications.EMPTY);
        Identifications id_2 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, 2), Identifications.EMPTY);
        Identifications id_3 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, 3), Identifications.EMPTY);

        if (!getComboBoxText(box, 0).isEmpty() || !getComboBoxText(box, 1).isEmpty() || !getComboBoxText(box, 2).isEmpty() || !getComboBoxText(box, 3).isEmpty()) {
            if (id_0.getIngName() != null || id_1.getIngName() != null || id_2.getIngName() != null || id_3.getIngName() != null) {
                for (int i = searchedItems.size() - 1; i >= 0; --i) {
                    JsonObject j = searchedItems.get(i);
                    if (!min.getText().isEmpty() || !max.getText().isEmpty()) { //ID Range Filter 0 ~ 0
                        int min_Int = Integer.MIN_VALUE;
                        int max_Int = Integer.MAX_VALUE;
                        if (!min.getText().isEmpty() && min.getText().matches("[+-]?\\d*(\\.\\d+)?")) min_Int = Integer.parseInt(min.getText());
                        if (!max.getText().isEmpty() && max.getText().matches("[+-]?\\d*(\\.\\d+)?")) max_Int = Integer.parseInt(max.getText());
                        if (min_Int == 0 || max_Int == 0) {
                            if (notHaveIngID(id_0, j, 0, 0) && notHaveIngID(id_1, j, 0, 0) && notHaveIngID(id_2, j, 0, 0) && notHaveIngID(id_3, j, 0, 0)) continue;
                        }
                    }
                    boolean remove = true;
                    for (int num = 0; 4 > num; ++num) {
                        if (!getComboBoxText(box, num).isEmpty()) {
                            Identifications id = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, num), Identifications.EMPTY);
                            if (id.getIngName() != null && id.getIngFieldPos() != null) {
                                if (notHaveIngID(id_0, j, num, 1) && notHaveIngID(id_1, j, num, 2) && notHaveIngID(id_2, j, num, 3)) {
                                    if (!Objects.equals(id.getIDType(), "sum")) {
                                        if (Objects.equals(id.getIngFieldPos(), "identifications") && j.get(id.getIngFieldPos()) != null && j.get(id.getIngFieldPos()).getAsJsonObject().get(id.getIngName()) != null) {
                                            remove = false;
                                        } else if (Objects.equals(id.getIngFieldPos(), "nothing") && j.get(id.getIngName()) != null && j.get(id.getIngName()).getAsInt() != 0) {
                                            remove = false;
                                        } else if (!Objects.equals(id.getIngFieldPos(), "identifications") && j.get(id.getIngFieldPos()) != null && j.get(id.getIngFieldPos()).getAsJsonObject().get(id.getIngName()) != null && j.get(id.getIngFieldPos()).getAsJsonObject().get(id.getIngName()).getAsInt() != 0) {
                                            remove = false;
                                        }
                                    } else if (Objects.equals(id.getIDType(), "sum")) {
                                        boolean need = false;
                                        boolean needAll = true;

                                        for (int n = 0; id.getSum().getIds().size() > n; ++n) {
                                            Identifications ids = id.getSum().getIds().get(n);
                                            if (ids.getIngName() != null && ids.getIngFieldPos() != null) {
                                                if (Objects.equals(ids.getIngFieldPos(), "identifications") && j.get(ids.getIngFieldPos()) != null && j.get(ids.getIngFieldPos()).getAsJsonObject().get(ids.getIngName()) != null) {
                                                    need = true;
                                                } else if (Objects.equals(ids.getIngFieldPos(), "nothing") && j.get(ids.getIngName()) != null) {
                                                    if (searchedItems.get(i).get(ids.getIngName()).getAsInt() == 0) {
                                                        needAll = false;
                                                    } else {
                                                        need = true;
                                                    }
                                                } else if (!Objects.equals(ids.getIngFieldPos(), "identifications") && j.get(ids.getIngFieldPos()) != null && j.get(ids.getIngFieldPos()).getAsJsonObject().get(ids.getIngName()) != null) {
                                                    if (j.get(ids.getIngFieldPos()).getAsJsonObject().get(ids.getIngName()).getAsInt() == 0) {
                                                        needAll = false;
                                                    } else {
                                                        need = true;
                                                    }
                                                } else {
                                                    needAll = false;
                                                }
                                            }
                                        }
                                        if (id.getSum().isNeedAll() && needAll) {
                                            remove = false;
                                        } else if (!id.getSum().isNeedAll() && need) {
                                            remove = false;
                                        }
                                    }
                                }
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

    public boolean notHaveIngID(Identifications id, JsonObject json, int idPos, int needPos) {
        if (idPos >= needPos) {
            if (!Objects.equals(id.getIDType(), "sum")) {
                if (id.getIngName() != null && id.getIngFieldPos() != null) {
                    if (Objects.equals(id.getIngFieldPos(), "identifications") && json.get(id.getIngFieldPos()) != null && json.get(id.getIngFieldPos()).getAsJsonObject().get(id.getIngName()) != null) {
                        return false;
                    } else if (Objects.equals(id.getIngFieldPos(), "nothing") && json.get(id.getIngName()).getAsInt() != 0) {
                        return false;
                    } else return Objects.equals(id.getIngFieldPos(), "identifications") || json.get(id.getIngFieldPos()).getAsJsonObject().get(id.getIngName()).getAsInt() == 0;

                }
            } else if (Objects.equals(id.getIDType(), "sum")) {
                boolean need = false;
                boolean needAll = true;

                for (int n = 0; id.getSum().getIds().size() > n; ++n) {
                    Identifications ids = id.getSum().getIds().get(n);
                    if (ids.getIngName() != null && ids.getIngFieldPos() != null) {
                        if (Objects.equals(ids.getIngFieldPos(), "identifications")) {
                            if (json.get(ids.getIngFieldPos()) != null && json.get(ids.getIngFieldPos()).getAsJsonObject().get(ids.getIngName()) != null) {
                                needAll = false;
                            } else {
                                need = true;
                            }
                        } else if (Objects.equals(ids.getIngFieldPos(), "nothing")) {
                            if (json.get(ids.getIngName()).getAsInt() == 0) {
                                needAll = false;
                            } else {
                                need = true;
                            }
                        } else if (!Objects.equals(ids.getIngFieldPos(), "identifications")) {
                            if (json.get(ids.getIngFieldPos()).getAsJsonObject().get(ids.getIngName()).getAsInt() == 0) {
                                needAll = false;
                            } else {
                                need = true;
                            }
                        }
                    }
                }
                if (id.getSum().isNeedAll() && needAll) {
                    return false;
                } else {
                    return id.getSum().isNeedAll() || !need;
                }
            }
        }
        return true;
        }

    public String getComboBoxText(List<JComboBox<String>> boxes, int i) {
        return ((JTextField) boxes.get(i).getEditor().getEditorComponent()).getText();
    }

    public void setDisplaySize() {
        if (itemDisplays.size() > 0) {
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

            if (10 + p.getBounds().y + p.getBounds().height > scrollPane.getHeight()) {
                searched.setPreferredSize(new Dimension(scrollPane.getWidth() - 18, 10 + p.getBounds().y + p.getBounds().height));
            } else {
                searched.setPreferredSize(new Dimension(scrollPane.getWidth() - 18, scrollPane.getHeight()));
            }
        }

        SwingUtilities.updateComponentTreeUI(searched);
    }

    public void searchItemFromTier() {
        if (!itemTier.getItemAt(itemTier.getSelectedIndex()).equals("All")) {
            for (int i = searchedItems.size() - 1; i >= 0; --i) {
                String s = searchedItems.get(i).get("tier").getAsString();
                switch (itemTier.getItemAt(itemTier.getSelectedIndex())) {
                    case "No Normal":
                        if (s.equals("normal")) {
                            searchedItems.remove(i);
                        }
                        break;
                    case "Mythic":
                        if (!s.equals("mythic")) {
                            searchedItems.remove(i);
                        }
                        break;
                    case "Fabled":
                        if (!s.equals("fabled")) {
                            searchedItems.remove(i);
                        }
                        break;
                    case "Legendary":
                        if (!s.equals("legendary")) {
                            searchedItems.remove(i);
                        }
                        break;
                    case "Rare":
                        if (!s.equals("rare")) {
                            searchedItems.remove(i);
                        }
                        break;
                    case "Unique":
                        if (!s.equals("unique")) {
                            searchedItems.remove(i);
                        }
                        break;
                    case "Set":
                        if (!s.equals("set")) {
                            searchedItems.remove(i);
                        }
                        break;
                    case "Normal":
                        if (!s.equals("normal")) {
                            searchedItems.remove(i);
                        }
                        break;
                }
            }
        }
    }

    public void searchIngFromTier() {
        if (!tier.getItemAt(tier.getSelectedIndex()).equals("All")) {
            for (int i = searchedItems.size() - 1; i >= 0; --i) {
                int t = searchedItems.get(i).get("tier").getAsInt();
                switch (tier.getItemAt(tier.getSelectedIndex())) {
                    case "0 Star":
                        if (t != 0) {
                            searchedItems.remove(i);
                        }
                        break;
                    case "1 Star":
                        if (t != 1) {
                            searchedItems.remove(i);
                        }
                        break;
                    case "2 Star":
                        if (t != 2) {
                            searchedItems.remove(i);
                        }
                        break;
                    case "3 Star":
                        if (t != 3) {
                            searchedItems.remove(i);
                        }
                        break;
                }
            }
        }
    }

    public void searchOtherFromTier() {
        if (!itemTier.getItemAt(itemTier.getSelectedIndex()).equals("All") || !tier.getItemAt(tier.getSelectedIndex()).equals("All")) {
            for (int i = searchedItems.size() - 1; i >= 0; --i) {
                try {
                    int t = searchedItems.get(i).get("tier").getAsInt();
                    switch (tier.getItemAt(tier.getSelectedIndex())) {
                        case "0 Star":
                            if (t != 0) {
                                searchedItems.remove(i);
                            }
                            break;
                        case "1 Star":
                            if (t != 1) {
                                searchedItems.remove(i);
                            }
                            break;
                        case "2 Star":
                            if (t != 2) {
                                searchedItems.remove(i);
                            }
                            break;
                        case "3 Star":
                            if (t != 3) {
                                searchedItems.remove(i);
                            }
                            break;
                    }
                } catch (NumberFormatException e) {
                    String s = searchedItems.get(i).get("tier").getAsString();
                    switch (itemTier.getItemAt(itemTier.getSelectedIndex())) {
                        case "No Normal":
                            if (s.equals("normal")) {
                                searchedItems.remove(i);
                            }
                            break;
                        case "Mythic":
                            if (!s.equals("mythic")) {
                                searchedItems.remove(i);
                            }
                            break;
                        case "Fabled":
                            if (!s.equals("fabled")) {
                                searchedItems.remove(i);
                            }
                            break;
                        case "Legendary":
                            if (!s.equals("legendary")) {
                                searchedItems.remove(i);
                            }
                            break;
                        case "Rare":
                            if (!s.equals("rare")) {
                                searchedItems.remove(i);
                            }
                            break;
                        case "Unique":
                            if (!s.equals("unique")) {
                                searchedItems.remove(i);
                            }
                            break;
                        case "Set":
                            if (!s.equals("set")) {
                                searchedItems.remove(i);
                            }
                            break;
                        case "Normal":
                            if (!s.equals("normal")) {
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
            if (json.get("name") != null) {
                if (!json.get("name").getAsString().toLowerCase().contains(searchF.getText().toLowerCase())) {
                    searchedItems.remove(i);
                }
            } else {
                searchedItems.remove(i);
            }
        }
    }

    public void filterItemFromSize(List<JComboBox<String>> box, JTextField min, JTextField max) {
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
                if (id_1.getItemName() != null || id_2.getItemName() != null || id_3.getItemName() != null || id_4.getItemName() != null) {
                    if (!id_1.getIDType().equals("string") && !id_2.getIDType().equals("string") && !id_3.getIDType().equals("string") && !id_4.getIDType().equals("string")) {
                        for (int i = searchedItems.size() - 1; i >= 0; --i) {
                            JsonObject j = searchedItems.get(i);
                            float total_min = 0;
                            float total_max = 0;
                            for (int s = 0; 4 > s; ++s) {
                                Identifications id = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, s), Identifications.EMPTY);
                                if (id.getItemName() != null && id.getItemFieldPos() != null) {
                                    if (!id.getIDType().equals("sum")) {
                                        if (id.getItemFieldPos().equals("nothing")) {
                                            if (id.getIDType().equals("int")) {
                                                total_min += j.get(id.getItemName()).getAsInt();
                                                total_max += j.get(id.getItemName()).getAsInt();
                                            } else if (id.getIDType().equals("string")) {
                                                total_min += 1;
                                                total_max += 1;
                                            }
                                        } else {
                                            if (j.get(id.getItemFieldPos()) != null && j.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()) != null) {
                                                if (!j.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()).isJsonObject()) {
                                                    total_min += j.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()).getAsInt();
                                                    total_max += j.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()).getAsInt();
                                                } else if (j.get("identified") != null && j.get("identified").getAsBoolean() && id.isItemVariable()) {
                                                    if (isReversedID(id)) {
                                                        int base = getBaseID(j.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()).getAsJsonObject().get("max").getAsInt());
                                                        total_min += base;
                                                        total_max += base;
                                                    } else {
                                                        String minOrMax = "max";
                                                        if (j.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()).getAsJsonObject().get("max").getAsInt() < 0) minOrMax = "min";
                                                        int base = getBaseID(j.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()).getAsJsonObject().get(minOrMax).getAsInt());
                                                        total_min += base;
                                                        total_max += base;
                                                    }
                                                } else if (id.isItemVariable()) { //Item ID Variable
                                                    if (!j.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()).isJsonObject()) {
                                                        total_min += j.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()).getAsInt();
                                                        total_max += j.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()).getAsInt();
                                                    } else if (isReversedID(id)) {
                                                        int base = getBaseID(j.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()).getAsJsonObject().get("max").getAsInt());
                                                        total_min += ItemUITemplate.getReversedMinInt(base);
                                                        total_max += ItemUITemplate.getReversedMaxInt(base);
                                                    } else {
                                                        total_min += j.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()).getAsJsonObject().get("min").getAsInt();
                                                        total_max += j.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()).getAsJsonObject().get("max").getAsInt();
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        if (id.getSum().getSumIDs() != null) {
                                            for (int n = 0; id.getSum().getSumIDs().size() > n; n++) {
                                                total_min += getTotalSumItemFloat(j, id.getSum().getSumIDs().get(n), true);
                                                total_max += getTotalSumItemFloat(j, id.getSum().getSumIDs().get(n), false);
                                            }
                                        } else {
                                            total_min += getTotalSumItemFloat(j, id.getSum(), true);
                                            total_max += getTotalSumItemFloat(j, id.getSum(), false);
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

    public void filterIngredientFromSize(List<JComboBox<String>> box, JTextField min, JTextField max) {
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
                if (id_1.getIngName() != null || id_2.getIngName() != null || id_3.getIngName() != null || id_4.getIngName() != null) {
                    for (int i = searchedItems.size() - 1; i >= 0; --i) {
                        JsonObject j = searchedItems.get(i);
                        int total_min = 0;
                        int total_max = 0;
                        for (int s = 0; 4 > s; ++s) {
                            Identifications id = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, s), Identifications.EMPTY);
                            if (id.getIngName() != null && id.getIngFieldPos() != null) {
                                if (!id.getIDType().equals("sum")) {
                                    if (Objects.equals(id.getIngFieldPos(), "identifications") && j.get(id.getIngFieldPos()) != null && j.get(id.getIngFieldPos()).getAsJsonObject().get(id.getIngName()) != null) {
                                        total_min += j.get(id.getIngFieldPos()).getAsJsonObject().get(id.getIngName()).getAsJsonObject().get("min").getAsInt();
                                        total_max += j.get(id.getIngFieldPos()).getAsJsonObject().get(id.getIngName()).getAsJsonObject().get("max").getAsInt();
                                    } else if (Objects.equals(id.getIngFieldPos(), "nothing") && j.get(id.getIngName()) != null && j.get(id.getIngName()).getAsInt() != 0) {
                                        total_min += j.get(id.getIngName()).getAsInt();
                                        total_max += j.get(id.getIngName()).getAsInt();
                                    } else if (!Objects.equals(id.getIngFieldPos(), "identifications") && j.get(id.getIngFieldPos()) != null && j.get(id.getIngFieldPos()).getAsJsonObject().get(id.getIngName()) != null && j.get(id.getIngFieldPos()).getAsJsonObject().get(id.getIngName()).getAsInt() != 0) {
                                        total_min += j.get(id.getIngFieldPos()).getAsJsonObject().get(id.getIngName()).getAsInt();
                                        total_max += j.get(id.getIngFieldPos()).getAsJsonObject().get(id.getIngName()).getAsInt();
                                    }
                                } else {
                                    int sum_total_min = 0;
                                    int sum_total_max = 0;
                                    for (int n = 0; id.getSum().getIds().size() > n; ++n) {
                                        Identifications ids = id.getSum().getIds().get(n);
                                        if (ids.getIngName() != null && ids.getIngFieldPos() != null) {
                                            if (Objects.equals(ids.getIngFieldPos(), "identifications") && j.get(ids.getIngFieldPos()) != null && j.get(ids.getIngFieldPos()).getAsJsonObject().get(ids.getIngName()) != null) {
                                                sum_total_min += j.get(ids.getIngFieldPos()).getAsJsonObject().get(ids.getIngName()).getAsJsonObject().get("min").getAsInt();
                                                sum_total_max += j.get(ids.getIngFieldPos()).getAsJsonObject().get(ids.getIngName()).getAsJsonObject().get("max").getAsInt();
                                            } else if (Objects.equals(ids.getIngFieldPos(), "nothing") && j.get(ids.getIngName()) != null && j.get(ids.getIngName()).getAsInt() != 0) {
                                                sum_total_min += j.get(ids.getIngName()).getAsInt();
                                                sum_total_max += j.get(ids.getIngName()).getAsInt();
                                            } else if (!Objects.equals(ids.getIngFieldPos(), "identifications") && j.get(ids.getIngFieldPos()) != null && j.get(ids.getIngFieldPos()).getAsJsonObject().get(ids.getIngName()) != null && j.get(ids.getIngFieldPos()).getAsJsonObject().get(ids.getIngName()).getAsInt() != 0) {
                                                sum_total_min += j.get(ids.getIngFieldPos()).getAsJsonObject().get(ids.getIngName()).getAsInt();
                                                sum_total_max += j.get(ids.getIngFieldPos()).getAsJsonObject().get(ids.getIngName()).getAsInt();
                                            }
                                        }
                                    }
                                    total_min += sum_total_min;
                                    total_max += sum_total_max;
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

    public void sortItems(List<JComboBox<String>> box) {
        int si = searchedItems.size() - 1;
        int iu = 0;
        float max = Integer.MIN_VALUE;
        boolean bSortType = false;

        if (sortType.getItemAt(sortType.getSelectedIndex()).equals("Sort: Min")) {
            max = Integer.MAX_VALUE;
            bSortType = true;
        }

        for (int i = 0; si >= i; ++i) {
            JsonObject j = searchedItems.get(si - i);
            float total = 0;

            for (int num = 0; 4 > num; ++num) {
                Identifications id = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, num), Identifications.EMPTY);
                if (id.getItemName() != null && id.getItemFieldPos() != null) {
                    if (!Objects.equals(id.getIDType(), "sum")) {
                        if (Objects.equals(id.getIDType(), "int")) {
                            //Integer Type IDs
                            if (id.getItemFieldPos().equals("nothing")) {
                                if (id.getIDType().equals("int")) {
                                    total += j.get(id.getItemName()).getAsInt();
                                }
                            } else {
                                if (j.get(id.getItemFieldPos()) != null && j.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()) != null) {
                                    if (!j.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()).isJsonObject()) {
                                        total += j.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()).getAsInt();
                                    } else if (j.get("identified") != null && j.get("identified").getAsBoolean() && id.isItemVariable()) {
                                        if (isReversedID(id)) {
                                            total += getBaseID(j.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()).getAsJsonObject().get("max").getAsInt());
                                        } else {
                                            String minOrMax = "max";
                                            if (j.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()).getAsJsonObject().get("max").getAsInt() < 0) minOrMax = "min";
                                            total += getBaseID(j.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()).getAsJsonObject().get(minOrMax).getAsInt());
                                        }
                                    } else if (id.isItemVariable()) { //Item ID Variable
                                        if (isReversedID(id)) {
                                            int base = getBaseID(j.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()).getAsJsonObject().get("max").getAsInt());
                                            if (bSortType) {
                                                total += ItemUITemplate.getReversedMinInt(base);
                                            } else {
                                                total += ItemUITemplate.getReversedMaxInt(base);
                                            }
                                        } else {
                                            if (bSortType) {
                                                total += j.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()).getAsJsonObject().get("min").getAsInt();
                                            } else {
                                                total += j.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()).getAsJsonObject().get("max").getAsInt();
                                            }
                                        }
                                    }
                                }
                            }
                        } else if (Objects.equals(id, Identifications.ATTACK_SPEED) && j.get(id.getItemName()) != null) {
                            //Attack Speed
                            switch (j.get(id.getItemName()).getAsString()) {
                                case "super_fast": total += 7;
                                break;
                                case "very_fast": total += 6;
                                break;
                                case "fast": total += 5;
                                break;
                                case "normal": total += 4;
                                break;
                                case "slow": total += 3;
                                break;
                                case "very_slow": total += 2;
                                break;
                                case "super_slow": total += 1;
                                break;
                            }
                        }
                    } else if (Objects.equals(id.getIDType(), "sum")) {
                        if (id.getSum().getSumIDs() != null) {
                            //SUM in SUM
                            for (int n = 0; id.getSum().getSumIDs().size() > n; ++n) {
                                total += getTotalSumItemFloat(j, id.getSum().getSumIDs().get(n), bSortType);
                            }
                        } else {
                            //Normal SUM
                            total += getTotalSumItemFloat(j, id.getSum(), bSortType);
                        }
                    }
                }
            }

            if (bSortType) {
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

        JPanel previous = null;
        JPanel above = null;
        if (itemDisplays.size() >= 1) {
            previous = itemDisplays.get(itemDisplays.size() - 1);
        }
        if (itemDisplays.size() >= (int) Math.floor((scrollPane.getWidth() - 5) / 260d)) {
            above = itemDisplays.get(itemDisplays.size() - (int) Math.floor((scrollPane.getWidth() - 5) / 260d));
        }
        itemDisplays.add(new ItemUITemplate(searchedItems.get(iu), "item", previous, above, scrollPane.getWidth(), max, false));
        searched.add(itemDisplays.get(itemDisplays.size() - 1));
        searchedItems.remove(iu);
    }

    public void sortIngredients(List<JComboBox<String>> box) {
        int si = searchedItems.size() - 1;
        int iu = 0;
        int max = Integer.MIN_VALUE;
        boolean bSortType = false;

        if (sortType.getItemAt(sortType.getSelectedIndex()).equals("Sort: Min")) {
            max = Integer.MAX_VALUE;
            bSortType = true;
        }

        for (int i = 0; si >= i; ++i) {
            JsonObject j = searchedItems.get(si - i);
            int total = 0;

            for (int num = 0; 4 > num; ++num) {
                Identifications id = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, num), Identifications.EMPTY);
                if (id.getIngName() != null) {
                    if (!Objects.equals(id.getIDType(), "sum")) {
                        if (Objects.equals(id.getIngFieldPos(), "identifications") && j.get(id.getIngFieldPos()) != null && j.get(id.getIngFieldPos()).getAsJsonObject().get(id.getIngName()) != null) {
                            int t = j.get(id.getIngFieldPos()).getAsJsonObject().get(id.getIngName()).getAsJsonObject().get("max").getAsInt();
                            if (bSortType) {
                                t = j.get(id.getIngFieldPos()).getAsJsonObject().get(id.getIngName()).getAsJsonObject().get("min").getAsInt();
                            }
                            total += t;
                        } else if (Objects.equals(id.getIngFieldPos(), "nothing") && j.get(id.getIngName()) != null && j.get(id.getIngName()).getAsInt() != 0) {
                            total += j.get(id.getIngName()).getAsInt();
                        } else if (!Objects.equals(id.getIngFieldPos(), "identifications") && j.get(id.getIngFieldPos()) != null && j.get(id.getIngFieldPos()).getAsJsonObject().get(id.getIngName()) != null && j.get(id.getIngFieldPos()).getAsJsonObject().get(id.getIngName()).getAsInt() != 0) {
                            total += j.get(id.getIngFieldPos()).getAsJsonObject().get(id.getIngName()).getAsInt();
                        }
                    } else if (Objects.equals(id.getIDType(), "sum")) {
                        int sum_total = 0;
                        for (int n = 0; id.getSum().getIds().size() > n; ++n) {
                            Identifications ids = id.getSum().getIds().get(n);
                            if (Objects.equals(ids.getIngFieldPos(), "identifications") && j.get(ids.getIngFieldPos()) != null && j.get(ids.getIngFieldPos()).getAsJsonObject().get(ids.getIngName()) != null) {
                                int t = j.get(ids.getIngFieldPos()).getAsJsonObject().get(ids.getIngName()).getAsJsonObject().get("max").getAsInt();
                                if (bSortType) {
                                    t = j.get(ids.getIngFieldPos()).getAsJsonObject().get(ids.getIngName()).getAsJsonObject().get("min").getAsInt();
                                }
                                sum_total += t;
                            } else if (Objects.equals(ids.getIngFieldPos(), "nothing") && j.get(ids.getIngName()) != null && j.get(ids.getIngName()).getAsInt() != 0) {
                                sum_total += j.get(ids.getIngName()).getAsInt();
                            } else if (!Objects.equals(ids.getIngFieldPos(), "identifications") && j.get(ids.getIngFieldPos()) != null && j.get(ids.getIngFieldPos()).getAsJsonObject().get(ids.getIngName()) != null && j.get(ids.getIngFieldPos()).getAsJsonObject().get(ids.getIngName()).getAsInt() != 0) {
                                sum_total += j.get(ids.getIngFieldPos()).getAsJsonObject().get(ids.getIngName()).getAsInt();
                            }
                        }
                        total += sum_total;
                    }
                }
            }

            if (bSortType) {
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

        JPanel previous = null;
        JPanel above = null;
        if (itemDisplays.size() >= 1) {
            previous = itemDisplays.get(itemDisplays.size() - 1);
        }
        if (itemDisplays.size() >= (int) Math.floor((scrollPane.getWidth() - 5) / 260d)) {
            above = itemDisplays.get(itemDisplays.size() - (int) Math.floor((scrollPane.getWidth() - 5) / 260d));
        }
        itemDisplays.add(new ItemUITemplate(searchedItems.get(iu), "ingredient", previous, above, scrollPane.getWidth(), max, false));
        searched.add(itemDisplays.get(itemDisplays.size() - 1));
        searchedItems.remove(iu);
    }

    public void sortOtherItems(List<JComboBox<String>> box) {
        int si = searchedItems.size() - 1;
        int iu = 0;
        float max = Integer.MIN_VALUE;
        boolean bSortType = false;

        if (sortType.getItemAt(sortType.getSelectedIndex()).equals("Sort: Min")) {
            max = Integer.MAX_VALUE;
            bSortType = true;
        }

        for (int i = 0; si >= i; ++i) {
            JsonObject j = searchedItems.get(si - i);
            float total = 0;

            for (int num = 0; 4 > num; ++num) {
                Identifications id = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, num), Identifications.EMPTY);
                if (id.getItemName() != null && id.getItemFieldPos() != null) {
                    if (!Objects.equals(id.getIDType(), "sum")) {
                        if (Objects.equals(id.getIDType(), "int")) {
                            //Integer Type IDs
                            if (id.getItemFieldPos().equals("nothing")) {
                                if (id.getIDType().equals("int")) {
                                    total += j.get(id.getItemName()).getAsInt();
                                }
                            } else {
                                if (j.get(id.getItemFieldPos()) != null && j.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()) != null) {
                                    if (!j.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()).isJsonObject()) {
                                        total += j.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()).getAsInt();
                                    } else if (j.get("identified") != null && j.get("identified").getAsBoolean() && id.isItemVariable()) {
                                        if (isReversedID(id)) {
                                            total += getBaseID(j.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()).getAsJsonObject().get("max").getAsInt());
                                        } else {
                                            String minOrMax = "max";
                                            if (j.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()).getAsJsonObject().get("max").getAsInt() < 0) minOrMax = "min";
                                            total += getBaseID(j.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()).getAsJsonObject().get(minOrMax).getAsInt());
                                        }
                                    } else if (id.isItemVariable()) { //Item ID Variable
                                        if (isReversedID(id)) {
                                            int base = getBaseID(j.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()).getAsJsonObject().get("max").getAsInt());
                                            if (bSortType) {
                                                total += ItemUITemplate.getReversedMinInt(base);
                                            } else {
                                                total += ItemUITemplate.getReversedMaxInt(base);
                                            }
                                        } else {
                                            if (bSortType) {
                                                total += j.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()).getAsJsonObject().get("min").getAsInt();
                                            } else {
                                                total += j.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()).getAsJsonObject().get("max").getAsInt();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else if (Objects.equals(id.getIDType(), "sum")) {
                        if (id.getSum().getSumIDs() != null) {
                            //SUM in SUM
                            for (int n = 0; id.getSum().getSumIDs().size() > n; ++n) {
                                total += getTotalSumItemFloat(j, id.getSum().getSumIDs().get(n), bSortType);
                            }
                        } else {
                            //Normal SUM
                            total += getTotalSumItemFloat(j, id.getSum(), bSortType);
                        }
                    }
                }
            }

            if (bSortType) {
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

        JPanel previous = null;
        JPanel above = null;
        if (itemDisplays.size() >= 1) {
            previous = itemDisplays.get(itemDisplays.size() - 1);
        }
        if (itemDisplays.size() >= (int) Math.floor((scrollPane.getWidth() - 5) / 260d)) {
            above = itemDisplays.get(itemDisplays.size() - (int) Math.floor((scrollPane.getWidth() - 5) / 260d));
        }
        itemDisplays.add(new ItemUITemplate(searchedItems.get(iu), "other", previous, above, scrollPane.getWidth(), max, false));
        searched.add(itemDisplays.get(itemDisplays.size() - 1));
        searchedItems.remove(iu);
    }

    public void removeAllSearchedItemOrIngredients() {
        if (searchedItems.size() > 0) {
            searchedItems.clear();
        }
    }

    public float getTotalSumItemFloat(JsonObject j, SumEnum sum, boolean getMin) {
        float total = 0;
        float sum_total = 0;
        int sum_total_sub = 0;
        //Base IDs
        if (sum.getIds() != null) {
            for (int n = 0; sum.getIds().size() > n; ++n) {
                Identifications ids = sum.getIds().get(n);
                if (j.get(ids.getItemFieldPos()) != null && j.get(ids.getItemFieldPos()).getAsJsonObject().get(ids.getItemName()) != null) {
                    if (!j.get(ids.getItemFieldPos()).getAsJsonObject().get(ids.getItemName()).isJsonObject()) {
                        sum_total += j.get(ids.getItemFieldPos()).getAsJsonObject().get(ids.getItemName()).getAsInt();
                    } else if (j.get("identified") != null && j.get("identified").getAsBoolean() && ids.isItemVariable() && !sum.isDPS()) {
                        if (isReversedID(ids)) {
                            sum_total += getBaseID(j.get(ids.getItemFieldPos()).getAsJsonObject().get(ids.getItemName()).getAsJsonObject().get("max").getAsInt());
                        } else {
                            String minOrMax = "max";
                            if (j.get(ids.getItemFieldPos()).getAsJsonObject().get(ids.getItemName()).getAsJsonObject().get("max").getAsInt() < 0) minOrMax = "min";
                            sum_total += getBaseID(j.get(ids.getItemFieldPos()).getAsJsonObject().get(ids.getItemName()).getAsJsonObject().get(minOrMax).getAsInt());
                        }
                    } else if (ids.isItemVariable()) { //Item ID Variable
                        if (sum.isDPS()) { //DPS (Average)
                            float total_Damage = j.get(ids.getItemFieldPos()).getAsJsonObject().get(ids.getItemName()).getAsJsonObject().get("min").getAsInt() + j.get(ids.getItemFieldPos()).getAsJsonObject().get(ids.getItemName()).getAsJsonObject().get("max").getAsInt();
                            sum_total += total_Damage / 2F;
                        } else if (isReversedID(ids)) {
                            int base = getBaseID(j.get(ids.getItemFieldPos()).getAsJsonObject().get(ids.getItemName()).getAsJsonObject().get("max").getAsInt());
                            if (getMin) {
                                sum_total += ItemUITemplate.getReversedMinInt(base);
                            } else {
                                sum_total += ItemUITemplate.getReversedMaxInt(base);
                            }
                        } else {
                            if (getMin) {
                                sum_total += j.get(ids.getItemFieldPos()).getAsJsonObject().get(ids.getItemName()).getAsJsonObject().get("min").getAsInt();
                            } else {
                                sum_total += j.get(ids.getItemFieldPos()).getAsJsonObject().get(ids.getItemName()).getAsJsonObject().get("max").getAsInt();
                            }
                        }
                    }
                }
            }
        }
        //Sub IDs
        if (sum.getMultiIDs() != null) {
            for (int n = 0; sum.getMultiIDs().size() > n; ++n) {
                Identifications ids = sum.getMultiIDs().get(n);
                if (j.get(ids.getItemFieldPos()) != null && j.get(ids.getItemFieldPos()).getAsJsonObject().get(ids.getItemName()) != null) {
                    if (!j.get(ids.getItemFieldPos()).getAsJsonObject().get(ids.getItemName()).isJsonObject()) {
                        sum_total_sub += j.get(ids.getItemFieldPos()).getAsJsonObject().get(ids.getItemName()).getAsInt();
                    } else if (j.get("identified") != null && j.get("identified").getAsBoolean() && ids.isItemVariable()) {
                        if (isReversedID(ids)) {
                            sum_total_sub += getBaseID(j.get(ids.getItemFieldPos()).getAsJsonObject().get(ids.getItemName()).getAsJsonObject().get("max").getAsInt());
                        } else {
                            String minOrMax = "max";
                            if (j.get(ids.getItemFieldPos()).getAsJsonObject().get(ids.getItemName()).getAsJsonObject().get("max").getAsInt() < 0) minOrMax = "min";
                            sum_total_sub += getBaseID(j.get(ids.getItemFieldPos()).getAsJsonObject().get(ids.getItemName()).getAsJsonObject().get(minOrMax).getAsInt());
                        }
                    } else if (ids.isItemVariable()) { //Item ID Variable
                        if (isReversedID(ids)) {
                            int base = getBaseID(j.get(ids.getItemFieldPos()).getAsJsonObject().get(ids.getItemName()).getAsJsonObject().get("max").getAsInt());
                            if (getMin) {
                                sum_total_sub += ItemUITemplate.getReversedMinInt(base);
                            } else {
                                sum_total_sub += ItemUITemplate.getReversedMaxInt(base);
                            }
                        } else {
                            if (getMin) {
                                sum_total_sub += j.get(ids.getItemFieldPos()).getAsJsonObject().get(ids.getItemName()).getAsJsonObject().get("min").getAsInt();
                            } else {
                                sum_total_sub += j.get(ids.getItemFieldPos()).getAsJsonObject().get(ids.getItemName()).getAsJsonObject().get("max").getAsInt();
                            }
                        }
                    }
                }
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
                int t = 0;
                if (j.get(ids.getItemFieldPos()) != null && j.get(ids.getItemFieldPos()).getAsJsonObject().get(ids.getItemName()) != null) {
                    if (!j.get(ids.getItemFieldPos()).getAsJsonObject().get(ids.getItemName()).isJsonObject()) {
                        t += j.get(ids.getItemFieldPos()).getAsJsonObject().get(ids.getItemName()).getAsInt();
                    } else if (j.get("identified") != null && j.get("identified").getAsBoolean() && ids.isItemVariable()) {
                        if (isReversedID(ids)) {
                            t += getBaseID(j.get(ids.getItemFieldPos()).getAsJsonObject().get(ids.getItemName()).getAsJsonObject().get("max").getAsInt());
                        } else {
                            String minOrMax = "max";
                            if (j.get(ids.getItemFieldPos()).getAsJsonObject().get(ids.getItemName()).getAsJsonObject().get("max").getAsInt() < 0) minOrMax = "min";
                            t += getBaseID(j.get(ids.getItemFieldPos()).getAsJsonObject().get(ids.getItemName()).getAsJsonObject().get(minOrMax).getAsInt());
                        }
                    } else if (ids.isItemVariable()) { //Item ID Variable
                        if (isReversedID(ids)) {
                            int base = getBaseID(j.get(ids.getItemFieldPos()).getAsJsonObject().get(ids.getItemName()).getAsJsonObject().get("max").getAsInt());
                            if (getMin) {
                                t += ItemUITemplate.getReversedMinInt(base);
                            } else {
                                t += ItemUITemplate.getReversedMaxInt(base);
                            }
                        } else {
                            if (getMin) {
                                t += j.get(ids.getItemFieldPos()).getAsJsonObject().get(ids.getItemName()).getAsJsonObject().get("min").getAsInt();
                            } else {
                                t += j.get(ids.getItemFieldPos()).getAsJsonObject().get(ids.getItemName()).getAsJsonObject().get("max").getAsInt();
                            }
                        }
                    }
                }
                if (sum.isMeleeDPS()) {
                    sum_total += t;
                } else {
                    total += t;
                }
            }
        }
        //DPS (Attack Speed)
        if (sum.isDPS()) {
            if (j.get("attackSpeed") != null) {
                switch (j.get("attackSpeed").getAsString()) {
                    case "super_fast": sum_total *= 4.3F;
                        break;
                    case "very_fast": sum_total *= 3.1F;
                        break;
                    case "fast": sum_total *= 2.5F;
                        break;
                    case "normal": sum_total *= 2.05F;
                        break;
                    case "slow": sum_total *= 1.5F;
                        break;
                    case "very_slow": sum_total *= 0.83F;
                        break;
                    case "super_slow": sum_total *= 0.51F;
                        break;
                }
            }
        }
        return total + sum_total;
    }

    public void setSearcherVisible(boolean visible) {
        itemOrIngredient.setVisible(visible);
        if (Objects.equals(itemOrIngredient.getItemAt(itemOrIngredient.getSelectedIndex()), "Type: Armor and Weapon")) {
            setVisibleItem(visible);
        } else if (Objects.equals(itemOrIngredient.getItemAt(itemOrIngredient.getSelectedIndex()), "Type: Ingredient")) {
            setVisibleIngredient(visible);
        } else if (itemOrIngredient.getItemAt(itemOrIngredient.getSelectedIndex()).equals("Type: Other Items")) {
            setVisibleOther(visible);
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
        }
    }

    public void setCrafterVisible(boolean visible) {
        crafterUI.setCrafterVisible(visible);

        if (visible) {
            setSearcherVisible(false);
            setBuilderVisible(false);
            setCustomVisible(false);
        }
    }

    public void setBuilderVisible(boolean visible) {
        builderUI.setBuilderVisible(visible);
        if (visible) {
            setSearcherVisible(false);
            setCrafterVisible(false);
            setCustomVisible(false);
        }
    }

    public void setCustomVisible(boolean visible) {
        customUI.setCustomVisible(visible);

        if (visible) {
            setSearcherVisible(false);
            setCrafterVisible(false);
            setBuilderVisible(false);
        }
    }

    public static int getBaseID(int i) {
        return Math.round(i / 1.3F);
    }
}
