package sunset.type;

import acontent.world.meta.AStats;
import arc.scene.ui.layout.Table;
import mindustry.*;
import mindustry.content.TechTree;
import arc.struct.BoolSeq;
import arc.struct.FloatSeq;
import arc.struct.Seq;
import arc.util.Log;
import mindustry.entities.units.StatusEntry;
import mindustry.gen.Unit;
import mindustry.type.StatusEffect;
import mma.*;
import sunset.entities.units.StackableStatusEntry;
import sunset.world.meta.values.StackableStatusEffectValue;

import static mindustry.Vars.state;

import java.lang.reflect.Field;

/** Накладываемый статус-эффект. Имеет различную силу на
 * каждой стадии наложения, как правило возрастающую.
 * @implNote Если длина массива, определяющего тот или иной параметр
 * статус-эффекта не совпадает с параметром {@code maxStacks}, то
 * используются параметры из обычного {@link StatusEffect}.
 * Не предназначен для реактивных эффектов (смысл им стакаться, если они моментально исчезают?)
 * @apiNote Для установки статус эффекта используйте метод {@code apply(Unit, float)}. 
 * Вместо {@code update(Unit, float)} переопределяйте метод {@code updateStack(Unit, float, int)}.
 * Вместо {@code draw(Unit)} переопределяйте метод {@code drawStack(Unit, int)}.*/
public class StackableStatusEffect extends StatusEffect {
    /** Максимальное количество наложений. */
    public int maxStacks = 1;
    /** Множители наносимого урона для каждого уровня стака. */
    public FloatSeq damageMultipliers = new FloatSeq();
    /** Множители здоровья для каждого уровня стака. */
    public FloatSeq healthMultipliers = new FloatSeq();
    /** Множители скорости передвижения для каждого уровня стака. */
    public FloatSeq speedMultipliers = new FloatSeq();
    /** Множители длительности перезарядки для каждого уровня стака. */
    public FloatSeq reloadMultipliers = new FloatSeq();
    /** Множители скорости строительства для каждого уровня стака. */
    public FloatSeq buildSpeedMultipliers = new FloatSeq();
    /** Множители ускорения для каждого уровня стака. */
    public FloatSeq dragMultipliers = new FloatSeq();
    /** Количества урона при взаимодействии с усиляющими эффектами для каждого уровня стака. */
    public FloatSeq transitionDamages = new FloatSeq();
    /** Неспособность стрелять для каждого уровня стака. */
    public BoolSeq disarmedArray = new BoolSeq();
    /** Урон за тик для каждого уровня стака. */
    public FloatSeq damageArray = new FloatSeq();
    /** Список отдельных наложение эффекта. */
    public Seq<StatusEffect> stacks;

    private StackableStatusEffectValue displayer;
    private final AStats aStats=new AStats(){
        @Override
        public void display(Table table) {
            displayer.display(table);
        }
    };

    public StackableStatusEffect(String name) {
        super(name);
        stats=aStats.copy(stats);
    }

    /** @implNote при инициализации создаёт статус-эффект для каждого уровня наложения. */
    @Override
    public void init() {
        super.init();
        stacks = new Seq<>(maxStacks);
        StackableStatusEffect base = this;
        Vars.content.setCurrentMod(ModVars.modInfo);
        for(int i = 0; i < maxStacks; i++) {
            final int finalI = i;
            stacks.add(new StatusEffect(name + finalI){{
                //параметры эффекта
                this.damageMultiplier = (base.damageMultipliers.size == base.maxStacks) ?
                base.damageMultipliers.get(finalI) :
                base.damageMultiplier;
                this.healthMultiplier = (base.healthMultipliers.size == base.maxStacks) ?
                base.healthMultipliers.get(finalI) :
                base.healthMultiplier;
                this.speedMultiplier = (base.speedMultipliers.size == base.maxStacks) ?
                base.speedMultipliers.get(finalI) :
                base.speedMultiplier;
                this.reloadMultiplier = (base.reloadMultipliers.size == base.maxStacks) ?
                base.reloadMultipliers.get(finalI) :
                base.reloadMultiplier;
                this.buildSpeedMultiplier = (base.buildSpeedMultipliers.size == base.maxStacks) ?
                base.buildSpeedMultipliers.get(finalI) :
                base.buildSpeedMultiplier;
                this.dragMultiplier = (base.dragMultipliers.size == base.maxStacks) ?
                base.dragMultipliers.get(finalI) :
                base.dragMultiplier;
                this.transitionDamage = (base.transitionDamages.size == base.maxStacks) ?
                base.transitionDamages.get(finalI) :
                base.transitionDamage;
                this.disarm = (base.disarmedArray.size == base.maxStacks) ?
                base.disarmedArray.get(finalI) :
                base.disarm;
                this.damage = (base.damageArray.size == base.maxStacks) ?
                base.damageArray.get(finalI) :
                base.damage;
                base.opposites.each(this::opposite);
                base.affinities.each(e -> this.affinity(e, transitions.get(e)));
                // визуальные параметры
                this.localizedName = base.localizedName;
                this.description = base.description;
                this.details = base.details;
            }
                @Override
                public boolean isHidden() { return true; }
                // перенаправляем вызовы различных методов статус эффектов
                // в вызовы методов базового (данного) статус-эффекта
                @Override
                public void update(Unit unit, float time) {
                    super.update(unit, time);
                    updateStack(unit, time, finalI+1);
                }
                @Override
                public void draw(Unit unit) {
                    drawStack(unit, finalI+1);
                }
                @Override
                public boolean reactsWith(StatusEffect effect) {
                    return base.reactsWith(effect);
                }

                @Override
                public boolean applyTransition(Unit unit, StatusEffect to, StatusEntry entry, float time){
                    return base.applyTransition(unit, to, entry, time);
                }

                @Override
                public String emoji() { return base.emoji(); }
                @Override
                public TechTree.TechNode node() { return base.node(); }
                /*
                @Override
                public void display(Table table) { base.display(table); }*/
            });
        }
        Vars.content.setCurrentMod(null);
        displayer = new StackableStatusEffectValue(this);
    }

    /** Вызывается для обработки юнитов, на которых наложен эффект. Передаёт
     *  информацию о оставшемся времени и степени наложения. */
    public void updateStack(Unit unit, float time, int stackCount) { }
    /** Вызывается для отрисовки юнитов, на которых наложен эффект. Передаёт
     *  информацию о оставшемся времени. */
    public void drawStack(Unit unit, int stackCount) { }

    /** Применяет эффект к юниту, если эффект ещё не был установлен, иначе
     *  увеличивает количество наложений данного эффекта на 1. */
    public void apply(Unit unit, float duration) {
        // Приходится использовать рефлексию, так как существуют несколько классов, описывающих
        // юнитов (как минимум - UnitEntity и UnitMech), и эти классы не имеют никакого общего
        // интерфейса, который бы имел поле statuses (ну или я слепой).
        Field fieldStatuses;
        Seq<StatusEntry> statuses = null;
        try {
            fieldStatuses = unit.getClass().getField("statuses");            
            fieldStatuses.setAccessible(true);
            statuses = (Seq<StatusEntry>)fieldStatuses.get(unit);
        } catch (NoSuchFieldException e) {
            // Поля может и не оказаться, тогда просто считаем, что юнит не поддерживает эффекты
            return;
        } catch (Throwable e) {
            Log.err(e);
        }
        boolean foundStandard = statuses.remove(e -> e.effect == this);
        StackableStatusEntry prev = (StackableStatusEntry)statuses.find(e -> 
            (e instanceof StackableStatusEntry) && ((StackableStatusEntry)e).baseEffect == this);
        if(prev == null) {
            applyEffect(statuses, unit, duration, foundStandard ? 2 : 1);
        } else {
            prev.stack();
        }
    }
    protected void applyEffect(Seq<StatusEntry> statuses, Unit unit, float duration, int stackCount) {
        if (unit.isImmune(this)) return;
        if (state.isCampaign()) {
            unlock();
        }
        if (statuses.size > 0) {
            for (int i = 0; i < statuses.size; i++) {
                StatusEntry entry = statuses.get(i);
                if (entry.effect.reactsWith(this)) {
                    /*StatusEntry.tmp.effect = entry.effect;
                    entry.effect.applyTransition(unit, this,entry, entry.time);
                    entry.time = StatusEntry.tmp.time;
                    if (StatusEntry.tmp.effect != entry.effect) {
                        entry.effect = StatusEntry.tmp.effect;
                    }*/
                    return;
                }
            }
        }
        StackableStatusEntry entry = new StackableStatusEntry(this, stackCount, duration);
        statuses.add(entry);
    }
}
