package com.hurtownia.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.web.multipart.MultipartFile;

public class Byczki {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private Long id;
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNazwa() {
		return Nazwa;
	}


	public void setNazwa(String nazwa) {
		Nazwa = nazwa;
	}


	public String getWłaściciel() {
		return właściciel;
	}


	public void setWłaściciel(String właściciel) {
		this.właściciel = właściciel;
	}


	public String getWiek() {
		return wiek;
	}


	public void setWiek(String wiek) {
		this.wiek = wiek;
	}


	public String getKategoria() {
		return kategoria;
	}


	public void setKategoria(String kategoria) {
		this.kategoria = kategoria;
	}


	public double getShippingWeight() {
		return shippingWeight;
	}


	public void setShippingWeight(double shippingWeight) {
		this.shippingWeight = shippingWeight;
	}


	public double getListPrice() {
		return listPrice;
	}


	public void setListPrice(double listPrice) {
		this.listPrice = listPrice;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public String getOpis() {
		return Opis;
	}


	public void setOpis(String opis) {
		Opis = opis;
	}


	public int getInStockNuber() {
		return inStockNuber;
	}


	public void setInStockNuber(int inStockNuber) {
		this.inStockNuber = inStockNuber;
	}


	public MultipartFile getByczkiImage() {
		return byczkiImage;
	}


	public void setByczkiImage(MultipartFile byczkiImage) {
		this.byczkiImage = byczkiImage;
	}


	private String Nazwa;
	private String właściciel;
	private String wiek;
	private String kategoria;
	private  double shippingWeight;
	private double listPrice;
	private boolean active=true;
	
	@Column(columnDefinition="text")
	private String Opis;
	private int inStockNuber;
	

	private MultipartFile byczkiImage;
}
