package com.github.iTzhsnu.WynnSearcher;

public enum Identifications {
    EMPTY(null, null, null, "", "", null, null, null, false),

    LEVEL("Lv. Min","level", "level", "", "int", null, "requirements", "requirements", false),
    STRENGTH_REQ("Strength Req", "strength", "strengthRequirement", "", "int", null, "requirements", "itemOnlyIDs", false),
    DEXTERITY_REQ("Dexterity Req", "dexterity", "dexterityRequirement", "", "int", null, "requirements", "itemOnlyIDs", false),
    INTELLIGENCE_REQ("Intelligence Req", "intelligence", "intelligenceRequirement", "", "int", null, "requirements", "itemOnlyIDs", false),
    DEFENSE_REQ("Defense Req", "defence", "defenceRequirement", "", "int", null, "requirements", "itemOnlyIDs", false),
    AGILITY_REQ("Agility Req", "agility", "agilityRequirement", "", "int", null, "requirements", "itemOnlyIDs", false),
    QUEST_REQ("Quest Req", "quest", null, "", "string", null, "requirements", null, false),
    UNTRADABLE("Untradable", "restrictions", null, "", "string", null, "nothing", null, false),

    HEALTH("Health", "health", null, "", "int", null, "base", null, false),
    HEALTH_BONUS("Health Bonus", "rawHealth", "rawHealth", "", "int", null, "identifications", "identifications", true),
    RAW_HEALTH_REGEN("Raw Health Regen", "healthRegenRaw", "healthRegenRaw", "", "int", null, "identifications", "identifications", true),
    HEALTH_REGEN_PERCENT("Health Regen", "healthRegen", "healthRegen", "%", "int", null, "identifications", "identifications", true),
    LIFE_STEAL("Life Steal", "lifeSteal", "lifeSteal", "/3s", "int", null, "identifications", "identifications", true),

    STRENGTH("Strength", "rawStrength", "rawStrength", "", "int", null, "identifications", "identifications", false),
    DEXTERITY("Dexterity", "rawDexterity", "rawDexterity", "", "int", null, "identifications", "identifications", false),
    INTELLIGENCE("Intelligence", "rawIntelligence", "rawIntelligence", "", "int", null, "identifications", "identifications", false),
    DEFENSE("Defense", "rawDefence", "rawDefence", "", "int", null, "identifications", "identifications", false),
    AGILITY("Agility", "rawAgility", "rawAgility", "", "int", null, "identifications", "identifications", false),

    EARTH_DEFENSE("Earth Defense", "earthDefence", null, "", "int", null, "base", null, false),
    THUNDER_DEFENSE("Thunder Defense", "thunderDefence", null, "", "int", null, "base", null, false),
    WATER_DEFENSE("Water Defense", "waterDefence", null, "", "int", null, "base", null, false),
    FIRE_DEFENSE("Fire Defense", "fireDefence", null, "", "int", null, "base", null, false),
    AIR_DEFENSE("Air Defense", "airDefence", null, "", "int", null, "base", null, false),

    EARTH_DEFENSE_PERCENT("Earth Defense", "earthDefence", "earthDefence", "%", "int", null, "identifications", "identifications", true),
    THUNDER_DEFENSE_PERCENT("Thunder Defense", "thunderDefence", "thunderDefence", "%", "int", null, "identifications", "identifications", true),
    WATER_DEFENSE_PERCENT("Water Defense", "waterDefence", "waterDefence", "%", "int", null, "identifications", "identifications", true),
    FIRE_DEFENSE_PERCENT("Fire Defense", "fireDefence", "fireDefence", "%", "int", null, "identifications", "identifications", true),
    AIR_DEFENSE_PERCENT("Air Defense", "airDefence", "airDefence", "%", "int", null, "identifications", "identifications", true),

    NEUTRAL_DAMAGE("Neutral Damage", "damage", null, "", "int", null, "base", null, true),
    EARTH_DAMAGE("Earth Damage", "earthDamage", null, "", "int", null, "base", null, true),
    THUNDER_DAMAGE("Thunder Damage", "thunderDamage", null, "", "int", null, "base", null, true),
    WATER_DAMAGE("Water Damage", "waterDamage", null, "", "int", null, "base", null, true),
    FIRE_DAMAGE("Fire Damage", "fireDamage", null, "", "int", null, "base", null, true),
    AIR_DAMAGE("Air Damage", "airDamage", null, "", "int", null, "base", null, true),

    EARTH_DAMAGE_PERCENT("Earth Damage", "earthDamage", "earthDamage", "%", "int", null, "identifications", "identifications", true),
    THUNDER_DAMAGE_PERCENT("Thunder Damage", "thunderDamage", "thunderDamage", "%", "int", null, "identifications", "identifications", true),
    WATER_DAMAGE_PERCENT("Water Damage", "waterDamage", "waterDamage", "%", "int", null, "identifications", "identifications", true),
    FIRE_DAMAGE_PERCENT("Fire Damage", "fireDamage", "fireDamage", "%", "int", null, "identifications", "identifications", true),
    AIR_DAMAGE_PERCENT("Air Damage", "airDamage", "airDamage", "%", "int", null, "identifications", "identifications", true),
    ELEMENTAL_DAMAGE_PERCENT("Elemental Damage", "elementalDamage", "elementalDamage", "%", "int", null, "identifications", "identifications", true),

    RAW_SPELL_DAMAGE("Raw Spell Damage", "rawSpellDamage", "rawSpellDamage", "", "int", null, "identifications", "identifications", true),
    RAW_MELEE_DAMAGE("Raw Melee Damage", "rawMainAttackDamage", "rawMainAttackDamage", "", "int", null, "identifications", "identifications", true),
    SPELL_DAMAGE_PERCENT("Spell Damage", "spellDamage", "spellDamage", "%", "int", null, "identifications", "identifications", true),
    MELEE_DAMAGE_PERCENT("Melee Damage", "mainAttackDamage", "mainAttackDamage", "%", "int", null, "identifications", "identifications", true),
    POISON("Poison", "poison", "poison", "/3s", "int", null, "identifications", "identifications", true),

    ATTACK_SPEED("Attack Speed", "attackSpeed", null, "", "string", null, "nothing", null, false),
    ATTACK_SPEED_BONUS("Attack Speed Bonus", "rawAttackSpeed", "rawAttackSpeed", "tier", "int", null, "identifications", "identifications", true),

    MANA_REGEN("Mana Regen", "manaRegen", "manaRegen", "/5s", "int", null, "identifications", "identifications", true),
    MANA_STEAL("Mana Steal", "manaSteal", "manaSteal", "/3s", "int", null, "identifications", "identifications", true),

    WALK_SPEED("Walk Speed", "walkSpeed", "walkSpeed", "%", "int", null, "identifications", "identifications", true),
    SPRINT_BONUS("Sprint Bonus", "sprint", "sprint", "%", "int", null, "identifications", "identifications", true),
    SPRINT_REGEN("Sprint Regen", "sprintRegen", "sprintRegen", "%", "int", null, "identifications", "identifications", true),
    JUMP_HEIGHT("Jump Height", "jumpHeight", "jumpHeight", "", "int", null, "identifications", "identifications", true),

    THORNS("Thorns", "thorns", "thorns", "%", "int", null, "identifications", "identifications", true),
    REFLECTION("Reflection", "reflection", "reflection", "%", "int", null, "identifications", "identifications", true),
    EXPLODING("Exploding", "exploding", "exploding", "%", "int", null, "identifications", "identifications", true),
    STEALING("Stealing", "stealing", "stealing", "%", "int", null, "identifications", "identifications", true),
    COMBAT_XP_BONUS("Combat XP Bonus", "xpBonus", "xpBonus", "%", "int", null, "identifications", "identifications", true),
    GATHERING_XP_BONUS("Gathering XP Bonus", "gatherXpBonus", "gatherXpBonus", "%", "int", null, "identifications", "identifications", true),
    GATHERING_SPEED_BONUS("Gathering Speed Bonus", "gatherSpeed", "gatherSpeed", "%", "int", null, "identifications", "identifications", true),
    LOOT_BONUS("Loot Bonus", "lootBonus", "lootBonus", "%", "int", null, "identifications", "identifications", true),
    LOOT_QUALITY("Loot Quality", "lootQuality", "lootQuality", "%", "int", null, "identifications", "identifications", true),
    SOUL_POINT_REGEN("Soul Point Regen", "soulPointRegen", "soulPointRegen", "%", "int", null, "identifications", "identifications", true),
    POWDER_SLOTS("Powder Slots", "powderSlots", null, "", "int", null, "nothing", null, false),
    MAJOR_IDS("Major ID", "majorIds", null, "", "string", null, "nothing", null, false),

    RAW_1ST_SPELL_COST("Raw 1st Spell Cost", "raw1stSpellCost", "raw1stSpellCost", "", "int", null, "identifications", "identifications", true),
    RAW_2ND_SPELL_COST("Raw 2nd Spell Cost", "raw2ndSpellCost", "raw2ndSpellCost", "", "int", null, "identifications", "identifications", true),
    RAW_3RD_SPELL_COST("Raw 3rd Spell Cost", "raw3rdSpellCost", "raw3rdSpellCost", "", "int", null, "identifications", "identifications", true),
    RAW_4TH_SPELL_COST("Raw 4th Spell Cost", "raw4thSpellCost", "raw4thSpellCost", "", "int", null, "identifications", "identifications", true),

    PERCENT_1ST_SPELL_COST("1st Spell Cost", "1stSpellCost", "1stSpellCost", "%", "int", null, "identifications", "identifications", true),
    PERCENT_2ND_SPELL_COST("2nd Spell Cost", "2ndSpellCost", "2ndSpellCost", "%", "int", null, "identifications", "identifications", true),
    PERCENT_3RD_SPELL_COST("3rd Spell Cost", "3rdSpellCost", "3rdSpellCost", "%", "int", null, "identifications", "identifications", true),
    PERCENT_4TH_SPELL_COST("4th Spell Cost", "4thSpellCost", "4thSpellCost", "%", "int", null, "identifications", "identifications", true),

    RAW_NEUTRAL_DAMAGE("Raw Neutral Damage", "rawNeutralDamage", "rawNeutralDamage", "", "int", null, "identifications", "identifications", true),
    RAW_EARTH_DAMAGE("Raw Earth Damage", "rawEarthDamage", "rawEarthDamage", "", "int", null, "identifications", "identifications", true),
    RAW_THUNDER_DAMAGE("Raw Thunder Damage", "rawThunderDamage", "rawThunderDamage", "", "int", null, "identifications", "identifications", true),
    RAW_WATER_DAMAGE("Raw Water Damage", "rawWaterDamage", "rawWaterDamage", "", "int", null, "identifications", "identifications", true),
    RAW_FIRE_DAMAGE("Raw Fire Damage", "rawFireDamage", "rawFireDamage", "", "int", null, "identifications", "identifications", true),
    RAW_AIR_DAMAGE("Raw Air Damage", "rawAirDamage", "rawAirDamage", "", "int", null, "identifications", "identifications", true),
    RAW_ELEMENTAL_DAMAGE("Raw Elemental Damage", "rawElementalDamage", "rawElementalDamage", "", "int", null, "identifications", "identifications", true),

    NEUTRAL_MELEE_DAMAGE_PERCENT("Neutral Melee Damage", "neutralMainAttackDamage", "neutralMainAttackDamage", "%", "int", null, "identifications", "identifications", true),
    EARTH_MELEE_DAMAGE_PERCENT("Earth Melee Damage", "earthMainAttackDamage", "earthMainAttackDamage", "%", "int", null, "identifications", "identifications", true),
    THUNDER_MELEE_DAMAGE_PERCENT("Thunder Melee Damage", "thunderMainAttackDamage", "thunderMainAttackDamage", "%", "int", null, "identifications", "identifications", true),
    WATER_MELEE_DAMAGE_PERCENT("Water Melee Damage", "waterMainAttackDamage", "waterMainAttackDamage", "%", "int", null, "identifications", "identifications", true),
    FIRE_MELEE_DAMAGE_PERCENT("Fire Melee Damage", "fireMainAttackDamage", "fireMainAttackDamage", "%", "int", null, "identifications", "identifications", true),
    AIR_MELEE_DAMAGE_PERCENT("Air Melee Damage", "airMainAttackDamage", "airMainAttackDamage", "%", "int", null, "identifications", "identifications", true),
    ELEMENTAL_MELEE_DAMAGE_PERCENT("Elem. Melee Damage", "elementalMainAttackDamage", "elementalMainAttackDamage", "%", "int", null, "identifications", "identifications", true),

    RAW_NEUTRAL_MELEE_DAMAGE("Raw Neutral Melee Damage", "rawNeutralMainAttackDamage", "rawNeutralMainAttackDamage", "", "int", null, "identifications", "identifications", true),
    RAW_EARTH_MELEE_DAMAGE("Raw Earth Melee Damage", "rawEarthMainAttackDamage", "rawEarthMainAttackDamage", "", "int", null, "identifications", "identifications", true),
    RAW_THUNDER_MELEE_DAMAGE("Raw Thunder Melee Damage", "rawThunderMainAttackDamage", "rawThunderMainAttackDamage", "", "int", null, "identifications", "identifications", true),
    RAW_WATER_MELEE_DAMAGE("Raw Water Melee Damage", "rawWaterMainAttackDamage", "rawWaterMainAttackDamage", "", "int", null, "identifications", "identifications", true),
    RAW_FIRE_MELEE_DAMAGE("Raw Fire Melee Damage", "rawFireMainAttackDamage", "rawFireMainAttackDamage", "", "int", null, "identifications", "identifications", true),
    RAW_AIR_MELEE_DAMAGE("Raw Air Melee Damage", "rawAirMainAttackDamage", "rawAirMainAttackDamage", "", "int", null, "identifications", "identifications", true),
    RAW_ELEMENTAL_MELEE_DAMAGE("Raw Elem. Melee Damage", "rawElementalMainAttackDamage", "rawElementalMainAttackDamage", "", "int", null, "identifications", "identifications", true),

    NEUTRAL_SPELL_DAMAGE_PERCENT("Neutral Spell Damage", "neutralSpellDamage", "neutralSpellDamage", "%", "int", null, "identifications", "identifications", true),
    EARTH_SPELL_DAMAGE_PERCENT("Earth Spell Damage", "earthSpellDamage", "earthSpellDamage", "%", "int", null, "identifications", "identifications", true),
    THUNDER_SPELL_DAMAGE_PERCENT("Thunder Spell Damage", "thunderSpellDamage", "thunderSpellDamage", "%", "int", null, "identifications", "identifications", true),
    WATER_SPELL_DAMAGE_PERCENT("Water Spell Damage", "waterSpellDamage", "waterSpellDamage", "%", "int", null, "identifications", "identifications", true),
    FIRE_SPELL_DAMAGE_PERCENT("Fire Spell Damage", "fireSpellDamage", "fireSpellDamage", "%", "int", null, "identifications", "identifications", true),
    AIR_SPELL_DAMAGE_PERCENT("Air Spell Damage", "airSpellDamage", "airSpellDamage", "%", "int", null, "identifications", "identifications", true),
    ELEMENTAL_SPELL_DAMAGE_PERCENT("Elem. Spell Damage", "elementalSpellDamage", "elementalSpellDamage", "%", "int", null, "identifications", "identifications", true),

    RAW_NEUTRAL_SPELL_DAMAGE("Raw Neutral Spell Damage", "rawNeutralSpellDamage", "rawNeutralSpellDamage", "", "int", null, "identifications", "identifications", true),
    RAW_EARTH_SPELL_DAMAGE("Raw Earth Spell Damage", "rawEarthSpellDamage", "rawEarthSpellDamage", "", "int", null, "identifications", "identifications", true),
    RAW_THUNDER_SPELL_DAMAGE("Raw Thunder Spell Damage", "rawThunderSpellDamage", "rawThunderSpellDamage", "", "int", null, "identifications", "identifications", true),
    RAW_WATER_SPELL_DAMAGE("Raw Water Spell Damage", "rawWaterSpellDamage", "rawWaterSpellDamage", "", "int", null, "identifications", "identifications", true),
    RAW_FIRE_SPELL_DAMAGE("Raw Fire Spell Damage", "rawFireSpellDamage", "rawFireSpellDamage", "", "int", null, "identifications", "identifications", true),
    RAW_AIR_SPELL_DAMAGE("Raw Air Spell Damage", "rawAirSpellDamage", "rawAirSpellDamage", "", "int", null, "identifications", "identifications", true),
    RAW_ELEMENTAL_SPELL_DAMAGE("Raw Elem. Spell Damage", "rawElementalSpellDamage", "rawElementalSpellDamage", "", "int", null, "identifications", "identifications", true),

    KNOCKBACK("Knockback", "knockback", null, "%", "int", null, "identifications", null, true),
    HEALING_EFFICIENCY("Healing Efficiency", "healingEfficiency", null, "%", "int", null, "identifications", null, true),
    WEAKEN_ENEMY("Weaken Enemy", "weakenEnemy", null, "%", "int", null, "identifications", null, true),
    SLOW_ENEMY("Slow Enemy", "slowEnemy", null, "%", "int", null, "identifications", null, true),
    ELEMENTAL_DEFENSE("Elemental Defense", "elementalDefence", null, "%", "int", null, "identifications", null, true),

    DAMAGE_FROM_MOBS("Damage From Mobs", "damageFromMobs", null, "%", "int", null, "identifications", null, false),
    LEVELED_LOOT_BONUS("Leveled Loot Bonus", "leveledLootBonus", null, "%", "int", null, "identifications", null, false),
    LEVELED_XP_BONUS("Leveled XP Bonus", "leveledXpBonus", null, "%", "int", null, "identifications", null, false),

    DURABILITY("Durability", null, "durabilityModifier", "", "int", null, null, "itemOnlyIDs", false),
    DURATION("Duration", null, "duration", "", "int", null, null, "consumableOnlyIDs", false),
    CHARGES("Charges", null, "charges", "", "int", null, null, "consumableOnlyIDs", false),
    INGREDIENT_EFFECTIVENESS_ABOVE("Ingredient Effectiveness (Above)", null, "above", "%", "int", null, null, "ingredientPositionModifiers", false),
    INGREDIENT_EFFECTIVENESS_UNDER("Ingredient Effectiveness (Under)", null, "under", "%", "int", null, null, "ingredientPositionModifiers", false),
    INGREDIENT_EFFECTIVENESS_RIGHT("Ingredient Effectiveness (Right)", null, "right", "%", "int", null, null, "ingredientPositionModifiers", false),
    INGREDIENT_EFFECTIVENESS_LEFT("Ingredient Effectiveness (Left)", null, "left", "%", "int", null, null, "ingredientPositionModifiers", false),
    INGREDIENT_EFFECTIVENESS_TOUCHING("Ingredient Effectiveness (Touch)", null, "touching", "%", "int", null, null, "ingredientPositionModifiers", false),
    INGREDIENT_EFFECTIVENESS_NOT_TOUCHING("Ingredient Effectiveness (Not Touch)", null, "notTouching", "%", "int", null, null, "ingredientPositionModifiers", false),
    INGREDIENT_EFFECTIVENESS("", null, "ingEffective", "", "sum", SumEnum.INGREDIENT_EFFECTIVENESS, null, "", false),

    DROP_TYPE("Drop Type", "dropRestriction", "droppedBy", "", "string", null, "nothing", "nothing", false),
    DROP_TYPE_NORMAL("normal", "dropRestriction", "droppedBy", "", "string", null, "nothing", "nothing", false),
    DROP_TYPE_LOOT_CHESTS("lootchest", "dropRestriction", "droppedBy", "", "string", null, "nothing", "nothing", false),
    DROP_TYPE_RAID_REWARDS("raid", "dropRestriction", "droppedBy", "", "string", null, "nothing", "nothing", false),
    DROP_TYPE_DUNGEON_DROP("dungeon", "dropRestriction", "droppedBy", "", "string", null, "nothing", "nothing", false),
    DROP_TYPE_DUNGEON_MERCHANT("dungeonMerchant", "dropRestriction", "droppedBy", "", "string", null, "nothing", "nothing", false),
    DROP_TYPE_BOSS_ALTAR("altar", "dropRestriction", "droppedBy", "", "string", null, "nothing", "nothing", false),
    DROP_TYPE_DISCONTINUED("discontinued", "dropRestriction", "droppedBy", "", "string", null, "nothing", "nothing", false),
    DROP_TYPE_UNKNOWN("never", "dropRestriction", "droppedBy", "", "string", null, "nothing", "nothing", false),
    DROP_TYPE_MERCHANT("merchant", "dropRestriction", "droppedBy", "", "string", null, "nothing", "nothing", false),
    DROP_TYPE_QUEST("quest", "dropRestriction", "droppedBy", "", "string", null, "nothing", "nothing", false),
    DROP_TYPE_UNOBTAINABLE("unobtainable", "dropRestriction", "droppedBy", "", "string", null, "nothing", "nothing", false),
    DROP_TYPE_SPECIFIC_DROP("specific", "dropRestriction", "droppedBy", "", "string", null, "nothing", "nothing", false),
    DROP_TYPE_SECRET_DISCOVERY("secret_discovery", "dropRestriction", "droppedBy", "", "string", null, "nothing", "nothing", false),
    DROP_TYPE_THE_QIRA_HIVE("the_qira_hive", "dropRestriction", "droppedBy", "", "string", null, "nothing", "nothing", false),
    DROP_TYPE_LEGENDARY_ISLAND("legendary_island", "dropRestriction", "droppedBy", "", "string", null, "nothing", "nothing", false),
    DROP_TYPE_OTHER("other", "dropRestriction", "droppedBy", "", "string", null, "nothing", "nothing", false),

    SUM_TOTAL_HEALTH("", "total_health", "total_health", "", "sum", SumEnum.TOTAL_HEALTH, "", "", false),
    SUM_TOTAL_HP_REGEN("", "total_hp_regen", "total_hp_regen", "", "sum", SumEnum.TOTAL_HEALTH_REGEN, "", "", false),
    SUM_RAW_DEFENSES("", "raw_defenses", null, "", "sum", SumEnum.RAW_DEFENSES, "", null, false),
    SUM_RAW_RAINBOW_DEFENSES("", "raw_rainbow_defense", null, "", "sum", SumEnum.RAW_RAINBOW_DEFENSES, "", null, false),
    SUM_DEFENSES_PERCENT("", "defenses", "defenses", "", "sum", SumEnum.DEFENSES_PERCENT, "", "", false),
    SUM_RAINBOW_DEFENSES_PERCENT("", "rainbow_defense", "rainbow_defense", "", "sum", SumEnum.RAINBOW_DEFENSES_PERCENT, "", "", false),
    SUM_SKILL_POINT_BONUS("", "sp_bonus", "sp_bonus", "", "sum", SumEnum.SKILL_POINT_BONUS, "", "", false),
    SUM_RAINBOW_SKILL_POINT_BONUS("", "rainbow_sp_bonus", "rainbow_sp_bonus", "", "sum", SumEnum.RAINBOW_SKILL_POINT_BONUS, "", "", false),

    SUM_BASE_DPS("", "base_dps", null, "", "sum", SumEnum.BASE_DPS, "", null, false),
    SUM_NEUTRAL_DPS("", "neutral_dps", null, "", "sum", SumEnum.NEUTRAL_DPS, "", null, false),
    SUM_EARTH_DPS("", "earth_dps", null, "", "sum", SumEnum.EARTH_DPS, "", null, false),
    SUM_THUNDER_DPS("", "thunder_dps", null, "", "sum", SumEnum.THUNDER_DPS, "", null, false),
    SUM_WATER_DPS("", "water_dps", null, "", "sum", SumEnum.WATER_DPS, "", null, false),
    SUM_FIRE_DPS("", "fire_dps", null, "", "sum", SumEnum.FIRE_DPS, "", null, false),
    SUM_AIR_DPS("", "air_dps", null, "", "sum", SumEnum.AIR_DPS, "", null, false),

    SUM_RAW_DAMAGES("", "raw_damages", null, "", "sum", SumEnum.RAW_DAMAGES, "", null, false),
    SUM_RAW_RAINBOW_DAMAGES("", "raw_rainbow_damage", null, "", "sum", SumEnum.RAW_RAINBOW_DAMAGES, "", null, false),
    SUM_DAMAGES_PERCENT("", "damages", "damages", "", "sum", SumEnum.DAMAGES_PERCENT, "", "", false),
    SUM_RAINBOW_DAMAGES_PERCENT("", "rainbow_damage", "rainbow_damage", "", "sum", SumEnum.RAINBOW_DAMAGES_PERCENT, "", "", false),

    SUM_TOTAL_MELEE_DAMAGE("", "total_melee_damage", null, "", "sum", SumEnum.TOTAL_MELEE_DAMAGE, "", null, false),
    SUM_TOTAL_NEUTRAL_MELEE_DAMAGE("", "total_neutral_melee_damage", null, "", "sum", SumEnum.TOTAL_NEUTRAL_MELEE_DAMAGE, "", null, false),
    SUM_TOTAL_EARTH_MELEE_DAMAGE("", "total_earth_damage", null, "", "sum", SumEnum.TOTAL_EARTH_MELEE_DAMAGE, "", null, false),
    SUM_TOTAL_THUNDER_MELEE_DAMAGE("", "total_thunder_damage", null, "", "sum", SumEnum.TOTAL_THUNDER_MELEE_DAMAGE, "", null, false),
    SUM_TOTAL_WATER_MELEE_DAMAGE("", "total_water_damage", null, "", "sum", SumEnum.TOTAL_WATER_MELEE_DAMAGE, "", null, false),
    SUM_TOTAL_FIRE_MELEE_DAMAGE("", "total_fire_damage", null, "", "sum", SumEnum.TOTAL_FIRE_MELEE_DAMAGE, "", null, false),
    SUM_TOTAL_AIR_MELEE_DAMAGE("", "total_air_damage", null, "", "sum", SumEnum.TOTAL_AIR_MELEE_DAMAGE, "", null, false),

    SUM_TOTAL_MELEE_DPS("", "total_melee_dps", null, "", "sum", SumEnum.TOTAL_MELEE_DPS, "", null, false),
    SUM_TOTAL_SPELL_DPS("", "total_spell_dps", null, "", "sum", SumEnum.TOTAL_SPELL_DPS, "", null, false),
    SUM_TOTAL_NEUTRAL_MELEE_DPS("", "total_neutral_melee_dps", null, "", "sum", SumEnum.TOTAL_NEUTRAL_MELEE_DPS, "", null, false),
    SUM_TOTAL_NEUTRAL_SPELL_DPS("", "total_neutral_spell_dps", null, "", "sum", SumEnum.TOTAL_NEUTRAL_SPELL_DPS, "", null, false),
    SUM_TOTAL_EARTH_MELEE_DPS("", "total_earth_melee_dps", null, "", "sum", SumEnum.TOTAL_EARTH_MELEE_DPS, "", null, false),
    SUM_TOTAL_EARTH_SPELL_DPS("", "total_earth_spell_dps", null, "", "sum", SumEnum.TOTAL_EARTH_SPELL_DPS, "", null, false),
    SUM_TOTAL_THUNDER_MELEE_DPS("", "total_thunder_melee_dps", null, "", "sum", SumEnum.TOTAL_THUNDER_MELEE_DPS, "", null, false),
    SUM_TOTAL_THUNDER_SPELL_DPS("", "total_thunder_spell_dps", null, "", "sum", SumEnum.TOTAL_THUNDER_SPELL_DPS, "", null, false),
    SUM_TOTAL_WATER_MELEE_DPS("", "total_water_melee_dps", null, "", "sum", SumEnum.TOTAL_WATER_MELEE_DPS, "", null, false),
    SUM_TOTAL_WATER_SPELL_DPS("", "total_water_spell_dps", null, "", "sum", SumEnum.TOTAL_WATER_SPELL_DPS, "", null, false),
    SUM_TOTAL_FIRE_MELEE_DPS("", "total_fire_melee_dps", null, "", "sum", SumEnum.TOTAL_FIRE_MELEE_DPS, "", null, false),
    SUM_TOTAL_FIRE_SPELL_DPS("", "total_fire_spell_dps", null, "", "sum", SumEnum.TOTAL_FIRE_SPELL_DPS, "", null, false),
    SUM_TOTAL_AIR_MELEE_DPS("", "total_air_melee_dps", null, "", "sum", SumEnum.TOTAL_AIR_MELEE_DPS, "", null, false),
    SUM_TOTAL_AIR_SPELL_DPS("", "total_air_spell_dps", null, "", "sum", SumEnum.TOTAL_AIR_SPELL_DPS, "", null, false),

    SUM_RAW_SPELL_COSTS("", "raw_spell_costs", null, "", "sum", SumEnum.RAW_SPELL_COSTS, "", "", false),
    SUM_SPELL_COSTS_PERCENT("", "spell_costs", null, "", "sum", SumEnum.SPELL_COSTS_PERCENT, "", "", false)
    ;

    private final String displayName;
    private final String itemName;
    private final String ingName;
    private final String displaySp;
    private final String idType;
    private final SumEnum sum;
    private final String itemFieldPos;
    private final String ingFieldPos;
    private final boolean itemVariable;

    Identifications(String displayName, String itemName, String ingName, String displaySp, String idType, SumEnum sum, String itemFieldPos, String ingFieldPos, boolean itemVariable) {
        this.displayName = displayName;
        this.itemName = itemName;
        this.ingName = ingName;
        this.displaySp = displaySp;
        this.idType = idType;
        this.sum = sum;
        this.itemFieldPos = itemFieldPos;
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

    public String getItemFieldPos() {
        return this.itemFieldPos;
    }

    public String getIngFieldPos() {
        return this.ingFieldPos;
    }

    public boolean isItemVariable() {
        return this.itemVariable;
    }
}
