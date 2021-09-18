package ar.uba.fi.tdd.exercise;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GildedRoseTest {

	@Test
	public void calidadDeUnItemNoPuedeSerNegativa() {
		int calidadEsperada = 0;
		int calidadInicial = 0;

		Item[] items = new Item[] { new Item("prueba", 0, calidadInicial) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();

		assertThat(calidadEsperada).isEqualTo(app.items[0].quality);
	}

	@Test
	public void calidadDeUnItemDisminuye(){
		int calidadEsperada = 15;
		int calidadInicial = 16;

		Item[] items = new Item[] { new Item("prueba", 80, calidadInicial) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();

		assertThat(calidadEsperada).isEqualTo(app.items[0].quality);
	}

	@Test
	public void itemPasoCaducidadSuCalidadCaeDosVecesMasRapido(){
		int calidadEsperada = 18;
		int calidadInicial = 20;

		Item[] items = new Item[] { new Item("prueba", -8, calidadInicial) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();

		assertThat(calidadEsperada).isEqualTo(app.items[0].quality);
	}

	@Test
	public void calidadDeUnItemNoPuedeSerMayorA50(){
		int calidadEsperada = 50;
		int calidadInicial = 50;

		Item[] items = new Item[] { new Item("Aged Brie", 80, calidadInicial) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();

		assertThat(calidadEsperada).isEqualTo(app.items[0].quality);
	}


	@Test
	public void vencimientoDeUnItemDisminuye(){
		int vencimientoEsperado = 79;
		int vencimientoInicial = 80;

		Item[] items = new Item[] { new Item("Aged Brie", vencimientoInicial, 16) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();

		assertThat(vencimientoEsperado).isEqualTo(app.items[0].sellIn);
	}

	@Test
	public void itemSulfurasNoPierdeCalidadYSiempreEs80(){
		int calidadEsperada = 80;
		int calidadInicial = 80;

		Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 15, calidadInicial) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();

		assertThat(calidadEsperada).isEqualTo(app.items[0].quality);
	}

	@Test
	public void itemSulfurasNoSeVence(){
		int vencimientoEsperado = 15;
		int vencimientoInicial = 15;

		Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", vencimientoInicial, 80) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();

		assertThat(vencimientoEsperado).isEqualTo(app.items[0].sellIn);
	}

	@Test
	public void itemAgedBrieAumentaDobleDeCalidadCaducado(){
		int calidadEsperada = 22;
		int calidadInicial = 20;

		Item[] items = new Item[] { new Item("Aged Brie", -8, calidadInicial) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();

		assertThat(calidadEsperada).isEqualTo(app.items[0].quality);
	}

	@Test
	public void itemBackstagePassesAlCaducarSuCalidadCaeA0(){
		int calidadEsperada = 0;
		int calidadInicial = 42;

		Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", -1, calidadInicial) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();

		assertThat(calidadEsperada).isEqualTo(app.items[0].quality);
	}

	@Test
	public void itemConjuredPierdeDosVecesMasRapidoDeCalidad(){
		int calidadEsperada = 40;
		int calidadInicial = 42;

		Item[] items = new Item[] { new Item("Conjured", 18, calidadInicial) };
		GildedRose app = new GildedRose(items);
		app.updateQuality();

		assertThat(calidadEsperada).isEqualTo(app.items[0].quality);
	}
}
