package com.nhom9.springjwt.payload.response;

public class DashbroadResponse {
	public long totalUser;
	public long totalProduct;
	public long totalInvoice;
	public double totalTurnover;
	public DashbroadResponse(long totalUser, long totalProduct, long totalInvoice, double totalTurnover) {
		super();
		this.totalUser = totalUser;
		this.totalProduct = totalProduct;
		this.totalInvoice = totalInvoice;
		this.totalTurnover = totalTurnover;
	}
	public long getTotalUser() {
		return totalUser;
	}
	public void setTotalUser(long totalUser) {
		this.totalUser = totalUser;
	}
	public long getTotalProduct() {
		return totalProduct;
	}
	public void setTotalProduct(long totalProduct) {
		this.totalProduct = totalProduct;
	}
	public long getTotalInvoice() {
		return totalInvoice;
	}
	public void setTotalInvoice(long totalInvoice) {
		this.totalInvoice = totalInvoice;
	}
	public double getTotalTurnover() {
		return totalTurnover;
	}
	public void setTotalTurnover(double totalTurnover) {
		this.totalTurnover = totalTurnover;
	}
	
}
