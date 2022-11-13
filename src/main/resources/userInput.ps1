#!/bin/bash
echo "1 - Sell Dining Table"
echo "2 - Sell Dining Chair"
echo "3 - See all available products"

$input = Read-Host "What would you like to do?"

switch ($input)
{
"1"

{curl -X PUT http://localhost:8080/rest/product/Dining%20Table -H ': ' -H 'Content-Type: application/json' -H 'Postman-Token: 00afb66f-01da-42eb-b4fe-2a22bf06c266' -H 'cache-control: no-cache'}

"2"

{ curl -X PUT http://localhost:8080/rest/product/Dining%20Chair -H ': ' -H 'Content-Type: application/json' -H 'Postman-Token: 00afb66f-01da-42eb-b4fe-2a22bf06c266' -H 'cache-control: no-cache'}

"3"

{ curl -X GET http://localhost:8080/rest/product -H 'Postman-Token: e7c044aa-1ad8-4749-bf03-c21fb89513b0' -H 'cache-control: no-cache'}

default

{ "Option not available." }

}