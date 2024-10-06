package com.github.iTzhsnu.WynnSearcher.builder;

import com.github.iTzhsnu.WynnSearcher.Identifications;

import javax.swing.*;
import java.awt.*;

public class Damage_Template {
    private final JPanel p = new JPanel();
    private int y_size = 25;
    private int y = 10;
    private final JLabel avg_total = new JLabel("Total Average: 0");
    private final SkillPoint sp;
    private final boolean crit_boost;
    private final JPanel pane;

    public Damage_Template(String name, int mana_Cost, JPanel pane, Damage_Template previous, SkillPoint sp, boolean crit_boost, boolean needTotal) {
        this.sp  = sp;
        this.crit_boost = crit_boost;
        this.pane = pane;
        if (previous != null) {
            p.setBounds(10, previous.y + previous.y_size + 10, 280, 30);
            y = previous.y + previous.y_size + 10;
        } else {
            p.setBounds(10, 10, 280, 30);
        }
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        JLabel nameL = new JLabel();
        if (mana_Cost > 0) {
            nameL.setText(name + " (" + mana_Cost + ")");
        } else {
            nameL.setText(name);
        }
        nameL.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        nameL.setAlignmentX(Component.CENTER_ALIGNMENT);
        avg_total.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(nameL);
        if (needTotal) { //Blank
            p.add(new JLabel(" ")); // +20
            y_size += 20;
        }
        if (needTotal) { //Avg Total Damage
            p.add(avg_total); // +16
            y_size += 16;
        }
        if (y + y_size + 10 > 397) {
            pane.setPreferredSize(new Dimension(300, y + y_size + 10));
        } else {
            pane.setPreferredSize(new Dimension(300, 397));
        }

        p.setBackground(new Color(200, 200, 200));
        pane.add(p);
    }

    public void addHeal(String name, int[] id_Numbers, float healPercent, boolean isCanBoost, boolean useGentleGlow) {
        float heal = (id_Numbers[ID_Display.ID_INT.get(Identifications.HEALTH)] + id_Numbers[ID_Display.ID_INT.get(Identifications.HEALTH_BONUS)]) * healPercent * (1F + (id_Numbers[ID_Display.ID_INT.get(Identifications.HEALING_EFFICIENCY)] / 100F));
        if (isCanBoost) {
            if (useGentleGlow) {
                heal *= 1F + (Math.min(id_Numbers[ID_Display.ID_INT.get(Identifications.WATER_DAMAGE_PERCENT)] * 0.75F, 100) / 100F); //TODO: NEED CHECK
            } else {
                heal *= 1F + (Math.min(id_Numbers[ID_Display.ID_INT.get(Identifications.WATER_DAMAGE_PERCENT)] * 0.3F, 75) / 100F);
            }
        }
        JLabel n = new JLabel(name);
        JLabel h = new JLabel(String.valueOf((int) Math.floor(heal)));
        n.setAlignmentX(Component.CENTER_ALIGNMENT);
        h.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(new JLabel(" ")); //+20
        if (!name.isEmpty()) p.add(n); //+16
        p.add(h); //+16

        y_size += 52;
        p.setBounds(10, y, 280, y_size);
        if (y + y_size + 10 > 397) {
            pane.setPreferredSize(new Dimension(300, y + y_size + 10));
        } else {
            pane.setPreferredSize(new Dimension(300, 397));
        }
    }

    public void addDamage(float total_Min, float total_Max) {
        JLabel l = new JLabel(total_Min + " to " + total_Max);
        l.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(new JLabel(" ")); //+20
        p.add(l); //+16
        y_size += 36;
        p.setBounds(10, y, 280, y_size);
        if (y + y_size + 10 > 397) {
            pane.setPreferredSize(new Dimension(300, y + y_size + 10));
        } else {
            pane.setPreferredSize(new Dimension(300, 397));
        }
    }

    public void addDamage(String name, float neutral_min, float earth_min, float thunder_min, float water_min, float fire_min, float air_min
            , float neutral_max, float earth_max, float thunder_max, float water_max, float fire_max, float air_max
            , boolean isTotal) {

        float crit_boost = 1F;
        if (this.crit_boost) crit_boost = 1.15F;

        float crit_neutral_min = neutral_min * (crit_boost + sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost());
        float crit_earth_min = earth_min  * (crit_boost + sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost());
        float crit_thunder_min = thunder_min  * (crit_boost + sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost());
        float crit_water_min = water_min  * (crit_boost + sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost());
        float crit_fire_min = fire_min  * (crit_boost + sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost());
        float crit_air_min = air_min  * (crit_boost + sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost());

        float crit_neutral_max = neutral_max * (crit_boost + sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost());
        float crit_earth_max = earth_max  * (crit_boost + sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost());
        float crit_thunder_max = thunder_max  * (crit_boost + sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost());
        float crit_water_max = water_max  * (crit_boost + sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost());
        float crit_fire_max = fire_max  * (crit_boost + sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost());
        float crit_air_max = air_max  * (crit_boost + sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost());

        neutral_min *= sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost();
        earth_min *= sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost();
        thunder_min *= sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost();
        water_min *= sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost();
        fire_min *= sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost();
        air_min *= sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost();

        neutral_max *= sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost();
        earth_max *= sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost();
        thunder_max *= sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost();
        water_max *= sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost();
        fire_max *= sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost();
        air_max *= sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost();

        float total_min = neutral_min + earth_min + thunder_min + water_min + fire_min + air_min;
        float total_max = neutral_max + earth_max + thunder_max + water_max + fire_max + air_max;
        float crit_min = crit_neutral_min + crit_earth_min + crit_thunder_min + crit_water_min + crit_fire_min + crit_air_min;
        float crit_max = crit_neutral_max + crit_earth_max + crit_thunder_max + crit_water_max + crit_fire_max + crit_air_max;
        float crit_chance = sp.getSkillPoint(SkillPoint.SkillPointType.DEXTERITY).getSPBoost();
        float total_average = (total_min + total_max) / 2F * (2F - crit_chance) + (crit_min + crit_max) / 2F * (crit_chance - 1F);

        JLabel nameL = new JLabel(name);
        JLabel avg = new JLabel("Average: " + total_average);
        JLabel no_crit_avg = new JLabel("Non-Crit Average: " + ((total_min + total_max) / 2F));
        JLabel neutral = new JLabel(neutral_min + " Neutral " + neutral_max);
        JLabel earth = new JLabel(earth_min + " Earth " + earth_max);
        JLabel thunder = new JLabel(thunder_min + " Thunder " + thunder_max);
        JLabel water = new JLabel(water_min + " Water " + water_max);
        JLabel fire = new JLabel(fire_min + " Fire " + fire_max);
        JLabel air = new JLabel(air_min + " Air " + air_max);
        JLabel total = new JLabel(total_min + " Total " + total_max);

        JLabel critical_avg = new JLabel("Crit Average: " + ((crit_min + crit_max) / 2F));
        JLabel critical_neutral = new JLabel(crit_neutral_min + " Neutral " + crit_neutral_max);
        JLabel critical_earth = new JLabel(crit_earth_min + " Earth " + crit_earth_max);
        JLabel critical_thunder = new JLabel(crit_thunder_min + " Thunder " + crit_thunder_max);
        JLabel critical_water = new JLabel(crit_water_min + " Water " + crit_water_max);
        JLabel critical_fire = new JLabel(crit_fire_min + " Fire " + crit_fire_max);
        JLabel critical_air = new JLabel(crit_air_min + " Air " + crit_air_max);
        JLabel critical_total = new JLabel(crit_min + " Total " + crit_max);

        nameL.setFont(new Font(Font.DIALOG, Font.PLAIN, 15));

        nameL.setAlignmentX(Component.CENTER_ALIGNMENT);
        avg.setAlignmentX(Component.CENTER_ALIGNMENT);
        no_crit_avg.setAlignmentX(Component.CENTER_ALIGNMENT);
        neutral.setAlignmentX(Component.CENTER_ALIGNMENT);
        earth.setAlignmentX(Component.CENTER_ALIGNMENT);
        thunder.setAlignmentX(Component.CENTER_ALIGNMENT);
        water.setAlignmentX(Component.CENTER_ALIGNMENT);
        fire.setAlignmentX(Component.CENTER_ALIGNMENT);
        air.setAlignmentX(Component.CENTER_ALIGNMENT);
        total.setAlignmentX(Component.CENTER_ALIGNMENT);

        critical_avg.setAlignmentX(Component.CENTER_ALIGNMENT);
        critical_neutral.setAlignmentX(Component.CENTER_ALIGNMENT);
        critical_earth.setAlignmentX(Component.CENTER_ALIGNMENT);
        critical_thunder.setAlignmentX(Component.CENTER_ALIGNMENT);
        critical_water.setAlignmentX(Component.CENTER_ALIGNMENT);
        critical_fire.setAlignmentX(Component.CENTER_ALIGNMENT);
        critical_air.setAlignmentX(Component.CENTER_ALIGNMENT);
        critical_total.setAlignmentX(Component.CENTER_ALIGNMENT);

        p.add(new JLabel(" ")); // +20

        if (!name.isEmpty()) {
            p.add(nameL);
            y_size += 20;
        }
        p.add(avg); // +16
        p.add(no_crit_avg); // +16
        if (neutral_min != 0 || neutral_max != 0) {
            p.add(neutral);
            y_size += 16;
        }
        if (earth_min != 0 || earth_max != 0) {
            p.add(earth);
            y_size += 16;
        }
        if (thunder_min != 0 || thunder_max != 0) {
            p.add(thunder);
            y_size += 16;
        }
        if (water_min != 0 || water_max != 0) {
            p.add(water);
            y_size += 16;
        }
        if (fire_min != 0 || fire_max != 0) {
            p.add(fire);
            y_size += 16;
        }
        if (air_min != 0 || air_max != 0) {
            p.add(air);
            y_size += 16;
        }
        if (total_min != 0 || total_max != 0) {
            p.add(total);
            y_size += 16;
        }

        p.add(new JLabel(" ")); // +16

        //Critical
        p.add(critical_avg); // +16
        if (neutral_min != 0 || neutral_max != 0) {
            p.add(critical_neutral);
            y_size += 16;
        }
        if (earth_min != 0 || earth_max != 0) {
            p.add(critical_earth);
            y_size += 16;
        }
        if (thunder_min != 0 || thunder_max != 0) {
            p.add(critical_thunder);
            y_size += 16;
        }
        if (water_min != 0 || water_max != 0) {
            p.add(critical_water);
            y_size += 16;
        }
        if (fire_min != 0 || fire_max != 0) {
            p.add(critical_fire);
            y_size += 16;
        }
        if (air_min != 0 || air_max != 0) {
            p.add(critical_air);
            y_size += 16;
        }
        if (crit_min != 0 || crit_max != 0) {
            p.add(critical_total);
            y_size += 16;
        }

        y_size += 84;
        p.setBounds(10, y, 280, y_size);
        if (y + y_size + 10 > 397) {
            pane.setPreferredSize(new Dimension(300, y + y_size + 10));
        } else {
            pane.setPreferredSize(new Dimension(300, 397));
        }

        if (isTotal) {
            avg_total.setText("Total Average: " + total_average);
        }
    }
}
