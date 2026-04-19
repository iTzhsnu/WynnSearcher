package com.github.iTzhsnu.WynnSearcher.data;

import com.github.iTzhsnu.WynnSearcher.Identifications;
import com.github.iTzhsnu.WynnSearcher.general.JsonKeys;
import com.google.gson.JsonObject;

public class Aspect extends ItemBase{
    public Aspect(JsonObject j) {
        super(j);
    }

    @Override
    public int getIdValue(Identifications id, JsonKeys sortType) {
        return 0;
    }

    @Override
    public boolean haveIdValue(Identifications id, JsonObject howToObtain, String min, String max) {
        return false;
    }

    @Override
    public String getIdString(Identifications id) {
        return getIdString(id, id.getItemName(), id.getItemFieldPos());
    }

    @Override
    public boolean haveFieldPos(Identifications id) {
        return haveFieldPos(id.getItemFieldPos());
    }
}
