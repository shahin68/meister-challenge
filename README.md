# meister-challenge
a kotlin programming challenge (only for demonstration purposes) 

### CI/CD
Workflow runs unit tests on each push to main branch
Main development takes place in develop branch

**Test reports will be uploaded as artifacts at the end of each pipeline and can be reviewd under pipeline summary**

#### Features:
* paging3
* room
* coroutines
* livedata
* navigation ui
* data-binding
* retrofit
* koin

#### Screenshots:
<img src="screenshots/Meister Search 1.jpg" width="200" /> <img src="screenshots/Meister Search 2.jpg" width="200" /> <img src="screenshots/Meister Search 3.jpg" width="200" />

#### Short Description:
App consists of a single home page (HomeFragment) which contains a search-view at the top & a recycler-view as content below the search bar.
Each time user inputs a keyword, a network call takes place gathering data from server.
Network call takes place on every letter typed after 400ms of inactivity on the search input view & an empty search-view disregards network call.
Each time server returns a result, sequences of functions call to inflate the Internal Database.
Database tables observed in the home fragment inflate the recycler-view with newly received data each after network call & database inflation is finished.

### Contact
Shahin Montazeri - shahin.montazeri@gmail.com
