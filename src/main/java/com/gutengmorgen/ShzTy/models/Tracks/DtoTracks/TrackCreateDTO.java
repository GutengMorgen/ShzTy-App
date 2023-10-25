package com.gutengmorgen.ShzTy.models.Tracks.DtoTracks;

import java.sql.Date;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.gutengmorgen.ShzTy.services.InsertDTO;
import com.gutengmorgen.ShzTy.views.Extras.ForGUI;
import com.gutengmorgen.ShzTy.views.Extras.ParmType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrackCreateDTO implements InsertDTO {
    @ForGUI(name = "Title:")
    @NotBlank(message = "Title is required")
    private String title;
    @ForGUI(name = "Release Date:", type = ParmType.DATE)
    @NotNull(message = "ReleaseDate is required")
    private Date releaseDate;
    @ForGUI(name = "Play Time:")
    @NotNull(message = "PlayTime is required")
    private int playTime;
//        String url,
    @ForGUI(name = "Notes:", type = ParmType.TEXT)
    private String notes;
    @ForGUI(name = "Genres:", type = ParmType.MULTI_OPTION, useEntity = "Genre")
    private Set<Long> genreIDs;
    @ForGUI(name = "Album:", type = ParmType.SINGLE_OPTION, useEntity = "Album")
    @NotNull(message = "AlbumId is required")
    private Long albumId;
    @ForGUI(name = "PlayList:", type = ParmType.SINGLE_OPTION, useEntity = "PlayList")
    @NotNull(message = "PlayListId is required")
    private Long playListId;

}
