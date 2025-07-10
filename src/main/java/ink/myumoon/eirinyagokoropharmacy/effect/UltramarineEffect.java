package ink.myumoon.eirinyagokoropharmacy.effect;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.jetbrains.annotations.NotNull;

import static ink.myumoon.eirinyagokoropharmacy.event.EventUltramarine.ULTRAMARINE_EFFECT_DEATH;

public class UltramarineEffect extends MobEffect {
    public UltramarineEffect(MobEffectCategory category, int color) {
        super(category, color);
    }
    @Override
    public boolean applyEffectTick(@NotNull LivingEntity entity, int amplifier){
        if (entity.hasEffect(MobEffects.BAD_OMEN)){
            entity.removeEffect(MobEffects.BAD_OMEN);
        }
        if (entity.getHealth() < entity.getMaxHealth()){
            entity.heal(1.0F);
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplifier){
        return tickCount % 40 == 0;
    }
    @Override
    public void onEffectAdded(@NotNull LivingEntity entity, int amplifier){
        super.onEffectAdded(entity,amplifier);
        entity.getAttributes().getInstance(Attributes.MAX_HEALTH).removeModifier(ULTRAMARINE_EFFECT_DEATH);
        entity.level().playSound(null, entity.blockPosition(), SoundEvents.LARGE_AMETHYST_BUD_PLACE, SoundSource.PLAYERS,1.0F,1.0F);
    }
}
