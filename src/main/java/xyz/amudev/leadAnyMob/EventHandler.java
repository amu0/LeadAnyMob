package xyz.amudev.leadAnyMob;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

public class EventHandler implements Listener {
    final LeadAnyMob plugin;

    public EventHandler(LeadAnyMob plugin) {
        this.plugin = plugin;
    }

    Set<EntityType> leashableInVanilla = new HashSet<>(){{
        add(EntityType.ALLAY);
        add(EntityType.ARMADILLO);
        add(EntityType.AXOLOTL);
        add(EntityType.BEE);
        add(EntityType.BOAT);
        add(EntityType.CHEST_BOAT);
        add(EntityType.CAMEL);
        add(EntityType.CAT);
        add(EntityType.CHICKEN);
        add(EntityType.COW);
        add(EntityType.DOLPHIN);
        add(EntityType.DONKEY);
        add(EntityType.FOX);
        add(EntityType.FROG);
        add(EntityType.GLOW_SQUID);
        add(EntityType.GOAT);
        add(EntityType.HOGLIN);
        add(EntityType.HORSE);
        add(EntityType.IRON_GOLEM);
        add(EntityType.LLAMA);
        add(EntityType.MOOSHROOM);
        add(EntityType.MULE);
        add(EntityType.OCELOT);
        add(EntityType.PARROT);
        add(EntityType.PIG);
        add(EntityType.POLAR_BEAR);
        add(EntityType.RABBIT);
        add(EntityType.SHEEP);
        add(EntityType.SKELETON_HORSE);
        add(EntityType.SNIFFER);
        add(EntityType.SNOW_GOLEM);
        add(EntityType.SQUID);
        add(EntityType.STRIDER);
        add(EntityType.WOLF);
        add(EntityType.ZOGLIN);
        add(EntityType.ZOMBIE_HORSE);
    }};

    @org.bukkit.event.EventHandler
    public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("leadanymob.use")
                && event.getHand().equals(EquipmentSlot.HAND)
                && player.getInventory().getItemInMainHand().getType().equals(Material.LEAD)
                && event.getRightClicked() instanceof LivingEntity entity
                && !leashableInVanilla.contains(entity.getType())) {

            if (!entity.isLeashed()) {
                // consumption lead except in creative mode
                if (!player.getGameMode().equals(GameMode.CREATIVE)) {
                    ItemStack item = player.getInventory().getItem(event.getHand());
                    item.setAmount(item.getAmount() - 1);
                }

                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                    entity.setLeashHolder(player);
                }, 1L);
            }
        }
    }
}
