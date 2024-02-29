# Weather App

## Overview
Weather Now & Later is an Android application designed to provide users with the current weather and a 7-day forecast for a given city. The app is built using modularized architecture, adhering to the MVVM pattern, Clean Architecture principles, and incorporating various technical requirements for code quality and testing.

## Functional Requirements
1. **City Input:**
   - Allow users to input a city name.

2. **Current Weather Display:**
   - Display the current weather for the entered city.
   - Include temperature, weather condition (e.g., cloudy, sunny, rainy), and an appropriate icon.

3. **7-Day Forecast:**
   - Display a 7-day forecast in a list with details similar to the current weather.

4. **Last Searched City:**
   - Store the last searched city name.
   - Display its weather data when the app is reopened.

## Technical Requirements

### Architecture
1. **MVVM:**
   - Implement the MVVM architectural pattern.

2. **Clean Architecture:**
   - Structure code layers into:
     - Presentation
     - Use cases
     - Repository
     - Data source (local for storing the last searched city and remote for fetching weather data).

### Testing
3. **Unit Testing:**
   - Cover at least 80% of the code with unit tests.
   - Use a mocking framework of your choice.

### Code Quality
4. **Clean Code & SOLID Principles:**
   - Ensure code is readable and maintainable.
   - Follow SOLID principles to showcase good OOP practices.

5. **Dependency Injection:**
   - Utilize Dagger-Hilt for all dependency injection needs.

### Modularity
6. **Modularization:**
   - App (main module)
   - Core (common utilities and shared components)
   - Data (data sources and repository)
   - Features (sub-modules for each feature/screen, e.g., city input, current weather, 7-day forecast).

## Bonus Features
- **Jetpack Compose:**
  - Use Jetpack Compose for the UI.

- **Instrumented Tests:**
  - Integrate instrumented tests.

- **Dark Mode Support:**
  - Add dark mode support.
