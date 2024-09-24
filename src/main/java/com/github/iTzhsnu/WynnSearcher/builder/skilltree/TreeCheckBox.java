package com.github.iTzhsnu.WynnSearcher.builder.skilltree;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

public class TreeCheckBox extends JCheckBox {
    private final String name;
    private final List<String> description;
    private final int cost;
    private final int minArchetype;
    private final Archetype archetype;
    private final String req;
    private final List<String> cantUse;
    private final List<String> link;
    private final int x;
    private final int y;
    private final String apiName;

    /**
     * @param name Display Name
     * @param description Display Description
     * @param apiName API Name
     * @param req Need Tree
     * @param cantUse Can't Use Ability
     * @param link Linked Ability
     * @param archetype Need Archetype
     * @param minArchetype Need Archetype Point Min
     * @param cost Ability Point Min
     * @param x Position X
     * @param y Position Y
     */
    public TreeCheckBox(String name, List<String> description, String apiName, String req, List<String> cantUse, List<String> link, Archetype archetype, int minArchetype, int cost, int x, int y) {
        super();
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.minArchetype = minArchetype;
        this.archetype = archetype;
        this.req = req;
        this.cantUse = cantUse;
        this.link = link;
        this.x = x;
        this.y = y;
        this.apiName = apiName;

        setHorizontalAlignment(CENTER);
        setOpaque(false);

        setToolTip();
    }

    public String getTreeName() {
        return name;
    }

    public String getFixedTreeName() {
        StringBuilder sb = new StringBuilder();
        for (String name_Split : name.split(">")) {
            if (name_Split.length() > 5) {
                String s = name_Split.substring(0, name_Split.length() - 6);
                if (!s.contains("span")) sb.append(s);
            }
        }

        return sb.toString();
    }

    public int getCost() {
        return this.cost;
    }

    public int getMinArchetype() {
        return this.minArchetype;
    }

    public Archetype getArchetype() {
        return this.archetype;
    }

    public String getAPIName() {
        return apiName;
    }

    public List<String> getLink() {
        return link;
    }

    public String getReq() {
        return req;
    }

    public List<String> getCantUse() {
        return cantUse;
    }

    private void setToolTip() {
        StringBuilder sb = new StringBuilder();
        String nameS = "<html>" + fixesText(name) + "<br>";
        sb.append(nameS);
        for (int i = 0; description.size() > i; ++i) {
            if (i == 0 && description.get(i).isEmpty()) {
                continue;
            }
            String ns = "<br>" + fixesText(description.get(i));
            sb.append(ns);
        }

        setToolTipText(sb.toString());
    }

    public TreeCheckBox whiteIcon() {
        setIcon(getResizedIcon("/ability_buttons/white_off.png", 40, 40));
        setSelectedIcon(getResizedIcon("/ability_buttons/white_on.png", 40, 40));
        setBounds(x, y, 40, 40);
        return this;
    }

    public TreeCheckBox yellowIcon() {
        setIcon(getResizedIcon("/ability_buttons/yellow_off.png", 44, 44));
        setSelectedIcon(getResizedIcon("/ability_buttons/yellow_on.png", 44, 44));
        setBounds(x, y, 44, 44);
        return this;
    }

    public TreeCheckBox purpleIcon() {
        setIcon(getResizedIcon("/ability_buttons/purple_off.png", 48, 48));
        setSelectedIcon(getResizedIcon("/ability_buttons/purple_on.png", 48, 48));
        setBounds(x, y, 48, 48);
        return this;
    }

    public TreeCheckBox redIcon() {
        setIcon(getResizedIcon("/ability_buttons/red_off.png", 56, 56));
        setSelectedIcon(getResizedIcon("/ability_buttons/red_on.png", 56, 56));
        setBounds(x, y, 56, 56);
        return this;
    }

    public TreeCheckBox warriorIcon() {
        setIcon(getResizedIcon("/ability_buttons/warrior_off.png", 48, 48));
        setSelectedIcon(getResizedIcon("/ability_buttons/warrior_on.png", 48, 48));
        setBounds(x, y, 48, 48);
        return this;
    }

    public TreeCheckBox archerIcon() {
        setIcon(getResizedIcon("/ability_buttons/archer_off.png", 48, 48));
        setSelectedIcon(getResizedIcon("/ability_buttons/archer_on.png", 48, 48));
        setBounds(x, y, 48, 48);
        return this;
    }

    public TreeCheckBox assassinIcon() {
        setIcon(getResizedIcon("/ability_buttons/assassin_off.png", 48, 48));
        setSelectedIcon(getResizedIcon("/ability_buttons/assassin_on.png", 48, 48));
        setBounds(x, y, 48, 48);
        return this;
    }

    public TreeCheckBox mageIcon() {
        setIcon(getResizedIcon("/ability_buttons/mage_off.png", 48, 48));
        setSelectedIcon(getResizedIcon("/ability_buttons/mage_on.png", 48, 48));
        setBounds(x, y, 48, 48);
        return this;
    }

    public TreeCheckBox shamanIcon() {
        setIcon(getResizedIcon("/ability_buttons/shaman_off.png", 48, 48));
        setSelectedIcon(getResizedIcon("/ability_buttons/shaman_on.png", 48, 48));
        setBounds(x, y, 48, 48);
        return this;
    }

    public ImageIcon getResizedIcon(String s, int width, int height) {
        return new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(s))).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }

    public static String fixesText(String text) {
        if (!text.isEmpty()) {
            String[] texts = text.split("&");
            StringBuilder sb = new StringBuilder();
            int type = 0;
            if (text.charAt(0) != '&') {
                String s = "<font color=\"#AAAAAA\">" + texts[0];
                sb.append(s);
                type = 1;
            }
            for (int i = 0; texts.length > i; ++i) {
                if (text.charAt(0) != '&' && i == 0) continue;
                String st = texts[i];
                String s;
                if (st.length() > 1) {
                    if (st.charAt(0) != 'l' && st.charAt(0) != 'n') if (type == 1) sb.append("</font>");
                    String output = texts[i].substring(1);
                    switch (st.charAt(0)) {
                        case '0':
                            s = "<font color=\"#000000\">" + output;
                            sb.append(s);
                            break;
                        case '1':
                            s = "<font color=\"#0000AA\">" + output;
                            sb.append(s);
                            break;
                        case '2':
                            s = "<font color=\"#00AA00\">" + output;
                            sb.append(s);
                            break;
                        case '3':
                            s = "<font color=\"#00AAAA\">" + output;
                            sb.append(s);
                            break;
                        case '4':
                            s = "<font color=\"#AA0000\">" + output;
                            sb.append(s);
                            break;
                        case '5':
                            s = "<font color=\"#AA00AA\">" + output;
                            sb.append(s);
                            break;
                        case '6':
                            s = "<font color=\"#FFAA00\">" + output;
                            sb.append(s);
                            break;
                        case '7':
                            s = "<font color=\"#AAAAAA\">" + output;
                            sb.append(s);
                            break;
                        case '8':
                            s = "<font color=\"#555555\">" + output;
                            sb.append(s);
                            break;
                        case '9':
                            s = "<font color=\"#5555FF\">" + output;
                            sb.append(s);
                            break;
                        case 'a':
                            s = "<font color=\"#55FF55\">" + output;
                            sb.append(s);
                            break;
                        case 'b':
                            s = "<font color=\"#55FFFF\">" + output;
                            sb.append(s);
                            break;
                        case 'c':
                            s = "<font color=\"#FF5555\">" + output;
                            sb.append(s);
                            break;
                        case 'd':
                            s = "<font color=\"#FF55FF\">" + output;
                            sb.append(s);
                            break;
                        case 'e':
                            s = "<font color=\"#FFFF55\">" + output;
                            sb.append(s);
                            break;
                        case 'f':
                            s = "<font color=\"#FFFFFF\">" + output;
                            sb.append(s);
                            break;
                        case 'l':
                            s = "<b>" + output + "</b>";
                            sb.append(s);
                            break;
                        case 'n':
                            s = "<u>" + output + "</u>";
                            sb.append(s);
                            break;
                    }
                    if (type == 2) sb.append("</b>");
                    if (type == 3) sb.append("</u>");
                    type = 0;
                } else if (st.length() == 1) {
                    type = 1;
                    switch (st.charAt(0)) {
                        case '0':
                            sb.append("<font color=\"#000000\">");
                            break;
                        case '1':
                            sb.append("<font color=\"#0000AA\">");
                            break;
                        case '2':
                            sb.append("<font color=\"#00AA00\">");
                            break;
                        case '3':
                            sb.append("<font color=\"#00AAAA\">");
                            break;
                        case '4':
                            sb.append("<font color=\"#AA0000\">");
                            break;
                        case '5':
                            sb.append("<font color=\"#AA00AA\">");
                            break;
                        case '6':
                            sb.append("<font color=\"#FFAA00\">");
                            break;
                        case '7':
                            sb.append("<font color=\"#AAAAAA\">");
                            break;
                        case '8':
                            sb.append("<font color=\"#555555\">");
                            break;
                        case '9':
                            sb.append("<font color=\"#5555FF\">");
                            break;
                        case 'a':
                            sb.append("<font color=\"#55FF55\">");
                            break;
                        case 'b':
                            sb.append("<font color=\"#55FFFF\">");
                            break;
                        case 'c':
                            sb.append("<font color=\"#FF5555\">");
                            break;
                        case 'd':
                            sb.append("<font color=\"#FF55FF\">");
                            break;
                        case 'e':
                            sb.append("<font color=\"#FFFF55\">");
                            break;
                        case 'f':
                            sb.append("<font color=\"#FFFFFF\">");
                            break;
                        case 'l':
                            sb.append("<b>");
                            type = 2;
                            break;
                        case 'n':
                            sb.append("<u>");
                            type = 3;
                            break;
                    }
                }
            }

            return sb.toString().replaceAll("À", " ");
        }
        return text;
    }

    @Override
    public JToolTip createToolTip() {
        JToolTip toolTip = new JToolTip().createToolTip();
        toolTip.setBackground(new Color(15, 1, 15));
        return toolTip;
    }
}
