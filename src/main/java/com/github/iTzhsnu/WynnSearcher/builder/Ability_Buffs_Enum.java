package com.github.iTzhsnu.WynnSearcher.builder;

public enum Ability_Buffs_Enum {
    //Sliders
    //All
    MARKED(0),

    //Warrior
    CORRUPTED(1),
    DISCOMBOBULATE(2),

    //Assassin
    CLONES(3),
    NIGHTCLOAK_KNIFE(4),

    //Archer
    FOCUS(5),
    PATIENT_HUNTER(6),
    DECIMATOR(7),

    //Mage
    WINDED(8),

    //Shaman
    SHEPHERD(9),

    //Boxes
    //All
    WAR_SCREAM(0),
    RAGNAROKKR(1),
    RADIANCE(2),
    FORTITUDE(3),
    VENGEFUL_SPIRIT(4),
    ARMOUR_BREAKER(5),
    MARKED_10PERCENT(6),
    COURSING_RESTRAINTS(7),
    IVYROOT_MAMBA(8),
    HAUNTING_MEMORY_LUNATIC(9),
    HAUNTING_MEMORY_FANATIC(10),
    CHANT_OF_THE_LUNATIC(11),
    SAVIOURS_SACRIFICE(12),

    //Warrior
    MANTLE(13),
    BRINK_OF_MADNESS(14),
    SACRED_SURGE(15),

    //Assassin
    BACKSTAB(16),
    SURPRISE_STRIKE(17),
    DELIRIOUS_GAS(18),
    MIRROR_IMAGE(19),
    SATSUJIN(20),
    FLOW_STATE(21),
    PARRY(22),
    DISSOLUTION(23),

    //Archer
    INITIATOR(24),

    //Mage

    //Shaman
    MASK_OF_THE_LUNATIC(25),
    MASK_OF_THE_FANATIC(26),
    MASK_OF_THE_COWARD(27),
    CHANT_OF_THE_FANATIC(28),
    BULLWHIP(29),
    INVIGORATING_WAVE(30),
    SACRIFICIAL_SHRINE(31),
    AWAKENED(32)
    ;

    private final int pos;
    Ability_Buffs_Enum(int pos) {
        this.pos = pos;
    }

    public int getPos() {
        return pos;
    }
}
