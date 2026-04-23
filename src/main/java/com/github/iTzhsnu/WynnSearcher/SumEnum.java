package com.github.iTzhsnu.WynnSearcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum SumEnum {
    TOTAL_HEALTH(createIdList(Identifications.HEALTH, Identifications.HEALTH_BONUS), null, null, null, false, false, false, false),
    TOTAL_HEALTH_REGEN(createIdList(Identifications.RAW_HEALTH_REGEN), null, createIdList(Identifications.HEALTH_REGEN_PERCENT), null, false, false, false, false),
    RAW_DEFENSES(rawDefenses(), null, null, null, false, false, false, false),
    RAW_RAINBOW_DEFENSES(rawDefenses(), null, null, null, false, false, true, false),
    DEFENSES_PERCENT(elemDefensePercent(), null, null, null, false, false, false, false),
    RAINBOW_DEFENSES_PERCENT(defensesPercent(), null, null, null, false, false, true, false),
    SKILL_POINT_BONUS(skillPoints(), null, null, null, false, false, false, false),
    RAINBOW_SKILL_POINT_BONUS(skillPoints(), null, null, null, false, false, true, false),

    BASE_DPS(damages(), null, null, null, true, false, false, true),
    NEUTRAL_DPS(neutralDamage(), null, null, null, true, false, false, true),
    EARTH_DPS(earthDamage(), null, null, null, true, false, false, true),
    THUNDER_DPS(thunderDamage(), null, null, null, true, false, false, true),
    WATER_DPS(waterDamage(), null, null, null, true, false, false, true),
    FIRE_DPS(fireDamage(), null, null, null, true, false, false, true),
    AIR_DPS(airDamage(), null, null, null, true, false, false, true),

    BASE_DAMAGES(damages(), null, null, null, false, false, false, false),
    BASE_RAINBOW_DAMAGES(rainbowDamages(), null, null, null, false, false, true, false),

    RAW_DAMAGES(rawDamages(), null, null, null, false, false, false, false),
    RAW_SPELL_DAMAGES(rawSpellDamages(), null, null, null, false, false, false, false),
    RAW_MELEE_DAMAGES(rawMeleeDamages(), null, null, null, false, false, false, false),

    DAMAGES_PERCENT(elemDamagePercent(), null, null, null, false, false, false, false),
    RAINBOW_DAMAGES_PERCENT(damagesPercent(), null, null, null, false, false, true, false),

    // ~ Melee Damage
    TOTAL_NEUTRAL_MELEE_DAMAGE(neutralDamage(), createIdList(Identifications.RAW_NEUTRAL_MELEE_DAMAGE, Identifications.RAW_MELEE_DAMAGE, Identifications.RAW_NEUTRAL_DAMAGE), createIdList(Identifications.NEUTRAL_MELEE_DAMAGE_PERCENT, Identifications.MELEE_DAMAGE_PERCENT, Identifications.DAMAGE_PERCENT), null, false, false, false, true),
    TOTAL_EARTH_MELEE_DAMAGE(earthDamage(), createIdList(Identifications.RAW_EARTH_MELEE_DAMAGE, Identifications.RAW_MELEE_DAMAGE, Identifications.RAW_ELEMENTAL_MELEE_DAMAGE, Identifications.RAW_ELEMENTAL_DAMAGE, Identifications.RAW_EARTH_DAMAGE) , createIdList(Identifications.EARTH_DAMAGE_PERCENT, Identifications.EARTH_MELEE_DAMAGE_PERCENT, Identifications.MELEE_DAMAGE_PERCENT, Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT, Identifications.ELEMENTAL_DAMAGE_PERCENT, Identifications.DAMAGE_PERCENT), null, false, false, false, true),
    TOTAL_THUNDER_MELEE_DAMAGE(thunderDamage(), createIdList(Identifications.RAW_THUNDER_MELEE_DAMAGE, Identifications.RAW_MELEE_DAMAGE, Identifications.RAW_ELEMENTAL_MELEE_DAMAGE, Identifications.RAW_ELEMENTAL_DAMAGE, Identifications.RAW_THUNDER_DAMAGE), createIdList(Identifications.THUNDER_DAMAGE_PERCENT, Identifications.THUNDER_MELEE_DAMAGE_PERCENT, Identifications.MELEE_DAMAGE_PERCENT, Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT, Identifications.ELEMENTAL_DAMAGE_PERCENT, Identifications.DAMAGE_PERCENT), null, false, false, false, true),
    TOTAL_WATER_MELEE_DAMAGE(waterDamage(), createIdList(Identifications.RAW_WATER_MELEE_DAMAGE, Identifications.RAW_MELEE_DAMAGE, Identifications.RAW_ELEMENTAL_MELEE_DAMAGE, Identifications.RAW_ELEMENTAL_DAMAGE, Identifications.RAW_WATER_DAMAGE), createIdList(Identifications.WATER_DAMAGE, Identifications.WATER_MELEE_DAMAGE_PERCENT, Identifications.MELEE_DAMAGE_PERCENT, Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT, Identifications.ELEMENTAL_DAMAGE_PERCENT, Identifications.DAMAGE_PERCENT), null, false, false, false, true),
    TOTAL_FIRE_MELEE_DAMAGE(fireDamage(), createIdList(Identifications.RAW_FIRE_MELEE_DAMAGE, Identifications.RAW_MELEE_DAMAGE, Identifications.RAW_ELEMENTAL_MELEE_DAMAGE, Identifications.RAW_ELEMENTAL_DAMAGE, Identifications.RAW_FIRE_DAMAGE), createIdList(Identifications.FIRE_DAMAGE_PERCENT, Identifications.FIRE_MELEE_DAMAGE_PERCENT, Identifications.MELEE_DAMAGE_PERCENT, Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT, Identifications.ELEMENTAL_DAMAGE_PERCENT, Identifications.DAMAGE_PERCENT), null, false, false, false, true),
    TOTAL_AIR_MELEE_DAMAGE(airDamage(), createIdList(Identifications.RAW_AIR_MELEE_DAMAGE, Identifications.RAW_MELEE_DAMAGE, Identifications.RAW_ELEMENTAL_MELEE_DAMAGE, Identifications.RAW_ELEMENTAL_DAMAGE, Identifications.RAW_AIR_DAMAGE), createIdList(Identifications.AIR_DAMAGE_PERCENT, Identifications.AIR_MELEE_DAMAGE_PERCENT, Identifications.MELEE_DAMAGE_PERCENT, Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT, Identifications.ELEMENTAL_DAMAGE_PERCENT, Identifications.DAMAGE_PERCENT), null, false, false, false, true),

    // ~ Melee DPS
    TOTAL_NEUTRAL_MELEE_DPS(neutralDamage(), createIdList(Identifications.RAW_NEUTRAL_MELEE_DAMAGE, Identifications.RAW_MELEE_DAMAGE, Identifications.RAW_NEUTRAL_DAMAGE), createIdList(Identifications.NEUTRAL_MELEE_DAMAGE_PERCENT, Identifications.MELEE_DAMAGE_PERCENT), null, true, true, false, true),
    TOTAL_NEUTRAL_SPELL_DPS(neutralDamage(), createIdList(Identifications.RAW_NEUTRAL_SPELL_DAMAGE, Identifications.RAW_SPELL_DAMAGE, Identifications.RAW_NEUTRAL_DAMAGE), createIdList(Identifications.NEUTRAL_SPELL_DAMAGE_PERCENT, Identifications.SPELL_DAMAGE_PERCENT), null, true, false, false, true),
    TOTAL_EARTH_MELEE_DPS(earthDamage(), createIdList(Identifications.RAW_EARTH_MELEE_DAMAGE, Identifications.RAW_MELEE_DAMAGE, Identifications.RAW_ELEMENTAL_MELEE_DAMAGE, Identifications.RAW_ELEMENTAL_DAMAGE, Identifications.RAW_EARTH_DAMAGE), createIdList(Identifications.EARTH_DAMAGE_PERCENT, Identifications.EARTH_MELEE_DAMAGE_PERCENT, Identifications.MELEE_DAMAGE_PERCENT, Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT, Identifications.ELEMENTAL_DAMAGE_PERCENT, Identifications.DAMAGE_PERCENT), null, true, true, false, true),
    TOTAL_EARTH_SPELL_DPS(earthDamage(), createIdList(Identifications.RAW_EARTH_SPELL_DAMAGE, Identifications.RAW_SPELL_DAMAGE, Identifications.RAW_ELEMENTAL_SPELL_DAMAGE, Identifications.RAW_ELEMENTAL_DAMAGE, Identifications.RAW_EARTH_DAMAGE), createIdList(Identifications.EARTH_DAMAGE_PERCENT, Identifications.EARTH_SPELL_DAMAGE_PERCENT, Identifications.SPELL_DAMAGE_PERCENT, Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT, Identifications.ELEMENTAL_DAMAGE_PERCENT, Identifications.DAMAGE_PERCENT), null, true, false, false, true),
    TOTAL_THUNDER_MELEE_DPS(thunderDamage(), createIdList(Identifications.RAW_THUNDER_MELEE_DAMAGE, Identifications.RAW_MELEE_DAMAGE, Identifications.RAW_ELEMENTAL_MELEE_DAMAGE, Identifications.RAW_ELEMENTAL_DAMAGE, Identifications.RAW_THUNDER_DAMAGE), createIdList(Identifications.THUNDER_DAMAGE_PERCENT, Identifications.THUNDER_MELEE_DAMAGE_PERCENT, Identifications.MELEE_DAMAGE_PERCENT, Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT, Identifications.ELEMENTAL_DAMAGE_PERCENT, Identifications.DAMAGE_PERCENT), null, true, true, false, true),
    TOTAL_THUNDER_SPELL_DPS(thunderDamage(), createIdList(Identifications.RAW_THUNDER_SPELL_DAMAGE, Identifications.RAW_SPELL_DAMAGE, Identifications.RAW_ELEMENTAL_SPELL_DAMAGE, Identifications.RAW_ELEMENTAL_DAMAGE, Identifications.RAW_THUNDER_DAMAGE), createIdList(Identifications.THUNDER_DAMAGE_PERCENT, Identifications.THUNDER_SPELL_DAMAGE_PERCENT, Identifications.SPELL_DAMAGE_PERCENT, Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT, Identifications.ELEMENTAL_DAMAGE_PERCENT, Identifications.DAMAGE_PERCENT), null, true, false, false, true),
    TOTAL_WATER_MELEE_DPS(waterDamage(), createIdList(Identifications.RAW_WATER_MELEE_DAMAGE, Identifications.RAW_MELEE_DAMAGE, Identifications.RAW_ELEMENTAL_MELEE_DAMAGE, Identifications.RAW_ELEMENTAL_DAMAGE, Identifications.RAW_WATER_DAMAGE), createIdList(Identifications.WATER_DAMAGE_PERCENT, Identifications.WATER_MELEE_DAMAGE_PERCENT, Identifications.MELEE_DAMAGE_PERCENT, Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT, Identifications.ELEMENTAL_DAMAGE_PERCENT, Identifications.DAMAGE_PERCENT), null, true, true, false, true),
    TOTAL_WATER_SPELL_DPS(waterDamage(), createIdList(Identifications.RAW_WATER_SPELL_DAMAGE, Identifications.RAW_SPELL_DAMAGE, Identifications.RAW_ELEMENTAL_SPELL_DAMAGE, Identifications.RAW_ELEMENTAL_DAMAGE, Identifications.RAW_WATER_DAMAGE), createIdList(Identifications.WATER_DAMAGE_PERCENT, Identifications.WATER_SPELL_DAMAGE_PERCENT, Identifications.SPELL_DAMAGE_PERCENT, Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT, Identifications.ELEMENTAL_DAMAGE_PERCENT, Identifications.DAMAGE_PERCENT), null, true, false, false, true),
    TOTAL_FIRE_MELEE_DPS(fireDamage(), createIdList(Identifications.RAW_FIRE_MELEE_DAMAGE, Identifications.RAW_MELEE_DAMAGE, Identifications.RAW_ELEMENTAL_MELEE_DAMAGE, Identifications.RAW_ELEMENTAL_DAMAGE, Identifications.RAW_FIRE_DAMAGE), createIdList(Identifications.FIRE_DAMAGE_PERCENT, Identifications.FIRE_MELEE_DAMAGE_PERCENT, Identifications.MELEE_DAMAGE_PERCENT, Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT, Identifications.ELEMENTAL_DAMAGE_PERCENT, Identifications.DAMAGE_PERCENT), null, true, true, false, true),
    TOTAL_FIRE_SPELL_DPS(fireDamage(), createIdList(Identifications.RAW_FIRE_SPELL_DAMAGE, Identifications.RAW_SPELL_DAMAGE, Identifications.RAW_ELEMENTAL_SPELL_DAMAGE, Identifications.RAW_ELEMENTAL_DAMAGE, Identifications.RAW_FIRE_DAMAGE), createIdList(Identifications.FIRE_DAMAGE_PERCENT, Identifications.FIRE_SPELL_DAMAGE_PERCENT, Identifications.SPELL_DAMAGE_PERCENT, Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT, Identifications.ELEMENTAL_DAMAGE_PERCENT, Identifications.DAMAGE_PERCENT), null, true, false, false, true),
    TOTAL_AIR_MELEE_DPS(airDamage(), createIdList(Identifications.RAW_AIR_MELEE_DAMAGE, Identifications.RAW_MELEE_DAMAGE, Identifications.RAW_ELEMENTAL_MELEE_DAMAGE, Identifications.RAW_ELEMENTAL_DAMAGE, Identifications.RAW_AIR_DAMAGE), createIdList(Identifications.AIR_DAMAGE_PERCENT, Identifications.AIR_MELEE_DAMAGE_PERCENT, Identifications.MELEE_DAMAGE_PERCENT, Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT, Identifications.ELEMENTAL_DAMAGE_PERCENT, Identifications.DAMAGE_PERCENT), null, true, true, false, true),
    TOTAL_AIR_SPELL_DPS(airDamage(), createIdList(Identifications.RAW_AIR_SPELL_DAMAGE, Identifications.RAW_SPELL_DAMAGE, Identifications.RAW_ELEMENTAL_SPELL_DAMAGE, Identifications.RAW_ELEMENTAL_DAMAGE, Identifications.RAW_AIR_DAMAGE), createIdList(Identifications.AIR_DAMAGE_PERCENT, Identifications.AIR_SPELL_DAMAGE_PERCENT, Identifications.SPELL_DAMAGE_PERCENT, Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT, Identifications.ELEMENTAL_DAMAGE_PERCENT, Identifications.DAMAGE_PERCENT), null, true, false, false, true),

    RAW_SPELL_COSTS(createIdList(Identifications.RAW_1ST_SPELL_COST, Identifications.RAW_2ND_SPELL_COST, Identifications.RAW_3RD_SPELL_COST, Identifications.RAW_4TH_SPELL_COST), null, null, null, false, false, false, false),
    SPELL_COSTS_PERCENT(createIdList(Identifications.PERCENT_1ST_SPELL_COST, Identifications.PERCENT_2ND_SPELL_COST, Identifications.PERCENT_3RD_SPELL_COST, Identifications.PERCENT_4TH_SPELL_COST), null, null, null, false, false, false, false),

    INGREDIENT_EFFECTIVENESS(createIdList(Identifications.INGREDIENT_EFFECTIVENESS_ABOVE, Identifications.INGREDIENT_EFFECTIVENESS_UNDER, Identifications.INGREDIENT_EFFECTIVENESS_RIGHT, Identifications.INGREDIENT_EFFECTIVENESS_LEFT, Identifications.INGREDIENT_EFFECTIVENESS_TOUCHING, Identifications.INGREDIENT_EFFECTIVENESS_NOT_TOUCHING), null, null, null, false, false, false, false),

    // Total Melee DPS
    TMD_NEUTRAL(neutralDamage(), createIdList(Identifications.RAW_NEUTRAL_MELEE_DAMAGE, Identifications.RAW_NEUTRAL_DAMAGE), createIdList(Identifications.NEUTRAL_MELEE_DAMAGE_PERCENT, Identifications.MELEE_DAMAGE_PERCENT, Identifications.DAMAGE_PERCENT), null, true, true, false, true),
    TMD_EARTH(earthDamage(), createIdList(Identifications.RAW_EARTH_MELEE_DAMAGE, Identifications.RAW_EARTH_DAMAGE), createIdList(Identifications.EARTH_MELEE_DAMAGE_PERCENT, Identifications.EARTH_DAMAGE_PERCENT, Identifications.MELEE_DAMAGE_PERCENT, Identifications.ELEMENTAL_DAMAGE_PERCENT, Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT, Identifications.DAMAGE_PERCENT), null, true, true, false, true),
    TMD_THUNDER(thunderDamage(), createIdList(Identifications.RAW_THUNDER_MELEE_DAMAGE, Identifications.RAW_THUNDER_DAMAGE), createIdList(Identifications.THUNDER_MELEE_DAMAGE_PERCENT, Identifications.THUNDER_DAMAGE_PERCENT, Identifications.MELEE_DAMAGE_PERCENT, Identifications.ELEMENTAL_DAMAGE_PERCENT, Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT, Identifications.DAMAGE_PERCENT), null, true, true, false, true),
    TMD_WATER(waterDamage(), createIdList(Identifications.RAW_WATER_MELEE_DAMAGE, Identifications.RAW_WATER_DAMAGE), createIdList(Identifications.WATER_MELEE_DAMAGE_PERCENT, Identifications.WATER_DAMAGE_PERCENT, Identifications.MELEE_DAMAGE_PERCENT, Identifications.ELEMENTAL_DAMAGE_PERCENT, Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT, Identifications.DAMAGE_PERCENT), null, true, true, false, true),
    TMD_FIRE(fireDamage(), createIdList(Identifications.RAW_FIRE_MELEE_DAMAGE, Identifications.RAW_FIRE_DAMAGE), createIdList(Identifications.FIRE_MELEE_DAMAGE_PERCENT, Identifications.FIRE_DAMAGE_PERCENT, Identifications.MELEE_DAMAGE_PERCENT, Identifications.ELEMENTAL_DAMAGE_PERCENT, Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT, Identifications.DAMAGE_PERCENT), null, true, true, false, true),
    TMD_AIR(airDamage(), createIdList(Identifications.RAW_AIR_MELEE_DAMAGE, Identifications.RAW_AIR_DAMAGE), createIdList(Identifications.AIR_MELEE_DAMAGE_PERCENT, Identifications.AIR_DAMAGE_PERCENT, Identifications.MELEE_DAMAGE_PERCENT, Identifications.ELEMENTAL_DAMAGE_PERCENT, Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT, Identifications.DAMAGE_PERCENT), null, true, true, false, true),
    TMD_SP(null, createIdList(Identifications.RAW_MELEE_DAMAGE, Identifications.RAW_ELEMENTAL_MELEE_DAMAGE, Identifications.RAW_ELEMENTAL_DAMAGE, Identifications.RAW_DAMAGE), null, null, true, true, false, true),
    TOTAL_MELEE_DPS(damages(), null, null, createSUmList(TMD_NEUTRAL, TMD_EARTH, TMD_THUNDER, TMD_WATER, TMD_FIRE, TMD_AIR, TMD_SP), true, true, false, true),

    // Total Melee Damage
    TM_NEUTRAL(neutralDamage(), createIdList(Identifications.RAW_NEUTRAL_MELEE_DAMAGE, Identifications.RAW_NEUTRAL_DAMAGE), createIdList(Identifications.NEUTRAL_MELEE_DAMAGE_PERCENT, Identifications.MELEE_DAMAGE_PERCENT, Identifications.NEUTRAL_DAMAGE_PERCENT), null, false, false, false, true),
    TM_EARTH(earthDamage(), createIdList(Identifications.RAW_EARTH_MELEE_DAMAGE, Identifications.RAW_EARTH_DAMAGE), createIdList(Identifications.EARTH_MELEE_DAMAGE_PERCENT, Identifications.EARTH_DAMAGE_PERCENT, Identifications.MELEE_DAMAGE_PERCENT, Identifications.ELEMENTAL_DAMAGE_PERCENT, Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT, Identifications.DAMAGE_PERCENT), null, false, false, false, true),
    TM_THUNDER(thunderDamage(), createIdList(Identifications.RAW_THUNDER_MELEE_DAMAGE, Identifications.RAW_THUNDER_DAMAGE), createIdList(Identifications.THUNDER_MELEE_DAMAGE_PERCENT, Identifications.THUNDER_DAMAGE_PERCENT, Identifications.MELEE_DAMAGE_PERCENT, Identifications.ELEMENTAL_DAMAGE_PERCENT, Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT, Identifications.DAMAGE_PERCENT), null, false, false, false, true),
    TM_WATER(waterDamage(), createIdList(Identifications.RAW_WATER_MELEE_DAMAGE, Identifications.RAW_WATER_DAMAGE), createIdList(Identifications.WATER_MELEE_DAMAGE_PERCENT, Identifications.WATER_DAMAGE_PERCENT, Identifications.MELEE_DAMAGE_PERCENT, Identifications.ELEMENTAL_DAMAGE_PERCENT, Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT, Identifications.DAMAGE_PERCENT), null, false, false, false, true),
    TM_FIRE(fireDamage(), createIdList(Identifications.RAW_FIRE_MELEE_DAMAGE, Identifications.RAW_FIRE_DAMAGE), createIdList(Identifications.FIRE_MELEE_DAMAGE_PERCENT, Identifications.FIRE_DAMAGE_PERCENT, Identifications.MELEE_DAMAGE_PERCENT, Identifications.ELEMENTAL_DAMAGE_PERCENT, Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT, Identifications.DAMAGE_PERCENT), null, false, false, false, true),
    TM_AIR(airDamage(), createIdList(Identifications.RAW_AIR_MELEE_DAMAGE, Identifications.RAW_AIR_DAMAGE), createIdList(Identifications.AIR_MELEE_DAMAGE_PERCENT, Identifications.AIR_DAMAGE_PERCENT, Identifications.MELEE_DAMAGE_PERCENT, Identifications.ELEMENTAL_DAMAGE_PERCENT, Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT, Identifications.DAMAGE_PERCENT), null, false, false, false, true),
    TM_SP(createIdList(Identifications.RAW_MELEE_DAMAGE, Identifications.RAW_ELEMENTAL_MELEE_DAMAGE, Identifications.RAW_ELEMENTAL_DAMAGE, Identifications.RAW_DAMAGE), null, null, null, false, false, false, true),
    TOTAL_MELEE_DAMAGE(damages(), null, null, createSUmList(TM_NEUTRAL, TM_EARTH, TM_THUNDER, TM_WATER, TM_FIRE, TM_AIR, TM_SP), false, false, false, true),

    // Total Spell DPS
    TS_NEUTRAL(neutralDamage(), createIdList(Identifications.RAW_NEUTRAL_SPELL_DAMAGE, Identifications.RAW_NEUTRAL_DAMAGE), createIdList(Identifications.NEUTRAL_SPELL_DAMAGE_PERCENT, Identifications.SPELL_DAMAGE_PERCENT, Identifications.NEUTRAL_DAMAGE_PERCENT, Identifications.DAMAGE_PERCENT), null, true, false, false, true),
    TS_EARTH(earthDamage(), createIdList(Identifications.RAW_EARTH_SPELL_DAMAGE, Identifications.RAW_EARTH_DAMAGE), createIdList(Identifications.EARTH_SPELL_DAMAGE_PERCENT, Identifications.EARTH_DAMAGE_PERCENT, Identifications.SPELL_DAMAGE_PERCENT, Identifications.ELEMENTAL_DAMAGE_PERCENT, Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT, Identifications.DAMAGE_PERCENT), null, true, false, false, true),
    TS_THUNDER(thunderDamage(), createIdList(Identifications.RAW_THUNDER_SPELL_DAMAGE, Identifications.RAW_THUNDER_DAMAGE), createIdList(Identifications.THUNDER_SPELL_DAMAGE_PERCENT, Identifications.THUNDER_DAMAGE_PERCENT, Identifications.SPELL_DAMAGE_PERCENT, Identifications.ELEMENTAL_DAMAGE_PERCENT, Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT, Identifications.DAMAGE_PERCENT), null, true, false, false, true),
    TS_WATER(waterDamage(), createIdList(Identifications.RAW_WATER_SPELL_DAMAGE, Identifications.RAW_WATER_DAMAGE), createIdList(Identifications.WATER_SPELL_DAMAGE_PERCENT, Identifications.WATER_DAMAGE_PERCENT, Identifications.SPELL_DAMAGE_PERCENT, Identifications.ELEMENTAL_DAMAGE_PERCENT, Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT, Identifications.DAMAGE_PERCENT), null, true, false, false, true),
    TS_FIRE(fireDamage(), createIdList(Identifications.RAW_FIRE_SPELL_DAMAGE, Identifications.RAW_FIRE_DAMAGE), createIdList(Identifications.FIRE_SPELL_DAMAGE_PERCENT, Identifications.FIRE_DAMAGE_PERCENT, Identifications.SPELL_DAMAGE_PERCENT, Identifications.ELEMENTAL_DAMAGE_PERCENT, Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT, Identifications.DAMAGE_PERCENT), null, true, false, false, true),
    TS_AIR(airDamage(), createIdList(Identifications.RAW_AIR_SPELL_DAMAGE, Identifications.RAW_AIR_DAMAGE), createIdList(Identifications.AIR_SPELL_DAMAGE_PERCENT, Identifications.AIR_DAMAGE_PERCENT, Identifications.SPELL_DAMAGE_PERCENT, Identifications.ELEMENTAL_DAMAGE_PERCENT, Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT, Identifications.DAMAGE_PERCENT), null, true, false, false, true),
    TS_SP(createIdList(Identifications.RAW_SPELL_DAMAGE, Identifications.RAW_ELEMENTAL_SPELL_DAMAGE, Identifications.RAW_ELEMENTAL_DAMAGE, Identifications.RAW_DAMAGE), null, null, null, false, false, false, true),
    TOTAL_SPELL_DPS(damages(), null, null, createSUmList(TS_NEUTRAL, TS_EARTH, TS_THUNDER, TS_WATER, TS_FIRE, TS_AIR, TS_SP), true, false, false, true),

    TM_DAM(null, createIdList(Identifications.RAW_MELEE_DAMAGE, Identifications.RAW_DAMAGE), null, null, false, false, false, true),
    TM_ELEM(null, createIdList(Identifications.RAW_ELEMENTAL_MELEE_DAMAGE, Identifications.RAW_ELEMENTAL_DAMAGE), null, null, false, false, false, true),
    TS_DAM(null, createIdList(Identifications.RAW_SPELL_DAMAGE, Identifications.RAW_DAMAGE), null, null, false, false, false, true),
    TS_ELEM(null, createIdList(Identifications.RAW_ELEMENTAL_SPELL_DAMAGE, Identifications.RAW_ELEMENTAL_DAMAGE), null, null, false, false, false, true),
    MELEE_APPROPRIATE(null, null, null, createSUmList(TM_NEUTRAL, TM_EARTH, TM_THUNDER, TM_WATER, TM_FIRE, TM_AIR, TM_DAM, TM_ELEM), false, true, false, true),
    SPELL_APPROPRIATE(null, null, null, createSUmList(TS_NEUTRAL, TS_EARTH, TS_THUNDER, TS_WATER, TS_FIRE, TS_AIR, TS_DAM, TS_ELEM), true, false, false, true)
    ;

    private final List<Identifications> ids;
    private final List<Identifications> addIds;
    private final List<Identifications> multiIds;
    private final List<SumEnum> sumIDs;
    private final boolean dps;
    private final boolean meleeDps;
    private final boolean needAll;
    private final boolean average;

    // if meleeDps = true: (Base IDs * Multi IDs + Add IDs) * DPS
    // if meleeDps = false: Base IDs * Multi IDs * DPS + Add IDs
    SumEnum(List<Identifications> ids, List<Identifications> addIds, List<Identifications> multiIds, List<SumEnum> sumIDs, boolean dps, boolean meleeDps, boolean needAll, boolean average) {
        this.ids = ids;
        this.addIds = addIds;
        this.multiIds = multiIds;
        this.sumIDs = sumIDs;
        this.dps = dps;
        this.meleeDps = meleeDps;
        this.needAll = needAll;
        this.average = average;
    }

    public List<Identifications> getIds() {
        return this.ids;
    }


    public List<Identifications> getAddIds() {
        return this.addIds;
    }

    public List<Identifications> getMultiIds() {
        return this.multiIds;
    }

    public List<SumEnum> getSumIDs() {
        return this.sumIDs;
    }

    public boolean isDps() {
        return this.dps;
    }

    public boolean isMeleeDps() {
        return this.meleeDps;
    }

    public boolean isNeedAll() {
        return this.needAll;
    }

    public boolean isAverage() {
        return this.average;
    }

    private static List<Identifications> rawDefenses() {
        List<Identifications> ids = new ArrayList<>();

        ids.add(Identifications.EARTH_DEFENSE);
        ids.add(Identifications.THUNDER_DEFENSE);
        ids.add(Identifications.WATER_DEFENSE);
        ids.add(Identifications.FIRE_DEFENSE);
        ids.add(Identifications.AIR_DEFENSE);

        return ids;
    }

    private static List<Identifications> defensesPercent() {
        List<Identifications> ids = new ArrayList<>();

        ids.add(Identifications.EARTH_DEFENSE_PERCENT);
        ids.add(Identifications.THUNDER_DEFENSE_PERCENT);
        ids.add(Identifications.WATER_DEFENSE_PERCENT);
        ids.add(Identifications.FIRE_DEFENSE_PERCENT);
        ids.add(Identifications.AIR_DEFENSE_PERCENT);

        return ids;
    }

    private static List<Identifications> elemDefensePercent() {
        List<Identifications> ids = defensesPercent();

        ids.add(Identifications.ELEMENTAL_DEFENSE_PERCENT);

        return ids;
    }

    private static List<Identifications> skillPoints() {
        List<Identifications> ids = new ArrayList<>();

        ids.add(Identifications.STRENGTH);
        ids.add(Identifications.DEXTERITY);
        ids.add(Identifications.INTELLIGENCE);
        ids.add(Identifications.DEFENSE);
        ids.add(Identifications.AGILITY);

        return ids;
    }

    private static List<Identifications> neutralDamage() {
        List<Identifications> ids = new ArrayList<>();

        ids.add(Identifications.NEUTRAL_DAMAGE);

        return ids;
    }

    private static List<Identifications> earthDamage() {
        List<Identifications> ids = new ArrayList<>();

        ids.add(Identifications.EARTH_DAMAGE);

        return ids;
    }

    private static List<Identifications> thunderDamage() {
        List<Identifications> ids = new ArrayList<>();

        ids.add(Identifications.THUNDER_DAMAGE);

        return ids;
    }

    private static List<Identifications> waterDamage() {
        List<Identifications> ids = new ArrayList<>();

        ids.add(Identifications.WATER_DAMAGE);

        return ids;
    }

    private static List<Identifications> fireDamage() {
        List<Identifications> ids = new ArrayList<>();

        ids.add(Identifications.FIRE_DAMAGE);

        return ids;
    }

    private static List<Identifications> airDamage() {
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

    private static List<Identifications> rainbowDamages() {
        List<Identifications> ids = new ArrayList<>();

        ids.add(Identifications.EARTH_DAMAGE);
        ids.add(Identifications.THUNDER_DAMAGE);
        ids.add(Identifications.WATER_DAMAGE);
        ids.add(Identifications.FIRE_DAMAGE);
        ids.add(Identifications.AIR_DAMAGE);

        return ids;
    }

    private static List<Identifications> damagesPercent() {
        return createIdList(Identifications.DAMAGE_PERCENT, Identifications.EARTH_DAMAGE_PERCENT, Identifications.THUNDER_DAMAGE_PERCENT, Identifications.WATER_DAMAGE_PERCENT, Identifications.FIRE_DAMAGE_PERCENT, Identifications.AIR_DAMAGE_PERCENT, Identifications.ELEMENTAL_DAMAGE_PERCENT);
    }

    private static List<Identifications> elemDamagePercent() {
        return createIdList(Identifications.DAMAGE_PERCENT, Identifications.ELEMENTAL_DAMAGE_PERCENT);
    }

    private static List<Identifications> rawDamages() {
        List<Identifications> ids = new ArrayList<>();

        ids.add(Identifications.RAW_DAMAGE);
        ids.add(Identifications.RAW_NEUTRAL_DAMAGE);
        ids.add(Identifications.RAW_EARTH_DAMAGE);
        ids.add(Identifications.RAW_THUNDER_DAMAGE);
        ids.add(Identifications.RAW_WATER_DAMAGE);
        ids.add(Identifications.RAW_FIRE_DAMAGE);
        ids.add(Identifications.RAW_AIR_DAMAGE);
        ids.add(Identifications.RAW_ELEMENTAL_DAMAGE);

        return ids;
    }

    private static List<Identifications> rawSpellDamages() {
        List<Identifications> ids = new ArrayList<>();

        ids.add(Identifications.RAW_SPELL_DAMAGE);
        ids.add(Identifications.RAW_NEUTRAL_SPELL_DAMAGE);
        ids.add(Identifications.RAW_EARTH_SPELL_DAMAGE);
        ids.add(Identifications.RAW_THUNDER_SPELL_DAMAGE);
        ids.add(Identifications.RAW_WATER_SPELL_DAMAGE);
        ids.add(Identifications.RAW_FIRE_SPELL_DAMAGE);
        ids.add(Identifications.RAW_AIR_SPELL_DAMAGE);
        ids.add(Identifications.RAW_ELEMENTAL_SPELL_DAMAGE);

        return ids;
    }

    private static List<Identifications> rawMeleeDamages() {
        List<Identifications> ids = new ArrayList<>();

        ids.add(Identifications.RAW_MELEE_DAMAGE);
        ids.add(Identifications.RAW_NEUTRAL_MELEE_DAMAGE);
        ids.add(Identifications.RAW_EARTH_MELEE_DAMAGE);
        ids.add(Identifications.RAW_THUNDER_MELEE_DAMAGE);
        ids.add(Identifications.RAW_WATER_MELEE_DAMAGE);
        ids.add(Identifications.RAW_FIRE_MELEE_DAMAGE);
        ids.add(Identifications.RAW_AIR_MELEE_DAMAGE);
        ids.add(Identifications.RAW_ELEMENTAL_MELEE_DAMAGE);

        return ids;
    }

    private static List<Identifications> createIdList(Identifications... ids) {
        return new ArrayList<>(Arrays.asList(ids));
    }

    private static List<SumEnum> createSUmList(SumEnum... sums) {
        return new ArrayList<>(Arrays.asList(sums));
    }
}
