package com.itheima.utils;

import java.util.List;

public class PageBean {

		private int pageNo=1;//��ǰҳ
		private int pageSize=3;//ÿҳ��¼��
		private int prep;//��һҳ
		private int nextp;//��һҳ
		private int totalRecordes;//�ܼ�¼��
		private int totalPage;//��ҳ��
		
		//Ϊ����Mysql����ʵ�ַ�ҳ  
		private int startIndex;//��ҳ����ʼ�±�
		
		private List recordes;//����ǰҳ�еļ�¼
		
		
		//Ϊ����ҳ������ʾһϵ������,���磺 3   4   5   6   7   8   9   10  11    ��ֻ��ʾ9����
		private int startpageNo;
		private int endpageNo;
		
		
		//Ϊ�˸�����ҳ���������
		private String url;

		
		public int getStartpageNo() {
			if(getTotalPage()<9){
				startpageNo=1;
				endpageNo=getTotalPage();//Ϊ�����£�����ô��
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
			startIndex = (pageNo-1)*pageSize;//��ҳ��ʼ��������Ϊ�˸� sql����õ�
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
