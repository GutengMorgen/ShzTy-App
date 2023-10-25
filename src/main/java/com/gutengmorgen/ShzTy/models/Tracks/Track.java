package com.gutengmorgen.ShzTy.models.Tracks;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.gutengmorgen.ShzTy.models.Albums.Album;
import com.gutengmorgen.ShzTy.models.Albums.DtoAlbums.AlbumCreateDTO;
import com.gutengmorgen.ShzTy.models.Albums.DtoAlbums.AlbumReturnDTO;
import com.gutengmorgen.ShzTy.models.Albums.DtoAlbums.AlbumUpdateDTO;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.DtoUpdateArtist;
import com.gutengmorgen.ShzTy.models.Genres.Genre;
import com.gutengmorgen.ShzTy.models.PlayLists.PlayList;
import com.gutengmorgen.ShzTy.models.Tracks.DtoTracks.DtoCreateTrack;
import com.gutengmorgen.ShzTy.models.Tracks.DtoTracks.DtoUpdateTrack;
import com.gutengmorgen.ShzTy.views.Extras.ModelDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tracks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tracks")
    private Long id;
    private String title;
    private Date release_date;
    private int play_time;
    private String notes;
    @ManyToMany(cascade =  CascadeType.MERGE)
    @JoinTable(name = "tracks_genres", joinColumns = @JoinColumn(name = "track_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres = new HashSet<>();
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_albums")
    private Album album;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_playlists")
    private PlayList playList;
    
    public Track(DtoCreateTrack dto) {
        this.title = dto.title();
        this.release_date = dto.releaseDate();
        this.play_time = dto.playTime();
        this.notes = dto.notes();
    }
    
    public void update(DtoUpdateTrack dto) {
	if(dto.title() != null) this.title = dto.title();
	if(dto.releaseDate() != null) this.release_date = dto.releaseDate();
	if(dto.playTime() != 0) this.play_time = dto.playTime();
	if(dto.notes() != null) this.notes = dto.notes();
    }
    
    public void removeAllGenres() {
        for (Genre genre : genres) {
            this.genres.remove(genre);
        }
    }
    
    public static Class<?> findDtoClassByModel(ModelDTO model) {
	Class<?> dtoClass = null;

	if (model == ModelDTO.CREATE) dtoClass = DtoCreateTrack.class;
	else if (model == ModelDTO.UPDATE) dtoClass = DtoUpdateTrack.class;

	return dtoClass;
    }

    @Override
    public String toString() {
	return "Track [id=" + id + ", title=" + title + ", release_date=" + release_date + ", play_time=" + play_time
		+ ", notes=" + notes + ", genres=" + genres + ", album=" + album.getId() + ", playList=" + playList.getId() + "]";
    }
}
