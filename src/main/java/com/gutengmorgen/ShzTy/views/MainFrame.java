package com.gutengmorgen.ShzTy.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTable;

import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import javax.swing.table.DefaultTableModel;

public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private JTable tableArtist;
    private JTable tableAlbum;
    private JTable tableTrack;
    private JTextField textField;
    private JTable table;
    private JTable table_1;
    private JTable table_2;

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
//	setResizable(false);
	setTitle("ShzTy - Desktop App");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 800, 460);

	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
	tabbedPane.setFocusable(false);
	tabbedPane.setFocusTraversalKeysEnabled(false);
	setContentPane(tabbedPane);
	
	JScrollPane sArtist = new JScrollPane();
	tabbedPane.addTab("Artist", null, sArtist, null);

	tableArtist = new JTable();
	tableArtist.setName("tAr");
	tableArtist.setShowVerticalLines(false);
	tableArtist.setRequestFocusEnabled(false);
	tableArtist.setFocusTraversalKeysEnabled(false);
	tableArtist.setFocusable(false);
	tableArtist.setBorder(null);
//	tableArtist.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	tableArtist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	tableArtist.setFillsViewportHeight(true);
	tableArtist.getTableHeader().setReorderingAllowed(false);
//	TableColumn column = tableArtist.getColumnModel().getColumn(1);
//	column.setPreferredWidth(1);
	tableArtist.setModel(new ArtistTableModel());
	tableArtist.addMouseListener(new TableMouseListenir(tableArtist));

	sArtist.setViewportView(tableArtist);
	
	JScrollPane sAlbum = new JScrollPane();
	tabbedPane.addTab("Album", null, sAlbum, null);
	
	tableAlbum = new JTable();
	tableAlbum.setFillsViewportHeight(true);
	tableAlbum.setModel(new DefaultTableModel(
		new Object[][] {
			{"3234", "213122", "123"},
			{"54556567", "353542", "2344"},
			{"7657", "7567", null},
		},
		new String[] {
			"New column", "New column", "New column"
		}
	));
	sAlbum.setViewportView(tableAlbum);
	
	JScrollPane sTrack = new JScrollPane();
	tabbedPane.addTab("Track", null, sTrack, null);
	
	tableTrack = new JTable();
	tableTrack.setFillsViewportHeight(true);
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
	
	table = new JTable();
	table.setFillsViewportHeight(true);
	table.setModel(new DefaultTableModel(
		new Object[][] {
			{"tret", "456435"},
			{null, "tre"},
		},
		new String[] {
			"New column", "New column"
		}
	));
	scrollPane.setViewportView(table);
	
	JPanel panel = new JPanel();
	tabbedPane.addTab("New tab", null, panel, null);
	panel.setLayout(null);
	
	JScrollPane scrollPane_1 = new JScrollPane();
	scrollPane_1.setBounds(43, 30, 185, 179);
	panel.add(scrollPane_1);
	
	table_1 = new JTable();
	scrollPane_1.setViewportView(table_1);
	
	JScrollPane scrollPane_2 = new JScrollPane();
	scrollPane_2.setBounds(319, 30, 185, 179);
	panel.add(scrollPane_2);
	
	table_2 = new JTable();
	scrollPane_2.setViewportView(table_2);
    }
}
