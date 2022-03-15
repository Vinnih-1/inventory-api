package com.henryfabio.minecraft.inventoryapi.bungee.inventory;

import com.henryfabio.minecraft.inventoryapi.bungee.editor.InventoryEditor;
import com.henryfabio.minecraft.inventoryapi.bungee.inventory.impl.global.GlobalInventory;
import com.henryfabio.minecraft.inventoryapi.bungee.item.InventoryItem;
import de.exceptionflug.protocolize.api.ClickType;
import de.exceptionflug.protocolize.inventory.InventoryType;
import de.exceptionflug.protocolize.items.ItemStack;
import de.exceptionflug.protocolize.items.ItemType;
import lombok.val;
import net.md_5.bungee.api.chat.TextComponent;
import org.jetbrains.annotations.NotNull;

/**
 * @author Henry Fábio
 * Github: https://github.com/HenryFabio
 */
public final class TestGlobalInventory extends GlobalInventory {

    public TestGlobalInventory() {
        super(
                "test.inventory.global", // Identificador do inventário (deve ser único)
                "&8GlobalInventory", // Título padrão do inventário
                InventoryType.GENERIC_9X3 // Tamanho do inventário
        );

        configuration(configuration -> {
            configuration.secondUpdate(1);
        });
    }

    /**
     * Método utilizado para configurar os itens do inventário.
     *
     * @param editor editor do inventário
     */
    @Override
    protected void configureInventory(@NotNull InventoryEditor editor) {
        editor.setItem(13, InventoryItem.of(
                new ItemStack(ItemType.STONE)
        ).callback(ClickType.RIGHT_CLICK, event -> {
            val player = event.getPlayer();
            player.sendMessage(new TextComponent("§eVocê clicou com o botão direito!"));
        }).defaultCallback(event -> {
            val player = event.getPlayer();
            player.sendMessage(new TextComponent("§eVocê interagiu com o inventário!"));
        }));
    }

}
