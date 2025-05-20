# MyNotes App
An app that allows users to create notes. There is 1 main screen:
- The home screen (showing list of notes)

Users can perform the following tasks:
- Insert Note
- Update Note
- Delete note

# Project Technology
- MVVM
- Kotlin
- Dagger Hilt DI
- Flow (Coroutines)
- Jetpack Compose
- Room DB

# Architecture Design
The Project has followed Clean Code architecture. Project split into these components, 
- Data contains Room DB logic and repository implementation that interacts with Room DB
- Domain contains business logic. Repository Contract and Usecases that perform specific tasks.
- Presentation This contains the viewmodel and composables
  
This architecture was chosen for:
- Separation of Concerns. This in turns allows app to be robust, scalable, maintainable and testable.
- Resilience to configuration changes allows the ViewModel classes to store UI data that would otherwise be lost on screen rotation or activity lifecycle changes.

Viewmodel communicates with usecases which in turn call the repository layer to get cached data using coroutines. Repository abstracts away the datasources, in this case its the Room DB. Viewmodel then updates the state which is being monitored for changes by UI. Any changes cause recomposition of the UI. 

# Test Cases
- TBC

# Libraries Used
- Coroutines to perform data related tasks in the background and so not affecting the main thread.
- AndroidX to provide Lifecycle functionality to the app
- Dagger Hilt Dependency Injection
- Jetpack compose for UI.

# Further Improvements
- TBC
