package com.gutengmorgen.ShzTy.models.Genres;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.gutengmorgen.ShzTy.models.Albums.Album;
import com.gutengmorgen.ShzTy.models.Artists.Artist;
import com.gutengmorgen.ShzTy.models.Tracks.Track;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "genres")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genres")
    private Long id;
    private String name;
    @ManyToMany(targetEntity = Artist.class, mappedBy = "genres")
    private Set<Artist> artists;
    @ManyToMany(targetEntity = Album.class, mappedBy = "genres")
    private Set<Album> albums;
    @ManyToMany(targetEntity = Track.class, mappedBy = "genres")
    private Set<Album> tracks;
    
    @Override
    public String toString() {
	return "Genre [id=" + id + ", name=" + name + "]";
    }
}
