package it.polito.tdp.food.model;

public class testModel {

	public static void main(String[] args) {
		Model model= new Model();
		model.creaGrafo(300.00);
		System.out.println(model.getNArchi()+"\n");
		System.out.println(model.getNVertici()+"\n");
	}

}
