package xyz.amudev.leadAnyMob;

import org.bukkit.plugin.java.JavaPlugin;

public final class LeadAnyMob extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new EventHandler(this), this);
    }
}
