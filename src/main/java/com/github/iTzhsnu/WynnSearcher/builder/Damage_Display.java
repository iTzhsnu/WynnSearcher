package com.github.iTzhsnu.WynnSearcher.builder;

import com.github.iTzhsnu.WynnSearcher.Identifications;
import com.github.iTzhsnu.WynnSearcher.builder.skilltree.TreeBase;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Damage_Display {
    private final JPanel pane = new JPanel();

    public Damage_Display(JPanel p) {

        pane.setPreferredSize(new Dimension(300, 1000));
        pane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane(pane);
        scrollPane.setBounds(708, 330, 318, 400);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);

        p.add(scrollPane);
    }

    public void setDamage_Display(JsonObject weapon, SkillPoint sp, Damage_Boosts damage_Boosts, TreeBase tree, int[] id_Numbers, List<JTextField> powders) {
        pane.removeAll();
        if (weapon != null) {
            float neutral_min = 0;
            float earth_min = 0;
            float thunder_min = 0;
            float water_min = 0;
            float fire_min = 0;
            float air_min = 0;

            float neutral_max = 0;
            float earth_max = 0;
            float thunder_max = 0;
            float water_max = 0;
            float fire_max = 0;
            float air_max = 0;

            List<Damage_Template> list = new ArrayList<>();

            //Neutral Damage
            if (weapon.get(Identifications.NEUTRAL_DAMAGE.getItemName()) != null && !weapon.get(Identifications.NEUTRAL_DAMAGE.getItemName()).getAsString().equals("0-0") && weapon.get(Identifications.NEUTRAL_DAMAGE.getItemName()).getAsString().contains("-")) {
                String[] ss = weapon.get(Identifications.NEUTRAL_DAMAGE.getItemName()).getAsString().split("-");
                neutral_min = Integer.parseInt(ss[0]);
                neutral_max = Integer.parseInt(ss[ss.length - 1]);
            }

            //Earth Damage
            if (weapon.get(Identifications.EARTH_DAMAGE.getItemName()) != null && !weapon.get(Identifications.EARTH_DAMAGE.getItemName()).getAsString().equals("0-0") && weapon.get(Identifications.EARTH_DAMAGE.getItemName()).getAsString().contains("-")) {
                String[] ss = weapon.get(Identifications.EARTH_DAMAGE.getItemName()).getAsString().split("-");
                earth_min = Integer.parseInt(ss[0]);
                earth_max = Integer.parseInt(ss[ss.length - 1]);
            }

            //Thunder Damage
            if (weapon.get(Identifications.THUNDER_DAMAGE.getItemName()) != null && !weapon.get(Identifications.THUNDER_DAMAGE.getItemName()).getAsString().equals("0-0") && weapon.get(Identifications.THUNDER_DAMAGE.getItemName()).getAsString().contains("-")) {
                String[] ss = weapon.get(Identifications.THUNDER_DAMAGE.getItemName()).getAsString().split("-");
                thunder_min = Integer.parseInt(ss[0]);
                thunder_max = Integer.parseInt(ss[ss.length - 1]);
            }

            //Water Damage
            if (weapon.get(Identifications.WATER_DAMAGE.getItemName()) != null && !weapon.get(Identifications.WATER_DAMAGE.getItemName()).getAsString().equals("0-0") && weapon.get(Identifications.WATER_DAMAGE.getItemName()).getAsString().contains("-")) {
                String[] ss = weapon.get(Identifications.WATER_DAMAGE.getItemName()).getAsString().split("-");
                water_min = Integer.parseInt(ss[0]);
                water_max = Integer.parseInt(ss[ss.length - 1]);
            }

            //Fire Damage
            if (weapon.get(Identifications.FIRE_DAMAGE.getItemName()) != null && !weapon.get(Identifications.FIRE_DAMAGE.getItemName()).getAsString().equals("0-0") && weapon.get(Identifications.FIRE_DAMAGE.getItemName()).getAsString().contains("-")) {
                String[] ss = weapon.get(Identifications.FIRE_DAMAGE.getItemName()).getAsString().split("-");
                fire_min = Integer.parseInt(ss[0]);
                fire_max = Integer.parseInt(ss[ss.length - 1]);
            }

            //Air Damage
            if (weapon.get(Identifications.AIR_DAMAGE.getItemName()) != null && !weapon.get(Identifications.AIR_DAMAGE.getItemName()).getAsString().equals("0-0") && weapon.get(Identifications.AIR_DAMAGE.getItemName()).getAsString().contains("-")) {
                String[] ss = weapon.get(Identifications.AIR_DAMAGE.getItemName()).getAsString().split("-");
                air_min = Integer.parseInt(ss[0]);
                air_max = Integer.parseInt(ss[ss.length - 1]);
            }


            //Powder
            try {
                JsonObject powderJ = JsonParser.parseReader(new FileReader(new File(Objects.requireNonNull(getClass().getResource("/other/powders.json")).toURI()))).getAsJsonObject();
                if (weapon.get("sockets") != null && weapon.get("sockets").getAsInt() > 0 && powders.get(4).getText().length() > 1) {
                    String[] turn = new String[] {"", "", "", "", ""};
                    int[] convert = new int[] {0, 0, 0, 0, 0};
                    for (int i = 0; (int) Math.floor(powders.get(0).getText().length() / 2F) * 2 > i; i += 2) {
                        if (weapon.get("sockets").getAsInt() >= i / 2) {
                            String name = String.valueOf(powders.get(0).getText().charAt(i)) + powders.get(0).getText().charAt(i + 1);
                            if (powderJ.get(name) != null) {
                                JsonObject j = powderJ.get(name).getAsJsonObject();
                                String[] ss = j.get("damage").getAsString().split("-");
                                switch (j.get("type").getAsString()) {
                                    case "earth": { //Earth Powder Damage
                                        earth_min += Integer.parseInt(ss[0]);
                                        earth_max += Integer.parseInt(ss[1]);
                                        break;
                                    }
                                    case "thunder": { //Thunder Powder Damage
                                        thunder_min += Integer.parseInt(ss[0]);
                                        thunder_max += Integer.parseInt(ss[1]);
                                        break;
                                    }
                                    case "water": { //Water Powder Damage
                                        water_min += Integer.parseInt(ss[0]);
                                        water_max += Integer.parseInt(ss[1]);
                                        break;
                                    }
                                    case "fire": { //Fire Powder Damage
                                        fire_min += Integer.parseInt(ss[0]);
                                        fire_max += Integer.parseInt(ss[1]);
                                        break;
                                    }
                                    case "air": { //Air Powder Damage
                                        air_min += Integer.parseInt(ss[0]);
                                        air_max += Integer.parseInt(ss[1]);
                                        break;
                                    }
                                }
                                for (int n = 0; 5 > n; ++n) { //Powder Convert
                                    if (turn[n].isEmpty()) {
                                        turn[n] = j.get("type").getAsString();
                                        convert[n] += j.get("convert").getAsInt();
                                        break;
                                    } else if (turn[n].equals(j.get("type").getAsString())) {
                                        convert[n] += j.get("convert").getAsInt();
                                        break;
                                    }
                                }
                            }
                        } else {
                            break;
                        }
                    }

                    for (int i = 0; 5 > i; ++i) { //Powder Convert (Math.round)
                        if (turn[i].isEmpty()) {
                            break;
                        } else if (neutral_min > 0 || neutral_max > 0) {
                            if (convert[i] > 100) { //Convert 101%+ => 100%
                                convert[i] = 100;
                            }

                            int convertCalcMin = Math.round(neutral_min * (convert[i] / 100F));
                            int convertCalcMax = Math.round(neutral_max * (convert[i] / 100F));

                            //Add Elemental
                            switch (turn[i]) {
                                case "earth": {
                                    earth_min += convertCalcMin; //Round (Neutral Damage Min * (Convert % / 100))
                                    earth_max += convertCalcMax; //Round (Neutral Damage Max * (Convert % / 100))
                                    break;
                                }
                                case "thunder": {
                                    thunder_min += convertCalcMin;
                                    thunder_max += convertCalcMax;
                                    break;
                                }
                                case "water": {
                                    water_min += convertCalcMin;
                                    water_max += convertCalcMax;
                                    break;
                                }
                                case "fire": {
                                    fire_min += convertCalcMin;
                                    fire_max += convertCalcMax;
                                    break;
                                }
                                case "air": {
                                    air_min += convertCalcMin;
                                    air_max += convertCalcMax;
                                    break;
                                }
                            }

                            //Remove Neutral
                            neutral_min -= Math.round(neutral_min * ((100 - convert[i]) / 100F)); //Round (Neutral Damage Min * ((100% - Convert %) / 100)
                            neutral_max -= Math.round(neutral_max * ((100 - convert[i]) / 100F)); //Round (Neutral Damage Max * ((100% - Convert %) / 100)
                        }
                    }
                }
            } catch (FileNotFoundException | URISyntaxException e) {
                e.printStackTrace();
            }

            float boosts = 1F;


            //Display Damages
            switch (tree.getClasses()) {
                case "Warrior": {

                    list.add(new Damage_Template("Melee", 0, pane, null));
                    float melee_boosts = boosts;
                    list.get(0).addDamage("", 100, 200, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

                    break;
                }
                case "Assassin": {
                    //TODO Assassin Ability Tree
                }
                case "Archer": {
                    //TODO Archer Ability Tree
                }
                case "Mage": {
                    //TODO Mage Ability Tree
                }
                case "Shaman": {
                    //TODO Shaman Ability Tree
                }
            }
        }
        SwingUtilities.updateComponentTreeUI(pane);
    }

    private static class Damage_Template {
        private final JPanel p = new JPanel();
        private int y_size = 35;
        private int y = 10;

        private Damage_Template(String name, int mana_Cost, JPanel pane, Damage_Template previous) {
            if (previous != null) {
                p.setBounds(10, previous.y + previous.y_size + 10, 280, 20);
                y = previous.y + previous.y_size + 10;
            } else {
                p.setBounds(10, 10, 280, 20);
            }
            p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
            JLabel nameL = new JLabel();
            if (mana_Cost > 0) {
                nameL.setText(name + " (" + mana_Cost + ")");
            } else {
                nameL.setText(name);
            }
            nameL.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
            nameL.setAlignmentX(Component.CENTER_ALIGNMENT);
            p.add(nameL);
            p.setBackground(new Color(200, 200, 200));

            pane.add(p);
        }

        private void addDamage(String name, float neutral_min, float earth_min, float thunder_min, float water_min, float fire_min, float air_min
                , float neutral_max, float earth_max, float thunder_max, float water_max, float fire_max, float air_max) {
            float damage = neutral_min + earth_min + thunder_min + water_min + fire_min + air_min + neutral_max + earth_max + thunder_max + water_max + fire_max + air_max;

            JLabel nameL = new JLabel(name);
            JLabel avg = new JLabel("Average: " + (damage / 2F));
            JLabel neutral = new JLabel(neutral_min + " Neutral " + neutral_max);
            JLabel earth = new JLabel(earth_min + " Earth " + earth_max);
            JLabel thunder = new JLabel(thunder_min + " Thunder " + thunder_max);
            JLabel water = new JLabel(water_min + " Water " + water_max);
            JLabel fire = new JLabel(fire_min + " Fire " + fire_max);
            JLabel air = new JLabel(air_min + " Air " + air_max);

            JLabel critical_avg = new JLabel("Crit Average: " + damage);
            JLabel critical_neutral = new JLabel(neutral_min * 2F + " Neutral " + neutral_max * 2F);
            JLabel critical_earth = new JLabel(earth_min * 2F + " Earth " + earth_max * 2F);
            JLabel critical_thunder = new JLabel(thunder_min * 2F + " Thunder " + thunder_max * 2F);
            JLabel critical_water = new JLabel(water_min * 2F + " Water " + water_max * 2F);
            JLabel critical_fire = new JLabel(fire_min * 2F + " Fire " + fire_max * 2F);
            JLabel critical_air = new JLabel(air_min * 2F + " Air " + air_max * 2F);

            nameL.setFont(new Font(Font.DIALOG, Font.PLAIN, 15));

            nameL.setAlignmentX(Component.CENTER_ALIGNMENT);
            avg.setAlignmentX(Component.CENTER_ALIGNMENT);
            neutral.setAlignmentX(Component.CENTER_ALIGNMENT);
            earth.setAlignmentX(Component.CENTER_ALIGNMENT);
            thunder.setAlignmentX(Component.CENTER_ALIGNMENT);
            water.setAlignmentX(Component.CENTER_ALIGNMENT);
            fire.setAlignmentX(Component.CENTER_ALIGNMENT);
            air.setAlignmentX(Component.CENTER_ALIGNMENT);
            critical_avg.setAlignmentX(Component.CENTER_ALIGNMENT);
            critical_neutral.setAlignmentX(Component.CENTER_ALIGNMENT);
            critical_earth.setAlignmentX(Component.CENTER_ALIGNMENT);
            critical_thunder.setAlignmentX(Component.CENTER_ALIGNMENT);
            critical_water.setAlignmentX(Component.CENTER_ALIGNMENT);
            critical_fire.setAlignmentX(Component.CENTER_ALIGNMENT);
            critical_air.setAlignmentX(Component.CENTER_ALIGNMENT);

            p.add(new JLabel(" ")); // +20

            if (!name.isEmpty()) {
                p.add(nameL);
                y_size += 20;
            }
            p.add(avg); // +16
            if (neutral_min != 0 || neutral_max != 0) {
                p.add(neutral);
                y_size += 16;
            }
            if (earth_min != 0 || earth_max != 0) {
                p.add(earth);
                y_size += 16;
            }
            if (thunder_min != 0 || thunder_max != 0) {
                p.add(thunder);
                y_size += 16;
            }
            if (water_min != 0 || water_max != 0) {
                p.add(water);
                y_size += 16;
            }
            if (fire_min != 0 || fire_max != 0) {
                p.add(fire);
                y_size += 16;
            }
            if (air_min != 0 || air_max != 0) {
                p.add(air);
                y_size += 16;
            }

            p.add(new JLabel(" ")); // +16

            //Critical
            p.add(critical_avg); // +16
            if (neutral_min != 0 || neutral_max != 0) {
                p.add(critical_neutral);
                y_size += 16;
            }
            if (earth_min != 0 || earth_max != 0) {
                p.add(critical_earth);
                y_size += 16;
            }
            if (thunder_min != 0 || thunder_max != 0) {
                p.add(critical_thunder);
                y_size += 16;
            }
            if (water_min != 0 || water_max != 0) {
                p.add(critical_water);
                y_size += 16;
            }
            if (fire_min != 0 || fire_max != 0) {
                p.add(critical_fire);
                y_size += 16;
            }
            if (air_min != 0 || air_max != 0) {
                p.add(critical_air);
                y_size += 16;
            }

            y_size += 68;
            p.setBounds(10, y, 280, y_size);
        }
    }
}
