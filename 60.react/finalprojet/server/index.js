const express = require('express');
const mysql = require('mysql');
const cors = require('cors');
const bodyParser = require('body-parser');
const multer = require('multer');
const app = express();
const PORT = process.env.PORT || 8001;

const db = mysql.createPool({
    host: "localhost",
    user: "root",
    password: "admin1234",
    database: "reviewboard"
});

const storage = multer.diskStorage({
    destination: function (req, file, cb) {
        cb(null, 'uploads/')
    },
    filename: function (req, file, cb) {
        cb(null, Date.now() + file.originalname)
    }
});

const upload = multer({ storage: storage });

app.use('/uploads', express.static('uploads'));
app.use(cors());
app.use(express.json());
app.use(bodyParser.urlencoded({ extended: true }));

app.get("/api/get", (req, res) => {
    const sqlQuery = "SELECT * FROM rboard;";
    db.query(sqlQuery, (err, result) => {
        res.send(result);
    });
});

app.post('/api/submitReview', upload.single('image'), (req, res) => {
    const title = req.body.title;
    const content = req.body.content;
    let imageUrl = req.file ? `${req.protocol}://${req.get('host')}/uploads/${req.file.filename}` : null;

    const sqlQuery = "INSERT INTO rboard (title, content, image_url) VALUES (?, ?, ?)";
    db.query(sqlQuery, [title, content, imageUrl], (err, result) => {
        if (err) {
            console.error(err);
            res.status(500).send('Error while inserting data');
        } else {
            res.send('Review submitted successfully with image!');
        }
    });
});

app.post('/api/updateReview/:id', upload.single('image'), (req, res) => {
    const id = req.params.id;
    const title = req.body.title;
    const content = req.body.content;
    let imageUrl = req.file ? `${req.protocol}://${req.get('host')}/uploads/${req.file.filename}` : null;

    const sqlQuery = "UPDATE rboard SET title = ?, content = ?, image_url = ? WHERE id = ?";
    db.query(sqlQuery, [title, content, imageUrl, id], (err, result) => {
        if (err) {
            console.error(err);
            res.status(500).send('Error while updating data');
        } else {
            res.send('Review updated successfully with image!');
        }
    });
});

app.delete('/api/deleteReview/:id', (req, res) => {
    const id = req.params.id;
    const sqlQuery = "DELETE FROM rboard WHERE id = ?";
    db.query(sqlQuery, [id], (err, result) => {
        if (err) {
            console.error(err);
            res.status(500).send('Error while deleting data');
        } else {
            res.send('Review deleted successfully');
        }
    });
});

app.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`);
});
