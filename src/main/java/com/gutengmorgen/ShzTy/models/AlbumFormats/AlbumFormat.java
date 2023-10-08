package com.gutengmorgen.ShzTy.models.AlbumFormats;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.gutengmorgen.ShzTy.models.Albums.Album;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "album_formats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlbumFormat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_album_formats")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "albumFormat")
    private Set<Album> albums;

    @Override
    public String toString() {
	return "AlbumFormat [id=" + id + ", name=" + name + "]";
    }
}
