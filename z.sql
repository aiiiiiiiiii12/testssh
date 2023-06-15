CREATE TABLE Users (
  account VARCHAR(50) PRIMARY KEY,
  email VARCHAR(100) NOT NULL,
  password VARCHAR(50) NOT NULL,
  name VARCHAR(50) NOT NULL,
  gender BIT NOT NULL,
  dateofbirth DATE NOT NULL,
  address VARCHAR(200) NOT NULL,
  CONSTRAINT chk_gender CHECK (gender IN (0, 1))
);

CREATE TABLE roomtypes (
  rtype_id INT PRIMARY KEY,
  number INT NOT NULL,
  price DECIMAL(10,2) NOT NULL
);

CREATE TABLE buildings (
  building_id INT PRIMARY KEY,
  buildingname VARCHAR(50) NOT NULL
);

CREATE TABLE rooms (
  roomid INT PRIMARY KEY,
  roomnumber VARCHAR(10) NOT NULL,
  building_id INT NOT NULL,
  rtype_id INT NOT NULL,
  FOREIGN KEY (building_id) REFERENCES buildings(building_id),
  FOREIGN KEY (rtype_id) REFERENCES roomtypes(rtype_id)
);

CREATE TABLE feedback (
  name VARCHAR(50) NOT NULL,
  address VARCHAR(200) NOT NULL,
  email VARCHAR(100) NOT NULL CHECK (email LIKE '%@gmail.com'),
  feedback VARCHAR(500) NOT NULL
);


CREATE TABLE booking (
  booking_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  room_id INT NOT NULL,
  account VARCHAR(50) NOT NULL,
  in_date DATE NOT NULL,
  notes VARCHAR(500),
  CONSTRAINT fk_room_id
    FOREIGN KEY (room_id)
    REFERENCES rooms (roomid),
  CONSTRAINT fk_account
    FOREIGN KEY (account)
    REFERENCES users (account)
) AUTO_INCREMENT=1000;

INSERT INTO roomtypes (rtype_id, number, price)
VALUES
  (1, 2, 150.00),
  (2, 4, 100.00),
  (3, 6, 50.00)


INSERT INTO buildings (building_id, buildingname)
VALUES
  (1, 'Building A'),
  (2, 'Building B'),
  (3, 'Building C'),
  (4, 'Building D'),
  (5, 'Building E'),
  (6, 'Building F')

INSERT INTO rooms (roomid, roomnumber, building_id, rtype_id)
VALUES
  (1, 'D101', 1, 1),
  (2, 'D102', 1, 1),
  (3, 'D103', 1, 2),
  (4, 'D104', 1, 2),
  (5, 'D105', 2, 3),
  (6, 'D106', 2, 3),
  (7, 'D107', 2, 1),
  (8, 'D108', 2, 2),
  (9, 'D109', 3, 1),
  (10, 'D110', 3, 2),
  (11, 'D201', 4, 1),
  (12, 'D202', 4, 3)

  --reset for all
  update Users set inroom = 0 where inroom=1

delete from booking

update rooms set member =0 where member!=0

CREATE TABLE admin (
   username VARCHAR(50) PRIMARY KEY,
   password VARCHAR(50) NOT NULL,
   email VARCHAR(50) NOT NULL
);

INSERT INTO admin (username, password, email)
VALUES 
    ('admin1', 'password1', 'admin1@example.com'),
    ('admin2', 'password2', 'admin2@example.com'),
    ('admin3', 'password3', 'admin3@example.com'),
    ('admin4', 'password4', 'admin4@example.com'),
    ('admin5', 'password5', 'admin5@example.com');