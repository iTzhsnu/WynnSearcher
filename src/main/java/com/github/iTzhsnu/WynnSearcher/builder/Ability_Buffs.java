package com.github.iTzhsnu.WynnSearcher.builder;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Ability_Buffs {
    private final List<Damage_Boost_CheckBox> box = new ArrayList<>();
    private final List<Damage_Boost_Slider> slider = new ArrayList<>();

    public Ability_Buffs(JPanel p) {
        //Sliders
        //Any Class
        slider.add(new Damage_Boost_Slider("Marked", 0, 6, 10, getY_Slider(0))); //0

        //Warrior
        slider.add(new Damage_Boost_Slider("Corrupted", 0, 100, 10, getY_Slider(1))); //1
        slider.add(new Damage_Boost_Slider("Discombobulate", 0, 100, 10, getY_Slider(2))); //2

        //Assassin
        slider.add(new Damage_Boost_Slider("Clones", 0, 6, 10, getY_Slider(1))); //3
        slider.add(new Damage_Boost_Slider("Nightcloak Knife", 0, 10, 10, getY_Slider(2))); //4

        //Archer
        slider.add(new Damage_Boost_Slider("Focus", 0, 7, 10, getY_Slider(1))); //5
        slider.add(new Damage_Boost_Slider("Patient Hunter", 0, 200, 10, getY_Slider(2))); //6
        slider.add(new Damage_Boost_Slider("Decimator", 0, 7, 10, getY_Slider(3))); //7

        //Mage
        slider.add(new Damage_Boost_Slider("Winded", 0, 30, 10, getY_Slider(1))); //8

        //Shaman
        slider.add(new Damage_Boost_Slider("Shepherd", 0, 8, 10, getY_Slider(1))); //9

        //Boxes
        //Any Class
        box.add(new Damage_Boost_CheckBox("War Scream", 140, getY_Boxes(0), 100)); //0
        box.add(new Damage_Boost_CheckBox("Ragnarokkr", 250, getY_Boxes(0), 95)); //1
        box.add(new Damage_Boost_CheckBox("Radiance", 355, getY_Boxes(0), 80)); //2
        box.add(new Damage_Boost_CheckBox("Fortitude", 440, getY_Boxes(0), 75)); //3
        box.add(new Damage_Boost_CheckBox("Vengeful Spirit", 525, getY_Boxes(0), 110)); //4
        box.add(new Damage_Boost_CheckBox("Armour Breaker", 645, getY_Boxes(0), 120)); //5
        box.add(new Damage_Boost_CheckBox("10%", 75, 305, 50)); //6
        box.add(new Damage_Boost_CheckBox("Coursing Restraints", 140, getY_Boxes(1), 140)); //7
        box.add(new Damage_Boost_CheckBox("Ivyroot Mamba", 290, getY_Boxes(1), 110)); //8
        box.add(new Damage_Boost_CheckBox("Haunting Memory (Lunatic)", 410, getY_Boxes(1), 180)); //9
        box.add(new Damage_Boost_CheckBox("Haunting Memory (Fanatic)", 600, getY_Boxes(1), 180)); //10
        box.add(new Damage_Boost_CheckBox("Chant of the Lunatic", 140, getY_Boxes(2), 140)); //11
        box.add(new Damage_Boost_CheckBox("Saviour's Sacrifice", 290, getY_Boxes(2), 135)); //12

        //Warrior
        box.add(new Damage_Boost_CheckBox("Mantle", 140, getY_Boxes(3), 65)); //13
        box.add(new Damage_Boost_CheckBox("Brink of Madness", 215, getY_Boxes(3), 125)); //14
        box.add(new Damage_Boost_CheckBox("Sacred Surge", 350, getY_Boxes(3), 105)); //15

        //Assassin
        box.add(new Damage_Boost_CheckBox("Backstab", 140, getY_Boxes(3), 80)); //16
        box.add(new Damage_Boost_CheckBox("Surprise Strike", 230, getY_Boxes(3), 115)); //17
        box.add(new Damage_Boost_CheckBox("Delirious Gas", 355, getY_Boxes(3), 100)); //18
        box.add(new Damage_Boost_CheckBox("Mirror Image", 465, getY_Boxes(3), 100)); //19
        box.add(new Damage_Boost_CheckBox("Satsujin", 575, getY_Boxes(3), 75)); //20
        box.add(new Damage_Boost_CheckBox("Flow State", 660, getY_Boxes(3), 90)); //21
        box.add(new Damage_Boost_CheckBox("Parry", 140, getY_Boxes(4), 60)); //22
        box.add(new Damage_Boost_CheckBox("Dissolution", 210, getY_Boxes(4), 90)); //23

        //Archer
        box.add(new Damage_Boost_CheckBox("Initiator", 140, getY_Boxes(3), 80)); //24

        //Shaman
        box.add(new Damage_Boost_CheckBox("Mask of the Lunatic", 140, getY_Boxes(3), 140)); //25
        box.add(new Damage_Boost_CheckBox("Mask of the Fanatic", 290, getY_Boxes(3), 140)); //26
        box.add(new Damage_Boost_CheckBox("Mask of the Coward", 440, getY_Boxes(3), 140)); //27
        box.add(new Damage_Boost_CheckBox("Chant of the Fanatic", 590, getY_Boxes(3), 140)); //28
        box.add(new Damage_Boost_CheckBox("Bullwhip", 140, getY_Boxes(4), 75)); //29
        box.add(new Damage_Boost_CheckBox("Invigorating Wave", 225, getY_Boxes(4), 130)); //30
        box.add(new Damage_Boost_CheckBox("Sacrificial Shrine", 365, getY_Boxes(4), 130)); //31
        box.add(new Damage_Boost_CheckBox("Awakened", 505, getY_Boxes(4), 85)); //32

        for (Damage_Boost_Slider dbs : slider) {
            p.add(dbs);
            p.add(dbs.label);
        }
        for (Damage_Boost_CheckBox dbc : box) {
            p.add(dbc);
        }
    }

    public static int getY_Boxes(int pos) {
        return 340 + pos * 25;
    }

    public static int getY_Slider(int pos) {
        return 290 + pos * 40;
    }

    public List<Damage_Boost_CheckBox> getBox() {
        return box;
    }

    public List<Damage_Boost_Slider> getSlider() {
        return slider;
    }

    public static class Damage_Boost_CheckBox extends JCheckBox {
        public Damage_Boost_CheckBox(String name, int x, int y, int width) {
            super(name);

            setBounds(x, y, width, 20);
        }
    }

    public static class Damage_Boost_Slider extends JSlider implements ChangeListener {
        private final JLabel label;
        private final String name;

        public Damage_Boost_Slider(String name, int min, int max, int x, int y) {
            super(min, max);
            this.name = name;
            label = new JLabel(name + ": " + min);
            setBounds(x, y, 120, 20);
            label.setBounds(x, y + 15, 120, 20);
            setValue(0);
            addChangeListener(this);
        }

        public void setSlider_Visible(boolean visible) {
            setVisible(visible);
            label.setVisible(visible);
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            label.setText(name + ": " + getValue());
        }
    }
}
