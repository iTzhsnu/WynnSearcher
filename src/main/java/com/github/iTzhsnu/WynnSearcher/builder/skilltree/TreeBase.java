package com.github.iTzhsnu.WynnSearcher.builder.skilltree;

import com.github.iTzhsnu.WynnSearcher.GetAPI;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TreeBase implements ActionListener {
    private final JLayeredPane pane = new JLayeredPane();
    private final JScrollPane scrollPane;
    private final JLabel text = new JLabel();
    private final String archetype1;
    private final String archetype2;
    private final String archetype3;
    private final List<TreeCheckBox> tcb = new ArrayList<>();
    private final String classes;

    public TreeBase(JPanel p, Archetype archetype1, Archetype archetype2, Archetype archetype3, String classes, JLabel connect) {
        this.archetype1 = archetype1.getName();
        this.archetype2 = archetype2.getName();
        this.archetype3 = archetype3.getName();
        this.classes = classes;

        List<JsonObject> map = new ArrayList<>();
        JsonObject tree = new GetAPI().setWynnAbilityTreeAPI(classes, map, connect);
        for (JsonObject json : map) { //Connector
            int x = -1;
            int y = -1;

            if (json.get("COORDINATES") != null) {
                JsonObject j = json.get("COORDINATES").getAsJsonObject();
                x += j.get("X").getAsInt();
                y += j.get("Y").getAsInt();
            }

            if (x != -1 && y != -1 && json.get("META") != null && json.get("META").getAsJsonObject().get("ICON") != null) {
                switch (json.get("META").getAsJsonObject().get("ICON").getAsString()) {
                    case "connector_up_right_down_left":
                        new TreeIcon(posWidth(x, 0), posHeight(y, 0), getPane()).all();
                        break;
                    case "connector_down_left":
                        new TreeIcon(posWidth(x, 0), posHeight(y, 0), getPane()).left_down();
                        break;
                    case "connector_right_down":
                        new TreeIcon(posWidth(x, 0), posHeight(y, 0), getPane()).right_down();
                        break;
                    case "connector_right_left":
                        new TreeIcon(posWidth(x, 0), posHeight(y, 0), getPane()).right_left();
                        break;
                    case "connector_right_down_left":
                        new TreeIcon(posWidth(x, 0), posHeight(y, 0), getPane()).right_left_down();
                        break;
                    case "connector_up_down":
                        new TreeIcon(posWidth(x, 0), posHeight(y, 0), getPane()).up_down();
                        break;
                    case "connector_up_left":
                        new TreeIcon(posWidth(x, 0), posHeight(y, 0), getPane()).up_left();
                        break;
                    case "connector_up_down_left":
                        new TreeIcon(posWidth(x, 0), posHeight(y, 0), getPane()).up_left_down();
                        break;
                    case "connector_up_right":
                        new TreeIcon(posWidth(x, 0), posHeight(y, 0), getPane()).up_right();
                        break;
                    case "connector_up_right_down":
                        new TreeIcon(posWidth(x, 0), posHeight(y, 0), getPane()).up_right_down();
                        break;
                    case "connector_up_right_left":
                        new TreeIcon(posWidth(x, 0), posHeight(y, 0), getPane()).up_right_left();
                        break;
                }
            }
        }

        if (tree.get("pages") != null) {
            for (int i = 1; 7 >= i; ++i) {
                JsonObject json = tree.get("pages").getAsJsonObject().get(String.valueOf(i)).getAsJsonObject();
                for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
                    int x = -1;
                    int y = -1;
                    if (i > 1) y += 6 * (i - 1);
                    JsonObject j = json.get(entry.getKey()).getAsJsonObject();
                    j.addProperty("apiName", entry.getKey());
                    if (j.get("coordinates") != null) {
                        x += j.get("coordinates").getAsJsonObject().get("x").getAsInt();
                        y += j.get("coordinates").getAsJsonObject().get("y").getAsInt();
                    }

                    if (x != -1 && y != -1) {
                        String name = null;
                        List<String> description = null;
                        String req = "";
                        List<String> cantUse = null;
                        List<String> link = null;
                        Archetype archetype = Archetype.NONE;
                        int minArchetype = 0;
                        int cost = 0;
                        if (j.get("name") != null) { //Name
                            name = j.get("name").getAsString();
                        }
                        if (j.get("description") != null) { //Description
                            description = new ArrayList<>();
                            for (JsonElement je : j.get("description").getAsJsonArray()) {
                                description.add(je.getAsString());
                            }
                        }
                        if (j.get("requirements") != null) { //Requirements
                            JsonObject jo = j.get("requirements").getAsJsonObject();
                            if (jo.get("NODE") != null) { //Requirements Ability
                                req = jo.get("NODE").getAsString();
                            }
                            if (jo.get("ABILITY_POINTS") != null) { //Requirements Ability Points
                                cost = jo.get("ABILITY_POINTS").getAsInt();
                            }
                            if (jo.get("ARCHETYPE") != null) { //Archetype Min
                                minArchetype = jo.get("ARCHETYPE").getAsJsonObject().get("amount").getAsInt();
                            }
                        }
                        if (j.get("locks") != null && !j.get("locks").isJsonNull()) { //Can't Use Ability
                            cantUse = new ArrayList<>();
                            for (JsonElement je : j.get("locks").getAsJsonArray()) {
                                cantUse.add(je.getAsString());
                            }
                        }
                        if (j.get("links") != null && !j.get("links").isJsonNull()) { //Linked Ability
                            link = new ArrayList<>();
                            for (JsonElement je : j.get("links").getAsJsonArray()) {
                                link.add(je.getAsString());
                            }
                        }
                        if (j.get("archetype") != null && !j.get("archetype").isJsonNull()) { //Archetype
                            archetype = Archetype.GET.getOrDefault(j.get("archetype").getAsString(), Archetype.NONE);
                        }

                        if (j.get("icon") != null) {
                            switch (j.get("icon").getAsString()) {
                                case "275:49": //Yellow Ability Icon (4)
                                    getTcb().add(new TreeCheckBox(name, description, entry.getKey(), req, cantUse, link, archetype, minArchetype, cost, posWidth(x, 4), posHeight(y, 4)).yellowIcon());
                                    break;
                                case "275:53": //Purple Ability Icon (6)
                                    getTcb().add(new TreeCheckBox(name, description, entry.getKey(), req, cantUse, link, archetype, minArchetype, cost, posWidth(x, 6), posHeight(y, 6)).purpleIcon());
                                    break;
                                case "275:57": //Red Ability Icon (10)
                                    getTcb().add(new TreeCheckBox(name, description, entry.getKey(), req, cantUse, link, archetype, minArchetype, cost, posWidth(x, 10), posHeight(y, 10)).redIcon());
                                    break;
                                case "275:61": //Warrior Ability Icon (6)
                                    getTcb().add(new TreeCheckBox(name, description, entry.getKey(), req, cantUse, link, archetype, minArchetype, cost, posWidth(x, 6), posHeight(y, 6)).warriorIcon());
                                    break;
                                case "275:67": //Assassin Ability Icon (6)
                                    getTcb().add(new TreeCheckBox(name, description, entry.getKey(), req, cantUse, link, archetype, minArchetype, cost, posWidth(x, 6), posHeight(y, 6)).assassinIcon());
                                    break;
                                case "275:64": //Archer Ability Icon (6)
                                    getTcb().add(new TreeCheckBox(name, description, entry.getKey(), req, cantUse, link, archetype, minArchetype, cost, posWidth(x, 6), posHeight(y, 6)).archerIcon());
                                    break;
                                case "275:70": //Mage Ability Icon (6)
                                    getTcb().add(new TreeCheckBox(name, description, entry.getKey(), req, cantUse, link, archetype, minArchetype, cost, posWidth(x, 6), posHeight(y, 6)).mageIcon());
                                    break;
                                case "275:73": //Shaman Ability Icon (6)
                                    getTcb().add(new TreeCheckBox(name, description, entry.getKey(), req, cantUse, link, archetype, minArchetype, cost, posWidth(x, 6), posHeight(y, 6)).shamanIcon());
                                    break;
                                default: //White Ability Icon (275:45) (2)
                                    getTcb().add(new TreeCheckBox(name, description, entry.getKey(), req, cantUse, link, archetype, minArchetype, cost, posWidth(x, 2), posHeight(y, 2)).whiteIcon());
                                    break;
                            }
                        }
                    }
                }
            }
        }

        setTreeUI();

        pane.setPreferredSize(new Dimension(368, 1600));
        pane.setLayout(null);

        scrollPane = new JScrollPane(pane);
        scrollPane.setBounds(18, 490, 386, 400);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);

        text.setBounds(20, 465, 400, 20);
        text.setText(this.archetype1 + ": 0 | " + this.archetype2 + ": 0 | " + this.archetype3 + ": 0 | Ability Point: 0/45");

        p.add(scrollPane);
        p.add(text);
    }

    public String getClasses() {
        return this.classes;
    }

    public JLayeredPane getPane() {
        return this.pane;
    }

    public void setTreeUI() {
        for (TreeCheckBox b : tcb) {
            b.addActionListener(this);
            pane.add(b);
            pane.setLayer(b, 1);
        }
    }

    public void manualFixes(String name) {
        //TODO
    }

    public void setTreeVisible(boolean visible) {
        scrollPane.setVisible(visible);
        text.setVisible(visible);
    }

    public List<TreeCheckBox> getTcb() {
        return tcb;
    }

    /**
     * @param pos Position (0 ~ 8)
     * @param size Branch = 0, White = 2, Yellow = 4, Class and Purple = 6, Red = 10
     * @return Position (X)
     */
    public static int posWidth(int pos, int size) {
        return 22 + 36 * pos - size;
    }

    /**
     * @param pos Position (0 ~ )
     * @param size Branch = 0, White = 2, Yellow = 4, Class and Purple = 6, Red = 10
     * @return Position (Y)
     */
    public static int posHeight(int pos, int size) {
        return 16 + 36 * pos - size;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int total_Cost = 0;
        int archetype_1 = 0;
        int archetype_2 = 0;
        int archetype_3 = 0;
        for (int i = 0; getTcb().size() > i; ++i) {
            TreeCheckBox t = getTcb().get(i);
            if (t.isSelected()) {
                if (canUse(i)) {
                    total_Cost += t.getCost();
                    if (t.getArchetype() != null) {
                        switch (t.getArchetype().getNum()) {
                            case 1:
                                ++archetype_1;
                                break;
                            case 2:
                                ++archetype_2;
                                break;
                            case 3:
                                ++archetype_3;
                        }
                    }
                } else {
                    t.setSelected(false);
                }
            }
        }
        if (archetype_1 != 0 || archetype_2 != 0 || archetype_3 != 0) {
            for (int i = getTcb().size() - 1; i >= 0; --i) {
                TreeCheckBox t = getTcb().get(i);
                if(t.isSelected() && canUse(i) && t.getArchetype() != null) {
                    if (t.getArchetype() != Archetype.NONE) {
                        boolean cantUse = true;
                        switch (t.getArchetype().getNum()) {
                            case 1:
                                if (archetype_1 > t.getMinArchetype()) cantUse = false;
                                break;
                            case 2:
                                if (archetype_2 > t.getMinArchetype()) cantUse = false;
                                break;
                            case 3:
                                if (archetype_3 > t.getMinArchetype()) cantUse = false;
                                break;
                        }
                        if (cantUse) {
                            total_Cost -= t.getCost();
                            switch (t.getArchetype().getNum()) {
                                case 1:
                                    --archetype_1;
                                    break;
                                case 2:
                                    --archetype_2;
                                    break;
                                case 3:
                                    --archetype_3;
                                    break;
                            }
                            t.setSelected(false);
                        }
                    }
                }
            }
        }
        if (total_Cost > 45) {
            for (int i = getTcb().size() - 1; i >= 0; --i) {
                TreeCheckBox t = getTcb().get(i);
                if (t.isSelected()) {
                    if (total_Cost > 45) {
                        total_Cost -= t.getCost();
                        if (t.getArchetype() != null) {
                            switch (t.getArchetype().getNum()) {
                                case 1:
                                    --archetype_1;
                                    break;
                                case 2:
                                    --archetype_2;
                                    break;
                                case 3:
                                    --archetype_3;
                                    break;
                            }
                        }
                        t.setSelected(false);
                    } else {
                        boolean cantUse = true;
                        if (canUse(i)) {
                            if (t.getArchetype() != null) {
                                switch (t.getArchetype().getNum()) {
                                    case 1:
                                        if (archetype_1 > t.getMinArchetype()) cantUse = false;
                                        break;
                                    case 2:
                                        if (archetype_2 > t.getMinArchetype()) cantUse = false;
                                        break;
                                    case 3:
                                        if (archetype_3 > t.getMinArchetype()) cantUse = false;
                                        break;
                                }
                            } else {
                                cantUse = false;
                            }
                        }
                        if (cantUse) {
                            total_Cost -= t.getCost();
                            if (t.getArchetype() != null) {
                                switch (t.getArchetype().getNum()) {
                                    case 1:
                                        --archetype_1;
                                        break;
                                    case 2:
                                        --archetype_2;
                                        break;
                                    case 3:
                                        --archetype_3;
                                        break;
                                }
                            }
                            t.setSelected(false);
                        }
                    }
                }
            }
        }
        text.setText(this.archetype1 + ": " + archetype_1 + " | " + this.archetype2 + ": " + archetype_2 + " | " + this.archetype3 + ": " + archetype_3 + " | " + "Ability Point: " + total_Cost + "/45");
    }

    public boolean canUse(int pos) {
        TreeCheckBox checkT = getTcb().get(pos);
        String apiName = checkT.getAPIName();
        List<String> link = checkT.getLink();
        String req = checkT.getReq();
        List<String> cantUse = checkT.getCantUse();
        boolean isLink = false;
        boolean reqB = req.isEmpty();
        boolean canUse = true;
        boolean isLinking = false;
        List<Integer> check = new ArrayList<>();

        if (pos == 0) return true;
        for (int i = 0; getTcb().size() > i; ++i) {
            if (pos == i) continue;
            TreeCheckBox t = getTcb().get(i);
            if (t.isSelected()) {
                //has Link
                if (link != null) {
                    for (String s : link) {
                        if (s.equals(t.getAPIName())) {
                            isLinking = true;
                            break;
                        }
                    }
                }
                if (t.getLink() != null) {
                    for (String s : t.getLink()) {
                        if (s.equals(apiName)) {
                            if (isLinking) {
                                check.add(i);
                            } else {
                                isLink = true;
                            }
                        }
                    }
                }

                //is Req
                if (!req.isEmpty() && t.getAPIName().equals(req)) reqB = true;

                //is Can't Use
                if (cantUse != null) {
                    for (String s : cantUse) {
                        if (s.equals(t.getAPIName())) {
                            canUse = false;
                            break;
                        }
                    }
                }
            }
        }

        if (isLinking && !isLink) { //Not Selected Link
            START: for (int i : check) {
                TreeCheckBox t = getTcb().get(i);
                String apiName2 = t.getAPIName();
                for (int n = 0; getTcb().size() > n; ++n) {
                    if (n == i || n == pos) continue;
                    TreeCheckBox t2 = getTcb().get(n);
                    if (t2.isSelected() && t2.getLink() != null) {
                        for (String s : t2.getLink()) {
                            if (s.equals(apiName2)) {
                                isLink = true;
                                break START;
                            }
                        }
                    }
                }
            }
        }

        return isLink && reqB && canUse;
    }
}
