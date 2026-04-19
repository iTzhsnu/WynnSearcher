package com.github.iTzhsnu.WynnSearcher.ui;

import com.github.iTzhsnu.WynnSearcher.data.ItemBase;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class ItemBoxAdapter<T extends ItemBase> extends KeyAdapter {
    private final JComboBox<String> box;
    private final List<T> item;

    public ItemBoxAdapter(JComboBox<String> box, List<T> item) {
        super();
        this.box = box;
        this.item = item;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        JTextField text = (JTextField) e.getComponent();

        setList(box, text.getText(), text.getCaretPosition(), text.getSelectionStart(), text.getSelectionEnd());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
    }

    private void setList(JComboBox<String> box, String text, int caretPos, int selectionStart, int selectionEnd) {
        box.removeAllItems();

        for (ItemBase i : item) {
            if (text.isEmpty() || i.getName().toLowerCase().contains(text.toLowerCase())) box.addItem(text.toLowerCase());
        }

        ((JTextField) box.getEditor().getEditorComponent()).setText(text);
        ((JTextField) box.getEditor().getEditorComponent()).setCaretPosition(caretPos);
        if (selectionStart < selectionEnd) {
            ((JTextField) box.getEditor().getEditorComponent()).setSelectionStart(selectionStart);
            ((JTextField) box.getEditor().getEditorComponent()).setSelectionEnd(selectionEnd);
        }
    }
}
