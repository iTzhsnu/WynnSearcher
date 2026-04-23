package com.github.iTzhsnu.WynnSearcher.builder;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.List;

public class AbilityBuffs {
    private final List<DamageBoostCheckBox> box = new ArrayList<>();
    private final List<DamageBoostSlider> slider = new ArrayList<>();

    public AbilityBuffs(JPanel p) {
        //Sliders
        //Any Class
        slider.add(new DamageBoostSlider("Marked", 0, 6, 10, getYSlider(0))); //0

        //Warrior
        slider.add(new DamageBoostSlider("Corrupted", 0, 100, 10, getYSlider(1))); //1
        slider.add(new DamageBoostSlider("Discombobulate", 0, 100, 10, getYSlider(2))); //2

        //Assassin
        slider.add(new DamageBoostSlider("Clones", 0, 6, 10, getYSlider(1))); //3
        slider.add(new DamageBoostSlider("Nightcloak Knife", 0, 10, 10, getYSlider(2))); //4

        //Archer
        slider.add(new DamageBoostSlider("Focus", 0, 7, 10, getYSlider(1))); //5
        slider.add(new DamageBoostSlider("Patient Hunter", 0, 200, 10, getYSlider(2))); //6
        slider.add(new DamageBoostSlider("Decimator", 0, 7, 10, getYSlider(3))); //7

        //Mage
        slider.add(new DamageBoostSlider("Winded", 0, 30, 10, getYSlider(1))); //8

        //Shaman
        slider.add(new DamageBoostSlider("Shepherd", 0, 8, 10, getYSlider(1))); //9

        //Boxes
        //Any Class
        box.add(new DamageBoostCheckBox("War Scream", 140, getYBoxes(0), 100)); //0
        box.add(new DamageBoostCheckBox("Ragnarokkr", 250, getYBoxes(0), 95)); //1
        box.add(new DamageBoostCheckBox("Radiance", 355, getYBoxes(0), 80)); //2
        box.add(new DamageBoostCheckBox("Fortitude", 440, getYBoxes(0), 75)); //3
        box.add(new DamageBoostCheckBox("Vengeful Spirit", 525, getYBoxes(0), 110)); //4
        box.add(new DamageBoostCheckBox("Armour Breaker", 645, getYBoxes(0), 120)); //5
        box.add(new DamageBoostCheckBox("10%", 75, 415, 50)); //6
        box.add(new DamageBoostCheckBox("Coursing Restraints", 140, getYBoxes(1), 140)); //7
        box.add(new DamageBoostCheckBox("Ivyroot Mamba", 290, getYBoxes(1), 110)); //8
        box.add(new DamageBoostCheckBox("Haunting Memory (Lunatic)", 410, getYBoxes(1), 180)); //9
        box.add(new DamageBoostCheckBox("Haunting Memory (Fanatic)", 600, getYBoxes(1), 180)); //10
        box.add(new DamageBoostCheckBox("Chant of the Lunatic", 140, getYBoxes(2), 140)); //11
        box.add(new DamageBoostCheckBox("Saviour's Sacrifice", 290, getYBoxes(2), 135)); //12

        //Warrior
        box.add(new DamageBoostCheckBox("Mantle", 140, getYBoxes(3), 65)); //13
        box.add(new DamageBoostCheckBox("Brink of Madness", 215, getYBoxes(3), 125)); //14
        box.add(new DamageBoostCheckBox("Sacred Surge", 350, getYBoxes(3), 105)); //15

        //Assassin
        box.add(new DamageBoostCheckBox("Backstab", 140, getYBoxes(3), 80)); //16
        box.add(new DamageBoostCheckBox("Surprise Strike", 230, getYBoxes(3), 115)); //17
        box.add(new DamageBoostCheckBox("Delirious Gas", 355, getYBoxes(3), 100)); //18
        box.add(new DamageBoostCheckBox("Mirror Image", 465, getYBoxes(3), 100)); //19
        box.add(new DamageBoostCheckBox("Satsujin", 575, getYBoxes(3), 75)); //20
        box.add(new DamageBoostCheckBox("Flow State", 660, getYBoxes(3), 90)); //21
        box.add(new DamageBoostCheckBox("Parry", 140, getYBoxes(4), 60)); //22
        box.add(new DamageBoostCheckBox("Dissolution", 210, getYBoxes(4), 90)); //23

        //Archer
        box.add(new DamageBoostCheckBox("Initiator", 140, getYBoxes(3), 80)); //24

        //Shaman
        box.add(new DamageBoostCheckBox("Mask of the Lunatic", 140, getYBoxes(3), 140)); //25
        box.add(new DamageBoostCheckBox("Mask of the Fanatic", 290, getYBoxes(3), 140)); //26
        box.add(new DamageBoostCheckBox("Mask of the Coward", 440, getYBoxes(3), 140)); //27
        box.add(new DamageBoostCheckBox("Chant of the Fanatic", 590, getYBoxes(3), 140)); //28
        box.add(new DamageBoostCheckBox("Bullwhip", 140, getYBoxes(4), 75)); //29
        box.add(new DamageBoostCheckBox("Invigorating Wave", 225, getYBoxes(4), 130)); //30
        box.add(new DamageBoostCheckBox("Sacrificial Shrine", 365, getYBoxes(4), 130)); //31
        box.add(new DamageBoostCheckBox("Awakened", 505, getYBoxes(4), 85)); //32

        for (DamageBoostSlider dbs : slider) {
            p.add(dbs);
            p.add(dbs.label);
        }
        for (DamageBoostCheckBox dbc : box) {
            p.add(dbc);
        }
    }

    public static int getYBoxes(int pos) {
        return 450 + pos * 25;
    }

    public static int getYSlider(int pos) {
        return 400 + pos * 40;
    }

    public List<DamageBoostCheckBox> getBox() {
        return box;
    }

    public List<DamageBoostSlider> getSlider() {
        return slider;
    }

    public static class DamageBoostCheckBox extends JCheckBox {
        public DamageBoostCheckBox(String name, int x, int y, int width) {
            super(name);

            setBounds(x, y, width, 20);
        }
    }

    public static class DamageBoostSlider extends JSlider implements ChangeListener {
        private final JLabel label;
        private final String name;

        public DamageBoostSlider(String name, int min, int max, int x, int y) {
            super(min, max);
            this.name = name;
            label = new JLabel(name + ": " + min);
            setBounds(x, y, 120, 20);
            label.setBounds(x, y + 15, 120, 20);
            setValue(0);
            addChangeListener(this);
        }

        public void setSliderVisible(boolean visible) {
            setVisible(visible);
            label.setVisible(visible);
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            label.setText(name + ": " + getValue());
        }
    }
}
