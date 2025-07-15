# Hangman

## About

The classic game Hangman offers a hands-on way to explore the MVC design pattern. By separating Word (Model), GUI (View), and game logic (Controller), this project helps put the concept into practice using Java Swing. I'm building it not just to play—but to learn.

## Folder Structure

```
Hangman/
├── bin/                           ← Output: packaged .jar files  
├── build/                         ← Compiled .class files from source  
├── lib/                           ← External libraries manually tracked  
├── manifest/                      ← Jar manifest file for Main-Class and classpath  
│   └── MANIFEST.MF                ← Declares entry point and classpath  
├── scripts/                       ← Cross-platform build tools  
│   ├── compile.cmd                ← Windows: compiles .java files into /build  
│   ├── compile.sh                 ← Unix: compiles .java files into /build  
│   ├── run.cmd                    ← Windows: launches App class with dependencies  
│   ├── run.sh                     ← Unix: launches App class with dependencies  
│   ├── pack.cmd                   ← Windows: creates executable .jar using manifest  
│   └── pack.sh                    ← Unix: creates executable .jar using manifest  
├── src/                           ← Source code directory
│   ├── App.java                   ← Entry point (default package)  
│   └── Hangman/                   ← Project-specific source code
|       ├── Control/               ← Controller specific source code 
│       │   └── GamePlay.java      ← GamePlay is the backend class with the game logic
│       ├── Gui/                   ← View container for layout, controls, and logging
│       │   └── MainFrame.java     ← Main application window with layout and controls
│       ├── Model/                 ← Model specific source code
│       │   └── WordBank.java      ← Data model (runtime database) spiked with words
│       └── Utility/               ← Utility classes useful across the project
│           └── Debug.java         ← Debugging utility for logging and error tracking  
├── LICENSE                        ← GNU General Public License v3.0  
├── README.md                      ← Project overview and usage instructions  
└── .gitignore                     ← Build hygiene and repo clarity
```

## Author

Hello, my name is **Patrik Eigenmann**. I spent nine years as a software engineer working with Java and C# on server-side backend projects. Along the way, I picked up PHP, HTML, and CSS. Later I pivoted to audio—now I work full-time as a Sound Engineer and production manager for large-scale corporate and private live events.

Even now, I still write software in C and Java, especially GUI applications. It’s a passion I never dropped. Programming helps me reflect, learn, and structure my thoughts about how the world works. Most of my projects come from real-world needs.

If you find this project useful, consider donating a few bucks via [PayPal](mailto:p.eigenmann@gmx.net). I appreciate every small contribution—it encourages me to keep developing practical, usable tools that solve real problems.

Thanks for checking out my repo. I hope it helps you.

## License

This project is licensed under the [GNU GPL v3.0](https://www.gnu.org/licenses/gpl-3.0.txt). Feel free to explore, modify, and use responsibly.
