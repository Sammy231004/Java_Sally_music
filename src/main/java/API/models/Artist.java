package API.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Artist {
    @SerializedName("artist_id")
    private int id;
    @SerializedName("artist_name")
    private String name;

    private List<Album> albumList;

    public Artist() {
    }

    public Artist(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Album> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
