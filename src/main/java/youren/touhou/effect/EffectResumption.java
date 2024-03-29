package youren.touhou.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

import java.util.HashMap;
import java.util.UUID;

public class EffectResumption extends StatusEffect {
    public EffectResumption() {
        super(StatusEffectCategory.BENEFICIAL, 0x63C74D);
    }
    private final HashMap<UUID, Integer> potionUseCount = new HashMap<>();

    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        UUID playerID = entity.getUuid();
        potionUseCount.putIfAbsent(playerID, 0);
        potionUseCount.put(playerID, potionUseCount.get(playerID) + 1);
            if (potionUseCount.get(playerID) == 3) {
                entity.kill();
                entity.clearStatusEffects();
                potionUseCount.put(playerID, 0); // 重置计数
            }
            entity.heal(10);
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 1));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200, 1));
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration % 200 == 0;
    }
}
