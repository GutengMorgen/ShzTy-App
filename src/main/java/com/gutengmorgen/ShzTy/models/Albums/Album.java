package com.gutengmorgen.ShzTy.models.Albums;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.gutengmorgen.ShzTy.models.AlbumFormats.AlbumFormat;
import com.gutengmorgen.ShzTy.models.Albums.DtoAlbums.DtoCreateAlbum;
import com.gutengmorgen.ShzTy.models.Albums.DtoAlbums.DtoReturnAlbum;
import com.gutengmorgen.ShzTy.models.Albums.DtoAlbums.DtoUpdateAlbum;
import com.gutengmorgen.ShzTy.models.Artists.Artist;
import com.gutengmorgen.ShzTy.models.Genres.Genre;
import com.gutengmorgen.ShzTy.models.Tracks.Track;
import com.gutengmorgen.ShzTy.views.DTO_MODEL;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "albums")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_albums")
    private Long id;
    private String title;
    private Date release_date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_artists")
    private Artist artist;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_album_formats")
    private AlbumFormat albumFormat;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "albums_genres", joinColumns = @JoinColumn(name = "album_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres = new HashSet<>();
    @OneToMany(mappedBy = "album")
    private Set<Track> tracks;

    public Album(DtoCreateAlbum dto) {
	this.title = dto.title();
	this.release_date = dto.releaseDate();
    }
    
    public void update(DtoUpdateAlbum dtoUpdateAlbum) {
        if(dtoUpdateAlbum.title() != null) this.title = dtoUpdateAlbum.title();
        if(dtoUpdateAlbum.releaseDate() != null) this.release_date = dtoUpdateAlbum.releaseDate();
    }
    
    public int tracksCount() {
	return this.tracks.size();
    }
    
    public void removeAllGenres() {
        for (Genre genre : genres) {
            this.genres.remove(genre);
        }
    }
    
    public static Class<?> findDtoClassByModel(DTO_MODEL model) {
	Class<?> dtoClass = null;

	if (model == DTO_MODEL.CREATE) dtoClass = DtoCreateAlbum.class;
	else if (model == DTO_MODEL.UPDATE) dtoClass = DtoUpdateAlbum.class;
	else if (model == DTO_MODEL.RETURN) dtoClass = DtoReturnAlbum.class;

	return dtoClass;
    }

    @Override
    public String toString() {
	return "Album [id=" + id + ", title=" + title + ", release_date=" + release_date + ", artist=" + artist.getId()
		+ ", albumFormat=" + albumFormat + ", genres=" + genres + ", tracks=" + tracks + "]";
    }
}
