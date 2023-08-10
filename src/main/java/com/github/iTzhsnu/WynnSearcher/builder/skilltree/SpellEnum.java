package com.github.iTzhsnu.WynnSearcher.builder.skilltree;

public enum SpellEnum {
      //Any
    //Melee
    MELEE(100, 0, 0, 0, 0, 0, 1), //BASE

    //Powder Special
    EARTH_1(0, 240, 0, 0, 0, 0, 1), //BASE
    EARTH_2(0, 280, 0, 0, 0, 0, 1), //BASE
    EARTH_3(0, 320, 0, 0, 0, 0, 1), //BASE
    EARTH_4(0, 360, 0, 0, 0, 0, 1), //BASE
    EARTH_5(0, 400, 0, 0, 0, 0, 1), //BASE

    THUNDER_1(0, 0, 200, 0, 0, 0, 1), //BASE
    THUNDER_2(0, 0, 225, 0, 0, 0, 1), //BASE
    THUNDER_3(0, 0, 250, 0, 0, 0, 1), //BASE
    THUNDER_4(0, 0, 275, 0, 0, 0, 1), //BASE
    THUNDER_5(0, 0, 300, 0, 0, 0, 1), //BASE

    FIRE_1(0, 0, 0, 0, 60, 0, 1), //BASE
    FIRE_2(0, 0, 0, 0, 70, 0, 1), //BASE
    FIRE_3(0, 0, 0, 0, 80, 0, 1), //BASE
    FIRE_4(0, 0, 0, 0, 90, 0, 1), //BASE
    FIRE_5(0, 0, 0, 0, 100, 0, 1), //BASE

      //Warrior
    //Bash
    BASH(150, 30, 0, 0, 0, 0, 1), //BASE
    DOUBLE_BASH(-40, 0, 0, 0, 0, 0, 1), //ADD (Bash)
    QUADRUPLE_BASH(-20, 0, 0, 0, 0, 0, 2), //ADD (Bash)
    STRONGER_BASH(30, 0, 0, 0, 0, 0, 0), //ADD (Bash)
    THUNDERCLAP(0, 0, 25, 0, 0, 0, 0), //ADD (Bash)
    DIVINE_HONOR(-15, 0, 0, 0, 0, 0, 0), //ADD (Bash, Major ID)
    OVERWHELM(0, 0, 0, 0, 0, 0, 2), //ADD (Bash, Major ID)

    //Charge
    HEAVY_IMPACT(120, 0, 0, 0, 0, 0, 1), //BASE (Charge)
    FLYBY_JAB(100, 0, 0, 0, 0, 20, 1), //BASE (Charge)
    FLYING_KICK(150, 0, 0, 20, 0, 30, 1), //BASE (Charge)
    COLLIDE(150, 0, 0, 0, 50, 0, 1), //BASE (Flying Kick)

    //Uppercut
    UPPERCUT(200, 40, 40, 0, 0, 0, 1), //BASE
    HALF_MOON_SWIPE(-70, 0, 0, 30, 0, 0, 0), //ADD (Uppercut)
    WHIRLWIND_STRIKE(-20, 0, 0, 0, 0, 30, 0), //ADD (Uppercut)
    AXE_KICK(150, 0, 0, 0, 0, 0, 0), //ADD (Uppercut)
    FIREWORKS(80, 0, 20, 0, 0, 0, 1), //BASE (Uppercut)
    COMET(80, 20, 0, 0, 0, 0, 1), //BASE (Fireworks)

    //War Scream
    WAR_SCREAM(75, 0, 0, 0, 25, 0, 1), //BASE
    IRON_LUNGS_FOR_WAR_SCREAM(30, 0, 0, 0, 0, 30, 0), //ADD (War Scream)
    IRON_LUNGS_FOR_AIR_SHOUT(15, 0, 0, 0, 0, 15, 0), //ADD (Air Shout)
    AIR_SHOUT(40, 0, 0, 0, 0, 10, 1), //BASE (War Scream)
    TEMPEST(30, 10, 0, 0, 0, 10, 3), //BASE (War Scream)
    RECKLESS_ABANDON(0, 0, 0, 0, 15, 0, 0), //ADD (Tempest, Major ID)

    //OTHER
    FLAMING_UPPERCUT(0, 0, 0, 0, 50, 0, 5), //BASE (Uppercut) (per 0.6s)
    COUNTER(60, 0, 20, 0, 0, 20, 1), //BASE
    BOILING_BLOOD(25, 0, 0, 0, 5, 0, 7), //BASE (Bash) (per 0.4s)
    SPARKLING_HOPE(40, 0, 20, 0, 0, 0, 1), //BASE
    CYCLONE(10, 0, 0, 0, 5, 10, 40), //BASE (War Scream) (per 0.5s)
    SHIELD_STRIKE(70, 0, 30, 0, 0, 0, 1), //BASE (Mantle)
    SACRED_SURGE(100, 0, 20, 0, 0, 0, 0), //ADD (Bash, Uppercut)


      //Assassin
    //Melee
    DOUBLE_SLICE(-40, 0, 0, 0, 0, 0, 1), //ADD (Melee)

    //Spin Attack
    SPIN_ATTACK(120, 0, 30, 0, 0, 0, 1), //BASE
    DOUBLE_SPIN(-40, 0, 0, 0, 0, 0, 1), //ADD (Spin Attack)
    BLAZING_POWDER(0, 0, 0, 0, 20, 0, 0), //ADD (Spin Attack, Lacerate)
    FATAL_SPIN(20, 0, 0, 0, 0, 0, 0), //ADD (Spin Attack)

    LACERATE(60, 0, 0, 10, 0, 20, 3), //BASE CHANGE (Spin Attack)
    STRONGER_LACERATE(0, 0, 0, 0, 0, 0, 1), //ADD (Lacerate)

    //Dash
    DANCING_BLADE(70, 0, 0, 0, 0, 10, 1), //BASE (Dash)
    PIROUETTE(30, 0, 0, 0, 0, 10, 0), //ADD (Dancing Blade)

    //Multihit
    MULTIHIT(30, 0, 0, 10, 0, 0, 8), //BASE
    STRONGER_MULTIHIT(0, 0, 0, 0, 0, 0, 3), //ADD (Multihit)
    BLADE_FURY(0, 0, 15, 0, 0, 0, 0), //ADD (Multihit)
    FATALITY(100, 0, 0, 0, 0, 50, 1), //BASE (Multihit)
    JUGGLE(-10, 0, 0, 0, 0, 0, 12), //ADD (Multihit, Major ID)

    BACKSTAB(200, 50, 0, 0, 0, 0, 1), //BASE CHANGE (Multihit)

    //Smoke Bomb
    SMOKE_BOMB(25, 5, 0, 0, 0, 5, 10), //BASE (0.5s per)
    STICKY_BOMB(0, 0, 0, 0, 10, 0, 0), //ADD (Smoke Bomb)
    WALL_OF_SMOKE(-10, 0, 0, 0, 0, 0, 20), //ADD (Smoke Bomb)
    CHERRY_BOMBS(90, 0, 0, 0, 0, 0, 0), //ADD (Smoke Bomb, Major ID)

    //Other
    BAMBOOZLE(360, 0, 0, 0, 40, 0, 1), //BASE (Multihit)
    SHURIKENS(100, 0, 0, 0, 20, 0, 3), //BASE (Melee)
    JASMINE_BLOOM(60, 5, 0, 15, 0, 0, 1), //BASE (per 0.3s)


      //Archer
    //Melee
    DOUBLE_SHOTS(-30, 0, 0, 0, 0, 0, 1), //ADD (Melee)
    TRIPLE_SHOTS(-20, 0, 0, 0, 0, 0, 1), //ADD (Melee)

    //Arrow Storm
    ARROW_STORM(30, 0, 10, 0, 0, 0, 8), //BASE
    NIMBLE_STRING(-5, 0, -5, 0, 0, 0, 6), //ADD (Arrow Storm)
    WINDSTORM(-9, 0, -3, 0, 0, 2, 2), //ADD (Arrow Storm) (2 Stream)
    ARROW_HURRICANE(-3, 0, -1, 0, 0, -1, 0), //ADD (Arrow Storm) (3 Stream)

    PHANTOM_RAY(25, 0, 0, 5, 0, 0, 12), //BASE CHANGE (Arrow Storm)

    //Escape
    FIERCE_STOMP(120, 0, 0, 0, 0, 0, 1), //BASE (Escape)
    GEYSER_STOMP(50, 0, 0, 30, 0, 0, 0), //ADD (Escape)
    ESCAPE_ARTIST(100, 0, 50, 0, 0, 0, 1), //BASE (Escape)

    //Arrow Bomb
    ARROW_BOMB(160, 0, 0, 0, 20, 0, 1), //BASE
    REFINED_GUNPOWDER(50, 0, 0, 0, 0, 0, 0), //ADD (Arrow Bomb)
    SHOCKING_BOMB(20, 0, 20, 0, 0, 0, 0), //ADD (Arrow Bomb)
    GRAPE_BOMB(60, 0, 0, 0, 20, 0, 2), //BASE (Arrow Bomb)
    HEART_SHATTER(100, 0, 0, 0, 0, 0, 1), //BASE (Arrow Bomb)
    IMPLOSION(40, 0, 0, 0, 0, 0, 0), //ADD (Heart Shatter)
    ARROW_BOMB_SELF_DAMAGE(10, 0, 0, 0, 0, 0, 1), //BASE (Arrow Bomb)
    ROCKET_JUMP(-9, 0, 0, 0, 0, 0, 0), //BASE (Arrow Bomb Self Damage)
    SHRAPNEL_BOMB(40, 0, 0, 0, 20, 0, 15), //BASE (Arrow Bomb)
    FORESTS_BLESSING(-30, 0, 0, 0, 0, 0, 0), //ADD (Arrow Bomb, Major ID)

    //Arrow Shield
    ARROW_SHIELD(90, 0, 0, 0, 0, 10, 2), //BASE
    MORE_SHIELDS_ARROW_SHIELD(-13, 0, 0, 0, 0, -5, 2), //ADD (Arrow Shield)
    BETTER_ARROW_SHIELD_ARROW_SHIELD(40, 0, 0, 0, 0, 0, 0), //ADD (Arrow Shield)

    GUARDIAN_ANGELS(30, 0, 0, 0, 0, 10, 2), //BASE CHANGE (Arrow Shield) (8 times, per 0.6s)
    MORE_SHIELDS_GUARDIAN_ANGELS(-18, 0, 0, 0, 0, 0, 2), //ADD (Guardian Angels)
    BETTER_ARROW_SHIELD_GUARDIAN_ANGELS(3, 0, 0, 0, 0, 0, 0), //ADD (Guardian Angels)
    //Better Guardian Angels ADD (Guardian Angels) (+4 times)
    ALL_SEEING_PANOPTES(2, 0, 0, 0, 5, 0, 0), //ADD (Guardian Angels) (+4 times, change to per 0.5s)

    //Other
    ARROW_RAIN(150, 0, 0, 0, 0, 100, 1), //BASE (Arrow Shield)
    FIRE_CREEP(30, 0, 0, 0, 20, 0, 15), //BASE (Arrow Bomb) (per 0.4s)
    SCORCHED_EARTH(10, 0, 0, 0, 5, 0, 5), //ADD (Fire Creep)
    BRYOPHYTE_ROOTS(40, 20, 0, 0, 0, 0, 12), //BASE (Arrow Bomb) (per 0.4s)
    BASALTIC_TRAP(210, 20, 0, 0, 20, 0, 1), //BASE (Arrow Bomb)
    MINEFIELD(-50, 0, 0, 0, 0, 0, 0), //ADD (Basaltic Trap)
    TWAINS_ARC(220, 0, 0, 0, 0, 0, 1), //BASE (Melee) (Melee Damage)
    PHASING_BEAM(-110, 0, 0, 0, 0, 0, 0), //ADD (Twain's Arc)
    CALL_OF_THE_HOUND(40, 0, 0, 0, 0, 0, 1), //BASE (Arrow Shield)
    BEAST_LORE(40, 0, 0, 0, 0, 0, 0), //ADD (Call of the Hound)
    CREPUSCULAR_RAY(30, 0, 0, 10, 0, 0, 1), //BASE (Arrow Storm) (20 arrow per focus) (20 arrow per sec)
    TANGLED_TRAPS(20, 0, 0, 0, 0, 20, 5), //BASE (Basaltic Trap) (per 0.2s)
    IVYROOT_MAMBA(40, 15, 0, 0, 0, 0, 1), //BASE (Bryophyte Roots)
    MURDER_FLOCK(60, 0, 0, 0, 0, 0, 1), //BASE (Basaltic Trap)


      //Mage
    //Heal
    SUNSHOWER(70, 0, 0, 30, 0, 0, 1), //BASE (Heal)
    STRONGER_SUNSHOWER(50, 0, 0, 20, 10, 0, 0), //ADD (Sunshower)

    //Teleport
    WIND_SLASH(75, 0, 0, 0, 0, 25, 1), //BASE (Teleport)
    EXPLOSIVE_ENTRANCE(60, 0, 0, 0, 20, 0, 1), //BASE (Teleport)
    TRANSONIC_WARP(10, 0, 3, 0, 0, 3, 1), //BASE (Teleport) (per Winded)

    //Meteor
    METEOR(330, 70, 0, 0, 0, 0, 1), //BASE
    STRONGER_METEOR(90, 30, 0, 0, 0, 0, 0), //ADD (Meteor)
    BREATHLESS_METEOR(25, 5, 0, 0, 0, 0, 0), //ADD (Meteor) (per Winded)
    THUNDERSTORM(30, 0, 15, 0, 0, 0, 3), //BASE (Meteor)
    BREATHLESS_THUNDERSTORM(8, 2, 0, 0, 0, 0, 1), //ADD (Thunderstorm) (per Winded)

    OPHANIM(90, 0, 15, 15, 0, 0, 1), //BASE CHANGE (Meteor)
    BREATHLESS_OPHANIM(3, 1, 0, 0, 0, 0, 1), //ADD (Ophanim) (per Winded)
    STRONGER_OPHANIM(20, 0, 0, 0, 0, 10, 0), //ADD (Ophanim)
    DIVINATION(-20, 0, -5, -5, 0, 0, 0), //ADD (Ophanim)

    //Ice Snake
    ICE_SNAKE(70, 0, 0, 30, 0, 0, 1), //BASE
    WINDSWEEPER(10, 0, 0, 5, 0, 0, 1), //ADD (Ice Snake) (per Winded)
    SENTIENT_SNAKE(20, 0, 0, 10, 0, 0, 0), //ADD (Ice Snake)
    SNAKE_NEST(35, 0, 0, 15, 0, 0, 2), //ADD (Ice Snake)
    GUST(20, 0, 0, 0, 0, 20, 0), //ADD (Ice Snake)

    //Other
    BURNING_SIGIL(25, 0, 0, 0, 15, 0, 12), //BASE (Meteor) (per 0.4s)
    PYROKINESIS(150, 0, 0, 0, 0, 0, 1), //BASE (Melee) (Melee Damage)
    LIGHTWEAVER(150, 0, 0, 0, 50, 0, 1), //BASE


      //Shaman
    //Melee
    SHAMAN_MELEE(33, 0, 0, 0, 0, 0, 3), //BASE (Melee)
    HAND_OF_THE_SHAMAN(0, 0, 0, 0, 0, 0, 2), //ADD (Shaman Melee)

    //Totem
    TOTEM(6, 0, 0, 0, 0, 6, 1), //BASE (per 0.4s)
    STRONGER_TOTEM(4, 0, 0, 0, 0, 0, 0), //ADD (Totem)

    //Haul
    NATURES_JOLT(90, 30, 0, 0, 0, 0, 1), //BASE (Haul)
    FROG_DANCE(90, 0, 0, 30, 0, 0, 1), //BASE (Haul, Mask of the Coward)

    //Aura
    AURA(150, 0, 0, 30, 0, 0, 1), //BASE
    SHOCKING_AURA(0, 0, 20, 0, 0, 0, 0), //ADD (Aura)
    STORM_DANCE(0, 0, 0, 0, 0, 30, 0), //ADD (Aura)
    //Rebound

    //Uproot
    UPROOT(80, 30, 20, 0, 0, 0, 1), //BASE
    FLAMING_TONGUE(-15, -30, -15, 0, 10, 0, 2), //ADD (Uproot)

    //Other
    RAIN_DANCE(30, 0, 0, 30, 0, 0, 1), //BASE (Totem) (per 0.4s)
    PUPPET_MASTER(16, 2, 0, 0, 0, 2, 2), //BASE (per 0.5s) (Max 3 Puppets)
    //More Puppets 1 (Max +1 Puppets)
    //More Puppets 2 (Max +2 Puppets)
    EXPLODING_PUPPETS(150, 0, 0, 0, 50, 0, 1), //BASE (Puppets)
    TWISTED_TETHER(25, 0, 0, 0, 0, 10, 1), //BASE
    STRONGER_TETHER(10, 0, 0, 0, 0, 0, 0), //ADD (Twisted Tether)
    CRIMSON_EFFIGY(75, 0, 0, 25, 0, 0, 1), //BASE
    MORE_EFFIGIES(-10, 0, 0, -10, 0, 0, 1), //ADD (Crimson Effigy)
    CHANT_OF_THE_COWARD(200, 0, 0, 0, 0, 0, 1), //BASE
    BLOOD_SORROW(100, 0, 0, 20, 0, 0, 20), //BASE (Uproot) (per 0.2s)
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
