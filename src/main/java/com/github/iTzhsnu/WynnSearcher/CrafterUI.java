package com.github.iTzhsnu.WynnSearcher;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;

public class CrafterUI implements ActionListener {
    private final Container pane;
    private final List<JsonObject> ingJson;
    private final JLabel ingAPIConnect = new JLabel();
    private final JComboBox<String> recipeType = new JComboBox<>();
    private final JComboBox<String> attackSpeed = new JComboBox<>();
    private final JComboBox<String> level = new JComboBox<>();
    private final List<JComboBox<String>> ingBox = new ArrayList<>();
    private final List<JLabel> texts = new ArrayList<>();
    private final JComboBox<String> material1 = new JComboBox<>();
    private final JComboBox<String> material2 = new JComboBox<>();
    private final JButton create = new JButton("Create");
    private final JTextField output = new JTextField();
    private final JPanel created = new JPanel();
    private final JScrollPane createdScroll;
    private final JPanel ingPanel = new JPanel();
    private final JScrollPane ingScroll;
    private final JButton load = new JButton("Load");
    private final List<JsonObject> recipeJson;

    private static final Map<Integer, Identifications> INT_TO_ID = new HashMap<Integer, Identifications>() {{
        put(0, Identifications.STRENGTH_REQ);
        put(1, Identifications.DEXTERITY_REQ);
        put(2, Identifications.INTELLIGENCE_REQ);
        put(3, Identifications.DEFENSE_REQ);
        put(4, Identifications.AGILITY_REQ);

        put(5, Identifications.STRENGTH);
        put(6, Identifications.DEXTERITY);
        put(7, Identifications.INTELLIGENCE);
        put(8, Identifications.DEFENSE);
        put(9, Identifications.AGILITY);

        put(10, Identifications.HEALTH_BONUS);
        put(11, Identifications.RAW_HEALTH_REGEN);
        put(12, Identifications.HEALTH_REGEN_PERCENT);
        put(13, Identifications.LIFE_STEAL);

        put(14, Identifications.EARTH_DEFENSE_PERCENT);
        put(15, Identifications.THUNDER_DEFENSE_PERCENT);
        put(16, Identifications.WATER_DEFENSE_PERCENT);
        put(17, Identifications.FIRE_DEFENSE_PERCENT);
        put(18, Identifications.AIR_DEFENSE_PERCENT);

        put(19, Identifications.EARTH_DAMAGE_PERCENT);
        put(20, Identifications.THUNDER_DAMAGE_PERCENT);
        put(21, Identifications.WATER_DAMAGE_PERCENT);
        put(22, Identifications.FIRE_DAMAGE_PERCENT);
        put(23, Identifications.AIR_DAMAGE_PERCENT);

        put(24, Identifications.RAW_SPELL_DAMAGE);
        put(25, Identifications.RAW_MELEE_DAMAGE);
        put(26, Identifications.SPELL_DAMAGE_PERCENT);
        put(27, Identifications.MELEE_DAMAGE_PERCENT);
        put(28, Identifications.POISON);

        put(29, Identifications.ATTACK_SPEED_BONUS);

        put(30, Identifications.MANA_REGEN);
        put(31, Identifications.MANA_STEAL);

        put(32, Identifications.WALK_SPEED);
        put(33, Identifications.SPRINT_BONUS);
        put(34,  Identifications.SPRINT_REGEN);
        put(35, Identifications.JUMP_HEIGHT);

        put(36, Identifications.THORNS);
        put(37, Identifications.REFLECTION);
        put(38, Identifications.EXPLODING);
        put(39, Identifications.STEALING);
        put(40, Identifications.COMBAT_XP_BONUS);
        put(41, Identifications.GATHERING_XP_BONUS);
        put(42, Identifications.GATHERING_SPEED_BONUS);
        put(43, Identifications.LOOT_BONUS);
        put(44, Identifications.LOOT_QUALITY);
        put(45, Identifications.SOUL_POINT_REGEN);
    }};

    private static final Map<String, String> TYPE_TO_SKILL = new HashMap<String, String>() {{
        put("Helmet", "Armouring");
        put("Chestplate", "Armouring");
        put("Leggings", "Tailoring");
        put("Boots", "Tailoring");
        put("Ring", "Jeweling");
        put("Bracelet", "Jeweling");
        put("Necklace", "Jeweling");
        put("Spear", "Weaponsmithing");
        put("Dagger", "Weaponsmithing");
        put("Bow", "Woodworking");
        put("Wand", "Woodworking");
        put("Relik", "Woodworking");
        put("Scroll", "Scribing");
        put("Food", "Cooking");
        put("Potion", "Alchemism");
    }};

    public CrafterUI(Container pane, List<JsonObject> ingJson, List<JsonObject> recipeJson, String recipeAPIConnect, JLabel ingAPIConnect) {
        this.pane = pane;
        this.ingJson = ingJson;
        this.recipeJson = recipeJson;

        this.ingAPIConnect.setText(ingAPIConnect.getText());
        this.ingAPIConnect.setForeground(ingAPIConnect.getForeground());
        this.ingAPIConnect.setBounds(530, 5, 150, 20);

        JLabel recipeConnect = new JLabel(recipeAPIConnect);
        recipeConnect.setBounds(530, 30, 150, 20);
        if (recipeAPIConnect.equals("Recipe API Connected")) {
            recipeConnect.setForeground(new Color(0, 169, 104));
        } else {
            recipeConnect.setForeground(new Color(255, 0, 0));
        }

        //Recipe Type
        JLabel typeText = new JLabel("Type:");
        typeText.setBounds(10, 40, 30, 20);
        recipeType.setBounds(45, 40, 90, 20);
        recipeType.addItem("Helmet");
        recipeType.addItem("Chestplate");
        recipeType.addItem("Leggings");
        recipeType.addItem("Boots");
        recipeType.addItem("Ring");
        recipeType.addItem("Bracelet");
        recipeType.addItem("Necklace");
        recipeType.addItem("Bow");
        recipeType.addItem("Wand");
        recipeType.addItem("Relik");
        recipeType.addItem("Spear");
        recipeType.addItem("Dagger");
        recipeType.addItem("Scroll");
        recipeType.addItem("Food");
        recipeType.addItem("Potion");

        //Attack Speed
        attackSpeed.setBounds(260, 40, 80, 20);
        attackSpeed.addItem("SLOW");
        attackSpeed.addItem("NORMAL");
        attackSpeed.addItem("FAST");

        //Level
        JLabel lvText = new JLabel("Lv.");
        lvText.setBounds(145, 40, 20, 20);
        level.setBounds(165, 40, 75, 20);
        setLevel();

        //Ingredient
        setIngBox();

        //Material Tier
        JLabel mat1 = new JLabel("Material 1 Tier:");
        JLabel mat2 = new JLabel("Material 2 Tier:");
        mat1.setBounds(350, 70, 90, 20);
        mat2.setBounds(350, 100, 90, 20);
        material1.setBounds(440, 70, 40, 20);
        material2.setBounds(440, 100, 40, 20);
        setMaterial(material1);
        setMaterial(material2);

        //Create
        create.setBounds(350, 10, 80, 40);
        create.addActionListener(this);

        load.setBounds(440, 10, 80, 40);
        load.addActionListener(this);

        //Displays
        JLabel oText = new JLabel("Output / Load:");
        oText.setBounds(350, 130, 80, 20);
        output.setBounds(430, 130, 160, 20);

        ingPanel.setBorder(new LineBorder(Color.BLACK));
        created.setBorder(new LineBorder(Color.BLACK));
        ingPanel.setLayout(null);
        created.setLayout(null);
        ingPanel.setPreferredSize(new Dimension(530, 595));
        created.setPreferredSize(new Dimension(270, 745));

        ingScroll = new JScrollPane(ingPanel);
        createdScroll = new JScrollPane(created);
        ingScroll.getVerticalScrollBar().setUnitIncrement(20);
        createdScroll.getVerticalScrollBar().setUnitIncrement(20);
        ingScroll.setBounds(10, 160, 548, 598);
        createdScroll.setBounds(700, 10, 288, 748);

        texts.add(recipeConnect);
        texts.add(typeText);
        texts.add(lvText);
        texts.add(mat1);
        texts.add(mat2);
        texts.add(oText);

        pane.add(this.ingAPIConnect);
        pane.add(recipeType);
        pane.add(attackSpeed);
        pane.add(level);
        pane.add(material1);
        pane.add(material2);
        pane.add(create);
        pane.add(output);
        pane.add(ingScroll);
        pane.add(createdScroll);
        pane.add(load);

        for (JLabel l : texts) {
            pane.add(l);
        }
    }

    public void setCrafterVisible(boolean visible) {
        ingAPIConnect.setVisible(visible);
        recipeType.setVisible(visible);
        attackSpeed.setVisible(visible);
        level.setVisible(visible);
        for (JComboBox<String> b : ingBox) {
            b.setVisible(visible);
        }
        for (JLabel l : texts) {
            l.setVisible(visible);
        }
        material1.setVisible(visible);
        material2.setVisible(visible);
        create.setVisible(visible);
        output.setVisible(visible);
        createdScroll.setVisible(visible);
        ingScroll.setVisible(visible);
        load.setVisible(visible);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == create) {
            createCraftItem();
        }
        if (e.getSource() == load) {
            loadCraftItem(true);
        }
    }

    public void setIngBox() {
        for (int i = 0; 6 > i; ++i) {
            JComboBox<String> box = new JComboBox<>();
            int pos = (int) Math.floor(i / 2F);

            for (JsonObject j : ingJson) {
                if (j.get("displayName") != null) {
                    box.addItem(j.get("displayName").getAsString());
                } else if (j.get("name") != null) {
                    box.addItem(j.get("name").getAsString());
                }
            }

            if (i % 2 == 0) {
                box.setBounds(10, 70 + (pos * 30), 160, 20);
            } else {
                box.setBounds(180, 70 + (pos * 30), 160, 20);
            }
            box.setEditable(true);
            box.setSelectedIndex(-1);
            JTextField textField = (JTextField) box.getEditor().getEditorComponent();
            textField.addKeyListener(new Adapter(box, ingJson));

            ingBox.add(box);
            pane.add(ingBox.get(ingBox.size() - 1));
        }
    }

    public void setLevel() {
        int min = 1;
        int max = 3;
        while (103 >= min) {
            level.addItem(min + "-" + max);

            if (min % 10 == 7) {
                min = max + 1;
                max += 4;
            } else {
                min = max;
                max += 2;
            }
        }
    }

    public void setMaterial(JComboBox<String> box) {
        box.addItem("1");
        box.addItem("2");
        box.addItem("3");
    }

    public void createCraftItem() {
        StringBuilder sb = new StringBuilder();
        sb.append("CR-{\"ing\":[");
        for (int i = 0; ingBox.size() > i; ++i) {
            String s = "\"" + ((JTextField) ingBox.get(i).getEditor().getEditorComponent()).getText() + "\"";
            sb.append(s);
            if (ingBox.size() - 1 != i) {
                sb.append(",");
            } else {
                sb.append("],");
            }
        }
        String recType = recipeType.getItemAt(recipeType.getSelectedIndex());
        String lv = level.getItemAt(level.getSelectedIndex());
        String lvS = "\"level\":\"" + lv + "\",";
        String type = "\"type\":\"" + recType + "\",";
        String mat1 = "\"material1\":" + material1.getItemAt(material1.getSelectedIndex()) + ",";
        String mat2 = "\"material2\":" + material2.getItemAt(material2.getSelectedIndex()) + "}";

        sb.append(lvS);
        sb.append(type);

        if (recType.equals("Bow") || recType.equals("Wand") || recType.equals("Relik") || recType.equals("Spear") || recType.equals("Dagger")) {
            String atkSpd = "\"attackSpeed\":\"" + attackSpeed.getItemAt(attackSpeed.getSelectedIndex()) + "\",";
            sb.append(atkSpd);
        }

        sb.append(mat1);
        sb.append(mat2);

        output.setText(sb.toString());
        loadCraftItem(false);
    }

    public void loadCraftItem(boolean updateIngBoxes) {
        created.removeAll();
        ingPanel.removeAll();

        String s = output.getText();
        if (s.contains("CR-")) {
            setIngDisplay(JsonParser.parseString(s.replace("CR-", "")).getAsJsonObject().get("ing").getAsJsonArray());

            JPanel itemUI = setDisplay(getCraftItemJson(recipeJson, ingJson, s, true), getCraftItemJson(recipeJson, ingJson, s, false), null, null, 270);
            created.add(itemUI);
            if (itemUI.getBounds().y + itemUI.getBounds().height > 745) {
                created.setPreferredSize(new Dimension(270, itemUI.getBounds().y + itemUI.getBounds().height));
            } else {
                created.setPreferredSize(new Dimension(270, 745));
            }
            SwingUtilities.updateComponentTreeUI(created);
            if (updateIngBoxes) {
                String sJ = s.replace("CR-", "");
                JsonObject j = JsonParser.parseString(sJ).getAsJsonObject();
                for (int i = 0; ingBox.size() > i; ++i) {
                    if (j.get("ing") != null && j.get("ing").getAsJsonArray().get(i) != null)
                        ((JTextField) ingBox.get(i).getEditor().getEditorComponent()).setText(j.get("ing").getAsJsonArray().get(i).getAsString());
                }
                level.setSelectedItem(j.get("level").getAsString());
                recipeType.setSelectedItem(j.get("type").getAsString());
                material1.setSelectedItem(String.valueOf(j.get("material1").getAsInt()));
                material2.setSelectedItem(String.valueOf(j.get("material2").getAsInt()));
                if (j.get("attackSpeed") != null) {
                    attackSpeed.setSelectedItem(j.get("attackSpeed").getAsString());
                }
            }
        }
    }

    public void setIngDisplay(JsonArray ing) {
        List<JPanel> lp = new ArrayList<>();
        List<JsonObject> lj = new ArrayList<>();

        for (JsonElement je : ing) {
            if (!je.getAsString().isEmpty()) {
                for (JsonObject jo : ingJson) {
                    if (jo.get("displayName") != null && jo.get("displayName").getAsString().equals(je.getAsString())) {
                        lj.add(jo);
                        break;
                    } else if (jo.get("name") != null && jo.get("name").getAsString().equals(je.getAsString())) {
                        lj.add(jo);
                        break;
                    }
                }
            }
        }

        for (int i = 0; lj.size() > i; ++i) {
            JsonObject j = lj.get(i);
            if (i == 0) {
                ItemUITemplate p = new ItemUITemplate(j, true, null, null, 530, 0, false);
                lp.add(p);
            } else if (i == 1) {
                ItemUITemplate p = new ItemUITemplate(j, true, lp.get(0), null, 530, 0, false);
                lp.add(p);
            } else {
                ItemUITemplate p = new ItemUITemplate(j, true, lp.get(i - 1), lp.get(i - 2), 530, 0, false);
                lp.add(p);
            }
        }

        if (lp.size() > 0) {
            int si = lp.size() - 1;
            int iu = -1;
            int max = 0;
            for (int i = 0; si >= i && 2 > i; ++i) {
                int y = lp.get(si - i).getBounds().y + lp.get(si - i).getBounds().height;
                if (y > max) {
                    max = y;
                    iu = i;
                }
            }
            JPanel p = lp.get(si);

            if (iu > 0) p = lp.get(si - iu);

            if (10 + p.getBounds().y + p.getBounds().height > 595) {
                ingPanel.setPreferredSize(new Dimension(530, 10 + p.getBounds().y + p.getBounds().height));
            } else {
                ingPanel.setPreferredSize(new Dimension(530, 595));
            }
        }

        for (JPanel p : lp) {
            ingPanel.add(p);
        }

        SwingUtilities.updateComponentTreeUI(ingPanel);
    }

    public static JPanel setDisplay(JsonObject minJ, JsonObject json, JPanel previous, JPanel above, int uiWidth) {
        JPanel p = new JPanel();
        List<JLabel> label = new ArrayList<>();

        if (json.get("name") != null) {
            label.add(new JLabel(json.get("name").getAsString()));
        }

        if (json.get("type") != null) {
            label.add(new JLabel("Type: " + json.get("type").getAsString()));
        } else if (json.get("accessoryType") != null) {
            label.add(new JLabel("Type: " + json.get("accessoryType").getAsString()));
        }

        if (json.get("attackSpeed") != null) {
            label.add(new JLabel("Attack Speed: " + json.get("attackSpeed").getAsString()));
        }

        label.add(new JLabel(" "));

        if (json.get("health") != null && json.get("health").getAsInt() != 0) {
            label.add(new JLabel(ItemUITemplate.setPlus(minJ.get("health").getAsInt()) + " Health " + ItemUITemplate.setPlus(json.get("health").getAsInt())));
        }
        if (json.get("earthDefense") != null && json.get("earthDefense").getAsInt() != 0) {
            label.add(new JLabel(ItemUITemplate.setPlus(minJ.get("earthDefense").getAsInt()) + " Earth Defense " + ItemUITemplate.setPlus(json.get("earthDefense").getAsInt())));
        }
        if (json.get("thunderDefense") != null && json.get("thunderDefense").getAsInt() != 0) {
            label.add(new JLabel(ItemUITemplate.setPlus(minJ.get("thunderDefense").getAsInt()) + " Thunder Defense " + ItemUITemplate.setPlus(json.get("thunderDefense").getAsInt())));
        }
        if (json.get("waterDefense") != null && json.get("waterDefense").getAsInt() != 0) {
            label.add(new JLabel(ItemUITemplate.setPlus(minJ.get("waterDefense").getAsInt()) + " Water Defense " + ItemUITemplate.setPlus(json.get("waterDefense").getAsInt())));
        }
        if (json.get("fireDefense") != null && json.get("fireDefense").getAsInt() != 0) {
            label.add(new JLabel(ItemUITemplate.setPlus(minJ.get("fireDefense").getAsInt()) + " Fire Defense " + ItemUITemplate.setPlus(json.get("fireDefense").getAsInt())));
        }
        if (json.get("airDefense") != null && json.get("airDefense").getAsInt() != 0) {
            label.add(new JLabel(ItemUITemplate.setPlus(minJ.get("airDefense").getAsInt()) + " Air Defense " + ItemUITemplate.setPlus(json.get("airDefense").getAsInt())));
        }

        if (json.get("damage") != null && !Objects.equals(json.get("damage").getAsString(), "0-0")) {
            label.add(new JLabel(minJ.get("damage").getAsString() + " Neutral Damage " + json.get("damage").getAsString()));
        }
        if (json.get("earthDamage") != null && !Objects.equals(json.get("earthDamage").getAsString(), "0-0")) {
            label.add(new JLabel(minJ.get("earthDamage").getAsString() + " Earth Damage " + json.get("earthDamage").getAsString()));
        }
        if (json.get("thunderDamage") != null && !Objects.equals(json.get("thunderDamage").getAsString(), "0-0")) {
            label.add(new JLabel(minJ.get("thunderDamage").getAsString() + " Thunder Damage " + json.get("thunderDamage").getAsString()));
        }
        if (json.get("waterDamage") != null && !Objects.equals(json.get("waterDamage").getAsString(), "0-0")) {
            label.add(new JLabel(minJ.get("waterDamage").getAsString() + " Water Damage " + json.get("waterDamage").getAsString()));
        }
        if (json.get("fireDamage") != null && !Objects.equals(json.get("fireDamage").getAsString(), "0-0")) {
            label.add(new JLabel(minJ.get("fireDamage").getAsString() + " Fire Damage " + json.get("fireDamage").getAsString()));
        }
        if (json.get("airDamage") != null && !Objects.equals(json.get("airDamage").getAsString(), "0-0")) {
            label.add(new JLabel(minJ.get("airDamage").getAsString() + " Air Damage " + json.get("airDamage").getAsString()));
        }

        label.add(new JLabel(" "));

        if (json.get("level") != null) {
            label.add(new JLabel("Combat Lv. Min: " + minJ.get("level").getAsInt() + "-" + json.get("level").getAsInt()));
        }
        if (minJ.get("strength").getAsInt() != 0 || json.get("strength").getAsInt() != 0) {
            label.add(new JLabel("Strength Req: " + json.get("strength").getAsInt()));
        }
        if (minJ.get("dexterity").getAsInt() != 0 || json.get("dexterity").getAsInt() != 0) {
            label.add(new JLabel("Dexterity Req: " + json.get("dexterity").getAsInt()));
        }
        if (minJ.get("intelligence").getAsInt() != 0 || json.get("intelligence").getAsInt() != 0) {
            label.add(new JLabel("Intelligence Req: " + json.get("intelligence").getAsInt()));
        }
        if (minJ.get("defense").getAsInt() != 0 || json.get("defense").getAsInt() != 0) {
            label.add(new JLabel("Defense Req: " + json.get("defense").getAsInt()));
        }
        if (minJ.get("agility").getAsInt() != 0 || json.get("agility").getAsInt() != 0) {
            label.add(new JLabel("Agility Req: " + json.get("agility").getAsInt()));
        }

        label.add(new JLabel(" "));

        for (int i = 5; 45 >= i; ++i) {
            Identifications id = INT_TO_ID.get(i);
            if (minJ.get(id.getItemName()).getAsInt() != 0 || json.get(id.getItemName()).getAsInt() != 0) label.add(new JLabel(ItemUITemplate.setPlus(minJ.get(id.getItemName()).getAsInt()) + id.getDisplaySp() + " " + id.getDisplayName() + " " + ItemUITemplate.setPlus(json.get(id.getItemName()).getAsInt()) + id.getDisplaySp()));
        }

        label.add(new JLabel(" "));

        if (json.get("sockets") != null && json.get("sockets").getAsInt() != 0) {
            label.add(new JLabel("Powder Slots: " + json.get("sockets").getAsInt()));
        }
        if (json.get("durability") != null && json.get("durability").getAsInt() != 0) {
            label.add(new JLabel("Durability: " + minJ.get("durability").getAsInt() + "-" + json.get("durability").getAsInt()));
        }
        if (json.get("duration") != null && json.get("duration").getAsInt() != 0) {
            label.add(new JLabel("Duration: " + minJ.get("duration").getAsInt() + "-" + json.get("duration").getAsInt()));
        }

        for (JLabel jLabel : label) {
            p.add(jLabel);
        }

        if (previous != null) {
            Rectangle x = previous.getBounds();
            boolean down = x.x + x.width + 10 > uiWidth - 255;

            if (down) {
                Rectangle y = above.getBounds();
                p.setBounds(10, y.y + y.height + 10, 250, (label.size() + 1) * 16);
            } else {
                if (above != null) {
                    Rectangle y = above.getBounds();
                    p.setBounds(10 + x.x + x.width, y.y + y.height + 10, 250, (label.size() + 1) * 16);
                } else {
                    p.setBounds(10 + x.x + x.width, 10, 250, (label.size() + 1) * 16);
                }
            }
        } else {
            p.setBounds(10, 10, 250, (label.size() + 1) * 16);
        }
        p.setBackground(new Color(0, 175, 204));
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setVisible(true);

        return p;
    }

    public static JsonObject getCraftItemJson(List<JsonObject> recipes, List<JsonObject> ing, String s, boolean getMin) {
        if (s.contains("CR-")) {
            String sJ = s.replace("CR-", "");
            JsonObject itemJ = JsonParser.parseString(sJ).getAsJsonObject();
            JsonObject recipeJ = getRecipe(recipes, itemJ.get("type").getAsString(), itemJ.get("level").getAsString());
            StringBuilder sb = new StringBuilder();
            String getMinOrMax = "maximum";
            String[] lvs = itemJ.get("level").getAsString().split("-");
            float matTB = getMatTB(itemJ.get("type").getAsString(), itemJ.get("material1").getAsInt(), itemJ.get("material2").getAsInt());
            int durabilityOrDuration = 0;
            if (getMin) getMinOrMax = "minimum";
            sb.append("{");

            boolean ingEmpty = true;
            for (JsonElement je : itemJ.get("ing").getAsJsonArray()) {
                if (!je.getAsString().isEmpty()) {
                    ingEmpty = false;
                    break;
                }
            }

            switch (itemJ.get("type").getAsString()) {
                case "Bow":
                case "Wand":
                case "Relik":
                case "Spear":
                case "Dagger":
                {
                    int lvM = Integer.parseInt(itemJ.get("level").getAsString().split("-")[0]);
                    int sockets;
                    if (lvM < 30) {
                        sockets = 1;
                    } else if (lvM < 70) {
                        sockets = 2;
                    } else {
                        sockets = 3;
                    }
                    float atkSpdBoost = 1F;
                    if (itemJ.get("attackSpeed").getAsString().equals("Fast")) {
                        atkSpdBoost = 0.82F;
                    } else if (itemJ.get("attackSpeed").getAsString().equals("Slow")) {
                        atkSpdBoost = 1.36667F;
                    }
                    int min = (int) Math.floor(recipeJ.get("healthOrDamage").getAsJsonObject().get(getMinOrMax).getAsInt() * 0.9F * atkSpdBoost * matTB);
                    int max = (int) Math.floor(recipeJ.get("healthOrDamage").getAsJsonObject().get(getMinOrMax).getAsInt() * 1.1F * atkSpdBoost * matTB);
                    String damage = "\"damage\":\"" + min + "-" + max + "\",\"sockets\":" + sockets + ",";
                    sb.append(damage);
                    break;
                }
                case "Helmet":
                case "Chestplate":
                case "Leggings":
                case "Boots":
                {
                    int lvM = Integer.parseInt(itemJ.get("level").getAsString().split("-")[0]);
                    int sockets;
                    if (lvM < 30) {
                        sockets = 1;
                    } else if (lvM < 70) {
                        sockets = 2;
                    } else {
                        sockets = 3;
                    }
                    int h = (int) Math.floor(recipeJ.get("healthOrDamage").getAsJsonObject().get(getMinOrMax).getAsInt() * matTB);
                    String health = "\"health\":" + h + ",\"sockets\":" + sockets + ",";
                    sb.append(health);
                    break;
                }
                case "Scroll":
                case "Potion":
                case "Food":
                {
                    if (ingEmpty) {
                        int h = (int) Math.floor(recipeJ.get("healthOrDamage").getAsJsonObject().get(getMinOrMax).getAsInt() * matTB);
                        String healthAndDuration = "\"health\":" + h + ",";
                        sb.append(healthAndDuration);
                    }
                    break;
                }
            }

            String lvString = "\"level\":" + lvs[lvs.length - 1] + ",";
            if (getMin) lvString = "\"level\":" + lvs[0] + ",";
            sb.append(lvString);

            String typeString = "\"type\":\"" + itemJ.get("type").getAsString() + "\",\"name\":\"Crafted " + itemJ.get("type").getAsString() + "\",";
            if (itemJ.get("type").getAsString().equals("Ring") || itemJ.get("type").getAsString().equals("Bracelet") || itemJ.get("type").getAsString().equals("Necklace")) typeString = "\"accessoryType\":\"" + itemJ.get("type").getAsString() + "\",\"name\":\"Crafted " + itemJ.get("type").getAsString() + "\",";
            sb.append(typeString);

            List<JsonObject> lJ = new ArrayList<>();
            float[] ingEffective = new float[] {1F, 1F, 1F, 1F, 1F, 1F};
            int charges = 0;

            if (!ingEmpty) {
                for (int i = 0; itemJ.get("ing").getAsJsonArray().size() > i; ++i) {
                    JsonElement je = itemJ.get("ing").getAsJsonArray().get(i);
                    lJ.add(JsonParser.parseString("{}").getAsJsonObject());
                    if (!je.getAsString().isEmpty()) {
                        for (JsonObject jo : ing) {
                            if (jo.get("displayName") != null && jo.get("displayName").getAsString().equals(je.getAsString())) {
                                lJ.set(i, jo);
                                break;
                            } else if (jo.get("name") != null && jo.get("name").getAsString().equals(je.getAsString())) {
                                lJ.set(i, jo);
                                break;
                            }
                        }

                        if (lJ.get(i).get("skills") != null) {
                            boolean remove = true;
                            for (int skillI = 0; lJ.get(i).get("skills").getAsJsonArray().size() > skillI; ++skillI) {
                                if (lJ.get(i).get("skills").getAsJsonArray().get(skillI).getAsString().equalsIgnoreCase(TYPE_TO_SKILL.get(itemJ.get("type").getAsString()))) {
                                    remove = false;
                                    break;
                                }
                            }
                            if (remove) lJ.set(i, JsonParser.parseString("{}").getAsJsonObject());
                        } else {
                            lJ.set(i, JsonParser.parseString("{}").getAsJsonObject());
                        }
                        if (lJ.get(i).get("level") != null) {
                            if (lJ.get(i).get("level").getAsInt() > Integer.parseInt(lvs[lvs.length - 1])) lJ.set(i, JsonParser.parseString("{}").getAsJsonObject());
                        } else {
                            lJ.set(i, JsonParser.parseString("{}").getAsJsonObject());
                        }

                        JsonObject j = lJ.get(i);

                        if (j.get("ingredientPositionModifiers") != null) {
                            JsonObject jo = j.get("ingredientPositionModifiers").getAsJsonObject();
                            if (jo.get("above") != null && jo.get("above").getAsInt() != 0) {
                                if (i == 2 || i == 3 || i == 4 || i == 5) ingEffective[i - 2] += (jo.get("above").getAsInt() / 100F);
                                if (i == 4 || i == 5) ingEffective[i - 4] += (jo.get("above").getAsInt() / 100F);
                            }
                            if (jo.get("under") != null && jo.get("under").getAsInt() != 0) {
                                if (i == 0 || i == 1 || i == 2 | i == 3) ingEffective[i + 2] += (jo.get("under").getAsInt() / 100F);
                                if (i == 0 || i == 1) ingEffective[i + 4] += (jo.get("under").getAsInt() / 100F);
                            }
                            if (jo.get("right") != null && jo.get("right").getAsInt() != 0) {
                                int pos = i + 1;
                                if (pos == 1 || pos == 3 || pos == 5)
                                    ingEffective[pos] += (jo.get("right").getAsInt() / 100F);
                            }
                            if (jo.get("left") != null && jo.get("left").getAsInt() != 0) {
                                int pos = i - 1;
                                if (pos == 0 || pos == 2 || pos == 4)
                                    ingEffective[pos] += (jo.get("left").getAsInt() / 100F);
                            }
                            if (jo.get("touching") != null && jo.get("touching").getAsInt() != 0) {
                                float touching = jo.get("touching").getAsInt() / 100F;
                                if (i == 2 || i == 3 || i == 4 || i == 5) ingEffective[i - 2] += touching; //Above
                                if (i == 0 || i == 1 || i == 2 || i == 3) ingEffective[i + 2] += touching; //Under
                                if (i == 0 || i == 2 || i == 4) ingEffective[i + 1] += touching; //Right
                                if (i == 1 || i == 3 || i == 5) ingEffective[i - 1] += touching; //Left
                            }
                            if (jo.get("notTouching") != null && jo.get("notTouching").getAsInt() != 0) {
                                float notTouching = jo.get("notTouching").getAsInt() / 100F;
                                if (i >= 3) ingEffective[0] += notTouching; //0
                                if (i == 2 || i >= 4) ingEffective[1] += notTouching; //1
                                if (i == 1 || i == 5) ingEffective[2] += notTouching; //2
                                if (i == 0 || i == 4) ingEffective[3] += notTouching; //3
                                if (i == 0 || i == 1 || i == 3) ingEffective[4] += notTouching; //4
                                if (i <= 2) ingEffective[5] += notTouching; //5
                            }
                        }

                        if (itemJ.get("type").getAsString().equals("Scroll") || itemJ.get("type").getAsString().equals("Potion") || itemJ.get("type").getAsString().equals("Food")) {
                            if (j.get("consumableOnlyIDs") != null) {
                                JsonObject jo = j.get("consumableOnlyIDs").getAsJsonObject();
                                if (jo.get("duration") != null) durabilityOrDuration += jo.get("duration").getAsInt();
                                if (jo.get("charges") != null) charges += jo.get("charges").getAsInt();
                            }
                        } else {
                            if (j.get("itemOnlyIDs") != null) {
                                JsonObject jo = j.get("itemOnlyIDs").getAsJsonObject();
                                if (jo.get("durabilityModifier") != null) {
                                    durabilityOrDuration += jo.get("durabilityModifier").getAsInt();
                                }
                            }
                        }
                    }
                }
            }

            for (int idl = 0; 45 >= idl; ++idl) {
                Identifications id = INT_TO_ID.get(idl);
                int total = 0;
                for (int i = 0; lJ.size() > i; ++i) {
                    JsonObject j = lJ.get(i);
                    if (id.getIngFieldPos().equals("identifications") && j.get(id.getIngFieldPos()) != null && j.get(id.getIngFieldPos()).getAsJsonObject().get(id.getIngName()) != null && j.get(id.getIngFieldPos()).getAsJsonObject().get(id.getIngName()).getAsJsonObject().get("minimum").getAsInt() != 0) {
                        total += (int) Math.floor(j.get(id.getIngFieldPos()).getAsJsonObject().get(id.getIngName()).getAsJsonObject().get(getMinOrMax).getAsInt() * ingEffective[i]);
                    } else if (!id.getIngFieldPos().equals("identifications") && j.get(id.getIngFieldPos()) != null && j.get(id.getIngFieldPos()).getAsJsonObject().get(id.getIngName()) != null && j.get(id.getIngFieldPos()).getAsJsonObject().get(id.getIngName()).getAsInt() != 0) {
                        total += (int) Math.floor(j.get(id.getIngFieldPos()).getAsJsonObject().get(id.getIngName()).getAsInt() * ingEffective[i]);
                    }
                }
                String idS = "\"" + id.getItemName() + "\":" + total + ",";
                sb.append(idS);
            }

            if (itemJ.get("type").getAsString().equals("Scroll") || itemJ.get("type").getAsString().equals("Potion") || itemJ.get("type").getAsString().equals("Food")) {
                if (ingEmpty) {
                    sb.append("\"charges\":3,\"duration\":3}");
                } else {
                    int lvM = Integer.parseInt(itemJ.get("level").getAsString().split("-")[0]);
                    if (lvM < 30) {
                        charges += 1;
                    } else if (lvM < 70) {
                        charges += 2;
                    } else {
                        charges += 3;
                    }
                    int d = durabilityOrDuration + Math.round(recipeJ.get("duration").getAsJsonObject().get(getMinOrMax).getAsInt() * matTB);
                    if (d < 0) d = 1;
                    String duration = "\"charges\":" + charges + ",\"duration\":" + d + "}";
                    sb.append(duration);
                }
            } else {
                int d = durabilityOrDuration + Math.round(recipeJ.get("durability").getAsJsonObject().get(getMinOrMax).getAsInt() * matTB);
                if (d < 0) d = 1;
                String durability = "\"durability\":" + d + "}";
                sb.append(durability);
            }

            return JsonParser.parseString(sb.toString()).getAsJsonObject();
        }
        return JsonParser.parseString("{\"failed\":true}").getAsJsonObject();
    }

    public static JsonObject getRecipe(List<JsonObject> recipe, String type, String lv) {
        String id = type + "-" + lv;
        JsonObject json = JsonParser.parseString("{\"failed\":true}").getAsJsonObject();
        for (JsonObject j : recipe) {
            if (j.get("id") != null && j.get("id").getAsString().equals(id)) {
                json = j;
                break;
            }
        }
        return json;
    }

    public static float getMatTB(String type, int mat1, int mat2) {
        float total = 1F;
        switch (type) {
            case "Helmet": //Material 2 Big
            case "Boots":
            case "Wand":
            case "Spear":
            case "Bracelet":
            case "Potion":
                if (mat1 == 2) {
                    total += 0.08333F;
                } else if (mat1 == 3) {
                    total += 0.13333F;
                }
                if (mat2 == 2) {
                    total += 0.16666F;
                } else if (mat2 == 3) {
                    total += 0.26666F;
                }
                break;
            case "Chestplate": //Material 1 Big
            case "Leggings":
            case "Relik":
            case "Dagger":
            case "Bow":
            case "Food":
                if (mat1 == 2) {
                    total += 0.16666F;
                } else if (mat1 == 3) {
                    total += 0.26666F;
                }
                if (mat2 == 2) {
                    total += 0.08333F;
                } else if (mat2 == 3) {
                    total += 0.13333F;
                }
                break;
            case "Necklace": //Mat 2 Very Big
                if (mat1 == 2) {
                    total += 0.0625F;
                } else if (mat1 == 3) {
                    total += 0.1F;
                }
                if (mat2 == 2) {
                    total += 0.1875F;
                } else if (mat2 == 3) {
                    total += 0.3F;
                }
                break;
            default: //Ring and Scroll
                 if (mat1 == 2) {
                     total += 0.125F;
                 } else if (mat1 == 3) {
                     total += 0.2F;
                 }
                 if (mat2 == 2) {
                     total += 0.125F;
                 } else if (mat2 == 3) {
                     total += 0.2F;
                 }
                break;
        }
        return total;
    }

    private static class Adapter extends KeyAdapter {
        private final JComboBox<String> box;
        private final List<JsonObject> json;

        public Adapter(JComboBox<String> box, List<JsonObject> json) {
            super();
            this.box = box;
            this.json = json;
        }

        @Override
        public void keyTyped(KeyEvent e) {
            JTextField text = (JTextField) e.getComponent();

            setList(box, text.getText(), text.getCaretPosition(), text.getSelectionStart(), text.getSelectionEnd());
        }

        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
        }

        private void setList(JComboBox<String> box, String text, int caretPos, int selectionStart, int selectionEnd) {
            box.removeAllItems();

            for (JsonObject j : json) {
                if (j.get("displayName") != null) {
                    if (text.isEmpty() || j.get("displayName").getAsString().toLowerCase().contains(text.toLowerCase())) box.addItem(j.get("displayName").getAsString());
                } else if (j.get("name") != null) {
                    if (text.isEmpty() || j.get("name").getAsString().toLowerCase().contains(text.toLowerCase())) box.addItem(j.get("name").getAsString());
                }
            }

            ((JTextField) box.getEditor().getEditorComponent()).setText(text);
            ((JTextField) box.getEditor().getEditorComponent()).setCaretPosition(caretPos);
            if (selectionStart < selectionEnd) {
                ((JTextField) box.getEditor().getEditorComponent()).setSelectionStart(selectionStart);
                ((JTextField) box.getEditor().getEditorComponent()).setSelectionEnd(selectionEnd);
            }
        }
    }
}
