Feature: Delete Attachment from Campaigns

Scenario Outline: S2053 DeleteDocument

Given Click toggle menu button from the left corner
And Click View All and trigger App Launcher
And Click Sales link
And Click Campaigns tab
And Click CampaignName link <cname>	
And Click Attachments View All
And Click Attachments Action dropdown
And Click Delete Action
When Click Confirm Delete
Then Campaign_Verify Attachment deletion <vfname>

Examples:
|cname|vfname|
|Bootcamp_Vishnu|AutoFileUploadTest|
