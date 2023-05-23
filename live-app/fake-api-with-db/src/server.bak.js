const express = require('express');
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
    },
    {
      id: 2,
      first_name: 'Jane',
      last_name: 'Smith',
      email: 'janesmith@example.com',
      phone: '9876543210',
      dob: '1995-05-10',
      role: 'admin',
    },
    {
      id: 3,
      first_name: 'Mike',
      last_name: 'Johnson',
      email: 'mikejohnson@example.com',
      phone: '5555555555',
      dob: '1985-09-15',
      role: 'user',
    },
    {
      id: 4,
      first_name: 'Sarah',
      last_name: 'Williams',
      email: 'sarahwilliams@example.com',
      phone: '4444444444',
      dob: '1992-07-20',
      role: 'user',
    },
    {
      id: 5,
      first_name: 'David',
      last_name: 'Brown',
      email: 'davidbrown@example.com',
      phone: '1111111111',
      dob: '1988-03-25',
      role: 'admin',
    },
    {
      id: 6,
      first_name: 'Emily',
      last_name: 'Davis',
      email: 'emilydavis@example.com',
      phone: '2222222222',
      dob: '1994-11-30',
      role: 'user',
    },
    {
      id: 7,
      first_name: 'Daniel',
      last_name: 'Wilson',
      email: 'danielwilson@example.com',
      phone: '6666666666',
      dob: '1987-06-05',
      role: 'user',
    },
    {
      id: 8,
      first_name: 'Olivia',
      last_name: 'Taylor',
      email: 'oliviataylor@example.com',
      phone: '7777777777',
      dob: '1991-09-12',
      role: 'admin',
    },
    {
      id: 9,
      first_name: 'Michael',
      last_name: 'Anderson',
      email: 'michaelanderson@example.com',
      phone: '8888888888',
      dob: '1993-04-15',
      role: 'user',
    },
    {
      id: 10,
      first_name: 'Sophia',
      last_name: 'Thomas',
      email: 'sophiathomas@example.com',
      phone: '9999999999',
      dob: '1998-02-18',
      role: 'user',
    },
  ];
  //creating databases
  pool.query(`CREATE TABLE IF NOT EXISTS user_table (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(255),
    dob DATE,
    role VARCHAR(255)
  )`, (error, results) => {
    if (error) {
      console.error('Error creating table:', error);
    } else {
      console.log('Table user_table created');
  
      // Insert initial data if the user_table is empty
      pool.query('INSERT INTO user_table (id, first_name, last_name, email, phone, dob, role) VALUES ?', [newUsers.map(user => Object.values(user))], (insertError, insertResults) => {
        if (insertError) {
          console.error('Error executing insert query:', insertError);
          
        } else {
          console.log("Done seeding!");
        }
      });
    }
  });

  //inserting data into database
  

const app = express();
app.use(express.json());

app.get('/', (req, res) => {
    res.sendFile(__dirname + '/login.html');
});

app.get('/signup', (req, res) => {
    res.sendFile(__dirname + '/signup.html');
});

// GET all users
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

// GET a specific user by ID
app.get('/users/:userId', (req, res) => {
  const userId = req.params.userId;
  pool.query('SELECT * FROM user_table WHERE id = ?', [userId], (error, results) => {
    if (error) {
      console.error('Error executing query:', error);
      res.status(500).json({ error: 'An error occurred' });
    } else {
      if (results.length === 0) {
        res.status(404).json({ error: 'User not found' });
      } else {
        res.status(200).json(results[0]);
      }
    }
  });
});

// POST a new user
app.post('/users', (req, res) => {
  const { first_name, last_name, email, phone, dob, role } = req.body;
  pool.query(
    'INSERT INTO user_table (first_name, last_name, email, phone, dob, role) VALUES (?, ?, ?, ?, ?, ?)',
    [first_name, last_name, email, phone, dob, role],
    (error, results) => {
      if (error) {
        console.error('Error executing query:', error);
        res.status(500).json({ error: 'An error occurred' });
      } else {
        res.status(201).json({ message: 'User added successfully' });
      }
    }
  );
});

// PUT (update) an existing user
app.put('/users/:userId', (req, res) => {
  const userId = req.params.userId;
  const { first_name, last_name, email, phone, dob, role } = req.body;
  pool.query(
    'UPDATE user_table SET first_name = ?, last_name = ?, email = ?, phone = ?, dob = ?, role = ? WHERE id = ?',
    [first_name, last_name, email, phone, dob, role, userId],
    (error, results) => {
      if (error) {
        console.error('Error executing query:', error);
        res.status(500).json({ error: 'An error occurred' });
      } else {
        if (results.affectedRows === 0) {
          res.status(404).json({ error: 'User not found' });
        } else {
          res.status(200).json({ message: 'User updated successfully' });
        }
      }
    }
  );
});

// DELETE a user by ID
app.delete('/users/:userId', (req, res) => {
  const userId = req.params.userId;
  pool.query('DELETE FROM user_table WHERE id = ?', [userId], (error, results) => {
    if (error) {
      console.error('Error executing query:', error);
      res.status(500).json({ error: 'An error occurred' });
    } else {
      if (results.affectedRows === 0) {
        res.status(404).json({ error: 'User not found' });
      } else {
        res.status(200).json({ message: 'User deleted successfully' });
      }
    }
  });
});

//restore database
app.post('/restore-database', (req, res) => {
    // Clear the user_table by deleting all existing records
    pool.query('DELETE FROM user_table', (deleteError, deleteResults) => {
      if (deleteError) {
        console.error('Error executing delete query:', deleteError);
        res.status(500).json({ error: 'An error occurred while deleting records' });
      } else {
        // Add new users to the user_table
        pool.query('INSERT INTO user_table (id, first_name, last_name, email, phone, dob, role, username, password) VALUES ?', [newUsers.map(user => Object.values(user))], (insertError, insertResults) => {
            if (insertError) {
              console.error('Error executing insert query:', insertError);
            } else {
              console.log('Initial data inserted into user_table');
            }
          });
        }
      });
    });

// Start the Express server
app.listen(8001, () => {
  console.log('Server is running on port 8001');
});