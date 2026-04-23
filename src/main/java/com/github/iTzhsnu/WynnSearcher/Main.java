package com.github.iTzhsnu.WynnSearcher;

import com.github.iTzhsnu.WynnSearcher.ui.ItemUi;

public class Main {
    public static void main(String[] args) {
        setItemIds();

        new SearchUi().setVisible(true);
    }

    private static void setItemIds() {
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.STRENGTH);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.DEXTERITY);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.INTELLIGENCE);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.DEFENSE);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.AGILITY);

        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.HEALTH_BONUS);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.RAW_HEALTH_REGEN);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.HEALTH_REGEN_PERCENT);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.LIFE_STEAL);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.MANA_STEAL);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.MANA_REGEN);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.MAX_MANA);

        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.EARTH_DEFENSE_PERCENT);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.THUNDER_DEFENSE_PERCENT);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.WATER_DEFENSE_PERCENT);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.FIRE_DEFENSE_PERCENT);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.AIR_DEFENSE_PERCENT);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.ELEMENTAL_DEFENSE_PERCENT);

        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.DAMAGE_PERCENT);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.NEUTRAL_DAMAGE_PERCENT);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.EARTH_DAMAGE_PERCENT);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.THUNDER_DAMAGE_PERCENT);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.WATER_DAMAGE_PERCENT);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.FIRE_DAMAGE_PERCENT);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.AIR_DAMAGE_PERCENT);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.ELEMENTAL_DAMAGE_PERCENT);

        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.SPELL_DAMAGE_PERCENT);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.NEUTRAL_SPELL_DAMAGE_PERCENT);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.EARTH_SPELL_DAMAGE_PERCENT);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.THUNDER_SPELL_DAMAGE_PERCENT);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.WATER_SPELL_DAMAGE_PERCENT);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.FIRE_SPELL_DAMAGE_PERCENT);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.AIR_SPELL_DAMAGE_PERCENT);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.ELEMENTAL_SPELL_DAMAGE_PERCENT);

        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.MELEE_DAMAGE_PERCENT);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.NEUTRAL_MELEE_DAMAGE_PERCENT);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.EARTH_MELEE_DAMAGE_PERCENT);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.THUNDER_MELEE_DAMAGE_PERCENT);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.WATER_MELEE_DAMAGE_PERCENT);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.FIRE_MELEE_DAMAGE_PERCENT);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.AIR_MELEE_DAMAGE_PERCENT);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.ELEMENTAL_MELEE_DAMAGE_PERCENT);

        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.RAW_DAMAGE);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.RAW_NEUTRAL_DAMAGE);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.RAW_EARTH_DAMAGE);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.RAW_THUNDER_DAMAGE);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.RAW_WATER_DAMAGE);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.RAW_FIRE_DAMAGE);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.RAW_AIR_DAMAGE);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.RAW_ELEMENTAL_DAMAGE);

        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.RAW_SPELL_DAMAGE);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.RAW_NEUTRAL_SPELL_DAMAGE);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.RAW_EARTH_SPELL_DAMAGE);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.RAW_THUNDER_SPELL_DAMAGE);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.RAW_WATER_SPELL_DAMAGE);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.RAW_FIRE_SPELL_DAMAGE);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.RAW_AIR_SPELL_DAMAGE);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.RAW_ELEMENTAL_SPELL_DAMAGE);

        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.RAW_MELEE_DAMAGE);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.RAW_NEUTRAL_MELEE_DAMAGE);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.RAW_EARTH_MELEE_DAMAGE);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.RAW_THUNDER_MELEE_DAMAGE);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.RAW_WATER_MELEE_DAMAGE);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.RAW_FIRE_MELEE_DAMAGE);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.RAW_AIR_MELEE_DAMAGE);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.RAW_ELEMENTAL_MELEE_DAMAGE);

        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.ATTACK_SPEED_BONUS);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.POISON);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.THORNS);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.REFLECTION);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.EXPLODING);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.CRITICAL_DAMAGE_PERCENT);

        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.WALK_SPEED);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.SPRINT_BONUS);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.SPRINT_REGEN);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.JUMP_HEIGHT);

        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.STEALING);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.COMBAT_XP_BONUS);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.GATHERING_XP_BONUS);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.GATHERING_SPEED_BONUS);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.LOOT_BONUS);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.LOOT_QUALITY);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.SOUL_POINT_REGEN);

        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.KNOCKBACK);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.HEALING_EFFICIENCY);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.WEAKEN_ENEMY);
        ItemUi.ITEM_IDS.put(ItemUi.ITEM_IDS.size(), Identifications.SLOW_ENEMY);
    }
}
