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

    public ItemJsons(JsonObject helmet, JsonObject chestplate, JsonObject leggings, JsonObject boots, JsonObject ring1, JsonObject ring2, JsonObject bracelet, JsonObject necklace, JsonObject weapon) {
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boots;
        this.ring1 = ring1;
        this.ring2 = ring2;
        this.bracelet = bracelet;
        this.necklace = necklace;
        this.weapon = weapon;
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

    public JsonObject getRing1() {
        return ring1;
    }

    public JsonObject getRing2() {
        return ring2;
    }

    public JsonObject getBracelet() {
        return bracelet;
    }

    public JsonObject getNecklace() {
        return necklace;
    }

    public JsonObject getWeapon() {
        return weapon;
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
