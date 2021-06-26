package it.polito.tdp.food.model;

public class ArcoPeso {
 private String s;
 private Integer peso;
public String getS() {
	return s;
}
public void setS(String s) {
	this.s = s;
}
public Integer getPeso() {
	return peso;
}
public void setPeso(Integer peso) {
	this.peso = peso;
}
public ArcoPeso(String s, Integer peso) {
	super();
	this.s = s;
	this.peso = peso;
}
@Override
public String toString() {
	return "ArcoPeso [s=" + s + ", peso=" + peso + "]";
}

 
}
