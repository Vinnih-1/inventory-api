package com.henryfabio.minecraft.inventoryapi.bungee.item;

import com.henryfabio.minecraft.inventoryapi.bungee.event.impl.CustomInventoryClickEvent;
import com.henryfabio.minecraft.inventoryapi.bungee.item.callback.ItemCallback;
import com.henryfabio.minecraft.inventoryapi.bungee.item.callback.update.ItemUpdateCallback;
import de.exceptionflug.protocolize.api.ClickType;
import de.exceptionflug.protocolize.items.ItemStack;
import lombok.Data;

import java.util.function.Consumer;

/**
 * @author Henry Fábio
 * Github: https://github.com/HenryFabio
 */
@Data(staticConstructor = "of")
public final class InventoryItem {

    private final ItemStack itemStack;
    private final ItemCallback itemCallback = new ItemCallback();

    public InventoryItem callback(ClickType clickType, Consumer<CustomInventoryClickEvent> eventConsumer) {
        this.itemCallback.callback(clickType, eventConsumer);
        return this;
    }

    public InventoryItem defaultCallback(Consumer<CustomInventoryClickEvent> eventConsumer) {
        return this.callback(null, eventConsumer);
    }

    public InventoryItem updateCallback(ItemUpdateCallback updateCallback) {
        this.itemCallback.setUpdateCallback(updateCallback);
        return this;
    }

}
