package org.libertya.ws.client;


import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.codec.binary.Base64;
import org.libertya.ws.bean.parameter.AllocationParameterBean;
import org.libertya.ws.bean.parameter.BPartnerParameterBean;
import org.libertya.ws.bean.parameter.CustomServiceParameterBean;
import org.libertya.ws.bean.parameter.DocumentParameterBean;
import org.libertya.ws.bean.parameter.FilteredColumnsParameterBean;
import org.libertya.ws.bean.parameter.InvoiceParameterBean;
import org.libertya.ws.bean.parameter.OrderParameterBean;
import org.libertya.ws.bean.parameter.ParameterBean;
import org.libertya.ws.bean.result.CustomServiceResultBean;
import org.libertya.ws.bean.result.DocumentResultBean;
import org.libertya.ws.bean.result.MultipleDocumentsResultBean;
import org.libertya.ws.bean.result.MultipleRecordsResultBean;
import org.libertya.ws.bean.result.ResultBean;
import org.openXpertya.model.MJournal;
import org.openXpertya.model.MJournalLine;
import org.openXpertya.util.Env;

import ws.libertya.org.LibertyaWSServiceLocator;
import ws.libertya.org.LibertyaWSSoapBindingStub;

public class LibertyaWSClient {

	/**
	 * For test-purposes only!
	 * 
	 */
	public static void main(String[] args)
	{
		try
		{
			// Conexión al WS
			LibertyaWSServiceLocator locator = new LibertyaWSServiceLocator();
			// Redefinir URL del servicio?
			if (args.length == 0)
				System.err.println("No se ha especificado URL del servicio.  Utilizando valor por defecto: http://localhost:8080/axis/services/LibertyaWS");
			else
				locator.setLibertyaWSEndpointAddress(args[0]);
//			locator.setLibertyaWSEndpointAddress("http://<IP_ADDR>:8080/axis/services/LibertyaWS");
//			Recuperar el servicio
//			ws.libertya.org.LibertyaWS lyws = locator.getLibertyaWS();
			
			//instancia de forma local para no tener que iniciar jboss
			org.libertya.ws.LibertyaWS lyws = new org.libertya.ws.LibertyaWSImpl();
			
			//creacion del ParameterBean
//			ParameterBean test_data = new ParameterBean("AdminLibertya", "AdminLibertya", 1010016, 1010053);
			
			
			//===========================================================
			//============= Testing Movimientos de inventario ===========
			//===========================================================
			
			//========= Alta de movimiento =====
				//1. ResultBean movementCreate(DocumentParameterBean data, boolean complete);
			//========= Completado de movimiento =====
				//2. ResultBean movementCompleteByID(ParameterBean data, int movementID);
				//3. ResultBean movementCompleteByColumn(ParameterBean data, String columnName, String columnCriteria);
			//========= Anulacion de movimiento en borrador =====
				//4. ResultBean movementVoidById(ParameterBean data, int movementID);
				//5. ResultBean movementVoidByColumn(ParameterBean data, String columnName, String columnCriteria);
		
			
			//1. ResultBean movementCreate(DocumentParameterBean data, boolean complete); OK	
//			DocumentParameterBean test_data = new DocumentParameterBean("AdminLibertya", "AdminLibertya", 1010016, 1010053);
//			
//			test_data.addColumnToMainTable("description", "mov_void_by_column");
//			test_data.addColumnToMainTable("C_DocType_ID", "1010528"); //Movimiento de material
//			
//				//lines
//				test_data.newDocumentLine();
//				test_data.addColumnToCurrentLine("movementqty", "1");
//				test_data.addColumnToCurrentLine("m_locator_id", "1010278");
//				test_data.addColumnToCurrentLine("m_locatorto_id", "1010575");
//				test_data.addColumnToCurrentLine("m_product_id", "1015721");
//				
//			ResultBean restest = lyws.movementCreate(test_data, false);
//			System.out.println(restest);

			
			//2. ResultBean movementCompleteByID(ParameterBean data, int movementID);
//			DocumentParameterBean test_data = new DocumentParameterBean("AdminLibertya", "AdminLibertya", 1010016, 1010053);
//				
//			ResultBean restest = lyws.movementCompleteByColumn(test_data, "description", "mov_complete_by_column");
//			System.out.println(restest);
			
			
			//3. ResultBean movementCompleteByColumn(ParameterBean data, String columnName, String columnCriteria); OK
//			DocumentParameterBean test_data = new DocumentParameterBean("AdminLibertya", "AdminLibertya", 1010016, 1010053);
//				
//			ResultBean restest = lyws.movementCompleteByID(test_data, 1011316);
//			System.out.println(restest);
			
			
			//4.ResultBean movementVoidById(ParameterBean data, int movementID);
			DocumentParameterBean test_data = new DocumentParameterBean("AdminLibertya", "AdminLibertya", 1010016, 1010053);
				
			ResultBean restest = lyws.movementVoidById(test_data, 1011317);
			System.out.println(restest);
			
			
			//5.ResultBean movementVoidByColumn(ParameterBean data, String columnName, String columnCriteria);
//			DocumentParameterBean test_data = new DocumentParameterBean("AdminLibertya", "AdminLibertya", 1010016, 1010053);
//				
//			ResultBean restest = lyws.movementVoidByColumn(test_data, "description", "mov_void_by_column");
//			System.out.println(restest);
		    
			 
			
			
			
			
			//=========================================
			//============= Testing Diarios ===========
			//=========================================
			
			//========= Alta de Diarios =====
				//1. ResultBean journalCreate(OrderParameterBean data, boolean completeJournal)
			//========= Baja de Diarios en borrador =====
				//2. ResultBean journalDeleteByID(ParameterBean data, int orderID)
				//3. ResultBean journalDeleteByColumn**(ParameterBean data, java.lang.String columnName, java.lang.String columnCriteria)
			//========= Completado de Diarios =====
				//4. ResultBean journalCompleteByID(ParameterBean data, int journalBatchID)
				//5. ResultBean journalCompleteByColumn(ParameterBean data, java.lang.String columnName, java.lang.String columnCriteria)
			//========= Anulación de Diarios =====
				//6. ResultBean journalVoidByID(ParameterBean data,int journalBatchID)
				//7. ResultBean journalVoidByColumn(ParameterBean data, java.lang.String columnName, java.lang.String columnCriteria)
			//#======== Consulta de Diarios =====
				//8. DocumentResultBean documentRetrieveJournalByID(ParameterBean data, int journalBatchID)
				//9. DocumentResultBean documentRetrieveJournalByColumn(ParameterBean data, java.lang.String column, java.lang.String value)
			
			
			//1. ResultBean journalCreate(OrderParameterBean data, boolean completeJournal)	OK		
//			DocumentParameterBean test_data = new DocumentParameterBean("AdminLibertya", "AdminLibertya", 1010016, 1010053);
//			
//			test_data.addColumnToMainTable("description", "test_lyws 20240112-1");
//			test_data.addColumnToMainTable("C_DocType_ID", "1010503"); //diario del mayor
//			test_data.addColumnToMainTable("C_Acctschema_id", "1010016");
//			test_data.addColumnToMainTable("GL_Category_ID", "1010098");
//			test_data.addColumnToMainTable("C_Conversiontype_id", "114");
//			
//				//lines
//				test_data.newDocumentLine();
//				test_data.addColumnToCurrentLine("amtacctdr", "1000"); //debe
//				test_data.addColumnToCurrentLine("amtacctrc", "0"); //haber
//				test_data.addColumnToCurrentLine("amtsourcedr", "1000");
//				test_data.addColumnToCurrentLine("amtsourcerc", "0");
//				test_data.addColumnToCurrentLine("c_currency_id", "118");
//				test_data.addColumnToCurrentLine("c_conversiontype_id", "114");
//
//				//valores para valid combination
//				//test_data.addColumnToCurrentLine("c_elementvalue_id", "1012825"); //cuenta
//				//test_data.addColumnToCurrentLine("C_ValidCombination_ID", "1034424"); //valid combination
//				test_data.addColumnToCurrentLine("vc_elementvalue", "1012836");
//				test_data.addColumnToCurrentLine("vc_productvalue", "1066323");
//				//test_data.addColumnToCurrentLine("vc_bpartnervalue", "1019825");
//				//test_data.addColumnToCurrentLine("vc_projectvalue", "1000005");
//				
//			ResultBean restest = lyws.journalCreate(test_data, false);
//			System.out.println(restest);
			
			//2. ResultBean journalDeleteByID(ParameterBean data, int orderID) OK
//			ParameterBean test_data2 = new ParameterBean("AdminLibertya", "AdminLibertya", 1010016, 1010053);
//			ResultBean restest2 = lyws.journalDeleteByID(test_data2, 1010483);
//			System.out.println(restest2);
			
			//3. ResultBean journalDeleteByColumn**(ParameterBean data, java.lang.String columnName, java.lang.String columnCriteria) OK
//			ParameterBean test_data3 = new ParameterBean("AdminLibertya", "AdminLibertya", 1010016, 1010053);
//			ResultBean restest3 = lyws.journalDeleteByColumn(test_data3, "description", "test_lyws_completar");
//			System.out.println(restest3);
			
			
			
			
			//4. ResultBean journalCompleteByID(ParameterBean data, int journalBatchID) OK
//			ParameterBean test_data4 = new ParameterBean("AdminLibertya", "AdminLibertya", 1010016, 1010053);
//			ResultBean restest4 = lyws.journalCompleteByID(test_data4, 1010485);
//			System.out.println(restest4);
			
			//5. ResultBean journalCompleteByColumn(ParameterBean data, java.lang.String columnName, java.lang.String columnCriteria) OK
//			ParameterBean test_data5 = new ParameterBean("AdminLibertya", "AdminLibertya", 1010016, 1010053);
//			ResultBean restest5 = lyws.journalCompleteByColumn(test_data5, "description", "test_lyws_completar");
//			System.out.println(restest5);

			
			//6. ResultBean journalVoidByID(ParameterBean data,int journalBatchID) OK
//			ParameterBean test_data6 = new ParameterBean("AdminLibertya", "AdminLibertya", 1010016, 1010053);
//			ResultBean restest6 = lyws.journalVoidByID(test_data6, 1010487);
//			System.out.println(restest6);
			
			
			//7. ResultBean journalVoidByColumn(ParameterBean data, java.lang.String columnName, java.lang.String columnCriteria)
//			ParameterBean test_data7 = new ParameterBean("AdminLibertya", "AdminLibertya", 1010016, 1010053);
//			ResultBean restest7 = lyws.journalVoidByColumn(test_data7, "description", "test_lyws_completar2");
//			System.out.println(restest7);	

			////8. DocumentResultBean documentRetrieveJournalByID(ParameterBean data, int journalBatchID) OK
//			ParameterBean test_data = new ParameterBean("AdminLibertya", "AdminLibertya", 1010016, 1010053);
//			DocumentResultBean restest = lyws.documentRetrieveJournalByID(test_data, 1010449);
//			System.out.println(restest);
			
			//9. DocumentResultBean documentRetrieveJournalByColumn(ParameterBean data, java.lang.String column, java.lang.String value) OK
//			ParameterBean test_data = new ParameterBean("AdminLibertya", "AdminLibertya", 1010016, 1010053);
//			DocumentResultBean restest = lyws.documentRetrieveJournalByColumn(test_data, "description", "test");
//			System.out.println(restest);
			
			
			
			
			
			
			

			
			
			
			
			
			/* #######################################################
			 * ####################### Lineas ########################
			 * #######################################################
			 */
			
			//Create
//			ParameterBean test_data = new ParameterBean("AdminLibertya", "AdminLibertya", 1010016, 1010053);
//			test_data.addColumnToMainTable("name", "test_lyws");
//			test_data.addColumnToMainTable("description", "test_lyws");
//			test_data.addColumnToMainTable("value", "test_lyws");
//			ResultBean restest = lyws.productLinesCreate(test_data);
//			System.out.println(restest);
			
			//Read
//			ParameterBean test_data2 = new ParameterBean("AdminLibertya", "AdminLibertya", 1010016, 0);
//			ResultBean restest = lyws.productLinesRetrieveByID(test_data2, Integer.valueOf("1010201"));
//			System.out.println(restest);
			
			//Update
//			ParameterBean test_data3 = new ParameterBean("AdminLibertya", "AdminLibertya", 1010016, 0);
//			test_data3.addColumnToMainTable("description", "description update by id exitoso");
//			ResultBean restest = lyws.productLinesUpdateByID(test_data3, Integer.valueOf("1010201"));
//			System.out.println(restest);
			
			//Delete
//			ParameterBean test_data4 = new ParameterBean("AdminLibertya", "AdminLibertya", 1010016, 0);
//			ResultBean restest = lyws.productLinesDelete(test_data4, Integer.valueOf("1010201"));
//			System.out.println(restest);
			
			
			
			/* #######################################################
			 * ####################### Gamas ########################
			 * #######################################################
			 */
			//Create
//			ParameterBean test_data = new ParameterBean("AdminLibertya", "AdminLibertya", 1010016, 1010053);
//			test_data.addColumnToMainTable("name", "test_lyws");
//			test_data.addColumnToMainTable("description", "test_lyws");
//			test_data.addColumnToMainTable("value", "test_lyws");
//			ResultBean restest = lyws.productGamasCreate(test_data);
//			System.out.println(restest);
			
			//Read
//			ParameterBean test_data2 = new ParameterBean("AdminLibertya", "AdminLibertya", 1010016, 0);
//			ResultBean restest = lyws.productGamasRetrieveByID(test_data2, Integer.valueOf("1010210"));
//			System.out.println(restest);
			
			//Update
//			ParameterBean test_data3 = new ParameterBean("AdminLibertya", "AdminLibertya", 1010016, 0);
//			test_data3.addColumnToMainTable("description", "description update by id exitoso");
//			ResultBean restest = lyws.productGamasUpdateByID(test_data3, Integer.valueOf("1010210"));
//			System.out.println(restest);
			
			//Delete
//			ParameterBean test_data4 = new ParameterBean("AdminLibertya", "AdminLibertya", 1010016, 0);
//			ResultBean restest = lyws.productGamasDelete(test_data4, Integer.valueOf("1010210"));
//			System.out.println(restest);
			
			
			
			/* #######################################################
			 * ####################### Category ########################
			 * #######################################################
			 */
			//Create
//			ParameterBean test_data = new ParameterBean("AdminLibertya", "AdminLibertya", 1010016, 1010053);
//			test_data.addColumnToMainTable("name", "test_lyws");
//			test_data.addColumnToMainTable("description", "test_lyws");
//			test_data.addColumnToMainTable("value", "test_lyws");
//			ResultBean restest = lyws.productCategoryCreate(test_data);
//			System.out.println(restest);
			
			//Read
//			ParameterBean test_data2 = new ParameterBean("AdminLibertya", "AdminLibertya", 1010016, 0);
//			ResultBean restest = lyws.productCategoryRetrieveByID(test_data2, Integer.valueOf("1010341"));
//			System.out.println(restest);
			
			//Update
//			ParameterBean test_data3 = new ParameterBean("AdminLibertya", "AdminLibertya", 1010016, 0);
//			test_data3.addColumnToMainTable("description", "description update by id exitoso");
//			ResultBean restest = lyws.productCategoryUpdateByID(test_data3, Integer.valueOf("1010341"));
//			System.out.println(restest);
			
			//Delete
//			ParameterBean test_data4 = new ParameterBean("AdminLibertya", "AdminLibertya", 1010016, 0);
//			ResultBean restest = lyws.productCategoryDelete(test_data4, Integer.valueOf("1010341"));
//			System.out.println(restest);

			
			
			
			
			
//			test.addColumnToMainTable("Description", "Test");
//			test.addColumnToMainTable("IsSold", "Y");
//			test.addColumnToMainTable("C_TaxCategory_ID", "1000023");
//			test.addColumnToMainTable("M_Product_Category_ID", "1000056");
//			test.addColumnToMainTable("RequireSerNo", "N");
//			test.addColumnToMainTable("C_UOM_ID", "100");
//			test.addColumnToMainTable("Name", "Test Jaco ( / Vta Directa)");
//			test.addColumnToMainTable("ItHasReplenishment", "N");
//			test.addColumnToMainTable("IsActive", "Y");
//			test.addColumnToMainTable("ProductType", "S");
//			test.addColumnToMainTable("GuaranteeType", "3 meses");
//			test.addColumnToMainTable("Attr03", "25");
//			test.addColumnToMainTable("CheckoutPlace", "B");
//			test.addColumnToMainTable("Attr01", "27");
//			test.addColumnToMainTable("UPC", "1254789632548");
//			test.addColumnToMainTable("IsPurchased", "Y");
//			test.addColumnToMainTable("Attr02", "112218");
//			ResultBean restest = lyws.productCreate(test);
//			System.out.println(restest);
					
			
/*			
			ParameterBean data = new ParameterBean("AdminLibertya", "AdminLibertya", 1010016, 1010053);
			ResultBean result = lyws.processGeneratePromotionCode(data);
			if (!result.isError())
				System.out.println(result);
			else
				System.out.println(result.getErrorMsg());
*/		
						
			
			/*
			ParameterBean data = new ParameterBean("AdminLibertya", "AdminLibertya", 1010016, 1010053);
			data.addColumnToMainTable("tablename", "C_Invoice");
			data.addColumnToMainTable("recordID", "1021812");
			ResultBean result = lyws.processRetrievePdfFromDocument(data);
			
			System.out.println(result);
			
			if (!result.isError()) {
				byte[] pdf = Base64.decodeBase64(result.getMainResult().get("PDF"));
				FileOutputStream fos = new FileOutputStream("/tmp/documento2.pdf");
				fos.write(pdf);
				fos.close();			
			}
	*/
			
			
			
			//((LibertyaWSSoapBindingStub)lyws).setTimeout(Integer.parseInt(args[1]));
			
			
			
//			FilteredColumnsParameterBean unBean = new FilteredColumnsParameterBean("Supervisor", "Supervisor", 0, 0);
//			unBean.addColumnToFilter("Line");
//			unBean.addColumnToFilter("QtyOrdered");
//			unBean.addColumnToFilter("QtyReserved");
//			unBean.addColumnToFilter("QtyDelivered");
//			
//			MultipleRecordsResultBean unResult = lyws.recordQueryDirect(unBean, "c_orderline", "c_order_id = (select c_order_id from c_order where retrieveuid = 'h3_1364281_c_order')");
//			System.out.println(unResult);
			
//			// Prueba 14: Crear un recibo de cliente cancelando una factura existente con varios medios de pago
//			AllocationParameterBean beanRC = new AllocationParameterBean("AdminCMD", "AdminCMD", 1010016, 1010053);
//			beanRC.addColumnToHeader("Description", "Un RC desde WS");
//			beanRC.addColumnToHeader("C_DocType_ID", "1010569");
//			// Factura a cobrar
//			beanRC.newInvoice();
//			beanRC.addColumnToCurrentInvoice("C_Invoice_ID", "3941962");
//			beanRC.addColumnToCurrentInvoice("Amount", "1");
//			// Medio de pago: Transferencia bancaria
//			beanRC.newPayment();
//			beanRC.addColumnToCurrentPayment("C_POSPaymentMedium_ID", "1010152");
//			beanRC.addColumnToCurrentPayment("Amount", "1");
//			beanRC.addColumnToCurrentPayment("C_BankAccount_ID", "1010073");
//			beanRC.addColumnToCurrentPayment("C_Bank_ID", "1010079");
//			beanRC.addColumnToCurrentPayment("A_Bank", "Un Banco");
//			beanRC.addColumnToCurrentPayment("TransferNo", "1234");
//			beanRC.addColumnToCurrentPayment("TransferDate", "2016-06-09 10:20:00");
//			// Invocar a la crecion de recibo para la entidad comercial cuyo value sea MC
//			ResultBean result14 = lyws.allocationCreateReceipt(beanRC, 1012142, null, null);
//			System.out.println(result14);
//			System.out.println(" -------------- \n ");
//			
//			if (1==1)
//				return;
			
//			ParameterBean data987 = new ParameterBean("Supervisor", "Supervisor", 0, 0);
//			MultipleDocumentsResultBean accesses =  lyws.userClientOrgAccessQuery(data987);
//			System.out.println(accesses);
			
//			if (1==1)
//				return;
			
//			ParameterBean data29 = new ParameterBean("AdminLibertya", "AdminLibertya", 1010016, 1010053);
//			data29.addColumnToMainTable("CouponBatchNumber", "128937");
//			data29.addColumnToMainTable("M_EntidadFinanciera_ID", "1010219");
//			data29.addColumnToMainTable("AD_Org_ID", "1010053");
//			System.out.println(lyws.processFiscalPrinterClose(data29));
			
//			FilteredColumnsParameterBean unBean = new FilteredColumnsParameterBean("AdminLibertya", "Putrajaya424", 1010016, 1010053);
////			unBean.addColumnToFilter("Name");
////			unBean.addColumnToFilter("AD_User_ID");
//			MultipleRecordsResultBean unResult = lyws.recordQuery(unBean, "v_o_ad_user", "name='System'", false);
////			MultipleRecordsResultBean unResult = lyws.recordQueryDirect(unBean, "ad_user", "name='System'");
//			
//			System.out.println(unResult);
//			if (true)
//				return;
			
			
//			ParameterBean test = new ParameterBean("AdminLibertya", "AdminLibertya", 1010016, 1010053);
//			test.addColumnToMainTable("Description", "Test");
//			test.addColumnToMainTable("IsSold", "Y");
//			test.addColumnToMainTable("C_TaxCategory_ID", "1000023");
//			test.addColumnToMainTable("M_Product_Category_ID", "1000056");
//			test.addColumnToMainTable("RequireSerNo", "N");
//			test.addColumnToMainTable("C_UOM_ID", "100");
//			test.addColumnToMainTable("Name", "Test Jaco ( / Vta Directa)");
//			test.addColumnToMainTable("ItHasReplenishment", "N");
//			test.addColumnToMainTable("IsActive", "Y");
//			test.addColumnToMainTable("ProductType", "S");
//			test.addColumnToMainTable("GuaranteeType", "3 meses");
//			test.addColumnToMainTable("Attr03", "25");
//			test.addColumnToMainTable("CheckoutPlace", "B");
//			test.addColumnToMainTable("Attr01", "27");
//			test.addColumnToMainTable("UPC", "1254789632548");
//			test.addColumnToMainTable("IsPurchased", "Y");
//			test.addColumnToMainTable("Attr02", "112218");
//			ResultBean restest = lyws.productCreate(test);
//			System.out.println(restest);
//			
//		//	if (1==1)
//		//		return;
//			
//			
////			FilteredColumnsParameterBean test = new FilteredColumnsParameterBean( "AdminLibertya", "1234567", 1010016, 0);
////			MultipleRecordsResultBean cur = lyws.recordQuery(test, "C_Currency", null, false);
////			System.out.println(cur);
////			
//			InvoiceParameterBean data54 = new InvoiceParameterBean("AdminLibertya", "AdminLibertya", 1010016, 1010053);
//			System.out.println(lyws.invoiceDeleteByColumn(data54, "DocumentNo", "100012"));
//			System.out.println(" -------------- \n ");
//			
//			
////			DocumentParameterBean dataR = new DocumentParameterBean("AdminTECDIA", "AdminTECDIA", 1010057, 0);
////			dataR.newDocumentLine();
////			dataR.addColumnToCurrentLine("QtyEntered", "1");
////			dataR.addColumnToCurrentLine("C_OrderLine_ID", "1185497");
////			dataR.addColumnToCurrentLine("SerNo", "A002");
////			ResultBean resR = lyws.inOutCreateFromOrder(dataR, 1139926, true);
////			System.out.println(resR);
////
////			if (1==1)
////				return;
//			
//			
////			CustomServiceParameterBean test222 = new CustomServiceParameterBean("AdminTECDIA", "AdminTECDIA", 1010057, 0);
////			// Nombre de la clase a invocar
////			test222.setClassName("org.libertya.custom.ccmd.process.DeliveredOrderConfirmation");
////			// Especificacion de parametros
////			test222.addParameter("1139926", "666666", "QQQ,A002,1132161");
////			// Invocar al servicio
////			CustomServiceResultBean customResult22 = lyws.customService(test222); 
////			System.out.println(customResult22);
////
////			if (1==1)
////				return;
//			
//			
//			
//			CustomServiceParameterBean test2222 = new CustomServiceParameterBean("AdminTECDIA", "AdminTECDIA", 1010057, 0);
//			ResultBean result = null; 
//			test2222.setClassName("org.libertya.custom.ccmd.process.ReceivedOrderConfirmation");
//			test2222.addParameter("1139926", "1185497,1024249,A002");
//			CustomServiceResultBean customResult222 = lyws.customService(test2222);
//			System.out.println(customResult222);
//			
//			if (1==1)
//				return;
//			
////			CustomServiceParameterBean test222 = new CustomServiceParameterBean("AdminCMD", "AdminCMD", 1010016, 0);
////			// Nombre de la clase a invocar
////			test222.setClassName("org.libertya.custom.ccmd.process.DeliveredOrderConfirmation");
////			// Especificacion de parametros
////			test222.addParameter("C_Order_ID", "1139887");
////			// Invocar al servicio
////			CustomServiceResultBean customResult22 = lyws.customService(test222); 
////			System.out.println(customResult22);
//
////			
////			InvoiceParameterBean aBean = new InvoiceParameterBean("AdminTECDIA", "AdminTECDIA", 1010057, 1010234);
////			aBean.addColumnToHeader("DateInvoiced", "2013-10-30 11:24:05");
////			aBean.addColumnToHeader("C_DocTypeTarget_ID", "1011373");
////			System.out.println(lyws.invoiceCreateCustomerFromOrderByID(aBean, 1175051, false));
//			
//			if (1==1)
//				return;
//			
//			
//			// Prueba 4: Crear una Factura
//			InvoiceParameterBean data4 = new InvoiceParameterBean("AdminTECDIA", "AdminTECDIA", 1010057, 1010234);
//			// Opcion 1: indicando DocTypeTarget
//			data4.addColumnToHeader("C_DocType_ID", "0");
//			data4.addColumnToHeader("C_DocTypeTarget_ID", "1011373");
//			data4.addColumnToHeader("C_Letra_Comprobante_ID", "6");
//			// Opcion 2: indicando PuntoDeVenta + TipoComprobante
////			data4.addColumnToHeader("TipoComprobante", "FC");
////			data4.addColumnToHeader("PuntoDeVenta", "1");
//			data4.addColumnToHeader("DateInvoiced", "2012-08-01 11:25:00");		 // OJO CON EL FORMATO: YYYY-MM-DD HH:mm:ss  
//			data4.addColumnToHeader("C_BPartner_Location_ID", "1706373");
//			data4.addColumnToHeader("M_PriceList_ID", "1010797");
//			data4.addColumnToHeader("C_Currency_ID", "118");
//			data4.addColumnToHeader("PaymentRule", "P");
//			data4.addColumnToHeader("C_PaymentTerm_ID", "1010202");
//			data4.addColumnToHeader("ApplyPercepcion", "N");
////			data4.addColumnToHeader("CreateCashLine", "N");
////			data4.addColumnToHeader("ManualGeneralDiscount", "0.00");
//			data4.addColumnToHeader("Description", "Una factura desde WS");
//			data4.newDocumentLine();											// Especifico nueva linea
//			data4.addColumnToCurrentLine("Line", "1");							// Datos de línea 1
//			data4.addColumnToCurrentLine("QtyEntered", "1");
//			data4.addColumnToCurrentLine("PriceEntered", "43.01");
////			data4.addColumnToCurrentLine("C_Tax_ID", "1010085");	// 1010135 es de TECDIA, pero 1010085 NO
//			data4.addColumnToCurrentLine("M_Product_ID", "1023097");
//			data4.addColumnToCurrentLine("Description", "LINEA 1");
//			data4.addColumnToCurrentLine("C_OrderLine_ID", "1229033");
//			data4.addColumnToCurrentLine("CostPrice", "78.3200");
////			data4.addColumnToCurrentLine("C_UOM_ID", "1010134");  // no se debe setear, es readonly
//			data4.newOtherTaxLine();
//			data4.addColumnToCurrentOtherTaxLine("C_Tax_ID", "1010134");
//			data4.addColumnToCurrentOtherTaxLine("IsPerceptionIncluded", "N");
//			data4.addColumnToCurrentOtherTaxLine("IsTaxIncluded", "Y");
//			data4.addColumnToCurrentOtherTaxLine("TaxAmt", "18.92");
//			data4.addColumnToCurrentOtherTaxLine("TaxBaseAmt", "90.08");
//			ResultBean resultI = lyws.invoiceCreateCustomer(data4, 1706580, null, null, false); 
//			System.out.println(resultI);
//			System.out.println(" -------------- \n ");
//			ParameterBean data3 = new ParameterBean("AdminTECDIA", "AdminTECDIA", 1010057, 1010234);
//			data3.addColumnToMainTable("PrintURL", "POPO");
//			data3.addColumnToMainTable("ApplyPercepcion", "Y");
//			data3.addColumnToMainTable("AD_Org_ID", "1010235");
//			data3.addColumnToMainTable("C_PaymentTerm_ID", "1010128");
//			System.out.println(lyws.invoiceUpdateByID(data3, Integer.parseInt(resultI.getMainResult().get("C_Invoice_ID"))));
//			System.out.println(" -------------- \n ");
//			
//			
//			
//			if (1==1)
//				return;
////			
////			
////			
////			
////			
////			
////			CustomServiceParameterBean test222 = new CustomServiceParameterBean("AdminTECDIA", "AdminTECDIA", 1010057, 0);
////			// Nombre de la clase a invocar
////			test222.setClassName("org.libertya.custom.ccmd.process.DeliveredOrderConfirmation");
////			// Especificacion de parametros
////			test222.addParameter("C_Order_ID", "11398560");
////			// Invocar al servicio
////			CustomServiceResultBean customResult22 = lyws.customService(test222); 
////			System.out.println(customResult22);
//			
////			// Prueba 4: Crear un Pedido
////			OrderParameterBean data4 = new OrderParameterBean("AdminTECDIA", "AdminTECDIA", 1010057, 1010234);
////			// Opcion 1: indicando DocTypeTarget
////			data4.addColumnToHeader("C_DocTypeTarget_ID", "1011373");
////			// Opcion 2: indicando PuntoDeVenta + TipoComprobante
//////			data4.setInvoiceTipoComprobante("FC"); // addColumnToHeader("TipoComprobante", "FC");
//////			data4.setInvoicePuntoDeVenta(3); //data4.addColumnToHeader("PuntoDeVenta", "33");
////			data4.addColumnToHeader("C_BPartner_Location_ID", "1645422");
////			data4.addColumnToHeader("M_PriceList_ID", "1010690");
////			data4.addColumnToHeader("C_Currency_ID", "118");
////			data4.addColumnToHeader("PaymentRule", "Tr");
////			data4.addColumnToHeader("C_PaymentTerm_ID", "1010127");
////			data4.addColumnToHeader("CreateCashLine", "N");
////			data4.addColumnToHeader("ManualGeneralDiscount", "0.00");
////			data4.addColumnToHeader("Description", "Una factura desde WS");
////			data4.addColumnToHeader("M_Warehouse_ID", "1010248");
////			data4.addColumnToHeader("DateOrdered", 	"2014-08-13 12:00:00");
////			data4.addColumnToHeader("DateAcct", 	"2014-08-14 12:00:00");
////			data4.addColumnToHeader("Invoice_DateInvoiced", "2014-08-25 17:00:00");
////			data4.addColumnToHeader("Invoice_DateAcct", 	"2014-08-26 18:00:00");
////			data4.addColumnToHeader("InOut_MovementDate", 	"2014-08-27 19:00:00");
////			data4.addColumnToHeader("InOut_DateAcct", 		"2014-08-28 20:00:00");
////			
////data4.addColumnToHeader("m_condition_shipping_delivery_id", "1000029");
////data4.addColumnToHeader("warehousedelivery", "1010247");
////
////			
////			data4.newDocumentLine();											// Especifico nueva linea
////			data4.addColumnToCurrentLine("Line", "1");							// Datos de línea 1
////			data4.addColumnToCurrentLine("QtyOrdered", "1");
////			data4.addColumnToCurrentLine("QtyEntered", "1");
//////			data4.addColumnToCurrentLine("PriceEntered", "199.99");
//////			data4.addColumnToCurrentLine("PriceActual", "199.99");
////			data4.addColumnToCurrentLine("C_Tax_ID", "1010136");
////			data4.addColumnToCurrentLine("M_Product_ID", "1024246");
////			ResultBean resultI = lyws.orderCreateCustomer(data4, 1645608, null, null, true, true, true, true, true); 
////			System.out.println(resultI);
////			System.out.println(" -------------- \n ");
////			
////			CustomServiceParameterBean test222 = new CustomServiceParameterBean("AdminTECDIA", "AdminTECDIA", 1010057, 0);
////			// Nombre de la clase a invocar
////			test222.setClassName("org.libertya.custom.ccmd.process.DeliveredOrderConfirmation");
////			// Especificacion de parametros
////			test222.addParameter("C_Order_ID", resultI.getMainResult().get("C_Order_ID"));
////			// Invocar al servicio
////			CustomServiceResultBean customResult22 = lyws.customService(test222); 
////			System.out.println(customResult22);
////			if (1==1)
////				return;
//
//			
//			// Prueba 20: Recuperación genérica de registros
//			FilteredColumnsParameterBean recParam = new FilteredColumnsParameterBean("AdminCMD", "AdminCMD", 1010016, 0);
////			recParam.addColumnToFilter("m_pricelist_version_id");
////			recParam.addColumnToFilter("m_product_id");
//			recParam.addColumnToFilter("pricelist");
//			MultipleRecordsResultBean recResult = lyws.recordQuery(recParam, "M_ProductPrice", "created > '2001-01-01'", false);
//			System.out.println(recResult);
//
//			// Prueba 5: Recuperar facturas
//			ParameterBean data51 = new ParameterBean("AdminCMD", "AdminCMD", 1010016, 0);
//			MultipleDocumentsResultBean invoices51 = lyws.documentQueryInvoices(data51, 1398714, null, null, true, false, false, false, null, null, null, new String[]{"CreatedBy.Description", "CreatedBy.Name", "C_DocType_ID.doctypekey", "C_DocType_ID.iscreatecounter", "C_DocType_ID.signo_issotrx", "C_DocType_ID.created"});
//			System.out.println(invoices51);
//			System.out.println(" -------------- \n ");
//			
//			 
//			// Prueba 9: Crear un nuevo pedido de compra
//			OrderParameterBean dBean2 = new OrderParameterBean("AdminCMD", "AdminCMD", 1010016, 1010095);
//			dBean2.addColumnToHeader("C_DocTypeTarget_ID", "1010524");
//			dBean2.addColumnToHeader("C_BPartner_Location_ID", "1398586");
//			dBean2.addColumnToHeader("M_PriceList_ID", "1010597");
//			dBean2.addColumnToHeader("C_Currency_ID", "118");
//			dBean2.addColumnToHeader("PaymentRule", "Tr");
//			dBean2.addColumnToHeader("DeliveryRule", "F");
//			dBean2.addColumnToHeader("C_PaymentTerm_ID", "1000073");
//			dBean2.addColumnToHeader("CreateCashLine", "N");
//			dBean2.addColumnToHeader("ManualGeneralDiscount", "0.00");
//			dBean2.addColumnToHeader("M_Warehouse_ID", "1010090");
//			dBean2.addColumnToHeader("Description", "Un pedido desde WS");
//			dBean2.newDocumentLine();
//			dBean2.addColumnToCurrentLine("Line", "1");
//			dBean2.addColumnToCurrentLine("QtyEntered", "15");
//			dBean2.addColumnToCurrentLine("PriceEntered", "25");
//			dBean2.addColumnToCurrentLine("C_Tax_ID", "1010085");
//			dBean2.addColumnToCurrentLine("M_Product_ID", "1020245");
//			dBean2.addColumnToCurrentLine("Description", "LINEA 1");
//			ResultBean orderResult = lyws.orderCreateVendor(dBean2, 1398714, null, null, true, false, false);
//			System.out.println(orderResult);
//			System.out.println(" -------------- \n ");
//			
//			if (!orderResult.isError()) {
//				int orderID = Integer.parseInt(orderResult.getMainResult().get("C_Order_ID"));
//				DocumentParameterBean data = new DocumentParameterBean("AdminCMD", "AdminCMD", 1010016, 0);
//				data.newDocumentLine();
//				data.addColumnToCurrentLine("QtyEntered", "1");
//				data.addColumnToCurrentLine("C_OrderLine_ID", "1131450");
//				ResultBean res = lyws.inOutCreateFromOrder(data, orderID, true);
//				System.out.println(res);
//			}
//			
//			// Pedido de cliente
//			dBean2.addColumnToHeader("C_DocTypeTarget_ID", "1010532");  // Pedido de cliente
//			dBean2.getDocumentLines().get(0).put("QtyEntered", "5");
//			orderResult = lyws.orderCreateCustomer(dBean2, 1398714, null, null, true, false, false);
//			System.out.println(orderResult);
//			System.out.println(" -------------- \n ");
//			// Remito de Cliente
//			if (!orderResult.isError()) {
//				int orderID = Integer.parseInt(orderResult.getMainResult().get("C_Order_ID"));
//				DocumentParameterBean data = new DocumentParameterBean("AdminCMD", "AdminCMD", 1010016, 0);
////				data.newDocumentLine();
////				data.addColumnToCurrentLine("QtyEntered", "2");
////				data.addColumnToCurrentLine("C_OrderLine_ID", "3");
//				ResultBean res = lyws.inOutCreateFromOrder(data, orderID, true);
//				System.out.println(res);
//			}
////			
////			
////			// Prueba 3: Recuperar una E.C.
////			ParameterBean data33 = new ParameterBean("AdminCMD", "AdminCMD", 1010016, 0);
////			BPartnerResultBean bean33 = lyws.bPartnerRetrieveByID(data33, 1398714);
////			System.out.println(bean33.toString());
////			System.out.println(" -------------- \n ");
////			
////			// Prueba 5: Recuperar facturas
////			ParameterBean data51 = new ParameterBean("AdminCMD", "AdminCMD", 1010016, 0);
////			MultipleDocumentsResultBean invoices51 = lyws.documentQueryInvoices(data51, 1398714, null, null, true, false, false, false, null, null, null, new String[]{"CreatedBy.Description", "CreatedBy.Name", "C_DocType_ID.doctypekey", "C_DocType_ID.iscreatecounter", "C_DocType_ID.signo_issotrx", "C_DocType_ID.created"});
////			System.out.println(invoices51);
////			System.out.println(" -------------- \n ");
////
////			if (1==1)
////				return;
////			
////			// Prueba 11: Nuevo remito de entrada.  Completarlo
//////			DocumentParameterBean rBean1 = new DocumentParameterBean("AdminCMD", "AdminCMD", 1010016, 0);
//////			rBean1.addColumnToHeader("C_DocTypeTarget_ID", "1010522");
//////			rBean1.addColumnToHeader("C_BPartner_Location_ID", "1200335");
//////			rBean1.addColumnToHeader("M_Warehouse_ID", "1010089");
//////			rBean1.addColumnToHeader("Description", "Una remito desde WS");
//////			rBean1.addColumnToHeader("C_Order_ID", "1104676");
//////			rBean1.newDocumentLine();
//////			rBean1.addColumnToCurrentLine("Line", "1");
//////			rBean1.addColumnToCurrentLine("C_OrderLine_ID", "1125948");
//////			rBean1.addColumnToCurrentLine("QtyEntered", "5");		
//////			rBean1.addColumnToCurrentLine("M_Product_ID", "1020200");
//////			rBean1.addColumnToCurrentLine("Description", "LINEA 1"); 
//////			ResultBean inOutResult1 = lyws.inOutCreateVendor(rBean1, 1200358, null, null, true);
//////			System.out.println(inOutResult1);
////			
////			CustomServiceParameterBean test222 = new CustomServiceParameterBean("AdminCMD", "AdminCMD", 1010016, 0);
////			// Nombre de la clase a invocar
////			test222.setClassName("org.libertya.custom.ccmd.process.DeliveredOrderConfirmation");
////			// Especificacion de parametros
////			test222.addParameter("C_Order_ID", "1109438");
////			// Invocar al servicio
////			CustomServiceResultBean customResult22 = lyws.customService(test222); 
////			System.out.println(customResult22);
////
////			
////			// Prueba 1: Eliminar una factura
////			InvoiceParameterBean data = new InvoiceParameterBean("AdminLibertya", "AdminLibertya", 1010016, 1010053);
////			System.out.println(lyws.invoiceDeleteByColumn(data, "DocumentNo", "100012"));
////			System.out.println(" -------------- \n ");
////			
////			// Prueba 2: Crear una entidad comercial
////			BPartnerParameterBean data2 = new BPartnerParameterBean("AdminLibertya", "AdminLibertya", 1010016, 1010053);
////			data2.addColumnToBPartner("value", "value2881");
////			data2.addColumnToBPartner("taxid", "20277467284");
////			data2.addColumnToBPartner("taxidtype", "80");
////			data2.addColumnToBPartner("name", "pruebaName3");
////			data2.addColumnToBPartner("name2", "un nombre");
////			data2.addColumnToBPartner("c_bp_group_id", "1010045");
////			data2.addColumnToBPartner("isonetime", "N");
////			data2.addColumnToBPartner("isprospect", "Y");
////			data2.addColumnToBPartner("isvendor", "N");
////			data2.addColumnToBPartner("iscustomer", "N");
////			data2.addColumnToBPartner("isemployee", "N");
////			data2.addColumnToBPartner("issalesrep", "N");
////			data2.addColumnToBPartner("c_paymentterm_id", "1010083");
////			data2.addColumnToBPartner("m_pricelist_id", "1010595");
////			data2.addColumnToLocation("address1", "una direccion ");
////			data2.addColumnToLocation("phone", "999999");
////			ResultBean resultado = lyws.bPartnerCreate(data2);
////			System.out.println(resultado.getMainResult().get("C_BPartner_ID"));
////			System.out.println(resultado.getMainResult().get("C_BPartner_Location_ID"));
////			System.out.println(lyws.bPartnerCreate(data2));
////			System.out.println(" -------------- \n ");
////			
////			// Prueba 3: Recuperar una E.C.
////			ParameterBean data3 = new ParameterBean("AdminLibertya", "AdminLibertya", 1010016, 1010053);
////			BPartnerResultBean bean = lyws.bPartnerRetrieveByValue(data3, "CF");
////			System.out.println(bean.toString());
////			System.out.println(" -------------- \n ");
////			
////			// Prueba 4: Crear una Factura
////			InvoiceParameterBean data4 = new InvoiceParameterBean("AdminLibertya", "AdminLibertya", 1010016, 1010053);
////			// Opcion 1: indicando DocTypeTarget
////			data4.addColumnToHeader("C_DocTypeTarget_ID", "1010507");
////			// Opcion 2: indicando PuntoDeVenta + TipoComprobante
////			data4.addColumnToHeader("TipoComprobante", "FC");
////			data4.addColumnToHeader("PuntoDeVenta", "1");
////			data4.addColumnToHeader("DateInvoiced", "2012-08-01 11:25:00");		 // OJO CON EL FORMATO: YYYY-MM-DD HH:mm:ss  
////			data4.addColumnToHeader("C_BPartner_Location_ID", "1012158");
////			data4.addColumnToHeader("M_PriceList_ID", "1010595");
////			data4.addColumnToHeader("C_Currency_ID", "118");
////			data4.addColumnToHeader("PaymentRule", "Tr");
////			data4.addColumnToHeader("C_PaymentTerm_ID", "1000073");
////			data4.addColumnToHeader("CreateCashLine", "N");
////			data4.addColumnToHeader("ManualGeneralDiscount", "0.00");
////			data4.addColumnToHeader("Description", "Una factura desde WS");
////			data4.newDocumentLine();											// Especifico nueva linea
////			data4.addColumnToCurrentLine("Line", "1");							// Datos de línea 1
////			data4.addColumnToCurrentLine("QtyEntered", "1");
////			data4.addColumnToCurrentLine("PriceEntered", "43.01");
////			data4.addColumnToCurrentLine("C_Tax_ID", "1010084");
////			data4.addColumnToCurrentLine("M_Product_ID", "1015400");
////			data4.addColumnToCurrentLine("Description", "LINEA 1");
////			data4.addColumnToCurrentLine("C_UOM_ID", "1000000");  // no se debe setear, es readonly
////			ResultBean resultI = lyws.invoiceCreateCustomer(data4, 1012145, null, null, false); 
////			System.out.println(resultI);
////			System.out.println(" -------------- \n ");
////			
////			if (!resultI.isError()) {
////				ParameterBean edit = new ParameterBean("AdminLibertya", "AdminLibertya", 1010016, 0);
////				edit.addColumnToMainTable("Description", "Una actualizacion");
////				System.out.println(lyws.invoiceUpdateByID(edit, 1021717));
////			}
////			
////			// Prueba 5: Recuperar facturas
////			ParameterBean data5 = new ParameterBean("AdminLibertya", "AdminLibertya", 1010016, 0);
////			MultipleDocumentsResultBean invoices = lyws.documentQueryInvoices(data5, 1012145, null, null, true, false, false, false, "2011-01-01", "2012-08-03", null);
////			System.out.println(invoices);
////			System.out.println(" -------------- \n ");
////			
////			// Prueba 6: Recuperar una factura a partir de su DocumentNo
////			ParameterBean data6 = new ParameterBean("AdminLibertya", "AdminLibertya", 1010016, 0);
////			InvoiceResultBean detalleFactura = lyws.documentRetrieveInvoiceByColumn(data6, "DocumentNo", "100088");
////			System.out.println(detalleFactura);
////			System.out.println(" -------------- \n ");
////
////			// Prueba 7: Crear un producto
////			ParameterBean pData = new ParameterBean("AdminLibertya", "AdminLibertya", 1010016, 0);
////			pData.addColumnToMainTable("value", "Articulo de Ejemplo N 33");
////			pData.addColumnToMainTable("name", "un nombre de Articulo");
////			pData.addColumnToMainTable("c_uom_id", "1000000");
////			pData.addColumnToMainTable("m_product_category_id", "1010146");
////			pData.addColumnToMainTable("c_taxcategory_id", "1010047");
////			ResultBean newProduct = lyws.productCreate(pData); 
////			System.out.println(newProduct);
////			System.out.println(" -------------- \n ");
////			
////			// Prueba 8: Recuperar el producto recien creado
////			if (!newProduct.isError()) {
////				ParameterBean pData2 = new ParameterBean("AdminLibertya", "AdminLibertya", 1010016, 0);
////				System.out.println(lyws.productRetrieveByID(pData2, Integer.parseInt(newProduct.getMainResult().get("M_Product_ID"))));
////			}
////			
////			// Prueba 9: Crear un nuevo pedido de venta. Completarlo.  Crear factura a partir del pedido.  Completarla.
////			//			 Modificado para soportar determinacion de tipos de documentos de manera dinamica a partir de ptoVta y tipoComprobante
////			OrderParameterBean dBean2 = new OrderParameterBean("AdminLibertya", "AdminLibertya", 1010016, 1010053);
////			dBean2.addColumnToHeader("C_DocTypeTarget_ID", "1010507");
////			dBean2.addColumnToHeader("C_BPartner_Location_ID", "1012158");
////			dBean2.addColumnToHeader("M_PriceList_ID", "1010595");
////			dBean2.addColumnToHeader("C_Currency_ID", "118");
////			dBean2.addColumnToHeader("PaymentRule", "Tr");
////			dBean2.addColumnToHeader("C_PaymentTerm_ID", "1000073");
////			dBean2.addColumnToHeader("CreateCashLine", "N");
////			dBean2.addColumnToHeader("ManualGeneralDiscount", "0.00");
////			dBean2.addColumnToHeader("M_Warehouse_ID", "1010048");
////			dBean2.addColumnToHeader("Description", "Una pedido desde WS");
////			dBean2.newDocumentLine();
////			dBean2.addColumnToCurrentLine("Line", "1");
////			dBean2.addColumnToCurrentLine("QtyEntered", "15");
////			dBean2.addColumnToCurrentLine("PriceEntered", "25");
////			dBean2.addColumnToCurrentLine("C_Tax_ID", "1010085");
////			dBean2.addColumnToCurrentLine("M_Product_ID", "1015400");
////			dBean2.addColumnToCurrentLine("Description", "LINEA 1");
////			// Opcion 1: Indicar el C_DocTypeTarget_ID directamente (comentado para utilizar la opción 2)
//////			dBean2.setInvoiceDocTypeTargetID(1010587);
////			// Opcion 2: Indicar el Punto de Venta + Tipo de Comprobante
////			dBean2.setInvoicePuntoDeVenta(1);
////			dBean2.setInvoiceTipoComprobante(OrderParameterBean.TIPO_COMPROBANTE_FACTURA);
////			ResultBean orderResult = lyws.orderCreateCustomer(dBean2, 1012145, null, null, true, true, true);
////			System.out.println(orderResult);
////			System.out.println(" -------------- \n ");
////			
////			// Alternativamente se podría no generar la factura en la invocación anterior, 
////			// y crearla posteriormente desde la siguiente invocación (actualmente comentada)
//////			if (!orderResult.isError()) {
//////				InvoiceParameterBean aBean = new InvoiceParameterBean("AdminLibertya", "AdminLibertya", 1010016, 1010053);
//////				aBean.addColumnToHeader("DateInvoiced", "2013-10-30 11:24:05");
//////				aBean.addColumnToHeader("c_doctypetarget_id", "1010507");
//////				System.out.println(lyws.invoiceCreateCustomerFromOrderByID(aBean, Integer.parseInt(orderResult.getMainResult().get("C_Order_ID")), false));
//////			}
////			
////			// Prueba 10: Anular el pedido recien creado
////			if (!orderResult.isError()) {
////				int orderID = Integer.parseInt(orderResult.getMainResult().get("C_Order_ID"));
////				System.out.println(lyws.orderVoidByID(dBean2, orderID));
////			}
////			System.out.println(" -------------- \n ");
////			
////			// Prueba 11: Nuevo remito de entrada.  Completarlo
////			DocumentParameterBean rBean = new DocumentParameterBean("AdminLibertya", "AdminLibertya", 1010016, 1010053);
////			rBean.addColumnToHeader("C_DocTypeTarget_ID", "1010522");
////			rBean.addColumnToHeader("C_BPartner_Location_ID", "1012158");
////			rBean.addColumnToHeader("M_Warehouse_ID", "1010048");
////			rBean.addColumnToHeader("Description", "Una remito desde WS");
////			rBean.newDocumentLine();
////			rBean.addColumnToCurrentLine("Line", "1");
////			rBean.addColumnToCurrentLine("QtyEntered", "300");		
////			rBean.addColumnToCurrentLine("M_Product_ID", "1015446");
////			rBean.addColumnToCurrentLine("Description", "LINEA 1"); 
////			ResultBean inOutResult = lyws.inOutCreateVendor(rBean, 1012145, null, null, true);
////			System.out.println(inOutResult);
////			
////			// Prueba 12: Intentar eliminar el remito creado (deberia dar error ya que el mismo se encuentra completado)
////			if (!inOutResult.isError()) {
////				int inOutID = Integer.parseInt(inOutResult.getMainResult().get("M_InOut_ID"));
////				System.out.println(lyws.inOutDeleteByID(rBean, inOutID));
////			}
////			System.out.println(" -------------- \n ");
////			
////			// Prueba 13: Recuperar informacion sobre suma de pedidos no facturados, cheques en cartera, facturas emitidas, y cobros
////			ParameterBean beanX = new ParameterBean("AdminLibertya", "AdminLibertya", 1010016, 1010053);
////			System.out.println(lyws.bPartnerBalanceSumOrdersNotInvoiced(beanX, -1, new int[]{1012145, 1012196}, null, 1010016, 0));
////			System.out.println(lyws.bPartnerBalanceSumChecks(beanX, -1, new int[]{1012145, 1012196}, null, 1010016, 0));
////			System.out.println(lyws.bPartnerBalanceSumInvoices(beanX, -1, new int[]{1012145, 1012196}, null, 1010016, 0));
////			System.out.println(lyws.bPartnerBalanceSumPayments(beanX, -1, new int[]{1012145, 1012196}, null, 1010016, 0));
////			
////			// Prueba 14: Crear un recibo de cliente cancelando una factura existente con varios medios de pago
////			AllocationParameterBean beanRC = new AllocationParameterBean("AdminLibertya", "AdminLibertya", 1010016, 1010099);
////			beanRC.addColumnToHeader("Description", "Un RC desde WS");
////			// Factura a cobrar
////			beanRC.newInvoice();
////			beanRC.addColumnToCurrentInvoice("C_Invoice_ID", "1021753");
////			beanRC.addColumnToCurrentInvoice("Amount", "43.0100");
////			
////			// Medio de pago: Nota de credito
////			beanRC.newPayment();
////			beanRC.addColumnToCurrentPayment("C_POSPaymentMedium_ID", "1010038");
////			beanRC.addColumnToCurrentPayment("Amount", "70");
////			beanRC.addColumnToCurrentPayment("C_Invoice_ID", "1021695");
////			// Medio de pago: Transferencia bancaria
////			beanRC.newPayment();
////			beanRC.addColumnToCurrentPayment("C_POSPaymentMedium_ID", "1010052");
////			beanRC.addColumnToCurrentPayment("Amount", "43.01");
////			beanRC.addColumnToCurrentPayment("C_BankAccount_ID", "1010133");
////			beanRC.addColumnToCurrentPayment("C_Bank_ID", "1010099");
////			beanRC.addColumnToCurrentPayment("A_Bank", "Un Banco");
////			beanRC.addColumnToCurrentPayment("TransferNo", "1234");
////			beanRC.addColumnToCurrentPayment("TransferDate", "2012-09-03 10:20:00");
////			// Medio de pago: Tarjeta de credito
////			beanRC.newPayment();
////			beanRC.addColumnToCurrentPayment("C_POSPaymentMedium_ID", "1010036");
////			beanRC.addColumnToCurrentPayment("Amount", "20");
////			beanRC.addColumnToCurrentPayment("M_EntidadFinancieraPlan_ID", "1010033");
////			beanRC.addColumnToCurrentPayment("A_Bank", "Comafi");
////			beanRC.addColumnToCurrentPayment("CreditCardNumber", "102929281810");
////			beanRC.addColumnToCurrentPayment("CouponNumber", "12341234");
////			// Medio de pago: Cheque
////			beanRC.newPayment();
////			beanRC.addColumnToCurrentPayment("C_POSPaymentMedium_ID", "1010037");
////			beanRC.addColumnToCurrentPayment("Amount", "40");
////			beanRC.addColumnToCurrentPayment("C_BankAccount_ID", "1010070");
////			beanRC.addColumnToCurrentPayment("CheckNo", "12345");
////			beanRC.addColumnToCurrentPayment("DateTrx", "2012-09-03 10:26:15");
////			beanRC.addColumnToCurrentPayment("DueDate", "2012-09-04 10:26:15");
////			// Medio de pago: Efectivo
////			beanRC.newPayment();
////			beanRC.addColumnToCurrentPayment("C_POSPaymentMedium_ID", "1010033");
////			beanRC.addColumnToCurrentPayment("Amount", "40");
////			beanRC.addColumnToCurrentPayment("C_Cash_ID", "1010062");
////			// Medio de pago: Cobro adelantado en efectivo
////			beanRC.newPayment();
////			beanRC.addColumnToCurrentPayment("C_POSPaymentMedium_ID", "1010035");
////			beanRC.addColumnToCurrentPayment("Amount", "70");
////			beanRC.addColumnToCurrentPayment("C_CashLine_ID", "1010100");
////			// Medio de pago: Cobro adelantado mediante transf. bancaria
////			beanRC.newPayment();
////			beanRC.addColumnToCurrentPayment("C_POSPaymentMedium_ID", "1010035");
////			beanRC.addColumnToCurrentPayment("Amount", "30");
////			beanRC.addColumnToCurrentPayment("C_Payment_ID", "1011960");
////			// Medio de pago: Retencion sufrida
////			beanRC.newPayment();
////			beanRC.addColumnToCurrentPayment("C_POSPaymentMedium_ID", "1010039");
////			beanRC.addColumnToCurrentPayment("Amount", "50");
////			beanRC.addColumnToCurrentPayment("C_RetencionSchema_ID", "1010054");
////			beanRC.addColumnToCurrentPayment("Retenc_DocumentNo", "5311181");
////			beanRC.addColumnToCurrentPayment("Retenc_Date", "2012-09-04 10:44:18");
////			// Invocar a la crecion de recibo para la entidad comercial cuyo value sea MC
////			ResultBean result = lyws.allocationCreateReceipt(beanRC, 1012221, null, null);
////			System.out.println(result);
////			System.out.println(" -------------- \n ");
////			
////			// Prueba 15: Intentar anular el recibo de cliente recien creado, incluyendo pagos y retenciones
////			if (!result.isError()) {
////				int allocationHdrID = Integer.parseInt(result.getMainResult().get("C_AllocationHdr_ID"));
////				AllocationParameterBean voidBean = new AllocationParameterBean("AdminLibertya", "AdminLibertya", 1010016, 1010053);
////				System.out.println(lyws.allocationVoidByID(voidBean, allocationHdrID, AllocationParameterBean.ALLOCATIONACTION_VoidPaymentsRetentions));
////				System.out.println(" -------------- \n ");
////			}
////			
////			// Prueba 16: Recuperar una factura contemplando nuevos datos de retorno
////			InvoiceParameterBean pData2 = new InvoiceParameterBean("AdminLibertya", "AdminLibertya", 1010016, 1010053);
////			System.out.println(lyws.documentRetrieveInvoiceByID(pData2, 1021701));
////			
////			// Prueba 17: Creacion de un usuario
////			ParameterBean data1 = new ParameterBean("AdminLibertya", "AdminLibertya", 1010016, 1010053);
////			data1.addColumnToMainTable("name", "fulanito55");
////			data1.addColumnToMainTable("password", "198798217");
////			data1.addColumnToMainTable("phone", "66666666");
////			ResultBean resUser = lyws.userCreate(data1); 
////			System.out.println(resUser);
////			
////			// Prueba 18: Recuperacion, Modificacion y Eliminacion logica de usuario
////			if (!resUser.isError()) {
////				// Recuperacion de usuario
////				int userID = Integer.parseInt(resUser.getMainResult().get("AD_User_ID"));
////				System.out.println(lyws.userRetrieveByID(data1, userID));
////				
////				// Modificacion de usuario
////				ParameterBean updateUser = new ParameterBean("AdminLibertya", "AdminLibertya", 1010016, 1010053);
////				updateUser.addColumnToMainTable("password", "153284");
////				System.out.println(lyws.userUpdateByID(updateUser, userID));
////				
////				// Eliminacion de usuario
////				System.out.println(lyws.userDeleteByID(updateUser, userID));
////			}
////			
////			// Prueba 19: Consulta de stock varias. NOTAR QUE Org = 0, en caso de ser > 0, entonces filtrará también por este criterio
////			ParameterBean storageBean = new ParameterBean("AdminLibertya", "AdminLibertya", 1010016, 0);
////			// Stock de todos los articulos en todas los almacenes
////			System.out.println(lyws.storageQuery(storageBean, null, 0, null, 0, null, null));
////			// Stock del artículo Standard, en los almacenes 1010048 y 1010087.
////			System.out.println(lyws.storageQuery(storageBean, new int[]{1010048, 1010087}, 0, "Standard", 0, null, null));
////			// Stock del artículo 1915452, en la ubicación 1010317
////			System.out.println(lyws.storageQuery(storageBean, null, 1010317, null, 1015452, null, null));
////			
////			// Prueba 20: Recuperación genérica de registros
////			FilteredColumnsParameterBean recParam = new FilteredColumnsParameterBean("AdminLibertya", "AdminLibertya", 1010016, 0);
////			recParam.addColumnToFilter("m_pricelist_version_id");
////			recParam.addColumnToFilter("m_product_id");
////			recParam.addColumnToFilter("pricelist");
////			MultipleRecordsResultBean recResult = lyws.recordQuery(recParam, "M_ProductPrice", "created > '2001-01-01'", false);
////			System.out.println(recResult);
////			
////			// Prueba 21: Actualización de un pedido.  Si el mismo está completado, reabrirlo.  Luego completar dicho pedido.
////			ParameterBean test21 = new ParameterBean("AdminLibertya", "AdminLibertya", 1010016, 0);
////			test21.addColumnToMainTable("Description", "Una modificacion");
////			System.out.println(lyws.orderUpdateByID(test21, 1014059, true));
////			
////			// Prueba 22: Servicio genérico.  Invocar a un servicio en clase Example, que recibe 4 parámetros:
////			//				param1: un String; param2: un String; param3: un entero; param 4: una lista de valores
////			//			  En todos los casos, los valores son enviados como Strings
////			CustomServiceParameterBean test22 = new CustomServiceParameterBean("AdminLibertya", "AdminLibertya", 1010016, 0);
////			// Nombre de la clase a invocar
////			test22.setClassName("org.libertya.example.customService.Example");
////			// Especificacion de parametros
////			test22.addParameter("param1", "foo");
////			test22.addParameter("param2", "bar");
////			test22.addParameter("param3", "43");
////			test22.addParameter("param4", "x", "y", "z");
////			// Invocar al servicio
////			CustomServiceResultBean customResult = lyws.customService(test22); 
////			System.out.println(customResult);
////			
////			// Prueba 23: Gestión de inventario
////			DocumentParameterBean param23 = new DocumentParameterBean("AdminLibertya", "AdminLibertya", 1010016, 1010053);
////			param23.addColumnToHeader("c_doctype_id", "1010529");
////			param23.addColumnToHeader("m_warehouse_id", "1010048");
////			param23.addColumnToHeader("inventoryKind", "PI");
////			param23.newDocumentLine();
////			param23.addColumnToCurrentLine("line", "10");
////			param23.addColumnToCurrentLine("m_locator_id", "1010278");
////			param23.addColumnToCurrentLine("m_product_id", "1015506");
////			param23.addColumnToCurrentLine("qtyCount", "33");
////			param23.addColumnToCurrentLine("inventorytype", "D");
////			System.out.println(lyws.inventoryCreate(param23, true));
////			
////			// Prueba 24: Direccion de EC
////			ParameterBean aLocationParam = new ParameterBean("AdminLibertya", "AdminLibertya", 1010016, 1010053);
////			aLocationParam.addColumnToMainTable("c_bpartner_id", "1012142");
////			aLocationParam.addColumnToMainTable("address1", "la direccion2");
////			aLocationParam.addColumnToMainTable("phone", "el telefono");
////			System.out.println(lyws.bPartnerLocationCreate(aLocationParam));
////			
////			// Prueba 25: Precio de artículo
////			ParameterBean priceParam = new ParameterBean("AdminLibertya", "AdminLibertya", 1010016, 1010053);
////			priceParam.addColumnToMainTable("m_product_id", "1015400");
////			priceParam.addColumnToMainTable("m_pricelist_version_id", "1010527");
////			priceParam.addColumnToMainTable("ad_org_id", "0");
////			priceParam.addColumnToMainTable("pricestd", "999.80");
////			System.out.println(lyws.productPriceCreateUpdate(priceParam));
////			
////			// Prueba 26: Orden de producción
////			DocumentParameterBean productionParam = new DocumentParameterBean("AdminLibertya", "AdminLibertya", 1010016, 1010053);
////			productionParam.addColumnToHeader("C_BPartner_ID", "1012142");
////			productionParam.addColumnToHeader("C_DocTypeTarget_ID", "1010532");
////			productionParam.addColumnToHeader("DateOrdered", "2013-12-19 11:25:00");
////			productionParam.addColumnToHeader("PriorityRule", "1");
////			productionParam.addColumnToHeader("M_Warehouse_ID", "1010048");
////			productionParam.newDocumentLine();
////			productionParam.addColumnToCurrentLine("m_product_id", "1015400");
////			productionParam.addColumnToCurrentLine("qtyordered", "97");
////			productionParam.addColumnToCurrentLine("qtyentered", "97");
////			productionParam.addColumnToCurrentLine("M_Locator_ID", "1010278");
////			System.out.println(lyws.productionOrderCreate(productionParam, true));
////			
////			// Prueba 27: Boleta de depósito
////			DocumentParameterBean depositParam = new DocumentParameterBean("AdminLibertya", "AdminLibertya", 1010016, 1010053);
////			depositParam.addColumnToHeader("C_BPartner_ID", "1012142");
////			depositParam.addColumnToHeader("C_BankAccount_ID", "1010078");
////			depositParam.addColumnToHeader("FechaDeposito", "2013-12-21 11:25:00");
////			depositParam.addColumnToHeader("c_currency_id", "118");
////			depositParam.newDocumentLine();
////			depositParam.addColumnToCurrentLine("C_Payment_ID", "1011951");
////			depositParam.addColumnToCurrentLine("c_currency_id", "118");
////			depositParam.addColumnToCurrentLine("payment_amt", "700");
////			System.out.println(lyws.depositSlipCreate(depositParam, true));
////			
////			// Prueba 28: Lista de materiales
////			ParameterBean bomParam = new ParameterBean("AdminLibertya", "AdminLibertya", 1010016, 1010053);
////			bomParam.addColumnToMainTable("m_product_id", "1015400");
////			bomParam.addColumnToMainTable("m_productbom_id", "1015401");
////			bomParam.addColumnToMainTable("bomqty", "7");
////			bomParam.addColumnToMainTable("line", "7");
////			System.out.println(lyws.billOfMaterialCreate(bomParam));			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
