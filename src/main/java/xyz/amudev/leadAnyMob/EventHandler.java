package xyz.amudev.leadAnyMob;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class EventHandler implements Listener {
    final LeadAnyMob plugin;

    public EventHandler(LeadAnyMob plugin) {
        this.plugin = plugin;
    }

    @org.bukkit.event.EventHandler
    public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("leadanymob.use") && event.getHand().equals(EquipmentSlot.HAND)
                && player.getInventory().getItemInMainHand().getType().equals(Material.LEAD)
                && event.getRightClicked() instanceof LivingEntity entity) {
            if (!entity.isLeashed()) {
                ItemStack item = player.getInventory().getItem(event.getHand());
                item.setAmount(item.getAmount() - 1);

                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                    entity.setLeashHolder(player);
                }, 1L);
            }
        }
    }
}
