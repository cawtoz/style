package com.github.cawtoz.style.cosmetic.impl;

import com.github.cawtoz.style.cosmetic.CosmeticType;
import com.github.cawtoz.style.cosmetic.type.CosmeticEquippableTeleportable;
import com.github.cawtoz.style.util.HeadUtil;
import com.github.cawtoz.style.util.LocationUtil;
import com.github.cawtoz.style.util.file.FileUtil;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

@Getter @Setter
public class Guardian extends CosmeticEquippableTeleportable {

    private Location location;

    private boolean headEnable;
    private final String headValue;
    private ItemStack chestplate;
    private ArmorStand armorStandAngel;

    //Distance
    private final double side;
    private final double frontal;
    private final double height;

    //Displacement
    private final boolean isDisplacement;
    private final double rangeDisplacement;
    private final double timeDisplacement;
    private double displacementPosition;
    private boolean up;
    private double valueY;

    public Guardian(LivingEntity livingEntity, String name) {

        super(livingEntity, name, CosmeticType.GUARDIAN);
        setDisplayName(FileUtil.getString("cosmetics/guardians", getName() + ".DISPLAY-NAME"));

        headEnable = FileUtil.getBoolean("cosmetics/guardians", getName() + ".HEAD.ENABLE");
        headValue = FileUtil.getString("cosmetics/guardians", getName() + ".HEAD.VALUE");
        chestplate = new ItemStack(FileUtil.getMaterial("cosmetics/guardians", getName() + ".CHESTPLATE"));


        //Distance
        side = FileUtil.getDouble("cosmetics/guardians", getName() + ".DISTANCE.SIDE");
        frontal = FileUtil.getDouble("cosmetics/guardians", getName() + ".DISTANCE.FRONTAL");
        height = FileUtil.getDouble("cosmetics/guardians", getName() + ".DISTANCE.HEIGHT");

        //Displacement
        isDisplacement = FileUtil.getBoolean("cosmetics/guardians", getName() + ".ANIMATION.DISPLACEMENT.ENABLE");
        rangeDisplacement = FileUtil.getDouble("cosmetics/guardians", getName() + ".ANIMATION.DISPLACEMENT.RANGE");
        timeDisplacement = FileUtil.getDouble("cosmetics/guardians", getName() + ".ANIMATION.DISPLACEMENT.TIME");

        location = livingEntity.getLocation();

    }

    @Override
    public Guardian create() {

        //Location
        updateLocation(getLocation());

        //Guardian
        armorStandAngel = (ArmorStand) getLocation().getWorld().spawnEntity(getLocation(), EntityType.ARMOR_STAND);
        armorStandAngel.setSmall(true);
        armorStandAngel.setVisible(false);
        armorStandAngel.setGravity(false);
        setEntityEquippable(armorStandAngel);
        if (headEnable) setHelmet(HeadUtil.createCustomSkull(headValue));
        updateArmor();

        return this;

    }

    @Override
    public void destroy() {
        armorStandAngel.remove();
    }

    @Override
    public void updateLocation(Location location) {
        setLocation(LocationUtil.createLocation(location, side, frontal, height + displacementPosition));
    }

    @Override
    public void makeAnimation() {
        makeDisplacement();
    }

    public void makeDisplacement() {

        if (isDisplacement) {

            double displacementIncrement = rangeDisplacement / (timeDisplacement * 10);
            double valueYMax = timeDisplacement * 10;

            if (valueY >= valueYMax) {
                up = false;
            } else if (valueY <= 0) {
                up = true;
            }

            if (up) {
                valueY += 1;
                displacementPosition += displacementIncrement;
                getLocation().setY(getLocation().getY() + displacementIncrement);
            } else {
                valueY -= 1;
                displacementPosition -= displacementIncrement;
                getLocation().setY(getLocation().getY() - displacementIncrement);
            }

        }

        armorStandAngel.teleport(getLocation());

    }

}
