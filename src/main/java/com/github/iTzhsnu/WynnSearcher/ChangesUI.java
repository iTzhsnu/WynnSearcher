package com.github.iTzhsnu.WynnSearcher;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ChangesUI {
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

        before.setBounds(300, 240, 120, 20);
        after.setBounds(640, 240, 120, 20);

        setFileList("Type: Armor and Weapon");

        pane.add(type);
        pane.add(before);
        pane.add(after);
    }

    public void setChangesVisible(boolean visible) {
        type.setVisible(visible);
        before.setVisible(visible);
        after.setVisible(visible);
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
        try {
            if (itemFile.listFiles() != null) {
                List<JsonObject> beforeJ = null;
                List<JsonObject> afterJ = new ArrayList<>(itemLatest);
                if (!before.getItemAt(before.getSelectedIndex()).isEmpty()) {
                    boolean beforeB = false;
                    boolean afterB = after.getItemAt(after.getSelectedIndex()).equals("Latest (Now)");
                    if (afterB) {
                        search.filterItemType(afterJ);
                    }
                    for (File f : itemFile.listFiles()) {
                        if (f.getName().replaceAll(".json", "").equals(before.getItemAt(before.getSelectedIndex()))) {
                            beforeJ = new ArrayList<>();
                            for (JsonElement je : JsonParser.parseReader(new JsonReader(new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8))).getAsJsonObject().get("items").getAsJsonArray()) {
                                if (search.filterItemType(je.getAsJsonObject())) {
                                    beforeJ.add(je.getAsJsonObject());
                                }
                            }
                            beforeB = true;
                        }
                        if (f.getName().replaceAll(".json", "").equals(after.getItemAt(after.getSelectedIndex()))) {
                            afterJ.clear();
                            for (JsonElement je : JsonParser.parseReader(new JsonReader(new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8))).getAsJsonObject().get("items").getAsJsonArray()) {
                                if (search.filterItemType(je.getAsJsonObject())) {
                                    afterJ.add(je.getAsJsonObject());
                                }
                            }
                            afterB = true;
                        }
                        if (beforeB && afterB) break;
                    }
                }
                if (beforeJ != null) {
                    switch (type.getSelectedIndex()) {
                        case 0: { //NEW
                            List<String> beforeL = new ArrayList<>();
                            List<JsonObject> afterL = new ArrayList<>();
                            for (JsonObject j : beforeJ) {
                                beforeL.add(j.get("name").getAsString());
                            }
                            for (JsonObject j : afterJ) {
                                if (!beforeL.contains(j.get("name").getAsString())) {
                                    afterL.add(j);
                                }
                            }
                            if (afterL.size() > 0) {
                                search.searchItems(afterL);
                            }
                            break;
                        }
                        case 1: { //CHANGED
                            List<String> changedL = new ArrayList<>();
                            List<String> beforeL = new ArrayList<>();
                            for (JsonObject j : beforeJ) {
                                beforeL.add(j.get("name").getAsString());
                            }
                            for (JsonObject j : afterJ) {
                                if (!beforeJ.contains(j) && beforeL.contains(j.get("name").getAsString())) {
                                    changedL.add(j.get("name").getAsString());
                                }
                            }
                            if (changedL.size() > 0) {
                                List<JsonObject> modify = new ArrayList<>();
                                for (JsonObject j : beforeJ) {
                                    if (changedL.contains(j.get("name").getAsString())) {
                                        modify.add(j);
                                    }
                                }
                                for (JsonObject j : afterJ) {
                                    if (changedL.contains(j.get("name").getAsString())) {
                                        modify.add(j);
                                    }
                                }
                                changedL.clear();
                                search.filterItems(modify);
                                for (int i = search.getSearchedItems().size(); i > 0; --i) {
                                    search.sortItems(changedL);
                                }
                                for (String s : changedL) {
                                    for (JsonObject j : beforeJ) {
                                        if (j.get("name").getAsString().equals(s)) {
                                            search.getItemDisplays().add(new ItemUITemplate(j, "item", null, null, search.getScrollPane().getWidth(), 0, false));
                                            break;
                                        }
                                    }
                                    for (JsonObject j : afterJ) {
                                        if (j.get("name").getAsString().equals(s)) {
                                            search.getItemDisplays().add(new ItemUITemplate(j, "item", null, null, search.getScrollPane().getWidth(), 0, false));
                                            break;
                                        }
                                    }
                                }
                                setChangesDisplayPos();
                            }
                            break;
                        }
                        case 2: { //REMOVED
                            List<JsonObject> beforeL = new ArrayList<>();
                            List<String> afterL = new ArrayList<>();
                            for (JsonObject j : afterJ) {
                                afterL.add(j.get("name").getAsString());
                            }
                            for (JsonObject j : beforeJ) {
                                if (!afterL.contains(j.get("name").getAsString())) {
                                    beforeL.add(j);
                                }
                            }
                            if (beforeL.size() > 0) {
                                search.searchItems(beforeL);
                            }
                            break;
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void searchIng() {
        try {
            if (ingFile.listFiles() != null) {
                List<JsonObject> beforeJ = null;
                List<JsonObject> afterJ = new ArrayList<>(ingLatest);
                if (!before.getItemAt(before.getSelectedIndex()).isEmpty()) {
                    boolean beforeB = false;
                    boolean afterB = after.getItemAt(after.getSelectedIndex()).equals("Latest (Now)");
                    if (afterB) {
                        search.filterIngType(afterJ);
                    }
                    for (File f : ingFile.listFiles()) {
                        if (f.getName().replaceAll(".json", "").equals(before.getItemAt(before.getSelectedIndex()))) {
                            beforeJ = new ArrayList<>();
                            for (JsonElement je : JsonParser.parseReader(new JsonReader(new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8))).getAsJsonObject().get("items").getAsJsonArray()) {
                                if (search.filterIngType(je.getAsJsonObject())) {
                                    beforeJ.add(je.getAsJsonObject());
                                }
                            }
                            beforeB = true;
                        }
                        if (f.getName().replaceAll(".json", "").equals(after.getItemAt(after.getSelectedIndex()))) {
                            afterJ.clear();
                            for (JsonElement je : JsonParser.parseReader(new JsonReader(new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8))).getAsJsonObject().get("items").getAsJsonArray()) {
                                if (search.filterIngType(je.getAsJsonObject())) {
                                    afterJ.add(je.getAsJsonObject());
                                }
                            }
                            afterB = true;
                        }
                        if (beforeB && afterB) break;
                    }
                }
                if (beforeJ != null) {
                    switch (type.getSelectedIndex()) {
                        case 0: { //NEW
                            List<String> beforeL = new ArrayList<>();
                            List<JsonObject> afterL = new ArrayList<>();
                            for (JsonObject j : beforeJ) {
                                beforeL.add(j.get("name").getAsString());
                            }
                            for (JsonObject j : afterJ) {
                                if (!beforeL.contains(j.get("name").getAsString())) {
                                    afterL.add(j);
                                }
                            }
                            if (afterL.size() > 0) {
                                search.searchIngredient(afterL);
                            }
                            break;
                        }
                        case 1: { //CHANGED
                            List<String> changedL = new ArrayList<>();
                            List<String> beforeL = new ArrayList<>();
                            for (JsonObject j : beforeJ) {
                                beforeL.add(j.get("name").getAsString());
                            }
                            for (JsonObject j : afterJ) {
                                if (!beforeJ.contains(j) && beforeL.contains(j.get("name").getAsString())) {
                                    changedL.add(j.get("name").getAsString());
                                }
                            }
                            if (changedL.size() > 0) {
                                List<JsonObject> modify = new ArrayList<>();
                                for (JsonObject j : beforeJ) {
                                    if (changedL.contains(j.get("name").getAsString())) {
                                        modify.add(j);
                                    }
                                }
                                for (JsonObject j : afterJ) {
                                    if (changedL.contains(j.get("name").getAsString())) {
                                        modify.add(j);
                                    }
                                }
                                changedL.clear();
                                search.filterIng(modify);
                                for (int i = search.getSearchedItems().size(); i > 0; --i) {
                                    search.sortIngredients(changedL);
                                }
                                for (String s : changedL) {
                                    for (JsonObject j : beforeJ) {
                                        if (j.get("name").getAsString().equals(s)) {
                                            search.getItemDisplays().add(new ItemUITemplate(j, "ingredient", null, null, search.getScrollPane().getWidth(), 0, false));
                                            break;
                                        }
                                    }
                                    for (JsonObject j : afterJ) {
                                        if (j.get("name").getAsString().equals(s)) {
                                            search.getItemDisplays().add(new ItemUITemplate(j, "ingredient", null, null, search.getScrollPane().getWidth(), 0, false));
                                            break;
                                        }
                                    }
                                }
                                setChangesDisplayPos();
                            }
                            break;
                        }
                        case 2: { //REMOVED
                            List<JsonObject> beforeL = new ArrayList<>();
                            List<String> afterL = new ArrayList<>();
                            for (JsonObject j : afterJ) {
                                afterL.add(j.get("name").getAsString());
                            }
                            for (JsonObject j : beforeJ) {
                                if (!afterL.contains(j.get("name").getAsString())) {
                                    beforeL.add(j);
                                }
                            }
                            if (beforeL.size() > 0) {
                                search.searchIngredient(beforeL);
                            }
                            break;
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void searchOther() {
        try {
            if (otherFile.listFiles() != null) {
                List<JsonObject> beforeJ = null;
                List<JsonObject> afterJ = new ArrayList<>(otherLatest);
                if (!before.getItemAt(before.getSelectedIndex()).isEmpty()) {
                    boolean beforeB = false;
                    boolean afterB = after.getItemAt(after.getSelectedIndex()).equals("Latest (Now)");
                    if (afterB) {
                        search.filterOtherType(afterJ);
                    }
                    for (File f : otherFile.listFiles()) {
                        if (f.getName().replaceAll(".json", "").equals(before.getItemAt(before.getSelectedIndex()))) {
                            beforeJ = new ArrayList<>();
                            for (JsonElement je : JsonParser.parseReader(new JsonReader(new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8))).getAsJsonObject().get("items").getAsJsonArray()) {
                                if (search.filterOtherType(je.getAsJsonObject())) {
                                    beforeJ.add(je.getAsJsonObject());
                                }
                            }
                            beforeB = true;
                        }
                        if (f.getName().replaceAll(".json", "").equals(after.getItemAt(after.getSelectedIndex()))) {
                            afterJ.clear();
                            for (JsonElement je : JsonParser.parseReader(new JsonReader(new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8))).getAsJsonObject().get("items").getAsJsonArray()) {
                                if (search.filterOtherType(je.getAsJsonObject())) {
                                    afterJ.add(je.getAsJsonObject());
                                }
                            }
                            afterB = true;
                        }
                        if (beforeB && afterB) break;
                    }
                }
                if (beforeJ != null) {
                    switch (type.getSelectedIndex()) {
                        case 0: { //NEW
                            List<String> beforeL = new ArrayList<>();
                            List<JsonObject> afterL = new ArrayList<>();
                            for (JsonObject j : beforeJ) {
                                beforeL.add(j.get("name").getAsString());
                            }
                            for (JsonObject j : afterJ) {
                                if (!beforeL.contains(j.get("name").getAsString())) {
                                    afterL.add(j);
                                }
                            }
                            if (afterL.size() > 0) {
                                search.searchOtherItems(afterL);
                            }
                            break;
                        }
                        case 1: { //CHANGED
                            List<String> changedL = new ArrayList<>();
                            List<String> beforeL = new ArrayList<>();
                            for (JsonObject j : beforeJ) {
                                beforeL.add(j.get("name").getAsString());
                            }
                            for (JsonObject j : afterJ) {
                                if (!beforeJ.contains(j) && beforeL.contains(j.get("name").getAsString())) {
                                    changedL.add(j.get("name").getAsString());
                                }
                            }
                            if (changedL.size() > 0) {
                                List<JsonObject> modify = new ArrayList<>();
                                for (JsonObject j : beforeJ) {
                                    if (changedL.contains(j.get("name").getAsString())) {
                                        modify.add(j);
                                    }
                                }
                                for (JsonObject j : afterJ) {
                                    if (changedL.contains(j.get("name").getAsString())) {
                                        modify.add(j);
                                    }
                                }
                                changedL.clear();
                                search.filterOther(modify);
                                for (int i = search.getSearchedItems().size(); i > 0; --i) {
                                    search.sortOtherItems(changedL);
                                }
                                for (String s : changedL) {
                                    for (JsonObject j : beforeJ) {
                                        if (j.get("name").getAsString().equals(s)) {
                                            search.getItemDisplays().add(new ItemUITemplate(j, "other", null, null, search.getScrollPane().getWidth(), 0, false));
                                            break;
                                        }
                                    }
                                    for (JsonObject j : afterJ) {
                                        if (j.get("name").getAsString().equals(s)) {
                                            search.getItemDisplays().add(new ItemUITemplate(j, "other", null, null, search.getScrollPane().getWidth(), 0, false));
                                            break;
                                        }
                                    }
                                }
                                setChangesDisplayPos();
                            }
                            break;
                        }
                        case 2: { //REMOVED
                            List<JsonObject> beforeL = new ArrayList<>();
                            List<String> afterL = new ArrayList<>();
                            for (JsonObject j : afterJ) {
                                afterL.add(j.get("name").getAsString());
                            }
                            for (JsonObject j : beforeJ) {
                                if (!afterL.contains(j.get("name").getAsString())) {
                                    beforeL.add(j);
                                }
                            }
                            if (beforeL.size() > 0) {
                                search.searchOtherItems(beforeL);
                            }
                            break;
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
}
