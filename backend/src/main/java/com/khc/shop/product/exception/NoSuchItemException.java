package com.khc.shop.product.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class NoSuchItemException extends DataIntegrityViolationException {
    // update 나 특정 조건을 통해 데이터를 수정/ 삭제 해야하는 경우
    // 해당 조건에 부합하는 데이터가 없을때 발생
    private final String status = "502";

    public NoSuchItemException() {
        super("잘못된 접근입니다.");
    }

    public String getStatus(){
        return this.status;
    }
}
