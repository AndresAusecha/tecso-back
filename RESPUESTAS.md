# Solución prueba técnica Tecso

##Urls para cuenta

### Crear cuenta:

curl -X POST \
  http://localhost:8080/api/cuentas \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: fe8f7b82-932c-4210-8aab-55fb9e84dcce' \
  -H 'cache-control: no-cache' \
  -d '{"moneda":"PESO", "saldo":"5000.00","titularPersonaFisicaId":"c1388c3d-39b4-48bb-a669-2461a260c833"}' 

Nota: Modificar titularPersonaFisicaId por un id de una persona fisica insertada previamente.

### Listar cuentas

curl -X GET \
  http://localhost:8080/api/cuentas-rest-repo \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: bcad47e3-7f11-4d01-994e-ec307b23ea5c' \
  -H 'cache-control: no-cache' 

### Leer una cuenta específica

curl -X GET \
  http://localhost:8080/api/cuentas-rest-repo/c52ce3a4-afd0-4dbb-8369-99dbb9b5ff7b \
  -H 'Postman-Token: 3daaad6c-68af-4599-bc2a-3abbbdcab651' \
  -H 'cache-control: no-cache'

Nota: reemplazar el id de cuentas-rest-repo/id por uno creado.

### Actualizar cuenta

Sólo se permite actualizar moneda y saldo

curl -X PUT \
  http://localhost:8080/api/cuentas/a92b46db-e2a5-41c5-9a4e-c00358291684 \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 26299ee0-1629-4a3d-a915-0d39ef024517' \
  -H 'cache-control: no-cache' \
  -d '{"moneda":"PESO", "saldo":1210.00}'
  
  Nota: reemplazar por otros datos si se desea
  
### Eliminar cuenta

curl -X DELETE \
  http://localhost:8080/api/cuentas/a92b46db-e2a5-41c5-9a4e-c00358291684 \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 2fc24d86-3644-4859-b3c3-fa14c91c9f5b' \
  -H 'cache-control: no-cache'
  
  Nota: reemplazar id de cuenta por una existente
  
##Urls para movimiento

### Crear movimiento

curl -X POST \
  http://localhost:8080/api/movimientos \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 87c6721d-61df-48e3-bad3-12c08f866f20' \
  -H 'cache-control: no-cache' \
  -d '{
	"tipoMovimiento": "DEBITO",
	"descripcion": "mov",
	"importe": "100.00",
	"cuentaId": "dd72a8ae-5d5f-45bd-b6f4-75b431f36436"
}'

### Listar movimientos

curl -X GET \
  http://localhost:8080/api/movimientos-rest-repo \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: f2308fba-5f3a-4eac-b7a8-b842b87aefed' \
  -H 'cache-control: no-cache'

### listar movimientos por cuenta

curl -X GET \
  http://localhost:8080/api/movimientos/listaXCuenta \
  -H 'Postman-Token: ac705de9-67c8-4055-8658-5b0566516718' \
  -H 'cache-control: no-cache'
  
## Notas:

* No fue necesaria la landing page ya que se hizo una 
funcionalidad de navegación, con menu y botones en varios puntos
los cuales permiten pasar por crear, leer, editar y eliminar 
personas fisicas y juridicas.

* De los bonus se hicieron las pruebas automatizadas,
 la navegación en el front y la conexión a postgre db.
 Las pruebas se ejecutan con la base de datos en H2, para agilizar
 su ejecución.
 
* La aplicación se corre manualmente en MainApplication.

* Para esta aplicación se hizo uso de Spring data rest, la cual es una dependencia que permite
utilizar un repositorio llamado CRUDRepository, el cual genera un punto de conexión y es
 capaz de realizar estas 4 operaciones básicas además genera unas respuestas
standard de json, es por eso que no hay controllers o services para los titulares.

  
### Conexión a la base de datos PostgreSQL
Reemplazar datasource.username y datasource.password por los datos
de la base de datos local.

Debe existir una base de datos llamada postgres, si no esta se puede usar
CREATE DATABASE postgres; pasada esta parte ejecutar el siguiente query
CREATE SCHEMA tecso; con lo cual ya debería poder correrse la aplicación principal correctamente
ya que las tablas son creadas automáticamente por Hibernate.

  