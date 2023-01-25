package jpaesim;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

public class TestTuoteKauppaSuhde {

	public static void main(String[] args) throws Exception {
     	// Startataan H2 TCP-serverimoodissa
        org.h2.tools.Server server = org.h2.tools.Server.createTcpServer().start();


		EntityManagerFactory tehdas = Persistence.createEntityManagerFactory("jpa_tuote");
		EntityManager manageri = tehdas.createEntityManager();
		EntityTransaction transaktio = manageri.getTransaction();

		Tuote t1 = new Tuote();
		t1.setNimi("Pesäpallo");
		t1.setHinta(15.56);
		t1.setKoodi("A321");

		Tuote t2 = new Tuote();
		t2.setNimi("Jalkapallo");
		t2.setHinta(19.99);
		t2.setKoodi("A654");

		Tuote t3 = new Tuote();
		t3.setNimi("Koripallo");
		t3.setHinta(24.99);
		t3.setKoodi("B342");

		Kauppa k1 = new Kauppa();
		k1.setNimi("Palloliike");
		k1.setOsoite("Pallokuja 2, Helsinki");

		Kauppa k2 = new Kauppa();
		k2.setNimi("Välineaitta");
		k2.setOsoite("Kauppatie 45, Jyväskylä");

		Kauppa k3 = new Kauppa();
		k3.setNimi("Verkkosportti");
		k3.setOsoite("Kuplahallintie 15, Toijala");

		// Lisätään kaupat listassa tuotteille
		List<Kauppa> kaupat = new ArrayList<>();
		kaupat.add(k1);
		kaupat.add(k2);

		List<Kauppa> kaupat2 = new ArrayList<>();
		kaupat2.add(k2);
		kaupat2.add(k3);

		t1.setKaupat(kaupat);
		t3.setKaupat(kaupat2);

		transaktio.begin();
		manageri.persist(t1);
		manageri.persist(t2);
		manageri.persist(t3);

		manageri.persist(k1);
		manageri.persist(k2);
		manageri.persist(k3);

		transaktio.commit();

		// Testihaku - haetaan ja tulostetaan kaikki kannassa olevat
		// Language-entiteetit
		@SuppressWarnings("unchecked")
		List<Tuote> entiteetit = manageri.createNamedQuery("selectTuotteet").getResultList();
		for (Tuote e : entiteetit) {
			System.out.println("Rivi: " + e.getNimi() + e.getKaupat().toString());
		}

		@SuppressWarnings("unchecked")
		List<Kauppa> entiteetit2 = manageri.createNamedQuery("selectKaupat").getResultList();
		for (Kauppa e : entiteetit2) {
			System.out.println("Rivi: " + e.getNimi());
		}

		manageri.close();
		tehdas.close();
		// Lopetetaan h2-palvelin
        server.stop();
	}
}
