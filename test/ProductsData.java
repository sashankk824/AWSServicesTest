package test;


public class ProductsData {
	private int productID;
	private String productCode = "";
	private String name = "";
	private String prodDesc = "";
	private int quantity;
	private float price = 0.0f;

	public ProductsData() {
		
	}
	
	public void setProductID( int productID ) {
		this.productID = productID;
	}	
	
	public int getProductID() {
		return this.productID;
	}
	
	public void setProductCode( String productCode ) {
		this.productCode = productCode;
	}	
	
	public String getProductCode() {
		return this.productCode;
	}

	public void setName( String name ) {
		this.name = name;
	}	
	
	public String getName() {
		return this.name;
	}

	public void setProdDesc( String prodDesc ) {
		this.prodDesc = prodDesc;
	}	
	
	public String getProdDesc() {
		return this.prodDesc;
	}	
	
	public void setQuantity( int quantity ) {
		this.quantity = quantity;
	}	
	
	public int getQuantity() {
		return this.quantity;
	}

	public void setPrice( float price ) {
		this.price = price;
	}	
	
	public float getPrice() {
		return this.price;
	}

	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("productID ="+productID)
			.append(", productCode ="+productCode)
			.append(", name ="+name)
			.append(", prodDesc ="+prodDesc)			
			.append(", quantity ="+quantity)			
			.append(", price ="+price);			
		return strBuilder.toString();
	}
}
