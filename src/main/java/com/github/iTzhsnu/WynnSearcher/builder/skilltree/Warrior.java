package com.github.iTzhsnu.WynnSearcher.builder.skilltree;

import javax.swing.*;

public class Warrior extends TreeBase {
    public Warrior(JPanel p) {
        super(p, TreeCheckBox.ArchetypeEnum.FALLEN, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, TreeCheckBox.ArchetypeEnum.PALADIN, "Warrior");
        getTcb().add(new TreeCheckBox("Bash", new String[] {"Mana Cost: 40", "Total Damage: 150% (of your DPS)", "(Damage 130%)", "(Earth 20%)", "Area of Effect: 4 Blocks (Circle-Shaped)", "Range: 3 Blocks"}, null, 1, SkillEnum.BASH, posWidth(4, 6), posHeight(0, 6)).warriorIcon()); //0

        new TreeIcon(posWidth(4, 0), posHeight(1, 0), getPane()).up_down();

        new TreeIcon(posWidth(3, 0), posHeight(2, 0), getPane()).right_left();
        getTcb().add(new TreeCheckBox("Spear Proficiency I", new String[] {"Melee Damage: +5%", "Melee Range: +1 Blocks"}, new TreeCheckBox[] {getTcb().get(0)}, 1, SkillEnum.PROFICIENCY, posWidth(4, 2), posHeight(2, 2)).whiteIcon()); //1
        getTcb().add(new TreeCheckBox("Cheaper Bash", new String[] {"Bash Spell Cost: -10"}, new TreeCheckBox[]{getTcb().get(1)}, 1, SkillEnum.CHEAPER_1ST_SP_COST_10, posWidth(2, 2), posHeight(2, 2)).whiteIcon()); //2

        new TreeIcon(posWidth(4, 0), posHeight(3, 0), getPane()).up_down();

        getTcb().add(new TreeCheckBox("Double Bash", new String[] {"Bash will hit second", "time at a father range", " ", "Total Damage: -50% (of your DPS, per Bash)", "(Damage -50%)", "Range: +3 Blocks"}, new TreeCheckBox[]{getTcb().get(1)}, 1, SkillEnum.DOUBLE_BASH, posWidth(4, 4), posHeight(4, 4)).yellowIcon()); //3

        new TreeIcon(posWidth(4, 0), posHeight(5, 0), getPane()).up_down();

        new TreeIcon(posWidth(3, 0), posHeight(6, 0), getPane()).right_left();
        new TreeIcon(posWidth(5, 0), posHeight(6, 0), getPane()).right_left();
        getTcb().add(new TreeCheckBox("Charge", new String[]{"Mana Cost: 25"}, new TreeCheckBox[]{getTcb().get(3)} , 1, SkillEnum.CHARGE, posWidth(4, 6), posHeight(6, 6)).warriorIcon()); //4
        getTcb().add(new TreeCheckBox("Vehement", new String[] {"For every 1% or 1 Raw", "Melee Damage you have from items", "gain +2% Walk Speed (Max 20%)", " ", "Raw Damage: +5"}, new TreeCheckBox[]{getTcb().get(4)}, 1, TreeCheckBox.ArchetypeEnum.FALLEN, 0, SkillEnum.VEHEMENT, posWidth(2, 2), posHeight(6, 2)).whiteIcon()); //5
        getTcb().add(new TreeCheckBox("Tougher Skin", new String[] {"For every 1% or 1 Raw", "Health Regen you have from items", "gain +10 Health (Max 100)", " ", "Base Defense: +5%"}, new TreeCheckBox[]{getTcb().get(4)}, new TreeCheckBox[]{getTcb().get(5)}, 1, TreeCheckBox.ArchetypeEnum.PALADIN, 0, SkillEnum.TOUGHER_SKIN, posWidth(6, 2), posHeight(6, 2)).whiteIcon()); //6
        getTcb().get(5).setCantUse(new TreeCheckBox[]{getTcb().get(6)});

        new TreeIcon(posWidth(2, 0), posHeight(7, 0), getPane()).up_down();
        new TreeIcon(posWidth(6, 0), posHeight(7, 0), getPane()).up_down();

        new TreeIcon(posWidth(0, 0), posHeight(8, 0), getPane()).right_down();
        new TreeIcon(posWidth(1, 0), posHeight(8, 0), getPane()).right_left_down();
        new TreeIcon(posWidth(3, 0), posHeight(8, 0), getPane()).right_left();
        new TreeIcon(posWidth(5, 0), posHeight(8, 0), getPane()).right_left();
        new TreeIcon(posWidth(7, 0), posHeight(8, 0), getPane()).right_left();
        new TreeIcon(posWidth(8, 0), posHeight(8, 0), getPane()).left_down();
        getTcb().add(new TreeCheckBox("Uppercut", new String[] {"Mana Cost: 40", "Total Damage: 280% (of your DPS)", "(Damage 200%)", "(Earth 40%)", "(Thunder 40%)", "Area of Effect: 1-3 (Cone-Shaped)", "Range: 5 Blocks"}, new TreeCheckBox[]{getTcb().get(5)}, 1, SkillEnum.UPPERCUT, posWidth(2, 6), posHeight(8, 6)).warriorIcon()); //7
        getTcb().add(new TreeCheckBox("War Scream", new String[] {"Mana Cost: 30", "Total Damage: 100% (of your DPS)", "(Damage 50%)", "(Fire 50%)", "Effect: +20% Resistance to Allies", "Duration: 30s", "Area of Effects: 12 Blocks (Circle-Shaped)"}, new TreeCheckBox[]{getTcb().get(6)}, 1, SkillEnum.WAR_SCREAM, posWidth(6, 6), posHeight(8, 6)).warriorIcon()); //8
        getTcb().add(new TreeCheckBox("Cheaper Charge", new String[] {"Charge Spell Cost: -5"}, new TreeCheckBox[]{getTcb().get(7), getTcb().get(8)}, 1, SkillEnum.CHEAPER_2ND_SP_COST, posWidth(4, 2), posHeight(8, 2)).whiteIcon()); //9
        getTcb().get(7).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(6), getTcb().get(8), getTcb().get(9)})});
        getTcb().get(8).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(5), getTcb().get(7), getTcb().get(9)})});

        new TreeIcon(posWidth(0, 0), posHeight(9, 0), getPane()).up_down();
        new TreeIcon(posWidth(2, 0), posHeight(9, 0), getPane()).up_down();
        new TreeIcon(posWidth(4, 0), posHeight(9, 0), getPane()).up_down();
        new TreeIcon(posWidth(6, 0), posHeight(9, 0), getPane()).up_down();
        new TreeIcon(posWidth(8, 0), posHeight(9, 0), getPane()).up_down();
        getTcb().add(new TreeCheckBox("Heavy Impact", new String[]{"After using Charge, violently crash", "down unto the ground and damage.", " ", "Total Damage: 120% (of your DPS)", "(Damage 120%)", "Area of Effect: 4 Blocks"}, new TreeCheckBox[]{getTcb().get(7)}, 1, SkillEnum.HEAVY_IMPACT, posWidth(1, 4), posHeight(9, 4)).yellowIcon()); //10

        new TreeIcon(posWidth(3, 0), posHeight(10, 0), getPane()).right_left();
        new TreeIcon(posWidth(4, 0), posHeight(10, 0), getPane()).all();
        new TreeIcon(posWidth(5, 0), posHeight(10, 0), getPane()).right_left();
        getTcb().add(TreeTemplates.earthMastery(posWidth(0, 2), posHeight(10, 2), TreeCheckBox.ArchetypeEnum.FALLEN, new TreeCheckBox[]{getTcb().get(7)}, null)); //11
        getTcb().add(TreeTemplates.thunderMastery(posWidth(2, 2), posHeight(10, 2), TreeCheckBox.ArchetypeEnum.FALLEN, new TreeCheckBox[]{getTcb().get(7), getTcb().get(9)},null)); //12
        getTcb().add(TreeTemplates.airMastery(posWidth(6, 2), posHeight(10, 2), TreeCheckBox.ArchetypeEnum.BATTLE_MONK, new TreeCheckBox[]{getTcb().get(8), getTcb().get(9)}, new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(7), getTcb().get(12)})})); //13
        getTcb().add(TreeTemplates.fireMastery(posWidth(8, 2), posHeight(10, 2), TreeCheckBox.ArchetypeEnum.PALADIN, new TreeCheckBox[]{getTcb().get(8)}, null)); //14
        getTcb().get(12).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(8), getTcb().get(13)})});

        new TreeIcon(posWidth(0, 0), posHeight(11, 0), getPane()).up_down();
        new TreeIcon(posWidth(2, 0), posHeight(11, 0), getPane()).up_down();
        new TreeIcon(posWidth(6, 0), posHeight(11, 0), getPane()).up_down();
        new TreeIcon(posWidth(8, 0), posHeight(11, 0), getPane()).up_down();
        getTcb().add(TreeTemplates.waterMastery(posWidth(4, 2), posHeight(11, 2), TreeCheckBox.ArchetypeEnum.BATTLE_MONK, new TreeCheckBox[]{getTcb().get(9), getTcb().get(12), getTcb().get(13)}, null)); //15

        new TreeIcon(posWidth(1, 0), posHeight(12, 0), getPane()).right_left_down();
        new TreeIcon(posWidth(4, 0), posHeight(12, 0), getPane()).up_down();
        new TreeIcon(posWidth(7, 0), posHeight(12, 0), getPane()).right_left_down();
        getTcb().add(new TreeCheckBox("Quadruple Bash", new String[]{"Bash will cast 4 times consecutively", "at double speed.", " ", "Total Damage: -20% (of your DPS, per Bash)", "(Damage -20%)", "Range: +6 Blocks"}, new TreeCheckBox[]{getTcb().get(11)}, new TreeCheckBox[]{getTcb().get(0)}, TreeCheckBox.ArchetypeEnum.FALLEN, 0, 2, SkillEnum.QUADRUPLE_BASH, posWidth(0, 4), posHeight(12, 4)).yellowIcon()); //16
        getTcb().add(new TreeCheckBox("Fireworks", new String[]{"Mobs hit by Uppercut will explode", "mid-air and receive additional damage.", " ", "Total Damage: +100% (of your DPS)", "(Damage +80%)", "(Thunder +20%)"}, new TreeCheckBox[]{getTcb().get(12)}, null, new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(11), getTcb().get(16)})}, TreeCheckBox.ArchetypeEnum.FALLEN, 0, 2, SkillEnum.FIREWORKS, posWidth(2, 4), posHeight(12, 4)).yellowIcon()); //17
        getTcb().add(new TreeCheckBox("Flyby Jab", new String[]{"Damage enemies in your", "way when using Charge.", " ", "Total Damage: 120% (of your DPS)", "(Damage 100%)", "(Air 20%)", "Area of Effect: 2 Blocks"}, new TreeCheckBox[]{getTcb().get(13)}, 2, SkillEnum.FLYBY_JAB, posWidth(6, 4), posHeight(12, 4)).yellowIcon()); //18
        getTcb().add(new TreeCheckBox("Flaming Uppercut", new String[]{"Uppercut will light mobs on fire,", "dealing damage every seconds.", " ", "Total Damage: 50% (of your DPS)", "(Fire 50%)", "Duration: 3s"}, new TreeCheckBox[]{getTcb().get(14)}, new TreeCheckBox[]{getTcb().get(7)}, new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(13), getTcb().get(18)})}, TreeCheckBox.ArchetypeEnum.PALADIN, 0, 2, SkillEnum.FLAMING_UPPERCUT, posWidth(8, 4), posHeight(12, 4)).yellowIcon()); //19
        getTcb().get(16).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(12), getTcb().get(17)})});
        getTcb().get(18).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(14), getTcb().get(19)})});

        new TreeIcon(posWidth(1, 0), posHeight(13, 0), getPane()).up_down();
        getTcb().add(new TreeCheckBox("Half-Moon Swipe", new String[]{"Uppercut will deal a footsweep", "attack at a longer and wider angle.", " ", "Uppercut Spell Cost: -5", "Total Damage: -40% (of your DPS)", "(Damage -70%)", "(Water 30%)", "Range: 1.5 Blocks"}, new TreeCheckBox[]{getTcb().get(15)}, new TreeCheckBox[]{getTcb().get(7)}, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, 1, 2, SkillEnum.HALF_MOON_SWIPE, posWidth(4, 4), posHeight(13, 4)).yellowIcon()); //20
        getTcb().add(new TreeCheckBox("Iron Lungs", new String[]{"War Scream will deal more damage.", " ", "Total Damage: +60% (of your DPS, for War Scream)", "(Damage +30%)", "(Air +30%)", " ", "Total Damage: +30% (of your DPS, for Air Shout)", "(Damage +15%)", "(Air +15%)"}, new TreeCheckBox[]{getTcb().get(18), getTcb().get(19)}, new TreeCheckBox[]{getTcb().get(8)}, TreeCheckBox.ArchetypeEnum.PALADIN, 0, 1, SkillEnum.IRON_LUNGS, posWidth(7, 2), posHeight(13, 2)).whiteIcon()); //21

        new TreeIcon(posWidth(1, 0), posHeight(14, 0), getPane()).up_down();
        new TreeIcon(posWidth(4, 0), posHeight(14, 0), getPane()).up_down();
        new TreeIcon(posWidth(7, 0), posHeight(14, 0), getPane()).up_down();

        new TreeIcon(posWidth(1, 0), posHeight(15, 0), getPane()).up_down();
        new TreeIcon(posWidth(3, 0), posHeight(15, 0), getPane()).right_left_down();
        getTcb().add(new TreeCheckBox("Air Shout", new String[]{"War Scream will fire a projectile that can", "go through walls and deal damage multiple times.", " ", "Total Damage: 50%", "(Neutral 40%)", "(Air 10%)", "Area of Effect: 2 Blocks (Circle-Shaped)", "Range: 32 Blocks"}, new TreeCheckBox[]{getTcb().get(20)}, new TreeCheckBox[]{getTcb().get(8)}, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, 0, 2, SkillEnum.AIR_SHOUT, posWidth(4, 4), posHeight(15, 4)).yellowIcon()); //22
        getTcb().add(new TreeCheckBox("Generalist", new String[]{"After casting 3 different spells", "in a row, your next spell cost 1 Mana."}, new TreeCheckBox[]{getTcb().get(22)}, 2, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, 3, SkillEnum.GENERALIST, posWidth(2, 10), posHeight(15, 10)).redIcon()); //23
        getTcb().add(new TreeCheckBox("Mantle of the Bovemists", new String[]{"When casting War Scream, create a holy", "shield around you that reduces all incoming", "damage by -70% for 3 hits. (25s Cooldown)"}, new TreeCheckBox[]{getTcb().get(21)}, new TreeCheckBox[]{getTcb().get(8)}, TreeCheckBox.ArchetypeEnum.PALADIN, 3, 2, SkillEnum.MANTLE_OF_THE_BOVEMISTS, posWidth(7, 10), posHeight(15, 10)).redIcon()); //24

        new TreeIcon(posWidth(0, 0), posHeight(16, 0), getPane()).right_down();
        new TreeIcon(posWidth(3, 0), posHeight(16, 0), getPane()).up_down();
        new TreeIcon(posWidth(7, 0), posHeight(16, 0), getPane()).up_down();
        getTcb().add(new TreeCheckBox("Bak'al's Grasp", new String[]{"Casting War Scream will make you", "Corrupted (15s Cooldown)", " ", "While Corrupted you lose the ability", "to heal, and 2% health you lose will", "grant you +4 Raw Damage (Max 120)"}, new TreeCheckBox[]{getTcb().get(16), getTcb().get(17)}, new TreeCheckBox[]{getTcb().get(8)}, TreeCheckBox.ArchetypeEnum.FALLEN, 2, 2, SkillEnum.BAKALS_GRASP, posWidth(1, 10), posHeight(16, 10)).redIcon()); //25

        new TreeIcon(posWidth(1, 0), posHeight(17, 0), getPane()).right_left_down();
        new TreeIcon(posWidth(2, 0), posHeight(17, 0), getPane()).right_left();
        new TreeIcon(posWidth(4, 0), posHeight(17, 0), getPane()).right_left_down();
        new TreeIcon(posWidth(6, 0), posHeight(17, 0), getPane()).right_left();
        new TreeIcon(posWidth(8, 0), posHeight(17, 0), getPane()).left_down();
        getTcb().add(new TreeCheckBox("Spear Proficiency 2", new String[]{"Melee Damage: +5%", "Melee Range: +1 Blocks"}, new TreeCheckBox[]{getTcb().get(25)}, 1, SkillEnum.PROFICIENCY, posWidth(0, 2), posHeight(17, 2)).whiteIcon()); //26
        getTcb().add(new TreeCheckBox("Cheaper Uppercut", new String[]{"Uppercut Spell Cost: -5"}, new TreeCheckBox[]{getTcb().get(22)}, 1, SkillEnum.CHEAPER_3RD_SP_COST, posWidth(3, 2), posHeight(17, 2)).whiteIcon()); //27
        getTcb().add(new TreeCheckBox("Aerodynamics", new String[]{"During Charge, you can", "steer and change direction."}, null, 2, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, 0, SkillEnum.AERODYNAMICS, posWidth(5, 4), posHeight(17, 4)).yellowIcon()); //28
        getTcb().add(new TreeCheckBox("Provoke", new String[]{"Mobs damaged by War Scream will target", "only you for at least 8s. (10s Cooldown)", " ", "War Scream Spell Cost: -5"}, new TreeCheckBox[]{getTcb().get(24)}, new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(25), getTcb().get(26), getTcb().get(27), getTcb().get(28)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(22), getTcb().get(27), getTcb().get(28)})}, 2, SkillEnum.PROVOKE, posWidth(7, 4), posHeight(17, 4)).yellowIcon()); //29
        getTcb().get(26).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(24), getTcb().get(27), getTcb().get(28), getTcb().get(29)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(22), getTcb().get(27)})});
        getTcb().get(27).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(25), getTcb().get(26)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(24), getTcb().get(28), getTcb().get(29)})});
        getTcb().get(28).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(25), getTcb().get(26), getTcb().get(27)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(22), getTcb().get(27)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(24), getTcb().get(29)})});

        new TreeIcon(posWidth(0, 0), posHeight(18, 0), getPane()).up_down();
        new TreeIcon(posWidth(3, 0), posHeight(18, 0), getPane()).up_down();
        new TreeIcon(posWidth(8, 0), posHeight(18, 0), getPane()).up_down();
        getTcb().add(new TreeCheckBox("Precise Strikes", new String[]{"Critical Damage: +15%"}, new TreeCheckBox[]{getTcb().get(26), getTcb().get(27)}, 1, SkillEnum.PRECISE_STRIKES, posWidth(1, 2), posHeight(18, 2)).whiteIcon()); //30
        getTcb().add(new TreeCheckBox("Counter", new String[]{"when dodding nearby enemy attack, get", "30% change to instaly attack back.", " ", "Total Damage: 100% (of your DPS)", "(Damage 60%)", "(Thunder 20%)", "(Air 20%)"}, new TreeCheckBox[]{getTcb().get(27), getTcb().get(28)}, 2, SkillEnum.COUNTER, posWidth(4, 4), posHeight(18, 4)).yellowIcon()); //31

        new TreeIcon(posWidth(0, 0), posHeight(19, 0), getPane()).up_down();
        new TreeIcon(posWidth(3, 0), posHeight(19, 0), getPane()).up_down();
        new TreeIcon(posWidth(8, 0), posHeight(19, 0), getPane()).up_down();

        new TreeIcon(posWidth(2, 0), posHeight(20, 0), getPane()).right_down();
        new TreeIcon(posWidth(4, 0), posHeight(20, 0), getPane()).right_left_down();
        new TreeIcon(posWidth(5, 0), posHeight(20, 0), getPane()).right_left_down();
        new TreeIcon(posWidth(7, 0), posHeight(20, 0), getPane()).right_left();
        getTcb().add(new TreeCheckBox("Enraged Blow", new String[]{"While Corrupted, every 1% of", "Health you lose will increase your", "damage by +1.5% (Max 80%)"}, new TreeCheckBox[]{getTcb().get(26)}, new TreeCheckBox[]{getTcb().get(25)}, TreeCheckBox.ArchetypeEnum.FALLEN, 0, 2, SkillEnum.ENRAGED_BLOW, posWidth(0, 6), posHeight(20, 6)).purpleIcon()); //32
        getTcb().add(new TreeCheckBox("Flying Kick", new String[]{"Hitting mobs with Charge will halt your", "momentum and knock them away.", " ", "Total Damage: 200% (of your DPS)", "(Damage 150%)", "(Water 20%)", "(Air 30%)"}, new TreeCheckBox[]{getTcb().get(27)}, 2, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, 1, SkillEnum.FLYING_KICK, posWidth(3, 4), posHeight(20, 4)).yellowIcon()); //33
        getTcb().add(new TreeCheckBox("Stronger Mantle", new String[]{"add +2 additional charges", "to Mantle of the Bovemists", "and reduce its cooldown by -5s."}, null, new TreeCheckBox[]{getTcb().get(24)}, TreeCheckBox.ArchetypeEnum.PALADIN, 0, 1, SkillEnum.STRONGER_MANTLE, posWidth(6, 2), posHeight(20, 2)).whiteIcon()); //34
        getTcb().add(new TreeCheckBox("Manachism", new String[]{"If you receive a hit that's less than 5%", "of your max HP, gain 10 Mana. (1s Cooldown)"}, new TreeCheckBox[]{getTcb().get(29)}, null, new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(27), getTcb().get(33), getTcb().get(34)})}, TreeCheckBox.ArchetypeEnum.PALADIN, 3, 2, SkillEnum.MANACHISM, posWidth(8, 6), posHeight(20, 6)).purpleIcon()); //35
        getTcb().get(33).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(29), getTcb().get(34), getTcb().get(35)})});
        getTcb().get(34).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(27), getTcb().get(33)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(29), getTcb().get(35)})});

        new TreeIcon(posWidth(0, 0), posHeight(21, 0), getPane()).up_down();
        new TreeIcon(posWidth(2, 0), posHeight(21, 0), getPane()).up_down();
        new TreeIcon(posWidth(4, 0), posHeight(21, 0), getPane()).up_down();
        new TreeIcon(posWidth(8, 0), posHeight(21, 0), getPane()).up_down();
        getTcb().add(new TreeCheckBox("Ambidextrous", new String[]{"Increase your chance to attack", "with Counter by +30%."}, new TreeCheckBox[]{getTcb().get(33), getTcb().get(34)}, new TreeCheckBox[]{getTcb().get(31)}, 1, SkillEnum.AMBIDEXTROUS, posWidth(5, 2), posHeight(21, 2)).whiteIcon()); //36

        new TreeIcon(posWidth(1, 0), posHeight(22, 0), getPane()).right_left_down();
        new TreeIcon(posWidth(5, 0), posHeight(22, 0), getPane()).right_left_down();
        new TreeIcon(posWidth(7, 0), posHeight(22, 0), getPane()).right_left_down();
        getTcb().add(new TreeCheckBox("Boiling Blood", new String[]{"Bash will leave a puddle of boiling blood", "after its fast first explosion, slowing down and", "damaging enemies on it every 0.4 seconds.", " ", "Total Damage: 30% (of your DPS)", "(Damage 25%)", "(Fire 5%)", "Effect: 20% Slowness to Enemies", "Duration: 3s"}, new TreeCheckBox[]{getTcb().get(32)}, 2, SkillEnum.BOILING_BLOOD, posWidth(0, 4), posHeight(22, 4)).yellowIcon()); //37
        getTcb().add(new TreeCheckBox("Ragnarokkr", new String[]{"War Scream becomes defeating, increasing its", "range and granting players a damage bonus.", " ", "War Scream Spell Cost: +10", "Effect: 20% Damage Bonus to Players", "Duration: +15s"}, new TreeCheckBox[]{getTcb().get(33)}, new TreeCheckBox[]{getTcb().get(8)}, new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(32), getTcb().get(37)})}, TreeCheckBox.ArchetypeEnum.FALLEN, 0, 2, SkillEnum.RAGNAROKKR, posWidth(2, 6), posHeight(22, 6)).purpleIcon()); //38
        getTcb().add(new TreeCheckBox("Cheaper War Scream", new String[]{"War Scream Spell Cost: -5"}, new TreeCheckBox[]{getTcb().get(33), getTcb().get(34)}, 1, SkillEnum.CHEAPER_4TH_SP_COST, posWidth(4, 2), posHeight(22, 2)).whiteIcon()); //39
        getTcb().add(new TreeCheckBox("Burning Heart", new String[]{"For every 100 Health Bonus you have from", "items, gain +2% Fire Damage. (Max 100%)"}, null, 1, TreeCheckBox.ArchetypeEnum.PALADIN, 0, SkillEnum.BURNING_HEART, posWidth(6, 2), posHeight(22, 2)).whiteIcon()); //40
        getTcb().add(new TreeCheckBox("Stronger Bash", new String[]{"Increase the damage of Bash.", " ", "Total Damage: +30% (of your DPS)", "(Damage +30%)"}, new TreeCheckBox[]{getTcb().get(35)}, new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(33), getTcb().get(39), getTcb().get(40)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(34), getTcb().get(39), getTcb().get(40)})}, 1, SkillEnum.STRONGER_BASH, posWidth(8, 2), posHeight(22, 2)).whiteIcon()); //41
        getTcb().get(37).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(33), getTcb().get(38)})}); //Boiling Blood
        getTcb().get(39).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(35), getTcb().get(40), getTcb().get(41)})}); //Cheaper War Scream
        getTcb().get(40).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(33), getTcb().get(39)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(34), getTcb().get(39)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(35), getTcb().get(41)})}); //Burning Heart

        new TreeIcon(posWidth(0, 0), posHeight(23, 0), getPane()).up_down();
        new TreeIcon(posWidth(2, 0), posHeight(23, 0), getPane()).up_down();
        new TreeIcon(posWidth(4, 0), posHeight(23, 0), getPane()).up_down();
        getTcb().add(new TreeCheckBox("Intoxicating Blood", new String[]{"After leaving Corrupted, gain 5%", "of the health lost back for each", "enemy killed while Corrupted."}, new TreeCheckBox[]{getTcb().get(37), getTcb().get(38)}, new TreeCheckBox[]{getTcb().get(25)}, TreeCheckBox.ArchetypeEnum.FALLEN, 5, 2, SkillEnum.INTOXICATING_BLOOD, posWidth(1, 4), posHeight(23, 4)).yellowIcon()); //42
        getTcb().add(new TreeCheckBox("Collide", new String[]{"Enemies thrown into walls from Flying Kick", "will explode and receive additional damage.", " ", "Total Damage: 200% (of your DPS)", "(Damage 150%)", "(Fire 50%)", "Area of Effect: 4 Blocks"}, new TreeCheckBox[]{getTcb().get(39), getTcb().get(40)}, new TreeCheckBox[]{getTcb().get(33)}, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, 4, 2, SkillEnum.COLLIDE, posWidth(5, 4), posHeight(23, 4)).yellowIcon()); //43
        getTcb().add(new TreeCheckBox("Rejuvenating Skin", new String[]{"Regain back 30% of the damage", "you take as healing 30s."}, new TreeCheckBox[]{getTcb().get(40), getTcb().get(41)}, 2, TreeCheckBox.ArchetypeEnum.PALADIN, 5, SkillEnum.REJUVENATING_SKIN, posWidth(7, 10), posHeight(23, 10)).redIcon()); //44

        new TreeIcon(posWidth(0, 0), posHeight(24, 0), getPane()).up_down();
        new TreeIcon(posWidth(4, 0), posHeight(24, 0), getPane()).up_down();
        new TreeIcon(posWidth(7, 0), posHeight(24, 0), getPane()).up_down();
        getTcb().add(new TreeCheckBox("Comet", new String[]{"After being hit by Fireworks, enemies will crash", "unto the ground and receive more damage.", " ", "Total Damage: +100% (of your DPS)", "(Damage +80%)", "(Earth +20%)"}, new TreeCheckBox[]{getTcb().get(38)}, new TreeCheckBox[]{getTcb().get(17)}, TreeCheckBox.ArchetypeEnum.FALLEN, 0, 2, SkillEnum.COMET, posWidth(2, 4), posHeight(24, 4)).yellowIcon()); //45

        new TreeIcon(posWidth(0, 0), posHeight(25, 0), getPane()).up_down();
        new TreeIcon(posWidth(4, 0), posHeight(25, 0), getPane()).up_down();
        new TreeIcon(posWidth(7, 0), posHeight(25, 0), getPane()).up_down();

        new TreeIcon(posWidth(1, 0), posHeight(26, 0), getPane()).right_left_down();
        new TreeIcon(posWidth(3, 0), posHeight(26, 0), getPane()).right_left();
        new TreeIcon(posWidth(6, 0), posHeight(26, 0), getPane()).right_down();
        new TreeIcon(posWidth(8, 0), posHeight(26, 0), getPane()).left_down();
        getTcb().add(new TreeCheckBox("Uncontainable Corruption", new String[]{"Reduces the cooldown of Bak'al's Grasp", "by -5s, and increases the raw damage", "gained for every 2% of health lost by +1."}, new TreeCheckBox[]{getTcb().get(37)}, new TreeCheckBox[]{getTcb().get(25)}, 1, SkillEnum.UNCONTAINABLE_CORRUPTION, posWidth(0, 2), posHeight(26, 2)).whiteIcon()); //46
        getTcb().add(new TreeCheckBox("Radiant Devotee", new String[]{"For every 4% Reflection you have from", "items, gain +1/5s Mana Regen. (Max 10/5s)"}, null, 1, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, 1, SkillEnum.RADIANT_DEVOTEE, posWidth(2, 2), posHeight(26, 2)).whiteIcon()); //47
        getTcb().add(new TreeCheckBox("Whirlwind Strike", new String[]{"Uppercut will create a strong gust of air,", "launching you upward with enemies.", " ", "Total Damage: +10% (of your DPS)", "(Damage -20%)", "(Air +30%)", "Area of Effect: Circle-Shaped", "Range: 1 Blocks"}, new TreeCheckBox[]{getTcb().get(39)}, new TreeCheckBox[]{getTcb().get(7)}, new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(37), getTcb().get(46), getTcb().get(47)})}, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, 6, 2, SkillEnum.WHIRLWIND_STRIKE, posWidth(4, 10), posHeight(26, 10)).redIcon()); //48
        getTcb().add(new TreeCheckBox("Mythril Skin", new String[]{"Gain +5% Base Resistance and", "become immune to knockback."}, new TreeCheckBox[]{getTcb().get(44)}, 2, TreeCheckBox.ArchetypeEnum.PALADIN, 6, SkillEnum.MYTHRIL_SKIN, posWidth(7, 4), posHeight(26, 4)).yellowIcon()); //49
        getTcb().get(46).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(39), getTcb().get(47), getTcb().get(48)})});
        getTcb().get(47).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(37), getTcb().get(46)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(39), getTcb().get(48)})});

        new TreeIcon(posWidth(0, 0), posHeight(27, 0), getPane()).up_down();
        new TreeIcon(posWidth(4, 0), posHeight(27, 0), getPane()).up_down();
        new TreeIcon(posWidth(7, 0), posHeight(27, 0), getPane()).right_left_down();
        getTcb().add(new TreeCheckBox("Armour Breaker", new String[]{"While Corrupted, losing 30% Health will make", "your next Uppercut reduce the enemies defense stats.", " ", "Effect: -30% Resistance to Enemies", "Duration: 8s"}, new TreeCheckBox[]{getTcb().get(46), getTcb().get(47)}, new TreeCheckBox[]{getTcb().get(25)}, TreeCheckBox.ArchetypeEnum.FALLEN, 0, 2, SkillEnum.ARMOUR_BREAKER, posWidth(1, 6), posHeight(27, 6)).purpleIcon()); //50
        getTcb().add(new TreeCheckBox("Shield Strike", new String[]{"Deals damage around you for each mantle lost", "when Mantle of the Bovemists loses all charges.", " ", "Total Damage: 100% (of your DPS, per Mantle lost)", "(Damage 70%)", "(Thunder 30%)", "Area of Effect: 5 Blocks (Circle-Shaped)"}, new TreeCheckBox[]{getTcb().get(49)}, new TreeCheckBox[]{getTcb().get(24)}, TreeCheckBox.ArchetypeEnum.PALADIN, 0, 2, SkillEnum.SHIELD_STRIKE, posWidth(6, 4), posHeight(27, 4)).yellowIcon()); //51
        getTcb().add(new TreeCheckBox("Sparkling Hope", new String[]{"Deal damage to all nearby enemies every", "time you heal 5% of your max health.", " ", "Total Damage: 60% (of your DPS)", "(Damage 40%)", "(Thunder 20%)", "Area of Effect: 6 Blocks (Circle-Shaped)"}, new TreeCheckBox[]{getTcb().get(49)}, 2, TreeCheckBox.ArchetypeEnum.PALADIN, 0, SkillEnum.SPARKLING_HOPE, posWidth(8, 6), posHeight(27, 6)).purpleIcon()); //52

        new TreeIcon(posWidth(1, 0), posHeight(28, 0), getPane()).right_left_down();
        new TreeIcon(posWidth(3, 0), posHeight(28, 0), getPane()).right_left_down();
        new TreeIcon(posWidth(5, 0), posHeight(28, 0), getPane()).left_down();
        new TreeIcon(posWidth(7, 0), posHeight(28, 0), getPane()).up_down();
        getTcb().add(new TreeCheckBox("Massive Bash", new String[]{"While Corrupted, every 3% Health you", "lose will increase Bash's area of effect", "radius by +1. (Max +10)"}, new TreeCheckBox[]{getTcb().get(46)}, 2, TreeCheckBox.ArchetypeEnum.FALLEN, 7, SkillEnum.MASSIVE_BASH, posWidth(0, 6), posHeight(28, 6)).purpleIcon()); //53
        getTcb().add(new TreeCheckBox("Tempest", new String[]{"War Scream will ripple the ground and", "deal damage to enemies 3 times", "over large area.", " ", "Total Damage: 50% (of your DPS, per ripple)", "(Damage 30%)", "(Earth 10%)", "(Air 10%)", "Area of Effect: 12 Blocks (Circle-Shaped)"}, null, 2, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, 0, SkillEnum.TEMPEST, posWidth(2, 4), posHeight(28, 4)).yellowIcon()); //54
        getTcb().add(new TreeCheckBox("Spirit of the Rabbit", new String[]{"Reduce the Mana Cost of Charge", "and increase your Walk Speed by +20%", " ", "Charge Spell Cost: -5"}, new TreeCheckBox[]{getTcb().get(48)}, null, new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(46), getTcb().get(53), getTcb().get(54)})}, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, 5, 1, SkillEnum.SPIRIT_OF_THE_RABBIT, posWidth(4, 2), posHeight(28, 2)).whiteIcon()); //55
        getTcb().get(53).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(48), getTcb().get(54), getTcb().get(55)})});
        getTcb().get(54).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(46), getTcb().get(53)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(48), getTcb().get(55)})});

        new TreeIcon(posWidth(0, 0), posHeight(29, 0), getPane()).up_down();
        new TreeIcon(posWidth(4, 0), posHeight(29, 0), getPane()).up_down();
        new TreeIcon(posWidth(6, 0), posHeight(29, 0), getPane()).right_left();
        getTcb().add(new TreeCheckBox("Massacre", new String[]{"While Corrupted, if your Attack Speed (affected by IDs)", "is Slow of lower, hitting an enemy with your", "Main Attack will add +4% Corruption to", "your Corrupted Bar."}, new TreeCheckBox[]{getTcb().get(53), getTcb().get(54)}, 2, TreeCheckBox.ArchetypeEnum.FALLEN, 5, SkillEnum.MASSACRE, posWidth(1, 4), posHeight(29, 4)).yellowIcon()); //56
        getTcb().add(new TreeCheckBox("Axe Kick", new String[]{"Increases the damage and mana cost", "of Uppercut.", " ", "Uppercut Spell Cost: +15", "Total Damage: +150% (of your DPS)", "(Damage +150%)"}, new TreeCheckBox[]{getTcb().get(54), getTcb().get(55)}, 1, SkillEnum.AXE_KICK, posWidth(3, 2), posHeight(29, 2)).whiteIcon()); //57
        getTcb().add(new TreeCheckBox("Radiance", new String[]{"Bash will buff positive IDs of", "yourself and allies. (15s Cooldown)", " ", "Effect: +20% ID Effectiveness to Allies", "Duration: 8s"}, new TreeCheckBox[]{getTcb().get(55)}, 2, TreeCheckBox.ArchetypeEnum.PALADIN, 3, SkillEnum.RADIANCE, posWidth(5, 6), posHeight(29, 6)).purpleIcon()); //58
        getTcb().add(new TreeCheckBox("Cheaper Bash", new String[]{"Bash Spell Cost: -5"}, new TreeCheckBox[]{getTcb().get(51), getTcb().get(52)}, new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(55), getTcb().get(58)})}, 1, SkillEnum.CHEAPER_1ST_SP_COST, posWidth(7, 2), posHeight(29, 2)).whiteIcon()); //59
        getTcb().get(58).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(51), getTcb().get(59)}), new SpPrevious(new TreeCheckBox[]{getTcb().get(52), getTcb().get(59)})});

        new TreeIcon(posWidth(0, 0), posHeight(30, 0), getPane()).up_down();
        new TreeIcon(posWidth(4, 0), posHeight(30, 0), getPane()).up_down();
        new TreeIcon(posWidth(7, 0), posHeight(30, 0), getPane()).up_down();

        new TreeIcon(posWidth(0, 0), posHeight(31, 0), getPane()).up_right_down();
        new TreeIcon(posWidth(1, 0), posHeight(31, 0), getPane()).left_down();
        new TreeIcon(posWidth(3, 0), posHeight(31, 0), getPane()).right_left();
        new TreeIcon(posWidth(5, 0), posHeight(31, 0), getPane()).left_down();
        new TreeIcon(posWidth(7, 0), posHeight(31, 0), getPane()).up_down();
        getTcb().add(new TreeCheckBox("Cheaper War Scream", new String[]{"War Scream Spell Cost: -5"}, new TreeCheckBox[]{getTcb().get(53)}, 1, SkillEnum.CHEAPER_4TH_SP_COST, posWidth(0, 2), posHeight(30, 2)).whiteIcon()); //60
        getTcb().add(new TreeCheckBox("Cyclone", new String[]{"After casting War Scream, envelop yourself with", "a vortex that damages nearby enemies every 0.5s.", " ", "Total Damage: 25% (of your DPS)", "(Damage 10%)", "(Fire 5%)", "(Air 10%)", "Duration: 20s", "Area of Effect: 4 Blocks (Circle-Shaped)"}, new TreeCheckBox[]{getTcb().get(55)}, 2, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, 0, SkillEnum.CYCLONE, posWidth(4, 4), posHeight(31, 4)).yellowIcon()); //61
        getTcb().add(new TreeCheckBox("Discombobulate", new String[]{"Hitting an enemy will increase the elemental", "damage you deal to them by +5 (Max 100)", "-5 of the bonus decays every second."}, new TreeCheckBox[]{getTcb().get(61)}, 2, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, 11, SkillEnum.DISCOMBOBULATE, posWidth(2, 10), posHeight(31, 10)).redIcon()); //62

        new TreeIcon(posWidth(0, 0), posHeight(32, 0), getPane()).up_down();
        new TreeIcon(posWidth(6, 0), posHeight(32, 0), getPane()).right_down();
        new TreeIcon(posWidth(8, 0), posHeight(32, 0), getPane()).left_down();
        getTcb().add(new TreeCheckBox("Better Enraged Blow", new String[]{"Increases the cap of Enraged Blow by +60%"}, new TreeCheckBox[]{getTcb().get(60)}, new TreeCheckBox[]{getTcb().get(32)}, 1, SkillEnum.BETTER_ENRAGED_BLOW, posWidth(1, 2), posHeight(32, 2)).whiteIcon()); //63
        getTcb().add(new TreeCheckBox("Thunderclap", new String[]{"Bash will cast at the player's position at", "double the speed and with an increased area of effect.", " ", "Total Damage: +25% (of your DPS, per Bash)", "(Thunder +25%)", "Area of Effect: +3 Blocks (Circle-Shaped)"}, new TreeCheckBox[]{getTcb().get(61)}, new TreeCheckBox[]{getTcb().get(0)}, TreeCheckBox.ArchetypeEnum.BATTLE_MONK, 8, 2, SkillEnum.THUNDERCLAP, posWidth(5, 4), posHeight(32, 4)).yellowIcon()); //64
        getTcb().add(new TreeCheckBox("Second Chance", new String[]{"Keeps you alive with 30% of your health remaining", "after receiving a fatal blow. (10m Cooldown)"}, new TreeCheckBox[]{getTcb().get(59)}, 2, TreeCheckBox.ArchetypeEnum.PALADIN, 12, SkillEnum.SECOND_CHANCE, posWidth(7, 10), posHeight(32, 10)).redIcon()); //65

        new TreeIcon(posWidth(1, 0), posHeight(33, 0), getPane()).right_left();
        new TreeIcon(posWidth(2, 0), posHeight(33, 0), getPane()).right_left_down();
        new TreeIcon(posWidth(3, 0), posHeight(33, 0), getPane()).right_left();
        new TreeIcon(posWidth(4, 0), posHeight(33, 0), getPane()).left_down();
        new TreeIcon(posWidth(6, 0), posHeight(33, 0), getPane()).up_down();
        new TreeIcon(posWidth(8, 0), posHeight(33, 0), getPane()).up_down();
        getTcb().add(new TreeCheckBox("Blood Pact", new String[]{"If you do not have enough mana to", "cast a spell, spend health instead,", "(0.4% health per mana)"}, new TreeCheckBox[]{getTcb().get(60)}, 2, TreeCheckBox.ArchetypeEnum.FALLEN, 10, SkillEnum.BLOOD_PACT, posWidth(0, 10), posHeight(33, 10)).redIcon()); //66

        new TreeIcon(posWidth(5, 0), posHeight(34, 0), getPane()).right_left();
        getTcb().add(new TreeCheckBox("Haemorrhage", new String[]{"Reduce Blood Pact's health cost.", "(0.25% health per mana)"}, new TreeCheckBox[]{getTcb().get(66)}, new TreeCheckBox[]{getTcb().get(66)}, TreeCheckBox.ArchetypeEnum.FALLEN, 0, 1, SkillEnum.HAEMORRHAGE, posWidth(2, 2), posHeight(34, 2)).whiteIcon()); //67
        getTcb().add(new TreeCheckBox("Brink of Madness", new String[]{"You gain a +40% Resistance buff if your", "health is at 25% or less."}, new TreeCheckBox[]{getTcb().get(66)}, 2, SkillEnum.BRINK_OF_MADNESS, posWidth(4, 6), posHeight(34, 6)).purpleIcon()); //68
        getTcb().add(new TreeCheckBox("Cheaper Uppercut", new String[]{"Uppercut Spell Cost: -5"}, new TreeCheckBox[]{getTcb().get(65)}, new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(66), getTcb().get(68)})}, 1, SkillEnum.CHEAPER_3RD_SP_COST, posWidth(6, 2), posHeight(34, 2)).whiteIcon()); //69
        getTcb().add(new TreeCheckBox("Martyr", new String[]{"When you receive a fatal blow, all", "nearby allies become invisible.", " ", "Effect: 100% Invisibility to Allies", "Duration: 3s", "Area of Effect: 12 Blocks (Circle-Shaped)"}, new TreeCheckBox[]{getTcb().get(65)}, 2, TreeCheckBox.ArchetypeEnum.PALADIN, 0, SkillEnum.MARTYR, posWidth(8, 4), posHeight(34, 4)).yellowIcon()); //70
        getTcb().get(68).setSpPrevious(new SpPrevious[]{new SpPrevious(new TreeCheckBox[]{getTcb().get(65), getTcb().get(69)})});

        setTreeUI();
    }
}
