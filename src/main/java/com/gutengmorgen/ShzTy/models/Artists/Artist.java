package com.gutengmorgen.ShzTy.models.Artists;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gutengmorgen.ShzTy.views.DtoTypes;

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
    
    
    public static Class<?> dtoRooter(DtoTypes dtoTypes) throws ClassNotFoundException {
	String packagePath = "com.gutengmorgen.ShzTy.models.Artists.DtoArtists.";
	Class<?> clazz = null;
	
	if(dtoTypes == DtoTypes.CREATE) {
	    String className = "DtoCreateArtist";
	    clazz = Class.forName(packagePath + className);
	}
	else if (dtoTypes == DtoTypes.UPDATE) {
	    String className = "DtoUpdateArtist";
	    clazz = Class.forName(packagePath + className);
	}
	else if (dtoTypes == DtoTypes.RETURN) {
	    String className = "DtoReturnArtist";
	    clazz = Class.forName(packagePath + className);
	}
	
	return clazz;
    }
    
    @Override
    public String toString() {
	return "Artist [id=" + id + ", name=" + name + ", born_date=" + born_date + ", gender=" + gender + ", country="
		+ country + ", biography=" + biography + "]";
    }
}
