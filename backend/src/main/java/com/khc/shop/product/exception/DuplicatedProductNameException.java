package com.khc.shop.product.exception;

public class DuplicatedProductNameException extends Exception{

    private final String status = "501";
    public DuplicatedProductNameException(String productName) {
        super("제품 명 : "+productName+" 은 이미 존재합니다. !");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public String getStatus(){
        return this.status;
    }
}
