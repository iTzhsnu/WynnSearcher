package com.github.iTzhsnu.WynnSearcher.builder.skilltree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TreeBase implements ActionListener {
    private final JLayeredPane pane = new JLayeredPane();
    private final JScrollPane scrollPane;
    private final JLabel text = new JLabel();
    private final String archetype1;
    private final String archetype2;
    private final String archetype3;
    private final List<TreeCheckBox> tcb = new ArrayList<>();
    private final String classes;

    public TreeBase(JPanel p, TreeCheckBox.ArchetypeEnum archetype1, TreeCheckBox.ArchetypeEnum archetype2, TreeCheckBox.ArchetypeEnum archetype3, String classes) {
        this.archetype1 = archetype1.getName();
        this.archetype2 = archetype2.getName();
        this.archetype3 = archetype3.getName();
        this.classes = classes;

        pane.setPreferredSize(new Dimension(368, 1500));
        pane.setLayout(null);

        scrollPane = new JScrollPane(pane);
        scrollPane.setBounds(18, 385, 386, 400);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);

        text.setBounds(20, 360, 400, 20);
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
        for (int i = 0; tcb.size() > i; ++i) {
            TreeCheckBox b = tcb.get(i);

            //debugTree(i, b);

            b.addActionListener(this);
            pane.add(b);
            pane.setLayer(b, 1);
        }
    }

    public void debugTree(int i, TreeCheckBox b) {
        if (classes.equals("Assassin")) {
            StringBuilder sb = new StringBuilder();
            String n = i + ". " + b.getTreeName();
            sb.append(n);

            if (b.getPrevious() != null) {
                sb.append(" , Previous:");
                for (TreeCheckBox treeBox : b.getPrevious()) {
                    String s = " " + treeBox.getTreeName();
                    sb.append(s);
                }
            }

            if (b.getSpPrevious() != null) {
                sb.append(" , Sp Previous:");
                for (SpPrevious sp : b.getSpPrevious()) {
                    sb.append(" (");
                    for (TreeCheckBox treeBox : sp.getT()) {
                        String s = " " + treeBox.getTreeName();
                        sb.append(s);
                    }
                    sb.append(" )");
                }
            }

            System.out.println(sb);
        }
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
        for (TreeCheckBox t : getTcb()) {
            if (t.isSelected()) {
                if (t.canUse()) {
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
                if(t.isSelected() && t.canUse() && t.getArchetype() != null) {
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
                        if (t.canUse()) {
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
}
