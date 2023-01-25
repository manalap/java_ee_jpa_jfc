package jpaesim;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Kauppa
 *
 */
@javax.persistence.Entity
@Table(name = "KAUPPA")
@NamedQuery(name = "selectKaupat", query = "SELECT k from Kauppa k")
public class Kauppa implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String nimi;
	private String osoite;
	private static final long serialVersionUID = 1L;

	public Kauppa() {
		super();
	}

	public String getNimi() {
		return this.nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public String getOsoite() {
		return this.osoite;
	}

	public void setOsoite(String osoite) {
		this.osoite = osoite;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Kauppa [id=" + id + ", nimi=" + nimi + ", osoite=" + osoite + "]";
	}

}
