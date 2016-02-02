package com.itheima.utils;

import java.util.List;

public class PageBean {

		private int pageNo=1;//当前页
		private int pageSize=3;//每页记录数
		private int prep;//上一页
		private int nextp;//下一页
		private int totalRecordes;//总记录数
		private int totalPage;//总页数
		
		//为了让Mysql更好实现分页  
		private int startIndex;//分页的起始下标
		
		private List recordes;//代表当前页中的记录
		
		
		//为了在页面能显示一系列数字,比如： 3   4   5   6   7   8   9   10  11    （只显示9个）
		private int startpageNo;
		private int endpageNo;
		
		
		//为了更好在页面控制链接
		private String url;

		
		public int getStartpageNo() {
			if(getTotalPage()<9){
				startpageNo=1;
				endpageNo=getTotalPage();//为了少事，才这么干
			}else{
				startpageNo=pageNo-4;
				endpageNo=pageNo+4;
				if(startpageNo<=1){
					startpageNo=1;
					endpageNo=startpageNo+8;
				}
				if(endpageNo>=getTotalPage()){
					endpageNo=getTotalPage();
					startpageNo = endpageNo-8;
				}
			}
			return startpageNo;
		}
		public void setStartpageNo(int startpageNo) {
			this.startpageNo = startpageNo;
		}
		public int getEndpageNo() {
			return endpageNo;
		}

		public void setEndpageNo(int endpageNo) {
			this.endpageNo = endpageNo;
		}

		public int getPageNo() {
			return pageNo;
		}

		public void setPageNo(int pageNo) {
			this.pageNo = pageNo;
		}

		public int getPageSize() {
			return pageSize;
		}

		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}

		public int getPrep() {
			if(pageNo<=1){
				prep=1;
			}else{
				prep=pageNo-1;
			}
			return prep;
		}

		public void setPrep(int prep) {
			this.prep = prep;
		}

		public int getNextp() {
			if(pageNo>=getTotalPage()){
				nextp= getTotalPage();
			}else{
				nextp=pageNo+1;
			}
			return nextp;
		}

		public void setNextp(int nextp) {
			this.nextp = nextp;
		}

		public int getTotalRecordes() {
			return totalRecordes;
		}

		public void setTotalRecordes(int totalRecordes) {
			this.totalRecordes = totalRecordes;
		}

		public int getTotalPage() {
			if(totalRecordes%pageSize==0){
				totalPage = totalRecordes/pageSize;
			}else {
				totalPage = totalRecordes/pageSize+1;
			}
			return totalPage;
		}

		public void setTotalPage(int totalPage) {
			this.totalPage = totalPage;
		}

		public int getStartIndex() {
			startIndex = (pageNo-1)*pageSize;//分页起始索引，是为了给 sql语句用的
			return startIndex;
		}

		public void setStartIndex(int startIndex) {
			this.startIndex = startIndex;
		}

		public List getRecordes() {
			return recordes;
		}

		public void setRecordes(List recordes) {
			this.recordes = recordes;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
}
