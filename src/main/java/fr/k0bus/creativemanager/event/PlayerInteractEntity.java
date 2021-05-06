package fr.k0bus.creativemanager.event;

import fr.k0bus.creativemanager.settings.Protections;
import fr.k0bus.creativemanager.utils.Messages;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Donkey;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Llama;
import org.bukkit.entity.Player;
import org.bukkit.entity.minecart.StorageMinecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import fr.k0bus.creativemanager.CreativeManager;

public class PlayerInteractEntity implements Listener {

    CreativeManager plugin;

    public PlayerInteractEntity(CreativeManager instance) {
        plugin = instance;
    }

    @EventHandler(ignoreCancelled = true)
    public void onUse(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();
        if (plugin.getSettings().getProtection(Protections.ENTITY) && p.getGameMode().equals(GameMode.CREATIVE) && !p.hasPermission("creativemanager.bypass.entity")) {
            if (!p.hasPermission("creativemanager.bypass.entity") && !p.hasPermission("creativemanager.bypass.entity." + e.getRightClicked().getType().name().toLowerCase())) {
                Messages.sendMessage(plugin, p, "permission.entity");
                e.setCancelled(true);
            }
        }
    }
}
