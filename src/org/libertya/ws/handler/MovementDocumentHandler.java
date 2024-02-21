package org.libertya.ws.handler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.libertya.ws.bean.parameter.DocumentParameterBean;
import org.libertya.ws.bean.parameter.ParameterBean;
import org.libertya.ws.bean.result.DocumentResultBean;
import org.libertya.ws.bean.result.ResultBean;
import org.libertya.ws.exception.ModelException;
import org.openXpertya.model.MAccount;
import org.openXpertya.model.MAcctSchema;
import org.openXpertya.model.MInvoice;
import org.openXpertya.model.MJournal;
import org.openXpertya.model.MJournalLine;
import org.openXpertya.model.MMovement;
import org.openXpertya.model.MMovementLine;
import org.openXpertya.model.PO;
import org.openXpertya.model.X_C_ValidCombination;
import org.openXpertya.process.DocAction;
import org.openXpertya.process.DocumentEngine;
import org.openXpertya.util.CLogger;
import org.openXpertya.util.DB;
import org.openXpertya.util.Env;
import org.openXpertya.util.Msg;
import org.openXpertya.util.Trx;

public class MovementDocumentHandler extends GeneralHandler {

	public MovementDocumentHandler() { }

	
	/**
	 * Creacion de movimiento de inventario
	 * @param data parametros correspondientes
	 * @param complete true/false dependiendo si se desea completar el documento
	 * @return ResultBean con datos
	 */
    public ResultBean movementCreate(DocumentParameterBean data, boolean complete) {
    	try {
			//Config inicial
			init(data, new String[]{}, new Object[]{});
			
			//Creacion de movimiento
			MMovement aMovement = new MMovement(Env.getCtx(), 0, getTrxName());
			setValues(aMovement, data.getMainTable(), true);
			
			if (!aMovement.save())
				throw new ModelException("Error al persistir movimiento de inventario:" + CLogger.retrieveErrorAsString());
			
			//Obtener lineas
			ArrayList<HashMap<String, String>> lines = new ArrayList<HashMap<String, String>>();
			for (HashMap<String, String> line : data.getDocumentLines())
				lines.add(toLowerCaseKeys(line));
			
			//Instanciar y persistir lineas
			for (HashMap<String, String> line : lines)
			{
				MMovementLine aMovementLine = new MMovementLine(getCtx(), 0, getTrxName());
				setValues(aMovementLine, line, true);
				aMovementLine.setM_Movement_ID(aMovement.getM_Movement_ID());
				if (!aMovementLine.save())
					throw new ModelException("Error al persistir linea de movimiento de inventario:" + CLogger.retrieveErrorAsString());	
			}
			
			// Recargar movimiento
			aMovement = new MMovement( getCtx(), aMovement.getM_Movement_ID(), getTrxName());
			
			//Completar documento?
			if (complete && !DocumentEngine.processAndSave(aMovement, DocAction.ACTION_Complete, false))
				throw new ModelException("Error al completar movimiento de inventario:" + Msg.parseTranslation(getCtx(), aMovement.getProcessMsg()));
			
			//Commitear transaccion
			commitTransaction();
			
			//Retornar
			HashMap<String, String> result = new HashMap<String, String>();
			result.put("M_Movement_ID", Integer.toString(aMovement.getM_Movement_ID()));
			return new ResultBean(false, null, result);
			
		}catch(Exception e) {
			
			//Devolver error
			return new ResultBean(true, e.getMessage(), null);
		
		}finally	{
			
			closeTransaction();
		}
    }
    
    
    /**
	 * Completa un movimiento de inventario en borrador. Se puede indicar por ID, o por Nombre y Criterio de Columna
	 * 		La segunda forma debe devolver solo un registro resultante, o se retornará un error
	 * @param data parametros correspondientes
	 * @param movementID identificador del movimiento (M_Movement_ID)
	 * @param columnName y columnCriteria columna y valor a filtrar para recuperar el movimiento
	 * @return ResultBean con IsError, ErrorMsg 
	 */
	protected ResultBean movementComplete(ParameterBean data, int movementID, String columnName, String columnCriteria)
	{
		try
		{
			//Configuracion inicial
			init(data, new String[]{"M_Movement_ID", "columnName", "columnCriteria"}, new Object[]{movementID, columnName, columnCriteria});

			//Recuperar y Completar diario si corresponde
			MMovement aMovement = (MMovement)getPO("M_Movement", movementID, columnName, columnCriteria, true, false, true, true);

			//Si el documento ya está completado retornar error
			if (DocAction.STATUS_Completed.equals(aMovement.getDocStatus()))
				throw new ModelException("Imposible completar el documento: ya se encuentra completado.");

			//Completar documento			
			if (!DocumentEngine.processAndSave(aMovement, DocAction.ACTION_Complete, false))
				throw new ModelException("Error al completar movimiento de inventario:" + Msg.parseTranslation(getCtx(), aMovement.getProcessMsg()));
					
			//Commitear transaccion
			commitTransaction();
			
			//Retornar valor ok			
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
	 * Completado de movimiento de inventario por id
	 * @param movementID identificador
	 * @return ResultBean con OK o ERROR
	 */
    public ResultBean movementCompleteByID(ParameterBean data, int movementID) {
    	return movementComplete(data, movementID, null, null);
	}
    
    /**
	 * Completado de movimiento de inventario por columna
	 * @param columnName columna correspondiente
	 * @param columnCriteria criterio de eliminacion
	 * @return ResultBean con OK o ERROR
	 */
    public ResultBean movementCompleteByColumn(ParameterBean data, String columnName, String columnCriteria) {
		return movementComplete(data, -1, columnName, columnCriteria);
	}
    
    
       /**
   	 * Anula un Movimiento de inventario.  indicado por ID, o por Nombre y Criterio de Columna
   	 * 		Para la segunda opción, en caso de recuperar más de uno se anularán todos.  En caso de error no se anulará ninguno.
   	 * @param data parametros correspondientes
   	 * @param movementID identificador del movimiento (M_Movement_ID)
   	 * @param columnName y columnCriteria columna y valor a filtrar para recuperar el/los movimientos
   	 * @return ResultBean con IsError, ErrorMsg
   	 */
	protected ResultBean movementVoid(ParameterBean data, int movementID, String columnName, String columnCriteria)
	{
		try
		{
			//Configuracion inicial
			init(data, new String[]{"M_Movement_ID", "columnName", "columnCriteria"}, new Object[]{movementID, columnName, columnCriteria});
			
			//Recuperar y anular movimiento
//			MMovement aMovement = (MMovement)getPO("M_Movement", movementID, columnName, columnCriteria, true, false, true, true);
//			System.out.println("ID: " + aMovement.getM_Movement_ID());
//			System.out.println("Desc:" + aMovement.getDescription());
//			DocAction movementAction = (DocAction)aMovement;
//			if (!DocumentEngine.processAndSave(movementAction, DocAction.ACTION_Void, false)) {
//				throw new ModelException("Error al anular movimiento:" + Msg.parseTranslation(getCtx(), ((DocAction)aMovement).getProcessMsg()));
//			}
			
			// Recuperar y anular el inventario
			PO[] pos = getPOs("M_Movement", movementID, columnName, columnCriteria, true, false, false, true);
			for (PO po : pos) {
				if (!DocumentEngine.processAndSave((DocAction)po, DocAction.ACTION_Void, false)) {
					throw new ModelException("Error al anular movimiento:" + Msg.parseTranslation(getCtx(), ((DocAction)po).getProcessMsg()));
				}
			}
						
			//Commitear transaccion
			commitTransaction();
			
			//Retornar valores
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
	 * Anulacion de movimiento de inventario por id
	 * @param movementID identificador (M_Movement_ID)
	 * @return ResultBean con IsError, ErrorMsg
	 */
    public ResultBean movementVoidByID(ParameterBean data, int movementID) {
		return movementVoid(data, movementID, null, null);
	}
    
    /**
	 * Anulacion de movimiento de inventario por columna
	 * @param columnName columna correspondiente
	 * @param columnCriteria criterio de eliminacion
	 * @return ResultBean con IsError, ErrorMsg
	 */
    public ResultBean movementVoidByColumn(ParameterBean data, String columnName, String columnCriteria) {
    	return movementVoid(data, -1, columnName, columnCriteria);
	}

}
