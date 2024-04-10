# Quizzical Mindscape

Welcome to Quizzical Mindscape, an engaging quiz app designed to challenge your intellect and take you on a journey through a captivating mindscape of knowledge.

## Overview

Quizzical Mindscape is built to offer a seamless quiz-taking experience, providing users with a platform to explore a variety of quizzes and test their knowledge on diverse topics.

## Database Collections

The repository contains the initial plans and structure for the Quizzical Mindscape's non-relational database. The database will store information crucial for the app's functionality. Here are the high-level collections:

1. **Users**
   - Fields: userID, username, email, password, profileImage, dateJoined, etc.

2. **Quizzes**
   - Fields: quizID, userID (foreign key), title, description, dateCreated, etc.

3. **Questions**
   - Fields: questionID, quizID (foreign key), content, options, correctAnswer, etc.

4. **UserResponses**
   - Fields: responseID, userID (foreign key), quizID (foreign key), questionID (foreign key), userAnswer, isCorrect, etc.

5. **Leaderboard**
   - Fields: leaderboardID, quizID (foreign key), userID (foreign key), score, dateAchieved, etc.

## Getting Started

To access and understand the database structure, refer to the list of high-level collections in the 'collections.txt' file in the root folder of this repository.



Happy quizzing!
