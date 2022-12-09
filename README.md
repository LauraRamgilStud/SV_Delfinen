# SV_Delfinen
DAT22-Projekt 2: Svømmeklubben SCDolphin.

This project is created to ease and simplify the membership management of the imaginary swimming club "Svømmeklubben Delfin".

We created an “Employee” - class, which interacts as a “parent class” to the three types of employees; 
Chairman, Cashier and Coach. We decided to design each employee's menu and thereby their shown interface into their own classes to ensure that they only get shown the - for them - relevant options. In order to simplify the process for the user, we created an option to log in with their “ID”. Since we created one coach for each of the four chosen disciplines, their personal IDs will determine their personal user-interface. The mentioned interface is thereby linked directly to the two separate student lists for each coach end thereby easily accessible. We created other classes, such as “Accounting” and “Database” in order to create a space for their concerning methods. This way we could keep the code as clean as possible, since the classes of our actors solely include their relevent menues. Additionally, we created sepreate classes for the training and event-management for the former mentioned reasoning.

The ID's for the employees are the following:

Chairman: 122
Cashier: 222
Coach - CRAWL: 334
Coach - BACKCRAWL: 335
Coach - BUTTERFLY: 336
Coach - BREASTSTROKE: 337
LogOut - 0000 (will result in termination of the program)
