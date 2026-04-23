package com.github.iTzhsnu.WynnSearcher.builder;

import com.github.iTzhsnu.WynnSearcher.Identifications;
import com.github.iTzhsnu.WynnSearcher.builder.skilltree.AbilityIdEnum;
import com.github.iTzhsnu.WynnSearcher.builder.skilltree.SpellEnum;
import com.github.iTzhsnu.WynnSearcher.builder.skilltree.TreeBase;
import com.github.iTzhsnu.WynnSearcher.builder.skilltree.TreeCheckBox;
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

    private float neutral_min = 0;
    private float earth_min = 0;
    private float thunder_min = 0;
    private float water_min = 0;
    private float fire_min = 0;
    private float air_min = 0;

    private float neutral_max = 0;
    private float earth_max = 0;
    private float thunder_max = 0;
    private float water_max = 0;
    private float fire_max = 0;
    private float air_max = 0;

    private final List<DamageTemplate> list = new ArrayList<>();

    private int raw_Damage = 0;
    private int raw_Elem_Damage = 0;
    private float percent = 1F;
    private float multiple = 1F;
    private float melee_percent = 0F;
    private float melee_multiple = 1F;
    private float spell_percent = 0F;
    private float spell_multiple = 1F;
    private int spell_cost_1 = 0;
    private int spell_cost_2 = 0;
    private int spell_cost_3 = 0;
    private int spell_cost_4 = 0;
    private float proficiency = 1F;
    private int atkSpd = 0;
    private boolean crit_boost = false;

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

    public void setDamage_Display(ItemData itemData, SkillPoint sp, AbilityBuffs abilityBuffs, TreeBase tree, PowderEffect powder_effects, int[] id_Numbers, List<JTextField> powders) {
        this.itemData = itemData;
        this.weapon = itemData.getWeapon();
        pane.removeAll();

        if (!list.isEmpty()) list.clear();

        neutral_min = 0;
        earth_min = 0;
        thunder_min = 0;
        water_min = 0;
        fire_min = 0;
        air_min = 0;

        neutral_max = 0;
        earth_max = 0;
        thunder_max = 0;
        water_max = 0;
        fire_max = 0;
        air_max = 0;

        raw_Damage = 0;
        raw_Elem_Damage = 0;
        percent = 1F;
        multiple = 1F;
        melee_percent = 0F;
        melee_multiple = 1F;
        spell_percent = 0F;
        spell_multiple = 1F;
        spell_cost_1 = 0;
        spell_cost_2 = 0;
        spell_cost_3 = 0;
        spell_cost_4 = 0;
        proficiency = 1F;
        atkSpd = 0;
        crit_boost = false;

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
                neutral_min = getDamage(json, Identifications.NEUTRAL_DAMAGE, false);
                neutral_max = getDamage(json, Identifications.NEUTRAL_DAMAGE, true);

                //Earth Damage
                earth_min = getDamage(json, Identifications.EARTH_DAMAGE, false);
                earth_max = getDamage(json, Identifications.EARTH_DAMAGE, true);

                //Thunder Damage
                thunder_min = getDamage(json, Identifications.THUNDER_DAMAGE, false);
                thunder_max = getDamage(json, Identifications.THUNDER_DAMAGE, true);

                //Water Damage
                water_min = getDamage(json, Identifications.WATER_DAMAGE, false);
                water_max = getDamage(json, Identifications.WATER_DAMAGE, true);

                //Fire Damage
                fire_min = getDamage(json, Identifications.FIRE_DAMAGE, false);
                fire_max = getDamage(json, Identifications.FIRE_DAMAGE, true);

                //Air Damage
                air_min = getDamage(json, Identifications.AIR_DAMAGE, false);
                air_max = getDamage(json, Identifications.AIR_DAMAGE, true);
            }


            //Powder
            calcPowderDamages(powders);

            //Attack Speed
            atkSpd = id_Numbers[IdDisplay.ID_INT.get(Identifications.ATTACK_SPEED_BONUS)];
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
            id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_SPELL_DAMAGE)] += raw_Damage; //Raw Spell Damage += Raw Damage
            id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_MELEE_DAMAGE)] += raw_Damage; //Raw Melee Damage += Raw Damage
            id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_DAMAGE)] += raw_Elem_Damage; //Raw Elemental Damage += Raw Elem Damage

            CalcRaw calc_raw = new CalcRaw(neutral_min, earth_min, thunder_min, water_min, fire_min, air_min, neutral_max, earth_max, thunder_max, water_max, fire_max, air_max, null, null);

            float intelligence = 1F - sp.getSkillPoint(SkillPoint.SkillPointType.INTELLIGENCE).getSPBoost();

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
                list.add(new DamageTemplate("Fire Powder Special", 0, pane, null, sp, crit_boost, false));
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
                DamageTemplate previous = null;
                if (!list.isEmpty()) previous = list.get(list.size() - 1);
                list.add(new DamageTemplate("Earth Powder Special", 0, pane, previous, sp, crit_boost, false));
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
                DamageTemplate previous = null;
                if (!list.isEmpty()) previous = list.get(list.size() - 1);
                list.add(new DamageTemplate("Thunder Powder Special", 0, pane, previous, sp, crit_boost, false));
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
            DamageTemplate previousM = null;
            if (!list.isEmpty()) previousM = list.get(list.size() - 1);
            list.add(new DamageTemplate("Melee", 0, pane, previousM, sp, crit_boost, true));
            float[] melee = set_Damage_Percent(SpellEnum.MELEE);

            switch (tree.getClasses()) {
                case "warrior": {
                    setWarriorDamage(list, calc_raw, (percent + melee_percent) * multiple * melee_multiple, (percent + spell_percent) * multiple * spell_multiple,
                            id_Numbers, melee, atkSpd, sp, abilityBuffs, tb, tbd, crit_boost, intelligence, spell_cost_1, spell_cost_2, spell_cost_3, spell_cost_4);
                    break;
                }
                case "assassin": {
                    setAssassinDamage(list, calc_raw, (percent + melee_percent) * multiple * melee_multiple, (percent + spell_percent) * multiple * spell_multiple,
                            id_Numbers, melee, atkSpd, sp, abilityBuffs, tb, tbd, crit_boost, intelligence, spell_cost_1, spell_cost_2, spell_cost_3, spell_cost_4);
                    break;
                }
                case "archer": {
                    setArcherDamage(list, calc_raw, (percent + melee_percent) * multiple * melee_multiple, (percent + spell_percent) * multiple * spell_multiple,
                            id_Numbers, melee, atkSpd, sp, abilityBuffs, tb, tbd, crit_boost, intelligence, spell_cost_1, spell_cost_2, spell_cost_3, spell_cost_4);
                    break;
                }
                case "mage": {
                    setMageDamage(list, calc_raw, (percent + melee_percent) * multiple * melee_multiple, (percent + spell_percent) * multiple * spell_multiple,
                            id_Numbers, melee, atkSpd, sp, abilityBuffs, tb, tbd, crit_boost, intelligence, spell_cost_1, spell_cost_2, spell_cost_3, spell_cost_4);
                    break;
                }
                case "shaman": {
                    setShamanDamage(list, calc_raw, (percent + melee_percent) * multiple * melee_multiple, (percent + spell_percent) * multiple * spell_multiple,
                            id_Numbers, melee, atkSpd, sp, abilityBuffs, tb, tbd, crit_boost, intelligence, spell_cost_1, spell_cost_2, spell_cost_3, spell_cost_4);
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
                        spell_cost_1 -= 10;
                        break;
                    case "bashCost2":
                        spell_cost_1 -= 5;
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
                        spell_cost_2 -= 5;
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
                        spell_cost_3 -= 5;
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
                        spell_cost_4 += 10;
                        break;
                    case "Cheaper War Scream":
                        spell_cost_4 -= 5;
                        break;
                    case "Axe Kick":
                        tbd[AbilityIdEnum.AXE_KICK.pos] = true;
                        spell_cost_3 += 15;
                        break;
                    case "Discombobulate": //Maybe Raw ** Damage
                        if (abilityBuffs.getSlider().get(AbilityBuffsEnum.DISCOMBOBULATE.getPos()).getValue() > 0) {
                            if (neutral_min != 0 || neutral_max != 0) {
                                neutral_min += abilityBuffs.getSlider().get(AbilityBuffsEnum.DISCOMBOBULATE.getPos()).getValue();
                                neutral_max += abilityBuffs.getSlider().get(AbilityBuffsEnum.DISCOMBOBULATE.getPos()).getValue();
                            }
                            if (earth_min != 0 || earth_max != 0) {
                                earth_min += abilityBuffs.getSlider().get(AbilityBuffsEnum.DISCOMBOBULATE.getPos()).getValue();
                                earth_max += abilityBuffs.getSlider().get(AbilityBuffsEnum.DISCOMBOBULATE.getPos()).getValue();
                            }
                            if (thunder_min != 0 || thunder_max != 0) {
                                thunder_min += abilityBuffs.getSlider().get(AbilityBuffsEnum.DISCOMBOBULATE.getPos()).getValue();
                                thunder_max += abilityBuffs.getSlider().get(AbilityBuffsEnum.DISCOMBOBULATE.getPos()).getValue();
                            }
                            if (water_min != 0 || water_max != 0) {
                                water_min += abilityBuffs.getSlider().get(AbilityBuffsEnum.DISCOMBOBULATE.getPos()).getValue();
                                water_max += abilityBuffs.getSlider().get(AbilityBuffsEnum.DISCOMBOBULATE.getPos()).getValue();
                            }
                            if (fire_min != 0 || fire_max != 0) {
                                fire_min += abilityBuffs.getSlider().get(AbilityBuffsEnum.DISCOMBOBULATE.getPos()).getValue();
                                fire_max += abilityBuffs.getSlider().get(AbilityBuffsEnum.DISCOMBOBULATE.getPos()).getValue();
                            }
                            if (air_min != 0 || air_max != 0) {
                                air_min += abilityBuffs.getSlider().get(AbilityBuffsEnum.DISCOMBOBULATE.getPos()).getValue();
                                air_max += abilityBuffs.getSlider().get(AbilityBuffsEnum.DISCOMBOBULATE.getPos()).getValue();
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
                        tbd[AbilityIdEnum.BACKSTAB.pos] = true;
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
                        spell_cost_1 -= 10;
                        break;
                    case "totemCost2":
                        spell_cost_1 -= 5;
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
                        spell_cost_2 -= 5;
                        break;
                    case "Cheaper Uproot":
                        spell_cost_4 -= 5;
                        break;
                    case "Cheaper Aura":
                        spell_cost_3 -= 5;
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
            list.add(new DamageTemplate("Bash", calc_Spell_Cost(40 + spell_cost_1, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_1ST_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_1ST_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
            float[] bash = set_Damage_Percent(SpellEnum.BASH);
            if (itemData.getMajorIDList().contains(MajorIdEnum.DIVINE_HONOR)) add_Damage_Percent(bash, SpellEnum.DIVINE_HONOR); //Divine Honor
            if (itemData.getMajorIDList().contains(MajorIdEnum.OVERWHELM)) add_Damage_Percent(bash, SpellEnum.OVERWHELM); //Overwhelm
            if (tbd[AbilityIdEnum.DOUBLE_BASH.pos]) add_Damage_Percent(bash, SpellEnum.DOUBLE_BASH);
            if (tbd[AbilityIdEnum.QUADRUPLE_BASH.pos]) add_Damage_Percent(bash, SpellEnum.QUADRUPLE_BASH);
            if (tbd[AbilityIdEnum.STRONGER_BASH.pos]) add_Damage_Percent(bash, SpellEnum.STRONGER_BASH);
            if (tbd[AbilityIdEnum.THUNDERCLAP.pos]) add_Damage_Percent(bash, SpellEnum.THUNDERCLAP);
            if (tbd[AbilityIdEnum.SACRED_SURGE.pos] && abilityBuffs.getBox().get(AbilityBuffsEnum.SACRED_SURGE.getPos()).isSelected()) add_Damage_Percent(bash, SpellEnum.SACRED_SURGE);
            calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total, id_Numbers, bash, sp, false, false);
            calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calc_Total_Damage_Percent(bash), sp, true, false);
        }

        //Charge
        if (tbd[AbilityIdEnum.CHARGE.pos]) {
            list.add(new DamageTemplate("Charge", calc_Spell_Cost(25 + spell_cost_2, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_2ND_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_2ND_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, false));
            if (itemData.getMajorIDList().contains(MajorIdEnum.RALLY)) { //Rally (Major ID)
                list.get(list.size() - 1).addHeal("Heal", id_Numbers, 0.1F, false, false);
                list.get(list.size() - 1).addHeal("Heal to Allies", id_Numbers, 0.15F, false, false);
            } else {
                if (tbd[AbilityIdEnum.HEAVY_IMPACT.pos]) calcSpell("Heavy Impact", list.size() - 1, calc_raw, list, spell_total, id_Numbers, set_Damage_Percent(SpellEnum.HEAVY_IMPACT), sp, false, false);
                if (tbd[AbilityIdEnum.FLYBY_JAB.pos]) calcSpell("Flyby Jab", list.size() - 1, calc_raw, list, spell_total, id_Numbers, set_Damage_Percent(SpellEnum.FLYBY_JAB), sp, false, false);
                if (tbd[AbilityIdEnum.FLYING_KICK.pos]) calcSpell("Flying Kick", list.size() - 1, calc_raw, list, spell_total, id_Numbers, set_Damage_Percent(SpellEnum.FLYING_KICK), sp, false, false);
                if (tbd[AbilityIdEnum.COLLIDE.pos]) calcSpell("Collide", list.size() - 1, calc_raw, list, spell_total, id_Numbers, set_Damage_Percent(SpellEnum.COLLIDE), sp, false, false);
            }
        }

        //Uppercut
        if (tbd[AbilityIdEnum.UPPERCUT.pos]) {
            list.add(new DamageTemplate("Uppercut", calc_Spell_Cost(40 + spell_cost_3, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_3RD_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_3RD_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
            float[] uppercut = set_Damage_Percent(SpellEnum.UPPERCUT);
            if (tbd[AbilityIdEnum.HALF_MOON_SWIPE.pos]) add_Damage_Percent(uppercut, SpellEnum.HALF_MOON_SWIPE);
            if (tbd[AbilityIdEnum.WHIRLWIND_STRIKE.pos]) add_Damage_Percent(uppercut, SpellEnum.WHIRLWIND_STRIKE);
            if (tbd[AbilityIdEnum.AXE_KICK.pos]) add_Damage_Percent(uppercut, SpellEnum.AXE_KICK);
            if (tbd[AbilityIdEnum.SACRED_SURGE.pos] && abilityBuffs.getBox().get(AbilityBuffsEnum.SACRED_SURGE.getPos()).isSelected()) add_Damage_Percent(uppercut, SpellEnum.SACRED_SURGE);
            calcSpell("Uppercut", list.size() - 1, calc_raw, list, spell_total, id_Numbers, uppercut, sp, false, false);
            if (tbd[AbilityIdEnum.FIREWORKS.pos]) {
                add_Damage_Percent(uppercut, SpellEnum.FIREWORKS);
                calcSpell("Fireworks", list.size() - 1, calc_raw, list, spell_total, id_Numbers, set_Damage_Percent(SpellEnum.FIREWORKS), sp, false, false);
            }
            if (tbd[AbilityIdEnum.COMET.pos]) {
                add_Damage_Percent(uppercut, SpellEnum.COMET);
                calcSpell("Comet", list.size() - 1, calc_raw, list, spell_total, id_Numbers, set_Damage_Percent(SpellEnum.COMET), sp, false, false);
            }
            calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total, id_Numbers, uppercut, sp, true, false);
        }

        //War Scream
        if (tbd[AbilityIdEnum.WAR_SCREAM.pos]) {
            list.add(new DamageTemplate("War Scream", calc_Spell_Cost(30 + spell_cost_4, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_4TH_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_4TH_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, false));
            float[] war_scream = set_Damage_Percent(SpellEnum.WAR_SCREAM);
            float[] air_shout = set_Damage_Percent(SpellEnum.AIR_SHOUT);
            if (tbd[AbilityIdEnum.IRON_LUNGS.pos]) {
                add_Damage_Percent(war_scream, SpellEnum.IRON_LUNGS_FOR_WAR_SCREAM);
                add_Damage_Percent(air_shout, SpellEnum.IRON_LUNGS_FOR_AIR_SHOUT);
            }
            calcSpell("War Scream", list.size() - 1, calc_raw, list, spell_total, id_Numbers, war_scream, sp, false, false);
            if (tbd[AbilityIdEnum.AIR_SHOUT.pos]) calcSpell("Air Shout", list.size() - 1, calc_raw, list, spell_total, id_Numbers, air_shout, sp, false, false);
            if (tbd[AbilityIdEnum.TEMPEST.pos]) {
                float[] tempest = set_Damage_Percent(SpellEnum.TEMPEST);
                if (itemData.getMajorIDList().contains(MajorIdEnum.RECKLESS_ABANDON)) add_Damage_Percent(tempest, SpellEnum.RECKLESS_ABANDON);
                calcSpell("Single Tempest", list.size() - 1, calc_raw, list, spell_total, id_Numbers, tempest, sp, false, false);
                calcSpell("Total Tempest", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calc_Total_Damage_Percent(tempest), sp, false, false);
            }
        }

        //Flaming Uppercut
        if (tbd[AbilityIdEnum.FLAMING_UPPERCUT.pos]) {
            list.add(new DamageTemplate("Flaming Uppercut", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            float[] flaming_Uppercut = set_Damage_Percent(SpellEnum.FLAMING_UPPERCUT);
            calcSpell("DPS", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calc_Total_Damage_Percent_Manual(flaming_Uppercut, 1.66F), sp, false, false);
            calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total, id_Numbers, flaming_Uppercut, sp, false, false);
            calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calc_Total_Damage_Percent(flaming_Uppercut), sp, true, false);
        }
        //Counter
        if (tbd[AbilityIdEnum.COUNTER.pos]) {
            list.add(new DamageTemplate("Counter", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            calcSpell("", list.size() - 1, calc_raw, list, spell_total, id_Numbers, set_Damage_Percent(SpellEnum.COUNTER), sp, false, false);
        }
        //Boiling Blood
        if (tbd[AbilityIdEnum.BOILING_BLOOD.pos]) {
            list.add(new DamageTemplate("Boiling Blood", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            float[] boiling_Blood = set_Damage_Percent(SpellEnum.BOILING_BLOOD);
            calcSpell("DPS", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calc_Total_Damage_Percent_Manual(boiling_Blood, 2.5F), sp, false, false);
            calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total, id_Numbers, boiling_Blood, sp, false, false);
            calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calc_Total_Damage_Percent(boiling_Blood), sp, true, false);
        }
        //Sparkling Hope
        if (tbd[AbilityIdEnum.SPARKLING_HOPE.pos]) {
            list.add(new DamageTemplate("Sparkling Hope", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            calcSpell("", list.size() - 1, calc_raw, list, spell_total, id_Numbers, set_Damage_Percent(SpellEnum.SPARKLING_HOPE), sp, false, false);
        }
        //Cyclone
        if (tbd[AbilityIdEnum.CYCLONE.pos]) {
            list.add(new DamageTemplate("Cyclone", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            float[] cyclone = set_Damage_Percent(SpellEnum.CYCLONE);
            calcSpell("DPS", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calc_Total_Damage_Percent_Manual(cyclone, 2F), sp, false, false);
            calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total, id_Numbers, cyclone, sp, false, false);
            calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calc_Total_Damage_Percent(cyclone), sp, true, false);
        }
        //Shield Strike
        if (tbd[AbilityIdEnum.SHIELD_STRIKE.pos]) {
            list.add(new DamageTemplate("Shield Strike", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            calcSpell("", list.size() - 1, calc_raw, list, spell_total, id_Numbers, set_Damage_Percent(SpellEnum.SHIELD_STRIKE), sp, false, false);
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
        if (tbd[AbilityIdEnum.DOUBLE_SLICE.pos]) add_Damage_Percent(melee, SpellEnum.DOUBLE_SLICE);
        calcMelee("DPS", list.size() - 1, calc_raw, list, melee_total * echo, id_Numbers, calc_Total_Damage_Percent(melee), atkSpd, sp, false, true, false);
        if (tbd[AbilityIdEnum.DOUBLE_SLICE.pos]) { //Double Slice
            melee_Name = "Total Damage";
            isSingleHit = false;
            calcMelee("Single Hit", list.size() - 1, calc_raw, list, melee_total * echo, id_Numbers, melee, atkSpd, sp, false, false, true);
        }
        calcMelee(melee_Name, list.size() - 1, calc_raw, list, melee_total * echo, id_Numbers, calc_Total_Damage_Percent(melee), atkSpd, sp, true, false, isSingleHit);

        //Spin Attack
        if (tbd[AbilityIdEnum.SPIN_ATTACK.pos]) {
            if (tbd[AbilityIdEnum.LACERATE.pos]) {
                //Lacerate
                list.add(new DamageTemplate("Lacerate", calc_Spell_Cost(40 + spell_cost_1, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_1ST_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_1ST_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
                float[] lacerate = set_Damage_Percent(SpellEnum.LACERATE);
                if (tbd[AbilityIdEnum.BLAZING_POWDER.pos]) add_Damage_Percent(lacerate, SpellEnum.BLAZING_POWDER);
                if (tbd[AbilityIdEnum.STRONGER_LACERATE.pos]) add_Damage_Percent(lacerate, SpellEnum.STRONGER_LACERATE);
                calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total, id_Numbers, lacerate, sp, false, true);
                calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calc_Total_Damage_Percent(lacerate), sp, true, false);
            } else {
                //Spin Attack
                list.add(new DamageTemplate("Spin Attack", calc_Spell_Cost(40 + spell_cost_1, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_1ST_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_1ST_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
                float[] spin_Attack = set_Damage_Percent(SpellEnum.SPIN_ATTACK);
                if (tbd[AbilityIdEnum.DOUBLE_SPIN.pos]) add_Damage_Percent(spin_Attack, SpellEnum.DOUBLE_SPIN);
                if (tbd[AbilityIdEnum.BLAZING_POWDER.pos]) add_Damage_Percent(spin_Attack, SpellEnum.BLAZING_POWDER);
                if (tbd[AbilityIdEnum.FATAL_SPIN.pos]) add_Damage_Percent(spin_Attack, SpellEnum.FATAL_SPIN);
                calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total * echo, id_Numbers, spin_Attack, sp, false, true);
                calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total * echo, id_Numbers, calc_Total_Damage_Percent(spin_Attack), sp, true, false);
            }
        }

        //Dash
        if (tbd[AbilityIdEnum.DASH.pos]) {
            list.add(new DamageTemplate("Dash", calc_Spell_Cost(20 + spell_cost_2, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_2ND_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_2ND_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, false));
            if (tbd[AbilityIdEnum.DANCING_BLADE.pos]) {
                float[] dancing_Blade = set_Damage_Percent(SpellEnum.DANCING_BLADE);
                if (tbd[AbilityIdEnum.PIROUETTE.pos]) add_Damage_Percent(dancing_Blade, SpellEnum.PIROUETTE);
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
                list.add(new DamageTemplate("Backstab", calc_Spell_Cost(40 + spell_cost_3, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_3RD_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_3RD_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, false));
                calcSpell("Backstab", list.size() - 1, calc_raw, list, multihit_Boost, id_Numbers, set_Damage_Percent(SpellEnum.BACKSTAB), sp, false, true);
            } else {
                //Multihit
                list.add(new DamageTemplate("Multihit", calc_Spell_Cost(40 + spell_cost_3, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_3RD_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_3RD_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
                float[] multihit = set_Damage_Percent(SpellEnum.MULTIHIT);
                if (tbd[AbilityIdEnum.STRONGER_MULTIHIT.pos]) {
                    add_Damage_Percent(multihit, SpellEnum.STRONGER_MULTIHIT);
                    if (itemData.getMajorIDList().contains(MajorIdEnum.JUGGLE)) add_Damage_Percent(multihit, SpellEnum.JUGGLE);
                }
                if (tbd[AbilityIdEnum.BLADE_FURY.pos]) add_Damage_Percent(multihit, SpellEnum.BLADE_FURY);
                calcSpell("Single Hit", list.size() - 1, calc_raw, list, multihit_Boost, id_Numbers, multihit, sp, false, true);
                multihit = calc_Total_Damage_Percent(multihit);
                if (tbd[AbilityIdEnum.FATALITY.pos]) {
                    add_Damage_Percent(multihit, SpellEnum.FATALITY);
                    calcSpell("Fatality", list.size() - 1, calc_raw, list, multihit_Boost, id_Numbers, set_Damage_Percent(SpellEnum.FATALITY), sp, false, true);
                }
                calcSpell("Total Damage", list.size() - 1, calc_raw, list, multihit_Boost, id_Numbers, multihit, sp, true, false);
            }
        }

        //Smoke Bomb (0.5s per Attack)
        if (tbd[AbilityIdEnum.SMOKE_BOMB.pos]) {
            list.add(new DamageTemplate("Smoke Bomb", calc_Spell_Cost(35 + spell_cost_4, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_4TH_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_4TH_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
            float[] smoke_Bomb = set_Damage_Percent(SpellEnum.SMOKE_BOMB);
            int bomb_count = 1;
            if (tbd[AbilityIdEnum.STICKY_BOMB.pos]) add_Damage_Percent(smoke_Bomb, SpellEnum.STICKY_BOMB);
            if (tbd[AbilityIdEnum.WALL_OF_SMOKE.pos]) {
                add_Damage_Percent(smoke_Bomb, SpellEnum.WALL_OF_SMOKE);
                bomb_count = 3;
            }
            if (itemData.getMajorIDList().contains(MajorIdEnum.CHERRY_BOMBS)) { //Cherry Bombs
                add_Damage_Percent(smoke_Bomb, SpellEnum.CHERRY_BOMBS);
                calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total * echo, id_Numbers, smoke_Bomb, sp, false, false);
                calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total * echo, id_Numbers, calc_Total_Damage_Percent_Manual(smoke_Bomb, bomb_count), sp, true, false);
            } else {
                calcSpell("DPS", list.size() - 1, calc_raw, list, spell_total * echo, id_Numbers, calc_Total_Damage_Percent_Manual(smoke_Bomb, bomb_count * 2F), sp, false, false);
                calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total * echo, id_Numbers, smoke_Bomb, sp, false, true);
                calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total * echo, id_Numbers, calc_Total_Damage_Percent(smoke_Bomb), sp, true, false);
            }
        }

        //Bamboozle
        if (tbd[AbilityIdEnum.BAMBOOZLE.pos]) {
            list.add(new DamageTemplate("Bamboozle", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            calcSpell("", list.size() - 1, calc_raw, list, spell_total, id_Numbers, set_Damage_Percent(SpellEnum.BAMBOOZLE), sp, false, true);
        }

        //Shurikens
        if (tbd[AbilityIdEnum.SHURIKENS.pos]) {
            list.add(new DamageTemplate("Shuriken", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total * echo, id_Numbers, set_Damage_Percent(SpellEnum.SHURIKENS), sp, false, true);
        }

        //Jasmine Bloom
        if (tbd[AbilityIdEnum.JASMINE_BLOOM.pos]) {
            list.add(new DamageTemplate("Jasmine Bloom", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            float[] jasmine_Bloom = set_Damage_Percent(SpellEnum.JASMINE_BLOOM);
            calcSpell("DPS", list.size() - 1, calc_raw, list, spell_total * echo, id_Numbers, calc_Total_Damage_Percent_Manual(jasmine_Bloom, 3.33F), sp, false, false);
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
        if (tbd[AbilityIdEnum.DOUBLE_SHOTS.pos]) add_Damage_Percent(melee, SpellEnum.DOUBLE_SHOTS);
        if (tbd[AbilityIdEnum.TRIPLE_SHOTS.pos]) add_Damage_Percent(melee, SpellEnum.TRIPLE_SHOTS);
        calcMelee("DPS", list.size() - 1, calc_raw, list, melee_total, id_Numbers, calc_Total_Damage_Percent(melee), atkSpd, sp, false, true, false);
        if (tbd[AbilityIdEnum.DOUBLE_SHOTS.pos] || tbd[AbilityIdEnum.TRIPLE_SHOTS.pos]) {
            melee_Name = "Total Damage";
            calcMelee("Single Hit", list.size() - 1, calc_raw, list, melee_total, id_Numbers, melee, atkSpd, sp, false, false, false);
        }
        calcMelee(melee_Name, list.size() - 1, calc_raw, list, melee_total, id_Numbers, calc_Total_Damage_Percent(melee), atkSpd, sp, true, false, false);

        //Arrow Storm
        if (tbd[AbilityIdEnum.ARROW_STORM.pos]) {
            if (tbd[AbilityIdEnum.PHANTOM_RAY.pos]) {
                //Phantom Ray
                list.add(new DamageTemplate("Phantom Ray", calc_Spell_Cost(35 + spell_cost_1, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_1ST_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_1ST_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
                float[] phantom_Ray = set_Damage_Percent(SpellEnum.PHANTOM_RAY);
                float phantom_Boost = spell_total;
                if (tbd[AbilityIdEnum.DECIMATOR.pos] && abilityBuffs.getSlider().get(AbilityBuffsEnum.DECIMATOR.getPos()).getValue() > 0) phantom_Boost *= 1F + (0.1F * abilityBuffs.getSlider().get(AbilityBuffsEnum.DECIMATOR.getPos()).getValue()); //Decimator
                calcSpell("Single Hit", list.size() - 1, calc_raw, list, phantom_Boost, id_Numbers, phantom_Ray, sp, false, false);
                calcSpell("Total Damage", list.size() - 1, calc_raw, list, phantom_Boost, id_Numbers, calc_Total_Damage_Percent(phantom_Ray), sp, true, false);
            } else {
                //Arrow Storm
                list.add(new DamageTemplate("Arrow Storm", calc_Spell_Cost(35 + spell_cost_1, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_1ST_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_1ST_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
                float[] arrow_Storm = set_Damage_Percent(SpellEnum.ARROW_STORM);
                int stream = 1;
                if (tbd[AbilityIdEnum.NIMBLE_STRING.pos]) add_Damage_Percent(arrow_Storm, SpellEnum.NIMBLE_STRING);
                if (tbd[AbilityIdEnum.WINDSTORM.pos]) {
                    add_Damage_Percent(arrow_Storm, SpellEnum.WINDSTORM);
                    stream = 2;
                }
                if (tbd[AbilityIdEnum.ARROW_HURRICANE.pos]) {
                    add_Damage_Percent(arrow_Storm, SpellEnum.ARROW_HURRICANE);
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
                calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calc_Total_Damage_Percent_Manual(calc_Total_Damage_Percent(arrow_Storm), stream), sp, true, false);
            }
        }

        //Escape
        if (tbd[AbilityIdEnum.ESCAPE.pos]) {
            list.add(new DamageTemplate("Escape", calc_Spell_Cost(20 + spell_cost_2, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_2ND_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_2ND_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, false));
            if (tbd[AbilityIdEnum.FIERCE_STOMP.pos]) {
                //Fierce Stomp
                float[] fierce_Stomp = set_Damage_Percent(SpellEnum.FIERCE_STOMP);
                if (tbd[AbilityIdEnum.GEYSER_STOMP.pos]) add_Damage_Percent(fierce_Stomp, SpellEnum.GEYSER_STOMP);
                calcSpell("Fierce Stomp", list.size() - 1, calc_raw, list, spell_total, id_Numbers, fierce_Stomp, sp, false, false);
            }
            if (tbd[AbilityIdEnum.ESCAPE_ARTIST.pos]) calcSpell("Escape Artist", list.size() - 1, calc_raw, list, spell_total, id_Numbers, set_Damage_Percent(SpellEnum.ESCAPE_ARTIST), sp, false, false);
        }

        //Arrow Bomb
        if (tbd[AbilityIdEnum.ARROW_BOMB.pos]) {
            list.add(new DamageTemplate("Arrow Bomb", calc_Spell_Cost(45 + spell_cost_3, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_3RD_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_3RD_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
            float[] arrow_Bomb = set_Damage_Percent(SpellEnum.ARROW_BOMB);
            if (itemData.getMajorIDList().contains(MajorIdEnum.FORESTS_BLESSING)) add_Damage_Percent(arrow_Bomb, SpellEnum.FORESTS_BLESSING); //Forest's Blessing
            if (tbd[AbilityIdEnum.REFINED_GUNPOWDER.pos]) add_Damage_Percent(arrow_Bomb, SpellEnum.REFINED_GUNPOWDER);
            if (tbd[AbilityIdEnum.SHOCKING_BOMB.pos]) add_Damage_Percent(arrow_Bomb, SpellEnum.SHOCKING_BOMB);
            calcSpell("Arrow Bomb", list.size() - 1, calc_raw, list, spell_total, id_Numbers, arrow_Bomb, sp, false, false);
            if (tbd[AbilityIdEnum.HEART_SHATTER.pos]) {
                //Heart Shatter
                float[] heart_Shatter = set_Damage_Percent(SpellEnum.HEART_SHATTER);
                if (tbd[AbilityIdEnum.IMPLOSION.pos]) add_Damage_Percent(heart_Shatter, SpellEnum.IMPLOSION);
                add_Damage_Percent(arrow_Bomb, heart_Shatter);
                calcSpell("Heart Shatter", list.size() - 1, calc_raw, list, spell_total, id_Numbers, heart_Shatter, sp, false, false);
            }
            if (tbd[AbilityIdEnum.SHARPNEL_BOMB.pos]) {
                //Shrapnel Bomb
                float[] shrapnel_Bomb = set_Damage_Percent(SpellEnum.SHRAPNEL_BOMB);
                add_Damage_Percent(arrow_Bomb, shrapnel_Bomb);
                calcSpell("Shrapnel Bomb Single", list.size() - 1, calc_raw, list, spell_total, id_Numbers, shrapnel_Bomb, sp, false, false);
                calcSpell("Shrapnel Bomb Total", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calc_Total_Damage_Percent(shrapnel_Bomb), sp, false, false);
            }
            if (tbd[AbilityIdEnum.GRAPE_BOMB.pos]){
                //Grape Bomb
                float[] grape_Bomb = set_Damage_Percent(SpellEnum.GRAPE_BOMB);
                add_Damage_Percent(arrow_Bomb, grape_Bomb);
                calcSpell("Grape Bomb Single", list.size() - 1, calc_raw, list, spell_total, id_Numbers, grape_Bomb, sp, false, false);
                calcSpell("Grape Bomb Total", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calc_Total_Damage_Percent(grape_Bomb), sp, false, false);
            }
            calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total, id_Numbers, arrow_Bomb, sp, true, false);
            float[] bomb_self_damage = set_Damage_Percent(SpellEnum.ARROW_BOMB_SELF_DAMAGE);
            if (tbd[AbilityIdEnum.ROCKET_JUMP.pos]) add_Damage_Percent(bomb_self_damage, SpellEnum.ROCKET_JUMP);
            calcSpell("Self Damage", list.size() - 1, calc_raw, list, spell_total, id_Numbers, bomb_self_damage, sp, false, false);
        }

        //Arrow Shield
        if (tbd[AbilityIdEnum.ARROW_SHIELD.pos]) {
            if (tbd[AbilityIdEnum.GUARDIAN_ANGELS.pos]) {
                //Guardian Angels
                list.add(new DamageTemplate("Guardian Angels", calc_Spell_Cost(30 + spell_cost_4, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_4TH_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_4TH_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
                float[] guardian_Angels = set_Damage_Percent(SpellEnum.GUARDIAN_ANGELS);
                int times = 8;
                if (tbd[AbilityIdEnum.MORE_SHIELDS.pos]) add_Damage_Percent(guardian_Angels, SpellEnum.MORE_SHIELDS_GUARDIAN_ANGELS);
                if (tbd[AbilityIdEnum.BETTER_ARROW_SHIELD.pos]) add_Damage_Percent(guardian_Angels, SpellEnum.BETTER_ARROW_SHIELD_GUARDIAN_ANGELS);
                if (tbd[AbilityIdEnum.BETTER_GUARDIAN_ANGELS.pos]) times += 4; //Better Guardian Angels
                if (tbd[AbilityIdEnum.ALL_SEEING_PANOPTES.pos]){ //All-Seeing Panoptes
                    times += 4;
                    add_Damage_Percent(guardian_Angels, SpellEnum.ALL_SEEING_PANOPTES);
                    calcSpell("DPS", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calc_Total_Damage_Percent_Manual(calc_Total_Damage_Percent(guardian_Angels), 2F), sp, false, false);
                } else {
                    calcSpell("DPS", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calc_Total_Damage_Percent_Manual(calc_Total_Damage_Percent(guardian_Angels), 1.66F), sp, false, false);
                }
                calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total, id_Numbers, guardian_Angels, sp, false, false);
                calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calc_Total_Damage_Percent_Manual(calc_Total_Damage_Percent(guardian_Angels), times), sp, true, false);
            } else {
                //Arrow Shield
                list.add(new DamageTemplate("Arrow Shield", calc_Spell_Cost(30 + spell_cost_4, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_4TH_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_4TH_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
                float[] arrow_Shield = set_Damage_Percent(SpellEnum.ARROW_SHIELD);
                if (tbd[AbilityIdEnum.MORE_SHIELDS.pos]) add_Damage_Percent(arrow_Shield, SpellEnum.MORE_SHIELDS_ARROW_SHIELD);
                if (tbd[AbilityIdEnum.BETTER_ARROW_SHIELD.pos]) add_Damage_Percent(arrow_Shield, SpellEnum.BETTER_ARROW_SHIELD_ARROW_SHIELD);
                calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total, id_Numbers, arrow_Shield, sp, false, false);
                calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calc_Total_Damage_Percent(arrow_Shield), sp, true, false);
            }
        }

        //Arrow Rain
        if (tbd[AbilityIdEnum.ARROW_RAIN.pos]) {
            list.add(new DamageTemplate("Arrow Rain", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            calcSpell("", list.size() - 1, calc_raw, list, spell_total, id_Numbers, set_Damage_Percent(SpellEnum.ARROW_RAIN), sp, false, false);
        }

        //Fire Creep (0.4s per Attack)
        if (tbd[AbilityIdEnum.FIRE_CREEP.pos]) {
            list.add(new DamageTemplate("Fire Creep", 0, pane, list.get(list.size() - 1), sp, crit_boost, true));
            float[] fire_Creep = set_Damage_Percent(SpellEnum.FIRE_CREEP);
            if (tbd[AbilityIdEnum.SCORCHED_EARTH.pos]) add_Damage_Percent(fire_Creep, SpellEnum.SCORCHED_EARTH);
            calcSpell("DPS", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calc_Total_Damage_Percent_Manual(fire_Creep, 2.5F), sp, false, false);
            calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total, id_Numbers, fire_Creep, sp, false, false);
            calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calc_Total_Damage_Percent(fire_Creep), sp, true, false);
        }

        //Byrophyte Roots (0.4s per Attack)
        if (tbd[AbilityIdEnum.BRYOPHYTE_ROOTS.pos]) {
            list.add(new DamageTemplate("Bryophyte Roots", 0, pane, list.get(list.size() - 1), sp, crit_boost, true));
            float[] bryophyte_Roots = set_Damage_Percent(SpellEnum.BRYOPHYTE_ROOTS);
            calcSpell("DPS", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calc_Total_Damage_Percent_Manual(bryophyte_Roots, 2.5F), sp, false, false);
            calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total, id_Numbers, bryophyte_Roots, sp, false, false);
            calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calc_Total_Damage_Percent(bryophyte_Roots), sp, true, false);
        }

        //Basaltic Trap
        if (tbd[AbilityIdEnum.BASALTIC_TRAP.pos]) {
            list.add(new DamageTemplate("Basaltic Trap", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            float[] basaltic_Trap = set_Damage_Percent(SpellEnum.BASALTIC_TRAP);
            float trap_Boost = spell_total;
            if (tbd[AbilityIdEnum.MINEFIELD.pos]) add_Damage_Percent(basaltic_Trap, SpellEnum.MINEFIELD);
            if (tbd[AbilityIdEnum.PATIENT_HUNTER.pos]) trap_Boost *= 1F + (abilityBuffs.getSlider().get(AbilityBuffsEnum.PATIENT_HUNTER.getPos()).getValue() * 0.01F); //Patient Hunter
            calcSpell("Single Hit", list.size() - 1, calc_raw, list, trap_Boost, id_Numbers, basaltic_Trap, sp, false, false);
        }

        //Twain's Arc
        if (tbd[AbilityIdEnum.TWAINS_ARC.pos]) {
            list.add(new DamageTemplate("Twain's Arc", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            float[] twains_arc = set_Damage_Percent(SpellEnum.TWAINS_ARC);
            calcMelee("Single Hit", list.size() - 1, calc_raw, list, melee_total, id_Numbers, twains_arc, atkSpd, sp, false, true, false);
            if (tbd[AbilityIdEnum.PHASING_BEAM.pos]) {
                add_Damage_Percent(twains_arc, SpellEnum.PHASING_BEAM);
                calcMelee("After Piercing", list.size() - 1, calc_raw, list, melee_total, id_Numbers, twains_arc, atkSpd, sp, false, true, false);
            }
        }

        //Call of the Hound (0.25s per Attack) TODO apply Forest's Blessing
        if (tbd[AbilityIdEnum.CALL_OF_THE_HOUND.pos]) {
            list.add(new DamageTemplate("Call of the Hound", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            float[] call_of_the_hound = set_Damage_Percent(SpellEnum.CALL_OF_THE_HOUND);
            if (tbd[AbilityIdEnum.BEAST_LORE.pos]) add_Damage_Percent(call_of_the_hound, SpellEnum.BEAST_LORE);
            calcSpell("DPS", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calc_Total_Damage_Percent_Manual(call_of_the_hound, 4F), sp, false, false);
            calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total, id_Numbers, call_of_the_hound, sp, false, false);
        }

        //Crepuscular Ray (0.05s per Attack)
        if (tbd[AbilityIdEnum.CREPUSCULAR_RAY.pos] && tbd[AbilityIdEnum.FOCUS.pos] && abilityBuffs.getSlider().get(AbilityBuffsEnum.FOCUS.getPos()).getValue() >= 5) {
            list.add(new DamageTemplate("Crepuscular Ray", 0, pane, list.get(list.size() - 1), sp, crit_boost, true));
            float[] crepuscular_Ray = set_Damage_Percent(SpellEnum.CREPUSCULAR_RAY);
            calcSpell("DPS", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calc_Total_Damage_Percent_Manual(crepuscular_Ray, 20F), sp, false, false);
            calcSpell("Single Hit", list.size() -1, calc_raw, list, spell_total, id_Numbers, crepuscular_Ray, sp, false, false);
            calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calc_Total_Damage_Percent_Manual(crepuscular_Ray, 20 * abilityBuffs.getSlider().get(5).getValue()), sp, true, false);
        }

        //Tangled Traps (0.2s per Attack)
        if (tbd[AbilityIdEnum.TANGLED_TRAPS.pos]) {
            list.add(new DamageTemplate("Tangled Traps", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            float[] tangled_Traps = set_Damage_Percent(SpellEnum.TANGLED_TRAPS);
            calcSpell("DPS", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calc_Total_Damage_Percent_Manual(tangled_Traps, 5F), sp, false, false);
            calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total, id_Numbers, tangled_Traps, sp, false, false);
        }

        //Ivyroot Mamba (1.5s per Attack) TODO apply Forest's Blessing
        if (tbd[AbilityIdEnum.IVYROOT_MAMBA.pos]) {
            list.add(new DamageTemplate("Ivyroot Mamba", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            float[] ivyroot_mamba = set_Damage_Percent(SpellEnum.IVYROOT_MAMBA);
            int max_snake = 1;
            if (tbd[AbilityIdEnum.BEAST_LORE.pos]) max_snake += 1;
            calcSpell("Max DPS", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calc_Total_Damage_Percent_Manual(ivyroot_mamba, 0.66F * max_snake), sp, false, false);
            if (max_snake > 1) calcSpell("per Snake DPS", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calc_Total_Damage_Percent_Manual(ivyroot_mamba, 0.66F), sp, false, false);
            calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total, id_Numbers, ivyroot_mamba, sp, false, false);
        }

        //Murder Flock (0.9s per Attack) TODO apply Forest's Blessing
        if (tbd[AbilityIdEnum.MURDER_FLOCK.pos]) {
            list.add(new DamageTemplate("Murder Flock", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            float[] murder_flock = set_Damage_Percent(SpellEnum.MURDER_FLOCK);
            int max_crow = 2;
            if (tbd[AbilityIdEnum.BEAST_LORE.pos]) max_crow += 2;
            calcSpell("Max DPS", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calc_Total_Damage_Percent_Manual(murder_flock, max_crow * 1.11F), sp, false, false);
            calcSpell("per Crow DPS", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calc_Total_Damage_Percent_Manual(murder_flock, 1.11F), sp, false, false);
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
                list.add(new DamageTemplate("Heal", calc_Spell_Cost(35 + spell_cost_1, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_1ST_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_1ST_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, false));
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
                float[] sunshower = set_Damage_Percent(SpellEnum.SUNSHOWER);
                if (tbd[AbilityIdEnum.STRONGER_SUNSHOWER.pos]) add_Damage_Percent(sunshower, SpellEnum.STRONGER_SUNSHOWER);
                calcSpell("Sunshower", list.size() - 1, calc_raw, list, spell_total, id_Numbers, sunshower, sp, false, false);
            }
        }

        //Teleport
        if (tbd[AbilityIdEnum.TELEPORT.pos]) {
            list.add(new DamageTemplate("Teleport", calc_Spell_Cost(25 + spell_cost_2, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_2ND_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_2ND_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, false));
            if (tbd[AbilityIdEnum.WIND_SLASH.pos]) {
                //Wind Slash
                calcSpell("Wind Slash", list.size() - 1, calc_raw, list, spell_total, id_Numbers, set_Damage_Percent(SpellEnum.WIND_SLASH), sp, false, false);
            }
            if (tbd[AbilityIdEnum.EXPLOSIVE_ENTRANCE.pos]) {
                //Explosive Entrance
                calcSpell("Explosive Entrance", list.size() - 1, calc_raw, list, spell_total, id_Numbers, set_Damage_Percent(SpellEnum.EXPLOSIVE_ENTRANCE), sp, false, false);
            }
            if (tbd[AbilityIdEnum.TRANSONIC_WARP.pos] && abilityBuffs.getSlider().get(AbilityBuffsEnum.WINDED.getPos()).getValue() > 0) { //Winded
                //Transonic Warp
                calcSpell("Transonic Warp", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calc_Total_Damage_Percent_Manual(set_Damage_Percent(SpellEnum.TRANSONIC_WARP), abilityBuffs.getSlider().get(AbilityBuffsEnum.WINDED.getPos()).getValue()), sp, false, false);
            }
        }

        //Meteor
        if (tbd[AbilityIdEnum.METEOR.pos]) {
            if (tbd[AbilityIdEnum.OPHANIM.pos]) {
                //Ophanim
                list.add(new DamageTemplate("Ophanim", calc_Spell_Cost(50 + spell_cost_3, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_3RD_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_3RD_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, false));
                float[] ophanim = set_Damage_Percent(SpellEnum.OPHANIM);
                if (tbd[AbilityIdEnum.BREATHLESS.pos] && abilityBuffs.getSlider().get(AbilityBuffsEnum.WINDED.getPos()).getValue() > 0) add_Damage_Percent(ophanim, calc_Total_Damage_Percent_Manual(set_Damage_Percent(SpellEnum.BREATHLESS_OPHANIM), abilityBuffs.getSlider().get(AbilityBuffsEnum.WINDED.getPos()).getValue())); //Winded
                if (tbd[AbilityIdEnum.STRONGER_OPHANIM.pos]) add_Damage_Percent(ophanim, SpellEnum.STRONGER_OPHANIM);
                if (tbd[AbilityIdEnum.DIVINATION.pos]) add_Damage_Percent(ophanim, SpellEnum.DIVINATION);
                calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total, id_Numbers, ophanim, sp, false, false);
            } else {
                //Meteor
                list.add(new DamageTemplate("Meteor", calc_Spell_Cost(50 + spell_cost_3, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_3RD_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_3RD_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
                float[] meteor = set_Damage_Percent(SpellEnum.METEOR);
                if (tbd[AbilityIdEnum.STRONGER_METEOR.pos]) add_Damage_Percent(meteor, SpellEnum.STRONGER_METEOR);
                if (tbd[AbilityIdEnum.BREATHLESS.pos] && abilityBuffs.getSlider().get(AbilityBuffsEnum.WINDED.getPos()).getValue() > 0) add_Damage_Percent(meteor, calc_Total_Damage_Percent_Manual(set_Damage_Percent(SpellEnum.BREATHLESS_METEOR), abilityBuffs.getSlider().get(AbilityBuffsEnum.WINDED.getPos()).getValue())); //Winded
                calcSpell("Meteor", list.size() - 1 ,calc_raw, list, spell_total, id_Numbers, meteor, sp, false, false);
                if (tbd[AbilityIdEnum.THUNDERSTORM.pos]) {
                    float[] thunderstorm = set_Damage_Percent(SpellEnum.THUNDERSTORM);
                    if (tbd[AbilityIdEnum.BREATHLESS.pos] && abilityBuffs.getSlider().get(AbilityBuffsEnum.WINDED.getPos()).getValue() > 0) add_Damage_Percent(thunderstorm, calc_Total_Damage_Percent_Manual(set_Damage_Percent(SpellEnum.BREATHLESS_THUNDERSTORM), abilityBuffs.getSlider().get(AbilityBuffsEnum.WINDED.getPos()).getValue())); //Winded
                    add_Damage_Percent(meteor, thunderstorm);
                    calcSpell("Thunderstorm", list.size() - 1, calc_raw, list, spell_total, id_Numbers, thunderstorm, sp, false, false);
                }
                calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total, id_Numbers, meteor, sp, true, false);
            }
        }

        //Ice Snake
        if (tbd[AbilityIdEnum.ICE_SNAKE.pos]) {
            list.add(new DamageTemplate("Ice Snake", calc_Spell_Cost(30 + spell_cost_4, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_4TH_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_4TH_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
            float[] ice_Snake = set_Damage_Percent(SpellEnum.ICE_SNAKE);
            if (tbd[AbilityIdEnum.WINDSWEEPER.pos] && abilityBuffs.getSlider().get(AbilityBuffsEnum.WINDED.getPos()).getValue() > 0) add_Damage_Percent(ice_Snake, calc_Total_Damage_Percent_Manual(set_Damage_Percent(SpellEnum.WINDSWEEPER), abilityBuffs.getSlider().get(AbilityBuffsEnum.WINDED.getPos()).getValue())); //Winded
            if (tbd[AbilityIdEnum.SENTIENT_SNAKE.pos]) add_Damage_Percent(ice_Snake, SpellEnum.SENTIENT_SNAKE);
            if (tbd[AbilityIdEnum.GUST.pos]) add_Damage_Percent(ice_Snake, SpellEnum.GUST);
            if (tbd[AbilityIdEnum.SNAKE_NEST.pos]) {
                add_Damage_Percent(ice_Snake, SpellEnum.SNAKE_NEST);
                calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total, id_Numbers, ice_Snake, sp, false, false);
                calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calc_Total_Damage_Percent(ice_Snake), sp, true, false);
            } else {
                calcSpell("", list.size() - 1, calc_raw, list, spell_total, id_Numbers, ice_Snake, sp, true, false);
            }
        }

        //Burning Sigil
        if (tbd[AbilityIdEnum.BURNING_SIGIL.pos]) {
            list.add(new DamageTemplate("Burning Sigil", 0, pane, list.get(list.size() - 1), sp, crit_boost, true));
            float[] burning_Sigil = set_Damage_Percent(SpellEnum.BURNING_SIGIL);
            calcSpell("DPS", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calc_Total_Damage_Percent_Manual(burning_Sigil, 2.5F), sp, false, false);
            calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total, id_Numbers, burning_Sigil, sp, false, false);
            calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calc_Total_Damage_Percent(burning_Sigil), sp, true, false);
        }

        //Pyrokinesis
        if (tbd[AbilityIdEnum.PYROKINESIS.pos]) {
            list.add(new DamageTemplate("Psychokinesis", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            float[] psychokinesis = set_Damage_Percent(SpellEnum.PYROKINESIS);
            calcMelee("DPS", list.size() - 1, calc_raw, list, melee_total, id_Numbers, psychokinesis, atkSpd, sp, false, true, false);
            calcMelee("Single Hit", list.size() - 1, calc_raw, list, melee_total, id_Numbers, psychokinesis, atkSpd, sp, false, false, false);
        }

        //Lightweaver
        if (tbd[AbilityIdEnum.LIGHTWEAVER.pos]) {
            list.add(new DamageTemplate("Lightweaver", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total, id_Numbers, set_Damage_Percent(SpellEnum.LIGHTWEAVER), sp, false, false);
        }
    }

    public void setShamanDamage(List<DamageTemplate> list, CalcRaw calc_raw, float melee_total, float spell_total, int[] id_Numbers, float[] melee, int atkSpd, SkillPoint sp, AbilityBuffs abilityBuffs, boolean[] tb, boolean[] tbd, boolean crit_boost, float intelligence, int spell_cost_1, int spell_cost_2, int spell_cost_3, int spell_cost_4) {
        //Melee
        melee = set_Damage_Percent(SpellEnum.SHAMAN_MELEE);
        if (tbd[AbilityIdEnum.HAND_OF_THE_SHAMAN.pos]) add_Damage_Percent(melee, SpellEnum.HAND_OF_THE_SHAMAN);
        calcMelee("DPS", list.size() - 1, calc_raw, list, melee_total, id_Numbers, calc_Total_Damage_Percent(melee), atkSpd, sp, false, true, false);
        calcMelee("Single Hit", list.size() - 1, calc_raw, list, melee_total, id_Numbers, melee, atkSpd, sp, false, false, false);
        calcMelee("Total Damage", list.size() - 1, calc_raw, list, melee_total, id_Numbers, calc_Total_Damage_Percent(melee), atkSpd, sp, true, false, false);

        //Totem
        if (tbd[AbilityIdEnum.TOTEM.pos]) {
            list.add(new DamageTemplate("Totem", calc_Spell_Cost(30 + spell_cost_1, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_1ST_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_1ST_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, false));
            float[] totem = set_Damage_Percent(SpellEnum.TOTEM);
            float totem_percent = spell_total;
            int totem_count = 1;
            int furious_effigy = 1; //Furious Effigy (Major ID)
            if (tbd[AbilityIdEnum.STRONGER_TOTEM.pos]) add_Damage_Percent(totem, SpellEnum.STRONGER_TOTEM);
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
            calcSpell("DPS", list.size() - 1, calc_raw, list, totem_percent, id_Numbers, calc_Total_Damage_Percent_Manual(totem, 2.5F * totem_count * furious_effigy), sp, false, false);
            calcSpell("Single Hit", list.size() - 1, calc_raw, list, totem_percent, id_Numbers, totem, sp, false, false);
        }

        //Haul
        if (tbd[AbilityIdEnum.HAUL.pos]) {
            list.add(new DamageTemplate("Haul", calc_Spell_Cost(15 + spell_cost_2, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_2ND_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_2ND_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
            if (tbd[AbilityIdEnum.NATURES_JOLT.pos]) {
                //Nature's Jolt
                calcSpell("Nature's Jolt", list.size() - 1, calc_raw, list, spell_total, id_Numbers, set_Damage_Percent(SpellEnum.NATURES_JOLT), sp, !(tbd[AbilityIdEnum.FROG_DANCE.pos] && abilityBuffs.getBox().get(AbilityBuffsEnum.MASK_OF_THE_COWARD.getPos()).isSelected()), false);
            }
            if (tbd[AbilityIdEnum.FROG_DANCE.pos] && abilityBuffs.getBox().get(AbilityBuffsEnum.MASK_OF_THE_COWARD.getPos()).isSelected()) {
                //Frog Dance
                calcSpell("Frog Dance", list.size() - 1, calc_raw, list, spell_total, id_Numbers, set_Damage_Percent(SpellEnum.FROG_DANCE), sp, !tbd[AbilityIdEnum.NATURES_JOLT.pos], false);
            }
            if (tbd[AbilityIdEnum.NATURES_JOLT.pos] && tbd[AbilityIdEnum.FROG_DANCE.pos] && abilityBuffs.getBox().get(AbilityBuffsEnum.MASK_OF_THE_COWARD.getPos()).isSelected()) {
                float[] haul = set_Damage_Percent(SpellEnum.NATURES_JOLT);
                add_Damage_Percent(haul, SpellEnum.FROG_DANCE);
                calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total, id_Numbers, haul, sp, true, false);
            }
        }

        //Aura
        if (tbd[AbilityIdEnum.AURA.pos]) {
            list.add(new DamageTemplate("Aura", calc_Spell_Cost(40 + spell_cost_3, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_3RD_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_3RD_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
            float[] aura = set_Damage_Percent(SpellEnum.AURA);
            float aura_percent = spell_total;
            int totem_count = 1;
            float heal = 1F;
            boolean gentle_Glow = itemData.getMajorIDList().contains(MajorIdEnum.GENTLE_GLOW); //Gentle Glow
            if (tbd[AbilityIdEnum.SHOCKING_AURA.pos]) add_Damage_Percent(aura, SpellEnum.SHOCKING_AURA);
            if (tbd[AbilityIdEnum.STORM_DANCE.pos]) add_Damage_Percent(aura, SpellEnum.STORM_DANCE);
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
                calcSpell("Total Damage", list.size() - 1, calc_raw, list, aura_percent, id_Numbers, calc_Total_Damage_Percent_Manual(aura, totem_count * 2), sp, true, false);
            } else {
                if (tbd[AbilityIdEnum.SACRIFICIAL_SHRINE.pos] && abilityBuffs.getBox().get(AbilityBuffsEnum.SACRIFICIAL_SHRINE.getPos()).isSelected()) {
                    list.get(list.size() - 1).addHeal("Total Heal", id_Numbers, 0.25F * heal * totem_count, tbd[AbilityIdEnum.FLUID_HEALING_SHAMAN.pos], gentle_Glow);
                    list.get(list.size() - 1).addHeal("Single Heal", id_Numbers, 0.25F * heal, tbd[AbilityIdEnum.FLUID_HEALING_SHAMAN.pos], gentle_Glow);
                    if (gentle_Glow) list.get(list.size() - 1).addHeal("Heal to Allies", id_Numbers, 0.25F * heal * 1.6F, tbd[AbilityIdEnum.FLUID_HEALING_SHAMAN.pos], true);
                    aura_percent *= 1.35F;
                }
                calcSpell("Single Hit", list.size() - 1, calc_raw, list, aura_percent, id_Numbers, aura, sp, false, false);
                calcSpell("Total Damage", list.size() - 1, calc_raw, list, aura_percent, id_Numbers, calc_Total_Damage_Percent_Manual(aura, totem_count), sp, true, false);
            }
        }

        //Uproot
        if (tbd[AbilityIdEnum.UPROOT.pos]) {
            if (tbd[AbilityIdEnum.MASKS.pos]) {
                list.add(new DamageTemplate("Switch Mask", calc_Spell_Cost(30 + spell_cost_4, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_4TH_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_4TH_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, false));
                if (tbd[AbilityIdEnum.CHANT_OF_THE_COWARD.pos]) calcSpell("Switch to Coward", list.size() - 1, calc_raw, list, spell_total, id_Numbers, set_Damage_Percent(SpellEnum.CHANT_OF_THE_COWARD), sp, false, false);
            } else {
                list.add(new DamageTemplate("Uproot", calc_Spell_Cost(30 + spell_cost_4, intelligence, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_4TH_SPELL_COST)], id_Numbers[IdDisplay.ID_INT.get(Identifications.PERCENT_4TH_SPELL_COST)]), pane, list.get(list.size() - 1), sp, crit_boost, true));
                float[] uproot = set_Damage_Percent(SpellEnum.UPROOT);
                if (tbd[AbilityIdEnum.FLAMING_TONGUE.pos]) {
                    //Flaming Tongue
                    add_Damage_Percent(uproot, SpellEnum.FLAMING_TONGUE);
                    calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total, id_Numbers, uproot, sp, false, false);
                    calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calc_Total_Damage_Percent(uproot), sp, true, false);
                } else {
                    calcSpell("", list.size() - 1, calc_raw, list, spell_total, id_Numbers, uproot, sp, true, false);
                }
            }
        }

        //Rain Dance
        if (tbd[AbilityIdEnum.RAIN_DANCE.pos]) {
            list.add(new DamageTemplate("Rain Dance", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            float[] rain_Dance = set_Damage_Percent(SpellEnum.RAIN_DANCE);
            calcSpell("DPS", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calc_Total_Damage_Percent_Manual(rain_Dance, 2.5F), sp, false, false);
            calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total, id_Numbers, rain_Dance, sp, false, false);
        }

        //Puppet Master
        if (tbd[AbilityIdEnum.PUPPET_MASTER.pos]) {
            list.add(new DamageTemplate("Puppet Master", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            float[] puppet_Master = set_Damage_Percent(SpellEnum.PUPPET_MASTER);
            float puppet_Percent = spell_total;
            float puppet_AtkSpd = 2F;
            if (itemData.getMajorIDList().contains(MajorIdEnum.STRINGS_OF_FATE)) puppet_Percent *= 2F; //Strings of Fate (Major ID)
            if (tbd[AbilityIdEnum.BULLWHIP.pos] && abilityBuffs.getBox().get(AbilityBuffsEnum.BULLWHIP.getPos()).isSelected()) puppet_Percent *= 1.2F; //Bullwhip
            if (tbd[AbilityIdEnum.INVIGORATING_WAVE.pos] && abilityBuffs.getBox().get(AbilityBuffsEnum.INVIGORATING_WAVE.getPos()).isSelected()) puppet_AtkSpd *= 1.3F; //Invigorating Wave
            int maxPuppets = 3;
            if (tbd[AbilityIdEnum.MORE_PUPPETS_1.pos]) maxPuppets += 1;
            if (tbd[AbilityIdEnum.MORE_PUPPETS_2.pos]) maxPuppets += 2;
            if (tbd[AbilityIdEnum.SHEPHERD.pos] && abilityBuffs.getSlider().get(AbilityBuffsEnum.SHEPHERD.getPos()).getValue() > 0) maxPuppets += abilityBuffs.getSlider().get(AbilityBuffsEnum.SHEPHERD.getPos()).getValue(); //Shepherd
            calcSpell("Max DPS", list.size() - 1, calc_raw, list, puppet_Percent, id_Numbers, calc_Total_Damage_Percent_Manual(puppet_Master, maxPuppets * puppet_AtkSpd), sp, false, false);
            calcSpell("Single DPS", list.size() - 1, calc_raw, list, puppet_Percent, id_Numbers, calc_Total_Damage_Percent_Manual(puppet_Master, puppet_AtkSpd), sp, false, false);
            calcSpell("Single Hit", list.size() - 1, calc_raw, list, puppet_Percent, id_Numbers, puppet_Master, sp, false, false);
        }

        //Exploding Puppets
        if (tbd[AbilityIdEnum.EXPLODING_PUPPETS.pos]) {
            list.add(new DamageTemplate("Exploding Puppets", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            float puppet_Percent = spell_total;
            if (itemData.getMajorIDList().contains(MajorIdEnum.STRINGS_OF_FATE)) puppet_Percent *= 2F; //Strings of Fate (Major ID)
            calcSpell("", list.size() - 1, calc_raw, list, puppet_Percent, id_Numbers, set_Damage_Percent(SpellEnum.EXPLODING_PUPPETS), sp, false, false);
        }

        //Twisted Tether
        if (tbd[AbilityIdEnum.TWISTED_TETHER.pos]) {
            list.add(new DamageTemplate("Twisted Tether", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            float[] twisted_Tether = set_Damage_Percent(SpellEnum.TWISTED_TETHER);
            if (tbd[AbilityIdEnum.STRONGER_TETHER.pos]) add_Damage_Percent(twisted_Tether, SpellEnum.STRONGER_TETHER);
            calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total, id_Numbers, twisted_Tether, sp, false, false);
        }

        //Crimson Effigy
        if (tbd[AbilityIdEnum.CRIMSON_EFFIGY.pos]) {
            list.add(new DamageTemplate("Crimson Effigy", 0, pane, list.get(list.size() - 1), sp, crit_boost, false));
            float[] crimson_Effigy = set_Damage_Percent(SpellEnum.CRIMSON_EFFIGY);
            float effigy_Percent = spell_total;
            float effigy_AtkSpd = 2F;
            if (tbd[AbilityIdEnum.BULLWHIP.pos] && abilityBuffs.getBox().get(AbilityBuffsEnum.BULLWHIP.getPos()).isSelected()) effigy_Percent *= 1.2F; //Bullwhip
            if (tbd[AbilityIdEnum.INVIGORATING_WAVE.pos] && abilityBuffs.getBox().get(AbilityBuffsEnum.INVIGORATING_WAVE.getPos()).isSelected()) effigy_AtkSpd *= 1.3F; //Invigorating Wave

            String n = "DPS";
            if (tbd[AbilityIdEnum.MORE_EFFIGIES.pos]) {
                add_Damage_Percent(crimson_Effigy, SpellEnum.MORE_EFFIGIES);
                n = "Single DPS";
                calcSpell("Total DPS", list.size() - 1, calc_raw, list, effigy_Percent, id_Numbers, calc_Total_Damage_Percent_Manual(crimson_Effigy, effigy_AtkSpd * 2F), sp, false, false);
            }
            calcSpell(n, list.size() - 1, calc_raw, list, effigy_Percent, id_Numbers, calc_Total_Damage_Percent_Manual(crimson_Effigy, effigy_AtkSpd), sp, false, false);
            calcSpell("Single Hit", list.size() - 1, calc_raw, list, effigy_Percent, id_Numbers, crimson_Effigy, sp, false, false);
        }

        //Blood Sorrow
        if (tbd[AbilityIdEnum.BLOOD_SORROW.pos]) {
            list.add(new DamageTemplate("Blood Sorrow", 0, pane, list.get(list.size() - 1), sp, crit_boost, true));
            float[] blood_Sorrow = set_Damage_Percent(SpellEnum.BLOOD_SORROW);
            calcSpell("DPS", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calc_Total_Damage_Percent_Manual(blood_Sorrow, 5F), sp, false, false);
            calcSpell("Single Hit", list.size() - 1, calc_raw, list, spell_total, id_Numbers, blood_Sorrow, sp, false, false);
            calcSpell("Total Damage", list.size() - 1, calc_raw, list, spell_total, id_Numbers, calc_Total_Damage_Percent(blood_Sorrow), sp, true, false);
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

    private int calc_Spell_Cost(int base, float intelligence, int id_Raw, int id_Percent) {
        return Math.max((int) Math.floor((base * intelligence + id_Raw) * (1F + id_Percent / 100F)), 1);
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
            neutral_min += (calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_MELEE_DAMAGE)], Damage_Type.NEUTRAL, false, true) + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_NEUTRAL_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_NEUTRAL_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;
            neutral_max += (calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_MELEE_DAMAGE)], Damage_Type.NEUTRAL, true, true) + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_NEUTRAL_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_NEUTRAL_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;

            neutral_min *= tomeBonus;
            neutral_max *= tomeBonus;
        }

        if (earth_min != 0 || earth_max != 0) {
            earth_min += (calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_MELEE_DAMAGE)], Damage_Type.EARTH, false, true) + calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_DAMAGE)], Damage_Type.EARTH, false, false) + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_EARTH_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_EARTH_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;
            earth_max += (calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_MELEE_DAMAGE)], Damage_Type.EARTH, true, true) + calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_DAMAGE)], Damage_Type.EARTH, true, false) + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_EARTH_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_EARTH_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;

            earth_min *= tomeBonus;
            earth_max *= tomeBonus;
        }

        if (thunder_min != 0 || thunder_max != 0) {
            thunder_min += (calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_MELEE_DAMAGE)], Damage_Type.THUNDER, false, true) + calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_DAMAGE)], Damage_Type.THUNDER, false, false) + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_THUNDER_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_THUNDER_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;
            thunder_max += (calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_MELEE_DAMAGE)], Damage_Type.THUNDER, true, true) + calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_DAMAGE)], Damage_Type.THUNDER, true, false) + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_THUNDER_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_THUNDER_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;

            thunder_min *= tomeBonus;
            thunder_max *= tomeBonus;
        }

        if (water_min != 0 || water_max != 0) {
            water_min += (calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_MELEE_DAMAGE)], Damage_Type.WATER, false, true) + calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_DAMAGE)], Damage_Type.WATER, false, false) + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_WATER_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_WATER_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;
            water_max += (calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_MELEE_DAMAGE)], Damage_Type.WATER, true, true) + calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_DAMAGE)], Damage_Type.WATER, true, false) + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_WATER_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_WATER_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;

            water_min *= tomeBonus;
            water_max *= tomeBonus;
        }

        if (fire_min != 0 || fire_max != 0) {
            fire_min += (calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_MELEE_DAMAGE)], Damage_Type.FIRE, false, true) + calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_DAMAGE)], Damage_Type.FIRE, false, false) + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_FIRE_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_FIRE_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;
            fire_max += (calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_MELEE_DAMAGE)], Damage_Type.FIRE, true, true) + calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_DAMAGE)], Damage_Type.FIRE, true, false) + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_FIRE_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_FIRE_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;

            fire_min *= tomeBonus;
            fire_max *= tomeBonus;
        }

        if (air_min != 0 || air_max != 0) {
            air_min += (calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_MELEE_DAMAGE)], Damage_Type.AIR, false, true) + calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_DAMAGE)], Damage_Type.AIR, false, false) + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_AIR_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_AIR_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;
            air_max += (calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_MELEE_DAMAGE)], Damage_Type.AIR, true, true) + calc_raw.calc(id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_ELEMENTAL_DAMAGE)], Damage_Type.AIR, true, false) + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_AIR_MELEE_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_AIR_DAMAGE)]) * (percent[0] + percent[1] + percent[2] + percent[3] + percent[4] + percent[5]) * boost;

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

        earth_min += calc(total_min * percent[Damage_Type.EARTH.id], boost, sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost(), id_Numbers[IdDisplay.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.EARTH_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.EARTH_DAMAGE_PERCENT)]) * atkSpd;
        thunder_min += calc(total_min * percent[Damage_Type.THUNDER.id], boost, sp.getSkillPoint(SkillPoint.SkillPointType.DEXTERITY).getSPBoost(), id_Numbers[IdDisplay.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.THUNDER_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.THUNDER_DAMAGE_PERCENT)]) * atkSpd;
        water_min += calc(total_min * percent[Damage_Type.WATER.id], boost, sp.getSkillPoint(SkillPoint.SkillPointType.INTELLIGENCE).getIntDamageBonus(), id_Numbers[IdDisplay.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.WATER_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.WATER_DAMAGE_PERCENT)]) * atkSpd;
        fire_min += calc(total_min * percent[Damage_Type.FIRE.id], boost, sp.getSkillPoint(SkillPoint.SkillPointType.DEFENSE).getSPBoost(), id_Numbers[IdDisplay.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.FIRE_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.FIRE_DAMAGE_PERCENT)]) * atkSpd;
        air_min += calc(total_min * percent[Damage_Type.AIR.id], boost, sp.getSkillPoint(SkillPoint.SkillPointType.AGILITY).getSPBoost(), id_Numbers[IdDisplay.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.AIR_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.AIR_DAMAGE_PERCENT)]) * atkSpd;

        earth_max += calc(total_max * percent[Damage_Type.EARTH.id], boost, sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost(), id_Numbers[IdDisplay.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.EARTH_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.EARTH_DAMAGE_PERCENT)]) * atkSpd;
        thunder_max += calc(total_max * percent[Damage_Type.THUNDER.id], boost, sp.getSkillPoint(SkillPoint.SkillPointType.DEXTERITY).getSPBoost(), id_Numbers[IdDisplay.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.THUNDER_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.THUNDER_DAMAGE_PERCENT)]) * atkSpd;
        water_max += calc(total_max * percent[Damage_Type.WATER.id], boost, sp.getSkillPoint(SkillPoint.SkillPointType.INTELLIGENCE).getIntDamageBonus(), id_Numbers[IdDisplay.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.WATER_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.WATER_DAMAGE_PERCENT)]) * atkSpd;
        fire_max += calc(total_max * percent[Damage_Type.FIRE.id], boost, sp.getSkillPoint(SkillPoint.SkillPointType.DEFENSE).getSPBoost(), id_Numbers[IdDisplay.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.FIRE_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.FIRE_DAMAGE_PERCENT)]) * atkSpd;
        air_max += calc(total_max * percent[Damage_Type.AIR.id], boost, sp.getSkillPoint(SkillPoint.SkillPointType.AGILITY).getSPBoost(), id_Numbers[IdDisplay.ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.AIR_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.ELEMENTAL_DAMAGE_PERCENT)], id_Numbers[IdDisplay.ID_INT.get(Identifications.AIR_DAMAGE_PERCENT)]) * atkSpd;


        //Raw Damage Calc and Calc Tome Bonus
        if (neutral_min != 0 || neutral_max != 0) {
            neutral_min += calcRawDamage(calcRaw, false, Damage_Type.NEUTRAL, percent, boost, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_NEUTRAL_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_NEUTRAL_SPELL_DAMAGE)]);
            neutral_max += calcRawDamage(calcRaw, true, Damage_Type.NEUTRAL, percent, boost, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_NEUTRAL_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_NEUTRAL_SPELL_DAMAGE)]);

            neutral_min *= tomeBonus;
            neutral_max *= tomeBonus;
        }

        if (earth_min != 0 || earth_max != 0) {
            earth_min += calcRawDamage(calcRaw, false, Damage_Type.EARTH, percent, boost, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_EARTH_SPELL_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_EARTH_DAMAGE)]);
            earth_max += calcRawDamage(calcRaw, true, Damage_Type.EARTH, percent, boost, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_EARTH_SPELL_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_EARTH_DAMAGE)]);

            earth_min *= tomeBonus;
            earth_max *= tomeBonus;
        }
        if (thunder_min != 0 || thunder_max != 0) {
            thunder_min += calcRawDamage(calcRaw, false, Damage_Type.THUNDER, percent, boost, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_THUNDER_SPELL_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_THUNDER_DAMAGE)]);
            thunder_max += calcRawDamage(calcRaw, true, Damage_Type.THUNDER, percent, boost, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_THUNDER_SPELL_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_THUNDER_DAMAGE)]);

            thunder_min *= tomeBonus;
            thunder_max *= tomeBonus;
        }
        if (water_min != 0 || water_max != 0) {
            water_min += calcRawDamage(calcRaw, false, Damage_Type.WATER, percent, boost, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_WATER_SPELL_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_WATER_DAMAGE)]);
            water_max += calcRawDamage(calcRaw, true, Damage_Type.WATER, percent, boost, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_WATER_SPELL_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_WATER_DAMAGE)]);

            water_min *= tomeBonus;
            water_max *= tomeBonus;
        }
        if (fire_min != 0 || fire_max != 0) {
            fire_min += calcRawDamage(calcRaw, false, Damage_Type.FIRE, percent, boost, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_FIRE_SPELL_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_FIRE_DAMAGE)]);
            fire_max += calcRawDamage(calcRaw, true, Damage_Type.FIRE, percent, boost, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_FIRE_SPELL_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_FIRE_DAMAGE)]);

            fire_min *= tomeBonus;
            fire_max *= tomeBonus;
        }
        if (air_min != 0 || air_max != 0) {
            air_min += calcRawDamage(calcRaw, false, Damage_Type.AIR, percent, boost, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_AIR_SPELL_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_AIR_DAMAGE)]);
            air_max += calcRawDamage(calcRaw, true, Damage_Type.AIR, percent, boost, id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_AIR_SPELL_DAMAGE)] + id_Numbers[IdDisplay.ID_INT.get(Identifications.RAW_AIR_DAMAGE)]);

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

    public float calcRawDamage(CalcRaw calc_raw, boolean isMax, Damage_Type damage_type, float[] percent, float boost, int others_Raw) {
        float calc_total = others_Raw * (percent[Damage_Type.NEUTRAL.id] + percent[Damage_Type.EARTH.id] + percent[Damage_Type.THUNDER.id] + percent[Damage_Type.WATER.id] + percent[Damage_Type.FIRE.id] + percent[Damage_Type.AIR.id]);

        if (isMax) {
            return (calc_total + calc_raw.rawDamagesMax[damage_type.id]) * boost;
        } else {
            return (calc_total + calc_raw.rawDamagesMin[damage_type.id]) * boost;
        }
    }

    public void calcPowderDamages(List<JTextField> powders) {
        int[] min = new int[] { (int)neutral_min, (int)earth_min, (int)thunder_min, (int)water_min, (int)fire_min, (int)air_min };
        int[] max = new int[] { (int)neutral_max, (int)earth_max, (int)thunder_max, (int)water_max, (int)fire_max, (int)air_max };

        DataUtils.setPowderOnNonCraft(min, powders.get(4).getText(), JsonKeys.MIN);
        DataUtils.setPowderOnNonCraft(max, powders.get(4).getText(), JsonKeys.MAX);

        neutral_min = min[0];
        earth_min = min[1];
        thunder_min = min[2];
        water_min = min[3];
        fire_min = min[4];
        air_min = min[5];

        neutral_max = max[0];
        earth_max = max[1];
        thunder_max = max[2];
        water_max = max[3];
        fire_max = max[4];
        air_max = max[5];
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
                        have_mastery[Damage_Type.EARTH.id] = true;
                        if (earth_min != 0 || earth_max != 0) {
                            earth_min += 2;
                            earth_max += 4;
                        }
                        break;
                    }
                    case "Thunder Mastery": {
                        have_mastery[Damage_Type.THUNDER.id] = true;
                        if (thunder_min != 0 || thunder_max != 0) {
                            thunder_min += 1;
                            thunder_max += 8;
                        }
                        break;
                    }
                    case "Water Mastery": {
                        have_mastery[Damage_Type.WATER.id] = true;
                        if (water_min != 0 || water_max != 0) {
                            water_min += 2;
                            water_max += 4;
                        }
                        break;
                    }
                    case "Fire Mastery": {
                        have_mastery[Damage_Type.FIRE.id] = true;
                        if (fire_min != 0 || fire_max != 0) {
                            fire_min += 3;
                            fire_max += 5;
                        }
                        break;
                    }
                    case "Air Mastery": {
                        have_mastery[Damage_Type.AIR.id] = true;
                        if (air_min != 0 || air_max != 0) {
                            air_min += 3;
                            air_max += 4;
                        }
                        break;
                    }
                    case "Precise Strike":
                        crit_boost = true;
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

    public enum Damage_Type {
        NEUTRAL("Neutral", 0),
        EARTH("Earth", 1),
        THUNDER("Thunder", 2),
        WATER("Water", 3),
        FIRE("Fire", 4),
        AIR("Air", 5);

        final String name;
        final int id;
        Damage_Type(String name, int id) {
            this.name = name;
            this.id = id;
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
