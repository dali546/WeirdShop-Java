package training.weirdshop;

class WeirdShop {
    private Item[] items;

    public WeirdShop(Item[] items) {
        this.items = items;
    }

    void updateQuality() {
        String name;
        for (Item item : items) {
            name = item.name;
            if (name.equals("Backstage Pass")) {
                updateBackStage(item);
            } else if (name.equals("Aged Brie")) {
                updateAgedBrie(item);
            } else if (!name.equals("Gold Coin")) {
                updateOtherItems(item);
            }
        }
    }

    private void updateAgedBrie(Item item) {
        item.sellIn--;
        if (item.quality<50) item.quality++;
    }

    private void updateBackStage(Item item) {
        item.sellIn--;
        if (item.quality < 50) item.quality++;
        if (item.quality < 50 && item.sellIn <= 10) item.quality++;
        if (item.quality < 50 && item.sellIn <= 5) item.quality++;
        if (item.sellIn < 0) item.quality = 0;
    }

    private void updateOtherItems(Item item) {
        item.sellIn--;
        if (item.quality > 0) item.quality--;
        if (item.sellIn < 0 && item.quality>0) item.quality--;
    }
}