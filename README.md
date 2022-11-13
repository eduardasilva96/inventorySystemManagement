# inventorySystemManagement

## used:

java 11 maven 3.6.3

## Spring boot microservices

For this assignment i fully based myself in the spring boot framework's microservice architecture. 
Main reasons were:

- code more readable
- smaller pieces of code and independent of each other
- easier to maintain and test

# build and package the application as usual:

mvn clean install

## in the target folder directory:

add the json files and the userInput.ps1 script; open a cmd in the directory -> java -jar warehouse.jar

# To test:

open a powershell window -> in the same path Run the script userInput.ps1 and answer according to what u want to do; You
can : Sell a dining table/chair and check all available products.


Notes:
1. Ideally I would have this working with a functional database. As it is now, 
if you want to load new products you need to restart the application and edit the json files.
So if you sell 2 dining tables, they run out of stock, and you cannot add more 
tables (which, in the real world, the user should be ale to).
2. The userInput script is just a "better looking" way to run the commands, 
I would have liked to implement a basic frontend/java frame solution for the user interface as well.
