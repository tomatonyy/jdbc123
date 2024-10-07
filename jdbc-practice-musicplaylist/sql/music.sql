-- 테이블 삭제
DROP TABLE IF EXISTS tbl_playlist CASCADE;
DROP TABLE IF EXISTS tbl_user CASCADE;
DROP TABLE IF EXISTS tbl_music CASCADE;


-- 테이블 생성
-- 음악(music) 테이블 생성
CREATE TABLE IF NOT EXISTS tbl_music
(
    music_code     INT AUTO_INCREMENT COMMENT '노래 식별코드',
    music_title    VARCHAR(30) NOT NULL COMMENT '제목',
    artist_name    VARCHAR(20) NOT NULL COMMENT '아티스트',
    CONSTRAINT pk_music_code PRIMARY KEY (music_code)
) ENGINE=INNODB COMMENT '음악';

-- 사용자(user) 테이블 생성
CREATE TABLE IF NOT EXISTS tbl_user
(
    user_id      VARCHAR(20) COMMENT '아이디',
    user_name    VARCHAR(20) NOT NULL COMMENT '이름',
    email        VARCHAR(30) NOT NULL COMMENT '이메일',
    CONSTRAINT pk_user_id PRIMARY KEY (user_id)
) ENGINE=INNODB COMMENT '회원';

-- 플레이리스트 테이블 생성
CREATE TABLE IF NOT EXISTS tbl_playlist
(
    music_code     INT COMMENT '노래 식별코드',
    user_id        VARCHAR(30) COMMENT '아이디',
    CONSTRAINT fk_playlist_user_id FOREIGN KEY (user_id) REFERENCES tbl_user (user_id),
    CONSTRAINT fk_playlist_music_code FOREIGN KEY (music_code) REFERENCES tbl_music (music_code)
) ENGINE=INNODB COMMENT '플레이리스트';

-- 데이터 삽입
INSERT INTO tbl_music VALUES (1, 'HAPPY', 'DAY6(데이식스)');
INSERT INTO tbl_music VALUES (2, 'Supernova', 'aespa');
INSERT INTO tbl_music VALUES (3, '내 이름 맑음', 'QWER');
INSERT INTO tbl_music VALUES (4, '소나기', '이클립스(ECLIPSE)');
INSERT INTO tbl_music VALUES (5, 'How Sweet', 'NewJeans');
INSERT INTO tbl_music VALUES (6, '첫 만남은 계획대로 되지 않아', 'TWS(투어스)');
INSERT INTO tbl_music VALUES (7, '해야(HEYA)', 'IVE(아이브)');
INSERT INTO tbl_music VALUES (8, '사건의 지평선', '윤하');

INSERT INTO tbl_user VALUES ('sun_di', '선동일', 'sun_di@greedy.com');
INSERT INTO tbl_user VALUES ('song_jk', '송종기', 'song_jk@greedy.com');
INSERT INTO tbl_user VALUES ('no_oc', '노옹철', 'no_oc@greedy.com');
INSERT INTO tbl_user VALUES ('song_eh', '송은희', 'song_eh@greedy.com');
INSERT INTO tbl_user VALUES ('yoo_js', '유재식', 'yoo_js@greedy.com');

INSERT INTO tbl_playlist VALUES (5, 'sun_di');
INSERT INTO tbl_playlist VALUES (6, 'sun_di');
INSERT INTO tbl_playlist VALUES (7, 'sun_di');



COMMIT;