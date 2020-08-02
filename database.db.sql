BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "employee" (
	"id"	INTEGER,
	"username"	TEXT,
	"password"	TEXT,
	PRIMARY KEY("id")
);
INSERT INTO 'employee' VALUES (1, 'user', 'password');
INSERT INTO 'employee' VALUES (2,'ldrkic1','lamka123');
CREATE TABLE IF NOT EXISTS "user" (
	"id"	INTEGER,
	"first_name"	TEXT,
	"last_name"	TEXT,
	"username"	TEXT,
	"password"	TEXT,
	"room_number"	INTEGER,
	PRIMARY KEY("id")
);
INSERT INTO 'user' VALUES (1, 'Lamija', 'Drkić', 'lamka', 'lamka123', 26);
INSERT INTO 'user' VALUES (2, 'Dino', 'Merlin', 'merlin', 'svejelaz', 50);
CREATE TABLE IF NOT EXISTS "actor" (
	"id"	INTEGER,
	"first_name"	TEXT,
	"last_name"	TEXT,
	"biography"	TEXT,
	"born_date"	TEXT,
	"image"	TEXT,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "genre" (
	"id"	INTEGER,
	"name"	TEXT,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "content" (
	"id"	INTEGER,
	"title"	TEXT,
	"year"	INTEGER,
	"director"	TEXT,
	"description"	TEXT,
	"rating"	REAL,
	"image"	TEXT,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "content_actor" (
	"id"	INTEGER,
	"actor_id"	INTEGER,
	"content_id"	INTEGER,
	PRIMARY KEY("id"),
	FOREIGN KEY("content_id") REFERENCES "content"("id"),
	FOREIGN KEY("actor_id") REFERENCES "actor"("id")
);
CREATE TABLE IF NOT EXISTS "movie" (
	"id"	INTEGER,
	"duration_minutes"	INTEGER,
	PRIMARY KEY("id"),
	FOREIGN KEY("id") REFERENCES "content"("id")
);
CREATE TABLE IF NOT EXISTS "serial" (
	"id"	INTEGER,
	PRIMARY KEY("id"),
	FOREIGN KEY("id") REFERENCES "content"("id")
);
CREATE TABLE IF NOT EXISTS "content_genre" (
	"id"	INTEGER,
	"content_id"	INTEGER,
	"genre_id"	INTEGER,
	PRIMARY KEY("id"),
	FOREIGN KEY("content_id") REFERENCES "content"("id"),
	FOREIGN KEY("genre_id") REFERENCES "genre"("id")
);
COMMIT;
