# Memory Game

## Introduction
**Memory Game** is a fun and interactive Android app designed to challenge and improve your memory. The goal is to match pairs of tiles by flipping them. As players successfully complete levels, the game dynamically increases difficulty by introducing more tiles—starting with 6, then progressing to 10, 14, 18, 24 etc. The app features engaging animations, a scoreboard, personalized user input, and a visually appealing interface.

##  Sample Screenshots
### Home Screen 
![image](https://github.com/user-attachments/assets/2437b9e5-f0e2-4cf1-9c44-293f95f9690b)

### Gameplay
![image](https://github.com/user-attachments/assets/c1b1ad7a-9a51-440b-b00e-c61c81596d5c)

##  Features
- Flip-to-match tile memory gameplay
- Dynamic difficulty progression (6 -> 10 -> 14 -> 18, 24 tiles)
- Shaking and floating animations for images and tiles
- Smooth tile-flipping mechanics using GridLayout
- Scoreboard to track performance
- Player name input prompt before game start
- Themed background and intuitive UI design
- Back-to-menu and exit functionality
- Fully responsive across screen sizes

##  Built With
- [Java](https://www.oracle.com/java/technologies/javase-downloads.html) – App logic and interactivity
- [Android SDK](https://developer.android.com/studio) – Platform support and APIs
- [XML](https://developer.android.com/guide/topics/ui/declaring-layout) – Layout and UI design
- [Drawable Resources](https://developer.android.com/guide/topics/resources/drawable-resource) – Custom vector icons and shapes
- [View Animations](https://developer.android.com/reference/android/view/animation/TranslateAnimation) – Shaking, floating, and movement animations
- [SharedPreferences](https://developer.android.com/training/data-storage/shared-preferences) – Saving score data locally

##  App Flow Overview

### Home Screen
- Welcome message
- Floating image animation
- Buttons for:
    - Start Game (prompts for player name)
    - View Scoreboard
    - Exit App

### Game Screen
- Grid of tiles based on current level
- Tap to flip and match tiles
- Score and attempts tracking
- Auto-progression to next level upon completion
- Back to Menu button revealed after win

### Scoreboard
- Displays names and scores from previous sessions
- Styled to match game theme
- Includes return navigation

##  Gameplay Logic
- Each tile pair is randomly assigned
- Matching logic includes delay before flipping back mismatched tiles
- Level increases once all matches are found
- Number of tiles adjusts automatically without switching screens
- Game ends or resets after final level is completed

##  Future Enhancements
- Add sound effects for tile actions and wins
- Implement image-based tile matching
- Add multiplayer mode or timed challenge mode
- Store scores in SQLite or Firebase for persistence

##  Author

**Pavlo Sernetskyi**
- [GitHub](https://github.com/PavloSernetskyi)
- [LinkedIn](https://www.linkedin.com/in/pavlo-sernetskyi)

---
 This project showcases skills in Android development with Java, user interface design, animation, and app lifecycle management.
