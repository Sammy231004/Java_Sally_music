import API.MusixMatch;
import API.exceptions.ArtistNotFound;
import API.exceptions.TrackNotFound;
import API.models.Artist;
import API.models.Track;
import API.tools.ReplaceString;
import org.example.Main;
import org.example.helper.IsNumber;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestApplication {

    private final MusixMatch api = new MusixMatch("5f0ab8375d4a3ab3ac1617167cb538b7");

    @Test
    public void testReplaceSpace(){
        String test1 = "TestTest";
        String test2 = "Test test test l";
        String test3 = "Привет, это тестовая строка, для проверки";

        String result1 = "TestTest";
        String result2 = "Test%20test%20test%20l";
        String result3 = "Привет,%20это%20тестовая%20строка,%20для%20проверки";

        Assertions.assertEquals(ReplaceString.replaceSpace(test1), result1);
        Assertions.assertEquals(ReplaceString.replaceSpace(test2), result2);
        Assertions.assertEquals(ReplaceString.replaceSpace(test3), result3);
    }

    @Test
    public void testIsNumber(){
        String realNumber = "93012";
        String noNumber = "A?2031";

        Assertions.assertTrue(IsNumber.isNumber(realNumber));
        Assertions.assertFalse(IsNumber.isNumber(noNumber));
    }

    @Test
    public void testAPIWork(){
        Assertions.assertDoesNotThrow(()->{
            api.getChartTrack();
        });
    }

    @Test
    public void testGetChartTrack() throws ArtistNotFound, IOException, InterruptedException {
        Assertions.assertNotNull(api.getChartTrack());
    }

    @Test
    public void testGetChartArtist() throws ArtistNotFound, IOException, InterruptedException {
        Assertions.assertNotNull(api.getChartArtist());
    }

    @Test
    public void testSearchTrack() throws TrackNotFound, IOException, InterruptedException {
        String findArtistValid = "Eminem";
        String findTrackValid = "Godzilla";
        String artistNonValid = "Test";
        String trackNotValid = "Test1324";

        Assertions.assertInstanceOf(Track.class, api.searchTrack(findArtistValid, findTrackValid));
        Assertions.assertThrows(TrackNotFound.class, ()-> api.searchTrack(artistNonValid, trackNotValid));
    }

    @Test
    public void testSearchArtist() throws ArtistNotFound, IOException, InterruptedException {
        String findValidArtist = "Eminem";
        Assertions.assertInstanceOf(Artist.class, api.searchArtist(findValidArtist));
        Assertions.assertEquals(findValidArtist, api.searchArtist(findValidArtist).getName());

        String nonValidArtist = "Test19313";
        Assertions.assertThrows(ArtistNotFound.class, ()->api.searchArtist(nonValidArtist));
    }

    @Test
    public void testSearchAlbumsByArtist() throws ArtistNotFound, IOException, InterruptedException {
        String validArtist = "Eminem";
        String notValidArtist = "Test130";

        Assertions.assertNotNull(api.searchAlbumsByArtist(validArtist));
        Assertions.assertThrows(ArtistNotFound.class, ()->api.searchAlbumsByArtist(notValidArtist));
    }

    @Test
    public void testGetTracksFromAlbum() throws IOException, InterruptedException {
        int albumId = 36300447;
        int albumIdNotValid = 93913021;
        int albumIdNotValid2 = -30192;

        Assertions.assertNotNull(api.getTracksFromAlbum(albumId));
        Assertions.assertThrows(Exception.class, ()->api.getTracksFromAlbum(albumIdNotValid));
        Assertions.assertThrows(Exception.class, ()->api.getTracksFromAlbum(albumIdNotValid2));
    }

    @Test
    public void testStartApp() {
        Assertions.assertDoesNotThrow(Main::start);
    }
}
