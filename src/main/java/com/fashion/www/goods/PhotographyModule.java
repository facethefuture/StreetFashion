package com.fashion.www.goods;

public class PhotographyModule {
	private int id;
	private String name;
	private String coverImage;
	public PhotographyModule(int id,String name,String coverImage){
		this.id = id;
		this.name = name;
		this.coverImage = coverImage;
	}
	public int getId(){
		return this.id;
	}
	public String getName(){
		return this.name;
	}
	public String getCoverImage(){
		return this.coverImage;
	}
}
