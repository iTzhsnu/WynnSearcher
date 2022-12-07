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
    private ItemJsons itemJsons = new ItemJsons(null, null, null, null, null, null, null, null, null);

    public Item_Display(JPanel p) {
        pane.setPreferredSize(new Dimension(1045, 497));
        pane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane(pane);
        scrollPane.setBounds(1, 1230, 1063, 500);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);

        p.add(scrollPane);
    }

    public void setItem_Display(List<JComboBox<String>> box, List<JsonObject> ing, List<JsonObject> recipe, List<JsonObject> helmetJ, List<JsonObject> chestplateJ, List<JsonObject> leggingsJ, List<JsonObject> bootsJ, List<JsonObject> ringJ, List<JsonObject> braceletJ, List<JsonObject> necklaceJ, List<JsonObject> weaponJ) {
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

        //Helmet
        if (helmet.contains("CI-")) {
            helmetJson = JsonParser.parseString(helmet.replace("CI-", "")).getAsJsonObject();
            itemsPanel.add(new ItemUITemplate(helmetJson, false, null, null, 1045, 0, true));
        } else if (helmet.contains("CR-")) {
            if (helmet.contains("\"type\":\"Helmet\"")) {
                helmetJson = CrafterUI.getCraftItemJson(recipe, ing, helmet, false);
                itemsPanel.add(CrafterUI.setDisplay(CrafterUI.getCraftItemJson(recipe, ing, helmet, true), helmetJson, null, null, 1045));
            }
        } else if (!helmet.isEmpty()) {
            JsonObject json = JsonParser.parseString("{}").getAsJsonObject();
            for (JsonObject j : helmetJ) {
                if (j.get("displayName") != null && j.get("displayName").getAsString().equals(helmet)) {
                    json = j;
                    break;
                } else if (j.get("name") != null && j.get("name").getAsString().equals(helmet)) {
                    json = j;
                    break;
                }
            }
            if (json.get("name") != null) {
                helmetJson = json;
                itemsPanel.add(new ItemUITemplate(json, false, null, null, 1045, 0, false));
            }
        }

        //Chestplate
        JPanel previousC = null;
        if (itemsPanel.size() > 0) previousC = itemsPanel.get(0);
        if (chestplate.contains("CI-")) {
            chestplateJson = JsonParser.parseString(chestplate.replace("CI-", "")).getAsJsonObject();
            itemsPanel.add(new ItemUITemplate(chestplateJson, false, previousC, null, 1045, 0, true));
        } else if (chestplate.contains("CR-")) {
            if (chestplate.contains("\"type\":\"Chestplate\"")) {
                chestplateJson = CrafterUI.getCraftItemJson(recipe, ing, chestplate, false);
                itemsPanel.add(CrafterUI.setDisplay(CrafterUI.getCraftItemJson(recipe, ing, chestplate, true), chestplateJson, previousC, null, 1045));
            }
        } else if (!chestplate.isEmpty()) {
            JsonObject json = JsonParser.parseString("{}").getAsJsonObject();
            for (JsonObject j : chestplateJ) {
                if (j.get("displayName") != null && j.get("displayName").getAsString().equals(chestplate)) {
                    json = j;
                    break;
                } else if (j.get("name") != null && j.get("name").getAsString().equals(chestplate)) {
                    json = j;
                    break;
                }
            }
            if (json.get("name") != null) {
                chestplateJson = json;
                itemsPanel.add(new ItemUITemplate(json, false, previousC, null, 1045, 0, false));
            }
        }

        //Leggings
        JPanel previousL = null;
        if (itemsPanel.size() > 0) previousL = itemsPanel.get(itemsPanel.size() - 1);
        if (leggings.contains("CI-")) {
            leggingsJson = JsonParser.parseString(leggings.replace("CI-", "")).getAsJsonObject();
            itemsPanel.add(new ItemUITemplate(leggingsJson, false, previousL, null, 1045, 0, true));
        } else if (leggings.contains("CR-")) {
            if (leggings.contains("\"type\":\"Leggings\"")) {
                leggingsJson = CrafterUI.getCraftItemJson(recipe, ing, leggings, false);
                itemsPanel.add(CrafterUI.setDisplay(CrafterUI.getCraftItemJson(recipe, ing, leggings, true), leggingsJson, previousL, null, 1045));
            }
        } else if (!leggings.isEmpty()) {
            JsonObject json = JsonParser.parseString("{}").getAsJsonObject();
            for (JsonObject j : leggingsJ) {
                if (j.get("displayName") != null && j.get("displayName").getAsString().equals(leggings)) {
                    json = j;
                    break;
                } else if (j.get("name") != null && j.get("name").getAsString().equals(leggings)) {
                    json = j;
                    break;
                }
            }
            if (json.get("name") != null) {
                leggingsJson = json;
                itemsPanel.add(new ItemUITemplate(json, false, previousL, null, 1045, 0, false));
            }
        }

        //Boots
        JPanel previousB = null;
        if (itemsPanel.size() > 0) previousB = itemsPanel.get(itemsPanel.size() - 1);
        if (boots.contains("CI-")) {
            bootsJson = JsonParser.parseString(boots.replace("CI-", "")).getAsJsonObject();
            itemsPanel.add(new ItemUITemplate(bootsJson, false, previousB, null, 1045, 0, true));
        } else if (boots.contains("CR-")) {
            if (boots.contains("\"type\":\"Boots\"")) {
                bootsJson = CrafterUI.getCraftItemJson(recipe, ing, boots, false);
                itemsPanel.add(CrafterUI.setDisplay(CrafterUI.getCraftItemJson(recipe, ing, boots, true), bootsJson, previousB, null, 1045));
            }
        } else if (!boots.isEmpty()) {
            JsonObject json = JsonParser.parseString("{}").getAsJsonObject();
            for (JsonObject j : bootsJ) {
                if (j.get("displayName") != null && j.get("displayName").getAsString().equals(boots)) {
                    json = j;
                    break;
                } else if (j.get("name") != null && j.get("name").getAsString().equals(boots)) {
                    json = j;
                    break;
                }
            }
            if (json.get("name") != null) {
                bootsJson = json;
                itemsPanel.add(new ItemUITemplate(json, false, previousB, null, 1045, 0, false));
            }
        }

        //Ring 1
        JPanel previousR1 = null;
        JPanel aboveR1 = null;
        if (itemsPanel.size() > 0) previousR1 = itemsPanel.get(itemsPanel.size() - 1);
        if (itemsPanel.size() > 3) aboveR1 = itemsPanel.get(itemsPanel.size() - 4);
        if (ring1.contains("CI-")) {
            ring1Json = JsonParser.parseString(ring1.replace("CI-", "")).getAsJsonObject();
            itemsPanel.add(new ItemUITemplate(ring1Json, false, previousR1, aboveR1, 1045, 0, true));
        } else if (ring1.contains("CR-")) {
            if (ring1.contains("\"type\":\"Ring\"")) {
                ring1Json = CrafterUI.getCraftItemJson(recipe, ing, ring1, false);
                itemsPanel.add(CrafterUI.setDisplay(CrafterUI.getCraftItemJson(recipe, ing, ring1, true), ring1Json, previousR1, aboveR1, 1045));
            }
        } else if (!ring1.isEmpty()) {
            JsonObject json = JsonParser.parseString("{}").getAsJsonObject();
            for (JsonObject j : ringJ) {
                if (j.get("displayName") != null && j.get("displayName").getAsString().equals(ring1)) {
                    json = j;
                    break;
                } else if (j.get("name") != null && j.get("name").getAsString().equals(ring1)) {
                    json = j;
                    break;
                }
            }
            if (json.get("name") != null) {
                ring1Json = json;
                itemsPanel.add(new ItemUITemplate(json, false, previousR1, aboveR1, 1045, 0, false));
            }
        }

        //Ring 2
        JPanel previousR2 = null;
        JPanel aboveR2 = null;
        if (itemsPanel.size() > 0) previousR2 = itemsPanel.get(itemsPanel.size() - 1);
        if (itemsPanel.size() > 3) aboveR2 = itemsPanel.get(itemsPanel.size() - 4);
        if (ring2.contains("CI-")) {
            ring2Json = JsonParser.parseString(ring2.replace("CI-", "")).getAsJsonObject();
            itemsPanel.add(new ItemUITemplate(ring2Json, false, previousR2, aboveR2, 1045, 0, true));
        } else if (ring2.contains("CR-")) {
            if (ring2.contains("\"type\":\"Ring\"")) {
                ring2Json = CrafterUI.getCraftItemJson(recipe, ing, ring2, false);
                itemsPanel.add(CrafterUI.setDisplay(CrafterUI.getCraftItemJson(recipe, ing, ring2, true), ring2Json, previousR2, aboveR2, 1045));
            }
        } else if (!ring2.isEmpty()) {
            JsonObject json = JsonParser.parseString("{}").getAsJsonObject();
            for (JsonObject j : ringJ) {
                if (j.get("displayName") != null && j.get("displayName").getAsString().equals(ring2)) {
                    json = j;
                    break;
                } else if (j.get("name") != null && j.get("name").getAsString().equals(ring2)) {
                    json = j;
                    break;
                }
            }
            if (json.get("name") != null) {
                ring2Json = json;
                itemsPanel.add(new ItemUITemplate(json, false, previousR2, aboveR2, 1045, 0, false));
            }
        }

        //Bracelet
        JPanel previousBr = null;
        JPanel aboveBr = null;
        if (itemsPanel.size() > 0) previousBr = itemsPanel.get(itemsPanel.size() - 1);
        if (itemsPanel.size() > 3) aboveBr = itemsPanel.get(itemsPanel.size() - 4);
        if (bracelet.contains("CI-")) {
            braceletJson = JsonParser.parseString(bracelet.replace("CI-", "")).getAsJsonObject();
            itemsPanel.add(new ItemUITemplate(braceletJson, false, previousBr, aboveBr, 1045, 0, true));
        } else if (bracelet.contains("CR-")) {
            if (bracelet.contains("\"type\":\"Bracelet\"")) {
                braceletJson = CrafterUI.getCraftItemJson(recipe, ing, bracelet, false);
                itemsPanel.add(CrafterUI.setDisplay(CrafterUI.getCraftItemJson(recipe, ing, bracelet, true), braceletJson, previousBr, aboveBr, 1045));
            }
        } else if (!bracelet.isEmpty()) {
            JsonObject json = JsonParser.parseString("{}").getAsJsonObject();
            for (JsonObject j : braceletJ) {
                if (j.get("displayName") != null && j.get("displayName").getAsString().equals(bracelet)) {
                    json = j;
                    break;
                } else if (j.get("name") != null && j.get("name").getAsString().equals(bracelet)) {
                    json = j;
                    break;
                }
            }
            if (json.get("name") != null) {
                braceletJson = json;
                itemsPanel.add(new ItemUITemplate(json, false, previousBr, aboveBr, 1045, 0, false));
            }
        }

        //Necklace
        JPanel previousN = null;
        JPanel aboveN = null;
        if (itemsPanel.size() > 0) previousN = itemsPanel.get(itemsPanel.size() - 1);
        if (itemsPanel.size() > 3) aboveN = itemsPanel.get(itemsPanel.size() - 4);
        if (necklace.contains("CI-")) {
            necklaceJson = JsonParser.parseString(necklace.replace("CI-", "")).getAsJsonObject();
            itemsPanel.add(new ItemUITemplate(necklaceJson, false, previousN, aboveN, 1045, 0, true));
        } else if (necklace.contains("CR-")) {
            if (necklace.contains("\"type\":\"Necklace\"")) {
                necklaceJson = CrafterUI.getCraftItemJson(recipe, ing, necklace, false);
                itemsPanel.add(CrafterUI.setDisplay(CrafterUI.getCraftItemJson(recipe, ing, necklace, true), necklaceJson, previousN, aboveN, 1045));
            }
        } else if (!necklace.isEmpty()) {
            JsonObject json = JsonParser.parseString("{}").getAsJsonObject();
            for (JsonObject j : necklaceJ) {
                if (j.get("displayName") != null && j.get("displayName").getAsString().equals(necklace)) {
                    json = j;
                    break;
                } else if (j.get("name") != null && j.get("name").getAsString().equals(necklace)) {
                    json = j;
                    break;
                }
            }
            if (json.get("name") != null) {
                necklaceJson = json;
                itemsPanel.add(new ItemUITemplate(json, false, previousN, aboveN, 1045, 0, false));
            }
        }

        //Weapon
        JPanel previousW = null;
        JPanel aboveW = null;
        if (itemsPanel.size() > 0) previousW = itemsPanel.get(itemsPanel.size() - 1);
        if (itemsPanel.size() > 3) aboveW = itemsPanel.get(itemsPanel.size() - 4);
        if (weapon.contains("CI-")) {
            weaponJson = JsonParser.parseString(weapon.replace("CI-", "")).getAsJsonObject();
            itemsPanel.add(new ItemUITemplate(weaponJson, false, previousW, aboveW, 1045, 0, true));
        } else if (weapon.contains("CR-")) {
            if (weapon.contains("\"type\":\"Spear\"") || weapon.contains("\"type\":\"Dagger\"") || weapon.contains("\"type\":\"Bow\"") || weapon.contains("\"type\":\"Wand\"") || weapon.contains("\"type\":\"Relik\"")) {
                weaponJson = CrafterUI.getCraftItemJson(recipe, ing, weapon, false);
                itemsPanel.add(CrafterUI.setDisplay(CrafterUI.getCraftItemJson(recipe, ing, weapon, true), weaponJson, previousW, aboveW, 1045));
            }
        } else if (!weapon.isEmpty()) {
            JsonObject json = JsonParser.parseString("{}").getAsJsonObject();
            for (JsonObject j : weaponJ) {
                if (j.get("displayName") != null && j.get("displayName").getAsString().equals(weapon)) {
                    json = j;
                    break;
                } else if (j.get("name") != null && j.get("name").getAsString().equals(weapon)) {
                    json = j;
                    break;
                }
            }
            if (json.get("name") != null) {
                weaponJson = json;
                itemsPanel.add(new ItemUITemplate(json, false, previousW, aboveW, 1045, 0, false));
            }
        }

        itemJsons = new ItemJsons(helmetJson, chestplateJson, leggingsJson, bootsJson, ring1Json, ring2Json, braceletJson, necklaceJson, weaponJson);

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
