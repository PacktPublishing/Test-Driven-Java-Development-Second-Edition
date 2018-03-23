Narrative:
In order to manage the book store collection
As a store administrator
I want to be able to add, update and remove books

Scenario: Book details form should have all fields

Given user is on the books screen
Then field bookId exists
Then field bookTitle exists
Then field bookAuthor exists
Then field bookDescription exists

Scenario: User should be able to create a new book

Given user is on the books screen
When user clicks the button newBook
When user sets values to the book form
When user clicks the button saveBook
Then book is stored

Scenario: User should be able to display book details

Given user is on the books screen
When user selects a book
Then book form contains all data

Scenario: User should be able to update book details

Given user is on the books screen
When user selects a book
When user sets new values to the book form
Then book is updated

Scenario: User should be able to delete a book

Given user is on the books screen
When user selects a book
When user clicks the button deleteBook
Then book is removed
