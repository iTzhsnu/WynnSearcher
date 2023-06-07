package com.github.iTzhsnu.WynnSearcher.builder;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Damage_Boosts {
    private final List<Damage_Boost_CheckBox> box = new ArrayList<>();
    private final List<Damage_Boost_Slider> slider = new ArrayList<>();

    public Damage_Boosts(JPanel p) {
        //Sliders
        //Any Class
        slider.add(new Damage_Boost_Slider("Marked", 0, 6, 10, 235)); //0

        //Warrior
        slider.add(new Damage_Boost_Slider("Corrupted", 0, 100, 10, 275)); //1
        slider.add(new Damage_Boost_Slider("Discombobulate", 0, 100, 10, 315)); //2

        //Assassin
        slider.add(new Damage_Boost_Slider("Clones", 0, 6, 10, 275)); //3
        slider.add(new Damage_Boost_Slider("Nightcloak Knife", 0, 10, 10, 315)); //4

        //Archer
        slider.add(new Damage_Boost_Slider("Focus", 0, 7, 10, 275)); //5
        slider.add(new Damage_Boost_Slider("Patient Hunter", 0, 200, 10, 315)); //6
        slider.add(new Damage_Boost_Slider("Decimator", 0, 7, 10, 355)); //7

        //Mage
        slider.add(new Damage_Boost_Slider("Winded", 0, 30, 10, 275)); //8

        //Shaman
        slider.add(new Damage_Boost_Slider("Shepherd", 0, 8, 10, 275)); //9

        //Boxes
        //Any Class
        box.add(new Damage_Boost_CheckBox("War Scream", 140, 305, 100)); //0
        box.add(new Damage_Boost_CheckBox("Ragnarokkr", 250, 305, 95)); //1
        box.add(new Damage_Boost_CheckBox("Radiance", 355, 305, 80)); //2
        box.add(new Damage_Boost_CheckBox("Fortitude", 440, 305, 75)); //3
        box.add(new Damage_Boost_CheckBox("Vengeful Spirit", 525, 305, 110)); //4
        box.add(new Damage_Boost_CheckBox("Armour Breaker", 645, 305, 120)); //5
        box.add(new Damage_Boost_CheckBox("10%", 75, 250, 50)); //6

        //Warrior
        box.add(new Damage_Boost_CheckBox("Mantle", 140, 330, 65)); //7
        box.add(new Damage_Boost_CheckBox("Brink of Madness", 215, 330, 125)); //8

        //Assassin
        box.add(new Damage_Boost_CheckBox("Backstab", 140, 330, 80)); //9
        box.add(new Damage_Boost_CheckBox("Surprise Strike", 230, 330, 115)); //10
        box.add(new Damage_Boost_CheckBox("Delirious Gas", 355, 330, 100)); //11
        box.add(new Damage_Boost_CheckBox("Mirror Image", 465, 330, 100)); //12
        box.add(new Damage_Boost_CheckBox("Satsujin", 575, 330, 75)); //13
        box.add(new Damage_Boost_CheckBox("Flow State", 660, 330, 90)); //14
        box.add(new Damage_Boost_CheckBox("Parry", 140, 355, 60)); //15
        box.add(new Damage_Boost_CheckBox("Dissolution", 210, 355, 90)); //16

        //Archer
        box.add(new Damage_Boost_CheckBox("Initiator", 140, 330, 80)); //17

        //Shaman
        box.add(new Damage_Boost_CheckBox("Mask of the Lunatic", 140, 330, 140)); //18
        box.add(new Damage_Boost_CheckBox("Mask of the Fanatic", 290, 330, 140)); //19
        box.add(new Damage_Boost_CheckBox("Mask of the Coward", 440, 330, 140)); //20
        box.add(new Damage_Boost_CheckBox("Chant of the Fanatic", 590, 330, 140)); //21
        box.add(new Damage_Boost_CheckBox("Bullwhip", 140, 355, 75)); //22
        box.add(new Damage_Boost_CheckBox("Invigorating Wave", 225, 355, 130)); //23
        box.add(new Damage_Boost_CheckBox("Sacrificial Shrine", 365, 355, 130)); //24
        box.add(new Damage_Boost_CheckBox("Mask of the Awakened", 505, 355, 160)); //25

        for (Damage_Boost_Slider dbs : slider) {
            p.add(dbs);
            p.add(dbs.label);
        }
        for (Damage_Boost_CheckBox dbc : box) {
            p.add(dbc);
        }
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

        public void setDamage_Boost_Slider_Visible(boolean visible) {
            setVisible(visible);
            label.setVisible(visible);
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            label.setText(name + ": " + getValue());
        }
    }
}
