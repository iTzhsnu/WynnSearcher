package com.github.iTzhsnu.WynnSearcher.builder.skilltree;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class TreeIcon extends JLabel {

    public TreeIcon(int x, int y, JPanel p) {
        super();
        setBounds(x, y, 36, 36);
        p.add(this);
    }

    public void all() {
        setIcon(getResizedIcon("/all.png"));
    }

    public void up_right_left() {
        setIcon(getResizedIcon("/up_right_left.png"));
    }

    public void up_right_down() {
        setIcon(getResizedIcon("/up_right_down.png"));
    }

    public void right_left_down() {
        setIcon(getResizedIcon("/right_left_down.png"));
    }

    public void up_left_down() {
        setIcon(getResizedIcon("/up_left_down.png"));
    }

    public void up_left() {
        setIcon(getResizedIcon("/up_right.png"));
    }

    public void up_right() {
        setIcon(getResizedIcon("/up_right.png"));
    }

    public void right_down() {
        setIcon(getResizedIcon("/right_down.png"));
    }

    public void left_down() {
        setIcon(getResizedIcon("/left_down.png"));
    }

    public void up_down() {
        setIcon(getResizedIcon("/up_down.png"));
    }

    public void right_left() {
        setIcon(getResizedIcon("/right_left.png"));
    }

    public ImageIcon getResizedIcon(String s) {
        return new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(s))).getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH));
    }
}
