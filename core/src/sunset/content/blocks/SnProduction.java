package sunset.content.blocks;

import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.ctype.ContentList;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.production.AttributeCrafter;
import mindustry.world.blocks.production.SolidPump;
import mindustry.world.draw.DrawRotator;
import mindustry.world.meta.Env;
import sunset.content.SnAttribute;
import sunset.content.SnFx;
import sunset.content.SnItems;
import sunset.content.SnLiquids;
import sunset.type.blocks.DrillItem;
import sunset.world.blocks.production.raw.DRDrill;
import sunset.world.blocks.production.raw.PrecussionDrill;

import static mindustry.type.ItemStack.with;

/** This category is for blocks that produce raw products. (Such as cultivator, drill etc.) */
public class SnProduction implements ContentList{
    public static Block
    //pumps
    mechanicalWaterExtractor,
    //crafters
    advancedCultivator,

    //drills
    electroPneumaticDrill,
    magneticDrill,
    percussionDrill,
    enojieDrill;

    @Override
    public void load(){
        mechanicalWaterExtractor = new SolidPump("mechanical-water-extractor"){{
            requirements(Category.production, with(Items.metaglass, 30, Items.graphite, 30, Items.lead, 30, SnItems.fors, 30));
            result = SnLiquids.burheyna;
            pumpAmount = 0.12f;
            size = 2;
            liquidCapacity = 35f;
            rotateSpeed = 1.5f;
            attribute = SnAttribute.burheyna;
            envRequired |= Env.groundWater;

            consumes.power(2f);
        }};

        //region crafters
        advancedCultivator = new AttributeCrafter("advanced-cultivator"){{
            requirements(Category.production, with(Items.copper, 200, Items.lead, 200, Items.silicon, 180, Items.metaglass, 140, Items.titanium, 170, Items.phaseFabric, 155));
            size = 3;
            health = 990;
            craftEffect = SnFx.cultivatorSmeltSmoke;
            craftTime = 200f;
            drawer = new DrawRotator();
            outputItem = new ItemStack(Items.sporePod, 6);
            itemCapacity = 30;
            liquidCapacity = 40f;

            consumes.liquid(Liquids.water, 0.4f);
            consumes.power(2f);
        }};
        //endregion crafters
        //region drills
        electroPneumaticDrill = new DRDrill("electro-pneumatic-drill"){{
            requirements(Category.production, with(Items.copper, 20, Items.graphite, 15, Items.silicon, 10));
            drillTime = 300;
            size = 2;
            hasPower = true;
            tier = 3;
            consumes.power(0.6f);
            consumes.liquid(Liquids.water, 0.07f).boost();
            m1 = 4;
        }};

        magneticDrill = new DRDrill("magnetic-drill"){{
            requirements(Category.production, with(Items.copper, 70, Items.titanium, 90, Items.silicon, 60, SnItems.fors, 60, SnItems.nobium, 55));
            drillTime = 200;
            size = 4;
            drawRim = true;
            hasPower = true;
            tier = 6;
            updateEffect = Fx.pulverizeRed;
            updateEffectChance = 0.03f;
            drillEffect = Fx.mineHuge;
            rotateSpeed = 7f;
            warmupSpeed = 0.01f;
            itemCapacity = 40;
            liquidBoostIntensity = 1.9f;
            consumes.power(4f);
            consumes.liquid(Liquids.water, 0.13f).boost();
            m1 = 7;
            m2 = -7;
        }};

        percussionDrill = new PrecussionDrill("percussion-drill"){{
            requirements(Category.production, with(Items.copper, 100, Items.silicon, 90, Items.titanium, 90, Items.thorium, 85, SnItems.nobium, 80, SnItems.naturite, 70));
            size = 5;
            hasPower = true;
            powerUse = 5f;
            shakeIntensity = 5f;
            shakeDuration = 10f;
            hardnessDrillMultiplier = 8;
            liquidBoost = 3.86f;
            itemCountMultiplier = 0.5f;
            canDump = true;
            consumes.liquid(Liquids.water, 0.15f).boost();
            drillItems(
            new DrillItem(Items.graphite, 1f),
            new DrillItem(Items.surgeAlloy, 1.25f),
            new DrillItem(SnItems.nobium, 1.6f)
            );
        }};

        enojieDrill = new DRDrill("enojie-drill"){{
            requirements(Category.production, with(Items.copper, 410, Items.silicon, 380, SnItems.enojie, 370, Items.thorium, 200, Items.surgeAlloy, 190));
            drillTime = 150;
            size = 7;
            drawRim = true;
            hasPower = true;
            tier = 6;
            updateEffect = Fx.pulverizeRed;
            updateEffectChance = 0.04f;
            drillEffect = Fx.mineHuge;
            rotateSpeed = 5.0f;
            warmupSpeed = 0.01f;
            itemCapacity = 50;

            liquidBoostIntensity = 1.7f;

            consumes.power(3f);
            consumes.liquid(Liquids.cryofluid, 0.4f).boost();
            m1 = 13;
        }};
        //endregion drills
    }
}
