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
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.DtoCreateArtist;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.DtoUpdateArtist;
import com.gutengmorgen.ShzTy.models.Genres.Genre;
import com.gutengmorgen.ShzTy.models.Languages.Language;
import com.gutengmorgen.ShzTy.views.DTO_MODEL;

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
    
    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(name = "artists_genres", joinColumns = @JoinColumn(name = "artist_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres = new HashSet<>();
    
    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(name = "artists_languages", joinColumns = @JoinColumn(name = "artist_id"), inverseJoinColumns = @JoinColumn(name = "language_id"))
    private Set<Language> languages = new HashSet<>();
    
    public Artist(DtoCreateArtist dto) {
	this.name = dto.Name();
        this.born_date = dto.BornDate();
        this.gender = dto.Gender();
        this.country = dto.Country();
        this.biography = dto.Biography();
    }
    
    public Artist(DtoUpdateArtist dto) {
	if(dto.Name() != null) this.name = dto.Name();
        if(dto.BornDate() != null) this.born_date = dto.BornDate();
        if(dto.Gender() != null) this.gender = dto.Gender();
        if(dto.Country() != null) this.country = dto.Country();
        if(dto.Biography() != null) this.biography = dto.Biography();
    }
    
    public void addGenre(Genre genre) {
        this.genres.add(genre);
        genre.getArtists().add(this);
    }
    public void removeGenre(Genre genre) {
        this.genres.remove(genre);
        genre.getArtists().remove(this);
    }
    public void removeGenres() {
        for (Genre genre : new HashSet<>(genres)) {
            removeGenre(genre);
        }
    }

    public void addLanguage(Language language) {
        this.languages.add(language);
        language.getArtists().add(this);
    }
    public void removeLanguage(Language language) {
        this.languages.remove(language);
        language.getArtists().remove(this);
    }
    public void removeLanguages() {
        for (Language language : new HashSet<>(languages)) {
            removeLanguage(language);
        }
    }
    
    public int tracksCount() {
	return 0;
    }
    
    public int albumsCount() {
	return this.albums.size();
    } 
    
    public static Class<?> findDtoClassByModel(DTO_MODEL model) {
	Class<?> dtoClass = null;
	
	if (model == DTO_MODEL.CREATE) dtoClass = DtoCreateArtist.class;
	else if (model == DTO_MODEL.UPDATE) dtoClass = DtoUpdateArtist.class;
	else if (model == DTO_MODEL.RETURN) dtoClass = null;

	return dtoClass;
    }

    @Override
    public String toString() {
	return "Artist [id=" + id + ", name=" + name + ", born_date=" + born_date + ", gender=" + gender + ", country="
		+ country + ", biography=" + biography + ", albums=" + albums + ", genres=" + genres + ", languages="
		+ languages + "]";
    }
}
