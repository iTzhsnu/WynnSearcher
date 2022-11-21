package com.github.iTzhsnu.WynnSearcher.builder.skilltree;

import javax.swing.*;

public class Warrior extends TreeBase {
    public Warrior(JPanel p) {
        super(p, TreeCheckBox.ArchetypeEnum.FALLEN, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, TreeCheckBox.ArchetypeEnum.PALADIN);
        getTcb().add(new TreeCheckBox("Bash", new String[] {"Mana Cost: 40", "Total Damage: 150% (of your DPS)", "(Neutral 130%)", "(Earth 20%)", "Area of Effect: 4 Blocks (Circle-Shaped)", "Range: 3 Blocks"}, null, 1, SkillEnum.BASH, 160, 10).warriorIcon()); //0

        new TreeIcon(166, 52, getPane()).up_down();
        getTcb().add(new TreeCheckBox("Spear Proficiency I", new String[] {"Melee Damage: +5%", "Melee Range: +1 Blocks"}, new TreeCheckBox[] {getTcb().get(0)}, 1, SkillEnum.PROFICIENCY_1, 164, 86).whiteIcon()); //1
        new TreeIcon(130, 88, getPane()).right_left();
        getTcb().add(new TreeCheckBox("Cheaper Bash", new String[] {"Bash Spell Cost: -10"}, new TreeCheckBox[]{getTcb().get(1)}, 1, SkillEnum.REDUCE_10_1ST_SP_COST, 92, 86).whiteIcon()); //2

        new TreeIcon(166, 124, getPane()).up_down();
        getTcb().add(new TreeCheckBox("Double Bash", new String[] {"Bash will hit second", "time at a father range", " ", "Total Damage: -50% (of your DPS, per Bash)", "(Neutral -50%)", "Range: +3 Blocks"}, new TreeCheckBox[]{getTcb().get(1)}, 1, SkillEnum.DOUBLE_BASH, 162, 156).yellowIcon()); //3

        new TreeIcon(166, 196, getPane()).up_down();
        getTcb().add(new TreeCheckBox("Charge", new String[]{"Mana Cost: 25"}, new TreeCheckBox[]{getTcb().get(3)} , 1, SkillEnum.CHARGE, 160, 220).warriorIcon()); //4

        new TreeIcon(130, 226, getPane()).right_left();
        new TreeIcon(202, 226, getPane()).right_left();
        getTcb().add(new TreeCheckBox("Vehement", new String[] {"For every 1% or 1 Raw", "Melee Damage you have from items", "gain +2% Walk Speed (Max 20%)", " ", "Raw Damage: +5"}, new TreeCheckBox[]{getTcb().get(4)}, 1, TreeCheckBox.ArchetypeEnum.FALLEN, 0, SkillEnum.VEHEMENT, 92, 224).whiteIcon()); //5
        getTcb().add(new TreeCheckBox("Tougher Skin", new String[] {"For every 1% or 1 Raw", "Health Regen you have from items", "gain +10 Health (Max 100)", " ", "Base Defense: +5%"}, new TreeCheckBox[]{getTcb().get(4)}, new TreeCheckBox[]{getTcb().get(5)}, 1, TreeCheckBox.ArchetypeEnum.PALADIN, 0, SkillEnum.TOUGHER_SKIN, 236, 224).whiteIcon()); //6
        getTcb().get(5).setCantUse(new TreeCheckBox[]{getTcb().get(6)});

        new TreeIcon(94, 262, getPane()).up_down();
        new TreeIcon(238, 262, getPane()).up_down();
        new TreeIcon(130, 298, getPane()).right_left();
        new TreeIcon(202, 298, getPane()).right_left();
        getTcb().add(new TreeCheckBox("Uppercut", new String[] {"Mana Cost: 40", "Total Damage: 280% (of your DPS)", "(Neutral 200%)", "(Earth 40%)", "(Thunder 40%)", "Area of Effect: 1-3 (Cone-Shaped)", "Range: 5 Blocks"}, new TreeCheckBox[]{getTcb().get(5)}, 1, SkillEnum.UPPERCUT, 88, 292).warriorIcon()); //7
        getTcb().add(new TreeCheckBox("War Scream", new String[] {"Mana Cost: 30", "Total Damage: 100% (of your DPS)", "(Neutral 50%)", "(Fire 50%)", "Effect: +20% Resistance to Allies", "Duration: 30s", "Area of Effects: 12 Blocks (Circle-Shaped)"}, new TreeCheckBox[]{getTcb().get(6)}, 1, SkillEnum.WAR_SCREAM, 232, 292).warriorIcon()); //8
        getTcb().add(new TreeCheckBox("Cheaper Charge", new String[] {"Charge Spell Cost: -5"}, new TreeCheckBox[]{getTcb().get(7), getTcb().get(8)}, 1, SkillEnum.REDUCE_5_2ND_SP_COST, 164, 296).whiteIcon()); //9
        getTcb().get(7).setHiddenReq(new TreeCheckBox[]{getTcb().get(6), getTcb().get(8), getTcb().get(9)});
        getTcb().get(8).setHiddenReq(new TreeCheckBox[]{getTcb().get(5), getTcb().get(7), getTcb().get(9)});

        new TreeIcon(58, 298, getPane()).right_left_down();
        new TreeIcon(22, 298, getPane()).right_down();
        new TreeIcon(274, 298, getPane()).right_left();
        new TreeIcon(310, 298, getPane()).left_down();

        new TreeIcon(22, 334, getPane()).up_down();
        new TreeIcon(94, 334, getPane()).up_down();
        new TreeIcon(166, 334, getPane()).up_down();
        new TreeIcon(238, 334, getPane()).up_down();
        new TreeIcon(310, 334, getPane()).up_down();
        getTcb().add(new TreeCheckBox("Heavy Impact", new String[]{"After using Charge, violently crash", "down unto the ground and damage.", " ", "Total Damage: 100% (of your DPS)", "(Neutral 100%)", "Area of Effect: 4 Blocks"}, new TreeCheckBox[]{getTcb().get(7)}, 1, SkillEnum.HEAVY_IMPACT, 54, 330).yellowIcon()); //10

        new TreeIcon(166, 370, getPane()).all();
        new TreeIcon(130, 370, getPane()).right_left();
        new TreeIcon(202, 370, getPane()).right_left();
        getTcb().add(TreeTemplates.earthMastery(20, 368, TreeCheckBox.ArchetypeEnum.FALLEN, new TreeCheckBox[]{getTcb().get(7)}, null)); //11
        getTcb().add(TreeTemplates.thunderMastery(92, 368, TreeCheckBox.ArchetypeEnum.FALLEN, new TreeCheckBox[]{getTcb().get(7), getTcb().get(9)},null)); //12
        getTcb().add(TreeTemplates.airMastery(236, 368, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, new TreeCheckBox[]{getTcb().get(8), getTcb().get(9)}, new TreeCheckBox[]{getTcb().get(7), getTcb().get(12)})); //13
        getTcb().add(TreeTemplates.fireMastery(308, 368, TreeCheckBox.ArchetypeEnum.PALADIN, new TreeCheckBox[]{getTcb().get(8)}, null)); //14
        getTcb().get(12).setHiddenReq(new TreeCheckBox[]{getTcb().get(8), getTcb().get(13)});

        new TreeIcon(22, 406, getPane()).up_down();
        new TreeIcon(94, 406, getPane()).up_down();
        new TreeIcon(238, 406, getPane()).up_down();
        new TreeIcon(310, 406, getPane()).up_down();
        getTcb().add(TreeTemplates.waterMastery(164, 404, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, new TreeCheckBox[]{getTcb().get(9), getTcb().get(12), getTcb().get(13)}, null)); //15

        new TreeIcon(166, 442, getPane()).up_down();
        new TreeIcon(58, 442, getPane()).right_left_down();
        new TreeIcon(274, 442, getPane()).right_left_down();
        getTcb().add(new TreeCheckBox("Quadruple Bash", new String[]{"Bash will cast 4 times consecutively", "at double speed.", " ", "Total Damage: -20% (of your DPS, per Bash)", "(Neutral -20%)", "Range: +6 Blocks"}, new TreeCheckBox[]{getTcb().get(11)}, new TreeCheckBox[]{getTcb().get(0)}, TreeCheckBox.ArchetypeEnum.FALLEN, 0, 2, SkillEnum.QUADRUPLE_BASH, 18, 438).yellowIcon()); //16
        getTcb().add(new TreeCheckBox("Fireworks", new String[]{"Mobs hit by Uppercut will explode", "mid-air and receive additional damage.", " ", "Total Damage: 100% (of your DPS)", "(Neutral 80%)", "(Thunder 20%)"}, new TreeCheckBox[]{getTcb().get(12)}, new TreeCheckBox[]{getTcb().get(11), getTcb().get(16)}, 2, SkillEnum.FIREWORKS, 90, 438).yellowIcon()); //17
        getTcb().add(new TreeCheckBox("Flyby Jab", new String[]{"Damage enemies in your", "way when using Charge.", " ", "Total Damage: 60% (of your DPS)", "(Neutral 20%)", "(Air 40%)", "Area of Effect: 2 Blocks"}, new TreeCheckBox[]{getTcb().get(13)}, 2, SkillEnum.FLYBY_JAB, 234, 438).yellowIcon()); //18
        getTcb().add(new TreeCheckBox("Flaming Uppercut", new String[]{"Uppercut will light mobs on fire,", "dealing damage every seconds.", " ", "Total Damage: 50% (of your DPS)", "(Fire 50%)", "Duration: 3s"}, new TreeCheckBox[]{getTcb().get(14)}, new TreeCheckBox[]{getTcb().get(7)}, new TreeCheckBox[]{getTcb().get(13), getTcb().get(18)}, TreeCheckBox.ArchetypeEnum.PALADIN, 0, 2, SkillEnum.FLAMING_UPPERCUT, 306, 438).yellowIcon()); //19
        getTcb().get(16).setHiddenReq(new TreeCheckBox[]{getTcb().get(12), getTcb().get(17)});
        getTcb().get(18).setHiddenReq(new TreeCheckBox[]{getTcb().get(14), getTcb().get(19)});

        new TreeIcon(58, 478, getPane()).up_down();
        getTcb().add(new TreeCheckBox("Half-Moon Swipe", new String[]{"Uppercut will deal a footsweep", "attack at a longer and wider angle.", " ", "Total Damage: -40% (of your DPS)", "(Neutral -70%)", "(Water 30%)", "Range: 3 Blocks"}, new TreeCheckBox[]{getTcb().get(15)}, new TreeCheckBox[]{getTcb().get(7)}, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, 1, 2, SkillEnum.HALF_MOON_SWIPE, 162, 474).yellowIcon()); //20
        getTcb().add(new TreeCheckBox("Iron Lungs", new String[]{"War Scream will deal more damage.", " ", "Total Damage: 60% (of your DPS)", "(Neutral 30%)", "(Air 30%)"}, new TreeCheckBox[]{getTcb().get(18), getTcb().get(19)}, new TreeCheckBox[]{getTcb().get(8)}, TreeCheckBox.ArchetypeEnum.PALADIN, 0, 1, SkillEnum.IRON_LUNGS, 272, 476).whiteIcon()); //21

        new TreeIcon(58, 514, getPane()).up_down();
        new TreeIcon(166, 514, getPane()).up_down();
        new TreeIcon(274, 514, getPane()).up_down();

        new TreeIcon(58, 550, getPane()).up_down();
        new TreeIcon(130, 550, getPane()).right_left_down();
        getTcb().add(new TreeCheckBox("Counter", new String[]{"when dodding nearby enemy attack, get", "30% change to instaly attack back.", " ", "Total Damage: 100% (of your DPS)", "(Neutral 60%)", "(Thunder 20%)", "(Air 20%)"}, new TreeCheckBox[]{getTcb().get(20)}, 2, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, 0, SkillEnum.COUNTER, 162, 546).yellowIcon()); //22
        getTcb().add(new TreeCheckBox("Generalist", new String[]{"After casting 3 different spells", "in a row, your next spell cost 5 Mana."}, new TreeCheckBox[]{getTcb().get(22)}, 2, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, 3, SkillEnum.GENERALIST, 84, 540).redIcon()); //23
        getTcb().add(new TreeCheckBox("Mantle of the Bovemists", new String[]{"When casting War Scream, create a holy", "shield around you that reduces all incoming", "damage by -70% for 3 hits. (25s Cooldown)"}, new TreeCheckBox[]{getTcb().get(21)}, new TreeCheckBox[]{getTcb().get(8)}, TreeCheckBox.ArchetypeEnum.PALADIN, 3, 2, SkillEnum.MANTLE_OF_THE_BOVEMISTS, 264, 540).redIcon()); //24

        new TreeIcon(22, 586, getPane()).right_down();
        new TreeIcon(130, 586, getPane()).up_down();
        new TreeIcon(274, 586, getPane()).up_down();
        getTcb().add(new TreeCheckBox("Bak'al's Grasp", new String[]{"Casting War Scream will make you", "Corrupted (15s Cooldown)", " ", "While Corrupted you lose the ability", "to heal, and 2% health you lose will", "grant you +4 Raw Damage (Max 120)"}, new TreeCheckBox[]{getTcb().get(16), getTcb().get(17)}, new TreeCheckBox[]{getTcb().get(8)}, TreeCheckBox.ArchetypeEnum.FALLEN, 2, 2, SkillEnum.BAKALS_GRASP, 48, 576).redIcon()); //25



        setTreeUI(getTcb());
    }
}
