package sunset.ai;

import arc.math.Mathf;
import mindustry.entities.Units;
import mindustry.gen.*;

/** AI, которое преследует повреждённые союзные боевые единицы, если таковые есть. */
public class HealAI extends FlyingUnitWeaponAI {
    @Override
    public void updateMovement() {
        Unit u = Units.closest(unit.team, unit.x, unit.y,
                Float.MAX_VALUE, un -> un != unit && un.damaged(),
                (unit1, x, y) -> Mathf.pow(unit1.health / unit1.maxHealth, 2f));
        if(u != null) {
            moveTo(u, unit.range());
            return;
        }
        super.updateMovement();
    }

    protected void moveTo(Posc target, float length){
        if(target == null) return;
        vec.set(target).sub(unit);
        unit.rotation(vec.angle());
        float scl = vec.len() < length ? 0 : 1f;
        vec.setLength(unit.realSpeed() * scl);
        unit.moveAt(vec);
    }
}