package com.github.iTzhsnu.WynnSearcher.builder.skilltree;

import java.util.HashMap;
import java.util.Map;

public enum Archetype {
    NONE("", 0),

    FALLEN("Fallen", 1),
    BATTLE_MONK("Battle Monk", 2),
    PALADIN("Paladin", 3),

    SHADESTEPPER("Shadestepper", 1),
    TRICKSTER("Trickster", 2),
    ACROBAT("Acrobat", 3),

    BOLTSLINGER("Boltslinger", 1),
    SHARPSHOOTER("Sharpshooter", 2),
    TRAPPER("Trapper", 3),

    RIFTWALKER("Riftwalker", 1),
    LIGHT_BENDER("Light Bender", 2),
    ARCANIST("Arcanist", 3),

    SUMMONER("Summoner", 1),
    RITUALIST("Ritualist", 2),
    ACOLYTE("Acolyte", 3)
    ;

    private final int num;
    private final String name;

    Archetype(String name, int num) {
        this.name = name;
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public String getName() {
        return name;
    }

    public static Map<String, Archetype> GET = new HashMap<String, Archetype>() {{
        //Warrior
        put("berserker", FALLEN);
        put("monk", BATTLE_MONK);
        put("tank", PALADIN);

        //Assassin
        put("shadestepper", SHADESTEPPER);
        put("trickster", TRICKSTER);
        put("acrobat", ACROBAT);

        //Archer
        put("boltslinger", BOLTSLINGER);
        put("sniper", SHARPSHOOTER);
        put("trapper", TRAPPER);

        //Mage
        put("blitz", RIFTWALKER);
        put("priest", LIGHT_BENDER);
        put("arcane", ARCANIST);

        //Shaman
        put("summoner", SUMMONER);
        put("ritualist", RITUALIST);
        put("bloodmagik", ACOLYTE);
    }};
}
