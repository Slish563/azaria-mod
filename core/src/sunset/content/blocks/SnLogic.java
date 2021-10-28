package sunset.content.blocks;

import mindustry.Vars;
import mindustry.content.Items;
import mindustry.ctype.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.logic.*;
import sunset.content.SnItems;

import static mindustry.type.ItemStack.*;

public class SnLogic implements ContentList{
    public static Block

    naturitelogicProcessor;

    @Override
    public void load(){
    naturitelogicProcessor = new LogicBlock("naturitelogic-logic-processor"){{
        instructionsPerTick = 13;
        range = 25.0f* Vars.tilesize;
        size = 1;
        requirements(Category.logic, with(Items.lead, 370, Items.silicon, 140, Items.graphite, 120, SnItems.naturite, 70));
    }};
}
}