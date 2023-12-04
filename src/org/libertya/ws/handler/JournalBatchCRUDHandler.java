package org.libertya.ws.handler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import org.libertya.ws.bean.parameter.OrderParameterBean;
import org.libertya.ws.bean.parameter.ParameterBean;
import org.libertya.ws.bean.result.DocumentResultBean;
import org.libertya.ws.bean.result.ResultBean;
import org.libertya.ws.exception.ModelException;
import org.libertya.wse.utils.MapTranslator;
import org.openXpertya.model.MInventoryLine;
import org.openXpertya.model.MInvoice;
import org.openXpertya.model.MJournal;
import org.openXpertya.model.MJournalBatch;
import org.openXpertya.model.MJournalLine;
import org.openXpertya.model.PO;
import org.openXpertya.model.X_C_Invoice;
import org.openXpertya.process.DocAction;
import org.openXpertya.process.DocumentEngine;
import org.openXpertya.util.CLogger;
import org.openXpertya.util.Env;
import org.openXpertya.util.Msg;

public class JournalBatchCRUDHandler extends GeneralHandler {
	

	/**
	 * =========================== IMPORTANTE ===========================
	 * 
	 * Esta clase tiene metodos incompletos porque se optó por 
	 * desarrollar JournalDocumentHandler primero y llegado el 
	 * caso que se necesite se continuará con la lógica de JournalBatch
	 * 
	 * =========================== 2023-11-23 ===========================
	 * 
	 */

	public JournalBatchCRUDHandler() { }
	
	/**
	 * Alta de lote de asientos
	 * @param data parametros correspondientes
	 * @param completeJournalBatch
	 * @return ResultBean con OK o ERROR
	 */
    public ResultBean journalBatchCreate(OrderParameterBean data, boolean completeJournalBatch) {
    	//Aclaraciones desarrollo:
    		//En todos los registros utilizar la organización ingresada como parámetro del lote (Diario y Líneas)
    		//La fecha contable siempre es la misma que la fecha del documento, tanto para el Lote como los Diarios (Asiento)
    	
    	try {
			//Config inicial
			init(data, new String[]{}, new Object[]{});
			
			//En todos los registros utilizar la organización ingresada como parámetro del lote (Diario y Líneas)
			Integer orgID = data.getOrgID();
			//La fecha contable siempre es la misma que la fecha del documento, tanto para el Lote como los Diarios (Asiento)			
			Timestamp dateDoc = Timestamp.valueOf(toLowerCaseKeys(data.getMainTable()).get("datedoc"));
			
			//Creacion de journal batch
			MJournalBatch jbatch = new MJournalBatch(Env.getCtx(), 0, null);
			setValues(jbatch, data.getMainTable(), true);
			if (!jbatch.save())
				throw new ModelException("Error al persistir lote:" + CLogger.retrieveErrorAsString());
			
			
			//Journals
			ArrayList<HashMap<String, String>> journals = new ArrayList<HashMap<String, String>>();
			for (HashMap<String, String> journal : data.getDocumentLines())
				journals.add(toLowerCaseKeys(journal));
			
			// Instanciar y persistir journals
			for (HashMap<String, String> journal : journals)
			{
				MJournal aJournal = new MJournal(getCtx(), 0, getTrxName());
				setValues(aJournal, journal, true);
				
				//setear org_id y fecha
				aJournal.setAD_Org_ID(orgID);
				aJournal.setDateDoc(dateDoc);
				
				if (!aJournal.save())
					throw new ModelException("Error al persistir diario:" + CLogger.retrieveErrorAsString());
				
				//Journal Lines
				for (HashMap<String, String> jline : journals)
				{
					MJournalLine aJournalLine = new MJournalLine(getCtx(), 0, getTrxName());
					setValues(aJournalLine, jline, true);
					
					//setear org_id
					aJournalLine.setAD_Org_ID(orgID);
					
					if (!aJournalLine.save())
						throw new ModelException("Error al persistir linea:" + CLogger.retrieveErrorAsString());					
					
				}	
			}
				
			//Completar documento si corresponde
			if (completeJournalBatch && !DocumentEngine.processAndSave(jbatch, DocAction.ACTION_Complete, false))
				throw new ModelException("Error al completar lote:" + Msg.parseTranslation(getCtx(), jbatch.getProcessMsg()));
			
			//Commitear la transaccion
			commitTransaction();
			
			//Retornar valor
			HashMap<String, String> result = new HashMap<String, String>();
			result.put("GL_JournalBatch_ID", "");
			result.put("JournalBatch_DocumentNO", "");
			
			return new ResultBean(false, null, result);
			
		}catch(Exception e) {
			
			//Devolver error
			return new ResultBean(true, e.getMessage(), null);
		
		}finally	{
			
			closeTransaction();
		}
	}
    
    
    
    //Baja de Lote de Asientos en Borrador
    
    private ResultBean journalBatchDelete(ParameterBean data, int orderID, String columnName, String columnCriteria) {
    	try
		{
    		//Configuracion inicial
			init(data, new String[]{"GL_JournalBatch_ID", "columnName", "columnCriteria"}, new Object[]{orderID, columnName, columnCriteria});
    		
			MJournalBatch jbatch = (MJournalBatch)getPO("GL_JournalBatch", orderID, columnName, columnCriteria, true, false, true, true);
			if (!jbatch.delete(false))
				throw new ModelException("Error al intentar eliminar lote " + jbatch.getGL_JournalBatch_ID() + ": " + CLogger.retrieveErrorAsString());
			
			//Commitear transaccion
			commitTransaction();
			
			//Retornar
			return new ResultBean(false, null, null);
		}
		catch (Exception e) {
			
			return processException(e, wsInvocationArguments(data));
		}
		finally	{
			closeTransaction();
		}
    }
    
    /**
	 * Baja de lote de asientos en borrador por id
	 * @param data parametros correspondientes
	 * @param orderID identificador -> GL_JournalBatch_ID
	 * @return ResultBean con OK o ERROR
	 */
    public ResultBean journalBatchDeleteByID(ParameterBean data, int orderID) {
    	
    	return journalBatchDelete(data, orderID, null, null);
	}
    
    /**
	 * Baja de lote de asientos en borrador por columna
	 * @param data parametros correspondientes
	 * @param columnName columna correspondiente
	 * @param columnCriteria criterio de eliminacion
	 * @return ResultBean con OK o ERROR
	 */
    public ResultBean journalBatchDeleteByColumn(ParameterBean data, String columnName, String columnCriteria) {
    	
    	return journalBatchDelete(data, -1, columnName, columnCriteria);
	}
    
    //Completado de Lote de Asientos
    
    /**
	 * Completa un lote de asientos en borrador. Se puede indicar por ID, o por Nombre y Criterio de Columna
	 * 		La segunda forma debe devolver solo un registro resultante, o se retornará un error
	 * @param data parametros correspondientes
	 * @param orderID identificador del asiento (GL_JournalBatch_ID)
	 * @param columnName y columnCriteria columna y valor a filtrar para recuperar el lote en cuestion
	 * @return ResultBean con IsError, ErrorMsg 
	 */
	protected ResultBean journalBatchComplete(ParameterBean data, int orderID, String columnName, String columnCriteria)
	{
		try
		{
			//Configuracion inicial
			init(data, new String[]{"GL_JournalBatch_ID", "columnName", "columnCriteria"}, new Object[]{orderID, columnName, columnCriteria});

			//Recuperar y Completar lote si corresponde
			MJournalBatch jbatch = (MJournalBatch)getPO("GL_JournalBatch", orderID, columnName, columnCriteria, true, false, true, true);

			//Si el documento ya está completado retornar error
			if (DocAction.STATUS_Completed.equals(jbatch.getDocStatus()))
				throw new ModelException("Imposible completar el documento: ya se encuentra completado.");

			//Completar el documento			
			if (!DocumentEngine.processAndSave(jbatch, DocAction.ACTION_Complete, false))
				throw new ModelException("Error al completar lote:" + Msg.parseTranslation(getCtx(), jbatch.getProcessMsg()));
					
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
	 * Completado de lote de asientos en borrador por id
	 * @param journalBatchID identificador
	 * @return ResultBean con OK o ERROR
	 */
    public ResultBean journalBatchCompleteByID(ParameterBean data, int journalBatchID) {
    	return journalBatchComplete(data, journalBatchID, null, null);
	}
    
    /**
	 * Completado de lote de asientos en borrador por columna
	 * @param columnName columna correspondiente
	 * @param columnCriteria criterio de eliminacion
	 * @return ResultBean con OK o ERROR
	 */
    public ResultBean journalBatchCompleteByColumn(ParameterBean data, String columnName, String columnCriteria) {
		return journalBatchComplete(data, -1, columnName, columnCriteria);
	}
    
    //Anulación de Lote de Asientos
    
    /**
	 * Anula un lote de asientos.  indicado por ID, o por Nombre y Criterio de Columna
	 * 		Para la segunda opción, en caso de recuperar más de uno se anularán todos.  En caso de error no se anulará ninguno.
	 * @param data parametros correspondientes
	 * @param journalBatchID identificador del lote (GL_JournalBatch_ID)
	 * @param columnName y columnCriteria columna y valor a filtrar para recuperar el/los lotes
	 * @return ResultBean con IsError, ErrorMsg
	 */
	protected ResultBean journalBatchVoid(ParameterBean data, int journalBatchID, String columnName, String columnCriteria)
	{
		try
		{
			//Configuracion inicial
			init(data, new String[]{"GL_JournalBatch_ID", "columnName", "columnCriteria"}, new Object[]{journalBatchID, columnName, columnCriteria});
			
			//Recuperar y anular journal batch
			PO[] pos = getPOs("GL_JournalBatch", journalBatchID, columnName, columnCriteria, true, false, false, true);
			for (PO po : pos) {
				if (!DocumentEngine.processAndSave((DocAction)po, DocAction.ACTION_Void, false)) {
					throw new ModelException("Error al anular lote de asientos:" + Msg.parseTranslation(getCtx(), ((DocAction)po).getProcessMsg()));
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
	 * Anulacion de lote de asientos por id
	 * @param journalBatchID identificador
	 * @return ResultBean con IsError, ErrorMsg
	 */
    public ResultBean journalBatchVoidByID(ParameterBean data, int journalBatchID) {
		return journalBatchVoid(data, journalBatchID, null, null);
	}
    
    /**
	 * Anulacion de lote de asientos por columna
	 * @param columnName columna correspondiente
	 * @param columnCriteria criterio de eliminacion
	 * @return ResultBean con IsError, ErrorMsg
	 */
    public ResultBean journalBatchVoidByColumn(ParameterBean data, String columnName, String columnCriteria) {
    	return journalBatchVoid(data, -1, columnName, columnCriteria);
	}
    
    //Consulta de Lotes de Asientos
    
    private DocumentResultBean documentRetrieveJournalBatch(ParameterBean data, int journalBatchID, String column, String value) {
    	
    	try
		{
			//Configuracion inicial
			init(data, new String[]{"GL_JournalBatch_ID", "columnName", "columnCriteria"}, new Object[]{journalBatchID, column, value});

			//Recuperar lote
			MJournalBatch jbatch = (MJournalBatch)getPO("GL_JournalBatch", journalBatchID, column, value, true, false, true, true);
			if (jbatch == null || jbatch.getGL_JournalBatch_ID()==0)
				throw new ModelException("No se ha podido recuperar lote de asientos a partir de los parametros indicados");

			//Resultado
			HashMap<String, String> map = new HashMap<String, String>();
			
			
			
			//obtener todos los diarios
			
				//obtener todos los asientos
			
			//impactar todo en map
			
			
			ResultBean result = new ResultBean(false, null, poToMap(jbatch, true));
			
			//return new DocumentResultBean(false, null, result);
		}
		catch (ModelException me) {
			//return processException(me, wsInvocationArguments(data));
		}
		catch (Exception e) {
			//return processException(e, wsInvocationArguments(data));
		}
		finally	{
			closeTransaction();
		}
    	return null;
	}
    
    
    /**
	 * Consulta de lote de asientos por id
	 * @param journalBatchID identificador
	 * @return ResultBean con datos
	 */
    public DocumentResultBean documentRetrieveJournalBatchByID(ParameterBean data, int journalBatchID) {
    	
    	return documentRetrieveJournalBatch(data, journalBatchID, null, null);
	}

	/**
	 * Consulta de lote de asientos por columna
	 * @param data parametros correspondientes
	 * @param columnName columna correspondiente
	 * @param columnCriteria criterio de eliminacion
	 * @return ResultBean con datos
	 */
    public DocumentResultBean documentRetrieveJournalBatchByColumn(ParameterBean data, String column, String value) {
    	
    	return documentRetrieveJournalBatch(data, -1, column, value);
	}

}
