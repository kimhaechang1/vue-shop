package com.khc.shop.product.exception;

public class DuplicatedNameException extends Exception{

    private final String status = "501";
    public DuplicatedNameException(String name) {
        super("name : "+name+" 은 이미 존재합니다. !");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public String getStatus(){
        return this.status;
    }
}
