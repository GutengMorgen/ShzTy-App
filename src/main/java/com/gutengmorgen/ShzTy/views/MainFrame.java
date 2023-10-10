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

import com.gutengmorgen.ShzTy.models.Albums.Album;
import com.gutengmorgen.ShzTy.models.Artists.Artist;
import com.gutengmorgen.ShzTy.models.Tracks.Track;

import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.border.MatteBorder;
import javax.swing.JLayeredPane;
import javax.swing.table.DefaultTableModel;

public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JTextField textSearchAnything;
    private JTextField textField_1;
    private JTable tableArtist;
    private JTable tableAlbum;
    private JTable tableTrack;

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
	setBounds(100, 100, 800, 460);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

	setContentPane(contentPane);
	contentPane.setLayout(null);

	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
	tabbedPane.setAutoscrolls(true);
	tabbedPane.setBounds(0, 0, 784, 388);
	contentPane.add(tabbedPane);

	JPanel Home = new JPanel();
	tabbedPane.addTab("Home", null, Home, null);
	GridBagLayout gbl_Home = new GridBagLayout();
	gbl_Home.columnWidths = new int[] { 721, 0 };
	gbl_Home.rowHeights = new int[] { 70, 244, 0 };
	gbl_Home.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
	gbl_Home.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
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

	TablePopupMenu tablePopupMenu = new TablePopupMenu();
	JScrollPane sArtist = new JScrollPane();
	tabbedPane.addTab("Artist", null, sArtist, null);

	tableArtist = new JTable();
	tableArtist.setModel(new DefaultTableModel(
		new Object[][] { { "efe", "2", "3", "78" }, { "few", "1", "4", "78" }, { "we", "er", "5", "687" },
			{ "er", "re", "6", "7" }, },
		new String[] { "New column", "New column", "New column", "New column" }));
	tableArtist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	tableArtist.setFillsViewportHeight(true);
	tableArtist.setAutoCreateRowSorter(true);

//	tableArtists.setModel(new ArtistTableModel());
	tableArtist.addMouseListener(new TableMouseListenir(tableArtist, tablePopupMenu, Artist.class));
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
	GridBagLayout gbl_Search = new GridBagLayout();
	gbl_Search.columnWidths = new int[] { 100 };
	gbl_Search.rowHeights = new int[] { 30 };
	gbl_Search.columnWeights = new double[] { 1.0 };
	gbl_Search.rowWeights = new double[] { 0.0, 1.0 };
	Search.setLayout(gbl_Search);

	textSearchAnything = new JTextField();
	GridBagConstraints gbc_textSearchAnything = new GridBagConstraints();
	gbc_textSearchAnything.insets = new Insets(0, 0, 5, 0);
	gbc_textSearchAnything.fill = GridBagConstraints.HORIZONTAL;
	gbc_textSearchAnything.gridx = 0;
	gbc_textSearchAnything.gridy = 0;
	Search.add(textSearchAnything, gbc_textSearchAnything);
	textSearchAnything.setColumns(10);

	JScrollPane scrollPaneSearch = new JScrollPane();
	GridBagConstraints gbc_scrollPaneSearch = new GridBagConstraints();
	gbc_scrollPaneSearch.fill = GridBagConstraints.BOTH;
	gbc_scrollPaneSearch.gridx = 0;
	gbc_scrollPaneSearch.gridy = 1;
	Search.add(scrollPaneSearch, gbc_scrollPaneSearch);

	JPanel ContentSearch = new JPanel();
	scrollPaneSearch.setViewportView(ContentSearch);

	JScrollPane Setting = new JScrollPane();
	Setting.setViewportBorder(null);
	Setting.setVerifyInputWhenFocusTarget(false);
	Setting.setRequestFocusEnabled(false);
	Setting.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	Setting.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	Setting.setFocusable(false);
	Setting.setFocusTraversalKeysEnabled(false);
	Setting.setBorder(null);
	tabbedPane.addTab("Setting", null, Setting, null);

	textField_1 = new JTextField();
	Setting.setColumnHeaderView(textField_1);
	textField_1.setColumns(10);

	JPanel panel_1 = new JPanel();
	panel_1.setBorder(new LineBorder(new Color(240, 240, 240), 5));
	Setting.setViewportView(panel_1);
	GridBagLayout gbl_panel_1 = new GridBagLayout();
	gbl_panel_1.columnWidths = new int[] { 470 };
	gbl_panel_1.rowHeights = new int[] { 0, 51, 30, 0 };
	gbl_panel_1.columnWeights = new double[] { 0.0 };
	gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
	panel_1.setLayout(gbl_panel_1);

	JPanel panel_2 = new JPanel();
	panel_2.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
	GridBagConstraints gbc_panel_2 = new GridBagConstraints();
	gbc_panel_2.insets = new Insets(0, 0, 5, 0);
	gbc_panel_2.fill = GridBagConstraints.HORIZONTAL;
	gbc_panel_2.anchor = GridBagConstraints.NORTH;
	gbc_panel_2.gridx = 0;
	gbc_panel_2.gridy = 0;
	panel_1.add(panel_2, gbc_panel_2);
	GridBagLayout gbl_panel_2 = new GridBagLayout();
	gbl_panel_2.columnWidths = new int[] { 75, 198, 51, 60, 75, 71, 61, 95, 0 };
	gbl_panel_2.rowHeights = new int[] { 15, 15, 0 };
	gbl_panel_2.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
	gbl_panel_2.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
	panel_2.setLayout(gbl_panel_2);

	JLabel lName = new JLabel("Name:");
	lName.setFont(new Font("Consolas", Font.BOLD, 10));
	GridBagConstraints gbc_lName = new GridBagConstraints();
	gbc_lName.anchor = GridBagConstraints.WEST;
	gbc_lName.insets = new Insets(0, 0, 5, 5);
	gbc_lName.gridx = 0;
	gbc_lName.gridy = 0;
	panel_2.add(lName, gbc_lName);

	JLabel llName = new JLabel("Red Hot Chilli Peppers");
	llName.setFont(new Font("Consolas", Font.PLAIN, 10));
	GridBagConstraints gbc_IIName = new GridBagConstraints();
	gbc_IIName.anchor = GridBagConstraints.WEST;
	gbc_IIName.insets = new Insets(0, 0, 5, 5);
	gbc_IIName.gridx = 1;
	gbc_IIName.gridy = 0;
	panel_2.add(llName, gbc_IIName);

	JLabel lGender = new JLabel("Gender:");
	lGender.setFont(new Font("Consolas", Font.BOLD, 10));
	GridBagConstraints gbc_IGender = new GridBagConstraints();
	gbc_IGender.anchor = GridBagConstraints.WEST;
	gbc_IGender.insets = new Insets(0, 0, 5, 5);
	gbc_IGender.gridx = 2;
	gbc_IGender.gridy = 0;
	panel_2.add(lGender, gbc_IGender);

	JLabel llGender = new JLabel("Female");
	llGender.setFont(new Font("Consolas", Font.PLAIN, 10));
	GridBagConstraints gbc_lblNewLabel_14_1 = new GridBagConstraints();
	gbc_lblNewLabel_14_1.anchor = GridBagConstraints.WEST;
	gbc_lblNewLabel_14_1.insets = new Insets(0, 0, 5, 5);
	gbc_lblNewLabel_14_1.gridx = 3;
	gbc_lblNewLabel_14_1.gridy = 0;
	panel_2.add(llGender, gbc_lblNewLabel_14_1);

	JLabel lnAlbum = new JLabel("Nº Albums:");
	lnAlbum.setFont(new Font("Consolas", Font.BOLD, 10));
	GridBagConstraints gbc_lblNewLabel_3_1 = new GridBagConstraints();
	gbc_lblNewLabel_3_1.anchor = GridBagConstraints.WEST;
	gbc_lblNewLabel_3_1.insets = new Insets(0, 0, 5, 5);
	gbc_lblNewLabel_3_1.gridx = 4;
	gbc_lblNewLabel_3_1.gridy = 0;
	panel_2.add(lnAlbum, gbc_lblNewLabel_3_1);

	JLabel lblNewLabel_13_1 = new JLabel("New label");
	lblNewLabel_13_1.setFont(new Font("Consolas", Font.PLAIN, 10));
	GridBagConstraints gbc_lblNewLabel_13_1 = new GridBagConstraints();
	gbc_lblNewLabel_13_1.anchor = GridBagConstraints.WEST;
	gbc_lblNewLabel_13_1.insets = new Insets(0, 0, 5, 5);
	gbc_lblNewLabel_13_1.gridx = 5;
	gbc_lblNewLabel_13_1.gridy = 0;
	panel_2.add(lblNewLabel_13_1, gbc_lblNewLabel_13_1);

	JLabel lCountry = new JLabel("Country:");
	lCountry.setFont(new Font("Consolas", Font.BOLD, 10));
	GridBagConstraints gbc_lblNewLabel_6_1 = new GridBagConstraints();
	gbc_lblNewLabel_6_1.anchor = GridBagConstraints.WEST;
	gbc_lblNewLabel_6_1.insets = new Insets(0, 0, 5, 5);
	gbc_lblNewLabel_6_1.gridx = 6;
	gbc_lblNewLabel_6_1.gridy = 0;
	panel_2.add(lCountry, gbc_lblNewLabel_6_1);

	JLabel lblNewLabel_15_1 = new JLabel("New label");
	lblNewLabel_15_1.setFont(new Font("Consolas", Font.PLAIN, 10));
	GridBagConstraints gbc_lblNewLabel_15_1 = new GridBagConstraints();
	gbc_lblNewLabel_15_1.anchor = GridBagConstraints.WEST;
	gbc_lblNewLabel_15_1.insets = new Insets(0, 0, 5, 0);
	gbc_lblNewLabel_15_1.gridx = 7;
	gbc_lblNewLabel_15_1.gridy = 0;
	panel_2.add(lblNewLabel_15_1, gbc_lblNewLabel_15_1);

	JLabel lblNewLabel_2_1 = new JLabel("Languages:");
	lblNewLabel_2_1.setFont(new Font("Consolas", Font.BOLD, 10));
	GridBagConstraints gbc_lblNewLabel_2_1 = new GridBagConstraints();
	gbc_lblNewLabel_2_1.anchor = GridBagConstraints.WEST;
	gbc_lblNewLabel_2_1.insets = new Insets(0, 0, 0, 5);
	gbc_lblNewLabel_2_1.gridx = 0;
	gbc_lblNewLabel_2_1.gridy = 1;
	panel_2.add(lblNewLabel_2_1, gbc_lblNewLabel_2_1);

	JLabel lblNewLabel_8_1 = new JLabel("New label");
	lblNewLabel_8_1.setFont(new Font("Consolas", Font.PLAIN, 10));
	GridBagConstraints gbc_lblNewLabel_8_1 = new GridBagConstraints();
	gbc_lblNewLabel_8_1.anchor = GridBagConstraints.WEST;
	gbc_lblNewLabel_8_1.insets = new Insets(0, 0, 0, 5);
	gbc_lblNewLabel_8_1.gridx = 1;
	gbc_lblNewLabel_8_1.gridy = 1;
	panel_2.add(lblNewLabel_8_1, gbc_lblNewLabel_8_1);

	JLabel lblNewLabel_5_1 = new JLabel("Genres:");
	lblNewLabel_5_1.setFont(new Font("Consolas", Font.BOLD, 10));
	GridBagConstraints gbc_lblNewLabel_5_1 = new GridBagConstraints();
	gbc_lblNewLabel_5_1.anchor = GridBagConstraints.WEST;
	gbc_lblNewLabel_5_1.insets = new Insets(0, 0, 0, 5);
	gbc_lblNewLabel_5_1.gridx = 2;
	gbc_lblNewLabel_5_1.gridy = 1;
	panel_2.add(lblNewLabel_5_1, gbc_lblNewLabel_5_1);

	JLabel lblNewLabel_11_1 = new JLabel("New label");
	lblNewLabel_11_1.setFont(new Font("Consolas", Font.PLAIN, 10));
	GridBagConstraints gbc_lblNewLabel_11_1 = new GridBagConstraints();
	gbc_lblNewLabel_11_1.anchor = GridBagConstraints.WEST;
	gbc_lblNewLabel_11_1.gridwidth = 5;
	gbc_lblNewLabel_11_1.insets = new Insets(0, 0, 0, 5);
	gbc_lblNewLabel_11_1.gridx = 3;
	gbc_lblNewLabel_11_1.gridy = 1;
	panel_2.add(lblNewLabel_11_1, gbc_lblNewLabel_11_1);
    }
}
