[![](https://jitpack.io/v/RohitSurwase/API-Calling-Flow.svg)](https://jitpack.io/#RohitSurwase/API-Calling-Flow)

# API Calling Flow

API Calling Flow is a Android library which can help you to simplify handling different conditions while calling an API (Web Service) in Android.

## Features

* Internal Network State Checking.
* On screen Setting's shortcuts to enable Wi-FI or Mobile Data.
* Hide unloaded screen during API call and show progress bar with white or transparent background.
* "Try Again" same API after enabling Network connection.
* Call API only if the Network connection is enabled.
* Remove progress bar after successfully loading data on screen.
* Show full-screen errors to users while hiding unloaded screen from user for better user experience.

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

	//TODO Step 1: Get reference to root layout of Activity or Fragment.
    //Root layout can be any ViewGroup

		RelativeLayout parentLayout = (RelativeLayout) findViewById(R.id.rootLayout);

	/*
	 * TODO Step 2: Crete Object of ApiCallingFlow and implement methods.
	 * 1st parameter - context
	 * 2nd parameter - parentLayout from step 1
	 * 3rd parameter - isTransparent (if you want background color to be transparent then 'true'
	 * else 'false' for default white background)
	 */

		ApiCallingFlow apiCallingFlow = new ApiCallingFlow(this, parentLayout, false) {
			@Override
			public void callCurrentApiHere() {
			
				//TODO Step 3: Pass function to call current API
				requestCurrentApi();
			}
		};


	//TODO Step 4: Get current Network state using ( apiCallingFlow.getNetworkState() ) 
	//and request API accordingly.

	//TODO Step 5: Call ( apiCallingFlow.onSuccessResponse(); ) after API is successful

	//TODO Step 6: Call ( apiCallingFlow.onErrorResponse(); ) after API is failed



## License

Copyright {2017} {Rohit Surwase}

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
