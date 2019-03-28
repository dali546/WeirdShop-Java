package training.weirdshop;

class WeirdShop {
    private Item[] items;

    public WeirdShop(Item[] items) {
        this.items = items;
    }

    void updateQuality() {
        for (Item item : items) {
            boolean isPremium = item.name.contains("Premium");
            if (item.name.contains("Backstage Pass")) updateBackStage(item, isPremium);
            else if (item.name.contains("Aged Brie")) updateAgedBrie(item, isPremium);
            else if (!item.name.contains("Gold Coin")) updateOtherItems(item, isPremium);
        }
    }

    private void updateAgedBrie(Item item, boolean isPremium) {
        item.sellIn--;
        if (item.quality < 50) increaseQuality(item, isPremium);
        capQuality(item);
    }

    private void updateBackStage(Item item, boolean isPremium) {
        item.sellIn--;
        if (item.quality < 50) increaseQuality(item, isPremium);
        if (item.quality < 50 && item.sellIn <= 10) increaseQuality(item, isPremium);
        if (item.quality < 50 && item.sellIn <= 5) increaseQuality(item, isPremium);
        if (item.sellIn < 0) item.quality = 0;
        capQuality(item);
    }

    private void updateOtherItems(Item item, boolean isPremium) {
        item.sellIn--;
        if (item.quality > 0) decreaseQuality(item, isPremium);
        if (item.sellIn < 0 && item.quality > 0) decreaseQuality(item, isPremium);
        capQuality(item);
    }

    private void capQuality(Item item) {
        if (item.quality < 0) item.quality = 0;
        if (item.quality > 50) item.quality = 50;
    }

    private void decreaseQuality(Item item, boolean isPremium) {
        if (isPremium) item.quality--;
        item.quality--;
    }

    private void increaseQuality(Item item, boolean isPremium) {
        if (isPremium) item.quality++;
        item.quality++;
    }
}