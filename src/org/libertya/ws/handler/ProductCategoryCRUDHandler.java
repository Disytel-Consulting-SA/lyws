package org.libertya.ws.handler;

import org.openXpertya.model.MProductCategory;
import org.openXpertya.model.PO;

public class ProductCategoryCRUDHandler extends ProductTiersCRUDHandler {
	
	@Override
	PO getPOEntity(int id) {
		return new MProductCategory(getCtx(), 0, getTrxName());
	}

	@Override
	String getEntityName() {
		return "product category";
	}

	@Override
	String getIDColumnName() {
		return "M_Product_Category_ID";
	}

	@Override
	int getEntityID(PO po) {
		return ((MProductCategory)po).getM_Product_Category_ID();
	}

}
