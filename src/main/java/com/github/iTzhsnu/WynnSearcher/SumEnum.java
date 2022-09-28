package com.github.iTzhsnu.WynnSearcher;

import java.util.ArrayList;
import java.util.List;

public enum SumEnum {
    TOTAL_HEALTH("total_health", health(), "add", null, false),
    RAW_DEFENSES("raw_defenses", raw_Defenses(), "add", null, false),
    RAW_RAINBOW_DEFENSES("raw_rainbow_defenses", raw_Defenses(), "add", null, true),
    DEFENSES_PERCENT("defenses_percent", defenses_Percent(), "add", null, false),
    RAINBOW_DEFENSES_PERCENT("rainbow_defenses_percent", defenses_Percent(), "add", null, true),
    SKILL_POINT_BONUS("skill_point_bonus", skill_Points(), "add", null, false),
    RAINBOW_SKILL_POINT_BONUS("rainbow_skill_point_bonus", skill_Points(), "add", null, true),

    BASE_DPS("base_dps", damages(), "base_dps", null, false),
    NEUTRAL_DPS("neutral_dps", neutral_Damage(), "base_dps", null, false),
    EARTH_DPS("earth_dps", earth_Damage(), "base_dps", null, false),
    THUNDER_DPS("thunder_dps", thunder_Damage(), "base_dps", null, false),
    WATER_DPS("water_dps", water_Damage(), "base_dps", null, false),
    FIRE_DPS("fire_dps", fire_Damage(), "base_dps", null, false),
    AIR_DPS("air_dps", air_Damage(), "base_dps", null, false),

    RAW_DAMAGES("raw_damages", damages(), "add", null, false),
    RAW_RAINBOW_DAMAGES("raw_rainbow_damages", damages(), "add", null, true),
    DAMAGES_PERCENT("damages_percent", damages_Percent(), "add", null, false),
    RAINBOW_DAMAGES_PERCENT("rainbow_damages_percent", damages_Percent(), "add", null, true),

    TOTAL_MELEE_DAMAGE("total_melee_damage", damages(), "add", null, false),
    TOTAL_NEUTRAL_MELEE_DAMAGE("total_neutral_melee_damage", neutral_Damage(), "multi", melee_Damage_Percent(), false),
    TOTAL_EARTH_DAMAGE("total_earth_damage", earth_Damage(), "multi", earth_Damage_Percent(), false),
    TOTAL_THUNDER_DAMAGE("total_thunder_damage", thunder_Damage(), "multi", thunder_Damage_Percent(), false),
    TOTAL_WATER_DAMAGE("total_water_damage", water_Damage(), "multi", water_Damage_Percent(), false),
    TOTAL_FIRE_DAMAGE("total_fire_damage", fire_Damage(), "multi", fire_Damage_Percent(), false),
    TOTAL_AIR_DAMAGE("total_air_damage", air_Damage(), "multi", air_Damage_Percent(), false),

    TOTAL_DPS("total_dps", damages(), "total_dps", null, false),
    TOTAL_MELEE_DPS("total_melee_dps", damages(), "total_dps", melee_Damage_Percent(), false),
    TOTAL_SPELL_DPS("total_spell_dps", damages(), "total_dps", spell_Damage_Percent(), false),
    TOTAL_NEUTRAL_MELEE_DPS("total_neutral_melee_dps", neutral_Damage(), "total_dps", melee_Damage_Percent(), false),
    TOTAL_NEUTRAL_SPELL_DPS("total_neutral_spell_dps", neutral_Damage(), "total_dps", spell_Damage_Percent(), false),
    TOTAL_EARTH_DPS("total_earth_dps", earth_Damage(), "total_dps", earth_Damage_Percent(), false),
    TOTAL_THUNDER_DPS("total_thunder_dps", thunder_Damage(), "total_dps", thunder_Damage_Percent(), false),
    TOTAL_WATER_DPS("total_water_dps", water_Damage(), "total_dps", water_Damage_Percent(), false),
    TOTAL_FIRE_DPS("total_fire_dps", fire_Damage(), "total_dps", fire_Damage_Percent(), false),
    TOTAL_AIR_DPS("total_air_dps", air_Damage(), "total_dps", air_Damage_Percent(), false),

    RAW_SPELL_COSTS("raw_spell_costs", raw_Spell_Costs(), "add", null, false),
    SPELL_COSTS_PERCENT("spell_costs_percent", spell_Costs_Percent(), "add", null, false)
    ;

    private final String name;
    private final List<Identifications> ids;
    private final String calcType;
    private final List<Identifications> andIDs;
    private final boolean needAll;


    SumEnum(String name, List<Identifications> ids, String calcType, List<Identifications> andIDs, boolean needAll) {
        this.name = name;
        this.ids = ids;
        this.calcType = calcType;
        this.andIDs = andIDs;
        this.needAll = needAll;
    }

    public String getName() {
        return this.name;
    }

    public List<Identifications> getIds() {
        return this.ids;
    }

    public String getCalcType() {
        return this.calcType;
    }

    public List<Identifications> getAndIDs() {
        return this.andIDs;
    }

    public boolean isNeedAll() {
        return this.needAll;
    }

    private static List<Identifications> health() {
        List<Identifications> ids = new ArrayList<>();

        ids.add(Identifications.HEALTH);
        ids.add(Identifications.HEALTH_BONUS);

        return ids;
    }

    private static List<Identifications> raw_Defenses() {
        List<Identifications> ids = new ArrayList<>();

        ids.add(Identifications.EARTH_DEFENSE);
        ids.add(Identifications.THUNDER_DEFENSE);
        ids.add(Identifications.WATER_DEFENSE);
        ids.add(Identifications.FIRE_DEFENSE);
        ids.add(Identifications.AIR_DEFENSE);

        return ids;
    }

    private static List<Identifications> defenses_Percent() {
        List<Identifications> ids = new ArrayList<>();

        ids.add(Identifications.EARTH_DEFENSE_PERCENT);
        ids.add(Identifications.THUNDER_DEFENSE_PERCENT);
        ids.add(Identifications.WATER_DEFENSE_PERCENT);
        ids.add(Identifications.FIRE_DEFENSE_PERCENT);
        ids.add(Identifications.AIR_DEFENSE_PERCENT);

        return ids;
    }

    private static List<Identifications> skill_Points() {
        List<Identifications> ids = new ArrayList<>();

        ids.add(Identifications.STRENGTH);
        ids.add(Identifications.DEXTERITY);
        ids.add(Identifications.INTELLIGENCE);
        ids.add(Identifications.DEFENSE);
        ids.add(Identifications.AGILITY);

        return ids;
    }

    private static List<Identifications> neutral_Damage() {
        List<Identifications> ids = new ArrayList<>();

        ids.add(Identifications.NEUTRAL_DAMAGE);

        return ids;
    }

    private static List<Identifications> earth_Damage() {
        List<Identifications> ids = new ArrayList<>();

        ids.add(Identifications.EARTH_DAMAGE);

        return ids;
    }

    private static List<Identifications> thunder_Damage() {
        List<Identifications> ids = new ArrayList<>();

        ids.add(Identifications.THUNDER_DAMAGE);

        return ids;
    }

    private static List<Identifications> water_Damage() {
        List<Identifications> ids = new ArrayList<>();

        ids.add(Identifications.WATER_DAMAGE);

        return ids;
    }

    private static List<Identifications> fire_Damage() {
        List<Identifications> ids = new ArrayList<>();

        ids.add(Identifications.FIRE_DAMAGE);

        return ids;
    }

    private static List<Identifications> air_Damage() {
        List<Identifications> ids = new ArrayList<>();

        ids.add(Identifications.AIR_DAMAGE);

        return ids;
    }

    private static List<Identifications> damages() {
        List<Identifications> ids = new ArrayList<>();

        ids.add(Identifications.NEUTRAL_DAMAGE);
        ids.add(Identifications.EARTH_DAMAGE);
        ids.add(Identifications.THUNDER_DAMAGE);
        ids.add(Identifications.WATER_DAMAGE);
        ids.add(Identifications.FIRE_DAMAGE);
        ids.add(Identifications.AIR_DAMAGE);

        return ids;
    }

    private static List<Identifications> damages_Percent() {
        List<Identifications> ids = new ArrayList<>();

        ids.add(Identifications.EARTH_DAMAGE_PERCENT);
        ids.add(Identifications.THUNDER_DAMAGE_PERCENT);
        ids.add(Identifications.WATER_DAMAGE_PERCENT);
        ids.add(Identifications.FIRE_DAMAGE_PERCENT);
        ids.add(Identifications.AIR_DAMAGE_PERCENT);

        return ids;
    }

    private static List<Identifications> melee_Damages_Percent() {
        List<Identifications> ids = new ArrayList<>();

        ids.add(Identifications.MELEE_DAMAGE_PERCENT);
        ids.add(Identifications.EARTH_DAMAGE_PERCENT);
        ids.add(Identifications.THUNDER_DAMAGE_PERCENT);
        ids.add(Identifications.WATER_DAMAGE_PERCENT);
        ids.add(Identifications.FIRE_DAMAGE_PERCENT);
        ids.add(Identifications.AIR_DAMAGE_PERCENT);

        return ids;
    }

    private static List<Identifications> melee_Damage_Percent() {
        List<Identifications> ids = new ArrayList<>();

        ids.add(Identifications.MELEE_DAMAGE_PERCENT);

        return ids;
    }

    private static List<Identifications> spell_Damage_Percent() {
        List<Identifications> ids = new ArrayList<>();

        ids.add(Identifications.SPELL_DAMAGE_PERCENT);

        return ids;
    }

    private static List<Identifications> spell_Damages_Percent() {
        List<Identifications> ids = new ArrayList<>();

        ids.add(Identifications.SPELL_DAMAGE_PERCENT);
        ids.add(Identifications.EARTH_DAMAGE_PERCENT);
        ids.add(Identifications.THUNDER_DAMAGE_PERCENT);
        ids.add(Identifications.WATER_DAMAGE_PERCENT);
        ids.add(Identifications.FIRE_DAMAGE_PERCENT);
        ids.add(Identifications.AIR_DAMAGE_PERCENT);

        return ids;
    }

    private static List<Identifications> earth_Damage_Percent() {
        List<Identifications> ids = new ArrayList<>();

        ids.add(Identifications.EARTH_DAMAGE_PERCENT);

        return ids;
    }

    private static List<Identifications> thunder_Damage_Percent() {
        List<Identifications> ids = new ArrayList<>();

        ids.add(Identifications.THUNDER_DAMAGE_PERCENT);

        return ids;
    }

    private static List<Identifications> water_Damage_Percent() {
        List<Identifications> ids = new ArrayList<>();

        ids.add(Identifications.WATER_DAMAGE_PERCENT);

        return ids;
    }

    private static List<Identifications> fire_Damage_Percent() {
        List<Identifications> ids = new ArrayList<>();

        ids.add(Identifications.FIRE_DAMAGE_PERCENT);

        return ids;
    }

    private static List<Identifications> air_Damage_Percent() {
        List<Identifications> ids = new ArrayList<>();

        ids.add(Identifications.AIR_DAMAGE_PERCENT);

        return ids;
    }

    private static List<Identifications> raw_Spell_Costs() {
        List<Identifications> ids = new ArrayList<>();

        ids.add(Identifications.RAW_1ST_SPELL_COST);
        ids.add(Identifications.RAW_2ND_SPELL_COST);
        ids.add(Identifications.RAW_3RD_SPELL_COST);
        ids.add(Identifications.RAW_4TH_SPELL_COST);

        return ids;
    }

    private static List<Identifications> spell_Costs_Percent() {
        List<Identifications> ids = new ArrayList<>();

        ids.add(Identifications.PERCENT_1ST_SPELL_COST);
        ids.add(Identifications.PERCENT_2ND_SPELL_COST);
        ids.add(Identifications.PERCENT_3RD_SPELL_COST);
        ids.add(Identifications.PERCENT_4TH_SPELL_COST);

        return ids;
    }

}
