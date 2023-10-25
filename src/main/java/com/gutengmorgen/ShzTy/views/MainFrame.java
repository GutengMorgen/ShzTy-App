package com.gutengmorgen.ShzTy.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTable;

import javax.swing.UIManager;

import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.ArtistCreateDTO;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.ArtistSimpleReturnDTO;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.ArtistUpdateDTO;
import com.gutengmorgen.ShzTy.views.Components.CustomTable;
import com.gutengmorgen.ShzTy.views.TableModel.ArtistTableModel;
import com.gutengmorgen.ShzTy.views.TableModel.TestingModel;

import javax.swing.JScrollPane;

public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private CustomTable<ArtistSimpleReturnDTO> tableArtist;
    private CustomTable<ArtistSimpleReturnDTO> tableAlbum;
    private JTable tableTrack;
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
	//TODO: hacer que hibernate manager se inicie en el start up
	setResizable(false);
	setTitle("ShzTy - Desktop App");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 800, 460);

	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
	tabbedPane.setFocusable(false);
	tabbedPane.setFocusTraversalKeysEnabled(false);
	setContentPane(tabbedPane);
	
	JScrollPane sArtist = new JScrollPane();
	tabbedPane.addTab("Artist", null, sArtist, null);

	tableArtist = new CustomTable<>("tAr", new ArtistTableModel(false));
	sArtist.setViewportView(tableArtist);

	JScrollPane sAlbum = new JScrollPane();
	tabbedPane.addTab("Album", null, sAlbum, null);

//	tableAlbum = new CustomTable("tAl", new AlbumTableModel(false));
	tableAlbum = new CustomTable<>("tAl", new TestingModel(false));
	sAlbum.setViewportView(tableAlbum);

	JScrollPane sTrack = new JScrollPane();
	tabbedPane.addTab("Track", null, sTrack, null);

//	tableTrack = new CustomTable("tTr", new TrackTableModel(false));
	tableTrack = new CustomTable("tTr");
	sTrack.setViewportView(tableTrack);

	JPanel Search = new JPanel();
	tabbedPane.addTab("Search", null, Search, null);
	Search.setLayout(null);

	textField = new JTextField();
	textField.setBounds(10, 11, 710, 27);
	Search.add(textField);
	textField.setColumns(10);

	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(10, 50, 710, 322);
	Search.add(scrollPane);

	table = new CustomTable("tSearch");
	scrollPane.setViewportView(table);
    }
}
