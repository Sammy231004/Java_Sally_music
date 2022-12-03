package API.models;

import com.google.gson.annotations.SerializedName;

public class Artist {
    @SerializedName("artist_id")
    private int id;
    @SerializedName("artist_name")
    private String name;

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

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
