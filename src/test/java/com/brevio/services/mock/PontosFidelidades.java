package com.brevio.services.mock;

public class PontosFidelidades {

	private String id;
	private Long points;
	
	
	
	public PontosFidelidades(String id, Long points) {
		super();
		this.id = id;
		this.points = points;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getPoints() {
		return points;
	}
	public void setPoints(Long points) {
		this.points = points;
	}
	
	
}
