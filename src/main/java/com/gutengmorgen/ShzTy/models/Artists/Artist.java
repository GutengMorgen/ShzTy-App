package com.gutengmorgen.ShzTy.models.Artists;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.gutengmorgen.ShzTy.models.Albums.Album;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.DtoCreateArtist;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.DtoUpdateArtist;
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
    
    
    public int tracksCount() {
	return this.albums.stream().mapToInt(Album::tracksCount).sum();
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
		+ country + ", biography=" + biography + ", albums=" + albums + "]";
    }
}
