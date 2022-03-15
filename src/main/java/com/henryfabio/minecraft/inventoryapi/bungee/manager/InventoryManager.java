package com.henryfabio.minecraft.inventoryapi.bungee.manager;

import com.henryfabio.minecraft.inventoryapi.bungee.controller.InventoryController;
import com.henryfabio.minecraft.inventoryapi.bungee.controller.ViewerController;
import com.henryfabio.minecraft.inventoryapi.bungee.listener.CustomInventoryListener;
import com.henryfabio.minecraft.inventoryapi.bungee.schedule.InventoryUpdateRunnable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.val;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

import java.util.concurrent.TimeUnit;

/**
 * @author Henry Fábio
 * Github: https://github.com/HenryFabio
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class InventoryManager {

    @Getter private static final InventoryManager instance = new InventoryManager();
    @Getter private static boolean enabled;

    private final InventoryController inventoryController = new InventoryController();
    private final ViewerController viewerController = new ViewerController();

    public static void enable(Plugin plugin) {
        if (InventoryManager.isEnabled()) return;

        PluginManager pluginManager = plugin.getProxy().getPluginManager();
        pluginManager.registerListener(plugin, new CustomInventoryListener());

        val scheduler = plugin.getProxy().getScheduler();
        scheduler.schedule(plugin, new InventoryUpdateRunnable(), 1, TimeUnit.SECONDS);

        InventoryManager.enabled = true;
    }

    public static InventoryController getInventoryController() {
        return instance.inventoryController;
    }

    public static ViewerController getViewerController() {
        return instance.viewerController;
    }

}
