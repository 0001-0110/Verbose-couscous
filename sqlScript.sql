CREATE DATABASE VerboseCouscous
GO

USE VerboseCouscous
GO

CREATE TABLE Users
(
    IdUser INT PRIMARY KEY IDENTITY,
    Username NVARCHAR(30) NOT NULL,
    Permission INT NOT NULL,
    PasswordHash NVARCHAR(256)
)
GO

CREATE PROCEDURE selectUser
    @IdUser INT
AS
BEGIN
    SELECT * FROM Users
    WHERE 
        IdUser = @IdUser
END
GO

CREATE PROCEDURE selectUsers
AS
BEGIN
    SELECT * FROM Users
END
GO

CREATE PROCEDURE createUser
    @Username NVARCHAR(30),
    @Permission INT,
    @PasswordHash NVARCHAR(256),
    @IdUser INT OUTPUT
AS
BEGIN
    INSERT INTO Users 
    VALUES (@Username, @Permission, @PasswordHash)
    SET @IdUser = SCOPE_IDENTITY()
END
GO

CREATE PROCEDURE updateUser
    @Username NVARCHAR(30),
    @Permission INT,
    @PasswordHash NVARCHAR(256),
    @IdUser INT
AS
BEGIN
    UPDATE Users SET
        Username = @Username,
        Permission = @Permission,
        PasswordHash = @PasswordHash
    WHERE
        IdUser = @IdUser
END
GO

CREATE PROCEDURE deleteUser
    @IdUser INT
AS
BEGIN
    DELETE FROM Users
    WHERE 
        IdUser = @IdUser
END
GO

CREATE PROCEDURE clearUsers
AS
BEGIN
    DELETE FROM Users
END
GO

CREATE TABLE Movies
(
    IdMovie INT PRIMARY KEY IDENTITY,
    Title NVARCHAR(64) NOT NULL,
    MovieDescription NVARCHAR(MAX) NULL,
    ReleaseDate INT NOT NULL,
    Duration INT NOT NULL,
    ImagePath NVARCHAR(512) NOT NULL
)
GO

CREATE PROCEDURE selectMovie
    @IdMovie INT
AS
BEGIN
    SELECT * FROM Movies
    WHERE 
        IdMovie = @IdMovie
END
GO

CREATE PROCEDURE selectMovies
AS
BEGIN
    SELECT * FROM Movies
END
GO

CREATE PROCEDURE createMovie
    @Title NVARCHAR(64),
    @MovieDescription NVARCHAR(MAX),
    @ReleaseDate INT,
    @Duration INT,
    @ImagePath NVARCHAR(512),
    @IdMovie INT OUTPUT
AS
BEGIN
    INSERT INTO Movies 
    VALUES (@Title, @MovieDescription, @ReleaseDate, @Duration, @ImagePath)
    SET @IdMovie = SCOPE_IDENTITY()
END
GO

CREATE PROCEDURE updateMovie
    @Title NVARCHAR(64),
    @MovieDescription NVARCHAR(MAX),
    @ReleaseDate INT,
    @Duration INT,
    @ImagePath INT,
    @IdMovie INT
AS
BEGIN
    UPDATE Movies SET
        Title = @Title,
        MovieDescription = @MovieDescription,
        ReleaseDate = @ReleaseDate,
        Duration = @Duration,
        ImagePath = @ImagePath
    WHERE
        IdMovie = @IdMovie
END
GO

CREATE PROCEDURE deleteMovie
    @IdMovie INT
AS
BEGIN
    DELETE FROM Movies
    WHERE 
        IdMovie = @IdMovie
END
GO

CREATE PROCEDURE clearMovies
AS
BEGIN
    DELETE FROM Movies
END
GO

CREATE TABLE Genres
(
    IdGenre INT PRIMARY KEY IDENTITY,
    GenreName NVARCHAR(32) NOT NULL
)
GO

CREATE PROCEDURE selectGenre
    @IdGenre INT
AS
BEGIN
    SELECT * FROM Genres
    WHERE 
        IdGenre = @IdGenre
END
GO

CREATE PROCEDURE selectGenres
AS
BEGIN
    SELECT * FROM Genres
END
GO

CREATE PROCEDURE createGenre
    @GenreName NVARCHAR(32),
    @IdGenre INT OUTPUT
AS
BEGIN
    INSERT INTO Genres 
    VALUES (@GenreName)
    SET @IdGenre = SCOPE_IDENTITY()
END
GO

CREATE PROCEDURE updateGenre
    @GenreName NVARCHAR(32),
    @IdGenre INT
AS
BEGIN
    UPDATE Genres SET
        GenreName = @GenreName
    WHERE
        IdGenre = @IdGenre
END
GO

CREATE PROCEDURE deleteGenre
    @IdGenre INT
AS
BEGIN
    DELETE FROM Genres
    WHERE
        IdGenre = @IdGenre
END
GO

CREATE PROCEDURE clearGenres
AS
BEGIN
    DELETE FROM Genres
END
GO

CREATE TABLE MovieGenre
(
    Id INT PRIMARY KEY IDENTITY,
    IdMovie INT,
    FOREIGN KEY (IdMovie) REFERENCES Movies(IdMovie),
    IdGenre INT,
    FOREIGN KEY (IdGenre) REFERENCES Genres(IdGenre)
)
GO

CREATE PROCEDURE selectMovieGenre
    @Id INT
AS
BEGIN
    SELECT * FROM MovieGenre
    WHERE 
        Id = @Id
END
GO

CREATE PROCEDURE selectMovieGenres
AS
BEGIN
    SELECT * FROM MovieGenre
END
GO

CREATE PROCEDURE createMovieGenre
    @IdMovie INT,
    @IdGenre INT,
    @Id INT OUTPUT
AS
BEGIN
    INSERT INTO MovieGenre
    VALUES (@IdMovie, @IdGenre)
    SET @Id = SCOPE_IDENTITY()
END
GO

CREATE PROCEDURE updateMovieGenre
    @IdMovie INT,
    @IdGenre INT,
    @Id INT
AS
BEGIN
    UPDATE MovieGenre SET
        IdMovie = @IdMovie,
        IdGenre = @IdGenre
    WHERE
        Id = @Id
END
GO

CREATE PROCEDURE deleteMovieGenre
    @Id INT
AS
BEGIN
    DELETE FROM MovieGenre
    WHERE 
        Id = @Id
END
GO

CREATE PROCEDURE clearMovieGenre
AS
BEGIN
    DELETE FROM MovieGenre
END
GO

CREATE TABLE Actors
(
    IdActor INT PRIMARY KEY IDENTITY,
    ActorName NVARCHAR(32) NOT NULL
)
GO

CREATE PROCEDURE selectActor
    @IdActor INT
AS
BEGIN
    SELECT * FROM Actors
    WHERE 
        IdActor = @IdActor
END
GO

CREATE PROCEDURE selectActors
AS
BEGIN
    SELECT * FROM Actors
END
GO

CREATE PROCEDURE createActor
    @ActorName NVARCHAR(32),
    @IdActor INT OUTPUT
AS
BEGIN
    INSERT INTO Actors 
    VALUES (@ActorName)
    SET @IdActor = SCOPE_IDENTITY()
END
GO

CREATE PROCEDURE updateActor
    @ActorName NVARCHAR(32),
    @IdActor INT
AS
BEGIN
    UPDATE Actors SET
        ActorName = @ActorName
    WHERE
        IdActor = @IdActor
END
GO

CREATE PROCEDURE deleteActor
    @IdActor INT
AS
BEGIN
    DELETE FROM Actors
    WHERE 
        IdActor = @IdActor
END
GO

CREATE PROCEDURE clearActors
AS
BEGIN
    DELETE FROM Actors
END
GO

CREATE TABLE MovieActor
(
    Id INT PRIMARY KEY IDENTITY,
    IdMovie INT,
    FOREIGN KEY (IdMovie) REFERENCES Movies(IdMovie),
    IdActor INT,
    FOREIGN KEY (IdActor) REFERENCES Actors(IdActor)
)
GO

CREATE PROCEDURE selectMovieActor
    @Id INT
AS
BEGIN
    SELECT * FROM MovieActor
    WHERE 
        Id = @Id
END
GO

CREATE PROCEDURE selectMovieActors
AS
BEGIN
    SELECT * FROM MovieActor
END
GO

CREATE PROCEDURE createMovieActor
    @IdMovie INT,
    @IdActor INT,
    @Id INT OUTPUT
AS
BEGIN
    INSERT INTO MovieActor 
    VALUES (@IdMovie, @IdActor)
    SET @Id = SCOPE_IDENTITY()
END
GO

CREATE PROCEDURE updateMovieActor
    @IdMovie INT,
    @IdActor INT,
    @Id INT
AS
BEGIN
    UPDATE MovieActor SET
        IdMovie = @IdMovie,
        IdActor = @IdActor
    WHERE
        Id = @Id
END
GO

CREATE PROCEDURE deleteMovieActor
    @Id INT
AS
BEGIN
    DELETE FROM MovieActor
    WHERE 
        Id = @Id
END
GO

CREATE PROCEDURE clearMovieActor
AS
BEGIN
    DELETE FROM MovieActor
END
GO

CREATE TABLE Directors
(
    IdDirector INT PRIMARY KEY IDENTITY,
    DirectorName NVARCHAR(32) NOT NULL
)
GO

CREATE PROCEDURE selectDirector
    @IdDirector INT
AS
BEGIN
    SELECT * FROM Directors
    WHERE 
        IdDirector = @IdDirector
END
GO

CREATE PROCEDURE selectDirectors
AS
BEGIN
    SELECT * FROM Directors
END
GO

CREATE PROCEDURE createDirector
    @DirectorName NVARCHAR(32),
    @IdDirector INT OUTPUT
AS
BEGIN
    INSERT INTO Directors 
    VALUES (@DirectorName)
    SET @IdDirector = SCOPE_IDENTITY()
END
GO

CREATE PROCEDURE updateDirector
    @DirectorName NVARCHAR(32),
    @IdDirector INT
AS
BEGIN
    UPDATE Directors SET
        DirectorName = @DirectorName
    WHERE
        IdDirector = @IdDirector
END
GO

CREATE PROCEDURE deleteDirector
    @IdDirector INT
AS
BEGIN
    DELETE FROM Directors
    WHERE
        IdDirector = @IdDirector
END
GO

CREATE PROCEDURE clearDirectors
AS
BEGIN
    DELETE FROM Directors
END
GO

CREATE TABLE MovieDirector
(
    Id INT PRIMARY KEY IDENTITY,
    IdMovie INT NOT NULL,
    FOREIGN KEY (IdMovie) REFERENCES Movies(IdMovie),
    IdDirector INT NOT NULL,
    FOREIGN KEY (IdDirector) REFERENCES Directors(IdDirector)
)
GO

CREATE PROCEDURE selectMovieDirector
    @Id INT
AS
BEGIN
    SELECT * FROM MovieDirector
    WHERE 
        Id = @Id
END
GO

CREATE PROCEDURE selectMovieDirectors
AS
BEGIN
    SELECT * FROM MovieDirector
END
GO

CREATE PROCEDURE createMovieDirector
    @IdMovie INT,
    @IdDirector INT,
    @Id INT OUTPUT
AS
BEGIN
    INSERT INTO MovieDirector 
    VALUES (@IdMovie, @IdDirector)
    SET @Id = SCOPE_IDENTITY()
END
GO

CREATE PROCEDURE updateMovieDirector
    @IdMovie INT,
    @IdDirector INT,
    @Id INT
AS
BEGIN
    UPDATE MovieDirector SET
        IdMovie = @IdMovie,
        IdDirector = @IdDirector
    WHERE
        Id = @Id
END
GO

CREATE PROCEDURE deleteMovieDirector
    @Id INT
AS
BEGIN
    DELETE FROM MovieDirector
    WHERE 
        Id = @Id
END
GO

CREATE PROCEDURE clearMovieDirector
AS
BEGIN
    DELETE FROM MovieDirector
END
GO

CREATE PROCEDURE clearAll
AS
BEGIN
    EXEC clearUsers
    EXEC clearMovies
    EXEC clearGenres
    EXEC clearActors
    EXEC clearMovieActor
    EXEC clearDirectors
    EXEC clearMovieDirector
END
GO
