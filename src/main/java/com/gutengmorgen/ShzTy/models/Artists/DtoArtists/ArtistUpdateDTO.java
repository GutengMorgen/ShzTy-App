package com.gutengmorgen.ShzTy.models.Artists.DtoArtists;

import java.sql.Date;
import java.util.Set;

import com.gutengmorgen.ShzTy.services.extras.InsertDTO;
import com.gutengmorgen.ShzTy.views.AllEntities;
import com.gutengmorgen.ShzTy.views.Extras.ForGUI;
import com.gutengmorgen.ShzTy.views.Extras.VarType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArtistUpdateDTO implements InsertDTO {
    @ForGUI(name = "Name:")
    private String name;
    @ForGUI(name = "Born Date:", type = VarType.DATE)
    private Date bornDate;
    @ForGUI(name = "Gender:")
    private String gender;
    @ForGUI(name = "Country:")
    private String country;
    @ForGUI(name = "Biography:")
    private String biography;
    @ForGUI(name = "Languages:", type = VarType.MULTI_OPTION, useEntity = AllEntities.Languages)
    private Set<Long> languageIDs;
    @ForGUI(name = "Genres:", type = VarType.MULTI_OPTION, useEntity = AllEntities.Genre)
    private Set<Long> genreIDs;
    
    @Override
    public String toString() {
	return "ArtistUpdateDTO [name=" + name + ", bornDate=" + bornDate + ", gender=" + gender + ", country="
		+ country + ", biography=" + biography + ", languageIDs=" + languageIDs + ", genreIDs=" + genreIDs
		+ "]";
    }
    
}
