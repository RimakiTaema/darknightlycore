package su.nightexpress.nightcore.integration.item.excellentcrates;

import java.util.Optional;

import org.bukkit.inventory.ItemStack;
import org.jspecify.annotations.NullMarked;

import su.nightexpress.engine.id.Identifier;
import su.nightexpress.excellentcrates.api.ExcellentCratesAPI;
import su.nightexpress.excellentcrates.api.key.KeysAPI;
import su.nightexpress.nightcore.bridge.item.AdaptedItem;
import su.nightexpress.nightcore.integration.item.ItemProvider;
import su.nightexpress.nightcore.integration.item.ItemProviderKeys;
import su.nightexpress.nightcore.util.Plugins;

@NullMarked
public class ExcellentCratesKeyProvider implements ItemProvider {

    @Override
    public String getId() {
        return ItemProviderKeys.EXCELLENT_CRATES_KEY;
    }

    @Override
    public boolean isAvailable() {
        return Plugins.isInstalled("ExcellentCrates");
    }

    @Override
    public boolean isProducedBy(ItemStack itemStack) {
        ExcellentCratesAPI api = ExcellentCratesAPIService.resolve();
        if (api == null) {
            return false;
        }

        KeysAPI keys = api.getKeysAPI().orElse(null);

        return keys != null && keys.getItemAPI().getKeyIdFromItem(itemStack) != null;
    }

    @Override
    public Optional<AdaptedItem> wrapItem(ItemStack itemStack) {
        ExcellentCratesAPI api = ExcellentCratesAPIService.resolve();
        if (api == null) {
            return Optional.empty();
        }

        KeysAPI keys = api.getKeysAPI().orElse(null);
        if (keys == null) {
            return Optional.empty();
        }

        Identifier keyId = keys.getItemAPI().getKeyIdFromItem(itemStack);
        if (keyId == null) {
            return Optional.empty();
        }

        ExcellentCratesItemContext context = new ExcellentCratesItemContext(ExcellentCratesItemType.KEY, keyId,
            itemStack.getAmount());

        return Optional.of(new ExcellentCratesItem(context));
    }
}