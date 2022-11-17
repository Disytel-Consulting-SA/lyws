# Libertya Web Services (LYWS)

## Generalidades

Libertya Web Services permite interactuar con Libertya de manera similar a como lo haría un usuario, abarcando un amplio espectro de “actividades” (operaciones), tanto de gestión como de recuperación de datos.

Concebido como un “thin layer” bajo protocolo SOAP, delega lo más posible la lógica de negocios a LY CORE y/o components instalados, siendo una una aplicación más dentro del contenedor de aplicaciones.

Abarca todo tipo de operaciones:
- Gestión de maestros: Entidades Comerciales, Ubicaciones, Artículos, Precios, Lista de Materiales, etc.
- Transacciones: Pedidos, Facturas, Remitos, Recibos de Clientes, Boletas de Depósito, Inventario, etc.
- Consultas: Recuperación de documentos (cabeceras / líneas), de registros según criterio especificado
- Custom: Permite invocar lógica ad-hoc Java implementando una interfaz específica

## Libertya Web Services Extendido (LYWSE)

Si bien LYWS simplifica considerablemente la gestión de parámetros/resultados desde Java gracias a la jerarquía de Beans (Parameters & Results), el manejo desde otras tecnologías puede llegar a requerir el uso de estructuras más tradicionales.

Es por ésto que se ha definido un conjunto de wrappers bajo LYWSE para el total de los servicios de LYWS, los cuales presentan una gestión de argumentos con tipos primitivos dentro del contexto de Servicios Web.

Las operaciones incluidas en LYWSE son por lo tanto exactamente las mismas que para LYWS; variando únicamente los parámetros requeridos para cada operación.

Ésto brinda un nivel de libertad adicional al momento de desarrollar un cliente para los servicios de Libertya en función de la tecnología que se utilice.

LYWSE es también parte del proyecto LYWS.
