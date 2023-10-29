package com.gutengmorgen.ShzTy.models.Tracks.DtoTracks;

import java.sql.Date;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
public class TrackCreateDTO implements InsertDTO {
	@ForGUI(name = "Title*:", mandatory = true)
	@NotBlank()
	private String title;
	@ForGUI(name = "Release Date*:", type = VarType.DATE, mandatory = true)
	@NotNull()
	private Date releaseDate;
	@ForGUI(name = "Play Time*:", mandatory = true)
	@NotNull()
	private int playTime;
//        String url,
	@ForGUI(name = "Notes:", type = VarType.TEXT)
	private String notes;
	@ForGUI(name = "Genres:", type = VarType.MULTI_OPTION, useEntity = AllEntities.Genre)
	private Set<Long> genreIDs;
	@ForGUI(name = "Album*:", type = VarType.SINGLE_OPTION, useEntity = AllEntities.Album, mandatory = true)
	@NotNull()
	private Long albumId;
	@ForGUI(name = "PlayList*:", type = VarType.SINGLE_OPTION, useEntity = AllEntities.PlayList, mandatory = true)
	@NotNull()
	private Long playListId;

}
