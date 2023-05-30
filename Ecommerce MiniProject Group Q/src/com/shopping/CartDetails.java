package com.shopping;

public class CartDetails {
	private String productName;
    private int productId;
    private int productPrice;
    private int quantityToAdd;
    
	public CartDetails() {
		super();
	}

	public CartDetails(String productName, int productId, int productPrice, int quantityToAdd) {
		super();
		this.productName = productName;
		this.productId = productId;
		this.productPrice = productPrice;
		this.quantityToAdd = quantityToAdd;
	}

	@Override
	public String toString() {
		return "CartDetails [productName=" + productName + ", productId=" + productId + ", productPrice=" + productPrice
				+ ", quantityToAdd=" + quantityToAdd + "]";
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public int getQuantityToAdd() {
		return quantityToAdd;
	}

	public void setQuantityToAdd(int quantityToAdd) {
		this.quantityToAdd = quantityToAdd;
	}


}
