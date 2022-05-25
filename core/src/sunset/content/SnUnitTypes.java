package sunset.content;

import arc.graphics.*;
import arc.util.*;
import mindustry.*;
import mindustry.ai.types.*;
import mindustry.annotations.Annotations.*;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.ammo.*;
import mindustry.type.weapons.*;
import mindustry.world.meta.*;
import sunset.ai.*;
import sunset.ai.weapon.*;
import sunset.entities.abilities.*;
import sunset.entities.bullet.*;
import sunset.gen.*;
import sunset.type.*;
import sunset.type.ammo.*;
import sunset.type.blocks.*;
import sunset.type.unitTypes.*;
import sunset.type.weapons.*;

import static mindustry.Vars.tilePayload;

public class SnUnitTypes{
    public static UnitType

    //vanilla
    bastion, arahnus, buffedCrawler,

    //attack copters
    wind, thunder, nadir, halo, mudflow, parhelion,
    //buffers
    comet, satellite, planet, star, galaxy, universe,
    //ground
    mirage, vision, illusion, soothSayer, seer, abyssEye,
    wheelT1, wheelT2, wheelT3, wheelT4, wheelT5, wheelT6,
    freezingT1,
    //air
    engineT1, hoverTest,
    //hylight
    light,
    //naval
    yellowT1, yellowT2, yellowT3, yellowT4, yellowT5,
    //misc
    router;
    //other
    @EntityDef({Unitc.class, Deliverc.class})
    public static UnitType courier;
    @EntityDef({Unitc.class, Segmentc.class})
    public static UnitType snake1;

    public static void load(){
        //region vanilla
        bastion = new UnitType("bastion"){{
            health = 47500;
            speed = 0.3f;
            hitSize = 37f;
            rotateSpeed = 1.4f;

            armor = 18f;
            mechStepParticles = true;
            drownTimeMultiplier = 7f;
            mechFrontSway = 2f;
            mechSideSway = 0.8f;
            stepShake = 1f;
            constructor = MechUnit::create;

            weapons.addAll(
            new SnWeapon("bastion-weapon"){{
                rotate = false;
                mirror = true;
                top = false;
                x = 36f;
                y = -1f;
                reload = 11f;
                inaccuracy = 3f;
                shootSound = Sounds.shootBig;
            }},
            new SnWeapon("bastion-art"){{
                rotate = true;
                mirror = false;
                x = 0f;
                y = -5f;
                reload = 50f;
                inaccuracy = 5f;
                shoot.shots = 5;
                shootSound = Sounds.artillery;
            }},
            new SnWeapon("bastion-fl"){{
                rotate = false;
                mirror = true;
                x = 17f;
                y = 19f;
                layerOffset = -0.01f;
                shootSound = Sounds.flame;
            }},
            new SnWeapon("bastion-fl"){{
                rotate = false;
                mirror = false;
                x = 0f;
                y = 24f;
                layerOffset = -0.01f;
                shootSound = Sounds.flame;
            }}
            );
        }};
        buffedCrawler = new UnitType("buffed-crawler"){
            {
                controller = u->new SuicideAI();

                speed = 1f;
                hitSize = 8f;
                health = 175;
                armor = 19f;
                mechSideSway = 0.25f;
                range = 60f;
                constructor = MechUnit::create;

                weapons.add(new Weapon(){{
                    shootOnDeath = true;
                    reload = 24f;
                    shootCone = 180f;
                    ejectEffect = Fx.none;
                    shootSound = Sounds.explosionbig;
                    x = shootY = 0f;
                    mirror = false;
                    bullet = SnBullets.t6crawlerBoom;
                }});
            }
            /*
            TODO заблокировать после отладки. Пока он должен быть виден, но в итоге - нет.
            @Override
            public boolean unlocked() { return false; }
            @Override
            public boolean unlockedNow() { return false; }
            @Override
            public boolean unlockedNowHost() { return false; }*/
        };
        arahnus = new UnitTypeExt("arahnus"){{
            health = 61000;
            speed = 0.6f;
            rotateSpeed = 1.5f;
            drag = 0.125f;
            hitSize = 71;
            armor = 26f;
            allowLegStep = true;
            hovering = true;
            groundLayer = Layer.legUnit;
            shadowElevation = 1.1f;

            legCount = 8;
            legMoveSpace = 0.9f;
            legPairOffset = 3;
            legLength = 70f;
            legExtension = -27;
            legBaseOffset = 4f;
            mechLandShake = 4f;
            legLengthScl = 2f;
            rippleScale = 4f;
            legSpeed = 0.3f;

            legSplashDamage = 70;
            legSplashRange = 70;

            constructor = LegsUnit::create;

            ammoType = new ItemAmmoType(SnItems.planatrium, 6);
            buildSpeed = 1f;

            weapons.add(
            new SnWeapon("arahnus-cannon"){{
                y = -14f;
                x = 0f;
                shootY = 22f;
                mirror = false;
                reload = 290;
                shake = 15f;
                recoil = 15f;
                rotateSpeed = 0.8f;
                ejectEffect = Fx.casing2;
                shootSound = Sounds.artillery;
                rotate = true;
                shadow = 30f;

                bullet = new ArtilleryBulletType(5f, 190){{
                    hitEffect = Fx.sapExplosion;
                    knockback = 3.9f;
                    lifetime = 100f;
                    width = height = 40f;
                    collidesTiles = collides = true;
                    ammoMultiplier = 4f;
                    splashDamageRadius = 132f;
                    splashDamage = 470f;
                    backColor = Pal.sapBulletBack;
                    frontColor = lightningColor = Pal.sapBullet;
                    lightning = 6;
                    lightningLength = 48;
                    smokeEffect = Fx.shootBigSmoke2;
                    hitShake = 21f;
                    lightRadius = 65f;
                    lightColor = Pal.sap;
                    lightOpacity = 0.6f;

                    status = StatusEffects.sapped;
                    statusDuration = 60f * 10;

                    fragLifeMin = 0.6f;
                    fragBullets = 6;

                    fragBullet = new SpawnArtilleryBulletType(3f, 70){{
                        hitEffect = Fx.sapExplosion;
                        knockback = 1.6f;
                        lifetime = 60f;
                        width = height = 30f;
                        collidesTiles = false;
                        splashDamageRadius = 90f;
                        splashDamage = 95f;
                        backColor = Pal.sapBulletBack;
                        frontColor = lightningColor = Pal.sapBullet;
                        lightning = 4;
                        lightningLength = 9;
                        smokeEffect = Fx.shootBigSmoke2;
                        hitShake = 10f;
                        lightRadius = 45f;
                        lightColor = Pal.sap;
                        lightOpacity = 0.5f;

                        status = StatusEffects.sapped;
                        statusDuration = 60f * 10;

                        unitType = SnUnitTypes.buffedCrawler;
                    }};
                }};
            }},
            new SnWeapon("arahnus-sap"){{
                x = 15;
                reload = 10;
                shootCone = 20f;
                alternate = true;
                bullet = SnBullets.t6sapBullet;
                shootSound = Sounds.sap;
            }}
            );
        }};
        //endregion vanilla
        //region mod-units
        //region ground
        //region berserk
        mirage = new BerserkUnitType("mirage"){{
            health = 320;
            speed = 1f;
            rotateSpeed = 3f;
            drag = 0.1f;
            armor = 4f;
            hitSize = 10f;
            allowLegStep = true;
            hovering = false;
            groundLayer = Layer.legUnit;
            shadowElevation = 0.15f;

            legCount = 4;
            legLength = 7f;
            legForwardScl = 0.5f;
            legMoveSpace = 1.3f;

            weapons.add(new SnWeapon("mirage-gun"){{
                reload = 30f;
                x = 5;
                range = 100;
                y = -0.3f;
                inaccuracy = 2f;
                rotate = false;
                mirror = true;
                bullet = SnBullets.mirageGunBullet;
            }});
            stages.add(
            new BerserkStage(){{
                healthMaximum = 0.4f;
                bulletWidthMultiplier = 2f;
                effect = StatusEffects.overclock;
            }},
            new BerserkStage(){{
                healthMaximum = 0.15f;
                bulletWidthMultiplier = 3f;
                effect = StatusEffects.burning;
            }}
            );
        }};
        vision = new BerserkUnitType("vision"){{
            health = 980;
            speed = 0.9f;
            rotateSpeed = 2f;
            drag = 0.125f;
            armor = 9f;
            hitSize = 19f;
            allowLegStep = true;
            hovering = false;
            groundLayer = Layer.legUnit - 1f;
            shadowElevation = 0.2f;

            legCount = 4;
            legLength = 10f;
            legForwardScl = 0.8f;
            legMoveSpace = 1.4f;
            legBaseOffset = 2f;

            weapons.add(
            new SnWeapon("vision-gun"){{
                reload = 30f;
                x = 6;
                y = -0.3f;
                alternate = true;
                mirror = true;
                inaccuracy = 2f;
                bullet = SnBullets.mirageGunBullet;
            }}
            );
        }};
        illusion = new BerserkUnitType("illusion"){{
            health = 1400;
            speed = 0.7f;
            rotateSpeed = 2.4f;
            drag = 0.125f;
            hitSize = 29f;
            armor = 14f;
            allowLegStep = true;
            hovering = false;
            groundLayer = Layer.legUnit;
            shadowElevation = 0.4f;

            legCount = 6;
            legLength = 15;
            legForwardScl = 0.8f;
            legMoveSpace = 1.4f;
            legBaseOffset = 4f;

            weapons.add(
            new SnWeapon("illusion-gun"){{
                reload = 40;
                x = 8;
                y = -2;
                alternate = false;
                mirror = true;
                inaccuracy = 3f;
                bullet = new BerserkLaserBulletType(){{
                    hitEffect = despawnEffect = Fx.none;
                    instantDisappear = true;
                    damage = 30;
                    shoot.shots = 4;
                    shoot.shotDelay = 7f;
                    lifetime = 15f;
                    fragBullets = 14;
                    keepVelocity = true;
                    fragBullet = new BasicBulletType(4f, 30f){{
                        lifetime = 13f;
                        keepVelocity = true;
                    }};
                    fragVelocityMin = 0.85f;
                    fragVelocityMax = 1.25f;
                    fragLifeMin = 0.85f;
                    fragLifeMax = 1.25f;
                    fragRandomSpread = 6f;
                }};
            }},
            new SnWeapon("iliusion-lasergun"){{
                reload = 60f;
                bullet = new BerserkLaserBulletType(){{
                    damage = 12;
                    lifetime = 30f;
                }};
            }}
            );
        }};
        soothSayer = new BerserkUnitType("soothSayer"){{
            health = 9700;
            speed = 0.57f;
            rotateSpeed = 2.1f;
            drag = 0.125f;
            hitSize = 38f;
            armor = 20f;
            allowLegStep = true;
            hovering = false;
            groundLayer = Layer.legUnit;
            shadowElevation = 0.6f;

            legCount = 6;
            legMoveSpace = 1f;
            legPairOffset = 3;
            legLength = 25f;
            legExtension = -14;
            legBaseOffset = 9f;
            mechLandShake = 1f;
            legLengthScl = 0.97f;

            legSplashDamage = 37;
            legSplashRange = 30;
            weapons.add(
            new SnWeapon("soothSayer-lasergun"){{
                reload = 75f;
                x = 12;
                y = -3;
                alternate = false;
                mirror = true;
                inaccuracy = 3f;
                bullet = new BerserkLaserBulletType(){
                    {
                        hitEffect = despawnEffect = Fx.none;
                        instantDisappear = true;
                        damage = 120;
                        shoot.shots = 6;
                        shoot.shotDelay = 12f;
                        lifetime = 30f;
                        fragBullets = 8;
                        keepVelocity = true;
                        fragBullet = new BasicBulletType(4f, 50f){{
                            lifetime = 13f;
                            keepVelocity = true;
                        }};
                        fragVelocityMin = 0.85f;
                        fragVelocityMax = 1.25f;
                        fragLifeMin = 0.85f;
                        fragLifeMax = 1.25f;
                        fragRandomSpread = 6f;
                    }
                };
            }},
            new SnWeapon("soothSayer-gun"){{
                reload = 40f;
                x = 12f;
                y = 4f;
                bullet = new BerserkLaserBulletType(){{
                    lifetime = 30;
                    damage = 4f;
                }};
            }}
            );
        }};
        seer = new BerserkUnitType("seer"){{
            health = 25900;
            speed = 0.53f;
            rotateSpeed = 1.8f;
            drag = 0.125f;
            hitSize = 47f;
            armor = 29f;
            allowLegStep = true;
            hovering = true;
            groundLayer = Layer.legUnit;
            shadowElevation = 0.96f;

            legCount = 8;
            legMoveSpace = 0.8f;
            legPairOffset = 3;
            legLength = 60f;
            legExtension = -19;
            legBaseOffset = 8f;
            mechLandShake = 1f;
            legLengthScl = 0.93f;
            rippleScale = 3f;
            legSpeed = 0.19f;

            legSplashDamage = 45;
            legSplashRange = 40;
        }};
        abyssEye = new BerserkUnitType("abyssEye"){{
            health = 70000;
            speed = 0.45f;
            rotateSpeed = 1.5f;
            drag = 0.125f;
            hitSize = 71;
            armor = 30f;
            allowLegStep = true;
            hovering = true;
            groundLayer = Layer.legUnit;
            shadowElevation = 1.1f;

            legCount = 8;
            legMoveSpace = 0.9f;
            legPairOffset = 3;
            legLength = 70f;
            legExtension = -27;
            legBaseOffset = 4f;
            mechLandShake = 4f;
            legLengthScl = 2f;
            rippleScale = 4f;
            legSpeed = 0.3f;

            legSplashDamage = 70;
            legSplashRange = 70;
        }};
        //endregion berserk
        //region wheel
        wheelT1 = new WheelUnitType("wheel-t1"){{
            health = 80;
            speed = 3.5f;
            rotateSpeed = baseRotateSpeed = 2.75f;
            drag = 0.075f;
            weapons.add(new WeaponExt("wheel1-minigun"){{
                reload = 5.5f;
                inaccuracy = 4f;
                rotate = true;
                mirror = false;
                bullet = SnBullets.wheel1Bullet;
                x = y = 0;
            }});
        }};
        wheelT2 = new WheelUnitType("wheel-t2"){{
            health = 420;
            speed = 3.4f;
            rotateSpeed = baseRotateSpeed = 2.66f;
            drag = 0.075f;
            weapons.add(new WeaponExt("wheel2-shotgun"){{
                reload = 48f;
                inaccuracy = 0f;
                rotate = true;
                mirror = false;
                bullet = SnBullets.wheel2Shotgun;
                x = y = 0;
            }});
        }};
        wheelT3 = new WheelUnitType("wheel-t3"){{
            health = 890;
            speed = 3.2f;
            rotateSpeed = baseRotateSpeed = 2.33f;
            drag = 0.075f;
            weapons.add(new WeaponExt("wheel3-burst"){{
                reload = 65f;
                inaccuracy = 1f;
                rotate = true;
                mirror = false;
                bullet = SnBullets.wheel3Burst;
                shootSound = Sounds.shootBig;
                x = y = 0;
            }});
        }};
        wheelT4 = new WheelUnitType("wheel-t4"){{
            health = 6800;
            speed = 3.1f;
            rotateSpeed = baseRotateSpeed = 2.25f;
            drag = 0.075f;
            weapons.add(new WeaponExt("wheel4-shotgun"){{
                reload = 92f;
                rotate = true;
                mirror = false;
                bullet = SnBullets.wheel4Shotgun;
                shootSound = Sounds.shootBig;
                x = y = 0;
            }});
            weapons.add(new WeaponExt("wheel4-burst"){{
                reload = 56f;
                inaccuracy = 3f;
                alternate = true;
                rotate = true;
                bullet = SnBullets.wheel4Artillery;
                shootSound = Sounds.shootBig;
                y = -6;
                x = -3;
            }});
        }};
        wheelT5 = new WheelUnitType("wheel-t5"){{
            health = 19400;
            speed = 3f;
            rotateSpeed = baseRotateSpeed = 2f;
            drag = 0.075f;
            weapons.add(new WeaponExt("wheel5-flame"){{
                reload = 7f;
                rotate = true;
                mirror = false;
                bullet = SnBullets.wheel5Flame;
                shootSound = Sounds.flame;
                x = y = 0;
            }});
            weapons.add(new WeaponExt("wheel5-bullet"){{
                reload = 149f;
                inaccuracy = 1f;
                alternate = true;
                rotate = true;
                faceTarget = true;
                shoot.shots = 9;
                shoot.shotDelay = 5f;
                bullet = SnBullets.wheel5Bullet;
                shootSound = Sounds.shootBig;
                y = -12;
                x = -6;
            }});
        }};
        //there should be a wheelT6 here
        //endregion wheel
        //region freezing
        freezingT1 = new UnitTypeExt("freezing-t1"){{
            controller = u -> new SuicideAI();
            constructor = UnitTypes.pulsar.constructor;
            speed = 1f;
            hitSize = 8f;
            health = 500;
            mechSideSway = 0.25f;
            range = 40f;

            immunities.add(StatusEffects.freezing);

            weapons.add(new SnWeapon(){{
                reload = 20f;
                shootCone = 180f;
                ejectEffect = Fx.freezing;
                shootSound = Sounds.explosion;
                x = shootY = 0f;
                mirror = false;
                bullet = new BombBulletType(0f, 0f, "clear"){{
                    hitEffect = Fx.pulverize;
                    lifetime = 10f;
                    speed = 1f;
                    status = StatusEffects.freezing;
                    splashDamageRadius = 90f;
                    instantDisappear = true;
                    splashDamage = 12f;
                    killShooter = true;
                    hittable = false;
                    collidesAir = true;
                }};
            }});
        }};
        //endregion freezing
        //endregion ground
        //region air
        //region copters
        wind = new CopterUnitType("wind"){{
            health = 140;
            hitSize = 15;
            speed = 3.2f;
            rotateSpeed = 5f;
            accel = 0.04f;
            drag = 0.016f;
//            commandLimit = 3;
            flying = true;
            circleTarget = false;
            range = 130;

            unitFallRotateSpeed = 6f;

            rotors.add(
            new Rotor("rotor-small"){{
                offsetX = 0;
                offsetY = 2;
                rotorRotateSpeed = -27f;
                rotorCount = 2;
            }},
            new Rotor("rotor-small"){{
                offsetX = 0;
                offsetY = 2;
                rotorRotateSpeed = 27f;
                rotorCount = 2;
            }}
            );

            weapons.add(
            new WeaponExt("small-minigun"){{
                rotate = false;
                mirror = true;
                x = 5f;
                y = 2f;
                shoot.shots = 1;
                inaccuracy = 1;
                reload = 5.5f;
                shootSound = Sounds.pew;
                bullet = SnBullets.basicHelicopterGun;
            }});
        }};
        thunder = new CopterUnitType("thunder"){{
            health = 310;
            hitSize = 20;
            speed = 2.9f;
            rotateSpeed = 4.5f;
            accel = 0.04f;
            drag = 0.016f;
//            commandLimit = 3;

            flying = true;
            circleTarget = false;
            range = 145;

            unitFallRotateSpeed = 5.7f;

            rotors.add(
            new Rotor("rotor-medium"){{
                offsetX = 0;
                offsetY = 2;
                rotorRotateSpeed = -27f;
                rotorCount = 2;
            }},
            new Rotor("rotor-medium"){{
                offsetX = 0;
                offsetY = 2;
                rotorRotateSpeed = 27f;
                rotorCount = 2;
            }});

            weapons.add(
            new WeaponExt("missile-mount-small"){{
                rotate = false;
                mirror = true;
                x = 3f;
                y = -2f;
                //spacing = 3f;
                reload = 28f;
                shake = 1f;
                recoil = 3f;
                inaccuracy = 5f;
                velocityRnd = 0.2f;
                shoot.shots = 1;
                shootSound = Sounds.missile;
                bullet = SnBullets.helicopterMissile;
            }},

            new WeaponExt("medium-minigun"){{
                rotate = false;
                mirror = true;
                x = -6f;
                y = 5f;
                shoot.shots = 1;
                inaccuracy = 1;
                reload = 7f;
                shootSound = Sounds.pew;
                bullet = SnBullets.mediumHelicopterGun;
            }});
        }};
        nadir = new CopterUnitType("nadir"){{
            health = 690;
            hitSize = 30;
            speed = 2.6f;
            rotateSpeed = 3.9f;
            accel = 0.08f;
            drag = 0.03f;
//            commandLimit = 4;
            flying = true;
            circleTarget = false;
            range = 145;

            unitFallRotateSpeed = 5f;

            rotors.add(
            new Rotor("rotor-mini"){{
                offsetX = 0;
                offsetY = -9;
                rotorRotateSpeed = -27f;
                rotorCount = 3;
            }},
            new Rotor("rotor-big3"){{
                offsetX = 0;
                offsetY = 8;
                rotorRotateSpeed = 28f;
                rotorCount = 3;
            }}
            );
            weapons.add(
            new WeaponExt("missile-launch"){{
                rotate = false;
                mirror = true;
                x = 9f;
                y = 3f;
                //spacing = 3f;
                reload = 40f;
                shake = 1f;
                recoil = 5f;
                inaccuracy = 5f;
                velocityRnd = 0.2f;
                shoot.shots = 1;
                shootSound = Sounds.missile;
                bullet = SnBullets.clusterRocket;
            }},

            new WeaponExt("small-energy-sphere-generator"){{
                rotate = false;
                mirror = false;
                x = 0f;
                y = 16f;
                layerOffset = -0.01f;
                reload = 25f;
                shake = 1f;
                recoil = 2f;
                inaccuracy = 3f;
                shoot.shots = 1;
                shootSound = Sounds.spark;
                bullet = SnBullets.copterEnergySphere;
            }});
        }};
        halo = new CopterUnitType("halo"){{
            health = 6900;
            hitSize = 40;
            speed = 2.3f;
            rotateSpeed = 3.3f;
            accel = 0.06f;
            drag = 0.04f;
            //commandLimit = 4;

            flying = true;
            circleTarget = false;
            range = 170f;

            unitFallRotateSpeed = 3.7f;

            rotors.add(
            new Rotor("rotor-small2"){{
                offsetX = 0;
                offsetY = -15;
                rotorRotateSpeed = 27f;
                rotorCount = 3;
            }},
            new Rotor("rotor-medium3"){{
                offsetX = -13;
                offsetY = 10;
                rotorRotateSpeed = -27f;
                rotorCount = 4;
            }},
            new Rotor("rotor-medium3"){{
                offsetX = 13;
                offsetY = 10;
                rotorRotateSpeed = 27f;
                rotorCount = 4;
            }}
            );
            weapons.add(
            new WeaponExt("laser-gun"){{
                rotate = true;
                rotateSpeed = 4.2f;
                mirror = true;
                shake = 2f;
                x = -18f;
                y = -4f;
                reload = 20f;
                shoot.shotDelay = 1f;
                shootY = 7f;
                shoot.shots = 1;
                inaccuracy = 3f;
                shootSound = Sounds.laser;
                bullet = SnBullets.laserGun;
            }},
            new WeaponExt("big-rocket-launcher"){{
                rotate = false;
                mirror = true;
                x = 17f;
                y = 17f;
                layerOffset = -0.01f;
                //spacing = 4;
                reload = 40f;
                recoil = 5f;
                shake = 2f;
                ejectEffect = Fx.casing3;
                inaccuracy = 1.3f;
                shoot.shots = 3;
                shootSound = Sounds.bang;
                bullet = SnBullets.bigHelicopterMissile;
            }},
            new WeaponExt("large-salvo"){{
                rotate = false;
                mirror = false;
                shake = 3f;
                x = 0f;
                y = 18f;
                layerOffset = -0.01f;
                shootY = 4f;
                reload = 37f;
                shoot.shotDelay = 4f;
                shoot.shots = 5;
                inaccuracy = 0.5f;
                shootSound = Sounds.shootBig;
                bullet = SnBullets.bigHelicopterGun;
            }});
        }};
        mudflow = new CopterUnitType("mudflow"){{
            health = 19700;
            hitSize = 70;
            speed = 2.1f;
            rotateSpeed = 2.2f;
            accel = 0.06f;
            drag = 0.04f;
            //commandLimit = 5;
            flying = true;
            circleTarget = false;
            range = 175f;

            unitFallRotateSpeed = 3.1f;
            rotors(
            new Rotor("rotor-medium2"){{
                offsetX = -15;
                offsetY = 20;
                rotorRotateSpeed = -27f;
                rotorCount = 3;
            }},
            new Rotor("rotor-medium2"){{
                offsetX = -15;
                offsetY = 20;
                rotorRotateSpeed = 27f;
                rotorCount = 3;
            }},
            new Rotor("rotor-medium2"){{
                offsetX = 15;
                offsetY = 20;
                rotorRotateSpeed = 27f;
                rotorCount = 3;
            }},
            new Rotor("rotor-medium2"){{
                offsetX = 15;
                offsetY = 20;
                rotorRotateSpeed = -27f;
                rotorCount = 3;
            }},
            new Rotor("rotor-big2"){{
                offsetX = 0;
                offsetY = -14;
                rotorRotateSpeed = -27f;
                rotorCount = 4;
            }},
            new Rotor("rotor-big2"){{
                offsetX = 0;
                offsetY = -14;
                rotorRotateSpeed = 27f;
                rotorCount = 4;
            }}
            );
            weapons.add(
            new WeaponExt("large-rocket-launcher"){{
                rotate = false;
                mirror = true;
                x = 9f;
                y = 4f;
                reload = 50f;
                recoil = 5f;
                shake = 2f;
                ejectEffect = Fx.casing3;
                inaccuracy = 9f;
                shoot.shots = 2;
                shoot.shotDelay = 12f;
                shootX = -1f;
                shootSound = Sounds.bang;
                bullet = SnBullets.largeHelicopterMissile;
            }},
            new SnWeapon("big-salvo"){{
                rotate = false;
                mirror = true;
                shake = 3f;
                x = 20f;
                y = 27f;
                reload = 40f;
                shoot.shotDelay = 5f;
                shoot.shots = 2;
                inaccuracy = 0.5f;
                shootSound = Sounds.shootBig;
                bullet = SnBullets.largeHelicopterGun;
            }},
            new SnWeapon("big-salvo"){{
                rotate = false;
                mirror = true;
                shake = 3f;
                x = -25f;
                y = 8f;
                reload = 30f;
                shoot.shotDelay = 5f;
                shoot.shots = 2;
                inaccuracy = 0.5f;
                shootSound = Sounds.shootBig;
                bullet = SnBullets.largeHelicopterGun;
            }},
            new SnWeapon("small-missile-launcher"){{
                rotate = false;
                mirror = false;
                shake = 3f;
                x = 0f;
                y = 35f;
                xRand = 5f;
                layerOffset = -0.01f;
                reload = 20f;
                shoot.shotDelay = 1f;
                shoot.shots = 3;
                inaccuracy = 6f;
                shootSound = Sounds.missile;
                bullet = SnBullets.smallHelicopterMissile;
            }});
        }};
        parhelion = new CopterUnitType("parhelion"){{
            health = 57000;
            hitSize = 90;
            speed = 1.5f;
            rotateSpeed = 1.6f;
            accel = 0.05f;
            drag = 0.04f;
            //commandLimit = 6;
            range = 170f;

            flying = true;
            circleTarget = false;
            unitFallRotateSpeed = 2.6f;

            rotors.add(
            new Rotor("rotor-medium"){{
                offsetX = 0;
                offsetY = -35;
                rotorRotateSpeed = -27f;
                rotorCount = 6;
            }},
            new Rotor("rotor-big"){{
                offsetX = -30;
                offsetY = 23;
                rotorRotateSpeed = -27f;
                rotorCount = 4;
            }},
            new Rotor("rotor-big"){{
                offsetX = 30;
                offsetY = 23;
                rotorRotateSpeed = -27f;
                rotorCount = 4;
            }},
            new Rotor("rotor-giant"){{
                offsetX = 0;
                offsetY = 8;
                rotorRotateSpeed = 26f;
                rotorCount = 3;
            }}
            );

            weapons.addAll(
            new WeaponExt("large-shrapnel-gun"){{
                rotate = true;
                rotateSpeed = 3f;
                mirror = false;
                x = 0f;
                y = 45f;
                reload = 65f;
                recoil = 7f;
                shake = 4f;
                inaccuracy = 1f;
                ejectEffect = Fx.casing1;
                shootSound = Sounds.shotgun;
                bullet = SnBullets.shrapnelCopterGun;
            }},
            new WeaponExt("giant-machine-gun"){{
                rotate = false;
                mirror = false;
                rotateSpeed = 2.5f;
                shake = 4f;
                x = 0f;
                y = 51f;
                layerOffset = -0.01f;
                recoil = 4f;
                reload = 7f;
                shoot.shots = 1;
                inaccuracy = 0.5f;
                shootSound = Sounds.shootBig;
                bullet = SnBullets.giantHelicopterGun;
            }},
            new WeaponExt("giant-rocket-launcher"){{
                rotate = false;
                mirror = true;
                shake = 3f;
                x = 28f;
                y = 52f;
                layerOffset = -0.01f;
                recoil = 3f;
                reload = 55f;
                shoot.shotDelay = 4f;
                shoot.shots = 2;
                inaccuracy = 0.5f;
                shootSound = Sounds.missile;
                bullet = SnBullets.bigClusterRocket;
            }},
            new WeaponExt("medium-energy-sphere-generator"){{
                rotate = true;
                rotateSpeed = 6f;
                mirror = true;
                shake = 2f;
                x = 44f;
                y = 9f;
                recoil = 4f;
                reload = 40f;
                shoot.shotDelay = 1f;
                shoot.shots = 1;
                inaccuracy = 3f;
                shootSound = Sounds.spark;
                bullet = SnBullets.bigCopterEnergySphere;
            }},
            new WeaponExt("medium-energy-sphere-generator"){{
                rotate = true;
                rotateSpeed = 6f;
                mirror = true;
                shake = 2f;
                x = 30f;
                y = 45f;
                reload = 40f;
                shoot.shotDelay = 1f;
                shoot.shots = 1;
                inaccuracy = 3f;
                shootSound = Sounds.spark;
                bullet = SnBullets.bigCopterEnergySphere;
            }});
        }};
        //endregion copters
        //region buffers
        comet = new UnitTypeExt("comet"){{
            health = 150;
            hitSize = 10;
            speed = 3.1f;
            accel = 0.15f;
            drag = 0.1f;

            flying = true;
            circleTarget = false;
            range = 75;

            itemCapacity = 20;
            //commandLimit = 4;
            ammoType = new LiquidAmmoType(Liquids.water, 1);

            controller = u -> ExtinguishAI.wrapper();

            constructor = UnitEntity::create;

            weapons.add(new WeaponExt("sprite"){{
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
        satellite = new UnitTypeExt("satellite"){{
            health = 470;
            hitSize = 17;
            speed = 3f;
            accel = 0.2f;
            drag = 0.15f;

            flying = true;
            circleTarget = false;
            range = 180;

            itemCapacity = 30;
            //commandLimit = 6;

            controller = u -> new HealAI();

            constructor = UnitEntity::create;

            weapons.add(new ChainWeapon("satellite"){{
                damageTick = 0f;
                healTick = 0.8f;
                buildingBuff = 0.5f;

                alternate = false;
                mirror = false;
                rotate = false;
                x = 0;
                shootCone = 2f;
                range = 180;
            }});
        }};
        planet = new UnitTypeExt("planet"){{
            health = 980;
            hitSize = 23;
            speed = 2.9f;
            accel = 0.25f;
            drag = 0.1f;
            engineSize = 3.5f;

            flying = true;
            circleTarget = false;
            range = 310;

            itemCapacity = 60;
            //commandLimit = 6;

            controller = u -> new HealAI();

            constructor = UnitEntity::create;

            weapons.add(new ChainWeapon("planet"){{
                damageTick = 0.9f;
                healTick = 2.6f;
                buildingBuff = 1f;
                maxChainLength = 3;
                alternate = false;
                mirror = false;
                rotate = false;
                x = 0;
                shootCone = 2f;
                range = 310;
            }});
        }};
        star = new UnitTypeExt("star"){{
            health = 5800;
            hitSize = 55;
            speed = 2.7f;
            accel = 0.2f;
            drag = 0.1f;

            flying = true;
            circleTarget = false;
            range = 380;

            engineOffset = 18f;
            engineSize = 4f;

            itemCapacity = 100;
            //commandLimit = 6;

            controller = u -> new FlyingWeaponAI();

            constructor = UnitEntity::create;

            abilities.add(new OverdriveAbility(0.75f, 8 * 60, 13 * Vars.tilesize));

            weapons.add(new WeaponExt("star-gun"){{
                x = 0;
                y = -12;
                mirror = false;
                rotate = true;
                shootCone = 2f;
                inaccuracy = 0f;
                range = 380;
                reload = 287f;
                recoil = 3.5f;
                bullet = SnBullets.starStunBullet;
            }});
            weapons.add(new ChainWeapon("galaxy-weak"){{
                damageTick = 0f;
                buildingBuff = 0f;
                healTick = 6.5f;
                maxChainLength = 3;
                alternate = false;
                mirror = true;
                rotate = true;
                x = 24;
                shootCone = 2f;
                range = 210;
                draw = true;
                laserLayer = Layer.flyingUnit;
            }});
        }};
        galaxy = new UnitTypeExt("galaxy"){{
            health = 20000;
            hitSize = 80;
            speed = 2.4f;
            accel = 0.1f;
            drag = 0.05f;

            flying = true;
            circleTarget = false;
            range = 420;

            engineOffset = 36f;
            engineSize = 7f;

            itemCapacity = 180;
            //commandLimit = 6;

            controller = u -> new FlyingWeaponAI();

            constructor = UnitEntity::create;

            abilities.add(new StatusFieldAbility(SnStatusEffects.starBuff, SnStatusEffects.galaxyDebuff, 180, 8 * 24));

            weapons.add(new WeaponExt("galaxy-segment"){{
                mirror = true;
                alternate = true;
                rotate = true;
                x = 18;
                y = -6;
                reload = 117;
                inaccuracy = 0;
                recoil = 7.5f;
                bullet = SnBullets.galaxyKnockbackBullet;
            }});

            weapons.add(new PointDefenseWeapon("galaxy-weak"){{
                mirror = true;
                alternate = true;
                rotate = true;
                x = 36;
                y = -6;
                reload = 3;
                bullet = new BasicBulletType(0, 80){{
                    maxRange = 420;
                    speed = 420;
                    lifetime = 1;
                }};
            }});
        }};
        universe = new UnitTypeExt("universe"){{
            health = 58000;
            hitSize = 88;
            speed = 1.8f;
            accel = 0.05f;
            drag = 0.066f;

            flying = true;
            circleTarget = false;
            range = 460;

            engineOffset = 33f;
            engineSize = 12f;

            itemCapacity = 270;
            //commandLimit = 6;

            controller = u -> new FlyingWeaponAI();

            constructor = UnitEntity::create;

            abilities.add(new StatusFieldAbility(SnStatusEffects.starBuff, SnStatusEffects.galaxyDebuff, 960, 8 * 32));
            abilities.add(new EffectLowHPAbility(0.15f, 40 * 60, Vars.tilesize * 24, 18 * 60f, SnStatusEffects.stun, SnFx.statusField));

            weapons.add(new WeaponExt("universe-main"){{
                shootSound = Sounds.laserblast;
                chargeSound = Sounds.lasercharge;
                mirror = false;
                rotate = true;
                x = 0;
                y = 16;
                shootX = 0;
                shootY = 8;
                reload = 360f;
                recoil = 0f;
                cooldownTime = 360f;
                shoot.firstShotDelay = 90f;
                parentizeEffects = true;
                shootStatus = SnStatusEffects.universityLaserSlow;
                faceTarget = false;
                bullet = SnBullets.universeLaserBullet;
            }});

            weapons.add(new WeaponExt("galaxy-segment"){{
                alternate = true;
                mirror = true;
                rotate = true;
                x = 24;
                shootCone = 2f;
                range = 210;
                reload = 60;
                bullet = SnBullets.universeEnergySphere;
            }});
        }};
        //endregion buffers
        //endregion air
        //region naval
        //region yellow
        yellowT1 = new SnUnitType("yellow-t1"){{
            speed = 1f;
            rotateSpeed = 3.5f;
            drag = 0.13f;
            hitSize = 10;
            accel = 0.21f;
            health = 180;
            range = 160;
            armor = 5;
            faceTarget = true;
            //commandLimit = 5;
            //commandRadius = 48;
            shadowElevation = -1;
            weapons.addAll(
            /*new WeaponExt("small-autocannon") {
                {
                bullet = SnBullets.smallShell;
                mirror = false;
                rotate = top = true;
                rotateSpeed = 16;
                reload = 0.5f * Time.toSeconds;
                shoot.shots = 1;
                //spacing = 15;
                inaccuracy = 7;
                x = 0f;
                y = 2.3f;
                shootCone = 3;
                cooldownTime = 0.5f * Time.toSeconds;
                ignoreRotation = true;
                shootSound = Sounds.shoot;
            }},*/
            new WeaponExt("small-autocannon"){{
                bullet = SnBullets.smallShell;
                mirror = rotate = top = flipSprite = true;
                rotateSpeed = 16;
                reload = 0.5f * Time.toSeconds;
                shoot.shots = 1;
                //spacing = 15;
                inaccuracy = 7;
                x = -3.9f;
                y = -4.3f;
                shootCone = 3;
                cooldownTime = 0.5f * Time.toSeconds;
                ignoreRotation = true;
                shootSound = Sounds.shoot;
            }}
            );
            constructor = UnitWaterMove::create;
            immunities.add(StatusEffects.wet);
            immunities.add(StatusEffects.freezing);
        }};
        yellowT2 = new SnUnitType("yellow-t2"){{
            speed = 0.9f;
            rotateSpeed = 2.8f;
            drag = 0.15f;
            hitSize = 15;
            accel = 0.21f;
            health = 360;
            range = 205;
            armor = 8;
            faceTarget = true;
            //commandLimit = 5;
            //commandRadius = 48;
            shadowElevation = -1;
            weapons.addAll(
            new WeaponExt("small-art"){{
                bullet = SnBullets.salvoArt;
                mirror = true;
                rotate = true;
                top = true;
                rotateSpeed = 6.7f;
                reload = 45;
                shoot.shots = 3;
                inaccuracy = 5;
                shoot.shotDelay = 0.15f * Time.toSeconds;
                x = 5.1f;
                y = -3;
                xRand = 5;
                shootCone = 1;
                cooldownTime = 0.09f * Time.toSeconds;
                ignoreRotation = true;
                shootSound = Sounds.missile;
            }},
            new WeaponExt(){{
                bullet = SnBullets.smallTorpedo;
                mirror = false;
                rotate = true;
                top = false;
                rotateSpeed = 6.7f;
                reload = 1.3f * Time.toSeconds;
                shoot.shots = 1;
                inaccuracy = 2;
                x = 0;
                y = 0;
                shootCone = 6;
                cooldownTime = 0.25f * Time.toSeconds;
                ignoreRotation = true;
                shootSound = Sounds.missile;
                shootStatusDuration = 30;
            }}
            );
            constructor = UnitWaterMove::create;
            immunities.add(StatusEffects.wet);
            immunities.add(StatusEffects.freezing);
        }};
        yellowT3 = new SnUnitType("yellow-t3"){{
            speed = 0.69f;
            rotateSpeed = 2;
            drag = 0.2f;
            hitSize = 18;
            accel = 1;
            health = 1980;
            range = 350;
            armor = 15;
            faceTarget = true;
            //commandLimit = 5;
            //commandRadius = 48;
            shadowElevation = -1;
            weapons.addAll(
            new WeaponExt("lightthrower"){{
                bullet = SnBullets.lightningBall;
                mirror = false;
                rotate = true;
                top = true;
                rotateSpeed = 2.8f;
                reload = 2.6f * Time.toSeconds;
                shoot.shots = 1;
                //spacing = 2.2f;
                inaccuracy = 5;
                x = 0;
                y = 5.6f;
                shootCone = 2.1f;
                cooldownTime = 5;
                ignoreRotation = true;
                shootStatusDuration = 180;
                shootSound = Sounds.spark;
            }},
            new WeaponExt("launcher"){{
                bullet = SnBullets.trailRocket;
                mirror = true;
                rotate = true;
                top = true;
                rotateSpeed = 3;
                reload = 1.1f * Time.toSeconds;
                shoot.shots = 1;
                //spacing = 3;
                inaccuracy = 6;
                x = 7.2f;
                y = -9;
                shootCone = 2.1f;
                cooldownTime = 5;
                ignoreRotation = true;
                shootStatusDuration = 180;
                shootSound = Sounds.missile;
            }}
            );
            constructor = UnitWaterMove::create;
            immunities.add(StatusEffects.wet);
            immunities.add(StatusEffects.freezing);
        }};
        /*yellowT4 = new SnUnitType("yellow-t4") {{
            speed = 1.5f;
            rotateSpeed = 5.9f;
            drag = 0.75f;
            hitSize = 31;
            accel = 0.7f;
            health = 5400;
            range = 180;
            armor = 900;
            faceTarget = false;
            //commandLimit = 5;
            //commandRadius = 48;
            shadowElevation = -1;
            weapons.add(

            );
            constructor = UnitWaterMove::create;
            immunities.add(StatusEffects.wet);
            immunities.add(StatusEffects.freezing);
        }};*/
        /*yellowT5 = new SnUnitType("yellow-t5") {{
            speed = 1.1f;
            rotateSpeed = 5.4f;
            drag = 1;
            hitSize = 35;
            accel = 0.5f;
            health = 21600;
            range = 160;
            armor = 2000;
            faceTarget = false;
            //commandLimit = 5;
            //commandRadius = 48;
            shadowElevation = -1;
            weapons.add(
            new WeaponExt("big-machinegun") {{
                bullet = SnBullets.bigMachineBullet;
                rotate = true;
                mirror = false;
                top = true;
                rotateSpeed = 30;
                reload = 35;
                shoot.shots = 1;
                //spacing = 15;
                inaccuracy = 5;
                x = 0;
                y = 3;
                shoot.firstShotDelay = 30;
                shootCone = 6;
                cooldownTime = 15;
                ignoreRotation = true;
                shootSound = Sounds.lasershoot;
                shootStatus = StatusEffects.shocked;
            }},
            new WeaponExt("heavy-rocket-launcher") {{
                bullet = SnBullets.heavyRocketArt;
                rotate = true;
                top = true;
                rotateSpeed = 30;
                reload = 45;
                shoot.shots = 1;
                //spacing = 1;
                inaccuracy = 5;
                x = 7;
                y = 0;
                xRand = 5;
                shoot.firstShotDelay = 0;
                shootCone = 1;
                cooldownTime = 5;
                ignoreRotation = true;
                shootSound = Sounds.missile;
                shootStatus = StatusEffects.blasted;
            }},
            new WeaponExt("laser-continuous") {{
                bullet = SnBullets.bigCLaserGun;
                mirror = false;
                rotate = true;
                top = true;
                rotateSpeed = 50;
                reload = 90;
                shoot.shots = 1;
                //spacing = 4.3f;
                inaccuracy = 7.1f;
                x = 0;
                y = -9;
                shoot.firstShotDelay = 0;
                shootCone = 2.2f;
                cooldownTime = 5;
                ignoreRotation = true;
                shootSound = Sounds.missile;
                shootStatus = StatusEffects.blasted;
            }},
            new WeaponExt("torpedo-gun") {{
                bullet = SnBullets.torpedo5;
                mirror = true;
                rotate = true;
                top = false;
                rotateSpeed = 30;
                reload = 35;
                shoot.shots = 1;
                inaccuracy = 2;
                x = 7.3f;
                y = 0;
                shoot.firstShotDelay = 20;
                shootCone = 6;
                cooldownTime = 15;
                ignoreRotation = true;
                shootSound = Sounds.missile;
                shootStatus = StatusEffects.disarmed;
                shootStatusDuration = 30;
            }}
            );
            constructor = UnitWaterMove::create;
            immunities.add(StatusEffects.wet);
            immunities.add(StatusEffects.freezing);
        }};*/
        //there should be a yellowT6 here
        //endregion yellow
        //endregion naval
        //region misc
        router = new UnitTypeExt("router"){
            {
                health = "ROUTER".hashCode();
                speed = 2.85f;
                hitSize = 16;
                flying = true;
                constructor = UnitEntity::create;
                engineSize = 0;
                drawCell = false;
            }

            @Override
            public boolean isHidden(){
                return true;
            }
        };
        //endregion misc
        //region other
        courier = new UncontrollableUnitType("courier"){{
            speed = 3.9f;
            flying = true;
            itemCapacity = 100;
            health = 50;
            hitSize = 4;
            controller = u -> DeliverAI.wrapper();
            //constructor = UnitEntity::create;
        }};
        //region snake
        snake1 = new SegmentUnitType("snake1"){{
            lengthSnake = 5;
            health = 3000f;
            hitSize = 30f;
            speed = 1.4f;
            //commandLimit = 2;
            rotateSpeed = 2f;
            //accel = 0.01f;
            //drag = 0.001f;
            flying = true;
            controller = u -> SegmentAI.wrapper(new FlyingAI());
        }};
        //endregion snake

        engineT1 = new SnUnitType("engine-t1"){{
            health = 140;
            hitSize = 15;
            speed = 3.2f;
            rotateSpeed = 5f;
            accel = 0.04f;
            drag = 0.016f;
            //commandLimit = 3;
            flying = true;
            circleTarget = false;
            range = 130;
            constructor = UnitEntity::create;
            engines.addAll(
            new UnitEngine(){{
                x = 0f;
                y = -5f;
                radius = 3f;
            }},

            new UnitEngine(){{
                x = 0f;
                y = -14f;
                radius = 1f;
            }},

            new UnitEngine(){{
                x = 0f;
                y = -10f;
                radius = 3f;
            }},

            new UnitEngine(){{
                x = 10f;
                y = 5f;
                radius = 2f;
            }});
        }};

        hoverTest = new ParticleUnitType("hover-test"){{
            armor = 8f;
            health = 6000;
            speed = 1.2f;
            rotateSpeed = 2f;
            accel = 0.05f;
            drag = 0.017f;
            lowAltitude = false;
            flying = true;
            circleTarget = true;
            engineOffset = 12f;
            engineSize = 6f;
            faceTarget = false;
            hitSize = 36f;
            payloadCapacity = (3 * 3) * tilePayload;
            buildSpeed = 2.5f;
            buildBeamOffset = 23;
            range = 140f;
            targetAir = false;
            targetFlags = new BlockFlag[]{BlockFlag.battery, BlockFlag.factory, null};

            ammoType = new PowerAmmoType(3000);

            particleEffects.add(
            new ParticleEffect(){{

            }}
            );

            weapons.add(
            new Weapon(){{
                x = y = 0f;
                mirror = false;
                reload = 55f;
                minShootVelocity = 0.01f;

                soundPitchMin = 1f;
                shootSound = Sounds.plasmadrop;

                bullet = new BasicBulletType(){{
                    sprite = "large-bomb";
                    width = height = 120 / 4f;

                    maxRange = 30f;
                    ignoreRotation = true;

                    backColor = Pal.darkestGray;
                    frontColor = Pal.darkestGray;
                    mixColorTo = Pal.darkestGray;

                    hitSound = Sounds.boom;

                    shootCone = 280f;
                    ejectEffect = Fx.hitFlamePlasma;
                    hitShake = 5f;

                    collidesAir = false;

                    lifetime = 85f;

                    despawnEffect = Fx.instBomb;
                    hitEffect = Fx.massiveExplosion;
                    keepVelocity = true;
                    spin = 2f;

                    shrinkX = shrinkY = 0.7f;

                    speed = 0f;
                    collides = false;

                    healPercent = 15f;
                    splashDamage = 220f;
                    splashDamageRadius = 80f;
                }};
            }});
        }};

        //endregion other
        //region hylight
        light = new SnUnitType("light"){
            {
                health = 140;
                hitSize = 15;
                speed = 4f;
                rotateSpeed = 4f;
                accel = 0.04f;
                drag = 0.01f;
                //commandLimit = 5;
                flying = true;
                circleTarget = true;
                range = 130;
                faceTarget = false;
                constructor = UnitEntity::create;
                ammoType = new PowerAmmoType(450);

                weapons.add(new SnWeapon("light-gun"){{
                    reload = 13f;
                    rotate = true;
                    mirror = true;
                    x = 4f;
                    y = -1f;
                    shootSound = Sounds.sap;
                    //spacing = 0f;

                    bullet = new SapBulletType(){{
                        sapStrength = 0.45f;
                        length = 60f;
                        damage = 9f;
                        shootEffect = Fx.shootSmall;
                        hitColor = color = Color.valueOf("ffd37f");
                        despawnEffect = Fx.none;
                        width = 0.35f;
                        lifetime = 12.5f;
                        knockback = -0.25f;
                    }};
                }});
                //to add { underUnit = true;} look at UnitType.useEngineElevation
                engines.addAll(
                new UnitEngine(){{
//                    underUnit = true;
                    x = -6.2f;
                    y = 3.2f;
                    radius = 1.8f;
                }},

                new UnitEngine(){{
//                    underUnit = true;
                    x = 6.2f;
                    y = 3.2f;
                    radius = 1.8f;
                }},

                new UnitEngine(){{
//                    underUnit = true;
                    x = -6.4f;
                    y = -6.4f;
                    radius = 1.8f;
                }},

                new UnitEngine(){{
//                    underUnit = true;
                    x = 6.4f;
                    y = -6.4f;
                    radius = 1.8f;
                }});
            }
        };

        //endregion mod-units
    }

}
