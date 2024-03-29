package org.libertya.ws;

import org.libertya.ws.bean.parameter.AllocationParameterBean;
import org.libertya.ws.bean.parameter.BPartnerParameterBean;
import org.libertya.ws.bean.parameter.CustomServiceParameterBean;
import org.libertya.ws.bean.parameter.DocumentParameterBean;
import org.libertya.ws.bean.parameter.FilteredColumnsParameterBean;
import org.libertya.ws.bean.parameter.InvoiceParameterBean;
import org.libertya.ws.bean.parameter.OrderParameterBean;
import org.libertya.ws.bean.parameter.ParameterBean;
import org.libertya.ws.bean.parameter.ReplicationParameterBean;
import org.libertya.ws.bean.result.BPartnerResultBean;
import org.libertya.ws.bean.result.CustomServiceResultBean;
import org.libertya.ws.bean.result.DocumentResultBean;
import org.libertya.ws.bean.result.InvoiceResultBean;
import org.libertya.ws.bean.result.MultipleDocumentsResultBean;
import org.libertya.ws.bean.result.MultipleRecordsResultBean;
import org.libertya.ws.bean.result.ReplicationResultBean;
import org.libertya.ws.bean.result.ResultBean;
import org.libertya.ws.bean.result.StorageResultBean;


public interface LibertyaWS {

	
	/* ================================================================== */
	/* ==================== Entidades Comerciales ======================= */
	/* ================================================================== */

	/**
	 * Alta de una entidad comercial
	 * @param data parametros correspondientes a la entidad comercial y su direccion
	 * @return ResultBean con OK C_BPartner_ID y C_BPartner_Location_ID, o ERROR en caso de error.
	 */
	public ResultBean bPartnerCreate(BPartnerParameterBean data);
	
	/**
	 * Recuperación de una entidad comercial a partir de su c_bpartner_id
	 * @param data parametros correspondientes
	 * @param bPartnerID id de la entidad comercial a recuperar
	 * @return BPartnerResultBean con los datos correspondientes
	 */
	public BPartnerResultBean bPartnerRetrieveByID(ParameterBean data, int bPartnerID);
	
	/**
	 * Recuperación de una entidad comercial a partir de su clave de busqueda
	 * @param data parametros correspondientes
	 * @param value clave de busqueda de la entidad comercial a recuperar
	 * @return BPartnerResultBean con los datos correspondientes
	 */
	public BPartnerResultBean bPartnerRetrieveByValue(ParameterBean data, String value);
	
	/**
	 * Recuperación de una entidad comercial a partir de su taxID
	 * @param data parametros correspondientes
	 * @param taxID cuit de la entidad comercial a recuperar
	 * @return BPartnerResultBean con los datos correspondientes
	 */	
	public BPartnerResultBean bPartnerRetrieveByTaxID(ParameterBean data, String taxID);
	
	/**
	 * Actualización de una entidad comercial
	 * @param data parametros correspondientes a actualizar
	 * @param bPartnerID identificador de la entidad comercial a modificar
	 * @param bPartnerLocationID identificador de la direccion de la entidad comercial (0 si no se desea modificar)
	 * @return ResultBean con OK o ERROR
	 */
	public ResultBean bPartnerUpdate(BPartnerParameterBean data, int bPartnerID, int bPartnerLocationID);
	
	/**
	 * Baja logica de una entidad comercial
	 * @param data parametros correspondientes
	 * @param bPartnerID identificador de la entidad comercial a eliminar
	 * @return ResultBean con OK o ERROR
	 */
	public ResultBean bPartnerDelete(ParameterBean data, int bPartnerID);
	
	
	/* ===================================================== */
	/* ==================== Facturas ======================= */
	/* ===================================================== */

	/**
	 * Creación de factura de cliente
	 * Debe indicarse, además del conjunto de parametros, una de las tres opciones para indicar la entidad comercial
	 * @param data parametros correspondientes
	 * @param bPartnerID identificador de la entidad comercial (o -1 en caso de no indicar)
	 * @param bPartnerValue clave de busqueda de la entidad comercial (o null en caso de no indicar)
	 * @param taxID CUIT de la entidad comercial (o null en caso de no indicar)
	 * @param completeDocument para especificar si se debe completar la factura o no
	 * @return ResultBean con OK y datos: C_Invoice_ID, Invoice_DocumentNo creado, o ERROR en caso contrario.
	 */
	public ResultBean invoiceCreateCustomer(InvoiceParameterBean data, int bPartnerID, String bPartnerValue, String taxID, boolean completeDocument);

	/**
	 * Creación de factura de cliente a partir de pedido
	 * @param data parametros correspondientes
	 * @param orderID el ID del pedido a utilizar como base
	 * @param completeDocument para especificar si se debe completar la factura o no
	 * @return ResultBean con OK y datos: C_Invoice_ID, Invoice_DocumentNo creado, o ERROR en caso contrario.
	 */
	public ResultBean invoiceCreateCustomerFromOrderByID(InvoiceParameterBean data, int orderID, boolean completeDocument);
	
	/**
	 * Creación de factura de cliente a partir de pedido
	 * @param data parametros correspondientes
	 * @param searchColumn y searchCriteria permite buscar un pedido a partir de una columna dada y un valor dado para dicha columna
	 * 			El criterio especificado filtra además por la organización especificada en los parametros data
	 * @param completeDocument para especificar si se debe completar la factura o no
	 * @return ResultBean con OK y datos: C_Invoice_ID, Invoice_DocumentNo creado, o ERROR en caso contrario.
	 */
	public ResultBean invoiceCreateCustomerFromOrderByColumn(InvoiceParameterBean data, String searchColumn, String searchCriteria, boolean completeDocument);
	
	/**
	 * Creación de factura de proveedor
	 * Debe indicarse, además del conjunto de parametros, una de las tres opciones para indicar la entidad comercial
	 * @param data parametros correspondientes
	 * @param bPartnerID identificador de la entidad comercial (o -1 en caso de no indicar)
	 * @param bPartnerValue clave de busqueda de la entidad comercial (o null en caso de no indicar)
	 * @param taxID CUIT de la entidad comercial (o null en caso de no indicar)
	 * @param completeDocument para especificar si se debe completar la factura o no
	 * @return ResultBean con OK y datos: C_Invoice_ID, Invoice_DocumentNo creado, o ERROR en caso contrario.
	 */
	public ResultBean invoiceCreateVendor(InvoiceParameterBean data, int bPartnerID, String bPartnerValue, String taxID, boolean completeDocument);
	
	/**
	 * Creación de factura de proveedor a partir de pedido
	 * @param data parametros correspondientes
	 * @param orderID el ID del pedido a utilizar como base
	 * @param completeDocument para especificar si se debe completar la factura o no
	 * @return ResultBean con OK y datos: C_Invoice_ID, Invoice_DocumentNo creado, o ERROR en caso contrario.
	 */
	public ResultBean invoiceCreateVendorFromOrderByID(InvoiceParameterBean data, int orderID, boolean completeDocument);
	
	/**
	 * Creación de factura de proveedor a partir de pedido
	 * @param data parametros correspondientes
	 * @param searchColumn y searchCriteria permite buscar un pedido a partir de una columna dada y un valor dado para dicha columna
	 * 			El criterio especificado filtra además por la organización especificada en los parametros data
	 * @param completeDocument para especificar si se debe completar la factura o no
	 * @return ResultBean con OK y datos: C_Invoice_ID, Invoice_DocumentNo creado, o ERROR en caso contrario.
	 */
	public ResultBean invoiceCreateVendorFromOrderByColumn(InvoiceParameterBean data, String searchColumn, String searchCriteria, boolean completeDocument);
	
	/**
	 * Elimina una factura en borrador.  La misma debe ser indicada por su ID
	 * @param data parametros correspondientes
	 * @param invoiceID identificador de la factura (C_Invoice_ID)
	 * @return ResultBean con OK o ERROR
	 */
	public ResultBean invoiceDeleteByID(ParameterBean data, int invoiceID);
	
	/**
	 * Elimina una factura en borrador.
	 * @param data parametros correspondientes
	 * @param columnName y columnCriteria columna y valor a filtrar para recuperar la factura en cuestion
	 * @return ResultBean con OK o ERROR
	 */
	public ResultBean invoiceDeleteByColumn(ParameterBean data, String columnName, String columnCriteria);
	
	/**
	 * Completa una factura en borrador.  La misma debe ser indicada por su ID
	 * @param data parametros correspondientes
	 * @param invoiceID identificador de la factura (C_Invoice_ID)
	 * @return ResultBean con OK o ERROR. 
	 */
	public ResultBean invoiceCompleteByID(ParameterBean data, int invoiceID);
	
	/**
	 * Completa una factura en borrador.  La debe ser indicada por un par: Nombre de Columna / Criterio de Columna
	 * @param data parametros correspondientes
	 * @param columnName y columnCriteria columna y valor a filtrar para recuperar la factura en cuestion
	 * @return ResultBean con OK o ERROR. 
	 */
	public ResultBean invoiceCompleteByColumn(ParameterBean data, String columnName, String columnCriteria);
	
	/**
	 * Anula una factura.  La misma debe ser indicada por su ID
	 * @param data parametros correspondientes
	 * @param invoiceID identificador de la factura (C_Invoice_ID)
	 * @return ResultBean con OK o ERROR 
	 * 			En el resultado se incluye la clave CreditNote_DocumentNo, en donde se cuarda el número de documento de la nota de crédito eventualmente creada
	 */
	public ResultBean invoiceVoidByID(ParameterBean data, int invoiceID);
	
	/**
	 * Anula una factura o más facturas.  Las mismas deben ser indicadas por un par: Nombre de Columna / Criterio de Columna
	 * En caso de recuperar más de una factura se anularán todas.  En caso de error en alguna no se anulará ninguna.
	 * @param data parametros correspondientes
	 * @param columnName y columnCriteria columna y valor a filtrar para recuperar la factura en cuestion
	 * @return ResultBean con OK o ERROR 
	 * 	 		En el resultado se incluye la clave CreditNote_DocumentNo, en donde se cuarda el número de documento de la nota de crédito eventualmente creada
	 * 			Si se está anulando más de una factura, se crearán las claves Credit_DocumentNo_For_InvoiceID_XXX  (donde XXX es el número de factura)
	 */
	public ResultBean invoiceVoidByColumn(ParameterBean data, String columnName, String columnCriteria);

	
	/**
	 * Actualiza campos de la cabecera de la factura únicamente.
	 * @param data el conjunto de datos a actualizar correspondientes a la cabecera de la factura
	 * @param invoiceID el ID de la factura a actualizar 
	 * @return ResultBean con OK, ERROR, etc. 
	 */
	public ResultBean invoiceUpdateByID(ParameterBean data, int invoiceID);
	
	
	/* ===================================================== */
	/* ==================== Artículos ====================== */
	/* ===================================================== */

	/**
	 * Alta de un artículo
	 * @param data parametros correspondientes al articulo
	 * @return ResultBean con OK y el M_Product_ID generado o ERROR en caso contrario
	 */
	public ResultBean productCreate(ParameterBean data);
	
	/**
	 * Alta de un artículo
	 * @param data parametros correspondientes al articulo
	 * @param createDefaultProductPrice si se quiere generan los productPrices iniciales para el articulo creado
	 * @return ResultBean con OK y el M_Product_ID generado o ERROR en caso contrario
	 */
	public ResultBean productCreate(ParameterBean data, boolean createDefaultProductPrice);
	
	/**
	 * Recuperacion de un articulo a partir de su ID
	 * @param data parametros correspondientes
	 * @param productID id del artículo (M_Product_ID)
	 * @return ResultBean con los datos correspondientes
	 */
	public ResultBean productRetrieveByID(ParameterBean data, int productID);
	
	/**
	 * Recuperacion de un articulo a partir de su clave de busqueda
	 * @param data parametros correspondientes
	 * @param value clave de busqueda del articulo
	 * @return ResultBean con los datos correspondientes
	 */
	public ResultBean productRetrieveByValue(ParameterBean data, String value);
	
	/**
	 * Actualización de un articulo a partir de su ID
	 * @param data parametros correspondientes
	 * @param productID identificador del articulo a modificar
	 * @return ResultBean con OK o ERROR
	 */
	public ResultBean productUpdateByID(ParameterBean data, int productID);
	
	/**
	 * Actualización de un articulo a partir de su clave de busqueda
	 * @param data parametros correspondientes
	 * @param value identificador del articulo
	 * @return ResultBean con OK o ERROR
	 */
	public ResultBean productUpdateByValue(ParameterBean data, String value);
	
	/**
	 * Eliminación logica de un articulo
	 * @param data parametros correspondientes
	 * @param productID identificador del articulo a eliminar
	 * @return ResultBean con OK o ERROR
	 */
	public ResultBean productDelete(ParameterBean data, int productID);
	
	/* ===================================================== */
	/* ========== Lineas, Familias, Subfamilias ============ */
	/* ===================================================== */

	//Lineas
	/**
	 * Alta de product lines
	 * @param data parametros correspondientes a product lines
	 * @return ResultBean con OK y el M_Product_Lines_ID generado o ERROR en caso contrario
	 */
	public ResultBean productLinesCreate(ParameterBean data);
	
	/**
	 * Recuperacion de product lines a partir de su ID
	 * @param data parametros correspondientes
	 * @param productLinesID id de product lines (M_Product_Lines_ID)
	 * @return ResultBean con los datos correspondientes
	 */
	public ResultBean productLinesRetrieveByID(ParameterBean data, int productLinesID);
	
	/**
	 * Actualización de product lines a partir de su ID
	 * @param data parametros correspondientes
	 * @param productLinesID identificador de product lines a modificar
	 * @return ResultBean con OK o ERROR
	 */
	public ResultBean productLinesUpdateByID(ParameterBean data, int productLinesID);
	
	/**
	 * Eliminación de product lines
	 * @param data parametros correspondientes
	 * @param productLinesID identificador de product lines a eliminar
	 * @return ResultBean con OK o ERROR
	 */
	public ResultBean productLinesDelete(ParameterBean data, int productLinesID);
	
	//Gamas
	
	/**
	 * Alta de product gamas
	 * @param data parametros correspondientes a product gamas
	 * @return ResultBean con OK y el M_Product_Gamas_ID generado o ERROR en caso contrario
	 */
	public ResultBean productGamasCreate(ParameterBean data);
	
	/**
	 * Recuperacion de product gamas a partir de su ID
	 * @param data parametros correspondientes
	 * @param productGamasID id de product gamas (M_Product_Gamas_ID)
	 * @return ResultBean con los datos correspondientes
	 */
	public ResultBean productGamasRetrieveByID(ParameterBean data, int productGamasID);
	
	/**
	 * Actualización de product gamas a partir de su ID
	 * @param data parametros correspondientes
	 * @param productGamasID identificador de product gamas a modificar
	 * @return ResultBean con OK o ERROR
	 */
	public ResultBean productGamasUpdateByID(ParameterBean data, int productGamasID);
	
	/**
	 * Eliminación de product gamas
	 * @param data parametros correspondientes
	 * @param productGamasID identificador de product gamas a eliminar
	 * @return ResultBean con OK o ERROR
	 */
	public ResultBean productGamasDelete(ParameterBean data, int productGamasID);
	
	//Category
	
	/**
	 * Alta de product category
	 * @param data parametros correspondientes a product category
	 * @return ResultBean con OK y el M_Product_Category_ID generado o ERROR en caso contrario
	 */
	public ResultBean productCategoryCreate(ParameterBean data);
	
	/**
	 * Recuperacion de product category a partir de su ID
	 * @param data parametros correspondientes
	 * @param productCategoryID id de product category (M_Product_Category_ID)
	 * @return ResultBean con los datos correspondientes
	 */
	public ResultBean productCategoryRetrieveByID(ParameterBean data, int productCategoryID);
	
	/**
	 * Actualización de product category a partir de su ID
	 * @param data parametros correspondientes
	 * @param productCategoryID identificador de product category a modificar
	 * @return ResultBean con OK o ERROR
	 */
	public ResultBean productCategoryUpdateByID(ParameterBean data, int productCategoryID);
	
	/**
	 * Eliminación de product category
	 * @param data parametros correspondientes
	 * @param productCategoryID identificador de product category a eliminar
	 * @return ResultBean con OK o ERROR
	 */
	public ResultBean productCategoryDelete(ParameterBean data, int productCategoryID);
	
	/* ===================================================== */
	/* ====================== Diarios === ================== */
	/* ===================================================== */
	
	
	//Alta de Diario
	
	/**
	 * Alta de Diario
	 * @param data parametros correspondientes
	 * @param completeJournal
	 * @return ResultBean con OK o ERROR
	 */
	public ResultBean journalCreate(DocumentParameterBean data, boolean completeJournal);

	
	//Baja de Diario en Borrador
	
	/**
	 * Baja de Diario en borrador por id
	 * @param data parametros correspondientes
	 * @param journalID identificador -> GL_Journal_ID
	 * @return ResultBean con OK o ERROR
	 */
	public ResultBean journalDeleteByID(ParameterBean data, int journalID);
	
	/**
	 * Baja de Diario en borrador por columna
	 * @param data parametros correspondientes
	 * @param columnName columna correspondiente
	 * @param columnCriteria criterio de eliminacion
	 * @return ResultBean con OK o ERROR
	 */
	public ResultBean journalDeleteByColumn(ParameterBean data, java.lang.String columnName, java.lang.String columnCriteria);


	//Completado de Diario
    
	/**
	 * Completado de diario en borrador por id
	 * @param journalID identificador
	 * @return ResultBean con OK o ERROR
	 */
	public ResultBean journalCompleteByID(ParameterBean data, int journalID);
	
	/**
	 * Completado de diario en borrador por columna
	 * @param columnName columna correspondiente
	 * @param columnCriteria criterio de eliminacion
	 * @return ResultBean con OK o ERROR
	 */
	public ResultBean journalCompleteByColumn(ParameterBean data, java.lang.String columnName, java.lang.String columnCriteria);

	//Anulación de Diario
	
	/**
	 * Anulacion de Diario por id
	 * @param journalID identificador (GL_Journal_ID)
	 * @return ResultBean con IsError, ErrorMsg
	 */
	public ResultBean journalVoidByID(ParameterBean data,int journalID);
	
	/**
	 * Anulacion de diario por columna
	 * @param columnName columna correspondiente
	 * @param columnCriteria criterio de eliminacion
	 * @return ResultBean con IsError, ErrorMsg
	 */
	public ResultBean journalVoidByColumn(ParameterBean data, java.lang.String columnName, java.lang.String columnCriteria);

	//Consulta de Diario

	/**
	 * Consulta de Diario por id
	 * @param journalID identificador (GL_Journal_ID)
	 * @return ResultBean con datos
	 */
	public DocumentResultBean documentRetrieveJournalByID(ParameterBean data, int journalID);
	
	/**
	 * Consulta de Diario por columna
	 * @param data parametros correspondientes
	 * @param columnName columna correspondiente
	 * @param columnCriteria criterio de eliminacion
	 * @return ResultBean con datos
	 */
	public DocumentResultBean documentRetrieveJournalByColumn(ParameterBean data, java.lang.String column, java.lang.String value);
	
	
	/* ===================================================== */
	/* ================= Lote de Asientos  ================= */
	/* ===================================================== */
	
	//Alta de Lote de Asientos
	
	/**
	 * Alta de lote de asientos
	 * @param data parametros correspondientes
	 * @param completeJournalBatch
	 * @return ResultBean con OK o ERROR
	 */
    //public ResultBean journalBatchCreate(OrderParameterBean data, boolean completeJournalBatch);
    
    //Baja de Lote de Asientos en Borrador
    
    /**
	 * Baja de lote de asientos en borrador por id
	 * @param data parametros correspondientes
	 * @param orderID identificador
	 * @return ResultBean con OK o ERROR
	 */
    //public ResultBean journalBatchDeleteByID(ParameterBean data, int orderID);
    
    /**
	 * Baja de lote de asientos en borrador por columna
	 * @param data parametros correspondientes
	 * @param columnName columna correspondiente
	 * @param columnCriteria criterio de eliminacion
	 * @return ResultBean con OK o ERROR
	 */
    //public ResultBean journalBatchDeleteByColumn(ParameterBean data, String columnName, String columnCriteria);
    
    //Completado de Lote de Asientos
    
    /**
	 * Completado de lote de asientos en borrador por id
	 * @param journalBatchID identificador
	 * @return ResultBean con OK o ERROR
	 */
    //public ResultBean journalBatchCompleteByID(ParameterBean data, int journalBatchID);
    
    /**
	 * Completado de lote de asientos en borrador por columna
	 * @param columnName columna correspondiente
	 * @param columnCriteria criterio de eliminacion
	 * @return ResultBean con OK o ERROR
	 */
    //public ResultBean journalBatchCompleteByColumn(ParameterBean data, String columnName, String columnCriteria);
    
    //Anulación de Lote de Asientos
    
    /**
	 * Anulacion de lote de asientos por id
	 * @param journalBatchID identificador
	 * @return ResultBean con OK o ERROR
	 */
    //public ResultBean journalBatchVoidByID(ParameterBean data, int journalBatchID);
    
    /**
	 * Anulacion de lote de asientos por columna
	 * @param columnName columna correspondiente
	 * @param columnCriteria criterio de eliminacion
	 * @return ResultBean con OK o ERROR
	 */
    //public ResultBean journalBatchVoidByColumn(ParameterBean data, String columnName, String columnCriteria);
    
    //Consulta de Lotes de Asientos
    
    /**
	 * Consulta de lote de asientos por id
	 * @param journalBatchID identificador
	 * @return ResultBean con datos
	 */
    //public DocumentResultBean documentRetrieveJournalBatchByID(ParameterBean data, int journalBatchID);
    
    /**
	 * Consulta de lote de asientos por columna
	 * @param data parametros correspondientes
	 * @param columnName columna correspondiente
	 * @param columnCriteria criterio de eliminacion
	 * @return ResultBean con datos
	 */
    //public DocumentResultBean documentRetrieveJournalBatchByColumn(ParameterBean data, String column, String value);
	
	
	/* ===================================================== */
	/* ============= Movimientos de inventario ============= */
	/* ===================================================== */
	
	/**
	 * Creacion de movimiento de inventario
	 * @param data parametros correspondientes
	 * @param complete true/false dependiendo si se desea completar el documento
	 * @return ResultBean con datos
	 */
    public ResultBean movementCreate(DocumentParameterBean data, boolean complete);
    
    /**
	 * Completar movimiento de inventario
	 * @param data parametros correspondientes
	 * @param movementID id del movimiento a completar
	 * @return ResultBean con datos
	 */
    public ResultBean movementCompleteByID(ParameterBean data, int movementID);
    
    /**
     * Completar movimiento de inventario por criterio de columna
     * @param data parametros correspondientes
     * @param columnName nombre de columna
     * @param columnCriteria valor de columna
     * @return ResultBean con datos
     */
    public ResultBean movementCompleteByColumn(ParameterBean data, String columnName, String columnCriteria);
    
    /**
	 * Anulacion de movimiento de inventario por id
	 * @param data parametros correspondientes
	 * @param movementID id del movimiento a anular
	 * @return ResultBean con datos
	 */
    public ResultBean movementVoidById(ParameterBean data, int movementID);    
   
    /**
	 * Anulacion de movimiento de inventario por criterio de columna
	 * @param data parametros correspondientes
	 * @param columnName nombre de columna
	 * @param columnCriteria valor de columna
	 * @return ResultBean con datos
	 */
    public ResultBean movementVoidByColumn(ParameterBean data, String columnName, String columnCriteria);
	
	/* ===================================================== */
	/* ============= Consulta de Comprobantes ============== */
	/* ===================================================== */

	/**
	 * Devuelve un pedido en conjunto con el detalle de sus lineas 
	 * @param data parametros generales de acceso
	 * @param orderID número de documento a recuperar
	 * @return DocumentResultBean con OK y los datos del encabezado y sus lineas; o ERROR en caso contrario
	 */
	public DocumentResultBean documentRetrieveOrderByID(ParameterBean data, int orderID);
	
	/**
	 * Devuelve un pedido en conjunto con el detalle de sus lineas 
	 * @param data parametros generales de acceso
	 * @param column columna a utilizar como criterio de busqueda
	 * @param value valor a utilizar para realizar la búsqueda sobre la columna indicada
	 * @return DocumentResultBean con OK y los datos del encabezado y sus lineas; o ERROR en caso contrario
	 */
	public DocumentResultBean documentRetrieveOrderByColumn(ParameterBean data, String column, String value);
	
	/**
	 * Devuelve una factura en conjunto con el detalle de sus lineas 
	 * @param data parametros generales de acceso
	 * @param invoiceID número de documento a recuperar
	 * @return DocumentResultBean con OK y los datos del encabezado y sus lineas; o ERROR en caso contrario
	 */
	public InvoiceResultBean documentRetrieveInvoiceByID(ParameterBean data, int invoiceID);
	
	/**
	 * Devuelve una factura en conjunto con el detalle de sus lineas 
	 * @param data parametros generales de acceso
	 * @param column columna a utilizar como criterio de busqueda
	 * @param value valor a utilizar para realizar la búsqueda sobre la columna indicada
	 * @return DocumentResultBean con OK y los datos del encabezado y sus lineas; o ERROR en caso contrario
	 */
	public InvoiceResultBean documentRetrieveInvoiceByColumn(ParameterBean data, String column, String value);
	
	/**
	 * Devuelve un remito en conjunto con el detalle de sus lineas 
	 * @param data parametros generales de acceso
	 * @param inoutID número de documento a recuperar
	 * @return DocumentResultBean con OK y los datos del encabezado y sus lineas; o ERROR en caso contrario
	 */
	public DocumentResultBean documentRetrieveInOutByID(ParameterBean data, int inoutID);
	
	/**
	 * Devuelve un remito en conjunto con el detalle de sus lineas 
	 * @param data parametros generales de acceso
	 * @param column columna a utilizar como criterio de busqueda
	 * @param value valor a utilizar para realizar la búsqueda sobre la columna indicada
	 * @return DocumentResultBean con OK y los datos del encabezado y sus lineas; o ERROR en caso contrario
	 */
	public DocumentResultBean documentRetrieveInOutByColumn(ParameterBean data, String column, String value);
	
	/**
	 * Devuelve una OP/RC en conjunto con el detalle de sus lineas 
	 * @param data parametros generales de acceso
	 * @param allocationID número de documento a recuperar
	 * @return DocumentResultBean con OK y los datos del encabezado y sus lineas; o ERROR en caso contrario
	 */
	public DocumentResultBean documentRetrieveAllocationByID(ParameterBean data, int allocationID);
	
	/**
	 * Devuelve una OP/RC en conjunto con el detalle de sus lineas 
	 * @param data parametros generales de acceso
	 * @param column columna a utilizar como criterio de busqueda
	 * @param value valor a utilizar para realizar la búsqueda sobre la columna indicada
	 * @return DocumentResultBean con OK, ERROR, los datos del encabezado y sus lineas
	 */
	public DocumentResultBean documentRetrieveAllocationByColumn(ParameterBean data, String column, String value);
	
	/**
	 * Devuelve una serie de encabezados de pedido que coincida con el criterio de busqueda indicado
	 * @param data parametros generales de acceso
	 * @param bPartnerID primer criterio de búsqueda.  Todos los documentos de la E.C. indicada por su ID 
	 * @param value segundo criterio de búsqueda.  Todos los documentos de la E.C. indicada por su Value
	 * @param taxID tercer criterio de búsquedda.  Todos los documentos de la E.C. indicada por su CUIT
	 * @param filterByClient optativo. filtrar los resultados por la compañía a la cual estamos logueados
	 * @param filterByOrg  optativo. filtrar los resultados por la organización a la cual estamos logueados
	 * @param purchaseTrxOnly  si se recibe como true, filtra por IsSoTrx = 'N' (o sea solo transacciones de compra)
	 * @param salesTrxOnly  si se recibe como true, filtra por IsSoTrx = 'Y' (o sea solo transacciones de venta)
	 * @param fromDate  optativo. filtrar por fecha de inicio a los resultados, con formato YYYY-MM-DD
	 * @param toDate  optativo. filtrar por fecha de fin a los resultados, con formato YYYY-MM-DD
	 * @return MultipleDocumentsResultBean con OK y la nomina de encabezados, o error en caso contrario.
	 */
	public MultipleDocumentsResultBean documentQueryOrders(ParameterBean data, int bPartnerID, String value, String taxID, boolean filterByClient, boolean filterByOrg, boolean purchaseTrxOnly, boolean salesTrxOnly, String fromDate, String toDate);
	
	/**
	 * Devuelve una serie de encabezados de pedido que coincida con el criterio de busqueda indicado
	 * @param data parametros generales de acceso
	 * @param bPartnerID primer criterio de búsqueda.  Todos los documentos de la E.C. indicada por su ID 
	 * @param value segundo criterio de búsqueda.  Todos los documentos de la E.C. indicada por su Value
	 * @param taxID tercer criterio de búsquedda.  Todos los documentos de la E.C. indicada por su CUIT
	 * @param filterByClient optativo. filtrar los resultados por la compañía a la cual estamos logueados
	 * @param filterByOrg  optativo. filtrar los resultados por la organización a la cual estamos logueados
	 * @param purchaseTrxOnly  si se recibe como true, filtra por IsSoTrx = 'N' (o sea solo transacciones de compra)
	 * @param salesTrxOnly  si se recibe como true, filtra por IsSoTrx = 'Y' (o sea solo transacciones de venta)
	 * @param fromDate  optativo. filtrar por fecha de inicio a los resultados, con formato YYYY-MM-DD
	 * @param toDate  optativo. filtrar por fecha de fin a los resultados, con formato YYYY-MM-DD
	 * @param additionalWhereClause criterios de filtrado adicionales
	 * @param referencedTablesColumns mostrar columnas especificas de los registros referenciados.  Ejemplo: CreatedBy.Name (nombre del que creo el registro), C_BPartner_ID.Description (descripcion de la EC), C_DocType_ID.DocTypeKey (doctypekey del tipo de documento), etc.
	 * @return MultipleDocumentsResultBean con OK y la nomina de encabezados, o error en caso contrario.
	 */
	public MultipleDocumentsResultBean documentQueryOrders(ParameterBean data, int bPartnerID, String value, String taxID, boolean filterByClient, boolean filterByOrg, boolean purchaseTrxOnly, boolean salesTrxOnly, String fromDate, String toDate, String additionalWhereClause, String[] referencedTablesColumns);

	/**
	 * Devuelve una serie de encabezados de factura que coincida con el criterio de busqueda indicado
	 * @param data parametros generales de acceso
	 * @param bPartnerID primer criterio de búsqueda.  Todos los documentos de la E.C. indicada por su ID 
	 * @param value segundo criterio de búsqueda.  Todos los documentos de la E.C. indicada por su Value
	 * @param taxID tercer criterio de búsquedda.  Todos los documentos de la E.C. indicada por su CUIT
	 * @param filterByClient optativo. filtrar los resultados por la compañía a la cual estamos logueados
	 * @param filterByOrg  optativo. filtrar los resultados por la organización a la cual estamos logueados
	 * @param purchaseTrxOnly  si se recibe como true, filtra por IsSoTrx = 'N' (o sea solo transacciones de compra)
	 * @param salesTrxOnly  si se recibe como true, filtra por IsSoTrx = 'Y' (o sea solo transacciones de venta)
	 * @param fromDate  optativo. filtrar por fecha de inicio a los resultados, con formato YYYY-MM-DD
	 * @param toDate  optativo. filtrar por fecha de fin a los resultados, con formato YYYY-MM-DD
	 * @param additionalWhereClause criterios de filtrado adicionales
	 * @return MultipleDocumentsResultBean con OK y la nomina de encabezados, o error en caso contrario.
	 */
	public MultipleDocumentsResultBean documentQueryInvoices(ParameterBean data, int bPartnerID, String value, String taxID, boolean filterByClient, boolean filterByOrg, boolean purchaseTrxOnly, boolean salesTrxOnly, String fromDate, String toDate, String additionalWhereClause);
	
	
	/**
	 * Devuelve una serie de encabezados de factura que coincida con el criterio de busqueda indicado
	 * @param data parametros generales de acceso
	 * @param bPartnerID primer criterio de búsqueda.  Todos los documentos de la E.C. indicada por su ID 
	 * @param value segundo criterio de búsqueda.  Todos los documentos de la E.C. indicada por su Value
	 * @param taxID tercer criterio de búsquedda.  Todos los documentos de la E.C. indicada por su CUIT
	 * @param filterByClient optativo. filtrar los resultados por la compañía a la cual estamos logueados
	 * @param filterByOrg  optativo. filtrar los resultados por la organización a la cual estamos logueados
	 * @param purchaseTrxOnly  si se recibe como true, filtra por IsSoTrx = 'N' (o sea solo transacciones de compra)
	 * @param salesTrxOnly  si se recibe como true, filtra por IsSoTrx = 'Y' (o sea solo transacciones de venta)
	 * @param fromDate  optativo. filtrar por fecha de inicio a los resultados, con formato YYYY-MM-DD
	 * @param toDate  optativo. filtrar por fecha de fin a los resultados, con formato YYYY-MM-DD
	 * @param additionalWhereClause criterios de filtrado adicionales
	 * @param referencedTablesColumns mostrar columnas especificas de los registros referenciados.  Ejemplo: CreatedBy.Name (nombre del que creo el registro), C_BPartner_ID.Description (descripcion de la EC), C_DocType_ID.DocTypeKey (doctypekey del tipo de documento), etc.
	 * @return MultipleDocumentsResultBean con OK y la nomina de encabezados, o error en caso contrario.
	 */
	public MultipleDocumentsResultBean documentQueryInvoices(ParameterBean data, int bPartnerID, String value, String taxID, boolean filterByClient, boolean filterByOrg, boolean purchaseTrxOnly, boolean salesTrxOnly, String fromDate, String toDate, String additionalWhereClause, String[] referencedTablesColumns);
	
	/**
	 * Devuelve una serie de encabezados de remitos que coincida con el criterio de busqueda indicado
	 * @param data parametros generales de acceso
	 * @param bPartnerID primer criterio de búsqueda.  Todos los documentos de la E.C. indicada por su ID 
	 * @param value segundo criterio de búsqueda.  Todos los documentos de la E.C. indicada por su Value
	 * @param taxID tercer criterio de búsquedda.  Todos los documentos de la E.C. indicada por su CUIT
	 * @param filterByClient optativo. filtrar los resultados por la compañía a la cual estamos logueados
	 * @param filterByOrg  optativo. filtrar los resultados por la organización a la cual estamos logueados
	 * @param purchaseTrxOnly  si se recibe como true, filtra por IsSoTrx = 'N' (o sea solo transacciones de compra)
	 * @param salesTrxOnly  si se recibe como true, filtra por IsSoTrx = 'Y' (o sea solo transacciones de venta)
	 * @param fromDate  optativo. filtrar por fecha de inicio a los resultados, con formato YYYY-MM-DD
	 * @param toDate  optativo. filtrar por fecha de fin a los resultados, con formato YYYY-MM-DD
	 * @return MultipleDocumentsResultBean con OK y la nomina de encabezados, o error en caso contrario.
	 */
	public MultipleDocumentsResultBean documentQueryInOuts(ParameterBean data, int bPartnerID, String value, String taxID, boolean filterByClient, boolean filterByOrg, boolean purchaseTrxOnly, boolean salesTrxOnly, String fromDate, String toDate);
	
	/**
	 * Devuelve una serie de encabezados de remitos que coincida con el criterio de busqueda indicado
	 * @param data parametros generales de acceso
	 * @param bPartnerID primer criterio de búsqueda.  Todos los documentos de la E.C. indicada por su ID 
	 * @param value segundo criterio de búsqueda.  Todos los documentos de la E.C. indicada por su Value
	 * @param taxID tercer criterio de búsquedda.  Todos los documentos de la E.C. indicada por su CUIT
	 * @param filterByClient optativo. filtrar los resultados por la compañía a la cual estamos logueados
	 * @param filterByOrg  optativo. filtrar los resultados por la organización a la cual estamos logueados
	 * @param purchaseTrxOnly  si se recibe como true, filtra por IsSoTrx = 'N' (o sea solo transacciones de compra)
	 * @param salesTrxOnly  si se recibe como true, filtra por IsSoTrx = 'Y' (o sea solo transacciones de venta)
	 * @param fromDate  optativo. filtrar por fecha de inicio a los resultados, con formato YYYY-MM-DD
	 * @param toDate  optativo. filtrar por fecha de fin a los resultados, con formato YYYY-MM-DD
	 * @param additionalWhereClause criterios de filtrado adicionales
	 * @param referencedTablesColumns mostrar columnas especificas de los registros referenciados.  Ejemplo: CreatedBy.Name (nombre del que creo el registro), C_BPartner_ID.Description (descripcion de la EC), C_DocType_ID.DocTypeKey (doctypekey del tipo de documento), etc.
	 * @return MultipleDocumentsResultBean con OK y la nomina de encabezados, o error en caso contrario.
	 */
	public MultipleDocumentsResultBean documentQueryInOuts(ParameterBean data, int bPartnerID, String value, String taxID, boolean filterByClient, boolean filterByOrg, boolean purchaseTrxOnly, boolean salesTrxOnly, String fromDate, String toDate, String additionalWhereClause, String[] referencedTablesColumns);
	
	/**
	 * Devuelve una serie de encabezados de OP/RC que coincida con el criterio de busqueda indicado
	 * @param data parametros generales de acceso
	 * @param bPartnerID primer criterio de búsqueda.  Todos los documentos de la E.C. indicada por su ID 
	 * @param value segundo criterio de búsqueda.  Todos los documentos de la E.C. indicada por su Value
	 * @param taxID tercer criterio de búsquedda.  Todos los documentos de la E.C. indicada por su CUIT
	 * @param filterByClient optativo. filtrar los resultados por la compañía a la cual estamos logueados
	 * @param filterByOrg  optativo. filtrar los resultados por la organización a la cual estamos logueados
	 * @param purchaseTrxOnly  si se recibe como true, filtra por IsSoTrx = 'N' (o sea solo transacciones de compra)
	 * @param salesTrxOnly  si se recibe como true, filtra por IsSoTrx = 'Y' (o sea solo transacciones de venta)
	 * @param fromDate  optativo. filtrar por fecha de inicio a los resultados, con formato YYYY-MM-DD
	 * @param toDate  optativo. filtrar por fecha de fin a los resultados, con formato YYYY-MM-DD
	 * @return MultipleDocumentsResultBean con OK y la nomina de encabezados, o error en caso contrario.
	 */
	public MultipleDocumentsResultBean documentQueryAllocations(ParameterBean data, int bPartnerID, String value, String taxID, boolean filterByClient, boolean filterByOrg, boolean purchaseTrxOnly, boolean salesTrxOnly, String fromDate, String toDate);
	
	/**
	 * Devuelve una serie de encabezados de OP/RC que coincida con el criterio de busqueda indicado
	 * @param data parametros generales de acceso
	 * @param bPartnerID primer criterio de búsqueda.  Todos los documentos de la E.C. indicada por su ID 
	 * @param value segundo criterio de búsqueda.  Todos los documentos de la E.C. indicada por su Value
	 * @param taxID tercer criterio de búsquedda.  Todos los documentos de la E.C. indicada por su CUIT
	 * @param filterByClient optativo. filtrar los resultados por la compañía a la cual estamos logueados
	 * @param filterByOrg  optativo. filtrar los resultados por la organización a la cual estamos logueados
	 * @param purchaseTrxOnly  si se recibe como true, filtra por IsSoTrx = 'N' (o sea solo transacciones de compra)
	 * @param salesTrxOnly  si se recibe como true, filtra por IsSoTrx = 'Y' (o sea solo transacciones de venta)
	 * @param fromDate  optativo. filtrar por fecha de inicio a los resultados, con formato YYYY-MM-DD
	 * @param toDate  optativo. filtrar por fecha de fin a los resultados, con formato YYYY-MM-DD
	 * @param additionalWhereClause criterios de filtrado adicionales
	 * @param referencedTablesColumns mostrar columnas especificas de los registros referenciados.  Ejemplo: CreatedBy.Name (nombre del que creo el registro), C_BPartner_ID.Description (descripcion de la EC), C_DocType_ID.DocTypeKey (doctypekey del tipo de documento), etc.
	 * @return MultipleDocumentsResultBean con OK y la nomina de encabezados, o error en caso contrario.
	 */
	public MultipleDocumentsResultBean documentQueryAllocations(ParameterBean data, int bPartnerID, String value, String taxID, boolean filterByClient, boolean filterByOrg, boolean purchaseTrxOnly, boolean salesTrxOnly, String fromDate, String toDate, String additionalWhereClause, String[] referencedTablesColumns);

	/* ===================================================== */
	/* ===================== Pedidos ======================= */
	/* ===================================================== */
	/**
	 * Creación de pedido de cliente
	 * Debe indicarse, además del conjunto de parametros, una de las tres opciones para indicar la entidad comercial
	 * @param data parametros correspondientes
	 * @param bPartnerID identificador de la entidad comercial (o -1 en caso de no indicar)
	 * @param bPartnerValue clave de busqueda de la entidad comercial (o null en caso de no indicar)
	 * @param taxID CUIT de la entidad comercial (o null en caso de no indicar)
	 * @param completeOrder para especificar si se debe completar el pedido
	 * @param createInvoice para indicar si se debe crear la factura a partir del pedido
	 * @param completeInvoice en caso de crear factura, permite indicar si se debe completar también la factura
	 * @return ResultBean con OK y datos: C_Order_ID, Order_DocumentNo creado, etc. o ERROR en caso contrario.
	 */
	public ResultBean orderCreateCustomer(OrderParameterBean data, int bPartnerID, String bPartnerValue, String taxID, boolean completeOrder, boolean createInvoice, boolean completeInvoice);
	
	/**
	 * Creación de pedido de cliente
	 * Debe indicarse, además del conjunto de parametros, una de las tres opciones para indicar la entidad comercial
	 * @param data parametros correspondientes
	 * @param bPartnerID identificador de la entidad comercial (o -1 en caso de no indicar)
	 * @param bPartnerValue clave de busqueda de la entidad comercial (o null en caso de no indicar)
	 * @param taxID CUIT de la entidad comercial (o null en caso de no indicar)
	 * @param completeOrder para especificar si se debe completar el pedido
	 * @param createInvoice para indicar si se debe crear la factura a partir del pedido
	 * @param completeInvoice en caso de crear factura, permite indicar si se debe completar también la factura
	 * @param createShipment para indicar si se debe crear el remito a partir del pedido
	 * @param completeShipment en caso de crear remito, permite indicar si se debe completar también el remito 
	 * @return ResultBean con OK y datos: C_Order_ID, Order_DocumentNo creado, etc. o ERROR en caso contrario.
	 */
	public ResultBean orderCreateCustomer(OrderParameterBean data, int bPartnerID, String bPartnerValue, String taxID, boolean completeOrder, boolean createInvoice, boolean completeInvoice, boolean createShipment, boolean completeShipment);
	
	/**
	 * Creación de pedido de proveedor
	 * Debe indicarse, además del conjunto de parametros, una de las tres opciones para indicar la entidad comercial
	 * @param data parametros correspondientes
	 * @param bPartnerID identificador de la entidad comercial (o -1 en caso de no indicar)
	 * @param bPartnerValue clave de busqueda de la entidad comercial (o null en caso de no indicar)
	 * @param taxID CUIT de la entidad comercial (o null en caso de no indicar)
	 * @param completeOrder para especificar si se debe completar el pedido
	 * @param createInvoice para indicar si se debe crear la factura a partir del pedido
	 * @param completeInvoice en caso de crear factura, permite indicar si se debe completar también la factura
	 * @return ResultBean con OK y datos: C_Order_ID, Order_DocumentNo creado, etc. o ERROR en caso contrario.
	 */
	public ResultBean orderCreateVendor(OrderParameterBean data, int bPartnerID, String bPartnerValue, String taxID, boolean completeOrder, boolean createInvoice, boolean completeInvoice);

	/**
	 * Elimina un pedido en borrador.  El mismo debe ser indicado por su ID
	 * @param data parametros correspondientes
	 * @param orderID identificador del pedido (C_Order_ID)
	 * @return ResultBean con OK o ERROR
	 */
	public ResultBean orderDeleteByID(ParameterBean data, int orderID);

	/**
	 * Elimina un pedido en borrador.
	 * @param data parametros correspondientes
	 * @param columnName y columnCriteria columna y valor a filtrar para recuperar el pedido en cuestión
	 * @return ResultBean con OK o ERROR
	 */
	public ResultBean orderDeleteByColumn(ParameterBean data, String columnName, String columnCriteria);

	/**
	 * Completa un pedido en borrador.  El mismo debe ser indicado por su ID
	 * @param data parametros correspondientes
	 * @param orderID identificador del pedido (C_Order_ID)
	 * @param createInvoice para indicar si se debe crear la factura a partir del pedido
	 * @param completeInvoice en caso de crear factura, permite indicar si se debe completar también la factura
	 * @return ResultBean con OK o ERROR. 
	 */
	public ResultBean orderCompleteByID(OrderParameterBean data, int orderID, boolean createInvoice, boolean completeInvoice);
	
	/**
	 * Completa un pedido en borrador.  El mismo debe ser indicado por su ID
	 * @param data parametros correspondientes
	 * @param orderID identificador del pedido (C_Order_ID)
	 * @param createInvoice para indicar si se debe crear la factura a partir del pedido
	 * @param completeInvoice en caso de crear factura, permite indicar si se debe completar también la factura
	 * @param createShipment para indicar si se debe crear el remito a partir del pedido
	 * @param completeShipment en caso de crear remito, permite indicar si se debe completar también el remito 
	 * @return ResultBean con OK o ERROR. 
	 */
	public ResultBean orderCompleteByID(OrderParameterBean data, int orderID, boolean createInvoice, boolean completeInvoice, boolean createShipment, boolean completeShipment);

	/**
	 * Completa un pedido en borrador.  El mismo debe ser indicado por un par: Nombre de Columna / Criterio de Columna
	 * @param data parametros correspondientes
	 * @param columnName y columnCriteria columna y valor a filtrar para recuperar el pedido en cuestion
	 * @param createInvoice para indicar si se debe crear la factura a partir del pedido
	 * @param completeInvoice en caso de crear factura, permite indicar si se debe completar también la factura
	 * @return ResultBean con OK o ERROR. 
	 */
	public ResultBean orderCompleteByColumn(OrderParameterBean data, String columnName, String columnCriteria, boolean createInvoice, boolean completeInvoice);

	/**
	 * Completa un pedido en borrador.  El mismo debe ser indicado por un par: Nombre de Columna / Criterio de Columna
	 * @param data parametros correspondientes
	 * @param columnName y columnCriteria columna y valor a filtrar para recuperar el pedido en cuestion
	 * @param createInvoice para indicar si se debe crear la factura a partir del pedido
	 * @param completeInvoice en caso de crear factura, permite indicar si se debe completar también la factura
	 * @param createShipment para indicar si se debe crear el remito a partir del pedido
	 * @param completeShipment en caso de crear remito, permite indicar si se debe completar también el remito 
	 * @return ResultBean con OK o ERROR. 
	 */
	public ResultBean orderCompleteByColumn(OrderParameterBean data, String columnName, String columnCriteria, boolean createInvoice, boolean completeInvoice, boolean createShipment, boolean completeShipment);
	
	/**
	 * Anula un pedido.  El mismo debe ser indicado por su ID
	 * @param data parametros correspondientes
	 * @param orderID identificador del pedido (C_Order_ID)
	 * @return ResultBean con OK o ERROR 
	 */
	public ResultBean orderVoidByID(ParameterBean data, int orderID);
	
	/**
	 * Anula uno o más pedidos.  Los mismos deben ser indicados por un par: Nombre de Columna / Criterio de Columna
	 * En caso de recuperar más de un pedido se anularán todos.  En caso de error en alguno no se anulará ninguno.
	 * @param data parametros correspondientes
	 * @param columnName y columnCriteria columna y valor a filtrar para recuperar el pedido en cuestion
	 * @return ResultBean con OK o ERROR 
	 */
	public ResultBean orderVoidByColumn(ParameterBean data, String columnName, String columnCriteria);
	
	
	/**
	 * Actualiza campos de la cabecera del pedido únicamente.  Si el pedido estaba completado lo reabre.
	 * @param data el conjunto de datos a actualizar correspondientes a la cabecera del pedido
	 * @param orderID el ID del pedido a actualizar
	 * @param completeOrder completa la orden si la misma tuvo que ser reabierta, o si bien la misma estaba en borrador
	 * @return ResultBean con OK, ERROR, etc. 
	 */
	public ResultBean orderUpdateByID(ParameterBean data, int orderID, boolean completeOrder);
	
	/**
	 * Actualiza campos de la cabecera del pedido únicamente.  Si el pedido estaba completado lo reabre.
	 * @param data el conjunto de datos a actualizar correspondientes a la cabecera del pedido
	 * @param columnName y columnCriteria columna y valor a filtrar para recuperar el pedido en cuestion
	 * @param completeOrder completa la orden si la misma tuvo que ser reabierta, o si bien la misma estaba en borrador
	 * @return ResultBean con OK, ERROR, etc. 
	 */
	public ResultBean orderUpdateByColumn(ParameterBean data, String columnName, String columnCriteria, boolean completeOrder);

	
	/**
	 * Consulta - y eventualmente actualiza en la misma operacion si la informacion es correcta - las cantidades pertenecientes a las lineas de pedido.
	 * Esta operación se utiliza principalmente para la entrega de mercadería en una sucursal distinta a la sucursal en donde se generó el pedido,
	 * con lo cual se requiere validar que las cantidades pendientes a entregar efectivamente sean las adecuadas, previo a la entrega de mercadería.
	 * Desde la sucursal donde se retira la mercadería, se debe enviar a la sucursal origen del pedido las existencias actuales de cada línea de pedido, 
	 * así como las nuevas cantidades.  Unicamente si la información enviada desde la sucursal remota en las líneas de pedido coincide con la información 
	 * de la sucursal origen, entonces se aceptarán las nuevas cantidades, actualizando las mismas.
	 * @param data parametros de login, así como de las líneas a checkear y actualizar (cada línea en una posición de documentLines), bajo siguientes las claves,
	 * 			las cuales serán parte del Map perteneciente a una posición de documentLinest: retrieveUID de la linea de pedido y alguna de los siguientes: currentQtyOrdered,  
	 * 			currentQtyDelivered, currentQtyReserved, currentQtyTransferred, currentQtyReturned.  Adicionalmente se enviarán los nuevos valores de cantidades, bajo  
	 * 			algunas de las siguientes claves: newQtyOrdered, newQtyDelivered, newQtyReserved, newQtyTransferred y newQtyReturned.
	 * 			El nombre de las columnas a verificar (current*) como el de las columnas a actualizar (new*), como por ejemplo currentQtyTransferred, newQtyDelivered, etc. 
	 * 			junto con las cantidades, así como la columna obligatoria retrieveUID se deberán enviar en documentLines (uno por linea de pedido).  
	 * 			No es necesario enviar todas las columnas current* o new* , solo las que se desea evaluar / actualizar.
	 * 			La implementacion no valida si las lineas enviadas pertenecen a un pedido o a varios.  
	 * @return
	 * 		OK si no hay discrepancias entre las existencias y se ejecuta correctamente la modificacion, o bien
	 * 			- un error general (envio de una columna inexistente por ejemplo),
	 *			- la nómina de cantidades actuales que deberán ser adecuados en el host remoto (bajo campos current*)  
	 */
	public DocumentResultBean orderLinesCheckUpdate(DocumentParameterBean data);
	
	/* ===================================================== */
	/* ===================== Remitos ======================= */
	/* ===================================================== */
	/**
	 * Creación de remito de salida
	 * Debe indicarse, además del conjunto de parametros, una de las tres opciones para indicar la entidad comercial
	 * @param data parametros correspondientes
	 * @param bPartnerID identificador de la entidad comercial (o -1 en caso de no indicar)
	 * @param bPartnerValue clave de busqueda de la entidad comercial (o null en caso de no indicar)
	 * @param taxID CUIT de la entidad comercial (o null en caso de no indicar)
	 * @param completeInOut para especificar si se debe completar el remito
	 * @return ResultBean con OK y datos: M_InOut_ID, InOut_DocumentNo creado, etc. o ERROR en caso contrario.
	 */
	public ResultBean inOutCreateCustomer(DocumentParameterBean data, int bPartnerID, String bPartnerValue, String taxID, boolean completeInOut);

	/**
	 * Creación de remito de proveedor
	 * Debe indicarse, además del conjunto de parametros, una de las tres opciones para indicar la entidad comercial
	 * @param data parametros correspondientes
	 * @param bPartnerID identificador de la entidad comercial (o -1 en caso de no indicar)
	 * @param bPartnerValue clave de busqueda de la entidad comercial (o null en caso de no indicar)
	 * @param taxID CUIT de la entidad comercial (o null en caso de no indicar)
	 * @param completeInOut para especificar si se debe completar el remito
	 * @return ResultBean con OK y datos: M_InOut_ID, InOut_DocumentNo creado, etc. o ERROR en caso contrario.
	 */
	public ResultBean inOutCreateVendor(DocumentParameterBean data, int bPartnerID, String bPartnerValue, String taxID, boolean completeInOut);

	/**
	 * Creación de un remito a partir de un pedido
	 * @param data información de acceso y de las líneas a remitir (para remisiones parciales, indicando para cada línea el C_OrderLine_ID y QtyEntered).   
	 * 			Si no se indican líneas, se considera remisión completa.
	 * @param orderID pedido a tomar como base para la creación del remito
	 * @param completeInOut si debe completar el remito creado
	 * @return ResultBean con OK y datos: M_InOut_ID, InOut_DocumentNo creado, etc. o ERROR en caso contrario.
	 */
	public ResultBean inOutCreateFromOrder(DocumentParameterBean data, int orderID, boolean completeInOut);
	
	/**
	 * Elimina un remito en borrador.  El mismo debe ser indicado por su ID
	 * @param data parametros correspondientes
	 * @param inOutID identificador del remito (M_InOut_ID)
	 * @return ResultBean con OK o ERROR
	 */
	public ResultBean inOutDeleteByID(ParameterBean data, int inOutID);

	/**
	 * Elimina un remito en borrador.
	 * @param data parametros correspondientes
	 * @param columnName y columnCriteria columna y valor a filtrar para recuperar el remito en cuestión
	 * @return ResultBean con OK o ERROR
	 */
	public ResultBean inOutDeleteByColumn(ParameterBean data, String columnName, String columnCriteria);

	/**
	 * Completa un remito en borrador.  El mismo debe ser indicado por su ID
	 * @param data parametros correspondientes
	 * @param inOutID identificador del remito (M_InOut_ID)
	 * @return ResultBean con OK o ERROR. 
	 */
	public ResultBean inOutCompleteByID(ParameterBean data, int inOutID);

	/**
	 * Completa un remito en borrador.  El mismo debe ser indicado por un par: Nombre de Columna / Criterio de Columna
	 * @param data parametros correspondientes
	 * @param columnName y columnCriteria columna y valor a filtrar para recuperar el remito en cuestion
	 * @return ResultBean con OK o ERROR. 
	 */
	public ResultBean inOutCompleteByColumn(ParameterBean data, String columnName, String columnCriteria);

	/**
	 * Anula un remito.  El mismo debe ser indicado por su ID
	 * @param data parametros correspondientes
	 * @param inOutID identificador del remito (M_InOut_ID)
	 * @return ResultBean con OK o ERROR 
	 */
	public ResultBean inOutVoidByID(ParameterBean data, int inOutID);
	
	/**
	 * Anula un remito.  El mismo debe ser indicado por un par: Nombre de Columna / Criterio de Columna
	 * En caso de recuperar más de un remito se anularán todos.  En caso de error en alguno no se anulará ninguno.
	 * @param data parametros correspondientes
	 * @param columnName y columnCriteria columna y valor a filtrar para recuperar el remito en cuestion
	 * @return ResultBean con OK o ERROR 
	 */
	public ResultBean inOutVoidByColumn(ParameterBean data, String columnName, String columnCriteria);


	/* ================================================================== */
	/* ================== Saldos Entidades Comerciales ================== */
	/* ================================================================== */

	/**
	 * Sumatoria de pedidos no facturados
	 * @param data datos basicos de acceso
	 * @param bPartnerID primer opcion para especificar entidad comercial
	 * @param bPartnerList segunda opcion para especificar entidad comercial (conjunto de IDs de EC)
	 * @param cuit tercer opcion para especificar entidad comercial
	 * @param clientID filtrar por la compañía indicada (debe ser mayor a cero, o en cc. se ignora)
	 * @param orgID filtrar por la organización indicada (debe ser mayor a cero, o en cc. se ignora)
	 * @return ResultBean con OK y el dato Amount en mainResult o ERROR en caso contrario
	 * 		   Amount puede devolver null si el criterio especificado no devuelve un valor resultante
	 * 			El valor sera siempre positivo ya que únicamente se toman las facturas de venta (isSoTrx = Y) 
	 */
	public ResultBean bPartnerBalanceSumOrdersNotInvoiced(ParameterBean data, int bPartnerID, int[] bPartnerList, String cuit, int clientID, int orgID);
	
	/**
	 * Sumatoria de Facturas
	 * @param data datos basicos de acceso
	 * @param bPartnerID primer opcion para especificar entidad comercial
	 * @param bPartnerList segunda opcion para especificar entidad comercial (conjunto de IDs de EC)
	 * @param cuit tercer opcion para especificar entidad comercial
	 * @param clientID filtrar por la compañía indicada (debe ser mayor a cero, o en cc. se ignora)
	 * @param orgID filtrar por la organización indicada (debe ser mayor a cero, o en cc. se ignora)
	 * @return ResultBean con OK y el dato Amount en mainResult o ERROR en caso contrario
	 * 		   Amount puede devolver null si el criterio especificado no devuelve un valor resultante
	 * 			El valor puede ser positivo o negativo dependiendo de los montos de las facturas de la 
	 * 			entidad comercial que se está evaluando.  Monto positivo indica mayor monto en facturas
	 * 			de venta hacia dicha entidad comercial que el monto en facturas de compra de la misma
	 */
	public ResultBean bPartnerBalanceSumInvoices(ParameterBean data, int bPartnerID, int[] bPartnerList, String cuit, int clientID, int orgID);
	
	/**
	 * Sumatoria de cobros y pagos (Banco y Efectivo)
	 * @param data datos basicos de acceso
	 * @param bPartnerID primer opcion para especificar entidad comercial
	 * @param bPartnerList segunda opcion para especificar entidad comercial (conjunto de IDs de EC)
	 * @param cuit tercer opcion para especificar entidad comercial
	 * @param clientID filtrar por la compañía indicada (debe ser mayor a cero, o en cc. se ignora)
	 * @param orgID filtrar por la organización indicada (debe ser mayor a cero, o en cc. se ignora)
	 * @return ResultBean con OK y el dato Amount en mainResult o ERROR en caso contrario
	 * 		   Amount puede devolver null si el criterio especificado no devuelve un valor resultante
	 * 			El valor puede ser positivo o negativo dependiendo de los montos de los cobros y pagos 
	 * 			de la entidad comercial que se está evaluando.  Monto positivo indica mayor monto en  
	 * 			cobros realizados sobre de dicha entidad comercial que pagos hacia la misma
	 */
	public ResultBean bPartnerBalanceSumPayments(ParameterBean data, int bPartnerID, int[] bPartnerList, String cuit, int clientID, int orgID);
	
	/**
	 * Sumatoria de Cheques en cartera
	 * @param data datos basicos de acceso
	 * @param bPartnerID primer opcion para especificar entidad comercial
	 * @param bPartnerList segunda opcion para especificar entidad comercial (conjunto de IDs de EC)
	 * @param cuit tercer opcion para especificar entidad comercial
	 * @param clientID filtrar por la compañía indicada (debe ser mayor a cero, o en cc. se ignora)
	 * @param orgID filtrar por la organización indicada (debe ser mayor a cero, o en cc. se ignora)
	 * @return ResultBean con OK y el dato Amount en mainResult o ERROR en caso contrario
	 * 		   Amount puede devolver null si el criterio especificado no devuelve un valor resultante
	 * 			El valor devuelvo siempre se tomará como monto positivo, dado que sólo se consideran 
	 * 			las transacciones de venta (isSoTrx = Y) de pagos de cheques en cartera, cuyo docstatus = 'CO'  
	 */
	public ResultBean bPartnerBalanceSumChecks(ParameterBean data, int bPartnerID, int[] bPartnerList, String cuit, int clientID, int orgID);

	
	/* ================================================================== */
	/* ====================== Recibos de clientes ======================= */
	/* ================================================================== */

	/**
	 * Registra un recibo de cliente que contiene múltiples medios de cobro imputados a una o mas facturas 
	 * @param data parametros correspondientes a las facturas y pagos a cancelar
	 * @param bPartnerID identificador de la entidad comercial (o -1 en caso de no indicar)
	 * @param bPartnerValue clave de busqueda de la entidad comercial (o null en caso de no indicar)
	 * @param taxID CUIT de la entidad comercial (o null en caso de no indicar)
	 * @return ResultBean con OK y datos: C_AllocationHdr_ID, AllocationHdr_DocumentNo creado, etc. o ERROR en caso contrario.
	 */
	public ResultBean allocationCreateReceipt(AllocationParameterBean data, int bPartnerID, String bPartnerValue, String taxID);
	
	/**
	 * Registra un recibo de cliente que contiene múltiples medios de cobro imputados que se tomarán como anticipos, 
	 * es decir que no se imputan a ninguna factura y quedan como crédito en la cuenta corriente de la Entidad Comercial
	 * @param data parametros correspondientes a las facturas y pagos a cancelar
	 * @param bPartnerID identificador de la entidad comercial (o -1 en caso de no indicar)
	 * @param bPartnerValue clave de busqueda de la entidad comercial (o null en caso de no indicar)
	 * @param taxID CUIT de la entidad comercial (o null en caso de no indicar)
	 * @return ResultBean con OK y datos: C_AllocationHdr_ID, AllocationHdr_DocumentNo creado, etc. o ERROR en caso contrario.
	 */
	public ResultBean allocationCreateEarlyReceipt(AllocationParameterBean data, int bPartnerID, String bPartnerValue, String taxID);
	
	/**
	 * Realiza la anulación de un Recibo emitido previamente
	 * @param data información basica de acceso
	 * @param allocationID recibo a anular
	 * @param allocationAction tipo de anulación:
	 * 		Revertir unicamente <code>AllocationParameterBean.ALLOCATIONACTION_RevertAllocation</code>, o bien 
	 *		Revertir y Anular Cobros <code>ALLOCATIONACTION_VoidPayments</code>, o bien
	 *		Revertir y Anular Cobros y Retenciones <code>ALLOCATIONACTION_VoidPaymentsRetentions</code>. 
	 * @return ResultBean con OK o ERROR. 
	 */
	public ResultBean allocationVoidByID(AllocationParameterBean data, int allocationID, String allocationAction);
	
	/**
	 * Realiza la anulación de un Recibo emitido previamente
	 *  En caso de recuperar más de un recibo se anularán todos.  En caso de error en alguno no se anulará ninguno.
	 * @param data información basica de acceso
	 * @param columnName y columnCriteria es  el criterio de búsqueda para recuperar los recibos
	 * @param allocationAction tipo de anulación:
	 * 		Revertir unicamente <code>AllocationParameterBean.ALLOCATIONACTION_RevertAllocation</code>, o bien 
	 *		Revertir y Anular Cobros <code>ALLOCATIONACTION_VoidPayments</code>, o bien
	 *		Revertir y Anular Cobros y Retenciones <code>ALLOCATIONACTION_VoidPaymentsRetentions</code>. 
	 * @return ResultBean con OK o ERROR. 
	 */
	public ResultBean allocationVoidByColumn(AllocationParameterBean data, String columnName, String columnCriteria, String allocationAction);
	
	
	/* ================================================================== */
	/* =========================== Usuarios ============================= */
	/* ================================================================== */

	/**
	 * Alta de un usuario
	 * @param data parametros correspondientes
	 * @return ResultBean con OK, ERROR, etc.
	 */
	public ResultBean userCreate(ParameterBean data);
	
	/**
	 * Recupera un usuario
	 * @param data parametros correspondientes
	 * @param userID recuperar usuario por este criterio
	 * @return ResultBean con los datos correspondientes
	 */
	public ResultBean userRetrieveByID(ParameterBean data, int userID);

	/**
	 * Recupera un usuario a partir del nombre de una columna y un criterio de búsqueda
	 * @param data parametros correspondientes
	 * @param columnName columna sobre la cual buscar el usuario
	 * @param criteria valor a buscar en la columna indicada
	 * @return ResultBean con los datos correspondientes
	 */
	public ResultBean userRetrieveByColumn(ParameterBean data, String columnName, String criteria);
	
	/**
	 * Actualización de un usuario a partir de su ID
	 * @param data parametros correspondientes
	 * @param userID identificador del usuario a modificar
	 * @return ResultBean con OK, ERROR, etc.
	 */
	public ResultBean userUpdateByID(ParameterBean data, int userID);
	
	/**
	 * Eliminación logica de un usuario
	 * @param data parametros correspondientes
	 * @param userID identificador del articulo a eliminar
	 * @return ResultBean con OK, ERROR, etc.
	 */
	public ResultBean userDeleteByID(ParameterBean data, int userID);

	/**
	 * Consulta por las compañías/organizaciones sobre las que tiene acceso el usuario
	 * @param data parametros de acceso.  Unicamente usuario y password
	 * @return ResultBean con la nomina de compañías / organizaciones.
	 */
	public MultipleDocumentsResultBean userClientOrgAccessQuery(ParameterBean data);
	
	/* ================================================================== */
	/* ============================ Stock =============================== */
	/* ================================================================== */

	/**
	 * Consulta general de stock.  Devolverá el totalizado de stock POR ARTÍCULO 
	 * @param data parametros de login.  Si orgID = 0 entonces no filtrará por organización la consulta
	 * @param warehouseList nomina de warehouseIDs a usar como filtro (o null en caso de no querer filtrar por este criterio).
	 * @param locatorID id de ubicación a usar como filtro (o 0 en caso de no querer filtrar por este criterio)
	 * @param productValue clave de búsqueda del artículo (o null en caso de no querer filtrar por este criterio)
	 * @param productID id de artículo (o 0 en caso de no querer filtrar por este criterio)
	 * @param lot lote del conjunto de atributos del artículo (o null en caso de no querer filtrar por este criterio)
	 * @param serno nro de serie del conjunto de atributos del artículo (o null en caso de no querer filtrar por este criterio)
	 * @return 
	 * 		El listado de stock totalizado por cada artículo, con datos del artículo y sus cantidades:
	 *			QtyOnHand: Cantidad en stock
	 *			QtyReserved: Cantidad reservada
	 *			QtyOrdered: Cantidad pendiente de recepción
	 *			QtyAvailable: Cantidad disponible (QtyOnHand - QtyReserved)
	 */
	public StorageResultBean storageQuery(ParameterBean data, int[] warehouseList, int locatorID, String productValue, int productID, String lot, String serno);
	


	/* ================================================================== */
	/* ========================= Inventario ============================= */
	/* ================================================================== */
	
	/**
	 * Creación de entrada en Inventario (Recuento de inventario, E/S Simple, etc.)
	 * @param data parametros correspondientes (cabecera y lineas)
	 * @param completeInventory indica si quiere completar el inventario
	 * @return ResultBean con OK, ERROR, etc. 
	 */
	public ResultBean inventoryCreate(DocumentParameterBean data, boolean completeInventory);
	
	/**
	 * Permite completar un inventario indicando su ID
	 * @param data parametros de acceso
	 * @param inventoryID ID del inventario a completar
	 * @return ResultBean con OK, ERROR, etc. 
	 */
	public ResultBean inventoryCompleteByID(ParameterBean data, int inventoryID);
	
	/**
	 * Permite completar un inventario especificando el mismo mediante una columna y su valor 
	 * @param data parametros de acceso
	 * @param columnName columna a filtrar para recuperar el inventario en cuestión
	 * @param value valor a filtrar para recuperar el inventario en cuestión
	 * @return ResultBean con OK, ERROR, etc. 
	 */
	public ResultBean inventoryCompleteByColumn(ParameterBean data, String columnName, String value);
	
	/**
	 * Permite eliminar un inventario indicando su ID
	 * @param data parametros de acceso
	 * @param inventoryID ID del inventario a eliminar
	 * @return ResultBean con OK, ERROR, etc. 
	 */
	public ResultBean inventoryDeleteByID(ParameterBean data, int inventoryID);
	
	/**
	 * Permite eliminar un inventario especificando el mismo mediante una columna y su valor
	 * @param data parametros de acceso
	 * @param columnName columna a filtrar para recuperar el inventario en cuestión
	 * @param value valor a filtrar para recuperar el inventario en cuestión
	 * @return ResultBean con OK, ERROR, etc. 
	 */
	public ResultBean inventoryDeleteByColumn(ParameterBean data, String columnName, String value);
		
	/**
	 * Permite anular un inventario indicando su ID
	 * @param data parametros de acceso
	 * @param inventoryID ID del inventario a anular
	 * @return ResultBean con OK, ERROR, etc. 
	 */
	public ResultBean inventoryVoidByID(ParameterBean data, int inventoryID);
	
	/**
	 * Permite anular uno o más inventarios especificando el mismo mediante una columna y su valor
	 * En caso de recuperar más de un inventario se anularán todos.  En caso de error en alguno no se anulará ninguno.
	 * @param data parametros de acceso
	 * @param columnName columna a filtrar para recuperar el inventario en cuestión
	 * @param value valor a filtrar para recuperar el inventario en cuestión
	 * @return ResultBean con OK, ERROR, etc. 
	 */
	public ResultBean inventoryVoidByColumn(ParameterBean data, String columnName, String value);
	

	/* ================================================================== */
	/* ====================== Direcciones de EC ========================= */
	/* ================================================================== */
	
	/**
	 * Alta de una dirección de entidad comercial
	 * @param data parametros correspondientes a la direccion
	 * @return ResultBean con OK C_BPartner_Location_ID o ERROR en caso de error.
	 */
	public ResultBean bPartnerLocationCreate(ParameterBean data);
	
	/**
	 * Actualización de una dirección de entidad comercial
	 * @param data parametros correspondientes a la direccion
	 * @param bPartnerLocationID id de la dirección de la EC
	 * @return ResultBean con OK o ERROR en caso de error.
	 */
	public ResultBean bPartnerLocationUpdate(ParameterBean data, int bPartnerLocationID);
	
	/**
	 * Eliminación logica de una dirección de entidad comercial
	 * @param data parametros de acceso
	 * @param bPartnerLocationID id de la dirección de la EC
	 * @return ResultBean con OK o ERROR en caso de error.
	 */
	public ResultBean bPartnerLocationDelete(ParameterBean data, int bPartnerLocationID);
	
	/**
	 * Recuperar una dirección de entidad comercial
	 * @param data parametros de acceso
	 * @param bPartnerLocationID id de la dirección de la EC
	 * @return ResultBean con el detalle o ERROR en caso de error.
	 */
	public ResultBean bPartnerLocationRetrieve(ParameterBean data, int bPartnerLocationID);

	
	/* ================================================================== */
	/* ===================== Precios de artículos ======================= */
	/* ================================================================== */
	
	/**
	 *  Crea o actualiza el precio de un artículo.
	 * 	La operación primero busca si el artículo ya tiene un precio asignado 
	 *  para la versión de tarifa parámetro, si lo tiene, entonces modifica el 
	 *  precio con el nuevo valor, si no lo tiene entonces crea el registro de 
	 *  precio para el artículo en esa tarifa.
	 *  
	 *  Respecto de los campos de Precio tenemos que:
	 * 		PriceList: si no se envía se asgina su valor a lo que se envíe en PriceStd
	 * 		PriceStd: es el único precio obligatorio en la operación
	 * 		PriceLimit: si no se envía se asigna cero, haciendo que el producto se pueda vender a cualquier precio.
     *
	 * @param data parametros del precio a crear o actualizar
	 * @return ResultBean con OK o ERROR en caso de error.
	 */
	public ResultBean productPriceCreateUpdate(ParameterBean data);
	
	/**
	 * Borrado logico del precio de un artículo
	 * @param data parametros de acceso
	 * @param productID id del producto
	 * @param priceListVersionID id de la lista de precio
	 * @return ResultBean OK o ERROR en caso de error.
	 */
	public ResultBean productPriceDelete(ParameterBean data, int productID, int priceListVersionID);

	/**
	 * Recupera los datos del precio de un artículo
	 * @param data parametros de acceso
	 * @param productID id del producto
	 * @param priceListVersionID id de la lista de precio
	 * @return ResultBean con el detalle o ERROR en caso de error.
	 */
	public ResultBean productPriceRetrieve(ParameterBean data, int productID, int priceListVersionID);
	

	/* ================================================================== */
	/* ===================== Ordenes de Producción ====================== */
	/* ================================================================== */
	
	/**
	 * Crea una orden de producción
	 * @param data parametros de la orden a generar
	 * @param completeProductionOrder indica si se desea completar la orden de produccion
	 * @return ResultBean OK, C_Production_Order_ID y ProductionOrder_DocumentNo o ERROR en caso de error.
	 */
	public ResultBean productionOrderCreate(DocumentParameterBean data, boolean completeProductionOrder);
	
	/**
	 * Elimina una orden de producción
	 * @param data parametros de acceso
	 * @param productionOrderID id de la orden de producción
	 * @return ResultBean OK o ERROR en caso de error.
	 */
	public ResultBean productionOrderDelete(ParameterBean data, int productionOrderID);

	/**
	 * Completa una orden de producción
	 * @param data parametros de acceso
	 * @param productionOrderID id de la orden de producción
	 * @return ResultBean OK o ERROR en caso de error.
	 */
	public ResultBean productionOrderComplete(ParameterBean data, int productionOrderID);
	
	/**
	 * Anula una orden de producción
	 * @param data parametros de acceso
	 * @param productionOrderID id de la orden de producción
	 * @return ResultBean OK o ERROR en caso de error.
	 */
	public ResultBean productionOrderVoid(ParameterBean data, int productionOrderID);

	/**
	 * Anula una o más ordenes de producción
	 * En caso de recuperar más de una orden se anularán todas.  En caso de error en alguna no se anulará ninguna.
	 * @param data parametros de acceso
	 * @param columnName y columnCriteria son los criterios de recuperación de las ordenes de producción
	 * @return ResultBean OK o ERROR en caso de error.
	 */
	public ResultBean productionOrderVoidByColumn(ParameterBean data, String columnName, String columnCriteria);
	
	/* ================================================================== */
	/* ====================== Boletas de depósito ======================= */
	/* ================================================================== */
	
	/**
	 * Crea una boleta de depósito
	 * @param data parametros de la boleta de depósito
	 * @param completeDepositSlip indica si se desea completar al boleta de depósito
	 * @return ResultBean OK, M_BoletaDeposito_ID y BoletaDeposito_DocumentNo o ERROR en caso de error.
	 */
	public ResultBean depositSlipCreate(DocumentParameterBean data, boolean completeDepositSlip);
	
	/**
	 * Elimina una boleta de depósito
	 * @param data parametros de acceso
	 * @param depositSlipID id de la boleta de depósito
	 * @return ResultBean OK o ERROR en caso de error.
	 */
	public ResultBean depositSlipDelete(ParameterBean data, int depositSlipID);

	/**
	 * Completa una boleta de depósito
	 * @param data parametros de acceso
	 * @param depositSlipID id de la boleta de depósito
	 * @return ResultBean OK o ERROR en caso de error.
	 */
	public ResultBean depositSlipComplete(ParameterBean data, int depositSlipID);
	
	/**
	 * Anula una boleta de depósito
	 * @param data parametros de acceso
	 * @param depositSlipID id de la boleta de depósito
	 * @return ResultBean OK o ERROR en caso de error.
	 */
	public ResultBean depositSlipVoid(ParameterBean data, int depositSlipID);
	
	/**
	 * Anula una o más boletas de depósito
	 * En caso de recuperar más de una boleta se anularán todas.  En caso de error en alguna no se anulará ninguna.
	 * @param data parametros de acceso
	 * @param columnName y columnCriteria son los criterios de recuperación de las boletas de deposito
	 * @return ResultBean OK o ERROR en caso de error.
	 */
	public ResultBean depositSlipVoidByColumn(ParameterBean data, String columnName, String columnCriteria);
	
	
	/* ================================================================== */
	/* ====================== Lista de materiales ======================= */
	/* ================================================================== */
	
	/**
	 * Adiciona una entrada a la configuración LDM para un artículo dado
	 * @param data datos a incorporar
	 * @return ResultBean OK y M_Product_BOM_ID o ERROR en caso de error.  
	 */
	public ResultBean billOfMaterialCreate(ParameterBean data);	

	/**
	 * Elimina una entrada en la configuración LDM
	 * @param data datos de acceso
	 * @param productBOMId M_Product_BOM_ID a eliminar
	 * @return ResultBean OK o ERROR en caso de error.  
	 */
	public ResultBean billOfMaterialDelete(ParameterBean data, int productBOMId);

	/* ================================================================== */
	/* ========================== Procesos ============================== */
	/* ================================================================== */
	
	/**
	 * Cierre de impresora fiscal.
	 * Requiere especificar los siguientes argumentos: <br>
	 * 		- FiscalCloseType (String ("X" o "Z") / obligatorio)
	 * 		- C_Controlador_Fiscal_ID (entero / obligatorio)
	 * @param data datos de acceso y argumentos. Se utiliza la map de mainTable para especificar 
	 * los argumentos del proceso, a fin de lograr versatilidad en caso de tener que incorporar nuevos parametros.
	 * @return ResultBean con OK o ERROR en caso de error
	 */
	public ResultBean processFiscalPrinterClose(ParameterBean data);
	
	/**
	 * Cierre de lote de Tarjeta de Credito <br>
	 * Requiere especificar los siguientes argumentos: <br> 
	 * 		- CouponBatchNumber (String / obligatorio), <br> 
	 * 		- M_EntidadFinanciera_ID (entero / obligatorio), <br>
	 * 		- AD_Org_ID (entero / obligatorio)
	 * @param data datos de acceso y argumentos. Se utiliza la map de mainTable para especificar 
	 * los argumentos del proceso, a fin de lograr versatilidad en caso de tener que incorporar nuevos parametros.
	 * @return ResultBean con OK o ERROR en caso de error
	 */
	public ResultBean processCreditCardBatchClose(ParameterBean data);
	
	/**
	 * Recupera en formato PDF la impresion de un documento (factura, pedido, remito, RC) <br> 
	 * tal como se visualizaría en el visor de documentos de Libertya al imprimirlo <br>
	 * @param data datos de acceso y argumentos. Se utiliza la map de mainTable para especificar 
	 * los argumentos del proceso, a fin de lograr versatilidad en caso de tener que incorporar nuevos parametros.
	 * 	 	- tableName nombre de la tabla sobre la cual recuperar la impresion. Opciones validas: C_Invoice, C_Order, M_InOut, C_AllocationHdr <br>
	 * 		- recordID identificador del registro sobre el cual recuperar la impresion <br>
	 * @return un string codificado en Base64 con la informacion del documento en la key con nombre PDF del resultBean
	 */
	public ResultBean processRetrievePdfFromDocument(ParameterBean data);
	
	
	/**
	 * Dispara la generacion de un cupon promocional
	 * @param data datos de acceso en donde tambien debe especificarse en la map los siguientes:
	 * - invoiceDoc: numero de documento de la factura original con la que se generará el cupon promocional
	 * - customerDNI: numero de documento del cliente indicado en la factura original
	 * @return la siguiente informacion bajo cada una de las siguientes claves: <br>
	 * - Code: Codigo de cupon <br>
	 * - Promotion: Nombre de la promocion <br>
	 * - ValidFrom: Valido desde <br>
	 * - ValidTo: Valido hasta (si fue definido) <br>
	 * - Batch: Numero de lote
	 */
	public ResultBean processGeneratePromotionCode(ParameterBean data);
	
	/* ================================================================== */
	/* ========================= Replicación ============================ */
	/* ================================================================== */

	/**
	 * Procesa el XML recibido, el cual contiene los eventos de replicación correspondientes
	 * (inserción, eliminación, modificación), y retorna los resultados en cada caso
	 * @param data datos a replicar
	 * @return resultado de la ejecución
	 */
	public ReplicationResultBean replicate(ReplicationParameterBean data);

	
	/* ================================================================== */
	/* ==================== Funciones de uso general ==================== */
	/* ================================================================== */

	/**
	 * Devuelve una serie de registros de una tabla dada
	 * @param data parametros generales de acceso y columnas a recuperar 
	 * @param tableName Nombre de tabla (M_Product, C_BPartner, C_Order, C_Invoice, M_InOut, C_AllocationHdr, etc.)
	 * @param whereClause criterio de filtrado
	 * @param includeNamedReferences para las foreign keys, devolver además del ID, el name, value o identificador correspondiente al registro referenciado
	 * @return MultipleRecordsResultBean con OK, ERROR, los registros correspondientes
	 */
	public MultipleRecordsResultBean recordQuery(FilteredColumnsParameterBean data, String tableName, String whereClause, boolean includeNamedReferences);
	
	/**
	 * Devuelve una serie de registros de una tabla dada, de manera directa sin instanciar POs ni pasar por diccionario de datos.  Operacion de muy bajo nivel.
	 * @param data parametros generales de acceso y columnas a recuperar (serán las usadas en la consulta)
	 * @param tableName Nombre de tabla (M_Product, C_BPartner, C_Order, C_Invoice, M_InOut, C_AllocationHdr, etc.)
	 * @param whereClause criterio de filtrado
	 * @return MultipleRecordsResultBean con OK, ERROR, los registros correspondientes
	 */
	public MultipleRecordsResultBean recordQueryDirect(FilteredColumnsParameterBean data, String tableName, String whereClause);
	
	/**
	 * Ejecuta un metodo específico de Libertya de manera dinámica
	 * @param data parametros generales, información del metodo a ejecutar y parámetros
	 * @return ResultBean con OK, ERROR, etc.
	 */
	public CustomServiceResultBean customService(CustomServiceParameterBean data);
	
}
