Feature: Campaigns

Scenario Outline: S2055 Delete Campaign

Given Click toggle menu button from the left corner
And Click View All and trigger App Launcher
And Click Sales link
And Click Campaigns tab
And Search Campaign as <cname>
And Click Campaign Action dropdown
And Click Delete Action
When Click Confirm Delete 
Then Campaign_Verify Campaign deletion <vcname> 

Examples:
|cname|startdate|enddate|vcname|
|Bootcamp_Vishnu|1|2|Bootcamp_Vishnu|
