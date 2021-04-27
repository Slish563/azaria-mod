package azaria.content;

import arc.graphics.*;
import mindustry.ctype.*;
import mindustry.type.*;

public class Items implements ContentList{
    public static Item naturit, nobil, flameid, adamantium, ksealloy, planatrium;

    @Override
    public void load(){
        naturit = new Item("naturit", Color.valueOf("f2e878")){{
            flammability = 0.6f;
            radioactivity = 01f;
            cost = 4f;
            hardness = 5f;
        }};

        nobil = new Item("nobil", Color.valueOf("ffffff")){{
        cost = 4f;
        flammability = 0f;
        radioactivity = 0.2;
        }};

        flameid = new Item("flameid", Color.valueOf("ea8878")){{
            cost = 4f;
            radioactivity = 0.1f;
            flammability = 0.3f;
            hardness = 4f;
        }};

        adamantium = new Item("adamantium", Color.valueOf("fa474ff")){{
            cost = 2f;
        }};

        ksenoalloy = new Item("ksenoalloy", Color.valueOf("99f2c7")){{
            radioactivity = 1.8f;
            flammability = 0f;
            cost = 5f;
        }};
        
        planatrium = new Item("planatrium", Color.valueOf("bc65d4ff")){{
            cost = 4f;
            hardness = 4f;
        }};
    }
}