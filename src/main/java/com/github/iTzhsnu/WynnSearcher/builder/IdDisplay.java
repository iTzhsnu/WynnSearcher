package com.github.iTzhsnu.WynnSearcher.builder;

import com.github.iTzhsnu.WynnSearcher.ApiDataManager;
import com.github.iTzhsnu.WynnSearcher.Identifications;
import com.github.iTzhsnu.WynnSearcher.data.ItemBase;
import com.github.iTzhsnu.WynnSearcher.general.JsonKeys;
import com.github.iTzhsnu.WynnSearcher.general.JsonValues;
import com.github.iTzhsnu.WynnSearcher.ui.ItemUi;
import com.github.iTzhsnu.WynnSearcher.builder.skilltree.TreeBase;
import com.github.iTzhsnu.WynnSearcher.builder.skilltree.TreeCheckBox;
import com.google.gson.JsonObject;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;

public class IdDisplay {
    private final JPanel pane = new JPanel();
    private final List<JLabel> ids = new ArrayList<>();

    private int[] idNumbers = new int[] {};
    private float def = 0;

    private final List<SetBonus> setBonuses = new ArrayList<>();
    private final JsonObject setListJson;

    public static final Map<Identifications, Integer> ID_INT = new HashMap<>();

    private static final Map<Integer, Float> CLASS_DEF = new HashMap<>() {{
        put(0, 1F); //Warrior
        put(1, 1F); //Assassin
        put(2, 0.8F); //Mage
        put(3, 0.7F); //Archer
        put(4, 0.6F); //Shaman
    }};

    public IdDisplay(JPanel p) {
        setListJson = ApiDataManager.getManager().getManualSetItems();

        pane.setPreferredSize(new Dimension(250, 2500));
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(pane);
        scrollPane.setBounds(422, 710, 268, 400);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);

        p.add(scrollPane);

        setIdInt();
    }

    public void setIdInt() {
        for (int i = 0; ItemUi.DEFENSE_IDS.size() > i; ++i) {
            ID_INT.put(ItemUi.DEFENSE_IDS.get(i), i);
        }
        for (int i = 0; ItemUi.ITEM_IDS.size() > i; ++i) {
            ID_INT.put(ItemUi.ITEM_IDS.get(i), i + ItemUi.DEFENSE_IDS.size());
        }
        for (int i = 0; ItemUi.REVERSED_ITEM_IDS.size() > i; ++i) {
            ID_INT.put(ItemUi.REVERSED_ITEM_IDS.get(i), i + ItemUi.DEFENSE_IDS.size() + ItemUi.ITEM_IDS.size());
        }
    }

    public void setIds(ItemData itemData, DamageId damageIds, SkillPoint sp, TreeBase tree, AbilityBuffs abilityBuffs, PowderEffect powderEffects, List<JTextField> powders, int classId, boolean updateOnly) {
        pane.removeAll();
        if (!ids.isEmpty()) ids.clear();
        if (!setBonuses.isEmpty()) setBonuses.clear();
        int[] numbers = new int[] { // 2026/04/26 ITEM_IDS.size() == 87, REVERSED_ITEM_IDS.size() == 8, DEFENSE_IDS.size() == 6, total size: 101
                610, 0, 0, 0, 0, 0, 0, 0, 0, 0, //0 ~ 9 (ID)
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
        int[] numbersSub = new int[] {
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
        def = 2F - CLASS_DEF.get(classId); //Base Defense

        //Tomes
        if (itemData.getTomes() != null) { //Tomes
            for (ItemBase item : itemData.getTomes()) {
                setNumbers(item, numbers);
            }
        }

        //Weapon, Armor, Accessory
        if (!itemData.getEquipmentsNoWeapon().isEmpty()) {
            for (ItemBase item : itemData.getEquipmentsNoWeapon()) {
                setNumbers(item, numbers);
                setSetBonuses(item.getName(), false); // Auto Detect Set Bonus
            }
        }

        if (itemData.getWeapon() != null) {
            setNumbers(itemData.getWeapon(), numbers);
            setSetBonuses(itemData.getWeapon().getName(), true);
        }

        //Set Bonus
        if (!setBonuses.isEmpty()) {
            for (SetBonus setBonus : setBonuses) {
                setBonus.add_to_ID_Numbers(numbers);
            }
        }

        JsonObject powderJ = ApiDataManager.getManager().powderData;

        //Helmet Powder
        if (itemData.getHelmet() != null) {
            setArmorPowder(itemData.getHelmet(), powderJ, powders.get(0), numbers);
        }

        //Chestplate Powder
        if (itemData.getChestplate() != null) {
            setArmorPowder(itemData.getChestplate(), powderJ, powders.get(1), numbers);
        }

        //Leggings Powder
        if (itemData.getLeggings() != null) {
            setArmorPowder(itemData.getLeggings(), powderJ, powders.get(2), numbers);
        }

        //Boots Powder
        if (itemData.getBoots() != null) {
            setArmorPowder(itemData.getBoots(), powderJ, powders.get(3), numbers);
        }

        if (updateOnly) { //Update Edited ID
            for (int i = 0; ItemUi.ITEM_IDS.size() > i; ++i) { //Damages and Health Regen
                Identifications id = ItemUi.ITEM_IDS.get(i);
                if (DamageId.GET_DAMAGE_ID_NUM_FROM_ID.getOrDefault(id, -1) != -1) {
                    numbers[ID_INT.get(id)] = damageIds.getID(DamageId.GET_DAMAGE_ID_NUM_FROM_ID.get(id)).getValue();
                }
            }
            for (int i = 0; ItemUi.REVERSED_ITEM_IDS.size() > i; ++i) { //Spell Costs
                Identifications id = ItemUi.REVERSED_ITEM_IDS.get(i);
                if (DamageId.GET_DAMAGE_ID_NUM_FROM_ID.getOrDefault(id, -1) != -1) {
                    numbers[ID_INT.get(id)] = damageIds.getID(DamageId.GET_DAMAGE_ID_NUM_FROM_ID.get(id)).getValue();
                }
            }
        } else { //Set Editable ID Original Num
            for (int i = 0; ItemUi.ITEM_IDS.size() > i; ++i) { //Damages and Health Regen
                Identifications id = ItemUi.ITEM_IDS.get(i);
                if (DamageId.GET_DAMAGE_ID_NUM_FROM_ID.getOrDefault(id, -1) != -1) {
                    damageIds.getID(DamageId.GET_DAMAGE_ID_NUM_FROM_ID.get(id)).setValue(numbers[ID_INT.get(id)]);
                }
            }
            for (int i = 0; ItemUi.REVERSED_ITEM_IDS.size() > i; ++i) { //Spell Costs
                Identifications id = ItemUi.REVERSED_ITEM_IDS.get(i);
                if (DamageId.GET_DAMAGE_ID_NUM_FROM_ID.getOrDefault(id, -1) != -1) {
                    damageIds.getID(DamageId.GET_DAMAGE_ID_NUM_FROM_ID.get(id)).setValue(numbers[ID_INT.get(id)]);
                }
            }
        }

        //Radiance
        if (abilityBuffs.getBox().get(AbilityBuffsEnum.RADIANCE.getPos()).isSelected()) {
            float radiance = 1.2F;
            if (itemData.getMajorIDList().contains(MajorIdEnum.DIVINE_HONOR)) radiance += 0.05F; //MAJOR ID: DIVINE HONOR
            for (int i = 5; ItemUi.ITEM_IDS.size() > i; ++i) {
                Identifications id = ItemUi.ITEM_IDS.get(i);
                if (id == Identifications.COMBAT_XP_BONUS || id == Identifications.LOOT_BONUS || id == Identifications.LOOT_QUALITY || id == Identifications.GATHERING_XP_BONUS || id == Identifications.GATHERING_SPEED_BONUS) continue;
                if (numbers[ID_INT.get(id)] > 0) {
                    numbers[ID_INT.get(id)] -= numbersSub[ID_INT.get(id)];
                    numbers[ID_INT.get(id)] = Math.round(numbers[ID_INT.get(id)] * radiance);
                    numbers[ID_INT.get(id)] += numbersSub[ID_INT.get(id)];
                }
            }
            for (int i = 0; ItemUi.REVERSED_ITEM_IDS.size() > i; ++i) {
                Identifications id = ItemUi.REVERSED_ITEM_IDS.get(i);
                if (0 > numbers[ID_INT.get(id)]) numbers[ID_INT.get(id)] = Math.round(numbers[ID_INT.get(id)] * radiance);
            }
        }

        for (int i = 0; ID_INT.size() > i; ++i) { //Reset Numbers Sub
            numbersSub[i] = 0;
        }

        //Ability Tree
        setTreeEffects(tree, numbers, numbersSub);

        for (int i = 0; ID_INT.size() > i; ++i) { //ID_Main + ID_Sub = ID_Main
            numbers[i] += numbersSub[i];
        }

        //Powder Effects
        if (powderEffects.getPowderPanel("Earth").getValue() > 0) numbers[ID_INT.get(Identifications.EARTH_DAMAGE_PERCENT)] += powderEffects.getPowderPanel("Earth").getValue();
        if (powderEffects.getPowderPanel("Thunder").getValue() > 0) numbers[ID_INT.get(Identifications.THUNDER_DAMAGE_PERCENT)] += powderEffects.getPowderPanel("Thunder").getValue();
        if (powderEffects.getPowderPanel("Water").getValue() > 0) numbers[ID_INT.get(Identifications.WATER_DAMAGE_PERCENT)] += powderEffects.getPowderPanel("Water").getValue();
        if (powderEffects.getPowderPanel("Fire").getValue() > 0) numbers[ID_INT.get(Identifications.FIRE_DAMAGE_PERCENT)] += powderEffects.getPowderPanel("Fire").getValue();
        if (powderEffects.getPowderPanel("Air").getValue() > 0) numbers[ID_INT.get(Identifications.AIR_DAMAGE_PERCENT)] += powderEffects.getPowderPanel("Air").getValue();

        float enemy_Damage_Bonus = 1F;
        // TODO UPDATE BUFFS
        if (abilityBuffs.getBox().get(AbilityBuffsEnum.WAR_SCREAM.getPos()).isSelected()) def *= 0.8F; //War Scream (+20% Resistance Bonus)
        if (abilityBuffs.getBox().get(AbilityBuffsEnum.IVYROOT_MAMBA.getPos()).isSelected()) enemy_Damage_Bonus -= 0.15F; //Ivyroot Mamba (-15% Damage Bonus)
        if (abilityBuffs.getBox().get(AbilityBuffsEnum.HAUNTING_MEMORY_LUNATIC.getPos()).isSelected()) enemy_Damage_Bonus -= 0.15F; //Haunting Memory (from Lunatic) (-15% Damage Bonus)
        def *= enemy_Damage_Bonus; //Enemy Damage Bonus
        if (abilityBuffs.getBox().get(AbilityBuffsEnum.SAVIOURS_SACRIFICE.getPos()).isSelected()) def *= 0.8F;

        //Check Boxes and Slider
        switch (tree.getClasses()) {
            case JsonValues.WARRIOR: {
                if (abilityBuffs.getBox().get(AbilityBuffsEnum.MANTLE.getPos()).isSelected()) def *= 0.3F; //Mantle
                if (abilityBuffs.getBox().get(AbilityBuffsEnum.BRINK_OF_MADNESS.getPos()).isSelected()) def *= 0.6F; //Brink of Madness
                break;
            }
            case JsonValues.ASSASSIN: {
                if (abilityBuffs.getBox().get(AbilityBuffsEnum.MIRROR_IMAGE.getPos()).isSelected()) def *= 0.4F; //Mirror Image
                if (abilityBuffs.getBox().get(AbilityBuffsEnum.DISSOLUTION.getPos()).isSelected()) def *= 0.25F; //Dissolution
                break;
            }
            case JsonValues.SHAMAN: {
                if (abilityBuffs.getBox().get(AbilityBuffsEnum.MASK_OF_THE_LUNATIC.getPos()).isSelected()) def *= 1.2F; //Mask of the Lunatic
                if (abilityBuffs.getBox().get(AbilityBuffsEnum.MASK_OF_THE_FANATIC.getPos()).isSelected()) { //Mask of the Fanatic
                    numbers[ID_INT.get(Identifications.WALK_SPEED)] -= 35;
                    def *= 0.65F;
                }
                if (abilityBuffs.getBox().get(AbilityBuffsEnum.MASK_OF_THE_COWARD.getPos()).isSelected()) numbers[ID_INT.get(Identifications.WALK_SPEED)] += 80; //Mask of the Coward
                if (abilityBuffs.getBox().get(AbilityBuffsEnum.CHANT_OF_THE_FANATIC.getPos()).isSelected()) def *= 0.3F; //Chant of the Fanatic
                if (abilityBuffs.getBox().get(AbilityBuffsEnum.AWAKENED.getPos()).isSelected()) { //Mask of the Awakened
                    def *= 0.65F;
                    numbers[ID_INT.get(Identifications.WALK_SPEED)] += 80;
                }
                break;
            }
        }

        idNumbers = numbers; //Copy ID Numbers

        int health = numbers[ID_INT.get(Identifications.HEALTH)] + numbers[ID_INT.get(Identifications.HEALTH_BONUS)]; //Health + Health Bonus
        if (health < 5) health = 5;
        float defSP = sp.getSkillPoint(SkillPoint.SkillPointType.DEFENSE).getSPBoost() - 1F; //Defense Skill Point
        float agiSP = sp.getSkillPoint(SkillPoint.SkillPointType.AGILITY).getSPBoost() - 1F; //Agility Skill Point
        float ehp = health / (0.1F * agiSP + (1F - agiSP) * (1F - defSP)) / def; //Effective Health Calc
        ids.add(new JLabel("Health: " + health)); //Total Health
        ids.add(new JLabel("EHP: " + ehp)); //Effective Health
        ids.add(new JLabel("EHP (No Agi): " + (health / ((1F - defSP) * def)))); //Effective Health (No Agility)
        if (numbers[IdDisplay.ID_INT.get(Identifications.RAW_HEALTH_REGEN)] < 0) {
            ids.add(new JLabel("HPR: " + Math.min(Math.round(numbers[ID_INT.get(Identifications.RAW_HEALTH_REGEN)] * ((100F + (numbers[ID_INT.get(Identifications.HEALTH_REGEN_PERCENT)] * -1)) / 100F)), 0))); //Total Health Regen
        } else {
            ids.add(new JLabel("HPR: " + (Math.round(numbers[ID_INT.get(Identifications.RAW_HEALTH_REGEN)] * ((100F + numbers[ID_INT.get(Identifications.HEALTH_REGEN_PERCENT)]) / 100F))))); //Total Health Regen
        }
        ids.add(new JLabel("Life Steal: " + numbers[ID_INT.get(Identifications.LIFE_STEAL)] + "/3s")); //Life Steal
        ids.add(new JLabel(" "));
        for (int i = 1; ItemUi.DEFENSE_IDS.size() > i; ++i) { //Defenses
            ids.add(new JLabel(ItemUi.DEFENSE_IDS.get(i).getDisplayName() + ": " + (int) Math.floor(numbers[ID_INT.get(ItemUi.DEFENSE_IDS.get(i))] * ((100F + numbers[ID_INT.get(Identifications.EARTH_DEFENSE_PERCENT) + i - 1] + numbers[ID_INT.get(Identifications.ELEMENTAL_DEFENSE_PERCENT)]) / 100F))));
        }
        ids.add(new JLabel(" "));
        for (int i = 0; ItemUi.ITEM_IDS.size() > i; ++i) { //Normal IDS
            Identifications id = ItemUi.ITEM_IDS.get(i);
            if (numbers[ID_INT.get(id)] != 0) ids.add(new JLabel(id.getDisplayName() + ": " + numbers[ID_INT.get(id)] + id.getDisplaySp()));
        }
        for (int i = 0; ItemUi.REVERSED_ITEM_IDS.size() > i; ++i) { //Reversed IDS
            Identifications id = ItemUi.REVERSED_ITEM_IDS.get(i);
            if (numbers[ID_INT.get(id)] != 0) ids.add(new JLabel(id.getDisplayName() + ": " + numbers[ID_INT.get(id)] + id.getDisplaySp()));
        }

        for (JLabel l : ids) {
            pane.add(l);
        }
        SwingUtilities.updateComponentTreeUI(pane);
    }

    // TODO update use data
    public void setSetBonuses(String itemName, boolean isWeapon) {
        if (setListJson.get(itemName) != null) {
            String s = setListJson.get(itemName).getAsString();
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

    public int[] getIdNumbers() {
        return idNumbers;
    }

    public List<SetBonus> getSetBonuses() {
        return setBonuses;
    }

    private static void setNumbers(ItemBase item, int[] numbers) {
        for (int i = 0; ItemUi.DEFENSE_IDS.size() > i; ++i) {
            Identifications id = ItemUi.DEFENSE_IDS.get(i);
            numbers[ID_INT.get(id)] += item.getIdValue(id, JsonKeys.MAX);
        }

        for (int i = 0; ItemUi.ITEM_IDS.size() > i; ++i) {
            Identifications id = ItemUi.ITEM_IDS.get(i);
            numbers[ID_INT.get(id)] += item.getIdValue(id, JsonKeys.MAX);
        }

        for (int i = 0; ItemUi.REVERSED_ITEM_IDS.size() > i; ++i) {
            Identifications id = ItemUi.REVERSED_ITEM_IDS.get(i);
            numbers[ID_INT.get(id)] += item.getIdValue(id, JsonKeys.MAX);
        }
    }

    private static void setArmorPowder(ItemBase item, JsonObject powderJ, JTextField powder, int[] numbers) {
        int slot = item.getIdValue(Identifications.POWDER_SLOTS, JsonKeys.MAX);
        if (slot > 0 && powder.getText().length() > 1) {
            for (int i = 0; (int) Math.floor(powder.getText().length() / 2F) * 2 > i; i += 2) {
                if (slot >= i / 2) {
                    String name = String.valueOf(powder.getText().charAt(i)) + powder.getText().charAt(i + 1);
                    if (powderJ.get(name) != null) {
                        JsonObject j = powderJ.get(name).getAsJsonObject();
                        if (j.get(Identifications.HEALTH_BONUS.getItemName()) != null) numbers[ID_INT.get(Identifications.HEALTH_BONUS)] += j.get(Identifications.HEALTH_BONUS.getItemName()).getAsInt();
                        if (j.get(Identifications.EARTH_DEFENSE.getItemName()) != null) numbers[ID_INT.get(Identifications.EARTH_DEFENSE)] += j.get(Identifications.EARTH_DEFENSE.getItemName()).getAsInt();
                        if (j.get(Identifications.THUNDER_DEFENSE.getItemName()) != null) numbers[ID_INT.get(Identifications.THUNDER_DEFENSE)] += j.get(Identifications.THUNDER_DEFENSE.getItemName()).getAsInt();
                        if (j.get(Identifications.WATER_DEFENSE.getItemName()) != null) numbers[ID_INT.get(Identifications.WATER_DEFENSE)] += j.get(Identifications.WATER_DEFENSE.getItemName()).getAsInt();
                        if (j.get(Identifications.FIRE_DEFENSE.getItemName()) != null) numbers[ID_INT.get(Identifications.FIRE_DEFENSE)] += j.get(Identifications.FIRE_DEFENSE.getItemName()).getAsInt();
                        if (j.get(Identifications.AIR_DEFENSE.getItemName()) != null) numbers[ID_INT.get(Identifications.AIR_DEFENSE)] += j.get(Identifications.AIR_DEFENSE.getItemName()).getAsInt();
                    }
                } else {
                    break;
                }
            }
        }
    }

    public void setTreeEffects(TreeBase tree, int[] numbers, int[] numberSub) {
        for (TreeCheckBox tcb : tree.getTcb()) {
            if (tcb.isSelected()) {
                setCommonTreeEffects(tcb, numberSub);

                switch (tree.getClasses()) {
                    case JsonValues.WARRIOR:
                        setWarriorEffects(tcb, numbers);
                        break;
                    case JsonValues.ASSASSIN:
                        setAssassinEffects(tcb, numbers);
                        break;
                    case JsonValues.ARCHER:
                        setArcherEffects(tcb, numbers);
                        break;
                    case JsonValues.MAGE:
                        setMageEffects(tcb, numbers);
                        break;
                    case JsonValues.SHAMAN:
                        setShamanEffects(tcb, numbers);
                        break;
                }
            }
        }
    }

    public void setCommonTreeEffects(TreeCheckBox tcb, int[] numbersSub) {
        switch (tcb.getFixedTreeName()) {
            case "Earth Mastery":
                numbersSub[ID_INT.get(Identifications.EARTH_DAMAGE_PERCENT)] += 20;
                break;
            case "Thunder Mastery":
                numbersSub[ID_INT.get(Identifications.THUNDER_DAMAGE_PERCENT)] += 10;
                break;
            case "Water Mastery":
                numbersSub[ID_INT.get(Identifications.WATER_DAMAGE_PERCENT)] += 15;
                break;
            case "Fire Mastery":
                numbersSub[ID_INT.get(Identifications.FIRE_DAMAGE_PERCENT)] += 15;
                break;
            case "Air Mastery":
                numbersSub[ID_INT.get(Identifications.AIR_DAMAGE_PERCENT)] += 15;
                break;
        }
    }

    public void setWarriorEffects(TreeCheckBox tcb, int[] numbers) {
        switch (tcb.getFixedTreeName()) {
            case "Vehement": // Raw Damage +5, Walk Speed +(Melee Damage % and Raw * 2)  {Max 20}
                numbers[ID_INT.get(Identifications.RAW_DAMAGE)] += 5;
                numbers[ID_INT.get(Identifications.WALK_SPEED)] += Math.max(Math.min((numbers[ID_INT.get(Identifications.RAW_MELEE_DAMAGE)] + numbers[ID_INT.get(Identifications.MELEE_DAMAGE_PERCENT)]) * 2, 20), 0);
                break;
            case "Tougher Skin": // Health Bonus +(Health Regen % and Raw * 10) {Max 100}
                numbers[ID_INT.get(Identifications.HEALTH_BONUS)] += Math.max(Math.min((numbers[ID_INT.get(Identifications.HEALTH_REGEN_PERCENT)] + numbers[ID_INT.get(Identifications.RAW_HEALTH_REGEN)]) * 10, 100), 0);
            case "Mythril Skin":
                def -= 0.05F;
                break;
            case "Radiant Devotee": // Mana Regen +(Reflection / 4) {Max 10}
                numbers[ID_INT.get(Identifications.MANA_REGEN)] += (int) Math.max(Math.min(Math.floor(numbers[ID_INT.get(Identifications.REFLECTION)] * 0.25F), 10), 0);
                break;
        }
    }

    public void setAssassinEffects(TreeCheckBox tcb, int[] numbers) {
        switch (tcb.getFixedTreeName()) {
            case "Poisoned Blade": // Poison +5 * {Melee Damage % and Raw / 2) {Max 50}
                numbers[ID_INT.get(Identifications.POISON)] += (int) Math.max(Math.min(5 * Math.floor((numbers[ID_INT.get(Identifications.MELEE_DAMAGE_PERCENT)] + numbers[ID_INT.get(Identifications.RAW_MELEE_DAMAGE)]) * 0.5F), 50), 0);
                break;
            case "Psithurism": // Walk Speed +20%, Jump Height +1
                numbers[ID_INT.get(Identifications.WALK_SPEED)] += 20;
                numbers[ID_INT.get(Identifications.JUMP_HEIGHT)] += 1;
                break;

        }
    }

    public void setArcherEffects(TreeCheckBox tcb, int[] numbers) {}

    public void setMageEffects(TreeCheckBox tcb, int[] numbers) {
        switch (tcb.getFixedTreeName()) {
            case "Wisdom": // Mana Regen +(Spell Damage % and Raw / 2) {Max 5}
                numbers[ID_INT.get(Identifications.MANA_REGEN)] += (int) Math.max(Math.min(Math.floor((numbers[ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)] + numbers[ID_INT.get(Identifications.RAW_SPELL_DAMAGE)]) * 0.5F), 5), 0);
                break;
            case "Seance": // Spell Damage +(Life Steal / 5)% {Max 50}
                numbers[ID_INT.get(Identifications.SPELL_DAMAGE_PERCENT)] += (int) Math.max(Math.min(Math.floor(numbers[ID_INT.get(Identifications.LIFE_STEAL)] * 0.2F), 50), 0);
                break;
        }
    }

    public void setShamanEffects(TreeCheckBox tcb, int[] numbers) {}
}
