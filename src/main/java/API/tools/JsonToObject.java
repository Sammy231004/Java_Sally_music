package API.tools;

import API.exceptions.ArtistNotFound;
import API.models.Album;
import API.models.Artist;
import API.models.Track;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.*;

import java.util.ArrayList;
import java.util.List;

public class JsonToObject {
    //Обработка чартов, по переданному параметры find(artist/track)
    public static List<?> processingChartResponse(String JSON, String find) throws JsonProcessingException, ArtistNotFound {
        JsonParser jsonParser = new JsonParser();
        JsonArray arrayElements = (JsonArray) jsonParser.parse(JSON)
                .getAsJsonObject().get("message")
                .getAsJsonObject().get("body")
                .getAsJsonObject().getAsJsonArray(find + "_list");

        if (find.equals("track"))
            return getTracks(arrayElements);

        else if (find.equals("artist"))
            return getArtist(arrayElements);

        else return null;
    }

    public static Track processingTrackResponse(String JSON) throws IllegalStateException {
        JsonParser jsonParser = new JsonParser();
        Gson gson = new GsonBuilder().create();
        JsonObject track = (JsonObject) jsonParser.parse(JSON)
                .getAsJsonObject().get("message")
                .getAsJsonObject().get("body")
                .getAsJsonObject().get("track");

        return gson.fromJson(track, Track.class);
    }

    public static String processingTextTrack(String JSON) {
        JsonParser jsonParser = new JsonParser();
        Gson gson = new GsonBuilder().create();

        JsonObject text = (JsonObject) jsonParser.parse(JSON)
                .getAsJsonObject().get("message")
                .getAsJsonObject().get("body")
                .getAsJsonObject().get("lyrics");

        return !text.get("lyrics_body").getAsString().equals("") ?
                text.get("lyrics_body").getAsString() : "Текст отстутствует";
    }

    //Обработка и поиск артиста по имени
    public static Artist processingSearchArtist(String JSON, String nameArtist) throws ArtistNotFound {
        Gson gson = new GsonBuilder().create();
        JsonParser jsonParser = new JsonParser();
        JsonArray arrayElements = (JsonArray) jsonParser.parse(JSON)
                .getAsJsonObject().get("message")
                .getAsJsonObject().get("body")
                .getAsJsonObject().getAsJsonArray("artist_list");

        for (var item : arrayElements) {
            var artist = item.getAsJsonObject().get("artist");
            if (nameArtist.equals(artist.getAsJsonObject().get("artist_name").getAsString())) {
                return gson.fromJson(artist, Artist.class);
            }
        }

        throw new ArtistNotFound("Артист " + nameArtist + " не найден");
    }

    public static List<Album> processingAlbumResponse(String JSON) {
        JsonParser jsonParser = new JsonParser();
        JsonArray arrayElements = (JsonArray) jsonParser.parse(JSON)
                .getAsJsonObject().get("message")
                .getAsJsonObject().get("body")
                .getAsJsonObject().getAsJsonArray("album_list");

        return getAlbums(arrayElements);
    }

    public static List<Track> processingTrackFromAlbum(String JSON){
        JsonParser jsonParser = new JsonParser();
        JsonArray arrayElements = (JsonArray) jsonParser.parse(JSON)
                .getAsJsonObject().get("message")
                .getAsJsonObject().get("body")
                .getAsJsonObject().getAsJsonArray("track_list");

        return getTracks(arrayElements);
    }

    //Для сериализации в список объектов Track
    private static List<Track> getTracks(JsonArray array) {
        List<Track> trackList = new ArrayList<>(10);
        Gson gson = new GsonBuilder().create();
        for (var item : array) {
            var track = item.getAsJsonObject().get("track");
            trackList.add(gson.fromJson(track, Track.class));
        }
        return trackList;
    }

    //Для обработки чарта артистов
    private static List<Artist> getArtist(JsonArray array) {
        List<Artist> artistList = new ArrayList<>(10);
        Gson gson = new GsonBuilder().create();

        for (var item : array) {
            var track = item.getAsJsonObject().get("artist");
            artistList.add(gson.fromJson(track, Artist.class));
        }
        return artistList;
    }

    //Для сериализации в список объектов Album
    private static List<Album> getAlbums(JsonArray array) {
        List<Album> albumList = new ArrayList<>(10);
        Gson gson = new GsonBuilder().create();

        for (var item : array){
            var album = item.getAsJsonObject().get("album");
            albumList.add(gson.fromJson(album, Album.class));
        }

        return albumList;
    }
}
