package com.github.iTzhsnu.WynnSearcher.builder.skilltree;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class TreeCheckBox extends JCheckBox {
    private final String name;
    private final String[] description;
    private final int cost;
    private final int minArchetype;
    private final ArchetypeEnum archetype;
    private final TreeCheckBox[] req;
    private TreeCheckBox[] cantUse;
    private final TreeCheckBox[] previous;
    private final int x;
    private final int y;
    private final SkillEnum skill;
    private SpPrevious[] spPrevious;

    public TreeCheckBox(String name, String[] description, TreeCheckBox[] req, TreeCheckBox[] cantUse, TreeCheckBox[] previous, SpPrevious[] spPrevious, ArchetypeEnum archetype, int minArchetype, int cost, SkillEnum skill, int x, int y) {
        super();

        this.name = name;
        this.description = description;
        this.req = req;
        this.cantUse = cantUse;
        this.previous = previous;
        this.archetype = archetype;
        this.minArchetype = minArchetype;
        this.cost = cost;
        this.x = x;
        this.y = y;
        this.skill = skill;
        this.spPrevious = spPrevious;

        setHorizontalAlignment(CENTER);
        setOpaque(false);

        setToolTip();
    }

    public TreeCheckBox(String name, String[] description, TreeCheckBox[] previous, int cost, SkillEnum skill, int x, int y) {
        this(name, description, null, null, previous, null, null, 0, cost, skill, x, y);
    }

    public TreeCheckBox(String name, String[] description, TreeCheckBox[] previous, int cost, ArchetypeEnum archetype, int minArchetype, SkillEnum skill, int x, int y) {
        this(name, description, null, null, previous, null, archetype, minArchetype, cost, skill, x, y);
    }

    public TreeCheckBox(String name, String[] description, TreeCheckBox[] previous, TreeCheckBox[] cantUse, int cost, ArchetypeEnum archetype, int minArchetype, SkillEnum skill, int x, int y) {
        this(name, description, null, cantUse, previous, null, archetype, minArchetype, cost, skill, x, y);
    }

    public TreeCheckBox(String name, String[] description, TreeCheckBox[] previous, SpPrevious[] spPrevious, int cost, SkillEnum skill, int x, int y) {
        this(name, description, null, null, previous, spPrevious, null, 0, cost, skill, x, y);
    }

    public TreeCheckBox(String name, String[] description, TreeCheckBox[] previous, TreeCheckBox[] req, SpPrevious[] spPrevious, ArchetypeEnum archetype, int minArchetype, int cost, SkillEnum skill, int x, int y) {
        this(name, description, req, null, previous, spPrevious, archetype, minArchetype, cost, skill, x, y);
    }

    public TreeCheckBox(String name, String[] description, TreeCheckBox[] previous, TreeCheckBox[] req, ArchetypeEnum archetype, int minArchetype, int cost, SkillEnum skill, int x, int y) {
        this(name, description, req, null, previous, null, archetype, minArchetype, cost, skill, x, y);
    }

    public TreeCheckBox(String name, String[] description, TreeCheckBox[] previous, TreeCheckBox[] req, int cost, SkillEnum skill, int x, int y) {
        this(name, description, req, null, previous, null, null, 0, cost, skill, x, y);
    }

    public String getTreeName() {
        return name;
    }

    public int getCost() {
        return this.cost;
    }

    public int getMinArchetype() {
        return this.minArchetype;
    }

    public ArchetypeEnum getArchetype() {
        return this.archetype;
    }

    public SkillEnum getSkill() {
        return skill;
    }

    public void setCantUse(TreeCheckBox[] cantUse) {
        this.cantUse = cantUse;
        setToolTip();
    }

    public void setSpPrevious(SpPrevious[] spPrevious) {
        this.spPrevious = spPrevious;
    }

    public boolean canUse() {
        boolean reqB = true;
        boolean cantUseB = true;
        boolean previousB = false;
        boolean specialPreviousB = false;

        if (req != null) {
            for (TreeCheckBox b : req) {
                if (!b.isSelected()) reqB = false;
            }
        }
        if (cantUse != null) {
            for (TreeCheckBox b : cantUse) {
                if (b.isSelected()) cantUseB = false;
            }
        }
        if (previous != null) {
            for (TreeCheckBox b : previous) {
                if (b.isSelected()) previousB = true;
            }
        } else if (spPrevious == null) {
            previousB = true;
        }
        if (spPrevious != null && !previousB) {
            for (SpPrevious s : spPrevious) {
                boolean spPreviousB = true;
                for (TreeCheckBox b : s.getT()) {
                    if (!b.isSelected()) spPreviousB = false;
                }
                if (spPreviousB) {
                    specialPreviousB = true;
                    break;
                }
            }
        }

        if (!previousB && spPrevious != null) {
            return reqB && cantUseB && specialPreviousB;
        }
        return reqB && cantUseB && previousB;
    }

    private void setToolTip() {
        StringBuilder sb = new StringBuilder();
        String nameS = "<html>" + name + "<br>";
        sb.append(nameS);
        for (String s : description) {
            String ns = "<br>" + s;
            sb.append(ns);
        }
        if (req != null) {
            sb.append("<br><br>Requirement:");
            for (TreeCheckBox t : req) {
                String reqN = "<br>" + t.getTreeName();
                sb.append(reqN);
            }
        }
        if (cantUse != null) {
            sb.append("<br><br>Unlocking will block:");
            for (TreeCheckBox t : cantUse) {
                String cantUseN = "<br>" + t.getTreeName();
                sb.append(cantUseN);
            }
        }
        if (archetype != null) {
            String arcS = "<br><br>" + archetype.getName() + " Archetype";
            sb.append(arcS);
            if (minArchetype > 0) {
                String arcM = "<br>" + archetype.getName() + " Min: " + minArchetype;
                sb.append(arcM);
            }
        }
        String point = "<br><br>" + "Ability Point: " + cost;
        sb.append(point);

        setToolTipText(sb.toString());
    }

    public TreeCheckBox whiteIcon() {
        setIcon(getResizedIcon("/ability_buttons/white_off.png", 40, 40));
        setSelectedIcon(getResizedIcon("/ability_buttons/white_on.png", 40, 40));
        setBounds(x, y, 40, 40);
        return this;
    }

    public TreeCheckBox yellowIcon() {
        setIcon(getResizedIcon("/ability_buttons/yellow_off.png", 44, 44));
        setSelectedIcon(getResizedIcon("/ability_buttons/yellow_on.png", 44, 44));
        setBounds(x, y, 44, 44);
        return this;
    }

    public TreeCheckBox purpleIcon() {
        setIcon(getResizedIcon("/ability_buttons/purple_off.png", 48, 48));
        setSelectedIcon(getResizedIcon("/ability_buttons/purple_on.png", 48, 48));
        setBounds(x, y, 48, 48);
        return this;
    }

    public TreeCheckBox redIcon() {
        setIcon(getResizedIcon("/ability_buttons/red_off.png", 56, 56));
        setSelectedIcon(getResizedIcon("/ability_buttons/red_on.png", 56, 56));
        setBounds(x, y, 56, 56);
        return this;
    }

    public TreeCheckBox warriorIcon() {
        setIcon(getResizedIcon("/ability_buttons/warrior_off.png", 48, 48));
        setSelectedIcon(getResizedIcon("/ability_buttons/warrior_on.png", 48, 48));
        setBounds(x, y, 48, 48);
        return this;
    }

    public TreeCheckBox archerIcon() {
        setIcon(getResizedIcon("/ability_buttons/archer_off.png", 48, 48));
        setSelectedIcon(getResizedIcon("/ability_buttons/archer_on.png", 48, 48));
        setBounds(x, y, 48, 48);
        return this;
    }

    public TreeCheckBox assassinIcon() {
        setIcon(getResizedIcon("/ability_buttons/assassin_off.png", 48, 48));
        setSelectedIcon(getResizedIcon("/ability_buttons/assassin_on.png", 48, 48));
        setBounds(x, y, 48, 48);
        return this;
    }

    public TreeCheckBox mageIcon() {
        setIcon(getResizedIcon("/ability_buttons/mage_off.png", 48, 48));
        setSelectedIcon(getResizedIcon("/ability_buttons/mage_on.png", 48, 48));
        setBounds(x, y, 48, 48);
        return this;
    }

    public TreeCheckBox shamanIcon() {
        setIcon(getResizedIcon("/ability_buttons/shaman_off.png", 48, 48));
        setSelectedIcon(getResizedIcon("/ability_buttons/shaman_on.png", 48, 48));
        setBounds(x, y, 48, 48);
        return this;
    }

    public ImageIcon getResizedIcon(String s, int width, int height) {
        return new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(s))).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }

    public enum ArchetypeEnum {
        FALLEN("Fallen", 1),
        BATTLE_MONK("Battle Monk", 2),
        PALADIN("Paladin", 3),

        SHADESTEPPER("Shadestepper", 1),
        TRICKSTER("Trickster", 2),
        ACROBAT("Acrobat", 3),

        BOLTSLINGER("Boltslinger", 1),
        SHARPSHOOTER("Sharpshooter", 2),
        TRAPPER("Trapper", 3),

        RIFTWALKER("Riftwalker", 1),
        LIGHT_BENDER("Light Bender", 2),
        ARCANIST("Arcanist", 3),

        SUMMONER("Summoner", 1),
        RITUALIST("Ritualist", 2),
        ACOLYTE("Acolyte", 3)
        ;

        private final int num;
        private final String name;

        ArchetypeEnum(String name, int num) {
            this.num = num;
            this.name = name;
        }

        public int getNum() {
            return num;
        }

        public String getName() {
            return name;
        }
    }
}
