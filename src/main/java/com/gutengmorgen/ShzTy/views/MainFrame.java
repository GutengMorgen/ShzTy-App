package com.gutengmorgen.ShzTy.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.gutengmorgen.ShzTy.DAOs.ArtistDAO;
import com.gutengmorgen.ShzTy.Repositories.ArtistRepository;
import com.gutengmorgen.ShzTy.models.Albums.Album;
import com.gutengmorgen.ShzTy.models.Artists.Artist;

import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JScrollPane;

public class MainFrame extends JFrame{

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tableArtists;
    private JTextField textSearchArtists;
    private JTextField textSearchAlbums;
    private JTextField textSearchTracks;
    private JTable tableTracks;
    private JTextField textSearchAnything;
    private JTable tableAlbums;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    MainFrame frame = new MainFrame();
		    frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the frame.
     */
    public MainFrame() {
    	setResizable(false);
    	setTitle("ShzTy - Desktop App");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 617, 427);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
	tabbedPane.setAutoscrolls(true);
	tabbedPane.setBounds(0, 0, 601, 388);
	contentPane.add(tabbedPane);
	
	JPanel Home = new JPanel();
	tabbedPane.addTab("Home", null, Home, null);
	GridBagLayout gbl_Home = new GridBagLayout();
	gbl_Home.columnWidths = new int[]{596, 0};
	gbl_Home.rowHeights = new int[] {70, 244, 0};
	gbl_Home.columnWeights = new double[]{0.0, Double.MIN_VALUE};
	gbl_Home.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
	Home.setLayout(gbl_Home);
	
	JLabel lblNewLabel = new JLabel("About");
	lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
	lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
	gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
	gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
	gbc_lblNewLabel.gridx = 0;
	gbc_lblNewLabel.gridy = 0;
	Home.add(lblNewLabel, gbc_lblNewLabel);
	
	JTextArea textArea = new JTextArea();
	GridBagConstraints gbc_textArea = new GridBagConstraints();
	gbc_textArea.fill = GridBagConstraints.BOTH;
	gbc_textArea.gridx = 0;
	gbc_textArea.gridy = 1;
	Home.add(textArea, gbc_textArea);
	
	JPanel Artists = new JPanel();
	tabbedPane.addTab("Artists", null, Artists, null);
	GridBagLayout gbl_Artists = new GridBagLayout();
	gbl_Artists.columnWidths = new int[] {206, 70};
	gbl_Artists.rowHeights = new int[] {38, 0};
	gbl_Artists.columnWeights = new double[]{1.0, 0.0};
	gbl_Artists.rowWeights = new double[]{0.0, 1.0};
	Artists.setLayout(gbl_Artists);
	
	textSearchArtists = new JTextField();
	GridBagConstraints gbc_textSearchArtists = new GridBagConstraints();
	gbc_textSearchArtists.fill = GridBagConstraints.HORIZONTAL;
	gbc_textSearchArtists.insets = new Insets(0, 0, 5, 5);
	gbc_textSearchArtists.gridx = 0;
	gbc_textSearchArtists.gridy = 0;
	Artists.add(textSearchArtists, gbc_textSearchArtists);
	textSearchArtists.setColumns(10);
	
	JButton btnSearchArtists = new JButton("Go");
	GridBagConstraints gbc_btnSearchArtists = new GridBagConstraints();
	gbc_btnSearchArtists.insets = new Insets(0, 0, 5, 0);
	gbc_btnSearchArtists.gridx = 1;
	gbc_btnSearchArtists.gridy = 0;
	Artists.add(btnSearchArtists, gbc_btnSearchArtists);
	
	JScrollPane scrollPane = new JScrollPane();
	GridBagConstraints gbc_scrollPane = new GridBagConstraints();
	gbc_scrollPane.gridwidth = 2;
	gbc_scrollPane.fill = GridBagConstraints.BOTH;
	gbc_scrollPane.gridx = 0;
	gbc_scrollPane.gridy = 1;
	Artists.add(scrollPane, gbc_scrollPane);

	TablePopupMenu tablePopupMenu = new TablePopupMenu();
	
//	ArtistService artistService = new ArtistService();
	ArtistDAO artistDAO = new ArtistDAO();
	
	tableArtists = new JTable(new ArtistTableModel(artistDAO.getSimpleList()));
	tableArtists.setAutoCreateRowSorter(true);
	tableArtists.addMouseListener(new TableMouseListenir(tableArtists, tablePopupMenu, Artist.class));
	tableArtists.setFillsViewportHeight(true);
	scrollPane.setViewportView(tableArtists);
	
	JPanel Albums = new JPanel();
	tabbedPane.addTab("Albums", null, Albums, null);
	GridBagLayout gbl_Albums = new GridBagLayout();
	gbl_Albums.columnWidths = new int[] {100, 70};
	gbl_Albums.rowHeights = new int[]{0, 0, 0};
	gbl_Albums.columnWeights = new double[]{1.0, 0.0};
	gbl_Albums.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
	Albums.setLayout(gbl_Albums);
	
	textSearchAlbums = new JTextField();
	GridBagConstraints gbc_textSearchAlbums = new GridBagConstraints();
	gbc_textSearchAlbums.insets = new Insets(0, 0, 5, 5);
	gbc_textSearchAlbums.fill = GridBagConstraints.HORIZONTAL;
	gbc_textSearchAlbums.gridx = 0;
	gbc_textSearchAlbums.gridy = 0;
	Albums.add(textSearchAlbums, gbc_textSearchAlbums);
	textSearchAlbums.setColumns(10);
	
	JButton btnSearchAlbums = new JButton("Go");
	GridBagConstraints gbc_btnSearchAlbums = new GridBagConstraints();
	gbc_btnSearchAlbums.insets = new Insets(0, 0, 5, 0);
	gbc_btnSearchAlbums.gridx = 1;
	gbc_btnSearchAlbums.gridy = 0;
	Albums.add(btnSearchAlbums, gbc_btnSearchAlbums);
	
	JScrollPane scrollPane_1 = new JScrollPane();
	GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
	gbc_scrollPane_1.gridwidth = 2;
	gbc_scrollPane_1.insets = new Insets(0, 0, 0, 5);
	gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
	gbc_scrollPane_1.gridx = 0;
	gbc_scrollPane_1.gridy = 1;
	Albums.add(scrollPane_1, gbc_scrollPane_1);
	
	tableAlbums = new JTable();
	tableAlbums.addMouseListener(new TableMouseListenir(tableAlbums, tablePopupMenu, Album.class));
	tableAlbums.setFillsViewportHeight(true);
	tableAlbums.setModel(new DefaultTableModel(
		new Object[][] {
			{"Album 1"},
			{"Album 2"},
			{"Album 3"},
			{"Album 4"},
			{"Album 5"},
		},
		new String[] {
			"Albums"
		}
	));
	scrollPane_1.setViewportView(tableAlbums);
	
	JPanel Tracks = new JPanel();
	tabbedPane.addTab("Tracks", null, Tracks, null);
	GridBagLayout gbl_Tracks = new GridBagLayout();
	gbl_Tracks.columnWidths = new int[] {100, 70};
	gbl_Tracks.rowHeights = new int[]{0, 0, 0};
	gbl_Tracks.columnWeights = new double[]{1.0, 0.0};
	gbl_Tracks.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
	Tracks.setLayout(gbl_Tracks);
	
	textSearchTracks = new JTextField();
	GridBagConstraints gbc_textSearchTracks = new GridBagConstraints();
	gbc_textSearchTracks.insets = new Insets(0, 0, 5, 5);
	gbc_textSearchTracks.fill = GridBagConstraints.HORIZONTAL;
	gbc_textSearchTracks.gridx = 0;
	gbc_textSearchTracks.gridy = 0;
	Tracks.add(textSearchTracks, gbc_textSearchTracks);
	textSearchTracks.setColumns(10);
	
	JButton btnSearchTracks = new JButton("Go");
	GridBagConstraints gbc_btnSearchTracks = new GridBagConstraints();
	gbc_btnSearchTracks.insets = new Insets(0, 0, 5, 0);
	gbc_btnSearchTracks.gridx = 1;
	gbc_btnSearchTracks.gridy = 0;
	Tracks.add(btnSearchTracks, gbc_btnSearchTracks);
	
	JScrollPane scrollPane_2 = new JScrollPane();
	GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
	gbc_scrollPane_2.gridwidth = 2;
	gbc_scrollPane_2.insets = new Insets(0, 0, 0, 5);
	gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
	gbc_scrollPane_2.gridx = 0;
	gbc_scrollPane_2.gridy = 1;
	Tracks.add(scrollPane_2, gbc_scrollPane_2);
	
	tableTracks = new JTable();
	tableTracks.addMouseListener(new TableMouseListenir(tableTracks, tablePopupMenu, null));
	tableTracks.setFillsViewportHeight(true);
	scrollPane_2.setViewportView(tableTracks);
	tableTracks.setModel(new DefaultTableModel(
		new Object[][] {
			{"Track 1"},
			{"Track 2"},
			{"Track 3"},
			{"Track 4"},
			{"Track 5"},
		},
		new String[] {
			"Tracks"
		}
	));
	
	JPanel Search = new JPanel();
	tabbedPane.addTab("Search", null, Search, null);
	GridBagLayout gbl_Search = new GridBagLayout();
	gbl_Search.columnWidths = new int[] {100, 70};
	gbl_Search.rowHeights = new int[] {30};
	gbl_Search.columnWeights = new double[]{1.0, 0.0};
	gbl_Search.rowWeights = new double[]{0.0, 1.0};
	Search.setLayout(gbl_Search);
	
	textSearchAnything = new JTextField();
	GridBagConstraints gbc_textSearchAnything = new GridBagConstraints();
	gbc_textSearchAnything.insets = new Insets(0, 0, 5, 5);
	gbc_textSearchAnything.fill = GridBagConstraints.HORIZONTAL;
	gbc_textSearchAnything.gridx = 0;
	gbc_textSearchAnything.gridy = 0;
	Search.add(textSearchAnything, gbc_textSearchAnything);
	textSearchAnything.setColumns(10);
	
	JButton btnSearchAnything = new JButton("Go");
	GridBagConstraints gbc_btnSearchAnything = new GridBagConstraints();
	gbc_btnSearchAnything.insets = new Insets(0, 0, 5, 0);
	gbc_btnSearchAnything.gridx = 1;
	gbc_btnSearchAnything.gridy = 0;
	Search.add(btnSearchAnything, gbc_btnSearchAnything);
	
	JScrollPane scrollPaneSearch = new JScrollPane();
	GridBagConstraints gbc_scrollPaneSearch = new GridBagConstraints();
	gbc_scrollPaneSearch.gridwidth = 2;
	gbc_scrollPaneSearch.fill = GridBagConstraints.BOTH;
	gbc_scrollPaneSearch.gridx = 0;
	gbc_scrollPaneSearch.gridy = 1;
	Search.add(scrollPaneSearch, gbc_scrollPaneSearch);
	
	JPanel ContentSearch = new JPanel();
	scrollPaneSearch.setViewportView(ContentSearch);
	
	JPanel Settings = new JPanel();
	tabbedPane.addTab("Settings", null, Settings, null);
    }
}
