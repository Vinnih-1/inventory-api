package com.henryfabio.minecraft.inventoryapi.bungee.item.callback;

import com.henryfabio.minecraft.inventoryapi.bungee.event.impl.CustomInventoryClickEvent;
import com.henryfabio.minecraft.inventoryapi.bungee.item.callback.update.ItemUpdateCallback;
import de.exceptionflug.protocolize.api.ClickType;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author Henry Fábio
 * Github: https://github.com/HenryFabio
 */
@Data
public final class ItemCallback {

    private final Map<ClickType, Consumer<CustomInventoryClickEvent>> callbackMap = new HashMap<>();
    private ItemUpdateCallback updateCallback;

    public void callback(ClickType clickType, Consumer<CustomInventoryClickEvent> eventConsumer) {
        this.callbackMap.put(clickType, eventConsumer);
    }

    public Consumer<CustomInventoryClickEvent> getClickCallback(ClickType clickType) {
        return this.callbackMap.getOrDefault(clickType, this.callbackMap.get(null));
    }

}
