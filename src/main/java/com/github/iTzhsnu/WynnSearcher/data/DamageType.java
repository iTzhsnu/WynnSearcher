package com.github.iTzhsnu.WynnSearcher.data;

public enum DamageType {
    NEUTRAL("Neutral", 0),
    EARTH("Earth", 1),
    THUNDER("Thunder", 2),
    WATER("Water", 3),
    FIRE("Fire", 4),
    AIR("Air", 5);

    private final String name;
    private final int id;
    DamageType(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public static DamageType getDamageType(int i) {
        switch (i) {
            case 1:
                return DamageType.EARTH;
            case 2:
                return DamageType.THUNDER;
            case 3:
                return DamageType.WATER;
            case 4:
                return DamageType.FIRE;
            case 5:
                return DamageType.AIR;
            default:
                return DamageType.NEUTRAL;
        }
    }
}
