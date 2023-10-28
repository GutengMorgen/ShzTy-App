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
import com.gutengmorgen.ShzTy.views.TableModel.AlbumTableModel;
import com.gutengmorgen.ShzTy.views.TableModel.ArtistTableModel;
import com.gutengmorgen.ShzTy.views.TableModel.TrackTableModel;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		super(800, 460);
		// TODO: hacer que hibernate manager se inicie en el start up
		setResizable(true);
		setTitle("ShzTy - Desktop App");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dim.getWidth() - getWidth()) / 2);
		int y = (int) ((dim.getHeight() - getHeight()) / 2);
		setLocation(x, y);

//		JPanel panel = new JPanel();
//		setContentPane(panel);
//		panel.setLayout(null);
//
//		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
//		tabbedPane.setBounds(0, 23, 800, 437);
//		tabbedPane.setAutoscrolls(true);
//		tabbedPane.setFocusable(false);
//		tabbedPane.setFocusTraversalKeysEnabled(false);
//		panel.add(tabbedPane);

//		JScrollPane sArtist = new JScrollPane();
//		tabbedPane.addTab("Artist", null, sArtist, null);

		tableArtist = new CustomTable<>(new ArtistTableModel(false));
		title.addTab("Artist", tableArtist, center);
//        tableArtist.setPotencials(new ArtistService(), new ArtistCreateDTO(), new ArtistUpdateDTO());
//		sArtist.setViewportView(tableArtist);

//		JScrollPane sAlbum = new JScrollPane();
//		tabbedPane.addTab("Album", null, sAlbum, null);

		tableAlbum = new CustomTable<>(new AlbumTableModel(false));
		title.addTab("Album", tableAlbum, center);
//        tableAlbum.setPotencials(new AlbumService(), new AlbumCreateDTO(), new AlbumUpdateDTO());
//		sAlbum.setViewportView(tableAlbum);

//		JScrollPane sTrack = new JScrollPane();
//		tabbedPane.addTab("Track", null, sTrack, null);

		tableTrack = new CustomTable<>(new TrackTableModel(false));
		title.addTab("Track", tableTrack, center);
		
		title.setActiveTab(0);
//        tableTrack.setPotencials(new TrackService(), new TrackCreateDTO(), new TrackUpdateDTO());
//		sTrack.setViewportView(tableTrack);

//		JPanel Search = new JPanel();
//		tabbedPane.addTab("Search", null, Search, null);
//		Search.setLayout(null);

//		textField = new JTextField();
//		textField.setBounds(10, 11, 710, 27);
//		Search.add(textField);
//		textField.setColumns(10);
//
//		JScrollPane scrollPane = new JScrollPane();
//		scrollPane.setBounds(10, 50, 710, 322);
//		Search.add(scrollPane);

//	table = new CustomTable("tSearch");
//		table = new JTable();
//		table.setName("tSearch");
//		scrollPane.setViewportView(table);
//
//		CustomTitleBar title = new CustomTitleBar(MainFrame.this);
//		title.setBounds(0, 0, 800, 21);
//		panel.add(title);
	}
}

class CustomTitleBar extends JPanel {
	private int posX;
	private int posY;
	private boolean dragging = false;

	public CustomTitleBar(JFrame frame) {
		setLayout(new BorderLayout());

		JButton closeButton = new JButton("x");
		closeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		add(closeButton, BorderLayout.EAST);

		closeButton.setFocusPainted(false);
		closeButton.setBorderPainted(false);

		addMouseListener(new MouseInputAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					posX = e.getX();
					posY = e.getY();
					dragging = true;
					frame.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				dragging = false;
			}

		});

		addMouseMotionListener(new MouseInputAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				if (dragging && SwingUtilities.isLeftMouseButton(e)) {
					int newX = (int) (frame.getLocation().getX() + e.getX() - posX);
					int newY = (int) (frame.getLocation().getY() + e.getY() - posY);
					frame.setLocation(newX, newY);
				}
			}

		});
	}
}
