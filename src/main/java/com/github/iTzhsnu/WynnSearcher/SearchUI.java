package com.github.iTzhsnu.WynnSearcher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SearchUI extends JFrame implements ActionListener {

    //Item or Ingredient
    private final JComboBox<String> itemOrIngredient = new JComboBox<>();

    //Item Check Boxes
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
        setTitle("Wynncraft Searcher");
        setBounds(100, 300, 1000, 800);
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

        //Add Contents
        contentPane.add(name);
        contentPane.add(searchB);
        contentPane.add(searchF);
        contentPane.add(itemOrIngredient);


    }

    public static void main(String[] args) {
        new SearchUI().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //e.getSource() == (Button)

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
            for (String s : IDBoxAdapter.displayIDList) {
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

}
