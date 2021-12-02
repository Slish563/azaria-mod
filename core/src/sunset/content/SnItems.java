package sunset.content;

import arc.graphics.*;
import mindustry.ctype.*;
import mindustry.type.*;

public class SnItems implements ContentList {
    public static Item
    
    fors, naturite, nobium, reneubite, planatrium, enojie, flameid, coldent;

    @Override
    public void load() {

        fors = new Item("fors", Color.valueOf("F3A39F")) {{
            cost = 2f;
            hardness = 3;
        }};

        naturite = new Item("naturite", Color.valueOf("F2E878")) {{
            flammability = 0.6f;
            radioactivity = 0.1f;
            cost = 3f;
        }};

        nobium = new Item("nobium", Color.valueOf("FFFFFF")) {{
            cost = 4f;
            radioactivity = 0.1f;
        }};

        reneubite = new Item("reneubite", Color.valueOf("6173FF")) {{
            cost = 4f;
            flammability = 0.8f;
            explosiveness = 0.7f;
        }};
        
        planatrium = new Item("planatrium", Color.valueOf("D57CED")) {{
            cost = 5f;
            hardness = 4;
            explosiveness = 0.5f;
            radioactivity = 1f;
        }};

        enojie = new Item("enojie", Color.valueOf("99f2C7")) {{
            radioactivity = 0.8f;
            explosiveness = 0.2f;
            cost = 6f;
        }};
        

        flameid = new Item("flameid", Color.valueOf("EA8878")) {{
            cost = 4f;
            flammability = 0.6f;
            hardness = 4;
        }};
        
        coldent = new Item("coldent", Color.valueOf("BAF4F5FF")) {{
            cost = 4f;
            hardness = 4;
        }};
    }
}