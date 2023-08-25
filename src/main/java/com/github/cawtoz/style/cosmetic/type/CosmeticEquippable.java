package com.github.cawtoz.style.cosmetic.type;

import com.github.cawtoz.style.Style;
import com.github.cawtoz.style.cosmetic.Cosmetic;
import com.github.cawtoz.style.cosmetic.CosmeticType;
import com.github.cawtoz.style.util.ColorUtil;
import com.github.cawtoz.style.util.ItemBuilder;
import com.github.cawtoz.style.util.file.FileUtil;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@Getter @Setter
public abstract class CosmeticEquippable extends Cosmetic {

    private LivingEntity entityEquippable;

    private ItemStack helmet;
    private ItemStack chestPlate;
    private ItemStack leggings;
    private ItemStack boots;

    private List<Color> colorHelmet;
    private List<Color> colorChestPlate;
    private List<Color> colorLeggings;
    private List<Color> colorBoots;

    private int stepsHelmet;
    private int stepsChetPlate;
    private int stepsLeggings;
    private int stepsBoots;

    private int currentStepHelmet;
    private int currentStepChetPlate;
    private int currentStepLeggings;
    private int currentStepBoots;

    public CosmeticEquippable(LivingEntity livingEntity, String name, CosmeticType type) {
        super(livingEntity, name, type);

        String file = type.getFileName();
        helmet = new ItemStack(FileUtil.getMaterial(file, name + ".HELMET.MATERIAL"));
        chestPlate = new ItemStack(FileUtil.getMaterial(file, name + ".CHESTPLATE.MATERIAL"));
        leggings = new ItemStack(FileUtil.getMaterial(file, name + ".LEGGINGS.MATERIAL"));
        boots = new ItemStack(FileUtil.getMaterial(file, name + ".BOOTS.MATERIAL"));

        colorHelmet = FileUtil.getColors(file, name + ".HELMET.COLOR");
        colorChestPlate = FileUtil.getColors(file, name + ".CHESTPLATE.COLOR");
        colorLeggings = FileUtil.getColors(file, name + ".LEGGINGS.COLOR");
        colorBoots = FileUtil.getColors(file, name + ".BOOTS.COLOR");

        stepsHelmet = FileUtil.getInt("colors", FileUtil.getString(file, name + ".HELMET.COLOR") + ".TIME") * 20;
        stepsChetPlate = FileUtil.getInt("colors", FileUtil.getString(file, name + ".CHESTPLATE.COLOR") + ".TIME") * 20;
        stepsLeggings = FileUtil.getInt("colors", FileUtil.getString(file, name + ".LEGGINGS.COLOR") + ".TIME") * 20;
        stepsBoots = FileUtil.getInt("colors", FileUtil.getString(file, name + ".BOOTS.COLOR") + ".TIME") * 20;
    }

    public void updateArmor() {
        changeColor();
        entityEquippable.getEquipment().setHelmet(getHelmet());
        entityEquippable.getEquipment().setChestplate(getChestPlate());
        entityEquippable.getEquipment().setLeggings(getLeggings());
        entityEquippable.getEquipment().setBoots(getBoots());
    }

    private void changeColor() {
        double progressHelmet = (double) currentStepHelmet / stepsHelmet;
        double progressChetPlate = (double) currentStepChetPlate / stepsChetPlate;
        double progressLeggings = (double) currentStepLeggings / stepsLeggings;
        double progressBoots = (double) currentStepBoots / stepsBoots;

        new ItemBuilder(getHelmet()).setColor(ColorUtil.getNextColor(getColorHelmet(), progressHelmet));
        new ItemBuilder(getChestPlate()).setColor(ColorUtil.getNextColor(getColorChestPlate(), progressChetPlate));
        new ItemBuilder(getLeggings()).setColor(ColorUtil.getNextColor(getColorLeggings(), progressLeggings));
        new ItemBuilder(getBoots()).setColor(ColorUtil.getNextColor(getColorBoots(), progressBoots));

        currentStepHelmet = getNextStep(currentStepHelmet, stepsHelmet);
        currentStepChetPlate = getNextStep(currentStepChetPlate, stepsChetPlate);
        currentStepLeggings = getNextStep(currentStepLeggings, stepsLeggings);
        currentStepBoots = getNextStep(currentStepBoots, stepsBoots);
    }

    private int getNextStep(int currentStep, int steps) {
        currentStep++;
        if (currentStep > steps) {
            currentStep = 0;
        }
        return currentStep;
    }

}
