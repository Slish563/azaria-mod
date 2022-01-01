package sunset.maps.generators;

import arc.graphics.Color;
import arc.math.Angles;
import arc.math.Mathf;
import arc.math.Rand;
import arc.math.geom.*;
import arc.struct.FloatSeq;
import arc.struct.ObjectMap;
import arc.struct.ObjectSet;
import arc.struct.Seq;
import arc.util.*;
import arc.util.noise.*;
import arc.util.noise.Ridged;
import mindustry.ai.Astar;
import mindustry.ai.BaseRegistry.BasePart;
import mindustry.content.Blocks;
import mindustry.game.*;
import mindustry.graphics.g3d.PlanetGrid.Ptile;
import mindustry.maps.generators.*;
import mindustry.type.Sector;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.TileGen;
import mindustry.world.Tiles;
import sunset.content.blocks.SnEnvironment;

import static mindustry.Vars.*;

public class AzariaGenerator extends SnPlanetGenerator{


    public AzariaGenerator(){
         arr = new Block[][]{
        {SnEnvironment.crimsonwater, SnEnvironment.crimsondirt, SnEnvironment.crimsongrass, SnEnvironment.crimsonwater, SnEnvironment.crimsonswamp, SnEnvironment.crimsongrass, Blocks.mud, SnEnvironment.granite, SnEnvironment.granite, Blocks.mud, SnEnvironment.crimsonwater, Blocks.stone, SnEnvironment.granite},
        {SnEnvironment.crimsonwater, SnEnvironment.crimsondirt, SnEnvironment.granite, Blocks.mud, Blocks.mud, SnEnvironment.crimsongrass, Blocks.mud, Blocks.mud,SnEnvironment.crimsondirt, SnEnvironment.crimsonwater, Blocks.stone, Blocks.stone, SnEnvironment.granite},
        {SnEnvironment.crimsonwater, SnEnvironment.crimsondirt, SnEnvironment.crimsongrass, Blocks.mud, SnEnvironment.granite, Blocks.mud, Blocks.mud, Blocks.mud, SnEnvironment.crimsongrass, SnEnvironment.crimsonwater, Blocks.stone, Blocks.stone, Blocks.stone},
        {SnEnvironment.crimsonwater, SnEnvironment.crimsondirt, Blocks.mud, SnEnvironment.crimsonswamp, Blocks.mud, SnEnvironment.granite, SnEnvironment.crimsongrass, Blocks.stone, Blocks.stone, Blocks.stone, SnEnvironment.crimsondirt, SnEnvironment.crimsonswamp, SnEnvironment.crimsonswamp},
        {SnEnvironment.crimsondeepwater, SnEnvironment.crimsonwater, SnEnvironment.granite, SnEnvironment.crimsonswamp, Blocks.mud, Blocks.mud, Blocks.mud, Blocks.mud, SnEnvironment.crimsondirt, SnEnvironment.crimsondirt, SnEnvironment.crimsondirt, SnEnvironment.crimsondirt, SnEnvironment.crimsongrass},
        {SnEnvironment.crimsondeepwater, SnEnvironment.crimsonwater, SnEnvironment.crimsongrass, SnEnvironment.crimsonswamp, Blocks.mud, Blocks.mud, SnEnvironment.crimsonwater, SnEnvironment.granite, SnEnvironment.crimsondirt, SnEnvironment.crimsondirt, SnEnvironment.crimsongrass, SnEnvironment.crimsondirt, Blocks.mud},
        {SnEnvironment.crimsonwater, SnEnvironment.crimsonsandwater, SnEnvironment.crimsongrass, SnEnvironment.crimsonswamp, SnEnvironment.crimsondirt, SnEnvironment.crimsonwater, SnEnvironment.crimsonwater, SnEnvironment.granite, SnEnvironment.crimsonswamp, SnEnvironment.crimsonswamp, Blocks.mud, SnEnvironment.crimsondirt, SnEnvironment.crimsongrass},
        {SnEnvironment.crimsongrass, SnEnvironment.crimsonwater, Blocks.mud, SnEnvironment.granite, SnEnvironment.crimsonwater, SnEnvironment.crimsondirt, SnEnvironment.crimsonwater, Blocks.hotrock, SnEnvironment.granite, SnEnvironment.crimsonswamp, SnEnvironment.crimsondirt, SnEnvironment.crimsonswamp, SnEnvironment.crimsongrass},
        {SnEnvironment.crimsondirt, Blocks.mud, Blocks.mud, Blocks.mud, SnEnvironment.crimsongrass, SnEnvironment.crimsondirt, SnEnvironment.crimsonwater, Blocks.mud, Blocks.mud, Blocks.mud, SnEnvironment.granite, SnEnvironment.crimsonswamp, SnEnvironment.crimsongrass},
        {SnEnvironment.crimsongrass, Blocks.mud, SnEnvironment.granite, SnEnvironment.crimsonsnow, SnEnvironment.crimsonswamp, SnEnvironment.granite, SnEnvironment.crimsondirt, SnEnvironment.crimsondirt, SnEnvironment.crimsonsnow, SnEnvironment.crimsonwater, SnEnvironment.granite, Blocks.mud, Blocks.mud},
        {SnEnvironment.crimsongrass, SnEnvironment.crimsongrass, Blocks.mud, SnEnvironment.granite, SnEnvironment.crimsondirt, SnEnvironment.crimsonsnow, SnEnvironment.granite, SnEnvironment.crimsonsnow, SnEnvironment.crimsonwater, Blocks.mud, SnEnvironment.crimsongrass, SnEnvironment.crimsongrass, Blocks.mud},
        {SnEnvironment.crimsongrass, SnEnvironment.crimsonsnow, Blocks.mud, SnEnvironment.crimsonsnow, SnEnvironment.crimsongrass, SnEnvironment.crimsonsnow, SnEnvironment.crimsongrass, SnEnvironment.crimsonsnow, SnEnvironment.crimsonwater, Blocks.mud, SnEnvironment.crimsonsnow, SnEnvironment.crimsonsnow, Blocks.mud},
        {SnEnvironment.crimsonsnow, SnEnvironment.crimsongrass, SnEnvironment.crimsonsnow, SnEnvironment.crimsonswamp, SnEnvironment.crimsonsnow, SnEnvironment.crimsonsnow, SnEnvironment.crimsondirt, SnEnvironment.crimsonsnow, SnEnvironment.crimsonice, SnEnvironment.crimsonsnow, SnEnvironment.granite, SnEnvironment.granite, SnEnvironment.crimsonsnow}
        };

         dec = ObjectMap.of(
        SnEnvironment.crimsondirt, Blocks.mud,
        SnEnvironment.crimsongrass, SnEnvironment.crimsondirt,
        SnEnvironment.crimsonwater, SnEnvironment.crimsonwater,
        SnEnvironment.crimsongrass, SnEnvironment.crimsongrass
        );

        tars = ObjectMap.of(
        Blocks.mud, Blocks.mud,
        Blocks.stone, Blocks.stone
        );
    }

    @Override
    protected void setupOres(Seq<Block> ores, float poles, float nmag, float scl, float addscl){
        super.setupOres(ores, poles, nmag, scl, addscl);


        if(Simplex.noise3d(seed, 3, 0.5f, scl, sector.tile.v.x + 1, sector.tile.v.y, sector.tile.v.z)*nmag + poles > 0.55f*addscl){
            ores.add(SnEnvironment.oreFors);
        }

        if(Simplex.noise3d(seed, 2, 0.5f, scl, sector.tile.v.x + 1, sector.tile.v.y, sector.tile.v.z)*nmag + poles > 0.8f*addscl){
            ores.add(SnEnvironment.orePlanatrium);
        }
    }


    @Override
    protected void passTiles(int x, int y){

        if(floor == SnEnvironment.crimsonswamp){
            if(Math.abs(0.5f - noise(x - 90, y, 4, 0.8, 65)) > 0.02){
                floor = SnEnvironment.crimsonmoss;
            }
        }
    }

    @Override
    protected void setupTree(){
        block = rand.chance(0.5) ? SnEnvironment.crimsontree : SnEnvironment.crimsontreedead;
    }
}
