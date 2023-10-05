package com.gutengmorgen.ShzTy.models.Tracks;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.gutengmorgen.ShzTy.models.Albums.Album;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tracks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tracks")
    private Long id;
    private String title;
    private Date release_date;
    private int play_time;
    private String notes;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_albums")
    private Album album;
}
