package com.github.iTzhsnu.WynnSearcher;

public enum Identifications {
    EMPTY(null, null, false, null, null, null, false),

    LEVEL("level", "level", false, "int", null, "nothing", false),
    STRENGTH_REQ("strength", "strengthRequirement", false, "int", null, "itemOnlyIDs", false),
    DEXTERITY_REQ("dexterity", "dexterityRequirement", false, "int", null, "itemOnlyIDs", false),
    INTELLIGENCE_REQ("intelligence", "intelligenceRequirement", false, "int", null, "itemOnlyIDs", false),
    DEFENSE_REQ("defense", "defenceRequirement", false, "int", null, "itemOnlyIDs", false),
    AGILITY_REQ("agility", "agilityRequirement", false, "int", null, "itemOnlyIDs", false),

    HEALTH("health", null, false, "int", null, null, false),
    HEALTH_BONUS("healthBonus", "HEALTHBONUS", false, "int", null, "identifications", true),
    RAW_HEALTH_REGEN("healthRegenRaw", "HEALTHREGENRAW", false, "int", null, "identifications", true),
    HEALTH_REGEN_PERCENT("healthRegen", "HEALTHREGEN", true, "int", null, "identifications", true),
    LIFE_STEAL("lifeSteal", "LIFESTEAL", false, "int", null, "identifications", true),

    STRENGTH("strengthPoints", "STRENGTHPOINTS", false, "int", null, "identifications", false),
    DEXTERITY("dexterityPoints", "DEXTERITYPOINTS", false, "int", null, "identifications", false),
    INTELLIGENCE("intelligencePoints", "INTELLIGENCEPOINTS", false, "int", null, "identifications", false),
    DEFENSE("defensePoints", "DEFENSEPOINTS", false, "int", null, "identifications", false),
    AGILITY("agilityPoints", "AGILITYPOINTS", false, "int", null, "identifications", false),

    EARTH_DEFENSE("earthDefense", null, false, "int", null, null, false),
    THUNDER_DEFENSE("thunderDefense", null, false, "int", null, null, false),
    WATER_DEFENSE("waterDefense", null, false, "int", null, null, false),
    FIRE_DEFENSE("fireDefense", null, false, "int", null, null, false),
    AIR_DEFENSE("airDefense", null, false, "int", null, null, false),

    EARTH_DEFENSE_PERCENT("bonusEarthDefense", "EARTHDEFENSE", true, "int", null, "identifications", true),
    THUNDER_DEFENSE_PERCENT("bonusThunderDefense", "THUNDERDEFENSE", true, "int", null, "identifications", true),
    WATER_DEFENSE_PERCENT("bonusWaterDefense", "WATERDEFENSE", true, "int", null, "identifications", true),
    FIRE_DEFENSE_PERCENT("bonusFireDefense", "FIREDEFENSE", true, "int", null, "identifications", true),
    AIR_DEFENSE_PERCENT("bonusAirDefense", "AIRDEFENSE", true, "int", null, "identifications", true),

    NEUTRAL_DAMAGE("damage", null, false, "damage_string", null, null, false),
    EARTH_DAMAGE("earthDamage", null, false, "damage_string", null, null, false),
    THUNDER_DAMAGE("thunderDamage", null, false, "damage_string", null, null, false),
    WATER_DAMAGE("waterDamage", null, false, "damage_string", null, null, false),
    FIRE_DAMAGE("fireDamage", null, false, "damage_string", null, null, false),
    AIR_DAMAGE("airDamage", null, false, "damage_string", null, null, false),

    EARTH_DAMAGE_PERCENT("earthDamageBonus", "EARTHDAMAGEBONUS", true, "int", null, "identifications", true),
    THUNDER_DAMAGE_PERCENT("thunderDamageBonus", "THUNDERDAMAGEBONUS", true, "int", null, "identifications", true),
    WATER_DAMAGE_PERCENT("waterDamageBonus", "WATERDAMAGEBONUS", true, "int", null, "identifications", true),
    FIRE_DAMAGE_PERCENT("fireDamageBonus", "FIREDAMAGEBONUS", true, "int", null, "identifications", true),
    AIR_DAMAGE_PERCENT("airDamageBonus", "AIRDAMAGEBONUS", true, "int", null, "identifications", true),

    RAW_SPELL_DAMAGE("spellDamageBonusRaw", "SPELLDAMAGERAW", false, "int", null, "identifications", true),
    RAW_MELEE_DAMAGE("mainAttackDamageBonusRaw", "DAMAGEBONUSRAW", false, "int", null, "identifications", true),
    SPELL_DAMAGE_PERCENT("spellDamageBonus", "SPELLDAMAGE", true, "int", null, "identifications", true),
    MELEE_DAMAGE_PERCENT("mainAttackDamageBonus", "DAMAGEBONUS", true, "int", null, "identifications", true),
    POISON("poison", "POISON", false, "int", null, "identifications", true),

    ATTACK_SPEED("attackSpeed", null, false, "string", null, null, false),
    ATTACK_SPEED_BONUS("attackSpeedBonus", "ATTACKSPEED", false, "int", null, "identifications", true),

    MANA_REGEN("manaRegen", "MANAREGEN", false, "int", null, "identifications", true),
    MANA_STEAL("manaSteal", "MANASTEAL", false, "int", null, "identifications", true),

    WALK_SPEED("speed", "SPEED", true, "int", null, "identifications", true),
    SPRINT_BONUS("sprint", "STAMINA", true, "int", null, "identifications", true),
    SPRINT_REGEN("sprintRegen", "STAMINA_REGEN", true, "int", null, "identifications", true),
    JUMP_HEIGHT("jumpHeight", "JUMP_HEIGHT", false, "int", null, "identifications", true),

    THORNS("thorns", "THORNS", true, "int", null, "identifications", true),
    REFLECTION("reflection", "REFLECTION", true, "int", null, "identifications", true),
    EXPLODING("exploding", "EXPLODING", true, "int", null, "identifications", true),
    STEALING("emeraldStealing", "EMERALDSTEALING", true, "int", null, "identifications", true),
    COMBAT_XP_BONUS("xpBonus", "XPBONUS", true, "int", null, "identifications", true),
    GATHERING_XP_BONUS("gatherXpBonus", "GATHER_XP_BONUS", true, "int", null, "identifications", true),
    GATHERING_SPEED_BONUS("gatherSpeed", "GATHER_SPEED", true, "int", null, "identifications", true),
    LOOT_BONUS("lootBonus", "LOOTBONUS", true, "int", null, "identifications", true),
    LOOT_QUALITY("lootQuality", "LOOT_QUALITY", true, "int", null, "identifications", true),
    SOUL_POINT_REGEN("soulPoints", "SOULPOINTS", true, "int", null, "identifications", true),
    POWDER_SLOTS("sockets", null, false, "int", null, null, false),
    MAJOR_IDS("majorIds", null, false, "string", null, null, false),

    RAW_1ST_SPELL_COST("spellCostRaw1", null, false, "int", null, null, true),
    RAW_2ND_SPELL_COST("spellCostRaw2", null, false, "int", null, null, true),
    RAW_3RD_SPELL_COST("spellCostRaw3", null, false, "int", null, null, true),
    RAW_4TH_SPELL_COST("spellCostRaw4", null, false, "int", null, null, true),

    PERCENT_1ST_SPELL_COST("spellCostPct1", null, true, "int", null, null, true),
    PERCENT_2ND_SPELL_COST("spellCostPct2", null, true, "int", null, null, true),
    PERCENT_3RD_SPELL_COST("spellCostPct3", null, true, "int", null, null, true),
    PERCENT_4TH_SPELL_COST("spellCostPct4", null, true, "int", null, null, true),

    NEUTRAL_MELEE_DAMAGE_PERCENT("mainAttackNeutralDamageBonus", null, true, "int", null, null, true),
    EARTH_MELEE_DAMAGE_PERCENT("mainAttackEarthDamageBonus", null, true, "int", null, null, true),
    THUNDER_MELEE_DAMAGE_PERCENT("mainAttackThunderDamageBonus", null, true, "int", null, null, true),
    WATER_MELEE_DAMAGE_PERCENT("mainAttackWaterDamageBonus", null, true, "int", null, null, true),
    FIRE_MELEE_DAMAGE_PERCENT("mainAttackFireDamageBonus", null, true, "int", null, null, true),
    AIR_MELEE_DAMAGE_PERCENT("mainAttackAirDamageBonus", null, true, "int", null, null, true),
    ELEMENTAL_MELEE_DAMAGE_PERCENT("mainAttackElementalDamageBonus", null, true, "int", null, null, true),

    RAW_NEUTRAL_MELEE_DAMAGE("mainAttackNeutralDamageBonusRaw", null, false, "int", null, null, true),
    RAW_EARTH_MELEE_DAMAGE("mainAttackEarthDamageBonusRaw", null, false, "int", null, null, true),
    RAW_THUNDER_MELEE_DAMAGE("mainAttackThunderDamageBonusRaw", null, false, "int", null, null, true),
    RAW_WATER_MELEE_DAMAGE("mainAttackWaterDamageBonusRaw", null, false, "int", null, null, true),
    RAW_FIRE_MELEE_DAMAGE("mainAttackFireDamageBonusRaw", null, false, "int", null, null, true),
    RAW_AIR_MELEE_DAMAGE("mainAttackAirDamageBonusRaw", null, false, "int", null, null, true),
    RAW_ELEMENTAL_MELEE_DAMAGE("mainAttackElementalDamageBonusRaw", null, false, "int", null, null, true),

    NEUTRAL_SPELL_DAMAGE_PERCENT("spellNeutralDamageBonus", null, true, "int", null, null, true),
    EARTH_SPELL_DAMAGE_PERCENT("spellEarthDamageBonus", null, true, "int", null, null, true),
    THUNDER_SPELL_DAMAGE_PERCENT("spellThunderDamageBonus", null, true, "int", null, null, true),
    WATER_SPELL_DAMAGE_PERCENT("spellWaterDamageBonus", null, true, "int", null, null, true),
    FIRE_SPELL_DAMAGE_PERCENT("spellFireDamageBonus", null, true, "int", null, null, true),
    AIR_SPELL_DAMAGE_PERCENT("spellAirDamageBonus", null, true, "int", null, null, true),
    ELEMENTAL_SPELL_DAMAGE_PERCENT("spellElementalDamageBonus", null, true, "int", null, null, true),

    RAW_NEUTRAL_SPELL_DAMAGE("spellNeutralDamageBonusRaw", null, false, "int", null, null, true),
    RAW_EARTH_SPELL_DAMAGE("spellEarthDamageBonusRaw", null, false, "int", null, null, true),
    RAW_THUNDER_SPELL_DAMAGE("spellThunderDamageBonusRaw", null, false, "int", null, null, true),
    RAW_WATER_SPELL_DAMAGE("spellWaterDamageBonusRaw", null, false, "int", null, null, true),
    RAW_FIRE_SPELL_DAMAGE("spellFireDamageBonusRaw", null, false, "int", null, null, true),
    RAW_AIR_SPELL_DAMAGE("spellAirDamageBonusRaw", null, false, "int", null, null, true),
    RAW_ELEMENTAL_SPELL_DAMAGE("spellElementalDamageBonusRaw", null, false, "int", null, null, true),

    DURABILITY(null, "durabilityModifier", false, "int", null, "itemOnlyIDs", false),
    DURATION(null, "duration", false, "int", null, "consumableOnlyIDs", false),
    CHARGES(null, "charges", false, "int", null, "consumableOnlyIDs", false),
    INGREDIENT_EFFECTIVENESS_ABOVE(null, "above", true, "int", null, "ingredientPositionModifiers", false),
    INGREDIENT_EFFECTIVENESS_UNDER(null, "under", true, "int", null, "ingredientPositionModifiers", false),
    INGREDIENT_EFFECTIVENESS_RIGHT(null, "right", true, "int", null, "ingredientPositionModifiers", false),
    INGREDIENT_EFFECTIVENESS_LEFT(null, "left", true, "int", null, "ingredientPositionModifiers", false),
    INGREDIENT_EFFECTIVENESS_TOUCHING(null, "touching", true, "int", null, "ingredientPositionModifiers", false),
    INGREDIENT_EFFECTIVENESS_NOT_TOUCHING(null, "notTouching", true, "int", null, "ingredientPositionModifiers", false),
    INGREDIENT_EFFECTIVENESS(null, "ingEffective", true, "sum", SumEnum.INGREDIENT_EFFECTIVENESS, "sum", false),

    SUM_TOTAL_HEALTH("total_health", "total_health", false, "sum", SumEnum.TOTAL_HEALTH, "sum", false),
    SUM_TOTAL_HP_REGEN("total_hp_regen", "total_hp_regen", false, "sum", SumEnum.TOTAL_HEALTH_REGEN, "sum", false),
    SUM_RAW_DEFENSES("raw_defenses", null, false, "sum", SumEnum.RAW_DEFENSES, null, false),
    SUM_RAW_RAINBOW_DEFENSES("raw_rainbow_defense", null, false, "sum", SumEnum.RAW_RAINBOW_DEFENSES, null, false),
    SUM_DEFENSES_PERCENT("defenses", "defenses", true, "sum", SumEnum.DEFENSES_PERCENT, "sum", false),
    SUM_RAINBOW_DEFENSES_PERCENT("rainbow_defense", "rainbow_defense", true, "sum", SumEnum.RAINBOW_DEFENSES_PERCENT, "sum", false),
    SUM_SKILL_POINT_BONUS("sp_bonus", "sp_bonus", false, "sum", SumEnum.SKILL_POINT_BONUS, "sum", false),
    SUM_RAINBOW_SKILL_POINT_BONUS("rainbow_sp_bonus", "rainbow_sp_bonus", false, "sum", SumEnum.RAINBOW_SKILL_POINT_BONUS, "sum", false),

    SUM_BASE_DPS("base_dps", null, false, "sum", SumEnum.BASE_DPS, null, false),
    SUM_NEUTRAL_DPS("neutral_dps", null, false, "sum", SumEnum.NEUTRAL_DPS, null, false),
    SUM_EARTH_DPS("earth_dps", null, false, "sum", SumEnum.EARTH_DPS, null, false),
    SUM_THUNDER_DPS("thunder_dps", null, false, "sum", SumEnum.THUNDER_DPS, null, false),
    SUM_WATER_DPS("water_dps", null, false, "sum", SumEnum.WATER_DPS, null, false),
    SUM_FIRE_DPS("fire_dps", null, false, "sum", SumEnum.FIRE_DPS, null, false),
    SUM_AIR_DPS("air_dps", null, false, "sum", SumEnum.AIR_DPS, null, false),

    SUM_RAW_DAMAGES("raw_damages", null, false, "sum", SumEnum.RAW_DAMAGES, null, false),
    SUM_RAW_RAINBOW_DAMAGES("raw_rainbow_damage", null, false, "sum", SumEnum.RAW_RAINBOW_DAMAGES, null, false),
    SUM_DAMAGES_PERCENT("damages", "damages", true, "sum", SumEnum.DAMAGES_PERCENT, "sum", false),
    SUM_RAINBOW_DAMAGES_PERCENT("rainbow_damage", "rainbow_damage", true, "sum", SumEnum.RAINBOW_DAMAGES_PERCENT, "sum", false),

    SUM_TOTAL_MELEE_DAMAGE("total_melee_damage", null, false, "sum", SumEnum.TOTAL_MELEE_DAMAGE, null, false), //Need Calc Fixes
    SUM_TOTAL_NEUTRAL_MELEE_DAMAGE("total_neutral_melee_damage", null, false, "sum", SumEnum.TOTAL_NEUTRAL_MELEE_DAMAGE, null, false),
    SUM_TOTAL_EARTH_MELEE_DAMAGE("total_earth_damage", null, false, "sum", SumEnum.TOTAL_EARTH_MELEE_DAMAGE, null, false),
    SUM_TOTAL_THUNDER_MELEE_DAMAGE("total_thunder_damage", null, false, "sum", SumEnum.TOTAL_THUNDER_MELEE_DAMAGE, null, false),
    SUM_TOTAL_WATER_MELEE_DAMAGE("total_water_damage", null, false, "sum", SumEnum.TOTAL_WATER_MELEE_DAMAGE, null, false),
    SUM_TOTAL_FIRE_MELEE_DAMAGE("total_fire_damage", null, false, "sum", SumEnum.TOTAL_FIRE_MELEE_DAMAGE, null, false),
    SUM_TOTAL_AIR_MELEE_DAMAGE("total_air_damage", null, false, "sum", SumEnum.TOTAL_AIR_MELEE_DAMAGE, null, false),

    SUM_TOTAL_MELEE_DPS("total_melee_dps", null, false, "sum", SumEnum.TOTAL_MELEE_DPS, null, false), //Need Calc Fixes
    SUM_TOTAL_SPELL_DPS("total_spell_dps", null, false, "sum", SumEnum.TOTAL_SPELL_DPS, null, false), //Need Calc Fixes
    SUM_TOTAL_NEUTRAL_MELEE_DPS("total_neutral_melee_dps", null, false, "sum", SumEnum.TOTAL_NEUTRAL_MELEE_DPS, null, false),
    SUM_TOTAL_NEUTRAL_SPELL_DPS("total_neutral_spell_dps", null, false, "sum", SumEnum.TOTAL_NEUTRAL_SPELL_DPS, null, false),
    SUM_TOTAL_EARTH_MELEE_DPS("total_earth_melee_dps", null, false, "sum", SumEnum.TOTAL_EARTH_MELEE_DPS, null, false),
    SUM_TOTAL_EARTH_SPELL_DPS("total_earth_spell_dps", null, false, "sum", SumEnum.TOTAL_EARTH_SPELL_DPS, null, false),
    SUM_TOTAL_THUNDER_MELEE_DPS( "total_thunder_melee_dps", null, false, "sum", SumEnum.TOTAL_THUNDER_MELEE_DPS, null, false),
    SUM_TOTAL_THUNDER_SPELL_DPS( "total_thunder_spell_dps", null, false, "sum", SumEnum.TOTAL_THUNDER_SPELL_DPS, null, false),
    SUM_TOTAL_WATER_MELEE_DPS( "total_water_melee_dps", null, false, "sum", SumEnum.TOTAL_WATER_MELEE_DPS, null, false),
    SUM_TOTAL_WATER_SPELL_DPS( "total_water_spell_dps", null, false, "sum", SumEnum.TOTAL_WATER_SPELL_DPS, null, false),
    SUM_TOTAL_FIRE_MELEE_DPS("total_fire_melee_dps", null, false, "sum", SumEnum.TOTAL_FIRE_MELEE_DPS, null, false),
    SUM_TOTAL_FIRE_SPELL_DPS("total_fire_spell_dps", null, false, "sum", SumEnum.TOTAL_FIRE_SPELL_DPS, null, false),
    SUM_TOTAL_AIR_MELEE_DPS("total_air_melee_dps", null, false, "sum", SumEnum.TOTAL_AIR_MELEE_DPS, null, false),
    SUM_TOTAL_AIR_SPELL_DPS("total_air_spell_dps", null, false, "sum", SumEnum.TOTAL_AIR_SPELL_DPS, null, false),

    SUM_RAW_SPELL_COSTS("raw_spell_costs", null, false, "sum", SumEnum.RAW_SPELL_COSTS, null, false),
    SUM_SPELL_COSTS_PERCENT("spell_costs", null, true, "sum", SumEnum.SPELL_COSTS_PERCENT, null, false)
    ;

    private final String itemName;
    private final String ingName;
    private final boolean percent;
    private final String idType;
    private final SumEnum sum;
    private final String ingFieldPos;
    private final boolean itemVariable;

    Identifications(String itemName, String ingName, boolean percent, String idType, SumEnum sum, String ingFieldPos, boolean itemVariable) {
        this.itemName = itemName;
        this.ingName = ingName;
        this.percent = percent;
        this.idType = idType;
        this.sum = sum;
        this.ingFieldPos = ingFieldPos;
        this.itemVariable = itemVariable;
    }

    public String getItemName() {
        return this.itemName;
    }

    public String getIngName() {
        return this.ingName;
    }

    public boolean isPercent() {
        return this.percent;
    }

    public String getIDType() {
        return this.idType;
    }

    public SumEnum getSum() {
        return this.sum;
    }

    public String getIngFieldPos() {
        return this.ingFieldPos;
    }

    public boolean isItemVariable() {
        return this.itemVariable;
    }
}
