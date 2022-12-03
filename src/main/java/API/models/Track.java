package API.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public class Track {
    @SerializedName("track_id")
    private int Id;
    @SerializedName("track_name")
    private String name;
    @SerializedName("album_name")
    private String album;
    @SerializedName("artist_name")
    private String artist;


    public Track() {
    }

    public Track(int id,
                 String name,
                 String album,
                 String artist) {
        Id = id;
        this.name = name;
        this.album = album;
        this.artist = artist;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public String getAlbum() {
        return album;
    }

    public String getArtist() {
        return artist;
    }

    @Override
    public String toString() {
        return "Track{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", album='" + album + '\'' +
                ", artist='" + artist + '\'' +
                '}';
    }
}
