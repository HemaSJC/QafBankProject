Feature: TestDemo

@Demo_001_CHROME
				Scenario Outline: [RTP-2605]_[Demo_001]_Testcase_Demo
Given get "http://demo.rapidtestpro.com/admin/login.php"
When sendKeys "<adminusername>" into "admin.uname"
And sendKeys "<adminpassword>" into "admin.pwd"
And click on "admin.btn"
Then verify "adminhomepage.label" is present
And click on "adminlogout.link"

				Examples: {'datafile' : 'data/Demo_001_CHROME.json'}


