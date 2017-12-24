[![](https://jitpack.io/v/RohitSurwase/API-Calling-Flow.svg)](https://jitpack.io/#RohitSurwase/API-Calling-Flow)
[//]: #[![](https://jitpack.io/v/RohitSurwase/API-Calling-Flow/month.svg)](https://jitpack.io/#jitpack/RohitSurwase/API-Calling-Flow)
[//]: #[![](https://jitpack.io/v/RohitSurwase/API-Calling-Flow/week.svg)](https://jitpack.io/#RohitSurwase/API-Calling-Flow)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-API%20Calling%20Flow-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/6192)
[![Project Status: Active – The project has reached a stable, usable state and is being actively developed.](http://www.repostatus.org/badges/latest/active.svg)](http://www.repostatus.org/#active)

[![GitHub stars](https://img.shields.io/github/stars/RohitSurwase/API-Calling-Flow.svg?style=social&label=Star)](https://GitHub.com/RohitSurwase/API-Calling-Flow/stargazers)

# API Calling Flow

API Calling Flow is a Android library which can help you to simplify handling different conditions while calling an API (Web Service) in Android.

Download example App [here](https://github.com/RohitSurwase/API-Calling-Flow/raw/master/api-calling-flow-example.apk) or checkout [example](https://github.com/RohitSurwase/API-Calling-Flow/tree/master/example-app).

## Features

* Internal Network State Checking.
* On screen Setting's shortcuts to enable Wi-FI or Mobile Data.
* Hide unloaded screen during API call and show progress bar with white or transparent background.
* "Try Again" same API after enabling Network connection.
* Call API only if the Network connection is enabled.
* Remove progress bar after successfully loading data on screen.
* Show full-screen errors to users and hide unloaded screen from user for better user experience.

## Setup
In your Project's build.gradle file:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
			...
		}
	}

In your Application's or Module's build.gradle file:


	dependencies {
  	  ...
      compile 'com.github.RohitSurwase.API-Calling-Flow:api-calling-flow:1.0'
      ...
	}

## Screenshots
Case 1. when Network connection is not available-

| <img src="https://github.com/RohitSurwase/API-Calling-Flow/raw/master/screenshots/Dummy_1_Disconnected.png" alt="Network Connection is not available"   width="200" height="350" title="Network Connection is not available" />  | <img src="https://github.com/RohitSurwase/API-Calling-Flow/raw/master/screenshots/Disconnected.png" alt="Setting's shortcut to enable Network Connection"   width="200" height="350" title="Setting's shortcut to enable Network Connection" />  |
|:---:|:---:|
| Network Connection is not available | Setting's shortcut to enable Network Connection |

Case 2. When Network connection is available and API is successful-

| <img src="https://github.com/RohitSurwase/API-Calling-Flow/raw/master/screenshots/Dummy_1_Connected.png" alt="Network Connection is available"   width="200" height="350" title="Network Connection is available" />  | <img src="https://github.com/RohitSurwase/API-Calling-Flow/raw/master/screenshots/Loading_1.png" alt="API requested"   width="200" height="350" title="API requested" /> | <img src="https://github.com/RohitSurwase/API-Calling-Flow/raw/master/screenshots/Success.png" alt="API successful, data loaded"   width="200" height="350" title="API successful, data loaded" /> |
|:---:|:---:|:---:|
| Network Connection is available | API requested | API successful, data loaded |


Case 3. When Network connection is available but API is failed-

| <img src="https://github.com/RohitSurwase/API-Calling-Flow/raw/master/screenshots/Dummy_1_Connected.png" alt="Network Connection is available"   width="200" height="350" title="Network Connection is available" />  | <img src="https://github.com/RohitSurwase/API-Calling-Flow/raw/master/screenshots/Loading_2.png" alt="API requested"   width="200" height="350" title="API requested" /> | <img src="https://github.com/RohitSurwase/API-Calling-Flow/raw/master/screenshots/Error.png" alt="API failed, error shown"   width="200" height="350" title="API failed, error shown" /> |
|:---:|:---:|:---:|
| Network Connection is available | API requested | API failed, error shown |


## Code Example

Refer to the [example](https://github.com/RohitSurwase/API-Calling-Flow/tree/master/example-app) for complete implementation and usage. 

* Step 1: Get reference to root layout of Activity or Fragment. Root layout can be any ViewGroup

Example-

	RelativeLayout parentLayout = (RelativeLayout) findViewById(R.id.rootLayout);
    

* Step 2: Crete Object of ApiCallingFlow and implement methods.
	 * 1st parameter - **context**
	 * 2nd parameter - **parentLayout** from step 1
	 * 3rd parameter - **true** for transparent background, **false** for default white background
* Step 3: Pass function to call current API on click of try again.
	 
		private void requestTestApi() {
  		...
      
		ApiCallingFlow apiCallingFlow = new ApiCallingFlow(this, parentLayout, false) {
			@Override
			public void callCurrentApiHere() {
				//Step 3: Pass function to call current API
				requestTestApi();
			}
		};
    
		...
		}


* Step 4: Get current Network state using **apiCallingFlow.getNetworkState()** and request API accordingly.
* Step 5: Call **apiCallingFlow.onSuccessResponse()** in API success function.
* Step 6: Call **apiCallingFlow.onErrorResponse()** in API error function.



## License

Copyright 2017 Rohit Surwase

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
limitations under the License.


## Contributing to API Calling Flow Library

Just make pull request. You are in!
