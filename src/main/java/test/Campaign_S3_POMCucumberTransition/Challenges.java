package test.Campaign_S3_POMCucumberTransition;

/*

1. Select list having no Select property 
**************************
Issue 			:: Select dropdown in the UI is not having Select property
Challenge	 	:: Unable to choose desired value from the dropdown.
Solution 		:: Figured, Dropdown and List of Values are seperare webelement.
Implementation 	:: Capture the xpath of both the elements seperately. Click on the dropdown file. Move to Element on List of Values using action class and click.

2. Scroll with in Table 
**************************
Issue 			:: If the table of list of records, which will list only initial no of rows during page landing, the list refreshes upon scrolling the table.
Challenge	 	:: Table scroll is not accessible
Solution 		:: executor.executeScript("arguments[0].scrollIntoView();", <<WebElement>>);
Implementation 	:: Get the webelement (driver.FindElements) of each row and use java script executor to scroll in to view.
This will refresh the table upon hitting the last visible record in the table, that would refresh the table to pull up additional record.

3. Date Sort 
**************************
Issue 			:: Format of date displayed in the UI and format of the date stored in List <Date>.
Challenge	 	:: Comparison fails
Solution 		:: Date date = srcDf.parse(dateStr); 
Implementation 	:: Convert the UI date in to proper date format and store it in list. Use if and .equals to compare


4. stale element reference: 
**************************
Issue 			:: Exception in thread "main" org.openqa.selenium.StaleElementReferenceException: stale element reference: element is not attached to the page document
Challenge	 	:: Unable to click page headers when record delete option is performed in table.
Solution 		:: Tried Thread.sleep and looping to wait for the element to present in DOM
Implementation 	:: Introduced a loop with try catch to wait for the element to refresh, the loop will keep trying for element to exist in DOM when it faces Stale error


5. Explicit wait - "wait" has to be initialized after Implicit wait. It will throw - NullPointerException. 
**************************
org.openqa.selenium.TimeoutException: Supplied function might have stalled


5. Illegal Argument exception when retrieving data from Excel
**************************


6. Element click Intercepted exception, When using explicit wait, that triggers StaleElementException
***********************
Solution : Wait for all the transactions, webelement to load completely.
Retry for the element available
Use Javascript to Click.

7. Error occurred when doing Parallel run in TestNG.
************************
An error occurred while instantiating class test.Campaign_S3_TestNGPOMTransition.S2051CreateCampaign. Check to make sure it can be instantiated
















































*/