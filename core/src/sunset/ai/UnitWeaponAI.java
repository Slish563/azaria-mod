package sunset.ai;

import arc.math.geom.Vec2;
import mindustry.entities.Predict;
import mindustry.entities.units.AIController;
import mindustry.entities.units.WeaponMount;
import mindustry.type.Weapon;
import sunset.ai.weapon.WeaponAI;
import sunset.type.weapons.WeaponExt;
import sunset.utils.Utils;

/**
 * AI, которое использует AI орудия, если таковое существует.
 */
public class UnitWeaponAI extends AIController {
    @Override
    public void updateWeapons() {
        float rotation = unit.rotation - 90;
        boolean ret = retarget();

        if (ret) {
            target = findTarget(unit.x, unit.y, unit.range(), unit.type.targetAir, unit.type.targetGround);
        }
        if (invalid(target)) {
            target = null;
        }

        unit.isShooting = false;
        for (WeaponMount mount : unit.mounts) {
            Weapon weapon = mount.weapon;

            float mountX = Utils.mountX(unit, mount),
                    mountY = Utils.mountY(unit, mount);

            if (weapon instanceof WeaponExt) {
                WeaponAI ai = ((WeaponExt) weapon).ai;
                unit.isShooting |= ai.update(unit, mount);
            } else {
                if (ret) {
                    mount.target = findTarget(mountX, mountY, weapon.bullet.range(), weapon.bullet.collidesAir, weapon.bullet.collidesGround);
                }

                boolean shoot = false;

                if (mount.target != null) {
                    shoot = mount.target.within(mountX, mountY, weapon.bullet.range()) && shouldShoot();

                    Vec2 to = Predict.intercept(unit, mount.target, weapon.bullet.speed);
                    mount.aimX = to.x;
                    mount.aimY = to.y;
                }

                mount.shoot = shoot;
                mount.rotate = shoot;

                unit.isShooting |= shoot;
                if (shoot) {
                    unit.aimX = mount.aimX;
                    unit.aimY = mount.aimY;
                }
            }
        }
    }
}
