package com.shopping;

public class ProductDetails {

    private Integer product_id;
    private String name;
    private Integer price;
    private String description;
    private Integer quantity;
    
    
	public ProductDetails() {
		super();	
	}
	
	public ProductDetails(Integer product_id, String name, Integer price, String description) {
		super();
		this.product_id = product_id;
		this.name = name;
		this.price = price;
		this.description = description;
	}


	public ProductDetails(Integer product_id, String name, Integer price, String description, Integer quantity) {
		super();
		this.product_id = product_id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ProductDetails [product_id=" + product_id + ", name=" + name + ", price=" + price + ", description="
				+ description + ", quantity=" + quantity + "]";
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
