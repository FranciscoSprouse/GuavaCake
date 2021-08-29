# Guava Cake
A small application that will show the suitability score for a set of drivers. 

## Network setup
To better mimic a real world application I am not reading the JSON input from a file.
Instead the class MockInterceptor is intercepting a Retrofit API call and returning the mock data. 

## Activity-Fragment pattern

This app uses the Single Activity pattern.
Deep linking should be used to navigate to a specific page.
This app uses the MVVM pattern. 

