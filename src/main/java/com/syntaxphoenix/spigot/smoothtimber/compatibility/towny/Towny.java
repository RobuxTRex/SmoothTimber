package com.syntaxphoenix.spigot.smoothtimber.compatibility.towny;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import com.palmergames.bukkit.towny.object.TownyPermission;
import com.palmergames.bukkit.towny.utils.PlayerCacheUtil;
import com.syntaxphoenix.spigot.smoothtimber.SmoothTimber;
import com.syntaxphoenix.spigot.smoothtimber.compatibility.CompatibilityAddon;
import com.syntaxphoenix.spigot.smoothtimber.utilities.locate.Locator;
import com.syntaxphoenix.spigot.smoothtimber.utilities.plugin.PluginPackage;

public class Towny extends CompatibilityAddon {

    private TownyChopListener listener;

    @Override
    public void onEnable(final PluginPackage pluginPackage, final SmoothTimber smoothTimber) throws Exception {
        Bukkit.getServer().getPluginManager().registerEvents(listener = new TownyChopListener(this), smoothTimber);
    }

    @Override
    public void onDisable(final SmoothTimber smoothTimber) throws Exception {
        if (listener != null) {
            HandlerList.unregisterAll(listener);
        }
    }

    public boolean canDestroy(final Player player, final Location location) {
        return PlayerCacheUtil.getCachePermission(player, location, Locator.getBlockState(location).getType(), TownyPermission.ActionType.DESTROY);
    }

}
