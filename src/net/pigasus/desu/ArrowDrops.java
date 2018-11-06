package net.pigasus.desu;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class ArrowDrops implements Listener {
	
	private Desu plugin;
	private Map<Integer, Integer> shotEntities;
	
	public ArrowDrops(Desu plugin) {
		this.plugin = plugin;
		this.shotEntities = new HashMap<>();
	}
	
	@EventHandler
	public void onProjectileHit(ProjectileHitEvent evt) {
		if (evt.getEntity() instanceof Arrow) {
			Entity hitEntity = evt.getHitEntity();
			if (hitEntity != null) {
				if (shotEntities.containsKey(hitEntity.getEntityId())) {
					Integer val = shotEntities.get(hitEntity.getEntityId());
					shotEntities.replace(hitEntity.getEntityId(), val + 1);
				} else {
					shotEntities.put(hitEntity.getEntityId(), 1);
				}
			}
		}
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent evt) {
		Entity entity = evt.getEntity();
		if (shotEntities.containsKey(entity.getEntityId())) {
			evt.getDrops().add(new ItemStack(Material.ARROW, shotEntities.get(entity.getEntityId())));
		}
	}

}
