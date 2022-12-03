package API;

import API.exceptions.ArtistNotFound;
import API.exceptions.TrackNotFound;
import API.models.Album;
import API.models.Artist;
import API.models.Track;
import API.tools.JsonToObject;
import API.tools.ReplaceString;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class MusixMatch {
    private final String apiKey;
    private final HttpClient client;

    public MusixMatch(String apiKey) {
        this.apiKey = apiKey;
        client = HttpClient.newHttpClient();
    }

    public List<Track> getChartTrack() throws IOException, InterruptedException, ArtistNotFound {
        String urlRequest = "https://api.musixmatch.com/ws/1.1/chart.tracks.get" +
                "?apikey="+apiKey+
                "&chart_name=top" +
                "&page=1" +
                "&page_size=11" +
                "&country=it" +
                "&f_has_lyrics=1";

        return (List<Track>) JsonToObject.processingChartResponse(sendRequest(urlRequest).body(), "track");
    }

    public List<Artist> getChartArtist() throws IOException, InterruptedException, ArtistNotFound {
        String urlRequest = "https://api.musixmatch.com/ws/1.1/chart.artists.get" +
                "?apikey="+apiKey+
                "&page=1" +
                "&page_size=10";

        return (List<Artist>)JsonToObject.processingChartResponse(sendRequest(urlRequest).body(), "artist");
    }

    public Track searchTrack(String artistName, String trackName) throws IOException, InterruptedException, TrackNotFound {
        String urlRequest = "http://api.musixmatch.com/ws/1.1/matcher.track.get" +
                "?apikey="+apiKey+
                "&q_artist="+ ReplaceString.replaceSpace(artistName) +
                "&q_track="+ReplaceString.replaceSpace(trackName);

        try{
            return JsonToObject.processingTrackResponse(sendRequest(urlRequest).body());
        }catch (IllegalStateException ex){
            throw new TrackNotFound("Трек не найден");
        }
    }

    public String getTextTrack(String artistName, String trackName) throws TrackNotFound, IOException, InterruptedException {
        Track track = searchTrack(artistName, trackName);
        String urlRequest = "http://api.musixmatch.com/ws/1.1/track.lyrics.get" +
                "?apikey="+apiKey+
                "&track_id="+track.getId();

        return JsonToObject.processingTextTrack(sendRequest(urlRequest).body());

    }

    public Artist searchArtist(String artistName) throws IOException, InterruptedException, ArtistNotFound {
        String urlRequest = "https://api.musixmatch.com/ws/1.1/artist.search?apikey="+apiKey+
                "&q_artist="+ReplaceString.replaceSpace(artistName) +
                "&page_size=10";

        return JsonToObject.processingSearchArtist(sendRequest(urlRequest).body(), artistName);
    }

    public List<Album> searchAlbumsByArtist(String artistName) throws ArtistNotFound, IOException, InterruptedException {
        Artist artist = searchArtist(artistName);

        String url = "http://api.musixmatch.com/ws/1.1/artist.albums.get" +
                "?apikey="+apiKey+
                "&artist_id="+artist.getId() +
                "&page_size=10";

        return JsonToObject.processingAlbumResponse(sendRequest(url).body());
    }

    public List<Track> getTracksFromAlbum(int idAlbum) throws IOException, InterruptedException {
        String url = "http://api.musixmatch.com/ws/1.1/album.tracks.get" +
                "?apikey="+apiKey+"" +
                "&album_id="+idAlbum +
                "&page=1&page_size=100";

        return JsonToObject.processingTrackFromAlbum(sendRequest(url).body());
    }

    private HttpResponse<String> sendRequest(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(URI.create(url)).GET().build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
