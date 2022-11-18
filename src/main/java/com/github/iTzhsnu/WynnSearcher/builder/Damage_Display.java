package com.github.iTzhsnu.WynnSearcher.builder;

import javax.swing.*;
import java.awt.*;

public class Damage_Display {
    private final JPanel pane = new JPanel();
    private final JScrollPane scrollPane;

    public Damage_Display(JPanel p) {

        pane.setPreferredSize(new Dimension(250, 1000));
        pane.setLayout(null);

        scrollPane = new JScrollPane(pane);
        scrollPane.setBounds(740, 300, 268, 400);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);

        p.add(scrollPane);
    }
}
