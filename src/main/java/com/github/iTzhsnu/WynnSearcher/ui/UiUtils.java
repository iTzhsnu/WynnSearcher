package com.github.iTzhsnu.WynnSearcher.ui;

import javax.swing.*;

public class UiUtils {
    public static JTextField createNoBeepTextField(String s) {
        JTextField noBeepText = new JTextField(s);
        IdBoxAdapter.removeBeepSounds(noBeepText.getActionMap());
        return noBeepText;
    }

    public static JTextField createNoBeepTextField() {
        JTextField noBeepText = new JTextField();
        IdBoxAdapter.removeBeepSounds(noBeepText.getActionMap());
        return noBeepText;
    }

    public static JComboBox<String> createNoBeepComboBox() {
        JComboBox<String> noBeepBox = new JComboBox<>();
        IdBoxAdapter.removeBeepSounds(((JTextField) noBeepBox.getEditor().getEditorComponent()).getActionMap());
        return noBeepBox;
    }
}
