package sunset.world.blocks.defense.turrets;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import mindustry.annotations.Annotations.Load;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.meta.Stat;
import sunset.SnVars;
import sunset.graphics.SnPal;
import sunset.utils.Utils;

import static arc.Core.settings;
import static mindustry.Vars.minArmorDamage;

public class SynthesisTurret extends ItemTurret {
    @Load("@-liquid")
    public TextureRegion liquid;
    @Load("@-light")
    public TextureRegion light;
    public float armor;
    public int speed = 1;

    public SynthesisTurret(String name) {
        super(name);
        unitSort = (u, x, y) -> -u.armor;
    }

    @Override
    public void setStats() {
        super.setStats();
        stats.add(Stat.armor, armor);
    }

    @Override
    public void setBars() {
        super.setBars();
        SnVars.settings.registerReloadBarBlock(this, (SynthesisBuild entity) -> new Bar(
                () -> Core.bundle.format("bar.sunset-reload", Utils.stringsFixed(Mathf.clamp(entity.reload / reloadTime) * 100f)),
                () -> entity.team.color,
                () -> Mathf.clamp(entity.reload / reloadTime)
        ));
    }
    public class SynthesisBuild extends ItemTurretBuild {
        @Override
        public void updateTile() {
            super.updateTile();
            //TODO Increase shooting speed if health less or equal 10%.
        }

        @Override
        public void draw() {
            Draw.rect(baseRegion, x, y);
            Draw.color();
            Draw.z(Layer.turret);
            tr2.trns(rotation, -recoil);
            Drawf.shadow(region, x + tr2.x - elevation, y + tr2.y - elevation, rotation - 90);
            drawer.get(this);
            if (this.health <= maxHealth * 100 / 10) Draw.rect(light, x + tr2.x, y + tr2.y, rotation - 90);
            if (heatRegion != Core.atlas.find("error")) {
                heatDrawer.get(this);
            }
            if (size > 2) Drawf.liquid(liquid, x + tr2.x, y + tr2.y, liquids.total() / liquidCapacity, SnPal.synthesis1);
        }

        @Override
        public void drawSelect() {
            Drawf.dashCircle(x, y, range, Pal.heal);
            if (minRange > 0) Drawf.dashCircle(x, y, minRange, Pal.health);
        }

        @Override
        public float handleDamage(float amount) {
            return Math.max(amount - armor, minArmorDamage * amount); //TODO make my own variant of armor applying (% and etc...)
        }

        //TODO make a visual display of the armor
    }
}
