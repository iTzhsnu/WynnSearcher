package com.github.iTzhsnu.WynnSearcher;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class IDBoxAdapter extends KeyAdapter {
    private final JComboBox<String> box;
    public static final String[] idList = new String[] {"Combat Level", "Strength Req", "Dexterity Req", "Inteligence Req", "Defense Req", "Agility Req"
            , "Health", "Earth Defense", "Thunder Defense", "Water Defense", "Fire Defense", "Air Defense"
            , "Health Bonus", "Earth Defense %", "Thunder Defense %", "Water Defense %", "Fire Defense %", "Air Defense %"
            , "Strength", "Dexterity", "Inteligence", "Defense", "Agility"};

    public IDBoxAdapter(JComboBox<String> box) {
        super();
        this.box = box;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        JTextField text = (JTextField) e.getComponent();

        setList(box, text.getText());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
    }

    private static void setList(JComboBox<String> box, String text) {
        box.removeAllItems();

        for (String s : idList) {
            if (text.isEmpty() || s.toLowerCase().contains(text.toLowerCase())) {
                box.addItem(s);
            }
        }

        ((JTextField) box.getEditor().getEditorComponent()).setText(text);
    }
}
