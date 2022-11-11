package com.github.iTzhsnu.WynnSearcher.builder.skilltree;

import com.github.iTzhsnu.WynnSearcher.Identifications;

public class TreeEffect {
    private final Identifications needID;
    private final int needNum;
    private final Identifications giveID;
    private final int giveNum;
    private final int maxNum;
    private final Identifications freeGiveID;
    private final int freeGiveNum;
    private final Identifications damageID;
    private final String damageNum;

    public TreeEffect(Identifications needID, int needNum, Identifications giveID, int giveNum, int maxNum, Identifications freeGiveID, int freeGiveNum, Identifications damageID, String damageNum) {
        this.needID = needID;
        this.needNum = needNum;
        this.giveID = giveID;
        this.giveNum = giveNum;
        this.maxNum = maxNum;
        this.freeGiveID = freeGiveID;
        this.freeGiveNum = freeGiveNum;
        this.damageID = damageID;
        this.damageNum = damageNum;
    }

    public TreeEffect(Identifications freeGiveID, int freeGiveNum) {
        this(null, 0, null, 0, 0, freeGiveID, freeGiveNum, null, "0-0");
    }

    public TreeEffect(Identifications needID, int needNum, Identifications giveID, int giveNum, int maxNum) {
        this(needID, needNum, giveID, giveNum, maxNum, null, 0, null, "0-0");
    }

    public TreeEffect(Identifications damageID, String damageNum, Identifications freeGiveID, int freeGiveNum) {
        this(null, 0, null, 0, 0, freeGiveID, freeGiveNum, damageID, damageNum);
    }

    public Identifications getNeedID() {
        return needID;
    }

    public int getNeedNum() {
        return needNum;
    }

    public Identifications getGiveID() {
        return giveID;
    }

    public int getGiveNum() {
        return giveNum;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public Identifications getFreeGiveID() {
        return freeGiveID;
    }

    public int getFreeGiveNum() {
        return freeGiveNum;
    }

    public Identifications getDamageID() {
        return damageID;
    }

    public String getDamageNum() {
        return damageNum;
    }
}
