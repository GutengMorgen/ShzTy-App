package com.gutengmorgen.ShzTy.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;

import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.DtoCreateArtist;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.DtoSimpleReturnArtist;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.DtoUpdateArtist;
import com.gutengmorgen.ShzTy.services.AlbumService;
import com.gutengmorgen.ShzTy.services.ArtistService;
import com.gutengmorgen.ShzTy.services.TrackService;

public class DtoMapper {

    public static void mapper(JTable table, Long rowId, CustomDialog dialog, DTO_MODEL model) {
	String name = table.getName();
	if (name.contains("Ar")) {
	    ArtistService se = new ArtistService();
	    if (model == DTO_MODEL.RETURN)
		dialog.autoFillReturn(se.getReturnArtistById(rowId));
	    else if (model == DTO_MODEL.UPDATE) {
		dialog.autoFillToInsert(DtoUpdateArtist.class);
		dialog.okButton.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
			List<Object> result = dialog.getResult();
			DtoUpdateArtist dto = new DtoUpdateArtist((String) result.get(0), (java.sql.Date) result.get(1),
				(String) result.get(2), (String) result.get(3), (String) result.get(4),
				(Set<Long>) result.get(5), (Set<Long>) result.get(6));
//			System.out.println(dto);
			DtoSimpleReturnArtist reDto = se.updateArtist(dto, rowId);
			SwingUtilities.invokeLater(new Runnable() {
			    @Override
			    public void run() {
				((MainTableModel<DtoSimpleReturnArtist>) table.getModel())
					.UpdateRow(table.getSelectedRow(), reDto);
			    }
			});

			dialog.dispose();
		    }
		});
	    } else if (model == DTO_MODEL.CREATE) {
		dialog.autoFillToInsert(DtoCreateArtist.class);
		dialog.okButton.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
			List<Object> result = dialog.getResult();
//			DtoUpdateArtist test = new DtoUpdateArtist();
//			System.out.println("void dto: " + test);
//			System.out.println("test dto: " + dialog.convert(test));
			DtoUpdateArtist dto = new DtoUpdateArtist((String) result.get(0), (java.sql.Date) result.get(1),
				(String) result.get(2), (String) result.get(3), (String) result.get(4),
				(Set<Long>) result.get(5), (Set<Long>) result.get(6));
			DtoSimpleReturnArtist reDto = se.updateArtist(dto, rowId);
			SwingUtilities.invokeLater(new Runnable() {
			    @Override
			    public void run() {
				((MainTableModel<DtoSimpleReturnArtist>) table.getModel()).InsertRow(reDto);
			    }
			});

			dialog.dispose();
		    }
		});
	    }
	} else if (name.contains("Al")) {
	    AlbumService se = new AlbumService();
	    se.getAlbumById(rowId);
	} else if (name.contains("Tr")) {
	    TrackService se = new TrackService();
	    se.getTrackById(rowId);
	}
    }
}
