package com.github.iTzhsnu.WynnSearcher.builder.skilltree;

import javax.swing.*;

public class Warrior extends TreeBase {
    public Warrior(JPanel p) {
        super(p, TreeCheckBox.ArchetypeEnum.FALLEN, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, TreeCheckBox.ArchetypeEnum.PALADIN);
        getTcb().add(new TreeCheckBox("Bash", new String[] {"Mana Cost: 40", "Total Damage: 150%", "(Neutral 130%)", "(Earth 20%)", "Area of Effect: 4 Blocks (Circle-Shaped)", "Range: 3 Blocks"}, null, 1, SkillEnum.BASH, 120, 10).warriorIcon());
        new TreeIcon(130, 56, getPane()).up_down();
        getTcb().add(new TreeCheckBox("Spear Proficiency I", new String[] {"Melee Damage: +5%", "Melee Range: +1 Blocks"}, new TreeCheckBox[] {getTcb().get(0)}, 1, SkillEnum.PROFICIENCY_1, 124, 90).whiteIcon());


        setTreeUI(getTcb());
    }
}
