package com.github.iTzhsnu.WynnSearcher;

public enum Identifications {
    EMPTY(null, null, null, "", "", null, null, false),

    LEVEL("Lv. Min","level", "level", "", "int", null, "nothing", false),
    STRENGTH_REQ("Strength Req", "strength", "strengthRequirement", "", "int", null, "itemOnlyIDs", false),
    DEXTERITY_REQ("Dexterity Req", "dexterity", "dexterityRequirement", "", "int", null, "itemOnlyIDs", false),
    INTELLIGENCE_REQ("Intelligence Req", "intelligence", "intelligenceRequirement", "", "int", null, "itemOnlyIDs", false),
    DEFENSE_REQ("Defense Req", "defense", "defenceRequirement", "", "int", null, "itemOnlyIDs", false),
    AGILITY_REQ("Agility Req", "agility", "agilityRequirement", "", "int", null, "itemOnlyIDs", false),
    QUEST_REQ("Quest Req", "quest", null, "", "string", null, null, false),
    UNTRADABLE("Untradable", "restrictions", null, "", "string", null, null, false),

    HEALTH("Health", "health", null, "", "int", null, null, false),
    HEALTH_BONUS("Health Bonus", "healthBonus", "HEALTHBONUS", "", "int", null, "identifications", true),
    RAW_HEALTH_REGEN("Raw Health Regen", "healthRegenRaw", "HEALTHREGENRAW", "", "int", null, "identifications", true),
    HEALTH_REGEN_PERCENT("Health Regen", "healthRegen", "HEALTHREGEN", "%", "int", null, "identifications", true),
    LIFE_STEAL("Life Steal", "lifeSteal", "LIFESTEAL", "/3s", "int", null, "identifications", true),

    STRENGTH("Strength", "strengthPoints", "STRENGTHPOINTS", "", "int", null, "identifications", false),
    DEXTERITY("Dexterity", "dexterityPoints", "DEXTERITYPOINTS", "", "int", null, "identifications", false),
    INTELLIGENCE("Intelligence", "intelligencePoints", "INTELLIGENCEPOINTS", "", "int", null, "identifications", false),
    DEFENSE("Defense", "defensePoints", "DEFENSEPOINTS", "", "int", null, "identifications", false),
    AGILITY("Agility", "agilityPoints", "AGILITYPOINTS", "", "int", null, "identifications", false),

    EARTH_DEFENSE("Earth Defense", "earthDefense", null, "", "int", null, null, false),
    THUNDER_DEFENSE("Thunder Defense", "thunderDefense", null, "", "int", null, null, false),
    WATER_DEFENSE("Water Defense", "waterDefense", null, "", "int", null, null, false),
    FIRE_DEFENSE("Fire Defense", "fireDefense", null, "", "int", null, null, false),
    AIR_DEFENSE("Air Defense", "airDefense", null, "", "int", null, null, false),

    EARTH_DEFENSE_PERCENT("Earth Defense", "bonusEarthDefense", "EARTHDEFENSE", "%", "int", null, "identifications", true),
    THUNDER_DEFENSE_PERCENT("Thunder Defense", "bonusThunderDefense", "THUNDERDEFENSE", "%", "int", null, "identifications", true),
    WATER_DEFENSE_PERCENT("Water Defense", "bonusWaterDefense", "WATERDEFENSE", "%", "int", null, "identifications", true),
    FIRE_DEFENSE_PERCENT("Fire Defense", "bonusFireDefense", "FIREDEFENSE", "%", "int", null, "identifications", true),
    AIR_DEFENSE_PERCENT("Air Defense", "bonusAirDefense", "AIRDEFENSE", "%", "int", null, "identifications", true),

    NEUTRAL_DAMAGE("Neutral Damage", "damage", null, "", "damage_string", null, null, false),
    EARTH_DAMAGE("Earth Damage", "earthDamage", null, "", "damage_string", null, null, false),
    THUNDER_DAMAGE("Thunder Damage", "thunderDamage", null, "", "damage_string", null, null, false),
    WATER_DAMAGE("Water Damage", "waterDamage", null, "", "damage_string", null, null, false),
    FIRE_DAMAGE("Fire Damage", "fireDamage", null, "", "damage_string", null, null, false),
    AIR_DAMAGE("Air Damage", "airDamage", null, "", "damage_string", null, null, false),

    EARTH_DAMAGE_PERCENT("Earth Damage", "earthDamageBonus", "EARTHDAMAGEBONUS", "%", "int", null, "identifications", true),
    THUNDER_DAMAGE_PERCENT("Thunder Damage", "thunderDamageBonus", "THUNDERDAMAGEBONUS", "%", "int", null, "identifications", true),
    WATER_DAMAGE_PERCENT("Water Damage", "waterDamageBonus", "WATERDAMAGEBONUS", "%", "int", null, "identifications", true),
    FIRE_DAMAGE_PERCENT("Fire Damage", "fireDamageBonus", "FIREDAMAGEBONUS", "%", "int", null, "identifications", true),
    AIR_DAMAGE_PERCENT("Air Damage", "airDamageBonus", "AIRDAMAGEBONUS", "%", "int", null, "identifications", true),
    ELEMENTAL_DAMAGE_PERCENT("Elemental Damage", "elementalDamageBonus", null, "%", "int", null, null, true),

    RAW_ELEMENTAL_DAMAGE("Raw Elemental Damage", "elementalDamageBonusRaw", null, "", "int", null, null, true),
    RAW_SPELL_DAMAGE("Raw Spell Damage", "spellDamageBonusRaw", "SPELLDAMAGERAW", "", "int", null, "identifications", true),
    RAW_MELEE_DAMAGE("Raw Melee Damage", "mainAttackDamageBonusRaw", "DAMAGEBONUSRAW", "", "int", null, "identifications", true),
    SPELL_DAMAGE_PERCENT("Spell Damage", "spellDamageBonus", "SPELLDAMAGE", "%", "int", null, "identifications", true),
    MELEE_DAMAGE_PERCENT("Melee Damage", "mainAttackDamageBonus", "DAMAGEBONUS", "%", "int", null, "identifications", true),
    POISON("Poison", "poison", "POISON", "/3s", "int", null, "identifications", true),

    ATTACK_SPEED("Attack Speed", "attackSpeed", null, "", "string", null, null, false),
    ATTACK_SPEED_BONUS("Attack Speed Bonus", "attackSpeedBonus", "ATTACKSPEED", "tier", "int", null, "identifications", true),

    MANA_REGEN("Mana Regen", "manaRegen", "MANAREGEN", "/5s", "int", null, "identifications", true),
    MANA_STEAL("Mana Steal", "manaSteal", "MANASTEAL", "/3s", "int", null, "identifications", true),

    WALK_SPEED("Walk Speed", "speed", "SPEED", "%", "int", null, "identifications", true),
    SPRINT_BONUS("Sprint Bonus", "sprint", "STAMINA", "%", "int", null, "identifications", true),
    SPRINT_REGEN("Sprint Regen", "sprintRegen", "STAMINA_REGEN", "%", "int", null, "identifications", true),
    JUMP_HEIGHT("Jump Height", "jumpHeight", "JUMP_HEIGHT", "", "int", null, "identifications", true),

    THORNS("Thorns", "thorns", "THORNS", "%", "int", null, "identifications", true),
    REFLECTION("Reflection", "reflection", "REFLECTION", "%", "int", null, "identifications", true),
    EXPLODING("Exploding", "exploding", "EXPLODING", "%", "int", null, "identifications", true),
    STEALING("Stealing", "emeraldStealing", "EMERALDSTEALING", "%", "int", null, "identifications", true),
    COMBAT_XP_BONUS("Combat XP Bonus", "xpBonus", "XPBONUS", "%", "int", null, "identifications", true),
    GATHERING_XP_BONUS("Gathering XP Bonus", "gatherXpBonus", "GATHER_XP_BONUS", "%", "int", null, "identifications", true),
    GATHERING_SPEED_BONUS("Gathering Speed Bonus", "gatherSpeed", "GATHER_SPEED", "%", "int", null, "identifications", true),
    LOOT_BONUS("Loot Bonus", "lootBonus", "LOOTBONUS", "%", "int", null, "identifications", true),
    LOOT_QUALITY("Loot Quality", "lootQuality", "LOOT_QUALITY", "%", "int", null, "identifications", true),
    SOUL_POINT_REGEN("Soul Point Regen", "soulPoints", "SOULPOINTS", "%", "int", null, "identifications", true),
    POWDER_SLOTS("Powder Slots", "sockets", null, "", "int", null, null, false),
    MAJOR_IDS("Major ID", "majorIds", null, "", "string", null, null, false),

    RAW_1ST_SPELL_COST("Raw 1st Spell Cost", "spellCostRaw1", null, "", "int", null, null, true),
    RAW_2ND_SPELL_COST("Raw 2nd Spell Cost", "spellCostRaw2", null, "", "int", null, null, true),
    RAW_3RD_SPELL_COST("Raw 3rd Spell Cost", "spellCostRaw3", null, "", "int", null, null, true),
    RAW_4TH_SPELL_COST("Raw 4th Spell Cost", "spellCostRaw4", null, "", "int", null, null, true),

    PERCENT_1ST_SPELL_COST("1st Spell Cost", "spellCostPct1", null, "%", "int", null, null, true),
    PERCENT_2ND_SPELL_COST("2nd Spell Cost", "spellCostPct2", null, "%", "int", null, null, true),
    PERCENT_3RD_SPELL_COST("3rd Spell Cost", "spellCostPct3", null, "%", "int", null, null, true),
    PERCENT_4TH_SPELL_COST("4th Spell Cost", "spellCostPct4", null, "%", "int", null, null, true),

    NEUTRAL_MELEE_DAMAGE_PERCENT("Neutral Melee Damage", "mainAttackNeutralDamageBonus", null, "%", "int", null, null, true),
    EARTH_MELEE_DAMAGE_PERCENT("Earth Melee Damage", "mainAttackEarthDamageBonus", null, "%", "int", null, null, true),
    THUNDER_MELEE_DAMAGE_PERCENT("Thunder Melee Damage", "mainAttackThunderDamageBonus", null, "%", "int", null, null, true),
    WATER_MELEE_DAMAGE_PERCENT("Water Melee Damage", "mainAttackWaterDamageBonus", null, "%", "int", null, null, true),
    FIRE_MELEE_DAMAGE_PERCENT("Fire Melee Damage", "mainAttackFireDamageBonus", null, "%", "int", null, null, true),
    AIR_MELEE_DAMAGE_PERCENT("Air Melee Damage", "mainAttackAirDamageBonus", null, "%", "int", null, null, true),
    ELEMENTAL_MELEE_DAMAGE_PERCENT("Elem. Melee Damage", "mainAttackElementalDamageBonus", null, "%", "int", null, null, true),

    RAW_NEUTRAL_MELEE_DAMAGE("Raw Neutral Melee Damage", "mainAttackNeutralDamageBonusRaw", null, "", "int", null, null, true),
    RAW_EARTH_MELEE_DAMAGE("Raw Earth Melee Damage", "mainAttackEarthDamageBonusRaw", null, "", "int", null, null, true),
    RAW_THUNDER_MELEE_DAMAGE("Raw Thunder Melee Damage", "mainAttackThunderDamageBonusRaw", null, "", "int", null, null, true),
    RAW_WATER_MELEE_DAMAGE("Raw Water Melee Damage", "mainAttackWaterDamageBonusRaw", null, "", "int", null, null, true),
    RAW_FIRE_MELEE_DAMAGE("Raw Fire Melee Damage", "mainAttackFireDamageBonusRaw", null, "", "int", null, null, true),
    RAW_AIR_MELEE_DAMAGE("Raw Air Melee Damage", "mainAttackAirDamageBonusRaw", null, "", "int", null, null, true),
    RAW_ELEMENTAL_MELEE_DAMAGE("Raw Elem. Melee Damage", "mainAttackElementalDamageBonusRaw", null, "", "int", null, null, true),

    NEUTRAL_SPELL_DAMAGE_PERCENT("Neutral Spell Damage", "spellNeutralDamageBonus", null, "%", "int", null, null, true),
    EARTH_SPELL_DAMAGE_PERCENT("Earth Spell Damage", "spellEarthDamageBonus", null, "%", "int", null, null, true),
    THUNDER_SPELL_DAMAGE_PERCENT("Thunder Spell Damage", "spellThunderDamageBonus", null, "%", "int", null, null, true),
    WATER_SPELL_DAMAGE_PERCENT("Water Spell Damage", "spellWaterDamageBonus", null, "%", "int", null, null, true),
    FIRE_SPELL_DAMAGE_PERCENT("Fire Spell Damage", "spellFireDamageBonus", null, "%", "int", null, null, true),
    AIR_SPELL_DAMAGE_PERCENT("Air Spell Damage", "spellAirDamageBonus", null, "%", "int", null, null, true),
    ELEMENTAL_SPELL_DAMAGE_PERCENT("Elem. Spell Damage", "spellElementalDamageBonus", null, "%", "int", null, null, true),

    RAW_NEUTRAL_SPELL_DAMAGE("Raw Neutral Spell Damage", "spellNeutralDamageBonusRaw", null, "", "int", null, null, true),
    RAW_EARTH_SPELL_DAMAGE("Raw Earth Spell Damage", "spellEarthDamageBonusRaw", null, "", "int", null, null, true),
    RAW_THUNDER_SPELL_DAMAGE("Raw Thunder Spell Damage", "spellThunderDamageBonusRaw", null, "", "int", null, null, true),
    RAW_WATER_SPELL_DAMAGE("Raw Water Spell Damage", "spellWaterDamageBonusRaw", null, "", "int", null, null, true),
    RAW_FIRE_SPELL_DAMAGE("Raw Fire Spell Damage", "spellFireDamageBonusRaw", null, "", "int", null, null, true),
    RAW_AIR_SPELL_DAMAGE("Raw Air Spell Damage", "spellAirDamageBonusRaw", null, "", "int", null, null, true),
    RAW_ELEMENTAL_SPELL_DAMAGE("Raw Elem. Spell Damage", "spellElementalDamageBonusRaw", null, "", "int", null, null, true),

    DURABILITY("Durability", null, "durabilityModifier", "", "int", null, "itemOnlyIDs", false),
    DURATION("Duration", null, "duration", "", "int", null, "consumableOnlyIDs", false),
    CHARGES("Charges", null, "charges", "", "int", null, "consumableOnlyIDs", false),
    INGREDIENT_EFFECTIVENESS_ABOVE("Ingredient Effectiveness (Above)", null, "above", "%", "int", null, "ingredientPositionModifiers", false),
    INGREDIENT_EFFECTIVENESS_UNDER("Ingredient Effectiveness (Under)", null, "under", "%", "int", null, "ingredientPositionModifiers", false),
    INGREDIENT_EFFECTIVENESS_RIGHT("Ingredient Effectiveness (Right)", null, "right", "%", "int", null, "ingredientPositionModifiers", false),
    INGREDIENT_EFFECTIVENESS_LEFT("Ingredient Effectiveness (Left)", null, "left", "%", "int", null, "ingredientPositionModifiers", false),
    INGREDIENT_EFFECTIVENESS_TOUCHING("Ingredient Effectiveness (Touch)", null, "touching", "%", "int", null, "ingredientPositionModifiers", false),
    INGREDIENT_EFFECTIVENESS_NOT_TOUCHING("Ingredient Effectiveness (Not Touch)", null, "notTouching", "%", "int", null, "ingredientPositionModifiers", false),
    INGREDIENT_EFFECTIVENESS("", null, "ingEffective", "", "sum", SumEnum.INGREDIENT_EFFECTIVENESS, "sum", false),

    SUM_TOTAL_HEALTH("", "total_health", "total_health", "", "sum", SumEnum.TOTAL_HEALTH, "sum", false),
    SUM_TOTAL_HP_REGEN("", "total_hp_regen", "total_hp_regen", "", "sum", SumEnum.TOTAL_HEALTH_REGEN, "sum", false),
    SUM_RAW_DEFENSES("", "raw_defenses", null, "", "sum", SumEnum.RAW_DEFENSES, null, false),
    SUM_RAW_RAINBOW_DEFENSES("", "raw_rainbow_defense", null, "", "sum", SumEnum.RAW_RAINBOW_DEFENSES, null, false),
    SUM_DEFENSES_PERCENT("", "defenses", "defenses", "", "sum", SumEnum.DEFENSES_PERCENT, "sum", false),
    SUM_RAINBOW_DEFENSES_PERCENT("", "rainbow_defense", "rainbow_defense", "", "sum", SumEnum.RAINBOW_DEFENSES_PERCENT, "sum", false),
    SUM_SKILL_POINT_BONUS("", "sp_bonus", "sp_bonus", "", "sum", SumEnum.SKILL_POINT_BONUS, "sum", false),
    SUM_RAINBOW_SKILL_POINT_BONUS("", "rainbow_sp_bonus", "rainbow_sp_bonus", "", "sum", SumEnum.RAINBOW_SKILL_POINT_BONUS, "sum", false),

    SUM_BASE_DPS("", "base_dps", null, "", "sum", SumEnum.BASE_DPS, null, false),
    SUM_NEUTRAL_DPS("", "neutral_dps", null, "", "sum", SumEnum.NEUTRAL_DPS, null, false),
    SUM_EARTH_DPS("", "earth_dps", null, "", "sum", SumEnum.EARTH_DPS, null, false),
    SUM_THUNDER_DPS("", "thunder_dps", null, "", "sum", SumEnum.THUNDER_DPS, null, false),
    SUM_WATER_DPS("", "water_dps", null, "", "sum", SumEnum.WATER_DPS, null, false),
    SUM_FIRE_DPS("", "fire_dps", null, "", "sum", SumEnum.FIRE_DPS, null, false),
    SUM_AIR_DPS("", "air_dps", null, "", "sum", SumEnum.AIR_DPS, null, false),

    SUM_RAW_DAMAGES("", "raw_damages", null, "", "sum", SumEnum.RAW_DAMAGES, null, false),
    SUM_RAW_RAINBOW_DAMAGES("", "raw_rainbow_damage", null, "", "sum", SumEnum.RAW_RAINBOW_DAMAGES, null, false),
    SUM_DAMAGES_PERCENT("", "damages", "damages", "", "sum", SumEnum.DAMAGES_PERCENT, "sum", false),
    SUM_RAINBOW_DAMAGES_PERCENT("", "rainbow_damage", "rainbow_damage", "", "sum", SumEnum.RAINBOW_DAMAGES_PERCENT, "sum", false),

    SUM_TOTAL_MELEE_DAMAGE("", "total_melee_damage", null, "", "sum", SumEnum.TOTAL_MELEE_DAMAGE, null, false),
    SUM_TOTAL_NEUTRAL_MELEE_DAMAGE("", "total_neutral_melee_damage", null, "", "sum", SumEnum.TOTAL_NEUTRAL_MELEE_DAMAGE, null, false),
    SUM_TOTAL_EARTH_MELEE_DAMAGE("", "total_earth_damage", null, "", "sum", SumEnum.TOTAL_EARTH_MELEE_DAMAGE, null, false),
    SUM_TOTAL_THUNDER_MELEE_DAMAGE("", "total_thunder_damage", null, "", "sum", SumEnum.TOTAL_THUNDER_MELEE_DAMAGE, null, false),
    SUM_TOTAL_WATER_MELEE_DAMAGE("", "total_water_damage", null, "", "sum", SumEnum.TOTAL_WATER_MELEE_DAMAGE, null, false),
    SUM_TOTAL_FIRE_MELEE_DAMAGE("", "total_fire_damage", null, "", "sum", SumEnum.TOTAL_FIRE_MELEE_DAMAGE, null, false),
    SUM_TOTAL_AIR_MELEE_DAMAGE("", "total_air_damage", null, "", "sum", SumEnum.TOTAL_AIR_MELEE_DAMAGE, null, false),

    SUM_TOTAL_MELEE_DPS("", "total_melee_dps", null, "", "sum", SumEnum.TOTAL_MELEE_DPS, null, false),
    SUM_TOTAL_SPELL_DPS("", "total_spell_dps", null, "", "sum", SumEnum.TOTAL_SPELL_DPS, null, false),
    SUM_TOTAL_NEUTRAL_MELEE_DPS("", "total_neutral_melee_dps", null, "", "sum", SumEnum.TOTAL_NEUTRAL_MELEE_DPS, null, false),
    SUM_TOTAL_NEUTRAL_SPELL_DPS("", "total_neutral_spell_dps", null, "", "sum", SumEnum.TOTAL_NEUTRAL_SPELL_DPS, null, false),
    SUM_TOTAL_EARTH_MELEE_DPS("", "total_earth_melee_dps", null, "", "sum", SumEnum.TOTAL_EARTH_MELEE_DPS, null, false),
    SUM_TOTAL_EARTH_SPELL_DPS("", "total_earth_spell_dps", null, "", "sum", SumEnum.TOTAL_EARTH_SPELL_DPS, null, false),
    SUM_TOTAL_THUNDER_MELEE_DPS("", "total_thunder_melee_dps", null, "", "sum", SumEnum.TOTAL_THUNDER_MELEE_DPS, null, false),
    SUM_TOTAL_THUNDER_SPELL_DPS("", "total_thunder_spell_dps", null, "", "sum", SumEnum.TOTAL_THUNDER_SPELL_DPS, null, false),
    SUM_TOTAL_WATER_MELEE_DPS("", "total_water_melee_dps", null, "", "sum", SumEnum.TOTAL_WATER_MELEE_DPS, null, false),
    SUM_TOTAL_WATER_SPELL_DPS("", "total_water_spell_dps", null, "", "sum", SumEnum.TOTAL_WATER_SPELL_DPS, null, false),
    SUM_TOTAL_FIRE_MELEE_DPS("", "total_fire_melee_dps", null, "", "sum", SumEnum.TOTAL_FIRE_MELEE_DPS, null, false),
    SUM_TOTAL_FIRE_SPELL_DPS("", "total_fire_spell_dps", null, "", "sum", SumEnum.TOTAL_FIRE_SPELL_DPS, null, false),
    SUM_TOTAL_AIR_MELEE_DPS("", "total_air_melee_dps", null, "", "sum", SumEnum.TOTAL_AIR_MELEE_DPS, null, false),
    SUM_TOTAL_AIR_SPELL_DPS("", "total_air_spell_dps", null, "", "sum", SumEnum.TOTAL_AIR_SPELL_DPS, null, false),

    SUM_RAW_SPELL_COSTS("", "raw_spell_costs", null, "", "sum", SumEnum.RAW_SPELL_COSTS, null, false),
    SUM_SPELL_COSTS_PERCENT("", "spell_costs", null, "", "sum", SumEnum.SPELL_COSTS_PERCENT, null, false)
    ;

    private final String displayName;
    private final String itemName;
    private final String ingName;
    private final String displaySp;
    private final String idType;
    private final SumEnum sum;
    private final String ingFieldPos;
    private final boolean itemVariable;

    Identifications(String displayName, String itemName, String ingName, String displaySp, String idType, SumEnum sum, String ingFieldPos, boolean itemVariable) {
        this.displayName = displayName;
        this.itemName = itemName;
        this.ingName = ingName;
        this.displaySp = displaySp;
        this.idType = idType;
        this.sum = sum;
        this.ingFieldPos = ingFieldPos;
        this.itemVariable = itemVariable;
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

    public String getDisplaySp() {
        return this.displaySp;
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
