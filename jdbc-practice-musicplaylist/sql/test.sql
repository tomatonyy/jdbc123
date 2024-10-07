-- 테이블 삭제
DROP TABLE IF EXISTS tbl_user CASCADE;

-- 테이블 생성

-- 사용자(user) 테이블 생성
CREATE TABLE IF NOT EXISTS tbl_user
(
    user_id      VARCHAR(20) COMMENT '아이디',
    user_name    VARCHAR(20) NOT NULL COMMENT '이름',
    email        VARCHAR(30) NOT NULL COMMENT '이메일',
    CONSTRAINT pk_user_id PRIMARY KEY (user_id)
    ) ENGINE=INNODB COMMENT '회원';

-- 데이터 삽입

INSERT INTO tbl_user VALUES ('sun_di', '선동일', 'sun_di@greedy.com');
INSERT INTO tbl_user VALUES ('song_jk', '송종기', 'song_jk@greedy.com');
INSERT INTO tbl_user VALUES ('no_oc', '노옹철', 'no_oc@greedy.com');
INSERT INTO tbl_user VALUES ('song_eh', '송은희', 'song_eh@greedy.com');
INSERT INTO tbl_user VALUES ('yoo_js', '유재식', 'yoo_js@greedy.com');

COMMIT;