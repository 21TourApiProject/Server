    -- 초기화 스크립트, 매번 db구동시 변하지 않는 값을 등록

-- 사용자
   INSERT INTO USER(USER_ID, REAL_NAME, SEX, BIRTH_DAY, MOBILE_PHONE_NUMBER, EMAIL, PASSWORD, NICK_NAME, SIGN_UP_DT) VALUES(1, '이세인', false, '2000-02-11', '01094069717', 'test1@gmail.com', 'dltpdls00*', '23in', '2021-08-01T15:41:20');
   INSERT INTO USER(USER_ID, REAL_NAME, SEX, BIRTH_DAY, MOBILE_PHONE_NUMBER, EMAIL, PASSWORD, NICK_NAME, SIGN_UP_DT) VALUES(2, '마시', true, '2000-02-11', '01011112222', 'test2@gmail.com', 'dltpdls00*', '마로', '2021-08-01T15:41:20');


--사용자 해시태그
    INSERT INTO MY_HASH_TAG(MY_HASH_TAG_LIST_ID, USER_ID, HASH_TAG_ID, HASH_TAG_NAME) VALUES(1, 1, 1, '공기 좋은');
    INSERT INTO MY_HASH_TAG(MY_HASH_TAG_LIST_ID, USER_ID, HASH_TAG_ID, HASH_TAG_NAME) VALUES(2, 1, 2, '분위기 있는');
    INSERT INTO MY_HASH_TAG(MY_HASH_TAG_LIST_ID, USER_ID, HASH_TAG_ID, HASH_TAG_NAME) VALUES(3, 1, 3, '깔끔한');
    INSERT INTO MY_HASH_TAG(MY_HASH_TAG_LIST_ID, USER_ID, HASH_TAG_ID, HASH_TAG_NAME) VALUES(4, 2, 4, '감성적인');
    INSERT INTO MY_HASH_TAG(MY_HASH_TAG_LIST_ID, USER_ID, HASH_TAG_ID, HASH_TAG_NAME) VALUES(5, 2, 5, '이색적인');
    INSERT INTO MY_HASH_TAG(MY_HASH_TAG_LIST_ID, USER_ID, HASH_TAG_ID, HASH_TAG_NAME) VALUES(6, 2, 6, '인생샷');

--사용자 찜(게시물)
--    INSERT INTO MY_WISH_POST(MY_WISH_POST_ID, USER_ID, POST_ID) VALUES();


-- 해시태그
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(1, '공기 좋은');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(2, '분위기 있는');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(3, '깔끔한');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(4, '감성적인');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(5, '이색적인');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(6, '인생샷');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(7, '전문적인');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(8, '캠핑');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(9, '차박');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(10, '뚜벅이');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(11, '드라이브');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(12, '자전거');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(13, '한적한');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(14, '근교');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(15, '도심 속');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(16, '연인');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(17, '가족');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(18, '친구');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(19, '혼자');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(20, '가성비');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(21, '소확행');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(22, '럭셔리한');