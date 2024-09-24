package com.github.iTzhsnu.WynnSearcher.builder;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Powder_Effects implements ActionListener {
    private final JComboBox<String> powderType = new JComboBox<>();
    private final PowderPanel earth;
    private final PowderPanel thunder;
    private final PowderPanel water;
    private final PowderPanel fire;
    private final PowderPanel air;

    public Powder_Effects(JPanel p) {
        powderType.addItem("Earth Powder");
        powderType.addItem("Thunder Powder");
        powderType.addItem("Water Powder");
        powderType.addItem("Fire Powder");
        powderType.addItem("Air Powder");
        powderType.setBounds(600, 665, 120, 20);
        powderType.addActionListener(this);

        earth = new PowderPanel(p, 300);
        thunder = new PowderPanel(p, 150);
        water = new PowderPanel(p, 100);
        fire = new PowderPanel(p, 100);
        air = new PowderPanel(p, 150);

        thunder.setVisible(false);
        water.setVisible(false);
        fire.setVisible(false);
        air.setVisible(false);

        p.add(powderType);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean earthB = false;
        boolean thunderB = false;
        boolean waterB = false;
        boolean fireB = false;
        boolean airB = false;
        switch (powderType.getSelectedIndex()) {
            case 0:
                earthB = true;
                break;
            case 1:
                thunderB = true;
                break;
            case 2:
                waterB = true;
                break;
            case 3:
                fireB = true;
                break;
            case 4:
                airB = true;
                break;
        }
        earth.setVisible(earthB);
        thunder.setVisible(thunderB);
        water.setVisible(waterB);
        fire.setVisible(fireB);
        air.setVisible(airB);
    }

    public PowderPanel getPowderPanel(String powder) {
        switch (powder) {
            case "Thunder":
                return thunder;
            case "Water":
                return water;
            case "Fire":
                return fire;
            case "Air":
                return air;
            default:
                return earth;
        }
    }

    public static class PowderPanel implements ChangeListener {
        private final JComboBox<String> specialLv = new JComboBox<>();
        private final JSlider boost;
        private final JLabel text = new JLabel("Damage Boost: +0%");

        public PowderPanel(JPanel p, int max) {
            boost = new JSlider(0, max);
            boost.setBounds(840, 675, 200, 20);
            boost.setValue(0);
            boost.addChangeListener(this);

            specialLv.addItem("Special None");
            specialLv.addItem("Special Lv.1");
            specialLv.addItem("Special Lv.2");
            specialLv.addItem("Special Lv.3");
            specialLv.addItem("Special Lv.4");
            specialLv.addItem("Special Lv.5");
            specialLv.setBounds(730, 665, 100, 20);

            text.setBounds(870, 655, 140, 20);

            p.add(specialLv);
            p.add(text);
            p.add(boost);
        }

        public void setVisible(boolean visible) {
            specialLv.setVisible(visible);
            boost.setVisible(visible);
            text.setVisible(visible);
        }

        public int getValue() {
            return boost.getValue();
        }

        public int getSpecial() {
            return specialLv.getSelectedIndex();
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            text.setText("Damage Boost: +" + boost.getValue() + "%");
        }
    }
}
