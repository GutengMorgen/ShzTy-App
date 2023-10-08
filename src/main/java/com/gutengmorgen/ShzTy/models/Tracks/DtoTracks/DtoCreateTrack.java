package com.gutengmorgen.ShzTy.models.Tracks.DtoTracks;

import java.sql.Date;

public record DtoCreateTrack(
	String title,
	Date releaseDate,
	int playTime,
	String notes) {

}
