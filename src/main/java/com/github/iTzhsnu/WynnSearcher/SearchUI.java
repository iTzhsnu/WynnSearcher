package com.github.iTzhsnu.WynnSearcher;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class SearchUI extends JFrame implements ActionListener {

    //Item and Ingredient Data
    private final List<JsonObject> wynnItems = new ArrayList<>();
    private final List<JsonObject> wynnIngredients = new ArrayList<>();

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

    //Item or Ingredient
    private final JComboBox<String> itemOrIngredient = new JComboBox<>();

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

    //Search Button and Text
    private final JButton searchB = new JButton("Search");
    private final JTextField searchF = new JTextField();
    private final Container contentPane;
    private final JPanel searched = new JPanel();
    private final List<JPanel> itemDisplays = new ArrayList<>();
    private final List<JsonObject> searchedItems = new ArrayList<>();

    //ID Combo Box
    private final List<JComboBox<String>> idBoxes_1 = new ArrayList<>();
    private final List<JComboBox<String>> idBoxes_2 = new ArrayList<>();
    private final List<JComboBox<String>> idBoxes_3 = new ArrayList<>();
    private final List<JComboBox<String>> idBoxes_4 = new ArrayList<>();

    //ID Min Text
    private final List<JTextField> idMin_1 = new ArrayList<>();
    private final List<JTextField> idMin_2 = new ArrayList<>();
    private final List<JTextField> idMin_3 = new ArrayList<>();
    private final List<JTextField> idMin_4 = new ArrayList<>();

    //ID Max Text
    private final List<JTextField> idMax_1 = new ArrayList<>();
    private final List<JTextField> idMax_2 = new ArrayList<>();
    private final List<JTextField> idMax_3 = new ArrayList<>();
    private final List<JTextField> idMax_4 = new ArrayList<>();

    public SearchUI() {

        GetAPI.setItemData(wynnItems);
        GetAPI.setIngredientData(wynnIngredients);

        setItemJson();
        setIngredientJson();

        setTitle("Wynncraft Searcher");
        setBounds(100, 100, 1100, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = getContentPane();
        setLayout(null);

        //Search Text Field
        searchF.setBounds(10, 30, 200, 30);

        JLabel name = new JLabel("Name:");
        name.setBounds(10, 0, 60, 30);

        //Click to Search
        searchB.setBounds(230, 20, 80, 40);
        searchB.addActionListener(this);

        //Item or Ingredient
        itemOrIngredient.setBounds(320, 15, 120, 20);
        itemOrIngredient.addItem("Type: Item");
        itemOrIngredient.addItem("Type: Ingredient");
        itemOrIngredient.addActionListener(this);

        typeItemDataSet();
        typeIngredientDataSet();
        setVisibleIngredient(false);

        //ID Search Setting Bar
        setIDBoxAndIDField(idBoxes_1, idMin_1, idMax_1, 10, 65, 4, true);
        setIDBoxAndIDField(idBoxes_2, idMin_2, idMax_2, 10, 115, 4, false);
        setIDBoxAndIDField(idBoxes_3, idMin_3, idMax_3, 10, 165, 4, false);
        setIDBoxAndIDField(idBoxes_4, idMin_4, idMax_4, 10, 215, 4, false);

        //Searched Items (or Ingredients) List Panel
        searched.setBorder(new LineBorder(Color.BLACK));
        searched.setPreferredSize(new Dimension(1057, 482));
        searched.setLayout(null);

        JScrollPane scrollPane = new JScrollPane(searched);
        scrollPane.setBounds(5, 270, 1075, 485);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);

        //Add Contents
        contentPane.add(name);
        contentPane.add(searchB);
        contentPane.add(searchF);
        contentPane.add(itemOrIngredient);
        contentPane.add(scrollPane);
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
            }
        }

        if (e.getSource() == itemOrIngredient) {
            if (Objects.equals(itemOrIngredient.getItemAt(itemOrIngredient.getSelectedIndex()), "Type: Item")) {
                setVisibleIngredient(false);
                setVisibleItem(true);
            } else if (Objects.equals(itemOrIngredient.getItemAt(itemOrIngredient.getSelectedIndex()), "Type: Ingredient")) {
                setVisibleItem(false);
                setVisibleIngredient(true);
            }
        }
    }

    public void setIDBoxAndIDField(List<JComboBox<String>> boxes, List<JTextField> min, List<JTextField> max, int baseX, int baseY, int length, boolean need) {
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

            JTextField textField = (JTextField) boxes.get(i).getEditor().getEditorComponent();
            textField.addKeyListener(new IDBoxAdapter(boxes.get(i)));

            //And
            if (i < 3) {
                JLabel label = new JLabel("and");
                label.setBounds(baseX + 202 + (224 * i), baseY, 30, 20);
                contentPane.add(label);
            } else if (i == 3) {
                JLabel label = new JLabel("(Total Sort)");
                label.setBounds(baseX + 202 + (224 * i), baseY, 70, 20);
                contentPane.add(label);
            }

            //Min
            min.add(new JTextField());
            min.get(i).setBounds(baseX + 120 + (224 * i), baseY + 21, 20, 20);

            //To
            JLabel toName = new JLabel("to");
            toName.setBounds(baseX + 145 + (224 * i), baseY + 20, 20, 20);

            //Max
            max.add(new JTextField());
            max.get(i).setBounds(baseX + 160 + (224 * i), baseY + 21, 20, 20);

            //Add
            contentPane.add(boxes.get(i));
            contentPane.add(min.get(i));
            contentPane.add(toName);
            contentPane.add(max.get(i));

        }
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
        itemTier.setBounds(320, 35, 120, 20);

        bow.setBounds(450, 10, 50, 20);
        spear.setBounds(510, 10, 60, 20);
        wand.setBounds(580, 10, 60, 20);
        dagger.setBounds(650, 10, 70, 20);
        relik.setBounds(730, 10, 60, 20);

        helmet.setBounds(450, 35, 65, 20);
        chestplate.setBounds(525, 35, 90, 20);
        leggings.setBounds(625, 35, 80, 20);
        boots.setBounds(715, 35, 60, 20);

        ring.setBounds(800, 10, 50, 20);
        bracelet.setBounds(860, 10, 80, 20);
        necklace.setBounds(785, 35, 80, 20);

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
    }

    public void typeIngredientDataSet() {
        tier.addItem("Any");
        tier.addItem("0 Star");
        tier.addItem("1 Star");
        tier.addItem("2 Star");
        tier.addItem("3 Star");
        tier.setBounds(320, 35, 80, 20);

        armouring.setBounds(450, 10, 85, 20);
        tailoring.setBounds(545, 10, 75, 20);
        weaponsmithing.setBounds(630, 10, 125, 20);
        woodworking.setBounds(765, 10, 105, 20);

        jeweling.setBounds(450, 35, 80, 20);
        scribing.setBounds(540, 35, 75, 20);
        cooking.setBounds(625, 35, 75, 20);
        alchemism.setBounds(710, 35, 90, 20);

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

    public void setVisibleItem(boolean visible) {
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

    public boolean canSearchItem() {
        if (Objects.equals(itemOrIngredient.getItemAt(itemOrIngredient.getSelectedIndex()), "Type: Item")) {
            if (bow.isSelected() || spear.isSelected() || wand.isSelected() || dagger.isSelected() || relik.isSelected() || helmet.isSelected() || chestplate.isSelected() || leggings.isSelected() || boots.isSelected() || ring.isSelected() || bracelet.isSelected() || necklace.isSelected()) {
                boolean hasText = false;
                for (JComboBox<String> box : idBoxes_1) {
                    if (notEmpty(box)) hasText = true;
                }
                for (JComboBox<String> box : idBoxes_2) {
                    if (notEmpty(box)) hasText = true;
                }
                for (JComboBox<String> box : idBoxes_3) {
                    if (notEmpty(box)) hasText = true;
                }
                for (JComboBox<String> box : idBoxes_4) {
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
                for (JComboBox<String> box : idBoxes_2) {
                    if (notEmpty(box)) hasText = true;
                }
                for (JComboBox<String> box : idBoxes_3) {
                    if (notEmpty(box)) hasText = true;
                }
                for (JComboBox<String> box : idBoxes_4) {
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

        searchItemFromIDs(idBoxes_1);
        searchItemFromIDs(idBoxes_2);
        searchItemFromIDs(idBoxes_3);
        searchItemFromIDs(idBoxes_4);

        Identifications id_0 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(idBoxes_1, 0), Identifications.EMPTY);
        Identifications id_1 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(idBoxes_1, 1), Identifications.EMPTY);
        Identifications id_2 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(idBoxes_1, 2), Identifications.EMPTY);
        Identifications id_3 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(idBoxes_1, 3), Identifications.EMPTY);
        if (id_0.getItemName() == null && id_1.getItemName() == null && id_2.getItemName() == null && id_3.getItemName() == null) {
            removeAllSearchedItemOrIngredients();
        }

        searchItemFromTier();

        searchFromName();

        for (int sil = searchedItems.size() - 1; sil >= 0; --sil) {
            sortItems(idBoxes_1);
        }

        setDisplaySize();
    }

    public void searchIngredient() {
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
                        JsonArray array = json.get("skills").getAsJsonArray();
                        for (int i = 0; array.size() > i; ++i) {
                            if (array.get(i).getAsString().contains("ARMOURING")) add = false;
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
                        JsonArray array = json.get("skills").getAsJsonArray();
                        for (int i = 0; array.size() > i; ++i) {
                            String s = array.get(i).getAsString();
                            if (s.contains("ARMOURING") || s.contains("TAILORING")) add = false;
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
                        JsonArray array = json.get("skills").getAsJsonArray();
                        for (int i = 0; array.size() > i; ++i) {
                            String s = array.get(i).getAsString();
                            if (s.contains("ARMOURING") || s.contains("TAILORING") || s.contains("WEAPONSMITHING")) add = false;
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
                        JsonArray array = json.get("skills").getAsJsonArray();
                        for (int i = 0; array.size() > i; ++i) {
                            String s = array.get(i).getAsString();
                            if (s.contains("ARMOURING") || s.contains("TAILORING") || s.contains("WEAPONSMITHING") || s.contains("WOODWORKING")) add = false;
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
                        JsonArray array = json.get("skills").getAsJsonArray();
                        for (int i = 0; array.size() > i; ++i) {
                            String s = array.get(i).getAsString();
                            if (s.contains("ARMOURING") || s.contains("TAILORING") || s.contains("WEAPONSMITHING") || s.contains("WOODWORKING") && s.contains("JEWELING")) add = false;
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
                        JsonArray array = json.get("skills").getAsJsonArray();
                        for (int i = 0; array.size() > i; ++i) {
                            String s = array.get(i).getAsString();
                            if (s.contains("ARMOURING") || s.contains("TAILORING") || s.contains("WEAPONSMITHING") || s.contains("WOODWORKING") && s.contains("JEWELING") || s.contains("SCRIBING")) add = false;
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
                        JsonArray array = json.get("skills").getAsJsonArray();
                        for (int i = 0; array.size() > i; ++i) {
                            String s = array.get(i).getAsString();
                            if (s.contains("ARMOURING") || s.contains("TAILORING") || s.contains("WEAPONSMITHING") || s.contains("WOODWORKING") && s.contains("JEWELING") || s.contains("SCRIBING") || s.contains("COOKING")) add = false;
                        }
                        if (add) searchedItems.add(json);
                    }
                }
            }
        }

        searchIngFromIDs(idBoxes_1);
        searchIngFromIDs(idBoxes_2);
        searchIngFromIDs(idBoxes_3);
        searchIngFromIDs(idBoxes_4);

        searchIngFromTier();

        searchFromName();

        //sortIngredients(idBoxes_1);


        for (int i = 0; searchedItems.size() > i; ++i) {
            JPanel previous = null;
            JPanel fourP = null;
            if (i >= 1) {
                previous = itemDisplays.get(i - 1);
            }
            if (i >= 4) {
                fourP = itemDisplays.get(i - 4);
            }
            itemDisplays.add(new ItemUITemplate(searchedItems.get(i), true, previous, fourP));
            searched.add(itemDisplays.get(i));
        }

        setDisplaySize();
    }

    public void setItemJson() {
        for (JsonObject item : wynnItems) {
            if (item.get("type") != null) {
                switch (item.get("type").getAsString()) {
                    case "Bow":
                        bowJson.add(item);
                        break;
                    case "Spear":
                        spearJson.add(item);
                        break;
                    case "Wand":
                        wandJson.add(item);
                        break;
                    case "Dagger":
                        daggerJson.add(item);
                        break;
                    case "Relik":
                        relikJson.add(item);
                        break;
                    case "Helmet":
                        helmetJson.add(item);
                        break;
                    case "Chestplate":
                        chestplateJson.add(item);
                        break;
                    case "Leggings":
                        leggingsJson.add(item);
                        break;
                    case "Boots":
                        bootsJson.add(item);
                        break;
                }
            } else if (item.get("accessoryType") != null) {
                switch (item.get("accessoryType").getAsString()) {
                    case "Ring":
                        ringJson.add(item);
                        break;
                    case "Bracelet":
                        braceletJson.add(item);
                        break;
                    case "Necklace":
                        necklaceJson.add(item);
                        break;
                }
            }
        }
    }

    public void setIngredientJson() {
        for (JsonObject ing : wynnIngredients) {
            for (int i = 0; ing.get("skills").getAsJsonArray().size() > i; ++i) {
                switch (ing.get("skills").getAsJsonArray().get(i).getAsString()) {
                    case "ARMOURING": armouringJson.add(ing);
                    break;
                    case "TAILORING": tailoringJson.add(ing);
                    break;
                    case "WEAPONSMITHING": weaponsmithingJson.add(ing);
                    break;
                    case "WOODWORKING": woodworkingJson.add(ing);
                    break;
                    case "JEWELING": jewelingJson.add(ing);
                    break;
                    case "SCRIBING": scribingJson.add(ing);
                    break;
                    case "COOKING": cookingJson.add(ing);
                    break;
                    case "ALCHEMISM": alchemismJson.add(ing);
                    break;
                }
            }
        }
    }

    public void searchItemFromIDs(List<JComboBox<String>> box) {
        Identifications id_0 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, 0), Identifications.EMPTY);
        Identifications id_1 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, 1), Identifications.EMPTY);
        Identifications id_2 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, 2), Identifications.EMPTY);
        Identifications id_3 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, 3), Identifications.EMPTY);

        if (!getComboBoxText(box, 0).isEmpty() || !getComboBoxText(box, 1).isEmpty() || !getComboBoxText(box, 2).isEmpty() || !getComboBoxText(box, 3).isEmpty()) {
            if (id_0.getItemName() != null || id_1.getItemName() != null || id_2.getItemName() != null || id_3.getItemName() != null) {
                for (int i = searchedItems.size() - 1; i >= 0; --i) {
                    boolean remove = true;
                    for (int num = 0; 4 > num; ++num) {
                        if (!getComboBoxText(box, num).isEmpty()) {
                            Identifications id = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, num), Identifications.EMPTY);
                            if (id.getItemName() != null) {
                                if (notHaveItemID(id_0, searchedItems.get(i), num, 1) && notHaveItemID(id_1, searchedItems.get(i), num, 2) && notHaveItemID(id_2, searchedItems.get(i), num, 3)) {
                                    if (!Objects.equals(id.getIDType(), "sum")) {
                                        if (searchedItems.get(i).get(id.getItemName()) != null) {
                                            if (Objects.equals(id.getIDType(), "int") && searchedItems.get(i).get(id.getItemName()).getAsInt() != 0) {
                                                remove = false;
                                            } else if (Objects.equals(id.getIDType(), "string") && !searchedItems.get(i).get(id.getItemName()).getAsString().isEmpty()) {
                                                remove = false;
                                            } else if (Objects.equals(id.getIDType(), "damage_string") && !Objects.equals(searchedItems.get(i).get(id.getItemName()).getAsString(), "0-0")) {
                                                remove = false;
                                            }
                                        }
                                    } else if (Objects.equals(id.getIDType(), "sum")) {
                                        boolean needAll = true;
                                        boolean need = false;
                                        for (int n = 0; id.getSum().getIds().size() > n; ++n) {
                                            if (id.getSum().getIds().get(n).getItemName() != null) {
                                                if (searchedItems.get(i).get(id.getSum().getIds().get(n).getItemName()) != null) {
                                                    if (Objects.equals(id.getSum().getIds().get(n).getIDType(), "int")) {
                                                        if (searchedItems.get(i).get(id.getSum().getIds().get(n).getItemName()).getAsInt() == 0) {
                                                            needAll = false;
                                                        } else {
                                                            need = true;
                                                        }
                                                    } else if (Objects.equals(id.getSum().getIds().get(n).getIDType(), "string")) {
                                                        if (searchedItems.get(i).get(id.getSum().getIds().get(n).getItemName()).getAsString().isEmpty()) {
                                                            needAll = false;
                                                        } else {
                                                            need = true;
                                                        }
                                                    } else if (Objects.equals(id.getSum().getIds().get(n).getIDType(), "damage_string")) {
                                                        if (Objects.equals(searchedItems.get(i).get(id.getSum().getIds().get(n).getItemName()).getAsString(), "0-0")) {
                                                            needAll = false;
                                                        } else {
                                                            need = true;
                                                        }
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

    public boolean notHaveItemID(Identifications id, JsonObject json, int idPos, int needPos) {
        if (idPos >= needPos) {
            if (!Objects.equals(id.getIDType(), "sum")) {
                if (id.getItemName() != null && json.get(id.getItemName()) != null) {
                    if (Objects.equals(id.getIDType(), "int") && json.get(id.getItemName()).getAsInt() != 0) {
                        return false;
                    } else if (Objects.equals(id.getIDType(), "string") && !json.get(id.getItemName()).getAsString().isEmpty()) {
                        return false;
                    } else {
                        return !Objects.equals(id.getIDType(), "damage_string") || Objects.equals(json.get(id.getItemName()).getAsString(), "0-0");
                    }
                }
            } else if (id.getIDType().equals("sum")) {
                boolean need = false;
                boolean needAll = true;

                for (int n = 0; id.getSum().getIds().size() > n; ++n) {
                    Identifications ids = id.getSum().getIds().get(n);
                    if (ids.getItemName() != null) {
                        if (json.get(ids.getItemName()) != null) {
                            if (Objects.equals(ids.getIDType(), "int")) {
                                if (json.get(ids.getItemName()).getAsInt() == 0) {
                                    needAll = false;
                                } else {
                                    need = true;
                                }
                            } else if (Objects.equals(ids.getIDType(), "string")) {
                                if (json.get(ids.getItemName()).getAsString().isEmpty()) {
                                    needAll = false;
                                } else {
                                    need = true;
                                }
                            } else if (Objects.equals(ids.getIDType(), "damage_string")) {
                                if (Objects.equals(json.get(ids.getItemName()).getAsString(), "0-0")) {
                                    needAll = false;
                                } else {
                                    need = true;
                                }
                            }
                        } else {
                            needAll = false;
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

    public void searchIngFromIDs(List<JComboBox<String>> box) {
        Identifications id_0 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, 0), Identifications.EMPTY);
        Identifications id_1 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, 1), Identifications.EMPTY);
        Identifications id_2 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, 2), Identifications.EMPTY);
        Identifications id_3 = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, 3), Identifications.EMPTY);

        if (!getComboBoxText(box, 0).isEmpty() || !getComboBoxText(box, 1).isEmpty() || !getComboBoxText(box, 2).isEmpty() || !getComboBoxText(box, 3).isEmpty()) {
            if (id_0.getIngName() != null || id_1.getIngName() != null || id_2.getIngName() != null || id_3.getIngName() != null) {
                for (int i = searchedItems.size() - 1; i >= 0; --i) {
                    boolean remove = true;
                    for (int num = 0; 4 > num; ++num) {
                        if (!getComboBoxText(box, num).isEmpty()) {
                            Identifications id = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, num), Identifications.EMPTY);
                            if (id.getIngName() != null && id.getIngFieldPos() != null) {
                                if (notHaveIngID(id_0, searchedItems.get(i), num, 1) && notHaveIngID(id_1, searchedItems.get(i), num, 2) && notHaveIngID(id_2, searchedItems.get(i), num, 3)) {
                                    if (!Objects.equals(id.getIDType(), "sum")) {
                                        if (Objects.equals(id.getIngFieldPos(), "identifications") && searchedItems.get(i).get(id.getIngFieldPos()).getAsJsonObject().get(id.getIngName()) != null && searchedItems.get(i).get(id.getIngFieldPos()).getAsJsonObject().get(id.getIngName()).getAsJsonObject().get("minimum").getAsInt() != 0) {
                                            remove = false;
                                        } else if (Objects.equals(id.getIngFieldPos(), "nothing") && searchedItems.get(i).get(id.getIngName()) != null && searchedItems.get(i).get(id.getIngName()).getAsInt() != 0) {
                                            remove = false;
                                        } else if (!Objects.equals(id.getIngFieldPos(), "identifications") && searchedItems.get(i).get(id.getIngFieldPos()) != null && searchedItems.get(i).get(id.getIngFieldPos()).getAsJsonObject().get(id.getIngName()) != null && searchedItems.get(i).get(id.getIngFieldPos()).getAsJsonObject().get(id.getIngName()).getAsInt() != 0) {
                                            remove = false;
                                        }
                                    } else if (Objects.equals(id.getIDType(), "sum")) {
                                        boolean need = false;
                                        boolean needAll = true;

                                        for (int n = 0; id.getSum().getIds().size() > n; ++n) {
                                            Identifications ids = id.getSum().getIds().get(n);
                                            if (ids.getIngName() != null && ids.getIngFieldPos() != null) {
                                                if (Objects.equals(ids.getIngFieldPos(), "identifications") && searchedItems.get(i).get(ids.getIngFieldPos()) != null && searchedItems.get(i).get(ids.getIngFieldPos()).getAsJsonObject().get(ids.getIngName()) != null) {
                                                    need = true;
                                                } else if (Objects.equals(ids.getIngFieldPos(), "nothing") && searchedItems.get(i).get(ids.getIngName()) != null) {
                                                    if (searchedItems.get(i).get(ids.getIngName()).getAsInt() == 0) {
                                                        needAll = false;
                                                    } else {
                                                        need = true;
                                                    }
                                                } else if (!Objects.equals(ids.getIngFieldPos(), "identifications") && searchedItems.get(i).get(ids.getIngFieldPos()) != null && searchedItems.get(i).get(ids.getIngFieldPos()).getAsJsonObject().get(ids.getIngName()) != null) {
                                                    if (searchedItems.get(i).get(ids.getIngFieldPos()).getAsJsonObject().get(ids.getIngName()).getAsInt() == 0) {
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
                    if (Objects.equals(id.getIngFieldPos(), "identifications") && json.get(id.getIngFieldPos()).getAsJsonObject().get(id.getIngName()).getAsJsonObject().get("minimum").getAsInt() != 0) {
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
                            if (json.get(ids.getIngFieldPos()).getAsJsonObject().get(ids.getIngName()).getAsJsonObject().get("minimum").getAsInt() == 0) {
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

            if (10 + p.getBounds().y + p.getBounds().height > 482) {
                searched.setPreferredSize(new Dimension(1057, 10 + p.getBounds().y + p.getBounds().height));
            } else {
                searched.setPreferredSize(new Dimension(1057, 482));
            }
        }

        SwingUtilities.updateComponentTreeUI(searched);
    }

    public void searchItemFromTier() {
        for (int i = searchedItems.size() - 1; i >= 0; --i) {
            String s = searchedItems.get(i).get("tier").getAsString();
            switch (itemTier.getItemAt(itemTier.getSelectedIndex())) {
                case "No Normal": if (s.equals("Normal")) {
                    searchedItems.remove(i);
                }
                break;
                case "Mythic": if (!s.equals("Mythic")) {
                    searchedItems.remove(i);
                }
                break;
                case "Fabled": if (!s.equals("Fabled")) {
                    searchedItems.remove(i);
                }
                break;
                case "Legendary": if (!s.equals("Legendary")) {
                    searchedItems.remove(i);
                }
                break;
                case "Rare": if (!s.equals("Rare")) {
                    searchedItems.remove(i);
                }
                break;
                case "Unique": if (!s.equals("Unique")) {
                    searchedItems.remove(i);
                }
                break;
                case "Set": if (!s.equals("Set")) {
                    searchedItems.remove(i);
                }
                break;
                case "Normal": if (!s.equals("Normal")) {
                    searchedItems.remove(i);
                }
                break;
            }
        }
    }

    public void searchIngFromTier() {
        for (int i = searchedItems.size() - 1; i >= 0; --i) {
            int t = searchedItems.get(i).get("tier").getAsInt();
            switch (tier.getItemAt(tier.getSelectedIndex())) {
                case "0 Star": if (t != 0) {
                    searchedItems.remove(i);
                }
                break;
                case "1 Star": if (t != 1) {
                    searchedItems.remove(i);
                }
                break;
                case "2 Star": if (t != 2) {
                    searchedItems.remove(i);
                }
                break;
                case "3 Star": if (t != 3) {
                    searchedItems.remove(i);
                }
                break;
            }
        }
    }

    public void searchFromName() {
        for (int i = searchedItems.size() - 1; i >= 0  ; --i) {
            JsonObject json = searchedItems.get(i);
            if (json.get("displayName") == null) {
                if (!json.get("name").getAsString().toLowerCase().contains(searchF.getText().toLowerCase())) {
                    searchedItems.remove(i);
                }
            } else {
                if (!json.get("displayName").getAsString().toLowerCase().contains(searchF.getText().toLowerCase())) {
                    searchedItems.remove(i);
                }
            }
        }
    }

    public void filterItemFromSize() {

    }

    public void filterIngFromSize() {

    }

    public void sortItems(List<JComboBox<String>> box) {
        int si = searchedItems.size() - 1;
        int iu = 0;
        int max = 0;
        for (int i = 0; si > i; ++i) {
            JsonObject j = searchedItems.get(si - i);
            int total = 0;

            for (int num = 0; 4 > num; ++num) {
                Identifications id = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, num), Identifications.EMPTY);
                if (id.getItemName() != null) {
                    if (!Objects.equals(id.getIDType(), "sum")) {
                        if (Objects.equals(id.getIDType(), "int") && j.get(id.getItemName()) != null) {
                            total = total + j.get(id.getItemName()).getAsInt();
                        } else if (Objects.equals(id.getIDType(), "damage_string") && j.get(id.getItemName()) != null && !Objects.equals(j.get(id.getItemName()).getAsString(), "0-0")) {
                            if (j.get(id.getItemName()).getAsString().contains("-")) {
                                String[] ss = j.get(id.getItemName()).getAsString().split("-");
                                int p = Integer.parseInt(ss[0]) + Integer.parseInt(ss[ss.length - 1]);
                                total = total + (p / 2);
                            }
                        }  else if (Objects.equals(id.getIDType(), "string") && Objects.equals(id.getItemName(), "attackSpeed") && j.get(id.getItemName()) != null) {
                            switch (j.get(id.getItemName()).getAsString()) {
                                case "SUPER_FAST": total = 7;
                                break;
                                case "VERY_FAST": total = 6;
                                break;
                                case "FAST": total = 5;
                                break;
                                case "NORMAL": total = 4;
                                break;
                                case "SLOW": total = 3;
                                break;
                                case "VERY_SLOW": total = 2;
                                break;
                                case "SUPER_SLOW": total = 1;
                                break;
                            }
                        }
                    } else if (Objects.equals(id.getIDType(), "sum")) {
                        double sum_total = 0;
                        int sum_multi = 0;
                        for (int n = 0; id.getSum().getIds().size() > n; ++n) {
                            Identifications ids = id.getSum().getIds().get(n);
                            if (Objects.equals(ids.getIDType(), "int") && j.get(ids.getItemName()) != null) {
                                sum_total = sum_total + j.get(ids.getItemName()).getAsInt();
                            } else if (Objects.equals(ids.getIDType(), "damage_string") && j.get(ids.getItemName()) != null && !Objects.equals(j.get(ids.getItemName()).getAsString(), "0-0")) {
                                String[] ss = j.get(ids.getItemName()).getAsString().split("-");
                                int p = Integer.parseInt(ss[0]) + Integer.parseInt(ss[ss.length - 1]);
                                sum_total = sum_total + (p / 2);
                            }
                        }
                        if (Objects.equals(id.getSum().getCalcType(), "multi") || Objects.equals(id.getSum().getCalcType(), "total_dps") && id.getSum().getAndIDs() != null) {
                            for (int n = 0; id.getSum().getAndIDs().size() > n; ++n) {
                                Identifications ids = id.getSum().getAndIDs().get(n);
                                if (Objects.equals(ids.getIDType(), "int") && j.get(ids.getItemName()) != null) {
                                    sum_multi = sum_multi + j.get(ids.getItemName()).getAsInt();
                                }
                            }
                            sum_total = sum_total * sum_multi / 100;
                        }
                        if (Objects.equals(id.getSum().getCalcType(), "base_dps") || Objects.equals(id.getSum().getCalcType(), "total_dps")) {
                            if (j.get("attackSpeed") != null) {
                                switch (j.get("attackSpeed").getAsString()) {
                                    case "SUPER_FAST": sum_total = sum_total * 4.3;
                                    break;
                                    case "VERY_FAST": sum_total = sum_total * 3.1;
                                    break;
                                    case "FAST": sum_total = sum_total * 2.5;
                                    break;
                                    case "NORMAL": sum_total = sum_total * 2.05;
                                    break;
                                    case "SLOW": sum_total = sum_total * 1.5;
                                    break;
                                    case "VERY_SLOT": sum_total = sum_total * 0.83;
                                    break;
                                    case "SUPER_SLOW": sum_total = sum_total * 0.51;
                                }
                            }
                        }
                        total = total + (int) sum_total;
                    }
                }
            }

            if (total > max) {
                max = total;
                iu = si - i;
            }
        }

        JPanel previous = null;
        JPanel fourP = null;
        if (itemDisplays.size() >= 1) {
            previous = itemDisplays.get(itemDisplays.size() - 1);
        }
        if (itemDisplays.size() >= 4) {
            fourP = itemDisplays.get(itemDisplays.size() - 4);
        }
        itemDisplays.add(new ItemUITemplate(searchedItems.get(iu), false, previous, fourP));
        searched.add(itemDisplays.get(itemDisplays.size() - 1));
        searchedItems.remove(iu);
    }

    public void sortIngredients(List<JComboBox<String>> box) {
        int si = searchedItems.size() - 1;
        int iu = 0;
        int max = 0;
        for (int i = 0; si > i; ++i) {
            JsonObject j = searchedItems.get(si - i);
            int total = 0;

            for (int num = 0; 4 > num; ++num) {
                Identifications id = IDBoxAdapter.ID_LIST.getOrDefault(getComboBoxText(box, num), Identifications.EMPTY);
                if (id.getIngName() != null) {
                    if (!Objects.equals(id.getIDType(), "sum")) {

                    } else if (Objects.equals(id.getIDType(), "sum")) {

                    }
                }
            }

            if (total > max) {
                max = total;
                iu = i;
            }
        }

        JPanel previous = null;
        JPanel fourP = null;
        if (itemDisplays.size() >= 1) {
            previous = itemDisplays.get(itemDisplays.size() - 1);
        }
        if (itemDisplays.size() >= 4) {
            fourP = itemDisplays.get(itemDisplays.size() - 4);
        }
        itemDisplays.add(new ItemUITemplate(searchedItems.get(iu), false, previous, fourP));
        searched.add(itemDisplays.get(itemDisplays.size() - 1));
        searchedItems.remove(iu);
    }

    public void removeAllSearchedItemOrIngredients() {
        if (searchedItems.size() > 0) {
            searchedItems.subList(0, searchedItems.size()).clear();
        }
    }
}
