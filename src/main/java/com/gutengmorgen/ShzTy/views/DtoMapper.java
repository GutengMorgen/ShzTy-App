package com.gutengmorgen.ShzTy.views;

import com.gutengmorgen.ShzTy.services.ReturnDTO;
import com.gutengmorgen.ShzTy.views.Components.CustomDialog;
import com.gutengmorgen.ShzTy.views.Components.CustomTable;
import com.gutengmorgen.ShzTy.views.Extras.ModelDTO;

public class DtoMapper {
    public static void search(CustomTable<?> table, CustomDialog dialog, ModelDTO model) {
	if (table.getName().contains("Ar")) {
	    //NOTE: poner mainservices, object save y object update en customtable y luego referenciarlos?
	    impact(table, dialog, model);
	} else if (table.getName().contains("Al")) {
	    impact(table, dialog, model);
	} else if (table.getName().contains("Tr")) {
	    impact(table, dialog, model);
	}

	dialog.setVisible(true);
    }

    public static <R extends ReturnDTO> void impact(CustomTable<R> t, CustomDialog d, ModelDTO md) {

	if (md == ModelDTO.RETURN) {
	    d.autoFillReturn(t.getService().getById(t.getIdEntity()));
	} else if (md == ModelDTO.CREATE) {
	    d.autoFillToInsert(t.getCreateObject());
	    d.okAction(t.getCustomModel(), t.getService());
	} else if (md == ModelDTO.UPDATE) {
	    d.autoFillToInsert(t.getUpdateObject());
	    d.okAction(t.getCustomModel(), t.getService(), t.getSelectedRow(), t.getIdEntity());
	}

	d.setVisible(true);
    }
}
