package youren.touhou.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import youren.touhou.EirinYagokorosPharmacy;

public class UndeadListener  implements ServerTickEvents.EndTick{

    @Override
    public void onEndTick(MinecraftServer server) {
        server.getWorlds().forEach(world -> {
            RegistryKey<World> worldKey = world.getRegistryKey();
            for (ServerPlayerEntity player : world.getPlayers()) {
                if (!player.hasStatusEffect(EirinYagokorosPharmacy.UNDEAD_EFFECT) && isPlayerInCorrectWorld(player, worldKey)) {
                    // 玩家失去UNDEAD_EFFECT效果，且在指定的世界中，再次给予效果
                    player.addStatusEffect(new StatusEffectInstance(EirinYagokorosPharmacy.UNDEAD_EFFECT, 6000, 0));
                }
            }
        });
    }

    private boolean isPlayerInCorrectWorld(ServerPlayerEntity player, RegistryKey<World> worldKey) {
        return player.getWorld().getRegistryKey().equals(worldKey);
    }
}
