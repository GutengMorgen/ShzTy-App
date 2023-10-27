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
import java.awt.*;

public class MainFrame extends JFrame {
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
        //TODO: hacer que hibernate manager se inicie en el start up
        setResizable(true);
        setTitle("ShzTy - Desktop App");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 460);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int width = getWidth();
        int height = getHeight();
        int x = (int) ((dim.getWidth() - getWidth()) / 2);
        int y = (int) ((dim.getHeight() - getHeight()) / 2);
        setLocation(x, y);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setFocusable(false);
        tabbedPane.setFocusTraversalKeysEnabled(false);
        setContentPane(tabbedPane);

        JScrollPane sArtist = new JScrollPane();
        tabbedPane.addTab("Artist", null, sArtist, null);

        tableArtist = new CustomTable<>(new ArtistTableModel(true));
        tableArtist.setPotencials(new ArtistService(), new ArtistCreateDTO(), new ArtistUpdateDTO());
        sArtist.setViewportView(tableArtist);

        JScrollPane sAlbum = new JScrollPane();
        tabbedPane.addTab("Album", null, sAlbum, null);

        tableAlbum = new CustomTable<>(new AlbumTableModel(false));
        tableAlbum.setPotencials(new AlbumService(), new AlbumCreateDTO(), new AlbumUpdateDTO());
        sAlbum.setViewportView(tableAlbum);

        JScrollPane sTrack = new JScrollPane();
        tabbedPane.addTab("Track", null, sTrack, null);

        tableTrack = new CustomTable<>(new TrackTableModel(false));
        tableTrack.setPotencials(new TrackService(), new TrackCreateDTO(), new TrackUpdateDTO());
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

//	table = new CustomTable("tSearch");
        table = new JTable();
        table.setName("tSearch");
        scrollPane.setViewportView(table);
    }
}
