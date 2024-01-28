package youren.touhou.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class EffectUndead extends StatusEffect {
    public EffectUndead() {
        super(StatusEffectCategory.NEUTRAL, 0x98D982);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity.getHealth() < 3.0F && entity.getHealth() > 0.0F) {
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 200, 1));
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
           return duration % 500 == 0;
    }
}

