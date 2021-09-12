package sunset.world.draw;

import arc.Core;
import arc.math.Interp;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import mindustry.world.Block;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.draw.DrawBlock;

public class DrawAngleRotator extends DrawBlock {
    protected TextureRegion rotatorRegion;
    protected TextureRegion top;
    protected final float angle;
    public DrawAngleRotator(float angle) {
        this.angle = angle;
    }
    @Override
    public void draw(GenericCrafter.GenericCrafterBuild entity) {
        Draw.rect(entity.block.region, entity.x, entity.y);
        Draw.rect(rotatorRegion, entity.x, entity.y, angle * Interp.pow3Out.apply(entity.progress));
        Draw.rect(top, entity.x, entity.y);
    }

    @Override
    public void load(Block block) {
        rotatorRegion = Core.atlas.find(block.name + "-rotator");
        top = Core.atlas.find(block.name + "-top");
    }

    @Override
    public TextureRegion[] icons(Block block) { return new TextureRegion[]{block.region, rotatorRegion, top}; }
}