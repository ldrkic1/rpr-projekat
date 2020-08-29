BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "employee" (
	"id"	INTEGER,
	"username"	TEXT,
	"password"	TEXT,
	PRIMARY KEY("id")
);
INSERT INTO 'employee' VALUES (1, 'admin', 'password');
INSERT INTO 'employee' VALUES (2,'ldrkic1','lamka123');
INSERT INTO 'employee' VALUES (3,'user','password');

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
INSERT INTO 'actor' VALUES (5, 'Kerem', 'Bürsin','Kerem Bürsin je turski glumac, poznat po serijama "Güneşi Beklerken" i "Şeref Meselesi". Izabran je za najboljeg pozorišnog glumca na takmičenju u srednjoj školi u SAD-u. Prije nego što je postao glumac, radio je kao vozač.', '4.6.1987', 'https://i.pinimg.com/originals/c4/4a/59/c44a59c98fbc9695fc4fee226964412f.jpg');
INSERT INTO 'actor' VALUES (6, 'Leyla Lydia','Tuğutlu','Leyla Lydia Tuğutlu rođena je 1989. u Berlinu. Nakon osnovne škole, upisala je časove klavira i violine u turskom Konzervatorijumu. Tokom tog perioda radila je u agenciji za modele. Završila je Anatolijsku srednju školu i napustila konzervatorij za modeliranje. U istom periodu studirala je njemački jezik i književnost na istanbulskom univerzitetu. Govori turski, engleski i njemački jezik. Igrala je najmlađu sestru Kenana Imirzalioglua (Mahir Kara) u seriji "Karadayi".','29.10.1989','https://i.pinimg.com/236x/7d/94/c7/7d94c798fe69b451946f05a38c87e575--famous-celebrities-leyla-lydia-tu%C4%9Futlu.jpg');
INSERT INTO 'actor' VALUES (7, 'Hande', 'Erçel','Hande Erçel je turska televizijska glumica i manekenka. Poznata je po glavnoj ulozi u Aşk Laftan Anlamazu kao Hayat Uzun. 2020. godine Erçel je postavljen u vodeću ulogu u Sen Çal Kapımı zajedno s Keremom Bürsinom.','24.11.1993','https://swall.teahub.io/photos/small/243-2430681_hayat-hd-wallpaper-for-iphone-and-all-android.jpg');
INSERT INTO 'actor' VALUES (8, 'Çağatay','Ulusoy','Çağatay Ulusoy turski je model i glumac bošnjačkog porijekla. Nakon što je završio srednju školu, Ulusoy je prvo počeo studirati na Odjelu za dizajn i uređenje vrta u Istanbulu na Sveučilištu. Tada je radio kao model. 2010. godine pobijedio je u takmičenju modela i proglašen je najboljim modelom Turske','23.9.1990','https://i.pinimg.com/564x/57/9f/d2/579fd20a7d3a441670a78b65a0ddf29d.jpg');
INSERT INTO 'actor' VALUES (9, 'Aras Bulut', 'İynemli','Aras Bulut İynemli je turski glumac. Smatran jednim od najtalentiranijih turskih glumaca, Iynemli je od početka karijere osvojio brojne pohvale za svoju glumu. Od djetinjstva odlikuje se lijepim izgledom, šarmom i karizmom. Proizvođači i agenti filmskih tvrtki odmah su to zabilježili.','25.8.1990','https://i.pinimg.com/564x/05/38/70/053870aa1a3c0bd63e6b428af6706301.jpg');
INSERT INTO 'actor' VALUES (10, 'Shane', 'West','Shannon Bruce Westgarth Snaith poznatiji kao Shane West, američki je glumac, muzičar i tekstopisac. Trenutno ima niz projekata koji čekaju izdanje, uključujući „Gossamer Folds“ i „No Running“. Osim glume, West je nastupio s pank rock bendom Germs i Jonny Was, te Twilight Creeps.','10.6.1978','https://i.pinimg.com/564x/bf/76/55/bf7655b6cc051096b4707487c41bf65d.jpg');
INSERT INTO 'actor' VALUES (11, 'Mandy', 'Moore','Amanda Leigh Moore rođena je u Nashua, New Hampshire, 10. aprila 1984. Tokom djetinjstva, njena porodica se preselila u Orlando na Floridi, gdje je odrasla. Nakon što je vidjela mjuzikl "Oklahoma!", Odlučila je da želi nastaviti karijeru u pjevanju. Dokazala se kao nevjerojatan talent kako u pjevanju tako i u glumi. Njezin je najveći san jednoga dana nastupiti na Broadwayu.', '10.4.1984','https://i.pinimg.com/564x/82/3b/10/823b10f5adeec8bf104971ff265bdabc.jpg');
INSERT INTO 'actor' VALUES (12, 'Erkan Kolçak', 'Köstendil','Erkan Kolçak Köstendil turski je glumac i pevač rođen 1983. godine u Bursi. Pohađao je Univerzitet Mimara Sinana za primenjene umetnosti. Na 47. Međunarodnom filmskom festivalu u Antaliji „Zlatna narandža“ osvojio je posebnu nagradu za scenarij, najbolju fotografiju, najboljeg režisera i za najbolji film.','16.1.1983','https://i.pinimg.com/564x/75/4e/38/754e38483d8dac14b7b5e2b3569cafa9.jpg');
INSERT INTO 'actor' VALUES (13, 'Channing', 'Tatum', 'Channing Matthew Tatum, poznatiji kao Channing Tatum, je američki glumac, producent i bivši model. Radio je kao model i onda se posvetio glumi.','24.4.1980','https://i.pinimg.com/564x/e1/62/24/e162246f11cead312ac2a18ceac804f2.jpg');
INSERT INTO 'actor' VALUES (14, 'Amanda', 'Seyfried','Amanda Michelle Seyfred američka je glumica, pjevačica i pjesnikinja. Karijeru je započela kao dječji model kad joj je bilo jedanaest godina, a sa petnaest godina počela je glumiti, prvo u manjim ulogama, a kasnije u serijama.','3.12.1985', 'https://i.pinimg.com/564x/4a/d5/d6/4ad5d6ad36099924bfb5b5ef5c2059f7.jpg');
CREATE TABLE IF NOT EXISTS "genre" (
	"id"	INTEGER,
	"name"	TEXT,
	PRIMARY KEY("id")
);
INSERT INTO 'genre' VALUES (1,'Drama');
INSERT INTO 'genre' VALUES (2,'Komedija');
INSERT INTO 'genre' VALUES (3,'Akcija');
INSERT INTO 'genre' VALUES (4,'Romantika');
INSERT INTO 'genre' VALUES (5,'Krimi');
INSERT INTO 'genre' VALUES (6,'Triler');
INSERT INTO 'genre' VALUES (7,'Ratni');
CREATE TABLE IF NOT EXISTS "content" (
	"id"	INTEGER,
	"title"	TEXT,
	"year"	INTEGER,
	"director"	TEXT,
	"description"	TEXT,
	"rating"	REAL,
	"image"	TEXT,
	price REAL,
	PRIMARY KEY("id")
);
INSERT INTO 'content' VALUES (1,'Smrt u Sarajevu', 2016, 'Danis Tanović', 'Glavni hotel Evropa u Sarajevu primit će važnu posjetu na godišnjicu atentata na nadvojvoda Franza Ferdinanda, napad koji je pokrenuo svjetski rat. Dok upraviteljica mjesta čeka Jacquesa, posebnog francuskog gosta, radnici u kuhinji pripremaju štrajk jer su mjesecima bez plaće, a novinar snima televizijsku emisiju na krovu.', 6.5, 'https://upload.wikimedia.org/wikipedia/bs/thumb/3/38/Poster_filma_Smrt_u_Sarajevu.jpg/220px-Poster_filma_Smrt_u_Sarajevu.jpg',0);
INSERT INTO 'content' VALUES (2, 'Viza za budućnost', 2002, 'Sulejman Kupusović', 'Porodica bošnjaka živi u stanu srpske Golijanin porodice koja je izbjegla tokom rata. Drama nastaje kada se porodica Golijanin vraća u Sarajevo i namjerava da živi u svom stanu. Porodica Husika koje predvodi čvrsta žena Alma, njen suprug uvijek potlačeni i kokuz Suad i njegova punica Mubera sa još troje djece Nejra, Merima i Belma ne želi izaći iz stana dok im se ne napravi novi stan i ne namjeravaju pustiti Golijanine u stan. Na dan deložacije Husika dolazi rješenje u kojem piše da dvije porodice moraju živjeti zajedno. Tokom zajedničkog stanovanja dolazi do stalnih sukoba, a često ih miri kabadahija Rile, Muberin sin, Almin brat, Suadov šura kojeg se Suad jako boji i Milanov dobar prijatelj.', 6.6, 'https://upload.wikimedia.org/wikipedia/en/thumb/1/14/Viza_za_buducnost.jpg/250px-Viza_za_buducnost.jpg',10.99);
INSERT INTO 'content' VALUES (3, 'Bu Sehir Arkandan Gelecek', 2017, 'Çağrı Vila Lostuvalı', 'Kada je imao samo 4 godine Ali je prisustvovao ubistvu majke od strane čovjeka za koga je vjerovao da mu je otac i taj trenutak mu se zauvek urezao u sjećanje. Majka je uspjela prije smrti da ga sakrije iza stijene ( kamena). Pronašao ga je Rauf kuhar na teretnjaku i prisvojio ga je. Ali je sa njim obišao mnoge zemlje, i dvadest godina posle ima 24. godine, govori 5 jezika, mornar je i profesionalni borac (bokser). Došlo je vrijeme da osveti majku...', 6.2, 'https://upload.wikimedia.org/wikipedia/tr/thumb/0/01/B%C5%9EAG-Afi%C5%9F.jpeg/250px-B%C5%9EAG-Afi%C5%9F.jpeg',12.90);
INSERT INTO 'content' VALUES (4, 'Delibal', 2015, 'Ali Bilgin', 'Dvoje mladih ljudi smišljaju kako da oblikuju svoju budućnost prema ljubavi i / ili karijeri. Tada se jedan od njih odluči da li će živjeti sretno sa voljenom suprugom s rizikom da joj nanese štetu ili se žrtvuje za njezinu sigurnost.', 6.9, 'https://i.pinimg.com/564x/67/d9/35/67d9357f0cd7c4677255aa483ada7976.jpg',0);
INSERT INTO 'content' VALUES (5,'Sen Çal Kapimi',2020,'Yusuf Pirhasan','Zbog okolnosti, Serkan i Eda će se morati pretvarati da su zaručeni. Komedija počinje. A romantika?',7.5,'https://m.media-amazon.com/images/M/MV5BMGQyZDQ4MzktMGZjNy00NDM1LWI4NmItMmQxOGFlYzZlZmRkXkEyXkFqcGdeQXVyMTE5NDAwMjg5._V1_SX562_CR0,0,562,999_AL_.jpg', 0);
INSERT INTO 'content' VALUES (6, 'Içerde', 2016, 'Uluç Bayraktar', 'Priča o dva brata rastrgana jedan od drugog. Sada na suprotnim stranama zakona, braća Sarp i Mert postavljaju se jedan protiv drugog, nesvjesni svog bratstva.',8.0,'https://upload.wikimedia.org/wikipedia/sr/4/46/Icerde.png',15.50);
INSERT INTO 'content' VALUES (7, 'Çukur', 2017, 'Sinan Ozturk', 'Opasni kvart, "Cukur", kojim je upravljala plemićka mafijaška porodica Kočovali. Kad porodici prijeti opasnost da izgubi kontrolu nad Cukurom, njihov najmlađi sin sada se mora vratiti svojoj kući, odakle nikad nije mogao pobjeći.',7.3,'https://i.pinimg.com/originals/b9/b9/fb/b9b9fb99fcfc3f1bb3794297c509a326.jpg',15.50);
INSERT INTO 'content' VALUES (8, 'A Walk to Remember', 2002, 'Adam Shankman', 'Priča o dvoje tinejdžera iz Sjeverne Karoline, Landonu Carteru i Jamieju Sullivanu, koji se udružuju nakon što Landon uđe u nevolju i nateraju se da rade u zajednici.', 7.4, 'https://i.pinimg.com/564x/c7/ef/bc/c7efbc8c900dce91a5d458dbb68725b0.jpg',0);
INSERT INTO 'content' VALUES (9, 'Dear John', 2010, 'Lasse Hallström','Film je snimljen po istoimenom romanu Nicholasa Sparksa. Film je premijerno prikazan u kinima u SAD-u 5. veljače 2010. Film prikazuje život vojnika Johna koji se zaljubljuje u djevojku Savannu te dok je on na službi razmjenjuju pisma.',6.3,'https://i.pinimg.com/564x/73/64/b3/7364b39ba2f7b84e4cc26e54dfdd6f08.jpg',7);
CREATE TABLE IF NOT EXISTS "content_actor" (
	"id"	INTEGER,
	"actor_id"	INTEGER,
	"content_id"	INTEGER,
	PRIMARY KEY("id"),
	FOREIGN KEY("actor_id") REFERENCES "actor"("id"),
	FOREIGN KEY("content_id") REFERENCES "content"("id")
);
INSERT INTO 'content_actor' VALUES (1,1,1);
INSERT INTO 'content_actor' VALUES (2,2,1);
INSERT INTO 'content_actor' VALUES (3,1,2);
INSERT INTO 'content_actor' VALUES (4,3,2);
INSERT INTO 'content_actor' VALUES (5,4,2);
INSERT INTO 'content_actor' VALUES (6,5,3);
INSERT INTO 'content_actor' VALUES (7,6,3);
INSERT INTO 'content_actor' VALUES (8,6,4);
INSERT INTO 'content_actor' VALUES (9,8,4);
INSERT INTO 'content_actor' VALUES (10,5,5);
INSERT INTO 'content_actor' VALUES (11,7,5);
INSERT INTO 'content_actor' VALUES (12,8,6);
INSERT INTO 'content_actor' VALUES (13,9,6);
INSERT INTO 'content_actor' VALUES (14,9,7);
INSERT INTO 'content_actor' VALUES (15,12,7);
INSERT INTO 'content_actor' VALUES (16,10,8);
INSERT INTO 'content_actor' VALUES (17,11,8);
INSERT INTO 'content_actor' VALUES (18,13,9);
INSERT INTO 'content_actor' VALUES (19,14,9);
CREATE TABLE IF NOT EXISTS "movie" (
	"id"	INTEGER,
	"duration_minutes"	INTEGER,
	PRIMARY KEY("id"),
	FOREIGN KEY("id") REFERENCES "content"("id")
);
INSERT INTO 'movie' VALUES (1,85);
INSERT INTO 'movie' VALUES (4, 117);
INSERT INTO 'movie' VALUES (8, 101);
INSERT INTO 'movie' VALUES (9, 108);
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
INSERT INTO 'content_genre' VALUES (3,3,1);
INSERT INTO 'content_genre' VALUES (4,3,3);
INSERT INTO 'content_genre' VALUES (5,3,4);
INSERT INTO 'content_genre' VALUES (6,4,1);
INSERT INTO 'content_genre' VALUES (7,4,4);
INSERT INTO 'content_genre' VALUES (8,5,2);
INSERT INTO 'content_genre' VALUES (9,5,4);
INSERT INTO 'content_genre' VALUES (10,6,1);
INSERT INTO 'content_genre' VALUES (11,6,3);
INSERT INTO 'content_genre' VALUES (12,6,5);
INSERT INTO 'content_genre' VALUES (13,7,3);
INSERT INTO 'content_genre' VALUES (14,7,5);
INSERT INTO 'content_genre' VALUES (15,7,6);
INSERT INTO 'content_genre' VALUES (16,8,1);
INSERT INTO 'content_genre' VALUES (17,8,4);
INSERT INTO 'content_genre' VALUES (18,9,1);
INSERT INTO 'content_genre' VALUES (19,9,4);
INSERT INTO 'content_genre' VALUES (20,9,7);
CREATE TABLE IF NOT EXISTS "serial" (
	"id"	INTEGER,
	"seasons_number"	INTEGER,
	"episodes_per_season"	INTEGER,
	PRIMARY KEY("id"),
	FOREIGN KEY("id") REFERENCES "content"("id")
);
INSERT INTO 'serial' VALUES (2,6,34);
INSERT INTO 'serial' VALUES (3,1,20);
INSERT INTO 'serial' VALUES (5,1,20);
INSERT INTO 'serial' VALUES (6,1,39);
INSERT INTO 'serial' VALUES (7,3,31);
CREATE TABLE IF NOT EXISTS "user" (
	"id"	INTEGER,
	"first_name"	TEXT,
	"last_name"	TEXT,
	"username"	TEXT,
	"password"	TEXT,
	"room_number"	INTEGER,
	"privilege"	INTEGER,
	PRIMARY KEY("id")
);
INSERT INTO 'user' VALUES (1, 'Lamija', 'Drkić', 'lamka', 'lamka123', 1, 0);
INSERT INTO 'user' VALUES (2, 'Dino', 'Merlin', 'merlin', 'svejelaz', 2, 1);
INSERT INTO 'user' VALUES (3, '', '', '14', '3Cs6HrRLJl', 14, 0);
CREATE TABLE IF NOT EXISTS "hotel" (
    "id" INTEGER,
    "rooms_number" INTEGER,
    PRIMARY KEY("id")
);
INSERT INTO "hotel" VALUES (1, 50);
CREATE TABLE IF NOT EXISTS "user_genres" (
    "id" INTEGER,
    "user_id" INTEGER,
    "genre_id" INTEGER,
    PRIMARY KEY ("id"),
    FOREIGN KEY ("user_id") REFERENCES "user"("id"),
    FOREIGN KEY ("genre_id") REFERENCES "genre"("id")
);
INSERT INTO "user_genres" VALUES (1, 2, 3);
INSERT INTO "user_genres" VALUES (2, 2, 5);
CREATE TABLE IF NOT EXISTS "user_requests" (
    "id" INTEGER,
    "user_id" INTEGER,
    "content_id" INTEGER,
    PRIMARY KEY ("id"),
    FOREIGN KEY ("user_id") REFERENCES "user"("id"),
    FOREIGN KEY ("content_id") REFERENCES "content"("id")
);
INSERT INTO "user_requests" VALUES (1, 1, 5);
INSERT INTO "user_requests" VALUES (2, 1, 4);
COMMIT;
