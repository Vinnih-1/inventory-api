package com.henryfabio.minecraft.inventoryapi.bungee.inventory;

import com.henryfabio.minecraft.inventoryapi.bungee.editor.InventoryEditor;
import com.henryfabio.minecraft.inventoryapi.bungee.inventory.impl.paged.PagedInventory;
import com.henryfabio.minecraft.inventoryapi.bungee.item.InventoryItem;
import com.henryfabio.minecraft.inventoryapi.bungee.item.supplier.InventoryItemSupplier;
import com.henryfabio.minecraft.inventoryapi.bungee.viewer.Viewer;
import com.henryfabio.minecraft.inventoryapi.bungee.viewer.configuration.border.Border;
import com.henryfabio.minecraft.inventoryapi.bungee.viewer.configuration.impl.ViewerConfigurationImpl;
import com.henryfabio.minecraft.inventoryapi.bungee.viewer.impl.paged.PagedViewer;

import de.exceptionflug.protocolize.inventory.InventoryType;
import de.exceptionflug.protocolize.items.ItemStack;
import de.exceptionflug.protocolize.items.ItemType;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Henry Fábio
 * Github: https://github.com/HenryFabio
 */
public final class TestPagedInventory extends PagedInventory {

    public TestPagedInventory() {
        super(
                "test.inventory.paged", // Identificador do inventário (deve ser único)
                "&8PagedInventory", // Título padrão do inventário
                InventoryType.GENERIC_9X6 // Tamanho do inventário
        );

        // Método para configurar características do inventário (não é obrigatória a configuração)
        configuration(configuration -> {
            configuration.secondUpdate(1); // Definir o tempo de atualização do inventário (não configure isso caso não queira que ele atualize automaticamente)
        });
    }

    /**
     * Método utilizado para configurar o visualizador, podendo definir
     * unicamente o título do inventário e tamanho.
     * <p>
     * Não é obrigatória a implementação desde método, apenas caso queira uma configuração
     * exclusiva para cada visualizador.
     *
     * @param viewer visualizador do inventário
     */
    @Override
    protected void configureViewer(@NotNull PagedViewer viewer) {
        ViewerConfigurationImpl.Paged configuration = viewer.getConfiguration();
        configuration.titleInventory("&8Seu nome: " + viewer.getName());
        configuration.border(Border.of(2, 2, 1, 1));

        // As configurações do inventário são definidas automaticamente, só altere elas caso queira modificações específicas
//        configuration.itemPageLimit(7);
        configuration.nextPageSlot(53);
        configuration.previousPageSlot(46);
    }

    @Override
    protected void configureInventory(@NotNull Viewer viewer, @NotNull InventoryEditor editor) {
        editor.fillColumn(1, InventoryItem.of(new ItemStack(ItemType.BLUE_STAINED_GLASS)));
    }

    /**
     * Método para configurar os itens das páginas do inventário.
     *
     * @param viewer visualizador do inventário
     * @return lista dos itens de todas as páginas
     */
    @Override
    protected List<InventoryItemSupplier> createPageItems(@NotNull PagedViewer viewer) {
        List<InventoryItemSupplier> itemSuppliers = new LinkedList<>();

        for (int i = 0; i < 100; i++) {
            int finalI = i;
            itemSuppliers.add(() -> {
                ItemStack itemStack = new ItemStack(ItemType.DIRT);
                itemStack.setDisplayName("§e" + finalI);
                return InventoryItem.of(itemStack);
            });
        }

        return itemSuppliers;
    }

}