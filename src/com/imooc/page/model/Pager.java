package com.imooc.page.model;

import java.io.Serializable;
import java.util.List;

public class Pager<T> implements Serializable{
	
	private static final long serialVersionUID = -8741766802354222579L;

	private int pageSize;
	private int currentPage;
	private int totalRecord;
	private int totalPage;
	private List<T> dataList;
	
	public Pager() {
		super();
	}
	
	public Pager(int pageNum, int pageSize, List<T> sourceList){
		if(sourceList == null){
			return;
		}
		
		//总纪录条数
		this.totalRecord = sourceList.size();
		
		//每页显示多少条纪录
		this.pageSize = pageSize;
		
		//获取总页数
		this.totalPage = this.totalRecord/this.pageSize;
		if(this.totalRecord % this.pageSize != 0){
			this.totalPage = this.totalPage + 1;
		}
		
		//当前第几页数据
		if(this.totalPage < pageNum){
			this.currentPage = this.totalPage;
		}else{
			this.currentPage = pageNum;
		}
		
		System.out.println(sourceList.toString());
		//起始索引
		int fromIndex = this.pageSize * (this.currentPage-1);
		
		//结束索引
		int toIndex;
		if(this.pageSize * this.currentPage > this.totalRecord){
			toIndex = this.totalRecord;
		}else{
			toIndex = this.pageSize * this.currentPage;
		}
		
		this.dataList = sourceList.subList(fromIndex, toIndex);
		
	}
	
	public Pager(int pageSize, int currentPage, int totalRecord, int totalPage, List<T> dataList) {
		super();
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.totalRecord = totalRecord;
		this.totalPage = totalPage;
		this.dataList = dataList;
	}

	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getDataList() {
		return dataList;
	}
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	
}

