package sunset.content;

import arc.func.Floatc2;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Angles;
import arc.math.Interp;
import arc.math.Mathf;
import arc.math.Rand;
import arc.math.geom.Position;
import arc.math.geom.Vec2;
import arc.struct.Seq;
import arc.util.Time;
import arc.util.Tmp;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.entities.effect.MultiEffect;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import sunset.graphics.Drawm;
import sunset.graphics.SnPal;
import sunset.utils.test.DrawFunc;
import sunset.world.blocks.defense.turrets.SynthesisTurret;

import static arc.graphics.g2d.Draw.alpha;
import static arc.graphics.g2d.Draw.color;
import static arc.graphics.g2d.Lines.*;
import static arc.math.Angles.randLenVectors;
import static mindustry.Vars.tilesize;

public class SnFx {
    private static final Rand rand = new Rand();

    public static final Effect
    //region unorganized
    enojieCraft = new Effect(55, e -> {
        randLenVectors(e.id, 6, 4f + e.fin() * 8f, (x, y) -> {
            color(Pal.heal);
            Fill.square(e.x + x, e.y + y, e.fout() + 0.9f, 45);
        });

        color(Pal.heal);
        for (int i = 0; i < 4; i++) {
            Drawf.tri(e.x, e.y, 4f, 28f * e.fout(), e.rotation + 90f * i + e.foutpow() * 140f);
        }

        color();
        for (int i = 0; i < 4; i++) {
            Drawf.tri(e.x, e.y, 2f, 13f * e.fout(), e.rotation + 90f * i + e.foutpow() * 140f);
        }
    }),

    enojieBurn = new Effect(40, e -> {
        randLenVectors(e.id, 5, 3f + e.fin() * 5f, (x, y) -> {
            color(Color.valueOf("CFEDD4"), Color.lime, e.fin());
            Fill.square(e.x + x, e.y + y, e.fout());
        });
    }),

    crystalyze = new Effect(40, e -> {
        randLenVectors(e.id, 5, 3f + e.fin() * 8f, (x, y) -> {
            color(Pal.lightTrail);
            Fill.square(e.x + x, e.y + y, e.fout() * 2f + 0.5f, 45);
        });
    }),

    crystalyzeSmall = new Effect(30, e -> {
        randLenVectors(e.id, 3, e.fin() * 5f, (x, y) -> {
            color(Pal.lightTrail);
            Fill.square(e.x + x, e.y + y, e.fout() + 0.5f, 45);
        });
    }),

    modSmokeCloud = new Effect(90, e -> {
        randLenVectors(e.id, 6, 4f + e.fin() * 1.3f, 30, 30f, (x, y) -> {
            color(SnPal.bGray);
            alpha((0.5f - Math.abs(e.fin() - 0.5f)) * 2f);
            Fill.square(e.x + x, e.y + y, 0.5f + e.fout() * 4f);
        });
    }),

    modPlasticBurn = new Effect(45, e -> {
        randLenVectors(e.id, 7, 2.8f + e.fin() * 5f, 25, 30f, (x, y) -> {
            color((SnPal.gGray), Color.gray, e.fin());
            Fill.square(e.x + x, e.y + y, e.fout() * 3.1f, 45);
        });
    }),

    liquidTransfer = new Effect(12f, e -> {
        if(!(e.data instanceof Position to)) return;
        Tmp.v1.set(e.x, e.y).interpolate(Tmp.v2.set(to), e.fin(), Interp.pow3)
        .add(Tmp.v2.sub(e.x, e.y).nor().rotate90(1).scl(Mathf.randomSeedRange(e.id, 1f) * e.fslope() * 10f));
        float x = Tmp.v1.x, y = Tmp.v1.y;
        float size = 1f;

        stroke(e.fslope() * 2f * size, e.color);
        circle(x, y, e.fslope() * 2f * size);

        color(e.color.cpy().mul(0.8f));
        Fill.circle(x, y, e.fslope() * 1.5f * size);
    }),

    weaverSmeltsmoke = new Effect(20f, e -> {
        randLenVectors(e.id, 7, 6.8f + e.fin() * 5f, (x, y) -> {
            color(Color.valueOf("C78D04"), e.color, e.fin());
            Fill.square(e.x + x, e.y + y, 0.5f + e.fout() * 2f, 45);
        });
    }),

    cultivatorSmeltSmoke = new Effect(20f, e -> {
        randLenVectors(e.id, 7, 6.8f + e.fin() * 5f, (x, y) -> {
            color(Color.valueOf("5841A6"), e.color, e.fin());
            Fill.square(e.x + x, e.y + y, 0.5f + e.fout() * 2f, 45);
        });
    }),

    giardSynthesizerCraft = new Effect(40f, e -> {
        color(SnPal.giardGas);

        randLenVectors(e.id, 4, 3f + e.fin() * 7f, (x, y) -> {
            Fill.circle(e.x + x, e.y + y, 0.5f + e.fslope() * 1.8f);
        });
    }),

    galebardLaserCharge = new Effect(35f, e -> {
        color(Pal.meltdownHit);

        randLenVectors(e.id, 5, 3f + 9f * e.fout(), e.rotation, 100f, (x, y) -> {
            lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 6f + 4f);
        });
    }),

    galebardLaserChargeBegin = new Effect(160f, e -> {
        color(Pal.meltdownHit);
        Fill.circle(e.x, e.y, e.fin() * 8f);

        color();
        Fill.circle(e.x, e.y, e.fin() * 7f);
    }),

    galebardShoot = new Effect(70f, e -> {
        color(Pal.meltdownHit);
        stroke(e.fout() * 7f);
        circle(e.x, e.y, e.fin() * 50f);
        //Lines.square(e.x, e.y, e.fin() * 40f, 65);

        for (int i : Mathf.signs) {
            Drawf.tri(e.x, e.y, 14.0f * e.fout(), 80f, e.rotation + 90f * i);
            Drawf.tri(e.x, e.y, 13.0f * e.fout(), 50f, e.rotation + 20f * i);
            Drawf.tri(e.x, e.y, 7.0f * e.fout(), 70f, e.rotation + 12f * i);
            Drawf.tri(e.x, e.y, 8.0f * e.fout(), 110f, e.rotation + 125f * i);
        }
    }),

    smallEnergySphereCharge = new Effect(17f, e -> {
        color(SnPal.redBombBack);
        Fill.circle(e.x, e.y, e.fin() * 2f);
    }),

    mediumEnergySphereCharge = new Effect(19f, e -> {
        color(SnPal.redBombBack);
        Fill.circle(e.x, e.y, e.fin() * 4f);
    }),
    
    mediumEnergySphereHit = new Effect(20f, 50f, e -> {
        color(SnPal.redBomb);
        stroke(e.fout() * 2f);
        
        color(SnPal.redBomb);
        
        for (int i : Mathf.signs) {
            Drawf.tri(e.x, e.y, 9f * e.fout(), 20f, e.rotation + 80f * i);
            Drawf.tri(e.x, e.y, 8f * e.fout(), 20f, e.rotation + 110f * i);
            
        }
        Drawf.light(e.x, e.y, 1.8f, SnPal.redBomb, e.fout());
    }),

    copterShoot = new Effect(12, e -> {
        color(SnPal.copterLaser, e.fin());
        stroke(e.fout() * 1.2f + 0.5f);

        randLenVectors(e.id, 7, 25f * e.finpow(), e.rotation, 50f, (x, y) -> {
            lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fin() * 5f + 2f);
        });
    }),

    spineShoot = new Effect(12, e -> {
        color(Pal.bulletYellow);
        stroke(e.fout() * 1.2f + 0.5f);

        randLenVectors(e.id, 7, 25f * e.finpow(), e.rotation, 50f, (x, y) -> {
            lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fin() * 5f + 2f);
        });
    }),

    redBomb = new Effect(30f, 70f, e -> {
        color(SnPal.redBomb);
        stroke(e.fout() * 2f);
        float circleRad = 3f + e.finpow() * 40f;
        circle(e.x, e.y, circleRad);

        color(SnPal.redBomb);
        for (int i = 0; i < 6; i++) {
            Drawf.tri(e.x, e.y, 6f, 100f * e.fout(), i * 60);
        }

        color();
        for (int i = 0; i < 6; i++) {
            Drawf.tri(e.x, e.y, 3f, 35f * e.fout(), i * 60);
        }

        Drawf.light(e.x, e.y, circleRad * 1.5f, SnPal.redBomb, e.fout());
    }),

    heavyFlame = new Effect(64f, 80f, e -> {
        Draw.color(Pal.lightFlame, Pal.darkFlame, Color.gray, e.fin());

        randLenVectors(e.id, 8, e.finpow() * 60f, e.rotation, 10f, (x, y) -> {
            Fill.circle(e.x + x * 2, e.y + y * 2, 0.65f + e.fout() * 1.9f);
        });
    }),

    typhoonShootLiquid = new Effect(15f, 80f, e -> {
        color(e.color);

        randLenVectors(e.id, 2, e.finpow() * 15f, e.rotation, 11f, (x, y) -> {
            Fill.circle(e.x + x, e.y + y, 0.5f + e.fout() * 2.5f);
        });
    }),

    viscous = new Effect(85f, e -> {
        color(Color.valueOf("721A1A"));
        alpha(Mathf.clamp(e.fin() * 3f));

        Fill.circle(e.x, e.y, e.fout());
    }),

    tridentCharge = new Effect(210, e -> {
        final Color c1 = Color.valueOf("eaecff"), c2 = Color.valueOf("ffeaec");
        float r = Mathf.degRad * e.rotation;
        float cx = e.x - Mathf.cos(r) * 12f;
        float cy = e.y - Mathf.sin(r) * 12f;
        Draw.z(Layer.block);
        Drawm.energySphere(e.id, e.time, e.fin(), 8, Mathf.PI / 120f, Mathf.PI / 30f, 8f, 1.5f, c1, c2, cx, cy);
    }),
    tridentHit = new Effect(30, e -> {
        color(Pal.plastaniumFront);

        e.scaled(7, i -> {
            stroke(3f * i.fout());
            circle(e.x, e.y, 3f + i.fin() * 24f);
        });

        color(Color.gray);

        randLenVectors(e.id, 7, 2f + 28f * e.finpow(), (x, y) -> {
            Fill.circle(e.x + x, e.y + y, 112f);
        });

        color(Pal.surge);
        stroke(e.fout());

        randLenVectors(e.id + 1, 4, 1f + 25f * e.finpow(), (x, y) -> {
            lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 1f + e.fout() * 3f);
        });

        Drawf.light(e.x, e.y, 50f, Pal.shield, 0.8f * e.fout());
    }),

    sniperTrail = new Effect(12f, e -> {
        float scale = e.data(), fin1 = Math.min(1, e.fin() * 1.5f), fout1 = 1 - Math.max(0, e.fin() - 0.6666f) * 3;
        Vec2 front = new Vec2(0, 32 * scale).setAngle(e.rotation).add(e.x, e.y);
        Vec2 left = new Vec2(0, 5 * fout1 * scale).setAngle(e.rotation + fin1 * 60f).add(e.x, e.y);
        Vec2 right = new Vec2(0, 5 * fout1 * scale).setAngle(e.rotation - fin1 * 60f).add(e.x, e.y);
        Draw.z(Layer.bullet);
        Draw.color(e.color);
        Fill.quad(front.x, front.y, left.x, left.y, e.x, e.y, right.x, right.y);
    }),

    shootWheel5Flame = new Effect(40f, 140f, e -> {
        color(Pal.lightFlame, Pal.darkFlame, Color.gray, e.fin());

        randLenVectors(e.id, 24, e.finpow() * 140f, e.rotation, 10f, (x, y) -> {
            Fill.circle(e.x + x, e.y + y, 1.35f + e.fout() * 2.35f);
        });
    }),

    missilePoint = new Effect(1,18* tilesize, (e) -> {
        float y = e.y;
        float x = e.x;
        float time = e.time;
        float rotation = e.rotation;
        Color color = e.color;
        Draw.draw(Layer.flyingUnit,()->{
            Runnable draw=()->{
                circle(x, y, rotation);
                circle(x, y, 20f);
                float angle = time / 75 * Mathf.radiansToDegrees-180;
                Vec2 p1 = Tmp.v1.trns(angle, 18);
                Vec2 p2 = Tmp.v2.trns(angle, 24);
                for (int i = 0; i < 4; i++) {
                    Lines.line(x + p1.x, y + p1.y, x + p2.x, y + p2.y);
                    p1.rotate90(1);
                    p2.rotate90(1);
                }
            };
            stroke(4, Pal.gray);
            draw.run();
            stroke(2, color);
            draw.run();
        });


    }),

    stunTrail = new Effect(40, e -> {
        Draw.z(Layer.flyingUnit + 1);
        Draw.color(Color.white);
        Fill.circle(e.x, e.y, 3 * e.fout());
        Draw.color();
    }),

    stunExplode = new Effect(60 * 2.25f, e -> {
        Draw.z(Layer.flyingUnit + 1);
        Draw.color(Color.white, Mathf.pow(e.fout(), 3f));
        Fill.circle(e.x, e.y, 59f);
        Draw.color();
    }),

    lightningFast = new Effect(6, Fx.lightning.renderer),

    missileLaunchSmall = new Effect(75, e -> {
        Draw.z(Layer.groundUnit);
        final int[] p = {0};
        Angles.randLenVectors(e.id, 16, 16 * e.fin(), 52 * Mathf.pow(e.fin(), 0.55f), (x, y) -> {
            float a = Mathf.pow(e.fout(), 0.35f);
            if (p[0] == 0) Draw.color(Pal.lightishGray, a);
            if (p[0] == 1) Draw.color(Pal.gray, a);
            if (p[0] == 2) Draw.color(Pal.darkestGray, a);
            Fill.circle(e.x + x, e.y + y, 0.75f + 3.25f * Mathf.pow(e.fin(), 0.7f));
            p[0] = (p[0] + 1) % 3;
        });
    }),

    missileLaunchMedium = new Effect(90, e -> {
        Draw.z(Layer.groundUnit);
        final int[] p = {0};
        Angles.randLenVectors(e.id, 24, 28 * e.fin(), 72 * Mathf.pow(e.fin(), 0.55f), (x, y) -> {
            float a = Mathf.pow(e.fout(), 0.5f);
            if (p[0] == 0) Draw.color(Pal.lightishGray, a);
            if (p[0] == 1) Draw.color(Pal.gray, a);
            if (p[0] == 2) Draw.color(Pal.darkestGray, a);
            Fill.circle(e.x + x, e.y + y, 1.5f + 4.5f * Mathf.pow(e.fin(), 0.7f));
            p[0] = (p[0] + 1) % 3;
        });
    }),

    missileLaunchLarge = new Effect(105, e -> {
        Draw.z(Layer.groundUnit);
        final int[] p = {0};
        Angles.randLenVectors(e.id, 32, 36 * e.fin(), 86 * Mathf.pow(e.fin(), 0.55f), (x, y) -> {
            float a = Mathf.pow(e.fout(), 0.7f);
            if (p[0] == 0) Draw.color(Pal.lightishGray, a);
            if (p[0] == 1) Draw.color(Pal.gray, a);
            if (p[0] == 2) Draw.color(Pal.darkestGray, a);
            Fill.circle(e.x + x, e.y + y, 2f + 6f * Mathf.pow(e.fin(), 0.7f));
            p[0] = (p[0] + 1) % 3;
        });
    }),

    sunriseMissileExplosion = new Effect(45, e -> {
        color(Pal.missileYellow);

        e.scaled(8, i -> {
            stroke(4f * i.fout());
            circle(e.x, e.y, 5f + i.fin() * 35f);
        });

        color(SnPal.aGray);

        randLenVectors(e.id, 20, 4f + 50f * e.finpow(), (x, y) -> {
            Fill.circle(e.x + x, e.y + y, e.fout() * 5f + 0.8f);
            Fill.circle(e.x + x, e.y + y, e.fout() * 3f + 0.3f);
        });

        color(SnPal.aGray);

        randLenVectors(e.id, 10, 2f + 35f * e.finpow(), (x, y) -> {
            Fill.circle(e.x + x, e.y + y, e.fout() * 4f + 0.4f);
        });

        color(Pal.missileYellowBack);
        stroke(e.fout());

        randLenVectors(e.id + 1, 6, 1f + 39f * e.finpow(), (x, y) -> {
            lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 2f + e.fout() * 6f);
        });
    }),

    sparkMissileExplosion = new Effect(55, e -> {
        color(Pal.missileYellow);

        e.scaled(9, i -> {
            stroke(5f * i.fout());
            circle(e.x, e.y, 6f + i.fin() * 40f);
        });

        color(SnPal.aGray);

        randLenVectors(e.id, 24, 5f + 60f * e.finpow(), (x, y) -> {
            Fill.circle(e.x + x, e.y + y, e.fout() * 6f + 1f);
            Fill.circle(e.x + x, e.y + y, e.fout() * 4f + 0.6f);
        });

        color(SnPal.aGray);

        randLenVectors(e.id, 10, 3f + 35f * e.finpow(), (x, y) -> {
            Fill.circle(e.x + x, e.y + y, e.fout() * 5f + 0.7f);
        });

        color(Pal.missileYellowBack);
        stroke(e.fout());

        randLenVectors(e.id + 1, 8, 2f + 45f * e.finpow(), (x, y) -> {
            lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 3f + e.fout() * 7f);
        });
    }),

    dissectorMissileExplosion = new Effect(77, e -> {
        color(Pal.missileYellow);

        e.scaled(11, i -> {
            stroke(7f * i.fout());
            circle(e.x, e.y, 8f + i.fin() * 50f);
        });

        color(SnPal.aGray);

        randLenVectors(e.id, 35, 8f + 70f * e.finpow(), (x, y) -> {
            Fill.circle(e.x + x, e.y + y, e.fout() * 8f + 2f);
            Fill.circle(e.x + x, e.y + y, e.fout() * 6f + 1f);
        });

        color(SnPal.aGray);

        randLenVectors(e.id, 14, 4f + 40f * e.finpow(), (x, y) -> {
            Fill.circle(e.x + x, e.y + y, e.fout() * 7f + 0.9f);
        });

        color(Pal.bulletYellow);

        randLenVectors(e.id, 7, 3f + 35f * e.finpow(), (x, y) -> {
            Fill.circle(e.x + x, e.y + y, e.fout() * 5f + 0.8f);
        });


        color(Pal.missileYellowBack);
        stroke(e.fout());

        randLenVectors(e.id + 4, 13, 4f + 45f * e.finpow(), (x, y) -> {
            lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 5f + e.fout() * 10f);
        });
    }),

    artMissileExplosion = new Effect(65, e -> {
        color(Pal.missileYellow);

        e.scaled(10, i -> {
            stroke(6f * i.fout());
            circle(e.x, e.y, 7f + i.fin() * 45f);
        });

        color(SnPal.aGray);

        randLenVectors(e.id, 30, 7f + 65f * e.finpow(), (x, y) -> {
            Fill.circle(e.x + x, e.y + y, e.fout() * 7f + 1.3f);
            Fill.circle(e.x + x, e.y + y, e.fout() * 5f + 0.7f);
        });

        color(SnPal.aGray);

        randLenVectors(e.id, 10, 3f + 35f * e.finpow(), (x, y) -> {
            Fill.circle(e.x + x, e.y + y, e.fout() * 6f + 0.9f);
        });

        color(Pal.bulletYellow);

        randLenVectors(e.id, 5, 2f + 30f * e.finpow(), (x, y) -> {
            Fill.circle(e.x + x, e.y + y, e.fout() * 4f + 0.6f);
        });

        color(Pal.missileYellowBack);
        stroke(e.fout());

        randLenVectors(e.id + 3, 10, 3f + 40f * e.finpow(), (x, y) -> {
            lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 4f + e.fout() * 8f);
        });
    }),

    bigRocketTrail = new Effect(36f, 78f, e -> {
        color(Pal.lightPyraFlame, Color.lightGray, e.fin() * e.fin());

        randLenVectors(e.id, 9, 1.8f + e.finpow() * 36, e.rotation + 180, 16f, (x, y) -> {
            Fill.circle(e.x + x, e.y + y, 0.40f + e.fout() * 2f);
        });
    }),

    aimMissileTrail = new Effect(30f, 78f, e -> {
        color(SnPal.nobiumBulletBack, Color.lightGray, Color.white,  e.fin() * e.fin());

        randLenVectors(e.id, 8, 1.4f + e.finpow() * 32, e.rotation + 180, 12f, (x, y) -> {
            Fill.circle(e.x + x, e.y + y, 0.37f + e.fout() * 2f);
        });
    }),

    torpedoTrail = new Effect(30, e -> {
        color(Color.lightGray);
        randLenVectors(e.id, 15, 2 + e.fin() * 5, (x, y) -> {
            Fill.circle(e.x + x, e.y + y, e.fin() * 1.9f);
        });
    }),

    galaxyMainHit = new Effect(45, e -> {
        Draw.color(Pal.surge);
        Draw.alpha(e.foutpow());
        Draw.z(Layer.effect);

        Lines.circle(e.x, e.y, 12);
        Lines.circle(e.x, e.y, 8);
        Fill.circle(e.x, e.y, 4);
    }),

    galaxyMainTrail = new Effect(45, e -> {
        Draw.color(Pal.surge);
        Draw.alpha(e.foutpow());
        Draw.z(Layer.bullet);

        Fill.poly(e.x, e.y, 4, 4, e.rotation);
    }),

    univerityLaserCharge = new Effect(90, e -> {
        rand.setSeed(e.id);
        float sum = 0;
        Vec2 pos = new Vec2(), tmp = new Vec2();
        for(int i = 0; i < 32; i++) {
            float rnd = rand.nextFloat();
            Draw.color(Pal.surge);
            tmp.set(0, sum * 21).setAngle(e.rotation);
            pos.set(e.x, e.y).add(tmp.scl(e.foutpow()));
            float len = (rnd*2-1)*e.fin()*21;
            tmp.set(0, len).setAngle(e.rotation + (len > 0 ? 90 : -90));
            pos.add(tmp.scl(e.fout()));
            Fill.circle(pos.x, pos.y, 18 * e.finpow());
            sum += rnd;
        }
    }),
    //endregion
    //region special
    empHit = new Effect(35, e -> {
        randLenVectors(e.id, 6, 4f + e.fin() * 8f, (x, y) -> {
            Draw.color(Color.valueOf("7FFFD4"), Color.valueOf("32D0DC"), e.fin());
            float circleRad = 2f + e.fin() * 10f;
            Lines.circle(e.x, e.y, circleRad);
        });
    }),
    hitReneubiteBullet = new Effect(14, e -> {
        color(Color.white, SnPal.renBlast1, e.fin());

        e.scaled(8f, s -> {
            stroke(0.6f + s.fout());
            Lines.circle(e.x, e.y, s.fin() * 8f);
        });

        stroke(0.6f + e.fout());

        randLenVectors(e.id, 6, e.fin() * 16f, (x, y) -> {
            float ang = Mathf.angle(x, y);
            lineAngle(e.x + x, e.y + y, ang, e.fout() * 4 + 2f);
        });

        Drawf.light(e.x, e.y, 20f, SnPal.renBlast2, 0.6f * e.fout());
    }),
    //endregion unorganized

    //region green turrets
    greenInstTrail = new Effect(30, e -> {
        for (int i = 0; i < 2; i++) {
            color(i == 0 ? SnPal.synthesis1 : SnPal.synthesis2);

            float m = i == 0 ? 1f : 0.5f;

            float rot = e.rotation + 180f;
            float w = 15f * e.fout() * m;
            Drawf.tri(e.x, e.y, w, (30f + Mathf.randomSeedRange(e.id, 15f)) * m, rot);
            Drawf.tri(e.x, e.y, w, 10f * m, rot + 180f);
        }

        Drawf.light(e.x, e.y, 60f, Pal.bulletYellowBack, 0.6f * e.fout());
    }),//temporary unused
    plasmaShot = new Effect(26, e -> {//TODO tune
        color(SnPal.plasma1);
        float length = !(e.data instanceof Float) ? 70f : (Float)e.data;
        randLenVectors(e.id, 7, length, e.rotation, 0f, (x, y) -> {
            lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fout() * 9f);
        });
    }),//temporary unused
    plasmaHit = new Effect(8, e -> {//TODO tune
        color(SnPal.plasma1, SnPal.plasma2, e.fin());
        stroke(0.5f + e.fout());
        circle(e.x, e.y, e.fin() * 5f);
    }),//temporary unused

    blockShieldBreak = new Effect(35, e -> {
        if(!(e.data instanceof SynthesisTurret.SynthesisBuild)) return;
        SynthesisTurret.SynthesisBuild build = e.data();

        float radius = build.block.size * build.block.size * 1.3f;

        e.scaled(16f, c -> {
            color(Pal.shield);
            stroke(c.fout() * 2f + 0.1f);

            randLenVectors(e.id, (int)(radius * 1.2f), radius / 2f + c.finpow() * radius * 1.25f, (x, y) -> lineAngle(c.x + x, c.y + y, Mathf.angle(x, y), c.fout() * 5 + 1f));
        });

        color(Pal.shield, e.fout());
        stroke(e.fout());
        Lines.circle(e.x, e.y, radius);
    }),
    //endregion green turrets

    laserArtHit = new Effect(20f, 50f, e -> {
        color(Pal.meltdownHit);
        stroke(e.fout() * 2);
        color(Pal.meltdownHit);
        for (int i = 0; i < 2; i++) {
            Drawf.tri(e.x, e.y, 1f, 40f * e.fout(), i * 50);
        }

        color();
        for (int i = 0; i < 7; i++) {
            Drawf.tri(e.x, e.y, 3f, 20f * e.fout(), i * 50);
        }

        Drawf.light(e.x, e.y, 5, Pal.meltdownHit, e.fout());
    }),//fanatic

    //region yellow ships
    acTrail = new Effect(30, e -> {
        /*color(SnPal.yellowTrailBack);
        Fill.circle(e.x, e.y, e.rotation * e.fout());*/
        color(SnPal.yellowTrail);
        Fill.circle(e.x, e.y, e.rotation * e.fout());
    }),
    //endregion yellow ships

    //region turrets 360
    redFlame = new Effect(20f, 95f, e -> {
        Draw.color(SnPal.redfire1, SnPal.redfire2, Pal.lightPyraFlame, e.fin());

        randLenVectors(e.id, 8, e.finpow() * 60f, e.rotation, 11f, (x, y) -> {
            Fill.circle(e.x + x * 2, e.y + y * 2, 0.9f + e.fout() * 1.9f);
        });
    }),
    redFlameHit = new Effect(14, e -> {
        color(SnPal.redfire1, SnPal.redfire2, e.fin());
        stroke(0.5f + e.fout());

        randLenVectors(e.id, 2, 1f + e.fin() * 15f, e.rotation, 50f, (x, y) -> {
            float ang = Mathf.angle(x, y);
            lineAngle(e.x + x, e.y + y, ang, e.fout() * 3 + 1f);
        });
    }),
    thoriumExplosion = new Effect(30, e -> {
        e.scaled(7, i -> {
            stroke(3f * i.fout());
            circle(e.x, e.y, 3f + i.fin() * 10f);
        });

        color(Color.gray);

        randLenVectors(e.id, 6, 2f + 19f * e.finpow(), (x, y) -> {
            Fill.circle(e.x + x, e.y + y, e.fout() * 3f + 0.5f);
            Fill.circle(e.x + x / 2f, e.y + y / 2f, e.fout());
        });

        color(Color.valueOf("F9A3C7"), Color.valueOf("F9A3C7"), Color.gray, e.fin());
        stroke(1.5f * e.fout());

        randLenVectors(e.id + 1, 8, 1f + 23f * e.finpow(), (x, y) -> {
            lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 1f + e.fout() * 3f);
        });
    }),
    forsExplosion = new Effect(30, e -> {
        e.scaled(7, i -> {
            stroke(3f * i.fout());
            circle(e.x, e.y, 3f + i.fin() * 10f);
        });

        color(Color.gray);
         randLenVectors(e.id, 6, 2f + 19f * e.finpow(), (x, y) -> {
             Fill.circle(e.x + x, e.y + y, e.fout() * 3f + 0.5f);
             Fill.circle(e.x + x / 2f, e.y + y / 2f, e.fout());
         });

         color(Color.valueOf("F3A39F"), Color.valueOf("F3A39F"), Color.gray, e.fin());
         stroke(1.5f * e.fout());

         randLenVectors(e.id + 1, 8, 1f + 23f * e.finpow(), (x, y) -> {
             lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 1f + e.fout() * 3f);
         });
    }),
    //endregion turrets 360

    //region tests
    unused1 = new Effect(30f, 65f, e -> {
        color(SnPal.copterBomb);
        stroke(e.fout() * 2f);
        float squareRad = 2f + e.finpow() * 35f;
        float circleRad = 2f + e.finpow() * 40f;
        // Lines.square(e.x, e.y, e.fin() * 60.0F, 90 * e.rotation);

        for(int i = 0; i < 2; ++i){
            Drawf.tri(e.x, e.y, e.fout() * 5, e.fout() * 70, e.rotation - 190 + (25 * i) - e.fin());
        };

        circle(e.x, e.y, circleRad);

        color(SnPal.copterBomb);
        for (int i = 0; i < 4; i++) {
            Drawf.tri(e.x, e.y, 5f, 70f * e.fout(), i * 90);
        }

        color();
        for (int i = 0; i < 4; i++) {
            Drawf.tri(e.x, e.y, 3f, 30f * e.fout(), i * 90);
        }

        Drawf.light(e.x, e.y, squareRad * 1.5f, SnPal.copterBomb, e.fout());
    }),
    unused2 = new Effect(70f, 65f, e -> {
                color(SnPal.copterBomb);
                stroke(e.fout() * 2f);

                // Lines.square(e.x, e.y, e.fin() * 60.0F, 90 * e.rotation);
                Lines.square(e.x, e.y, e.fin() * 15f,  Time.time % 360f * Time.delta * 10f);
            }),
    blastgenerate1 = new MultiEffect(new Effect(40f, 600, e -> {
                color(Pal.sapBullet);
                stroke(e.fout() * 3.7f);
                circle(e.x, e.y, e.fin(Interp.pow3Out) * 240 + 15);
                rand.setSeed(e.id);
                randLenVectors(e.id, 12, 8 + 60 * e.fin(Interp.pow5Out), (x, y) -> Fill.circle(e.x + x, e.y + y, e.fout(Interp.circleIn) * (6f + rand.random(6f))));
                Drawf.light(e.x, e.y, e.fout() * 320f, Pal.sapBullet, 0.7f);
            }), circleOut(Pal.sapBullet, 120f)),
    blastgenerate2 = new Effect(40f, 600, e -> {
        color(Pal.sapBullet);
        stroke(e.fout() * 3.7f);
        circle(e.x, e.y, e.fin(Interp.pow3Out) * 240 + 15);
        rand.setSeed(e.id);
        randLenVectors(e.id, 12, 8 + 60 * e.fin(Interp.pow5Out), (x, y) -> Fill.circle(e.x + x, e.y + y, e.fout(Interp.circleIn) * (6f + rand.random(6f))));
        Drawf.light(e.x, e.y, e.fout() * 320f, Pal.sapBullet, 0.7f);
    }),
    //endregion tests

    //region unused
    modFormSmoke = new Effect(50, e -> {
        randLenVectors(e.id, 8, 6f + e.fin() * 8f, (x, y) -> {
            color(Pal.plasticSmoke, Color.lightGray, e.fin());
            Fill.rect(e.x + x, e.y + y, 0.2f + e.fout() * 2f, 45);
        });
    }),
    hitBlue = new Effect(50f, 100f, e -> {
        float rad = 40f;
        e.scaled(7f, b -> {
            color(SnPal.blueBullet, b.fout());
            Fill.circle(e.x, e.y, rad);
        });

        color(SnPal.blueBullet);
        stroke(e.fout() * 3f);
        circle(e.x, e.y, rad);

        int points = 15;
        float offset = Mathf.randomSeed(e.id, 360f);
        for(int i = 0; i < points; i++){
            float angle = i* 360f / points + offset;
                Drawf.tri(e.x + Angles.trnsx(angle, rad), e.y + Angles.trnsy(angle, rad), 3f, 40f * e.fout(), angle);
        }

        Fill.circle(e.x, e.y, 11f * e.fout());
        color();
        Fill.circle(e.x, e.y, 4f * e.fout());
        Drawf.light(e.x, e.y, rad * 1.6f, SnPal.blueBullet, e.fout());
    }),
    helicopterFlame = new Effect(65f, 84f, e -> {
        Draw.color(Pal.lightFlame, Pal.darkFlame, Color.gray, e.fin());

        randLenVectors(e.id, 8, e.finpow() * 64f, e.rotation, 10f, (x, y) -> {
            Fill.circle(e.x + x * 2, e.y + y * 4, 0.70f + e.fout() * 2.0f);
        });
    }),
    stuff = new Effect(75, e -> {
        float s = (float) e.data;
        float f = s * 2 * Mathf.sin(Mathf.pow(e.fin(), 0.5f) * Mathf.PI2 * 4);
        float sin = Mathf.sinDeg(e.rotation + 90) * f;
        float cos = Mathf.cosDeg(e.rotation + 90) * f;
        Draw.color(e.color, e.fout());
        Fill.circle(e.x + cos, e.y + sin, s * e.fout());
        Fill.circle(e.x - cos, e.y - sin, s * e.fout());
    }),
    statusField = new Effect(30, e -> {
        Draw.z(Layer.flyingUnit);
        float val = Mathf.pow(e.fin(), 0.3f);
        Color c = Pal.boostFrom.cpy().lerp(Pal.boostTo, e.fout());
        Draw.color(c, Mathf.pow(e.fout(), 2f));
        Fill.circle(e.x, e.y, e.rotation * val);
        stroke(1.5f, Pal.boostTo);
        circle(e.x, e.y, e.rotation * val);
    }),
    berserkLaserHitSmall = new Effect(20, e -> {
        Draw.color(Color.valueOf("CCCDDA"));
        Floatc2 floatc2 = (v, v1) -> Fill.poly(e.x + v, e.y + v1, 4, 1.5f + e.fout() * 2f);
        Angles.randLenVectors(e.id, 4, e.finpow() * 20, e.rotation, 360, floatc2);
        Draw.color(Color.valueOf("FFFFFF"));
        Floatc2 floatc21 = (v, v1) -> Lines.lineAngle(e.x + v, e.y + v1, Mathf.angle(v, v1), e.fout() * 1.5f);
        Angles.randLenVectors(e.id, 4, e.finpow() * 20, e.rotation, 360, floatc21);
    });
    //endregion unused

    //region energy sphere utils
    public static void lightning(float x1, float y1, float x2, float y2, Color c, int iterations, float rndScale, Effect e) {
        Seq<Vec2> lines = new Seq<>();
        lines.add(new Vec2(x1, y1));
        lines.add(new Vec2(x2, y2));
        for (int i = 0; i < iterations; i++) {
            for (int j = 0; j < lines.size - 1; j += 2) {
                Vec2 v1 = lines.get(j), v2 = lines.get(j + 1);
                float ang = (Angles.angle(v1.x, v1.y, v2.x, v2.y) + 90f) * Mathf.degRad;
                float sin = Mathf.sin(ang), cos = Mathf.cos(ang);
                Vec2 v = new Vec2((v1.x + v2.x) / 2, ((v1.y + v2.y) / 2));
                v.x += Mathf.random(rndScale) * sin;
                v.y += Mathf.random(rndScale) * cos;
                lines.insert(j + 1, v);
            }
        }
        e.at(x1, y1, 0f, c, lines);

    }

    //testing
    public static Effect circleOut(Color color, float range){
        return new Effect(Mathf.clamp(range / 2, 45f, 360f), range * 1.5f, e -> {
            rand.setSeed(e.id);

            Draw.color(Color.white, color, e.fin() + 0.6f);
            float circleRad = e.fin(Interp.circleOut) * range;
            Lines.stroke(Mathf.clamp(range / 24, 4, 20) * e.fout());
            Lines.circle(e.x, e.y, circleRad);
            for(int i = 0; i < Mathf.clamp(range / 12, 9, 60); i++){
                Tmp.v1.set(1, 0).setToRandomDirection(rand).scl(circleRad);
                DrawFunc.tri(e.x + Tmp.v1.x, e.y + Tmp.v1.y, rand.random(circleRad / 16, circleRad / 12) * e.fout(), rand.random(circleRad / 4, circleRad / 1.5f) * (1 + e.fin()) / 2, Tmp.v1.angle() - 180);
            }
        });
    }
}
