package com.ohgiraffers.model.dto;

public class MusicDTO {

    private int musicCode;
    private String musicTitle;
    private String artistName;

    public MusicDTO() {}

    //constructor
    public MusicDTO(int musicCode, String musicTitle, String artistName) {
        this.musicCode = musicCode;
        this.musicTitle = musicTitle;
        this.artistName = artistName;
    }

    //setter, getter
    public int getMusicCode() {
        return musicCode;
    }

    public void setMusicCode(int musicCode) {
        this.musicCode = musicCode;
    }

    public String getMusicTitle() {
        return musicTitle;
    }

    public void setMusicTitle(String musicTitle) {
        this.musicTitle = musicTitle;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    //toString()
    @Override
    public String toString() {
        return "MusicDTO{" +
                "musicCode=" + musicCode +
                ", musicTitle='" + musicTitle + '\'' +
                ", artistName='" + artistName + '\'' +
                '}';
    }
}
