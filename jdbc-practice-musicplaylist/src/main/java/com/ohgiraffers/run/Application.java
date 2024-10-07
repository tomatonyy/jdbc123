package com.ohgiraffers.run;

import com.ohgiraffers.model.dao.MusicDAO;
import com.ohgiraffers.model.dto.MusicDTO;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application {

    public static void main(String[] args) {

        /* 1. 노래의 마지막 번호 조회 */
        Connection con = getConnection();
        MusicDAO registDAO = new MusicDAO();

        int maxMusicCode = registDAO.selectLastMusicCode(con);

        System.out.println("maxMusicCode = " + maxMusicCode);

        /* 2. 음악리스트 조회 */
        List<Map<Integer, String>> musicList = registDAO.selectAllMusic(con);

        for (Map<Integer, String> music : musicList) {
            System.out.println("music = " + music);
        }

        /* 3. 신규 음악 등록 */
        /* 3-1. 신규 음악 등록을 위한 정보 입력 */
        Scanner sc = new Scanner(System.in);

        System.out.print("신규 음악을 등록합니다. 노래 제목을 입력해주세요 : ");
        String musicTitle = sc.nextLine();
        System.out.print("노래의 아티스트 이름을 입력해주세요 : ");
        String artistName = sc.nextLine();
        System.out.print("바로 노래 목록에 등록하시겠습니까? (예/아니오) : ");
        String answer = sc.nextLine();

        /* 3-2. 신규 음악 등록을 위한 값 가공 */
        int musicCode = maxMusicCode + 1;
        MusicDTO newMusic = new MusicDTO(musicCode, musicTitle, artistName);

        /* 3-3. 신규 음악 등록을 위한 메소드 호출하여 등록 */
        int result = registDAO.insertMusic(con, newMusic);

        if (result > 0) {
            System.out.println("신규 노래 등록 성공!😎");
        } else {
            System.out.println("신규 노래 등록 실패😥");
        }

        /* Update 음악 변경시킬 때 */

        System.out.print("변경할 노래 식별코드를 입력해주세요 : ");
        int musiccode = sc.nextInt();
        System.out.print("변경할 노래 제목을 입력하세요 : ");
        sc.nextLine();
        String musictitle = sc.nextLine();
        System.out.print("변경할 노래 가수 이름을 입력하세요 : ");
        String artistname = sc.nextLine();

        MusicDTO updateMusic = new MusicDTO();
        int updateResult = registDAO.updateMusic(con, updateMusic);

        if(updateResult > 0) {
            System.out.println("노래 변경 성공!😎");
        } else {
            System.out.println("노래 변경 실패😥");
        }

        /* Delete 음악 삭제시킬 때 */

        System.out.print("삭제할 노래 식별코드를 입력해주세요 : ");
        int deleteMusicCode = sc.nextInt();

        MusicDTO deleteMusic = new MusicDTO();

        int deleteResult = registDAO.deleteMusic(con, deleteMusic);

        if (deleteResult > 0) {
            System.out.println("노래 삭제 성공!😎");
        } else {
            System.out.println("노래 삭제 실패😥");
        }

    }
}
