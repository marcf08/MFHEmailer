# MFHEmailer
MFHEmailer is an email program for a local restaurant. The eventual goal involves hosting this application with Tomcat
or Apache, but that's a month or two (or three) off right now. At this point, the primary goal involves establishing core
functionality. So I apologize in advance for the spaghetti code. Some refactoring is definitely in order.

The program uses a single administrative user (such as a restuarant manager) who would add the hand-collected patron data
following a shift. The user can manually add the patrons via the New Patron button or via a bulk import (pictured below).
The program currently accepts a tab-delimited text file. For the moment, it does not check the file for errors. From that point,
the program will (eventually) automate the sending of mass emails on special events (patron birthdays, anniversaries). In
addition, it can be used as a promotional/mass emailer. 

The program uses SendGrid's API which has allowed for a seamless integration. The program uses their servers to email users.

![alt tag](https://github.com/marcf08/MFHEmailer/blob/master/EmailProgram/resources/MainGUI.png?raw=true)
![alt tag](https://github.com/marcf08/MFHEmailer/blob/master/EmailProgram/resources/ImportGUI.png?raw=true)
