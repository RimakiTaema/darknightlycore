package su.nightexpress.nightcore.integration.item.excellentcrates;

import java.util.Optional;

import org.bukkit.inventory.ItemStack;
import org.jspecify.annotations.NullMarked;

import su.nightexpress.nightcore.bridge.item.AdaptedItem;
import su.nightexpress.nightcore.integration.item.ItemProvider;
import su.nightexpress.nightcore.integration.item.ItemProviderKeys;
import su.nightexpress.nightcore.util.Plugins;

/**
 * Legacy combined provider kept only so products saved before the crate/key split
 * ({@code Provider: excellent_crates}) still load. Its codec reads the stored type and
 * produces an item bound to the new crate/key provider, so the next save migrates the id.
 * It never claims new items; detection is handled by the split providers.
 */
@NullMarked
public class ExcellentCratesItemProvider implements ItemProvider {

    @Override
    public String getId() {
        return ItemProviderKeys.EXCELLENT_CRATES;
    }

    @Override
    public boolean isAvailable() {
        return Plugins.isInstalled("ExcellentCrates");
    }

    @Override
    public boolean isProducedBy(ItemStack itemStack) {
        return false;
    }

    @Override
    public Optional<AdaptedItem> wrapItem(ItemStack itemStack) {
        return Optional.empty();
    }
}