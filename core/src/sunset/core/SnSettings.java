package sunset.core;

import arc.ApplicationListener;
import arc.Core;
import arc.Events;
import arc.func.Func;
import arc.scene.Group;
import arc.scene.ui.layout.Table;
import arc.struct.ObjectMap;
import arc.struct.OrderedMap;
import mindustry.Vars;
import mindustry.game.EventType;
import mindustry.gen.Building;
import mindustry.ui.Bar;
import mindustry.ui.Styles;
import mindustry.ui.dialogs.BaseDialog;
import mindustry.ui.dialogs.SettingsMenuDialog.SettingsTable;
import mindustry.world.Block;
;
;

import java.lang.reflect.Field;

import static arc.Core.bundle;
import static arc.Core.settings;
import static mindustry.Vars.ui;

public class SnSettings implements ApplicationListener {
    public SettingsTable snSettings;
//    public ObjectMap<Couple<Block, OrderedMap<String, Func<Building, Bar>>>, Func<Building, Bar>> reloadBarBlock = new ObjectMap<>();

    public SnSettings() {
    }

    public <T extends Building> void registerReloadBarBlock(Block block, Func<T, Bar> prov) {


    }

    public boolean reloadBar() {
        return settings.getBool("sn-reloadbar", false);
    }

    public void reloadBar(boolean reloadBar) {
        settings.put("sn-reloadbar", reloadBar);
        updateReloadBars();
    }

    public boolean advancedShadows() {
        return settings.getBool("sn-advancedShadows", false);
    }

    public void advancedShadows(boolean advancedShadows) {
        settings.put("sn-advancedShadows", advancedShadows);
    }

    public void updateReloadBars() {
        boolean bar = reloadBar();
        final String barName = "reload";

    }

    public void init() {
        if (!Vars.headless){
            updateReloadBars();
            ui.settings.game.checkPref("sn-reloadbar",false,this::reloadBar);
            ui.settings.graphics.checkPref("sn-advancedshadows",false,this::advancedShadows);
        }
    }

}
