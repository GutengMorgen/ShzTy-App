package com.gutengmorgen.ShzTy.models.Artists.DtoArtists;

import java.util.HashSet;
import java.util.Set;

public record DtoSimpleTestingArtist(
	String name,
	String gender,
	String country,
	Set<String> languagesName) {
}
