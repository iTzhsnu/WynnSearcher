package com.github.iTzhsnu.WynnSearcher.builder;

import com.github.iTzhsnu.WynnSearcher.ApiDataManager;
import com.github.iTzhsnu.WynnSearcher.CrafterUI;
import com.github.iTzhsnu.WynnSearcher.data.Item;
import com.github.iTzhsnu.WynnSearcher.data.ItemBase;
import com.github.iTzhsnu.WynnSearcher.general.ItemType;
import com.github.iTzhsnu.WynnSearcher.general.JsonValues;
import com.github.iTzhsnu.WynnSearcher.ui.EquipmentUi;
import com.google.gson.JsonParser;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Item_Display {
    private final JPanel pane = new JPanel();
    private final List<JPanel> itemsPanel = new ArrayList<>();
    private ItemData itemData = new ItemData(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

    public Item_Display(JPanel p) {
        pane.setPreferredSize(new Dimension(1045, 497));
        pane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane(pane);
        scrollPane.setBounds(1, 1120, 1063, 500); // Y + 70 (OLD: 1680)
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);

        p.add(scrollPane);
    }

    public void setItem_Display(List<JComboBox<String>> box, int classID) {
        pane.removeAll();
        if (!itemsPanel.isEmpty()) {
            itemsPanel.subList(0, itemsPanel.size()).clear();
        }

        ApiDataManager api = ApiDataManager.getManager();
        //Item Data
        ItemBase helmetData = setItemData(((JTextField) box.get(0).getEditor().getEditorComponent()).getText(), api.helmetData, JsonValues.HELMET, ItemType.ITEM);
        ItemBase chestplateData = setItemData(((JTextField) box.get(1).getEditor().getEditorComponent()).getText(), api.chestplateData, JsonValues.CHESTPLATE, ItemType.ITEM);
        ItemBase leggingsData = setItemData(((JTextField) box.get(2).getEditor().getEditorComponent()).getText(), api.leggingsData, JsonValues.LEGGINGS, ItemType.ITEM);
        ItemBase bootsData = setItemData(((JTextField) box.get(3).getEditor().getEditorComponent()).getText(), api.bootsData, JsonValues.BOOTS, ItemType.ITEM);
        ItemBase ring1Data = setItemData(((JTextField) box.get(4).getEditor().getEditorComponent()).getText(), api.ringData, JsonValues.RING, ItemType.ITEM);
        ItemBase ring2Data = setItemData(((JTextField) box.get(5).getEditor().getEditorComponent()).getText(), api.ringData, JsonValues.RING, ItemType.ITEM);
        ItemBase braceletData = setItemData(((JTextField) box.get(6).getEditor().getEditorComponent()).getText(), api.braceletData, JsonValues.BRACELET, ItemType.ITEM);
        ItemBase necklaceData = setItemData(((JTextField) box.get(7).getEditor().getEditorComponent()).getText(), api.necklaceData, JsonValues.NECKLACE, ItemType.ITEM);

        //Weapon
        String weaponType = JsonValues.SPEAR;
        switch (classID) {
            case 1:
                weaponType = JsonValues.DAGGER;
                break;
            case 2:
                weaponType = JsonValues.BOW;
                break;
            case 3:
                weaponType = JsonValues.WAND;
                break;
            case 4:
                weaponType = JsonValues.RELIK;
                break;
        }
        ItemBase weaponData = setItemData(((JTextField) box.get(8).getEditor().getEditorComponent()).getText(), api.weaponData, weaponType, ItemType.ITEM);

        //Tomes
        ItemBase armourTome1Data = setItemData(((JTextField) box.get(9).getEditor().getEditorComponent()).getText(), api.armourTomeData, JsonValues.TOME, ItemType.OTHER);
        ItemBase armourTome2Data = setItemData(((JTextField) box.get(10).getEditor().getEditorComponent()).getText(), api.armourTomeData, JsonValues.TOME, ItemType.OTHER);
        ItemBase armourTome3Data = setItemData(((JTextField) box.get(11).getEditor().getEditorComponent()).getText(), api.armourTomeData, JsonValues.TOME, ItemType.OTHER);
        ItemBase armourTome4Data = setItemData(((JTextField) box.get(12).getEditor().getEditorComponent()).getText(), api.armourTomeData, JsonValues.TOME, ItemType.OTHER);
        ItemBase guildTomeData = setItemData(((JTextField) box.get(13).getEditor().getEditorComponent()).getText(), api.guildTomeData, JsonValues.TOME, ItemType.OTHER);
        ItemBase weaponTome1Data = setItemData(((JTextField) box.get(14).getEditor().getEditorComponent()).getText(), api.weaponTomeData, JsonValues.TOME, ItemType.OTHER);
        ItemBase weaponTome2Data = setItemData(((JTextField) box.get(15).getEditor().getEditorComponent()).getText(), api.weaponTomeData, JsonValues.TOME, ItemType.OTHER);
        ItemBase marathonTome1Data = setItemData(((JTextField) box.get(16).getEditor().getEditorComponent()).getText(), api.marathonTomeData, JsonValues.TOME, ItemType.OTHER);
        ItemBase marathonTome2Data = setItemData(((JTextField) box.get(17).getEditor().getEditorComponent()).getText(), api.marathonTomeData, JsonValues.TOME, ItemType.OTHER);
        ItemBase lootrunTomeData = setItemData(((JTextField) box.get(18).getEditor().getEditorComponent()).getText(), api.lootrunTomeData, JsonValues.TOME, ItemType.OTHER);
        ItemBase expertiseTome1Data = setItemData(((JTextField) box.get(19).getEditor().getEditorComponent()).getText(), api.expertiseTomeData, JsonValues.TOME, ItemType.OTHER);
        ItemBase expertiseTome2Data = setItemData(((JTextField) box.get(20).getEditor().getEditorComponent()).getText(), api.expertiseTomeData, JsonValues.TOME, ItemType.OTHER);
        ItemBase mysticismTome1Data = setItemData(((JTextField) box.get(21).getEditor().getEditorComponent()).getText(), api.mysticismTomeData, JsonValues.TOME, ItemType.OTHER);
        ItemBase mysticismTome2Data = setItemData(((JTextField) box.get(22).getEditor().getEditorComponent()).getText(), api.mysticismTomeData, JsonValues.TOME, ItemType.OTHER);

        //Aspects (TODO)
        ItemBase aspect1Data = setItemData(((JTextField) box.get(23).getEditor().getEditorComponent()).getText(), api.aspects, JsonValues.ASPECT, ItemType.ASPECT);
        ItemBase aspect2Data = setItemData(((JTextField) box.get(24).getEditor().getEditorComponent()).getText(), api.aspects, JsonValues.ASPECT, ItemType.ASPECT);
        ItemBase aspect3Data = setItemData(((JTextField) box.get(25).getEditor().getEditorComponent()).getText(), api.aspects, JsonValues.ASPECT, ItemType.ASPECT);
        ItemBase aspect4Data = setItemData(((JTextField) box.get(26).getEditor().getEditorComponent()).getText(), api.aspects, JsonValues.ASPECT, ItemType.ASPECT);
        ItemBase aspect5Data = setItemData(((JTextField) box.get(27).getEditor().getEditorComponent()).getText(), api.aspects, JsonValues.ASPECT, ItemType.ASPECT);

        itemData = new ItemData(helmetData, chestplateData, leggingsData, bootsData, ring1Data, ring2Data, braceletData, necklaceData, weaponData, armourTome1Data, armourTome2Data, armourTome3Data, armourTome4Data, guildTomeData, weaponTome1Data, weaponTome2Data, marathonTome1Data, marathonTome2Data, lootrunTomeData, expertiseTome1Data, expertiseTome2Data, mysticismTome1Data, mysticismTome2Data, aspect1Data, aspect2Data, aspect3Data, aspect4Data, aspect5Data);

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

    public <T extends ItemBase> ItemBase setItemData(String itemName, List<T> items, String itemType, ItemType type) {
        ItemBase item = null;
        JPanel previous = null;
        JPanel above = null;
        ApiDataManager api = ApiDataManager.getManager();
        if (!itemsPanel.isEmpty()) previous = itemsPanel.get(itemsPanel.size() - 1);
        if (itemsPanel.size() > 3) above = itemsPanel.get(itemsPanel.size() - 4);
        if (itemName.contains("CI-")) { // TODO fixes (Custom)
            item = new Item(JsonParser.parseString(itemName.replace("CI-", "")).getAsJsonObject());
            itemsPanel.add(new EquipmentUi(item, type, previous, above, 1045, 0, true));
        } else if (itemName.contains("CR-")) {
            if (itemName.contains("\"type\":\"" + itemType + "\"")) { // Todo fixes (Craft)
                item = new Item(CrafterUI.getCraftItemJson(api.wynnRecipes, api.wynnIngredients, itemName, false));
                itemsPanel.add(new EquipmentUi(item, type, previous, above, 1045, 0, true));
            }
        } else if (!itemName.isEmpty()) {
            for (ItemBase i : items) {
                if (!i.getName().isEmpty() && i.getName().equals(itemName)) {
                    item = i;
                    break;
                }
            }
            if (item != null) {
                itemsPanel.add(new EquipmentUi(item, type, previous, above, 1045, 0, false));
            }
        }
        return item;
    }

    public ItemData getItemJsons() {
        return itemData;
    }
}
