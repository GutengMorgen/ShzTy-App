package com.gutengmorgen.ShzTy.models.Artists;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
    
    
    @Override
    public String toString() {
	return "Artist [id=" + id + ", name=" + name + ", born_date=" + born_date + ", gender=" + gender + ", country="
		+ country + ", biography=" + biography + "]";
    }
    
    public static Class<?> find_DTOs(DTO_MODEL model) throws ClassNotFoundException {
	String packageName = "com.gutengmorgen.ShzTy.models.Artists.DtoArtists";
	String classSimpleName = "";
	
	if(model == DTO_MODEL.CREATE) classSimpleName = "DtoCreateArtist";
	else if (model == DTO_MODEL.UPDATE) classSimpleName = "DtoUpdateArtist";
	else if (model == DTO_MODEL.RETURN) classSimpleName = "DtoReturnArtist";

	return Class.forName(packageName + "." + classSimpleName);
    }
}
