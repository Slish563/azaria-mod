package sunset.content.blocks.defense;

import arc.util.Time;
import mindustry.Vars;
import mindustry.content.Items;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.defense.ForceProjector;
import sunset.content.SnItems;
import sunset.world.blocks.defense.RepairStation;
import sunset.world.blocks.defense.projectors.DeflectorProjector;

import static mindustry.type.ItemStack.with;

public class SnProjectors {
    public static Block
    hugeRestoringDome, forceDome, deflectorProjector,
    testStand;

    public static void load() {

        hugeRestoringDome = new RepairStation("huge-restoring-dome"){{
            requirements(Category.effect, with(Items.lead, 560, Items.titanium, 255, Items.silicon, 140, SnItems.nobium, 100));
            consumePower(4.4f);
            size = 5;
            reload = 5f * Time.toSeconds;
            range = 30f * Vars.tilesize;
            healPercent = 25f;
            repairHealth = 250f;
            phaseBoost = 15f;
            health = 80 * size * size;
            consumeItem(SnItems.nobium).boost();
        }};

        forceDome = new ForceProjector("force-dome") {{
            requirements(Category.effect, with(Items.titanium, 600, Items.thorium, 480, Items.silicon, 300, SnItems.naturite, 250, SnItems.nobium, 240, SnItems.enojie, 210));
            size = 5;
            radius = 220f;
            shieldHealth = 2100f;
            cooldownNormal = 2f;
            cooldownLiquid = 3f;
            cooldownBrokenBase = 1.1f;

            consumeItems(with(SnItems.enojie, 2, SnItems.nobium, 2, Items.phaseFabric, 2));
            consumePower(15f);
        }};

        deflectorProjector = new DeflectorProjector("deflector-projector") {{
            requirements(Category.effect, with(Items.silicon, 1200, Items.titanium, 2500, Items.thorium, 1300, Items.phaseFabric, 900, Items.surgeAlloy, 1000, SnItems.enojie, 850));
            size = 3;
            health = 900;
            radius = 140;
            phaseRadiusBoost = 90;
            phaseShieldBoost = 1200;
            shieldHealth = 2500;
            cooldownNormal = 2.1f;
            cooldownLiquid = 3.3f;
            cooldownBrokenBase = 1f;
            consumeItems(with(Items.phaseFabric, 3, SnItems.coldent, 5));
            consumePower(20f);
        }};
    }
}
