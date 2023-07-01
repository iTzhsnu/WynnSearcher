package com.github.iTzhsnu.WynnSearcher.builder;

import com.github.iTzhsnu.WynnSearcher.CrafterUI;
import com.github.iTzhsnu.WynnSearcher.ItemUITemplate;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Item_Display {
    private final JPanel pane = new JPanel();
    private final List<JPanel> itemsPanel = new ArrayList<>();
    private ItemJsons itemJsons = new ItemJsons(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

    public Item_Display(JPanel p) {
        pane.setPreferredSize(new Dimension(1045, 497));
        pane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane(pane);
        scrollPane.setBounds(1, 1405, 1063, 500); // Y + 70
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);

        p.add(scrollPane);
    }

    public void setItem_Display(List<JComboBox<String>> box, List<JsonObject> ing, List<JsonObject> recipe,
                                List<JsonObject> helmetJ, List<JsonObject> chestplateJ, List<JsonObject> leggingsJ, List<JsonObject> bootsJ, List<JsonObject> ringJ, List<JsonObject> braceletJ, List<JsonObject> necklaceJ, List<JsonObject> weaponJ,
                                List<JsonObject> armourTomeJ, List<JsonObject> weaponTomeJ, List<JsonObject> guildTomeJ) {
        pane.removeAll();
        if (itemsPanel.size() > 0) {
            itemsPanel.subList(0, itemsPanel.size()).clear();
        }

        //Texts
        String helmet = ((JTextField) box.get(0).getEditor().getEditorComponent()).getText();
        String chestplate = ((JTextField) box.get(1).getEditor().getEditorComponent()).getText();
        String leggings = ((JTextField) box.get(2).getEditor().getEditorComponent()).getText();
        String boots = ((JTextField) box.get(3).getEditor().getEditorComponent()).getText();
        String ring1 = ((JTextField) box.get(4).getEditor().getEditorComponent()).getText();
        String ring2 = ((JTextField) box.get(5).getEditor().getEditorComponent()).getText();
        String bracelet = ((JTextField) box.get(6).getEditor().getEditorComponent()).getText();
        String necklace = ((JTextField) box.get(7).getEditor().getEditorComponent()).getText();
        String weapon = ((JTextField) box.get(8).getEditor().getEditorComponent()).getText();

        String armourTome1 = ((JTextField) box.get(9).getEditor().getEditorComponent()).getText();
        String armourTome2 = ((JTextField) box.get(10).getEditor().getEditorComponent()).getText();
        String armourTome3 = ((JTextField) box.get(11).getEditor().getEditorComponent()).getText();
        String armourTome4 = ((JTextField) box.get(12).getEditor().getEditorComponent()).getText();
        String guildTome = ((JTextField) box.get(13).getEditor().getEditorComponent()).getText();
        String weaponTome1 = ((JTextField) box.get(14).getEditor().getEditorComponent()).getText();
        String weaponTome2 = ((JTextField) box.get(15).getEditor().getEditorComponent()).getText();

        //Item Json
        JsonObject helmetJson = null;
        JsonObject chestplateJson = null;
        JsonObject leggingsJson = null;
        JsonObject bootsJson = null;
        JsonObject ring1Json = null;
        JsonObject ring2Json = null;
        JsonObject braceletJson = null;
        JsonObject necklaceJson = null;
        JsonObject weaponJson = null;

        JsonObject armourTome1Json = null;
        JsonObject armourTome2Json = null;
        JsonObject armourTome3Json = null;
        JsonObject armourTome4Json = null;
        JsonObject guildTomeJson = null;
        JsonObject weaponTome1Json = null;
        JsonObject weaponTome2Json = null;

        //Helmet
        if (helmet.contains("CI-")) {
            helmetJson = JsonParser.parseString(helmet.replace("CI-", "")).getAsJsonObject();
            itemsPanel.add(new ItemUITemplate(helmetJson, "item", null, null, 1045, 0, true));
        } else if (helmet.contains("CR-")) {
            if (helmet.contains("\"type\":\"helmet\"")) {
                helmetJson = CrafterUI.getCraftItemJson(recipe, ing, helmet, false);
                itemsPanel.add(new ItemUITemplate(helmetJson, "item", null, null, 1045, 0, true));
            }
        } else if (!helmet.isEmpty()) {
            JsonObject json = null;
            for (JsonObject j : helmetJ) {
                if (j.get("name") != null && j.get("name").getAsString().equals(helmet)) {
                    json = j;
                    break;
                }
            }
            if (json != null) {
                helmetJson = json;
                itemsPanel.add(new ItemUITemplate(json, "item", null, null, 1045, 0, false));
            }
        }

        //Chestplate
        JPanel previousC = null;
        if (itemsPanel.size() > 0) previousC = itemsPanel.get(0);
        if (chestplate.contains("CI-")) {
            chestplateJson = JsonParser.parseString(chestplate.replace("CI-", "")).getAsJsonObject();
            itemsPanel.add(new ItemUITemplate(chestplateJson, "item", previousC, null, 1045, 0, true));
        } else if (chestplate.contains("CR-")) {
            if (chestplate.contains("\"type\":\"chestplate\"")) {
                chestplateJson = CrafterUI.getCraftItemJson(recipe, ing, chestplate, false);
                itemsPanel.add(new ItemUITemplate(chestplateJson, "item", previousC, null, 1045, 0, true));
            }
        } else if (!chestplate.isEmpty()) {
            JsonObject json = null;
            for (JsonObject j : chestplateJ) {
                if (j.get("name") != null && j.get("name").getAsString().equals(chestplate)) {
                    json = j;
                    break;
                }
            }
            if (json != null) {
                chestplateJson = json;
                itemsPanel.add(new ItemUITemplate(json, "item", previousC, null, 1045, 0, false));
            }
        }

        //Leggings
        JPanel previousL = null;
        if (itemsPanel.size() > 0) previousL = itemsPanel.get(itemsPanel.size() - 1);
        if (leggings.contains("CI-")) {
            leggingsJson = JsonParser.parseString(leggings.replace("CI-", "")).getAsJsonObject();
            itemsPanel.add(new ItemUITemplate(leggingsJson, "item", previousL, null, 1045, 0, true));
        } else if (leggings.contains("CR-")) {
            if (leggings.contains("\"type\":\"leggings\"")) {
                leggingsJson = CrafterUI.getCraftItemJson(recipe, ing, leggings, false);
                itemsPanel.add(new ItemUITemplate(leggingsJson, "item", previousL, null, 1045, 0, true));
            }
        } else if (!leggings.isEmpty()) {
            JsonObject json = null;
            for (JsonObject j : leggingsJ) {
                if (j.get("name") != null && j.get("name").getAsString().equals(leggings)) {
                    json = j;
                    break;
                }
            }
            if (json != null) {
                leggingsJson = json;
                itemsPanel.add(new ItemUITemplate(json, "item", previousL, null, 1045, 0, false));
            }
        }

        //Boots
        JPanel previousB = null;
        if (itemsPanel.size() > 0) previousB = itemsPanel.get(itemsPanel.size() - 1);
        if (boots.contains("CI-")) {
            bootsJson = JsonParser.parseString(boots.replace("CI-", "")).getAsJsonObject();
            itemsPanel.add(new ItemUITemplate(bootsJson, "item", previousB, null, 1045, 0, true));
        } else if (boots.contains("CR-")) {
            if (boots.contains("\"type\":\"boots\"")) {
                bootsJson = CrafterUI.getCraftItemJson(recipe, ing, boots, false);
                itemsPanel.add(new ItemUITemplate(bootsJson, "item", previousB, null, 1045, 0, true));
            }
        } else if (!boots.isEmpty()) {
            JsonObject json = null;
            for (JsonObject j : bootsJ) {
                if (j.get("name") != null && j.get("name").getAsString().equals(boots)) {
                    json = j;
                    break;
                }
            }
            if (json != null) {
                bootsJson = json;
                itemsPanel.add(new ItemUITemplate(json, "item", previousB, null, 1045, 0, false));
            }
        }

        //Ring 1
        JPanel previousR1 = null;
        JPanel aboveR1 = null;
        if (itemsPanel.size() > 0) previousR1 = itemsPanel.get(itemsPanel.size() - 1);
        if (itemsPanel.size() > 3) aboveR1 = itemsPanel.get(itemsPanel.size() - 4);
        if (ring1.contains("CI-")) {
            ring1Json = JsonParser.parseString(ring1.replace("CI-", "")).getAsJsonObject();
            itemsPanel.add(new ItemUITemplate(ring1Json, "item", previousR1, aboveR1, 1045, 0, true));
        } else if (ring1.contains("CR-")) {
            if (ring1.contains("\"type\":\"ring\"")) {
                ring1Json = CrafterUI.getCraftItemJson(recipe, ing, ring1, false);
                itemsPanel.add(new ItemUITemplate(ring1Json, "item", previousR1, aboveR1, 1045, 0, true));
            }
        } else if (!ring1.isEmpty()) {
            JsonObject json = null;
            for (JsonObject j : ringJ) {
                if (j.get("name") != null && j.get("name").getAsString().equals(ring1)) {
                    json = j;
                    break;
                }
            }
            if (json != null) {
                ring1Json = json;
                itemsPanel.add(new ItemUITemplate(json, "item", previousR1, aboveR1, 1045, 0, false));
            }
        }

        //Ring 2
        JPanel previousR2 = null;
        JPanel aboveR2 = null;
        if (itemsPanel.size() > 0) previousR2 = itemsPanel.get(itemsPanel.size() - 1);
        if (itemsPanel.size() > 3) aboveR2 = itemsPanel.get(itemsPanel.size() - 4);
        if (ring2.contains("CI-")) {
            ring2Json = JsonParser.parseString(ring2.replace("CI-", "")).getAsJsonObject();
            itemsPanel.add(new ItemUITemplate(ring2Json, "item", previousR2, aboveR2, 1045, 0, true));
        } else if (ring2.contains("CR-")) {
            if (ring2.contains("\"type\":\"ring\"")) {
                ring2Json = CrafterUI.getCraftItemJson(recipe, ing, ring2, false);
                itemsPanel.add(new ItemUITemplate(ring2Json, "item", previousR2, aboveR2, 1045, 0, true));
            }
        } else if (!ring2.isEmpty()) {
            JsonObject json = null;
            for (JsonObject j : ringJ) {
                if (j.get("name") != null && j.get("name").getAsString().equals(ring2)) {
                    json = j;
                    break;
                }
            }
            if (json != null) {
                ring2Json = json;
                itemsPanel.add(new ItemUITemplate(json, "item", previousR2, aboveR2, 1045, 0, false));
            }
        }

        //Bracelet
        JPanel previousBr = null;
        JPanel aboveBr = null;
        if (itemsPanel.size() > 0) previousBr = itemsPanel.get(itemsPanel.size() - 1);
        if (itemsPanel.size() > 3) aboveBr = itemsPanel.get(itemsPanel.size() - 4);
        if (bracelet.contains("CI-")) {
            braceletJson = JsonParser.parseString(bracelet.replace("CI-", "")).getAsJsonObject();
            itemsPanel.add(new ItemUITemplate(braceletJson, "item", previousBr, aboveBr, 1045, 0, true));
        } else if (bracelet.contains("CR-")) {
            if (bracelet.contains("\"type\":\"bracelet\"")) {
                braceletJson = CrafterUI.getCraftItemJson(recipe, ing, bracelet, false);
                itemsPanel.add(new ItemUITemplate(braceletJson, "item", previousBr, aboveBr, 1045, 0, true));
            }
        } else if (!bracelet.isEmpty()) {
            JsonObject json = null;
            for (JsonObject j : braceletJ) {
                if (j.get("name") != null && j.get("name").getAsString().equals(bracelet)) {
                    json = j;
                    break;
                }
            }
            if (json != null) {
                braceletJson = json;
                itemsPanel.add(new ItemUITemplate(json, "item", previousBr, aboveBr, 1045, 0, false));
            }
        }

        //Necklace
        JPanel previousN = null;
        JPanel aboveN = null;
        if (itemsPanel.size() > 0) previousN = itemsPanel.get(itemsPanel.size() - 1);
        if (itemsPanel.size() > 3) aboveN = itemsPanel.get(itemsPanel.size() - 4);
        if (necklace.contains("CI-")) {
            necklaceJson = JsonParser.parseString(necklace.replace("CI-", "")).getAsJsonObject();
            itemsPanel.add(new ItemUITemplate(necklaceJson, "item", previousN, aboveN, 1045, 0, true));
        } else if (necklace.contains("CR-")) {
            if (necklace.contains("\"type\":\"necklace\"")) {
                necklaceJson = CrafterUI.getCraftItemJson(recipe, ing, necklace, false);
                itemsPanel.add(new ItemUITemplate(necklaceJson, "item", previousN, aboveN, 1045, 0, true));
            }
        } else if (!necklace.isEmpty()) {
            JsonObject json = null;
            for (JsonObject j : necklaceJ) {
                if (j.get("name") != null && j.get("name").getAsString().equals(necklace)) {
                    json = j;
                    break;
                }
            }
            if (json != null) {
                necklaceJson = json;
                itemsPanel.add(new ItemUITemplate(json, "item", previousN, aboveN, 1045, 0, false));
            }
        }

        //Weapon
        JPanel previousW = null;
        JPanel aboveW = null;
        if (itemsPanel.size() > 0) previousW = itemsPanel.get(itemsPanel.size() - 1);
        if (itemsPanel.size() > 3) aboveW = itemsPanel.get(itemsPanel.size() - 4);
        if (weapon.contains("CI-")) {
            weaponJson = JsonParser.parseString(weapon.replace("CI-", "")).getAsJsonObject();
            itemsPanel.add(new ItemUITemplate(weaponJson, "item", previousW, aboveW, 1045, 0, true));
        } else if (weapon.contains("CR-")) {
            if (weapon.contains("\"type\":\"spear\"") || weapon.contains("\"type\":\"dagger\"") || weapon.contains("\"type\":\"bow\"") || weapon.contains("\"type\":\"wand\"") || weapon.contains("\"type\":\"relik\"")) {
                weaponJson = CrafterUI.getCraftItemJson(recipe, ing, weapon, false);
                itemsPanel.add(new ItemUITemplate(weaponJson, "item", previousW, aboveW, 1045, 0, true));
            }
        } else if (!weapon.isEmpty()) {
            JsonObject json = null;
            for (JsonObject j : weaponJ) {
                if (j.get("name") != null && j.get("name").getAsString().equals(weapon)) {
                    json = j;
                    break;
                }
            }
            if (json != null) {
                weaponJson = json;
                itemsPanel.add(new ItemUITemplate(json, "item", previousW, aboveW, 1045, 0, false));
            }
        }

        //Armour Tome 1
        JPanel previousAT1 = null;
        JPanel aboveAT1 = null;
        if (itemsPanel.size() > 0) previousAT1 = itemsPanel.get(itemsPanel.size() - 1);
        if (itemsPanel.size() > 3) aboveAT1 = itemsPanel.get(itemsPanel.size() - 4);
        if (!armourTome1.isEmpty()) {
            JsonObject json = null;
            for (JsonObject j : armourTomeJ) {
                if (j.get("name").getAsString().equals(armourTome1)) {
                    json = j;
                    break;
                }
            }
            if (json != null) {
                armourTome1Json = json;
                itemsPanel.add(new ItemUITemplate(json, "other", previousAT1, aboveAT1, 1045, 0, false));
            }
        }

        //Armour Tome 2
        JPanel previousAT2 = null;
        JPanel aboveAT2 = null;
        if (itemsPanel.size() > 0) previousAT2 = itemsPanel.get(itemsPanel.size() - 1);
        if (itemsPanel.size() > 3) aboveAT2 = itemsPanel.get(itemsPanel.size() - 4);
        if (!armourTome2.isEmpty()) {
            JsonObject json = null;
            for (JsonObject j : armourTomeJ) {
                if (j.get("name").getAsString().equals(armourTome2)) {
                    json = j;
                    break;
                }
            }
            if (json != null) {
                armourTome2Json = json;
                itemsPanel.add(new ItemUITemplate(json, "other", previousAT2, aboveAT2, 1045, 0, false));
            }
        }

        //Armour Tome 3
        JPanel previousAT3 = null;
        JPanel aboveAT3 = null;
        if (itemsPanel.size() > 0) previousAT3 = itemsPanel.get(itemsPanel.size() - 1);
        if (itemsPanel.size() > 3) aboveAT3 = itemsPanel.get(itemsPanel.size() - 4);
        if (!armourTome3.isEmpty()) {
            JsonObject json = null;
            for (JsonObject j : armourTomeJ) {
                if (j.get("name").getAsString().equals(armourTome3)) {
                    json = j;
                    break;
                }
            }
            if (json != null) {
                armourTome3Json = json;
                itemsPanel.add(new ItemUITemplate(json, "other", previousAT3, aboveAT3, 1045, 0, false));
            }
        }

        //Armour Tome 4
        JPanel previousAT4 = null;
        JPanel aboveAT4 = null;
        if (itemsPanel.size() > 0) previousAT4 = itemsPanel.get(itemsPanel.size() - 1);
        if (itemsPanel.size() > 3) aboveAT4 = itemsPanel.get(itemsPanel.size() - 4);
        if (!armourTome4.isEmpty()) {
            JsonObject json = null;
            for (JsonObject j : armourTomeJ) {
                if (j.get("name").getAsString().equals(armourTome4)) {
                    json = j;
                    break;
                }
            }
            if (json != null) {
                armourTome4Json = json;
                itemsPanel.add(new ItemUITemplate(json, "other", previousAT4, aboveAT4, 1045, 0, false));
            }
        }

        //Guild Tome
        JPanel previousGT = null;
        JPanel aboveGT = null;
        if (itemsPanel.size() > 0) previousGT = itemsPanel.get(itemsPanel.size() - 1);
        if (itemsPanel.size() > 3) aboveGT = itemsPanel.get(itemsPanel.size() - 4);
        if (!guildTome.isEmpty()) {
            JsonObject json = null;
            for (JsonObject j : guildTomeJ) {
                if (j.get("name").getAsString().equals(guildTome)) {
                    json = j;
                    break;
                }
            }
            if (json != null) {
                guildTomeJson = json;
                itemsPanel.add(new ItemUITemplate(json, "other", previousGT, aboveGT, 1045, 0, false));
            }
        }

        //Weapon Tome 1
        JPanel previousWT1 = null;
        JPanel aboveWT1 = null;
        if (itemsPanel.size() > 0) previousWT1 = itemsPanel.get(itemsPanel.size() - 1);
        if (itemsPanel.size() > 3) aboveWT1 = itemsPanel.get(itemsPanel.size() - 4);
        if (!weaponTome1.isEmpty()) {
            JsonObject json = null;
            for (JsonObject j : weaponTomeJ) {
                if (j.get("name").getAsString().equals(weaponTome1)) {
                    json = j;
                    break;
                }
            }
            if (json != null) {
                weaponTome1Json = json;
                itemsPanel.add(new ItemUITemplate(json, "other", previousWT1, aboveWT1, 1045, 0, false));
            }
        }

        //Weapon Tome 2
        JPanel previousWT2 = null;
        JPanel aboveWT2 = null;
        if (itemsPanel.size() > 0) previousWT2 = itemsPanel.get(itemsPanel.size() - 1);
        if (itemsPanel.size() > 3) aboveWT2 = itemsPanel.get(itemsPanel.size() - 4);
        if (!weaponTome2.isEmpty()) {
            JsonObject json = null;
            for (JsonObject j : weaponTomeJ) {
                if (j.get("name").getAsString().equals(weaponTome2)) {
                    json = j;
                    break;
                }
            }
            if (json != null) {
                weaponTome2Json = json;
                itemsPanel.add(new ItemUITemplate(json, "other", previousWT2, aboveWT2, 1045, 0, false));
            }
        }

        itemJsons = new ItemJsons(helmetJson, chestplateJson, leggingsJson, bootsJson, ring1Json, ring2Json, braceletJson, necklaceJson, weaponJson, armourTome1Json, armourTome2Json, armourTome3Json, armourTome4Json, weaponTome1Json, weaponTome2Json, guildTomeJson);

        //Display Size
        int height = 0;
        for (JPanel p : itemsPanel) {
            pane.add(p);
            if (height < p.getBounds().y + p.getBounds().height) height = p.getBounds().y + p.getBounds().height;
        }
        if (height > 497) {
            pane.setPreferredSize(new Dimension(1045, height));
        } else {
            pane.setPreferredSize(new Dimension(1045, 497));
        }
        SwingUtilities.updateComponentTreeUI(pane);
    }

    public ItemJsons getItemJsons() {
        return itemJsons;
    }
}
