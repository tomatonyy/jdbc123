package com.ohgiraffers.model.dao;

import com.ohgiraffers.model.dto.MusicDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import static com.ohgiraffers.common.JDBCTemplate.close;

public class MusicDAO {

    private Properties prop = new Properties();

    public MusicDAO() {                         // xml파일에서 sql 쿼리 로드
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/music-query.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // 맨 끝에 음악코드를 조회하는 메서드
    public int selectLastMusicCode(Connection con) {
        Statement stmt = null;
        ResultSet rset = null;

        int maxMusicCode = 0;

        String query = prop.getProperty("selectLastMusicCode");

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            if(rset.next()) {
                maxMusicCode = rset.getInt("MAX(MUSIC_CODE)");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(stmt);
        }
        return maxMusicCode;
    }

    // 음악 전체 조회하는 메서드
    public List<Map<Integer, String>> selectAllMusic(Connection con) {

        Statement stmt = null;
        ResultSet rset = null;
        List<Map<Integer, String>> musicList = null;

        String query = prop.getProperty("selectAllMusic");

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            musicList = new ArrayList<>();

            while(rset.next()) {
                Map<Integer, String> category = new HashMap<>();
                category.put(rset.getInt("MUSIC_CODE"), rset.getString("MUSIC_TITLE"));

                musicList.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(rset);
        }
        return  musicList;
    }

    // 음악 추가하는 메서드
    public int insertMusic(Connection con, MusicDTO newMusic) {          // 새로운 음악 추가하는 메서드

        PreparedStatement pstmt = null;                     //db에 명령 내리기위한 선언

        int result = 0;                                     // 추가 결과 저장할 변수

        String query = prop.getProperty("insertMusic");      // xml에서 추가기능있는 쿼리 불러오기

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, newMusic.getMusicCode());           // xml보면 첫번째 물음표자리에 노래 식별코드 넣기
            pstmt.setString(2, newMusic.getMusicTitle());
            pstmt.setString(3, newMusic.getArtistName());       // xml보면 세번째 물음표자리에 아티스트이름 넣기

            result = pstmt.executeUpdate();                                // 쿼리 실행하고 성공적으로 추가되면 result 값이 저장됨

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }
        return result;
    }

    // 음악 변경하는 메서드
    public int updateMusic(Connection con, MusicDTO updateMusic) {

        PreparedStatement pstmt = null;

        int result = 0;

        String query = prop.getProperty("updateMusic");      // xml에서 변경기능있는 쿼리 불러오기

        try {
            pstmt = con.prepareStatement(query);            //객체에 쿼리 준비
            pstmt.setInt(1, updateMusic.getMusicCode());
            pstmt.setString(2, updateMusic.getMusicTitle());
            pstmt.setString(3, updateMusic.getArtistName());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }
        return result;
    }

    // 음악 삭제하는 메서드
    public int deleteMusic(Connection con, MusicDTO deleteMusic) {

        PreparedStatement pstmt = null;

        int result = 0;

        String query = prop.getProperty("deleteMusic");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, deleteMusic.getMusicCode());         // 식별코드로만 삭제

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }
        return result;
    }

}
