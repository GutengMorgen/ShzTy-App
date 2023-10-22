package com.gutengmorgen.ShzTy.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTable;

import javax.swing.UIManager;

import javax.swing.JScrollPane;

public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private JTable tableArtist;
    private JTable tableAlbum;
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

	JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem menuItem = new JMenuItem("Refresh");
        popupMenu.add(menuItem);

        tabbedPane.setComponentPopupMenu(popupMenu);
	
	JScrollPane sArtist = new JScrollPane();
	tabbedPane.addTab("Artist", null, sArtist, null);

	// TODO: agregar un boolean para inicializar el modelo de la tabla en el start
	// up
	tableArtist = new CustomTable("tAr", new ArtistTableModel(true));
	sArtist.setViewportView(tableArtist);

	JScrollPane sAlbum = new JScrollPane();
	tabbedPane.addTab("Album", null, sAlbum, null);

	tableAlbum = new CustomTable("tAl", new AlbumTableModel(false));
	sAlbum.setViewportView(tableAlbum);

	JScrollPane sTrack = new JScrollPane();
	tabbedPane.addTab("Track", null, sTrack, null);

	tableTrack = new CustomTable("tTr", new TrackTableModel(false));
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
