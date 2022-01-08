package sunset.content;

import arc.graphics.*;
import arc.util.*;
import mindustry.content.StatusEffects;
import mindustry.content.Weathers;
import mindustry.ctype.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.type.weather.*;
import mindustry.world.meta.*;
import sunset.type.weather.Storm;

public class SnWeathers implements ContentList {
    public static Weather
            blizzard, flood, lightningStorm;

    @Override
    public void load() {
        blizzard = new ParticleWeather("blizzard") {{
            color = noiseColor = Color.valueOf("F3F3F3");
            particleRegion = "particle";
            drawNoise = true;
            useWindVector = true;
            sizeMax = 70f;
            sizeMin = 30f;
            minAlpha = 0f;
            maxAlpha = 0.2f;
            density = 1800f;
            baseSpeed = 5.9f;
            attrs.set(Attribute.light, -0.40f);
            attrs.set(Attribute.water, -0.1f);
            status = SnStatusEffects.frostbite;
            opacityMultiplier = 0.35f;
            force = 0.3f;
            duration = 3f * Time.toMinutes;

            sound = Sounds.windhowl;
            soundVol = 0f;
            soundVolOscMag = 1.5f;
            soundVolOscScl = 1100f;
            soundVolMin = 0.02f;
        }};

        flood = new ParticleWeather("flood") {{
            duration = 10f * Time.toMinutes;
            noiseLayers = 3;
            noiseLayerSclM = 0.4f;
            noiseLayerAlphaM = 0.4f;
            noiseLayerSpeedM = 1f;
            noiseLayerSclM = 0.7f;
            baseSpeed = 0.03f;
            color = noiseColor = Color.grays(0.4f);
            noiseScale = 1000f;
            //TODO make new weather

//            noisePath = fullName("flood");
//            noisePath = "flood";
            noisePath= ((ParticleWeather) Weathers.fog).noisePath;
            drawParticles = false;
            drawNoise = true;
            useWindVector = false;
            xspeed = 3f;
            yspeed = 0.1f;
            attrs.set(Attribute.water, 0.07f);
            opacityMultiplier = 0.30f;

            statusAir = false;
            statusGround = true;
        }};

        lightningStorm = new Storm("lightning-storm") {{
            color = noiseColor = Color.valueOf("CACACA");
            particleRegion = "particle";
            drawNoise = true;
            useWindVector = true;
            sizeMax = 30f;
            sizeMin = 10f;
            minAlpha = 0f;
            maxAlpha = 0.1f;
            density = 1700f;
            baseSpeed = 5.9f;
            attrs.set(Attribute.light, -0.40f);
            attrs.set(Attribute.water, 0.2f);
            status = StatusEffects.wet;
            opacityMultiplier = 0.35f;
            force = 0.4f;
            duration = 3f * Time.toMinutes;

            lightningScl = 40;
            lightningLength = 9;
            lightningDamage = 20f;

            sound = Sounds.rain;
            soundVol = 0.25f;
        }};
    }
}