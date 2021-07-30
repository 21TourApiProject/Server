    -- 초기화 스크립트, 매번 db구동시 변하지 않는 값을 등록

--    insert into user(user_id,email,nick_name,mobile_phone_number)values(1, 'test1@gmail.com', 'sein1', '010-1111-1111');
--    insert into user(user_id,email,nick_name,mobile_phone_number)values(2, 'test2@gmail.com', 'sein2', '010-2222-2222');
--    insert into user(user_id,email,nick_name,mobile_phone_number)values(3, 'test3@gmail.com', 'sein3', '010-3333-3333');

-- 해시태그
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(1, '조용한');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(2, '시끄러운');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(3, '사람이 많은');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(4, '사람이 적은');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(5, '저렴한');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(6, '럭셔리한');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(7, '교통이 편한');