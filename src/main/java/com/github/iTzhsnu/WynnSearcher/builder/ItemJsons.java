package com.github.iTzhsnu.WynnSearcher.builder;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemJsons {
    private final JsonObject helmet;
    private final JsonObject chestplate;
    private final JsonObject leggings;
    private final JsonObject boots;
    private final JsonObject ring1;
    private final JsonObject ring2;
    private final JsonObject bracelet;
    private final JsonObject necklace;
    private final JsonObject weapon;

    //Tomes
    private final JsonObject armourTome1;
    private final JsonObject armourTome2;
    private final JsonObject armourTome3;
    private final JsonObject armourTome4;
    private final JsonObject guildTome;
    private final JsonObject weaponTome1;
    private final JsonObject weaponTome2;
    private final JsonObject marathonTome1;
    private final JsonObject marathonTome2;
    private final JsonObject lootrunTome;
    private final JsonObject expertiseTome1;
    private final JsonObject expertiseTome2;
    private final JsonObject mysticismTome1;
    private final JsonObject mysticismTome2;

    //Aspects
    private final JsonObject aspect1;
    private final JsonObject aspect2;
    private final JsonObject aspect3;
    private final JsonObject aspect4;
    private final JsonObject aspect5;

    public ItemJsons(JsonObject helmet, JsonObject chestplate, JsonObject leggings, JsonObject boots, JsonObject ring1, JsonObject ring2, JsonObject bracelet, JsonObject necklace, JsonObject weapon
            , JsonObject armourTome1, JsonObject armourTome2, JsonObject armourTome3, JsonObject armourTome4, JsonObject guildTome
            , JsonObject weaponTome1, JsonObject weaponTome2, JsonObject marathonTome1, JsonObject marathonTome2, JsonObject lootrunTome
            , JsonObject expertiseTome1, JsonObject expertiseTome2, JsonObject mysticismTome1, JsonObject mysticismTome2
            , JsonObject aspect1, JsonObject aspect2, JsonObject aspect3, JsonObject aspect4, JsonObject aspect5) {
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

    public JsonObject getHelmet() {
        return helmet;
    }

    public JsonObject getChestplate() {
        return chestplate;
    }

    public JsonObject getLeggings() {
        return leggings;
    }

    public JsonObject getBoots() {
        return boots;
    }

    public JsonObject getWeapon() {
        return weapon;
    }

    public List<JsonObject> getTomes() {
        List<JsonObject> list = new ArrayList<>();

        if (armourTome1 != null) list.add(armourTome1);
        if (armourTome2 != null) list.add(armourTome2);
        if (armourTome3 != null) list.add(armourTome3);
        if (armourTome4 != null) list.add(armourTome4);
        if (armourTome1 != null) list.add(guildTome);

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

    public JsonObject getGuildTome() {
        return guildTome;
    }

    public List<JsonObject> getJsonObjectList() {
        List<JsonObject> list = new ArrayList<>();

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

    public List<JsonObject> getEquipments() {
        List<JsonObject> list = new ArrayList<>();

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

    public List<JsonObject> getAspects() {
        List<JsonObject> list = new ArrayList<>();

        if (aspect1 != null) list.add(aspect1);
        if (aspect2 != null) list.add(aspect2);
        if (aspect3 != null) list.add(aspect3);
        if (aspect4 != null) list.add(aspect4);
        if (aspect5 != null) list.add(aspect5);

        return list;
    }

    public List<MajorIDEnum> getMajorIDList() {
        List<MajorIDEnum> l = new ArrayList<>();
        if (getJsonObjectList().size() > 0) {
            for (JsonObject j : getJsonObjectList()) {
                if (j.get("majorIds") != null) {
                    for (Map.Entry<String, JsonElement> entry : j.get("majorIds").getAsJsonObject().entrySet()) {
                        MajorIDEnum majorID = MajorIDEnum.GET_MAJOR_IDS.getOrDefault(entry.getKey(), MajorIDEnum.EMPTY);
                        if (majorID != MajorIDEnum.EMPTY && !l.contains(majorID)) l.add(majorID);
                    }
                }
            }
        }
        if (getWeapon() != null && getWeapon().get("majorIds") != null) {
            for (Map.Entry<String, JsonElement> entry : getWeapon().get("majorIds").getAsJsonObject().entrySet()) {
                MajorIDEnum majorID = MajorIDEnum.GET_MAJOR_IDS.getOrDefault(entry.getKey(), MajorIDEnum.EMPTY);
                if (majorID != MajorIDEnum.EMPTY && !l.contains(majorID)) l.add(majorID);
            }
        }
        return l;
    }

    public List<String> getMajorIDNameList() {
        List<String> l = new ArrayList<>();
        if (getJsonObjectList().size() > 0) {
            for (JsonObject j : getJsonObjectList()) {
                if (j.get("majorIds") != null) {
                    for (Map.Entry<String, JsonElement> entry : j.get("majorIds").getAsJsonObject().entrySet()) {
                        if (!l.contains(entry.getKey())) l.add(entry.getKey());
                    }
                }
            }
        }
        if (getWeapon() != null && getWeapon().get("majorIds") != null) {
            if (getWeapon().get("majorIds") != null) {
                for (Map.Entry<String, JsonElement> entry : getWeapon().get("majorIds").getAsJsonObject().entrySet()) {
                    if (!l.contains(entry.getKey())) l.add(entry.getKey());
                }
            }
        }
        return l;
    }
}
