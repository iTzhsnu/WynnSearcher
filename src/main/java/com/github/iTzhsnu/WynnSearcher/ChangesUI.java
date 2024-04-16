package com.github.iTzhsnu.WynnSearcher;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.List;

public class ChangesUI implements ActionListener {
    private final SearchUI search;
    private final File itemFile;
    private final File ingFile;
    private final File otherFile;
    private final List<JsonObject> itemLatest;
    private final List<JsonObject> ingLatest;
    private final List<JsonObject> otherLatest;

    private final JComboBox<String> type = new JComboBox<>();
    private final JComboBox<String> before = new JComboBox<>();
    private final JComboBox<String> after = new JComboBox<>();
    private final JComboBox<String> check_type = new JComboBox<>();

    public ChangesUI(SearchUI search, List<JsonObject> itemLatest, List<JsonObject> ingLatest, List<JsonObject> otherLatest) {
        this.search = search;
        Container pane = search.getContentPane();
        GetAPI getAPI = new GetAPI();
        itemFile = getAPI.getOldItemFile();
        ingFile = getAPI.getOldIngFile();
        otherFile = getAPI.getOldOtherFile();
        this.itemLatest = itemLatest;
        this.ingLatest = ingLatest;
        this.otherLatest = otherLatest;

        type.addItem("New Item");
        type.addItem("Changed Item");
        type.addItem("Deleted Item");
        type.setBounds(10, 240, 110, 20);
        type.addActionListener(this);

        before.setBounds(300, 240, 120, 20);
        after.setBounds(640, 240, 120, 20);

        check_type.addItem("Check Any");
        check_type.addItem("Check IDs");
        check_type.addItem("Check Obtain");
        check_type.addItem("Check Major IDs");
        check_type.addItem("Check IDs and Obtain");
        check_type.setBounds(900, 145, 145, 20);
        check_type.setVisible(false);

        setFileList("Type: Armor and Weapon");

        pane.add(type);
        pane.add(before);
        pane.add(after);
        pane.add(check_type);
    }

    public void setChangesVisible(boolean visible) {
        type.setVisible(visible);
        before.setVisible(visible);
        after.setVisible(visible);
        check_type.setVisible(visible && type.getItemAt(type.getSelectedIndex()).equals("Changed Item"));
    }

    public void setFileList(String s) {
        before.removeAllItems();
        after.removeAllItems();
        after.addItem("Latest (Now)");

        switch (s) {
            case "Type: Armor and Weapon": {
                if (itemFile.listFiles() != null) {
                    for (File f : itemFile.listFiles()) {
                        before.addItem(f.getName().replaceAll(".json", ""));
                        after.addItem(f.getName().replaceAll(".json", ""));
                    }
                }
                break;
            }
            case "Type: Ingredient": {
                if (ingFile.listFiles() != null) {
                    for (File f : ingFile.listFiles()) {
                        before.addItem(f.getName().replaceAll(".json", ""));
                        after.addItem(f.getName().replaceAll(".json", ""));
                    }
                }
                break;
            }
            case "Type: Other Items": {
                if (otherFile.listFiles() != null) {
                    for (File f : otherFile.listFiles()) {
                        before.addItem(f.getName().replaceAll(".json", ""));
                        after.addItem(f.getName().replaceAll(".json", ""));
                    }
                }
                break;
            }
        }
    }

    public void searchItems() {
        long startTime = System.currentTimeMillis();

        if (itemFile.listFiles() != null && !before.getItemAt(before.getSelectedIndex()).isEmpty() && !after.getItemAt(after.getSelectedIndex()).isEmpty()) {
            List<JsonObject> beforeJ = new ArrayList<>();
            List<JsonObject> afterJ = new ArrayList<>(itemLatest);

            if (after.getItemAt(after.getSelectedIndex()).equals("Latest (Now)")) {
                search.filterItemType(afterJ);
            }

            if (findJsonFile(itemFile, beforeJ, afterJ, before.getItemAt(before.getSelectedIndex()), after.getItemAt(after.getSelectedIndex()), ItemType.ITEM)) {
                switch (type.getSelectedIndex()) {
                    case 0: { //NEW
                        searchNewItems(ItemType.ITEM, beforeJ, afterJ);
                        break;
                    }
                    case 1: { //CHANGED
                        searchChangedItems(ItemType.ITEM, beforeJ, afterJ, startTime);
                        break;
                    }
                    case 2: { //REMOVED
                        searchRemovedItems(ItemType.ITEM, beforeJ, afterJ);
                        break;
                    }
                }
            }
        }
    }

    public void searchIng() {
        long startTime = System.currentTimeMillis();

        if (ingFile.listFiles() != null && !before.getItemAt(before.getSelectedIndex()).isEmpty() && !after.getItemAt(after.getSelectedIndex()).isEmpty()) {
            List<JsonObject> beforeJ = new ArrayList<>();
            List<JsonObject> afterJ = new ArrayList<>(ingLatest);

            if (after.getItemAt(after.getSelectedIndex()).equals("Latest (Now)")) {
                search.filterIngType(afterJ);
            }

            if (findJsonFile(ingFile, beforeJ, afterJ, before.getItemAt(before.getSelectedIndex()), after.getItemAt(after.getSelectedIndex()), ItemType.INGREDIENT)) {
                switch (type.getSelectedIndex()) {
                    case 0: { //NEW
                        searchNewItems(ItemType.INGREDIENT, beforeJ, afterJ);
                        break;
                    }
                    case 1: { //CHANGED
                        searchChangedItems(ItemType.INGREDIENT, beforeJ, afterJ, startTime);
                        break;
                    }
                    case 2: { //REMOVED
                        searchRemovedItems(ItemType.INGREDIENT, beforeJ, afterJ);
                        break;
                    }
                }
            }
        }
    }

    public void searchOther() {
        long startTime = System.currentTimeMillis();

        if (otherFile.listFiles() != null && !before.getItemAt(before.getSelectedIndex()).isEmpty() && !after.getItemAt(after.getSelectedIndex()).isEmpty()) {
            List<JsonObject> beforeJ = new ArrayList<>();
            List<JsonObject> afterJ = new ArrayList<>(otherLatest);

            if (after.getItemAt(after.getSelectedIndex()).equals("Latest (Now)")) {
                search.filterOtherType(afterJ);
            }

            if (findJsonFile(otherFile, beforeJ, afterJ, before.getItemAt(before.getSelectedIndex()), after.getItemAt(after.getSelectedIndex()), ItemType.OTHER)) {
                switch (type.getSelectedIndex()) {
                    case 0: { //NEW
                        searchNewItems(ItemType.OTHER, beforeJ, afterJ);
                        break;
                    }
                    case 1: { //CHANGED
                        searchChangedItems(ItemType.OTHER, beforeJ, afterJ, startTime);
                        break;
                    }
                    case 2: { //REMOVED
                        searchRemovedItems(ItemType.OTHER, beforeJ, afterJ);
                        break;
                    }
                }
            }
        }
    }

    public void setChangesDisplayPos() {
        List<JPanel> l = search.getItemDisplays();
        int y = 0;
        if (l.size() > 0) {
            for (int i = 0; l.size() - 1 > i; i += 2) {
                JPanel before = l.get(i);
                JPanel after = l.get(i + 1);
                before.setBounds(270, y + 10, 250, before.getBounds().height);
                after.setBounds(530, y + 10, 250, after.getBounds().height);
                if (before.getBounds().height > after.getBounds().height) {
                    y = before.getBounds().y + before.getBounds().height;
                } else {
                    y = after.getBounds().y + after.getBounds().height;
                }
                search.getSearched().add(l.get(i));
                search.getSearched().add(l.get(i + 1));
            }

            search.setDisplaySize();
        }
    }

    public boolean checkIDsDiff(JsonObject before, JsonObject after, ItemType itemType) {
        for (int i = 0; ItemUITemplate.ITEM_IDS.size() > i; ++i) {
            if (checkIDDiff(ItemUITemplate.ITEM_IDS.get(i), before, after, itemType)) return true;
        }

        for (int i = 0; ItemUITemplate.REVERSED_ITEM_IDS.size() > i; ++i) {
            if (checkIDDiff(ItemUITemplate.REVERSED_ITEM_IDS.get(i), before, after, itemType)) return true;
        }

        for (int i = 0; ItemUITemplate.SP_REQUESTS.size() > i; ++i) {
            if (checkIDDiff(ItemUITemplate.SP_REQUESTS.get(i), before, after, itemType)) return true;
        }

        if (itemType == ItemType.INGREDIENT) {
            for (int i = 0; ItemUITemplate.INGREDIENT_EFFECTIVENESS.size() > i; ++i) {
                if (checkIDDiff(ItemUITemplate.INGREDIENT_EFFECTIVENESS.get(i), before, after, itemType)) return true;
            }

            return checkIDDiff(Identifications.DURABILITY, before, after, itemType) || checkIDDiff(Identifications.DURATION, before, after, itemType) || checkIDDiff(Identifications.CHARGES, before, after, itemType);
        } else {
            for (int i = 0; ItemUITemplate.DAMAGE_IDS.size() > i; ++i) {
                if (checkIDDiff(ItemUITemplate.DAMAGE_IDS.get(i), before, after, itemType)) return true;
            }

            for (int i = 0; ItemUITemplate.DEFENSE_IDS.size() > i; ++i) {
                if (checkIDDiff(ItemUITemplate.DEFENSE_IDS.get(i), before, after, itemType)) return true;
            }

            return checkIDDiff(Identifications.ATTACK_SPEED, before, after, itemType);
        }
    }

    public boolean checkMajorIDsDiff(JsonObject before, JsonObject after) {
        return checkIDDiff(Identifications.MAJOR_IDS, before, after, ItemType.ITEM);
    }

    public boolean checkObtainDiff(JsonObject before, JsonObject after, ItemType itemType) {
        if (itemType == ItemType.INGREDIENT) {
            return checkObtainDiff_Ing(before, after);
        } else {
            return checkObtainDiff_Item(before, after);
        }
    }

    public boolean checkObtainDiff_Item(JsonObject before, JsonObject after) {
        return false;
    }

    public boolean checkObtainDiff_Ing(JsonObject before, JsonObject after) {
        return false;
    }

    public boolean checkIDDiff(Identifications id, JsonObject before, JsonObject after, ItemType itemType) {
        if (itemType == ItemType.INGREDIENT) {
            return checkIDDiff_Ing(id, before, after);
        } else {
            return checkIDDiff_Item(id, before, after);
        }
    }

    public boolean checkIDDiff_Item(Identifications id, JsonObject before, JsonObject after) {
        if (id.getItemName() != null && id.getItemFieldPos() != null) {
            //if (id == Identifications.DEFENSE_REQ) return false;
            if (id.getItemFieldPos().equals("nothing")) { //TODO CHANGE TO VALUE CHECK (Field Nothing)
                JsonElement beforeE = before.get(id.getItemName());
                JsonElement afterE = after.get(id.getItemName());
                if (beforeE != null && afterE != null) {
                    if (!beforeE.equals(afterE)) return true;
                } else if (beforeE != null || afterE != null) {
                    return true;
                }
            } else if (before.get(id.getItemFieldPos()) != null && after.get(id.getItemFieldPos()) != null) {
                JsonElement beforeE = before.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName());
                JsonElement afterE = after.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName());
                if (beforeE != null && afterE != null) {
                    if (!beforeE.isJsonObject() && !afterE.isJsonObject()) {
                        return !beforeE.equals(afterE);
                    } else if (!beforeE.isJsonObject() || !afterE.isJsonObject()) {
                        return true;
                    } else {
                        //return beforeE.getAsJsonObject().get("min").getAsInt() != afterE.getAsJsonObject().get("min").getAsInt() && beforeE.getAsJsonObject().get("max").getAsInt() != afterE.getAsJsonObject().get("max").getAsInt();
                        return beforeE.getAsJsonObject().get("min").getAsInt() != afterE.getAsJsonObject().get("min").getAsInt() || beforeE.getAsJsonObject().get("max").getAsInt() != afterE.getAsJsonObject().get("max").getAsInt();
                    }
                } else {
                    return beforeE != null || afterE != null;
                }
            }
            return before.get(id.getItemFieldPos()) != null || after.get(id.getItemFieldPos()) != null;
        } else {
            return false;
        }
    }

    public boolean checkIDDiff_Ing(Identifications id, JsonObject before, JsonObject after) {
        if (id.getIngName() != null && id.getIngFieldPos() != null) {
            if (id.getIngFieldPos().equals("nothing")) { //TODO CHANGE TO VALUE CHECK (Field Nothing)
                JsonElement beforeE = before.get(id.getIngName());
                JsonElement afterE = after.get(id.getIngName());
                if (beforeE != null && afterE != null) {
                    if (!beforeE.equals(afterE)) return true;
                } else if (beforeE != null || afterE != null) {
                    return true;
                }
            } else if (before.get(id.getIngFieldPos()) != null && after.get(id.getIngFieldPos()) != null) {
                JsonElement beforeE = before.get(id.getIngFieldPos()).getAsJsonObject().get(id.getIngName());
                JsonElement afterE = after.get(id.getIngFieldPos()).getAsJsonObject().get(id.getIngName());
                if (beforeE != null && afterE != null) {
                    if (!beforeE.isJsonObject() && !afterE.isJsonObject()) {
                        return !beforeE.equals(afterE);
                    } else if (!beforeE.isJsonObject() || !afterE.isJsonObject()) {
                        return true;
                    } else {
                        //return beforeE.getAsJsonObject().get("min").getAsInt() != afterE.getAsJsonObject().get("min").getAsInt() && beforeE.getAsJsonObject().get("max").getAsInt() != afterE.getAsJsonObject().get("max").getAsInt();
                        return beforeE.getAsJsonObject().get("min").getAsInt() != afterE.getAsJsonObject().get("min").getAsInt() || beforeE.getAsJsonObject().get("max").getAsInt() != afterE.getAsJsonObject().get("max").getAsInt();
                    }
                } else {
                    return beforeE != null || afterE != null;
                }
            }
            return before.get(id.getIngFieldPos()) != null || after.get(id.getIngFieldPos()) != null;
        } else {
            return false;
        }
    }

    public boolean findJsonFile(File file, List<JsonObject> before, List<JsonObject> after, String beforeFileName, String afterFileName, ItemType itemType) {
        try {
            boolean before_B = false;
            boolean after_B = afterFileName.equals("Latest (Now)");
            for (File f : file.listFiles()) {
                if (f.getName().replaceAll(".json", "").equals(beforeFileName)) {
                    for (JsonElement je : JsonParser.parseReader(new JsonReader(new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8))).getAsJsonObject().get("items").getAsJsonArray()) {
                        if (filterSelectedSearchType(itemType, je.getAsJsonObject())) {
                            before.add(je.getAsJsonObject());
                        }
                    }
                    before_B = true;
                    if (after_B) return true;
                }

                if (after_B) continue;

                if (f.getName().replaceAll(".json", "").equals(afterFileName)) {
                    after.clear();
                    for (JsonElement je : JsonParser.parseReader(new JsonReader(new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8))).getAsJsonObject().get("items").getAsJsonArray()) {
                        if (filterSelectedSearchType(itemType, je.getAsJsonObject())) {
                            after.add(je.getAsJsonObject());
                        }
                    }
                    after_B = true;
                    if (before_B) return true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     * @param itemType filter type
     * @param j check this item type
     * @return this item compatible
     */
    public boolean filterSelectedSearchType(ItemType itemType, JsonObject j) {
        switch (itemType) {
            case INGREDIENT:
                return search.filterIngType(j);
            case OTHER:
                return search.filterOtherType(j);
            default:
                return search.filterItemType(j);
        }
    }

    public void searchNewItems(ItemType itemType, List<JsonObject> before, List<JsonObject> after) {
        Set<String> beforeL = new HashSet<>(before.size());
        List<JsonObject> afterL = new ArrayList<>();

        for (JsonObject j : before) {
            beforeL.add(j.get("name").getAsString());
        }

        for (JsonObject j : after) {
            if (!beforeL.contains(j.get("name").getAsString())) {
                afterL.add(j);
            }
        }

        if (afterL.size() > 0) {
            switch (itemType) {
                case INGREDIENT:
                    search.searchIngredient(afterL);
                    break;
                case OTHER:
                    search.searchOtherItems(afterL);
                    break;
                default:
                    search.searchItems(afterL);
                    break;
            }
        } else {
            //Item Not Found.
        }
    }

    public void searchChangedItems(ItemType itemType, List<JsonObject> before, List<JsonObject> after, long startTime) {
        long midTime = startTime;

        Set<String> changedL = new HashSet<>(); //Changed Item List (String Name)

        if (check_type.getItemAt(check_type.getSelectedIndex()).equals("Check Any")) {
            Set<String> beforeL = new HashSet<>(before.size(), 2); //Before
            Set<JsonObject> setBefore = new HashSet<>(before);
            for (JsonObject j : before) {
                beforeL.add(j.get("name").getAsString());
            }

            for (JsonObject j : after) {
                if (!setBefore.contains(j) && beforeL.contains(j.get("name").getAsString())) { //Before != After
                    changedL.add(j.get("name").getAsString());
                }
            }
        } else {
            Map<String, JsonObject> afterMap = new HashMap<>(after.size(), 2);

            for (JsonObject j : after) {
                afterMap.put(j.get("name").getAsString(), j);
            }

            for (JsonObject j : before) {
                String name = j.get("name").getAsString();
                if (check_type.getItemAt(check_type.getSelectedIndex()).equals("Check IDs") || check_type.getItemAt(check_type.getSelectedIndex()).equals("Check IDs and Obtain")) {
                    if (checkIDsDiff(j, afterMap.get(name), itemType)) {
                        changedL.add(name);
                        continue;
                    }
                }

                if (check_type.getItemAt(check_type.getSelectedIndex()).equals("Check Obtain") || check_type.getItemAt(check_type.getSelectedIndex()).equals("Check IDs and Obtain")) {
                    //if (checkObtainDiff(before, after, itemType)) {
                    //    changedL.add(name);
                    //    continue;
                    //}
                }

                if (itemType != ItemType.INGREDIENT) {
                    if (check_type.getItemAt(check_type.getSelectedIndex()).equals("Check Major IDs") || check_type.getItemAt(check_type.getSelectedIndex()).equals("Check IDs and Obtain")) {
                        if (checkMajorIDsDiff(j, afterMap.get(name))) {
                            changedL.add(name);
                        }
                    }
                }
            }
        }

        if (changedL.size() > 0) {
            List<JsonObject> modify = new ArrayList<>();
            for (JsonObject j : before) {
                if (changedL.contains(j.get("name").getAsString())) {
                    modify.add(j);
                }
            }
            for (JsonObject j : after) {
                if (changedL.contains(j.get("name").getAsString())) {
                    modify.add(j);
                }
            }
            changedL.clear();

            switch (itemType) {
                case INGREDIENT:
                    search.filterIng(modify);
                    for (int i = search.getSearchedItems().size(); i > 0; --i) {
                        search.sortIngredients(changedL);
                    }
                    break;
                case OTHER:
                    search.filterOther(modify);
                    for (int i = search.getSearchedItems().size(); i > 0; --i) {
                        search.sortOtherItems(changedL);
                    }
                    break;
                default:
                    search.filterItems(modify);
                    for (int i = search.getSearchedItems().size(); i > 0; --i) {
                        search.sortItems(changedL);
                    }
                    break;
            }

            midTime = System.currentTimeMillis();

            for (String s : changedL) {
                for (JsonObject j : before) {
                    if (j.get("name").getAsString().equals(s)) {
                        search.getItemDisplays().add(new ItemUITemplate(j, itemType, null, null, search.getScrollPane().getWidth(), 0, false));
                        break;
                    }
                }
                for (JsonObject j : after) {
                    if (j.get("name").getAsString().equals(s)) {
                        search.getItemDisplays().add(new ItemUITemplate(j, itemType, null, null, search.getScrollPane().getWidth(), 0, false));
                        break;
                    }
                }
            }
            setChangesDisplayPos();
        }

        search.setProcessTime(startTime, midTime);
    }

    public void searchRemovedItems(ItemType itemType, List<JsonObject> before, List<JsonObject> after) {
        List<JsonObject> beforeL = new ArrayList<>();
        Set<String> afterL = new HashSet<>(after.size());

        for (JsonObject j : after) {
            afterL.add(j.get("name").getAsString());
        }

        for (JsonObject j : before) {
            if (!afterL.contains(j.get("name").getAsString())) {
                beforeL.add(j);
            }
        }

        if (beforeL.size() > 0) {
            switch (itemType) {
                case INGREDIENT:
                    search.searchIngredient(beforeL);
                    break;
                case OTHER:
                    search.searchOtherItems(beforeL);
                    break;
                default:
                    search.searchItems(beforeL);
                    break;
            }
        } else {
            //Item Not Found.
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        check_type.setVisible(type.getItemAt(type.getSelectedIndex()).equals("Changed Item"));
    }
}
