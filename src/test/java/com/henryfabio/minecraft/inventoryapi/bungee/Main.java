package com.henryfabio.minecraft.inventoryapi.bungee;

import com.henryfabio.minecraft.inventoryapi.bungee.inventory.TestGlobalInventory;
import com.henryfabio.minecraft.inventoryapi.bungee.inventory.TestPagedInventory;
import com.henryfabio.minecraft.inventoryapi.bungee.inventory.TestSimpleInventory;
import com.henryfabio.minecraft.inventoryapi.bungee.manager.InventoryManager;
import com.henryfabio.minecraft.inventoryapi.bungee.viewer.property.ViewerPropertyMap;
import lombok.Getter;
import lombok.val;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

/**
 * @author Henry Fábio
 * Github: https://github.com/HenryFabio
 */
public final class Main extends Plugin implements Listener {

    public final TestSimpleInventory simpleInventory = new TestSimpleInventory().init();
    public final TestGlobalInventory globalInventory = new TestGlobalInventory().init();
    public final TestPagedInventory pagedInventory = new TestPagedInventory().init();

    @Getter public static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        InventoryManager.enable(this);
        this.getProxy().getPluginManager().registerListener(this, this);

        getLogger().info("§aIniciado");
    }

    @EventHandler
    public void onChatEvent(ChatEvent event) {
        val player = (ProxiedPlayer) event.getSender();

        val message = event.getMessage();
        switch (message) {
            case "simples":
                simpleInventory.openInventory(player, viewer -> {
                    ViewerPropertyMap propertyMap = viewer.getPropertyMap();
                    propertyMap.set("number", 1);
                    propertyMap.set("boolean", true);
                    propertyMap.set("long", 20L);
                });
                break;
            case "global":
                globalInventory.openInventory(player);
                break;
            case "paginado":
                pagedInventory.openInventory(player);
                break;
        }
    }

}
