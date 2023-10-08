package com.gutengmorgen.ShzTy.models.PlayLists;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "playlists")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_playlists")
    private Long id;
    private String tile;
}
