# Rubik's Cube Simulator

## Overview

This is a simple, command-line Rubik's Cube simulator in Java. You can input a sequence of moves and watch the cube change in real-time. On top of that, it has an extra feature that optimizes the solution, cutting down unnecessary or redundant moves so you can get the cube back to its solved state as quickly as possible. Want a challenge? Use the built-in randomizer to scramble the cube with a series of random moves, then try solving it yourself!

### Key Features:
- Simulate a 3x3 Rubik's Cube with six faces: Red, Blue, Orange, Green, Yellow, White.
- Supports moves like `u, d, r, l, f, b` and their prime versions (`u', d', r', l', f', b'`).
- Randomizer to scramble the cube with a user-defined number of random moves.
- Optimized solver that simplifies your move history by canceling redundant moves or combining repeated ones.

## How It Works

You can either enter your own moves or let the program scramble the cube for you. After making moves, the cube will display its current state. When you're ready, you can ask the program for an optimized solution to bring it back to the solved position.

The moves are pretty straightforward:
- **u**: Rotate the top face clockwise
- **d**: Rotate the bottom face clockwise
- r**: Rotate the right face clockwise
- l: Rotate the left face clockwise
- f: Rotate the front face clockwise
- b: Rotate the back face clockwise

And if you add a `'` (prime) to any of the moves, it’ll rotate counterclockwise (u', d', r', etc.)

## Installation

1. Clone the repo:
   git clone https://github.com/your-username/rubiks-cube-simulator.git

2. Compile:
   javac RubiksCube.java

3. Run Program:
   java RubiksCube
