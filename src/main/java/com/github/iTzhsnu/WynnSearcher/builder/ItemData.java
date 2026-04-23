package com.github.iTzhsnu.WynnSearcher.builder;

import com.github.iTzhsnu.WynnSearcher.data.ItemBase;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemData {
    private final ItemBase helmet;
    private final ItemBase chestplate;
    private final ItemBase leggings;
    private final ItemBase boots;
    private final ItemBase ring1;
    private final ItemBase ring2;
    private final ItemBase bracelet;
    private final ItemBase necklace;
    private final ItemBase weapon;

    //Tomes
    private final ItemBase armourTome1;
    private final ItemBase armourTome2;
    private final ItemBase armourTome3;
    private final ItemBase armourTome4;
    private final ItemBase guildTome;
    private final ItemBase weaponTome1;
    private final ItemBase weaponTome2;
    private final ItemBase marathonTome1;
    private final ItemBase marathonTome2;
    private final ItemBase lootrunTome;
    private final ItemBase expertiseTome1;
    private final ItemBase expertiseTome2;
    private final ItemBase mysticismTome1;
    private final ItemBase mysticismTome2;

    //Aspects
    private final ItemBase aspect1;
    private final ItemBase aspect2;
    private final ItemBase aspect3;
    private final ItemBase aspect4;
    private final ItemBase aspect5;

    public ItemData(ItemBase helmet, ItemBase chestplate, ItemBase leggings, ItemBase boots, ItemBase ring1, ItemBase ring2, ItemBase bracelet, ItemBase necklace, ItemBase weapon
            , ItemBase armourTome1, ItemBase armourTome2, ItemBase armourTome3, ItemBase armourTome4, ItemBase guildTome
            , ItemBase weaponTome1, ItemBase weaponTome2, ItemBase marathonTome1, ItemBase marathonTome2, ItemBase lootrunTome
            , ItemBase expertiseTome1, ItemBase expertiseTome2, ItemBase mysticismTome1, ItemBase mysticismTome2
            , ItemBase aspect1, ItemBase aspect2, ItemBase aspect3, ItemBase aspect4, ItemBase aspect5) {
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boots;
        this.ring1 = ring1;
        this.ring2 = ring2;
        this.bracelet = bracelet;
        this.necklace = necklace;
        this.weapon = weapon;

        this.armourTome1 = armourTome1;
        this.armourTome2 = armourTome2;
        this.armourTome3 = armourTome3;
        this.armourTome4 = armourTome4;
        this.guildTome = guildTome;
        this.weaponTome1 = weaponTome1;
        this.weaponTome2 = weaponTome2;
        this.marathonTome1 = marathonTome1;
        this.marathonTome2 = marathonTome2;
        this.lootrunTome = lootrunTome;
        this.expertiseTome1 = expertiseTome1;
        this.expertiseTome2 = expertiseTome2;
        this.mysticismTome1 = mysticismTome1;
        this.mysticismTome2 = mysticismTome2;

        this.aspect1 = aspect1;
        this.aspect2 = aspect2;
        this.aspect3 = aspect3;
        this.aspect4 = aspect4;
        this.aspect5 = aspect5;
    }

    public ItemBase getHelmet() {
        return helmet;
    }

    public ItemBase getChestplate() {
        return chestplate;
    }

    public ItemBase getLeggings() {
        return leggings;
    }

    public ItemBase getBoots() {
        return boots;
    }

    public ItemBase getWeapon() {
        return weapon;
    }

    public List<ItemBase> getTomes() {
        List<ItemBase> list = new ArrayList<>();

        if (armourTome1 != null) list.add(armourTome1);
        if (armourTome2 != null) list.add(armourTome2);
        if (armourTome3 != null) list.add(armourTome3);
        if (armourTome4 != null) list.add(armourTome4);
        if (guildTome != null) list.add(guildTome);

        if (weaponTome1 != null) list.add(weaponTome1);
        if (weaponTome2 != null) list.add(weaponTome2);
        if (marathonTome1 != null) list.add(marathonTome1);
        if (marathonTome2 != null) list.add(marathonTome2);
        if (lootrunTome != null) list.add(lootrunTome);

        if (expertiseTome1 != null) list.add(expertiseTome1);
        if (expertiseTome2 != null) list.add(expertiseTome2);
        if (mysticismTome1 != null) list.add(mysticismTome1);
        if (mysticismTome2 != null) list.add(mysticismTome2);

        return list;
    }

    public ItemBase getGuildTome() {
        return guildTome;
    }

    public List<ItemBase> getEquipmentsNoWeapon() {
        List<ItemBase> list = new ArrayList<>();

        if (helmet != null) list.add(helmet);
        if (chestplate != null) list.add(chestplate);
        if (leggings != null) list.add(leggings);
        if (boots != null) list.add(boots);
        if (ring1 != null) list.add(ring1);
        if (ring2 != null) list.add(ring2);
        if (bracelet != null) list.add(bracelet);
        if (necklace != null) list.add(necklace);

        return list;
    }

    public List<ItemBase> getEquipments() {
        List<ItemBase> list = new ArrayList<>();

        list.add(helmet);
        list.add(chestplate);
        list.add(leggings);
        list.add(boots);
        list.add(ring1);
        list.add(ring2);
        list.add(bracelet);
        list.add(necklace);
        list.add(weapon);

        return list;
    }

    public List<ItemBase> getAspects() {
        List<ItemBase> list = new ArrayList<>();

        if (aspect1 != null) list.add(aspect1);
        if (aspect2 != null) list.add(aspect2);
        if (aspect3 != null) list.add(aspect3);
        if (aspect4 != null) list.add(aspect4);
        if (aspect5 != null) list.add(aspect5);

        return list;
    }

    public List<MajorIdEnum> getMajorIDList() {
        List<MajorIdEnum> l = new ArrayList<>();
        if (!getEquipmentsNoWeapon().isEmpty()) {
            for (ItemBase item : getEquipmentsNoWeapon()) {
                JsonObject j = item.getJson();
                if (j.get("majorIds") != null) {
                    for (Map.Entry<String, JsonElement> entry : j.get("majorIds").getAsJsonObject().entrySet()) {
                        MajorIdEnum majorID = MajorIdEnum.GET_MAJOR_IDS.getOrDefault(entry.getKey(), MajorIdEnum.EMPTY);
                        if (majorID != MajorIdEnum.EMPTY && !l.contains(majorID)) l.add(majorID);
                    }
                }
            }
        }
        if (getWeapon() != null && getWeapon().getJson().get("majorIds") != null) {
            for (Map.Entry<String, JsonElement> entry : getWeapon().getJson().get("majorIds").getAsJsonObject().entrySet()) {
                MajorIdEnum majorID = MajorIdEnum.GET_MAJOR_IDS.getOrDefault(entry.getKey(), MajorIdEnum.EMPTY);
                if (majorID != MajorIdEnum.EMPTY && !l.contains(majorID)) l.add(majorID);
            }
        }
        return l;
    }

    public List<String> getMajorIDNameList() {
        List<String> l = new ArrayList<>();
        if (!getEquipmentsNoWeapon().isEmpty()) {
            for (ItemBase item : getEquipmentsNoWeapon()) {
                JsonObject j = item.getJson();
                if (j.get("majorIds") != null) {
                    for (Map.Entry<String, JsonElement> entry : j.get("majorIds").getAsJsonObject().entrySet()) {
                        if (!l.contains(entry.getKey())) l.add(entry.getKey());
                    }
                }
            }
        }
        if (getWeapon() != null && getWeapon().getJson().get("majorIds") != null) {
            for (Map.Entry<String, JsonElement> entry : getWeapon().getJson().get("majorIds").getAsJsonObject().entrySet()) {
                if (!l.contains(entry.getKey())) l.add(entry.getKey());
            }
        }
        return l;
    }
}
