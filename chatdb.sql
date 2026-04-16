CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    pass VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT NOW() NOT NULL
);

CREATE TABLE rooms (
    room_id SERIAL PRIMARY KEY,
    roomname VARCHAR(100) NOT NULL,
    pass VARCHAR(255) NOT NULL,
    multicastip VARCHAR(20) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT NOW() NOT NULL
);

CREATE TABLE messages (
    message_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    room_id INT NOT NULL,
    content TEXT,
    sent_at TIMESTAMP DEFAULT NOW() NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (room_id) REFERENCES rooms(room_id) ON DELETE CASCADE
);

CREATE TABLE room_members (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    room_id INT NOT NULL,
    joined_at TIMESTAMP DEFAULT NOW() NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (room_id) REFERENCES rooms(room_id) ON DELETE CASCADE,

    CONSTRAINT uq_user_room UNIQUE (user_id, room_id)
);


INSERT INTO users (username, pass) VALUES
('nhat', '123456'),
('nhan', '123456'),
('binh', '123456'),
('cuong', '123456'),
('dung', '123456');


INSERT INTO rooms (roomname, pass, multicastip) VALUES
('Phòng Java', '123456', '224.0.0.1'),
('Phòng C#', '123456', '224.0.0.2'),
('Phòng Chat Chung', '123456', '224.0.0.3'),
('Phòng Game', '123456', '224.0.0.4'),
('Phòng Web', '123456', '224.0.0.5');


INSERT INTO room_members (user_id, room_id) VALUES
(1,1),(1,3),(1,4),
(2,1),(2,3),
(3,2),(3,3),
(4,3),(4,4),
(5,1),(5,4);


INSERT INTO messages (user_id, room_id, content) VALUES
(1,1,'Chào mọi người'),
(2,1,'Hello Nhat'),
(5,1,'Java hôm nay học gì vậy'),

(3,2,'Không khó lắm đâu'),

(1,3,'Chào tất cả'),
(2,3,'Hello'),
(3,3,'Hi'),
(4,3,'Xin chào'),

(1,4,'Chơi game không'),
(4,4,'Có mình'),
(5,4,'Tham gia với');


SELECT * FROM users;
SELECT * FROM rooms;
SELECT * FROM room_members;
SELECT * FROM messages;