# Student Beans Android Task

Homework exercise for candidates.

## Instructions

Please work towards building a simple app with the following acceptance criteria. 
Please complete as many as you can, and feel free to add anything that will help you demonstrate advanced knowledge.
Make sure you clone the repository and send us a compressed package (zip) of your solution.

### Core Focus Areas:

UI: Use Jetpack Compose exclusively for the entire user interface.

Architecture: Implement the MVVM (Model-View-ViewModel) pattern.

Best Practices: Focus on Clean Architecture principles (e.g., clear separation of concerns, use of Usecases/Interactors is encouraged).

Modern Libraries: Leverage the latest stable AndroidX and Jetpack libraries.

### Technical Requirements & Screens

1. Login Screen

Design: Implement a clean, responsive login screen using Compose.

Validation:

On tapping "Log in", validate that both fields (Username/Email and Password) are not empty.

Show inline, user-friendly error messages below the input fields if validation fails.

Navigation: Upon successful validation, navigate to the Photos Screen.

2. Photos Screen

API Source: Display data from https://jsonplaceholder.typicode.com/photos

List Implementation: Use a Lazy Column or Lazy Vertical Grid in Compose to display the data efficiently.

Item Display: Each item must clearly show:

- The image from the thumbnailUrl. Use a dedicated Compose image loading library (like Coil or Glide/Accompanist) for this.

- The title.

Filtering/Search 🔍

Add a search bar at the top of the screen.

The list must dynamically filter in real-time as the user types, matching against the title field. Bonus points for debounce implementation to optimize API or list filtering calls.


State Handling & Loading 🔄

Implement and visually show Loading (e.g., a CircularProgressIndicator) while the API request is in flight.

Implement robust Error Handling (e.g., a Snackbar or a persistent error message with a "Retry" button) for network or API errors.


Show us your best code!

## Designs

<img src="Login.png" width="400"> <img src="Photos.png" width="400">


## Candidate Notes

(Add your notes here: approach, assumptions, trade-offs, anything you’d like us to know.)

## How to run
- Have all dependencies synced in the `build.gradle.kts` file.
- Click RUN

## Tech used
- Kotlin/Jetpack Compose
- MVVM
- Glide
- Compose Navigation
- Coroutines

## My Approach
- I took time to understand the data being pulled from the api to determine what and how to display the various items, like the thumbnail and title of the photos, along with how to make the navigation between the login page and the photos page run smoothly and to use the MVVM design pattern throughout the application. 
- **Model** - Contains data models and handles data operations through a Repository abstraction and UseCase, kept the same due to the correct data models being in place already.
- **ViewModel** - LoginViewModel manages login UI state to determine the navigation graph, which navigates depending on the state. PhotosViewModel contains the logic for fetching the list of photos via the data model, along with mapping of the data model to the UI model for further extraction.
- **View (UI)** - LoginScreen has the login layout that checks that the text fields are not empty and displays error warnings. Once successfully verified that the fields are not empty, the login button directs the app to the PhotosScreen, which has the loading, error and success state layouts for the photos list. It seems that the thumbnail URL doesn't work from the feed, as I tested with a manual image URL link shown in the screenshot below, so I added a placeholder image to display on failure. A Search bar is at the top of the PhotosScreen for filtering by photo title and a topbar for navigation to go back to the LoginScreen, and resets the values once loaded to re-login.
  
<img width="459" height="865" alt="Screenshot 2025-10-10 at 17 39 17" src="https://github.com/user-attachments/assets/feb45682-8004-498e-a9ac-7075fb18a8e8" />

***

#### Screenshot of Thumbnail URL's not showing
<img width="795" height="448" alt="Screenshot 2025-10-10 at 17 34 58" src="https://github.com/user-attachments/assets/04a9ff55-4077-4765-b9e7-a03be25ccf0b" />


## Future implementations
- If I had more time, I would have added viewModel and repository tests, along with the debounce functionality for the search bar, for more efficient searching.
- I would also add pagination to handle the long list of items when loaded.
