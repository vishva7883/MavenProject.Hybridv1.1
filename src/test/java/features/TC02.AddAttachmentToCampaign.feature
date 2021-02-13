Feature: Campaigns

Scenario Outline: S2052 AttachDocument

Given Click toggle menu button from the left corner
And Click View All and trigger App Launcher
And Click Sales link
And Click Campaigns tab
And Click CampaignName link <cname>	
And Click Upload file <fname> from path <fpath>
When Complete File Upload
Then Campaign_Verify the Attachment upload <vfname>

Examples:
|cname|fname|fpath|vfname|
|Bootcamp_Vishnu|AutoFileUploadTest.pdf|E:\\Test Leaf\\BootCamp\\05.MavenProject.POMCucumberHooks\\DataSource|AutoFileUploadTest|
