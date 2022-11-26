package com.github.iTzhsnu.WynnSearcher.builder.skilltree;

import javax.swing.*;

public class Warrior extends TreeBase {
    public Warrior(JPanel p) {
        super(p, TreeCheckBox.ArchetypeEnum.FALLEN, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, TreeCheckBox.ArchetypeEnum.PALADIN);
        getTcb().add(new TreeCheckBox("Bash", new String[] {"Mana Cost: 40", "Total Damage: 150% (of your DPS)", "(Damage 130%)", "(Earth 20%)", "Area of Effect: 4 Blocks (Circle-Shaped)", "Range: 3 Blocks"}, null, 1, SkillEnum.BASH, 160, 10).warriorIcon()); //0

        new TreeIcon(166, 52, getPane()).up_down();

        getTcb().add(new TreeCheckBox("Spear Proficiency I", new String[] {"Melee Damage: +5%", "Melee Range: +1 Blocks"}, new TreeCheckBox[] {getTcb().get(0)}, 1, SkillEnum.PROFICIENCY_1, 164, 86).whiteIcon()); //1
        new TreeIcon(130, 88, getPane()).right_left();
        getTcb().add(new TreeCheckBox("Cheaper Bash", new String[] {"Bash Spell Cost: -10"}, new TreeCheckBox[]{getTcb().get(1)}, 1, SkillEnum.REDUCE_10_1ST_SP_COST, 92, 86).whiteIcon()); //2

        new TreeIcon(166, 124, getPane()).up_down();

        getTcb().add(new TreeCheckBox("Double Bash", new String[] {"Bash will hit second", "time at a father range", " ", "Total Damage: -50% (of your DPS, per Bash)", "(Damage -50%)", "Range: +3 Blocks"}, new TreeCheckBox[]{getTcb().get(1)}, 1, SkillEnum.DOUBLE_BASH, 162, 156).yellowIcon()); //3

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
        new TreeIcon(58, 298, getPane()).right_left_down();
        new TreeIcon(22, 298, getPane()).right_down();
        new TreeIcon(274, 298, getPane()).right_left();
        new TreeIcon(310, 298, getPane()).left_down();
        getTcb().add(new TreeCheckBox("Uppercut", new String[] {"Mana Cost: 40", "Total Damage: 280% (of your DPS)", "(Damage 200%)", "(Earth 40%)", "(Thunder 40%)", "Area of Effect: 1-3 (Cone-Shaped)", "Range: 5 Blocks"}, new TreeCheckBox[]{getTcb().get(5)}, 1, SkillEnum.UPPERCUT, 88, 292).warriorIcon()); //7
        getTcb().add(new TreeCheckBox("War Scream", new String[] {"Mana Cost: 30", "Total Damage: 100% (of your DPS)", "(Damage 50%)", "(Fire 50%)", "Effect: +20% Resistance to Allies", "Duration: 30s", "Area of Effects: 12 Blocks (Circle-Shaped)"}, new TreeCheckBox[]{getTcb().get(6)}, 1, SkillEnum.WAR_SCREAM, 232, 292).warriorIcon()); //8
        getTcb().add(new TreeCheckBox("Cheaper Charge", new String[] {"Charge Spell Cost: -5"}, new TreeCheckBox[]{getTcb().get(7), getTcb().get(8)}, 1, SkillEnum.REDUCE_5_2ND_SP_COST, 164, 296).whiteIcon()); //9
        getTcb().get(7).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(6), getTcb().get(8), getTcb().get(9)})});
        getTcb().get(8).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(5), getTcb().get(7), getTcb().get(9)})});

        new TreeIcon(22, 334, getPane()).up_down();
        new TreeIcon(94, 334, getPane()).up_down();
        new TreeIcon(166, 334, getPane()).up_down();
        new TreeIcon(238, 334, getPane()).up_down();
        new TreeIcon(310, 334, getPane()).up_down();
        getTcb().add(new TreeCheckBox("Heavy Impact", new String[]{"After using Charge, violently crash", "down unto the ground and damage.", " ", "Total Damage: 100% (of your DPS)", "(Damage 100%)", "Area of Effect: 4 Blocks"}, new TreeCheckBox[]{getTcb().get(7)}, 1, SkillEnum.HEAVY_IMPACT, 54, 330).yellowIcon()); //10

        new TreeIcon(166, 370, getPane()).all();
        new TreeIcon(130, 370, getPane()).right_left();
        new TreeIcon(202, 370, getPane()).right_left();
        getTcb().add(TreeTemplates.earthMastery(20, 368, TreeCheckBox.ArchetypeEnum.FALLEN, new TreeCheckBox[]{getTcb().get(7)}, null)); //11
        getTcb().add(TreeTemplates.thunderMastery(92, 368, TreeCheckBox.ArchetypeEnum.FALLEN, new TreeCheckBox[]{getTcb().get(7), getTcb().get(9)},null)); //12
        getTcb().add(TreeTemplates.airMastery(236, 368, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, new TreeCheckBox[]{getTcb().get(8), getTcb().get(9)}, new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(7), getTcb().get(12)})})); //13
        getTcb().add(TreeTemplates.fireMastery(308, 368, TreeCheckBox.ArchetypeEnum.PALADIN, new TreeCheckBox[]{getTcb().get(8)}, null)); //14
        getTcb().get(12).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(8), getTcb().get(13)})});

        new TreeIcon(22, 406, getPane()).up_down();
        new TreeIcon(94, 406, getPane()).up_down();
        new TreeIcon(238, 406, getPane()).up_down();
        new TreeIcon(310, 406, getPane()).up_down();
        getTcb().add(TreeTemplates.waterMastery(164, 404, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, new TreeCheckBox[]{getTcb().get(9), getTcb().get(12), getTcb().get(13)}, null)); //15

        new TreeIcon(166, 442, getPane()).up_down();
        new TreeIcon(58, 442, getPane()).right_left_down();
        new TreeIcon(274, 442, getPane()).right_left_down();
        getTcb().add(new TreeCheckBox("Quadruple Bash", new String[]{"Bash will cast 4 times consecutively", "at double speed.", " ", "Total Damage: -20% (of your DPS, per Bash)", "(Damage -20%)", "Range: +6 Blocks"}, new TreeCheckBox[]{getTcb().get(11)}, new TreeCheckBox[]{getTcb().get(0)}, TreeCheckBox.ArchetypeEnum.FALLEN, 0, 2, SkillEnum.QUADRUPLE_BASH, 18, 438).yellowIcon()); //16
        getTcb().add(new TreeCheckBox("Fireworks", new String[]{"Mobs hit by Uppercut will explode", "mid-air and receive additional damage.", " ", "Total Damage: +100% (of your DPS)", "(Damage +80%)", "(Thunder +20%)"}, new TreeCheckBox[]{getTcb().get(12)}, new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(11), getTcb().get(16)})}, 2, SkillEnum.FIREWORKS, 90, 438).yellowIcon()); //17
        getTcb().add(new TreeCheckBox("Flyby Jab", new String[]{"Damage enemies in your", "way when using Charge.", " ", "Total Damage: 60% (of your DPS)", "(Damage 20%)", "(Air 40%)", "Area of Effect: 2 Blocks"}, new TreeCheckBox[]{getTcb().get(13)}, 2, SkillEnum.FLYBY_JAB, 234, 438).yellowIcon()); //18
        getTcb().add(new TreeCheckBox("Flaming Uppercut", new String[]{"Uppercut will light mobs on fire,", "dealing damage every seconds.", " ", "Total Damage: 50% (of your DPS)", "(Fire 50%)", "Duration: 3s"}, new TreeCheckBox[]{getTcb().get(14)}, new TreeCheckBox[]{getTcb().get(7)}, new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(13), getTcb().get(18)})}, TreeCheckBox.ArchetypeEnum.PALADIN, 0, 2, SkillEnum.FLAMING_UPPERCUT, 306, 438).yellowIcon()); //19
        getTcb().get(16).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(12), getTcb().get(17)})});
        getTcb().get(18).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(14), getTcb().get(19)})});

        new TreeIcon(58, 478, getPane()).up_down();
        getTcb().add(new TreeCheckBox("Half-Moon Swipe", new String[]{"Uppercut will deal a footsweep", "attack at a longer and wider angle.", " ", "Total Damage: -40% (of your DPS)", "(Damage -70%)", "(Water 30%)", "Range: 3 Blocks"}, new TreeCheckBox[]{getTcb().get(15)}, new TreeCheckBox[]{getTcb().get(7)}, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, 1, 2, SkillEnum.HALF_MOON_SWIPE, 162, 474).yellowIcon()); //20
        getTcb().add(new TreeCheckBox("Iron Lungs", new String[]{"War Scream will deal more damage.", " ", "Total Damage: +60% (of your DPS)", "(Damage +30%)", "(Air +30%)"}, new TreeCheckBox[]{getTcb().get(18), getTcb().get(19)}, new TreeCheckBox[]{getTcb().get(8)}, TreeCheckBox.ArchetypeEnum.PALADIN, 0, 1, SkillEnum.IRON_LUNGS, 272, 476).whiteIcon()); //21

        new TreeIcon(58, 514, getPane()).up_down();
        new TreeIcon(166, 514, getPane()).up_down();
        new TreeIcon(274, 514, getPane()).up_down();

        new TreeIcon(58, 550, getPane()).up_down();
        new TreeIcon(130, 550, getPane()).right_left_down();
        getTcb().add(new TreeCheckBox("Counter", new String[]{"when dodding nearby enemy attack, get", "30% change to instaly attack back.", " ", "Total Damage: 100% (of your DPS)", "(Damage 60%)", "(Thunder 20%)", "(Air 20%)"}, new TreeCheckBox[]{getTcb().get(20)}, 2, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, 0, SkillEnum.COUNTER, 162, 546).yellowIcon()); //22
        getTcb().add(new TreeCheckBox("Generalist", new String[]{"After casting 3 different spells", "in a row, your next spell cost 5 Mana."}, new TreeCheckBox[]{getTcb().get(22)}, 2, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, 3, SkillEnum.GENERALIST, 84, 540).redIcon()); //23
        getTcb().add(new TreeCheckBox("Mantle of the Bovemists", new String[]{"When casting War Scream, create a holy", "shield around you that reduces all incoming", "damage by -70% for 3 hits. (25s Cooldown)"}, new TreeCheckBox[]{getTcb().get(21)}, new TreeCheckBox[]{getTcb().get(8)}, TreeCheckBox.ArchetypeEnum.PALADIN, 3, 2, SkillEnum.MANTLE_OF_THE_BOVEMISTS, 264, 540).redIcon()); //24

        new TreeIcon(22, 586, getPane()).right_down();
        new TreeIcon(130, 586, getPane()).up_down();
        new TreeIcon(274, 586, getPane()).up_down();
        getTcb().add(new TreeCheckBox("Bak'al's Grasp", new String[]{"Casting War Scream will make you", "Corrupted (15s Cooldown)", " ", "While Corrupted you lose the ability", "to heal, and 2% health you lose will", "grant you +4 Raw Damage (Max 120)"}, new TreeCheckBox[]{getTcb().get(16), getTcb().get(17)}, new TreeCheckBox[]{getTcb().get(8)}, TreeCheckBox.ArchetypeEnum.FALLEN, 2, 2, SkillEnum.BAKALS_GRASP, 48, 576).redIcon()); //25

        new TreeIcon(58, 622, getPane()).right_left();
        new TreeIcon(94, 622, getPane()).right_left_down();
        new TreeIcon(166, 622, getPane()).right_left();
        new TreeIcon(238, 622, getPane()).right_left_down();
        new TreeIcon(310, 622, getPane()).left_down();
        getTcb().add(new TreeCheckBox("Spear Proficiency 2", new String[]{"Melee Damage: +5%", "Melee Range: +1 Blocks"}, new TreeCheckBox[]{getTcb().get(25)}, 1, SkillEnum.PROFICIENCY_2, 20, 620).whiteIcon()); //26
        getTcb().add(new TreeCheckBox("Cheaper Uppercut", new String[]{"Uppercut Spell Cost: -5"}, new TreeCheckBox[]{getTcb().get(22)}, 1, SkillEnum.REDUCE_5_3RD_SP_COST, 128, 620).whiteIcon()); //27
        getTcb().add(new TreeCheckBox("Aerodynamics", new String[]{"During Charge, you can", "steer and change direction."}, null, 2, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, 0, SkillEnum.AERODYNAMICS, 198, 618).yellowIcon()); //28
        getTcb().add(new TreeCheckBox("Provoke", new String[]{"Mobs damaged by War Scream will target", "only you for at least 8s. (10s Cooldown)", " ", "War Scream Spell Cost: -5"}, new TreeCheckBox[]{getTcb().get(24)}, new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(25), getTcb().get(26), getTcb().get(27), getTcb().get(28)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(22), getTcb().get(27), getTcb().get(28)})}, 2, SkillEnum.PROVOKE, 270, 618).yellowIcon()); //29
        getTcb().get(26).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(24), getTcb().get(27), getTcb().get(28), getTcb().get(29)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(22), getTcb().get(27)})});
        getTcb().get(27).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(25), getTcb().get(26)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(24), getTcb().get(28), getTcb().get(29)})});
        getTcb().get(28).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(25), getTcb().get(26), getTcb().get(27), getTcb().get(28)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(22), getTcb().get(27)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(24), getTcb().get(29)})});

        new TreeIcon(22, 658, getPane()).up_down();
        new TreeIcon(130, 658, getPane()).up_down();
        new TreeIcon(310, 658, getPane()).up_down();
        getTcb().add(new TreeCheckBox("Precise Strikes", new String[]{"Critical Damage: +30%"}, new TreeCheckBox[]{getTcb().get(26), getTcb().get(27)}, 1, SkillEnum.PRECISE_STRIKES, 92, 656).whiteIcon()); //30
        getTcb().add(new TreeCheckBox("Air Shout", new String[]{"War Scream will fire a projectile that can", "go through walls and deal damage multiple times."}, new TreeCheckBox[]{getTcb().get(28), getTcb().get(29)}, new TreeCheckBox[]{getTcb().get(8)}, 2, SkillEnum.AIR_SHOUT, 234, 654).yellowIcon()); //31

        new TreeIcon(22, 694, getPane()).up_down();
        new TreeIcon(130, 694, getPane()).up_down();
        new TreeIcon(310, 694, getPane()).up_down();

        new TreeIcon(94, 730, getPane()).right_down();
        new TreeIcon(166, 730, getPane()).right_left_down();
        new TreeIcon(202, 730, getPane()).right_left();
        new TreeIcon(274, 730, getPane()).right_left();
        getTcb().add(new TreeCheckBox("Enraged Blow", new String[]{"While Corrupted, every 1% of", "Health you lose will increase your", "damage by +2% (Max 160%)"}, new TreeCheckBox[]{getTcb().get(26)}, new TreeCheckBox[]{getTcb().get(25)}, TreeCheckBox.ArchetypeEnum.FALLEN, 0, 2, SkillEnum.ENRAGED_BLOW, 16, 724).purpleIcon()); //32
        getTcb().add(new TreeCheckBox("Flying Kick", new String[]{"Hitting mobs with Charge will halt your", "momentum and knock them away."}, new TreeCheckBox[]{getTcb().get(27)}, 2, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, 1, SkillEnum.FLYING_KICK, 126, 726).yellowIcon()); //33
        getTcb().add(new TreeCheckBox("Stronger Mantle", new String[]{"add +2 additional charges", "to Mantle of the Bovemists", "and reduce its cooldown by -5s."}, null, new TreeCheckBox[]{getTcb().get(24)}, TreeCheckBox.ArchetypeEnum.PALADIN, 0, 1, SkillEnum.STRONGER_MANTLE, 236, 728).whiteIcon()); //34
        getTcb().add(new TreeCheckBox("Manachism", new String[]{"If you receive a hit that's less than 5%", "of your max HP, gain 10 Mana. (1s Cooldown)"}, new TreeCheckBox[]{getTcb().get(29)}, null, new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(27), getTcb().get(33), getTcb().get(34)})}, TreeCheckBox.ArchetypeEnum.PALADIN, 3, 2, SkillEnum.MANACHISM, 304, 724).purpleIcon()); //35
        getTcb().get(33).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(29), getTcb().get(34), getTcb().get(35)})});
        getTcb().get(34).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(27), getTcb().get(33)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(29), getTcb().get(35)})});

        new TreeIcon(22, 766, getPane()).up_down();
        new TreeIcon(94, 766, getPane()).up_down();
        new TreeIcon(166, 766, getPane()).up_down();
        new TreeIcon(310, 766, getPane()).up_down();

        new TreeIcon(58, 802, getPane()).right_left_down();
        new TreeIcon(202, 802, getPane()).right_left_down();
        new TreeIcon(274, 802, getPane()).right_left_down();
        getTcb().add(new TreeCheckBox("Boiling Blood", new String[]{"Bash will leave a puddle of boiling blood", "after its fast first explosion, slowing down and", "damaging enemies on it every 0.4 seconds.", " ", "Total Damage: 30% (of your DPS)", "(Damage 25%)", "(Fire 5%)", "Effect: 20% Slowness to Enemies", "Duration: 3s"}, new TreeCheckBox[]{getTcb().get(32)}, 2, SkillEnum.BOILING_BLOOD, 18, 798).yellowIcon()); //36
        getTcb().add(new TreeCheckBox("Ragnarokkr", new String[]{"War Scream becomes defeating, increasing its", "range and granting players a damage bonus."}, new TreeCheckBox[]{getTcb().get(33)}, new TreeCheckBox[]{getTcb().get(8)}, new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(32), getTcb().get(36)})}, TreeCheckBox.ArchetypeEnum.FALLEN, 0, 2, SkillEnum.RAGNAROKKR, 88, 796).purpleIcon()); //37
        getTcb().add(new TreeCheckBox("Ambidextrous", new String[]{"Increase your chance to attack", "with Counter by +30%."}, new TreeCheckBox[]{getTcb().get(33), getTcb().get(34)}, new TreeCheckBox[]{getTcb().get(22)}, 1, SkillEnum.AMBIDEXTROUS, 164, 800).whiteIcon()); //38
        getTcb().add(new TreeCheckBox("Burning Heart", new String[]{"For every 100 Health Bonus you have from", "items, gain +2% Fire Damage. (Max 100%)"}, null, 1, TreeCheckBox.ArchetypeEnum.PALADIN, 0, SkillEnum.BURNING_HEART, 236, 800).whiteIcon()); //39
        getTcb().add(new TreeCheckBox("Stronger Bash", new String[]{"Increase the damage of Bash.", " ", "Total Damage: +30% (of your DPS)", "(Damage +30%)"}, new TreeCheckBox[]{getTcb().get(35)}, 1, SkillEnum.STRONGER_BASH, 308, 800).whiteIcon()); //40
        getTcb().get(36).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(33), getTcb().get(37)})});
        getTcb().get(38).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(35), getTcb().get(39), getTcb().get(40)})});
        getTcb().get(39).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(33), getTcb().get(38)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(35), getTcb().get(40)})});

        new TreeIcon(22, 838, getPane()).up_down();
        new TreeIcon(94, 838, getPane()).up_down();
        new TreeIcon(166, 838, getPane()).up_down();
        getTcb().add(new TreeCheckBox("Intoxicating Blood", new String[]{"After leaving Corrupted, gain 5%", "of the health lost back for each", "enemy killed while Corrupted."}, new TreeCheckBox[]{getTcb().get(36), getTcb().get(37)}, new TreeCheckBox[]{getTcb().get(25)}, TreeCheckBox.ArchetypeEnum.FALLEN, 5, 2, SkillEnum.INTOXICATING_BLOOD, 54, 834).yellowIcon()); //41
        getTcb().add(new TreeCheckBox("Collide", new String[]{"Enemies thrown into walls from Flying Kick", "will explode and receive additional damage.", " ", "Total Damage: 200% (of your DPS)", "(Damage 150%)", "(Fire 50%)", "Area of Effect: 4 Blocks"}, new TreeCheckBox[]{getTcb().get(38), getTcb().get(39)}, new TreeCheckBox[]{getTcb().get(33)}, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, 4, 2, SkillEnum.COLLIDE, 198, 834).yellowIcon()); //42
        getTcb().add(new TreeCheckBox("Rejuvenating Skin", new String[]{"Regain back 30% of the damage", "you take as healing 30s."}, new TreeCheckBox[]{getTcb().get(39), getTcb().get(40)}, 2, TreeCheckBox.ArchetypeEnum.PALADIN, 5, SkillEnum.REJUVENATING_SKIN, 264, 828).redIcon()); //43

        new TreeIcon(22, 874, getPane()).up_down();
        new TreeIcon(166, 874, getPane()).up_down();
        new TreeIcon(274, 874, getPane()).up_down();
        getTcb().add(new TreeCheckBox("Comet", new String[]{"After being hit by Fireworks, enemies will crash", "unto the ground and receive more damage.", " ", "Total Damage: +100% (of your DPS)", "(Damage +80%)", "(Earth +20%)"}, new TreeCheckBox[]{getTcb().get(37)}, new TreeCheckBox[]{getTcb().get(33)}, TreeCheckBox.ArchetypeEnum.FALLEN, 0, 2, SkillEnum.COMET, 90, 870).yellowIcon()); //44

        new TreeIcon(22, 910, getPane()).up_down();
        new TreeIcon(166, 910, getPane()).up_down();
        new TreeIcon(274, 910, getPane()).up_down();

        new TreeIcon(58, 946, getPane()).right_left_down();
        new TreeIcon(130, 946, getPane()).right_left();
        new TreeIcon(238, 946, getPane()).right_down();
        new TreeIcon(310, 946, getPane()).left_down();
        getTcb().add(new TreeCheckBox("Uncontainable Corruption", new String[]{"Reduces the cooldown of Bak'al's Grasp", "by -5s, and increases the raw damage", "gained for every 2% of health lost by +1."}, new TreeCheckBox[]{getTcb().get(36)}, new TreeCheckBox[]{getTcb().get(25)}, 1, SkillEnum.UNCONTAINABLE_CORRUPTION, 20, 944).whiteIcon()); //45
        getTcb().add(new TreeCheckBox("Radiant Devotee", new String[]{"For every 4% Reflection you have from", "items, gain +1/5s Mana Regen. (Max 10/5s)"}, null, 1, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, 1, SkillEnum.RADIANT_DEVOTEE, 92, 944).whiteIcon()); //46
        getTcb().add(new TreeCheckBox("Whirlwind Strike", new String[]{"Uppercut will create a strong gust of air,", "launching you upward with enemies.", " ", "Total Damage: +50% (of your DPS)", "(Air +50%)", "Area of Effect: Circle-Shaped", "Range: 1 Blocks"}, new TreeCheckBox[]{getTcb().get(38)}, new TreeCheckBox[]{getTcb().get(7)}, new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(36), getTcb().get(45), getTcb().get(46)})}, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, 5, 2, SkillEnum.WHIRLWIND_STRIKE, 162, 942).yellowIcon()); //47
        getTcb().add(new TreeCheckBox("Mythril Skin", new String[]{"Gain +5% Base Resistance and", "become immune to knockback."}, new TreeCheckBox[]{getTcb().get(43)}, 2, TreeCheckBox.ArchetypeEnum.PALADIN, 6, SkillEnum.MYTHRIL_SKIN, 270, 942).yellowIcon()); //48
        getTcb().get(45).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(38), getTcb().get(46), getTcb().get(47)})});
        getTcb().get(46).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(36), getTcb().get(45)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(38), getTcb().get(47)})});

        new TreeIcon(22, 982, getPane()).up_down();
        new TreeIcon(166, 982, getPane()).up_down();
        new TreeIcon(274, 982, getPane()).right_left_down();
        getTcb().add(new TreeCheckBox("Armour Breaker", new String[]{"While Corrupted, losing 30% Health will make", "your next Uppercut reduce the enemies defense stats.", " ", "Effect: -30% Resistance to Enemies", "Duration: 8s"}, new TreeCheckBox[]{getTcb().get(45), getTcb().get(46)}, new TreeCheckBox[]{getTcb().get(25)}, TreeCheckBox.ArchetypeEnum.FALLEN, 0, 2, SkillEnum.ARMOUR_BREAKER, 52, 976).purpleIcon()); //49
        getTcb().add(new TreeCheckBox("Shield Strike", new String[]{"Deals damage around you for each mantle lost", "when Mantle of the Bovemists loses all charges.", " ", "Total Damage: 100% (of your DPS, per Mantle lost)", "(Damage 70%)", "(Thunder 30%)", "Area of Effect: 5 Blocks (Circle-Shaped)"}, new TreeCheckBox[]{getTcb().get(48)}, new TreeCheckBox[]{getTcb().get(24)}, TreeCheckBox.ArchetypeEnum.PALADIN, 0, 2, SkillEnum.SHIELD_STRIKE, 234, 978).yellowIcon()); //50
        getTcb().add(new TreeCheckBox("Sparkling Hope", new String[]{"Deal damage to all nearby enemies every", "time you heal 5% of your max health.", " ", "Total Damage: 60% (of your DPS)", "(Damage 40%)", "(Thunder 20%)", "Area of Effect: 6 Blocks (Circle-Shaped)"}, new TreeCheckBox[]{getTcb().get(48)}, 2, TreeCheckBox.ArchetypeEnum.PALADIN, 0, SkillEnum.SPARKLING_HOPE, 304, 976).purpleIcon()); //51



        setTreeUI();
    }
}
