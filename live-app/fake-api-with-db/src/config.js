const mysql = require('mysql2');

// Create a MySQL connection pool
const pool = mysql.createPool({
  host: 'localhost',
  user: 'qa',
  password: 'user_123',
  database: 'qa_db',
  port: 3306,
});

module.exports = pool;
