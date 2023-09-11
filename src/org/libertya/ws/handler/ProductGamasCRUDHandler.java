package org.libertya.ws.handler;

import org.openXpertya.model.MProductGamas;
import org.openXpertya.model.PO;

public class ProductGamasCRUDHandler extends ProductTiersCRUDHandler {

	@Override
	PO getPOEntity(int id) {
		return new MProductGamas(getCtx(), id, getTrxName());
	}

	@Override
	String getEntityName() {
		return "product gamas";
	}

	@Override
	String getIDColumnName() {
		return "M_Product_Gamas_ID";
	}

	@Override
	int getEntityID(PO po) {
		return ((MProductGamas)po).getM_Product_Gamas_ID();
	}


}
