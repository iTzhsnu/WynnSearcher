package com.github.iTzhsnu.WynnSearcher;

public enum Identifications implements IIdentifications {
    LEVEL("Level","level", "level", false, 1),
    STRENGTH_REQ("Strength Req", "strength", "strengthRequirement", false, 1),
    DEXTERITY_REQ("Dexterity Req", "dexterity", "dexterityRequirement", false, 1),
    INTELLIGENCE_REQ("Intelligence Req", "intelligence", "intelligenceRequirement", false, 1),
    DEFENSE_REQ("Defense Req", "defense", "defenceRequirement", false, 1),
    AGILITY_REQ("Agility Req", "agility", "agilityRequirement", false, 1),
    QUEST_REQ("Quest Req", "quest", null, false, 1),

    HEALTH("Health", "health", null, false, 0),
    HEALTH_BONUS("Health Bonus", "healthBonus", "HEALTHBONUS", false, 0),
    RAW_HEALTH_REGEN("Raw Health Regen", "healthRegenRaw", "HEALTHREGENRAW", false, 0),
    HEALTH_REGEN_PERCENT("Health Regen", "healthRegen", "HEALTHREGEN", true, 0),
    LIFE_STEAL("Life Steal", "lifeSteal", "LIFESTEAL", false, 3),

    STRENGTH("Strength", "strengthPoints", "STRENGTHPOINTS", false, 0),
    DEXTERITY("Dexterity", "dexterityPoints", "DEXTERITYPOINTS", false, 0),
    INTELLIGENCE("Intelligence", "intelligencePoints", "INTELLIGENCEPOINTS", false, 0),
    DEFENSE("Defense", "defensePoints", "DEFENSEPOINTS", false, 0),
    AGILITY("Agility", "agilityPoints", "AGILITYPOINTS", false, 0),

    EARTH_DEFENSE("Earth Defense", "earthDefense", null, false, 0),
    THUNDER_DEFENSE("Thunder Defense", "thunderDefense", null, false, 0),
    WATER_DEFENSE("Water Defense", "waterDefense", null, false, 0),
    FIRE_DEFENSE("Fire Defense", "fireDefense", null, false, 0),
    AIR_DEFENSE("Air Defense", "airDefense", null, false, 0),

    EARTH_DEFENSE_PERCENT("Earth Defense", "bonusEarthDefense", "EARTHDEFENSE", true, 0),
    THUNDER_DEFENSE_PERCENT("Thunder Defense", "bonusThunderDefense", "THUNDERDEFENSE", true, 0),
    WATER_DEFENSE_PERCENT("Water Defense", "bonusWaterDefense", "WATERDEFENSE", true, 0),
    FIRE_DEFENSE_PERCENT("Fire Defense", "bonusFireDefense", "FIREDEFENSE", true, 0),
    AIR_DEFENSE_PERCENT("Air Defense", "bonusAirDefense", "AIRDEFENSE", true, 0),

    NEUTRAL_DAMAGE("Neutral Damage", "damage", null, false, 3),
    EARTH_DAMAGE("Earth Damage", "earthDamage", null, false, 3),
    THUNDER_DAMAGE("Thunder Damage", "thunderDamage", null, false, 3),
    WATER_DAMAGE("Water Damage", "waterDamage", null, false, 3),
    FIRE_DAMAGE("Fire Damage", "fireDamage", null, false, 3),
    AIR_DAMAGE("Air Damage", "airDamage", null, false, 3),

    EARTH_DAMAGE_PERCENT("Earth Damage", "earthDamageBonus", "EARTHDAMAGEBONUS", true, 0),
    THUNDER_DAMAGE_PERCENT("Thunder Damage", "thunderDamageBonus", "THUNDERDAMAGEBONUS", true, 0),
    WATER_DAMAGE_PERCENT("Water Damage", "waterDamageBonus", "WATERDAMAGEBONUS", true, 0),
    FIRE_DAMAGE_PERCENT("Fire Damage", "fireDamageBonus", "FIREDAMAGEBONUS", true, 0),
    AIR_DAMAGE_PERCENT("Air Damage", "airDamageBonus", "AIRDAMAGEBONUS", true, 0),

    RAW_SPELL_DAMAGE("Raw Spell Damage", "spellDamageBonusRaw", "SPELLDAMAGERAW", false, 0),
    RAW_MELEE_DAMAGE("Raw Melee Damage", "mainAttackDamageBonusRaw", "DAMAGEBONUSRAW", false, 0),
    SPELL_DAMAGE_PERCENT("Spell Damage", "spellDamageBonus", "SPELLDAMAGE", true, 0),
    MELEE_DAMAGE_PERCENT("Melee Damage", "mainAttackDamageBonus", "DAMAGEBONUS", true, 0),
    POISON("Poison", "poison", "POISON", false, 3),

    ATTACK_SPEED("Attack Speed", "attackSpeed", null, false, 3),
    ATTACK_SPEED_BONUS("Attack Speed Bonus", "attackSpeedBonus", "ATTACKSPEED", false, 0),

    MANA_REGEN("Mana Regen", "manaRegen", "MANAREGEN", false, 3),
    MANA_STEAL("Mana Steal", "manaSteal", "MANASTEAL", false, 3),

    WALK_SPEED("Walk Speed", "speed", "SPEED", true, 0),
    SPRINT_BONUS("Sprint Bonus", "sprint", "STAMINA", true, 0),
    SPRINT_REGEN("Sprint Regen", "sprintRegen", "STAMINA_REGEN", true, 0),
    JUMP_HEIGHT("Jump Height", "jumpHeight", "JUMP_HEIGHT", false, 0),

    THORNS("Thorns", "thorns", "THORNS", true, 0),
    REFLECTION("Reflection", "reflection", "REFLECTION", true, 0),
    EXPLODING("Exploding", "exploding", "EXPLODING", true, 0),
    STEALING("Stealing", "emeraldStealing", "EMERALDSTEALING", true, 0),
    COMBAT_XP_BONUS("Combat XP Bonus", "xpBonus", "XPBONUS", true, 0),
    GATHERING_XP_BONUS("Gather XP Bonus", "gatherXpBonus", "GATHER_XP_BONUS", true, 0),
    GATHERING_SPEED_BONUS("Gather Speed Bonus", "gatherSpeed", "GATHER_SPEED", true, 0),
    LOOT_BONUS("Loot Bonus", "lootBonus", "LOOTBONUS", true, 0),
    LOOT_QUALITY("Loot Quality", "lootQuality", "LOOT_QUALITY", true, 0),
    SOUL_POINT_REGEN("Soul Point Regen", "soulPoints", "SOULPOINTS", true, 0),
    POWDER_SLOTS("Powder Slot", "sockets", null, false, 0),
    MAJOR_IDS("Major ID", "majorIds", null, false, 3),

    RAW_1ST_SPELL_COST("1st Spell Cost Raw", "spellCostRaw1", null, false, 0),
    RAW_2ND_SPELL_COST("2nd Spell Cost Raw", "spellCostRaw2", null, false, 0),
    RAW_3RD_SPELL_COST("3rd Spell Cost Raw", "spellCostRaw3", null, false, 0),
    RAW_4TH_SPELL_COST("4th Spell Cost Raw", "spellCostRaw4", null, false, 0),

    PERCENT_1ST_SPELL_COST("1st Spell Cost", "spellCostPct1", null, true, 0),
    PERCENT_2ND_SPELL_COST("2nd Spell Cost", "spellCostPct2", null, true, 0),
    PERCENT_3RD_SPELL_COST("3rd Spell Cost", "spellCostPct3", null, true, 0),
    PERCENT_4TH_SPELL_COST("4th Spell Cost", "spellCostPct4", null, true, 0),

    RAW_EARTH_SPELL_DAMAGE("Earth Spell Damage", null, null, false, 0),
    RAW_THUNDER_SPELL_DAMAGE("Thunder Spell Damage", "spellThunderDamageBonusRaw", null, false, 0),
    RAW_WATER_SPELL_DAMAGE("Water Spell Damage", "spellWaterDamageBonus", null, false, 0),
    RAW_FIRE_SPELL_DAMAGE("Fire Spell Damage", "spellFireDamageBonusRaw", null, false, 0),
    RAW_AIR_SPELL_DAMAGE("Air Spell Damage", "spellAirDamageBonusRaw", null, false, 0),
    RAW_ELEMENTAL_SPELL_DAMAGE("Elemental Spell Damage", "spellElementalDamageBonusRaw", null, false, 0),

    DURABILITY("Durability", null, "durabilityModifier", false, 0),
    DURATION("Duration", null, "duration", false, 0),
    CHARGES("Charges", null, "charges", false, 0),
    INGREDIENT_EFFECTIVENESS(null, null, "ingEffectiveness", true, 3),
    INGREDIENT_EFFECTIVENESS_ABOVE("Ingredient Effectiveness (Above)", null, "ingAbove", true, 3),
    INGREDIENT_EFFECTIVENESS_UNDER("Ingredient Effectiveness (Under)", null, "ingUnder", true, 3),
    INGREDIENT_EFFECTIVENESS_RIGHT("Ingredient Effectiveness (Right)", null, "ingRight", true, 3),
    INGREDIENT_EFFECTIVENESS_LEFT("Ingredient Effectiveness (Left)", null, "ingLeft", true, 3),
    INGREDIENT_EFFECTIVENESS_TOUCHING("Ingredient Effectiveness (Touching)", null, "ingTouch", true, 3),
    INGREDIENT_EFFECTIVENESS_NOT_TOUCHING("Ingredient Effectiveness (Not Touching)", null, "ingNotTouch", true, 3),

    SUM_TOTAL_HEALTH(null, "total_health", "total_health", false, 2),
    SUM_RAW_DEFENSES(null, "raw_defenses", null, false, 2),
    SUM_RAW_RAINBOW_DEFENSES(null, "raw_rainbow_defense", null, false, 2),
    SUM_DEFENSES_PERCENT(null, "defenses", "defenses", true, 2),
    SUM_RAINBOW_DEFENSES_PERCENT(null, "rainbow_defense", "rainbow_defense", true, 2),
    SUM_SKILL_POINT_BONUS(null, "sp_bonus", "sp_bonus", false, 2),
    SUM_RAINBOW_SKILL_POINT_BONUS(null, "rainbow_sp_bonus", "rainbow_sp_bonus", false, 2),

    SUM_BASE_DPS(null, "base_dps", null, false, 2),
    SUM_NEUTRAL_DPS(null, "earth_dps", null, false, 2),
    SUM_EARTH_DPS(null, "thunder_dps", null, false, 2),
    SUM_WATER_DPS(null, "water_dps", null, false, 2),
    SUM_FIRE_DPS(null, "fire_dps", null, false, 2),
    SUM_AIR_DPS(null, "air_dps", null, false, 2),

    SUM_RAW_DAMAGES(null, "raw_damages", null, false, 2),
    SUM_RAW_RAINBOW_DAMAGES(null, "raw_rainbow_damage", null, false, 2),
    SUM_DAMAGES_PERCENT(null, "damages", "damages", true, 2),
    SUM_RAINBOW_DAMAGES_PERCENT(null, "rainbow_damage", "rainbow_damage", true, 2),

    SUM_TOTAL_MELEE_DAMAGE(null, "total_melee_damage", null, false, 2),
    SUM_TOTAL_NEUTRAL_MELEE_DAMAGE(null, "total_neutral_melee_damage", null, false, 2),
    SUM_TOTAL_EARTH_DAMAGE(null, "total_earth_damage", null, false, 2),
    SUM_TOTAL_THUNDER_DAMAGE(null, "total_thunder_damage", null, false, 2),
    SUM_TOTAL_WATER_DAMAGE(null, "total_water_damage", null, false, 2),
    SUM_TOTAL_FIRE_DAMAGE(null, "total_fire_damage", null, false, 2),
    SUM_TOTAL_AIR_DAMAGE(null, "total_air_damage", null, false, 2),

    SUM_TOTAL_DPS(null, "total_dps", null, false, 2),
    SUM_TOTAL_MELEE_DPS(null, "total_melee_dps", null, false, 2),
    SUM_TOTAL_SPELL_DPS(null, "total_spell_dps", null, false, 2),
    SUM_TOTAL_NEUTRAL_MELEE_DPS(null, "total_neutral_melee_dps", null, false, 2),
    SUM_TOTAL_NEUTRAL_SPELL_DPS(null, "total_neutral_spell_dps", null, false, 2),
    SUM_TOTAL_EARTH_DPS(null, "total_earth_dps", null, false, 2),
    SUM_TOTAL_THUNDER_DPS(null, "total_thunder_dps", null, false, 2),
    SUM_TOTAL_WATER_DPS(null, "total_water_dps", null, false, 2),
    SUM_TOTAL_FIRE_DPS(null, "total_defense_dps", null, false, 2),
    SUM_TOTAL_AIR_DPS(null, "total_air_dps", null, false, 2),

    SUM_RAW_SPELL_COSTS(null, "raw_spell_costs", null, false, 2),
    SUM_SPELL_COSTS_PERCENT(null, "spell_costs", null, true, 2)
    ;

    private final String displayName;
    private final String itemName;
    private final String ingName;
    private final boolean percent;
    private final int special;

    //Special 0 = not Special, 1 = Require, 2 = Sum, 3 = other Special
    Identifications(String displayName, String itemName, String ingName, boolean percent, int special) {
        this.displayName = displayName;
        this.itemName = itemName;
        this.ingName = ingName;
        this.percent = percent;
        this.special = special;
    }


    @Override
    public String getDisplayName() {
        return this.displayName;
    }

    @Override
    public String getItemName() {
        return this.itemName;
    }

    @Override
    public String getIngName() {
        return this.ingName;
    }

    @Override
    public boolean isPercent() {
        return this.percent;
    }

    @Override
    public int isSpecial() {
        return this.special;
    }
}
