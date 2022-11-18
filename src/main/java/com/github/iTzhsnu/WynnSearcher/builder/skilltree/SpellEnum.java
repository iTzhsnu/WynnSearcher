package com.github.iTzhsnu.WynnSearcher.builder.skilltree;

public enum SpellEnum {
    BASH(130, 20, 0, 0, 0, 0, SpellType.BASE, 1),
    DOUBLE_BASH(-50, 0, 0, 0, 0, 0, SpellType.ADD, 1),
    QUADRUPLE_BASH(-20, 0, 0, 0, 0, 0, SpellType.ADD, 1),

    CHARGE(0, 0, 0, 0, 0, 0, SpellType.BASE, 1),

    ARROW_BOMB(160, 0, 0, 0, 20, 0, SpellType.BASE, 1),
    HEART_SHATTER(100, 0, 0, 0, 0, 0, SpellType.ADD, 0),

    ;

    private final int neutral;
    private final int earth;
    private final int thunder;
    private final int water;
    private final int fire;
    private final int air;
    private final SpellType spellType;
    private final int addNum;

    SpellEnum(int neutral, int earth, int thunder, int water, int fire, int air, SpellType spellType, int addNum) {
        this.neutral = neutral;
        this.earth = earth;
        this.thunder = thunder;
        this.water = water;
        this.fire = fire;
        this.air = air;
        this.spellType = spellType;
        this.addNum = addNum;
    }

    public int getNeutral() {
        return neutral;
    }

    public int getEarth() {
        return earth;
    }

    public int getThunder() {
        return thunder;
    }

    public int getWater() {
        return water;
    }

    public int getFire() {
        return fire;
    }

    public int getAir() {
        return air;
    }

    public SpellType getSpellType() {
        return spellType;
    }

    public int getAddNum() {
        return addNum;
    }

    public enum SpellType {
        ADD,
        CHANGE,
        BASE
    }
}
