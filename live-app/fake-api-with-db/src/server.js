const express = require('express');
const bcrypt = require('bcrypt');
const pool = require('./config');

const newUsers = [
  {
    id: 1,
    first_name: 'John',
    last_name: 'Doe',
    email: 'johndoe@example.com',
    phone: '1234567890',
    dob: '1990-01-01',
    role: 'user',
    username: 'johndoe',
    password: 'password',
  },
  {
    id: 2,
    first_name: 'Jane',
    last_name: 'Smith',
    email: 'janesmith@example.com',
    phone: '9876543210',
    dob: '1995-05-10',
    role: 'admin',
    username: 'janesmith',
    password: 'password',
  },
  {
    id: 3,
    first_name: 'Mike',
    last_name: 'Johnson',
    email: 'mikejohnson@example.com',
    phone: '5555555555',
    dob: '1985-09-15',
    role: 'user',
    username: 'mikejohnson',
    password: 'password',
  },
  {
    id: 4,
    first_name: 'Sarah',
    last_name: 'Williams',
    email: 'sarahwilliams@example.com',
    phone: '4444444444',
    dob: '1992-07-20',
    role: 'user',
    username: 'sarahwilliams',
    password: 'password',
  },
  {
    id: 5,
    first_name: 'David',
    last_name: 'Brown',
    email: 'davidbrown@example.com',
    phone: '1111111111',
    dob: '1988-03-25',
    role: 'admin',
    username: 'davidbrown',
    password: 'password',
  },
  {
    id: 6,
    first_name: 'Emily',
    last_name: 'Davis',
    email: 'emilydavis@example.com',
    phone: '2222222222',
    dob: '1994-11-30',
    role: 'user',
    username: 'emilydavis',
    password: 'password',
  },
  {
    id: 7,
    first_name: 'Daniel',
    last_name: 'Wilson',
    email: 'danielwilson@example.com',
    phone: '6666666666',
    dob: '1987-06-05',
    role: 'user',
    username: 'danielwilson',
    password: 'password',
  },
  {
    id: 8,
    first_name: 'Olivia',
    last_name: 'Taylor',
    email: 'oliviataylor@example.com',
    phone: '7777777777',
    dob: '1991-09-12',
    role: 'admin',
    username: 'oliviataylor',
    password: 'password',
  },
  {
    id: 9,
    first_name: 'Michael',
    last_name: 'Anderson',
    email: 'michaelanderson@example.com',
    phone: '8888888888',
    dob: '1993-04-15',
    role: 'user',
    username: 'michaelanderson',
    password: 'password',
  },
  {
    id: 10,
    first_name: 'Sophia',
    last_name: 'Thomas',
    email: 'sophiathomas@example.com',
    phone: '9999999999',
    dob: '1998-02-18',
    role: 'user',
    username: 'sophiathomas',
    password: 'password',
  },
];

pool.query(`CREATE TABLE IF NOT EXISTS user_table (
  id INT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(255),
  last_name VARCHAR(255),
  email VARCHAR(255),
  phone VARCHAR(255),
  dob DATE,
  role VARCHAR(255),
  username VARCHAR(255),
  password VARCHAR(255)
)`, (error, results) => {
  if (error) {
    console.error('Error creating table:', error);
  } else {
    console.log('Table user_table created');

    // Insert initial data if the user_table is empty
    pool.query('SELECT COUNT(*) AS count FROM user_table', (selectError, selectResults) => {
      if (selectError) {
        console.error('Error executing select query:', selectError);
      } else {
        const rowCount = selectResults[0].count;
        if (rowCount === 0) {
          pool.query('INSERT INTO user_table (id, first_name, last_name, email, phone, dob, role, username, password) VALUES ?', [newUsers.map(user => Object.values(user))], (insertError, insertResults) => {
            if (insertError) {
              console.error('Error executing insert query:', insertError);
            } else {
              console.log('Initial data inserted into user_table');
            }
          });
        }
      }
    });
  }
});

const app = express();
app.use(express.json());

// ... Existing code ...
app.get('/', (req, res) => {
    res.sendFile(__dirname + '/login.html');
});

app.get('/login', (req, res) => {
    res.sendFile(__dirname + '/login.html');
});

app.get('/logout', (req, res) => {
  res.sendFile(__dirname + '/login.html');
});

app.get('/signup', (req, res) => {
    res.sendFile(__dirname + '/signup.html');
});

app.get('/home', (req, res) => {
  res.sendFile(__dirname + '/home.html');
});

app.get('/edituser', (req, res) =>{
  res.sendFile(__dirname + '/edituser.html');
});

app.get('/restore-database', (req, res) =>{
  res.sendFile(__dirname + '/login.html');
});

//sending data to the database only email and phone number
app.put('/user/:username', (req, res)=>{
  const {email, phone} = req.body;
  const {username} = req.params;
  pool.query(
    'UPDATE user_table SET email = ?, phone = ? WHERE username = ?', 
    [email, phone, username],
     (error, results) => {
      if (error) {
        console.error('Error executing query:', error);
        res.status(500).json({ error: 'An error occurred' });
      } else {
        if (results.length === 0) {
          res.status(404).json({ error: 'User not found' });
        } else {
          res.status(200).json({ message: 'User updated successfully' });
        }
      }
    }
  )
});

// POST a new user
app.post('/users', (req, res) => {
    const { first_name, last_name, email, phone, dob, role, username, password } = req.body;
  
    // Hash the password
    bcrypt.hash(password, 10, (hashError, hashedPassword) => {
      if (hashError) {
        console.error('Error hashing password:', hashError);
        res.status(500).json({ error: 'An error occurred' });
      } else {
        // Insert the user into the user_table
        pool.query(
          'INSERT INTO user_table (first_name, last_name, email, phone, dob, role, username, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)',
          [first_name, last_name, email, phone, dob, role, username, hashedPassword],
          (error, results) => {
            if (error) {
              console.error('Error executing query:', error);
              res.status(500).json({ error: 'An error occurred' });
            } else {
              res.status(201).json({ message: 'User added successfully' });
            }
          }
        );
      }
    });
  });

  // POST request to authenticate a user
app.post('/login', (req, res) => {
    const { username, password } = req.body;
  
    // Retrieve the user from the user_table based on the provided username
    pool.query(
      'SELECT * FROM user_table WHERE username = ?',
      [username],
      (error, results) => {
        if (error) {
          console.error('Error executing query:', error);
          res.status(500).json({ error: 'An error occurred' });
        } else {
          // Check if a user with the provided username exists
          if (results.length === 0) {
            res.status(401).json({ error: 'Invalid username or password' });
          } else {
            const user = results[0];
  
            // Compare the provided password with the hashed password in the database
            bcrypt.compare(password, user.password, (compareError, passwordMatch) => {
              if (compareError) {
                console.error('Error comparing passwords:', compareError);
                res.status(500).json({ error: 'An error occurred' });
              } else {
                // Check if the passwords match
                if (passwordMatch) {
                  res.status(200).json({ message: 'Login successful' });
                } else {
                  res.status(401).json({ error: 'Invalid username or password' });
                }
              }
            });
          }
        }
      }
    );
  });
  
  // GET request to retrieve specific user
  app.get('/users/:id', (req, res) => {
    const { id } = req.params;
    // Retrieve the user from the user_table based on the provided id
    pool.query(
      'SELECT * FROM user_table WHERE id = ?',
      [id],
      (error, results) => {
        if (error) {
          console.error('Error executing query:', error);
          res.status(500).json({ error: 'An error occurred' });
        } else {
          // Check if a user with the provided id exists
          if (results.length === 0) {
            res.status(404).json({ error: 'User not found' });
          } else {
            res.status(200).json(results[0]);
          }
        }
      }
    );
  });

  app.get('/user/:username', (req, res) => {
    const { username } = req.params;
    // Retrieve the user from the user_table based on the provided username
    pool.query(
      'SELECT * FROM user_table WHERE username = ?',
      [username],
      (error, results) => {
        if (error) {
          console.error('Error executing query:', error);
          res.status(500).json({ error: 'An error occurred' });
        } else {
          // Check if a user with the provided username exists
          if (results.length === 0) {
            res.status(404).json({ error: 'User not found' });
          } else {
            res.status(200).json(results[0]);
          }
        }
      }
    );
  });

  //delete user by username
  app.delete("/user/:username", (req, res) =>{
    const {username} = req.params;
    pool.query(
      'DELETE FROM user_table WHERE username = ?',
      [username], 
      (error, result) =>{
        if(error){
          console.error('Error executing query:', error);
          res.status(500).json({ error: 'An error occurred' });  
        } else {
          res.status(200).json({message: 'User deleted successfully'});
        }
      }
    )
  });

  app.post('/restore-database', async (req, res) => {
    try {
      // Clear the user_table by deleting all existing records
      await new Promise((resolve, reject) => {
        pool.query('DELETE FROM user_table', (deleteError, deleteResults) => {
          if (deleteError) {
            console.error('Error executing delete query:', deleteError);
            reject('An error occurred while deleting records');
          } else {
            console.log('Records deleted from user_table');
            resolve();
          }
        });
      });
  
      // Add new users to the user_table
      await new Promise((resolve, reject) => {
        const values = newUsers.map(user => Object.values(user));
        pool.query('INSERT INTO user_table (id, first_name, last_name, email, phone, dob, role, username, password) VALUES ?', [values], (insertError, insertResults) => {
          if (insertError) {
            console.error('Error executing insert query:', insertError);
            reject('An error occurred while inserting new records');
          } else {
            console.log('Initial data inserted into user_table');
            resolve();
          }
        });
      });
  
      res.json({ message: 'Database restored successfully' });
    } catch (error) {
      console.error(error);
      res.status(500).json({ error: 'An error occurred while restoring the database' });
    }
  });

  app.get('/users', (req, res) => {
    pool.query('SELECT * FROM user_table', (error, results) => {
      if (error) {
        console.error('Error executing query:', error);
        res.status(500).json({ error: 'An error occurred' });
      } else {
        res.status(200).json(results);
      }
    });
  });

  


// Start the Express server
app.listen(8001, () => {
  console.log('Server is running on port 8001');
});
