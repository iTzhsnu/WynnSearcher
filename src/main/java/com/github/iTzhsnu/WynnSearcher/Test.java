package com.github.iTzhsnu.WynnSearcher;

import com.google.gson.JsonObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Test extends JFrame {

    private final List<JsonObject> json = new ArrayList<>();

    public static void main(String[] args) {
        new Test();
    }

    public Test() {
        GetAPI.setIngredientData(json);

        Container pane = getContentPane();
        setBounds(100, 100, 250, 500);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("-32500 Melee Damage -17500");
        label.setBounds(5, 30, 200, 20);

        JLabel label1 = new JLabel("Effectiveness (Not Touching) -200%");
        label1.setBounds(5, 60, 200, 20);

        JLabel name = new JLabel(json.get(0).get("name").getAsString());
        name.setBounds(xTest(json.get(0).get("name").getAsString().length()), 5, 200, 20);

        pane.add(label);
        pane.add(label1);
        pane.add(name);

        JPanel panel = new JPanel();
        panel.setBackground(Color.ORANGE);
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(200, 1000));

        JLabel l = new JLabel("HAGEKASU!");
        l.setBounds(20, 800, 100, 20);

        panel.add(l);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(10, 100, 200, 300);

        pane.add(scrollPane);
    }

    private int xTest(int length) {
        return (250 - (length * 8)) / 2;
    }
    //if (string.indexOf("-") != -1) String[] s = string.split("-");
    //use for String[] => String
}
