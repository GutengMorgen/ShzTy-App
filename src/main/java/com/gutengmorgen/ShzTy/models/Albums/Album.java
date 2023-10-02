package com.gutengmorgen.ShzTy.models.Albums;

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
    private Date releaseDate;
    
    
    @Override
    public String toString() {
	return "Album [id=" + id + ", title=" + title + ", releaseDate=" + releaseDate + "]";
    }
    
    public static Class<?> find_DTOs(DTO_MODEL model) throws ClassNotFoundException {
	String packageName = "com.gutengmorgen.ShzTy.models.Albums.DtoAlbums";
	String classSimpleName = "";
	
	if(model == DTO_MODEL.CREATE) classSimpleName = "DtoCreateAlbum";
	else if (model == DTO_MODEL.UPDATE) classSimpleName = "DtoUpdateAlbum";
	else if (model == DTO_MODEL.RETURN) classSimpleName = "DtoReturnAlbum";

	return Class.forName(packageName + "." + classSimpleName);
    }
}
