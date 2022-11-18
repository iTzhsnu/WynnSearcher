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



        setTreeUI(getTcb());
    }
}
