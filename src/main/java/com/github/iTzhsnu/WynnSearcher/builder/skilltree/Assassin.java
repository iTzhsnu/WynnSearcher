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
        new TreeIcon(posWidth(3, 0), posHeight(15, 0), getPane()).right_down();
        new TreeIcon(posWidth(5, 0), posHeight(15, 0), getPane()).left_down();
        new TreeIcon(posWidth(8, 0), posHeight(15, 0), getPane()).left_down();
        getTcb().add(new TreeCheckBox("Surprise Strike", new String[]{"Your first hit after casting Vanish", "will deal +80% more damage."}, new TreeCheckBox[]{getTcb().get(17)}, new TreeCheckBox[]{getTcb().get(17)}, TreeCheckBox.ArchetypeEnum.SHADESTEPPER, 3, 2, SkillEnum.SURPRISE_STRIKE, posWidth(2, 10), posHeight(15, 10)).redIcon()); //20
        getTcb().add(new TreeCheckBox("Mirror Image", new String[]{"Summons 3 Clones after reappearing from Vanish", "that will follow and protect you. (15s Cooldown)", " ", "When hit, you take 60% less damage.", "Clones can take 2 hits before dying."}, new TreeCheckBox[]{getTcb().get(18)}, new TreeCheckBox[]{getTcb().get(17)}, TreeCheckBox.ArchetypeEnum.TRICKSTER, 2, 2, SkillEnum.MIRROR_IMAGE, posWidth(4, 10), posHeight(15, 10)).redIcon()); //21
        getTcb().add(new TreeCheckBox("Lacerate", new String[]{"Spin Attack will lunge you forward,", "deal 3 strikes and then lunge you upward.", " ", "Total Damage: 90% (of your DPS, per Strike)", "(Damage 60%)", "(Water 10%)", "(Air 20%)"}, new TreeCheckBox[]{getTcb().get(16)}, 2, TreeCheckBox.ArchetypeEnum.ACROBAT, 2, SkillEnum.LACERATE, posWidth(7, 10), posHeight(15, 10)).redIcon()); //22

        new TreeIcon(posWidth(0, 0), posHeight(16, 0), getPane()).right_down();
        new TreeIcon(posWidth(2, 0), posHeight(16, 0), getPane()).left_down();
        new TreeIcon(posWidth(7, 0), posHeight(16, 0), getPane()).right_down();
        getTcb().add(new TreeCheckBox("Silent Killer", new String[]{"Resets Vanish's Cooldown", "after killing an enemy."}, new TreeCheckBox[]{getTcb().get(20)}, new TreeCheckBox[]{getTcb().get(17)}, 2, SkillEnum.SILENT_KILLER, posWidth(1, 6), posHeight(16, 6)).purpleIcon()); //23
        getTcb().add(new TreeCheckBox("Last Laugh", new String[]{"When losing Clone, it will", "cast Spin Attack before dying."}, new TreeCheckBox[]{getTcb().get(21)}, new TreeCheckBox[]{getTcb().get(21)}, TreeCheckBox.ArchetypeEnum.TRICKSTER, 0, 1, SkillEnum.LAST_LAUGH, posWidth(3, 2), posHeight(16, 2)).whiteIcon()); //24
        getTcb().add(new TreeCheckBox("Shenanigans", new String[]{"For every 2% Stealing you have", "from items, gain 1/3s Mana Steal. (Max 8/3s)"}, new TreeCheckBox[]{getTcb().get(21)}, 2, TreeCheckBox.ArchetypeEnum.TRICKSTER, 0, SkillEnum.SHENANIGANS, posWidth(5, 2), posHeight(16, 2)).whiteIcon()); //25
        getTcb().add(new TreeCheckBox("Wall of Smoke", new String[]{"Smoke Bomb will throw +2 bombs."}, new TreeCheckBox[]{getTcb().get(22)}, new TreeCheckBox[]{getTcb().get(7)}, 2, SkillEnum.WALL_OF_SMOKE, posWidth(8, 4), posHeight(16, 4)).yellowIcon()); //26

        new TreeIcon(posWidth(0, 0), posHeight(17, 0), getPane()).up_down();
        new TreeIcon(posWidth(2, 0), posHeight(17, 0), getPane()).up_down();
        new TreeIcon(posWidth(5, 0), posHeight(17, 0), getPane()).up_down();
        new TreeIcon(posWidth(8, 0), posHeight(17, 0), getPane()).up_down();
        getTcb().add(new TreeCheckBox("Psithurism", new String[]{"Walk Speed +20%", "Jump Height +1"}, new TreeCheckBox[]{getTcb().get(26)}, 1, SkillEnum.PSITHURISM, posWidth(7, 2), posHeight(17, 2)).whiteIcon()); //27

        new TreeIcon(posWidth(1, 0), posHeight(18, 0), getPane()).right_left();
        new TreeIcon(posWidth(3, 0), posHeight(18, 0), getPane()).right_left_down();
        new TreeIcon(posWidth(4, 0), posHeight(18, 0), getPane()).right_left_down();
        new TreeIcon(posWidth(6, 0), posHeight(18, 0), getPane()).right_left_down();
        new TreeIcon(posWidth(7, 0), posHeight(18, 0), getPane()).right_left_down();
        getTcb().add(new TreeCheckBox("Better Smoke Bomb", new String[]{"Increase Smoke Bomb's range", "and area of effect.", " ", "Range: +10 Blocks", "Area of Effect: +1 Blocks (Circle-Shaped)"}, new TreeCheckBox[]{getTcb().get(23)}, new TreeCheckBox[]{getTcb().get(7)}, 1, SkillEnum.BETTER_SMOKE_BOMB, posWidth(0, 2), posHeight(18, 2)).whiteIcon()); //28
        getTcb().add(new TreeCheckBox("Shadow Travel", new String[]{"Increase your Walk Speed", "by +120% when in Vanish."}, new TreeCheckBox[]{getTcb().get(23)}, new TreeCheckBox[]{getTcb().get(17)}, TreeCheckBox.ArchetypeEnum.SHADESTEPPER, 0, 2, SkillEnum.SHADOW_TRAVEL, posWidth(2, 4), posHeight(18, 4)).yellowIcon()); //29
        getTcb().add(new TreeCheckBox("Cheaper Multihit", new String[]{"Multihit Spell Cost: -5"}, new TreeCheckBox[]{getTcb().get(25)}, new TreeCheckBox[]{getTcb().get(8)}, 1, SkillEnum.CHEAPER_3RD_SP_COST, posWidth(5, 2), posHeight(18, 2)).whiteIcon()); //30
        getTcb().add(new TreeCheckBox("Dagger Proficiency II", new String[]{"Damage Bonus: +5 (Raw)", "Range: +1 Block"}, new TreeCheckBox[]{getTcb().get(26)}, new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(23), getTcb().get(29), getTcb().get(30)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(25), getTcb().get(30)})}, 1, SkillEnum.DAGGER_PROFICIENCY_2, posWidth(8, 2), posHeight(18, 2)).whiteIcon()); //31
        getTcb().get(28).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(25), getTcb().get(29), getTcb().get(30)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(26), getTcb().get(29), getTcb().get(30), getTcb().get(31)})});
        getTcb().get(29).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(25), getTcb().get(30)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(26), getTcb().get(30), getTcb().get(31)})});
        getTcb().get(30).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(23), getTcb().get(29)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(26), getTcb().get(31)})});

        new TreeIcon(posWidth(0, 0), posHeight(19, 0), getPane()).up_down();
        new TreeIcon(posWidth(3, 0), posHeight(19, 0), getPane()).up_down();
        new TreeIcon(posWidth(7, 0), posHeight(19, 0), getPane()).up_down();
        getTcb().add(new TreeCheckBox("Bamboozle", new String[]{"When Clones are active, casting Multihit while", "holding shift will instead slay 1 Clone and", "teleport you forwards with a brutal fiery slash.", " ", "Total Damage: 400% (of your DPS)", "(Damage 360%)", "(Fire 40%)", "Range: 6 Blocks", "Area of Effect: 5 Blocks (Circle-Shaped)"}, new TreeCheckBox[]{getTcb().get(29), getTcb().get(30)}, new TreeCheckBox[]{getTcb().get(21)}, TreeCheckBox.ArchetypeEnum.TRICKSTER, 0, 2, SkillEnum.BAMBOOZLE, posWidth(4, 6), posHeight(19, 6)).purpleIcon()); //32
        getTcb().add(new TreeCheckBox("Distraction", new String[]{"When hitting enemies, reduce their", "damage by -0.1% per hit. (Max -15%)", "The debuff decays at -0.3% per second."}, new TreeCheckBox[]{getTcb().get(30), getTcb().get(31)}, 2, TreeCheckBox.ArchetypeEnum.TRICKSTER, 0, SkillEnum.DISTRACTION, posWidth(6, 4), posHeight(19, 4)).yellowIcon()); //33

        new TreeIcon(posWidth(0, 0), posHeight(20, 0), getPane()).up_down();
        new TreeIcon(posWidth(3, 0), posHeight(20, 0), getPane()).up_down();
        new TreeIcon(posWidth(7, 0), posHeight(20, 0), getPane()).up_down();

        new TreeIcon(posWidth(1, 0), posHeight(21, 0), getPane()).right_left_down();
        new TreeIcon(posWidth(2, 0), posHeight(21, 0), getPane()).right_left();
        new TreeIcon(posWidth(4, 0), posHeight(21, 0), getPane()).left_down();
        new TreeIcon(posWidth(6, 0), posHeight(21, 0), getPane()).right_down();
        new TreeIcon(posWidth(8, 0), posHeight(21, 0), getPane()).left_down();
        getTcb().add(new TreeCheckBox("Cheaper Smoke Bomb", new String[]{"Smoke Bomb Spell Cost: -5"}, new TreeCheckBox[]{getTcb().get(28)}, new TreeCheckBox[]{getTcb().get(7)}, 1, SkillEnum.CHEAPER_4TH_SP_COST, posWidth(0, 2), posHeight(21, 2)).whiteIcon()); //34
        getTcb().add(new TreeCheckBox("Blazing Powder", new String[]{"Spin Attack will blind enemies", "and deal additional damage.", " ", "Total Damage: +20% (of your DPS)", "(Fire +20%)", "Effect: Blindness to Enemies", "Duration: 1s"}, new TreeCheckBox[]{getTcb().get(29), getTcb().get(30)}, new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(28), getTcb().get(34)})}, 2, SkillEnum.BLAZING_POWDER, posWidth(3, 4), posHeight(21, 4)).yellowIcon()); //35
        getTcb().add(new TreeCheckBox("Weightless", new String[]{"You gain +0.7 Mana if you", "hit an enemy while airborne.", "(1.25+ blocks off the", "ground to be airborne.)"}, new TreeCheckBox[]{getTcb().get(30), getTcb().get(31)}, 2, TreeCheckBox.ArchetypeEnum.ACROBAT, 3, SkillEnum.WEIGHTLESS, posWidth(7, 6), posHeight(21, 6)).purpleIcon()); //36
        getTcb().get(34).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(29), getTcb().get(35)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(30), getTcb().get(35)})}); //Cheaper Smoke Bomb

        new TreeIcon(posWidth(0, 0), posHeight(22, 0), getPane()).up_down();
        new TreeIcon(posWidth(3, 0), posHeight(22, 0), getPane()).right_down();
        new TreeIcon(posWidth(5, 0), posHeight(22, 0), getPane()).right_left_down();
        getTcb().add(new TreeCheckBox("Black Hole", new String[]{"Smoke Bomb will pull nearby enemies.", "Range: 6 Blocks"}, new TreeCheckBox[]{getTcb().get(34), getTcb().get(35)}, 2, SkillEnum.BLACK_HOLE, posWidth(1, 4), posHeight(22, 4)).yellowIcon()); //37
        getTcb().add(new TreeCheckBox("Sandbagging", new String[]{"Reduces the cooldown on all off", "your abilities by -3s every", "time you get hit for less than 5%", "of your max health. (1s Cooldown)"}, new TreeCheckBox[]{getTcb().get(35)}, 2, TreeCheckBox.ArchetypeEnum.TRICKSTER, 0, SkillEnum.SANDBAGGING, posWidth(4, 4), posHeight(22, 4)).yellowIcon()); //38
        getTcb().add(new TreeCheckBox("Hop", new String[]{"Leap forward when you double jump. (2s Cooldown)"}, new TreeCheckBox[]{getTcb().get(36)}, null, new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(35), getTcb().get(38)})}, TreeCheckBox.ArchetypeEnum.ACROBAT, 0, 2, SkillEnum.HOP, posWidth(6, 4), posHeight(22, 4)).yellowIcon()); //39
        getTcb().add(new TreeCheckBox("Flow State", new String[]{"Landing 60 hits without stopping for", "over 2s will greatly increase your", "damage for a short time. (10s Cooldown)", " ", "Effect: +50% Air Damage Bonus to Self", "Duration: 5s"}, new TreeCheckBox[]{getTcb().get(36)}, 2, TreeCheckBox.ArchetypeEnum.ACROBAT, 5, SkillEnum.FLOW_STATE, posWidth(8, 10), posHeight(22, 10)).redIcon()); //40
        getTcb().get(38).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(36), getTcb().get(39)})}); //Sandbagging

        new TreeIcon(posWidth(1, 0), posHeight(23, 0), getPane()).left_down();
        new TreeIcon(posWidth(4, 0), posHeight(23, 0), getPane()).up_down();
        new TreeIcon(posWidth(8, 0), posHeight(23, 0), getPane()).up_down();
        getTcb().add(new TreeCheckBox("Violent Vortex", new String[]{"Dealing over 1.5x of your max health as", "damage in single hit will deal 30% of the", "damage to other nearby enemies. (2s Cooldown)", " ", "Area of Effect: 10 Blocks (Circle-Shaped)"}, new TreeCheckBox[]{getTcb().get(34)}, 2, TreeCheckBox.ArchetypeEnum.SHADESTEPPER, 0, SkillEnum.VIOLENT_VORTEX, posWidth(0, 4), posHeight(23, 4)).yellowIcon()); //41
        getTcb().add(new TreeCheckBox("Delirious Gas", new String[]{"While you're inside Smoke Bomb,", "increase your damage by +40%", "and Luring for 20s.", " ", "While Luring, enemies within a 16 block", "radius of you will become Lured, causing", "them to move 30% faster and target you."}, new TreeCheckBox[]{getTcb().get(38)}, new TreeCheckBox[]{getTcb().get(7)}, TreeCheckBox.ArchetypeEnum.TRICKSTER, 4, 2, SkillEnum.DELIRIOUS_GAS, posWidth(3, 6), posHeight(23, 6)).purpleIcon()); //42
        getTcb().add(new TreeCheckBox("Stronger Multihit", new String[]{"Increase Multihit's amount of hit by +3"}, new TreeCheckBox[]{getTcb().get(8)}, new TreeCheckBox[]{getTcb().get(15)}, new TreeCheckBox[]{getTcb().get(38), getTcb().get(39)}, null, null, 0, 1, SkillEnum.STRONGER_MULTIHIT, posWidth(5, 2), posHeight(23, 2)).whiteIcon()); //43
        getTcb().get(15).setCantUse(new TreeCheckBox[]{getTcb().get(16), getTcb().get(43)}); //Backstab

        new TreeIcon(posWidth(0, 0), posHeight(24, 0), getPane()).right_down();
        new TreeIcon(posWidth(2, 0), posHeight(24, 0), getPane()).left_down();
        new TreeIcon(posWidth(5, 0), posHeight(24, 0), getPane()).right_left();
        new TreeIcon(posWidth(7, 0), posHeight(24, 0), getPane()).right_left_down();
        getTcb().add(new TreeCheckBox("Marked", new String[]{"Smoke Bomb will add +1 Mark to", "enemies it hits. (Max 4, 0.4s Cooldown)", " ", "Marked will take enemies +6% damage", "from attacks for each mark they have."}, new TreeCheckBox[]{getTcb().get(41)}, 2, TreeCheckBox.ArchetypeEnum.SHADESTEPPER, 5, SkillEnum.MARKED, posWidth(1, 10), posHeight(24, 10)).redIcon()); //44
        getTcb().add(new TreeCheckBox("Echo", new String[]{"Your Clones will mimic your spells and", "abilities, and you deal -65% of your", "normal damage while they are active.", " ", "Bamboozle ignores Echo's damage reduction."}, new TreeCheckBox[]{getTcb().get(21)}, new TreeCheckBox[]{getTcb().get(22)}, new TreeCheckBox[]{getTcb().get(38)}, null, TreeCheckBox.ArchetypeEnum.TRICKSTER, 6, 2, SkillEnum.ECHO, posWidth(4, 10), posHeight(24, 10)).redIcon()); //45
        getTcb().add(new TreeCheckBox("Shurikens", new String[]{"After using Dash, your next", "melee attack will throw 3 shurikens.", " ", "Total Damage: 120% (of your DPS)", "(Damage 100%)", "(Fire 20%)", "Range: 50 Blocks"}, null, 2, TreeCheckBox.ArchetypeEnum.ACROBAT, 0, SkillEnum.SHURIKENS, posWidth(6, 6), posHeight(24, 6)).purpleIcon()); //46
        getTcb().add(new TreeCheckBox("Far Reach", new String[]{"Increases the range of Multihit.", " ", "Range: +2 Blocks"}, new TreeCheckBox[]{getTcb().get(40)}, new TreeCheckBox[]{getTcb().get(8)}, new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(38), getTcb().get(45), getTcb().get(46)})}, null, 0, 1, SkillEnum.FAR_REACH, posWidth(8, 2), posHeight(24, 2)).whiteIcon()); //47
        getTcb().get(22).setCantUse(new TreeCheckBox[]{getTcb().get(45)}); //Lacerate
        getTcb().get(45).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(40), getTcb().get(46), getTcb().get(47)})}); //Echo
        getTcb().get(46).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(38), getTcb().get(45)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(40), getTcb().get(47)})}); //Shurikens

        new TreeIcon(posWidth(0, 0), posHeight(25, 0), getPane()).up_down();
        new TreeIcon(posWidth(2, 0), posHeight(25, 0), getPane()).up_down();
        new TreeIcon(posWidth(4, 0), posHeight(25, 0), getPane()).up_down();
        new TreeIcon(posWidth(8, 0), posHeight(25, 0), getPane()).up_down();
        getTcb().add(new TreeCheckBox("Dancing Blade", new String[]{"Deal damage to enemies you Dash through.", " ", "Total Damage: 80% (of your DPS)", "(Damage 70%)", "(Air 10%)"}, new TreeCheckBox[]{getTcb().get(46), getTcb().get(47)}, new TreeCheckBox[]{getTcb().get(4)}, 1, SkillEnum.DANCING_BLADE, posWidth(7, 2), posHeight(25, 2)).whiteIcon()); //48

        new TreeIcon(posWidth(0, 0), posHeight(26, 0), getPane()).up_down();
        new TreeIcon(posWidth(2, 0), posHeight(26, 0), getPane()).up_down();
        new TreeIcon(posWidth(4, 0), posHeight(26, 0), getPane()).up_down();
        new TreeIcon(posWidth(8, 0), posHeight(26, 0), getPane()).up_down();

        new TreeIcon(posWidth(3, 0), posHeight(27, 0), getPane()).right_left();
        new TreeIcon(posWidth(7, 0), posHeight(27, 0), getPane()).right_left();
        getTcb().add(new TreeCheckBox("Ambush", new String[]{"Increases Suprise Strike's", "damage by +40%."}, new TreeCheckBox[]{getTcb().get(44)}, new TreeCheckBox[]{getTcb().get(20)}, 1, SkillEnum.AMBUSH, posWidth(0, 2), posHeight(27, 2)).whiteIcon()); //49
        getTcb().add(new TreeCheckBox("Death Magnet", new String[]{"When exiting Vanish, pull all", "nearby Marked mobs towards", "where you entered Vanish.", " ", "Range: 20 Blocks"}, new TreeCheckBox[]{getTcb().get(44)}, new TreeCheckBox[]{getTcb().get(44)}, TreeCheckBox.ArchetypeEnum.SHADESTEPPER, 0, 2, SkillEnum.DEATH_MAGNET, posWidth(2, 4), posHeight(27, 4)).yellowIcon()); //50
        getTcb().add(new TreeCheckBox("Cheaper Dash", new String[]{"Dash Spell Cost: -5"}, new TreeCheckBox[]{getTcb().get(45)}, new TreeCheckBox[]{getTcb().get(4)}, new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(44), getTcb().get(50)})}, null, 0, 1, SkillEnum.CHEAPER_2ND_SP_COST, posWidth(4, 2), posHeight(27, 2)).whiteIcon()); //51
        getTcb().add(new TreeCheckBox("Cheaper Spin Attack", new String[]{"Spin Attack Spell Cost: -5"}, new TreeCheckBox[]{getTcb().get(47)}, 1, SkillEnum.CHEAPER_1ST_SP_COST, posWidth(8, 2), posHeight(27, 2)).whiteIcon()); //52
        getTcb().add(new TreeCheckBox("Parry", new String[]{"After dodding damage with Agility, gain", "a brief damage buff, and make your next", "spell within 1.5s free. (3s Cooldown)", " ", "Effect: +30% Damage Bonus to Self", "Duration: 1.5s"}, new TreeCheckBox[]{getTcb().get(52)}, 2, TreeCheckBox.ArchetypeEnum.ACROBAT, 5, SkillEnum.PARRY, posWidth(6, 6), posHeight(27, 6)).purpleIcon()); //53
        getTcb().get(50).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(45), getTcb().get(51)})}); //Death Magnet

        new TreeIcon(posWidth(0, 0), posHeight(28, 0), getPane()).up_down();
        new TreeIcon(posWidth(2, 0), posHeight(28, 0), getPane()).up_down();
        new TreeIcon(posWidth(4, 0), posHeight(28, 0), getPane()).up_down();
        new TreeIcon(posWidth(6, 0), posHeight(28, 0), getPane()).up_down();
        new TreeIcon(posWidth(8, 0), posHeight(28, 0), getPane()).up_down();

        new TreeIcon(posWidth(1, 0), posHeight(29, 0), getPane()).right_left_down();
        new TreeIcon(posWidth(3, 0), posHeight(29, 0), getPane()).right_left_down();
        new TreeIcon(posWidth(5, 0), posHeight(29, 0), getPane()).right_left_down();
        new TreeIcon(posWidth(7, 0), posHeight(29, 0), getPane()).right_left_down();
        getTcb().add(new TreeCheckBox("Nightcloak Knife", new String[]{"If cast while in Vanish,", "Spin Attack will consume all", "Marks from nearby enemies to", "summon the Nightcloak Knife.", " ", "The Knife will copy your", "attacks on enemies near you,", "dealing 6% of your damage for every", "1 Mark consumed. (Max 10 Marks)", " ", "Duration: 15s"}, new TreeCheckBox[]{getTcb().get(49)}, new TreeCheckBox[]{getTcb().get(44)}, TreeCheckBox.ArchetypeEnum.SHADESTEPPER, 0, 2, SkillEnum.NIGHTCLOAK_KNIFE, posWidth(0, 6), posHeight(29, 6)).purpleIcon()); //54
        getTcb().add(new TreeCheckBox("Cheaper Multihit", new String[]{"Multihit Spell Cost: -5"}, new TreeCheckBox[]{getTcb().get(50)}, new TreeCheckBox[]{getTcb().get(8)}, 1, SkillEnum.CHEAPER_3RD_SP_COST, posWidth(2, 2), posHeight(29, 2)).whiteIcon()); //55
        getTcb().add(new TreeCheckBox("Hoodwink", new String[]{"When hitting enemies with Spin Attack, reduce", "the duration of your negative effects by", "30% per hit and transfer them onto enemies.", " ", "Luring can be transferred to most enemies.", "(Bosses and special enemies are immune)"}, new TreeCheckBox[]{getTcb().get(51)}, new TreeCheckBox[]{getTcb().get(0)}, TreeCheckBox.ArchetypeEnum.TRICKSTER, 1, 2, SkillEnum.HOODWINK, posWidth(4, 4), posHeight(29, 4)).yellowIcon()); //56
        getTcb().add(new TreeCheckBox("Choke Bomb", new String[]{"Smoke Bomb will slow down", "enemies while in the smoke.", " ", "Effect: 40% Slowness to enemies", "Duration: 1s"}, new TreeCheckBox[]{getTcb().get(53)}, 2, TreeCheckBox.ArchetypeEnum.TRICKSTER, 0, SkillEnum.CHOKE_BOMB, posWidth(6, 4), posHeight(29, 4)).yellowIcon()); //57
        getTcb().add(new TreeCheckBox("Wall Jump", new String[]{"Reduces Hop's cooldown by", "-1s. Using Hop into a wall", "will bounce you backwards.", "(Hold Shift to cancel)"}, new TreeCheckBox[]{getTcb().get(52)}, new TreeCheckBox[]{getTcb().get(39)}, new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(49), getTcb().get(54), getTcb().get(55), getTcb().get(56), getTcb().get(57)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(50), getTcb().get(55), getTcb().get(56), getTcb().get(57)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(51), getTcb().get(56), getTcb().get(57)})}, TreeCheckBox.ArchetypeEnum.ACROBAT, 5, 2, SkillEnum.WALL_JUMP, posWidth(8, 4), posHeight(29, 4)).yellowIcon()); //58
        getTcb().get(54).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(50), getTcb().get(55)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(51), getTcb().get(55), getTcb().get(56)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(53), getTcb().get(55), getTcb().get(56), getTcb().get(57)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(52), getTcb().get(55), getTcb().get(56), getTcb().get(57), getTcb().get(58)})}); //Nightcloak Knife
        getTcb().get(55).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(49), getTcb().get(54)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(51), getTcb().get(56)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(53), getTcb().get(56), getTcb().get(57)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(52), getTcb().get(56), getTcb().get(57), getTcb().get(58)})}); //Cheaper Multihit
        getTcb().get(56).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(49), getTcb().get(54), getTcb().get(55)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(50), getTcb().get(55)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(53), getTcb().get(57)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(52), getTcb().get(57), getTcb().get(58)})}); //Hoodwink
        getTcb().get(57).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(49), getTcb().get(54), getTcb().get(55), getTcb().get(56)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(50), getTcb().get(55), getTcb().get(56)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(51), getTcb().get(56)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(52), getTcb().get(58)})}); //Choke Bomb

        new TreeIcon(posWidth(0, 0), posHeight(30, 0), getPane()).right_down();
        new TreeIcon(posWidth(4, 0), posHeight(30, 0), getPane()).up_down();
        new TreeIcon(posWidth(6, 0), posHeight(30, 0), getPane()).right_left();
        getTcb().add(new TreeCheckBox("Fatal Spin", new String[]{"Increases Spin Attack and Lacerate's range", "and damage, and adds +1 Mark to enemies hit.", " ", "Total Damage: +20% (of your DPS)", "(Damage +20%)", "Area of Effect: +1 Block (Circle-Shaped)"}, new TreeCheckBox[]{getTcb().get(54), getTcb().get(55)}, new TreeCheckBox[]{getTcb().get(44)}, TreeCheckBox.ArchetypeEnum.SHADESTEPPER, 8, 2, SkillEnum.FATAL_SPIN, posWidth(1, 4), posHeight(30, 4)).yellowIcon()); //59
        getTcb().add(new TreeCheckBox("Dissolution", new String[]{"When entering Vanish, become immune to knockback", "and gain resistance for a brief moment.", " ", "Effect: +40% Resistance Bonus to Self", "Duration: 0.5s"}, new TreeCheckBox[]{getTcb().get(55), getTcb().get(56)}, new TreeCheckBox[]{getTcb().get(17)}, TreeCheckBox.ArchetypeEnum.SHADESTEPPER, 2, 1, SkillEnum.DISSOLUTION, posWidth(3, 2), posHeight(30, 2)).whiteIcon()); //60
        getTcb().add(new TreeCheckBox("Pirouette", new String[]{"When hitting an enemy with Dancing Blade,", "you will deal extra damage and vault upwards,", "resetting Dash as if you touched the ground.", " ", "Total Damage: +40%", "(Neutral +30%)", "(Air +10%)"}, new TreeCheckBox[]{getTcb().get(56), getTcb().get(57)}, new TreeCheckBox[]{getTcb().get(48)}, 2, SkillEnum.PIROUETTE, posWidth(5, 4), posHeight(30, 4)).yellowIcon()); //61
        getTcb().add(new TreeCheckBox("Stronger Lacerate", new String[]{"Lacerate will deal +1 slash."}, new TreeCheckBox[]{getTcb().get(57), getTcb().get(58)}, new TreeCheckBox[]{getTcb().get(22)}, new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(56), getTcb().get(61)})}, TreeCheckBox.ArchetypeEnum.ACROBAT, 0, 1, SkillEnum.STRONGER_LACERATE, posWidth(7, 2), posHeight(30, 2)).whiteIcon()); //62
        getTcb().get(61).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(58), getTcb().get(62)})});

        new TreeIcon(posWidth(1, 0), posHeight(31, 0), getPane()).up_down();
        new TreeIcon(posWidth(4, 0), posHeight(31, 0), getPane()).up_down();
        new TreeIcon(posWidth(7, 0), posHeight(31, 0), getPane()).up_down();
        getTcb().add(new TreeCheckBox("Stronger Vortex", new String[]{"Increases Violet Vortex's damage", "by +40% and increases the damage", "requirement to be 2.5x your max health."}, new TreeCheckBox[]{getTcb().get(59)}, new TreeCheckBox[]{getTcb().get(41)}, TreeCheckBox.ArchetypeEnum.SHADESTEPPER, 0, 1, SkillEnum.STRONGER_VORTEX, posWidth(0, 2), posHeight(31, 2)).whiteIcon()); //63

        new TreeIcon(posWidth(2, 0), posHeight(32, 0), getPane()).right_left_down();
        new TreeIcon(posWidth(3, 0), posHeight(32, 0), getPane()).right_left();
        new TreeIcon(posWidth(5, 0), posHeight(32, 0), getPane()).right_left_down();
        new TreeIcon(posWidth(6, 0), posHeight(32, 0), getPane()).right_left();
        new TreeIcon(posWidth(8, 0), posHeight(32, 0), getPane()).left_down();
        getTcb().add(new TreeCheckBox("Harvester", new String[]{"After killing enemy, gain +5 Mana", "for each leftover Mark it had."}, new TreeCheckBox[]{getTcb().get(59)}, new TreeCheckBox[]{getTcb().get(44)}, TreeCheckBox.ArchetypeEnum.SHADESTEPPER, 0, 2, SkillEnum.HARVESTER, posWidth(1, 6), posHeight(32, 6)).purpleIcon()); //64
        getTcb().add(new TreeCheckBox("Cheaper Smoke Bomb", new String[]{"Smoke Bomb Spell Cost: -5"}, new TreeCheckBox[]{getTcb().get(56)}, new TreeCheckBox[]{getTcb().get(7)}, 1, SkillEnum.CHEAPER_4TH_SP_COST, posWidth(4, 2), posHeight(32, 2)).whiteIcon()); //65
        getTcb().add(new TreeCheckBox("Blade Fury", new String[]{"While using Multihit on an enemy, lock", "their position in front of you.", " ", "Multihit gain extra damage and", "can be aimed in all directions.", " ", "Total Damage: +15% (of your DPS, per hit)", "(Thunder +15%)"}, new TreeCheckBox[]{getTcb().get(62)}, new TreeCheckBox[]{getTcb().get(8)}, new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(59), getTcb().get(64), getTcb().get(65)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(56), getTcb().get(65)})}, TreeCheckBox.ArchetypeEnum.ACROBAT, 0, 2, SkillEnum.BLADE_FURY, posWidth(7, 4), posHeight(32, 4)).yellowIcon()); //66
        getTcb().get(64).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(56), getTcb().get(65)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(62), getTcb().get(65), getTcb().get(66)})});
        getTcb().get(65).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(59), getTcb().get(64)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(62), getTcb().get(66)})});

        new TreeIcon(posWidth(1, 0), posHeight(33, 0), getPane()).up_down();
        new TreeIcon(posWidth(4, 0), posHeight(33, 0), getPane()).up_down();
        new TreeIcon(posWidth(7, 0), posHeight(33, 0), getPane()).up_down();
        getTcb().add(new TreeCheckBox("More Marks", new String[]{"Increases your maximum Marks by +2."}, new TreeCheckBox[]{getTcb().get(64), getTcb().get(65)}, new TreeCheckBox[]{getTcb().get(44)}, TreeCheckBox.ArchetypeEnum.SHADESTEPPER, 0, 1, SkillEnum.MORE_MARKS, posWidth(2, 2), posHeight(33, 2)).whiteIcon()); //67
        getTcb().add(new TreeCheckBox("Stronger Clones", new String[]{"Improves your damage by +15%", "while your Clones are active."}, new TreeCheckBox[]{getTcb().get(65), getTcb().get(66)}, new TreeCheckBox[]{getTcb().get(45)}, TreeCheckBox.ArchetypeEnum.TRICKSTER, 7, 2, SkillEnum.STRONGER_CLONES, posWidth(5, 4), posHeight(33, 4)).yellowIcon()); //68
        getTcb().add(new TreeCheckBox("Ricochets", new String[]{"Your Shurikens will bounce", "1 time between enemies upon impact.", " ", "Range: +12 Blocks"}, new TreeCheckBox[]{getTcb().get(66)}, new TreeCheckBox[]{getTcb().get(46)}, TreeCheckBox.ArchetypeEnum.ACROBAT, 6, 1, SkillEnum.RICOCHETS, posWidth(8, 2), posHeight(33, 2)).whiteIcon()); //69

        new TreeIcon(posWidth(0, 0), posHeight(34, 0), getPane()).right_down();
        new TreeIcon(posWidth(2, 0), posHeight(34, 0), getPane()).left_down();
        new TreeIcon(posWidth(5, 0), posHeight(34, 0), getPane()).left_down();
        new TreeIcon(posWidth(8, 0), posHeight(34, 0), getPane()).left_down();
        getTcb().add(new TreeCheckBox("Satsujin", new String[]{"If an enemy has 4+ Marks, your next Multihit", "or Special Powder will deal +100% damage.", "(15s Cooldown)"}, new TreeCheckBox[]{getTcb().get(64)}, 2, TreeCheckBox.ArchetypeEnum.SHADESTEPPER, 13, SkillEnum.SATSUJIN, posWidth(1, 10), posHeight(34, 10)).redIcon()); //70
        getTcb().add(new TreeCheckBox("Forbidden Art", new String[]{"Summons +3 additional Clones.", "(+15s Cooldown)"}, new TreeCheckBox[]{getTcb().get(65)}, new TreeCheckBox[]{getTcb().get(21)}, TreeCheckBox.ArchetypeEnum.TRICKSTER, 8, 2, SkillEnum.FORBIDDEN_ART, posWidth(4, 6), posHeight(34, 6)).purpleIcon()); //71
        getTcb().add(new TreeCheckBox("Jasmine Bloom", new String[]{"After spending 40 Mana, an area underneath", "you blooms and damages enemies below it every 0.3s.", " ", "The duration is reset and the radius is", "increased after every bloom. (Max 10 Blocks)", " ", "Total Damage: 80% (of your DPS)", "(Damage 60%)", "(Earth 5%)", "(Water 15%)", "Duration: 5s", "Area of Effect: +2 Blocks (per bloom)"}, new TreeCheckBox[]{getTcb().get(66)}, 2, TreeCheckBox.ArchetypeEnum.ACROBAT, 12, SkillEnum.JASMINE_BLOOM, posWidth(7, 10), posHeight(34, 10)).redIcon()); //72

        new TreeIcon(posWidth(0, 0), posHeight(35, 0), getPane()).up_down();
        new TreeIcon(posWidth(2, 0), posHeight(35, 0), getPane()).up_down();
        new TreeIcon(posWidth(4, 0), posHeight(35, 0), getPane()).right_down();
        getTcb().add(new TreeCheckBox("Diversion", new String[]{"Grants allies within 16 blocks +15% Overhealth", "whenever you hit a Lured enemy. (6.5s Cooldown)", "The bonus health decays over 10s."}, new TreeCheckBox[]{getTcb().get(71)}, new TreeCheckBox[]{getTcb().get(42)}, TreeCheckBox.ArchetypeEnum.TRICKSTER, 12, 2, SkillEnum.DIVERSION, posWidth(5, 10), posHeight(35, 10)).redIcon()); //73
        getTcb().add(new TreeCheckBox("Better Ricochets", new String[]{"Add +1 Max Bounce to Ricochets."}, new TreeCheckBox[]{getTcb().get(72)}, new TreeCheckBox[]{getTcb().get(69)}, TreeCheckBox.ArchetypeEnum.ACROBAT, 0, 1, SkillEnum.BETTER_RICOCHETS, posWidth(8, 2), posHeight(35, 2)).whiteIcon()); //74

        getTcb().add(new TreeCheckBox("Devour", new String[]{"Harvester will give +5 Mana."}, new TreeCheckBox[]{getTcb().get(70)}, new TreeCheckBox[]{getTcb().get(64)}, 1, SkillEnum.DEVOUR, posWidth(0, 2), posHeight(36, 2)).whiteIcon()); //75
        getTcb().add(new TreeCheckBox("Better Marked", new String[]{"Increases Marked's damage", "bonus by +4%"}, new TreeCheckBox[]{getTcb().get(70)}, 1, SkillEnum.BETTER_MARKED, posWidth(2, 2), posHeight(36, 2)).whiteIcon()); //76
        getTcb().add(new TreeCheckBox("Even Stronger Clones", new String[]{"Improves your damages by +10%", "while your Clones are active."}, new TreeCheckBox[]{getTcb().get(73)}, new TreeCheckBox[]{getTcb().get(45)}, TreeCheckBox.ArchetypeEnum.TRICKSTER, 0, 1, SkillEnum.EVEN_STRONGER_CLONES, posWidth(4, 2), posHeight(36, 2)).whiteIcon()); //77

        setTreeUI();
    }
}
