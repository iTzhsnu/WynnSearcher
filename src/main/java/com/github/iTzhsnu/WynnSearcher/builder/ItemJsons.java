package com.github.iTzhsnu.WynnSearcher.builder;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

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
    private final JsonObject armourTome1;
    private final JsonObject armourTome2;
    private final JsonObject armourTome3;
    private final JsonObject armourTome4;
    private final JsonObject weaponTome1;
    private final JsonObject weaponTome2;
    private final JsonObject guildTome;

    public ItemJsons(JsonObject helmet, JsonObject chestplate, JsonObject leggings, JsonObject boots, JsonObject ring1, JsonObject ring2, JsonObject bracelet, JsonObject necklace, JsonObject weapon, JsonObject armourTome1, JsonObject armourTome2, JsonObject armourTome3, JsonObject armourTome4, JsonObject weaponTome1, JsonObject weaponTome2, JsonObject guildTome) {
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
        this.weaponTome1 = weaponTome1;
        this.weaponTome2 = weaponTome2;
        this.guildTome = guildTome;
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

    public List<JsonObject> getArmourTomes() {
        List<JsonObject> list = new ArrayList<>();

        if (armourTome1 != null) list.add(armourTome1);
        if (armourTome2 != null) list.add(armourTome2);
        if (armourTome3 != null) list.add(armourTome3);
        if (armourTome4 != null) list.add(armourTome4);

        return list;
    }

    public List<JsonObject> getWeaponTomes() {
        List<JsonObject> list = new ArrayList<>();

        if (weaponTome1 != null) list.add(weaponTome1);
        if (weaponTome2 != null) list.add(weaponTome2);

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
}
