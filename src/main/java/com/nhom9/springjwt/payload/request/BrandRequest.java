package com.nhom9.springjwt.payload.request;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BrandRequest {
    @NotBlank(message = "Name is required")
	private String name;

	@NotNull
	private Long id;

//    @NotNull
//	private Long product_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BrandRequest(@NotBlank(message = "Name is required") String name, @NotNull Long id) {
//    	, ,NotNull Long product_id   (tham số của hàm)
        this.name = name;
        this.id = id;
//        this.product_id = product_id;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public Long getProduct_id() {
//		return product_id;
//	}
//
//	public void setProduct_id(Long product_id) {
//		this.product_id = product_id;
//	}

	
    
}