package com.github.iTzhsnu.WynnSearcher.builder.skilltree;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class TreeCheckBox extends JCheckBox {
    private final int cost;
    private final int minArchetype;
    private final ArchetypeEnum archetype;
    private final TreeCheckBox[] req;
    private final TreeCheckBox[] cantUse;
    private final TreeCheckBox[] above;
    private final int x;
    private final int y;
    private final SkillEnum skill;

    public TreeCheckBox(String name, String[] description, TreeCheckBox[] req, TreeCheckBox[] cantUse, TreeCheckBox[] above, ArchetypeEnum archetype, int minArchetype, int cost, SkillEnum skill, int x, int y) {
        super();

        this.req = req;
        this.cantUse = cantUse;
        this.above = above;
        this.archetype = archetype;
        this.minArchetype = minArchetype;
        this.cost = cost;
        this.x = x;
        this.y = y;
        this.skill = skill;

        StringBuilder sb = new StringBuilder();
        String nameS = "<html>" + name + "<br>";
        sb.append(nameS);
        for (String s : description) {
            String ns = "<br>" + s;
            sb.append(ns);
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

    public TreeCheckBox(String name, String[] description, TreeCheckBox[] above, int cost, SkillEnum skill, int x, int y) {
        this(name, description, null, null, above, null, 0, cost, skill, x, y);
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

    public boolean canUse() {
        boolean reqB = true;
        boolean cantUseB = true;
        boolean aboveB = false;

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
        if (above != null) {
            for (TreeCheckBox b : above) {
                if (b.isSelected()) aboveB = true;
            }
        } else {
            aboveB = true;
        }

        return reqB && cantUseB && aboveB;
    }

    public TreeCheckBox whiteIcon() {
        setIcon(getResizedIcon("/white_off.png", 40, 40));
        setSelectedIcon(getResizedIcon("/white_on.png", 40, 40));
        setBounds(x, y, 44, 40);
        return this;
    }

    public TreeCheckBox yellowIcon() {
        setIcon(getResizedIcon("/yellow_off.png", 44, 44));
        setSelectedIcon(getResizedIcon("/yellow_on.png", 44, 44));
        setBounds(x, y, 44, 44);
        return this;
    }

    public TreeCheckBox purpleIcon() {
        setIcon(getResizedIcon("/purple_off.png", 48, 48));
        setSelectedIcon(getResizedIcon("/purple_on.png", 48, 48));
        setBounds(x, y, 52, 48);
        return this;
    }

    public TreeCheckBox redIcon() {
        setIcon(getResizedIcon("/red_off.png", 56, 56));
        setSelectedIcon(getResizedIcon("/red_on.png", 56, 56));
        setBounds(x, y, 56, 56);
        return this;
    }

    public TreeCheckBox warriorIcon() {
        setIcon(getResizedIcon("/warrior_off.png", 48, 48));
        setSelectedIcon(getResizedIcon("/warrior_on.png", 48, 48));
        setBounds(x, y, 52, 48);
        return this;
    }

    public TreeCheckBox archerIcon() {
        setIcon(getResizedIcon("/archer_off.png", 48, 48));
        setSelectedIcon(getResizedIcon("/archer_on.png", 48, 48));
        setBounds(x, y, 52, 48);
        return this;
    }

    public TreeCheckBox assassinIcon() {
        setIcon(getResizedIcon("/assassin_off.png", 48, 48));
        setSelectedIcon(getResizedIcon("/assassin_on.png", 48, 48));
        setBounds(x, y, 52, 48);
        return this;
    }

    public TreeCheckBox mageIcon() {
        setIcon(getResizedIcon("/mage_off.png", 48, 48));
        setSelectedIcon(getResizedIcon("/mage_on.png", 48, 48));
        setBounds(x, y, 52, 48);
        return this;
    }

    public TreeCheckBox shamanIcon() {
        setIcon(getResizedIcon("/shaman_off.png", 48, 48));
        setSelectedIcon(getResizedIcon("/shaman_on.png", 48, 48));
        setBounds(x, y, 52, 48);
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
