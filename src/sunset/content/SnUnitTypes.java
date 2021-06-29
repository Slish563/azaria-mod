package sunset.content;

import mindustry.Vars;
import mindustry.content.UnitTypes;
import mindustry.ctype.Content;
import mindustry.ctype.ContentList;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.game.Team;
import mindustry.gen.Sounds;
import mindustry.gen.UnitEntity;
import mindustry.type.UnitType;
import mindustry.type.Weapon;
import sunset.ai.ExtinguishAI;
import sunset.ai.FlyingUnitWeaponAI;
import sunset.ai.weapon.EmptyWeaponAI;
import sunset.ai.weapon.ExtinguishWeaponAI;
import sunset.type.AutoWeapon;
import sunset.type.ChainWeapon;
import sunset.type.CopterUnitType;
import sunset.type.UnitTypeExt;

public class SnUnitTypes implements ContentList{
   public static UnitType

    //flying
    guardcopter, bladecopter, //attacks copters
    
      comet, satelite; //buffers

@Override
public void load() {
   
      guardcopter = new CopterUnitType("guard_copter"){{
         health = 170;
         hitSize = 20;
         speed = 3.3f;
         accel = 0.1f;
         drag = 0.02f;
         commandLimit = 4;
         constructor = UnitEntity::create;

         flying = true;
         circleTarget = false;
         range = 130;

         offsetY = 2.2f;
         weapons.add(
                new Weapon("sunset-guard-gun"){{
                    rotate = false;
                    mirror = true;
                    top = true;
                    x = 8f;
                    y = 0f;
                    shots = 1;
                    inaccuracy = 1;
                    reload = 5f;
                    shootSound = Sounds.pew;
                    bullet = SnBullets.BasicHelicopterGun;
            }});
      }};

      bladecopter = new CopterUnitType("blade_copter"){{
            health = 370;
            hitSize = 31;
            speed = 3.0f;
            accel = 0.1f;
            drag = 0.02f;
            commandLimit = 4;
            constructor = UnitEntity::create;
            
            flying = true;
            circleTarget = false;
            range = 170;
   
            offsetY = 2.5f;
            weapons.add(
                   new Weapon("sunset-blade-gun"){{
                       rotate = false;
                       mirror = true;
                       top = true;
                       x = -10f;
                       y = 4f;
                       spacing = 2f;
                       shots = 3;
                       inaccuracy = 10;
                       reload = 15f;
                       shootSound = Sounds.shoot;
                       bullet = SnBullets.HelicopterShootgun;
               }});
         }};

//buffers
         comet = new UnitType("comet"){{
            health = 150;
            hitSize = 12;
            speed = 3f;
            accel = 0.15f;
            drag = 0.1f;

            flying = true;
            circleTarget = false;
            range = 75;

            itemCapacity = 20;
            commandLimit = 4;

            defaultController = ExtinguishAI::new;

            constructor = UnitEntity::create;

            weapons.add(new AutoWeapon("comet"){{
                ai = new ExtinguishWeaponAI();
                rotate = true;
                mirror = false;
                x = 0;
                top = true;
                inaccuracy = 4;
                alternate = false;
                reload = 2.5f;
                shootSound = Sounds.spray;
                bullet = SnBullets.cometWaterShot;
            }});
        }};

         satelite = new UnitTypeExt("satellite"){{
             health = 470;
             hitSize = 16;
             speed = 2.8f;
             accel = 0.2f;
             drag = 0.15f;

             flying = true;
             circleTarget = false;
             range = 180;

             itemCapacity = 20;
             commandLimit = 4;

             defaultController = FlyingUnitWeaponAI::new;

             constructor = UnitEntity::create;

             weapons.add(new ChainWeapon("satellite"){{
                 ai = new EmptyWeaponAI();
                 damageTick = 1f;
                 healTick = 2f;
                 alternate = false;
                 mirror = false;
                 rotate = false;
                 x = 0;
                 maxChainLength = 2;
                 shootCone = 2f;
                 range = 180;
             }});
         }};
    }
}
