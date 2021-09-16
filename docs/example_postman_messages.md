####Account Service (Create Account)
```
http://localhost:8501/account

{
    "username" : "furkang",
    "name" : "Furkan",
    "surname" : "Guzel",
    "email" : "software@gmail.com",
    "birthDate" : "1999-01-01"
}
```

####Ticket Service (Create Ticket)
http://localhost:8502/ticket
```
{
    "description" : "Test Ticket 1",
    "notes" : "Test Ticket 1 - Docker Compose Exception",
    "assignee" : "furkang",
    "priorityType" : "URGENT",
    "ticketStatus" : "OPEN",
    "ticketDate" : "1999-01-01"
}
```