package com.github.iTzhsnu.WynnSearcher.builder;

import com.github.iTzhsnu.WynnSearcher.Identifications;
import com.github.iTzhsnu.WynnSearcher.builder.skilltree.AbilityIdEnum;
import com.github.iTzhsnu.WynnSearcher.builder.skilltree.SpellEnum;
import com.github.iTzhsnu.WynnSearcher.builder.skilltree.TreeBase;
import com.github.iTzhsnu.WynnSearcher.builder.skilltree.TreeCheckBox;
import com.github.iTzhsnu.WynnSearcher.data.DamageType;
import com.github.iTzhsnu.WynnSearcher.data.DataUtils;
import com.github.iTzhsnu.WynnSearcher.data.ItemBase;
import com.github.iTzhsnu.WynnSearcher.general.JsonKeys;
import com.github.iTzhsnu.WynnSearcher.general.JsonValues;
import com.google.gson.JsonObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DamageDisplay {
    private final JPanel pane = new JPanel();
    private ItemData itemData = null;
    private ItemBase weapon = null;
    private TotalMaxDamage totalMaxDamage = null;

    private float neutralMin = 0;
    private float earthMin = 0;
    private float thunderMin = 0;
    private float waterMin = 0;
    private float fireMin = 0;
    private float airMin = 0;

    private float neutralMax = 0;
    private float earthMax = 0;
    private float thunderMax = 0;
    private float waterMax = 0;
    private float fireMax = 0;
    private float airMax = 0;

    private final List<DamageTemplate> list = new ArrayList<>();

    private int raw_Damage = 0;
    private int raw_Elem_Damage = 0;
    private float percent = 1F;
    private float multiple = 1F;
    private float melee_percent = 0F;
    private float melee_multiple = 1F;
    private float spell_percent = 0F;
    private float spell_multiple = 1F;
    private int spellCost1 = 0;
    private int spellCost2 = 0;
    private int spellCost3 = 0;
    private int spellCost4 = 0;
    private float proficiency = 1F;
    private int atkSpd = 0;
    private boolean critBoost = false;

    private boolean[] tb = {
            false, false, false, false, false, false, false, false, false, false //0 ~ 9
    };
    private boolean[] tbd = new boolean[] {
            false, false, false, false, false, false, false, false, false, false, //0 ~ 9
            false, false, false, false, false, false, false, false, false, false, //10 ~ 19
            false, false, false, false, false, false, false, false, false, false, //20 ~ 29
            false, false, false, false, false, false, false, false, false, false, //30 ~ 39
            false, false, false, false, false, false, false, false, false, false //40 ~ 49
    };
    private boolean[] have_mastery;

    public DamageDisplay(JPanel p) {

        pane.setPreferredSize(new Dimension(300, 397));
        pane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane(pane);
        scrollPane.setBounds(708, 710, 318, 400);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);

        p.add(scrollPane);
    }

    public void setDamageDisplay(ItemData itemData, SkillPoint sp, AbilityBuffs abilityBuffs, TreeBase tree, PowderEffect powderEffects, int[] idNumbers, List<JTextField> powders) {
        this.itemData = itemData;
        this.weapon = itemData.getWeapon();
        pane.removeAll();

        if (!list.isEmpty()) list.clear();

        neutralMin = 0;
        earthMin = 0;
        thunderMin = 0;
        waterMin = 0;
        fireMin = 0;
        airMin = 0;

        neutralMax = 0;
        earthMax = 0;
        thunderMax = 0;
        waterMax = 0;
        fireMax = 0;
        airMax = 0;

        raw_Damage = 0;
        raw_Elem_Damage = 0;
        percent = 1F;
        multiple = 1F;
        melee_percent = 0F;
        melee_multiple = 1F;
        spell_percent = 0F;
        spell_multiple = 1F;
        spellCost1 = 0;
        spellCost2 = 0;
        spellCost3 = 0;
        spellCost4 = 0;
        proficiency = 1F;
        atkSpd = 0;
        critBoost = false;

        tb = new boolean[] {
                false, false, false, false, false, false, false, false, false, false //0 ~ 9
        };
        tbd = new boolean[] {
                false, false, false, false, false, false, false, false, false, false, //0 ~ 9
                false, false, false, false, false, false, false, false, false, false, //10 ~ 19
                false, false, false, false, false, false, false, false, false, false, //20 ~ 29
                false, false, false, false, false, false, false, false, false, false, //30 ~ 39
                false, false, false, false, false, false, false, false, false, false //40 ~ 49
        };
        have_mastery = new boolean[] {false, false, false, false, false, false};

        if (weapon != null) {
            if (weapon.haveFieldPos(Identifications.NEUTRAL_DAMAGE)) {
                JsonObject json = weapon.getJson().get(Identifications.NEUTRAL_DAMAGE.getItemFieldPos().getKey()).getAsJsonObject();
                //Neutral Damage
                neutralMin = getDamage(json, Identifications.NEUTRAL_DAMAGE, false);
                neutralMax = getDamage(json, Identifications.NEUTRAL_DAMAGE, true);

                //Earth Damage
                earthMin = getDamage(json, Identifications.EARTH_DAMAGE, false);
                earthMax = getDamage(json, Identifications.EARTH_DAMAGE, true);

                //Thunder Damage
                thunderMin = getDamage(json, Identifications.THUNDER_DAMAGE, false);
                thunderMax = getDamage(json, Identifications.THUNDER_DAMAGE, true);

                //Water Damage
                waterMin = getDamage(json, Identifications.WATER_DAMAGE, false);
                waterMax = getDamage(json, Identifications.WATER_DAMAGE, true);

                //Fire Damage
                fireMin = getDamage(json, Identifications.FIRE_DAMAGE, false);
                fireMax = getDamage(json, Identifications.FIRE_DAMAGE, true);

                //Air Damage
                airMin = getDamage(json, Identifications.AIR_DAMAGE, false);
                airMax = getDamage(json, Identifications.AIR_DAMAGE, true);
            }


            //Powder
            calcPowderDamages(powders);

            //Attack Speed
            atkSpd = idNumbers[IdDisplay.ID_INT.get(Identifications.ATTACK_SPEED_BONUS)];
            switch (weapon.getIdString(Identifications.ATTACK_SPEED)) {
                case JsonValues.VERY_SLOW: atkSpd += 1;
                    break;
                case JsonValues.SLOW: atkSpd += 2;
                    break;
                case JsonValues.A_NORMAL: atkSpd += 3;
                    break;
                case JsonValues.FAST: atkSpd += 4;
                    break;
                case JsonValues.VERY_FAST: atkSpd += 5;
                    break;
                case JsonValues.SUPER_FAST: atkSpd += 6;
                    break;
            }

            //Ability Tree
            setAbilityData(tree, abilityBuffs);

            melee_multiple *= proficiency; //Melee Multiple * Proficiency (5% or 10%)
            idNumbers[IdDisplay.ID_INT.get(Identifications.RAW_SPELL_DAMAGE)] += raw_Damage; //Raw Spell Damage += Raw Damage
            idNumbers[IdDisplay.ID_INT.get(Identifications.RAW_MELEE_DAMAGE)] += raw_Damage; //Raw Melee Damage += Raw Damage
            idNumbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_DAMAGE)] += raw_Elem_Damage; //Raw Elemental Damage += Raw Elem Damage

            CalcRaw calc_raw = new CalcRaw(neutralMin, earthMin, thunderMin, waterMin, fireMin, airMin, neutralMax, earthMax, thunderMax, waterMax, fireMax, airMax, null, null);

            float intelligence = 1F - sp.getSkillPoint(SkillPoint.SkillPointType.INTELLIGENCE).getSPBoost();

            //Display Damages
            totalMaxDamage = new TotalMaxDamage(sp, critBoost);
            //Powder Specials
            float powder_SP = (percent + melee_percent) * multiple * melee_multiple;
            if (tree.getClasses().equals("assassin") && tb[3]) powder_SP *= 2F; //Satsujin
            if (powderEffects.getPowderPanel("Water").getSpecial() != 0) {
                multiple *= 1.1F + (0.05F * (powderEffects.getPowderPanel("Water").getSpecial() - 1));
            }
            if (powderEffects.getPowderPanel("Air").getSpecial() != 0) {
                multiple *= 2F + (0.25F * (powderEffects.getPowderPanel("Air").getSpecial() - 1));
            }
            if (powderEffects.getPowderPanel("Fire").getSpecial() != 0) {
                multiple *= 1.1F + (0.025F * (powderEffects.getPowderPanel("Fire").getSpecial() - 1));
                list.add(new DamageTemplate("Fire Powder Special", 0, pane, null, sp, critBoost, false));
                SpellEnum fire = SpellEnum.FIRE_1;
                switch (powderEffects.getPowderPanel("Fire").getSpecial()) {
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
                calcMelee("", 0, calc_raw, list, powder_SP, idNumbers, setDamagePercent(fire), atkSpd, sp, false, false, true);
            }
            if (powderEffects.getPowderPanel("Earth").getSpecial() != 0) {
                DamageTemplate previous = null;
                if (!list.isEmpty()) previous = list.get(list.size() - 1);
                list.add(new DamageTemplate("Earth Powder Special", 0, pane, previous, sp, critBoost, false));
                SpellEnum earth = SpellEnum.EARTH_1;
                switch (powderEffects.getPowderPanel("Earth").getSpecial()) {
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
                calcMelee("", list.size() - 1, calc_raw, list, powder_SP, idNumbers, setDamagePercent(earth), atkSpd, sp, false, false, true);
            }
            if (powderEffects.getPowderPanel("Thunder").getSpecial() != 0) {
                DamageTemplate previous = null;
                if (!list.isEmpty()) previous = list.get(list.size() - 1);
                list.add(new DamageTemplate("Thunder Powder Special", 0, pane, previous, sp, critBoost, false));
                SpellEnum thunder = SpellEnum.THUNDER_1;
                switch (powderEffects.getPowderPanel("Thunder").getSpecial()) {
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
                calcMelee("", list.size() - 1, calc_raw, list, powder_SP, idNumbers, setDamagePercent(thunder), atkSpd, sp, false, false, true);
            }

            //Melee
            DamageTemplate previousM = null;
            if (!list.isEmpty()) previousM = list.get(list.size() - 1);
            list.add(new DamageTemplate("Melee", 0, pane, previousM, sp, critBoost, true));
            float[] melee = setDamagePercent(SpellEnum.MELEE);

            switch (tree.getClasses()) {
                case "warrior": {
                    setWarriorDamage(list, calc_raw, (percent + melee_percent) * multiple * melee_multiple, (percent + spell_percent) * multiple * spell_multiple,
                            idNumbers, melee, atkSpd, sp, abilityBuffs, tb, tbd, critBoost, intelligence, spellCost1, spellCost2, spellCost3, spellCost4);
                    break;
                }
                case "assassin": {
                    setAssassinDamage(list, calc_raw, (percent + melee_percent) * multiple * melee_multiple, (percent + spell_percent) * multiple * spell_multiple,
                            idNumbers, melee, atkSpd, sp, abilityBuffs, tb, tbd, critBoost, intelligence, spellCost1, spellCost2, spellCost3, spellCost4);
                    break;
                }
                case "archer": {
                    setArcherDamage(list, calc_raw, (percent + melee_percent) * multiple * melee_multiple, (percent + spell_percent) * multiple * spell_multiple,
                            idNumbers, melee, atkSpd, sp, abilityBuffs, tb, tbd, critBoost, intelligence, spellCost1, spellCost2, spellCost3, spellCost4);
                    break;
                }
                case "mage": {
                    setMageDamage(list, calc_raw, (percent + melee_percent) * multiple * melee_multiple, (percent + spell_percent) * multiple * spell_multiple,
                            idNumbers, melee, atkSpd, sp, abilityBuffs, tb, tbd, critBoost, intelligence, spellCost1, spellCost2, spellCost3, spellCost4);
                    break;
                }
                case "shaman": {
                    setShamanDamage(list, calc_raw, (percent + melee_percent) * multiple * melee_multiple, (percent + spell_percent) * multiple * spell_multiple,
                            idNumbers, melee, atkSpd, sp, abilityBuffs, tb, tbd, critBoost, intelligence, spellCost1, spellCost2, spellCost3, spellCost4);
                    break;
                }
            }
        }
        SwingUtilities.updateComponentTreeUI(pane);
    }

    public void setWarrior(TreeBase tree, AbilityBuffs abilityBuffs) {
        for (TreeCheckBox tcb : tree.getTcb()) {
            if (tcb.isSelected()) {
                switch (tcb.getFixedTreeName()) {
                    case "Uncontainable Corruption":
                        tb[AbilityIdEnum.UNOBTAINABLE_CORRUPTION.pos] = true;
                        break;
                    case "Better Enraged Blow":
                        tb[AbilityIdEnum.BETTER_ENRAGED_BLOW.pos] = true;
                        break;
                }
            }
            if (tb[AbilityIdEnum.UNOBTAINABLE_CORRUPTION.pos] && tb[AbilityIdEnum.BETTER_ENRAGED_BLOW.pos]) break;
        }
        for (TreeCheckBox tcb : tree.getTcb()) {
            if (tcb.isSelected()) {
                switch (tcb.getAPIName()) {
                    case "bashCost1":
                        spellCost1 -= 10;
                        break;
                    case "bashCost2":
                        spellCost1 -= 5;
                        break;
                }

                switch (tcb.getFixedTreeName()) {
                    case "Spear Proficiency I":
                    case "Spear Proficiency II":
                        proficiency += 0.05F;
                        break;
                    case "Vehement":
                        raw_Damage += 5;
                        break;
                    case "Cheaper Charge":
                    case "Spirit of the Rabbit":
                        spellCost2 -= 5;
                        break;
                    case "Bak'al's Grasp": //Corrupted
                        if (abilityBuffs.getSlider().get(AbilityBuffsEnum.CORRUPTED.getPos()).getValue() > 1) {
                            if (tb[AbilityIdEnum.UNOBTAINABLE_CORRUPTION.pos]) { //Uncontainable Corruption
                                raw_Damage += Math.min((int) Math.floor(abilityBuffs.getSlider().get(AbilityBuffsEnum.CORRUPTED.getPos()).getValue() / 2F) * 5, 120);
                            } else {
                                raw_Damage += Math.min((int) Math.floor(abilityBuffs.getSlider().get(AbilityBuffsEnum.CORRUPTED.getPos()).getValue() / 2F) * 4, 120);
                            }
                        }
                        break;
                    case "Half-Moon Swipe":
                        tbd[AbilityIdEnum.HALF_MOON_SWIPE.pos] = true;
                    case "Cheaper Uppercut":
                        spellCost3 -= 5;
                        break;
                    case "Enraged Blow": //Corrupted
                        if (abilityBuffs.getSlider().get(AbilityBuffsEnum.CORRUPTED.getPos()).getValue() > 0) {
                            if (tb[AbilityIdEnum.BETTER_ENRAGED_BLOW.pos]) { //Better Enraged Blow
                                multiple *= Math.min(1F + abilityBuffs.getSlider().get(AbilityBuffsEnum.CORRUPTED.getPos()).getValue() * 0.015F, 2.4F);
                            } else {
                                multiple *= Math.min(1F + abilityBuffs.getSlider().get(AbilityBuffsEnum.CORRUPTED.getPos()).getValue() * 0.015F, 1.8F);
                            }
                        }
                        break;
                    case "Ragnarokkr":
                        spellCost4 += 10;
                        break;
                    case "Cheaper War Scream":
                        spellCost4 -= 5;
                        break;
                    case "Axe Kick":
                        tbd[AbilityIdEnum.AXE_KICK.pos] = true;
                        spellCost3 += 15;
                        break;
                    case "Discombobulate": //Maybe Raw ** Damage
                        if (abilityBuffs.getSlider().get(AbilityBuffsEnum.DISCOMBOBULATE.getPos()).getValue() > 0) {
                            if (neutralMin != 0 || neutralMax != 0) {
                                neutralMin += abilityBuffs.getSlider().get(AbilityBuffsEnum.DISCOMBOBULATE.getPos()).getValue();
                                neutralMax += abilityBuffs.getSlider().get(AbilityBuffsEnum.DISCOMBOBULATE.getPos()).getValue();
                            }
                            if (earthMin != 0 || earthMax != 0) {
                                earthMin += abilityBuffs.getSlider().get(AbilityBuffsEnum.DISCOMBOBULATE.getPos()).getValue();
                                earthMax += abilityBuffs.getSlider().get(AbilityBuffsEnum.DISCOMBOBULATE.getPos()).getValue();
                            }
                            if (thunderMin != 0 || thunderMax != 0) {
                                thunderMin += abilityBuffs.getSlider().get(AbilityBuffsEnum.DISCOMBOBULATE.getPos()).getValue();
                                thunderMax += abilityBuffs.getSlider().get(AbilityBuffsEnum.DISCOMBOBULATE.getPos()).getValue();
                            }
                            if (waterMin != 0 || waterMax != 0) {
                                waterMin += abilityBuffs.getSlider().get(AbilityBuffsEnum.DISCOMBOBULATE.getPos()).getValue();
                                waterMax += abilityBuffs.getSlider().get(AbilityBuffsEnum.DISCOMBOBULATE.getPos()).getValue();
                            }
                            if (fireMin != 0 || fireMax != 0) {
                                fireMin += abilityBuffs.getSlider().get(AbilityBuffsEnum.DISCOMBOBULATE.getPos()).getValue();
                                fireMax += abilityBuffs.getSlider().get(AbilityBuffsEnum.DISCOMBOBULATE.getPos()).getValue();
                            }
                            if (airMin != 0 || airMax != 0) {
                                airMin += abilityBuffs.getSlider().get(AbilityBuffsEnum.DISCOMBOBULATE.getPos()).getValue();
                                airMax += abilityBuffs.getSlider().get(AbilityBuffsEnum.DISCOMBOBULATE.getPos()).getValue();
                            }
                        }
                        break;
                    case "Bash":
                        tbd[AbilityIdEnum.BASH.pos] = true;
                        break;
                    case "Double Bash":
                        tbd[AbilityIdEnum.DOUBLE_BASH.pos] = true;
                        break;
                    case "Charge":
                        tbd[AbilityIdEnum.CHARGE.pos] = true;
                        break;
                    case "Uppercut":
                        tbd[AbilityIdEnum.UPPERCUT.pos] = true;
                        break;
                    case "War Scream":
                        tbd[AbilityIdEnum.WAR_SCREAM.pos] = true;
                        break;
                    case "Heavy Impact":
                        tbd[AbilityIdEnum.HEAVY_IMPACT.pos] = true;
                        break;
                    case "Quadruple Bash":
                        tbd[AbilityIdEnum.QUADRUPLE_BASH.pos] = true;
                        break;
                    case "Fireworks":
                        tbd[AbilityIdEnum.FIREWORKS.pos] = true;
                        break;
                    case "Flyby Jab":
                        tbd[AbilityIdEnum.FLYBY_JAB.pos] = true;
                        break;
                    case "Flaming Uppercut":
                        tbd[AbilityIdEnum.FLAMING_UPPERCUT.pos] = true;
                        break;
                    case "Iron Lungs":
                        tbd[AbilityIdEnum.IRON_LUNGS.pos] = true;
                        break;
                    case "Air Shout":
                        tbd[AbilityIdEnum.AIR_SHOUT.pos] = true;
                        break;
                    case "Counter":
                        tbd[AbilityIdEnum.COUNTER.pos] = true;
                        break;
                    case "Flying Kick":
                        tbd[AbilityIdEnum.FLYING_KICK.pos] = true;
                        break;
                    case "Boiling Blood":
                        tbd[AbilityIdEnum.BOILING_BLOOD.pos] = true;
                        break;
                    case "Stronger Bash":
                        tbd[AbilityIdEnum.STRONGER_BASH.pos] = true;
                        break;
                    case "Collide":
                        tbd[AbilityIdEnum.COLLIDE.pos] = true;
                        break;
                    case "Comet":
                        tbd[AbilityIdEnum.COMET.pos] = true;
                        break;
                    case "Whirlwind Strike":
                        tbd[AbilityIdEnum.WHIRLWIND_STRIKE.pos] = true;
                        break;
                    case "Shield Strike":
                        tbd[AbilityIdEnum.SHIELD_STRIKE.pos] = true;
                        break;
                    case "Sparkling Hope":
                        tbd[AbilityIdEnum.SPARKLING_HOPE.pos] = true;
                        break;
                    case "Tempest":
                        tbd[AbilityIdEnum.TEMPEST.pos] = true;
                        break;
                    case "Cyclone":
                        tbd[AbilityIdEnum.CYCLONE.pos] = true;
                        break;
                    case "Thunderclap":
                        tbd[AbilityIdEnum.THUNDERCLAP.pos] = true;
                        break;
                    case "Sacred Surge":
                        tbd[AbilityIdEnum.SACRED_SURGE.pos] = true;
                        break;
                }
            }
        }
    }

    public void setAssassin(TreeBase tree, AbilityBuffs abilityBuffs) {
        for (TreeCheckBox tcb : tree.getTcb()) {
            if (tcb.isSelected()) {
                if (tcb.getFixedTreeName().equals("Ambush")) {
                    tb[AbilityIdEnum.AMBUSH.pos] = true;
                    break;
                }
                //switch (tcb.getFixedTreeName()) {
                //    case "Ambush":
                //        tb[AbilityIDEnum.AMBUSH.pos] = true;
                //        break;
                //}
            }
            //if (tb[AbilityIDEnum.AMBUSH.pos]) break;
        }
        for (TreeCheckBox tcb : tree.getTcb()) {
            if (tcb.isSelected()) {
                switch (tcb.getAPIName()) {
                    case "spinAttackCost1":
                        spellCost1 -= 10;
                        break;
                    case "spinAttackCost2":
                        spellCost1 -= 5;
                        break;
                }
                switch (tcb.getFixedTreeName()) {
                    case "Dagger Proficiency I":
                        proficiency += 0.05F;
                        break;
                    case "Cheaper Dash":
                        spellCost2 -= 5;
                        break;
                    case "Backstab":
                        tbd[AbilityIdEnum.BACKSTAB.pos] = true;
                    case "Cheaper Multihit":
                        spellCost3 -= 5;
                        break;
                    case "Dagger Proficiency II":
                        raw_Damage += 5;
                        break;
                    case "Cheaper Smoke Bomb":
                        spellCost4 -= 5;
                        break;
                    case "Surprise Strike":
                        if (abilityBuffs.getBox().get(AbilityBuffsEnum.SURPRISE_STRIKE.getPos()).isSelected()) {
                            if (tb[AbilityIdEnum.AMBUSH.pos]) { //Ambush
                                multiple *= 2.2F;
                            } else {
                                multiple *= 1.8F;
                            }
                        }
                        break;
                    case "Delirious Gas":
                        if (abilityBuffs.getBox().get(AbilityBuffsEnum.DELIRIOUS_GAS.getPos()).isSelected()) {
                            multiple *= 1.4F;
                        }
                        break;
                    case "Flow State":
                        if (abilityBuffs.getBox().get(AbilityBuffsEnum.FLOW_STATE.getPos()).isSelected()) {
                            multiple *= 1.5F;
                        }
                        break;
                    case "Echo":
                        tbd[AbilityIdEnum.ECHO.pos] = true;
                        break;
                    case "Parry":
                        if (abilityBuffs.getBox().get(AbilityBuffsEnum.PARRY.getPos()).isSelected()) {
                            multiple *= 1.3F;
                        }
                        break;
                    case "Nightcloak Knife":
                        if (abilityBuffs.getSlider().get(AbilityBuffsEnum.NIGHTCLOAK_KNIFE.getPos()).getValue() > 0) {
                            multiple *= 1F + (abilityBuffs.getSlider().get(AbilityBuffsEnum.NIGHTCLOAK_KNIFE.getPos()).getValue() * 0.04F);
                        }
                        break;
                    case "Spin Attack":
                        tbd[AbilityIdEnum.SPIN_ATTACK.pos] = true;
                        break;
                    case "Double Spin":
                        tbd[AbilityIdEnum.DOUBLE_SPIN.pos] = true;
                        break;
                    case "Dash":
                        tbd[AbilityIdEnum.DASH.pos] = true;
                        break;
                    case "Double Slice":
                        tbd[AbilityIdEnum.DOUBLE_SLICE.pos] = true;
                        break;
                    case "Smoke Bomb":
                        tbd[AbilityIdEnum.SMOKE_BOMB.pos] = true;
                        break;
                    case "Multihit":
                        tbd[AbilityIdEnum.MULTIHIT.pos] = true;
                        break;
                    case "Fatality":
                        tbd[AbilityIdEnum.FATALITY.pos] = true;
                        break;
                    case "Sticky Bomb":
                        tbd[AbilityIdEnum.STICKY_BOMB.pos] = true;
                        break;
                    case "Lacerate":
                        tbd[AbilityIdEnum.LACERATE.pos] = true;
                        break;
                    case "Wall of Smoke":
                        tbd[AbilityIdEnum.WALL_OF_SMOKE.pos] = true;
                        break;
                    case "Bamboozle":
                        tbd[AbilityIdEnum.BAMBOOZLE.pos] = true;
                        break;
                    case "Blazing Powder":
                        tbd[AbilityIdEnum.BLAZING_POWDER.pos] = true;
                        break;
                    case "Violent Vortex":
                        tbd[AbilityIdEnum.VIOLENT_VORTEX.pos] = true;
                        break;
                    case "Stronger Multihit":
                        tbd[AbilityIdEnum.STRONGER_MULTIHIT.pos] = true;
                        break;
                    case "Shurikens":
                        tbd[AbilityIdEnum.SHURIKENS.pos] = true;
                        break;
                    case "Dancing Blade":
                        tbd[AbilityIdEnum.DANCING_BLADE.pos] = true;
                        break;
                    case "Fatal Spin":
                        tbd[AbilityIdEnum.FATAL_SPIN.pos] = true;
                        break;
                    case "Pirouette":
                        tbd[AbilityIdEnum.PIROUETTE.pos] = true;
                        break;
                    case "Stronger Lacerate":
                        tbd[AbilityIdEnum.STRONGER_LACERATE.pos] = true;
                        break;
                    case "Stronger Vortex":
                        tbd[AbilityIdEnum.STRONGER_VORTEX.pos] = true;
                        break;
                    case "Blade Fury":
                        tbd[AbilityIdEnum.BLADE_FURY.pos] = true;
                        break;
                    case "Jasmine Bloom":
                        tbd[AbilityIdEnum.JASMINE_BLOOM.pos] = true;
                        break;
                    case "Satsujin":
                        if (abilityBuffs.getBox().get(AbilityBuffsEnum.SATSUJIN.getPos()).isSelected()) {
                            tbd[AbilityIdEnum.SATSUJIN.pos] = true;
                        }
                        break;
                    case "Stronger Clones":
                        tbd[AbilityIdEnum.STRONGER_CLONES.pos] = true;
                        break;
                    case "Even Stronger Clones":
                        tbd[AbilityIdEnum.EVEN_STRONGER_CLONES.pos] = true;
                        break;
                    case "Forbidden Art":
                        tbd[AbilityIdEnum.FORBIDDEN_ART.pos] = true;
                        break;
                }
            }
        }
    }

    public void setArcher(TreeBase tree,  AbilityBuffs abilityBuffs) {
        for (TreeCheckBox tcb : tree.getTcb()) {
            if (tcb.isSelected()) {
                switch (tcb.getAPIName()) {
                    case "arrowBombCost1":
                        spellCost3 -= 10;
                        break;
                    case "arrowBombCost2":
                        spellCost3 -= 5;
                        break;
                }
                switch (tcb.getFixedTreeName()) {
                    case "Bow Proficiency I":
                        proficiency += 0.05F;
                        break;
                    case "Cheaper Escape":
                        spellCost2 -= 5;
                        break;
                    case "Cheaper Arrow Storm":
                        spellCost1 -= 5;
                        break;
                    case "Mana Trap":
                        spellCost3 += 10;
                        break;
                    case "Cheaper Arrow Shield":
                        spellCost4 -= 5;
                        break;
                    case "Focus":
                        tbd[AbilityIdEnum.FOCUS.pos] = true;
                        if (abilityBuffs.getSlider().get(AbilityBuffsEnum.FOCUS.getPos()).getValue() > 0)
                            multiple *= 1F + abilityBuffs.getSlider().get(AbilityBuffsEnum.FOCUS.getPos()).getValue() * 0.15F;
                        break;
                    case "Initiator":
                        if (abilityBuffs.getBox().get(AbilityBuffsEnum.INITIATOR.getPos()).isSelected()) {
                            multiple *= 1.6F;
                        }
                        break;
                    case "Arrow Bomb":
                        tbd[AbilityIdEnum.ARROW_BOMB.pos] = true;
                        break;
                    case "Heart Shatter":
                        tbd[AbilityIdEnum.HEART_SHATTER.pos] = true;
                        break;
                    case "Double Shots":
                        tbd[AbilityIdEnum.DOUBLE_SHOTS.pos] = true;
                        break;
                    case "Escape":
                        tbd[AbilityIdEnum.ESCAPE.pos] = true;
                        break;
                    case "Arrow Storm":
                        tbd[AbilityIdEnum.ARROW_STORM.pos] = true;
                        break;
                    case "Arrow Shield":
                        tbd[AbilityIdEnum.ARROW_SHIELD.pos] = true;
                        break;
                    case "Arrow Rain":
                        tbd[AbilityIdEnum.ARROW_RAIN.pos] = true;
                        break;
                    case "Nimble String":
                        tbd[AbilityIdEnum.NIMBLE_STRING.pos] = true;
                        break;
                    case "Phantom Ray":
                        tbd[AbilityIdEnum.PHANTOM_RAY.pos] = true;
                        break;
                    case "Fire Creep":
                        tbd[AbilityIdEnum.FIRE_CREEP.pos] = true;
                        break;
                    case "Bryophyte Roots":
                        tbd[AbilityIdEnum.BRYOPHYTE_ROOTS.pos] = true;
                        break;
                    case "Triple Shots":
                        tbd[AbilityIdEnum.TRIPLE_SHOTS.pos] = true;
                        break;
                    case "Guardian Angels":
                        tbd[AbilityIdEnum.GUARDIAN_ANGELS.pos] = true;
                        break;
                    case "Basaltic Trap":
                        tbd[AbilityIdEnum.BASALTIC_TRAP.pos] = true;
                        break;
                    case "Windstorm":
                        tbd[AbilityIdEnum.WINDSTORM.pos] = true;
                        break;
                    case "More Shields":
                        tbd[AbilityIdEnum.MORE_SHIELDS.pos] = true;
                        break;
                    case "Implosion":
                        tbd[AbilityIdEnum.IMPLOSION.pos] = true;
                        break;
                    case "Refined Gunpowder":
                        tbd[AbilityIdEnum.REFINED_GUNPOWDER.pos] = true;
                        break;
                    case "Twain's Arc":
                        tbd[AbilityIdEnum.TWAINS_ARC.pos] = true;
                        break;
                    case "Fierce Stomp":
                        tbd[AbilityIdEnum.FIERCE_STOMP.pos] = true;
                        break;
                    case "Scorched Earth":
                        tbd[AbilityIdEnum.SCORCHED_EARTH.pos] = true;
                        break;
                    case "Shocking Bomb":
                        tbd[AbilityIdEnum.SHOCKING_BOMB.pos] = true;
                        break;
                    case "Better Arrow Shield":
                        tbd[AbilityIdEnum.BETTER_ARROW_SHIELD.pos] = true;
                        break;
                    case "Escape Artist":
                        tbd[AbilityIdEnum.ESCAPE_ARTIST.pos] = true;
                        break;
                    case "Call of the Hound":
                        tbd[AbilityIdEnum.CALL_OF_THE_HOUND.pos] = true;
                        break;
                    case "Arrow Hurricane":
                        tbd[AbilityIdEnum.ARROW_HURRICANE.pos] = true;
                        break;
                    case "Shrapnel Bomb":
                        tbd[AbilityIdEnum.SHARPNEL_BOMB.pos] = true;
                        break;
                    case "Geyser Stomp":
                        tbd[AbilityIdEnum.GEYSER_STOMP.pos] = true;
                        break;
                    case "Crepuscular Ray":
                        tbd[AbilityIdEnum.CREPUSCULAR_RAY.pos] = true;
                        break;
                    case "Tangled Traps":
                        tbd[AbilityIdEnum.TANGLED_TRAPS.pos] = true;
                        break;
                    case "Minefield":
                        tbd[AbilityIdEnum.MINEFIELD.pos] = true;
                        break;
                    case "All-Seeing Panoptes":
                        tbd[AbilityIdEnum.ALL_SEEING_PANOPTES.pos] = true;
                        break;
                    case "Grape Bomb":
                        tbd[AbilityIdEnum.GRAPE_BOMB.pos] = true;
                        break;
                    case "Better Guardian Angels":
                        tbd[AbilityIdEnum.BETTER_GUARDIAN_ANGELS.pos] = true;
                        break;
                    case "Patient Hunter":
                        tbd[AbilityIdEnum.PATIENT_HUNTER.pos] = true;
                        break;
                    case "Decimator":
                        tbd[AbilityIdEnum.DECIMATOR.pos] = true;
                        break;
                    case "Phasing Beam":
                        tbd[AbilityIdEnum.PHASING_BEAM.pos] = true;
                        break;
                    case "Ivyroot Mamba":
                        tbd[AbilityIdEnum.IVYROOT_MAMBA.pos] = true;
                        break;
                    case "Murder Flock":
                        tbd[AbilityIdEnum.MURDER_FLOCK.pos] = true;
                        break;
                    case "Beast Lore":
                        tbd[AbilityIdEnum.BEAST_LORE.pos] = true;
                        break;
                    case "Rocket Jump":
                        tbd[AbilityIdEnum.ROCKET_JUMP.pos] = true;
                        break;
                }
            }
        }
    }

    public void setMage(TreeBase tree, AbilityBuffs abilityBuffs) {
        for (TreeCheckBox tcb : tree.getTcb()) {
            if (tcb.isSelected()) {
                switch (tcb.getAPIName()) {
                    case "meteorCost1":
                        spellCost3 -= 10;
                        break;
                    case "meteorCost2":
                        spellCost3 -= 5;
                        break;
                }
                switch (tcb.getFixedTreeName()) {
                    case "Wand Proficiency I":
                    case "Wand Proficiency II":
                        proficiency += 0.05F;
                        break;
                    case "Cheaper Teleport":
                        spellCost2 -= 5;
                        break;
                    case "Cheaper Heal":
                        spellCost1 -= 5;
                        break;
                    case "Cheaper Ice Snake":
                        spellCost4 -= 5;
                        break;
                    case "Ophanim":
                        spellCost3 += 30;
                        tbd[AbilityIdEnum.OPHANIM.pos] = true;
                        break;
                    case "Meteor":
                        tbd[AbilityIdEnum.METEOR.pos] = true;
                        break;
                    case "Teleport":
                        tbd[AbilityIdEnum.TELEPORT.pos] = true;
                        break;
                    case "Heal":
                        tbd[AbilityIdEnum.HEAL.pos] = true;
                        break;
                    case "Ice Snake":
                        tbd[AbilityIdEnum.ICE_SNAKE.pos] = true;
                        break;
                    case "Wind Slash":
                        tbd[AbilityIdEnum.WIND_SLASH.pos] = true;
                        break;
                    case "Thunderstorm":
                        tbd[AbilityIdEnum.THUNDERSTORM.pos] = true;
                        break;
                    case "Burning Sigil":
                        tbd[AbilityIdEnum.BURNING_SIGIL.pos] = true;
                        break;
                    case "Sunshower":
                        tbd[AbilityIdEnum.SUNSHOWER.pos] = true;
                        break;
                    case "Stronger Meteor":
                        tbd[AbilityIdEnum.STRONGER_METEOR.pos] = true;
                        break;
                    case "Windsweeper":
                        tbd[AbilityIdEnum.WINDSWEEPER.pos] = true;
                        break;
                    case "Sentient Snake":
                        tbd[AbilityIdEnum.SENTIENT_SNAKE.pos] = true;
                        break;
                    case "Breathless":
                        tbd[AbilityIdEnum.BREATHLESS.pos] = true;
                        break;
                    case "Pyrokinesis":
                        tbd[AbilityIdEnum.PYROKINESIS.pos] = true;
                        break;
                    case "Snake Nest":
                        tbd[AbilityIdEnum.SNAKE_NEST.pos] = true;
                        break;
                    case "Transonic Warp":
                        tbd[AbilityIdEnum.TRANSONIC_WARP.pos] = true;
                        break;
                    case "Stronger Ophanim":
                        tbd[AbilityIdEnum.STRONGER_OPHANIM.pos] = true;
                        break;
                    case "Lightweaver":
                        tbd[AbilityIdEnum.LIGHTWEAVER.pos] = true;
                        break;
                    case "Explosive Entrance":
                        tbd[AbilityIdEnum.EXPLOSIVE_ENTRANCE.pos] = true;
                        break;
                    case "Gust":
                        tbd[AbilityIdEnum.GUST.pos] = true;
                        break;
                    case "Stronger Sunshower":
                        tbd[AbilityIdEnum.STRONGER_SUNSHOWER.pos] = true;
                        break;
                    case "Divination":
                        tbd[AbilityIdEnum.DIVINATION.pos] = true;
                        break;
                    case "Fluid Healing":
                        tbd[AbilityIdEnum.FLUID_HEALING_MAGE.pos] = true;
                        break;
                    case "Orphion's Pulse":
                        tbd[AbilityIdEnum.ORPHIONS_PULSE.pos] = true;
                        break;
                    case "Arcane Transfer":
                        tbd[AbilityIdEnum.ARCANE_TRANSFER.pos] = true;
                        break;
                }
            }
        }
    }

    public void setShaman(TreeBase tree, AbilityBuffs abilityBuffs) {
        for (TreeCheckBox tcb : tree.getTcb()) {
            if (tcb.isSelected()) {
                switch (tcb.getAPIName()) {
                    case "totemCost1":
                        spellCost1 -= 10;
                        break;
                    case "totemCost2":
                        spellCost1 -= 5;
                        break;
                    case "morePuppets":
                        tbd[AbilityIdEnum.MORE_PUPPETS_1.pos] = true;
                        break;
                    case "morePuppets2":
                        tbd[AbilityIdEnum.MORE_PUPPETS_2.pos] = true;
                        break;
                }
                switch (tcb.getFixedTreeName()) {
                    case "Relik Proficiency I":
                        proficiency += 0.05F;
                        break;
                    case "Cheaper Haul":
                        spellCost2 -= 5;
                        break;
                    case "Cheaper Uproot":
                        spellCost4 -= 5;
                        break;
                    case "Cheaper Aura":
                        spellCost3 -= 5;
                        break;
                    case "Mask of the Lunatic":
                        tbd[AbilityIdEnum.MASKS.pos] = true;
                        if (abilityBuffs.getBox().get(AbilityBuffsEnum.MASK_OF_THE_LUNATIC.getPos()).isSelected()) {
                            multiple *= 1.35F;
                        }
                        break;
                    case "Mask of the Coward":
                        tbd[AbilityIdEnum.MASKS.pos] = true;
                        if (abilityBuffs.getBox().get(AbilityBuffsEnum.MASK_OF_THE_COWARD.getPos()).isSelected()) {
                            multiple *= 0.9F;
                        }
                        break;
                    case "Awakened":
                        if (abilityBuffs.getBox().get(AbilityBuffsEnum.AWAKENED.getPos()).isSelected()) {
                            multiple *= 1.35F;
                        }
                        break;
                    case "Totem":
                        tbd[AbilityIdEnum.TOTEM.pos] = true;
                        break;
                    case "Totemic Smash":
                        tbd[AbilityIdEnum.TOTEMIC_SMASH.pos] = true;
                        break;
                    case "Haul":
                        tbd[AbilityIdEnum.HAUL.pos] = true;
                        break;
                    case "Uproot":
                        tbd[AbilityIdEnum.UPROOT.pos] = true;
                        break;
                    case "Aura":
                        tbd[AbilityIdEnum.AURA.pos] = true;
                        break;
                    case "Nature's Jolt":
                        tbd[AbilityIdEnum.NATURES_JOLT.pos] = true;
                        break;
                    case "Shocking Aura":
                        tbd[AbilityIdEnum.SHOCKING_AURA.pos] = true;
                        break;
                    case "Flaming Tongue":
                        tbd[AbilityIdEnum.FLAMING_TONGUE.pos] = true;
                        break;
                    case "Rain Dance":
                        tbd[AbilityIdEnum.RAIN_DANCE.pos] = true;
                        break;
                    case "Puppet Master":
                        tbd[AbilityIdEnum.PUPPET_MASTER.pos] = true;
                        break;
                    case "Exploding Puppets":
                        tbd[AbilityIdEnum.EXPLODING_PUPPETS.pos] = true;
                        break;
                    case "Double Totem":
                        tbd[AbilityIdEnum.DOUBLE_TOTEM.pos] = true;
                        break;
                    case "Storm Dance":
                        tbd[AbilityIdEnum.STORM_DANCE.pos] = true;
                        break;
                    case "Twisted Tether":
                        tbd[AbilityIdEnum.TWISTED_TETHER.pos] = true;
                        break;
                    case "Stronger Totem":
                        tbd[AbilityIdEnum.STRONGER_TOTEM.pos] = true;
                        break;
                    case "Crimson Effigy":
                        tbd[AbilityIdEnum.CRIMSON_EFFIGY.pos] = true;
                        break;
                    case "Chant of the Coward":
                        tbd[AbilityIdEnum.CHANT_OF_THE_COWARD.pos] = true;
                        break;
                    case "More Effigies":
                        tbd[AbilityIdEnum.MORE_EFFIGIES.pos] = true;
                        break;
                    case "Stronger Tether":
                        tbd[AbilityIdEnum.STRONGER_TETHER.pos] = true;
                        break;
                    case "Triple Totem":
                        tbd[AbilityIdEnum.TRIPLE_TOTEM.pos] = true;
                        break;
                    case "Frog Dance":
                        tbd[AbilityIdEnum.FROG_DANCE.pos] = true;
                        break;
                    case "Blood Sorrow":
                        tbd[AbilityIdEnum.BLOOD_SORROW.pos] = true;
                        break;
                    case "Hand of the Shaman":
                        tbd[AbilityIdEnum.HAND_OF_THE_SHAMAN.pos] = true;
                        break;
                    case "Regeneration":
                        tbd[AbilityIdEnum.REGENERATION.pos] = true;
                        break;
                    case "Rebound":
                        tbd[AbilityIdEnum.REBOUND.pos] = true;
                        break;
                    case "Sacrificial Shrine":
                        tbd[AbilityIdEnum.SACRIFICIAL_SHRINE.pos] = true;
                        break;
                    case "Fluid Healing":
                        tbd[AbilityIdEnum.FLUID_HEALING_SHAMAN.pos] = true;
                        break;
                    case "Mask of the Fanatic":
                        tbd[AbilityIdEnum.MASKS.pos] = true;
                        break;
                    case "Bullwhip":
                        tbd[AbilityIdEnum.BULLWHIP.pos] = true;
                        break;
                    case "Invigorating Wave":
                        tbd[AbilityIdEnum.INVIGORATING_WAVE.pos] = true;
                        break;
                    case "Shepherd":
                        tbd[AbilityIdEnum.SHEPHERD.pos] = true;
                        break;
                }
            }
        }
    }

    public void setWarriorDamage(List<DamageTemplate> list, CalcRaw calc_raw, float melee_total, float spell_total, int[] id_Numbers, float[] melee, int atkSpd, SkillPoint sp, AbilityBuffs abilityBuffs, boolean[] tb, boolean[] tbd, boolean crit_boost, float intelligence, int spell_cost_1, int spell_cost_2, int spell_cost_3, int spell_cost_4) {
        //Melee
        calcMelee("DPS", list.size() - 1, calc_raw, list, melee_total, id_Numbers, melee, atkSpd, sp, false, true, false);
        calcMelee("Single Hit", list.size() - 1, calc_raw, list, melee_total, id_Numbers, melee, atkSpd, sp, true, false, false);

        //Bash
        if (tbd[AbilityIdEnum.BASH.pos]) {
            list.add(new DamageTemplate("Bash", calcSpellCost(40 + spell_cost_1, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_1ST_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_1ST_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
            float[] bash = setDamagePercent(SpellEnum.BASH);
            if (itemData.getMajorIDList().contains(MajorIdEnum.DIVINE_HONOR)) addDamagePercent(bash, SpellEnum.DIVINE_HONOR); //Divine Honor
            if (itemData.getMajorIDList().contains(MajorIdEnum.OVERWHELM)) addDamagePercent(bash, SpellEnum.OVERWHELM); //Overwhelm
            if (tbd[AbilityIdEnum.DOUBLE_BASH.pos]) addDamagePercent(bash, SpellEnum.DOUBLE_BASH);
            if (tbd[AbilityIdEnum.QUADRUPLE_BASH.pos]) addDamagePercent(bash, SpellEnum.QUADRUPLE_BASH);
            if (tbd[AbilityIdEnum.STRONGER_BASH.pos]) addDamagePercent(bash, SpellEnum.STRONGER_BASH);
            if (tbd[AbilityIdEnum.THUNDERCLAP.pos]) addDamagePercent(bash, SpellEnum.THUNDERCLAP);
            if (tbd[AbilityIdEnum.SACRED_SURGE.pos] && abilityBuffs.getBox().get(AbilityBuffsEnum.SACRED_SURGE.getPos()).isSelected()) addDamagePercent(bash, SpellEnum.SACRED_SURGE);
            calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total, id_Numbers, bash, sp, false, false);
            calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calcTotalDamagePercent(bash), sp, true, false);
        }

        //Charge
        if (tbd[AbilityIdEnum.CHARGE.pos]) {
            list.add(new DamageTemplate("Charge", calcSpellCost(25 + spell_cost_2, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_2ND_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_2ND_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, false));
            if (itemData.getMajorIDList().contains(MajorIdEnum.RALLY)) { //Rally (Major ID)
                list.get(list.size() - 1).addHeal("Heal", id_Numbers, 0.1F, false, false);
                list.get(list.size() - 1).addHeal("Heal to Allies", id_Numbers, 0.15F, false, false);
            } else {
                if (tbd[AbilityIdEnum.HEAVY_IMPACT.pos]) calcSpell("Heavy Impact", list.size() - 1, calc_raw, list, spell_total, id_Numbers, setDamagePercent(SpellEnum.HEAVY_IMPACT), sp, false, false);
                if (tbd[AbilityIdEnum.FLYBY_JAB.pos]) calcSpell("Flyby Jab", list.size() - 1, calc_raw, list, spell_total, id_Numbers, setDamagePercent(SpellEnum.FLYBY_JAB), sp, false, false);
                if (tbd[AbilityIdEnum.FLYING_KICK.pos]) calcSpell("Flying Kick", list.size() - 1, calc_raw, list, spell_total, id_Numbers, setDamagePercent(SpellEnum.FLYING_KICK), sp, false, false);
                if (tbd[AbilityIdEnum.COLLIDE.pos]) calcSpell("Collide", list.size() - 1, calc_raw, list, spell_total, id_Numbers, setDamagePercent(SpellEnum.COLLIDE), sp, false, false);
            }
        }

        //Uppercut
        if (tbd[AbilityIdEnum.UPPERCUT.pos]) {
            list.add(new DamageTemplate("Uppercut", calcSpellCost(40 + spell_cost_3, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_3RD_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_3RD_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
            float[] uppercut = setDamagePercent(SpellEnum.UPPERCUT);
            if (tbd[AbilityIdEnum.HALF_MOON_SWIPE.pos]) addDamagePercent(uppercut, SpellEnum.HALF_MOON_SWIPE);
            if (tbd[AbilityIdEnum.WHIRLWIND_STRIKE.pos]) addDamagePercent(uppercut, SpellEnum.WHIRLWIND_STRIKE);
            if (tbd[AbilityIdEnum.AXE_KICK.pos]) addDamagePercent(uppercut, SpellEnum.AXE_KICK);
            if (tbd[AbilityIdEnum.SACRED_SURGE.pos] && abilityBuffs.getBox().get(AbilityBuffsEnum.SACRED_SURGE.getPos()).isSelected()) addDamagePercent(uppercut, SpellEnum.SACRED_SURGE);
            calcSpell("Uppercut", list.size() - 1, calc_raw, list, spell_total, id_Numbers, uppercut, sp, false, false);
            if (tbd[AbilityIdEnum.FIREWORKS.pos]) {
                addDamagePercent(uppercut, SpellEnum.FIREWORKS);
                calcSpell("Fireworks", list.size() - 1, calc_raw, list, spell_total, id_Numbers, setDamagePercent(SpellEnum.FIREWORKS), sp, false, false);
            }
            if (tbd[AbilityIdEnum.COMET.pos]) {
                addDamagePercent(uppercut, SpellEnum.COMET);
                calcSpell("Comet", list.size() - 1, calc_raw, list, spell_total, id_Numbers, setDamagePercent(SpellEnum.COMET), sp, false, false);
            }
            calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total, id_Numbers, uppercut, sp, true, false);
        }

        //War Scream
        if (tbd[AbilityIdEnum.WAR_SCREAM.pos]) {
            list.add(new DamageTemplate("War Scream", calcSpellCost(30 + spell_cost_4, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_4TH_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_4TH_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, false));
            float[] war_scream = setDamagePercent(SpellEnum.WAR_SCREAM);
            float[] air_shout = setDamagePercent(SpellEnum.AIR_SHOUT);
            if (tbd[AbilityIdEnum.IRON_LUNGS.pos]) {
                addDamagePercent(war_scream, SpellEnum.IRON_LUNGS_FOR_WAR_SCREAM);
                addDamagePercent(air_shout, SpellEnum.IRON_LUNGS_FOR_AIR_SHOUT);
            }
            calcSpell("War Scream", list.size() - 1, calc_raw, list, spell_total, id_Numbers, war_scream, sp, false, false);
            if (tbd[AbilityIdEnum.AIR_SHOUT.pos]) calcSpell("Air Shout", list.size() - 1, calc_raw, list, spell_total, id_Numbers, air_shout, sp, false, false);
            if (tbd[AbilityIdEnum.TEMPEST.pos]) {
                float[] tempest = setDamagePercent(SpellEnum.TEMPEST);
                if (itemData.getMajorIDList().contains(MajorIdEnum.RECKLESS_ABANDON)) addDamagePercent(tempest, SpellEnum.RECKLESS_ABANDON);
                calcSpell("Single Tempest", list.size() - 1, calc_raw, list, spell_total, id_Numbers, tempest, sp, false, false);
                calcSpell("Total Tempest", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calcTotalDamagePercent(tempest), sp, false, false);
            }
        }

        //Flaming Uppercut
        if (tbd[AbilityIdEnum.FLAMING_UPPERCUT.pos]) {
            list.add(new DamageTemplate("Flaming Uppercut", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            float[] flaming_Uppercut = setDamagePercent(SpellEnum.FLAMING_UPPERCUT);
            calcSpell("DPS", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calcTotalDamagePercentManual(flaming_Uppercut, 1.66F), sp, false, false);
            calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total, id_Numbers, flaming_Uppercut, sp, false, false);
            calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calcTotalDamagePercent(flaming_Uppercut), sp, true, false);
        }
        //Counter
        if (tbd[AbilityIdEnum.COUNTER.pos]) {
            list.add(new DamageTemplate("Counter", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            calcSpell("", list.size() - 1, calc_raw, list, spell_total, id_Numbers, setDamagePercent(SpellEnum.COUNTER), sp, false, false);
        }
        //Boiling Blood
        if (tbd[AbilityIdEnum.BOILING_BLOOD.pos]) {
            list.add(new DamageTemplate("Boiling Blood", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            float[] boiling_Blood = setDamagePercent(SpellEnum.BOILING_BLOOD);
            calcSpell("DPS", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calcTotalDamagePercentManual(boiling_Blood, 2.5F), sp, false, false);
            calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total, id_Numbers, boiling_Blood, sp, false, false);
            calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calcTotalDamagePercent(boiling_Blood), sp, true, false);
        }
        //Sparkling Hope
        if (tbd[AbilityIdEnum.SPARKLING_HOPE.pos]) {
            list.add(new DamageTemplate("Sparkling Hope", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            calcSpell("", list.size() - 1, calc_raw, list, spell_total, id_Numbers, setDamagePercent(SpellEnum.SPARKLING_HOPE), sp, false, false);
        }
        //Cyclone
        if (tbd[AbilityIdEnum.CYCLONE.pos]) {
            list.add(new DamageTemplate("Cyclone", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            float[] cyclone = setDamagePercent(SpellEnum.CYCLONE);
            calcSpell("DPS", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calcTotalDamagePercentManual(cyclone, 2F), sp, false, false);
            calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total, id_Numbers, cyclone, sp, false, false);
            calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calcTotalDamagePercent(cyclone), sp, true, false);
        }
        //Shield Strike
        if (tbd[AbilityIdEnum.SHIELD_STRIKE.pos]) {
            list.add(new DamageTemplate("Shield Strike", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            calcSpell("", list.size() - 1, calc_raw, list, spell_total, id_Numbers, setDamagePercent(SpellEnum.SHIELD_STRIKE), sp, false, false);
        }
    }

    public void setAssassinDamage(List<DamageTemplate> list, CalcRaw calc_raw, float melee_total, float spell_total, int[] id_Numbers, float[] melee, int atkSpd, SkillPoint sp, AbilityBuffs abilityBuffs, boolean[] tb, boolean[] tbd, boolean crit_boost, float intelligence, int spell_cost_1, int spell_cost_2, int spell_cost_3, int spell_cost_4) {

        //Echo
        float echo = 1F;
        if (tbd[AbilityIdEnum.ECHO.pos]) {
            echo = 0.35F;
            if (tbd[AbilityIdEnum.STRONGER_CLONES.pos]) echo += 0.15F;
            if (tbd[AbilityIdEnum.EVEN_STRONGER_CLONES.pos]) echo += 0.1F;
            if (tbd[AbilityIdEnum.FORBIDDEN_ART.pos]) echo -= 0.1F;
            if (abilityBuffs.getSlider().get(AbilityBuffsEnum.CLONES.getPos()).getValue() > 0) {
                echo *= abilityBuffs.getSlider().get(AbilityBuffsEnum.CLONES.getPos()).getValue() + 1F;
            }
        }

        //Melee
        String melee_Name = "Single Hit";
        boolean isSingleHit = true;
        if (tbd[AbilityIdEnum.DOUBLE_SLICE.pos]) addDamagePercent(melee, SpellEnum.DOUBLE_SLICE);
        calcMelee("DPS", list.size() - 1, calc_raw, list, melee_total * echo, id_Numbers, calcTotalDamagePercent(melee), atkSpd, sp, false, true, false);
        if (tbd[AbilityIdEnum.DOUBLE_SLICE.pos]) { //Double Slice
            melee_Name = "Total Damage";
            isSingleHit = false;
            calcMelee("Single Hit", list.size() - 1, calc_raw, list, melee_total * echo, id_Numbers, melee, atkSpd, sp, false, false, true);
        }
        calcMelee(melee_Name, list.size() - 1, calc_raw, list, melee_total * echo, id_Numbers, calcTotalDamagePercent(melee), atkSpd, sp, true, false, isSingleHit);

        //Spin Attack
        if (tbd[AbilityIdEnum.SPIN_ATTACK.pos]) {
            if (tbd[AbilityIdEnum.LACERATE.pos]) {
                //Lacerate
                list.add(new DamageTemplate("Lacerate", calcSpellCost(40 + spell_cost_1, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_1ST_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_1ST_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
                float[] lacerate = setDamagePercent(SpellEnum.LACERATE);
                if (tbd[AbilityIdEnum.BLAZING_POWDER.pos]) addDamagePercent(lacerate, SpellEnum.BLAZING_POWDER);
                if (tbd[AbilityIdEnum.STRONGER_LACERATE.pos]) addDamagePercent(lacerate, SpellEnum.STRONGER_LACERATE);
                calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total, id_Numbers, lacerate, sp, false, true);
                calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calcTotalDamagePercent(lacerate), sp, true, false);
            } else {
                //Spin Attack
                list.add(new DamageTemplate("Spin Attack", calcSpellCost(40 + spell_cost_1, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_1ST_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_1ST_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
                float[] spin_Attack = setDamagePercent(SpellEnum.SPIN_ATTACK);
                if (tbd[AbilityIdEnum.DOUBLE_SPIN.pos]) addDamagePercent(spin_Attack, SpellEnum.DOUBLE_SPIN);
                if (tbd[AbilityIdEnum.BLAZING_POWDER.pos]) addDamagePercent(spin_Attack, SpellEnum.BLAZING_POWDER);
                if (tbd[AbilityIdEnum.FATAL_SPIN.pos]) addDamagePercent(spin_Attack, SpellEnum.FATAL_SPIN);
                calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total * echo, id_Numbers, spin_Attack, sp, false, true);
                calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total * echo, id_Numbers, calcTotalDamagePercent(spin_Attack), sp, true, false);
            }
        }

        //Dash
        if (tbd[AbilityIdEnum.DASH.pos]) {
            list.add(new DamageTemplate("Dash", calcSpellCost(20 + spell_cost_2, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_2ND_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_2ND_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, false));
            if (tbd[AbilityIdEnum.DANCING_BLADE.pos]) {
                float[] dancing_Blade = setDamagePercent(SpellEnum.DANCING_BLADE);
                if (tbd[AbilityIdEnum.PIROUETTE.pos]) addDamagePercent(dancing_Blade, SpellEnum.PIROUETTE);
                calcSpell("Dancing Blade", list.size() - 1, calc_raw, list, spell_total * echo, id_Numbers, dancing_Blade, sp, false, true);
            }
        }

        //Multihit
        if (tbd[AbilityIdEnum.MULTIHIT.pos]) {
            float multihit_Boost = spell_total * echo;
            if (tbd[AbilityIdEnum.SATSUJIN.pos]) multihit_Boost *= 2F; //Satsujin
            if (tbd[AbilityIdEnum.BACKSTAB.pos]) {
                if (abilityBuffs.getBox().get(AbilityBuffsEnum.BACKSTAB.getPos()).isSelected()) multihit_Boost *= 2F;
                //Backstab
                list.add(new DamageTemplate("Backstab", calcSpellCost(40 + spell_cost_3, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_3RD_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_3RD_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, false));
                calcSpell("Backstab", list.size() - 1, calc_raw, list, multihit_Boost, id_Numbers, setDamagePercent(SpellEnum.BACKSTAB), sp, false, true);
            } else {
                //Multihit
                list.add(new DamageTemplate("Multihit", calcSpellCost(40 + spell_cost_3, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_3RD_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_3RD_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
                float[] multihit = setDamagePercent(SpellEnum.MULTIHIT);
                if (tbd[AbilityIdEnum.STRONGER_MULTIHIT.pos]) {
                    addDamagePercent(multihit, SpellEnum.STRONGER_MULTIHIT);
                    if (itemData.getMajorIDList().contains(MajorIdEnum.JUGGLE)) addDamagePercent(multihit, SpellEnum.JUGGLE);
                }
                if (tbd[AbilityIdEnum.BLADE_FURY.pos]) addDamagePercent(multihit, SpellEnum.BLADE_FURY);
                calcSpell("Single Hit", list.size() - 1, calc_raw, list, multihit_Boost, id_Numbers, multihit, sp, false, true);
                multihit = calcTotalDamagePercent(multihit);
                if (tbd[AbilityIdEnum.FATALITY.pos]) {
                    addDamagePercent(multihit, SpellEnum.FATALITY);
                    calcSpell("Fatality", list.size() - 1, calc_raw, list, multihit_Boost, id_Numbers, setDamagePercent(SpellEnum.FATALITY), sp, false, true);
                }
                calcSpell("Total Damage", list.size() - 1, calc_raw, list, multihit_Boost, id_Numbers, multihit, sp, true, false);
            }
        }

        //Smoke Bomb (0.5s per Attack)
        if (tbd[AbilityIdEnum.SMOKE_BOMB.pos]) {
            list.add(new DamageTemplate("Smoke Bomb", calcSpellCost(35 + spell_cost_4, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_4TH_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_4TH_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
            float[] smoke_Bomb = setDamagePercent(SpellEnum.SMOKE_BOMB);
            int bomb_count = 1;
            if (tbd[AbilityIdEnum.STICKY_BOMB.pos]) addDamagePercent(smoke_Bomb, SpellEnum.STICKY_BOMB);
            if (tbd[AbilityIdEnum.WALL_OF_SMOKE.pos]) {
                addDamagePercent(smoke_Bomb, SpellEnum.WALL_OF_SMOKE);
                bomb_count = 3;
            }
            if (itemData.getMajorIDList().contains(MajorIdEnum.CHERRY_BOMBS)) { //Cherry Bombs
                addDamagePercent(smoke_Bomb, SpellEnum.CHERRY_BOMBS);
                calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total * echo, id_Numbers, smoke_Bomb, sp, false, false);
                calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total * echo, id_Numbers, calcTotalDamagePercentManual(smoke_Bomb, bomb_count), sp, true, false);
            } else {
                calcSpell("DPS", list.size() - 1, calc_raw, list, spell_total * echo, id_Numbers, calcTotalDamagePercentManual(smoke_Bomb, bomb_count * 2F), sp, false, false);
                calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total * echo, id_Numbers, smoke_Bomb, sp, false, true);
                calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total * echo, id_Numbers, calcTotalDamagePercent(smoke_Bomb), sp, true, false);
            }
        }

        //Bamboozle
        if (tbd[AbilityIdEnum.BAMBOOZLE.pos]) {
            list.add(new DamageTemplate("Bamboozle", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            calcSpell("", list.size() - 1, calc_raw, list, spell_total, id_Numbers, setDamagePercent(SpellEnum.BAMBOOZLE), sp, false, true);
        }

        //Shurikens
        if (tbd[AbilityIdEnum.SHURIKENS.pos]) {
            list.add(new DamageTemplate("Shuriken", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total * echo, id_Numbers, setDamagePercent(SpellEnum.SHURIKENS), sp, false, true);
        }

        //Jasmine Bloom
        if (tbd[AbilityIdEnum.JASMINE_BLOOM.pos]) {
            list.add(new DamageTemplate("Jasmine Bloom", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            float[] jasmine_Bloom = setDamagePercent(SpellEnum.JASMINE_BLOOM);
            calcSpell("DPS", list.size() - 1, calc_raw, list, spell_total * echo, id_Numbers, calcTotalDamagePercentManual(jasmine_Bloom, 3.33F), sp, false, false);
            calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total * echo, id_Numbers, jasmine_Bloom, sp, false, true);
        }

        //Violent Vortex
        if (tbd[AbilityIdEnum.VIOLENT_VORTEX.pos]) {
            list.add(new DamageTemplate("Violent Vortex", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            int total_health = id_Numbers[IdDisplay.ID_INT.get(Identifications.HEALTH)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.HEALTH_BONUS)];
            int max_Damage = totalMaxDamage.getMaxDamage();
            if (tbd[AbilityIdEnum.STRONGER_VORTEX.pos]) { //Stronger Vortex
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
    }

    public void setArcherDamage(List<DamageTemplate> list, CalcRaw calc_raw, float melee_total, float spell_total, int[] id_Numbers, float[] melee, int atkSpd, SkillPoint sp, AbilityBuffs abilityBuffs, boolean[] tb, boolean[] tbd, boolean crit_boost, float intelligence, int spell_cost_1, int spell_cost_2, int spell_cost_3, int spell_cost_4) {
        //Melee
        String melee_Name = "Single Hit";
        if (tbd[AbilityIdEnum.DOUBLE_SHOTS.pos]) addDamagePercent(melee, SpellEnum.DOUBLE_SHOTS);
        if (tbd[AbilityIdEnum.TRIPLE_SHOTS.pos]) addDamagePercent(melee, SpellEnum.TRIPLE_SHOTS);
        calcMelee("DPS", list.size() - 1, calc_raw, list, melee_total, id_Numbers, calcTotalDamagePercent(melee), atkSpd, sp, false, true, false);
        if (tbd[AbilityIdEnum.DOUBLE_SHOTS.pos] || tbd[AbilityIdEnum.TRIPLE_SHOTS.pos]) {
            melee_Name = "Total Damage";
            calcMelee("Single Hit", list.size() - 1, calc_raw, list, melee_total, id_Numbers, melee, atkSpd, sp, false, false, false);
        }
        calcMelee(melee_Name, list.size() - 1, calc_raw, list, melee_total, id_Numbers, calcTotalDamagePercent(melee), atkSpd, sp, true, false, false);

        //Arrow Storm
        if (tbd[AbilityIdEnum.ARROW_STORM.pos]) {
            if (tbd[AbilityIdEnum.PHANTOM_RAY.pos]) {
                //Phantom Ray
                list.add(new DamageTemplate("Phantom Ray", calcSpellCost(35 + spell_cost_1, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_1ST_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_1ST_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
                float[] phantom_Ray = setDamagePercent(SpellEnum.PHANTOM_RAY);
                float phantom_Boost = spell_total;
                if (tbd[AbilityIdEnum.DECIMATOR.pos] && abilityBuffs.getSlider().get(AbilityBuffsEnum.DECIMATOR.getPos()).getValue() > 0) phantom_Boost *= 1F + (0.1F * abilityBuffs.getSlider().get(AbilityBuffsEnum.DECIMATOR.getPos()).getValue()); //Decimator
                calcSpell("Single Hit", list.size() - 1, calc_raw, list, phantom_Boost, id_Numbers, phantom_Ray, sp, false, false);
                calcSpell("Total Damage", list.size() - 1, calc_raw, list, phantom_Boost, id_Numbers, calcTotalDamagePercent(phantom_Ray), sp, true, false);
            } else {
                //Arrow Storm
                list.add(new DamageTemplate("Arrow Storm", calcSpellCost(35 + spell_cost_1, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_1ST_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_1ST_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
                float[] arrow_Storm = setDamagePercent(SpellEnum.ARROW_STORM);
                int stream = 1;
                if (tbd[AbilityIdEnum.NIMBLE_STRING.pos]) addDamagePercent(arrow_Storm, SpellEnum.NIMBLE_STRING);
                if (tbd[AbilityIdEnum.WINDSTORM.pos]) {
                    addDamagePercent(arrow_Storm, SpellEnum.WINDSTORM);
                    stream = 2;
                }
                if (tbd[AbilityIdEnum.ARROW_HURRICANE.pos]) {
                    addDamagePercent(arrow_Storm, SpellEnum.ARROW_HURRICANE);
                    stream = 3;
                }
                if (itemData.getMajorIDList().contains(MajorIdEnum.HAWKEYE)) { //Hawkeye
                    arrow_Storm[0] = 0.1F; //Neutral
                    arrow_Storm[1] = 0F; //Earth
                    arrow_Storm[2] = 0.01F; //Thunder
                    arrow_Storm[3] = 0F; //Water
                    arrow_Storm[4] = 0F; //Fire
                    arrow_Storm[5] = 0.01F; //Air
                }
                calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total, id_Numbers, arrow_Storm, sp, false, false);
                calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calcTotalDamagePercentManual(calcTotalDamagePercent(arrow_Storm), stream), sp, true, false);
            }
        }

        //Escape
        if (tbd[AbilityIdEnum.ESCAPE.pos]) {
            list.add(new DamageTemplate("Escape", calcSpellCost(20 + spell_cost_2, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_2ND_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_2ND_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, false));
            if (tbd[AbilityIdEnum.FIERCE_STOMP.pos]) {
                //Fierce Stomp
                float[] fierce_Stomp = setDamagePercent(SpellEnum.FIERCE_STOMP);
                if (tbd[AbilityIdEnum.GEYSER_STOMP.pos]) addDamagePercent(fierce_Stomp, SpellEnum.GEYSER_STOMP);
                calcSpell("Fierce Stomp", list.size() - 1, calc_raw, list, spell_total, id_Numbers, fierce_Stomp, sp, false, false);
            }
            if (tbd[AbilityIdEnum.ESCAPE_ARTIST.pos]) calcSpell("Escape Artist", list.size() - 1, calc_raw, list, spell_total, id_Numbers, setDamagePercent(SpellEnum.ESCAPE_ARTIST), sp, false, false);
        }

        //Arrow Bomb
        if (tbd[AbilityIdEnum.ARROW_BOMB.pos]) {
            list.add(new DamageTemplate("Arrow Bomb", calcSpellCost(45 + spell_cost_3, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_3RD_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_3RD_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
            float[] arrow_Bomb = setDamagePercent(SpellEnum.ARROW_BOMB);
            if (itemData.getMajorIDList().contains(MajorIdEnum.FORESTS_BLESSING)) addDamagePercent(arrow_Bomb, SpellEnum.FORESTS_BLESSING); //Forest's Blessing
            if (tbd[AbilityIdEnum.REFINED_GUNPOWDER.pos]) addDamagePercent(arrow_Bomb, SpellEnum.REFINED_GUNPOWDER);
            if (tbd[AbilityIdEnum.SHOCKING_BOMB.pos]) addDamagePercent(arrow_Bomb, SpellEnum.SHOCKING_BOMB);
            calcSpell("Arrow Bomb", list.size() - 1, calc_raw, list, spell_total, id_Numbers, arrow_Bomb, sp, false, false);
            if (tbd[AbilityIdEnum.HEART_SHATTER.pos]) {
                //Heart Shatter
                float[] heart_Shatter = setDamagePercent(SpellEnum.HEART_SHATTER);
                if (tbd[AbilityIdEnum.IMPLOSION.pos]) addDamagePercent(heart_Shatter, SpellEnum.IMPLOSION);
                addDamagePercent(arrow_Bomb, heart_Shatter);
                calcSpell("Heart Shatter", list.size() - 1, calc_raw, list, spell_total, id_Numbers, heart_Shatter, sp, false, false);
            }
            if (tbd[AbilityIdEnum.SHARPNEL_BOMB.pos]) {
                //Shrapnel Bomb
                float[] shrapnel_Bomb = setDamagePercent(SpellEnum.SHRAPNEL_BOMB);
                addDamagePercent(arrow_Bomb, shrapnel_Bomb);
                calcSpell("Shrapnel Bomb Single", list.size() - 1, calc_raw, list, spell_total, id_Numbers, shrapnel_Bomb, sp, false, false);
                calcSpell("Shrapnel Bomb Total", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calcTotalDamagePercent(shrapnel_Bomb), sp, false, false);
            }
            if (tbd[AbilityIdEnum.GRAPE_BOMB.pos]){
                //Grape Bomb
                float[] grape_Bomb = setDamagePercent(SpellEnum.GRAPE_BOMB);
                addDamagePercent(arrow_Bomb, grape_Bomb);
                calcSpell("Grape Bomb Single", list.size() - 1, calc_raw, list, spell_total, id_Numbers, grape_Bomb, sp, false, false);
                calcSpell("Grape Bomb Total", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calcTotalDamagePercent(grape_Bomb), sp, false, false);
            }
            calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total, id_Numbers, arrow_Bomb, sp, true, false);
            float[] bomb_self_damage = setDamagePercent(SpellEnum.ARROW_BOMB_SELF_DAMAGE);
            if (tbd[AbilityIdEnum.ROCKET_JUMP.pos]) addDamagePercent(bomb_self_damage, SpellEnum.ROCKET_JUMP);
            calcSpell("Self Damage", list.size() - 1, calc_raw, list, spell_total, id_Numbers, bomb_self_damage, sp, false, false);
        }

        //Arrow Shield
        if (tbd[AbilityIdEnum.ARROW_SHIELD.pos]) {
            if (tbd[AbilityIdEnum.GUARDIAN_ANGELS.pos]) {
                //Guardian Angels
                list.add(new DamageTemplate("Guardian Angels", calcSpellCost(30 + spell_cost_4, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_4TH_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_4TH_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
                float[] guardian_Angels = setDamagePercent(SpellEnum.GUARDIAN_ANGELS);
                int times = 8;
                if (tbd[AbilityIdEnum.MORE_SHIELDS.pos]) addDamagePercent(guardian_Angels, SpellEnum.MORE_SHIELDS_GUARDIAN_ANGELS);
                if (tbd[AbilityIdEnum.BETTER_ARROW_SHIELD.pos]) addDamagePercent(guardian_Angels, SpellEnum.BETTER_ARROW_SHIELD_GUARDIAN_ANGELS);
                if (tbd[AbilityIdEnum.BETTER_GUARDIAN_ANGELS.pos]) times += 4; //Better Guardian Angels
                if (tbd[AbilityIdEnum.ALL_SEEING_PANOPTES.pos]){ //All-Seeing Panoptes
                    times += 4;
                    addDamagePercent(guardian_Angels, SpellEnum.ALL_SEEING_PANOPTES);
                    calcSpell("DPS", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calcTotalDamagePercentManual(calcTotalDamagePercent(guardian_Angels), 2F), sp, false, false);
                } else {
                    calcSpell("DPS", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calcTotalDamagePercentManual(calcTotalDamagePercent(guardian_Angels), 1.66F), sp, false, false);
                }
                calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total, id_Numbers, guardian_Angels, sp, false, false);
                calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calcTotalDamagePercentManual(calcTotalDamagePercent(guardian_Angels), times), sp, true, false);
            } else {
                //Arrow Shield
                list.add(new DamageTemplate("Arrow Shield", calcSpellCost(30 + spell_cost_4, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_4TH_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_4TH_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
                float[] arrow_Shield = setDamagePercent(SpellEnum.ARROW_SHIELD);
                if (tbd[AbilityIdEnum.MORE_SHIELDS.pos]) addDamagePercent(arrow_Shield, SpellEnum.MORE_SHIELDS_ARROW_SHIELD);
                if (tbd[AbilityIdEnum.BETTER_ARROW_SHIELD.pos]) addDamagePercent(arrow_Shield, SpellEnum.BETTER_ARROW_SHIELD_ARROW_SHIELD);
                calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total, id_Numbers, arrow_Shield, sp, false, false);
                calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calcTotalDamagePercent(arrow_Shield), sp, true, false);
            }
        }

        //Arrow Rain
        if (tbd[AbilityIdEnum.ARROW_RAIN.pos]) {
            list.add(new DamageTemplate("Arrow Rain", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            calcSpell("", list.size() - 1, calc_raw, list, spell_total, id_Numbers, setDamagePercent(SpellEnum.ARROW_RAIN), sp, false, false);
        }

        //Fire Creep (0.4s per Attack)
        if (tbd[AbilityIdEnum.FIRE_CREEP.pos]) {
            list.add(new DamageTemplate("Fire Creep", 0, pane, list.get(list.size() - 1), sp, crit_boost, true));
            float[] fire_Creep = setDamagePercent(SpellEnum.FIRE_CREEP);
            if (tbd[AbilityIdEnum.SCORCHED_EARTH.pos]) addDamagePercent(fire_Creep, SpellEnum.SCORCHED_EARTH);
            calcSpell("DPS", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calcTotalDamagePercentManual(fire_Creep, 2.5F), sp, false, false);
            calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total, id_Numbers, fire_Creep, sp, false, false);
            calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calcTotalDamagePercent(fire_Creep), sp, true, false);
        }

        //Byrophyte Roots (0.4s per Attack)
        if (tbd[AbilityIdEnum.BRYOPHYTE_ROOTS.pos]) {
            list.add(new DamageTemplate("Bryophyte Roots", 0, pane, list.get(list.size() - 1), sp, crit_boost, true));
            float[] bryophyte_Roots = setDamagePercent(SpellEnum.BRYOPHYTE_ROOTS);
            calcSpell("DPS", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calcTotalDamagePercentManual(bryophyte_Roots, 2.5F), sp, false, false);
            calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total, id_Numbers, bryophyte_Roots, sp, false, false);
            calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calcTotalDamagePercent(bryophyte_Roots), sp, true, false);
        }

        //Basaltic Trap
        if (tbd[AbilityIdEnum.BASALTIC_TRAP.pos]) {
            list.add(new DamageTemplate("Basaltic Trap", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            float[] basaltic_Trap = setDamagePercent(SpellEnum.BASALTIC_TRAP);
            float trap_Boost = spell_total;
            if (tbd[AbilityIdEnum.MINEFIELD.pos]) addDamagePercent(basaltic_Trap, SpellEnum.MINEFIELD);
            if (tbd[AbilityIdEnum.PATIENT_HUNTER.pos]) trap_Boost *= 1F + (abilityBuffs.getSlider().get(AbilityBuffsEnum.PATIENT_HUNTER.getPos()).getValue() * 0.01F); //Patient Hunter
            calcSpell("Single Hit", list.size() - 1, calc_raw, list, trap_Boost, id_Numbers, basaltic_Trap, sp, false, false);
        }

        //Twain's Arc
        if (tbd[AbilityIdEnum.TWAINS_ARC.pos]) {
            list.add(new DamageTemplate("Twain's Arc", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            float[] twains_arc = setDamagePercent(SpellEnum.TWAINS_ARC);
            calcMelee("Single Hit", list.size() - 1, calc_raw, list, melee_total, id_Numbers, twains_arc, atkSpd, sp, false, true, false);
            if (tbd[AbilityIdEnum.PHASING_BEAM.pos]) {
                addDamagePercent(twains_arc, SpellEnum.PHASING_BEAM);
                calcMelee("After Piercing", list.size() - 1, calc_raw, list, melee_total, id_Numbers, twains_arc, atkSpd, sp, false, true, false);
            }
        }

        //Call of the Hound (0.25s per Attack) TODO apply Forest's Blessing
        if (tbd[AbilityIdEnum.CALL_OF_THE_HOUND.pos]) {
            list.add(new DamageTemplate("Call of the Hound", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            float[] call_of_the_hound = setDamagePercent(SpellEnum.CALL_OF_THE_HOUND);
            if (tbd[AbilityIdEnum.BEAST_LORE.pos]) addDamagePercent(call_of_the_hound, SpellEnum.BEAST_LORE);
            calcSpell("DPS", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calcTotalDamagePercentManual(call_of_the_hound, 4F), sp, false, false);
            calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total, id_Numbers, call_of_the_hound, sp, false, false);
        }

        //Crepuscular Ray (0.05s per Attack)
        if (tbd[AbilityIdEnum.CREPUSCULAR_RAY.pos] && tbd[AbilityIdEnum.FOCUS.pos] && abilityBuffs.getSlider().get(AbilityBuffsEnum.FOCUS.getPos()).getValue() >= 5) {
            list.add(new DamageTemplate("Crepuscular Ray", 0, pane, list.get(list.size() - 1), sp, crit_boost, true));
            float[] crepuscular_Ray = setDamagePercent(SpellEnum.CREPUSCULAR_RAY);
            calcSpell("DPS", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calcTotalDamagePercentManual(crepuscular_Ray, 20F), sp, false, false);
            calcSpell("Single Hit", list.size() -1, calc_raw, list, spell_total, id_Numbers, crepuscular_Ray, sp, false, false);
            calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calcTotalDamagePercentManual(crepuscular_Ray, 20 * abilityBuffs.getSlider().get(5).getValue()), sp, true, false);
        }

        //Tangled Traps (0.2s per Attack)
        if (tbd[AbilityIdEnum.TANGLED_TRAPS.pos]) {
            list.add(new DamageTemplate("Tangled Traps", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            float[] tangled_Traps = setDamagePercent(SpellEnum.TANGLED_TRAPS);
            calcSpell("DPS", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calcTotalDamagePercentManual(tangled_Traps, 5F), sp, false, false);
            calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total, id_Numbers, tangled_Traps, sp, false, false);
        }

        //Ivyroot Mamba (1.5s per Attack) TODO apply Forest's Blessing
        if (tbd[AbilityIdEnum.IVYROOT_MAMBA.pos]) {
            list.add(new DamageTemplate("Ivyroot Mamba", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            float[] ivyroot_mamba = setDamagePercent(SpellEnum.IVYROOT_MAMBA);
            int max_snake = 1;
            if (tbd[AbilityIdEnum.BEAST_LORE.pos]) max_snake += 1;
            calcSpell("Max DPS", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calcTotalDamagePercentManual(ivyroot_mamba, 0.66F * max_snake), sp, false, false);
            if (max_snake > 1) calcSpell("per Snake DPS", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calcTotalDamagePercentManual(ivyroot_mamba, 0.66F), sp, false, false);
            calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total, id_Numbers, ivyroot_mamba, sp, false, false);
        }

        //Murder Flock (0.9s per Attack) TODO apply Forest's Blessing
        if (tbd[AbilityIdEnum.MURDER_FLOCK.pos]) {
            list.add(new DamageTemplate("Murder Flock", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            float[] murder_flock = setDamagePercent(SpellEnum.MURDER_FLOCK);
            int max_crow = 2;
            if (tbd[AbilityIdEnum.BEAST_LORE.pos]) max_crow += 2;
            calcSpell("Max DPS", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calcTotalDamagePercentManual(murder_flock, max_crow * 1.11F), sp, false, false);
            calcSpell("per Crow DPS", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calcTotalDamagePercentManual(murder_flock, 1.11F), sp, false, false);
            calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total, id_Numbers, murder_flock, sp, false, false);
        }
    }

    public void setMageDamage(List<DamageTemplate> list, CalcRaw calc_raw, float melee_total, float spell_total, int[] id_Numbers, float[] melee, int atkSpd, SkillPoint sp, AbilityBuffs abilityBuffs, boolean[] tb, boolean[] tbd, boolean crit_boost, float intelligence, int spell_cost_1, int spell_cost_2, int spell_cost_3, int spell_cost_4) {
        //Melee
        calcMelee("DPS", list.size() - 1, calc_raw, list, melee_total, id_Numbers, melee, atkSpd, sp, false, true, false);
        calcMelee("Single Hit", list.size() - 1, calc_raw, list, melee_total, id_Numbers, melee, atkSpd, sp, true, false, false);

        //Heal
        if (tbd[AbilityIdEnum.HEAL.pos]) {
            if (tbd[AbilityIdEnum.ARCANE_TRANSFER.pos]) {
                list.add(new DamageTemplate("Arcane Transfer", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            } else {
                list.add(new DamageTemplate("Heal", calcSpellCost(35 + spell_cost_1, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_1ST_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_1ST_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, false));
                if (itemData.getMajorIDList().contains(MajorIdEnum.EXPUNGE)) {
                    list.get(list.size() - 1).addHeal("Heal (Instant)", id_Numbers, 0.2F, tbd[AbilityIdEnum.FLUID_HEALING_MAGE.pos], false);
                } else if (tbd[AbilityIdEnum.ORPHIONS_PULSE.pos]) { //Orphion's Pulse
                    if (itemData.getMajorIDList().contains(MajorIdEnum.GENTLE_GLOW)) { //Gentle Glow
                        list.get(list.size() - 1).addHeal("Total Heal", id_Numbers, 0.6F, tbd[AbilityIdEnum.FLUID_HEALING_MAGE.pos], true);
                        list.get(list.size() - 1).addHeal("per Heal", id_Numbers, 0.2F, tbd[AbilityIdEnum.FLUID_HEALING_MAGE.pos], true);
                        list.get(list.size() - 1).addHeal("per Heal to Allies", id_Numbers, 0.15F * 1.6F, tbd[AbilityIdEnum.FLUID_HEALING_MAGE.pos], true);
                    } else {
                        list.get(list.size() - 1).addHeal("Total Heal", id_Numbers, 0.55F, tbd[AbilityIdEnum.FLUID_HEALING_MAGE.pos], false);
                        list.get(list.size() - 1).addHeal("First Heal", id_Numbers, 0.15F, tbd[AbilityIdEnum.FLUID_HEALING_MAGE.pos], false);
                        list.get(list.size() - 1).addHeal("Second and Third Heal Pulses", id_Numbers, 0.2F, tbd[AbilityIdEnum.FLUID_HEALING_MAGE.pos], false);
                    }
                } else {
                    if (itemData.getMajorIDList().contains(MajorIdEnum.GENTLE_GLOW)) { //Gentle Glow
                        list.get(list.size() - 1).addHeal("Heal", id_Numbers, 0.15F, tbd[AbilityIdEnum.FLUID_HEALING_MAGE.pos], true);
                        list.get(list.size() - 1).addHeal("Heal to Allies", id_Numbers, 0.15F * 1.6F, tbd[AbilityIdEnum.FLUID_HEALING_MAGE.pos], true);
                    } else {
                        list.get(list.size() - 1).addHeal("Heal", id_Numbers, 0.15F, tbd[AbilityIdEnum.FLUID_HEALING_MAGE.pos], false);
                    }
                }
            }

            if (tbd[AbilityIdEnum.SUNSHOWER.pos]) {
                //Sunshower
                float[] sunshower = setDamagePercent(SpellEnum.SUNSHOWER);
                if (tbd[AbilityIdEnum.STRONGER_SUNSHOWER.pos]) addDamagePercent(sunshower, SpellEnum.STRONGER_SUNSHOWER);
                calcSpell("Sunshower", list.size() - 1, calc_raw, list, spell_total, id_Numbers, sunshower, sp, false, false);
            }
        }

        //Teleport
        if (tbd[AbilityIdEnum.TELEPORT.pos]) {
            list.add(new DamageTemplate("Teleport", calcSpellCost(25 + spell_cost_2, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_2ND_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_2ND_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, false));
            if (tbd[AbilityIdEnum.WIND_SLASH.pos]) {
                //Wind Slash
                calcSpell("Wind Slash", list.size() - 1, calc_raw, list, spell_total, id_Numbers, setDamagePercent(SpellEnum.WIND_SLASH), sp, false, false);
            }
            if (tbd[AbilityIdEnum.EXPLOSIVE_ENTRANCE.pos]) {
                //Explosive Entrance
                calcSpell("Explosive Entrance", list.size() - 1, calc_raw, list, spell_total, id_Numbers, setDamagePercent(SpellEnum.EXPLOSIVE_ENTRANCE), sp, false, false);
            }
            if (tbd[AbilityIdEnum.TRANSONIC_WARP.pos] && abilityBuffs.getSlider().get(AbilityBuffsEnum.WINDED.getPos()).getValue() > 0) { //Winded
                //Transonic Warp
                calcSpell("Transonic Warp", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calcTotalDamagePercentManual(setDamagePercent(SpellEnum.TRANSONIC_WARP), abilityBuffs.getSlider().get(AbilityBuffsEnum.WINDED.getPos()).getValue()), sp, false, false);
            }
        }

        //Meteor
        if (tbd[AbilityIdEnum.METEOR.pos]) {
            if (tbd[AbilityIdEnum.OPHANIM.pos]) {
                //Ophanim
                list.add(new DamageTemplate("Ophanim", calcSpellCost(50 + spell_cost_3, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_3RD_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_3RD_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, false));
                float[] ophanim = setDamagePercent(SpellEnum.OPHANIM);
                if (tbd[AbilityIdEnum.BREATHLESS.pos] && abilityBuffs.getSlider().get(AbilityBuffsEnum.WINDED.getPos()).getValue() > 0) addDamagePercent(ophanim, calcTotalDamagePercentManual(setDamagePercent(SpellEnum.BREATHLESS_OPHANIM), abilityBuffs.getSlider().get(AbilityBuffsEnum.WINDED.getPos()).getValue())); //Winded
                if (tbd[AbilityIdEnum.STRONGER_OPHANIM.pos]) addDamagePercent(ophanim, SpellEnum.STRONGER_OPHANIM);
                if (tbd[AbilityIdEnum.DIVINATION.pos]) addDamagePercent(ophanim, SpellEnum.DIVINATION);
                calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total, id_Numbers, ophanim, sp, false, false);
            } else {
                //Meteor
                list.add(new DamageTemplate("Meteor", calcSpellCost(50 + spell_cost_3, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_3RD_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_3RD_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
                float[] meteor = setDamagePercent(SpellEnum.METEOR);
                if (tbd[AbilityIdEnum.STRONGER_METEOR.pos]) addDamagePercent(meteor, SpellEnum.STRONGER_METEOR);
                if (tbd[AbilityIdEnum.BREATHLESS.pos] && abilityBuffs.getSlider().get(AbilityBuffsEnum.WINDED.getPos()).getValue() > 0) addDamagePercent(meteor, calcTotalDamagePercentManual(setDamagePercent(SpellEnum.BREATHLESS_METEOR), abilityBuffs.getSlider().get(AbilityBuffsEnum.WINDED.getPos()).getValue())); //Winded
                calcSpell("Meteor", list.size() - 1 ,calc_raw, list, spell_total, id_Numbers, meteor, sp, false, false);
                if (tbd[AbilityIdEnum.THUNDERSTORM.pos]) {
                    float[] thunderstorm = setDamagePercent(SpellEnum.THUNDERSTORM);
                    if (tbd[AbilityIdEnum.BREATHLESS.pos] && abilityBuffs.getSlider().get(AbilityBuffsEnum.WINDED.getPos()).getValue() > 0) addDamagePercent(thunderstorm, calcTotalDamagePercentManual(setDamagePercent(SpellEnum.BREATHLESS_THUNDERSTORM), abilityBuffs.getSlider().get(AbilityBuffsEnum.WINDED.getPos()).getValue())); //Winded
                    addDamagePercent(meteor, thunderstorm);
                    calcSpell("Thunderstorm", list.size() - 1, calc_raw, list, spell_total, id_Numbers, thunderstorm, sp, false, false);
                }
                calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total, id_Numbers, meteor, sp, true, false);
            }
        }

        //Ice Snake
        if (tbd[AbilityIdEnum.ICE_SNAKE.pos]) {
            list.add(new DamageTemplate("Ice Snake", calcSpellCost(30 + spell_cost_4, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_4TH_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_4TH_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
            float[] ice_Snake = setDamagePercent(SpellEnum.ICE_SNAKE);
            if (tbd[AbilityIdEnum.WINDSWEEPER.pos] && abilityBuffs.getSlider().get(AbilityBuffsEnum.WINDED.getPos()).getValue() > 0) addDamagePercent(ice_Snake, calcTotalDamagePercentManual(setDamagePercent(SpellEnum.WINDSWEEPER), abilityBuffs.getSlider().get(AbilityBuffsEnum.WINDED.getPos()).getValue())); //Winded
            if (tbd[AbilityIdEnum.SENTIENT_SNAKE.pos]) addDamagePercent(ice_Snake, SpellEnum.SENTIENT_SNAKE);
            if (tbd[AbilityIdEnum.GUST.pos]) addDamagePercent(ice_Snake, SpellEnum.GUST);
            if (tbd[AbilityIdEnum.SNAKE_NEST.pos]) {
                addDamagePercent(ice_Snake, SpellEnum.SNAKE_NEST);
                calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total, id_Numbers, ice_Snake, sp, false, false);
                calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calcTotalDamagePercent(ice_Snake), sp, true, false);
            } else {
                calcSpell("", list.size() - 1, calc_raw, list, spell_total, id_Numbers, ice_Snake, sp, true, false);
            }
        }

        //Burning Sigil
        if (tbd[AbilityIdEnum.BURNING_SIGIL.pos]) {
            list.add(new DamageTemplate("Burning Sigil", 0, pane, list.get(list.size() - 1), sp, crit_boost, true));
            float[] burning_Sigil = setDamagePercent(SpellEnum.BURNING_SIGIL);
            calcSpell("DPS", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calcTotalDamagePercentManual(burning_Sigil, 2.5F), sp, false, false);
            calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total, id_Numbers, burning_Sigil, sp, false, false);
            calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calcTotalDamagePercent(burning_Sigil), sp, true, false);
        }

        //Pyrokinesis
        if (tbd[AbilityIdEnum.PYROKINESIS.pos]) {
            list.add(new DamageTemplate("Psychokinesis", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            float[] psychokinesis = setDamagePercent(SpellEnum.PYROKINESIS);
            calcMelee("DPS", list.size() - 1, calc_raw, list, melee_total, id_Numbers, psychokinesis, atkSpd, sp, false, true, false);
            calcMelee("Single Hit", list.size() - 1, calc_raw, list, melee_total, id_Numbers, psychokinesis, atkSpd, sp, false, false, false);
        }

        //Lightweaver
        if (tbd[AbilityIdEnum.LIGHTWEAVER.pos]) {
            list.add(new DamageTemplate("Lightweaver", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total, id_Numbers, setDamagePercent(SpellEnum.LIGHTWEAVER), sp, false, false);
        }
    }

    public void setShamanDamage(List<DamageTemplate> list, CalcRaw calc_raw, float melee_total, float spell_total, int[] id_Numbers, float[] melee, int atkSpd, SkillPoint sp, AbilityBuffs abilityBuffs, boolean[] tb, boolean[] tbd, boolean crit_boost, float intelligence, int spell_cost_1, int spell_cost_2, int spell_cost_3, int spell_cost_4) {
        //Melee
        melee = setDamagePercent(SpellEnum.SHAMAN_MELEE);
        if (tbd[AbilityIdEnum.HAND_OF_THE_SHAMAN.pos]) addDamagePercent(melee, SpellEnum.HAND_OF_THE_SHAMAN);
        calcMelee("DPS", list.size() - 1, calc_raw, list, melee_total, id_Numbers, calcTotalDamagePercent(melee), atkSpd, sp, false, true, false);
        calcMelee("Single Hit", list.size() - 1, calc_raw, list, melee_total, id_Numbers, melee, atkSpd, sp, false, false, false);
        calcMelee("Total Damage", list.size() - 1, calc_raw, list, melee_total, id_Numbers, calcTotalDamagePercent(melee), atkSpd, sp, true, false, false);

        //Totem
        if (tbd[AbilityIdEnum.TOTEM.pos]) {
            list.add(new DamageTemplate("Totem", calcSpellCost(30 + spell_cost_1, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_1ST_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_1ST_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, false));
            float[] totem = setDamagePercent(SpellEnum.TOTEM);
            float totem_percent = spell_total;
            int totem_count = 1;
            int furious_effigy = 1; //Furious Effigy (Major ID)
            if (tbd[AbilityIdEnum.STRONGER_TOTEM.pos]) addDamagePercent(totem, SpellEnum.STRONGER_TOTEM);
            if (tbd[AbilityIdEnum.TRIPLE_TOTEM.pos]) {
                //Triple Totem
                totem_percent *= 0.5F;
                totem_count = 3;
            } else if (tbd[AbilityIdEnum.DOUBLE_TOTEM.pos]) {
                //Double Totem
                totem_percent *= 0.6F;
                totem_count = 2;
            }
            if (itemData.getMajorIDList().contains(MajorIdEnum.FURIOUS_EFFIGY)) furious_effigy = 2; //Has Furious Effigy (x2 Time)
            if (tbd[AbilityIdEnum.REGENERATION.pos]) list.get(list.size() - 1).addHeal("Heal", id_Numbers, 0.01F * furious_effigy, false, false);
            calcSpell("DPS", list.size() - 1, calc_raw, list, totem_percent, id_Numbers, calcTotalDamagePercentManual(totem, 2.5F * totem_count * furious_effigy), sp, false, false);
            calcSpell("Single Hit", list.size() - 1, calc_raw, list, totem_percent, id_Numbers, totem, sp, false, false);
        }

        //Haul
        if (tbd[AbilityIdEnum.HAUL.pos]) {
            list.add(new DamageTemplate("Haul", calcSpellCost(15 + spell_cost_2, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_2ND_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_2ND_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
            if (tbd[AbilityIdEnum.NATURES_JOLT.pos]) {
                //Nature's Jolt
                calcSpell("Nature's Jolt", list.size() - 1, calc_raw, list, spell_total, id_Numbers, setDamagePercent(SpellEnum.NATURES_JOLT), sp, !(tbd[AbilityIdEnum.FROG_DANCE.pos] && abilityBuffs.getBox().get(AbilityBuffsEnum.MASK_OF_THE_COWARD.getPos()).isSelected()), false);
            }
            if (tbd[AbilityIdEnum.FROG_DANCE.pos] && abilityBuffs.getBox().get(AbilityBuffsEnum.MASK_OF_THE_COWARD.getPos()).isSelected()) {
                //Frog Dance
                calcSpell("Frog Dance", list.size() - 1, calc_raw, list, spell_total, id_Numbers, setDamagePercent(SpellEnum.FROG_DANCE), sp, !tbd[AbilityIdEnum.NATURES_JOLT.pos], false);
            }
            if (tbd[AbilityIdEnum.NATURES_JOLT.pos] && tbd[AbilityIdEnum.FROG_DANCE.pos] && abilityBuffs.getBox().get(AbilityBuffsEnum.MASK_OF_THE_COWARD.getPos()).isSelected()) {
                float[] haul = setDamagePercent(SpellEnum.NATURES_JOLT);
                addDamagePercent(haul, SpellEnum.FROG_DANCE);
                calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total, id_Numbers, haul, sp, true, false);
            }
        }

        //Aura
        if (tbd[AbilityIdEnum.AURA.pos]) {
            list.add(new DamageTemplate("Aura", calcSpellCost(40 + spell_cost_3, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_3RD_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_3RD_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
            float[] aura = setDamagePercent(SpellEnum.AURA);
            float aura_percent = spell_total;
            int totem_count = 1;
            float heal = 1F;
            boolean gentle_Glow = itemData.getMajorIDList().contains(MajorIdEnum.GENTLE_GLOW); //Gentle Glow
            if (tbd[AbilityIdEnum.SHOCKING_AURA.pos]) addDamagePercent(aura, SpellEnum.SHOCKING_AURA);
            if (tbd[AbilityIdEnum.STORM_DANCE.pos]) addDamagePercent(aura, SpellEnum.STORM_DANCE);
            if (tbd[AbilityIdEnum.TRIPLE_TOTEM.pos]) {
                //Triple Totem
                aura_percent *= 0.5F;
                heal = 0.5F;
                totem_count = 3;
            } else if (tbd[AbilityIdEnum.DOUBLE_TOTEM.pos]) {
                //Double Totem
                aura_percent *= 0.6F;
                heal = 0.6F;
                totem_count = 2;
            }

            if (tbd[AbilityIdEnum.REBOUND.pos]) {
                //Rebound
                if (tbd[AbilityIdEnum.SACRIFICIAL_SHRINE.pos] && abilityBuffs.getBox().get(AbilityBuffsEnum.SACRIFICIAL_SHRINE.getPos()).isSelected()) { //Sacrificial Shrine
                    list.get(list.size() - 1).addHeal("Total Heal", id_Numbers, 0.5F * heal * totem_count * 0.6F, tbd[AbilityIdEnum.FLUID_HEALING_SHAMAN.pos], gentle_Glow);
                    list.get(list.size() - 1).addHeal("Single Heal", id_Numbers, 0.25F * heal * 0.6F, tbd[AbilityIdEnum.FLUID_HEALING_SHAMAN.pos], gentle_Glow);
                    if (gentle_Glow) list.get(list.size() - 1).addHeal("Heal to Allies", id_Numbers, 0.25F * heal * 0.6F * 1.6F, tbd[AbilityIdEnum.FLUID_HEALING_SHAMAN.pos], true);
                    aura_percent *= 1.35F;
                }
                calcSpell("Single Hit", list.size() - 1, calc_raw, list, aura_percent, id_Numbers, aura, sp, false, false);
                calcSpell("Total Damage", list.size() - 1, calc_raw, list, aura_percent, id_Numbers, calcTotalDamagePercentManual(aura, totem_count * 2), sp, true, false);
            } else {
                if (tbd[AbilityIdEnum.SACRIFICIAL_SHRINE.pos] && abilityBuffs.getBox().get(AbilityBuffsEnum.SACRIFICIAL_SHRINE.getPos()).isSelected()) {
                    list.get(list.size() - 1).addHeal("Total Heal", id_Numbers, 0.25F * heal * totem_count, tbd[AbilityIdEnum.FLUID_HEALING_SHAMAN.pos], gentle_Glow);
                    list.get(list.size() - 1).addHeal("Single Heal", id_Numbers, 0.25F * heal, tbd[AbilityIdEnum.FLUID_HEALING_SHAMAN.pos], gentle_Glow);
                    if (gentle_Glow) list.get(list.size() - 1).addHeal("Heal to Allies", id_Numbers, 0.25F * heal * 1.6F, tbd[AbilityIdEnum.FLUID_HEALING_SHAMAN.pos], true);
                    aura_percent *= 1.35F;
                }
                calcSpell("Single Hit", list.size() - 1, calc_raw, list, aura_percent, id_Numbers, aura, sp, false, false);
                calcSpell("Total Damage", list.size() - 1, calc_raw, list, aura_percent, id_Numbers, calcTotalDamagePercentManual(aura, totem_count), sp, true, false);
            }
        }

        //Uproot
        if (tbd[AbilityIdEnum.UPROOT.pos]) {
            if (tbd[AbilityIdEnum.MASKS.pos]) {
                list.add(new DamageTemplate("Switch Mask", calcSpellCost(30 + spell_cost_4, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_4TH_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_4TH_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, false));
                if (tbd[AbilityIdEnum.CHANT_OF_THE_COWARD.pos]) calcSpell("Switch to Coward", list.size() - 1, calc_raw, list, spell_total, id_Numbers, setDamagePercent(SpellEnum.CHANT_OF_THE_COWARD), sp, false, false);
            } else {
                list.add(new DamageTemplate("Uproot", calcSpellCost(30 + spell_cost_4, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_4TH_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_4TH_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
                float[] uproot = setDamagePercent(SpellEnum.UPROOT);
                if (tbd[AbilityIdEnum.FLAMING_TONGUE.pos]) {
                    //Flaming Tongue
                    addDamagePercent(uproot, SpellEnum.FLAMING_TONGUE);
                    calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total, id_Numbers, uproot, sp, false, false);
                    calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calcTotalDamagePercent(uproot), sp, true, false);
                } else {
                    calcSpell("", list.size() - 1, calc_raw, list, spell_total, id_Numbers, uproot, sp, true, false);
                }
            }
        }

        //Rain Dance
        if (tbd[AbilityIdEnum.RAIN_DANCE.pos]) {
            list.add(new DamageTemplate("Rain Dance", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            float[] rain_Dance = setDamagePercent(SpellEnum.RAIN_DANCE);
            calcSpell("DPS", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calcTotalDamagePercentManual(rain_Dance, 2.5F), sp, false, false);
            calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total, id_Numbers, rain_Dance, sp, false, false);
        }

        //Puppet Master
        if (tbd[AbilityIdEnum.PUPPET_MASTER.pos]) {
            list.add(new DamageTemplate("Puppet Master", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            float[] puppet_Master = setDamagePercent(SpellEnum.PUPPET_MASTER);
            float puppet_Percent = spell_total;
            float puppet_AtkSpd = 2F;
            if (itemData.getMajorIDList().contains(MajorIdEnum.STRINGS_OF_FATE)) puppet_Percent *= 2F; //Strings of Fate (Major ID)
            if (tbd[AbilityIdEnum.BULLWHIP.pos] && abilityBuffs.getBox().get(AbilityBuffsEnum.BULLWHIP.getPos()).isSelected()) puppet_Percent *= 1.2F; //Bullwhip
            if (tbd[AbilityIdEnum.INVIGORATING_WAVE.pos] && abilityBuffs.getBox().get(AbilityBuffsEnum.INVIGORATING_WAVE.getPos()).isSelected()) puppet_AtkSpd *= 1.3F; //Invigorating Wave
            int maxPuppets = 3;
            if (tbd[AbilityIdEnum.MORE_PUPPETS_1.pos]) maxPuppets += 1;
            if (tbd[AbilityIdEnum.MORE_PUPPETS_2.pos]) maxPuppets += 2;
            if (tbd[AbilityIdEnum.SHEPHERD.pos] && abilityBuffs.getSlider().get(AbilityBuffsEnum.SHEPHERD.getPos()).getValue() > 0) maxPuppets += abilityBuffs.getSlider().get(AbilityBuffsEnum.SHEPHERD.getPos()).getValue(); //Shepherd
            calcSpell("Max DPS", list.size() - 1, calc_raw, list, puppet_Percent, id_Numbers, calcTotalDamagePercentManual(puppet_Master, maxPuppets * puppet_AtkSpd), sp, false, false);
            calcSpell("Single DPS", list.size() - 1, calc_raw, list, puppet_Percent, id_Numbers, calcTotalDamagePercentManual(puppet_Master, puppet_AtkSpd), sp, false, false);
            calcSpell("Single Hit", list.size() - 1, calc_raw, list, puppet_Percent, id_Numbers, puppet_Master, sp, false, false);
        }

        //Exploding Puppets
        if (tbd[AbilityIdEnum.EXPLODING_PUPPETS.pos]) {
            list.add(new DamageTemplate("Exploding Puppets", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            float puppet_Percent = spell_total;
            if (itemData.getMajorIDList().contains(MajorIdEnum.STRINGS_OF_FATE)) puppet_Percent *= 2F; //Strings of Fate (Major ID)
            calcSpell("", list.size() - 1, calc_raw, list, puppet_Percent, id_Numbers, setDamagePercent(SpellEnum.EXPLODING_PUPPETS), sp, false, false);
        }

        //Twisted Tether
        if (tbd[AbilityIdEnum.TWISTED_TETHER.pos]) {
            list.add(new DamageTemplate("Twisted Tether", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            float[] twisted_Tether = setDamagePercent(SpellEnum.TWISTED_TETHER);
            if (tbd[AbilityIdEnum.STRONGER_TETHER.pos]) addDamagePercent(twisted_Tether, SpellEnum.STRONGER_TETHER);
            calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total, id_Numbers, twisted_Tether, sp, false, false);
        }

        //Crimson Effigy
        if (tbd[AbilityIdEnum.CRIMSON_EFFIGY.pos]) {
            list.add(new DamageTemplate("Crimson Effigy", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            float[] crimson_Effigy = setDamagePercent(SpellEnum.CRIMSON_EFFIGY);
            float effigy_Percent = spell_total;
            float effigy_AtkSpd = 2F;
            if (tbd[AbilityIdEnum.BULLWHIP.pos] && abilityBuffs.getBox().get(AbilityBuffsEnum.BULLWHIP.getPos()).isSelected()) effigy_Percent *= 1.2F; //Bullwhip
            if (tbd[AbilityIdEnum.INVIGORATING_WAVE.pos] && abilityBuffs.getBox().get(AbilityBuffsEnum.INVIGORATING_WAVE.getPos()).isSelected()) effigy_AtkSpd *= 1.3F; //Invigorating Wave

            String n = "DPS";
            if (tbd[AbilityIdEnum.MORE_EFFIGIES.pos]) {
                addDamagePercent(crimson_Effigy, SpellEnum.MORE_EFFIGIES);
                n = "Single DPS";
                calcSpell("Total DPS", list.size() - 1, calc_raw, list, effigy_Percent, id_Numbers, calcTotalDamagePercentManual(crimson_Effigy, effigy_AtkSpd * 2F), sp, false, false);
            }
            calcSpell(n, list.size() - 1, calc_raw, list, effigy_Percent, id_Numbers, calcTotalDamagePercentManual(crimson_Effigy, effigy_AtkSpd), sp, false, false);
            calcSpell("Single Hit", list.size() - 1, calc_raw, list, effigy_Percent, id_Numbers, crimson_Effigy, sp, false, false);
        }

        //Blood Sorrow
        if (tbd[AbilityIdEnum.BLOOD_SORROW.pos]) {
            list.add(new DamageTemplate("Blood Sorrow", 0, pane, list.get(list.size() - 1), sp, crit_boost, true));
            float[] blood_Sorrow = setDamagePercent(SpellEnum.BLOOD_SORROW);
            calcSpell("DPS", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calcTotalDamagePercentManual(blood_Sorrow, 5F), sp, false, false);
            calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total, id_Numbers, blood_Sorrow, sp, false, false);
            calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calcTotalDamagePercent(blood_Sorrow), sp, true, false);
        }
    }

    public float getDamage(JsonObject json, Identifications id, boolean isMax) {
        float damage_min = 0;
        float damage_max = 0;
        if (json.get(id.getItemName()) != null) {
            JsonObject j = json.get(id.getItemName()).getAsJsonObject();
            if (j.get("max").getAsString().contains("-")) {
                String[] ss = j.get("max").getAsString().split("-");
                damage_min = Integer.parseInt(ss[0]);
                damage_max = Integer.parseInt(ss[ss.length - 1]);
            } else {
                damage_min = j.get("min").getAsInt();
                damage_max = j.get("max").getAsInt();
            }
        }
        if (isMax) {
            return damage_max;
        } else {
            return damage_min;
        }
    }

    private float[] setDamagePercent(SpellEnum spell) {
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

    private void addDamagePercent(float[] percent, SpellEnum spell) {
        percent[0] += spell.getNeutral();
        percent[1] += spell.getEarth();
        percent[2] += spell.getThunder();
        percent[3] += spell.getWater();
        percent[4] += spell.getFire();
        percent[5] += spell.getAir();
        percent[6] += spell.getAddNum();
    }

    private void addDamagePercent(float[] basePercent, float[] addPercent) {
        basePercent[0] += addPercent[0] * addPercent[6];
        basePercent[1] += addPercent[1] * addPercent[6];
        basePercent[2] += addPercent[2] * addPercent[6];
        basePercent[3] += addPercent[3] * addPercent[6];
        basePercent[4] += addPercent[4] * addPercent[6];
        basePercent[5] += addPercent[5] * addPercent[6];
    }

    private float[] calcTotalDamagePercent(float[] p) {
        float[] percent = p.clone();
        percent[0] *= percent[6];
        percent[1] *= percent[6];
        percent[2] *= percent[6];
        percent[3] *= percent[6];
        percent[4] *= percent[6];
        percent[5] *= percent[6];
        return percent;
    }

    private float[] calcTotalDamagePercentManual(float[] p, float boost) {
        float[] percent = p.clone();
        percent[0] *= boost;
        percent[1] *= boost;
        percent[2] *= boost;
        percent[3] *= boost;
        percent[4] *= boost;
        percent[5] *= boost;
        return percent;
    }

    private int calcSpellCost(int base, float intelligence, int idRaw, int idPercent) {
        return Math.max((int) Math.floor((base * intelligence + idRaw) * (1F + idPercent / 100F)), 1);
    }

    private float calc(float damage, float boosts, float spBoost, int... idBoosts) {
        if (damage == 0) return 0;

        float idBoost = 1F;
        if (spBoost != 1F) idBoost = spBoost; //Skill Point Boost
        if (idBoosts != null) {
            for (int i : idBoosts) {
                idBoost += i / 100F;
            }
        }

        return damage * idBoost * boosts;
    }

    private void calcMelee(String name, int pos, CalcRaw damages, List<DamageTemplate> list, float boost, int[] id_Numbers, float[] percent, int atkSpd, SkillPoint sp, boolean isTotal, boolean isDPS, boolean checkDamage) {
        float tomeBonus = 1F + (id_Numbers[109] / 100F);
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

        float neutral_min_base = damages.neutralMin;
        float earth_min_base = damages.earthMin;
        float thunder_min_base = damages.thunderMin;
        float water_min_base = damages.waterMin;
        float fire_min_base = damages.fireMin;
        float air_min_base = damages.airMin;
        float total_min = damages.neutralMin + damages.earthMin + damages.thunderMin + damages.waterMin + damages.fireMin + damages.airMin;

        float neutral_max_base = damages.neutralMax;
        float earth_max_base = damages.earthMax;
        float thunder_max_base = damages.thunderMax;
        float water_max_base = damages.waterMax;
        float fire_max_base = damages.fireMax;
        float air_max_base = damages.airMax;
        float total_max = damages.neutralMax + damages.earthMax + damages.thunderMax + damages.waterMax + damages.fireMax + damages.airMax;

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


        float neutral_min = calc(neutral_min_base, boost, 1F, id_Numbers[IdDisplay.ID_INT.get(Identifications.MELEE_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.NEUTRAL_MELEE_DAMAGE_PERCENT)]);
        float earth_min = calc(earth_min_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost(), id_Numbers[IdDisplay.ID_INT.get(Identifications.MELEE_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.EARTH_MELEE_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.EARTH_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)]);
        float thunder_min = calc(thunder_min_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.DEXTERITY).getSPBoost(), id_Numbers[IdDisplay.ID_INT.get(Identifications.MELEE_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.THUNDER_MELEE_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.THUNDER_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)]);
        float water_min = calc(water_min_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.INTELLIGENCE).getIntDamageBonus(), id_Numbers[IdDisplay.ID_INT.get(Identifications.MELEE_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.WATER_MELEE_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.WATER_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)]);
        float fire_min = calc(fire_min_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.DEFENSE).getSPBoost(), id_Numbers[IdDisplay.ID_INT.get(Identifications.MELEE_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.FIRE_MELEE_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.FIRE_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)]);
        float air_min = calc(air_min_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.AGILITY).getSPBoost(), id_Numbers[IdDisplay.ID_INT.get(Identifications.MELEE_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.AIR_MELEE_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.AIR_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)]);

        float neutral_max = calc(neutral_max_base, boost, 1F, id_Numbers[IdDisplay.ID_INT.get(Identifications.MELEE_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.NEUTRAL_MELEE_DAMAGE_PERCENT)]);
        float earth_max = calc(earth_max_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost(), id_Numbers[IdDisplay.ID_INT.get(Identifications.MELEE_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.EARTH_MELEE_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.EARTH_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)]);
        float thunder_max = calc(thunder_max_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.DEXTERITY).getSPBoost(), id_Numbers[IdDisplay.ID_INT.get(Identifications.MELEE_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.THUNDER_MELEE_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.THUNDER_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)]);
        float water_max = calc(water_max_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.INTELLIGENCE).getIntDamageBonus(), id_Numbers[IdDisplay.ID_INT.get(Identifications.MELEE_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.WATER_MELEE_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.WATER_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)]);
        float fire_max = calc(fire_max_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.DEFENSE).getSPBoost(), id_Numbers[IdDisplay.ID_INT.get(Identifications.MELEE_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.FIRE_MELEE_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.FIRE_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)]);
        float air_max = calc(air_max_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.AGILITY).getSPBoost(), id_Numbers[IdDisplay.ID_INT.get(Identifications.MELEE_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.AIR_MELEE_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.AIR_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)]);

        CalcRaw calc_raw = new CalcRaw(neutral_min, earth_min, thunder_min, water_min, fire_min, air_min, neutral_max, earth_max, thunder_max, water_max, fire_max, air_max, percent, null);

        if (neutral_min != 0 || neutral_max != 0) {
            neutral_min += (calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_MELEE_DAMAGE)], DamageType.NEUTRAL, false, true) + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_NEUTRAL_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_NEUTRAL_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;
            neutral_max += (calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_MELEE_DAMAGE)], DamageType.NEUTRAL, true, true) + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_NEUTRAL_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_NEUTRAL_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;

            neutral_min *= tomeBonus;
            neutral_max *= tomeBonus;
        }

        if (earth_min != 0 || earth_max != 0) {
            earth_min += (calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_MELEE_DAMAGE)], DamageType.EARTH, false, true) + calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_DAMAGE)], DamageType.EARTH, false, false) + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_EARTH_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_EARTH_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;
            earth_max += (calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_MELEE_DAMAGE)], DamageType.EARTH, true, true) + calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_DAMAGE)], DamageType.EARTH, true, false) + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_EARTH_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_EARTH_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;

            earth_min *= tomeBonus;
            earth_max *= tomeBonus;
        }

        if (thunder_min != 0 || thunder_max != 0) {
            thunder_min += (calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_MELEE_DAMAGE)], DamageType.THUNDER, false, true) + calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_DAMAGE)], DamageType.THUNDER, false, false) + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_THUNDER_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_THUNDER_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;
            thunder_max += (calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_MELEE_DAMAGE)], DamageType.THUNDER, true, true) + calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_DAMAGE)], DamageType.THUNDER, true, false) + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_THUNDER_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_THUNDER_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;

            thunder_min *= tomeBonus;
            thunder_max *= tomeBonus;
        }

        if (water_min != 0 || water_max != 0) {
            water_min += (calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_MELEE_DAMAGE)], DamageType.WATER, false, true) + calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_DAMAGE)], DamageType.WATER, false, false) + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_WATER_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_WATER_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;
            water_max += (calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_MELEE_DAMAGE)], DamageType.WATER, true, true) + calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_DAMAGE)], DamageType.WATER, true, false) + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_WATER_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_WATER_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;

            water_min *= tomeBonus;
            water_max *= tomeBonus;
        }

        if (fire_min != 0 || fire_max != 0) {
            fire_min += (calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_MELEE_DAMAGE)], DamageType.FIRE, false, true) + calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_DAMAGE)], DamageType.FIRE, false, false) + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_FIRE_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_FIRE_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;
            fire_max += (calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_MELEE_DAMAGE)], DamageType.FIRE, true, true) + calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_DAMAGE)], DamageType.FIRE, true, false) + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_FIRE_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_FIRE_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;

            fire_min *= tomeBonus;
            fire_max *= tomeBonus;
        }

        if (air_min != 0 || air_max != 0) {
            air_min += (calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_MELEE_DAMAGE)], DamageType.AIR, false, true) + calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_DAMAGE)], DamageType.AIR, false, false) + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_AIR_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_AIR_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;
            air_max += (calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_MELEE_DAMAGE)], DamageType.AIR, true, true) + calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_DAMAGE)], DamageType.AIR, true, false) + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_AIR_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_AIR_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;

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

    private void calcSpell(String name, int pos, CalcRaw damages, List<DamageTemplate> list, float boost, int[] id_Numbers, float[] percent, SkillPoint sp, boolean isTotal, boolean checkDamage) {
        float tomeBonus = 1F + (id_Numbers[109] / 100F);
        float atkSpd = weapon.getAttackSpeed();

        float neutral_min_base = damages.neutralMin;
        float earth_min_base = damages.earthMin;
        float thunder_min_base = damages.thunderMin;
        float water_min_base = damages.waterMin;
        float fire_min_base = damages.fireMin;
        float air_min_base = damages.airMin;
        float total_min = damages.neutralMin + damages.earthMin + damages.thunderMin + damages.waterMin + damages.fireMin + damages.airMin;

        float neutral_max_base = damages.neutralMax;
        float earth_max_base = damages.earthMax;
        float thunder_max_base = damages.thunderMax;
        float water_max_base = damages.waterMax;
        float fire_max_base = damages.fireMax;
        float air_max_base = damages.airMax;
        float total_max = damages.neutralMax + damages.earthMax + damages.thunderMax + damages.waterMax + damages.fireMax + damages.airMax;

        //Neutral Base
        if (neutral_min_base != 0) neutral_min_base *= percent[0];
        if (neutral_max_base != 0) neutral_max_base *= percent[0];

        //Earth Base
        if (earth_min_base != 0) earth_min_base *= percent[0];
        if (earth_max_base != 0) earth_max_base *= percent[0];

        //Thunder Base
        if (thunder_min_base != 0) thunder_min_base *= percent[0];
        if (thunder_max_base != 0) thunder_max_base *= percent[0];

        //Water Base
        if (water_min_base != 0) water_min_base *= percent[0];
        if (water_max_base != 0) water_max_base *= percent[0];

        //Fire Base
        if (fire_min_base != 0) fire_min_base *= percent[0];
        if (fire_max_base != 0) fire_max_base *= percent[0];

        //Air Base
        if (air_min_base != 0) air_min_base *= percent[0];
        if (air_max_base != 0) air_max_base *= percent[0];

        //Base Damage * ID (%)
        float neutral_min = calc(neutral_min_base, boost, 1F, id_Numbers[IdDisplay.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.NEUTRAL_SPELL_DAMAGE_PERCENT)]) * atkSpd;
        float earth_min = calc(earth_min_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost(), id_Numbers[IdDisplay.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.EARTH_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.EARTH_DAMAGE_PERCENT)]) * atkSpd;
        float thunder_min = calc(thunder_min_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.DEXTERITY).getSPBoost(), id_Numbers[IdDisplay.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.THUNDER_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.THUNDER_DAMAGE_PERCENT)]) * atkSpd;
        float water_min = calc(water_min_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.INTELLIGENCE).getIntDamageBonus(), id_Numbers[IdDisplay.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.WATER_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.WATER_DAMAGE_PERCENT)]) * atkSpd;
        float fire_min = calc(fire_min_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.DEFENSE).getSPBoost(), id_Numbers[IdDisplay.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.FIRE_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.FIRE_DAMAGE_PERCENT)]) * atkSpd;
        float air_min = calc(air_min_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.AGILITY).getSPBoost(), id_Numbers[IdDisplay.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.AIR_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.AIR_DAMAGE_PERCENT)]) * atkSpd;

        float neutral_max = calc(neutral_max_base, boost, 1F, id_Numbers[IdDisplay.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.NEUTRAL_SPELL_DAMAGE_PERCENT)]) * atkSpd;
        float earth_max = calc(earth_max_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost(), id_Numbers[IdDisplay.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.EARTH_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.EARTH_DAMAGE_PERCENT)]) * atkSpd;
        float thunder_max = calc(thunder_max_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.DEXTERITY).getSPBoost(), id_Numbers[IdDisplay.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.THUNDER_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.THUNDER_DAMAGE_PERCENT)]) * atkSpd;
        float water_max = calc(water_max_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.INTELLIGENCE).getIntDamageBonus(), id_Numbers[IdDisplay.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.WATER_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.WATER_DAMAGE_PERCENT)]) * atkSpd;
        float fire_max = calc(fire_max_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.DEFENSE).getSPBoost(), id_Numbers[IdDisplay.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.FIRE_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.FIRE_DAMAGE_PERCENT)]) * atkSpd;
        float air_max = calc(air_max_base, boost, sp.getSkillPoint(SkillPoint.SkillPointType.AGILITY).getSPBoost(), id_Numbers[IdDisplay.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.AIR_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.AIR_DAMAGE_PERCENT)]) * atkSpd;


        //Raw Damage Calc Save
        CalcRaw calcRaw = new CalcRaw(neutral_min, earth_min, thunder_min, water_min, fire_min, air_min, neutral_max, earth_max, thunder_max, water_max, fire_max, air_max, percent, have_mastery);
        calcRaw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_SPELL_DAMAGE)], id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_SPELL_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_DAMAGE)]);

        earth_min += calc(total_min * percent[DamageType.EARTH.getId()], boost, sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost(), id_Numbers[IdDisplay.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.EARTH_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.EARTH_DAMAGE_PERCENT)]) * atkSpd;
        thunder_min += calc(total_min * percent[DamageType.THUNDER.getId()], boost, sp.getSkillPoint(SkillPoint.SkillPointType.DEXTERITY).getSPBoost(), id_Numbers[IdDisplay.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.THUNDER_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.THUNDER_DAMAGE_PERCENT)]) * atkSpd;
        water_min += calc(total_min * percent[DamageType.WATER.getId()], boost, sp.getSkillPoint(SkillPoint.SkillPointType.INTELLIGENCE).getIntDamageBonus(), id_Numbers[IdDisplay.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.WATER_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.WATER_DAMAGE_PERCENT)]) * atkSpd;
        fire_min += calc(total_min * percent[DamageType.FIRE.getId()], boost, sp.getSkillPoint(SkillPoint.SkillPointType.DEFENSE).getSPBoost(), id_Numbers[IdDisplay.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.FIRE_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.FIRE_DAMAGE_PERCENT)]) * atkSpd;
        air_min += calc(total_min * percent[DamageType.AIR.getId()], boost, sp.getSkillPoint(SkillPoint.SkillPointType.AGILITY).getSPBoost(), id_Numbers[IdDisplay.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.AIR_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.AIR_DAMAGE_PERCENT)]) * atkSpd;

        earth_max += calc(total_max * percent[DamageType.EARTH.getId()], boost, sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost(), id_Numbers[IdDisplay.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.EARTH_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.EARTH_DAMAGE_PERCENT)]) * atkSpd;
        thunder_max += calc(total_max * percent[DamageType.THUNDER.getId()], boost, sp.getSkillPoint(SkillPoint.SkillPointType.DEXTERITY).getSPBoost(), id_Numbers[IdDisplay.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.THUNDER_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.THUNDER_DAMAGE_PERCENT)]) * atkSpd;
        water_max += calc(total_max * percent[DamageType.WATER.getId()], boost, sp.getSkillPoint(SkillPoint.SkillPointType.INTELLIGENCE).getIntDamageBonus(), id_Numbers[IdDisplay.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.WATER_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.WATER_DAMAGE_PERCENT)]) * atkSpd;
        fire_max += calc(total_max * percent[DamageType.FIRE.getId()], boost, sp.getSkillPoint(SkillPoint.SkillPointType.DEFENSE).getSPBoost(), id_Numbers[IdDisplay.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.FIRE_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.FIRE_DAMAGE_PERCENT)]) * atkSpd;
        air_max += calc(total_max * percent[DamageType.AIR.getId()], boost, sp.getSkillPoint(SkillPoint.SkillPointType.AGILITY).getSPBoost(), id_Numbers[IdDisplay.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.AIR_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.AIR_DAMAGE_PERCENT)]) * atkSpd;


        //Raw Damage Calc and Calc Tome Bonus
        if (neutral_min != 0 || neutral_max != 0) {
            neutral_min += calcRawDamage(calcRaw, false, DamageType.NEUTRAL, percent, boost, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_NEUTRAL_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_NEUTRAL_SPELL_DAMAGE)]);
            neutral_max += calcRawDamage(calcRaw, true, DamageType.NEUTRAL, percent, boost, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_NEUTRAL_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_NEUTRAL_SPELL_DAMAGE)]);

            neutral_min *= tomeBonus;
            neutral_max *= tomeBonus;
        }

        if (earth_min != 0 || earth_max != 0) {
            earth_min += calcRawDamage(calcRaw, false, DamageType.EARTH, percent, boost, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_EARTH_SPELL_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_EARTH_DAMAGE)]);
            earth_max += calcRawDamage(calcRaw, true, DamageType.EARTH, percent, boost, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_EARTH_SPELL_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_EARTH_DAMAGE)]);

            earth_min *= tomeBonus;
            earth_max *= tomeBonus;
        }
        if (thunder_min != 0 || thunder_max != 0) {
            thunder_min += calcRawDamage(calcRaw, false, DamageType.THUNDER, percent, boost, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_THUNDER_SPELL_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_THUNDER_DAMAGE)]);
            thunder_max += calcRawDamage(calcRaw, true, DamageType.THUNDER, percent, boost, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_THUNDER_SPELL_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_THUNDER_DAMAGE)]);

            thunder_min *= tomeBonus;
            thunder_max *= tomeBonus;
        }
        if (water_min != 0 || water_max != 0) {
            water_min += calcRawDamage(calcRaw, false, DamageType.WATER, percent, boost, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_WATER_SPELL_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_WATER_DAMAGE)]);
            water_max += calcRawDamage(calcRaw, true, DamageType.WATER, percent, boost, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_WATER_SPELL_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_WATER_DAMAGE)]);

            water_min *= tomeBonus;
            water_max *= tomeBonus;
        }
        if (fire_min != 0 || fire_max != 0) {
            fire_min += calcRawDamage(calcRaw, false, DamageType.FIRE, percent, boost, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_FIRE_SPELL_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_FIRE_DAMAGE)]);
            fire_max += calcRawDamage(calcRaw, true, DamageType.FIRE, percent, boost, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_FIRE_SPELL_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_FIRE_DAMAGE)]);

            fire_min *= tomeBonus;
            fire_max *= tomeBonus;
        }
        if (air_min != 0 || air_max != 0) {
            air_min += calcRawDamage(calcRaw, false, DamageType.AIR, percent, boost, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_AIR_SPELL_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_AIR_DAMAGE)]);
            air_max += calcRawDamage(calcRaw, true, DamageType.AIR, percent, boost, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_AIR_SPELL_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_AIR_DAMAGE)]);

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

    public float calcRawDamage(CalcRaw calc_raw, boolean isMax, DamageType damage_type, float[] percent, float boost, int others_Raw) {
        float calc_total = others_Raw * (percent[DamageType.NEUTRAL.getId()] + percent[DamageType.EARTH.getId()] + percent[DamageType.THUNDER.getId()] + percent[DamageType.WATER.getId()] + percent[DamageType.FIRE.getId()] + percent[DamageType.AIR.getId()]);

        if (isMax) {
            return (calc_total + calc_raw.rawDamagesMax[damage_type.getId()]) * boost;
        } else {
            return (calc_total + calc_raw.rawDamagesMin[damage_type.getId()]) * boost;
        }
    }

    public void calcPowderDamages(List<JTextField> powders) {
        int[] min = new int[] { (int) neutralMin, (int) earthMin, (int) thunderMin, (int) waterMin, (int) fireMin, (int) airMin};
        int[] max = new int[] { (int) neutralMax, (int) earthMax, (int) thunderMax, (int) waterMax, (int) fireMax, (int) airMax};

        DataUtils.setPowderOnNonCraft(min, powders.get(4).getText(), JsonKeys.MIN);
        DataUtils.setPowderOnNonCraft(max, powders.get(4).getText(), JsonKeys.MAX);

        neutralMin = min[0];
        earthMin = min[1];
        thunderMin = min[2];
        waterMin = min[3];
        fireMin = min[4];
        airMin = min[5];

        neutralMax = max[0];
        earthMax = max[1];
        thunderMax = max[2];
        waterMax = max[3];
        fireMax = max[4];
        airMax = max[5];
    }

    public void setAbilityData(TreeBase tree, AbilityBuffs abilityBuffs) {
        if (abilityBuffs.getBox().get(AbilityBuffsEnum.RAGNAROKKR.getPos()).isSelected()) percent += 0.2F; //Ragnarokkr (+20%)
        if (abilityBuffs.getBox().get(AbilityBuffsEnum.FORTITUDE.getPos()).isSelected()) percent += 0.6F; //Fortitude (+60%)
        if (abilityBuffs.getBox().get(AbilityBuffsEnum.VENGEFUL_SPIRIT.getPos()).isSelected()) percent += 0.2F; //Vengeful Spirit (+20%)
        if (abilityBuffs.getSlider().get(AbilityBuffsEnum.MARKED.getPos()).getValue() > 0) { //Marked
            if (abilityBuffs.getBox().get(AbilityBuffsEnum.MARKED_10PERCENT.getPos()).isSelected()) { //is 10%
                multiple *= 1F + abilityBuffs.getSlider().get(AbilityBuffsEnum.MARKED.getPos()).getValue() * 0.1F;
            } else { //is 6%
                multiple *= 1F + abilityBuffs.getSlider().get(AbilityBuffsEnum.MARKED.getPos()).getValue() * 0.06F;
            }
        }
        if (abilityBuffs.getBox().get(AbilityBuffsEnum.ARMOUR_BREAKER.getPos()).isSelected()) {
            multiple /= 0.7F; //Armour Breaker (-30% Def)
        } else if (abilityBuffs.getBox().get(AbilityBuffsEnum.COURSING_RESTRAINTS.getPos()).isSelected() || abilityBuffs.getBox().get(AbilityBuffsEnum.HAUNTING_MEMORY_FANATIC.getPos()).isSelected() || abilityBuffs.getBox().get(AbilityBuffsEnum.CHANT_OF_THE_LUNATIC.getPos()).isSelected()) {
            multiple /= 0.85F; //Coursing Restraints, Haunting Memory (from Fanatic), Chant of the Lunatic (-15% Def)
        }
        if (abilityBuffs.getBox().get(AbilityBuffsEnum.SAVIOURS_SACRIFICE.getPos()).isSelected()) multiple *= 1.2F; //Saviour's Sacrifice (+20%)

        for (TreeCheckBox tcb : tree.getTcb()) {
            if (tcb.isSelected()) {
                //All Classes
                switch (tcb.getFixedTreeName()) {
                    case "Earth Mastery": {
                        have_mastery[DamageType.EARTH.getId()] = true;
                        if (earthMin != 0 || earthMax != 0) {
                            earthMin += 2;
                            earthMax += 4;
                        }
                        break;
                    }
                    case "Thunder Mastery": {
                        have_mastery[DamageType.THUNDER.getId()] = true;
                        if (thunderMin != 0 || thunderMax != 0) {
                            thunderMin += 1;
                            thunderMax += 8;
                        }
                        break;
                    }
                    case "Water Mastery": {
                        have_mastery[DamageType.WATER.getId()] = true;
                        if (waterMin != 0 || waterMax != 0) {
                            waterMin += 2;
                            waterMax += 4;
                        }
                        break;
                    }
                    case "Fire Mastery": {
                        have_mastery[DamageType.FIRE.getId()] = true;
                        if (fireMin != 0 || fireMax != 0) {
                            fireMin += 3;
                            fireMax += 5;
                        }
                        break;
                    }
                    case "Air Mastery": {
                        have_mastery[DamageType.AIR.getId()] = true;
                        if (airMin != 0 || airMax != 0) {
                            airMin += 3;
                            airMax += 4;
                        }
                        break;
                    }
                    case "Precise Strike":
                        critBoost = true;
                        break;
                }
            }
        }

        switch (tree.getClasses()) {
            case "warrior": {
                setWarrior(tree, abilityBuffs);
                break;
            }
            case "assassin": {
                setAssassin(tree, abilityBuffs);
                break;
            }
            case "archer": {
                setArcher(tree, abilityBuffs);
                break;
            }
            case "mage": {
                setMage(tree, abilityBuffs);
                break;
            }
            case "shaman": {
                setShaman(tree, abilityBuffs);
                break;
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
