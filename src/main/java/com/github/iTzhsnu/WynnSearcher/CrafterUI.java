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
    private final List<JsonObject> recipeJson;

    private final JLabel itemAPIConnect = new JLabel();
    private final JComboBox<String> recipeType = new JComboBox<>();
    private final JComboBox<String> attackSpeed = new JComboBox<>();
    private final JComboBox<String> level = new JComboBox<>();
    private final List<JComboBox<String>> ingBox = new ArrayList<>();
    private final List<JLabel> texts = new ArrayList<>();
    private final JComboBox<String> material1 = new JComboBox<>();
    private final JComboBox<String> material2 = new JComboBox<>();

    private final JButton create = new JButton("Create");
    private final JButton load = new JButton("Load");
    private final JTextField output = new JTextField();
    private final JPanel outputP = new JPanel();

    private final JPanel created = new JPanel();
    private final JScrollPane createdScroll;
    private final JPanel ingPanel = new JPanel();
    private final JScrollPane ingScroll;

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

    private static final Map<Integer, Identifications> SP_REQ = new HashMap<Integer, Identifications>() {{
        put(0, Identifications.STRENGTH_REQ);
        put(1, Identifications.DEXTERITY_REQ);
        put(2, Identifications.INTELLIGENCE_REQ);
        put(3, Identifications.DEFENSE_REQ);
        put(4, Identifications.AGILITY_REQ);
    }};

    private static final Map<String, String> TYPE_TO_SKILL = new HashMap<String, String>() {{
        put("helmet", "armouring");
        put("chestplate", "armouring");
        put("leggings", "tailoring");
        put("boots", "tailoring");
        put("ring", "jeweling");
        put("bracelet", "jeweling");
        put("necklace", "jeweling");
        put("spear", "weaponsmithing");
        put("dagger", "weaponsmithing");
        put("bow", "woodworking");
        put("wand", "woodworking");
        put("relik", "woodworking");
        put("scroll", "scribing");
        put("food", "cooking");
        put("potion", "alchemism");
    }};

    public CrafterUI(Container pane, List<JsonObject> ingJson, List<JsonObject> recipeJson, String recipeAPIConnect, JLabel itemAPIConnect) {
        this.pane = pane;
        this.ingJson = ingJson;
        this.recipeJson = recipeJson;

        this.itemAPIConnect.setText(itemAPIConnect.getText());
        this.itemAPIConnect.setForeground(itemAPIConnect.getForeground());
        this.itemAPIConnect.setBounds(530, 5, 150, 20);

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
        recipeType.addItem("helmet");
        recipeType.addItem("chestplate");
        recipeType.addItem("leggings");
        recipeType.addItem("boots");
        recipeType.addItem("ring");
        recipeType.addItem("bracelet");
        recipeType.addItem("necklace");
        recipeType.addItem("bow");
        recipeType.addItem("wand");
        recipeType.addItem("relik");
        recipeType.addItem("spear");
        recipeType.addItem("dagger");
        recipeType.addItem("scroll");
        recipeType.addItem("food");
        recipeType.addItem("potion");

        //Attack Speed
        attackSpeed.setBounds(260, 40, 80, 20);
        attackSpeed.addItem("slow");
        attackSpeed.addItem("normal");
        attackSpeed.addItem("fast");

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
        oText.setBounds(440, 120, 80, 20);
        outputP.setBounds(520, 110, 200, 40);
        outputP.setLayout(new BoxLayout(outputP, BoxLayout.Y_AXIS));
        JScrollBar outputB = new JScrollBar(JScrollBar.HORIZONTAL);
        outputB.setUnitIncrement(20);
        outputB.setModel(output.getHorizontalVisibility());
        outputP.add(output);
        outputP.add(outputB);

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
        createdScroll.setBounds(730, 10, 288, 748);

        texts.add(recipeConnect);
        texts.add(typeText);
        texts.add(lvText);
        texts.add(mat1);
        texts.add(mat2);
        texts.add(oText);

        pane.add(this.itemAPIConnect);
        pane.add(recipeType);
        pane.add(attackSpeed);
        pane.add(level);
        pane.add(material1);
        pane.add(material2);
        pane.add(create);
        pane.add(outputP);
        pane.add(ingScroll);
        pane.add(createdScroll);
        pane.add(load);

        for (JLabel l : texts) {
            pane.add(l);
        }
    }

    public void setCrafterVisible(boolean visible) {
        itemAPIConnect.setVisible(visible);
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
        outputP.setVisible(visible);
        createdScroll.setVisible(visible);
        ingScroll.setVisible(visible);
        load.setVisible(visible);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == create) {
            createCraftItem();
        } else if (e.getSource() == load) {
            loadCraftItem(true);
        }
    }

    public void setIngBox() {
        for (int i = 0; 6 > i; ++i) {
            JComboBox<String> box = new JComboBox<>();
            int pos = (int) Math.floor(i / 2F);

            for (JsonObject j : ingJson) {
                if (j.get("name") != null) {
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
            box.getEditor().getEditorComponent().addKeyListener(new Adapter(box, ingJson));

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

        if (recType.equals("bow") || recType.equals("wand") || recType.equals("relik") || recType.equals("spear") || recType.equals("dagger")) {
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

            //JPanel itemUI = setDisplay(getCraftItemJson(recipeJson, ingJson, s, true), getCraftItemJson(recipeJson, ingJson, s, false), null, null, 270); //Change to ItemUITemplate
            ItemUITemplate itemUI = new ItemUITemplate(getCraftItemJson(recipeJson, ingJson, s), "item", null, null, 270, 0, true);
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
                    if (jo.get("name") != null && jo.get("name").getAsString().equals(je.getAsString())) {
                        lj.add(jo);
                        break;
                    }
                }
            }
        }

        for (int i = 0; lj.size() > i; ++i) {
            JsonObject j = lj.get(i);
            if (i == 0) {
                ItemUITemplate p = new ItemUITemplate(j, "ingredient", null, null, 530, 0, false);
                lp.add(p);
            } else if (i == 1) {
                ItemUITemplate p = new ItemUITemplate(j, "ingredient", lp.get(0), null, 530, 0, false);
                lp.add(p);
            } else {
                ItemUITemplate p = new ItemUITemplate(j, "ingredient", lp.get(i - 1), lp.get(i - 2), 530, 0, false);
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
        if (json.get("charges") != null && json.get("charges").getAsInt() != 0) {
            label.add(new JLabel("Charges: " + json.get("charges").getAsInt()));
        }
        label.add(new JLabel("Rarity: Crafted"));

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

    public static JsonObject getCraftItemJson(List<JsonObject> recipes, List<JsonObject> ing, String s) {
        if (s.contains("CR-")) {
            String sJ = s.replace("CR-", "");
            JsonObject itemJ = JsonParser.parseString(sJ).getAsJsonObject();
            JsonObject recipeJ = getRecipe(recipes, itemJ.get("type").getAsString(), itemJ.get("level").getAsString());
            String[] lvs = itemJ.get("level").getAsString().split("-");
            float matTB = getMatTB(itemJ.get("type").getAsString(), itemJ.get("material1").getAsInt(), itemJ.get("material2").getAsInt());
            int durabilityOrDuration = 0;
            JsonObject output = JsonParser.parseString("{\"name\":\"crafted " + itemJ.get("type").getAsString() + "\",\"base\":{},\"requirements\":{},\"tier\":\"crafted\"}").getAsJsonObject();

            boolean ingEmpty = true;
            for (JsonElement je : itemJ.get("ing").getAsJsonArray()) {
                if (!je.getAsString().isEmpty()) {
                    ingEmpty = false;
                    break;
                }
            }

            switch (itemJ.get("type").getAsString()) {
                case "bow":
                case "wand":
                case "relik":
                case "spear":
                case "dagger":
                {
                    float atkSpdBoost = 1F;
                    if (itemJ.get("attackSpeed").getAsString().equals("fast")) {
                        atkSpdBoost = 0.82F;
                    } else if (itemJ.get("attackSpeed").getAsString().equals("slow")) {
                        atkSpdBoost = 1.36667F;
                    }
                    JsonObject j = recipeJ.get("healthOrDamage").getAsJsonObject();
                    float min = 0.9F * atkSpdBoost * matTB;
                    float max = 1.1F * atkSpdBoost * matTB;

                    output.get("base").getAsJsonObject().add(Identifications.NEUTRAL_DAMAGE.getItemName(), JsonParser.parseString("{\"min\":\"" + ((int) Math.floor(j.get("minimum").getAsInt() * min)) + "-" + ((int) Math.floor(j.get("minimum").getAsInt() * max)) + "\",\"max\":\"" + ((int) Math.floor(j.get("maximum").getAsInt() * min)) + "-" + ((int) Math.floor(j.get("maximum").getAsInt() * max)) + "\"}"));
                    break;
                }
                case "helmet":
                case "chestplate":
                case "leggings":
                case "boots":
                {
                    JsonObject j = recipeJ.get("healthOrDamage").getAsJsonObject();
                    int min = (int) Math.floor(j.get("minimum").getAsInt() * matTB);
                    int max = (int) Math.floor(j.get("maximum").getAsInt() * matTB);

                    output.get("base").getAsJsonObject().add(Identifications.HEALTH.getItemName(), JsonParser.parseString("{\"min\":" + min + ",\"max\":" + max + "}"));
                    break;
                }
                case "scroll":
                case "potion":
                case "food":
                {
                    if (ingEmpty) {
                        JsonObject j = recipeJ.get("healthOrDamage").getAsJsonObject();
                        int min = (int) Math.floor(j.get("minimum").getAsInt() * matTB);
                        int max = (int) Math.floor(j.get("maximum").getAsInt() * matTB);

                        output.get("base").getAsJsonObject().add(Identifications.HEALTH.getItemName(), JsonParser.parseString("{\"min\":" + min + ",\"max\":" + max + "}"));
                    }
                    break;
                }
            }

            output.get("requirements").getAsJsonObject().add(Identifications.LEVEL.getItemName(), JsonParser.parseString("{\"min\":" + lvs[0] + ",\"max\":" + lvs[lvs.length - 1] + "}"));
            output.addProperty("type", itemJ.get("type").getAsString());

            List<JsonObject> lJ = new ArrayList<>();
            float[] ingEffective = new float[] {1F, 1F, 1F, 1F, 1F, 1F};
            int charges = 1;

            if (!ingEmpty) {
                for (int i = 0; itemJ.get("ing").getAsJsonArray().size() > i; ++i) {
                    JsonElement je = itemJ.get("ing").getAsJsonArray().get(i);
                    lJ.add(JsonParser.parseString("{}").getAsJsonObject());
                    if (!je.getAsString().isEmpty()) {
                        for (JsonObject jo : ing) {
                            if (jo.get("name") != null && jo.get("name").getAsString().equals(je.getAsString())) {
                                lJ.set(i, jo);
                                break;
                            }
                        }

                        //Can Use (Item Type)
                        if (lJ.get(i).get("requirements") != null && lJ.get(i).get("requirements").getAsJsonObject().get("skills") != null) {
                            boolean remove = true;
                            for (int skillI = 0; lJ.get(i).get("requirements").getAsJsonObject().get("skills").getAsJsonArray().size() > skillI; ++skillI) {
                                if (lJ.get(i).get("requirements").getAsJsonObject().get("skills").getAsJsonArray().get(skillI).getAsString().toLowerCase().equals(TYPE_TO_SKILL.get(itemJ.get("type").getAsString()))) {
                                    remove = false;
                                    break;
                                }
                            }
                            if (remove) lJ.set(i, JsonParser.parseString("{}").getAsJsonObject());
                        } else {
                            lJ.set(i, JsonParser.parseString("{}").getAsJsonObject());
                        }

                        //Can Use (Level)
                        if (lJ.get(i).get("requirements") != null && lJ.get(i).get("requirements").getAsJsonObject().get("level") != null) {
                            if (lJ.get(i).get("requirements").getAsJsonObject().get("level").getAsInt() > Integer.parseInt(lvs[lvs.length - 1])) lJ.set(i, JsonParser.parseString("{}").getAsJsonObject());
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
                                    durabilityOrDuration += jo.get("durabilityModifier").getAsInt() / 1000F;
                                }
                            }
                        }
                    }
                }
            }

            for (int i = 0; 4 >= i; ++i) { //SP Requests
                Identifications id = SP_REQ.get(i);
                int total = 0;
                for (int n = 0; lJ.size() > n; ++n) {
                    JsonObject j = lJ.get(n);
                    if (id.getIngName() != null && j.get("itemOnlyIDs") != null && j.get("itemOnlyIDs").getAsJsonObject().get(id.getIngName()) != null) {
                        total += (int) Math.floor(j.get("itemOnlyIDs").getAsJsonObject().get(id.getIngName()).getAsInt() * ingEffective[n]);
                    }
                }
                if (total != 0) {
                    output.get("requirements").getAsJsonObject().addProperty(id.getItemName(), total);
                }
            }

            for (int i = 0; 76 >= i; ++i) { //IDs
                Identifications id = ItemUITemplate.ITEM_IDS.get(i);
                int total_Min = 0;
                int total_Max = 0;
                for (int n = 0; lJ.size() > n; ++n) {
                    JsonObject j = lJ.get(n);
                    if (id.getIngName() != null && id.getIngFieldPos().equals("identifications") && j.get("identifications") != null && j.get("identifications").getAsJsonObject().get(id.getIngName()) != null) {
                        JsonElement je = j.get("identifications").getAsJsonObject().get(id.getIngName());
                        if (!je.isJsonObject()) {
                            total_Min += (int) Math.floor(je.getAsInt() * ingEffective[n]);
                            total_Max += (int) Math.floor(je.getAsInt() * ingEffective[n]);
                        } else {
                            total_Min += (int) Math.floor(je.getAsJsonObject().get("min").getAsInt() * ingEffective[n]);
                            total_Max += (int) Math.floor(je.getAsJsonObject().get("max").getAsInt() * ingEffective[n]);
                        }
                    }
                }
                if (total_Min != 0 || total_Max != 0) {
                    if (output.get("identifications") != null) {
                        output.get("identifications").getAsJsonObject().add(id.getItemName(), JsonParser.parseString("{\"min\":" + total_Min + ",\"max\":" + total_Max + "}"));
                    } else {
                        output.add("identifications", JsonParser.parseString("{\"" + id.getItemName() + "\":{\"min\":" + total_Min + ",\"max\":" + total_Max + "}}"));
                    }
                }
            }

            //TODO Reversed IDs

            if (itemJ.get("type").getAsString().equals("Scroll") || itemJ.get("type").getAsString().equals("Potion") || itemJ.get("type").getAsString().equals("Food")) {
                if (ingEmpty) {
                    output.addProperty("charges", 3);
                    output.addProperty("duration", 3);
                } else {
                    int lvM = Integer.parseInt(itemJ.get("level").getAsString().split("-")[0]);
                    if (lvM >= 30 && lvM < 70) {
                        charges += 1;
                    } else if (lvM >= 70) {
                        charges += 2;
                    }
                    if (charges < 0) charges = 1;
                    output.addProperty("charges", charges);

                    int duration_Min = durabilityOrDuration + Math.round(recipeJ.get("duration").getAsJsonObject().get("minimum").getAsInt() * matTB);
                    int duration_Max = durabilityOrDuration + Math.round(recipeJ.get("duration").getAsJsonObject().get("maximum").getAsInt() * matTB);
                    if (duration_Min < 0) duration_Min = 1;
                    if (duration_Max < 0) duration_Max = 1;
                    output.addProperty("duration", duration_Min + "-" + duration_Max);
                }
            } else {
                int durability_Min = durabilityOrDuration + Math.round(recipeJ.get("durability").getAsJsonObject().get("minimum").getAsInt() * matTB);
                int durability_Max = durabilityOrDuration + Math.round(recipeJ.get("durability").getAsJsonObject().get("maximum").getAsInt() * matTB);
                if (durability_Min < 0) durability_Min = 1;
                if (durability_Max < 0) durability_Max = 1;
                output.addProperty("durability", durability_Min + "-" + durability_Max);

                int lvM = Integer.parseInt(itemJ.get("level").getAsString().split("-")[0]);
                int powderSlots = 1;
                if (lvM >= 30 && lvM < 70) {
                    powderSlots += 1;
                } else if (lvM >= 70) {
                    powderSlots += 2;
                }
                output.addProperty(Identifications.POWDER_SLOTS.getItemName(), powderSlots);
                if (itemJ.get("attackSpeed") != null) output.addProperty(Identifications.ATTACK_SPEED.getItemName(), itemJ.get("attackSpeed").getAsString());
            }

            return output;
        }
        return JsonParser.parseString("{\"failed\":true}").getAsJsonObject();
    }

    public static JsonObject getRecipe(List<JsonObject> recipe, String type, String lv) {
        String id = type + "-" + lv;
        JsonObject json = JsonParser.parseString("{\"failed\":true}").getAsJsonObject();
        for (JsonObject j : recipe) {
            if (j.get("id") != null && j.get("id").getAsString().toLowerCase().equals(id)) {
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

    public static class Adapter extends KeyAdapter {
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
