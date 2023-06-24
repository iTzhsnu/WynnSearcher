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
    private TotalMaxDamage totalMaxDamage = null;

    public Damage_Display(JPanel p) {

        pane.setPreferredSize(new Dimension(300, 397));
        pane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane(pane);
        scrollPane.setBounds(708, 435, 318, 400);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);

        p.add(scrollPane);
    }

    public void setDamage_Display(JsonObject weapon, SkillPoint sp, Damage_Boosts damage_Boosts, TreeBase tree, Powder_Effects powder_effects, int[] id_Numbers, List<JTextField> powders) {
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

            if (weapon.get("base") != null) {
                JsonObject json = weapon.get("base").getAsJsonObject();
                //Neutral Damage
                if (json.get(Identifications.NEUTRAL_DAMAGE.getItemName()) != null) {
                    JsonObject j = json.get(Identifications.NEUTRAL_DAMAGE.getItemName()).getAsJsonObject();
                    if (j.get("max").getAsString().contains("-")) {
                        String[] ss = j.get("max").getAsString().split("-");
                        neutral_min = Integer.parseInt(ss[0]);
                        neutral_max = Integer.parseInt(ss[ss.length - 1]);
                    } else {
                        neutral_min = j.get("min").getAsInt();
                        neutral_max = j.get("max").getAsInt();
                    }
                }

                //Earth Damage
                if (json.get(Identifications.EARTH_DAMAGE.getItemName()) != null) {
                    JsonObject j = json.get(Identifications.EARTH_DAMAGE.getItemName()).getAsJsonObject();
                    if (j.get("max").getAsString().contains("-")) {
                        String[] ss = j.get("max").getAsString().split("-");
                        earth_min = Integer.parseInt(ss[0]);
                        earth_max = Integer.parseInt(ss[ss.length - 1]);
                    } else {
                        earth_min = j.get("min").getAsInt();
                        earth_max = j.get("max").getAsInt();
                    }
                }

                //Thunder Damage
                if (json.get(Identifications.THUNDER_DAMAGE.getItemName()) != null) {
                    JsonObject j = json.get(Identifications.THUNDER_DAMAGE.getItemName()).getAsJsonObject();
                    if (j.get("max").getAsString().contains("-")) {
                        String[] ss = j.get("max").getAsString().split("-");
                        thunder_min = Integer.parseInt(ss[0]);
                        thunder_max = Integer.parseInt(ss[ss.length - 1]);
                    } else {
                        thunder_min = j.get("min").getAsInt();
                        thunder_max = j.get("max").getAsInt();
                    }
                }

                //Water Damage
                if (json.get(Identifications.WATER_DAMAGE.getItemName()) != null) {
                    JsonObject j = json.get(Identifications.WATER_DAMAGE.getItemName()).getAsJsonObject();
                    if (j.get("max").getAsString().contains("-")) {
                        String[] ss = j.get("max").getAsString().split("-");
                        water_min = Integer.parseInt(ss[0]);
                        water_max = Integer.parseInt(ss[ss.length - 1]);
                    } else {
                        water_min = j.get("min").getAsInt();
                        water_max = j.get("max").getAsInt();
                    }
                }

                //Fire Damage
                if (json.get(Identifications.FIRE_DAMAGE.getItemName()) != null) {
                    JsonObject j = json.get(Identifications.FIRE_DAMAGE.getItemName()).getAsJsonObject();
                    if (j.get("max").getAsString().contains("-")) {
                        String[] ss = j.get("max").getAsString().split("-");
                        fire_min = Integer.parseInt(ss[0]);
                        fire_max = Integer.parseInt(ss[ss.length - 1]);
                    } else {
                        fire_min = j.get("min").getAsInt();
                        fire_max = j.get("max").getAsInt();
                    }
                }

                //Air Damage
                if (json.get(Identifications.AIR_DAMAGE.getItemName()) != null) {
                    JsonObject j = json.get(Identifications.AIR_DAMAGE.getItemName()).getAsJsonObject();
                    if (j.get("max").getAsString().contains("-")) {
                        String[] ss = j.get("max").getAsString().split("-");
                        air_min = Integer.parseInt(ss[0]);
                        air_max = Integer.parseInt(ss[ss.length - 1]);
                    } else {
                        air_min = j.get("min").getAsInt();
                        air_max = j.get("max").getAsInt();
                    }
                }
            }


            //Powder
            JsonObject powderJ = JsonParser.parseReader(new JsonReader(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/other/powders.json")), StandardCharsets.UTF_8))).getAsJsonObject();
            if (weapon.get(Identifications.POWDER_SLOTS.getItemName()) != null && weapon.get(Identifications.POWDER_SLOTS.getItemName()).getAsInt() > 0 && powders.get(4).getText().length() > 1) {
                String[] turn = new String[] {"", "", "", "", ""};
                int[] convert = new int[] {0, 0, 0, 0, 0};
                for (int i = 0; (int) Math.floor(powders.get(4).getText().length() / 2F) * 2 > i; i += 2) {
                    if (weapon.get(Identifications.POWDER_SLOTS.getItemName()).getAsInt() >= i / 2) {
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
            //int elem_Raw_Damage = 0;
            float percent = 1F;
            float multiple = 1F;
            float melee_percent = 0F;
            float melee_multiple = 1F;
            //float spell_percent = 0F;
            //float spell_multiple = 1F;
            int spell_cost_1 = 0;
            int spell_cost_2 = 0;
            int spell_cost_3 = 0;
            int spell_cost_4 = 0;
            float proficiency = 1F;
            int atkSpd = id_Numbers[ID_Display.ID_INT.get(Identifications.ATTACK_SPEED_BONUS)];
            boolean crit_boost = false;
            if (weapon.get(Identifications.ATTACK_SPEED.getItemName()) != null) {
                switch (weapon.get(Identifications.ATTACK_SPEED.getItemName()).getAsString()) {
                    case "very_slow": atkSpd += 1;
                        break;
                    case "slow": atkSpd += 2;
                        break;
                    case "normal": atkSpd += 3;
                        break;
                    case "fast": atkSpd += 4;
                        break;
                    case "very_fast": atkSpd += 5;
                        break;
                    case "super_fast": atkSpd += 6;
                        break;
                }
            }

            if (damage_Boosts.getBox().get(1).isSelected()) percent += 0.2F; //Ragnarokkr (+20%)
            if (damage_Boosts.getBox().get(3).isSelected()) percent += 0.6F; //Fortitude (+60%)
            if (damage_Boosts.getBox().get(4).isSelected()) percent += 0.2F; //Vengeful Spirit (+20%)
            if (damage_Boosts.getBox().get(5).isSelected()) multiple /= 0.7F; //Armour Breaker (-30% Def)
            if (damage_Boosts.getSlider().get(0).getValue() > 0) { //Marked
                if (damage_Boosts.getBox().get(6).isSelected()) { //is 10%
                    multiple *= 1F + damage_Boosts.getSlider().get(0).getValue() * 0.1F;
                } else { //is 6%
                    multiple *= 1F + damage_Boosts.getSlider().get(0).getValue() * 0.06F;
                }
            }

            boolean[] tb = {
                    false, false, false, false, false, false, false, false, false, false //0 ~ 9
            };
            for (TreeCheckBox tcb : tree.getTcb()) {
                if (tcb.isSelected()) {
                    switch (tree.getClasses()) {
                        case "warrior": {
                            switch (tcb.getFixedTreeName()) {
                                case "Uncontainable Corruption":
                                    tb[0] = true;
                                    break;
                                case "Better Enraged Blow":
                                    tb[1] = true;
                                    break;
                            }
                            break;
                        }
                        case "assassin": {
                            switch (tcb.getFixedTreeName()) {
                                case "Ambush":
                                    tb[0] = true;
                                    break;
                                case "Stronger Clones":
                                    tb[1] = true;
                                    break;
                                case "Even Stronger Clones":
                                    tb[2] = true;
                                    break;
                                case "Satsujin":
                                    if (damage_Boosts.getBox().get(13).isSelected()) {
                                        tb[3] = true;
                                    }
                                    break;
                            }
                            break;
                        }
                        case "archer": {
                            switch (tcb.getFixedTreeName()) {
                                case "Patient Hunter":
                                    tb[0] = true;
                                    break;
                                case "Decimator":
                                    tb[1] = true;
                                    break;
                                case "Focus":
                                    tb[2] = true;
                                    break;
                            }
                            break;
                        }
                        case "mage": {
                            switch (tcb.getFixedTreeName()) {
                                case "Fluid Healing":
                                    tb[0] = true;
                                    break;
                                case "Orphion's Pulse":
                                    tb[1] = true;
                                    break;
                                case "Windsweeper":
                                    tb[2] = true;
                                    break;
                            }
                            break;
                        }
                        case "shaman": {
                            switch (tcb.getAPIName()) {
                                case "morePuppets":
                                    tb[0] = true;
                                    break;
                                case "morePuppets2":
                                    tb[1] = true;
                                    break;
                            }
                            switch (tcb.getFixedTreeName()) {
                                case "Bullwhip":
                                    tb[2] = true;
                                    break;
                                case "Invigorating Wave":
                                    tb[3] = true;
                                    break;
                            }
                            break;
                        }
                    }
                }
            }

            for (TreeCheckBox tcb : tree.getTcb()) {
                if (tcb.isSelected()) {
                    //All Class
                    switch (tcb.getFixedTreeName()) {
                        case "Earth Mastery":
                            if (earth_min != 0 || earth_max != 0) {
                                earth_min += 2;
                                earth_max += 4;
                            }
                            break;
                        case "Thunder Mastery":
                            if (thunder_min != 0 || thunder_max != 0) {
                                thunder_min += 1;
                                thunder_max += 8;
                            }
                            break;
                        case "Water Mastery":
                            if (water_min != 0 || water_max != 0) {
                                water_min += 2;
                                water_max += 4;
                            }
                            break;
                        case "Fire Mastery":
                            if (fire_min != 0 || fire_max != 0) {
                                fire_min += 3;
                                fire_max += 5;
                            }
                            break;
                        case "Air Mastery":
                            if (air_min != 0 || air_max != 0) {
                                air_min += 3;
                                air_max += 4;
                            }
                            break;
                        case "Precise Strike":
                            crit_boost = true;
                            break;
                    }

                    //per Class
                    switch (tree.getClasses()) {
                        case "warrior": {
                            switch (tcb.getAPIName()) { //Check from API Name
                                case "bashCost1":
                                    spell_cost_1 -= 10;
                                    break;
                                case "bashCost2":
                                    spell_cost_1 -= 5;
                                    break;
                            }
                            switch (tcb.getFixedTreeName()) { //Check from Tree Display Name
                                case "Spear Proficiency I":
                                case "Spear Proficiency II":
                                    proficiency += 0.05F;
                                    break;
                                case "Vehement":
                                    raw_Damage += 5;
                                    break;
                                case "Cheaper Charge":
                                case "Spirit of the Rabbit":
                                    spell_cost_2 -= 5;
                                    break;
                                case "Bak'al's Grasp": //Corrupted
                                    if (damage_Boosts.getSlider().get(1).getValue() > 1) {
                                        if (tb[0]) { //Uncontainable Corruption
                                            raw_Damage += Math.min((int) Math.floor(damage_Boosts.getSlider().get(1).getValue() / 2F) * 5, 120);
                                        } else {
                                            raw_Damage += Math.min((int) Math.floor(damage_Boosts.getSlider().get(1).getValue() / 2F) * 4, 120);
                                        }
                                    }
                                    break;
                                case "Half-Moon Swipe":
                                case "Cheaper Uppercut":
                                    spell_cost_3 -= 5;
                                    break;
                                case "Enraged Blow": //Corrupted
                                    if (damage_Boosts.getSlider().get(1).getValue() > 0) {
                                        if (tb[1]) { //Better Enraged Blow
                                            multiple *= Math.min(1F + damage_Boosts.getSlider().get(1).getValue() * 0.015F, 2.4F);
                                        } else {
                                            multiple *= Math.min(1F + damage_Boosts.getSlider().get(1).getValue() * 0.015F, 1.8F);
                                        }
                                    }
                                    break;
                                case "Ragnarokkr":
                                    spell_cost_4 += 10;
                                    break;
                                case "Cheaper War Scream":
                                    spell_cost_4 -= 5;
                                    break;
                                case "Axe Kick":
                                    spell_cost_3 += 15;
                                    break;
                                case "Discombobulate":
                                    if (damage_Boosts.getSlider().get(2).getValue() > 0) {
                                        if (neutral_min != 0 || neutral_max != 0) {
                                            neutral_min += damage_Boosts.getSlider().get(2).getValue();
                                            neutral_max += damage_Boosts.getSlider().get(2).getValue();
                                        }
                                        if (earth_min != 0 || earth_max != 0) {
                                            earth_min += damage_Boosts.getSlider().get(2).getValue();
                                            earth_max += damage_Boosts.getSlider().get(2).getValue();
                                        }
                                        if (thunder_min != 0 || thunder_max != 0) {
                                            thunder_min += damage_Boosts.getSlider().get(2).getValue();
                                            thunder_max += damage_Boosts.getSlider().get(2).getValue();
                                        }
                                        if (water_min != 0 || water_max != 0) {
                                            water_min += damage_Boosts.getSlider().get(2).getValue();
                                            water_max += damage_Boosts.getSlider().get(2).getValue();
                                        }
                                        if (fire_min != 0 || fire_max != 0) {
                                            fire_min += damage_Boosts.getSlider().get(2).getValue();
                                            fire_max += damage_Boosts.getSlider().get(2).getValue();
                                        }
                                        if (air_min != 0 || air_max != 0) {
                                            air_min += damage_Boosts.getSlider().get(2).getValue();
                                            air_max += damage_Boosts.getSlider().get(2).getValue();
                                        }
                                    }
                                    break;
                            }
                            break;
                        }
                        case "assassin": {
                            switch (tcb.getAPIName()) {
                                case "spinAttackCost1":
                                    spell_cost_1 -= 10;
                                    break;
                                case "spinAttackCost2":
                                    spell_cost_1 -= 5;
                                    break;
                            }
                            switch (tcb.getFixedTreeName()) {
                                case "Dagger Proficiency I":
                                    proficiency += 0.05F;
                                    break;
                                case "Cheaper Dash":
                                    spell_cost_2 -= 5;
                                    break;
                                case "Backstab":
                                case "Cheaper Multihit":
                                    spell_cost_3 -= 5;
                                    break;
                                case "Dagger Proficiency II":
                                    raw_Damage += 5;
                                    break;
                                case "Cheaper Smoke Bomb":
                                    spell_cost_4 -= 5;
                                    break;
                                case "Surprise Strike":
                                    if (damage_Boosts.getBox().get(10).isSelected()) {
                                        if (tb[0]) { //Ambush
                                            multiple *= 2.2F;
                                        } else {
                                            multiple *= 1.8F;
                                        }
                                    }
                                    break;
                                case "Delirious Gas":
                                    if (damage_Boosts.getBox().get(11).isSelected()) {
                                        multiple *= 1.4F;
                                    }
                                    break;
                                case "Flow State":
                                    if (damage_Boosts.getBox().get(14).isSelected()) {
                                        multiple *= 1.5F;
                                    }
                                    break;
                                case "Echo":
                                    float echo = 0.35F;
                                    if (tb[1]) echo += 0.15F;
                                    if (tb[2]) echo += 0.1F;
                                    if (damage_Boosts.getSlider().get(3).getValue() > 0) {
                                        multiple *= (1F + damage_Boosts.getSlider().get(3).getValue()) * echo;
                                    }
                                    break;
                                case "Parry":
                                    if (damage_Boosts.getBox().get(15).isSelected()) {
                                        multiple *= 1.3F;
                                    }
                                    break;
                                case "Nightcloak Knife":
                                    if (damage_Boosts.getSlider().get(4).getValue() > 0) {
                                        multiple *= 1F + (damage_Boosts.getSlider().get(4).getValue() * 0.06F);
                                    }
                                    break;
                            }
                            break;
                        }
                        case "archer": {
                            switch (tcb.getAPIName()) {
                                case "arrowBombCost1":
                                    spell_cost_3 -= 10;
                                    break;
                                case "arrowBombCost2":
                                    spell_cost_3 -= 5;
                                    break;
                            }
                            switch (tcb.getFixedTreeName()) {
                                case "Bow Proficiency I":
                                    proficiency += 0.05F;
                                    break;
                                case "Cheaper Escape":
                                    spell_cost_2 -= 5;
                                    break;
                                case "Cheaper Arrow Storm":
                                    spell_cost_1 -= 5;
                                    break;
                                case "Mana Trap":
                                    spell_cost_3 += 10;
                                    break;
                                case "Cheaper Arrow Shield":
                                    spell_cost_4 -= 5;
                                    break;
                                case "Focus":
                                    if (damage_Boosts.getSlider().get(5).getValue() > 0)
                                        multiple *= 1F + damage_Boosts.getSlider().get(5).getValue() * 0.15F;
                                    break;
                                case "Initiator":
                                    if (damage_Boosts.getBox().get(17).isSelected()) {
                                        multiple *= 1.6F;
                                    }
                                    break;
                            }
                            break;
                        }
                        case "mage": {
                            switch (tcb.getAPIName()) {
                                case "meteorCost1":
                                    spell_cost_3 -= 10;
                                    break;
                                case "meteorCost2":
                                    spell_cost_3 -= 5;
                                    break;
                            }
                            switch (tcb.getFixedTreeName()) {
                                case "Wand Proficiency I":
                                case "Wand Proficiency II":
                                    proficiency += 0.05F;
                                    break;
                                case "Cheaper Teleport":
                                    spell_cost_2 -= 5;
                                    break;
                                case "Cheaper Heal":
                                    spell_cost_1 -= 5;
                                    break;
                                case "Cheaper Ice Snake":
                                    spell_cost_4 -= 5;
                                    break;
                                case "Ophanim":
                                    spell_cost_3 += 30;
                                    break;
                            }
                            break;
                        }
                        case "shaman": {
                            switch (tcb.getAPIName()) {
                                case "totemCost1":
                                    spell_cost_1 -= 10;
                                    break;
                                case "totemCost2":
                                    spell_cost_1 -= 5;
                                    break;
                            }
                            switch (tcb.getFixedTreeName()) {
                                case "Relik Proficiency I":
                                    proficiency += 0.05F;
                                    break;
                                case "Cheaper Haul":
                                    spell_cost_2 -= 5;
                                    break;
                                case "Cheaper Uproot":
                                    spell_cost_4 -= 5;
                                    break;
                                case "Cheaper Aura":
                                    spell_cost_3 -= 5;
                                    break;
                                case "Mask of the Lunatic":
                                    if (damage_Boosts.getBox().get(18).isSelected()) {
                                        multiple *= 1.35F;
                                    }
                                    break;
                                case "Mask of the Fanatic":
                                    if (damage_Boosts.getBox().get(19).isSelected()) {
                                        multiple *= 0.8F;
                                    }
                                    break;
                                case "Mask of the Awakened":
                                    if (damage_Boosts.getBox().get(25).isSelected()) {
                                        multiple *= 1.35F;
                                    }
                                    break;
                            }
                            break;
                        }
                    }
                }
            }

            melee_multiple *= proficiency; //Melee Multiple * Proficiency (5% or 10%)
            id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_SPELL_DAMAGE)] += raw_Damage; //Raw Spell Damage += Raw Damage
            id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_MELEE_DAMAGE)] += raw_Damage; //Raw Melee Damage += Raw Damage

            Calc_Raw calc_raw = new Calc_Raw(neutral_min, earth_min, thunder_min, water_min, fire_min, air_min, neutral_max, earth_max, thunder_max, water_max, fire_max, air_max);

            float intelligence = 1F - sp.getSkillPoint(SkillPoint.SkillPointType.INTELLIGENCE).getSPBoost();
            boolean[] tbd = new boolean[] {
                    false, false, false, false, false, false, false, false, false, false, //0 ~ 9
                    false, false, false, false, false, false, false, false, false, false, //10 ~ 19
                    false, false, false, false, false, false, false, false, false, false, //20 ~ 29
                    false, false, false, false, false, false, false, false, false, false //30 ~ 39
            };
            for (TreeCheckBox t : tree.getTcb()) {
                if (t.isSelected()) {
                    switch (tree.getClasses()) {
                        case "warrior": {
                            switch (t.getFixedTreeName()) {
                                case "Bash":
                                    tbd[0] = true;
                                    break;
                                case "Double Bash":
                                    tbd[1] = true;
                                    break;
                                case "Charge":
                                    tbd[2] = true;
                                    break;
                                case "Uppercut":
                                    tbd[3] = true;
                                    break;
                                case "War Scream":
                                    tbd[4] = true;
                                    break;
                                case "Heavy Impact":
                                    tbd[5] = true;
                                    break;
                                case "Quadruple Bash":
                                    tbd[6] = true;
                                    break;
                                case "Fireworks":
                                    tbd[7] = true;
                                    break;
                                case "Flyby Jab":
                                    tbd[8] = true;
                                    break;
                                case "Flaming Uppercut":
                                    tbd[9] = true;
                                    break;
                                case "Half-Moon Swipe":
                                    tbd[10] = true;
                                    break;
                                case "Iron Lungs":
                                    tbd[11] = true;
                                    break;
                                case "Air Shout":
                                    tbd[12] = true;
                                    break;
                                case "Counter":
                                    tbd[13] = true;
                                    break;
                                case "Flying Kick":
                                    tbd[14] = true;
                                    break;
                                case "Boiling Blood":
                                    tbd[15] = true;
                                    break;
                                case "Stronger Bash":
                                    tbd[16] = true;
                                    break;
                                case "Collide":
                                    tbd[17] = true;
                                    break;
                                case "Comet":
                                    tbd[18] = true;
                                    break;
                                case "Whirlwind Strike":
                                    tbd[19] = true;
                                    break;
                                case "Shield Strike":
                                    tbd[20] = true;
                                    break;
                                case "Sparkling Hope":
                                    tbd[21] = true;
                                    break;
                                case "Tempest":
                                    tbd[22] = true;
                                    break;
                                case "Axe Kick":
                                    tbd[23] = true;
                                    break;
                                case "Cyclone":
                                    tbd[24] = true;
                                    break;
                                case "Thunderclap":
                                    tbd[25] = true;
                                    break;
                            }
                            break;
                        }
                        case "assassin": {
                            switch (t.getFixedTreeName()) {
                                case "Spin Attack":
                                    tbd[0] = true;
                                    break;
                                case "Double Spin":
                                    tbd[1] = true;
                                    break;
                                case "Dash":
                                    tbd[2] = true;
                                    break;
                                case "Double Slice":
                                    tbd[3] = true;
                                    break;
                                case "Smoke Bomb":
                                    tbd[4] = true;
                                    break;
                                case "Multihit":
                                    tbd[5] = true;
                                    break;
                                case "Backstab":
                                    tbd[6] = true;
                                    break;
                                case "Fatality":
                                    tbd[7] = true;
                                    break;
                                case "Sticky Bomb":
                                    tbd[8] = true;
                                    break;
                                case "Lacerate":
                                    tbd[9] = true;
                                    break;
                                case "Wall of Smoke":
                                    tbd[10] = true;
                                    break;
                                case "Bamboozle":
                                    tbd[11] = true;
                                    break;
                                case "Blazing Powder":
                                    tbd[12] = true;
                                    break;
                                case "Violent Vortex":
                                    tbd[13] = true;
                                    break;
                                case "Stronger Multihit":
                                    tbd[14] = true;
                                    break;
                                case "Shurikens":
                                    tbd[15] = true;
                                    break;
                                case "Dancing Blade":
                                    tbd[16] = true;
                                    break;
                                case "Fatal Spin":
                                    tbd[17] = true;
                                    break;
                                case "Pirouette":
                                    tbd[18] = true;
                                    break;
                                case "Stronger Lacerate":
                                    tbd[19] = true;
                                    break;
                                case "Stronger Vortex":
                                    tbd[20] = true;
                                    break;
                                case "Blade Fury":
                                    tbd[21] = true;
                                    break;
                                case "Jasmine Bloom":
                                    tbd[22] = true;
                                    break;
                            }
                            break;
                        }
                        case "archer": {
                            switch (t.getFixedTreeName()) {
                                case "Arrow Bomb":
                                    tbd[0] = true;
                                    break;
                                case "Heart Shatter":
                                    tbd[1] = true;
                                    break;
                                case "Double Shots":
                                    tbd[2] = true;
                                    break;
                                case "Escape":
                                    tbd[3] = true;
                                    break;
                                case "Arrow Storm":
                                    tbd[4] = true;
                                    break;
                                case "Arrow Shield":
                                    tbd[5] = true;
                                    break;
                                case "Arrow Rain":
                                    tbd[6] = true;
                                    break;
                                case "Nimble String":
                                    tbd[7] = true;
                                    break;
                                case "Phantom Ray":
                                    tbd[8] = true;
                                    break;
                                case "Fire Creep":
                                    tbd[9] = true;
                                    break;
                                case "Bryophyte Roots":
                                    tbd[10] = true;
                                    break;
                                case "Triple Shots":
                                    tbd[11] = true;
                                    break;
                                case "Guardian Angels":
                                    tbd[12] = true;
                                    break;
                                case "Basaltic Trap":
                                    tbd[13] = true;
                                    break;
                                case "Windstorm":
                                    tbd[14] = true;
                                    break;
                                case "More Shields":
                                    tbd[15] = true;
                                    break;
                                case "Implosion":
                                    tbd[16] = true;
                                    break;
                                case "Refined Gunpowder":
                                    tbd[17] = true;
                                    break;
                                case "Twain's Arc":
                                    tbd[18] = true;
                                    break;
                                case "Fierce Stomp":
                                    tbd[19] = true;
                                    break;
                                case "Scorched Earth":
                                    tbd[20] = true;
                                    break;
                                case "Shocking Bomb":
                                    tbd[21] = true;
                                    break;
                                case "Better Arrow Shield":
                                    tbd[22] = true;
                                    break;
                                case "Escape Artist":
                                    tbd[23] = true;
                                    break;
                                case "Call of the Hound":
                                    tbd[24] = true;
                                    break;
                                case "Arrow Hurricane":
                                    tbd[25] = true;
                                    break;
                                case "Shrapnel Bomb":
                                    tbd[26] = true;
                                    break;
                                case "Geyser Stomp":
                                    tbd[27] = true;
                                    break;
                                case "Crepuscular Ray":
                                    tbd[28] = true;
                                    break;
                                case "Tangled Traps":
                                    tbd[29] = true;
                                    break;
                                case "Minefield":
                                    tbd[30] = true;
                                    break;
                                case "All-Seeing Panoptes":
                                    tbd[31] = true;
                                    break;
                                case "Grape Bomb":
                                    tbd[32] = true;
                                    break;
                                case "Better Guardian Angels":
                                    tbd[33] = true;
                                    break;
                            }
                            break;
                        }
                        case "mage": {
                            switch (t.getFixedTreeName()) {
                                case "Meteor":
                                    tbd[0] = true;
                                    break;
                                case "Teleport":
                                    tbd[1] = true;
                                    break;
                                case "Heal":
                                    tbd[2] = true;
                                    break;
                                case "Ice Snake":
                                    tbd[3] = true;
                                    break;
                                case "Wind Slash":
                                    tbd[4] = true;
                                    break;
                                case "Thunderstorm":
                                    tbd[5] = true;
                                    break;
                                case "Burning Sigil":
                                    tbd[6] = true;
                                    break;
                                case "Sunshower":
                                    tbd[7] = true;
                                    break;
                                case "Stronger Meteor":
                                    tbd[8] = true;
                                    break;
                                case "Windsweeper":
                                    tbd[9] = true;
                                    break;
                                case "Ophanim":
                                    tbd[10] = true;
                                    break;
                                case "Sentient Snake":
                                    tbd[11] = true;
                                    break;
                                case "Breathless":
                                    tbd[12] = true;
                                    break;
                                case "Pyrokinesis":
                                    tbd[13] = true;
                                    break;
                                case "Snake Nest":
                                    tbd[14] = true;
                                    break;
                                case "Transonic Warp":
                                    tbd[15] = true;
                                    break;
                                case "Stronger Ophanim":
                                    tbd[16] = true;
                                    break;
                                case "Lightweaver":
                                    tbd[17] = true;
                                    break;
                                case "Explosive Entrance":
                                    tbd[18] = true;
                                    break;
                                case "Gust":
                                    tbd[19] = true;
                                    break;
                                case "Stronger Sunshower":
                                    tbd[20] = true;
                                    break;
                                case "Divination":
                                    tbd[21] = true;
                                    break;
                            }
                            break;
                        }
                        case "shaman": {
                            switch (t.getFixedTreeName()) {
                                case "Totem":
                                    tbd[0] = true;
                                    break;
                                case "Totemic Smash":
                                    tbd[1] = true;
                                    break;
                                case "Haul":
                                    tbd[2] = true;
                                    break;
                                case "Uproot":
                                    tbd[3] = true;
                                    break;
                                case "Aura":
                                    tbd[4] = true;
                                    break;
                                case "Nature's Jolt":
                                    tbd[5] = true;
                                    break;
                                case "Shocking Aura":
                                    tbd[6] = true;
                                    break;
                                case "Flaming Tongue":
                                    tbd[7] = true;
                                    break;
                                case "Rain Dance":
                                    tbd[8] = true;
                                    break;
                                case "Puppet Master":
                                    tbd[9] = true;
                                    break;
                                case "Exploding Puppets":
                                    tbd[10] = true;
                                    break;
                                case "Double Totem":
                                    tbd[11] = true;
                                    break;
                                case "Storm Dance":
                                    tbd[12] = true;
                                    break;
                                case "Twisted Tether":
                                    tbd[13] = true;
                                    break;
                                case "Stronger Totem":
                                    tbd[14] = true;
                                    break;
                                case "Crimson Effigy":
                                    tbd[15] = true;
                                    break;
                                case "Chant of the Coward":
                                    tbd[16] = true;
                                    break;
                                case "More Effigies":
                                    tbd[17] = true;
                                    break;
                                case "Stronger Tether":
                                    tbd[18] = true;
                                    break;
                                case "Triple Totem":
                                    tbd[19] = true;
                                    break;
                                case "Frog Dance":
                                    tbd[20] = true;
                                    break;
                                case "Blood Sorrow":
                                    tbd[21] = true;
                                    break;
                                case "Hand of the Shaman":
                                    tbd[22] = true;
                                    break;
                                case "Regeneration":
                                    tbd[23] = true;
                                    break;
                                case "Rebound":
                                    tbd[24] = true;
                                    break;
                                case "Sacrificial Shrine":
                                    tbd[25] = true;
                                    break;
                                case "Fluid Healing":
                                    tbd[26] = true;
                                    break;
                                case "Mask of the Lunatic":
                                case "Mask of the Fanatic":
                                case "Mask of the Coward":
                                    tbd[27] = true;
                                    break;
                            }
                            break;
                        }
                    }
                }
            }

            //Display Damages
            totalMaxDamage = new TotalMaxDamage(sp, crit_boost);
            //Powder Specials
            float powder_SP = (percent + melee_percent) * multiple * melee_multiple;
            if (tree.getClasses().equals("assassin") && tb[3]) powder_SP *= 2F; //Satsujin
            if (powder_effects.getPowderPanel("Water").getSpecial() != 0) {
                multiple *= 1.1F + (0.05F * (powder_effects.getPowderPanel("Water").getSpecial() - 1));
            }
            if (powder_effects.getPowderPanel("Air").getSpecial() != 0) {
                multiple *= 2F + (0.25F * (powder_effects.getPowderPanel("Air").getSpecial() - 1));
            }
            if (powder_effects.getPowderPanel("Fire").getSpecial() != 0) {
                multiple *= 1.1F + (0.025F * (powder_effects.getPowderPanel("Fire").getSpecial() - 1));
                list.add(new Damage_Template("Fire Powder Special", 0, pane, null, sp, crit_boost, false));
                SpellEnum fire = SpellEnum.FIRE_1;
                switch (powder_effects.getPowderPanel("Fire").getSpecial()) {
                    case 2:
                        fire = SpellEnum.FIRE_2;
                        break;
                    case 3:
                        fire = SpellEnum.FIRE_3;
                        break;
                    case 4:
                        fire = SpellEnum.FIRE_4;
                        break;
                    case 5:
                        fire = SpellEnum.FIRE_5;
                        break;
                }
                calcMelee("", 0, calc_raw, list, powder_SP, id_Numbers, set_Damage_Percent(fire), atkSpd, sp, false, false, true);
            }
            if (powder_effects.getPowderPanel("Earth").getSpecial() != 0) {
                Damage_Template previous = null;
                if (list.size() > 0) previous = list.get(list.size() - 1);
                list.add(new Damage_Template("Earth Powder Special", 0, pane, previous, sp, crit_boost, false));
                SpellEnum earth = SpellEnum.EARTH_1;
                switch (powder_effects.getPowderPanel("Earth").getSpecial()) {
                    case 2:
                        earth = SpellEnum.EARTH_2;
                        break;
                    case 3:
                        earth = SpellEnum.EARTH_3;
                        break;
                    case 4:
                        earth = SpellEnum.EARTH_4;
                        break;
                    case 5:
                        earth = SpellEnum.EARTH_5;
                        break;
                }
                calcMelee("", list.size() - 1, calc_raw, list, powder_SP, id_Numbers, set_Damage_Percent(earth), atkSpd, sp, false, false, true);
            }
            if (powder_effects.getPowderPanel("Thunder").getSpecial() != 0) {
                Damage_Template previous = null;
                if (list.size() > 0) previous = list.get(list.size() - 1);
                list.add(new Damage_Template("Thunder Powder Special", 0, pane, previous, sp, crit_boost, false));
                SpellEnum thunder = SpellEnum.THUNDER_1;
                switch (powder_effects.getPowderPanel("Thunder").getSpecial()) {
                    case 2:
                        thunder = SpellEnum.THUNDER_2;
                        break;
                    case 3:
                        thunder = SpellEnum.THUNDER_3;
                        break;
                    case 4:
                        thunder = SpellEnum.THUNDER_4;
                        break;
                    case 5:
                        thunder = SpellEnum.THUNDER_5;
                        break;
                }
                calcMelee("", list.size() - 1, calc_raw, list, powder_SP, id_Numbers, set_Damage_Percent(thunder), atkSpd, sp, false, false, true);
            }

            //Melee
            Damage_Template previousM = null;
            if (list.size() > 0) previousM = list.get(list.size() - 1);
            list.add(new Damage_Template("Melee", 0, pane, previousM, sp, crit_boost, true));
            float[] melee = set_Damage_Percent(SpellEnum.MELEE);

            switch (tree.getClasses()) {
                case "warrior": {
                    //Melee
                    calcMelee("DPS", list.size() - 1, calc_raw, list, (percent + melee_percent) * multiple * melee_multiple, id_Numbers, melee, atkSpd, sp, false, true, false);
                    calcMelee("Single Hit", list.size() - 1, calc_raw, list, (percent + melee_percent) * multiple * melee_multiple, id_Numbers, melee, atkSpd, sp, true, false, false);

                    //Bash
                    if (tbd[0]) {
                        list.add(new Damage_Template("Bash", calc_Spell_Cost(40 + spell_cost_1, intelligence, id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_1ST_SPELL_COST)], id_Numbers[ID_Display.ID_INT.get(Identifications.PERCENT_1ST_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
                        float[] bash = set_Damage_Percent(SpellEnum.BASH);
                        if (tbd[1]) add_Damage_Percent(bash, SpellEnum.DOUBLE_BASH);
                        if (tbd[6]) add_Damage_Percent(bash, SpellEnum.QUADRUPLE_BASH);
                        if (tbd[16]) add_Damage_Percent(bash, SpellEnum.STRONGER_BASH);
                        if (tbd[25]) add_Damage_Percent(bash, SpellEnum.THUNDERCLAP);
                        calcSpell("Single Hit", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, bash, sp, false, false);
                        calcSpell("Total Damage", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, calc_Total_Damage_Percent(bash), sp, true, false);
                    }

                    //Charge
                    if (tbd[2]) {
                        list.add(new Damage_Template("Charge", calc_Spell_Cost(25 + spell_cost_2, intelligence, id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_2ND_SPELL_COST)], id_Numbers[ID_Display.ID_INT.get(Identifications.PERCENT_2ND_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, false));
                        if (tbd[5]) calcSpell("Heavy Impact", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, set_Damage_Percent(SpellEnum.HEAVY_IMPACT), sp, false, false);
                        if (tbd[8]) calcSpell("Flyby Jab", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, set_Damage_Percent(SpellEnum.FLYBY_JAB), sp, false, false);
                        if (tbd[14]) calcSpell("Flying Kick", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, set_Damage_Percent(SpellEnum.FLYING_KICK), sp, false, false);
                        if (tbd[17]) calcSpell("Collide", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, set_Damage_Percent(SpellEnum.COLLIDE), sp, false, false);
                    }

                    //Uppercut
                    if (tbd[3]) {
                        list.add(new Damage_Template("Uppercut", calc_Spell_Cost(40 + spell_cost_3, intelligence, id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_3RD_SPELL_COST)], id_Numbers[ID_Display.ID_INT.get(Identifications.PERCENT_3RD_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
                        float[] uppercut = set_Damage_Percent(SpellEnum.UPPERCUT);
                        if (tbd[10]) add_Damage_Percent(uppercut, SpellEnum.HALF_MOON_SWIPE);
                        if (tbd[19]) add_Damage_Percent(uppercut, SpellEnum.WHIRLWIND_STRIKE);
                        if (tbd[23]) add_Damage_Percent(uppercut, SpellEnum.AXE_KICK);
                        calcSpell("Uppercut", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, uppercut, sp, false, false);
                        if (tbd[7]) {
                            add_Damage_Percent(uppercut, SpellEnum.FIREWORKS);
                            calcSpell("Fireworks", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, set_Damage_Percent(SpellEnum.FIREWORKS), sp, false, false);
                        }
                        if (tbd[18]) {
                            add_Damage_Percent(uppercut, SpellEnum.COMET);
                            calcSpell("Comet", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, set_Damage_Percent(SpellEnum.COMET), sp, false, false);
                        }
                        calcSpell("Total Damage", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, uppercut, sp, true, false);
                    }

                    //War Scream
                    if (tbd[4]) {
                        list.add(new Damage_Template("War Scream", calc_Spell_Cost(30 + spell_cost_4, intelligence, id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_4TH_SPELL_COST)], id_Numbers[ID_Display.ID_INT.get(Identifications.PERCENT_4TH_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, false));
                        float[] war_scream = set_Damage_Percent(SpellEnum.WAR_SCREAM);
                        float[] air_shout = set_Damage_Percent(SpellEnum.AIR_SHOUT);
                        if (tbd[11]) {
                            add_Damage_Percent(war_scream, SpellEnum.IRON_LUNGS_FOR_WAR_SCREAM);
                            add_Damage_Percent(air_shout, SpellEnum.IRON_LUNGS_FOR_AIR_SHOUT);
                        }
                        calcSpell("War Scream", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, war_scream, sp, false, false);
                        if (tbd[12]) calcSpell("Air Shout", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, air_shout, sp, false, false);
                        if (tbd[22]) {
                            float[] tempest = set_Damage_Percent(SpellEnum.TEMPEST);
                            calcSpell("Single Tempest", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, tempest, sp, false, false);
                            calcSpell("Total Tempest", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, calc_Total_Damage_Percent(tempest), sp, false, false);
                        }
                    }

                    //Flaming Uppercut
                    if (tbd[9]) {
                        list.add(new Damage_Template("Flaming Uppercut", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
                        float[] flaming_Uppercut = set_Damage_Percent(SpellEnum.FLAMING_UPPERCUT);
                        calcSpell("DPS", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, calc_Total_Damage_Percent_Manual(flaming_Uppercut, 1.66F), sp, false, false);
                        calcSpell("Single Hit", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, flaming_Uppercut, sp, false, false);
                        calcSpell("Total Damage", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, calc_Total_Damage_Percent(flaming_Uppercut), sp, true, false);
                    }
                    //Counter
                    if (tbd[13]) {
                        list.add(new Damage_Template("Counter", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
                        calcSpell("", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, set_Damage_Percent(SpellEnum.COUNTER), sp, false, false);
                    }
                    //Boiling Blood
                    if (tbd[15]) {
                        list.add(new Damage_Template("Boiling Blood", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
                        float[] boiling_Blood = set_Damage_Percent(SpellEnum.BOILING_BLOOD);
                        calcSpell("DPS", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, calc_Total_Damage_Percent_Manual(boiling_Blood, 2.5F), sp, false, false);
                        calcSpell("Single Hit", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, boiling_Blood, sp, false, false);
                        calcSpell("Total Damage", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, calc_Total_Damage_Percent(boiling_Blood), sp, true, false);
                    }
                    //Sparkling Hope
                    if (tbd[21]) {
                        list.add(new Damage_Template("Sparkling Hope", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
                        calcSpell("", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, set_Damage_Percent(SpellEnum.SPARKLING_HOPE), sp, false, false);
                    }
                    //Cyclone
                    if (tbd[24]) {
                        list.add(new Damage_Template("Cyclone", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
                        float[] cyclone = set_Damage_Percent(SpellEnum.CYCLONE);
                        calcSpell("DPS", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, calc_Total_Damage_Percent_Manual(cyclone, 2F), sp, false, false);
                        calcSpell("Single Hit", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, cyclone, sp, false, false);
                        calcSpell("Total Damage", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, calc_Total_Damage_Percent(cyclone), sp, true, false);
                    }
                    //Shield Strike
                    if (tbd[20]) {
                        list.add(new Damage_Template("Shield Strike", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
                        calcSpell("", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, set_Damage_Percent(SpellEnum.SHIELD_STRIKE), sp, false, false);
                    }
                    break;
                }
                case "assassin": {
                    //Melee
                    String melee_Name = "Single Hit";
                    boolean isSingleHit = true;
                    if (tbd[3]) add_Damage_Percent(melee, SpellEnum.DOUBLE_SLICE);
                    calcMelee("DPS", list.size() - 1, calc_raw, list, (percent + melee_percent) * multiple * melee_multiple, id_Numbers, calc_Total_Damage_Percent(melee), atkSpd, sp, false, true, false);
                    if (tbd[3]) { //Double Slice
                        melee_Name = "Total Damage";
                        isSingleHit = false;
                        calcMelee("Single Hit", list.size() - 1, calc_raw, list, (percent + melee_percent) * multiple * melee_multiple, id_Numbers, melee, atkSpd, sp, false, false, true);
                    }
                    calcMelee(melee_Name, list.size() - 1, calc_raw, list, (percent + melee_percent) * multiple * melee_multiple, id_Numbers, calc_Total_Damage_Percent(melee), atkSpd, sp, true, false, isSingleHit);

                    //Spin Attack
                    if (tbd[0]) {
                        if (tbd[9]) {
                            //Lacerate
                            list.add(new Damage_Template("Lacerate", calc_Spell_Cost(40 + spell_cost_1, intelligence, id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_1ST_SPELL_COST)], id_Numbers[ID_Display.ID_INT.get(Identifications.PERCENT_1ST_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
                            float[] lacerate = set_Damage_Percent(SpellEnum.LACERATE);
                            if (tbd[12]) add_Damage_Percent(lacerate, SpellEnum.BLAZING_POWDER);
                            if (tbd[19]) add_Damage_Percent(lacerate, SpellEnum.STRONGER_LACERATE);
                            calcSpell("Single Hit", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, lacerate, sp, false, true);
                            calcSpell("Total Damage", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, calc_Total_Damage_Percent(lacerate), sp, true, false);
                        } else {
                            //Spin Attack
                            list.add(new Damage_Template("Spin Attack", calc_Spell_Cost(40 + spell_cost_1, intelligence, id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_1ST_SPELL_COST)], id_Numbers[ID_Display.ID_INT.get(Identifications.PERCENT_1ST_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
                            float[] spin_Attack = set_Damage_Percent(SpellEnum.SPIN_ATTACK);
                            if (tbd[1]) add_Damage_Percent(spin_Attack, SpellEnum.DOUBLE_SPIN);
                            if (tbd[12]) add_Damage_Percent(spin_Attack, SpellEnum.BLAZING_POWDER);
                            if (tbd[17]) add_Damage_Percent(spin_Attack, SpellEnum.FATAL_SPIN);
                            calcSpell("Single Hit", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, spin_Attack, sp, false, true);
                            calcSpell("Total Damage", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, calc_Total_Damage_Percent(spin_Attack), sp, true, false);
                        }
                    }

                    //Dash
                    if (tbd[2]) {
                        list.add(new Damage_Template("Dash", calc_Spell_Cost(20 + spell_cost_2, intelligence, id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_2ND_SPELL_COST)], id_Numbers[ID_Display.ID_INT.get(Identifications.PERCENT_2ND_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, false));
                        if (tbd[16]) {
                            float[] dancing_Blade = set_Damage_Percent(SpellEnum.DANCING_BLADE);
                            if (tbd[18]) add_Damage_Percent(dancing_Blade, SpellEnum.PIROUETTE);
                            calcSpell("Dancing Blade", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, dancing_Blade, sp, false, true);
                        }
                    }

                    //Multihit
                    if (tbd[5]) {
                        float multihit_Boost = percent * multiple;
                        if (tb[3]) multihit_Boost *= 2F; //Satsujin
                        if (tbd[6]) {
                            if (damage_Boosts.getBox().get(9).isSelected()) multihit_Boost *= 2F;
                            //Backstab
                            list.add(new Damage_Template("Backstab", calc_Spell_Cost(40 + spell_cost_3, intelligence, id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_3RD_SPELL_COST)], id_Numbers[ID_Display.ID_INT.get(Identifications.PERCENT_3RD_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, false));
                            calcSpell("Backstab", list.size() - 1, calc_raw, list, multihit_Boost, id_Numbers, set_Damage_Percent(SpellEnum.BACKSTAB), sp, false, true);
                        } else {
                            //Multihit
                            list.add(new Damage_Template("Multihit", calc_Spell_Cost(40 + spell_cost_3, intelligence, id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_3RD_SPELL_COST)], id_Numbers[ID_Display.ID_INT.get(Identifications.PERCENT_3RD_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
                            float[] multihit = set_Damage_Percent(SpellEnum.MULTIHIT);
                            if (tbd[14]) add_Damage_Percent(multihit, SpellEnum.STRONGER_MULTIHIT);
                            if (tbd[21]) add_Damage_Percent(multihit, SpellEnum.BLADE_FURY);
                            calcSpell("Single Hit", list.size() - 1, calc_raw, list, multihit_Boost, id_Numbers, multihit, sp, false, true);
                            multihit = calc_Total_Damage_Percent(multihit);
                            if (tbd[7]) {
                                add_Damage_Percent(multihit, SpellEnum.FATALITY);
                                calcSpell("Fatality", list.size() - 1, calc_raw, list, multihit_Boost, id_Numbers, set_Damage_Percent(SpellEnum.FATALITY), sp, false, true);
                            }
                            calcSpell("Total Damage", list.size() - 1, calc_raw, list, multihit_Boost, id_Numbers, multihit, sp, true, false);
                        }
                    }

                    //Smoke Bomb
                    if (tbd[4]) {
                        list.add(new Damage_Template("Smoke Bomb", calc_Spell_Cost(35 + spell_cost_4, intelligence, id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_4TH_SPELL_COST)], id_Numbers[ID_Display.ID_INT.get(Identifications.PERCENT_4TH_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
                        float[] smoke_Bomb = set_Damage_Percent(SpellEnum.SMOKE_BOMB);
                        if (tbd[8]) add_Damage_Percent(smoke_Bomb, SpellEnum.STICKY_BOMB);
                        if (tbd[10]) {
                            add_Damage_Percent(smoke_Bomb, SpellEnum.WALL_OF_SMOKE);
                            calcSpell("DPS", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, calc_Total_Damage_Percent_Manual(smoke_Bomb, 6F), sp, false, false);
                        } else {
                            calcSpell("DPS", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, calc_Total_Damage_Percent_Manual(smoke_Bomb, 2F), sp, false, false);
                        }
                        calcSpell("Single Hit", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, smoke_Bomb, sp, false, true);
                        calcSpell("Total Damage", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, calc_Total_Damage_Percent(smoke_Bomb), sp, true, false);
                    }

                    //Bamboozle
                    if (tbd[11]) {
                        list.add(new Damage_Template("Bamboozle", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
                        calcSpell("", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, set_Damage_Percent(SpellEnum.BAMBOOZLE), sp, false, true);
                    }

                    //Shurikens
                    if (tbd[15]) {
                        list.add(new Damage_Template("Shuriken", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
                        calcSpell("Single Hit", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, set_Damage_Percent(SpellEnum.SHURIKENS), sp, false, true);
                    }

                    //Jasmine Bloom
                    if (tbd[22]) {
                        list.add(new Damage_Template("Jasmine Bloom", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
                        float[] jasmine_Bloom = set_Damage_Percent(SpellEnum.JASMINE_BLOOM);
                        calcSpell("DPS", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, calc_Total_Damage_Percent_Manual(jasmine_Bloom, 3.33F), sp, false, false);
                        calcSpell("Single Hit", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, jasmine_Bloom, sp, false, true);
                    }

                    //Violent Vortex
                    if (tbd[13]) {
                        list.add(new Damage_Template("Violent Vortex", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
                        int total_health = id_Numbers[ID_Display.ID_INT.get(Identifications.HEALTH)] + id_Numbers[ID_Display.ID_INT.get(Identifications.HEALTH_BONUS)];
                        int max_Damage = totalMaxDamage.getMaxDamage();
                        if (tbd[20]) { //Stronger Vortex
                            if (max_Damage > Math.round(total_health * 2.5F)) {
                                list.get(list.size() - 1).addDamage(total_health, max_Damage * 0.4F);
                            } else {
                                list.get(list.size() - 1).addDamage(0, 0);
                            }
                        } else {
                            if (max_Damage > Math.round(total_health * 1.5F)) {
                                list.get(list.size() - 1).addDamage(total_health * 0.45F, max_Damage);
                            } else {
                                list.get(list.size() - 1).addDamage(0, 0);
                            }
                        }
                    }
                    break;
                }
                case "archer": {
                    //Melee
                    String melee_Name = "Single Hit";
                    if (tbd[2]) add_Damage_Percent(melee, SpellEnum.DOUBLE_SHOTS);
                    if (tbd[11]) add_Damage_Percent(melee, SpellEnum.TRIPLE_SHOTS);
                    calcMelee("DPS", list.size() - 1, calc_raw, list, (percent + melee_percent) * multiple * melee_multiple, id_Numbers, calc_Total_Damage_Percent(melee), atkSpd, sp, false, true, false);
                    if (tbd[2] || tbd[11]) {
                        melee_Name = "Total Damage";
                        calcMelee("Single Hit", list.size() - 1, calc_raw, list, (percent + melee_percent) * multiple * melee_multiple, id_Numbers, melee, atkSpd, sp, false, false, false);
                    }
                    calcMelee(melee_Name, list.size() - 1, calc_raw, list, (percent + melee_percent) * multiple * melee_multiple, id_Numbers, calc_Total_Damage_Percent(melee), atkSpd, sp, true, false, false);

                    //Arrow Storm
                    if (tbd[8]) {
                        //Phantom Ray
                        list.add(new Damage_Template("Phantom Ray", calc_Spell_Cost(35 + spell_cost_1, intelligence, id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_1ST_SPELL_COST)], id_Numbers[ID_Display.ID_INT.get(Identifications.PERCENT_1ST_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
                        float[] phantom_Ray = set_Damage_Percent(SpellEnum.PHANTOM_RAY);
                        float phantom_Boost = percent * multiple;
                        if (tb[1] && damage_Boosts.getSlider().get(7).getValue() > 0) phantom_Boost *= 1F + (0.1F * damage_Boosts.getSlider().get(7).getValue()); //Decimator
                        calcSpell("Single Hit", list.size() - 1, calc_raw, list, phantom_Boost, id_Numbers, phantom_Ray, sp, false, false);
                        calcSpell("Total Damage", list.size() - 1, calc_raw, list, phantom_Boost, id_Numbers, calc_Total_Damage_Percent(phantom_Ray), sp, true, false);
                    } else {
                        //Arrow Storm
                        list.add(new Damage_Template("Arrow Storm", calc_Spell_Cost(35 + spell_cost_1, intelligence, id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_1ST_SPELL_COST)], id_Numbers[ID_Display.ID_INT.get(Identifications.PERCENT_1ST_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
                        float[] arrow_Storm = set_Damage_Percent(SpellEnum.ARROW_STORM);
                        int stream = 1;
                        if (tbd[7]) add_Damage_Percent(arrow_Storm, SpellEnum.NIMBLE_STRING);
                        if (tbd[14]) {
                            add_Damage_Percent(arrow_Storm, SpellEnum.WINDSTORM);
                            stream = 2;
                        }
                        if (tbd[25]) {
                            add_Damage_Percent(arrow_Storm, SpellEnum.ARROW_HURRICANE);
                            stream = 3;
                        }
                        calcSpell("Single Hit", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, arrow_Storm, sp, false, false);
                        calcSpell("Total Damage", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, calc_Total_Damage_Percent_Manual(calc_Total_Damage_Percent(arrow_Storm), stream), sp, true, false);
                    }

                    //Escape
                    if (tbd[3]) {
                        list.add(new Damage_Template("Escape", calc_Spell_Cost(20 + spell_cost_2, intelligence, id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_2ND_SPELL_COST)], id_Numbers[ID_Display.ID_INT.get(Identifications.PERCENT_2ND_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, false));
                        if (tbd[19]) {
                            //Fierce Stomp
                            float[] fierce_Stomp = set_Damage_Percent(SpellEnum.FIERCE_STOMP);
                            if (tbd[27]) add_Damage_Percent(fierce_Stomp, SpellEnum.GEYSER_STOMP);
                            calcSpell("Fierce Stomp", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, fierce_Stomp, sp, false, false);
                        }
                        if (tbd[23]) calcSpell("Escape Artist", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, set_Damage_Percent(SpellEnum.ESCAPE_ARTIST), sp, false, false);
                    }

                    //Arrow Bomb
                    if (tbd[0]) {
                        list.add(new Damage_Template("Arrow Bomb", calc_Spell_Cost(45 + spell_cost_3, intelligence, id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_3RD_SPELL_COST)], id_Numbers[ID_Display.ID_INT.get(Identifications.PERCENT_3RD_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
                        float[] arrow_Bomb = set_Damage_Percent(SpellEnum.ARROW_BOMB);
                        if (tbd[17]) add_Damage_Percent(arrow_Bomb, SpellEnum.REFINED_GUNPOWDER);
                        if (tbd[21]) add_Damage_Percent(arrow_Bomb, SpellEnum.SHOCKING_BOMB);
                        calcSpell("Arrow Bomb", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, arrow_Bomb, sp, false, false);
                        if (tbd[1]) {
                            //Heart Shatter
                            float[] heart_Shatter = set_Damage_Percent(SpellEnum.HEART_SHATTER);
                            if (tbd[16]) add_Damage_Percent(heart_Shatter, SpellEnum.IMPLOSION);
                            add_Damage_Percent(arrow_Bomb, heart_Shatter);
                            calcSpell("Heart Shatter", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, heart_Shatter, sp, false, false);
                        }
                        if (tbd[26]) {
                            //Shrapnel Bomb
                            float[] shrapnel_Bomb = set_Damage_Percent(SpellEnum.SHRAPNEL_BOMB);
                            add_Damage_Percent(arrow_Bomb, shrapnel_Bomb);
                            calcSpell("Shrapnel Bomb Single", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, shrapnel_Bomb, sp, false, false);
                            calcSpell("Shrapnel Bomb Total", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, calc_Total_Damage_Percent(shrapnel_Bomb), sp, false, false);
                        }
                        if (tbd[32]){
                            //Grape Bomb
                            float[] grape_Bomb = set_Damage_Percent(SpellEnum.GRAPE_BOMB);
                            add_Damage_Percent(arrow_Bomb, grape_Bomb);
                            calcSpell("Grape Bomb Single", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, grape_Bomb, sp, false, false);
                            calcSpell("Grape Bomb Total", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, calc_Total_Damage_Percent(grape_Bomb), sp, false, false);
                        }
                        calcSpell("Total Damage", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, arrow_Bomb, sp, true, false);
                        calcSpell("Self Damage", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, set_Damage_Percent(SpellEnum.ARROW_BOMB_SELF_DAMAGE), sp, false, false);
                    }

                    //Arrow Shield
                    if (tbd[5]) {
                        if (tbd[12]) {
                            //Guardian Angels
                            list.add(new Damage_Template("Guardian Angels", calc_Spell_Cost(30 + spell_cost_4, intelligence, id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_4TH_SPELL_COST)], id_Numbers[ID_Display.ID_INT.get(Identifications.PERCENT_4TH_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
                            float[] guardian_Angels = set_Damage_Percent(SpellEnum.GUARDIAN_ANGELS);
                            int times = 8;
                            if (tbd[15]) add_Damage_Percent(guardian_Angels, SpellEnum.MORE_SHIELDS_GUARDIAN_ANGELS);
                            if (tbd[22]) add_Damage_Percent(guardian_Angels, SpellEnum.BETTER_ARROW_SHIELD_GUARDIAN_ANGELS);
                            if (tbd[33]) times += 4;
                            if (tbd[31]){ //All-Seeing Panoptes
                                times += 4;
                                add_Damage_Percent(guardian_Angels, SpellEnum.ALL_SEEING_PANOPTES);
                                calcSpell("DPS", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, calc_Total_Damage_Percent_Manual(calc_Total_Damage_Percent(guardian_Angels), 2F), sp, false, false);
                            } else {
                                calcSpell("DPS", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, calc_Total_Damage_Percent_Manual(calc_Total_Damage_Percent(guardian_Angels), 1.66F), sp, false, false);
                            }
                            calcSpell("Single Hit", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, guardian_Angels, sp, false, false);
                            calcSpell("Total Damage", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, calc_Total_Damage_Percent_Manual(calc_Total_Damage_Percent(guardian_Angels), times), sp, true, false);
                        } else {
                            //Arrow Shield
                            list.add(new Damage_Template("Arrow Shield", calc_Spell_Cost(30 + spell_cost_4, intelligence, id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_4TH_SPELL_COST)], id_Numbers[ID_Display.ID_INT.get(Identifications.PERCENT_4TH_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
                            float[] arrow_Shield = set_Damage_Percent(SpellEnum.ARROW_SHIELD);
                            if (tbd[15]) add_Damage_Percent(arrow_Shield, SpellEnum.MORE_SHIELDS_ARROW_SHIELD);
                            if (tbd[22]) add_Damage_Percent(arrow_Shield, SpellEnum.BETTER_ARROW_SHIELD_ARROW_SHIELD);
                            calcSpell("Single Hit", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, arrow_Shield, sp, false, false);
                            calcSpell("Total Damage", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, calc_Total_Damage_Percent(arrow_Shield), sp, true, false);
                        }
                    }

                    //Arrow Rain
                    if (tbd[6]) {
                        list.add(new Damage_Template("Arrow Rain", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
                        calcSpell("", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, set_Damage_Percent(SpellEnum.ARROW_RAIN), sp, false, false);
                    }

                    //Fire Creep
                    if (tbd[9]) {
                        list.add(new Damage_Template("Fire Creep", 0, pane, list.get(list.size() - 1), sp, crit_boost, true));
                        float[] fire_Creep = set_Damage_Percent(SpellEnum.FIRE_CREEP);
                        if (tbd[20]) add_Damage_Percent(fire_Creep, SpellEnum.SCORCHED_EARTH);
                        calcSpell("DPS", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, calc_Total_Damage_Percent_Manual(fire_Creep, 2.5F), sp, false, false);
                        calcSpell("Single Hit", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, fire_Creep, sp, false, false);
                        calcSpell("Total Damage", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, calc_Total_Damage_Percent(fire_Creep), sp, true, false);
                    }

                    //Byrophyte Roots
                    if (tbd[10]) {
                        list.add(new Damage_Template("Bryophyte Roots", 0, pane, list.get(list.size() - 1), sp, crit_boost, true));
                        float[] bryophyte_Roots = set_Damage_Percent(SpellEnum.BRYOPHYTE_ROOTS);
                        calcSpell("DPS", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, calc_Total_Damage_Percent_Manual(bryophyte_Roots, 2.5F), sp, false, false);
                        calcSpell("Single Hit", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, bryophyte_Roots, sp, false, false);
                        calcSpell("Total Damage", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, calc_Total_Damage_Percent(bryophyte_Roots), sp, true, false);
                    }

                    //Basaltic Trap
                    if (tbd[13]) {
                        list.add(new Damage_Template("Basaltic Trap", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
                        float[] basaltic_Trap = set_Damage_Percent(SpellEnum.BASALTIC_TRAP);
                        float trap_Boost = percent * multiple;
                        if (tbd[30]) add_Damage_Percent(basaltic_Trap, SpellEnum.MINEFIELD);
                        if (tb[0]) trap_Boost *= 1F + (damage_Boosts.getSlider().get(6).getValue() * 0.01F); //Patient Hunter
                        calcSpell("Single Hit", list.size() - 1, calc_raw, list, trap_Boost, id_Numbers, basaltic_Trap, sp, false, false);
                    }

                    //Twain's Arc
                    if (tbd[18]) {
                        list.add(new Damage_Template("Twain's Arc", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
                        calcMelee("", list.size() - 1, calc_raw, list, (percent + melee_percent) * multiple * melee_multiple, id_Numbers, set_Damage_Percent(SpellEnum.TWAINS_ARC), atkSpd, sp, false, true, false);
                    }

                    //Call of the Hound
                    if (tbd[24]) {
                        list.add(new Damage_Template("Call of the Hound", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
                        calcSpell("Single Hit", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, set_Damage_Percent(SpellEnum.CALL_OF_THE_HOUND), sp, false, false);
                    }

                    //Crepuscular Ray
                    if (tbd[28] && tb[2] && damage_Boosts.getSlider().get(5).getValue() >= 5) {
                        list.add(new Damage_Template("Crepuscular Ray", 0, pane, list.get(list.size() - 1), sp, crit_boost, true));
                        float[] crepuscular_Ray = set_Damage_Percent(SpellEnum.CREPUSCULAR_RAY);
                        calcSpell("DPS", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, calc_Total_Damage_Percent_Manual(crepuscular_Ray, 20F), sp, false, false);
                        calcSpell("Single Hit", list.size() -1, calc_raw, list, percent * multiple, id_Numbers, crepuscular_Ray, sp, false, false);
                        calcSpell("Total Damage", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, calc_Total_Damage_Percent_Manual(crepuscular_Ray, 20 * damage_Boosts.getSlider().get(5).getValue()), sp, true, false);
                    }

                    //Tangled Traps
                    if (tbd[29]) {
                        list.add(new Damage_Template("Tangled Traps", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
                        float[] tangled_Traps = set_Damage_Percent(SpellEnum.TANGLED_TRAPS);
                        calcSpell("DPS", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, calc_Total_Damage_Percent_Manual(tangled_Traps, 5F), sp, false, false);
                        calcSpell("Single Hit", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, tangled_Traps, sp, false, false);
                    }
                    break;
                }
                case "mage": {
                    //Melee
                    calcMelee("DPS", list.size() - 1, calc_raw, list, (percent + melee_percent) * multiple * melee_multiple, id_Numbers, melee, atkSpd, sp, false, true, false);
                    calcMelee("Single Hit", list.size() - 1, calc_raw, list, (percent + melee_percent) * multiple * melee_multiple, id_Numbers, melee, atkSpd, sp, true, false, false);

                    //Heal
                    if (tbd[2]) {
                        list.add(new Damage_Template("Heal", calc_Spell_Cost(35 + spell_cost_1, intelligence, id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_1ST_SPELL_COST)], id_Numbers[ID_Display.ID_INT.get(Identifications.PERCENT_1ST_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, false));
                        if (tb[1]) { //Orphion's Pulse
                            list.get(list.size() - 1).addHeal("Total Heal", id_Numbers, 0.55F, tb[0]);
                            list.get(list.size() - 1).addHeal("First Heal", id_Numbers, 0.15F, tb[0]);
                            list.get(list.size() - 1).addHeal("Second and Third Heal Pulses", id_Numbers, 0.2F, tb[0]);
                        } else {
                            list.get(list.size() - 1).addHeal("Heal", id_Numbers, 0.15F, tb[0]);
                        }

                        if (tbd[7]) {
                            //Sunshower
                            float[] sunshower = set_Damage_Percent(SpellEnum.SUNSHOWER);
                            if (tbd[16]) add_Damage_Percent(sunshower, SpellEnum.STRONGER_SUNSHOWER);
                            calcSpell("Sunshower", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, sunshower, sp, false, false);
                        }
                    }

                    //Teleport
                    if (tbd[1]) {
                        list.add(new Damage_Template("Teleport", calc_Spell_Cost(25 + spell_cost_2, intelligence, id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_2ND_SPELL_COST)], id_Numbers[ID_Display.ID_INT.get(Identifications.PERCENT_2ND_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, false));
                        if (tbd[4]) {
                            //Wind Slash
                            calcSpell("Wind Slash", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, set_Damage_Percent(SpellEnum.WIND_SLASH), sp, false, false);
                        }
                        if (tbd[18]) {
                            //Explosive Entrance
                            calcSpell("Explosive Entrance", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, set_Damage_Percent(SpellEnum.EXPLOSIVE_ENTRANCE), sp, false, false);
                        }
                        if (tbd[15] && tb[2] && damage_Boosts.getSlider().get(8).getValue() > 0) { //Winded
                            //Transonic Warp
                            calcSpell("Transonic Warp", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, calc_Total_Damage_Percent_Manual(set_Damage_Percent(SpellEnum.TRANSONIC_WARP), damage_Boosts.getSlider().get(8).getValue()), sp, false, false);
                        }
                    }

                    //Meteor
                    if (tbd[0]) {
                        if (tbd[10]) {
                            //Ophanim
                            list.add(new Damage_Template("Ophanim", calc_Spell_Cost(50 + spell_cost_3, intelligence, id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_3RD_SPELL_COST)], id_Numbers[ID_Display.ID_INT.get(Identifications.PERCENT_3RD_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, false));
                            float[] ophanim = set_Damage_Percent(SpellEnum.OPHANIM);
                            if (tbd[12] && tb[2] && damage_Boosts.getSlider().get(8).getValue() > 0) add_Damage_Percent(ophanim, calc_Total_Damage_Percent_Manual(set_Damage_Percent(SpellEnum.BREATHLESS_OPHANIM), damage_Boosts.getSlider().get(8).getValue())); //Winded
                            if (tbd[16]) add_Damage_Percent(ophanim, SpellEnum.STRONGER_OPHANIM);
                            if (tbd[21]) add_Damage_Percent(ophanim, SpellEnum.DIVINATION);
                            calcSpell("Single Hit", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, ophanim, sp, false, false);
                        } else {
                            //Meteor
                            list.add(new Damage_Template("Meteor", calc_Spell_Cost(50 + spell_cost_3, intelligence, id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_3RD_SPELL_COST)], id_Numbers[ID_Display.ID_INT.get(Identifications.PERCENT_3RD_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
                            float[] meteor = set_Damage_Percent(SpellEnum.METEOR);
                            if (tbd[8]) add_Damage_Percent(meteor, SpellEnum.STRONGER_METEOR);
                            if (tbd[12] && tb[2] && damage_Boosts.getSlider().get(8).getValue() > 0) add_Damage_Percent(meteor, calc_Total_Damage_Percent_Manual(set_Damage_Percent(SpellEnum.BREATHLESS_METEOR), damage_Boosts.getSlider().get(8).getValue())); //Winded
                            calcSpell("Meteor", list.size() - 1 ,calc_raw, list, percent * multiple, id_Numbers, meteor, sp, false, false);
                            if (tbd[5]) {
                                float[] thunderstorm = set_Damage_Percent(SpellEnum.THUNDERSTORM);
                                if (tbd[12] && tb[2] && damage_Boosts.getSlider().get(8).getValue() > 0) add_Damage_Percent(thunderstorm, calc_Total_Damage_Percent_Manual(set_Damage_Percent(SpellEnum.BREATHLESS_THUNDERSTORM), damage_Boosts.getSlider().get(8).getValue())); //Winded
                                add_Damage_Percent(meteor, thunderstorm);
                                calcSpell("Thunderstorm", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, thunderstorm, sp, false, false);
                            }
                            calcSpell("Total Damage", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, meteor, sp, true, false);
                        }
                    }

                    //Ice Snake
                    if (tbd[3]) {
                        list.add(new Damage_Template("Ice Snake", calc_Spell_Cost(30 + spell_cost_4, intelligence, id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_4TH_SPELL_COST)], id_Numbers[ID_Display.ID_INT.get(Identifications.PERCENT_4TH_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
                        float[] ice_Snake = set_Damage_Percent(SpellEnum.ICE_SNAKE);
                        if (tbd[9] && damage_Boosts.getSlider().get(8).getValue() > 0) add_Damage_Percent(ice_Snake, calc_Total_Damage_Percent_Manual(set_Damage_Percent(SpellEnum.WINDSWEEPER), damage_Boosts.getSlider().get(8).getValue())); //Winded
                        if (tbd[11]) add_Damage_Percent(ice_Snake, SpellEnum.SENTIENT_SNAKE);
                        if (tbd[19]) add_Damage_Percent(ice_Snake, SpellEnum.GUST);
                        if (tbd[14]) {
                            add_Damage_Percent(ice_Snake, SpellEnum.SNAKE_NEST);
                            calcSpell("Single Hit", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, ice_Snake, sp, false, false);
                            calcSpell("Total Damage", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, calc_Total_Damage_Percent(ice_Snake), sp, true, false);
                        } else {
                            calcSpell("", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, ice_Snake, sp, true, false);
                        }
                    }

                    //Burning Sigil
                    if (tbd[6]) {
                        list.add(new Damage_Template("Burning Sigil", 0, pane, list.get(list.size() - 1), sp, crit_boost, true));
                        float[] burning_Sigil = set_Damage_Percent(SpellEnum.BURNING_SIGIL);
                        calcSpell("DPS", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, calc_Total_Damage_Percent_Manual(burning_Sigil, 2.5F), sp, false, false);
                        calcSpell("Single Hit", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, burning_Sigil, sp, false, false);
                        calcSpell("Total Damage", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, calc_Total_Damage_Percent(burning_Sigil), sp, true, false);
                    }

                    //Psychokinesis
                    if (tbd[13]) {
                        list.add(new Damage_Template("Psychokinesis", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
                        float[] psychokinesis = set_Damage_Percent(SpellEnum.PSYCHOKINESIS);
                        calcMelee("DPS", list.size() - 1, calc_raw, list, (percent + melee_percent) * multiple * melee_multiple, id_Numbers, psychokinesis, atkSpd, sp, false, true, false);
                        calcMelee("Single Hit", list.size() - 1, calc_raw, list, (percent + melee_percent) * multiple * melee_multiple, id_Numbers, psychokinesis, atkSpd, sp, false, false, false);
                    }

                    //Lightweaver
                    if (tbd[17]) {
                        list.add(new Damage_Template("Lightweaver", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
                        calcSpell("Single Hit", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, set_Damage_Percent(SpellEnum.LIGHTWEAVER), sp, false, false);
                    }
                    break;
                }
                case "shaman": {
                    //Melee
                    melee = set_Damage_Percent(SpellEnum.SHAMAN_MELEE);
                    if (tbd[22]) add_Damage_Percent(melee, SpellEnum.HAND_OF_THE_SHAMAN);
                    calcMelee("DPS", list.size() - 1, calc_raw, list, (percent * melee_percent) * melee_multiple * multiple, id_Numbers, calc_Total_Damage_Percent(melee), atkSpd, sp, false, true, false);
                    calcMelee("Single Hit", list.size() - 1, calc_raw, list, (percent * melee_percent) * melee_multiple * multiple, id_Numbers, melee, atkSpd, sp, false, false, false);
                    calcMelee("Total Damage", list.size() - 1, calc_raw, list, (percent * melee_percent) * melee_multiple * multiple, id_Numbers, calc_Total_Damage_Percent(melee), atkSpd, sp, true, false, false);

                    //Totem
                    if (tbd[0]) {
                        list.add(new Damage_Template("Totem", calc_Spell_Cost(30 + spell_cost_1, intelligence, id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_1ST_SPELL_COST)], id_Numbers[ID_Display.ID_INT.get(Identifications.PERCENT_1ST_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, false));
                        float[] totem = set_Damage_Percent(SpellEnum.TOTEM);
                        float totem_percent = percent * multiple;
                        int totem_count = 1;
                        if (tbd[14]) add_Damage_Percent(totem, SpellEnum.STRONGER_TOTEM);
                        if (tbd[19]) {
                            //Triple Totem
                            totem_percent *= 0.5F;
                            totem_count = 3;
                        } else if (tbd[11]) {
                            //Double Totem
                            totem_percent *= 0.6F;
                            totem_count = 2;
                        }
                        if (tbd[23]) list.get(list.size() - 1).addHeal("Heal", id_Numbers, 0.01F, false);
                        calcSpell("DPS", list.size() - 1, calc_raw, list, totem_percent, id_Numbers, calc_Total_Damage_Percent_Manual(totem, 2.5F * totem_count), sp, false, false);
                        calcSpell("Single Hit", list.size() - 1, calc_raw, list, totem_percent, id_Numbers, totem, sp, false, false);
                    }

                    //Haul
                    if (tbd[2]) {
                        list.add(new Damage_Template("Haul", calc_Spell_Cost(15 + spell_cost_2, intelligence, id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_2ND_SPELL_COST)], id_Numbers[ID_Display.ID_INT.get(Identifications.PERCENT_2ND_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
                        if (tbd[5]) {
                            //Nature's Jolt
                            calcSpell("Nature's Jolt", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, set_Damage_Percent(SpellEnum.NATURES_JOLT), sp, !(tbd[20] && damage_Boosts.getBox().get(20).isSelected()), false);
                        }
                        if (tbd[20] && damage_Boosts.getBox().get(20).isSelected()) {
                            //Frog Dance
                            calcSpell("Frog Dance", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, set_Damage_Percent(SpellEnum.FROG_DANCE), sp, !tbd[5], false);
                        }
                        if (tbd[5] && tbd[20] && damage_Boosts.getBox().get(20).isSelected()) {
                            float[] haul = set_Damage_Percent(SpellEnum.NATURES_JOLT);
                            add_Damage_Percent(haul, SpellEnum.FROG_DANCE);
                            calcSpell("Total Damage", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, haul, sp, true, false);
                        }
                    }

                    //Aura
                    if (tbd[4]) {
                        list.add(new Damage_Template("Aura", calc_Spell_Cost(40 + spell_cost_3, intelligence, id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_3RD_SPELL_COST)], id_Numbers[ID_Display.ID_INT.get(Identifications.PERCENT_3RD_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
                        float[] aura = set_Damage_Percent(SpellEnum.AURA);
                        float aura_percent = percent * multiple;
                        int totem_count = 1;
                        float heal = 1F;
                        if (tbd[6]) add_Damage_Percent(aura, SpellEnum.SHOCKING_AURA);
                        if (tbd[12]) add_Damage_Percent(aura, SpellEnum.STORM_DANCE);
                        if (tbd[19]) {
                            //Triple Totem
                            aura_percent *= 0.5F;
                            heal = 0.5F;
                            totem_count = 3;
                        } else if (tbd[11]) {
                            //Double Totem
                            aura_percent *= 0.6F;
                            heal = 0.6F;
                            totem_count = 2;
                        }

                        if (tbd[24]) {
                            //Rebound
                            if (tbd[25] && damage_Boosts.getBox().get(24).isSelected()) { //Sacrificial Shrine
                                list.get(list.size() - 1).addHeal("Total Heal", id_Numbers, 0.3F * heal * totem_count, tbd[26]);
                                list.get(list.size() - 1).addHeal("Single Heal", id_Numbers, 0.15F * heal, tbd[26]);
                                aura_percent *= 1.35F;
                            }
                            calcSpell("Single Hit", list.size() - 1, calc_raw, list, aura_percent, id_Numbers, aura, sp, false, false);
                            calcSpell("Total Damage", list.size() - 1, calc_raw, list, aura_percent, id_Numbers, calc_Total_Damage_Percent_Manual(aura, totem_count * 2), sp, true, false);
                        } else {
                            if (tbd[25] && damage_Boosts.getBox().get(24).isSelected()) {
                                list.get(list.size() - 1).addHeal("Total Heal", id_Numbers, 0.15F * heal * totem_count, tbd[26]);
                                list.get(list.size() - 1).addHeal("Single Heal", id_Numbers, 0.15F * heal, tbd[26]);
                                aura_percent *= 1.35F;
                            }
                            calcSpell("Single Hit", list.size() - 1, calc_raw, list, aura_percent, id_Numbers, aura, sp, false, false);
                            calcSpell("Total Damage", list.size() - 1, calc_raw, list, aura_percent, id_Numbers, calc_Total_Damage_Percent_Manual(aura, totem_count), sp, true, false);
                        }
                    }

                    //Uproot
                    if (tbd[3]) {
                        if (tbd[27]) {
                            list.add(new Damage_Template("Switch Mask", calc_Spell_Cost(30 + spell_cost_4, intelligence, id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_4TH_SPELL_COST)], id_Numbers[ID_Display.ID_INT.get(Identifications.PERCENT_4TH_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, false));
                            if (tbd[16]) calcSpell("Switch to Coward", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, set_Damage_Percent(SpellEnum.CHANT_OF_THE_COWARD), sp, false, false);
                        } else {
                            list.add(new Damage_Template("Uproot", calc_Spell_Cost(30 + spell_cost_4, intelligence, id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_4TH_SPELL_COST)], id_Numbers[ID_Display.ID_INT.get(Identifications.PERCENT_4TH_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
                            float[] uproot = set_Damage_Percent(SpellEnum.UPROOT);
                            if (tbd[7]) {
                                //Flaming Tongue
                                add_Damage_Percent(uproot, SpellEnum.FLAMING_TONGUE);
                                calcSpell("Single Hit", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, uproot, sp, false, false);
                                calcSpell("Total Damage", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, calc_Total_Damage_Percent(uproot), sp, true, false);
                            } else {
                                calcSpell("", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, uproot, sp, true, false);
                            }
                        }
                    }

                    //Rain Dance
                    if (tbd[8]) {
                        list.add(new Damage_Template("Rain Dance", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
                        float[] rain_Dance = set_Damage_Percent(SpellEnum.RAIN_DANCE);
                        calcSpell("DPS", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, calc_Total_Damage_Percent_Manual(rain_Dance, 2.5F), sp, false, false);
                        calcSpell("Single Hit", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, rain_Dance, sp, false, false);
                    }

                    //Puppet Master
                    if (tbd[9]) {
                        list.add(new Damage_Template("Puppet Master", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
                        float[] puppet_Master = set_Damage_Percent(SpellEnum.PUPPET_MASTER);
                        float puppet_Percent = percent * multiple;
                        float puppet_AtkSpd = 2F;
                        if (tb[2] && damage_Boosts.getBox().get(22).isSelected()) puppet_Percent *= 1.2F; //Bullwhip
                        if (tb[3] && damage_Boosts.getBox().get(23).isSelected()) puppet_AtkSpd *= 1.3F; //Invigorating Wave
                        int maxPuppets = 3;
                        if (tb[0]) maxPuppets += 1;
                        if (tb[1]) maxPuppets += 2;
                        if (tb[2] && damage_Boosts.getSlider().get(9).getValue() > 0) maxPuppets += damage_Boosts.getSlider().get(9).getValue(); //Shepherd
                        calcSpell("Max DPS", list.size() - 1, calc_raw, list, puppet_Percent, id_Numbers, calc_Total_Damage_Percent_Manual(puppet_Master, maxPuppets * puppet_AtkSpd), sp, false, false);
                        calcSpell("Single DPS", list.size() - 1, calc_raw, list, puppet_Percent, id_Numbers, calc_Total_Damage_Percent_Manual(puppet_Master, puppet_AtkSpd), sp, false, false);
                        calcSpell("Single Hit", list.size() - 1, calc_raw, list, puppet_Percent, id_Numbers, puppet_Master, sp, false, false);
                    }

                    //Exploding Puppets
                    if (tbd[10]) {
                        list.add(new Damage_Template("Exploding Puppets", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
                        calcSpell("", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, set_Damage_Percent(SpellEnum.EXPLODING_PUPPETS), sp, false, false);
                    }

                    //Twisted Tether
                    if (tbd[13]) {
                        list.add(new Damage_Template("Twisted Tether", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
                        float[] twisted_Tether = set_Damage_Percent(SpellEnum.TWISTED_TETHER);
                        if (tbd[13]) add_Damage_Percent(twisted_Tether, SpellEnum.STRONGER_TETHER);
                        calcSpell("Single Hit", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, twisted_Tether, sp, false, false);
                    }

                    //Crimson Effigy
                    if (tbd[15]) {
                        list.add(new Damage_Template("Crimson Effigy", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
                        float[] crimson_Effigy = set_Damage_Percent(SpellEnum.CRIMSON_EFFIGY);
                        float effigy_Percent = percent * multiple;
                        float effigy_AtkSpd = 2F;
                        if (tb[2] && damage_Boosts.getBox().get(22).isSelected()) effigy_Percent *= 1.2F; //Bullwhip
                        if (tb[3] && damage_Boosts.getBox().get(23).isSelected()) effigy_AtkSpd *= 1.3F; //Invigorating Wave

                        String n = "DPS";
                        if (tbd[17]) {
                            add_Damage_Percent(crimson_Effigy, SpellEnum.MORE_EFFIGIES);
                            n = "Single DPS";
                            calcSpell("Total DPS", list.size() - 1, calc_raw, list, effigy_Percent, id_Numbers, calc_Total_Damage_Percent_Manual(crimson_Effigy, effigy_AtkSpd * 2F), sp, false, false);
                        }
                        calcSpell(n, list.size() - 1, calc_raw, list, effigy_Percent, id_Numbers, calc_Total_Damage_Percent_Manual(crimson_Effigy, effigy_AtkSpd), sp, false, false);
                        calcSpell("Single Hit", list.size() - 1, calc_raw, list, effigy_Percent, id_Numbers, crimson_Effigy, sp, false, false);
                    }

                    //Blood Sorrow
                    if (tbd[21]) {
                        list.add(new Damage_Template("Blood Sorrow", 0, pane, list.get(list.size() - 1), sp, crit_boost, true));
                        float[] blood_Sorrow = set_Damage_Percent(SpellEnum.BLOOD_SORROW);
                        calcSpell("DPS", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, calc_Total_Damage_Percent_Manual(blood_Sorrow, 5F), sp, false, false);
                        calcSpell("Single Hit", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, blood_Sorrow, sp, false, false);
                        calcSpell("Total Damage", list.size() - 1, calc_raw, list, percent * multiple, id_Numbers, calc_Total_Damage_Percent(blood_Sorrow), sp, true, false);
                    }
                    break;
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

    private void add_Damage_Percent(float[] basePercent, float[] addPercent) {
        basePercent[0] += addPercent[0] * addPercent[6];
        basePercent[1] += addPercent[1] * addPercent[6];
        basePercent[2] += addPercent[2] * addPercent[6];
        basePercent[3] += addPercent[3] * addPercent[6];
        basePercent[4] += addPercent[4] * addPercent[6];
        basePercent[5] += addPercent[5] * addPercent[6];
    }

    private float[] calc_Total_Damage_Percent(float[] p) {
        float[] percent = p.clone();
        percent[0] *= percent[6];
        percent[1] *= percent[6];
        percent[2] *= percent[6];
        percent[3] *= percent[6];
        percent[4] *= percent[6];
        percent[5] *= percent[6];
        return percent;
    }

    private float[] calc_Total_Damage_Percent_Manual(float[] p, float boost) {
        float[] percent = p.clone();
        percent[0] *= boost;
        percent[1] *= boost;
        percent[2] *= boost;
        percent[3] *= boost;
        percent[4] *= boost;
        percent[5] *= boost;
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

    private void calcMelee(String name, int pos, Calc_Raw damages, List<Damage_Template> list, float boost, int[] id_Numbers, float[] percent, int atkSpd, SkillPoint sp, boolean isTotal, boolean isDPS, boolean checkDamage) {
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

        float neutral_min_base = damages.neutral_min;
        float earth_min_base = damages.earth_min;
        float thunder_min_base = damages.thunder_min;
        float water_min_base = damages.water_min;
        float fire_min_base = damages.fire_min;
        float air_min_base = damages.air_min;
        float total_min = damages.neutral_min + damages.earth_min + damages.thunder_min + damages.water_min + damages.fire_min + damages.air_min;

        float neutral_max_base = damages.neutral_max;
        float earth_max_base = damages.earth_max;
        float thunder_max_base = damages.thunder_max;
        float water_max_base = damages.water_max;
        float fire_max_base = damages.fire_max;
        float air_max_base = damages.air_max;
        float total_max = damages.neutral_max + damages.earth_max + damages.thunder_max + damages.water_max + damages.fire_max + damages.air_max;

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


        float neutral_min = calc(neutral_min_base, boost, 1F, id_Numbers[ID_Display.ID_INT.get(Identifications.MELEE_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.NEUTRAL_MELEE_DAMAGE_PERCENT)]);
        float earth_min = calc(earth_min_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost(), id_Numbers[ID_Display.ID_INT.get(Identifications.MELEE_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.EARTH_MELEE_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.EARTH_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)]);
        float thunder_min = calc(thunder_min_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.DEXTERITY).getSPBoost(), id_Numbers[ID_Display.ID_INT.get(Identifications.MELEE_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.THUNDER_MELEE_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.THUNDER_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)]);
        float water_min = calc(water_min_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.INTELLIGENCE).getIntDamageBonus(), id_Numbers[ID_Display.ID_INT.get(Identifications.MELEE_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.WATER_MELEE_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.WATER_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)]);
        float fire_min = calc(fire_min_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.DEFENSE).getSPBoost(), id_Numbers[ID_Display.ID_INT.get(Identifications.MELEE_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.FIRE_MELEE_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.FIRE_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)]);
        float air_min = calc(air_min_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.AGILITY).getSPBoost(), id_Numbers[ID_Display.ID_INT.get(Identifications.MELEE_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.AIR_MELEE_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.AIR_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)]);

        float neutral_max = calc(neutral_max_base, boost, 1F, id_Numbers[ID_Display.ID_INT.get(Identifications.MELEE_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.NEUTRAL_MELEE_DAMAGE_PERCENT)]);
        float earth_max = calc(earth_max_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost(), id_Numbers[ID_Display.ID_INT.get(Identifications.MELEE_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.EARTH_MELEE_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.EARTH_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)]);
        float thunder_max = calc(thunder_max_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.DEXTERITY).getSPBoost(), id_Numbers[ID_Display.ID_INT.get(Identifications.MELEE_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.THUNDER_MELEE_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.THUNDER_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)]);
        float water_max = calc(water_max_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.INTELLIGENCE).getIntDamageBonus(), id_Numbers[ID_Display.ID_INT.get(Identifications.MELEE_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.WATER_MELEE_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.WATER_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)]);
        float fire_max = calc(fire_max_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.DEFENSE).getSPBoost(), id_Numbers[ID_Display.ID_INT.get(Identifications.MELEE_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.FIRE_MELEE_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.FIRE_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)]);
        float air_max = calc(air_max_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.AGILITY).getSPBoost(), id_Numbers[ID_Display.ID_INT.get(Identifications.MELEE_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.AIR_MELEE_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.AIR_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)]);

        Calc_Raw calc_raw = new Calc_Raw(neutral_min, earth_min, thunder_min, water_min, fire_min, air_min, neutral_max, earth_max, thunder_max, water_max, fire_max, air_max);

        if (neutral_min != 0 || neutral_max != 0) {
            neutral_min += (calc_raw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_MELEE_DAMAGE)], "Neutral", false, true) + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_NEUTRAL_MELEE_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_NEUTRAL_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;
            neutral_max += (calc_raw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_MELEE_DAMAGE)], "Neutral", true, true) + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_NEUTRAL_MELEE_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_NEUTRAL_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;

            neutral_min *= tomeBonus;
            neutral_max *= tomeBonus;
        }

        if (earth_min != 0 || earth_max != 0) {
            earth_min += (calc_raw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_MELEE_DAMAGE)], "Earth", false, true) + calc_raw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_ELEMENTAL_MELEE_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_ELEMENTAL_DAMAGE)], "Earth", false, false) + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_EARTH_MELEE_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_EARTH_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;
            earth_max += (calc_raw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_MELEE_DAMAGE)], "Earth", true, true) + calc_raw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_ELEMENTAL_MELEE_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_ELEMENTAL_DAMAGE)], "Earth", true, false) + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_EARTH_MELEE_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_EARTH_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;

            earth_min *= tomeBonus;
            earth_max *= tomeBonus;
        }

        if (thunder_min != 0 || thunder_max != 0) {
            thunder_min += (calc_raw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_MELEE_DAMAGE)], "Thunder", false, true) + calc_raw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_ELEMENTAL_MELEE_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_ELEMENTAL_DAMAGE)], "Thunder", false, false) + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_THUNDER_MELEE_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_THUNDER_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;
            thunder_max += (calc_raw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_MELEE_DAMAGE)], "Thunder", true, true) + calc_raw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_ELEMENTAL_MELEE_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_ELEMENTAL_DAMAGE)], "Thunder", true, false) + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_THUNDER_MELEE_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_THUNDER_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;

            thunder_min *= tomeBonus;
            thunder_max *= tomeBonus;
        }

        if (water_min != 0 || water_max != 0) {
            water_min += (calc_raw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_MELEE_DAMAGE)], "Water", false, true) + calc_raw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_ELEMENTAL_MELEE_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_ELEMENTAL_DAMAGE)], "Water", false, false) + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_WATER_MELEE_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_WATER_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;
            water_max += (calc_raw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_MELEE_DAMAGE)], "Water", true, true) + calc_raw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_ELEMENTAL_MELEE_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_ELEMENTAL_DAMAGE)], "Water", true, false) + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_WATER_MELEE_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_WATER_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;

            water_min *= tomeBonus;
            water_max *= tomeBonus;
        }

        if (fire_min != 0 || fire_max != 0) {
            fire_min += (calc_raw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_MELEE_DAMAGE)], "Fire", false, true) + calc_raw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_ELEMENTAL_MELEE_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_ELEMENTAL_DAMAGE)], "Fire", false, false) + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_FIRE_MELEE_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_FIRE_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;
            fire_max += (calc_raw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_MELEE_DAMAGE)], "Fire", true, true) + calc_raw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_ELEMENTAL_MELEE_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_ELEMENTAL_DAMAGE)], "Fire", true, false) + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_FIRE_MELEE_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_FIRE_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;

            fire_min *= tomeBonus;
            fire_max *= tomeBonus;
        }

        if (air_min != 0 || air_max != 0) {
            air_min += (calc_raw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_MELEE_DAMAGE)], "Air", false, true) + calc_raw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_ELEMENTAL_MELEE_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_ELEMENTAL_DAMAGE)], "Air", false, false) + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_AIR_MELEE_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_AIR_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;
            air_max += (calc_raw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_MELEE_DAMAGE)], "Air", true, true) + calc_raw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_ELEMENTAL_MELEE_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_ELEMENTAL_DAMAGE)], "Air", true, false) + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_AIR_MELEE_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_AIR_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;

            air_min *= tomeBonus;
            air_max *= tomeBonus;
        }

        if (isDPS) {
            //Neutral
            neutral_min *= atkSpdF;
            neutral_max *= atkSpdF;

            //Earth
            earth_min *= atkSpdF;
            earth_max *= atkSpdF;

            //Thunder
            thunder_min *= atkSpdF;
            thunder_max *= atkSpdF;

            //Water
            water_min *= atkSpdF;
            water_max *= atkSpdF;

            //Fire
            fire_min *= atkSpdF;
            fire_max *= atkSpdF;

            //Air
            air_min *= atkSpdF;
            air_max *= atkSpdF;
        }

        if (checkDamage) totalMaxDamage.addDamage(neutral_max, earth_max, thunder_max, water_max, fire_max, air_max);

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
                , isTotal);
    }

    private void calcSpell(String name, int pos, Calc_Raw damages, List<Damage_Template> list, float boost, int[] id_Numbers, float[] percent, SkillPoint sp, boolean isTotal, boolean checkDamage) {
        float tomeBonus = 1F + (id_Numbers[89] / 100F);
        float atkSpd = 0.51F;
        if (weapon != null && weapon.get(Identifications.ATTACK_SPEED.getItemName()) != null) {
            switch (weapon.get(Identifications.ATTACK_SPEED.getItemName()).getAsString()) {
                case "very_slow": atkSpd = 0.83F;
                    break;
                case "slow": atkSpd = 1.5F;
                    break;
                case "normal": atkSpd = 2.05F;
                    break;
                case "fast": atkSpd = 2.5F;
                    break;
                case "very_fast": atkSpd = 3.1F;
                    break;
                case "super_fast": atkSpd = 4.3F;
                    break;
            }
        }

        float neutral_min_base = damages.neutral_min;
        float earth_min_base = damages.earth_min;
        float thunder_min_base = damages.thunder_min;
        float water_min_base = damages.water_min;
        float fire_min_base = damages.fire_min;
        float air_min_base = damages.air_min;
        float total_min = damages.neutral_min + damages.earth_min + damages.thunder_min + damages.water_min + damages.fire_min + damages.air_min;

        float neutral_max_base = damages.neutral_max;
        float earth_max_base = damages.earth_max;
        float thunder_max_base = damages.thunder_max;
        float water_max_base = damages.water_max;
        float fire_max_base = damages.fire_max;
        float air_max_base = damages.air_max;
        float total_max = damages.neutral_max + damages.earth_max + damages.thunder_max + damages.water_max + damages.fire_max + damages.air_max;

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

        float neutral_min = calc(neutral_min_base, boost, 1F, id_Numbers[ID_Display.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.NEUTRAL_SPELL_DAMAGE_PERCENT)]) * atkSpd;
        float earth_min = calc(earth_min_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost(), id_Numbers[ID_Display.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.EARTH_SPELL_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.EARTH_DAMAGE_PERCENT)]) * atkSpd;
        float thunder_min = calc(thunder_min_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.DEXTERITY).getSPBoost(), id_Numbers[ID_Display.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.THUNDER_SPELL_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.THUNDER_DAMAGE_PERCENT)]) * atkSpd;
        float water_min = calc(water_min_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.INTELLIGENCE).getIntDamageBonus(), id_Numbers[ID_Display.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.WATER_SPELL_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.WATER_DAMAGE_PERCENT)]) * atkSpd;
        float fire_min = calc(fire_min_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.DEFENSE).getSPBoost(), id_Numbers[ID_Display.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.FIRE_SPELL_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.FIRE_DAMAGE_PERCENT)]) * atkSpd;
        float air_min = calc(air_min_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.AGILITY).getSPBoost(), id_Numbers[ID_Display.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.AIR_SPELL_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.AIR_DAMAGE_PERCENT)]) * atkSpd;

        float neutral_max = calc(neutral_max_base, boost, 1F, id_Numbers[ID_Display.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.NEUTRAL_SPELL_DAMAGE_PERCENT)]) * atkSpd;
        float earth_max = calc(earth_max_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost(), id_Numbers[ID_Display.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.EARTH_SPELL_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.EARTH_DAMAGE_PERCENT)]) * atkSpd;
        float thunder_max = calc(thunder_max_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.DEXTERITY).getSPBoost(), id_Numbers[ID_Display.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.THUNDER_SPELL_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.THUNDER_DAMAGE_PERCENT)]) * atkSpd;
        float water_max = calc(water_max_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.INTELLIGENCE).getIntDamageBonus(), id_Numbers[ID_Display.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.WATER_SPELL_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.WATER_DAMAGE_PERCENT)]) * atkSpd;
        float fire_max = calc(fire_max_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.DEFENSE).getSPBoost(), id_Numbers[ID_Display.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.FIRE_SPELL_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.FIRE_DAMAGE_PERCENT)]) * atkSpd;
        float air_max = calc(air_max_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.AGILITY).getSPBoost(), id_Numbers[ID_Display.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.AIR_SPELL_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)], id_Numbers[ID_Display.ID_INT.get(Identifications.AIR_DAMAGE_PERCENT)]) * atkSpd;

        Calc_Raw calcRaw = new Calc_Raw(neutral_min, earth_min, thunder_min, water_min, fire_min, air_min, neutral_max, earth_max, thunder_max, water_max, fire_max, air_max);

        if (neutral_min != 0 || neutral_max != 0) {
            neutral_min += (calcRaw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_SPELL_DAMAGE)], "Neutral", false, true) + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_NEUTRAL_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_NEUTRAL_SPELL_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;
            neutral_max += (calcRaw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_SPELL_DAMAGE)], "Neutral", true, true) + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_NEUTRAL_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_NEUTRAL_SPELL_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;

            neutral_min *= tomeBonus;
            neutral_max *= tomeBonus;
        }
        if (earth_min != 0 || earth_max != 0) {
            earth_min += (calcRaw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_SPELL_DAMAGE)], "Earth", false, true) + calcRaw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_ELEMENTAL_SPELL_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_ELEMENTAL_DAMAGE)], "Earth", false, false) + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_EARTH_SPELL_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_EARTH_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;
            earth_max += (calcRaw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_SPELL_DAMAGE)], "Earth", true, true) + calcRaw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_ELEMENTAL_SPELL_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_ELEMENTAL_DAMAGE)], "Earth", true, false) + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_EARTH_SPELL_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_EARTH_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;

            earth_min *= tomeBonus;
            earth_max *= tomeBonus;
        }
        if (thunder_min != 0 || thunder_max != 0) {
            thunder_min += (calcRaw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_SPELL_DAMAGE)], "Thunder", false, true) + calcRaw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_ELEMENTAL_SPELL_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_ELEMENTAL_DAMAGE)], "Thunder", false, false) + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_THUNDER_SPELL_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_THUNDER_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;
            thunder_max += (calcRaw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_SPELL_DAMAGE)], "Thunder", true, true) + calcRaw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_ELEMENTAL_SPELL_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_ELEMENTAL_DAMAGE)], "Thunder", true, false) + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_THUNDER_SPELL_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_THUNDER_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;

            thunder_min *= tomeBonus;
            thunder_max *= tomeBonus;
        }
        if (water_min != 0 || water_max != 0) {
            water_min += (calcRaw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_SPELL_DAMAGE)], "Water", false, true) + calcRaw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_ELEMENTAL_SPELL_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_ELEMENTAL_DAMAGE)], "Water", false, false) + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_WATER_SPELL_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_WATER_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;
            water_max += (calcRaw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_SPELL_DAMAGE)], "Water", true, true) + calcRaw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_ELEMENTAL_SPELL_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_ELEMENTAL_DAMAGE)], "Water", true, false) + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_WATER_SPELL_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_WATER_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;

            water_min *= tomeBonus;
            water_max *= tomeBonus;
        }
        if (fire_min != 0 || fire_max != 0) {
            fire_min += (calcRaw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_SPELL_DAMAGE)], "Fire", false, true) + calcRaw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_ELEMENTAL_SPELL_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_ELEMENTAL_DAMAGE)], "Fire", false, false) + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_FIRE_SPELL_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_FIRE_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;
            fire_max += (calcRaw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_SPELL_DAMAGE)], "Fire", true, true) + calcRaw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_ELEMENTAL_SPELL_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_ELEMENTAL_DAMAGE)], "Fire", true, false) + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_FIRE_SPELL_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_FIRE_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;

            fire_min *= tomeBonus;
            fire_max *= tomeBonus;
        }
        if (air_min != 0 || air_max != 0) {
            air_min += (calcRaw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_SPELL_DAMAGE)], "Air", false, true) + calcRaw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_ELEMENTAL_SPELL_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_ELEMENTAL_DAMAGE)], "Air", false, false) + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_AIR_SPELL_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_AIR_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;
            air_max += (calcRaw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_SPELL_DAMAGE)], "Air", true, true) + calcRaw.calc(id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_ELEMENTAL_SPELL_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_ELEMENTAL_DAMAGE)], "Air", true, false) + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_AIR_SPELL_DAMAGE)] + id_Numbers[ID_Display.ID_INT.get(Identifications.RAW_AIR_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;

            air_min *= tomeBonus;
            air_max *= tomeBonus;
        }

        if (checkDamage) totalMaxDamage.addDamage(neutral_max, earth_max, thunder_max, water_max, fire_max, air_max);

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
        private final SkillPoint sp;
        private final boolean crit_boost;
        private final JPanel pane;

        private Damage_Template(String name, float mana_Cost, JPanel pane, Damage_Template previous, SkillPoint sp, boolean crit_boost, boolean needTotal) {
            this.sp  = sp;
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
            p.add(nameL);
            if (needTotal) { //Blank
                p.add(new JLabel(" ")); // +20
                y_size += 20;
            }
            if (needTotal) { //Avg Total Damage
                p.add(avg_total); // +16
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

        private void addHeal(String name, int[] id_Numbers, float healPercent, boolean isCanBoost) {
            float heal = (id_Numbers[ID_Display.ID_INT.get(Identifications.HEALTH)] + id_Numbers[ID_Display.ID_INT.get(Identifications.HEALTH_BONUS)]) * healPercent;
            if (isCanBoost) heal *= 1F + ((id_Numbers[ID_Display.ID_INT.get(Identifications.WATER_DAMAGE_PERCENT)] * 0.3F) / 100F);
            JLabel n = new JLabel(name);
            JLabel h = new JLabel(String.valueOf(heal));
            n.setAlignmentX(Component.CENTER_ALIGNMENT);
            h.setAlignmentX(Component.CENTER_ALIGNMENT);
            p.add(new JLabel(" ")); //+20
            if (!name.isEmpty()) p.add(n); //+16
            p.add(h); //+16

            y_size += 52;
            p.setBounds(10, y, 280, y_size);
            if (y + y_size + 10 > 397) {
                pane.setPreferredSize(new Dimension(300, y + y_size + 10));
            } else {
                pane.setPreferredSize(new Dimension(300, 397));
            }
        }

        private void addDamage(float total_Min, float total_Max) {
            JLabel l = new JLabel(total_Min + " to" + total_Max);
            l.setAlignmentX(Component.CENTER_ALIGNMENT);
            p.add(new JLabel(" ")); //+20
            p.add(l); //+16
            y_size += 36;
            p.setBounds(10, y, 280, y_size);
            if (y + y_size + 10 > 397) {
                pane.setPreferredSize(new Dimension(300, y + y_size + 10));
            } else {
                pane.setPreferredSize(new Dimension(300, 397));
            }
        }

        private void addDamage(String name, float neutral_min, float earth_min, float thunder_min, float water_min, float fire_min, float air_min
                , float neutral_max, float earth_max, float thunder_max, float water_max, float fire_max, float air_max
                , boolean isTotal) {

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
            }
        }
    }

    private static class TotalMaxDamage {
        private final SkillPoint sp;
        private final boolean crit_boost;
        private int maxDamage = 0;

        private TotalMaxDamage(SkillPoint sp, boolean crit_boost) {
            this.sp  = sp;
            this.crit_boost = crit_boost;
        }

        private void addDamage(float neutral_max, float earth_max, float thunder_max, float water_max, float fire_max, float air_max) {
            float crit_boost = 1F;
            if (this.crit_boost) crit_boost = 1.15F;

            float crit_neutral_max = neutral_max * (crit_boost + sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost());
            float crit_earth_max = earth_max  * (crit_boost + sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost());
            float crit_thunder_max = thunder_max  * (crit_boost + sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost());
            float crit_water_max = water_max  * (crit_boost + sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost());
            float crit_fire_max = fire_max  * (crit_boost + sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost());
            float crit_air_max = air_max  * (crit_boost + sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost());
            int total_Damage = (int) Math.floor(crit_neutral_max + crit_earth_max + crit_thunder_max + crit_water_max + crit_fire_max + crit_air_max);

            if (total_Damage > maxDamage) maxDamage = total_Damage;
        }

        private int getMaxDamage() {
            return maxDamage;
        }
    }
}
