package sunset.content;

import arc.Core;
import arc.assets.AssetDescriptor;
import arc.assets.loaders.SoundLoader;
import arc.audio.Sound;
import mindustry.Vars;

public class SnSounds {

    public static Sound
        blizzardWind = new Sound()//,
        //empCharge = new Sound(),
        //empShoot = new Sound()
        ;


    public static void load() {
        blizzardWind = loadSound("blizzardWind");
        //empShoot = loadSound("emp-shoot");
        //empCharge = loadSound("emp-charge");
}
        private static Sound loadSound(String soundName){
            if(!Vars.headless){
                String name = "sounds/" + soundName;
                String path = Vars.tree.get(name + ".ogg").exists() ? name + ".ogg" : name + ".mp3";
                
                Sound sound = new Sound();
                
                AssetDescriptor<?> desc = Core.assets.load(path, Sound.class, new SoundLoader.SoundParameter(sound));
                desc.errored = Throwable::printStackTrace;
                return sound;
            }else return new Sound();
    }
}

