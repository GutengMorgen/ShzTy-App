package com.gutengmorgen.ShzTy.views;

import com.gutengmorgen.ShzTy.services.extras.ReturnDTO;
import com.gutengmorgen.ShzTy.views.Components.CustomDialog;
import com.gutengmorgen.ShzTy.views.Components.CustomTable;
import com.gutengmorgen.ShzTy.views.Extras.ModelDTO;

public class DtoMapper {
    public static <R extends ReturnDTO> void impact(CustomTable<R> t, CustomDialog d, ModelDTO md) {
        // NOTE: cubrir el service en algo general para luego llamar a ese general y
        // iniciar al service aqui
        if (md == ModelDTO.RETURN) {
            d.autoFill(t.getService().getById(t.getIdEntity()));
        } else if (md == ModelDTO.CREATE) {
            d.autoFillInsert(t.getCreateObject());
            d.okAction(t, md);
        } else if (md == ModelDTO.UPDATE) {
            d.autoFillInsert(t.getUpdateObject());
            d.okAction(t, md);
        }

        d.setVisible(true);
    }
}
