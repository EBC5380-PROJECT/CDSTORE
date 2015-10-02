package com;
public class CD {

	private String cdId;
	private String cdTitle;
	private int cdPrice;
	private String cdCategory;
	
	public String getCdId(){
		return this.cdId;
	}
	
	public void setCdId(String cdId){
		this.cdId = cdId;
	}
	
	public String getCdTitle(){
		return this.cdTitle;
	}
	
	public void setCdTitle(String cdTitle){
		this.cdTitle = cdTitle;
	}
	
	public int getCdPrice(){
		return this.cdPrice;
	}
	
	public void setCdPrice(int cdPrice){
		this.cdPrice = cdPrice;
	}
	
	public String getCdCategory(){
		return this.cdCategory;
	}
	
	public void setCdCategory(String cdCategory){
		this.cdCategory = cdCategory;
	}
}
