# XmlFormatter
This program accept the user input and retreive the data from database to generate
the report in xml format, specifically the year end summary.

The solution uses the several divisions between code segment, 
so the code will be high cohesion (do 1 thing) and loose coupling (dependency of module).

## Files and external data
### assignment_5 (main package for the program)
* UserInputUI.java --- main for the program that prompts the user for startDate, endDate and filename

### assignment_5.database (package for database related program)
* DatabaseConnector.java --- interface to interact with database provider
  * MySqlConnector.java --- connect to mySql database
* MySqlProcessor.java --- interface for extract data from the result set
  * YearEndSummaryProcessor.java --- extract data from the result set, specifically for year end summary
* InformationType.java --- Enumeration for classify the data category in result set
* PropertiesType.java --- Enumeration for classify the type of properties file

### assignment_5.datamodel
* Address.java --- included the attributes for the address
* Customer.java --- included the attributes for the customer
* Employee.java --- included attributes for the employee
* Product.java --- included the attributes for the product
* Summary.java --- based class for Summary report as every report need to specify the period of time
  * YearEndSummary.java --- stores information needed in year end summary

### assignment_5.xml (package for xml related program)
* XmlTransformer.java --- transform data into xml format and send the output to the file
* XmlFormatter.java --- format element into xml format.
  * YearEndSummaryXmlFormatter.java --- specifically format the xml for year end summary.

### external.configuration (package for configuration)
* mySqlDatabase.properties --- mySql database configuration

### external.mySql (package for mySql code)
* mySql_yearEndSummary.properties --- mySql query for year end summary

### output
all the xml file will be generated into the output folder

## Data structures and their relations to each other
When connect to database, the program extract the properties file for configuration and mySql query.
Using interface to help standardize the program. 
	e.g. DatabaseConnector which force its child to implement the specific method

Use inheritance to reduce code duplication and reusability
	e.g. Summary need period of time -> startDate, endDate. 
	So every child class (YearEndSummary) will have this attribute from parent class (Summary).

In year end summary data model, there are 3 main components that need to be extract from database.
* customers data --- use Set -> unique (no duplicate)
		address attribute is in the customer class (help in mobility)
* products data --- use Map -> to group product in the productLine and list Set of product
* employees data  --- use Set -> unique (no duplicate)

When the program process data from the database, after getting the result set, 
the program will assign the data in to List to maintain order of column.
This helps to populate the data into the right data type and create the object.

Use data type that suitable for the variable :
* LocalDate for date (automatically check if date is valid), 
* number that could do calculation, use integer
* money, use double
* String for text or data that don't need calculation (e.g. postal code)

XmlFormatter have methods to create the tag which could be used in every child class.
YearEndSummaryXmlFormatter determine the format for year end summary and populate data into xml format.

## Assumptions
* all file are in the correct directory (external configuration, external sql)
* if user enter startDate after endDate, there will be no output (can't extract data from database). 
* the properties file is configured correctly
* seacrh will be based on orderDate in orders table in database

## Choices
* use Scanner to ensure that user provide the input and ignore whitespace
* store username and password in the properties file which is separated from the program
(for security reason and the code to be more mobile and flexible)
* store mySql code in the properties file which is separated from the program
(for the code to be more mobile and flexible)
* if the file already exists, it will overwrite the existing file.

## Key algorithms and design elements
The program accept user input. It will continue prompt user for valid date in correct format 
until the user input the correct date. Then, it will ask for the filename.

The program extract the data from properties file for configuration and mySql code.
* separate mySQL code from Java code --> mobility, easier to fix

Then, it connect to database and extract the year end summary from database.
* preparedStatement --> protect against SQL injection attack
The data is populated into the data structure and transform into the xml format.
The xml format is wriiten to the xml file and save into the output folder.
* use overloading in create tag in XmlFormatter to create the cleanliness in code
	as both methods have the same tasks (not neccessary to create the new method)

Design Elements in general:
* use constructor to set value and getter to get the value
* never let the object escape in getter, 
	populate the data into new data structure to protect data from being changed from outsider
* use encapsulation for data hiding to protect the data from the outsider
	e.g. datamodel --- Address.java, Customer.java, etc.
* use switch for flexibility in adding more case
	e.g. XmlFormatter --- use Level to determine the level in nested tag
* use interface which make change more flexibility

## Limitations
* all output file will be in the output folder
* all configuration file will be in the external.configuration
* all mySql code file will be in the external.mySql

## Noted
* The solution seperates the package into several sub-packages to help organize to code.
* Use the data type that suitable for the data. Make it easier to check and process if needed.
	e.g. LocalDate --- can check date whether the use input is valid
* Use external file to do the configuration --- more secure and easier to change
* The sql code and java code is seperated which make it easier for developer to work seperately.
* The interface has 1 responsibility which is specific and make it easy to deploy by other class.
* The inheritance help to reduce code duplication.
	e.g. Summary has startDate and endDate which is the attribute that every report needs to have.
	The child class could extends Summary to get access to thos attributes.
