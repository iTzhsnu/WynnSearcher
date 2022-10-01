package com.github.iTzhsnu.WynnSearcher;

public enum Identifications {
    EMPTY(null, null, null, false, null, null, null),

    LEVEL("Level","level", "level", false, "int", null, "nothing"),
    STRENGTH_REQ("Strength Req", "strength", "strengthRequirement", false, "int", null, "itemOnlyIDs"),
    DEXTERITY_REQ("Dexterity Req", "dexterity", "dexterityRequirement", false, "int", null, "itemOnlyIDs"),
    INTELLIGENCE_REQ("Intelligence Req", "intelligence", "intelligenceRequirement", false, "int", null, "itemOnlyIDs"),
    DEFENSE_REQ("Defense Req", "defense", "defenceRequirement", false, "int", null, "itemOnlyIDs"),
    AGILITY_REQ("Agility Req", "agility", "agilityRequirement", false, "int", null, "itemOnlyIDs"),

    HEALTH("Health", "health", null, false, "int", null, null),
    HEALTH_BONUS("Health Bonus", "healthBonus", "HEALTHBONUS", false, "int", null, "identifications"),
    RAW_HEALTH_REGEN("Raw Health Regen", "healthRegenRaw", "HEALTHREGENRAW", false, "int", null, "identifications"),
    HEALTH_REGEN_PERCENT("Health Regen", "healthRegen", "HEALTHREGEN", true, "int", null, "identifications"),
    LIFE_STEAL("Life Steal", "lifeSteal", "LIFESTEAL", false, "int", null, "identifications"),

    STRENGTH("Strength", "strengthPoints", "STRENGTHPOINTS", false, "int", null, "identifications"),
    DEXTERITY("Dexterity", "dexterityPoints", "DEXTERITYPOINTS", false, "int", null, "identifications"),
    INTELLIGENCE("Intelligence", "intelligencePoints", "INTELLIGENCEPOINTS", false, "int", null, "identifications"),
    DEFENSE("Defense", "defensePoints", "DEFENSEPOINTS", false, "int", null, "identifications"),
    AGILITY("Agility", "agilityPoints", "AGILITYPOINTS", false, "int", null, "identifications"),

    EARTH_DEFENSE("Earth Defense", "earthDefense", null, false, "int", null, null),
    THUNDER_DEFENSE("Thunder Defense", "thunderDefense", null, false, "int", null, null),
    WATER_DEFENSE("Water Defense", "waterDefense", null, false, "int", null, null),
    FIRE_DEFENSE("Fire Defense", "fireDefense", null, false, "int", null, null),
    AIR_DEFENSE("Air Defense", "airDefense", null, false, "int", null, null),

    EARTH_DEFENSE_PERCENT("Earth Defense", "bonusEarthDefense", "EARTHDEFENSE", true, "int", null, "identifications"),
    THUNDER_DEFENSE_PERCENT("Thunder Defense", "bonusThunderDefense", "THUNDERDEFENSE", true, "int", null, "identifications"),
    WATER_DEFENSE_PERCENT("Water Defense", "bonusWaterDefense", "WATERDEFENSE", true, "int", null, "identifications"),
    FIRE_DEFENSE_PERCENT("Fire Defense", "bonusFireDefense", "FIREDEFENSE", true, "int", null, "identifications"),
    AIR_DEFENSE_PERCENT("Air Defense", "bonusAirDefense", "AIRDEFENSE", true, "int", null, "identifications"),

    NEUTRAL_DAMAGE("Neutral Damage", "damage", null, false, "damage_string", null, null),
    EARTH_DAMAGE("Earth Damage", "earthDamage", null, false, "damage_string", null, null),
    THUNDER_DAMAGE("Thunder Damage", "thunderDamage", null, false, "damage_string", null, null),
    WATER_DAMAGE("Water Damage", "waterDamage", null, false, "damage_string", null, null),
    FIRE_DAMAGE("Fire Damage", "fireDamage", null, false, "damage_string", null, null),
    AIR_DAMAGE("Air Damage", "airDamage", null, false, "damage_string", null, null),

    EARTH_DAMAGE_PERCENT("Earth Damage", "earthDamageBonus", "EARTHDAMAGEBONUS", true, "int", null, "identifications"),
    THUNDER_DAMAGE_PERCENT("Thunder Damage", "thunderDamageBonus", "THUNDERDAMAGEBONUS", true, "int", null, "identifications"),
    WATER_DAMAGE_PERCENT("Water Damage", "waterDamageBonus", "WATERDAMAGEBONUS", true, "int", null, "identifications"),
    FIRE_DAMAGE_PERCENT("Fire Damage", "fireDamageBonus", "FIREDAMAGEBONUS", true, "int", null, "identifications"),
    AIR_DAMAGE_PERCENT("Air Damage", "airDamageBonus", "AIRDAMAGEBONUS", true, "int", null, "identifications"),

    RAW_SPELL_DAMAGE("Raw Spell Damage", "spellDamageBonusRaw", "SPELLDAMAGERAW", false, "int", null, "identifications"),
    RAW_MELEE_DAMAGE("Raw Melee Damage", "mainAttackDamageBonusRaw", "DAMAGEBONUSRAW", false, "int", null, "identifications"),
    SPELL_DAMAGE_PERCENT("Spell Damage", "spellDamageBonus", "SPELLDAMAGE", true, "int", null, "identifications"),
    MELEE_DAMAGE_PERCENT("Melee Damage", "mainAttackDamageBonus", "DAMAGEBONUS", true, "int", null, "identifications"),
    POISON("Poison", "poison", "POISON", false, "int", null, "identifications"),

    ATTACK_SPEED("Attack Speed", "attackSpeed", null, false, "string", null, null),
    ATTACK_SPEED_BONUS("Attack Speed Bonus", "attackSpeedBonus", "ATTACKSPEED", false, "int", null, "identifications"),

    MANA_REGEN("Mana Regen", "manaRegen", "MANAREGEN", false, "int", null, "identifications"),
    MANA_STEAL("Mana Steal", "manaSteal", "MANASTEAL", false, "int", null, "identifications"),

    WALK_SPEED("Walk Speed", "speed", "SPEED", true, "int", null, "identifications"),
    SPRINT_BONUS("Sprint Bonus", "sprint", "STAMINA", true, "int", null, "identifications"),
    SPRINT_REGEN("Sprint Regen", "sprintRegen", "STAMINA_REGEN", true, "int", null, "identifications"),
    JUMP_HEIGHT("Jump Height", "jumpHeight", "JUMP_HEIGHT", false, "int", null, "identifications"),

    THORNS("Thorns", "thorns", "THORNS", true, "int", null, "identifications"),
    REFLECTION("Reflection", "reflection", "REFLECTION", true, "int", null, "identifications"),
    EXPLODING("Exploding", "exploding", "EXPLODING", true, "int", null, "identifications"),
    STEALING("Stealing", "emeraldStealing", "EMERALDSTEALING", true, "int", null, "identifications"),
    COMBAT_XP_BONUS("Combat XP Bonus", "xpBonus", "XPBONUS", true, "int", null, "identifications"),
    GATHERING_XP_BONUS("Gather XP Bonus", "gatherXpBonus", "GATHER_XP_BONUS", true, "int", null, "identifications"),
    GATHERING_SPEED_BONUS("Gather Speed Bonus", "gatherSpeed", "GATHER_SPEED", true, "int", null, "identifications"),
    LOOT_BONUS("Loot Bonus", "lootBonus", "LOOTBONUS", true, "int", null, "identifications"),
    LOOT_QUALITY("Loot Quality", "lootQuality", "LOOT_QUALITY", true, "int", null, "identifications"),
    SOUL_POINT_REGEN("Soul Point Regen", "soulPoints", "SOULPOINTS", true, "int", null, "identifications"),
    POWDER_SLOTS("Powder Slot", "sockets", null, false, "int", null, null),
    MAJOR_IDS("Major ID", "majorIds", null, false, "string", null, null),

    RAW_1ST_SPELL_COST("1st Spell Cost Raw", "spellCostRaw1", null, false, "int", null, null),
    RAW_2ND_SPELL_COST("2nd Spell Cost Raw", "spellCostRaw2", null, false, "int", null, null),
    RAW_3RD_SPELL_COST("3rd Spell Cost Raw", "spellCostRaw3", null, false, "int", null, null),
    RAW_4TH_SPELL_COST("4th Spell Cost Raw", "spellCostRaw4", null, false, "int", null, null),

    PERCENT_1ST_SPELL_COST("1st Spell Cost", "spellCostPct1", null, true, "int", null, null),
    PERCENT_2ND_SPELL_COST("2nd Spell Cost", "spellCostPct2", null, true, "int", null, null),
    PERCENT_3RD_SPELL_COST("3rd Spell Cost", "spellCostPct3", null, true, "int", null, null),
    PERCENT_4TH_SPELL_COST("4th Spell Cost", "spellCostPct4", null, true, "int", null, null),

    RAW_EARTH_SPELL_DAMAGE("Earth Spell Damage", "spellEarthDamageBonusRaw", null, false, "int", null, null),
    RAW_THUNDER_SPELL_DAMAGE("Thunder Spell Damage", "spellThunderDamageBonusRaw", null, false, "int", null, null),
    RAW_WATER_SPELL_DAMAGE("Water Spell Damage", "spellWaterDamageBonus", null, false, "int", null, null),
    RAW_FIRE_SPELL_DAMAGE("Fire Spell Damage", "spellFireDamageBonusRaw", null, false, "int", null, null),
    RAW_AIR_SPELL_DAMAGE("Air Spell Damage", "spellAirDamageBonusRaw", null, false, "int", null, null),
    RAW_ELEMENTAL_SPELL_DAMAGE("Elemental Spell Damage", "spellElementalDamageBonusRaw", null, false, "int", null, null),

    DURABILITY("Durability", null, "durabilityModifier", false, "int", null, "itemOnlyIDs"),
    DURATION("Duration", null, "duration", false, "int", null, "consumableOnlyIDs"),
    CHARGES("Charges", null, "charges", false, "int", null, "consumableOnlyIDs"),
    INGREDIENT_EFFECTIVENESS_ABOVE("Ingredient Effectiveness (Above)", null, "above", true, "int", null, "ingredientPositionModifiers"),
    INGREDIENT_EFFECTIVENESS_UNDER("Ingredient Effectiveness (Under)", null, "under", true, "int", null, "ingredientPositionModifiers"),
    INGREDIENT_EFFECTIVENESS_RIGHT("Ingredient Effectiveness (Right)", null, "right", true, "int", null, "ingredientPositionModifiers"),
    INGREDIENT_EFFECTIVENESS_LEFT("Ingredient Effectiveness (Left)", null, "left", true, "int", null, "ingredientPositionModifiers"),
    INGREDIENT_EFFECTIVENESS_TOUCHING("Ingredient Effectiveness (Touching)", null, "touching", true, "int", null, "ingredientPositionModifiers"),
    INGREDIENT_EFFECTIVENESS_NOT_TOUCHING("Ingredient Effectiveness (Not Touching)", null, "notTouching", true, "int", null, "ingredientPositionModifiers"),
    INGREDIENT_EFFECTIVENESS(null, null, "ingEffective", true, "sum", SumEnum.INGREDIENT_EFFECTIVENESS, "sum"),

    SUM_TOTAL_HEALTH(null, "total_health", "total_health", false, "sum", SumEnum.TOTAL_HEALTH, "sum"),
    SUM_RAW_DEFENSES(null, "raw_defenses", null, false, "sum", SumEnum.RAW_DEFENSES, null),
    SUM_RAW_RAINBOW_DEFENSES(null, "raw_rainbow_defense", null, false, "sum", SumEnum.RAW_RAINBOW_DEFENSES, null),
    SUM_DEFENSES_PERCENT(null, "defenses", "defenses", true, "sum", SumEnum.DEFENSES_PERCENT, "sum"),
    SUM_RAINBOW_DEFENSES_PERCENT(null, "rainbow_defense", "rainbow_defense", true, "sum", SumEnum.RAINBOW_DEFENSES_PERCENT, "sum"),
    SUM_SKILL_POINT_BONUS(null, "sp_bonus", "sp_bonus", false, "sum", SumEnum.SKILL_POINT_BONUS, "sum"),
    SUM_RAINBOW_SKILL_POINT_BONUS(null, "rainbow_sp_bonus", "rainbow_sp_bonus", false, "sum", SumEnum.RAINBOW_SKILL_POINT_BONUS, "sum"),

    SUM_BASE_DPS(null, "base_dps", null, false, "sum", SumEnum.BASE_DPS, null),
    SUM_NEUTRAL_DPS(null, "neutral_dps", null, false, "sum", SumEnum.NEUTRAL_DPS, null),
    SUM_EARTH_DPS(null, "earth_dps", null, false, "sum", SumEnum.EARTH_DPS, null),
    SUM_THUNDER_DPS(null, "thunder_dps", null, false, "sum", SumEnum.THUNDER_DPS, null),
    SUM_WATER_DPS(null, "water_dps", null, false, "sum", SumEnum.WATER_DPS, null),
    SUM_FIRE_DPS(null, "fire_dps", null, false, "sum", SumEnum.FIRE_DPS, null),
    SUM_AIR_DPS(null, "air_dps", null, false, "sum", SumEnum.AIR_DPS, null),

    SUM_RAW_DAMAGES(null, "raw_damages", null, false, "sum", SumEnum.RAW_DAMAGES, null),
    SUM_RAW_RAINBOW_DAMAGES(null, "raw_rainbow_damage", null, false, "sum", SumEnum.RAW_RAINBOW_DAMAGES, null),
    SUM_DAMAGES_PERCENT(null, "damages", "damages", true, "sum", SumEnum.DAMAGES_PERCENT, "sum"),
    SUM_RAINBOW_DAMAGES_PERCENT(null, "rainbow_damage", "rainbow_damage", true, "sum", SumEnum.RAINBOW_DAMAGES_PERCENT, "sum"),

    SUM_TOTAL_MELEE_DAMAGE(null, "total_melee_damage", null, false, "sum", SumEnum.TOTAL_MELEE_DAMAGE, null),
    SUM_TOTAL_NEUTRAL_MELEE_DAMAGE(null, "total_neutral_melee_damage", null, false, "sum", SumEnum.TOTAL_NEUTRAL_MELEE_DAMAGE, null),
    SUM_TOTAL_EARTH_DAMAGE(null, "total_earth_damage", null, false, "sum", SumEnum.TOTAL_EARTH_DAMAGE, null),
    SUM_TOTAL_THUNDER_DAMAGE(null, "total_thunder_damage", null, false, "sum", SumEnum.TOTAL_THUNDER_DAMAGE, null),
    SUM_TOTAL_WATER_DAMAGE(null, "total_water_damage", null, false, "sum", SumEnum.TOTAL_WATER_DAMAGE, null),
    SUM_TOTAL_FIRE_DAMAGE(null, "total_fire_damage", null, false, "sum", SumEnum.TOTAL_FIRE_DAMAGE, null),
    SUM_TOTAL_AIR_DAMAGE(null, "total_air_damage", null, false, "sum", SumEnum.TOTAL_AIR_DAMAGE, null),

    SUM_TOTAL_DPS(null, "total_dps", null, false, "sum", SumEnum.TOTAL_DPS, null),
    SUM_TOTAL_MELEE_DPS(null, "total_melee_dps", null, false, "sum", SumEnum.TOTAL_MELEE_DPS, null),
    SUM_TOTAL_SPELL_DPS(null, "total_spell_dps", null, false, "sum", SumEnum.TOTAL_SPELL_DPS, null),
    SUM_TOTAL_NEUTRAL_MELEE_DPS(null, "total_neutral_melee_dps", null, false, "sum", SumEnum.TOTAL_NEUTRAL_MELEE_DPS, null),
    SUM_TOTAL_NEUTRAL_SPELL_DPS(null, "total_neutral_spell_dps", null, false, "sum", SumEnum.TOTAL_NEUTRAL_SPELL_DPS, null),
    SUM_TOTAL_EARTH_DPS(null, "total_earth_dps", null, false, "sum", SumEnum.TOTAL_EARTH_DPS, null),
    SUM_TOTAL_THUNDER_DPS(null, "total_thunder_dps", null, false, "sum", SumEnum.TOTAL_THUNDER_DPS, null),
    SUM_TOTAL_WATER_DPS(null, "total_water_dps", null, false, "sum", SumEnum.TOTAL_WATER_DPS, null),
    SUM_TOTAL_FIRE_DPS(null, "total_defense_dps", null, false, "sum", SumEnum.TOTAL_FIRE_DPS, null),
    SUM_TOTAL_AIR_DPS(null, "total_air_dps", null, false, "sum", SumEnum.TOTAL_AIR_DPS, null),

    SUM_RAW_SPELL_COSTS(null, "raw_spell_costs", null, false, "sum", SumEnum.RAW_SPELL_COSTS, null),
    SUM_SPELL_COSTS_PERCENT(null, "spell_costs", null, true, "sum", SumEnum.SPELL_COSTS_PERCENT, null)
    ;

    private final String displayName;
    private final String itemName;
    private final String ingName;
    private final boolean percent;
    private final String idType;
    private final SumEnum sum;
    private final String ingFieldPos;

    //Special 0 = not Special, 1 = Require, 2 = Sum, 3 = other Special
    Identifications(String displayName, String itemName, String ingName, boolean percent, String idType, SumEnum sum, String ingFieldPos) {
        this.displayName = displayName;
        this.itemName = itemName;
        this.ingName = ingName;
        this.percent = percent;
        this.idType = idType;
        this.sum = sum;
        this.ingFieldPos = ingFieldPos;
    }

    public String getDisplayName() {
        return this.displayName;
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
}
