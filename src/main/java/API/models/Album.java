package API.models;

import com.google.gson.annotations.SerializedName;

public class Album {
    @SerializedName("album_id")
    private int id;
    @SerializedName("album_name")
    private String name;

    public Album() {
    }

    public Album(int id, String name) {
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
        return "Album{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
