package com.model2.mvc.service.domain;

import java.sql.Date;

public class Purchase {


	///Field
	private int rowNum;
	private User buyer;//ȸ�� ���̵�
	private String divyAddr;//��� �ּ�
	private String divyDate;//��������
	private String divyRequest;//��ۿ�û����
	private Date orderDate;//�ֹ��Ͻ�
	private String paymentOption;//���� ���
	private Product purchaseProd;//
	private String receiverName;//������
	private String receiverPhone;//������ ��ȣ
	private String tranCode;//�ڵ�
	private int tranNo;//��ȣ
	private int prodCount;	
	
	public Purchase() {
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	public String getDivyAddr() {
		return divyAddr;
	}

	public void setDivyAddr(String divyAddr) {
		this.divyAddr = divyAddr;
	}

	public String getDivyDate() {
		return divyDate;
	}

	public void setDivyDate(String divyDate) {
		this.divyDate = divyDate.replace("-","");
	}

	public String getDivyRequest() {
		return divyRequest;
	}

	public void setDivyRequest(String divyRequest) {
		this.divyRequest = divyRequest;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getPaymentOption() {
		return paymentOption;
	}

	public void setPaymentOption(String paymentOption) {
		this.paymentOption = paymentOption;
	}

	public Product getPurchaseProd() {
		return purchaseProd;
	}

	public void setPurchaseProd(Product purchaseProd) {
		this.purchaseProd = purchaseProd;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public String getTranCode() {
		return tranCode;
	}

	public void setTranCode(String tranCode) {
		this.tranCode = tranCode;
	}

	public int getTranNo() {
		return tranNo;
	}

	public void setTranNo(int tranNo) {
		this.tranNo = tranNo;
	}

	public int getProdCount() {
		return prodCount;
	}

	public void setProdCount(int prodCount) {
		this.prodCount = prodCount;
	}

	@Override
	public String toString() {
		return "Purchase [rowNum=" + rowNum + ", buyer=" + buyer + ", divyAddr=" + divyAddr + ", divyDate=" + divyDate
				+ ", divyRequest=" + divyRequest + ", orderDate=" + orderDate + ", paymentOption=" + paymentOption
				+ ", purchaseProd=" + purchaseProd + ", receiverName=" + receiverName + ", receiverPhone="
				+ receiverPhone + ", tranCode=" + tranCode + ", tranNo=" + tranNo + ", prodCount=" + prodCount + "]";
	}
	



}
	

