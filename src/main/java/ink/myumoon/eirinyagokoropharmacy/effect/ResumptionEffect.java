package ink.myumoon.eirinyagokoropharmacy.effect;

import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class ResumptionEffect extends MobEffect {
    public ResumptionEffect(MobEffectCategory category, int color) {
        super(category, color);
    }
    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier){
        entity.heal(2.0F);
        entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,200,1));
        entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,200,1));
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplifier){
        return  tickCount % 15 == 0;
    }

    @Override
    public void onEffectStarted(LivingEntity entity, int amplifier) {
        int ResumptionCount = entity.getPersistentData().getInt("ResumptionCount");
        entity.getPersistentData().putInt("ResumptionCount",ResumptionCount + 1);
        if (entity.getPersistentData().getInt("ResumptionCount") >= 3){
            entity.hurt(entity.damageSources().source(DamageTypes.MAGIC),Float.MAX_VALUE);
            entity.getPersistentData().putInt("ResumptionCount",0);
        }
    }
}