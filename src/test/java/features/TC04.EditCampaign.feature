Feature: Campaigns

Scenario Outline: S2054 Edit Campaign

Given Click toggle menu button from the left corner
And Click View All and trigger App Launcher
And Click Sales link
And Click Campaigns tab
And Click CampaignName link <cname>	
And Click Details tab
And Edit EndDate add <noofdays> days
And Edit Revenue as <Revenue>
And Edit Budget as <Budget>
When Click EditSave		
Then Campaign_Verify Campaign changes <expRevenue> and <expBudget>

Examples:
|cname|noofdays|Revenue|Budget|expRevenue|expBudget|
|Bootcamp_Vishnu|2|1000000|100000|$1,000,000|$100,000|

