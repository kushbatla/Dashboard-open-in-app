# Dashboard
A sample dasboard app of the OpenInApp which fetches and then displays the data in the respective UI components. 

## Built using - 
- Kotlin Programming Language
- opneinApp API
- Glide Image Library
- Retrofit Library
- [Material Design](https://m2.material.io/components/app-bars-bottom/android#anatomy-and-key-properties) 
- [Linechart Library](https://github.com/diogobernardino/williamchart)
- Android Studio

## Features & Working of the app - 
- The App uses MVVM architecure & coroutines to asynchronously call the API. 
- Then on the MainActivity/Dashboard-Screen the UI of the dashboard is loaded and the API calling happens which inserts the data into their respective components. The greeting on the dashboard is shown by being consistent with the time of the day. 
- LineChart UI Component 
> For this used an external library which shows the LineChart on the X&Y axis. It accepts a Pair so had to convert the response from the API in a Pair format of date -> value. Added a few color sceheme like line color and gradient to make the UI look good.
- Analystics UI Component 
> Inserted different cardviews on a horizontal scrollview. Each cardview binds data to its respective components after the data is fetched from API. 
- TabLayout & ViewPager 
> Custom TabLayout which displays different fragments using the ViewPager, mainly "TopLinks" and "RecentLinks" 
- TopLinks Fragment 
> - This is the default fragment which fetches data from the API in the form of List and then displays each data item in the recyclerView. 
> - Image is also loaded using the URL provided 
> - There is a copy button which copy's the weblink to the clipboard. 
- RecentLinks Fragment 
> - This is the second fragment which again fetches data from the API in the form of List and then displays each dat item in the recyclerview. 
> - Image is also loaded using the URL provided 
- Talk to us ( Whatsapp Button ) 
> A button is there which redirects the user to open a chat witch a particular number on the main whatsapp application. 
> This particular whatsapp number is also fetched through the API. 
- FAQ button 
- Bottom Navigation Bar 
> Used the BottomNavBar component of Android Material Design to display a Bottom Navbar with a Floating Icon on it. 


## Concepts used - 
- **MVVM Architecture** : Followed clean architecture and MVVM design pattern. Followed the respositoy pattern where API calls happen through repostiory and it becomes the single source of truth for the app. The ViewModels can access the repostiory and then provide the Livedata to the activities and fragments to observe.
- **Coroutines** : Used coroutines to asynchronously call the API in background. Also used coroutines while displaying and observing the Livedata. 
- **Glide Image Library** : Used the famous Glide Library to parse the url of the images that are getting fetched from the API and then display it.
- **Retrofit Library** : Used the Retrofit library which is type-safe HTTP client for Android for interacting with API.
- **LineChart Library**: Used the WilliamChart Library to rapidly develop attractive and insightful linechart in application.
- **ViewPager and Adapter** : USed the concept of VIewPager and ViewPager Adapter to show the split of 'Top Links' and 'Recent Links'.





