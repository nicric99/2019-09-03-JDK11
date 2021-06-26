package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.db.FoodDao;


public class Model {
	
	FoodDao dao;
	//Map<Integer, Actor> actorsIdMap;
	Graph<String, DefaultWeightedEdge> grafo;
	
	public Model() {
		dao= new FoodDao();
		 
		
	}
	public void creaGrafo(Double calorie) {
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(this.grafo, dao.getVertici(calorie));
		for(Adiacenza a: dao.getArchi(calorie)) {
			if(this.grafo.vertexSet().contains(a.getS1()) && this.grafo.vertexSet().contains(a.getS2())) {
			Graphs.addEdge(this.grafo, a.getS1(), a.getS2(), a.getPeso());
			}
		}
	}
	public List<ArcoPeso> getConnessi(String tipo){
		List<ArcoPeso> result= new ArrayList<>();
		for( String s : Graphs.neighborListOf(this.grafo, tipo)){
			DefaultWeightedEdge e= this.grafo.getEdge(s, tipo);
			result.add(new ArcoPeso(s,(int)this.grafo.getEdgeWeight(e)));
		}
		return result;		
	}
	public Integer getNVertici() {
		return this.grafo.vertexSet().size();
	}
	public Integer getNArchi() {
		return this.grafo.edgeSet().size();
	}
	public Set<String> getTipi(){
		return this.grafo.vertexSet();
	}
	
	
}
