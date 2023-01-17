package com.github.iTzhsnu.WynnSearcher.builder.skilltree;

import javax.swing.*;

public class Warrior extends TreeBase {
    public Warrior(JPanel p) {
        super(p, TreeCheckBox.ArchetypeEnum.FALLEN, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, TreeCheckBox.ArchetypeEnum.PALADIN, "Warrior");
        getTcb().add(new TreeCheckBox("Bash", new String[] {"Mana Cost: 40", "Total Damage: 150% (of your DPS)", "(Damage 130%)", "(Earth 20%)", "Area of Effect: 4 Blocks (Circle-Shaped)", "Range: 3 Blocks"}, null, 1, SkillEnum.BASH, 160, 10).warriorIcon()); //0

        new TreeIcon(166, 52, getPane()).up_down();

        getTcb().add(new TreeCheckBox("Spear Proficiency I", new String[] {"Melee Damage: +5%", "Melee Range: +1 Blocks"}, new TreeCheckBox[] {getTcb().get(0)}, 1, SkillEnum.PROFICIENCY, 164, 86).whiteIcon()); //1
        new TreeIcon(130, 88, getPane()).right_left();
        getTcb().add(new TreeCheckBox("Cheaper Bash", new String[] {"Bash Spell Cost: -10"}, new TreeCheckBox[]{getTcb().get(1)}, 1, SkillEnum.CHEAPER_1ST_SP_COST_10, 92, 86).whiteIcon()); //2

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
        getTcb().add(new TreeCheckBox("Cheaper Charge", new String[] {"Charge Spell Cost: -5"}, new TreeCheckBox[]{getTcb().get(7), getTcb().get(8)}, 1, SkillEnum.CHEAPER_2ND_SP_COST, 164, 296).whiteIcon()); //9
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
        getTcb().add(new TreeCheckBox("Fireworks", new String[]{"Mobs hit by Uppercut will explode", "mid-air and receive additional damage.", " ", "Total Damage: +100% (of your DPS)", "(Damage +80%)", "(Thunder +20%)"}, new TreeCheckBox[]{getTcb().get(12)}, null, new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(11), getTcb().get(16)})}, TreeCheckBox.ArchetypeEnum.FALLEN, 0, 2, SkillEnum.FIREWORKS, 90, 438).yellowIcon()); //17
        getTcb().add(new TreeCheckBox("Flyby Jab", new String[]{"Damage enemies in your", "way when using Charge.", " ", "Total Damage: 60% (of your DPS)", "(Damage 20%)", "(Air 40%)", "Area of Effect: 2 Blocks"}, new TreeCheckBox[]{getTcb().get(13)}, 2, SkillEnum.FLYBY_JAB, 234, 438).yellowIcon()); //18
        getTcb().add(new TreeCheckBox("Flaming Uppercut", new String[]{"Uppercut will light mobs on fire,", "dealing damage every seconds.", " ", "Total Damage: 50% (of your DPS)", "(Fire 50%)", "Duration: 3s"}, new TreeCheckBox[]{getTcb().get(14)}, new TreeCheckBox[]{getTcb().get(7)}, new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(13), getTcb().get(18)})}, TreeCheckBox.ArchetypeEnum.PALADIN, 0, 2, SkillEnum.FLAMING_UPPERCUT, 306, 438).yellowIcon()); //19
        getTcb().get(16).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(12), getTcb().get(17)})});
        getTcb().get(18).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(14), getTcb().get(19)})});

        new TreeIcon(58, 478, getPane()).up_down();
        getTcb().add(new TreeCheckBox("Half-Moon Swipe", new String[]{"Uppercut will deal a footsweep", "attack at a longer and wider angle.", " ", "Uppercut Spell Cost: -5", "Total Damage: -40% (of your DPS)", "(Damage -70%)", "(Water 30%)", "Range: 1.5 Blocks"}, new TreeCheckBox[]{getTcb().get(15)}, new TreeCheckBox[]{getTcb().get(7)}, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, 1, 2, SkillEnum.HALF_MOON_SWIPE, 162, 474).yellowIcon()); //20
        getTcb().add(new TreeCheckBox("Iron Lungs", new String[]{"War Scream will deal more damage.", " ", "Total Damage: +60% (of your DPS, for War Scream)", "(Damage +30%)", "(Air +30%)", " ", "Total Damage: +30% (of your DPS, for Air Shout)", "(Damage +15%)", "(Air +15%)"}, new TreeCheckBox[]{getTcb().get(18), getTcb().get(19)}, new TreeCheckBox[]{getTcb().get(8)}, TreeCheckBox.ArchetypeEnum.PALADIN, 0, 1, SkillEnum.IRON_LUNGS, 272, 476).whiteIcon()); //21

        new TreeIcon(58, 514, getPane()).up_down();
        new TreeIcon(166, 514, getPane()).up_down();
        new TreeIcon(274, 514, getPane()).up_down();

        new TreeIcon(58, 550, getPane()).up_down();
        new TreeIcon(130, 550, getPane()).right_left_down();
        getTcb().add(new TreeCheckBox("Air Shout", new String[]{"War Scream will fire a projectile that can", "go through walls and deal damage multiple times.", " ", "Total Damage: 50%", "(Neutral 40%)", "(Air 10%)", "Area of Effect: 2 Blocks (Circle-Shaped)", "Range: 32 Blocks"}, new TreeCheckBox[]{getTcb().get(20)}, new TreeCheckBox[]{getTcb().get(8)}, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, 0, 2, SkillEnum.AIR_SHOUT, 162, 546).yellowIcon()); //22
        getTcb().add(new TreeCheckBox("Generalist", new String[]{"After casting 3 different spells", "in a row, your next spell cost 5 Mana."}, new TreeCheckBox[]{getTcb().get(22)}, 2, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, 3, SkillEnum.GENERALIST, 84, 540).redIcon()); //23
        getTcb().add(new TreeCheckBox("Mantle of the Bovemists", new String[]{"When casting War Scream, create a holy", "shield around you that reduces all incoming", "damage by -70% for 3 hits. (25s Cooldown)"}, new TreeCheckBox[]{getTcb().get(21)}, new TreeCheckBox[]{getTcb().get(8)}, TreeCheckBox.ArchetypeEnum.PALADIN, 3, 2, SkillEnum.MANTLE_OF_THE_BOVEMISTS, 264, 540).redIcon()); //24

        new TreeIcon(22, 586, getPane()).right_down();
        new TreeIcon(130, 586, getPane()).up_down();
        new TreeIcon(274, 586, getPane()).up_down();
        getTcb().add(new TreeCheckBox("Bak'al's Grasp", new String[]{"Casting War Scream will make you", "Corrupted (15s Cooldown)", " ", "While Corrupted you lose the ability", "to heal, and 2% health you lose will", "grant you +4 Raw Damage (Max 120)"}, new TreeCheckBox[]{getTcb().get(16), getTcb().get(17)}, new TreeCheckBox[]{getTcb().get(8)}, TreeCheckBox.ArchetypeEnum.FALLEN, 2, 2, SkillEnum.BAKALS_GRASP, 48, 576).redIcon()); //25

        new TreeIcon(58, 622, getPane()).right_left();
        new TreeIcon(94, 622, getPane()).right_left();
        new TreeIcon(166, 622, getPane()).right_left_down();
        new TreeIcon(238, 622, getPane()).right_left();
        new TreeIcon(310, 622, getPane()).left_down();
        getTcb().add(new TreeCheckBox("Spear Proficiency 2", new String[]{"Melee Damage: +5%", "Melee Range: +1 Blocks"}, new TreeCheckBox[]{getTcb().get(25)}, 1, SkillEnum.PROFICIENCY, 20, 620).whiteIcon()); //26
        getTcb().add(new TreeCheckBox("Cheaper Uppercut", new String[]{"Uppercut Spell Cost: -5"}, new TreeCheckBox[]{getTcb().get(22)}, 1, SkillEnum.CHEAPER_3RD_SP_COST, 128, 620).whiteIcon()); //27
        getTcb().add(new TreeCheckBox("Aerodynamics", new String[]{"During Charge, you can", "steer and change direction."}, null, 2, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, 0, SkillEnum.AERODYNAMICS, 198, 618).yellowIcon()); //28
        getTcb().add(new TreeCheckBox("Provoke", new String[]{"Mobs damaged by War Scream will target", "only you for at least 8s. (10s Cooldown)", " ", "War Scream Spell Cost: -5"}, new TreeCheckBox[]{getTcb().get(24)}, new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(25), getTcb().get(26), getTcb().get(27), getTcb().get(28)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(22), getTcb().get(27), getTcb().get(28)})}, 2, SkillEnum.PROVOKE, 270, 618).yellowIcon()); //29
        getTcb().get(26).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(24), getTcb().get(27), getTcb().get(28), getTcb().get(29)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(22), getTcb().get(27)})});
        getTcb().get(27).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(25), getTcb().get(26)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(24), getTcb().get(28), getTcb().get(29)})});
        getTcb().get(28).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(25), getTcb().get(26), getTcb().get(27), getTcb().get(28)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(22), getTcb().get(27)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(24), getTcb().get(29)})});

        new TreeIcon(22, 658, getPane()).up_down();
        new TreeIcon(130, 658, getPane()).up_down();
        new TreeIcon(310, 658, getPane()).up_down();
        getTcb().add(new TreeCheckBox("Counter", new String[]{"when dodding nearby enemy attack, get", "30% change to instaly attack back.", " ", "Total Damage: 100% (of your DPS)", "(Damage 60%)", "(Thunder 20%)", "(Air 20%)"}, new TreeCheckBox[]{getTcb().get(27), getTcb().get(28)}, 2, SkillEnum.COUNTER, 162, 654).yellowIcon()); //30

        new TreeIcon(22, 694, getPane()).up_down();
        new TreeIcon(130, 694, getPane()).up_down();
        new TreeIcon(310, 694, getPane()).up_down();

        new TreeIcon(94, 730, getPane()).right_down();
        new TreeIcon(166, 730, getPane()).right_left_down();
        new TreeIcon(202, 730, getPane()).right_left();
        new TreeIcon(274, 730, getPane()).right_left();
        getTcb().add(new TreeCheckBox("Enraged Blow", new String[]{"While Corrupted, every 1% of", "Health you lose will increase your", "damage by +1.5% (Max 120%)"}, new TreeCheckBox[]{getTcb().get(26)}, new TreeCheckBox[]{getTcb().get(25)}, TreeCheckBox.ArchetypeEnum.FALLEN, 0, 2, SkillEnum.ENRAGED_BLOW, 16, 724).purpleIcon()); //31
        getTcb().add(new TreeCheckBox("Flying Kick", new String[]{"Hitting mobs with Charge will halt your", "momentum and knock them away.", " ", "Total Damage: 200% (of your DPS)", "(Damage 150%)", "(Water 20%)", "(Air 30%)"}, new TreeCheckBox[]{getTcb().get(27)}, 2, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, 1, SkillEnum.FLYING_KICK, 126, 726).yellowIcon()); //32
        getTcb().add(new TreeCheckBox("Stronger Mantle", new String[]{"add +2 additional charges", "to Mantle of the Bovemists", "and reduce its cooldown by -5s."}, null, new TreeCheckBox[]{getTcb().get(24)}, TreeCheckBox.ArchetypeEnum.PALADIN, 0, 1, SkillEnum.STRONGER_MANTLE, 236, 728).whiteIcon()); //33
        getTcb().add(new TreeCheckBox("Manachism", new String[]{"If you receive a hit that's less than 5%", "of your max HP, gain 10 Mana. (1s Cooldown)"}, new TreeCheckBox[]{getTcb().get(29)}, null, new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(27), getTcb().get(32), getTcb().get(33)})}, TreeCheckBox.ArchetypeEnum.PALADIN, 3, 2, SkillEnum.MANACHISM, 304, 724).purpleIcon()); //34
        getTcb().get(32).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(29), getTcb().get(33), getTcb().get(34)})});
        getTcb().get(33).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(27), getTcb().get(32)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(29), getTcb().get(34)})});

        new TreeIcon(22, 766, getPane()).up_down();
        new TreeIcon(94, 766, getPane()).up_down();
        new TreeIcon(166, 766, getPane()).up_down();
        new TreeIcon(310, 766, getPane()).up_down();

        new TreeIcon(58, 802, getPane()).right_left_down();
        new TreeIcon(202, 802, getPane()).right_left_down();
        new TreeIcon(274, 802, getPane()).right_left_down();
        getTcb().add(new TreeCheckBox("Boiling Blood", new String[]{"Bash will leave a puddle of boiling blood", "after its fast first explosion, slowing down and", "damaging enemies on it every 0.4 seconds.", " ", "Total Damage: 30% (of your DPS)", "(Damage 25%)", "(Fire 5%)", "Effect: 20% Slowness to Enemies", "Duration: 3s"}, new TreeCheckBox[]{getTcb().get(31)}, 2, SkillEnum.BOILING_BLOOD, 18, 798).yellowIcon()); //35
        getTcb().add(new TreeCheckBox("Ragnarokkr", new String[]{"War Scream becomes defeating, increasing its", "range and granting players a damage bonus.", " ", "War Scream Spell Cost: +10", "Effect: 30% Damage Bonus to Players", "Duration: +90s"}, new TreeCheckBox[]{getTcb().get(32)}, new TreeCheckBox[]{getTcb().get(8)}, new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(31), getTcb().get(35)})}, TreeCheckBox.ArchetypeEnum.FALLEN, 0, 2, SkillEnum.RAGNAROKKR, 88, 796).purpleIcon()); //36
        getTcb().add(new TreeCheckBox("Precise Strike", new String[]{"Critical Hit Damage: +30%"}, new TreeCheckBox[]{getTcb().get(32), getTcb().get(33)}, 1, SkillEnum.PRECISE_STRIKES, 164, 800).whiteIcon()); //37
        getTcb().add(new TreeCheckBox("Burning Heart", new String[]{"For every 100 Health Bonus you have from", "items, gain +2% Fire Damage. (Max 100%)"}, null, 1, TreeCheckBox.ArchetypeEnum.PALADIN, 0, SkillEnum.BURNING_HEART, 236, 800).whiteIcon()); //38
        getTcb().add(new TreeCheckBox("Stronger Bash", new String[]{"Increase the damage of Bash.", " ", "Total Damage: +30% (of your DPS)", "(Damage +30%)"}, new TreeCheckBox[]{getTcb().get(34)}, 1, SkillEnum.STRONGER_BASH, 308, 800).whiteIcon()); //39
        getTcb().get(35).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(32), getTcb().get(36)})});
        getTcb().get(37).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(34), getTcb().get(38), getTcb().get(39)})});
        getTcb().get(38).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(32), getTcb().get(37)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(34), getTcb().get(39)})});

        new TreeIcon(22, 838, getPane()).up_down();
        new TreeIcon(94, 838, getPane()).up_down();
        new TreeIcon(166, 838, getPane()).up_down();
        getTcb().add(new TreeCheckBox("Intoxicating Blood", new String[]{"After leaving Corrupted, gain 5%", "of the health lost back for each", "enemy killed while Corrupted."}, new TreeCheckBox[]{getTcb().get(35), getTcb().get(36)}, new TreeCheckBox[]{getTcb().get(25)}, TreeCheckBox.ArchetypeEnum.FALLEN, 5, 2, SkillEnum.INTOXICATING_BLOOD, 54, 834).yellowIcon()); //40
        getTcb().add(new TreeCheckBox("Collide", new String[]{"Enemies thrown into walls from Flying Kick", "will explode and receive additional damage.", " ", "Total Damage: 200% (of your DPS)", "(Damage 150%)", "(Fire 50%)", "Area of Effect: 4 Blocks"}, new TreeCheckBox[]{getTcb().get(37), getTcb().get(38)}, new TreeCheckBox[]{getTcb().get(32)}, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, 4, 2, SkillEnum.COLLIDE, 198, 834).yellowIcon()); //41
        getTcb().add(new TreeCheckBox("Rejuvenating Skin", new String[]{"Regain back 30% of the damage", "you take as healing 30s."}, new TreeCheckBox[]{getTcb().get(38), getTcb().get(39)}, 2, TreeCheckBox.ArchetypeEnum.PALADIN, 5, SkillEnum.REJUVENATING_SKIN, 264, 828).redIcon()); //42

        new TreeIcon(22, 874, getPane()).up_down();
        new TreeIcon(166, 874, getPane()).up_down();
        new TreeIcon(274, 874, getPane()).up_down();
        getTcb().add(new TreeCheckBox("Comet", new String[]{"After being hit by Fireworks, enemies will crash", "unto the ground and receive more damage.", " ", "Total Damage: +100% (of your DPS)", "(Damage +80%)", "(Earth +20%)"}, new TreeCheckBox[]{getTcb().get(36)}, new TreeCheckBox[]{getTcb().get(17)}, TreeCheckBox.ArchetypeEnum.FALLEN, 0, 2, SkillEnum.COMET, 90, 870).yellowIcon()); //43

        new TreeIcon(22, 910, getPane()).up_down();
        new TreeIcon(166, 910, getPane()).up_down();
        new TreeIcon(274, 910, getPane()).up_down();

        new TreeIcon(58, 946, getPane()).right_left_down();
        new TreeIcon(130, 946, getPane()).right_left_down();
        new TreeIcon(238, 946, getPane()).right_down();
        new TreeIcon(310, 946, getPane()).left_down();
        getTcb().add(new TreeCheckBox("Uncontainable Corruption", new String[]{"Reduces the cooldown of Bak'al's Grasp", "by -5s, and increases the raw damage", "gained for every 2% of health lost by +1."}, new TreeCheckBox[]{getTcb().get(35)}, new TreeCheckBox[]{getTcb().get(25)}, 1, SkillEnum.UNCONTAINABLE_CORRUPTION, 20, 944).whiteIcon()); //44
        getTcb().add(new TreeCheckBox("Radiant Devotee", new String[]{"For every 4% Reflection you have from", "items, gain +1/5s Mana Regen. (Max 10/5s)"}, null, 1, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, 1, SkillEnum.RADIANT_DEVOTEE, 92, 944).whiteIcon()); //45
        getTcb().add(new TreeCheckBox("Whirlwind Strike", new String[]{"Uppercut will create a strong gust of air,", "launching you upward with enemies.", " ", "Total Damage: +10% (of your DPS)", "(Damage -20%)", "(Air +30%)", "Area of Effect: Circle-Shaped", "Range: 1 Blocks"}, new TreeCheckBox[]{getTcb().get(37)}, new TreeCheckBox[]{getTcb().get(7)}, new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(35), getTcb().get(44), getTcb().get(45)})}, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, 5, 2, SkillEnum.WHIRLWIND_STRIKE, 156, 936).redIcon()); //46
        getTcb().add(new TreeCheckBox("Mythril Skin", new String[]{"Gain +5% Base Resistance and", "become immune to knockback."}, new TreeCheckBox[]{getTcb().get(42)}, 2, TreeCheckBox.ArchetypeEnum.PALADIN, 6, SkillEnum.MYTHRIL_SKIN, 270, 942).yellowIcon()); //47
        getTcb().get(44).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(37), getTcb().get(45), getTcb().get(46)})});
        getTcb().get(45).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(35), getTcb().get(44)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(37), getTcb().get(46)})});

        new TreeIcon(22, 982, getPane()).up_down();
        new TreeIcon(166, 982, getPane()).up_down();
        new TreeIcon(274, 982, getPane()).right_left_down();
        getTcb().add(new TreeCheckBox("Armour Breaker", new String[]{"While Corrupted, losing 30% Health will make", "your next Uppercut reduce the enemies defense stats.", " ", "Effect: -30% Resistance to Enemies", "Duration: 8s"}, new TreeCheckBox[]{getTcb().get(44), getTcb().get(45)}, new TreeCheckBox[]{getTcb().get(25)}, TreeCheckBox.ArchetypeEnum.FALLEN, 0, 2, SkillEnum.ARMOUR_BREAKER, 52, 976).purpleIcon()); //48
        getTcb().add(new TreeCheckBox("Ambidextrous", new String[]{"Increase your chance to attack", "with Counter by +30%."}, new TreeCheckBox[]{getTcb().get(44), getTcb().get(45)}, new TreeCheckBox[]{getTcb().get(30)}, 1, SkillEnum.AMBIDEXTROUS, 128, 980).whiteIcon()); //50
        getTcb().add(new TreeCheckBox("Shield Strike", new String[]{"Deals damage around you for each mantle lost", "when Mantle of the Bovemists loses all charges.", " ", "Total Damage: 100% (of your DPS, per Mantle lost)", "(Damage 70%)", "(Thunder 30%)", "Area of Effect: 5 Blocks (Circle-Shaped)"}, new TreeCheckBox[]{getTcb().get(47)}, new TreeCheckBox[]{getTcb().get(24)}, TreeCheckBox.ArchetypeEnum.PALADIN, 0, 2, SkillEnum.SHIELD_STRIKE, 234, 978).yellowIcon()); //50
        getTcb().add(new TreeCheckBox("Sparkling Hope", new String[]{"Deal damage to all nearby enemies every", "time you heal 5% of your max health.", " ", "Total Damage: 60% (of your DPS)", "(Damage 40%)", "(Thunder 20%)", "Area of Effect: 6 Blocks (Circle-Shaped)"}, new TreeCheckBox[]{getTcb().get(47)}, 2, TreeCheckBox.ArchetypeEnum.PALADIN, 0, SkillEnum.SPARKLING_HOPE, 304, 976).purpleIcon()); //51

        new TreeIcon(58, 1018, getPane()).right_left_down();
        new TreeIcon(130, 1018, getPane()).right_left_down();
        new TreeIcon(202, 1018, getPane()).left_down();
        new TreeIcon(274, 1018, getPane()).up_down();
        getTcb().add(new TreeCheckBox("Massive Bash", new String[]{"While Corrupted, every 3% Health you", "lose will increase Bash's area of effect", "radius by +1. (Max +10)"}, new TreeCheckBox[]{getTcb().get(44)}, 2, TreeCheckBox.ArchetypeEnum.FALLEN, 7, SkillEnum.MASSIVE_BASH, 16, 1012).purpleIcon()); //52
        getTcb().add(new TreeCheckBox("Tempest", new String[]{"War Scream will ripple the ground and", "deal damage to enemies 3 times", "over large area.", " ", "Total Damage: 50% (of your DPS, per ripple)", "(Damage 30%)", "(Earth 10%)", "(Air 10%)", "Area of Effect: 12 Blocks (Circle-Shaped)"}, null, 2, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, 0, SkillEnum.TEMPEST, 90, 1014).yellowIcon()); //53
        getTcb().add(new TreeCheckBox("Spirit of the Rabbit", new String[]{"Reduce the Mana Cost of Charge", "and increase your Walk Speed by +20%", " ", "Charge Spell Cost: -5"}, new TreeCheckBox[]{getTcb().get(46)}, null, new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(44), getTcb().get(52), getTcb().get(53)})}, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, 5, 1, SkillEnum.SPIRIT_OF_THE_RABBIT, 164, 1016).whiteIcon()); //54
        getTcb().get(52).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(46), getTcb().get(53), getTcb().get(54)})});
        getTcb().get(53).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(46), getTcb().get(54)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(44), getTcb().get(52)})});

        new TreeIcon(22, 1054, getPane()).up_down();
        new TreeIcon(166, 1054, getPane()).up_down();
        new TreeIcon(238, 1054, getPane()).right_left();
        getTcb().add(new TreeCheckBox("Massacre", new String[]{"While Corrupted, if your Attack Speed (affected by IDs)", "is Slow of lower, hitting an enemy with your", "Main Attack will add +4% Corruption to", "your Corrupted Bar."}, new TreeCheckBox[]{getTcb().get(52), getTcb().get(53)}, 2, TreeCheckBox.ArchetypeEnum.FALLEN, 5, SkillEnum.MASSACRE, 54, 1050).yellowIcon()); //55
        getTcb().add(new TreeCheckBox("Axe Kick", new String[]{"Increases the damage and mana cost", "of Uppercut.", " ", "Uppercut Spell Cost: +15", "Total Damage: +150% (of your DPS)", "(Damage +150%)"}, new TreeCheckBox[]{getTcb().get(53), getTcb().get(54)}, 1, SkillEnum.AXE_KICK, 128, 1052).whiteIcon()); //56
        getTcb().add(new TreeCheckBox("Radiance", new String[]{"Bash will buff positive IDs of", "yourself and allies. (15s Cooldown)", " ", "Effect: +20% ID Effectiveness to Allies", "Duration: 8s"}, new TreeCheckBox[]{getTcb().get(54)}, 2, TreeCheckBox.ArchetypeEnum.PALADIN, 5, SkillEnum.RADIANCE, 196, 1048).purpleIcon()); //57
        getTcb().add(new TreeCheckBox("Cheaper Bash", new String[]{"Bash Spell Cost: -5"}, new TreeCheckBox[]{getTcb().get(50), getTcb().get(51)}, new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(54), getTcb().get(57)})}, 1, SkillEnum.CHEAPER_1ST_SP_COST, 272, 1052).whiteIcon()); //58
        getTcb().get(57).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(50), getTcb().get(58)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(51), getTcb().get(58)})});

        new TreeIcon(22, 1090, getPane()).up_down();
        new TreeIcon(166, 1090, getPane()).up_down();
        new TreeIcon(274, 1090, getPane()).up_down();

        new TreeIcon(130, 1126, getPane()).right_left();
        new TreeIcon(202, 1126, getPane()).left_down();
        new TreeIcon(274, 1126, getPane()).up_down();
        getTcb().add(new TreeCheckBox("Cheaper War Scream", new String[]{"War Scream Spell Cost: -5"}, new TreeCheckBox[]{getTcb().get(52)}, 1, SkillEnum.CHEAPER_4TH_SP_COST, 20, 1124).whiteIcon()); //59
        getTcb().add(new TreeCheckBox("Cyclone", new String[]{"After casting War Scream, envelop yourself with", "a vortex that damages nearby enemies every 0.5s.", " ", "Total Damage: 25% (of your DPS)", "(Damage 10%)", "(Fire 5%)", "(Air 10%)", "Duration: 20s", "Area of Effect: 4 Blocks (Circle-Shaped)"}, new TreeCheckBox[]{getTcb().get(54)}, 2, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, 0, SkillEnum.CYCLONE, 162, 1122).yellowIcon()); //60
        getTcb().add(new TreeCheckBox("Discombobulate", new String[]{"Hitting an enemy will increase the elemental", "damage you deal to them by +3 (Max 80)", "-5 of the bonus decays every second."}, new TreeCheckBox[]{getTcb().get(60)}, 2, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, 11, SkillEnum.DISCOMBOBULATE, 84, 1116).redIcon()); //61

        new TreeIcon(22, 1162, getPane()).up_down();
        new TreeIcon(238, 1162, getPane()).right_down();
        new TreeIcon(310, 1162, getPane()).left_down();
        getTcb().add(new TreeCheckBox("Thunderclap", new String[]{"Bash will cast at the player's position at", "double the speed and with an increased area of effect.", " ", "Total Damage: +25% (of your DPS, per Bash)", "(Thunder +25%)", "Area of Effect: +3 Blocks (Circle-Shaped)"}, new TreeCheckBox[]{getTcb().get(60)}, new TreeCheckBox[]{getTcb().get(0)}, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, 8, 2, SkillEnum.THUNDERCLAP, 198, 1158).yellowIcon()); //62
        getTcb().add(new TreeCheckBox("Second Chance", new String[]{"Keeps you alive with 30% of your health remaining", "after receiving a fatal blow. (10m Cooldown)"}, new TreeCheckBox[]{getTcb().get(58)}, 2, TreeCheckBox.ArchetypeEnum.PALADIN, 12, SkillEnum.SECOND_CHANCE, 264, 1152).redIcon()); //63

        new TreeIcon(58, 1198, getPane()).right_left();
        new TreeIcon(94, 1198, getPane()).right_left_down();
        new TreeIcon(130, 1198, getPane()).right_left();
        new TreeIcon(166, 1198, getPane()).left_down();
        new TreeIcon(238, 1198, getPane()).up_down();
        new TreeIcon(310, 1198, getPane()).up_down();
        getTcb().add(new TreeCheckBox("Blood Pact", new String[]{"If you do not have enough mana to", "cast a spell, spend health instead,", "(0.6% health per mana)"}, new TreeCheckBox[]{getTcb().get(59)}, 2, TreeCheckBox.ArchetypeEnum.FALLEN, 10, SkillEnum.BLOOD_PACT, 12, 1188).redIcon()); //64

        new TreeIcon(202, 1234, getPane()).right_left();
        getTcb().add(new TreeCheckBox("Haemorrhage", new String[]{"Reduce Blood Pact's health cost.", "(0.3% health per mana)"}, new TreeCheckBox[]{getTcb().get(64)}, new TreeCheckBox[]{getTcb().get(64)}, TreeCheckBox.ArchetypeEnum.FALLEN, 0, 2, SkillEnum.HAEMORRHAGE, 90, 1230).yellowIcon()); //65
        getTcb().add(new TreeCheckBox("Brink of Madness", new String[]{"You gain a +40% Resistance buff if your", "health is at 25% or less."}, new TreeCheckBox[]{getTcb().get(64)}, 2, SkillEnum.BRINK_OF_MADNESS, 160, 1228).purpleIcon()); //66
        getTcb().add(new TreeCheckBox("Cheaper Uppercut", new String[]{"Uppercut Spell Cost: -5"}, new TreeCheckBox[]{getTcb().get(63)}, new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(64), getTcb().get(66)})}, 1, SkillEnum.CHEAPER_3RD_SP_COST, 236, 1232).whiteIcon()); //67
        getTcb().add(new TreeCheckBox("Martyr", new String[]{"When you receive a fatal blow, all", "nearby allies become invisible.", " ", "Effect: 100% Invisibility to Allies", "Duration: 3s", "Area of Effect: 12 Blocks (Circle-Shaped)"}, new TreeCheckBox[]{getTcb().get(63)}, 2, TreeCheckBox.ArchetypeEnum.PALADIN, 0, SkillEnum.MARTYR, 306, 1230).yellowIcon()); //68
        getTcb().get(66).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(63), getTcb().get(67)})});

        setTreeUI();
    }
}
