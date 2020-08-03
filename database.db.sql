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
INSERT INTO 'actor' VALUES (1, 'Izudin', 'Bajrović', 'Izudin Bajrović je bosanskohercegovački pozorišni i filmski glumac. Rođen je 1963. godine u Pljevljima, Crna Gora. Završio je Akademiju scenskih umjetnosti u Sarajevu 1987. u klasi prof. Bore Stjepanovića. Iste godine postaje član Drame Narodnog pozorišta Sarajevo, gdje i danas radi. U periodu 1993-2007. bio je angažovan kao predavač na predmetu Gluma na ASU u Sarajevu. Obavljao je dužnost direktora Drame Narodnog pozorišta Sarajevo od 1997-2001. Odigrao je veliki broj uloga u bosanskohercegovačkim pozorištima, te u Crnoj Gori, Sloveniji, Švicarskoj, Švedskoj.','9.2.1963','https://www3.pictures.zimbio.com/gi/Death+Sarajevo+Premiere+66th+Berlinale+International+VKsEX0bxHcIx.jpg');
INSERT INTO 'actor' VALUES (2, 'Rijad', 'Gvozden', 'Rijad Gvozden, rođen u Gornjem Vakufu, je bosanskohercegovački pozorišni i filmski glumac. Godine 2007. upisuje Akademiju scenskih umjetnosti u Sarajevu. Nakon završenog studija ostvaruje prvu ulogu na filmu Prtljag čiju režiju potpisuje režiser Danis Tanović.','12.2.1988','https://azra.ba/wp-content/uploads/2018/01/rijad-gvozden2.jpg');
INSERT INTO 'actor' VALUES (3, 'Nada', 'Đurevska', 'Nada Đurevska bila je jedna od najznačajnijih bosanskohercegovačkih glumica. Kao šestogodišnja djevojčica 1958. godine, Nada je stigla u Sarajevo. Završila je Osnovnu školu "Hasan Kikić", gdje je bila izuzetan đak, a potom je maturirala u srednjoj medicinskoj školi, nakon koje je upisala i završila glumu. Prvi put je zaigrala u Čehovljevu “Višnjiku”, gdje je tumačila Varju. Niti jedan bosanskohercegovački kultni film, počevši još od "Igmanskog marša" nije mogao proći bez Nadinog pojavljivanja.', '8.1.1952','https://www.sagafilm.com/About_us/Nada_Djurevska/files/stacks-image-5552f2c.jpg');
INSERT INTO 'actor' VALUES (4, 'Enis', 'Bešlagić', 'Enis Bešlagić je bosanskohercegovački pozorišni i filmski glumac. Porijeklom je iz Tešnja. Diplomirao je na Akademiji scenskih umjetnosti u Sarajevu 2001. godine. Ostvario je uloge u više od 30 predstava.', '6.1.1975','https://www.life.ba/wp-content/uploads/2011/03/enis-be%C5%A1lagi%C4%87-305x400.jpg');
CREATE TABLE IF NOT EXISTS "genre" (
	"id"	INTEGER,
	"name"	TEXT,
	PRIMARY KEY("id")
);
INSERT INTO 'genre' VALUES (1,'Drama');
INSERT INTO 'genre' VALUES (2,'Komedija');
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
INSERT INTO 'content' VALUES (1,'Smrt u Sarajevu', 2016, 'Danis Tanović', 'Glavni hotel Evropa u Sarajevu primit će važnu posjetu na godišnjicu atentata na nadvojvoda Franza Ferdinanda, napad koji je pokrenuo svjetski rat. Dok upraviteljica mjesta čeka Jacquesa, posebnog francuskog gosta, radnici u kuhinji pripremaju štrajk jer su mjesecima bez plaće, a novinar snima televizijsku emisiju na krovu.', 6.5, 'https://upload.wikimedia.org/wikipedia/bs/thumb/3/38/Poster_filma_Smrt_u_Sarajevu.jpg/220px-Poster_filma_Smrt_u_Sarajevu.jpg');
INSERT INTO 'content' VALUES (2, 'Viza za budućnost', 2002,'Sulejman Kupusović', 'Porodica bošnjaka živi u stanu srpske Golijanin porodice koja je izbjegla tokom rata u Norvešku pošto je njihov stan uništen u ratu. Drama nastaje kada se porodica Golijanin zbog nostalgije vraća u Sarajevo i namjerava da živi u svom stanu. Porodica Husika koje predvodi čvrsta žena Alma, njen suprug uvijek potlačeni i kokuz Suad i njegova punica Mubera sa još troje djece Nejra, Merima i Belma ne želi izaći iz stana dok im se ne napravi novi stan i ne namjeravaju pustiti Golijanine u stan. Porodica Golijanin predvođena Milanom, penizionisanim diplomatom i političarem, Danicom, njegovom ljubomornom i borbenom ženom i sinom Nešom koje je ostao u Norveškoj, ali se vraća u petoj sezoni. Komšije Husikama su Sena, koja je dosadna i poznata po tome što sve zna i Mirom, srbinom koji ima želju da im dijete i koji se potpuno asimilirao sa ovdašnjim stanovništvom. Nakon što ih Husike ne puštaju u stan oni nakratko prelaze u kod Mire, kojeg nacionalno osvijeste i pokušavaju ga okrenuti protiv Sene. Na dan deložacije Husika dolazi rješenje u kojem piše da dvije porodice moraju živjeti zajedno. Tokom zajedničkog stanovanja dolazi do stalnih sukoba, a često ih miri kabadahija Rile, Muberin sin, Almin brat, Suadov šura kojeg se Suad jako boji i Milanov dobar prijatelj.', 6.6, 'https://upload.wikimedia.org/wikipedia/en/thumb/1/14/Viza_za_buducnost.jpg/250px-Viza_za_buducnost.jpg');

CREATE TABLE IF NOT EXISTS "content_actor" (
	"id"	INTEGER,
	"actor_id"	INTEGER,
	"content_id"	INTEGER,
	PRIMARY KEY("id"),
	FOREIGN KEY("content_id") REFERENCES "content"("id"),
	FOREIGN KEY("actor_id") REFERENCES "actor"("id")
);
INSERT INTO 'content_actor' VALUES (1,1,1);
INSERT INTO 'content_actor' VALUES (2,2,1);
INSERT INTO 'content_actor' VALUES (3,1,2);
INSERT INTO 'content_actor' VALUES (4,3,2);
INSERT INTO 'content_actor' VALUES (5,4,2);

CREATE TABLE IF NOT EXISTS "movie" (
	"id"	INTEGER,
	"duration_minutes"	INTEGER,
	PRIMARY KEY("id"),
	FOREIGN KEY("id") REFERENCES "content"("id")
);
INSERT INTO 'movie' VALUES (1,85);
CREATE TABLE IF NOT EXISTS "serial" (
	"id"	INTEGER,
	PRIMARY KEY("id"),
	FOREIGN KEY("id") REFERENCES "content"("id")
);
INSERT INTO 'serial' VALUES (2);
CREATE TABLE IF NOT EXISTS "content_genre" (
	"id"	INTEGER,
	"content_id"	INTEGER,
	"genre_id"	INTEGER,
	PRIMARY KEY("id"),
	FOREIGN KEY("content_id") REFERENCES "content"("id"),
	FOREIGN KEY("genre_id") REFERENCES "genre"("id")
);
INSERT INTO 'content_genre' VALUES (1,1,1);
INSERT INTO 'content_genre' VALUES (2,2,2);
COMMIT;
