package com.gutengmorgen.ShzTy.views;

import com.gutengmorgen.ShzTy.models.Albums.DtoAlbums.AlbumCreateDTO;
import com.gutengmorgen.ShzTy.models.Albums.DtoAlbums.AlbumUpdateDTO;
import com.gutengmorgen.ShzTy.models.Albums.DtoAlbums.AlbumViewModel;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.ArtistCreateDTO;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.ArtistUpdateDTO;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.ArtistViewModel;
import com.gutengmorgen.ShzTy.models.Tracks.DtoTracks.TrackCreateDTO;
import com.gutengmorgen.ShzTy.models.Tracks.DtoTracks.TrackUpdateDTO;
import com.gutengmorgen.ShzTy.models.Tracks.DtoTracks.TrackViewModel;
import com.gutengmorgen.ShzTy.services.AlbumService;
import com.gutengmorgen.ShzTy.services.ArtistService;
import com.gutengmorgen.ShzTy.services.TrackService;
import com.gutengmorgen.ShzTy.views.Components.CustomTable;
import com.gutengmorgen.ShzTy.views.Components.TablePopupMenu;
import com.gutengmorgen.ShzTy.views.TableModel.AlbumTableModel;
import com.gutengmorgen.ShzTy.views.TableModel.ArtistTableModel;
import com.gutengmorgen.ShzTy.views.TableModel.TrackTableModel;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends TabExtension {
	private static final long serialVersionUID = 1L;

	private CustomTable<ArtistViewModel> tableArtist;
	private CustomTable<AlbumViewModel> tableAlbum;
	private CustomTable<TrackViewModel> tableTrack;
	private JTextField textField;
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainFrame() {
		super(850, 500);
		// TODO: hacer que hibernate manager se inicie en el start up
		// TODO: mejorar el query para el show info
		// TODO: crear un objecto que contenga el nombre e id de las entidades para los
		// lookup
		setTitle("ShzTy - Desktop App");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dim.getWidth() - getWidth()) / 2);
		int y = (int) ((dim.getHeight() - getHeight()) / 2);
		setLocation(x, y);

		ContraFooter.initialize(this);
		TablePopupMenu popupMenu = new TablePopupMenu();

		tableArtist = new CustomTable<>(new ArtistTableModel(true), popupMenu);
		tableArtist.setPotencials(new ArtistService(), new ArtistCreateDTO(), new ArtistUpdateDTO());
		getBar().addTab("Artist", tableArtist);
		getBar().setActiveTab(0);

		tableAlbum = new CustomTable<>(new AlbumTableModel(true), popupMenu);
		tableAlbum.setPotencials(new AlbumService(), new AlbumCreateDTO(), new AlbumUpdateDTO());
		getBar().addTab("Album", tableAlbum);

		tableTrack = new CustomTable<>(new TrackTableModel(true), popupMenu);
		tableTrack.setPotencials(new TrackService(), new TrackCreateDTO(), new TrackUpdateDTO());
		getBar().addTab("Track", tableTrack);

		JPanel Search = new JPanel();
		getBar().addTab("Search", Search);
		Search.setLayout(null);

		textField = new JTextField();
		textField.setBounds(10, 11, 710, 27);
		Search.add(textField);
		textField.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 710, 322);
		Search.add(scrollPane);

		table = new CustomTable("tSearch");
		table = new JTable();
		table.setName("tSearch");
		scrollPane.setViewportView(table);
	}
}