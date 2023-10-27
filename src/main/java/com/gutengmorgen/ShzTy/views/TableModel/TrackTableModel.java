package com.gutengmorgen.ShzTy.views.TableModel;

import com.gutengmorgen.ShzTy.models.Tracks.DtoTracks.TrackViewModel;
import com.gutengmorgen.ShzTy.services.TrackService;

import java.util.ArrayList;

public class TrackTableModel extends TotalModel<TrackViewModel> {
    private static final long serialVersionUID = -264696072105143647L;

    public TrackTableModel(boolean initModel) {
	super(TrackViewModel.class);
	if(initModel)
	    connection();
    }

    @Override
    public void connection() {
	list = new ArrayList<>(new TrackService().viewList());
    }
}
