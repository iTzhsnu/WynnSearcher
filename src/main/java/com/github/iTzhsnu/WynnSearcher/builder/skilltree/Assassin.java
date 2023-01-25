package com.github.iTzhsnu.WynnSearcher.builder.skilltree;

import javax.swing.*;

public class Assassin extends TreeBase {
    public Assassin(JPanel p) {
        super(p, TreeCheckBox.ArchetypeEnum.SHADESTEPPER, TreeCheckBox.ArchetypeEnum.TRICKSTER, TreeCheckBox.ArchetypeEnum.ACROBAT, "Assassin");
        getTcb().add(new TreeCheckBox("Spin Attack", new String[]{"Mana Cost: 40", "Total Damage: 150% (of your DPS)", "(Damage 120%)", "(Thunder 30%)", "Area of Effect: 4.5 Blocks (Circle-Shaped)"}, null, 1, SkillEnum.SPIN_ATTACK, posWidth(4, 6), posHeight(0, 6)).assassinIcon()); //0

        new TreeIcon(posWidth(4, 0), posHeight(1, 0), getPane()).up_down();

        new TreeIcon(posWidth(3, 0), posHeight(2, 0), getPane()).right_left();
        getTcb().add(new TreeCheckBox("Dagger Proficiency I", new String[]{"Walk Speed: +5%", "Melee Damage: +5%"}, new TreeCheckBox[]{getTcb().get(0)}, 1, SkillEnum.DAGGER_PROFICIENCY_1, posWidth(4, 2), posHeight(2, 2)).whiteIcon()); //1
        getTcb().add(new TreeCheckBox("Cheaper Spin Attack", new String[]{"Spin Attack Spell Cost: -10"}, new TreeCheckBox[]{getTcb().get(1)}, 1, SkillEnum.CHEAPER_1ST_SP_COST_10, posWidth(2, 2), posHeight(2, 2)).whiteIcon()); //2

        new TreeIcon(posWidth(4, 0), posHeight(3, 0), getPane()).up_down();

        getTcb().add(new TreeCheckBox("Double Spin", new String[]{"Spin Attack will activate twice", "with a larger area of effect.", " ", "Total Damage: -40% (of your DPS)", "(Damage -40%)", "Area of Effect: 2 Blocks (Circle-Shaped)"}, new TreeCheckBox[]{getTcb().get(1)}, 1, SkillEnum.DOUBLE_SPIN, posWidth(4, 4), posHeight(4, 4)).yellowIcon()); //3

        new TreeIcon(posWidth(4, 0), posHeight(5, 0), getPane()).up_down();

        new TreeIcon(posWidth(3, 0), posHeight(6, 0), getPane()).right_left();
        new TreeIcon(posWidth(5, 0), posHeight(6, 0), getPane()).right_left();
        getTcb().add(new TreeCheckBox("Dash", new String[]{"Mana Cost: 20"}, new TreeCheckBox[]{getTcb().get(3)}, 1, SkillEnum.DASH, posWidth(4, 6), posHeight(6, 6)).assassinIcon()); //4
        getTcb().add(new TreeCheckBox("Poisoned Blade", new String[]{"For every 2% or 2 Raw Melee Damage you have", "from items, gain +5/3s Poison. (Max 50/3s)", " ", "Range: +1 Block"}, new TreeCheckBox[]{getTcb().get(4)}, 1, TreeCheckBox.ArchetypeEnum.SHADESTEPPER, 0, SkillEnum.POISONED_BLADE, posWidth(2, 2), posHeight(6, 2)).whiteIcon()); //5
        getTcb().add(new TreeCheckBox("Double Slice", new String[]{"Your Melee Attack will hit twice,", " but deal -40% damage per hit."}, new TreeCheckBox[]{getTcb().get(4)}, new TreeCheckBox[]{getTcb().get(5)}, 1, TreeCheckBox.ArchetypeEnum.ACROBAT, 0, SkillEnum.DOUBLE_SLICE, posWidth(6, 2), posHeight(6, 2)).whiteIcon()); //6
        getTcb().get(5).setCantUse(new TreeCheckBox[]{getTcb().get(6)}); //Poison Blade

        new TreeIcon(posWidth(2, 0), posHeight(7, 0), getPane()).up_down();
        new TreeIcon(posWidth(6, 0), posHeight(7, 0), getPane()).up_down();

        new TreeIcon(posWidth(0, 0), posHeight(8, 0), getPane()).right_down();
        new TreeIcon(posWidth(1, 0), posHeight(8, 0), getPane()).right_left();
        new TreeIcon(posWidth(3, 0), posHeight(8, 0), getPane()).right_left();
        new TreeIcon(posWidth(5, 0), posHeight(8, 0), getPane()).right_left();
        new TreeIcon(posWidth(7, 0), posHeight(8, 0), getPane()).right_left();
        new TreeIcon(posWidth(8, 0), posHeight(8, 0), getPane()).left_down();
        getTcb().add(new TreeCheckBox("Smoke Bomb", new String[]{"Throws a bomb that slowly emanates smoke", "damaging all enemies in it every 0.5s.", " ", "Mana Cost: 35", "Total Damage: 35% (of your DPS)", "(Damage 25%)", "(Earth 5%)", "(Air 5%)", "Range: 10 Blocks", "Duration: 5s", "Area of Effect: 3 Blocks (Circle-Shaped)"}, new TreeCheckBox[]{getTcb().get(5)}, 1, SkillEnum.SMOKE_BOMB, posWidth(2, 6), posHeight(8, 6)).assassinIcon()); //7
        getTcb().add(new TreeCheckBox("Multihit", new String[]{"Unleashes a rapid flurry of 8 hit to enemies", "facing you, deal heavy damage.", " ", "Mana Cost: 40", "Total Damage: 40% (of your DPS, per hit)", "(Damage 30%)", "(Water 10%)", "Range: 3 Blocks (Cone-Shaped)"}, new TreeCheckBox[]{getTcb().get(6)}, 1, SkillEnum.MULTIHIT, posWidth(6, 6), posHeight(8, 6)).assassinIcon()); //8
        getTcb().add(new TreeCheckBox("Cheaper Dash", new String[]{"Dash Spell Cost: -5"}, new TreeCheckBox[]{getTcb().get(7), getTcb().get(8)}, 1, SkillEnum.CHEAPER_2ND_SP_COST, posWidth(4, 2), posHeight(8, 2)).whiteIcon()); //9
        getTcb().get(7).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(6), getTcb().get(8), getTcb().get(9)})}); //Smoke Bomb
        getTcb().get(8).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(5), getTcb().get(7), getTcb().get(9)})}); //Multihit

        new TreeIcon(posWidth(0, 0), posHeight(9, 0), getPane()).up_down();
        new TreeIcon(posWidth(2, 0), posHeight(9, 0), getPane()).up_down();
        new TreeIcon(posWidth(4, 0), posHeight(9, 0), getPane()).up_down();
        new TreeIcon(posWidth(6, 0), posHeight(9, 0), getPane()).up_down();
        new TreeIcon(posWidth(8, 0), posHeight(9, 0), getPane()).up_down();

        new TreeIcon(posWidth(1, 0), posHeight(10, 0), getPane()).right_left_down();
        new TreeIcon(posWidth(4, 0), posHeight(10, 0), getPane()).up_right_down();
        new TreeIcon(posWidth(5, 0), posHeight(10, 0), getPane()).right_left();
        new TreeIcon(posWidth(7, 0), posHeight(10, 0), getPane()).right_left_down();
        getTcb().add(TreeTemplates.earthMastery(posWidth(0, 2), posHeight(10, 2), TreeCheckBox.ArchetypeEnum.SHADESTEPPER, new TreeCheckBox[]{getTcb().get(7)}, null)); //10
        getTcb().add(TreeTemplates.thunderMastery(posWidth(2, 2), posHeight(10, 2), TreeCheckBox.ArchetypeEnum.SHADESTEPPER, new TreeCheckBox[]{getTcb().get(7)}, null)); //11
        getTcb().add(TreeTemplates.waterMastery(posWidth(6, 2), posHeight(10, 2), TreeCheckBox.ArchetypeEnum.ACROBAT, new TreeCheckBox[]{getTcb().get(8), getTcb().get(9)}, null)); //12
        getTcb().add(TreeTemplates.airMastery(posWidth(8, 2), posHeight(10, 2), TreeCheckBox.ArchetypeEnum.ACROBAT, new TreeCheckBox[]{getTcb().get(8)}, new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(9), getTcb().get(12)})})); //13

        new TreeIcon(posWidth(1, 0), posHeight(11, 0), getPane()).up_down();
        new TreeIcon(posWidth(7, 0), posHeight(11, 0), getPane()).up_down();
        getTcb().add(TreeTemplates.fireMastery(posWidth(4, 2), posHeight(11, 2), TreeCheckBox.ArchetypeEnum.TRICKSTER, new TreeCheckBox[]{getTcb().get(9)}, new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(8), getTcb().get(12)})})); //14

        new TreeIcon(posWidth(2, 0), posHeight(12, 0), getPane()).left_down();
        new TreeIcon(posWidth(4, 0), posHeight(12, 0), getPane()).up_down();
        new TreeIcon(posWidth(6, 0), posHeight(12, 0), getPane()).right_down();
        getTcb().add(new TreeCheckBox("Backstab", new String[]{"Makes Multihit a single devastating hit.", "If you strike an enemy from behind, it", "deal double the damage.", " ", "Mana Cost: -5", "Total Damage: 250% (of your DPS)", "(Damage 200%)", "(Earth 50%)"}, new TreeCheckBox[]{getTcb().get(10), getTcb().get(11)}, new TreeCheckBox[]{getTcb().get(8)}, TreeCheckBox.ArchetypeEnum.SHADESTEPPER, 0, 2, SkillEnum.BACKSTAB, posWidth(1, 4), posHeight(12, 4)).yellowIcon()); //15
        getTcb().add(new TreeCheckBox("Fatality", new String[]{"Multihit will deal an", "additional final slash.", " ", "Total Damage: +150% (of your DPS)", "(Damage +100%)", "(Air +50%)"}, new TreeCheckBox[]{getTcb().get(8)}, new TreeCheckBox[]{getTcb().get(15)}, new TreeCheckBox[]{getTcb().get(12), getTcb().get(13)}, null, null, 0, 2, SkillEnum.FATALITY, posWidth(7, 4), posHeight(12, 4)).yellowIcon()); //16

        new TreeIcon(posWidth(3, 0), posHeight(13, 0), getPane()).right_left();
        new TreeIcon(posWidth(7, 0), posHeight(13, 0), getPane()).up_down();
        getTcb().add(new TreeCheckBox("Vanish", new String[]{"Dash will hide you into the shadows. (5s Cooldown)", "You cannot heal or gain mana while in this state.", "(attack or get hit to cancel)", " ", "Effect: Invisibility to Self", "Duration: 5s"}, new TreeCheckBox[]{getTcb().get(15)}, new TreeCheckBox[]{getTcb().get(4)}, 2, SkillEnum.VANISH, posWidth(2, 6), posHeight(13, 6)).purpleIcon()); //17
        getTcb().add(new TreeCheckBox("Sticky Bomb", new String[]{"Smoke Bomb will stick to enemies", "and deal additional damage.", " ", "Total Damage: +10% (of your DPS)", "(Fire +10%)"}, new TreeCheckBox[]{getTcb().get(14)}, new TreeCheckBox[]{getTcb().get(7)}, new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(15), getTcb().get(17)})}, TreeCheckBox.ArchetypeEnum.TRICKSTER, 0, 2, SkillEnum.STICKY_BOMB, posWidth(4, 4), posHeight(13, 4)).yellowIcon()); //18
        getTcb().add(new TreeCheckBox("Righting Reflex", new String[]{"When you hold shift while airborne, slowly", "glide and become immune to fall damage. (Max 5s)"}, new TreeCheckBox[]{getTcb().get(16)}, 2, SkillEnum.RIGHTING_REFLEX, posWidth(6, 4), posHeight(13, 4)).yellowIcon()); //19
        getTcb().get(17).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(14), getTcb().get(18)})}); //Vanish

        new TreeIcon(posWidth(2, 0), posHeight(14, 0), getPane()).up_down();
        new TreeIcon(posWidth(4, 0), posHeight(14, 0), getPane()).up_down();
        new TreeIcon(posWidth(7, 0), posHeight(14, 0), getPane()).up_down();

        new TreeIcon(posWidth(1, 0), posHeight(15, 0), getPane()).right_down();
        new TreeIcon(posWidth(5, 0), posHeight(15, 0), getPane()).left_down();
        new TreeIcon(posWidth(8, 0), posHeight(15, 0), getPane()).left_down();
        getTcb().add(new TreeCheckBox("Surprise Strike", new String[]{"Your first hit after casting Vanish", "will deal +80% more damage."}, new TreeCheckBox[]{getTcb().get(17)}, 2, TreeCheckBox.ArchetypeEnum.SHADESTEPPER, 3, SkillEnum.SURPRISE_STRIKE, posWidth(2, 10), posHeight(15, 10)).redIcon()); //20
        //getTcb().add(new TreeCheckBox().redIcon()); //21
        //getTcb().add(new TreeCheckBox().redIcon()); //22

        getTcb().get(15).setCantUse(new TreeCheckBox[]{getTcb().get(16)}); //Backstab

        setTreeUI();
    }
}
