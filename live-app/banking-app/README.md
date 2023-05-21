## Objective
A dummy banking app to automate with Selenium!!

## Demo Account Credentials

Use the following credentials to test the app.

### For Admin
```
email: admin@admin.com
password: abc123
```

### For Client
```
email: client@client.com
password: abc123
```

### Start the app using docker (Recommended)
```
1. Navigate to the ./live-app/banking-app dir and run below command
2. docker build -t banking-app:latest .
3. Navigate to the ./DockerFiles, run below command
4. docker-compose -f start_bank_app.yml up
5. Now access the server at http://localhost:3000/banking-app

Note: For login use above credentials!!
```

### Start the app using NodeJs
```
1. Navigate to the ./live-app/banking-app dir and run below command
2. npm install && npm start

It will prompt you to open the browser, once you have access, you can use above credentials
```
