package com.github.iTzhsnu.WynnSearcher.builder.skilltree;

public class TreeTemplates {
    public static TreeCheckBox earthMastery(int x, int y, TreeCheckBox.ArchetypeEnum archetype, TreeCheckBox[] previous, TreeCheckBox[] hiddenReq) {
        return new TreeCheckBox("Earth Mastery", new String[]{"Increase your base damage", "from all earth attacks.", " ", "Earth Damage: 2-4", "Earth Damage: +20%"}, null, null, previous, hiddenReq, archetype, 0, 1, SkillEnum.EARTH_DAMAGE, x, y).whiteIcon();
    }

    public static TreeCheckBox thunderMastery(int x, int y, TreeCheckBox.ArchetypeEnum archetype, TreeCheckBox[] previous, TreeCheckBox[] hiddenReq) {
        return new TreeCheckBox("Thunder Mastery", new String[]{"Increase your base damage", "from all thunder attacks.", " ", " Damage: 1-8", " Damage: +10%"}, null, null, previous, hiddenReq, archetype, 0, 1, SkillEnum.THUNDER_DAMAGE, x, y).whiteIcon();
    }

    public static TreeCheckBox waterMastery(int x, int y, TreeCheckBox.ArchetypeEnum archetype, TreeCheckBox[] previous, TreeCheckBox[] hiddenReq) {
        return new TreeCheckBox("Water Mastery", new String[]{"Increase your base damage", "from all water attacks.", " ", " Damage: 2-4", " Damage: +15%"}, null, null, previous, hiddenReq, archetype, 0, 1, SkillEnum.WATER_DAMAGE, x, y).whiteIcon();
    }

    public static TreeCheckBox fireMastery(int x, int y, TreeCheckBox.ArchetypeEnum archetype, TreeCheckBox[] previous, TreeCheckBox[] hiddenReq) {
        return new TreeCheckBox("Fire Mastery", new String[]{"Increase your base damage", "from all fire attacks.", " ", " Damage: 3-5", " Damage: +15%"}, null, null, previous, hiddenReq, archetype, 0, 1, SkillEnum.FIRE_DAMAGE, x, y).whiteIcon();
    }

    public static TreeCheckBox airMastery(int x, int y, TreeCheckBox.ArchetypeEnum archetype, TreeCheckBox[] previous, TreeCheckBox[] hiddenReq) {
        return new TreeCheckBox("Air Mastery", new String[]{"Increase your base damage", "from all air attacks.", " ", " Damage: 3-4", " Damage: +15%"}, null, null, previous, hiddenReq, archetype, 0, 1, SkillEnum.AIR_DAMAGE, x, y).whiteIcon();
    }
}
