package org.libertya.ws.handler;

import org.openXpertya.model.MProductLines;
import org.openXpertya.model.PO;

public class ProductLinesCRUDHandler extends ProductTiersCRUDHandler {

	@Override
	PO getPOEntity(int id) {
		return new MProductLines(getCtx(), 0, getTrxName());
	}

	@Override
	String getEntityName() {
		return "product lines";
	}

	@Override
	String getIDColumnName() {
		return "M_Product_Lines_ID";
	}

	@Override
	int getEntityID(PO po) {
		return ((MProductLines)po).getM_Product_Lines_ID();
	}
	
}
