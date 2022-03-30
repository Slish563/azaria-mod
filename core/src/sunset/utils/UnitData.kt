/**
 * Allows you to store information about a specific unit, usually for AI work.
 */
@file:JvmName("UnitData")
@file:JvmMultifileClass

package sunset.utils

import arc.*
import arc.func.*
import arc.struct.*
import mindustry.game.*
import mindustry.game.EventType.*
import mindustry.gen.*
import mindustry.gen.Unit
import sunset.utils.kotlin.*

object UnitData {
    private val data = IntMap<IntMap<Any>>()

    @JvmStatic
    fun <T> dataKey(def: Prov<T?>?): DataKey<T> {
        return DataKey(def)
    }

    @JvmStatic
    fun <T> getData(unit: Unit?, key: DataKey<*>, def: Prov<T?>): T? {
        if (unit == null || invalidUnit(unit)) return null;
        @Suppress("UNCHECKED_CAST")
        return (data[unit.id, { IntMap() }] as IntMap<T>)[key.id, def]
    }

    @JvmStatic
    fun <T> setData(unit: Unit?, key: DataKey<T>, value: T?) {
        if (unit == null || invalidUnit(unit)) return
        data[unit.id, { IntMap() }][key.id] = value
    }

    @JvmStatic
    fun init() {
        Events.run(EventType.Trigger.update) {
            val keys = data.keys()
            while (keys.hasNext) {
                val id = keys.next()
                if (invalidUnit(Groups.unit.getByID(id))) data.remove(id)
            }
        }
        Events.on(WorldLoadEvent::class.java) { data.clear() }
        Events.on(UnitDestroyEvent::class.java) { e: UnitDestroyEvent -> data.remove(e.unit.id) }
    }

    fun validUnit(unit: Unit?): Boolean {
        return !invalidUnit(unit)
    }

    fun invalidUnit(unit: Unit?): Boolean {
        return unit == null || !unit.isValid || unit.isNull || Groups.unit.getByID(unit.id) == null
    }

    private var dataKeysAmount = 0

    class DataKey<T> internal constructor(def: Prov<T?>?) {
        internal val id: Int = dataKeysAmount++
        private val def: Prov<T?> = def ?: Prov { null }

        operator fun get(unit: Unit?): T? {
            return getData(unit, this, def)
        }

        operator fun set(unit: Unit?, value: T?) {
            setData(unit, this, value)
        }

    }
}

