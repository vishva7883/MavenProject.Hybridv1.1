Feature: Campaigns

#Background: SF login with URL as <url> UserName as <uname> Password as <pwd>

Scenario Outline: S2051 Create Campaign

Given Click toggle menu button from the left corner
And Click View All and trigger App Launcher
And Click Sales link
And Click Campaigns tab
And Click New button
And Enter Campaign name as <cname> Get the text and Store it
And Choose Start date as Tomorrow <startdate>
And Choose End date as Tomorrow plus one <enddate>
When click Save 
Then Campaign_Verify the newly created Campaign <vcname> 

Examples:
|cname|startdate|enddate|vcname|
|Bootcamp_Vishnu|1|2|Bootcamp_Vishnu|
