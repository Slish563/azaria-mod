package sunset.content.blocks;

import mindustry.content.Items;
import mindustry.ctype.ContentList;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.meta.BuildVisibility;
import sunset.content.SnItems;
import sunset.world.blocks.laser.*;

public class SnLasers implements ContentList {
    public static Block laserNode, laserSource, laserCrafter, laserGenerator, laserSuppressor;
    @Override
    public void load() {
        laserNode = new LaserNode("laser-node"){{
            consumesLaser = true;
            outputsLaser = true;
            //todo
            localizedName = "Laser node";
            requirements(Category.distribution, ItemStack.with(Items.copper, 1));
            size = 2;
            buildVisibility = BuildVisibility.sandboxOnly;

        }};
        laserSource = new LaserSource("laser-source"){{
            outputsLaser = true;
            consumesLaser = false;
            overheats = false;
            //todo
            localizedName = "Laser source";
            requirements(Category.distribution, ItemStack.with(Items.copper, 1));
            size = 1;
            buildVisibility = BuildVisibility.sandboxOnly;
        }};
        laserCrafter = new LaserCrafter("laser-crafter"){{
            //todo
            localizedName = "Laser crafter";
            requirements(Category.distribution, ItemStack.with(Items.copper, 1));
            size = 1;
            buildVisibility = BuildVisibility.sandboxOnly;

            consumesLaser = true;
            outputsLaser = false;
            consumes.item(Items.copper, 1);
            laserConsumption = 1.0f;

            outputItem = new ItemStack(Items.graphite, 1);
        }};
        laserGenerator = new LaserGenerator("laser-starter"){{
            //todo
            localizedName = "Laser starter";
            requirements(Category.distribution, ItemStack.with(Items.copper, 1));
            size = 2;
            maxLinks = 8;
            buildVisibility = BuildVisibility.sandboxOnly;

            consumesLaser = false;
            outputsLaser = true;
            consumesPower = true;
            overheats = false;

            consumes.power(30f);
        }};
        laserSuppressor = new LaserConsumer("laser-suppressor"){{
            //todo
            localizedName = "Laser suppressor";
            requirements(Category.distribution, ItemStack.with(Items.copper, 1));
            size = 2;
            buildVisibility = BuildVisibility.sandboxOnly;

            overheats = false;
        }};
    }
}
