-- Kiem tra xem database da ton tai hay chua, ton tai thi xoa
IF EXISTS (SELECT * FROM sys.databases WHERE name = N'chat_multicast')
BEGIN
    -- Dong tat ca cac ket noi den co so du lieu
    EXECUTE sp_MSforeachdb 'IF ''?'' = ''chat_multicast''
    BEGIN
        DECLARE @sql AS NVARCHAR(MAX) = ''USE [?]; ALTER DATABASE [?] SET SINGLE_USER WITH ROLLBACK IMMEDIATE;''
        EXEC (@sql)
    END'
    -- Xoa tat ca cac ket noi toi co so du lieu (thuc hien qua he thong master)
    USE MASTER
    -- Xoa co so du lieu neu ton tai
    DROP DATABASE chat_multicast
END

CREATE DATABASE chat_multicast;
GO

USE chat_multicast;
GO

CREATE TABLE Users (
    user_id INT IDENTITY PRIMARY KEY,
    userName NVARCHAR(50) NOT NULL UNIQUE,
    pass VARCHAR(255) NOT NULL,
    createdAt DATETIME DEFAULT GETDATE() NOT NULL
);

CREATE TABLE Rooms (
    room_id INT IDENTITY PRIMARY KEY,
    roomName NVARCHAR(100) NOT NULL,
    pass VARCHAR(255) NOT NULL,
    multicastIp VARCHAR(20) NOT NULL UNIQUE,
    createdAt DATETIME DEFAULT GETDATE() NOT NULL
);

CREATE TABLE Messages (
    message_id INT IDENTITY PRIMARY KEY,
    user_id INT NOT NULL,
    room_id INT NOT NULL,
    content NVARCHAR(MAX),
    sentAt DATETIME DEFAULT GETDATE() NOT NULL,

    FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (room_id) REFERENCES Rooms(room_id) ON DELETE CASCADE
);

CREATE TABLE RoomMembers (
    id INT IDENTITY PRIMARY KEY,
    user_id INT NOT NULL,
    room_id INT NOT NULL,
    joinedAt DATETIME DEFAULT GETDATE() NOT NULL,

    FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (room_id) REFERENCES Rooms(room_id) ON DELETE CASCADE,

    CONSTRAINT UQ_user_room UNIQUE (user_id, room_id)
);



INSERT INTO Users (userName, pass) VALUES
(N'nhat', '123456'),
(N'nhan', '123456'),
(N'binh', '123456'),
(N'cuong', '123456'),
(N'dung', '123456');


INSERT INTO Rooms (roomName, pass, multicastIp) VALUES
(N'Phòng Java', '123456', '224.0.0.1'),
(N'Phòng C#', '123456', '224.0.0.2'),
(N'Phòng Chat Chung', '123456', '224.0.0.3'),
(N'Phòng Game', '123456', '224.0.0.4'),
(N'Phòng Web', '123456', '224.0.0.5');



-- nhat tham gia tất cả
INSERT INTO RoomMembers (user_id, room_id) VALUES
(1,1),(1,3),(1,4),

-- an
(2,1),(2,3),

-- binh
(3,2),(3,3),

-- cuong
(4,3),(4,4),

-- dung
(5,1),(5,4);



INSERT INTO Messages (user_id, room_id, content) VALUES
-- Phòng Java
(1,1,N'Chào mọi người'),
(2,1,N'Hello Nhat'),
(5,1,N'Java hôm nay học gì vậy'),

-- Phòng C#
(3,2,N'Không khó lắm đâu'),

-- Phòng Chat Chung
(1,3,N'Chào tất cả'),
(2,3,N'Hello'),
(3,3,N'Hi'),
(4,3,N'Xin chào'),

-- Phòng Game
(1,4,N'Chơi game không'),
(4,4,N'Có mình'),
(5,4,N'Tham gia với');


SELECT * FROM Users;
SELECT * FROM Rooms;
SELECT * FROM RoomMembers;
SELECT * FROM Messages;


SELECT COLUMN_NAME 
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_NAME = 'Users';