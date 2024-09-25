package com.github.iTzhsnu.WynnSearcher.builder;

import com.github.iTzhsnu.WynnSearcher.CrafterUI;
import com.github.iTzhsnu.WynnSearcher.ItemType;
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
    private ItemJsons itemJsons = new ItemJsons(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

    public Item_Display(JPanel p) {
        pane.setPreferredSize(new Dimension(1045, 497));
        pane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane(pane);
        scrollPane.setBounds(1, 1680, 1063, 500); // Y + 70
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);

        p.add(scrollPane);
    }

    public void setItem_Display(List<JComboBox<String>> box, List<JsonObject> ing, List<JsonObject> recipe,
                                List<JsonObject> helmetJ, List<JsonObject> chestplateJ, List<JsonObject> leggingsJ, List<JsonObject> bootsJ, List<JsonObject> ringJ, List<JsonObject> braceletJ, List<JsonObject> necklaceJ, List<JsonObject> weaponJ,
                                List<JsonObject> armourTomeJ, List<JsonObject> guildTomeJ, List<JsonObject> weaponTomeJ, List<JsonObject> marathonTomeJ, List<JsonObject> lootrunTomeJ, List<JsonObject> expertiseTomeJ, List<JsonObject> mysticismTomeJ, int classID) {
        pane.removeAll();
        if (!itemsPanel.isEmpty()) {
            itemsPanel.subList(0, itemsPanel.size()).clear();
        }

        //Item Json
        JsonObject helmetJson = setItemData(((JTextField) box.get(0).getEditor().getEditorComponent()).getText(), helmetJ, "helmet", recipe, ing, ItemType.ITEM);
        JsonObject chestplateJson = setItemData(((JTextField) box.get(1).getEditor().getEditorComponent()).getText(), chestplateJ, "chestplate", recipe, ing, ItemType.ITEM);
        JsonObject leggingsJson = setItemData(((JTextField) box.get(2).getEditor().getEditorComponent()).getText(), leggingsJ, "leggings", recipe, ing, ItemType.ITEM);
        JsonObject bootsJson = setItemData(((JTextField) box.get(3).getEditor().getEditorComponent()).getText(), bootsJ, "boots", recipe, ing, ItemType.ITEM);
        JsonObject ring1Json = setItemData(((JTextField) box.get(4).getEditor().getEditorComponent()).getText(), ringJ, "ring", recipe, ing, ItemType.ITEM);
        JsonObject ring2Json = setItemData(((JTextField) box.get(5).getEditor().getEditorComponent()).getText(), ringJ, "ring", recipe, ing, ItemType.ITEM);
        JsonObject braceletJson = setItemData(((JTextField) box.get(6).getEditor().getEditorComponent()).getText(), braceletJ, "bracelet", recipe, ing, ItemType.ITEM);
        JsonObject necklaceJson = setItemData(((JTextField) box.get(7).getEditor().getEditorComponent()).getText(), necklaceJ, "necklace", recipe, ing, ItemType.ITEM);

        //Weapon
        String weaponType = "spear";
        switch (classID) {
            case 1:
                weaponType = "dagger";
                break;
            case 2:
                weaponType = "bow";
                break;
            case 3:
                weaponType = "wand";
                break;
            case 4:
                weaponType = "relik";
                break;
        }
        JsonObject weaponJson = setItemData(((JTextField) box.get(8).getEditor().getEditorComponent()).getText(), weaponJ, weaponType, recipe, ing, ItemType.ITEM);

        //Tomes
        JsonObject armourTome1Json = setItemData(((JTextField) box.get(9).getEditor().getEditorComponent()).getText(), armourTomeJ, "tome", recipe, ing, ItemType.OTHER);
        JsonObject armourTome2Json = setItemData(((JTextField) box.get(10).getEditor().getEditorComponent()).getText(), armourTomeJ, "tome", recipe, ing, ItemType.OTHER);
        JsonObject armourTome3Json = setItemData(((JTextField) box.get(11).getEditor().getEditorComponent()).getText(), armourTomeJ, "tome", recipe, ing, ItemType.OTHER);
        JsonObject armourTome4Json = setItemData(((JTextField) box.get(12).getEditor().getEditorComponent()).getText(), armourTomeJ, "tome", recipe, ing, ItemType.OTHER);
        JsonObject guildTomeJson = setItemData(((JTextField) box.get(13).getEditor().getEditorComponent()).getText(), guildTomeJ, "tome", recipe, ing, ItemType.OTHER);
        JsonObject weaponTome1Json = setItemData(((JTextField) box.get(14).getEditor().getEditorComponent()).getText(), weaponTomeJ, "tome", recipe, ing, ItemType.OTHER);
        JsonObject weaponTome2Json = setItemData(((JTextField) box.get(15).getEditor().getEditorComponent()).getText(), weaponTomeJ, "tome", recipe, ing, ItemType.OTHER);
        JsonObject marathonTome1Json = setItemData(((JTextField) box.get(16).getEditor().getEditorComponent()).getText(), marathonTomeJ, "tome", recipe, ing, ItemType.OTHER);
        JsonObject marathonTome2Json = setItemData(((JTextField) box.get(17).getEditor().getEditorComponent()).getText(), marathonTomeJ, "tome", recipe, ing, ItemType.OTHER);
        JsonObject lootrunTomeJson = setItemData(((JTextField) box.get(18).getEditor().getEditorComponent()).getText(), lootrunTomeJ, "tome", recipe, ing, ItemType.OTHER);
        JsonObject expertiseTome1Json = setItemData(((JTextField) box.get(19).getEditor().getEditorComponent()).getText(), expertiseTomeJ, "tome", recipe, ing, ItemType.OTHER);
        JsonObject expertiseTome2Json = setItemData(((JTextField) box.get(20).getEditor().getEditorComponent()).getText(), expertiseTomeJ, "tome", recipe, ing, ItemType.OTHER);
        JsonObject mysticismTome1Json = setItemData(((JTextField) box.get(21).getEditor().getEditorComponent()).getText(), mysticismTomeJ, "tome", recipe, ing, ItemType.OTHER);
        JsonObject mysticismTome2Json = setItemData(((JTextField) box.get(22).getEditor().getEditorComponent()).getText(), mysticismTomeJ, "tome", recipe, ing, ItemType.OTHER);

        //Aspects (TODO)


        itemJsons = new ItemJsons(helmetJson, chestplateJson, leggingsJson, bootsJson, ring1Json, ring2Json, braceletJson, necklaceJson, weaponJson, armourTome1Json, armourTome2Json, armourTome3Json, armourTome4Json, guildTomeJson, weaponTome1Json, weaponTome2Json, marathonTome1Json, marathonTome2Json, lootrunTomeJson, expertiseTome1Json, expertiseTome2Json, mysticismTome1Json, mysticismTome2Json, null, null, null, null, null);

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

    public JsonObject setItemData(String itemName, List<JsonObject> itemJ, String itemType, List<JsonObject> recipe, List<JsonObject> ing, ItemType type) {
        JsonObject json = null;
        JPanel previous = null;
        JPanel above = null;
        if (!itemsPanel.isEmpty()) previous = itemsPanel.get(itemsPanel.size() - 1);
        if (itemsPanel.size() > 3) above = itemsPanel.get(itemsPanel.size() - 4);
        if (itemName.contains("CI-")) {
            json = JsonParser.parseString(itemName.replace("CI-", "")).getAsJsonObject();
            itemsPanel.add(new ItemUITemplate(json, type, previous, above, 1045, 0, true));
        } else if (itemName.contains("CR-")) {
            if (itemName.contains("\"type\":\"" + itemType + "\"")) {
                json = CrafterUI.getCraftItemJson(recipe, ing, itemName, false);
                itemsPanel.add(new ItemUITemplate(json, type, previous, above, 1045, 0, true));
            }
        } else if (!itemName.isEmpty()) {
            for (JsonObject j : itemJ) {
                if (j.get("name") != null && j.get("name").getAsString().equals(itemName)) {
                    json = j;
                    break;
                }
            }
            if (json != null) {
                itemsPanel.add(new ItemUITemplate(json, type, previous, above, 1045, 0, false));
            }
        }
        return json;
    }

    public ItemJsons getItemJsons() {
        return itemJsons;
    }
}
