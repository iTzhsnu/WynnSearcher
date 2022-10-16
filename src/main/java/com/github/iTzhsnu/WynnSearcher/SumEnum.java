package com.github.iTzhsnu.WynnSearcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum SumEnum {
    TOTAL_HEALTH(create_ID_List(Identifications.HEALTH, Identifications.HEALTH_BONUS), null, null, false, false, false),
    TOTAL_HEALTH_REGEN(create_ID_List(Identifications.RAW_HEALTH_REGEN), null, create_ID_List(Identifications.HEALTH_REGEN_PERCENT), false, false, false),
    RAW_DEFENSES(raw_Defenses(), null, null, false, false, false),
    RAW_RAINBOW_DEFENSES(raw_Defenses(), null, null, false, false, true),
    DEFENSES_PERCENT(defenses_Percent(), null, null, false, false, false),
    RAINBOW_DEFENSES_PERCENT(defenses_Percent(), null, null, false, false, true),
    SKILL_POINT_BONUS(skill_Points(), null, null, false, false, false),
    RAINBOW_SKILL_POINT_BONUS(skill_Points(), null, null, false, false, true),

    BASE_DPS(damages(), null, null, true, false, false),
    NEUTRAL_DPS(neutral_Damage(), null, null, true, false, false),
    EARTH_DPS(earth_Damage(), null, null, true, false, false),
    THUNDER_DPS(thunder_Damage(), null, null, true, false, false),
    WATER_DPS(water_Damage(), null, null, true, false, false),
    FIRE_DPS(fire_Damage(), null, null, true, false, false),
    AIR_DPS(air_Damage(), null, null, true, false, false),

    RAW_DAMAGES(damages(), null, null, false, false, false),
    RAW_RAINBOW_DAMAGES(rainbow_damages(), null, null, false, false, true),
    DAMAGES_PERCENT(damages_Percent(), null, null, false, false, false),
    RAINBOW_DAMAGES_PERCENT(damages_Percent(), null, null, false, false, true),

    TOTAL_MELEE_DAMAGE(damages(), create_ID_List(Identifications.RAW_MELEE_DAMAGE), create_ID_List(Identifications.MELEE_DAMAGE_PERCENT), false, false, false),
    TOTAL_NEUTRAL_MELEE_DAMAGE(neutral_Damage(), create_ID_List(Identifications.RAW_NEUTRAL_MELEE_DAMAGE, Identifications.RAW_MELEE_DAMAGE), create_ID_List(Identifications.NEUTRAL_MELEE_DAMAGE_PERCENT, Identifications.MELEE_DAMAGE_PERCENT), false, false, false),
    TOTAL_EARTH_MELEE_DAMAGE(earth_Damage(), create_ID_List(Identifications.RAW_EARTH_MELEE_DAMAGE, Identifications.RAW_MELEE_DAMAGE, Identifications.RAW_ELEMENTAL_MELEE_DAMAGE) , create_ID_List(Identifications.EARTH_DAMAGE_PERCENT, Identifications.EARTH_MELEE_DAMAGE_PERCENT, Identifications.MELEE_DAMAGE_PERCENT), false, false, false),
    TOTAL_THUNDER_MELEE_DAMAGE(thunder_Damage(), create_ID_List(Identifications.RAW_THUNDER_MELEE_DAMAGE, Identifications.RAW_MELEE_DAMAGE, Identifications.RAW_ELEMENTAL_MELEE_DAMAGE), create_ID_List(Identifications.THUNDER_DAMAGE_PERCENT, Identifications.THUNDER_MELEE_DAMAGE_PERCENT, Identifications.MELEE_DAMAGE_PERCENT), false, false, false),
    TOTAL_WATER_MELEE_DAMAGE(water_Damage(), create_ID_List(Identifications.RAW_WATER_MELEE_DAMAGE, Identifications.RAW_MELEE_DAMAGE, Identifications.RAW_ELEMENTAL_MELEE_DAMAGE), create_ID_List(Identifications.WATER_DAMAGE, Identifications.WATER_MELEE_DAMAGE_PERCENT, Identifications.MELEE_DAMAGE_PERCENT), false, false, false),
    TOTAL_FIRE_MELEE_DAMAGE(fire_Damage(), create_ID_List(Identifications.RAW_FIRE_MELEE_DAMAGE, Identifications.RAW_MELEE_DAMAGE, Identifications.RAW_ELEMENTAL_MELEE_DAMAGE), create_ID_List(Identifications.FIRE_DAMAGE_PERCENT, Identifications.FIRE_MELEE_DAMAGE_PERCENT, Identifications.MELEE_DAMAGE_PERCENT), false, false, false),
    TOTAL_AIR_MELEE_DAMAGE(air_Damage(), create_ID_List(Identifications.RAW_AIR_MELEE_DAMAGE, Identifications.RAW_MELEE_DAMAGE, Identifications.RAW_ELEMENTAL_MELEE_DAMAGE), create_ID_List(Identifications.AIR_DAMAGE_PERCENT, Identifications.AIR_MELEE_DAMAGE_PERCENT, Identifications.MELEE_DAMAGE_PERCENT), false, false, false),

    TOTAL_MELEE_DPS(damages(), create_ID_List(Identifications.RAW_MELEE_DAMAGE), create_ID_List(Identifications.MELEE_DAMAGE_PERCENT), true, true, false),
    TOTAL_SPELL_DPS(damages(), create_ID_List(Identifications.RAW_SPELL_DAMAGE), create_ID_List(Identifications.SPELL_DAMAGE_PERCENT), true, false, false),
    TOTAL_NEUTRAL_MELEE_DPS(neutral_Damage(), create_ID_List(Identifications.RAW_NEUTRAL_MELEE_DAMAGE, Identifications.RAW_MELEE_DAMAGE), create_ID_List(Identifications.NEUTRAL_MELEE_DAMAGE_PERCENT, Identifications.MELEE_DAMAGE_PERCENT), true, true, false),
    TOTAL_NEUTRAL_SPELL_DPS(neutral_Damage(), create_ID_List(Identifications.RAW_NEUTRAL_SPELL_DAMAGE, Identifications.RAW_SPELL_DAMAGE), create_ID_List(Identifications.NEUTRAL_SPELL_DAMAGE_PERCENT, Identifications.SPELL_DAMAGE_PERCENT), true, false, false),
    TOTAL_EARTH_MELEE_DPS(earth_Damage(), create_ID_List(Identifications.RAW_EARTH_MELEE_DAMAGE, Identifications.RAW_MELEE_DAMAGE, Identifications.RAW_ELEMENTAL_MELEE_DAMAGE), create_ID_List(Identifications.EARTH_DAMAGE_PERCENT, Identifications.EARTH_MELEE_DAMAGE_PERCENT, Identifications.MELEE_DAMAGE_PERCENT, Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT), true, true, false),
    TOTAL_EARTH_SPELL_DPS(earth_Damage(), create_ID_List(Identifications.RAW_EARTH_SPELL_DAMAGE, Identifications.RAW_SPELL_DAMAGE, Identifications.RAW_ELEMENTAL_SPELL_DAMAGE), create_ID_List(Identifications.EARTH_DAMAGE_PERCENT, Identifications.EARTH_SPELL_DAMAGE_PERCENT, Identifications.SPELL_DAMAGE_PERCENT, Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT), true, false, false),
    TOTAL_THUNDER_MELEE_DPS(thunder_Damage(), create_ID_List(Identifications.RAW_THUNDER_MELEE_DAMAGE, Identifications.RAW_MELEE_DAMAGE, Identifications.RAW_ELEMENTAL_MELEE_DAMAGE), create_ID_List(Identifications.THUNDER_DAMAGE_PERCENT, Identifications.THUNDER_MELEE_DAMAGE_PERCENT, Identifications.MELEE_DAMAGE_PERCENT, Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT), true, true, false),
    TOTAL_THUNDER_SPELL_DPS(thunder_Damage(), create_ID_List(Identifications.RAW_THUNDER_SPELL_DAMAGE, Identifications.RAW_SPELL_DAMAGE, Identifications.RAW_ELEMENTAL_SPELL_DAMAGE), create_ID_List(Identifications.THUNDER_DAMAGE_PERCENT, Identifications.THUNDER_SPELL_DAMAGE_PERCENT, Identifications.SPELL_DAMAGE_PERCENT, Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT), true, false, false),
    TOTAL_WATER_MELEE_DPS(water_Damage(), create_ID_List(Identifications.RAW_WATER_MELEE_DAMAGE, Identifications.RAW_MELEE_DAMAGE, Identifications.RAW_ELEMENTAL_MELEE_DAMAGE), create_ID_List(Identifications.WATER_DAMAGE_PERCENT, Identifications.WATER_MELEE_DAMAGE_PERCENT, Identifications.MELEE_DAMAGE_PERCENT, Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT), true, true, false),
    TOTAL_WATER_SPELL_DPS(water_Damage(), create_ID_List(Identifications.RAW_WATER_SPELL_DAMAGE, Identifications.RAW_SPELL_DAMAGE, Identifications.RAW_ELEMENTAL_SPELL_DAMAGE), create_ID_List(Identifications.WATER_DAMAGE_PERCENT, Identifications.WATER_SPELL_DAMAGE_PERCENT, Identifications.SPELL_DAMAGE_PERCENT, Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT), true, false, false),
    TOTAL_FIRE_MELEE_DPS(fire_Damage(), create_ID_List(Identifications.RAW_FIRE_MELEE_DAMAGE, Identifications.RAW_MELEE_DAMAGE, Identifications.RAW_ELEMENTAL_MELEE_DAMAGE), create_ID_List(Identifications.FIRE_DAMAGE_PERCENT, Identifications.FIRE_MELEE_DAMAGE_PERCENT, Identifications.MELEE_DAMAGE_PERCENT, Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT), true, true, false),
    TOTAL_FIRE_SPELL_DPS(fire_Damage(), create_ID_List(Identifications.RAW_FIRE_SPELL_DAMAGE, Identifications.RAW_SPELL_DAMAGE, Identifications.RAW_ELEMENTAL_SPELL_DAMAGE), create_ID_List(Identifications.FIRE_DAMAGE_PERCENT, Identifications.FIRE_SPELL_DAMAGE_PERCENT, Identifications.SPELL_DAMAGE_PERCENT, Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT), true, false, false),
    TOTAL_AIR_MELEE_DPS(air_Damage(), create_ID_List(Identifications.RAW_AIR_MELEE_DAMAGE, Identifications.RAW_MELEE_DAMAGE, Identifications.RAW_ELEMENTAL_MELEE_DAMAGE), create_ID_List(Identifications.AIR_DAMAGE_PERCENT, Identifications.AIR_MELEE_DAMAGE_PERCENT, Identifications.MELEE_DAMAGE_PERCENT, Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT), true, true, false),
    TOTAL_AIR_SPELL_DPS(air_Damage(), create_ID_List(Identifications.RAW_AIR_SPELL_DAMAGE, Identifications.RAW_SPELL_DAMAGE, Identifications.RAW_ELEMENTAL_SPELL_DAMAGE), create_ID_List(Identifications.AIR_DAMAGE_PERCENT, Identifications.AIR_SPELL_DAMAGE_PERCENT, Identifications.SPELL_DAMAGE_PERCENT, Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT), true, false, false),

    RAW_SPELL_COSTS(create_ID_List(Identifications.RAW_1ST_SPELL_COST, Identifications.RAW_2ND_SPELL_COST, Identifications.RAW_3RD_SPELL_COST, Identifications.RAW_4TH_SPELL_COST), null, null, false, false, false),
    SPELL_COSTS_PERCENT(create_ID_List(Identifications.PERCENT_1ST_SPELL_COST, Identifications.PERCENT_2ND_SPELL_COST, Identifications.PERCENT_3RD_SPELL_COST, Identifications.PERCENT_4TH_SPELL_COST), null, null, false, false, false),

    INGREDIENT_EFFECTIVENESS(create_ID_List(Identifications.INGREDIENT_EFFECTIVENESS_ABOVE, Identifications.INGREDIENT_EFFECTIVENESS_UNDER, Identifications.INGREDIENT_EFFECTIVENESS_RIGHT, Identifications.INGREDIENT_EFFECTIVENESS_LEFT, Identifications.INGREDIENT_EFFECTIVENESS_TOUCHING, Identifications.INGREDIENT_EFFECTIVENESS_NOT_TOUCHING), null, null, false, false, false)
    ;

    private final List<Identifications> ids;
    private final List<Identifications> addIDs;
    private final List<Identifications> multiIDs;
    private final boolean dps;
    private final boolean meleeDPS;
    private final boolean needAll;


    SumEnum(List<Identifications> ids, List<Identifications> addIDs, List<Identifications> multiIDs, boolean dps, boolean meleeDPS, boolean needAll) {
        this.ids = ids;
        this.addIDs = addIDs;
        this.multiIDs = multiIDs;
        this.dps = dps;
        this.meleeDPS = meleeDPS;
        this.needAll = needAll;
    }

    public List<Identifications> getIds() {
        return this.ids;
    }


    public List<Identifications> getAddIDs() {
        return this.addIDs;
    }

    public List<Identifications> getMultiIDs() {
        return this.multiIDs;
    }

    public boolean isDPS() {
        return this.dps;
    }

    public boolean isMeleeDPS() {
        return this.meleeDPS;
    }

    public boolean isNeedAll() {
        return this.needAll;
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

    private static List<Identifications> rainbow_damages() {
        List<Identifications> ids = new ArrayList<>();

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

    private static List<Identifications> create_ID_List(Identifications... ids) {
        return new ArrayList<>(Arrays.asList(ids));
    }
}
