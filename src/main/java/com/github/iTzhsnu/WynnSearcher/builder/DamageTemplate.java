package com.github.iTzhsnu.WynnSearcher.builder;

import com.github.iTzhsnu.WynnSearcher.Identifications;

import javax.swing.*;
import java.awt.*;

public class DamageTemplate {
    private final JPanel p = new JPanel();
    private int ySize = 25;
    private int y = 10;
    private final JLabel avgTotal = new JLabel("Total Average: 0");
    private final SkillPoint sp;
    private final boolean critBoost;
    private final JPanel pane;

    public DamageTemplate(String name, int manaCost, JPanel pane, DamageTemplate previous, SkillPoint sp, boolean critBoost, boolean needTotal) {
        this.sp  = sp;
        this.critBoost = critBoost;
        this.pane = pane;
        if (previous != null) {
            p.setBounds(10, previous.y + previous.ySize + 10, 280, 30);
            y = previous.y + previous.ySize + 10;
        } else {
            p.setBounds(10, 10, 280, 30);
        }
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        JLabel nameL = new JLabel();
        if (manaCost > 0) {
            nameL.setText(name + " (" + manaCost + ")");
        } else {
            nameL.setText(name);
        }
        nameL.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        nameL.setAlignmentX(Component.CENTER_ALIGNMENT);
        avgTotal.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(nameL);
        if (needTotal) { //Blank
            p.add(new JLabel(" ")); // +20
            ySize += 20;
        }
        if (needTotal) { //Avg Total Damage
            p.add(avgTotal); // +16
            ySize += 16;
        }
        if (y + ySize + 10 > 397) {
            pane.setPreferredSize(new Dimension(300, y + ySize + 10));
        } else {
            pane.setPreferredSize(new Dimension(300, 397));
        }

        p.setBackground(new Color(200, 200, 200));
        pane.add(p);
    }

    public void addHeal(String name, int[] idNumbers, float healPercent, boolean isCanBoost, boolean useGentleGlow) {
        float heal = (idNumbers[IdDisplay.ID_INT.get(Identifications.HEALTH)] + idNumbers[IdDisplay.ID_INT.get(Identifications.HEALTH_BONUS)]) * healPercent * (1F + (idNumbers[IdDisplay.ID_INT.get(Identifications.HEALING_EFFICIENCY)] / 100F));
        if (isCanBoost) {
            if (useGentleGlow) {
                heal *= 1F + (Math.min(idNumbers[IdDisplay.ID_INT.get(Identifications.WATER_DAMAGE_PERCENT)] * 0.75F, 100) / 100F); //TODO: NEED CHECK
            } else {
                heal *= 1F + (Math.min(idNumbers[IdDisplay.ID_INT.get(Identifications.WATER_DAMAGE_PERCENT)] * 0.3F, 75) / 100F);
            }
        }
        JLabel n = new JLabel(name);
        JLabel h = new JLabel(String.valueOf((int) Math.floor(heal)));
        n.setAlignmentX(Component.CENTER_ALIGNMENT);
        h.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(new JLabel(" ")); //+20
        if (!name.isEmpty()) p.add(n); //+16
        p.add(h); //+16

        ySize += 52;
        p.setBounds(10, y, 280, ySize);
        if (y + ySize + 10 > 397) {
            pane.setPreferredSize(new Dimension(300, y + ySize + 10));
        } else {
            pane.setPreferredSize(new Dimension(300, 397));
        }
    }

    public void addDamage(float totalMin, float totalMax) {
        JLabel l = new JLabel(totalMin + " to " + totalMax);
        l.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(new JLabel(" ")); //+20
        p.add(l); //+16
        ySize += 36;
        p.setBounds(10, y, 280, ySize);
        if (y + ySize + 10 > 397) {
            pane.setPreferredSize(new Dimension(300, y + ySize + 10));
        } else {
            pane.setPreferredSize(new Dimension(300, 397));
        }
    }

    public void addDamage(String name, float neutralMin, float earthMin, float thunderMin, float waterMin, float fireMin, float airMin
            , float neutralMax, float earthMax, float thunderMax, float waterMax, float fireMax, float airMax
            , boolean isTotal) {

        float critBoost = 1F;
        if (this.critBoost) critBoost = 1.15F;

        float critNeutralMin = neutralMin * (critBoost + sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost());
        float critEarthMin = earthMin * (critBoost + sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost());
        float critThunderMin = thunderMin * (critBoost + sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost());
        float critWaterMin = waterMin * (critBoost + sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost());
        float critFireMin = fireMin * (critBoost + sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost());
        float critAirMin = airMin * (critBoost + sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost());

        float critNeutralMax = neutralMax * (critBoost + sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost());
        float critEarthMax = earthMax * (critBoost + sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost());
        float critThunderMax = thunderMax * (critBoost + sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost());
        float critWaterMax = waterMax * (critBoost + sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost());
        float critFireMax = fireMax * (critBoost + sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost());
        float critAirMax = airMax * (critBoost + sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost());

        neutralMin *= sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost();
        earthMin *= sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost();
        thunderMin *= sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost();
        waterMin *= sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost();
        fireMin *= sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost();
        airMin *= sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost();

        neutralMax *= sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost();
        earthMax *= sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost();
        thunderMax *= sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost();
        waterMax *= sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost();
        fireMax *= sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost();
        airMax *= sp.getSkillPoint(SkillPoint.SkillPointType.STRENGTH).getSPBoost();

        float totalMin = neutralMin + earthMin + thunderMin + waterMin + fireMin + airMin;
        float totalMax = neutralMax + earthMax + thunderMax + waterMax + fireMax + airMax;
        float critMin = critNeutralMin + critEarthMin + critThunderMin + critWaterMin + critFireMin + critAirMin;
        float critMax = critNeutralMax + critEarthMax + critThunderMax + critWaterMax + critFireMax + critAirMax;
        float critChance = sp.getSkillPoint(SkillPoint.SkillPointType.DEXTERITY).getSPBoost();
        float totalAverage = (totalMin + totalMax) / 2F * (2F - critChance) + (critMin + critMax) / 2F * (critChance - 1F);

        JLabel nameL = new JLabel(name);
        JLabel avg = new JLabel("Average: " + totalAverage);
        JLabel noCritAvg = new JLabel("Non-Crit Average: " + ((totalMin + totalMax) / 2F));
        JLabel neutral = new JLabel(neutralMin + " Neutral " + neutralMax);
        JLabel earth = new JLabel(earthMin + " Earth " + earthMax);
        JLabel thunder = new JLabel(thunderMin + " Thunder " + thunderMax);
        JLabel water = new JLabel(waterMin + " Water " + waterMax);
        JLabel fire = new JLabel(fireMin + " Fire " + fireMax);
        JLabel air = new JLabel(airMin + " Air " + airMax);
        JLabel total = new JLabel(totalMin + " Total " + totalMax);

        JLabel critAvg = new JLabel("Crit Average: " + ((critMin + critMax) / 2F));
        JLabel critBeutral = new JLabel(critNeutralMin + " Neutral " + critNeutralMax);
        JLabel critEarth = new JLabel(critEarthMin + " Earth " + critEarthMax);
        JLabel critThunder = new JLabel(critThunderMin + " Thunder " + critThunderMax);
        JLabel critWater = new JLabel(critWaterMin + " Water " + critWaterMax);
        JLabel critFire = new JLabel(critFireMin + " Fire " + critFireMax);
        JLabel critAir = new JLabel(critAirMin + " Air " + critAirMax);
        JLabel critTotal = new JLabel(critMin + " Total " + critMax);

        nameL.setFont(new Font(Font.DIALOG, Font.PLAIN, 15));

        nameL.setAlignmentX(Component.CENTER_ALIGNMENT);
        avg.setAlignmentX(Component.CENTER_ALIGNMENT);
        noCritAvg.setAlignmentX(Component.CENTER_ALIGNMENT);
        neutral.setAlignmentX(Component.CENTER_ALIGNMENT);
        earth.setAlignmentX(Component.CENTER_ALIGNMENT);
        thunder.setAlignmentX(Component.CENTER_ALIGNMENT);
        water.setAlignmentX(Component.CENTER_ALIGNMENT);
        fire.setAlignmentX(Component.CENTER_ALIGNMENT);
        air.setAlignmentX(Component.CENTER_ALIGNMENT);
        total.setAlignmentX(Component.CENTER_ALIGNMENT);

        critAvg.setAlignmentX(Component.CENTER_ALIGNMENT);
        critBeutral.setAlignmentX(Component.CENTER_ALIGNMENT);
        critEarth.setAlignmentX(Component.CENTER_ALIGNMENT);
        critThunder.setAlignmentX(Component.CENTER_ALIGNMENT);
        critWater.setAlignmentX(Component.CENTER_ALIGNMENT);
        critFire.setAlignmentX(Component.CENTER_ALIGNMENT);
        critAir.setAlignmentX(Component.CENTER_ALIGNMENT);
        critTotal.setAlignmentX(Component.CENTER_ALIGNMENT);

        p.add(new JLabel(" ")); // +20

        if (!name.isEmpty()) {
            p.add(nameL);
            ySize += 20;
        }
        p.add(avg); // +16
        p.add(noCritAvg); // +16
        if (neutralMin != 0 || neutralMax != 0) {
            p.add(neutral);
            ySize += 16;
        }
        if (earthMin != 0 || earthMax != 0) {
            p.add(earth);
            ySize += 16;
        }
        if (thunderMin != 0 || thunderMax != 0) {
            p.add(thunder);
            ySize += 16;
        }
        if (waterMin != 0 || waterMax != 0) {
            p.add(water);
            ySize += 16;
        }
        if (fireMin != 0 || fireMax != 0) {
            p.add(fire);
            ySize += 16;
        }
        if (airMin != 0 || airMax != 0) {
            p.add(air);
            ySize += 16;
        }
        if (totalMin != 0 || totalMax != 0) {
            p.add(total);
            ySize += 16;
        }

        p.add(new JLabel(" ")); // +16

        //Critical
        p.add(critAvg); // +16
        if (neutralMin != 0 || neutralMax != 0) {
            p.add(critBeutral);
            ySize += 16;
        }
        if (earthMin != 0 || earthMax != 0) {
            p.add(critEarth);
            ySize += 16;
        }
        if (thunderMin != 0 || thunderMax != 0) {
            p.add(critThunder);
            ySize += 16;
        }
        if (waterMin != 0 || waterMax != 0) {
            p.add(critWater);
            ySize += 16;
        }
        if (fireMin != 0 || fireMax != 0) {
            p.add(critFire);
            ySize += 16;
        }
        if (airMin != 0 || airMax != 0) {
            p.add(critAir);
            ySize += 16;
        }
        if (critMin != 0 || critMax != 0) {
            p.add(critTotal);
            ySize += 16;
        }

        ySize += 84;
        p.setBounds(10, y, 280, ySize);
        if (y + ySize + 10 > 397) {
            pane.setPreferredSize(new Dimension(300, y + ySize + 10));
        } else {
            pane.setPreferredSize(new Dimension(300, 397));
        }

        if (isTotal) {
            avgTotal.setText("Total Average: " + totalAverage);
        }
    }
}
