# API Inditex

Esta API proporciona un servicio REST para consultar precios de productos en una base de datos de comercio electrónico. 
La tabla de precios (PRICES) contiene información sobre el precio final y la tarifa que se aplica a un producto de una 
cadena en un rango de fechas determinado.

### Configuración
```bash
curl --location --request POST 'localhost:8989/prices/priority-prices' \
```
Permite consultar el precio final y la tarifa que se aplica a un producto de una cadena en una fecha determinada.

#### Parámetros:

* brandId (Long): Identificador de la cadena
* productId (Long): Identificador del producto
* applicationDate (LocalDateTime): Fecha en formato yyyy-MM-dd HH.mm.ss

#### Response:

* brandId (Long): Identificador de la cadena
* productId (Long): Identificador del producto
* price_list (Long): Identificador de la tarifa
* start_date (LocalDateTime): Fecha en formato yyyy-MM-ddTHH.mm.ss
* end_date (LocalDateTime): Fecha en formato yyyy-MM-ddTHH.mm.ss
* price (String): Identificador del producto

#### Ejemplo de peticion
```bash
curl --location --request POST 'localhost:8989/prices/priority-prices' \
--header 'Content-Type: application/json' \
--data-raw '{
    "brandId": 1,
    "productId": 35455,
    "applicationDate": "2020-06-16 21:00:00"
}'
```
#### Ejemplo de respuesta

```bash
{
    "product_id": 35455,
    "brand_id": 1,
    "price_list": 4,
    "start_date": "2020-06-15T16:00:00",
    "end_date": "2020-12-31T23:59:59",
    "price": "38.95 EUR"
}
```
## Base de datos

La tabla PRICES contiene los siguientes campos:

* BRAND_ID (int): foreign key de la cadena del grupo (1 = ZARA)
* START_DATE (timestamp): fecha y hora de inicio de la tarifa
* END_DATE (timestamp): fecha y hora de fin de la tarifa
* PRICE_LIST (int): identificador de la tarifa de precios aplicable
* PRODUCT_ID (int): identificador código de producto
* PRIORITY (int): desambiguador de aplicación de precios
* PRICE (decimal): precio final de venta
* CURR (varchar): iso de la moneda

## Notas adicionales

* Si dos tarifas coinciden en un rango de fechas, se aplicará la de mayor prioridad (mayor valor numérico en el campo PRIORITY).
