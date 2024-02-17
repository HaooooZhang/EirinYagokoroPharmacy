package youren.touhou;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import youren.touhou.effect.EffectResumption;
import youren.touhou.effect.EffectUltramarine;
import youren.touhou.effect.EffectUndead;
import youren.touhou.event.ArrowHitSelfEvent;
import youren.touhou.recipes.PotionRecipe;

import java.util.HashMap;
import java.util.UUID;

import static youren.touhou.event.DeathEvent.onPlayerDeath;
import static youren.touhou.event.EventUltramarineDeath.onPlayerDeathB;

public class EirinYagokorosPharmacy implements ModInitializer {

    public static final StatusEffect UNDEAD_EFFECT = new EffectUndead();
    public static final StatusEffect ULTRAMARINE_EFFECT = new EffectUltramarine();
    public static final StatusEffect RESUMPTION_EFFECT = new EffectResumption();
    public static final Potion HOURAI_POTION = new Potion("hourai_potion", new StatusEffectInstance(UNDEAD_EFFECT, -1));
    public static final Potion ULTRAMARINE_POTION = new Potion("ultramarine_potion", new StatusEffectInstance(ULTRAMARINE_EFFECT, 4800));
    public static final Potion RESUMPTION_POTION = new Potion("resumption_potion", new StatusEffectInstance(RESUMPTION_EFFECT, 200));
    public static final Item ITEM_FLESH = new Item(new FabricItemSettings().maxCount(1));
    private final HashMap<UUID, Boolean> undeadPlayers = new HashMap<>();

    @Override
    public void onInitialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> content.add(ITEM_FLESH));
        Registry.register(Registries.STATUS_EFFECT, new Identifier("yagokoropharmacy", "undead_effect"), UNDEAD_EFFECT);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("yagokoropharmacy", "ultramarine_effect"), ULTRAMARINE_EFFECT);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("yagokoropharmacy", "resumption_effect"), RESUMPTION_EFFECT);
        Registry.register(Registries.POTION, new Identifier("yagokoropharmacy", "undead_effect"), HOURAI_POTION);
        Registry.register(Registries.POTION, new Identifier("yagokoropharmacy", "ultramarine_effect"), ULTRAMARINE_POTION);
        Registry.register(Registries.POTION, new Identifier("yagokoropharmacy", "resumption_effect"), RESUMPTION_POTION);
        Registry.register(Registries.ITEM, new Identifier("yagokoropharmacy", "flesh"), ITEM_FLESH);
        ArrowHitSelfEvent.register();
        PotionRecipe.registerPotions();


        ServerLivingEntityEvents.ALLOW_DEATH.register((entity, damageSource, damageAmount) -> {
            if (entity instanceof ServerPlayerEntity) {
                return onPlayerDeath((ServerPlayerEntity) entity, damageSource, damageAmount);
            }
            return true;
        });

        ServerLivingEntityEvents.ALLOW_DEATH.register((entity, damageSource, damageAmount) -> {
            if (entity instanceof ServerPlayerEntity) {
                return onPlayerDeathB((ServerPlayerEntity) entity, damageSource, damageAmount);
            }
            return true;
        });

        ServerTickEvents.END_SERVER_TICK.register(server -> server.getPlayerManager().getPlayerList().forEach(EirinYagokorosPharmacy.this::handlePlayerEffect));
  }

  private void handlePlayerEffect(ServerPlayerEntity player) {
    boolean hasUndeadEffect = player.hasStatusEffect(EirinYagokorosPharmacy.UNDEAD_EFFECT);
    UUID playerUUID = player.getUuid();

    if (hasUndeadEffect) {
      undeadPlayers.put(playerUUID, true);
    } else if (undeadPlayers.getOrDefault(playerUUID, false)) {
      player.addStatusEffect(new StatusEffectInstance(EirinYagokorosPharmacy.UNDEAD_EFFECT, -1, 0));
      undeadPlayers.put(playerUUID, false);
    }
  }
}