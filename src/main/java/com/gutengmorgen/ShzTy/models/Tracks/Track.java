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
import com.gutengmorgen.ShzTy.models.Genres.Genre;
import com.gutengmorgen.ShzTy.models.PlayLists.PlayList;
import com.gutengmorgen.ShzTy.models.Tracks.DtoTracks.TrackCreateDTO;
import com.gutengmorgen.ShzTy.models.Tracks.DtoTracks.TrackUpdateDTO;

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
    
    public Track(TrackCreateDTO dto) {
        this.title = dto.getTitle();
        this.release_date = dto.getReleaseDate();
        this.play_time = dto.getPlayTime();
        this.notes = dto.getNotes();
    }
    
    public void update(TrackUpdateDTO dto) {
	if(dto.getTitle() != null) this.title = dto.getTitle();
	if(dto.getReleaseDate() != null) this.release_date = dto.getReleaseDate();
	if(dto.getPlayListId() != 0) this.play_time = dto.getPlayTime();
	if(dto.getNotes() != null) this.notes = dto.getNotes();
    }
    
    public void removeAllGenres() {
        for (Genre genre : genres) {
            this.genres.remove(genre);
        }
    }
    
    @Override
    public String toString() {
	return "Track [id=" + id + ", title=" + title + ", release_date=" + release_date + ", play_time=" + play_time
		+ ", notes=" + notes + ", genres=" + genres + ", album=" + album.getId() + ", playList=" + playList.getId() + "]";
    }
}
