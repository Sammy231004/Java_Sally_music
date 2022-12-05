package org.example.processingData;

import API.MusixMatch;
import API.exceptions.ArtistNotFound;
import API.exceptions.TrackNotFound;
import API.models.Album;
import API.models.Artist;
import API.models.Track;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ProcessingResponse {

    private final MusixMatch API;

    public ProcessingResponse(MusixMatch API) {
        this.API = API;
    }

    public String chartMusic() throws ArtistNotFound, IOException, InterruptedException {
        List<Track> trackList = API.getChartTrack();
        StringBuilder chartMusic = new StringBuilder();

        for (var track:trackList){
            chartMusic.append(track.getName()).append(" <===> ").append(track.getArtist()).append("\n\n");
        }

        return chartMusic.toString();
    }

    public String chartArtist() throws ArtistNotFound, IOException, InterruptedException {
        List<Artist> artistList = API.getChartArtist();

        StringBuilder chartArtist = new StringBuilder();

        for (var artist: artistList)
            chartArtist.append(artist.getName()).append("\n\n");

        return chartArtist.toString();
    }

    public String findTrack(String authorName, String trackName) throws TrackNotFound, IOException, InterruptedException {
        String text = API.getTextTrack(authorName, trackName);
        return "Текст "+trackName+":\n"+text;
    }

    public Artist findArtist(String artistName) throws ArtistNotFound, IOException, InterruptedException {
        Artist findArtist = API.searchArtist(artistName);
        List<Album> albumsList = API.searchAlbumsByArtist(findArtist.getName());

        findArtist.setAlbumList(albumsList);

        return findArtist;
    }

    public String getTracksFromAlbum(int albumId, String artist) throws IOException, InterruptedException {
        List<Track> trackList = API.getTracksFromAlbum(albumId);
        StringBuilder stringBuilder = new StringBuilder("Author - ");
        stringBuilder.append(artist).append(" +\n");

        for (var item: trackList){
            stringBuilder.append(item.getName()).append("\n");
        }

        return stringBuilder.toString();
    }


}
