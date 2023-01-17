package com.github.iTzhsnu.WynnSearcher.builder.skilltree;

public enum SpellEnum {
      //Warrior
    //Bash
    BASH(130, 20, 0, 0, 0, 0, 1), //BASE
    DOUBLE_BASH(-50, 0, 0, 0, 0, 0, 1), //ADD (Bash)
    QUADRUPLE_BASH(-20, 0, 0, 0, 0, 0, 2), //ADD (Bash)
    STRONGER_BASH(30, 0, 0, 0, 0, 0, 0), //ADD (Bash)
    THUNDERCLAP(0, 0, 25, 0, 0, 0, 0), //ADD (Bash)

    //Charge
    HEAVY_IMPACT(100, 0, 0, 0, 0, 0, 1), //BASE (Charge)
    FLYBY_JAB(20, 0, 0, 0, 0, 40, 1), //BASE (Charge)
    FLYING_KICK(150, 0, 0, 20, 0, 30, 1), //BASE (Charge)
    COLLIDE(150, 0, 0, 0, 50, 0, 1), //BASE (Flying Kick)

    //Uppercut
    UPPERCUT(200, 40, 40, 0, 0, 0, 1), //BASE
    FIREWORKS(80, 0, 20, 0, 0, 0, 1), //BASE (Uppercut)
    HALF_MOON_SWIPE(-70, 0, 0, 30, 0, 0, 0), //ADD (Uppercut)
    COMET(80, 0, 0, 0, 20, 0, 1), //BASE (Fireworks)
    WHIRLWIND_STRIKE(-20, 0, 0, 0, 0, 30, 0), //ADD (Uppercut)
    AXE_KICK(150, 0, 0, 0, 0, 0, 0), //ADD (Uppercut)

    //War Scream
    WAR_SCREAM(50, 0, 0, 0, 50, 0, 1), //BASE
    IRON_LUNGS_FOR_WAR_SCREAM(30, 0, 0, 0, 0, 30, 0), //ADD (War Scream)
    IRON_LUNGS_FOR_AIR_SHOUT(15, 0, 0, 0, 0, 15, 0), //ADD (Air Shout)
    AIR_SHOUT(40, 0, 0, 0, 0, 10, 1), //BASE (War Scream)
    TEMPEST(30, 10, 0, 0, 0, 10, 0), //BASE (War Scream)

    //OTHER
    FLAMING_UPPERCUT(0, 0, 0, 0, 50, 0, 1), //BASE (Uppercut)
    COUNTER(60, 0, 20, 0, 0, 20, 1), //BASE
    BOILING_BLOOD(25, 0, 0, 0, 25, 0, 1), //BASE (Bash)
    SPARKING_HOPE(40, 0, 20, 0, 0, 0, 1), //BASE
    CYCLONE(10, 0, 0, 0, 5, 10, 1), //BASE (War Scream)
    SHIELD_STRIKE(70, 0, 30, 0, 0, 0, 1), //BASE (Mantle)

      //Assassin
    //Spin Attack
    //Dash
    //Multihit
    //Smoke Bomb

      //Archer
    //Arrow Storm
    //Escape
    //Arrow Bomb
    //Arrow Shield

      //Mage
    //Heal
    //Teleport
    //Heal
    //Ice Snake

      //Shaman
    //Totem
    //Haul
    //Aura
    //Uproot
    ;

    private final int neutral;
    private final int earth;
    private final int thunder;
    private final int water;
    private final int fire;
    private final int air;
    private final int addNum;

    SpellEnum(int neutral, int earth, int thunder, int water, int fire, int air, int addNum) {
        this.neutral = neutral;
        this.earth = earth;
        this.thunder = thunder;
        this.water = water;
        this.fire = fire;
        this.air = air;
        this.addNum = addNum;
    }

    public float getNeutral() {
        return neutral / 100F;
    }

    public float getEarth() {
        return earth / 100F;
    }

    public float getThunder() {
        return thunder / 100F;
    }

    public float getWater() {
        return water / 100F;
    }

    public float getFire() {
        return fire / 100F;
    }

    public float getAir() {
        return air / 100F;
    }

    public int getAddNum() {
        return addNum;
    }
}
