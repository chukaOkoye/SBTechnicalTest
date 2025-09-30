# Student Beans Android Task

Homework exercise for candidates.

## Instructions

Please work towards building a simple app with the following acceptance criteria. 
Please complete as many as you can, and feel free to add anything that will help you demonstrate advanced knowledge.

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
