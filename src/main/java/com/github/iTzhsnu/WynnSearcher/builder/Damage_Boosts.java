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
        slider.add(new Damage_Boost_Slider("Corrupted", 0, 100, 10, 180));
        slider.add(new Damage_Boost_Slider("Discombobulate", 0, 80, 10, 220));

        slider.add(new Damage_Boost_Slider("Marked", 0, 6, 10, 180));

        slider.add(new Damage_Boost_Slider("Focus", 0, 7, 10, 180));
        slider.add(new Damage_Boost_Slider("Patient Hunter", 0, 200, 10, 220));
        slider.add(new Damage_Boost_Slider("Decimator", 0, 7, 655, 220));

        slider.add(new Damage_Boost_Slider("Winded", 0, 30, 10, 180));

        slider.add(new Damage_Boost_Slider("Shepherd", 0, 8, 10, 180));

        //Boxes
        box.add(new Damage_Boost_CheckBox("War Scream", 140, 220, 100));
        box.add(new Damage_Boost_CheckBox("Ragnarokkr", 250, 220, 95));
        box.add(new Damage_Boost_CheckBox("Radiance", 355, 220, 80));
        box.add(new Damage_Boost_CheckBox("Fortitude", 440, 220, 75));
        box.add(new Damage_Boost_CheckBox("Vengeful Spirit", 525, 220, 110));

        box.add(new Damage_Boost_CheckBox("Mantle", 140, 245, 65));
        box.add(new Damage_Boost_CheckBox("Armour Breaker", 215, 245, 120));
        box.add(new Damage_Boost_CheckBox("Brink of Madness", 345, 245, 125));

        box.add(new Damage_Boost_CheckBox("Backstab", 140, 245, 80));
        box.add(new Damage_Boost_CheckBox("Suprise Strike", 230, 245, 110));
        box.add(new Damage_Boost_CheckBox("Delirious Gas", 350, 245, 100));
        box.add(new Damage_Boost_CheckBox("Mirror Image", 460, 245, 100));
        box.add(new Damage_Boost_CheckBox("Satsujin", 570, 245, 80));

        box.add(new Damage_Boost_CheckBox("Mask of the Lunatic", 40, 245, 140));
        box.add(new Damage_Boost_CheckBox("Mask of the Fanatic", 190, 245, 140));
        box.add(new Damage_Boost_CheckBox("Mask of the Coward", 340, 245, 140));
        box.add(new Damage_Boost_CheckBox("Chant of the Fanatic", 490, 245, 140));
        box.add(new Damage_Boost_CheckBox("Bullwhip", 640, 245, 75));
        box.add(new Damage_Boost_CheckBox("Invigorating Wave", 725, 245, 130));
        box.add(new Damage_Boost_CheckBox("Sacrificial Shrine", 865, 245, 130));
        box.add(new Damage_Boost_CheckBox("Mask of the Awakened", 645, 220, 160));

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
