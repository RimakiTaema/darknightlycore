package su.nightexpress.nightcore.integration.item.excellentcrates;

import org.jspecify.annotations.NullMarked;

import su.nightexpress.nightcore.integration.item.ItemProviderKeys;

@NullMarked
public enum ExcellentCratesItemType {

    CRATE(ItemProviderKeys.EXCELLENT_CRATES_CRATE),
    KEY(ItemProviderKeys.EXCELLENT_CRATES_KEY),
    ;

    private final String providerId;

    ExcellentCratesItemType(String providerId) {
        this.providerId = providerId;
    }

    public String getProviderId() {
        return this.providerId;
    }
}