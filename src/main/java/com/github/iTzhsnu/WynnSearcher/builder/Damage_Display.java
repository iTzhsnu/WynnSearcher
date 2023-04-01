package com.github.iTzhsnu.WynnSearcher.builder;

import com.github.iTzhsnu.WynnSearcher.Identifications;
import com.github.iTzhsnu.WynnSearcher.builder.skilltree.SpellEnum;
import com.github.iTzhsnu.WynnSearcher.builder.skilltree.TreeBase;
import com.github.iTzhsnu.WynnSearcher.builder.skilltree.TreeCheckBox;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Damage_Display {
    private final JPanel pane = new JPanel();
    private JsonObject weapon = null;

    public Damage_Display(JPanel p) {

        pane.setPreferredSize(new Dimension(300, 397));
        pane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane(pane);
        scrollPane.setBounds(708, 385, 318, 400);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);

        p.add(scrollPane);
    }

    public void setDamage_Display(JsonObject weapon, SkillPoint sp, Damage_Boosts damage_Boosts, TreeBase tree, int[] id_Numbers, List<JTextField> powders) {
        this.weapon = weapon;
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
            JsonObject powderJ = JsonParser.parseReader(new JsonReader(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/other/powders.json")), StandardCharsets.UTF_8))).getAsJsonObject();
            if (weapon.get("sockets") != null && weapon.get("sockets").getAsInt() > 0 && powders.get(4).getText().length() > 1) {
                String[] turn = new String[] {"", "", "", "", ""};
                int[] convert = new int[] {0, 0, 0, 0, 0};
                for (int i = 0; (int) Math.floor(powders.get(4).getText().length() / 2F) * 2 > i; i += 2) {
                    if (weapon.get("sockets").getAsInt() >= i / 2) {
                        String name = String.valueOf(powders.get(4).getText().charAt(i)) + powders.get(4).getText().charAt(i + 1);
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

                        float convertCalcMin = neutral_min * (convert[i] / 100F);
                        float convertCalcMax = neutral_max * (convert[i] / 100F);

                        if (neutral_min == 0) convertCalcMin = 0;
                        if (neutral_max == 0) convertCalcMax = 0;

                        //Add Elemental
                        switch (turn[i]) {
                            case "earth": {
                                earth_min += convertCalcMin; //Neutral Damage Min * (Convert % / 100)
                                earth_max += convertCalcMax; //Neutral Damage Max * (Convert % / 100)
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
                        neutral_min -= convertCalcMin;
                        neutral_max -= convertCalcMax;
                    }
                }
            }

            int raw_Damage = 0;
            int elem_Raw_Damage = 0;
            float percent = 1F;
            float multiple = 1F;
            float melee_percent = 0F;
            float melee_multiple = 1F;
            float spell_percent = 0F;
            float spell_multiple = 1F;
            int spell_cost_1 = 0;
            int spell_cost_2 = 0;
            int spell_cost_3 = 0;
            int spell_cost_4 = 0;
            float proficiency = 1F;
            int atkSpd = id_Numbers[41];
            boolean crit_boost = false;
            if (weapon.get(Identifications.ATTACK_SPEED.getItemName()) != null) {
                switch (weapon.get(Identifications.ATTACK_SPEED.getItemName()).getAsString()) {
                    case "VERY_SLOW": atkSpd += 1;
                        break;
                    case "SLOW": atkSpd += 2;
                        break;
                    case "NORMAL": atkSpd += 3;
                        break;
                    case "FAST": atkSpd += 4;
                        break;
                    case "VERY_FAST": atkSpd += 5;
                        break;
                    case "SUPER_FAST": atkSpd += 6;
                        break;
                }
            }

            if (damage_Boosts.getBox().get(1).isSelected()) percent += 0.2F; //Ragnarokkr (+20%)
            if (damage_Boosts.getBox().get(3).isSelected()) percent += 0.6F; //Fortitude (+60%)
            if (damage_Boosts.getBox().get(4).isSelected()) percent += 0.2F; //Vengeful Spirit (+20%)

            for (TreeCheckBox tcb : tree.getTcb()) {
                if (tcb.isSelected()) {
                    switch (tcb.getSkill()) {
                        case EARTH_DAMAGE:
                            if (earth_min != 0 || earth_max != 0) {
                                earth_min += 2;
                                earth_max += 4;
                            }
                            break;
                        case THUNDER_DAMAGE:
                            if (thunder_min != 0 || thunder_max != 0) {
                                thunder_min += 1;
                                thunder_max += 8;
                            }
                            break;
                        case WATER_DAMAGE:
                            if (water_min != 0 || water_max != 0) {
                                water_min += 2;
                                water_max += 4;
                            }
                            break;
                        case FIRE_DAMAGE:
                            if (fire_min != 0 || fire_max != 0) {
                                fire_min += 3;
                                fire_max += 5;
                            }
                            break;
                        case AIR_DAMAGE:
                            if (air_min != 0 || air_max != 0) {
                                air_min += 3;
                                air_max += 4;
                            }
                            break;
                        case PROFICIENCY:
                            proficiency += 0.05F;
                            break;
                        case PRECISE_STRIKES:
                            crit_boost = true;
                            break;
                        case CHEAPER_1ST_SP_COST_10:
                            spell_cost_1 -= 10;
                            break;
                        case CHEAPER_3RD_SP_COST_10:
                            spell_cost_3 -= 10;
                            break;
                        case CHEAPER_1ST_SP_COST:
                            spell_cost_1 -= 5;
                            break;
                        case CHEAPER_2ND_SP_COST:
                            spell_cost_2 -= 5;
                            break;
                        case CHEAPER_3RD_SP_COST:
                            spell_cost_3 -= 5;
                            break;
                        case CHEAPER_4TH_SP_COST:
                            spell_cost_4 -= 5;
                            break;
                    }

                    switch (tree.getClasses()) {
                        case "Warrior": {
                            switch (tcb.getSkill()) {
                                case VEHEMENT:
                                    raw_Damage += 5;
                                    break;
                                case BAKALS_GRASP:
                                    if (damage_Boosts.getSlider().get(0).getValue() > 1) {
                                        if (tree.getTcb().get(44).isSelected()) { //Uncontainable Corruption
                                            raw_Damage += Math.min((int) Math.floor(damage_Boosts.getSlider().get(0).getValue() / 2F) * 5, 120);
                                        } else {
                                            raw_Damage += Math.min((int) Math.floor(damage_Boosts.getSlider().get(0).getValue() / 2F) * 4, 120);
                                        }
                                    }
                                    break;
                                case ENRAGED_BLOW:
                                    if (damage_Boosts.getSlider().get(0).getValue() > 0) {
                                        if (tree.getTcb().get(63).isSelected()) {
                                            multiple *= Math.min(1F + damage_Boosts.getSlider().get(0).getValue() * 0.015F, 2.4F);
                                        } else {
                                            multiple *= Math.min(1F + damage_Boosts.getSlider().get(0).getValue() * 0.015F, 1.8F);
                                        }
                                    }
                                    break;
                                case DISCOMBOBULATE:
                                    if (damage_Boosts.getSlider().get(1).getValue() > 0) {
                                        if (earth_min != 0 || earth_max != 0) {
                                            earth_min += damage_Boosts.getSlider().get(1).getValue();
                                            earth_max += damage_Boosts.getSlider().get(1).getValue();
                                        }
                                        if (thunder_min != 0 || thunder_max != 0) {
                                            thunder_min += damage_Boosts.getSlider().get(1).getValue();
                                            thunder_max += damage_Boosts.getSlider().get(1).getValue();
                                        }
                                        if (water_min != 0 || water_max != 0) {
                                            water_min += damage_Boosts.getSlider().get(1).getValue();
                                            water_max += damage_Boosts.getSlider().get(1).getValue();
                                        }
                                        if (fire_min != 0 || fire_max != 0) {
                                            fire_min += damage_Boosts.getSlider().get(1).getValue();
                                            fire_max += damage_Boosts.getSlider().get(1).getValue();
                                        }
                                        if (air_min != 0 || air_max != 0) {
                                            air_min += damage_Boosts.getSlider().get(1).getValue();
                                            air_max += damage_Boosts.getSlider().get(1).getValue();
                                        }
                                    }
                                    break;
                                case HALF_MOON_SWIPE:
                                    spell_cost_3 -= 5;
                                    break;
                                case SPIRIT_OF_THE_RABBIT:
                                    spell_cost_2 -= 5;
                                    break;
                                case AXE_KICK:
                                    spell_cost_3 += 15;
                                    break;
                            }
                            break;
                        }
                        case "Assassin":
                            //TODO Assassin Ability Tree
                        case "Archer":
                            //TODO Archer Ability Tree
                        case "Mage":
                            //TODO Mage Ability Tree
                        case "Shaman":
                            //TODO Shaman Ability Tree
                    }
                }
            }

            melee_multiple *= proficiency; //Melee Multiple * Proficiency (5% or 10%)

            if (raw_Damage != 0 || elem_Raw_Damage != 0) {
                Calc_Raw calc = new Calc_Raw(neutral_min, earth_min, thunder_min, water_min, fire_min, air_min, neutral_max, earth_max, thunder_max, water_max, fire_max, air_max);

                //Now Debug...
                id_Numbers[37] = raw_Damage;
                id_Numbers[39] = raw_Damage;
                raw_Damage = 0;

                if (neutral_min != 0 || neutral_max != 0) { //Neutral
                    neutral_min += calc.calc(raw_Damage, "Neutral", false, true);
                    neutral_max += calc.calc(raw_Damage, "Neutral", true, true);
                }
                if (earth_min != 0 || earth_max != 0) { //Earth
                    earth_min += calc.calc(raw_Damage, "Earth", false, true) + calc.calc(elem_Raw_Damage, "Earth", false, false);
                    earth_max += calc.calc(raw_Damage, "Earth", true, true) + calc.calc(elem_Raw_Damage, "Earth", true, false);
                }
                if (thunder_min != 0 || thunder_max != 0) { //Thunder
                    thunder_min += calc.calc(raw_Damage, "Thunder", false, true) + calc.calc(elem_Raw_Damage, "Thunder", false, false);
                    thunder_max += calc.calc(raw_Damage, "Thunder", true, true) + calc.calc(elem_Raw_Damage, "Thunder", true, false);
                }
                if (water_min != 0 || water_max != 0) { //Water
                    water_min += calc.calc(raw_Damage, "Water", false, true) + calc.calc(elem_Raw_Damage, "Water", false, false);
                    water_max += calc.calc(raw_Damage, "Water", true, true) + calc.calc(elem_Raw_Damage, "Water", true, false);
                }
                if (fire_min != 0 || fire_max != 0) { //Fire
                    fire_min += calc.calc(raw_Damage, "Fire", false, true) + calc.calc(elem_Raw_Damage, "Fire", false, false);
                    fire_max += calc.calc(raw_Damage, "Fire", true, true) + calc.calc(elem_Raw_Damage, "Fire", true, false);
                }
                if (air_min != 0 || air_max != 0) { //Air
                    air_min += calc.calc(raw_Damage, "Air", false, true) + calc.calc(elem_Raw_Damage, "Air", false, false);
                    air_max += calc.calc(raw_Damage, "Air", true, true) + calc.calc(elem_Raw_Damage, "Air", true, false);
                }
            }

            Calc_Raw calc_raw = new Calc_Raw(neutral_min, earth_min, thunder_min, water_min, fire_min, air_min, neutral_max, earth_max, thunder_max, water_max, fire_max, air_max);

            float intelligence = 1F - sp.getSkillPoint(SkillPoint.SkillPointType.INTELLIGENCE).getSPBoost();

            //Display Damages
            //Melee
            list.add(new Damage_Template("Melee", 0, pane, null, sp, true, false, crit_boost, false));
            calcMelee("", 0, calc_raw, list, (percent + melee_percent) * multiple * melee_multiple, id_Numbers, new float[] {1, 0, 0, 0, 0, 0}, atkSpd, sp);
            switch (tree.getClasses()) {
                case "Warrior": {
                    //Bash
                    if (tree.getTcb().get(0).isSelected()) {
                        list.add(new Damage_Template("Bash", calc_Spell_Cost(40 + spell_cost_1, intelligence, id_Numbers[72], id_Numbers[76]), pane, list.get(0), sp, false, true, crit_boost, true));
                        float[] bash_percent = set_Damage_Percent(SpellEnum.BASH);
                        if (tree.getTcb().get(3).isSelected()) add_Damage_Percent(bash_percent, SpellEnum.DOUBLE_BASH);
                        if (tree.getTcb().get(16).isSelected())
                            add_Damage_Percent(bash_percent, SpellEnum.QUADRUPLE_BASH);
                        if (tree.getTcb().get(39).isSelected())
                            add_Damage_Percent(bash_percent, SpellEnum.STRONGER_BASH);
                        if (tree.getTcb().get(62).isSelected()) add_Damage_Percent(bash_percent, SpellEnum.THUNDERCLAP);
                        calcSpell("Single Hit", 1, calc_raw, list, percent * multiple, id_Numbers, bash_percent, sp, false);
                        calcSpell("Total Damage", 1, calc_raw, list, percent * multiple, id_Numbers, calc_Total_Damage_Percent(bash_percent), sp, true);
                    }

                    //Charge
                    if (tree.getTcb().get(4).isSelected()) {
                        list.add(new Damage_Template("Charge", calc_Spell_Cost(25 + spell_cost_2, intelligence, id_Numbers[73], id_Numbers[77]), pane, list.get(list.size() - 1), sp, false, true, crit_boost, false));
                        if (tree.getTcb().get(10).isSelected()) calcSpell("Heavy Impact", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, set_Damage_Percent(SpellEnum.HEAVY_IMPACT), sp, false);
                        if (tree.getTcb().get(18).isSelected()) calcSpell("Flyby Jab", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, set_Damage_Percent(SpellEnum.FLYBY_JAB), sp, false);
                        if (tree.getTcb().get(32).isSelected()) calcSpell("Flying Kick", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, set_Damage_Percent(SpellEnum.FLYING_KICK), sp, false);
                        if (tree.getTcb().get(41).isSelected()) calcSpell("Collide", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, set_Damage_Percent(SpellEnum.COLLIDE), sp, false);
                    }

                    //Uppercut
                    if (tree.getTcb().get(7).isSelected()) {
                        list.add(new Damage_Template("Uppercut", calc_Spell_Cost(40 + spell_cost_3, intelligence, id_Numbers[74], id_Numbers[78]), pane, list.get(list.size() - 1), sp, false, true, crit_boost, true));
                        float[] uppercut_percent = set_Damage_Percent(SpellEnum.UPPERCUT);
                        if (tree.getTcb().get(20).isSelected()) add_Damage_Percent(uppercut_percent, SpellEnum.HALF_MOON_SWIPE);
                        if (tree.getTcb().get(46).isSelected()) add_Damage_Percent(uppercut_percent, SpellEnum.WHIRLWIND_STRIKE);
                        if (tree.getTcb().get(56).isSelected()) add_Damage_Percent(uppercut_percent, SpellEnum.AXE_KICK);
                        calcSpell("Uppercut", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, uppercut_percent, sp, false);
                        if (tree.getTcb().get(17).isSelected()) {
                            add_Damage_Percent(uppercut_percent, SpellEnum.FIREWORKS);
                            calcSpell("Fireworks", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, set_Damage_Percent(SpellEnum.FIREWORKS), sp, false);
                        }
                        if (tree.getTcb().get(43).isSelected()) {
                            add_Damage_Percent(uppercut_percent, SpellEnum.COMET);
                            calcSpell("Comet", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, set_Damage_Percent(SpellEnum.COMET), sp, false);
                        }
                        calcSpell("Total Damage", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, uppercut_percent, sp, true);
                    }

                    //War Scream
                    if (tree.getTcb().get(8).isSelected()) {
                        list.add(new Damage_Template("War Scream", calc_Spell_Cost(30 + spell_cost_4, intelligence, id_Numbers[75], id_Numbers[79]), pane, list.get(list.size() - 1), sp, false, true, crit_boost, false));
                        float[] war_scream_percent = set_Damage_Percent(SpellEnum.WAR_SCREAM);
                        float[] air_shout_percent = set_Damage_Percent(SpellEnum.AIR_SHOUT);
                        if (tree.getTcb().get(21).isSelected()) {
                            add_Damage_Percent(war_scream_percent, SpellEnum.IRON_LUNGS_FOR_WAR_SCREAM);
                            add_Damage_Percent(air_shout_percent, SpellEnum.IRON_LUNGS_FOR_AIR_SHOUT);
                        }
                        calcSpell("War Scream", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, war_scream_percent, sp, false);
                        if (tree.getTcb().get(22).isSelected()) calcSpell("Air Shout", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, air_shout_percent, sp, false);
                        if (tree.getTcb().get(53).isSelected()) calcSpell("Tempest", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, set_Damage_Percent(SpellEnum.TEMPEST), sp, false);
                    }

                    //Flaming Uppercut
                    if (tree.getTcb().get(19).isSelected()) {
                        list.add(new Damage_Template("Flaming Uppercut", 0, pane, list.get(list.size() - 1), sp, false, true, crit_boost, false));
                        calcSpell("", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, set_Damage_Percent(SpellEnum.FLAMING_UPPERCUT), sp, false);
                    }
                    //Counter
                    if (tree.getTcb().get(30).isSelected()) {
                        list.add(new Damage_Template("Counter", 0, pane, list.get(list.size() - 1), sp, false, true, crit_boost, false));
                        calcSpell("", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, set_Damage_Percent(SpellEnum.COUNTER), sp, false);
                    }
                    //Boiling Blood
                    if (tree.getTcb().get(35).isSelected()) {
                        list.add(new Damage_Template("Boiling Blood", 0, pane, list.get(list.size() - 1), sp, false, true, crit_boost, false));
                        calcSpell("", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, set_Damage_Percent(SpellEnum.BOILING_BLOOD), sp, false);
                    }
                    //Sparking Hope
                    if (tree.getTcb().get(51).isSelected()) {
                        list.add(new Damage_Template("Sparkling Hope", 0, pane, list.get(list.size() - 1), sp, false, true, crit_boost, false));
                        calcSpell("", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, set_Damage_Percent(SpellEnum.SPARKING_HOPE), sp, false);
                    }
                    //Cyclone
                    if (tree.getTcb().get(60).isSelected()) {
                        list.add(new Damage_Template("Cyclone", 0, pane, list.get(list.size() - 1), sp, false, true, crit_boost, false));
                        calcSpell("", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, set_Damage_Percent(SpellEnum.CYCLONE), sp, false);
                    }
                    //Shield Strike
                    if (tree.getTcb().get(50).isSelected()) {
                        list.add(new Damage_Template("Shield Strike", 0, pane, list.get(list.size() - 1), sp, false, true, crit_boost, false));
                        calcSpell("", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, set_Damage_Percent(SpellEnum.SHIELD_STRIKE), sp, false);
                    }
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

    private float[] set_Damage_Percent(SpellEnum spell) {
        float[] percent = new float[] {0, 0, 0, 0, 0, 0, 0};
        percent[0] = spell.getNeutral();
        percent[1] = spell.getEarth();
        percent[2] = spell.getThunder();
        percent[3] = spell.getWater();
        percent[4] = spell.getFire();
        percent[5] = spell.getAir();
        percent[6] = spell.getAddNum();
        return percent;
    }

    private void add_Damage_Percent(float[] percent, SpellEnum spell) {
        percent[0] += spell.getNeutral();
        percent[1] += spell.getEarth();
        percent[2] += spell.getThunder();
        percent[3] += spell.getWater();
        percent[4] += spell.getFire();
        percent[5] += spell.getAir();
        percent[6] += spell.getAddNum();
    }

    private float[] calc_Total_Damage_Percent(float[] percent) {
        percent[0] *= percent[6];
        percent[1] *= percent[6];
        percent[2] *= percent[6];
        percent[3] *= percent[6];
        percent[4] *= percent[6];
        percent[5] *= percent[6];
        return percent;
    }

    private float calc_Spell_Cost(int base, float intelligence, int id_Raw, int id_Percent) {
        return Math.max((base * intelligence + id_Raw) * (1F + id_Percent / 100F), 1);
    }

    private float calc(float damage, float boosts, float sp_Boost, int... id_Boosts) {
        if (damage == 0) return 0;

        float id_Boost = 1F;
        if (sp_Boost != 1F) id_Boost = sp_Boost; //Skill Point Boost
        if (id_Boosts.length > 0) {
            for (int i : id_Boosts) {
                id_Boost += i / 100F;
            }
        }

        return damage * id_Boost * boosts;
    }

    private void calcMelee(String name, int pos, Calc_Raw calc_raw, List<Damage_Template> list, float boost, int[] id_Numbers, float[] percent, int atkSpd, SkillPoint sp) {
        float tomeBonus = 1F + (id_Numbers[89] / 100F);
        float atkSpdF = 0.51F;
        if (atkSpd >= 6) {
            atkSpdF = 4.3F;
        } else if (atkSpd > 0) {
            switch (atkSpd) {
                case 1: atkSpdF = 0.83F;
                    break;
                case 2: atkSpdF = 1.5F;
                    break;
                case 3: atkSpdF = 2.05F;
                    break;
                case 4: atkSpdF = 2.5F;
                    break;
                case 5: atkSpdF = 3.1F;
                    break;
            }
        }

        float neutral_min_base = calc_raw.neutral_min;
        float earth_min_base = calc_raw.earth_min;
        float thunder_min_base = calc_raw.thunder_min;
        float water_min_base = calc_raw.water_min;
        float fire_min_base = calc_raw.fire_min;
        float air_min_base = calc_raw.air_min;
        float total_min = calc_raw.neutral_min + calc_raw.thunder_min + calc_raw.water_min + calc_raw.fire_min + calc_raw.air_min;

        float neutral_max_base = calc_raw.neutral_max;
        float earth_max_base = calc_raw.earth_max;
        float thunder_max_base = calc_raw.thunder_max;
        float water_max_base = calc_raw.water_max;
        float fire_max_base = calc_raw.fire_max;
        float air_max_base = calc_raw.air_max;
        float total_max = calc_raw.neutral_max + calc_raw.thunder_max + calc_raw.water_max + calc_raw.fire_max + calc_raw.air_max;

        //Neutral Base
        if (neutral_min_base != 0) neutral_min_base *= percent[0];
        if (neutral_max_base != 0) neutral_max_base *= percent[0];

        //Earth Base
        if (earth_min_base != 0) earth_min_base *= percent[0];
        if (earth_max_base != 0) earth_max_base *= percent[0];
        earth_min_base += total_min * percent[1];
        earth_max_base += total_max * percent[1];

        //Thunder Base
        if (thunder_min_base != 0) thunder_min_base *= percent[0];
        if (thunder_max_base != 0) thunder_max_base *= percent[0];
        thunder_min_base += total_min * percent[2];
        thunder_max_base += total_max * percent[2];

        //Water Base
        if (water_min_base != 0) water_min_base *= percent[0];
        if (water_max_base != 0) water_max_base *= percent[0];
        water_min_base += total_min * percent[3];
        water_max_base += total_max * percent[3];

        //Fire Base
        if (fire_min_base != 0) fire_min_base *= percent[0];
        if (fire_max_base != 0) fire_max_base *= percent[0];
        fire_min_base += total_min * percent[4];
        fire_max_base += total_max * percent[4];

        //Air Base
        if (air_min_base != 0) air_min_base *= percent[0];
        if (air_max_base != 0) air_max_base *= percent[0];
        air_min_base += total_min * percent[5];
        air_max_base += total_max * percent[5];


        float neutral_min = calc(neutral_min_base, boost, 1F, id_Numbers[38], id_Numbers[56]);
        float earth_min = calc(earth_min_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost(), id_Numbers[38], id_Numbers[57], id_Numbers[62], id_Numbers[31], id_Numbers[70]);
        float thunder_min = calc(thunder_min_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.DEXTERITY).getSPBoost(), id_Numbers[38], id_Numbers[58], id_Numbers[62], id_Numbers[32], id_Numbers[70]);
        float water_min = calc(water_min_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.INTELLIGENCE).getIntDamageBonus(), id_Numbers[38], id_Numbers[59], id_Numbers[62], id_Numbers[33], id_Numbers[70]);
        float fire_min = calc(fire_min_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.DEFENSE).getSPBoost(), id_Numbers[38], id_Numbers[60], id_Numbers[62], id_Numbers[34], id_Numbers[70]);
        float air_min = calc(air_min_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.AGILITY).getSPBoost(), id_Numbers[38], id_Numbers[61], id_Numbers[62], id_Numbers[35], id_Numbers[70]);

        float neutral_max = calc(neutral_max_base, boost, 1F, id_Numbers[38], id_Numbers[56]);
        float earth_max = calc(earth_max_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost(), id_Numbers[38], id_Numbers[57], id_Numbers[62], id_Numbers[31], id_Numbers[70]);
        float thunder_max = calc(thunder_max_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.DEXTERITY).getSPBoost(), id_Numbers[38], id_Numbers[58], id_Numbers[62], id_Numbers[32], id_Numbers[70]);
        float water_max = calc(water_max_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.INTELLIGENCE).getIntDamageBonus(), id_Numbers[38], id_Numbers[59], id_Numbers[62], id_Numbers[33], id_Numbers[70]);
        float fire_max = calc(fire_max_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.DEFENSE).getSPBoost(), id_Numbers[38], id_Numbers[60], id_Numbers[62], id_Numbers[34], id_Numbers[70]);
        float air_max = calc(air_max_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.AGILITY).getSPBoost(), id_Numbers[38], id_Numbers[61], id_Numbers[62], id_Numbers[35], id_Numbers[70]);

        if (neutral_min != 0 || neutral_max != 0) {
            neutral_min += calc_raw.calc(id_Numbers[39], "Neutral", false, true) + id_Numbers[63];
            neutral_max += calc_raw.calc(id_Numbers[39], "Neutral", true, true) + id_Numbers[63];

            neutral_min *= tomeBonus;
            neutral_max *= tomeBonus;
        }

        if (earth_min != 0 || earth_max != 0) {
            earth_min += calc_raw.calc(id_Numbers[39], "Earth", false, true) + calc_raw.calc(id_Numbers[69] + id_Numbers[71], "Earth", false, false) + id_Numbers[64];
            earth_max += calc_raw.calc(id_Numbers[39], "Earth", true, true) + calc_raw.calc(id_Numbers[69] + id_Numbers[71], "Earth", true, false) + id_Numbers[64];

            earth_min *= tomeBonus;
            earth_max *= tomeBonus;
        }

        if (thunder_min != 0 || thunder_max != 0) {
            thunder_min += calc_raw.calc(id_Numbers[39], "Thunder", false, true) + calc_raw.calc(id_Numbers[69] + id_Numbers[71], "Thunder", false, false) + id_Numbers[65];
            thunder_max += calc_raw.calc(id_Numbers[39], "Thunder", true, true) + calc_raw.calc(id_Numbers[69] + id_Numbers[71], "Thunder", true, false) + id_Numbers[65];

            thunder_min *= tomeBonus;
            thunder_max *= tomeBonus;
        }

        if (water_min != 0 || water_max != 0) {
            water_min += calc_raw.calc(id_Numbers[39], "Water", false, true) + calc_raw.calc(id_Numbers[69] + id_Numbers[71], "Water", false, false) + id_Numbers[66];
            water_max += calc_raw.calc(id_Numbers[39], "Water", true, true) + calc_raw.calc(id_Numbers[69] + id_Numbers[71], "Water", true, false) + id_Numbers[66];

            water_min *= tomeBonus;
            water_max *= tomeBonus;
        }

        if (fire_min != 0 || fire_max != 0) {
            fire_min += calc_raw.calc(id_Numbers[39], "Fire", false, true) + calc_raw.calc(id_Numbers[69] + id_Numbers[71], "Fire", false, false) + id_Numbers[67];
            fire_max += calc_raw.calc(id_Numbers[39], "Fire", true, true) + calc_raw.calc(id_Numbers[69] + id_Numbers[71], "Fire", true, false) + id_Numbers[67];

            fire_min *= tomeBonus;
            fire_max *= tomeBonus;
        }

        if (air_min != 0 || air_max != 0) {
            air_min += calc_raw.calc(id_Numbers[39], "Air", false, true) + calc_raw.calc(id_Numbers[69] + id_Numbers[71], "Air", false, false) + id_Numbers[68];
            air_max += calc_raw.calc(id_Numbers[39], "Air", true, true) + calc_raw.calc(id_Numbers[69] + id_Numbers[71], "Air", true, false) + id_Numbers[68];

            air_min *= tomeBonus;
            air_max *= tomeBonus;
        }

        list.get(pos).addDamage(name
                , neutral_min //Neutral Min
                , earth_min //Earth Min
                , thunder_min //Thunder Min
                , water_min //Water Min
                , fire_min //Fire Min
                , air_min //Air Min

                , neutral_max //Neutral Max
                , earth_max //Earth Max
                , thunder_max //Thunder Max
                , water_max //Water Max
                , fire_max //Fire Max
                , air_max //Air Max
                , atkSpdF
                , true);
    }

    private void calcSpell(String name, int pos, Calc_Raw calc_raw, List<Damage_Template> list, float boost, int[] id_Numbers, float[] percent, SkillPoint sp, boolean isTotal) {
        float tomeBonus = 1F - (id_Numbers[89] / 100F);
        float atkSpd = 0.51F;
        if (weapon != null && weapon.get(Identifications.ATTACK_SPEED.getItemName()) != null) {
            switch (weapon.get(Identifications.ATTACK_SPEED.getItemName()).getAsString()) {
                case "VERY_SLOW": atkSpd = 0.83F;
                    break;
                case "SLOW": atkSpd = 1.5F;
                    break;
                case "NORMAL": atkSpd = 2.05F;
                    break;
                case "FAST": atkSpd = 2.5F;
                    break;
                case "VERY_FAST": atkSpd = 3.1F;
                    break;
                case "SUPER_FAST": atkSpd = 4.3F;
                    break;
            }
        }

        float neutral_min_base = calc_raw.neutral_min;
        float earth_min_base = calc_raw.earth_min;
        float thunder_min_base = calc_raw.thunder_min;
        float water_min_base = calc_raw.water_min;
        float fire_min_base = calc_raw.fire_min;
        float air_min_base = calc_raw.air_min;
        float total_min = calc_raw.neutral_min + calc_raw.thunder_min + calc_raw.water_min + calc_raw.fire_min + calc_raw.air_min;

        float neutral_max_base = calc_raw.neutral_max;
        float earth_max_base = calc_raw.earth_max;
        float thunder_max_base = calc_raw.thunder_max;
        float water_max_base = calc_raw.water_max;
        float fire_max_base = calc_raw.fire_max;
        float air_max_base = calc_raw.air_max;
        float total_max = calc_raw.neutral_max + calc_raw.thunder_max + calc_raw.water_max + calc_raw.fire_max + calc_raw.air_max;

        //Neutral Base
        if (neutral_min_base != 0) neutral_min_base *= percent[0];
        if (neutral_max_base != 0) neutral_max_base *= percent[0];

        //Earth Base
        if (earth_min_base != 0) earth_min_base *= percent[0];
        if (earth_max_base != 0) earth_max_base *= percent[0];
        earth_min_base += total_min * percent[1];
        earth_max_base += total_max * percent[1];

        //Thunder Base
        if (thunder_min_base != 0) thunder_min_base *= percent[0];
        if (thunder_max_base != 0) thunder_max_base *= percent[0];
        thunder_min_base += total_min * percent[2];
        thunder_max_base += total_max * percent[2];

        //Water Base
        if (water_min_base != 0) water_min_base *= percent[0];
        if (water_max_base != 0) water_max_base *= percent[0];
        water_min_base += total_min * percent[3];
        water_max_base += total_max * percent[3];

        //Fire Base
        if (fire_min_base != 0) fire_min_base *= percent[0];
        if (fire_max_base != 0) fire_max_base *= percent[0];
        fire_min_base += total_min * percent[4];
        fire_max_base += total_max * percent[4];

        //Air Base
        if (air_min_base != 0) air_min_base *= percent[0];
        if (air_max_base != 0) air_max_base *= percent[0];
        air_min_base += total_min * percent[5];
        air_max_base += total_max * percent[5];

        float neutral_min = calc(neutral_min_base, boost, 1F, id_Numbers[36], id_Numbers[42]);
        float earth_min = calc(earth_min_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost(), id_Numbers[36], id_Numbers[43], id_Numbers[48], id_Numbers[31], id_Numbers[70]);
        float thunder_min = calc(thunder_min_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.DEXTERITY).getSPBoost(), id_Numbers[36], id_Numbers[44], id_Numbers[48], id_Numbers[32], id_Numbers[70]);
        float water_min = calc(water_min_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.INTELLIGENCE).getIntDamageBonus(), id_Numbers[36], id_Numbers[45], id_Numbers[48], id_Numbers[33], id_Numbers[70]);
        float fire_min = calc(fire_min_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.DEFENSE).getSPBoost(), id_Numbers[36], id_Numbers[46], id_Numbers[48], id_Numbers[34], id_Numbers[70]);
        float air_min = calc(air_min_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.AGILITY).getSPBoost(), id_Numbers[36], id_Numbers[47], id_Numbers[48], id_Numbers[35], id_Numbers[70]);

        float neutral_max = calc(neutral_max_base, boost, 1F, id_Numbers[36], id_Numbers[42]);
        float earth_max = calc(earth_max_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost(), id_Numbers[36], id_Numbers[43], id_Numbers[48], id_Numbers[31], id_Numbers[70]);
        float thunder_max = calc(thunder_max_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.DEXTERITY).getSPBoost(), id_Numbers[36], id_Numbers[44], id_Numbers[48], id_Numbers[32], id_Numbers[70]);
        float water_max = calc(water_max_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.INTELLIGENCE).getIntDamageBonus(), id_Numbers[36], id_Numbers[45], id_Numbers[48], id_Numbers[33], id_Numbers[70]);
        float fire_max = calc(fire_max_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.DEFENSE).getSPBoost(), id_Numbers[36], id_Numbers[46], id_Numbers[48], id_Numbers[34], id_Numbers[70]);
        float air_max = calc(air_max_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.AGILITY).getSPBoost(), id_Numbers[36], id_Numbers[47], id_Numbers[48], id_Numbers[35], id_Numbers[70]);

        if (neutral_min != 0 || neutral_max != 0) {
            neutral_min += calc_raw.calc(id_Numbers[37], "Neutral", false, true) + id_Numbers[49];
            neutral_max += calc_raw.calc(id_Numbers[37], "Neutral", true, true) + id_Numbers[49];

            neutral_min *= tomeBonus;
            neutral_max *= tomeBonus;
        }
        if (earth_min != 0 || earth_max != 0) {
            earth_min += calc_raw.calc(id_Numbers[37], "Earth", false, true) + calc_raw.calc(id_Numbers[55] + id_Numbers[71], "Earth", false, false) + id_Numbers[50];
            earth_max += calc_raw.calc(id_Numbers[37], "Earth", true, true) + calc_raw.calc(id_Numbers[55] + id_Numbers[71], "Earth", true, false) + id_Numbers[50];

            earth_min *= tomeBonus;
            earth_max *= tomeBonus;
        }
        if (thunder_min != 0 || thunder_max != 0) {
            thunder_min += calc_raw.calc(id_Numbers[37], "Thunder", false, true) + calc_raw.calc(id_Numbers[55] + id_Numbers[71], "Thunder", false, false) + id_Numbers[51];
            thunder_max += calc_raw.calc(id_Numbers[37], "Thunder", true, true) + calc_raw.calc(id_Numbers[55] + id_Numbers[71], "Thunder", true, false) + id_Numbers[51];

            thunder_min *= tomeBonus;
            thunder_max *= tomeBonus;
        }
        if (water_min != 0 || water_max != 0) {
            water_min += calc_raw.calc(id_Numbers[37], "Water", false, true) + calc_raw.calc(id_Numbers[55] + id_Numbers[71], "Water", false, false) + id_Numbers[52];
            water_max += calc_raw.calc(id_Numbers[37], "Water", true, true) + calc_raw.calc(id_Numbers[55] + id_Numbers[71], "Water", true, false) + id_Numbers[52];

            water_min *= tomeBonus;
            water_max *= tomeBonus;
        }
        if (fire_min != 0 || fire_max != 0) {
            fire_min += calc_raw.calc(id_Numbers[37], "Fire", false, true) + calc_raw.calc(id_Numbers[55] + id_Numbers[71], "Fire", false, false) + id_Numbers[53];
            fire_max += calc_raw.calc(id_Numbers[37], "Fire", true, true) + calc_raw.calc(id_Numbers[55] + id_Numbers[71], "Fire", true, false) + id_Numbers[53];

            fire_min *= tomeBonus;
            fire_max *= tomeBonus;
        }
        if (air_min != 0 || air_max != 0) {
            air_min += calc_raw.calc(id_Numbers[37], "Air", false, true) + calc_raw.calc(id_Numbers[55] + id_Numbers[71], "Air", false, false) + id_Numbers[54];
            air_max += calc_raw.calc(id_Numbers[37], "Air", true, true) + calc_raw.calc(id_Numbers[55] + id_Numbers[71], "Air", true, false) + id_Numbers[54];

            air_min *= tomeBonus;
            air_max *= tomeBonus;
        }

        list.get(pos).addDamage(name
                , neutral_min //Neutral Min
                , earth_min //Earth Min
                , thunder_min //Thunder Min
                , water_min //Water Min
                , fire_min //Fire Min
                , air_min //Air Min

                , neutral_max //Neutral Max
                , earth_max //Earth Max
                , thunder_max //Thunder Max
                , water_max //Water Max
                , fire_max //Fire Max
                , air_max //Air Max
                , atkSpd
                , isTotal);
    }

    private static class Calc_Raw {
        private final float neutral_min;
        private final float earth_min;
        private final float thunder_min;
        private final float water_min;
        private final float fire_min;
        private final float air_min;

        private final float neutral_max;
        private final float earth_max;
        private final float thunder_max;
        private final float water_max;
        private final float fire_max;
        private final float air_max;

        private Calc_Raw(float neutral_min, float earth_min, float thunder_min, float water_min, float fire_min, float air_min
                , float neutral_max, float earth_max, float thunder_max, float water_max, float fire_max, float air_max) {
            this.neutral_min = neutral_min;
            this.earth_min = earth_min;
            this.thunder_min = thunder_min;
            this.water_min = water_min;
            this.fire_min = fire_min;
            this.air_min = air_min;

            this.neutral_max = neutral_max;
            this.earth_max = earth_max;
            this.thunder_max = thunder_max;
            this.water_max = water_max;
            this.fire_max = fire_max;
            this.air_max = air_max;
        }

        public float calc(int raw, String elem, boolean max, boolean isNotElem) {
            if (raw == 0) return 0;

            float total_min = earth_min + thunder_min + water_min + fire_min + air_min;
            float total_max = earth_max + thunder_max + water_max + fire_max + air_max;

            if (isNotElem) {
                total_min += neutral_min;
                total_max += neutral_max;
            }

            float elemMin_OR_Max;
            if (max) {
                switch (elem) {
                    case "Earth": elemMin_OR_Max = earth_max;
                        break;
                    case "Thunder": elemMin_OR_Max = thunder_max;
                        break;
                    case "Water": elemMin_OR_Max = water_max;
                        break;
                    case "Fire": elemMin_OR_Max = fire_max;
                        break;
                    case "Air": elemMin_OR_Max = air_max;
                        break;
                    default: elemMin_OR_Max = neutral_max;
                        break;
                }
                if (elemMin_OR_Max != 0) {
                    return raw * (elemMin_OR_Max / total_max);
                } else {
                    return 0;
                }
            } else {
                switch (elem) {
                    case "Earth": elemMin_OR_Max = earth_min;
                        break;
                    case "Thunder": elemMin_OR_Max = thunder_min;
                        break;
                    case "Water": elemMin_OR_Max = water_min;
                        break;
                    case "Fire": elemMin_OR_Max = fire_min;
                        break;
                    case "Air": elemMin_OR_Max = air_min;
                        break;
                    default: elemMin_OR_Max = neutral_min;
                        break;
                }

                if (elemMin_OR_Max != 0) {
                    return raw * (elemMin_OR_Max / total_min);
                } else {
                    return 0;
                }
            }
        }
    }

    private static class Damage_Template {
        private final JPanel p = new JPanel();
        private int y_size = 25;
        private int y = 10;
        private final JLabel avg_total = new JLabel("Total Average: 0");
        private final JLabel avg_dps = new JLabel("Average DPS: 0");
        private final SkillPoint sp;
        private final boolean isMelee;
        private final boolean isSpell;
        private final boolean crit_boost;
        private final JPanel pane;

        private Damage_Template(String name, float mana_Cost, JPanel pane, Damage_Template previous, SkillPoint sp, boolean isMelee, boolean isSpell, boolean crit_boost, boolean needTotal) {
            this.sp  = sp;
            this.isMelee = isMelee;
            this.isSpell = isSpell;
            this.crit_boost = crit_boost;
            this.pane = pane;
            if (previous != null) {
                p.setBounds(10, previous.y + previous.y_size + 10, 280, 30);
                y = previous.y + previous.y_size + 10;
            } else {
                p.setBounds(10, 10, 280, 30);
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
            avg_total.setAlignmentX(Component.CENTER_ALIGNMENT);
            avg_dps.setAlignmentX(Component.CENTER_ALIGNMENT);
            p.add(nameL);
            if (needTotal || isMelee) { //Blank
                p.add(new JLabel(" ")); // +20
                y_size += 20;
            }
            if (needTotal) { //Avg Total Damage
                p.add(avg_total); // +16
                y_size += 16;
            }
            if (isMelee) { //Melee Avg DPS
                p.add(avg_dps); // +16
                y_size += 16;
            }
            if (y + y_size + 10 > 397) {
                pane.setPreferredSize(new Dimension(300, y + y_size + 10));
            } else {
                pane.setPreferredSize(new Dimension(300, 397));
            }

            p.setBackground(new Color(200, 200, 200));
            pane.add(p);
        }

        private void addDamage(String name, float neutral_min, float earth_min, float thunder_min, float water_min, float fire_min, float air_min
                , float neutral_max, float earth_max, float thunder_max, float water_max, float fire_max, float air_max
                , float atkSpd, boolean isTotal) {

            if (isSpell) {
                neutral_min *= atkSpd;
                earth_min *= atkSpd;
                thunder_min *= atkSpd;
                water_min *= atkSpd;
                fire_min *= atkSpd;
                air_min *= atkSpd;

                neutral_max *= atkSpd;
                earth_max *= atkSpd;
                thunder_max *= atkSpd;
                water_max *= atkSpd;
                fire_max *= atkSpd;
                air_max *= atkSpd;
            }

            float crit_boost = 1F;
            if (this.crit_boost) crit_boost = 1.15F;

            float crit_neutral_min = neutral_min * (crit_boost + sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost());
            float crit_earth_min = earth_min  * (crit_boost + sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost());
            float crit_thunder_min = thunder_min  * (crit_boost + sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost());
            float crit_water_min = water_min  * (crit_boost + sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost());
            float crit_fire_min = fire_min  * (crit_boost + sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost());
            float crit_air_min = air_min  * (crit_boost + sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost());

            float crit_neutral_max = neutral_max * (crit_boost + sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost());
            float crit_earth_max = earth_max  * (crit_boost + sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost());
            float crit_thunder_max = thunder_max  * (crit_boost + sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost());
            float crit_water_max = water_max  * (crit_boost + sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost());
            float crit_fire_max = fire_max  * (crit_boost + sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost());
            float crit_air_max = air_max  * (crit_boost + sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost());

            neutral_min *= sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost();
            earth_min *= sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost();
            thunder_min *= sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost();
            water_min *= sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost();
            fire_min *= sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost();
            air_min *= sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost();

            neutral_max *= sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost();
            earth_max *= sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost();
            thunder_max *= sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost();
            water_max *= sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost();
            fire_max *= sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost();
            air_max *= sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost();

            float damage = neutral_min + earth_min + thunder_min + water_min + fire_min + air_min + neutral_max + earth_max + thunder_max + water_max + fire_max + air_max;
            float crit_damage = crit_neutral_min + crit_earth_min + crit_thunder_min + crit_water_min + crit_fire_min + crit_air_min + crit_neutral_max + crit_earth_max + crit_thunder_max + crit_water_max + crit_fire_max + crit_air_max;
            float crit_chance = sp.getSkillPoint(SkillPoint.SkillPointType.DEXTERITY).getSPBoost();
            float total_average = damage / 2F * (2F - crit_chance) + crit_damage / 2F * (crit_chance - 1F);

            JLabel nameL = new JLabel(name);
            JLabel avg = new JLabel("Average: " + total_average);
            JLabel no_crit_avg = new JLabel("Non-Crit Average: " + (damage / 2F));
            JLabel neutral = new JLabel(neutral_min + " Neutral " + neutral_max);
            JLabel earth = new JLabel(earth_min + " Earth " + earth_max);
            JLabel thunder = new JLabel(thunder_min + " Thunder " + thunder_max);
            JLabel water = new JLabel(water_min + " Water " + water_max);
            JLabel fire = new JLabel(fire_min + " Fire " + fire_max);
            JLabel air = new JLabel(air_min + " Air " + air_max);

            JLabel critical_avg = new JLabel("Crit Average: " + (crit_damage / 2F));
            JLabel critical_neutral = new JLabel(crit_neutral_min + " Neutral " + crit_neutral_max);
            JLabel critical_earth = new JLabel(crit_earth_min + " Earth " + crit_earth_max);
            JLabel critical_thunder = new JLabel(crit_thunder_min + " Thunder " + crit_thunder_max);
            JLabel critical_water = new JLabel(crit_water_min + " Water " + crit_water_max);
            JLabel critical_fire = new JLabel(crit_fire_min + " Fire " + crit_fire_max);
            JLabel critical_air = new JLabel(crit_air_min + " Air " + crit_air_max);

            nameL.setFont(new Font(Font.DIALOG, Font.PLAIN, 15));

            nameL.setAlignmentX(Component.CENTER_ALIGNMENT);
            avg.setAlignmentX(Component.CENTER_ALIGNMENT);
            no_crit_avg.setAlignmentX(Component.CENTER_ALIGNMENT);
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
            p.add(no_crit_avg); // +16
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

            y_size += 84;
            p.setBounds(10, y, 280, y_size);
            if (y + y_size + 10 > 397) {
                pane.setPreferredSize(new Dimension(300, y + y_size + 10));
            } else {
                pane.setPreferredSize(new Dimension(300, 397));
            }

            if (isTotal) {
                avg_total.setText("Total Average: " + total_average);
                if (isMelee) avg_dps.setText("Average DPS: " + (total_average * atkSpd));
            }
        }
    }
}
