
package ar.uba.fi.tdd.exercise;

class GildedRose {
  Item[] items;

    public static int MAX_CALIDAD = 50;

    public GildedRose(Item[] _items) {
        items = _items;
    }

    // update the quality of the emements
    public void updateQuality() {
        // for each item
        for (int i = 0; i < items.length; i++) {

            if (isSulfuras(items[i])) continue;

            // Se reduce los dias de vencimiento al inicio
            reducirVencimiento(items[i]);

            // Divido en cada caso para que se entienda mejor
            if (isAgedBrie(items[i])) {

                if (itemCaducado(items[i])) aumentarCalidadEn(items[i], 2);
                else aumentarCalidadEn(items[i], 1);

            } else if (isBackstagePasses(items[i])) {

                if (itemCaducado(items[i])){
                    items[i].quality = 0;
                    break;
                }

                if(venceItemEn(items[i], 10)) aumentarCalidadEn(items[i], 2);
                else if(venceItemEn(items[i], 5)) aumentarCalidadEn(items[i], 3);
                else aumentarCalidadEn(items[i], 1);

            } else if (isConjured(items[i])){

                disminuirCalidadEn(items[i], 2);

            } else{

                if(itemCaducado(items[i])) disminuirCalidadEn(items[i], 2);
                else disminuirCalidadEn(items[i], 1);
            }
        }
    }

    private void reducirVencimiento(Item item) {
        item.sellIn -= 1;
    }

    private void aumentarCalidadEn(Item item, int cantidad){
        int calidad_nueva = item.quality + cantidad;

        item.quality = calidad_nueva > MAX_CALIDAD ? MAX_CALIDAD : calidad_nueva;
    }

    private void disminuirCalidadEn(Item item, int cantidad){
        int calidad_nueva = item.quality - cantidad;

        item.quality = calidad_nueva > 0 ? calidad_nueva : 0;
    }

    private boolean isConjured(Item item) {
        return item.Name.equals("Conjured");
    }

    private boolean isBackstagePasses(Item item) {
        return item.Name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private boolean isAgedBrie(Item item) {
        return item.Name.equals("Aged Brie");
    }

    private boolean isSulfuras(Item item) {
        return item.Name.equals("Sulfuras, Hand of Ragnaros");
    }

    private boolean itemCaducado(Item item){
        int dias_caducado = -1;

        return venceItemEn(item, dias_caducado);
    }

    private boolean venceItemEn(Item item, int cantidad){
        return item.sellIn <= cantidad;
    }
}
