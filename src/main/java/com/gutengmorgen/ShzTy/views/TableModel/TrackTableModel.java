package com.gutengmorgen.ShzTy.views.TableModel;

import com.gutengmorgen.ShzTy.models.Tracks.DtoTracks.TrackSimpleReturnDTO;
import com.gutengmorgen.ShzTy.services.TrackService;

public class TrackTableModel extends TotalModel<TrackSimpleReturnDTO> {
    private static final long serialVersionUID = -264696072105143647L;

    public TrackTableModel(boolean initModel) {
	super(TrackSimpleReturnDTO.class);
	if(initModel)
	    connection();
    }

    @Override
    public void connection() {
	super.list = new TrackService().simpleList();
    }
}
