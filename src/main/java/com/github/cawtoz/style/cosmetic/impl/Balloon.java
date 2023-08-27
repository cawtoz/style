package com.github.cawtoz.style.cosmetic.impl;

import com.github.cawtoz.style.cosmetic.CosmeticType;
import com.github.cawtoz.style.cosmetic.type.CosmeticTeleportable;
import com.github.cawtoz.style.util.HeadUtil;
import com.github.cawtoz.style.util.LocationUtil;
import com.github.cawtoz.style.util.file.FileUtil;
import de.tr7zw.nbtapi.NBTEntity;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffectType;

@Getter @Setter
public class Balloon extends CosmeticTeleportable {

    private final String headValue;
    private Location leashBalonLocation;
    private ArmorStand armorStandBalloon;
    private LivingEntity leashEntityBalloon;

    //Distance
    private final double side;
    private final double frontal;
    private final double height;

    //Rotation
    private final boolean isRotation;
    private final double timeRotation;
    private float yaw;

    //Displacement
    private final boolean isDisplacement;
    private final double rangeDisplacement;
    private final double timeDisplacement;
    private double displacementPosition;
    private boolean up;
    private double valueY;

    public Balloon(LivingEntity livingEntity, String name) {

        super(livingEntity, name, CosmeticType.BALLOON);
        setDisplayName(FileUtil.getString("cosmetics/balloons", getName() + ".DISPLAY-NAME"));
        this.headValue = FileUtil.getString("cosmetics/balloons", getName() + ".HEAD-VALUE");

        //Distance
        side = FileUtil.getDouble("cosmetics/balloons", getName() + ".DISTANCE.SIDE");
        frontal = FileUtil.getDouble("cosmetics/balloons", getName() + ".DISTANCE.FRONTAL");
        height = FileUtil.getDouble("cosmetics/balloons", getName() + ".DISTANCE.HEIGHT");

        //Rotation
        isRotation = FileUtil.getBoolean("cosmetics/balloons", getName() + ".ANIMATION.ROTATION.ENABLE");
        timeRotation = FileUtil.getDouble("cosmetics/balloons", getName() + ".ANIMATION.ROTATION.TIME");
        yaw = -180;

        //Displacement
        isDisplacement = FileUtil.getBoolean("cosmetics/balloons", getName() + ".ANIMATION.DISPLACEMENT.ENABLE");
        rangeDisplacement = FileUtil.getDouble("cosmetics/balloons", getName() + ".ANIMATION.DISPLACEMENT.RANGE");
        timeDisplacement = FileUtil.getDouble("cosmetics/balloons", getName() + ".ANIMATION.DISPLACEMENT.TIME");
        up = true;
    }

    @Override
    public Balloon create() {

        //Location
        updateLocation(getLocation());

        //Balloon
        armorStandBalloon = (ArmorStand) getLocation().getWorld().spawnEntity(getLocation(), EntityType.ARMOR_STAND);
        armorStandBalloon.setHelmet(HeadUtil.createCustomSkull(headValue));
        armorStandBalloon.setVisible(false);
        armorStandBalloon.setGravity(false);
        armorStandBalloon.setMarker(true);

        //Leash
        leashEntityBalloon = (LivingEntity) getLocation().getWorld().spawnEntity(leashBalonLocation, EntityType.CHICKEN);
        leashEntityBalloon.addPotionEffect(PotionEffectType.INVISIBILITY.createEffect(Integer.MAX_VALUE, 0));
        NBTEntity entity = new NBTEntity(leashEntityBalloon);
        entity.setBoolean("Silent", true);
        entity.setBoolean("NoAI", true);
        entity.setBoolean("Gravity", false);
        entity.setBoolean("Invulnerable", true);
        ((Chicken) leashEntityBalloon).setAgeLock(true);
        leashEntityBalloon.setLeashHolder(getPlayer());

        return this;

    }

    @Override
    public void destroy() {
        armorStandBalloon.remove();
        leashEntityBalloon.remove();
    }

    @Override
    public void updateLocation(Location location) {
        setLocation(LocationUtil.createLocation(location, side, frontal, height + displacementPosition));
        leashBalonLocation = LocationUtil.createLocation(getLocation(), 0, 0, 0.5 + displacementPosition);
    }

    public void makeAnimation() {
        makeRotation();
        makeDisplacement();
    }

    public void makeRotation() {
        if (isRotation) {
            yaw += 360 / (timeRotation * 20);
            if (yaw >= 180) yaw = -180;
            getLocation().setYaw(yaw);
        }
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
                leashBalonLocation.setY(leashBalonLocation.getY() + displacementIncrement);
            } else {
                valueY -= 1;
                displacementPosition -= displacementIncrement;
                getLocation().setY(getLocation().getY() - displacementIncrement);
                leashBalonLocation.setY(leashBalonLocation.getY() - displacementIncrement);
            }

        }

        armorStandBalloon.teleport(getLocation());
        leashEntityBalloon.teleport(leashBalonLocation);

    }

}

