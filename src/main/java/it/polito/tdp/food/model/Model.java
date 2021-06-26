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
	
	private FoodDao dao;
	private Graph<String, DefaultWeightedEdge> grafo;
	private List<String> cammino;
	private Integer peso;
	private Integer N;
	
	public Model() {
		dao= new FoodDao();	
		peso=0;
		cammino = new ArrayList<>();
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
	public List<String> startCammino(String sorgente,Integer passi){
		List<String> parziale= new ArrayList<>();
		parziale.add(sorgente);
		Integer livello=0;
		this.N=passi;
		Double pes = (double) 0;
		cerca(parziale,livello);
		return this.cammino;
	}
	public void cerca(List<String> parziale,Integer livello){
		if(livello==this.N) {
			Integer pesoParziale= calcolaPeso(parziale);
			if(this.peso< pesoParziale) {
				this.peso=pesoParziale;
				this.cammino= new ArrayList<>(parziale);
			}
			return;
		}
		
		for(String s: Graphs.neighborListOf(this.grafo, parziale.get(parziale.size()-1))) {
			if(!parziale.contains(s)) {
			parziale.add(s);
			cerca(parziale,livello+1);
			parziale.remove(s);
			}
		}
		
	}
	public Integer calcolaPeso(List<String> parziale) {
		Integer peso=0;
		for(int i=0; i<parziale.size();i++) {
			if(i>0){
			DefaultWeightedEdge e= this.grafo.getEdge(parziale.get(i), parziale.get(i-1));
			peso=(int) (peso+this.grafo.getEdgeWeight(e));
			}
		}
		return peso;
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
	public Integer getPesoCammino() {
		return this.peso;
	}
	public Integer prova() {
		/*bottle (10 fl oz)
		cup
		Tablespoon*/
		DefaultWeightedEdge e= this.grafo.getEdge("Tablespoon", "cup");
		int peso1=(int) this.grafo.getEdgeWeight(e);
		return peso1;
	}
	
	
}
