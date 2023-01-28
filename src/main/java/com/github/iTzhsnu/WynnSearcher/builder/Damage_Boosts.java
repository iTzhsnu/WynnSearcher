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
        slider.add(new Damage_Boost_Slider("Corrupted", 0, 100, 10, 235)); //0
        slider.add(new Damage_Boost_Slider("Discombobulate", 0, 80, 10, 275)); //1

        slider.add(new Damage_Boost_Slider("Marked", 0, 6, 10, 235)); //2

        slider.add(new Damage_Boost_Slider("Focus", 0, 7, 10, 235)); //3
        slider.add(new Damage_Boost_Slider("Patient Hunter", 0, 200, 10, 275)); //4
        slider.add(new Damage_Boost_Slider("Decimator", 0, 7, 10, 315)); //5

        slider.add(new Damage_Boost_Slider("Winded", 0, 30, 10, 235)); //6

        slider.add(new Damage_Boost_Slider("Shepherd", 0, 8, 10, 235)); //7

        //Boxes
        box.add(new Damage_Boost_CheckBox("War Scream", 140, 305, 100)); //0
        box.add(new Damage_Boost_CheckBox("Ragnarokkr", 250, 305, 95)); //1
        box.add(new Damage_Boost_CheckBox("Radiance", 355, 305, 80)); //2
        box.add(new Damage_Boost_CheckBox("Fortitude", 440, 305, 75)); //3
        box.add(new Damage_Boost_CheckBox("Vengeful Spirit", 525, 305, 110)); //4

        box.add(new Damage_Boost_CheckBox("Mantle", 140, 330, 65)); //5
        box.add(new Damage_Boost_CheckBox("Armour Breaker", 215, 330, 120)); //6
        box.add(new Damage_Boost_CheckBox("Brink of Madness", 345, 330, 125)); //7

        box.add(new Damage_Boost_CheckBox("Backstab", 140, 330, 80)); //8
        box.add(new Damage_Boost_CheckBox("Suprise Strike", 230, 330, 110)); //9
        box.add(new Damage_Boost_CheckBox("Delirious Gas", 350, 330, 100)); //10
        box.add(new Damage_Boost_CheckBox("Mirror Image", 460, 330, 100)); //11
        box.add(new Damage_Boost_CheckBox("Satsujin", 570, 330, 80)); //12

        box.add(new Damage_Boost_CheckBox("Mask of the Lunatic", 40, 330, 140)); //13
        box.add(new Damage_Boost_CheckBox("Mask of the Fanatic", 190, 330, 140)); //14
        box.add(new Damage_Boost_CheckBox("Mask of the Coward", 340, 330, 140)); //15
        box.add(new Damage_Boost_CheckBox("Chant of the Fanatic", 490, 330, 140)); //16
        box.add(new Damage_Boost_CheckBox("Bullwhip", 640, 330, 75)); //17
        box.add(new Damage_Boost_CheckBox("Invigorating Wave", 725, 330, 130)); //18
        box.add(new Damage_Boost_CheckBox("Sacrificial Shrine", 865, 330, 130)); //19
        box.add(new Damage_Boost_CheckBox("Mask of the Awakened", 645, 305, 160)); //20

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
