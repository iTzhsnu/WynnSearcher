package com.github.iTzhsnu.WynnSearcher.builder;

import com.github.iTzhsnu.WynnSearcher.Identifications;
import com.github.iTzhsnu.WynnSearcher.ItemUITemplate;
import com.google.gson.JsonObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Comprehensive_Display {
    private final JPanel pane = new JPanel();
    private final List<JLabel> label = new ArrayList<>();

    public Comprehensive_Display(JPanel p) {
        pane.setPreferredSize(new Dimension(182, 1000));
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(pane);
        scrollPane.setBounds(850, 120, 200, 205);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);

        p.add(scrollPane);
    }

    public void setDisplay(ItemJsons itemJsons, List<SetBonus> setBonuses, List<Integer> equipOrder) {
        int size = 0;

        //Equip Order
        if (itemJsons.getJsonObjectList().size() > 0) {
            label.add(new JLabel("Equip Order"));

            for (int i = 0; itemJsons.getJsonObjectList().size() > i; ++i) {
                label.add(new JLabel(itemJsons.getJsonObjectList().get(equipOrder.get(i)).get("name").getAsString()));
            }

            label.add(new JLabel(" "));
            size = label.size();
            for (JLabel l : label) {
                pane.add(l);
            }
        }

        //Set Bonuses
        if (setBonuses.size() > 0) {
            label.add(new JLabel("Set Bonuses"));
            for (SetBonus setBonus : setBonuses) {
                label.add(new JLabel(setBonus.getName()));
                for (int i = 0; ItemUITemplate.ITEM_IDS.size() > i; ++i) {
                    Identifications id = ItemUITemplate.ITEM_IDS.get(i);
                    if (setBonus.getId_Numbers(false)[ID_Display.ID_INT.get(id)] != 0) {
                        label.add(new JLabel(id.getDisplayName() + ": " + ItemUITemplate.setPlus(setBonus.getId_Numbers(false)[ID_Display.ID_INT.get(id)]) + id.getDisplaySp()));
                    }
                }
                for (int i = 0; ItemUITemplate.REVERSED_ITEM_IDS.size() > i; ++i) {
                    Identifications id = ItemUITemplate.REVERSED_ITEM_IDS.get(i);
                    if (setBonus.getId_Numbers(false)[ID_Display.ID_INT.get(id)] != 0) {
                        label.add(new JLabel(id.getDisplayName() + ": " + ItemUITemplate.setPlus(setBonus.getId_Numbers(false)[ID_Display.ID_INT.get(id)]) + id.getDisplaySp()));
                    }
                }
                label.add(new JLabel(" "));
            }

            for (int i = size; label.size() > i; ++i) {
                pane.add(label.get(i));
            }
            size = label.size();
        }


        //Major IDs
        if (itemJsons.getMajorIDNameList().size() > 0) {
            label.add(new JLabel("Major IDs"));
            for (String s : itemJsons.getMajorIDNameList()) {
                label.add(new JLabel(s));
            }

            for (int i = size; label.size() > i; ++i) {
                pane.add(label.get(i));
            }
        }

        SwingUtilities.updateComponentTreeUI(pane);
    }

    public void reset() {
        if (label.size() > 0) {
            for (JLabel l : label) {
                pane.remove(l);
            }
            label.clear();
        }
    }
}
