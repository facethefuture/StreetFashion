package com.fashion.www.page;

import java.util.List;

public class Page<T> {
	private int currentPage;
	private int perPage;
	private int totalRecord;
	private int totalPage;
	private List<T> dataList;
	public Page(){}
	public Page(int currentPage,int perPage,int totalRecord){
		this.currentPage = currentPage;
		this.perPage = perPage;
		this.totalRecord = totalRecord;
		if(this.totalRecord % this.perPage == 0){
			this.totalPage = this.totalRecord / this.perPage;
		} else {
			this.totalPage = this.totalRecord / this.perPage + 1;
		}
	}
	public int getCurrentPage(){
		return this.currentPage;
	}
	public int getPerPage(){
		return this.perPage;
	}
	public int getTotalPage(){
		return this.totalPage;
	}
	public List<T> getDataList(){
		return this.dataList;
	}
	public void setDataList(List<T> list){
		this.dataList = list;
	}
	public int getTotalRecord(){
		return this.totalRecord;
	}
}  
