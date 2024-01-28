package youren.touhou.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class EffectUltramarine extends StatusEffect {
    public EffectUltramarine() {
        super(StatusEffectCategory.BENEFICIAL, 0xA22633);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity.isAlive()) {
            if (entity.hasStatusEffect(StatusEffects.BAD_OMEN)) {
                entity.removeStatusEffect(StatusEffects.BAD_OMEN);
            }

            if (entity.getHealth() < entity.getMaxHealth()) {
                entity.heal(0.5F);
            }

            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.LUCK, 4800, 1)); // 4分钟的幸运效果
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return false; //
    }
}
