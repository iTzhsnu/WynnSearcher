package com.github.iTzhsnu.WynnSearcher.builder;

import com.github.iTzhsnu.WynnSearcher.GetAPI;
import com.github.iTzhsnu.WynnSearcher.Identifications;
import com.github.iTzhsnu.WynnSearcher.ItemUITemplate;
import com.github.iTzhsnu.WynnSearcher.SearchUI;
import com.github.iTzhsnu.WynnSearcher.builder.skilltree.TreeBase;
import com.github.iTzhsnu.WynnSearcher.builder.skilltree.TreeCheckBox;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.*;

public class ID_Display {
    private final JPanel pane = new JPanel();
    private final List<JLabel> ids = new ArrayList<>();
    private int[] id_Numbers = new int[] {};
    private final List<SetBonus> setBonuses = new ArrayList<>();

    public static final Map<Integer, Identifications> IDS_ = new HashMap<Integer, Identifications>(){{
        put(0, Identifications.HEALTH);
        put(1, Identifications.EARTH_DEFENSE);
        put(2, Identifications.THUNDER_DEFENSE);
        put(3, Identifications.WATER_DEFENSE);
        put(4, Identifications.FIRE_DEFENSE);
        put(5, Identifications.AIR_DEFENSE);
    }};

    public static final Map<Identifications, Integer> ID_INT = new HashMap<>();

    private static final Map<Integer, Float> CLASS_DEF = new HashMap<Integer, Float>() {{
        put(0, 1F); //Warrior
        put(1, 1F); //Assassin
        put(2, 0.8F); //Mage
        put(3, 0.7F); //Archer
        put(4, 0.6F); //Shaman
    }};

    public ID_Display(JPanel p) {
        pane.setPreferredSize(new Dimension(250, 2500));
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(pane);
        scrollPane.setBounds(422, 710, 268, 400);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);

        p.add(scrollPane);

        setID_INT();
    }

    public void setID_INT() {
        for (int i = 0; IDS_.size() > i; ++i) {
            ID_INT.put(IDS_.get(i), i);
        }
        for (int i = 0; ItemUITemplate.ITEM_IDS.size() > i; ++i) {
            ID_INT.put(ItemUITemplate.ITEM_IDS.get(i), i + IDS_.size());
        }
        for (int i = 0; ItemUITemplate.REVERSED_ITEM_IDS.size() > i; ++i) {
            ID_INT.put(ItemUITemplate.REVERSED_ITEM_IDS.get(i), i + IDS_.size() + ItemUITemplate.ITEM_IDS.size());
        }
    }

    public void setIDs(ItemJsons itemJsons, Damage_IDs damage_ids, SkillPoint sp, TreeBase tree, Ability_Buffs abilityBuffs, Powder_Effects powder_effects, List<JTextField> powders, int classID, boolean updateOnly) {
        pane.removeAll();
        if (!ids.isEmpty()) ids.clear();
        if (!setBonuses.isEmpty()) setBonuses.clear();
        int[] numbers = new int[] {
                535, 0, 0, 0, 0, 0, 0, 0, 0, 0, //0 ~ 9 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //10 ~ 19 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //20 ~ 29 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //30 ~ 39 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //40 ~ 49 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //50 ~ 59 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //60 ~ 69 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //70 ~ 79 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //80 ~ 89 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //90 ~ 99 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0 //100 ~ 108 (ID), 109 (Weapon Tome Damage Bonus)
        };
        int[] numbers_Sub = new int[] {
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //0 ~ 9 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //10 ~ 19 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //20 ~ 29 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //30 ~ 39 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //40 ~ 49 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //50 ~ 59 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //60 ~ 69 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //70 ~ 79 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //80 ~ 89 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, //90 ~ 99 (ID)
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0 //100 ~ 108 (ID), 109 (Weapon Tome Damage Bonus)
        };
        float def = 2F - CLASS_DEF.get(classID); //Base Defense

        //Tomes
        if (itemJsons.getTomes() != null) { //Tomes
            for (JsonObject j : itemJsons.getTomes()) {
                setNumbers(j, numbers);
            }
        }

        //Weapon, Armor, Accessory
        if (!itemJsons.getJsonObjectList().isEmpty()) {
            for (JsonObject j : itemJsons.getJsonObjectList()) {
                setNumbers(j, numbers);
                if (j.get(Identifications.RARITY.getItemName()) != null && j.get(Identifications.RARITY.getItemName()).getAsString().equals("set")) setSetBonuses(j.get("name").getAsString(), false);
            }
        }

        if (itemJsons.getWeapon() != null) {
            setNumbers(itemJsons.getWeapon(), numbers);
            if (itemJsons.getWeapon().get(Identifications.RARITY.getItemName()) != null && itemJsons.getWeapon().get(Identifications.RARITY.getItemName()).getAsString().equals("set")) setSetBonuses(itemJsons.getWeapon().get("name").getAsString(), true);
        }

        //Set Bonus
        if (!setBonuses.isEmpty()) {
            for (SetBonus setBonus : setBonuses) {
                setBonus.add_to_ID_Numbers(numbers);
            }
        }

        JsonObject powderJ = JsonParser.parseReader(new JsonReader(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/other/powders.json")), StandardCharsets.UTF_8))).getAsJsonObject();

        //Helmet Powder
        if (itemJsons.getHelmet() != null) {
            setArmorPowder(itemJsons.getHelmet(), powderJ, powders.get(0), numbers);
        }

        //Chestplate Powder
        if (itemJsons.getChestplate() != null) {
            setArmorPowder(itemJsons.getChestplate(), powderJ, powders.get(1), numbers);
        }

        //Leggings Powder
        if (itemJsons.getLeggings() != null) {
            setArmorPowder(itemJsons.getLeggings(), powderJ, powders.get(2), numbers);
        }

        //Boots Powder
        if (itemJsons.getBoots() != null) {
            setArmorPowder(itemJsons.getBoots(), powderJ, powders.get(3), numbers);
        }

        if (updateOnly) { //Update Edited ID
            for (int i = 0; ItemUITemplate.ITEM_IDS.size() > i; ++i) { //Damages and Health Regen
                Identifications id = ItemUITemplate.ITEM_IDS.get(i);
                if (Damage_IDs.GET_DAMAGE_ID_NUM_FROM_ID.getOrDefault(id, -1) != -1) {
                    numbers[ID_INT.get(id)] = damage_ids.getID(Damage_IDs.GET_DAMAGE_ID_NUM_FROM_ID.get(id)).getValue();
                }
            }
            for (int i = 0; ItemUITemplate.REVERSED_ITEM_IDS.size() > i; ++i) { //Spell Costs
                Identifications id = ItemUITemplate.REVERSED_ITEM_IDS.get(i);
                if (Damage_IDs.GET_DAMAGE_ID_NUM_FROM_ID.getOrDefault(id, -1) != -1) {
                    numbers[ID_INT.get(id)] = damage_ids.getID(Damage_IDs.GET_DAMAGE_ID_NUM_FROM_ID.get(id)).getValue();
                }
            }
        } else { //Set Editable ID Original Num
            for (int i = 0; ItemUITemplate.ITEM_IDS.size() > i; ++i) { //Damages and Health Regen
                Identifications id = ItemUITemplate.ITEM_IDS.get(i);
                if (Damage_IDs.GET_DAMAGE_ID_NUM_FROM_ID.getOrDefault(id, -1) != -1) {
                    damage_ids.getID(Damage_IDs.GET_DAMAGE_ID_NUM_FROM_ID.get(id)).setValue(numbers[ID_INT.get(id)]);
                }
            }
            for (int i = 0; ItemUITemplate.REVERSED_ITEM_IDS.size() > i; ++i) { //Spell Costs
                Identifications id = ItemUITemplate.REVERSED_ITEM_IDS.get(i);
                if (Damage_IDs.GET_DAMAGE_ID_NUM_FROM_ID.getOrDefault(id, -1) != -1) {
                    damage_ids.getID(Damage_IDs.GET_DAMAGE_ID_NUM_FROM_ID.get(id)).setValue(numbers[ID_INT.get(id)]);
                }
            }
        }

        //Radiance
        if (abilityBuffs.getBox().get(Ability_Buffs_Enum.RADIANCE.getPos()).isSelected()) {
            float radiance = 1.2F;
            if (itemJsons.getMajorIDList().contains(MajorIDEnum.DIVINE_HONOR)) radiance += 0.05F;
            for (int i = 5; ItemUITemplate.ITEM_IDS.size() > i; ++i) {
                Identifications id = ItemUITemplate.ITEM_IDS.get(i);
                if (id == Identifications.COMBAT_XP_BONUS || id == Identifications.LOOT_BONUS || id == Identifications.LOOT_QUALITY || id == Identifications.GATHERING_XP_BONUS || id == Identifications.GATHERING_SPEED_BONUS) continue;
                if (numbers[ID_INT.get(id)] > 0) {
                    numbers[ID_INT.get(id)] -= numbers_Sub[ID_INT.get(id)];
                    numbers[ID_INT.get(id)] = Math.round(numbers[ID_INT.get(id)] * radiance);
                    numbers[ID_INT.get(id)] += numbers_Sub[ID_INT.get(id)];
                }
            }
            for (int i = 0; ItemUITemplate.REVERSED_ITEM_IDS.size() > i; ++i) {
                Identifications id = ItemUITemplate.REVERSED_ITEM_IDS.get(i);
                if (0 > numbers[ID_INT.get(id)]) numbers[ID_INT.get(id)] = Math.round(numbers[ID_INT.get(id)] * radiance);
            }
        }

        for (int i = 0; ID_INT.size() > i; ++i) { //Reset Numbers Sub
            numbers_Sub[i] = 0;
        }

        //Ability Tree
        for (TreeCheckBox tcb : tree.getTcb()) {
            if (tcb.isSelected()) {
                switch (tcb.getFixedTreeName()) {
                    case "Earth Mastery":
                        numbers_Sub[ID_INT.get(Identifications.EARTH_DAMAGE_PERCENT)] += 20;
                        break;
                    case "Thunder Mastery":
                        numbers_Sub[ID_INT.get(Identifications.THUNDER_DAMAGE_PERCENT)] += 10;
                        break;
                    case "Water Mastery":
                        numbers_Sub[ID_INT.get(Identifications.WATER_DAMAGE_PERCENT)] += 15;
                        break;
                    case "Fire Mastery":
                        numbers_Sub[ID_INT.get(Identifications.FIRE_DAMAGE_PERCENT)] += 15;
                        break;
                    case "Air Mastery":
                        numbers_Sub[ID_INT.get(Identifications.AIR_DAMAGE_PERCENT)] += 15;
                        break;
                }
                switch (tree.getClasses()) {
                    case "warrior": {
                        switch (tcb.getFixedTreeName()) {
                            case "Tougher Skin":
                                numbers[ID_INT.get(Identifications.HEALTH_BONUS)] += Math.max(Math.min((numbers[ID_INT.get(Identifications.HEALTH_REGEN_PERCENT)] + numbers[ID_INT.get(Identifications.RAW_HEALTH_REGEN)]) * 10, 100), 0);
                            case "Mythril Skin":
                                def -= 0.05F;
                                break;
                            case "Vehement": numbers[ID_INT.get(Identifications.WALK_SPEED)] += Math.max(Math.min((numbers[ID_INT.get(Identifications.RAW_MELEE_DAMAGE)] + numbers[ID_INT.get(Identifications.MELEE_DAMAGE_PERCENT)]) * 2, 20), 0);
                                break;
                            case "Radiant Devotee": numbers[ID_INT.get(Identifications.MANA_REGEN)] += (int) Math.max(Math.min(Math.floor(numbers[ID_INT.get(Identifications.REFLECTION)] / 4F), 10), 0);
                                break;
                        }
                        break;
                    }
                    case "assassin": {
                        switch (tcb.getFixedTreeName()) {
                            case "Poisoned Blade":
                                numbers[ID_INT.get(Identifications.POISON)] += (int) Math.max(Math.min(Math.floor((numbers[ID_INT.get(Identifications.MELEE_DAMAGE_PERCENT)] + numbers[ID_INT.get(Identifications.RAW_MELEE_DAMAGE)]) / 2F), 50), 0);
                                break;
                            case "Shenanigans":
                                numbers[ID_INT.get(Identifications.MANA_STEAL)] += (int) Math.max(Math.min(Math.floor(numbers[ID_INT.get(Identifications.STEALING)] / 2F), 8), 0);
                                break;
                            case "Psithurism":
                                numbers[ID_INT.get(Identifications.WALK_SPEED)] += 20;
                                numbers[ID_INT.get(Identifications.JUMP_HEIGHT)] += 1;
                                break;
                        }
                        break;
                    }
                    case "archer": {
                        if (tcb.getFixedTreeName().equals("Traveler")) numbers[ID_INT.get(Identifications.RAW_SPELL_DAMAGE)] += Math.max(Math.min(numbers[ID_INT.get(Identifications.WALK_SPEED)], 100), 0);
                        break;
                    }
                    case "mage": {
                        switch (tcb.getFixedTreeName()) {
                            case "Wisdom":
                                numbers[ID_INT.get(Identifications.MANA_REGEN)] += (int) Math.max(Math.min(Math.floor((numbers[ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)] + numbers[ID_INT.get(Identifications.RAW_SPELL_DAMAGE)]) / 2F), 5), 0);
                                break;
                            case "Seance":
                                numbers[ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)] += (int) Math.max(Math.min(Math.floor(numbers[ID_INT.get(Identifications.LIFE_STEAL)] / 5F), 50), 0);
                                break;
                            case "Dynamic Faith":
                                numbers[ID_INT.get(Identifications.THUNDER_DAMAGE_PERCENT)] += (int) Math.max(Math.min(Math.floor(numbers[ID_INT.get(Identifications.SPRINT_BONUS)] / 2F), 40), 0);
                                break;
                        }
                        break;
                    }
                    case "shaman": {
                        if (tcb.getFixedTreeName().equals("Blood Moon")) numbers[ID_INT.get(Identifications.LIFE_STEAL)] += Math.max(Math.min(numbers[ID_INT.get(Identifications.SOUL_POINT_REGEN)] * 5, 1000), 0);
                        //switch (tcb.getFixedTreeName()) {
                        //    case "Blood Moon":
                        //        numbers[ID_INT.get(Identifications.LIFE_STEAL)] += Math.max(Math.min(numbers[ID_INT.get(Identifications.SOUL_POINT_REGEN)] * 5, 1000), 0);
                        //        break;
                        //}
                        break;
                    }
                }
            }
        }

        for (int i = 0; ID_INT.size() > i; ++i) { //ID_Main + ID_Sub = ID_Main
            numbers[i] += numbers_Sub[i];
        }

        //Powder Effects
        if (powder_effects.getPowderPanel("Earth").getValue() > 0) numbers[ID_INT.get(Identifications.EARTH_DAMAGE_PERCENT)] += powder_effects.getPowderPanel("Earth").getValue();
        if (powder_effects.getPowderPanel("Thunder").getValue() > 0) numbers[ID_INT.get(Identifications.THUNDER_DAMAGE_PERCENT)] += powder_effects.getPowderPanel("Thunder").getValue();
        if (powder_effects.getPowderPanel("Water").getValue() > 0) numbers[ID_INT.get(Identifications.WATER_DAMAGE_PERCENT)] += powder_effects.getPowderPanel("Water").getValue();
        if (powder_effects.getPowderPanel("Fire").getValue() > 0) numbers[ID_INT.get(Identifications.FIRE_DAMAGE_PERCENT)] += powder_effects.getPowderPanel("Fire").getValue();
        if (powder_effects.getPowderPanel("Air").getValue() > 0) numbers[ID_INT.get(Identifications.AIR_DAMAGE_PERCENT)] += powder_effects.getPowderPanel("Air").getValue();

        float enemy_Damage_Bonus = 1F;
        if (abilityBuffs.getBox().get(Ability_Buffs_Enum.WAR_SCREAM.getPos()).isSelected()) def *= 0.8F; //War Scream (+20% Resistance Bonus)
        if (abilityBuffs.getBox().get(Ability_Buffs_Enum.IVYROOT_MAMBA.getPos()).isSelected()) enemy_Damage_Bonus -= 0.15F; //Ivyroot Mamba (-15% Damage Bonus)
        if (abilityBuffs.getBox().get(Ability_Buffs_Enum.HAUNTING_MEMORY_LUNATIC.getPos()).isSelected()) enemy_Damage_Bonus -= 0.15F; //Haunting Memory (from Lunatic) (-15% Damage Bonus)
        def *= enemy_Damage_Bonus; //Enemy Damage Bonus
        if (abilityBuffs.getBox().get(Ability_Buffs_Enum.SAVIOURS_SACRIFICE.getPos()).isSelected()) def *= 0.8F;

        //Check Boxes and Slider
        switch (tree.getClasses()) {
            case "warrior": {
                if (abilityBuffs.getBox().get(Ability_Buffs_Enum.MANTLE.getPos()).isSelected()) def *= 0.3F; //Mantle
                if (abilityBuffs.getBox().get(Ability_Buffs_Enum.BRINK_OF_MADNESS.getPos()).isSelected()) def *= 0.6F; //Brink of Madness
                break;
            }
            case "assassin": {
                if (abilityBuffs.getBox().get(Ability_Buffs_Enum.MIRROR_IMAGE.getPos()).isSelected()) def *= 0.4F; //Mirror Image
                if (abilityBuffs.getBox().get(Ability_Buffs_Enum.DISSOLUTION.getPos()).isSelected()) def *= 0.25F; //Dissolution
                break;
            }
            case "shaman": {
                if (abilityBuffs.getBox().get(Ability_Buffs_Enum.MASK_OF_THE_LUNATIC.getPos()).isSelected()) def *= 1.2F; //Mask of the Lunatic
                if (abilityBuffs.getBox().get(Ability_Buffs_Enum.MASK_OF_THE_FANATIC.getPos()).isSelected()) { //Mask of the Fanatic
                    numbers[ID_INT.get(Identifications.WALK_SPEED)] -= 35;
                    def *= 0.65F;
                }
                if (abilityBuffs.getBox().get(Ability_Buffs_Enum.MASK_OF_THE_COWARD.getPos()).isSelected()) numbers[ID_INT.get(Identifications.WALK_SPEED)] += 80; //Mask of the Coward
                if (abilityBuffs.getBox().get(Ability_Buffs_Enum.CHANT_OF_THE_FANATIC.getPos()).isSelected()) def *= 0.3F; //Chant of the Fanatic
                if (abilityBuffs.getBox().get(Ability_Buffs_Enum.AWAKENED.getPos()).isSelected()) { //Mask of the Awakened
                    def *= 0.65F;
                    numbers[ID_INT.get(Identifications.WALK_SPEED)] += 80;
                }
                break;
            }
        }

        id_Numbers = numbers; //Copy ID Numbers

        int health = numbers[ID_INT.get(Identifications.HEALTH)] + numbers[ID_INT.get(Identifications.HEALTH_BONUS)]; //Health + Health Bonus
        if (health < 5) health = 5;
        float defSP = sp.getSkillPoint(SkillPoint.SkillPointType.DEFENSE).getSPBoost() - 1F; //Defense Skill Point
        float agiSP = sp.getSkillPoint(SkillPoint.SkillPointType.AGILITY).getSPBoost() - 1F; //Agility Skill Point
        float ehp = health / (0.1F * agiSP + (1F - agiSP) * (1F - defSP)) / def; //Effective Health Calc
        ids.add(new JLabel("Health: " + health)); //Total Health
        ids.add(new JLabel("EHP: " + ehp)); //Effective Health
        ids.add(new JLabel("EHP (No Agi): " + (health / ((1F - defSP) * def)))); //Effective Health (No Agility)
        if (numbers[ID_Display.ID_INT.get(Identifications.RAW_HEALTH_REGEN)] < 0) {
            ids.add(new JLabel("HPR: " + Math.min(Math.round(numbers[ID_INT.get(Identifications.RAW_HEALTH_REGEN)] * ((100F + (numbers[ID_INT.get(Identifications.HEALTH_REGEN_PERCENT)] * -1)) / 100F)), 0))); //Total Health Regen
        } else {
            ids.add(new JLabel("HPR: " + (Math.round(numbers[ID_INT.get(Identifications.RAW_HEALTH_REGEN)] * ((100F + numbers[ID_INT.get(Identifications.HEALTH_REGEN_PERCENT)]) / 100F))))); //Total Health Regen
        }
        ids.add(new JLabel("Life Steal: " + numbers[ID_INT.get(Identifications.LIFE_STEAL)] + "/3s")); //Life Steal
        ids.add(new JLabel(" "));
        for (int i = 1; IDS_.size() > i; ++i) { //Defenses
            ids.add(new JLabel(IDS_.get(i).getDisplayName() + ": " + (int) Math.floor(numbers[ID_INT.get(IDS_.get(i))] * ((100F + numbers[ID_INT.get(Identifications.EARTH_DEFENSE_PERCENT) + i - 1] + numbers[ID_INT.get(Identifications.ELEMENTAL_DEFENSE_PERCENT)]) / 100F))));
        }
        ids.add(new JLabel(" "));
        for (int i = 0; ItemUITemplate.ITEM_IDS.size() > i; ++i) { //Normal IDS
            Identifications id = ItemUITemplate.ITEM_IDS.get(i);
            if (numbers[ID_INT.get(id)] != 0) ids.add(new JLabel(id.getDisplayName() + ": " + numbers[ID_INT.get(id)] + id.getDisplaySp()));
        }
        for (int i = 0; ItemUITemplate.REVERSED_ITEM_IDS.size() > i; ++i) { //Reversed IDS
            Identifications id = ItemUITemplate.REVERSED_ITEM_IDS.get(i);
            if (numbers[ID_INT.get(id)] != 0) ids.add(new JLabel(id.getDisplayName() + ": " + numbers[ID_INT.get(id)] + id.getDisplaySp()));
        }

        for (JLabel l : ids) {
            pane.add(l);
        }
        SwingUtilities.updateComponentTreeUI(pane);
    }

    public void setSetBonuses(String itemName, boolean isWeapon) {
        JsonObject j = new GetAPI().getSetItems();
        if (j.get(itemName) != null) {
            String s = j.get(itemName).getAsString();
            if (!setBonuses.isEmpty()) {
                boolean hasThisSetBonus = false;
                for (SetBonus setBonus : setBonuses) {
                    if (setBonus.getSetName().equals(s)) {
                        hasThisSetBonus = true;
                        setBonus.add(itemName, isWeapon);
                    }
                }
                if (!hasThisSetBonus) {
                    setBonuses.add(new SetBonus(s, itemName, isWeapon));
                }
            } else {
                setBonuses.add(new SetBonus(s, itemName, isWeapon));
            }
        }
    }

    public int[] getId_Numbers() {
        return id_Numbers;
    }

    public List<SetBonus> getSetBonuses() {
        return setBonuses;
    }

    private static void setNumbers(JsonObject json, int[] numbers) {
        if (json.get("base") != null) {
            JsonObject j = json.get("base").getAsJsonObject();
            if (j.get(Identifications.HEALTH.getItemName()) != null) {
                JsonElement j2 = j.get(Identifications.HEALTH.getItemName());
                if (json.get(Identifications.RARITY.getItemName()) != null && json.get(Identifications.RARITY.getItemName()).getAsString().equals("crafted") && j2.isJsonObject()) {
                    numbers[ID_INT.get(Identifications.HEALTH)] += j2.getAsJsonObject().get("max").getAsInt();
                } else if (j2.getAsInt() != 0) {
                    numbers[ID_INT.get(Identifications.HEALTH)] += j2.getAsInt();
                }
            }

            if (j.get(Identifications.EARTH_DEFENSE.getItemName()) != null && j.get(Identifications.EARTH_DEFENSE.getItemName()).getAsInt() != 0) {
                numbers[ID_INT.get(Identifications.EARTH_DEFENSE)] += j.get(Identifications.EARTH_DEFENSE.getItemName()).getAsInt();
            }

            if (j.get(Identifications.THUNDER_DEFENSE.getItemName()) != null && j.get(Identifications.THUNDER_DEFENSE.getItemName()).getAsInt() != 0) {
                numbers[ID_INT.get(Identifications.THUNDER_DEFENSE)] += j.get(Identifications.THUNDER_DEFENSE.getItemName()).getAsInt();
            }

            if (j.get(Identifications.WATER_DEFENSE.getItemName()) != null && j.get(Identifications.WATER_DEFENSE.getItemName()).getAsInt() != 0) {
                numbers[ID_INT.get(Identifications.WATER_DEFENSE)] += j.get(Identifications.WATER_DEFENSE.getItemName()).getAsInt();
            }

            if (j.get(Identifications.FIRE_DEFENSE.getItemName()) != null && j.get(Identifications.FIRE_DEFENSE.getItemName()).getAsInt() != 0) {
                numbers[ID_INT.get(Identifications.FIRE_DEFENSE)] += j.get(Identifications.FIRE_DEFENSE.getItemName()).getAsInt();
            }

            if (j.get(Identifications.AIR_DEFENSE.getItemName()) != null && j.get(Identifications.AIR_DEFENSE.getItemName()).getAsInt() != 0) {
                numbers[ID_INT.get(Identifications.AIR_DEFENSE)] += j.get(Identifications.AIR_DEFENSE.getItemName()).getAsInt();
            }
        }

        for (int i = 0; ItemUITemplate.ITEM_IDS.size() > i; ++i) {
            Identifications id = ItemUITemplate.ITEM_IDS.get(i);
            if (json.get(id.getItemFieldPos()) != null && json.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()) != null) {
                JsonElement j = json.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName());
                if (!j.isJsonObject()) {
                    numbers[ID_INT.get(id)] += j.getAsInt();
                } else if (json.get("identified") != null && json.get("identified").getAsBoolean()) {
                    String minOrMax = "max";
                    if (j.getAsJsonObject().get("max").getAsInt() < 0) minOrMax = "min";
                    numbers[ID_INT.get(id)] += SearchUI.getBaseID(j.getAsJsonObject().get(minOrMax).getAsInt());
                } else if (id.isItemVariable() || json.get(Identifications.RARITY.getItemName()).getAsString().equals("crafted")) {
                    numbers[ID_INT.get(id)] += j.getAsJsonObject().get("max").getAsInt();
                }
            }
        }

        for (int i = 0; ItemUITemplate.REVERSED_ITEM_IDS.size() > i; ++i) {
            Identifications id = ItemUITemplate.REVERSED_ITEM_IDS.get(i);
            if (json.get(id.getItemFieldPos()) != null && json.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName()) != null) {
                JsonElement j = json.get(id.getItemFieldPos()).getAsJsonObject().get(id.getItemName());
                if (!j.isJsonObject()) {
                    numbers[ID_INT.get(id)] += j.getAsInt();
                } else if (json.get("identified") != null && json.get("identified").getAsBoolean()) {
                    numbers[ID_INT.get(id)] += SearchUI.getBaseID(j.getAsJsonObject().get("max").getAsInt());
                } else {
                    int base = SearchUI.getBaseID(j.getAsJsonObject().get("max").getAsInt());
                    numbers[ID_INT.get(id)] += ItemUITemplate.getReversedMinInt(base);
                }
            }
        }
    }

    private static void setArmorPowder(JsonObject json, JsonObject powderJ, JTextField powder, int[] numbers) {
        if (json.get(Identifications.POWDER_SLOTS.getItemName()) != null && json.get(Identifications.POWDER_SLOTS.getItemName()).getAsInt() > 0 && powder.getText().length() > 1) {
            for (int i = 0; (int) Math.floor(powder.getText().length() / 2F) * 2 > i; i += 2) {
                if (json.get(Identifications.POWDER_SLOTS.getItemName()).getAsInt() >= i / 2) {
                    String name = String.valueOf(powder.getText().charAt(i)) + powder.getText().charAt(i + 1);
                    if (powderJ.get(name) != null) {
                        JsonObject j = powderJ.get(name).getAsJsonObject();
                        if (j.get("earth_def") != null) numbers[ID_INT.get(Identifications.EARTH_DEFENSE)] += j.get("earth_def").getAsInt();
                        if (j.get("thunder_def") != null) numbers[ID_INT.get(Identifications.THUNDER_DEFENSE)] += j.get("thunder_def").getAsInt();
                        if (j.get("water_def") != null) numbers[ID_INT.get(Identifications.WATER_DEFENSE)] += j.get("water_def").getAsInt();
                        if (j.get("fire_def") != null) numbers[ID_INT.get(Identifications.FIRE_DEFENSE)] += j.get("fire_def").getAsInt();
                        if (j.get("air_def") != null) numbers[ID_INT.get(Identifications.AIR_DEFENSE)] += j.get("air_def").getAsInt();
                    }
                } else {
                    break;
                }
            }
        }
    }
}
