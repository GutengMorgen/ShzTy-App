package com.gutengmorgen.ShzTy.models.Artists;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.gutengmorgen.ShzTy.models.Albums.Album;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.ArtistCreateDTO;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.ArtistUpdateDTO;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.DtoCreateArtist;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.DtoUpdateArtist;
import com.gutengmorgen.ShzTy.models.Genres.Genre;
import com.gutengmorgen.ShzTy.models.Languages.Language;
import com.gutengmorgen.ShzTy.services.InsertDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "artists")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_artists")
    private Long id;
    @Column(unique = true)
    private String name;
    private Date born_date;
    private String gender;
    private String country;
    private String biography;
    @OneToMany(mappedBy = "artist")
    private Set<Album> albums;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "artists_genres", joinColumns = @JoinColumn(name = "artist_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres = new HashSet<>();
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "artists_languages", joinColumns = @JoinColumn(name = "artist_id"), inverseJoinColumns = @JoinColumn(name = "language_id"))
    private Set<Language> languages = new HashSet<>();
    
    public Artist(DtoCreateArtist dto) {
	this.name = dto.name();
        this.born_date = dto.bornDate();
        this.gender = dto.gender();
        this.country = dto.country();
        this.biography = dto.biography();
    }
    
    public Artist(ArtistCreateDTO dto) {
	this.name = dto.getName();
        this.born_date = dto.getBornDate();
        this.gender = dto.getGender();
        this.country = dto.getCountry();
        this.biography = dto.getBiography();
    }
    
    public void update(ArtistUpdateDTO dto) {
	if(dto.getName() != null) this.name = dto.getName();
        if(dto.getBornDate() != null) this.born_date = dto.getBornDate();
        if(dto.getGender() != null) this.gender = dto.getGender();
        if(dto.getCountry() != null) this.country = dto.getCountry();
        if(dto.getBiography() != null) this.biography = dto.getBiography();
    }
    
    public void removeAllGenres() {
        for (Genre genre : genres) {
            this.genres.remove(genre);
        }
    }
    
    public void removeAllLanguages() {
        for (Language language : languages) {
            this.languages.remove(language);
        }
    }
    
    public int albumsCount() {
	return this.albums.size();
    }

    @Override
    public String toString() {
	return "Artist [id=" + id + ", name=" + name + ", born_date=" + born_date + ", gender=" + gender + ", country="
		+ country + ", biography=" + biography + ", albums=" + albums + ", genres=" + genres + ", languages="
		+ languages + "]";
    }
}
