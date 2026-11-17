package su.nightexpress.nightcore.integration.item.excellentcrates;

import java.util.Optional;

import org.bukkit.inventory.ItemStack;
import org.jspecify.annotations.NullMarked;

import su.nightexpress.engine.id.Identifier;
import su.nightexpress.excellentcrates.api.ExcellentCratesAPI;
import su.nightexpress.nightcore.bridge.item.AdaptedItem;
import su.nightexpress.nightcore.integration.item.ItemProvider;
import su.nightexpress.nightcore.integration.item.ItemProviderKeys;
import su.nightexpress.nightcore.util.Plugins;

@NullMarked
public class ExcellentCratesCrateProvider implements ItemProvider {

    @Override
    public String getId() {
        return ItemProviderKeys.EXCELLENT_CRATES_CRATE;
    }

    @Override
    public boolean isAvailable() {
        return Plugins.isInstalled("ExcellentCrates");
    }

    @Override
    public boolean isProducedBy(ItemStack itemStack) {
        ExcellentCratesAPI api = ExcellentCratesAPIService.resolve();

        return api != null && api.getCratesAPI().items().getCrateId(itemStack) != null;
    }

    @Override
    public Optional<AdaptedItem> wrapItem(ItemStack itemStack) {
        ExcellentCratesAPI api = ExcellentCratesAPIService.resolve();
        if (api == null) {
            return Optional.empty();
        }

        Identifier crateId = api.getCratesAPI().items().getCrateId(itemStack);
        if (crateId == null) {
            return Optional.empty();
        }

        ExcellentCratesItemContext context = new ExcellentCratesItemContext(ExcellentCratesItemType.CRATE, crateId,
            itemStack.getAmount());

        return Optional.of(new ExcellentCratesItem(context));
    }
}