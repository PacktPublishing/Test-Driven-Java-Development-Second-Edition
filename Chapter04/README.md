Remote Controlled Ship Kata
===========================

Develop an program that moves a ship around Earth seas.
Earth is represented as a grid.

Following requirements should be fulfilled one at a time using TDD approach.

Req. 01: You are given the initial starting point (x, y) of a ship and the direction (n, s, e, w) it is facing.

Req. 02: Implement commands that move the ship forward and backward (f, b).

Req. 03: Implement commands that turn the ship left and right (l, r).

Req. 04: The ship can receive a string with commands ("lrfb" is equivalent to left, right, forward, backwards).

Req. 05: Earth, as any other planet is a sphere. Implement wrapping from one edge of the grid to another.

Req. 06: Not the whole planet consists of seas. Roughly 30% is surface are islands and continents. Implement surface detection before each move to a new position. If a command encounters surface, the ship aborts the move, stays on the current position and reports the obstacle.