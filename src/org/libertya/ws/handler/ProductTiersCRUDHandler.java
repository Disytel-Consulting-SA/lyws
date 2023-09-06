package org.libertya.ws.handler;

import java.util.HashMap;

import org.libertya.ws.bean.parameter.ParameterBean;
import org.libertya.ws.bean.result.ResultBean;
import org.libertya.ws.exception.ModelException;
import org.openXpertya.model.PO;
import org.openXpertya.util.CLogger;


/**
 * Product Tiers hace referencia a ProductLines, ProductGamas y ProductGategory en una sola entidad
 * @param data parametros correspondientes
 * @return ResultBean con OK, ERROR, etc.
 */
public abstract class ProductTiersCRUDHandler extends GeneralHandler {
	
	//getPOEntiy() -> ej: return new MProductGamas(getCtx(), id, getTrxName())
	abstract PO getPOEntity(int id);
	//getEntityName() -> ej: "product gamas"
	abstract String getEntityName();
	//getIDColumnName -> ej: "M_Product_Gamas_ID"
	abstract String getIDColumnName();
	//getEntityID -> ej: pg.getM_Product_Gamas_ID()
	abstract int getEntityID(PO po);
	
	/**
	 * Alta de product Tier
	 * @param data parametros correspondientes
	 * @return ResultBean con OK, ERROR, etc.
	 */
	public ResultBean productTierCreate(ParameterBean data)
	{
		try {
			PO po = getPOEntity(0);
			setValues(po, data.getMainTable(), true);
			if (!po.save())
				throw new ModelException("Error al persistir " + getEntityName() + ":" + CLogger.retrieveErrorAsString());
			
			//Commitear la transaccion
			commitTransaction();
			
			//Respuesta
			HashMap<String, String> result = new HashMap<String, String>();
			result.put(getIDColumnName(), Integer.toString(getEntityID(po)));
			return new ResultBean(false, null, result);
			
		}
		catch (ModelException me) {
			return processException(me, wsInvocationArguments(data));
		}
		catch (Exception e) {
			return processException(e, wsInvocationArguments(data));
		}
		finally	{
			closeTransaction();
		}
	}
	
	/**
	 * Recupera product tier a partir de su ID
	 * @param data parametros correspondientes
	 * @param ID identificador de entidad a recuperar
	 */
	public ResultBean productTierRetrieveByID(ParameterBean data, int ID) {
		
		try
		{	
			//recuperar por ID
			PO po = getPOEntity(ID);
			
			if (po == null || getEntityID(po) == 0)
				throw new ModelException("No se ha podido recuperar " + getEntityName() + " a partir de los parametros indicados");

			//respuesta
			ResultBean result = new ResultBean(false, null, poToMap(po, true));
			return result;
		}
		catch (ModelException me) {
			return processException(me, wsInvocationArguments(data));
		}
		catch (Exception e) {
			return processException(e, wsInvocationArguments(data));
		}	
	}
	
	
	/**
	 * Actualiza product tier a partir de su ID
	 * @param data parametros correspondientes
	 * @param ID identificador de product tier a actualizar
	 */
	public ResultBean productTierUpdateByID(ParameterBean data, int ID) {
		
		try
		{	
			//recuperar por ID
			PO po = getPOEntity(ID);
			if (po == null || getEntityID(po) == 0)
				throw new ModelException("No se ha podido recuperar " + getEntityName() + " a partir de los parametros indicados");
			setValues(po, data.getMainTable(), false);
			if (!po.save())
				throw new ModelException("Error al actualizar " + getEntityName() + ":" + CLogger.retrieveErrorAsString());
			
			//commit
			commitTransaction();
			
			//respuesta
			return new ResultBean(false, null, null);
		}
		catch (ModelException me) {
			return processException(me, wsInvocationArguments(data));
		}
		catch (Exception e) {
			return processException(e, wsInvocationArguments(data));
		}
		finally	{
			closeTransaction();
		}
		
	}

	/**
	 * Eliminación de product tier
	 * @param data parametros correspondientes
	 * @param ID identificador de product tier a eliminar
	 * @return ResultBean con OK, ERROR, etc.
	 */
	public ResultBean productTierDelete(ParameterBean data, int ID)
	{
		try
		{
			PO po = getPOEntity(ID);
			if (po==null || getEntityID(po) == 0 )
				throw new ModelException("No se ha podido recuperar " + getEntityName() + " a partir de los parametros indicados");
			po.setIsActive(false);
			if (!po.save())
				throw new ModelException("Error al eliminar " + getEntityName() + ":" + CLogger.retrieveErrorAsString());

			//commit
			commitTransaction();
			
			//respuesta
			return new ResultBean(false, null, null);
		}
		catch (ModelException me) {
			return processException(me, wsInvocationArguments(data));
		}
		catch (Exception e) {
			return processException(e, wsInvocationArguments(data));
		}
		finally	{
			closeTransaction();
		}
		
	}

}
