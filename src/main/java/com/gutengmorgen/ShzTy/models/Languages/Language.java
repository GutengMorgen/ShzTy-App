package com.gutengmorgen.ShzTy.models.Languages;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.gutengmorgen.ShzTy.models.Artists.Artist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "languages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_languages")
    private Long id;
    private String name;
    
    @ManyToMany(targetEntity = Artist.class, mappedBy = "languages")
    private Set<Artist> artists;
    
    public void addArtist(Artist a) {
	this.artists.add(a);
    }
    public void removeArtist(Artist a) {
	this.artists.remove(a);
    }
    public void removeArtists() {
	for (Artist a : artists) {
	    removeArtist(a);
	}
    }
}