DELIMITER $$ 
CREATE PROCEDURE myFunction()
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE (i <= 20) DO
        insert into product (product_name, product_brand, product_description, product_cost) values (concat("ADIDAS_TEST_",i), "ADIDAS", "description", i*10000); -- ⓓ 테이블에 i값 넣어주기
        SET i = i + 1; -- ⓔ i값에 1더해주고 WHILE문 처음으로 이동
    END WHILE;
END$$
DELIMITER ; 

drop procedure myFunction;
call myFunction();