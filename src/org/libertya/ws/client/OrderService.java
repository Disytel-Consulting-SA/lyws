package org.libertya.ws.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.openXpertya.model.MDocType;
import org.openXpertya.model.MOrder;
import org.openXpertya.model.MOrderLine;
import org.openXpertya.plugin.common.CustomServiceInterface;
import org.openXpertya.plugin.common.DynamicArgument;
import org.openXpertya.plugin.common.DynamicResult;
import org.openXpertya.process.DocAction;
import org.openXpertya.process.DocumentEngine;
import org.openXpertya.util.Env;

//import org.libertya.custom.cintolo.model.MOrderCastingData;

public class OrderService implements CustomServiceInterface{
	
	
	
	/*parámetros:
	 * 
	 * id de una Orden de Despacho (c_order). Validar:
	 * 			Tipo de docuemento debe ser una O.Despacho.
	 * 
	 * Estado debe ser "Completo" 
	 * 			Comentar (Anular) la validación de Uniones. Esa no hace falta.
	 * 
	 * Una lista de datos de producción (tabla C_Order_Casting_Data) con la siguiente info:
	 * 		c_orderline_id (referencia a la línea de la Orden de Despacho. 
	 * 			Validar:Integridad: la línea debe pertenecer a la Orden de Despacho.
			
			Orden de Producción (string con 4 letras) 
			
			Cantidad: cantidad del producto. 
				Validar:Pueden venir varios datos de producción referenciando a la misma línea, pero la suma del total no debe superar la cantidad de la línea. (si mal no recuerdo ya está hecho en el modelo).
			
			URL (campo nuevo, hay que crearlo) con una referencia a Orden de Preparación en el MRP.
			
			Un campo Procesar tipo SI/NO que indique si se debe cambiar el estado de la Orden de Despacho a Procesado.
	
	*/
	
	
	//params: C_Order_ID, Data, Procesar
	private final String ORDEN_DESPACHO_DOCTYPE_KEY = "SOSOD";
	
	public DynamicResult execute(DynamicArgument args, Properties ctx, String trxName) {
		
		DynamicResult result = new DynamicResult();
		result.setError(false);
		HashMap<String, ArrayList<String>> req = args.getContent();
		
		
		//Order
		int order_id = Integer.valueOf(req.get("C_Order_ID").get(0));
		MOrder order = new MOrder(Env.getCtx(), order_id, trxName);
		if( !isOrdenDespacho(order) ) {
			result.setError(true);
			result.setErrorMsg("C_Order_ID = " + order_id + " no corresponde a una orden de despacho");
		}
		if( !isCompletedStatus(order)) {
			result.setError(true);
			result.setErrorMsg("El estado de la orden de despacho debe ser completado");
		}
		
		
		//Map de C_Order_Casting_Data
		
//		for(HashMap<String, ArrayList<String>> ocd : req.get("Data")) {
//			
//			MOrderCastingData ocd new MOrderCastingData(Env.getCtx(), 0, trxName);
//			
//			ocd.get("C_OrderLine_ID"); //hashmap?
//		}
		
		//para cada una de las C_Order_Casting_Data:
		//primero que nada, se debe crear y guardar la C_Order_Casting_Data
		
		String orderline_id = null;
		String orden_prod = null; //4 letras
		String cantidad = null; //
		String URL = null; //nuevo campo, generar
		
		
		//la linea pertenece a la orden de despacho?
		MOrderLine ol = new MOrderLine(Env.getCtx(), Integer.valueOf(orderline_id), trxName );
		if(!orderLineBelongsToDispatchOrder(order,ol)) {
			//la linea no pertenece a la orden de despacho
			result.setError(true);
			result.setErrorMsg("La linea C_OrderLine_ID = " + ol.getC_OrderLine_ID() + 
					" no corresponde a la orden de despacho C_Order_ID = " + order.getC_Order_ID());
		}
		
		
		
		/*
		 * 
		 * 
		<rawArguments xsi:type="org:ArrayOf_tns3_ListedMap" soapenc:arrayType="com:ListedMap[]" xmlns:com="http://common.wse.libertya.org"/>
    	<Item>
			<key xsi:type="xsd:string">C_Order_ID</key>
			<values xsi:type="xsd:string[]" soapenc:arrayType="xsd:string[]">
				<Item xsi:type="xsd:string">12341234</Item>
			</values>
		</Item>
	  </rawArguments>
		
		*/
		
				
		
		
		
		
		
		
		String procesar = req.get("Procesar").get(0);
		if( procesar.equalsIgnoreCase("Y") ) {
			//procesar
			//A que estado pasa? closed? o algun estado Custom de cintolo?
			//DocumentEngine.processAndSave(order, DocAction.???, false);
		}
		
		
		
		
		
		return result;
	}
	
	
	
	private boolean isOrdenDespacho(MOrder order){
		MDocType doctype = new MDocType(Env.getCtx(), order.getDocTypeID(), null);
		return doctype.getDocTypeKey().equalsIgnoreCase(ORDEN_DESPACHO_DOCTYPE_KEY);
	}
	
	private boolean isCompletedStatus(MOrder order){
		return order.getDocStatus().equalsIgnoreCase(DocAction.STATUS_Completed);
	}
	
	
	private boolean orderLineBelongsToDispatchOrder(MOrder order, MOrderLine line) {
		return line.getC_Order_ID() == order.getC_Order_ID();
	}
	
	
	
	
	

}
