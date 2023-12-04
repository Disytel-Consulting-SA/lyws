package org.libertya.ws.handler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import org.libertya.ws.bean.parameter.DocumentParameterBean;
import org.libertya.ws.bean.parameter.ParameterBean;
import org.libertya.ws.bean.result.DocumentResultBean;
import org.libertya.ws.bean.result.ResultBean;
import org.libertya.ws.exception.ModelException;
import org.openXpertya.model.MInvoice;
import org.openXpertya.model.MJournal;
import org.openXpertya.model.MJournalLine;
import org.openXpertya.model.PO;
import org.openXpertya.process.DocAction;
import org.openXpertya.process.DocumentEngine;
import org.openXpertya.util.CLogger;
import org.openXpertya.util.Env;
import org.openXpertya.util.Msg;

public class JournalDocumentHandler extends GeneralHandler {

	public JournalDocumentHandler() { }
	
	//Alta de Diario
	
	/**
	 * Alta de Diario
	 * @param data parametros correspondientes
	 * @param completeJournal
	 * @return ResultBean con OK o ERROR
	 */
    public ResultBean journalCreate(DocumentParameterBean data, boolean completeJournal) {
    	//Aclaraciones desarrollo:
		//En todos los registros utilizar la organización ingresada (Diario y Líneas)
	
		try {
			//Config inicial
			init(data, new String[]{}, new Object[]{});
			
			//En todos los registros utilizar la organización ingresada
			//Integer orgID = data.getOrgID();
			//La fecha contable siempre es la misma que la fecha del documento		
			//Timestamp dateDoc = Timestamp.valueOf(toLowerCaseKeys(data.getMainTable()).get("datedoc"));
			
			//Creacion de journal
			MJournal aJournal = new MJournal(Env.getCtx(), 0, null);
			setValues(aJournal, data.getMainTable(), true);
			if (!aJournal.save())
				throw new ModelException("Error al persistir diario:" + CLogger.retrieveErrorAsString());
			
			
			//En todos los registros utilizar organización y fecha ingresada
			Integer orgID = data.getOrgID();
			Timestamp dateDoc = aJournal.getDateDoc();
			
			
			//Obtener lineas
			ArrayList<HashMap<String, String>> lines = new ArrayList<HashMap<String, String>>();
			for (HashMap<String, String> line : data.getDocumentLines())
				lines.add(toLowerCaseKeys(line));
			
			//Instanciar y persistir lineas
			for (HashMap<String, String> line : lines)
			{
				MJournalLine aJournalLine = new MJournalLine(getCtx(), 0, getTrxName());
				setValues(aJournalLine, line, true);
				aJournalLine.setGL_Journal_ID(aJournal.getGL_Journal_ID());
				
				//setear org_id y fecha
				aJournalLine.setAD_Org_ID(orgID);
				aJournalLine.setDateAcct(dateDoc);
				
				
				if (!aJournalLine.save())
					throw new ModelException("Error al persistir linea:" + CLogger.retrieveErrorAsString());	
			}
			
			// Recargar Journal, sino no se completa el documento
			aJournal =  new MJournal( getCtx(), aJournal.getGL_Journal_ID(), getTrxName());
				
			//Completar documento si corresponde
			if (completeJournal && !DocumentEngine.processAndSave(aJournal, DocAction.ACTION_Complete, false))
				throw new ModelException("Error al completar diario:" + Msg.parseTranslation(getCtx(), aJournal.getProcessMsg()));
			
			//Commitear la transaccion
			commitTransaction();
			
			//Retornar valor
			HashMap<String, String> result = new HashMap<String, String>();
			result.put("GL_Journal_ID", Integer.toString(aJournal.getGL_Journal_ID()));
			
			return new ResultBean(false, null, result);
			
		}catch(Exception e) {
			
			//Devolver error
			return new ResultBean(true, e.getMessage(), null);
		
		}finally	{
			
			closeTransaction();
		}
	}

    
    //Baja de Diario en Borrador
    
    private ResultBean journalDelete(ParameterBean data, int journalID, String columnName, String columnCriteria) {
    	try
		{
    		//Configuracion inicial
			init(data, new String[]{"GL_Journal_ID", "columnName", "columnCriteria"}, new Object[]{journalID, columnName, columnCriteria});
    		
			MJournal aJournal = (MJournal)getPO("GL_Journal", journalID, columnName, columnCriteria, true, false, true, true);
			if (!aJournal.delete(false))
				throw new ModelException("Error al intentar eliminar diario " + aJournal.getGL_Journal_ID()+ ": " + CLogger.retrieveErrorAsString());
			
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
	 * Baja de Diario en borrador por id
	 * @param data parametros correspondientes
	 * @param orderID identificador -> GL_Journal_ID
	 * @return ResultBean con OK o ERROR
	 */
    public ResultBean journalDeleteByID(ParameterBean data, int journalID) {
    	
    	return journalDelete(data, journalID, null, null);
	}
    
    /**
	 * Baja de Diario en borrador por columna
	 * @param data parametros correspondientes
	 * @param columnName columna correspondiente
	 * @param columnCriteria criterio de eliminacion
	 * @return ResultBean con OK o ERROR
	 */
    public ResultBean journalDeleteByColumn(ParameterBean data, String columnName, String columnCriteria) {
    	
    	return journalDelete(data, -1, columnName, columnCriteria);
	}
    
    //Completado de Diario
    
    /**
	 * Completa un Diario en borrador. Se puede indicar por ID, o por Nombre y Criterio de Columna
	 * 		La segunda forma debe devolver solo un registro resultante, o se retornará un error
	 * @param data parametros correspondientes
	 * @param journalID identificador del diario (GL_Journal_ID)
	 * @param columnName y columnCriteria columna y valor a filtrar para recuperar el diario en cuestion
	 * @return ResultBean con IsError, ErrorMsg 
	 */
	protected ResultBean journalComplete(ParameterBean data, int journalID, String columnName, String columnCriteria)
	{
		try
		{
			//Configuracion inicial
			init(data, new String[]{"GL_Journal_ID", "columnName", "columnCriteria"}, new Object[]{journalID, columnName, columnCriteria});

			//Recuperar y Completar diario si corresponde
			MJournal aJournal = (MJournal)getPO("GL_Journal", journalID, columnName, columnCriteria, true, false, true, true);

			//Si el documento ya está completado retornar error
			if (DocAction.STATUS_Completed.equals(aJournal.getDocStatus()))
				throw new ModelException("Imposible completar el documento: ya se encuentra completado.");

			//Completar el documento			
			if (!DocumentEngine.processAndSave(aJournal, DocAction.ACTION_Complete, false))
				throw new ModelException("Error al completar diario:" + Msg.parseTranslation(getCtx(), aJournal.getProcessMsg()));
					
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
	 * Completado de diario en borrador por id
	 * @param journalID identificador
	 * @return ResultBean con OK o ERROR
	 */
    public ResultBean journalCompleteByID(ParameterBean data, int journalID) {
    	return journalComplete(data, journalID, null, null);
	}
    
    /**
	 * Completado de diario en borrador por columna
	 * @param columnName columna correspondiente
	 * @param columnCriteria criterio de eliminacion
	 * @return ResultBean con OK o ERROR
	 */
    public ResultBean journalCompleteByColumn(ParameterBean data, String columnName, String columnCriteria) {
		return journalComplete(data, -1, columnName, columnCriteria);
	}
    
    //Anulación de Diario
    
    /**
	 * Anula un Diario.  indicado por ID, o por Nombre y Criterio de Columna
	 * 		Para la segunda opción, en caso de recuperar más de uno se anularán todos.  En caso de error no se anulará ninguno.
	 * @param data parametros correspondientes
	 * @param journalID identificador del diario (GL_Journal_ID)
	 * @param columnName y columnCriteria columna y valor a filtrar para recuperar el/los diarios
	 * @return ResultBean con IsError, ErrorMsg
	 */
	protected ResultBean journalVoid(ParameterBean data, int journalID, String columnName, String columnCriteria)
	{
		try
		{
			//Configuracion inicial
			init(data, new String[]{"GL_Journal_ID", "columnName", "columnCriteria"}, new Object[]{journalID, columnName, columnCriteria});
			
			//Recuperar y anular diario
			MJournal aJournal = (MJournal)getPO("GL_Journal", journalID, columnName, columnCriteria, true, false, true, true);
			if (!DocumentEngine.processAndSave((DocAction)aJournal, DocAction.ACTION_Void, false)) {
				throw new ModelException("Error al anular diario:" + Msg.parseTranslation(getCtx(), ((DocAction)aJournal).getProcessMsg()));
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
	 * Anulacion de Diario por id
	 * @param journalID identificador (GL_Journal_ID)
	 * @return ResultBean con IsError, ErrorMsg
	 */
    public ResultBean journalVoidByID(ParameterBean data, int journalID) {
		return journalVoid(data, journalID, null, null);
	}
    
    /**
	 * Anulacion de diario por columna
	 * @param columnName columna correspondiente
	 * @param columnCriteria criterio de eliminacion
	 * @return ResultBean con IsError, ErrorMsg
	 */
    public ResultBean journalVoidByColumn(ParameterBean data, String columnName, String columnCriteria) {
    	return journalVoid(data, -1, columnName, columnCriteria);
	}
    
    //Consulta de Diario
    
    private DocumentResultBean documentRetrieveJournal(ParameterBean data, int journalID, String column, String value) {
    	try
		{
			//Configuracion inicial
			init(data, new String[]{"GL_Journal_ID", "columnName", "columnCriteria"}, new Object[]{journalID, column, value});

			//Recuperar diario
			MJournal aJournal = (MJournal)getPO("GL_Journal", journalID, column, value, true, false, true, true);
			if (aJournal == null || aJournal.getGL_Journal_ID()==0)
				throw new ModelException("No se ha podido recuperar diario a partir de los parametros indicados");

			
			//Resultado
			DocumentResultBean result = new DocumentResultBean(false, null, poToMap(aJournal, true));
			
			//Recuperar lineas (asientos) y agregarlas al result como document lines
			PO[] pos = getPOs("GL_JournalLine", -1, "GL_Journal_ID", Integer.toString(aJournal.getGL_Journal_ID()), true, false, false, true);
			for (PO po : pos) {
				result.addDocumentLine(poToMap(po, true));
			}
			
			return result;
		}
		catch (ModelException me) {
			return (DocumentResultBean) processException(me, wsInvocationArguments(data));
		}
		catch (Exception e) {
			return (DocumentResultBean) processException(e, wsInvocationArguments(data));
		}
		finally	{
			closeTransaction();
		}
	}
    
    
    /**
	 * Consulta de Diario por id
	 * @param journalID identificador (GL_Journal_ID)
	 * @return ResultBean con datos
	 */
    public DocumentResultBean documentRetrieveJournalByID(ParameterBean data, int journalID) {
    	
    	return documentRetrieveJournal(data, journalID, null, null);
	}

	/**
	 * Consulta de Diario por columna
	 * @param data parametros correspondientes
	 * @param columnName columna correspondiente
	 * @param columnCriteria criterio de eliminacion
	 * @return ResultBean con datos
	 */
    public DocumentResultBean documentRetrieveJournalByColumn(ParameterBean data, String column, String value) {
    	
    	return documentRetrieveJournal(data, -1, column, value);
	}

}
