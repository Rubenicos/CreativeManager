package fr.k0bus.creativemanager.event;

import fr.k0bus.creativemanager.CreativeManager;
import fr.k0bus.creativemanager.settings.Protections;
import fr.k0bus.creativemanager.utils.CMUtils;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.projectiles.ProjectileSource;

/**
 * Projectile throw event listener.
 */
public class ProjectileThrow implements Listener {

	/**
	 * Instantiates a new Projectile throw.
	 *
	 */
	CreativeManager plugin;
	public ProjectileThrow(CreativeManager plugin) {
		this.plugin = plugin;
	}

	/**
	 * On drop.
	 *
	 * @param e the event.
	 */
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onDrop(ProjectileLaunchEvent e) {
		ProjectileSource source = e.getEntity().getShooter();
		if (source instanceof Player p) {
			if (CreativeManager.getSettings().getProtection(Protections.THROW) && p.getGameMode().equals(GameMode.CREATIVE)) {
				if (!p.hasPermission("creativemanager.bypass.throw")) {
					if (CreativeManager.getSettings().getConfiguration().getBoolean("send-player-messages"))
						CMUtils.sendMessage(p, "permission.throw");
					e.setCancelled(true);
				}
			}
		}
	}
}
