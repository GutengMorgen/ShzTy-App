package com.gutengmorgen.ShzTy.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;

import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.ArtistCreateDTO;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.ArtistUpdateDTO;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.DtoCreateArtist;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.DtoUpdateArtist;
import com.gutengmorgen.ShzTy.services.AlbumService;
import com.gutengmorgen.ShzTy.services.ArtistService;
import com.gutengmorgen.ShzTy.services.InsertDTO;
import com.gutengmorgen.ShzTy.services.MainServices;
import com.gutengmorgen.ShzTy.services.ReturnDTO;
import com.gutengmorgen.ShzTy.services.TrackService;

public class DtoMapper {
    private CustomTable<?> t;
    private Long id;
    private CustomDialog d;
    private DTO_MODEL dm;

    public DtoMapper(CustomTable<?> t, Long idEntity, CustomDialog d, DTO_MODEL dto_MODEL) {
	this.t = t;
	this.id = idEntity;
	this.d = d;
	this.dm = dto_MODEL;
    }

    public void mapper() {
	String name = t.getName();

	if (name.contains("Ar")) {
	    ArtistService se = new ArtistService();
	    if (dm == DTO_MODEL.RETURN) {
//		d.autoFillReturn(se.getReturnArtistById(id));
	    } else if (dm == DTO_MODEL.UPDATE) {
//		d.autoFillToInsert(DtoUpdateArtist.class);
		d.okButton.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
//			List<Object> result = d.getResult();
//			DtoUpdateArtist dto = new DtoUpdateArtist((String) result.get(0), (java.sql.Date) result.get(1),
//				(String) result.get(2), (String) result.get(3), (String) result.get(4),
//				(Set<Long>) result.get(5), (Set<Long>) result.get(6));
//			DtoSimpleReturnArtist reDto = se.update(dto, id);
//			((MainTableModel<DtoSimpleReturnArtist>) t.getModel()).updateRow(t.getSelectedRow(), reDto);

			d.dispose();
		    }
		});
	    } else if (dm == DTO_MODEL.CREATE) {
//		d.autoFillToInsert(DtoCreateArtist.class);
		d.okButton.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
//			List<Object> result = d.getResult();
//			DtoCreateArtist dto = new DtoCreateArtist((String) result.get(0), (java.sql.Date) result.get(1),
//				(String) result.get(2), (String) result.get(3), (String) result.get(4),
//				(Set<Long>) result.get(5), (Set<Long>) result.get(6));
//			((MainTableModel<DtoSimpleReturnArtist>) t.getModel()).insertRow(se.save(dto));

			d.dispose();
		    }
		});
	    }
	} else if (name.contains("Al")) {
	    AlbumService se = new AlbumService();
	    se.getAlbumById(id);
	} else if (name.contains("Tr")) {
	    TrackService se = new TrackService();
	    se.getTrackById(id);
	}
    }

    public void mapper(JTable t, DTO_MODEL dm) {
	String name = t.getName();

	if (name.contains("Ar")) {
//	    ArtistService service = new ArtistService();
	    if (dm == DTO_MODEL.RETURN) {
		// do stuff with service;
	    } else if (dm == DTO_MODEL.UPDATE) {
		// do stuff with service;
	    } else if (dm == DTO_MODEL.CREATE) {
		// do stuff with service;
	    }
	} else if (name.contains("Al")) {
//	    AlbumService se = new AlbumService();
	    if (dm == DTO_MODEL.RETURN) {
		// do stuff with service;
	    } else if (dm == DTO_MODEL.UPDATE) {
		// do stuff with service;
	    } else if (dm == DTO_MODEL.CREATE) {
		// do stuff with service;
	    }
	} else if (name.contains("Tr")) {
//	    TrackService se = new TrackService();
	    if (dm == DTO_MODEL.RETURN) {
		// do stuff with service;
	    } else if (dm == DTO_MODEL.UPDATE) {
		// do stuff with service;
	    } else if (dm == DTO_MODEL.CREATE) {
		// do stuff with service;
	    }
	}
    }

    public void contra() {
	if (dm.equals(DTO_MODEL.RETURN)) {
//	    d.autoFillReturn(search());
	} else if (dm.equals(DTO_MODEL.UPDATE) || dm.equals(DTO_MODEL.CREATE)) {
//	    d.autoFillToInsert((Class<?>) search());
	}
    }

    public static void search(CustomTable<?> table, Long idEntity, CustomDialog dialog, DTO_MODEL model) {
	if (table.getName().contains("Ar")) {
	    impact(new ArtistService(), table, dialog, model, idEntity, new ArtistCreateDTO(), new ArtistUpdateDTO());
	} else if(table.getName().contains("Al")) {
//	    impact(new AlbumService(), table, dialog, model, idEntity, null, null);
	}

	dialog.setVisible(true);
    }

    // necesita el dto return, dto create y dto update
    public <R extends ReturnDTO> void action(CustomTable<R> table, CustomDialog dialog, MainServices<R> service,
	    InsertDTO dtoCreate, InsertDTO dtoUpdate) {
	d.okButton.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {

		// TODO: probrar crear un metodo para ejecutar una accion para cada entidad como
		// swingworks
		MainTableModel<R> tm = table.getCustomModel();

		if (dm.equals(DTO_MODEL.RETURN)) {
//		    service.getById(id);
		} else if (dm.equals(DTO_MODEL.CREATE)) {
		    tm.insertRow(service.save(dtoCreate));
		} else if (dm.equals(DTO_MODEL.UPDATE)) {
		    tm.updateRow(t.getSelectedRow(), service.update(dtoUpdate, id));
		}
		d.dispose();
	    }
	});
    }

    public static <R extends ReturnDTO> void execute(MainServices<R> service, CustomDialog d, Long id) {
	d.autoFillReturn(service.getById(id));
    }

    public static void insertion(CustomTable<?> table, CustomDialog d) {
//	d.autoFillToInsert(table.getSubjectClass());
    }
    
    //NOTE: hacer que el dtomapper acepte un generic type?
    @SuppressWarnings("unchecked")
    public static <R extends ReturnDTO> void impact(MainServices<R> services, CustomTable<?> table, CustomDialog dialog,
	    DTO_MODEL model, Long id, Object save, Object update) {

	if (model == DTO_MODEL.RETURN) {
	    dialog.autoFillReturn(services.getById(id));
	} else if (model == DTO_MODEL.CREATE) {
	    dialog.autoFillToInsert(table.getCreateClass());
	    MainTableModel<R> tm = (MainTableModel<R>) table.getCustomModel();
	    dialog.okAction(tm, services, save);
	} else if (model == DTO_MODEL.UPDATE) {
	    dialog.autoFillToInsert(table.getUpdateClass());
	    MainTableModel<R> tm = (MainTableModel<R>) table.getCustomModel();
	    dialog.okAction(tm, services, table.getSelectedRow(), id, update);
	}
    }
}
